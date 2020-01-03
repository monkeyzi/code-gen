layui.define([],function(exports){
    exports('api',{
        getMenus: 'menu/' + currentUser.id + '?invalid_ie_cache=' + new Date().getTime()
    });
});