class PromiseStudy {
    constructor() {
        this.items = [
            {   itemName : "itemName0",
                itemDesc : "itemDesc0",
                itemVal : 0
            },
            {   itemName : "itemName1",
                itemDesc : "itemDesc1",
                itemVal : 1
            },
            {   itemName : "itemName2",
                itemDesc : "itemDesc2",
                itemVal : 2
            },
            {   itemName : "itemName3",
                itemDesc : "itemDesc3",
                itemVal : 3
            },
        ];
        this.itemProcessStep = 0;
        this.itemProcessError = false;
        this.debug = true;
        this.debugLog = function(data) {
            if(this.debug) {
                console.log(data);
            }
        };
        this.debugDir = function(data) {
            if(this.debug) {
                console.dir(data);
            }
        };
    }
}

var ps = new PromiseStudy();

var itemLoadInit = function(data) {
    ps.debugLog("itemLoadInit()");
    ps.debugDir(data);
    var targetItemIndex = data.targetItemIndex;
    return new Promise(function(resolve, reject){
        var tick, tock;
        var tickTockDelay = 1000;
        tock = function() {
            var logText = 'itemLoadInit() : current step : ' + ps.itemProcessStep + ' : target index : ' + targetItemIndex;
            if(targetItemIndex == ps.itemProcessStep) {
                /* dependent code here */
                logText = logText + ' : proper step.';
                ps.debugLog(logText);
                resolve(data);
            } else if(ps.itemProcessError) {
                logText = logText + ' : something worong.';
                reject(data);
            } else {
                logText = logText + ' : wait for proper step.';
                tick = setTimeout(tock, tickTockDelay);
            }
        };
        tick = setTimeout(tock, tickTockDelay);
    });
};
var itemLoad = function(data) {
    ps.debugLog("itemLoad()");
    ps.debugDir(data);
    return new Promise(function(resolve, reject){
        resolve(data);
    });
};
var itemProcess = function(data) {
    ps.debugLog("itemProcess()");
    ps.debugDir(data);
    return new Promise(function(resolve, reject){
        ps.itemProcessStep++;
        resolve(data);
    });
};

var pv, i;
for(i=0; i<ps.items.length; i++) {
    pv = itemLoadInit({targetItemIndex:i})
    .then(itemLoad)
    .then(itemProcess)
    .then(function(data){
        ps.debugLog("item process done.");
        ps.debugDir(data);
    })
    .catch(function(data){
        ps.debugLog("catch exception.");
        ps.debugDir(data);
    });
}