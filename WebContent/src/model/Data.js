/*
 * 数据管理
 * 
 * @author: Ricky
 */

es.Models.Data = Backbone.Model.extend({
    defaults: {
        data: null
    },
    
    getData: function(args) {
        var me = this;
        me.set({data : null}, {silent : true});
        
        util.ajax.run({
            url: "",
            data: args,
            success: function(response) {
                me.set({data: response});
            },
            mock: true,
            mockData: {
                success: true,
                errorMsg: "errorMsg",
                total: 100,
                data: [
                    {id: 1, name: "小明"},
                    {id: 2, name: "小红"}
                ]
            }
        });
    }
});
