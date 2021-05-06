//has dependency with logger
function includeResource(filePath, resourceMaker) {
    if(Array.from(document.querySelectorAll('script')).filter(script => script.src.indexOf(filePath) >= 0).length > 0) {
        logger.log('alreay load script', filePath);
        return;
    }
    let done = false;
    let promise = new Promise((resolve, reject) => {
        let resource = resourceMaker(filePath);
        resource.onload = function() {
            if(!done && (!this.readyState || this.readyState == 'loaded' || this.readyState == 'complete')) {
                done = true;
                logger.log('resource load done', filePath);
                resolve();
            }
        };
        document.head.appendChild(resource);
    });
    return promise;
}

function script(filePath) {
    let script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = filePath;
    return script;
}

function css(filePath) {
    let link = document.createElement('link');
    link.type = 'text/css';
    link.rel = 'stylesheet';
    link.href = filePath;
    return link;
}

const dependencyPromise = Promise.resolve()
    .then(() => {
        //css here
        includeResource('vendor/bootstrap/bootstrap.min.css', css);
        return Promise.resolve();
    })
    .then(() =>
        Promise.all([
            includeResource('vendor/bootstrap/bootstrap.bundle.min.js', script),
            includeResource('js/data.js', script),
        ])
    )
    .then(() => includeResource('js/index.js', script))
    .then(() => logger.log('all script load done'))
    .catch(err => logger.log('script load error -> ', err))
