layui.define(function(exports) {
  exports('conf', {
    container: 'npms',
    containerBody: 'mpms-body',
    v: '2.0',
    base: layui.cache.base,
    css: layui.cache.base + 'css/',
    views: layui.cache.base + 'views/',
    viewLoadBar: true,
    debug: layui.cache.debug,
    name: 'npms',
    entry: '/index',
    engine: '',
    eventName: 'npms-event',
    tableName: 'npms',
    requestUrl: './'
  })
});
