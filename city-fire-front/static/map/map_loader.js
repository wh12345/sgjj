(function() {
    window.BMap_loadScriptTime = (new Date).getTime();
    window.BMap = window.BMap || {};
    window.BMap.apiLoad = function () {
      delete window.BMap.apiLoad;
    };
    let s = document.createElement('script');
    s.src = '/static/map/map.js';
    document.body.appendChild(s);
})
();