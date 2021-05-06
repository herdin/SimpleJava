logger.name = 'index.js';
logger.log('hello');

const ELEMENT_ID = {
    INPUT: 'input',
    OUTPUT: 'output',
    STATISTIC: 'statistic',
    BUTTON_PROCESS: 'process',
    PROGRESS_CONTAINER: 'progress-container',
    PROGRESS_BAR: 'progress-bar',
    ALERT_CONTAINER: 'alert-container',
    ALERT_CONTENT: 'alert-content',
};

function init() {
    let inputSampleJsonStr = JSON.stringify(sampleInput, null, 4);
    logger.log('input sample json string', inputSampleJsonStr);
    document.querySelector('#' + ELEMENT_ID.INPUT).value = inputSampleJsonStr;
    document.querySelector('#' + ELEMENT_ID.BUTTON_PROCESS).addEventListener('click', () => {
        initProcess();
        try {
            let inputObject = JSON.parse(document.querySelector('#' + ELEMENT_ID.INPUT).value);
            logger.log('parsed input object', inputObject);
            process(inputObject);
        } catch(e) {
            showAlertWith(e);
        }
    });
}
function initProcess() {
    document.querySelector('#' + ELEMENT_ID.OUTPUT).value = '';
    setProgressBar(0);
    document.querySelector('#' + ELEMENT_ID.STATISTIC).value = '';
    hideAlert();
}

function process(inputObject) {
    logger.name = logger.name + ', process';
    logger.log('check input', inputObject);
    setProgressBar(10);

    //휴일 세팅
    let holidaySet = new Set();
    inputObject.holiday.forEach(holidayStr => {
        holidaySet.add(holidayStr);
    });
    logger.log('holiday set', holidaySet);
    setProgressBar(15);

    if(!inputObject.staff || inputObject.staff.length <= 0) throw new Error('staff must be exists');

    //재택 배정 대상 일자 세팅
    let targetDayArr = [];
    let targetStartDate = new Date(new Date().getFullYear(), inputObject.targetMonth-1, 1);
    let targetEndDate = new Date(new Date().getFullYear(), inputObject.targetMonth, 1);
    targetEndDate = new Date(targetEndDate.getTime() - 1000*60*60*24); //다음달 1일에서 하루 를 빼서 마지막 날을 만든다.

    if(inputObject.duration && inputObject.duration.length == 2) {
        targetStartDate = strToDate(inputObject.duration[0]);
        targetEndDate = strToDate(inputObject.duration[1]);
        if(targetStartDate - targetEndDate >= 0) throw new Error(`duration error`);
    }

    let targetDay = new Date(targetStartDate.getTime());
    logger.log('target day', targetDay);
    while(targetDay <= targetEndDate) {
        if(isNotHoliDay(holidaySet, targetDay) && isWorkDay(targetDay)) {
            targetDayArr.push(new Date(targetDay.getFullYear(), targetDay.getMonth(), targetDay.getDate()));
        }
        targetDay.setDate(targetDay.getDate()+1);
    }
    logger.log('target work day', targetDayArr);
    logger.log('target work day length', targetDayArr.length);
    setProgressBar(20);

    let outputObject = {};
    outputObject.targetMonth = inputObject.targetMonth;
    outputObject.holiday = inputObject.holiday;

    let statisticObject = {};
    statisticObject.totalWorkDay = targetDayArr.length;
    statisticObject.totalStaffCount = inputObject.staff.length;
    statisticObject.assignment = {};
    inputObject.staff.forEach(staffItem => {
        statisticObject.assignment[staffItem.name] = 0;
    });

    //근무 타입 loop
    for(let key in WORK_TYPE) {
        let workType = WORK_TYPE[key];
        logger.log('work type', workType);
        let workAtHomeDayPerDay = workType.countOfStaffWhoWorksAtHomePerDay;
        let targetDayLength = targetDayArr.length;
        let poolLength = workAtHomeDayPerDay * targetDayLength;

        let pool = [];
        let staff = inputObject.staff;
        let typeStaff = staff.filter(item => item.workType === key);
        if(typeStaff.length <= 0) continue;
        // poolLength = poolLength * typeStaff.length;
        let staffIndex = Math.floor(Math.random()*typeStaff.length);
        for(let i=0; i<poolLength; i++) {
            pool.push({staff: typeStaff[staffIndex++], seed: Math.floor(Math.random()*poolLength)});
            if(staffIndex >= typeStaff.length) staffIndex = 0;
        }
        pool.sort((s1, s2) => s1.seed - s2.seed);
        pool = pool.map(item => item.staff.name);

        for(targetDay of targetDayArr) {
            let targetDayStr = dateToStr(targetDay);
            if(outputObject[targetDayStr] === undefined) {
                outputObject[targetDayStr] = [];
            }
            let pick = pickFromPool(pool, workAtHomeDayPerDay);
            // logger.log(`target day ${targetDayStr}, pick map to name`, pick);
            outputObject[targetDayStr] = outputObject[targetDayStr].concat(pick);
            updateStatistic(pick, statisticObject);
        }
    }

    logger.log('output object', outputObject);
    setProgressBar(100);
    document.querySelector('#' + ELEMENT_ID.OUTPUT).value = JSON.stringify(outputObject, null, 4);
    logger.log('statistic object', statisticObject);
    document.querySelector('#' + ELEMENT_ID.STATISTIC).value = JSON.stringify(beautifyStatistic(statisticObject), null, 4);

}
function isNotHoliDay(holidaySet, date) {
    return !holidaySet.has(dateToStr(date));
}
function dateToStr(date) {
    return `${date.getFullYear()}-${(''+(date.getMonth()+1)).padStart(2, '0')}-${(''+date.getDate()).padStart(2, '0')}`;
}
function strToDate(dateStr) {
    let split = dateStr.split('-');
    if(!split || split.length != 3) throw new Error(`can not parse date string -> ${dateStr}`);
    [year, month, day] = split;
    return new Date(Number(year), Number(month-1), Number(day));
}
function isWorkDay(date) {
    return date.getDay() !== 6 && date.getDay() !== 0;
}
function setProgressBar(width) {
    document.querySelector('#' + ELEMENT_ID.PROGRESS_BAR).style.width = width + '%';
}
function pickFromPool(pool, pickSize) {
    let pickSet = new Set();
    let pickCount = 0;
    let pickLimit = 100;
    if(pool.length <= pickSize) {
        let pick = pool.slice(0);
        pool.length = 0;
        return pick;
    }
    while(pickSet.size < pickSize && pickCount < pickLimit) {
        pickCount++;
        let last = pool.pop();
        if(!pickSet.has(last)) {
            pickSet.add(last);
        } else {
            pool.unshift(last);
        }
    }
    let iter = pickSet.keys();
    let result = iter.next();
    let pick = [];
    while(!result.done) {
        pick.push(result.value);
        result = iter.next();
    }
    return pick;
}

