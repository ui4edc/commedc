/*
 * CRF
 * 
 * @author: Ricky
 */

es.Models.CRF = Backbone.Model.extend({
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
                no: "333-1234",
                abbr: "XM",
                progress: "10%"
            }
        });
    }
});
