/*
 * 患者信息-个人史 + 过敏史
 * 
 * @author: Ricky
 */

es.Models.Form12 = Backbone.Model.extend({
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
                data: {}
            }
        });
    }
});
