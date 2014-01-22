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
        
        console.log("获取CRF基本信息-请求", args);

        util.ajax.run({
            url: "",
            data: args,
            success: function(response) {
                console.log("获取CRF基本信息-响应", response);
                
                me.set({data: response});
            },
            mock: MOCK,
            mockData: {
                success: true,
                no: "333-1234",
                abbr: "XM",
                progress: "10%"
            }
        });
    }
});