function updateStatistic(pick, statisticObject) {
    if(statisticObject.assignment === undefined) statisticObject.assignment = {};

    for(let item of pick) {
        if(statisticObject.assignment[item] === undefined) {
            statisticObject.assignment[item] = 1;
        } else {
            statisticObject.assignment[item] = statisticObject.assignment[item]+1;
        }
    }
}
function beautifyStatistic(statisticObject) {
    let beautifyObject = {};
    Object.assign(beautifyObject, statisticObject);
    beautifyObject.assignmentCopy = beautifyObject.assignment;
    delete beautifyObject.assignment;
    beautifyObject.assignment = [];

    for(let key in beautifyObject.assignmentCopy) {
        beautifyObject.assignment.push({name: key, count: beautifyObject.assignmentCopy[key]});
    }
    delete beautifyObject.assignmentCopy;
    beautifyObject.assignment.sort((b1, b2) => {
        let comparingCount = b1.count - b2.count;
        if(comparingCount !== 0) return comparingCount;
        else {
            if(b1.name < b2.name) {
                return -1;
            } else return 1;
        }
    });
    return beautifyObject;
}

function showAlertWith(content) {
    document.querySelector('#' + ELEMENT_ID.ALERT_CONTAINER).style.display = '';
    document.querySelector('#' + ELEMENT_ID.ALERT_CONTENT).innerHTML = content;

    document.querySelector('#' + ELEMENT_ID.PROGRESS_CONTAINER).style.display = 'none';

}
function hideAlert() {
    document.querySelector('#' + ELEMENT_ID.ALERT_CONTAINER).style.display = 'none';
    document.querySelector('#' + ELEMENT_ID.ALERT_CONTENT).innerHTML = '';

    document.querySelector('#' + ELEMENT_ID.PROGRESS_CONTAINER).style.display = '';
}
init();