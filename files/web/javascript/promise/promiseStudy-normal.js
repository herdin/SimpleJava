class PromiseStudy {
    constructor() {
        this.items = [
            {   itemName : "itemName0",
                itemDesc : "itemDesc0",
                itemVal : 0,
                itemError : false
            },
            {   itemName : "itemName1",
                itemDesc : "itemDesc1",
                itemVal : 1,
                itemError : false
            },
            {   itemName : "itemName2",
                itemDesc : "itemDesc2",
                itemVal : 2,
                itemError : false
            },
            {   itemName : "itemName3",
                itemDesc : "itemDesc3",
                itemVal : 3,
                itemError : false
            },
        ];
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
        if(ps.items[targetItemIndex].itemError) {
            reject(data);
        } else {
            resolve(data);
        }
    });
};
var itemLoad = function(data) {
    ps.debugLog("itemLoad()");
    ps.debugDir(data);
    var targetItemIndex = data.targetItemIndex;
    return new Promise(function(resolve, reject){
        if(ps.items[targetItemIndex].itemError) {
            reject(data);
        } else {
            resolve(data);
        }
    });
};
var itemProcess = function(data) {
    ps.debugLog("itemProcess()");
    ps.debugDir(data);
    var targetItemIndex = data.targetItemIndex;
    return new Promise(function(resolve, reject){
        if(ps.items[targetItemIndex].itemError) {
            reject(data);
        } else {
            resolve(data);
        }
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