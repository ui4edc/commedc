/*
 * 账户管理
 * 
 * @author: Ricky
 */

es.Models.Account = Backbone.Model.extend({
    defaults: {
        data: null
    },
    
    getData: function(args) {
        var me = this;
        me.set({data : null}, {silent : true});
        
        console.log("获取列表-请求", args);
        
        util.ajax.run({
            url: "",
            data: args,
            success: function(response) {
                console.log("获取列表-响应", response);
                
                me.set({data: response});
            },
            mock: MOCK,
            mockData: {
                success: true,
                total: 100,
                data: [
                    {id: 1, name: "小明", admin: "小明爸爸", contact: "13500112233", auth: "管理员"},
                    {id: 2, name: "小红", admin: "小红妈妈", contact: "13500112233", auth: "管理员"}
                ]
            }
        });
    }
});
