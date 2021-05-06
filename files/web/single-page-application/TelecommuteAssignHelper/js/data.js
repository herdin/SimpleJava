logger.name = 'data.js';
logger.log('hello');

const WORK_TYPE = {
    A: {
        workDay: ['월', '화', '수', '목', '금'],
        countOfStaffWhoWorksAtHomePerDay: 5
    },
    B: {
        workDay: ['화', '수', '목', '금', '토'],
        countOfStaffWhoWorksAtHomePerDay: 1
    },
};
const sampleInput = {
    targetMonth : new Date().getMonth()+2,
    duration: ['2021-05-01', '2021-05-30'],
    holiday: [
        '2021-05-01',
        '2021-05-05',
        '2021-05-19',
    ],
    staff: [
        { name: '깅즈너01', workType: 'A' },
        { name: '깅즈너02', workType: 'A' },
        { name: '깅즈너03', workType: 'A' },
        { name: '깅즈너04', workType: 'A' },
        { name: '깅즈너05', workType: 'A' },
        { name: '깅즈너06', workType: 'A' },
        { name: '깅즈너07', workType: 'A' },
        { name: '깅즈너08', workType: 'A' },
        { name: '깅즈너09', workType: 'A' },
        { name: '깅즈너10', workType: 'A' },
        { name: '깅즈너11', workType: 'A' },
        { name: '깅즈너12', workType: 'A' },
        { name: '깅즈너13', workType: 'A' },
        { name: '깅즈너14', workType: 'A' },

        { name: '보호구01', workType: 'B' },
        { name: '보호구02', workType: 'B' },
        { name: '보호구03', workType: 'B' },
        { name: '보호구04', workType: 'B' },
        { name: '보호구05', workType: 'B' },
        { name: '보호구06', workType: 'B' },
    ]
};