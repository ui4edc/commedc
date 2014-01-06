/*
 * 全局统计
 * 
 * @author: Ricky
 */

es.Models.Stat = Backbone.Model.extend({
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
            mock: MOCK,
            mockData: {
                success: true,
                total: 100,
                data: []
            }
        });
    }
});
