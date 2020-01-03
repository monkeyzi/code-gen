layui.extend({
    npms: 'lay/modules/npms',
    validate: 'lay/modules/validate'
}).define(['npms', 'conf'], function (exports) {
    layui.febs.initPage();
    console.log("\n 欢迎使用动态数据源代码生成系统","color: #fff; font-size: .84rem;background: #366ed8; padding:5px 0;", "font-size: .84rem;background: #fff; border: 2px solid #b0e0a8;border-left: none; padding:3px 0; font-size: .84rem;background: #fcf9ec; padding:5px 0;margin-left: 8px");
    exports('index', {});
});