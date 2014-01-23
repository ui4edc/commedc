/*
 * 病症情况
 * 
 * @author: Ricky
 */

es.Models.Form20 = Backbone.Model.extend({
    defaults: {
        data: null
    },
    
    getData: function(args) {
        var me = this;
        me.set({data : null}, {silent : true});
        
        console.log("crf/getDeseaseInfo.do-请求", args);
        
        util.ajax.run({
            url: "crf/getDeseaseInfo.do",
            data: args,
            success: function(response) {
                console.log("crf/getDeseaseInfo.do-响应", response);
                
                me.set({data: response});
            },
            mock: MOCK,
            mockData: {
                success: true,
                data: {
                    disease1: "1,2,3,4,5",
                    fy1: "1,2",
                    fy2: "1,2",
                    disease2: "1,2,3,4,5",
                    disease3: "1,2",
                    diseasetxt: "",
                    diagnosis: "",
                    zy: 7,
                    zytxt: ""
                }
            }
        });
    }
});
