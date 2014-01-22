/*
 * 患者信息-既往史
 * 
 * @author: Ricky
 */

es.Models.Form13 = Backbone.Model.extend({
    defaults: {
        data: null
    },
    
    getData: function(args) {
        var me = this;
        me.set({data : null}, {silent : true});
        
        console.log("crf/getPersonHistory.do-请求", args);
        
        util.ajax.run({
            url: "crf/getPersonHistory.do",
            data: args,
            success: function(response) {
                console.log("crf/getPersonHistory.do-响应", response);
                
                me.set({data: response});
            },
            mock: MOCK,
            mockData: {
                success: true,
                data: {
                    hasDisease: 1,
                    hasAllergy: 1,
                    disease: "1,2,3,4,5,6,7,8",
                    disease1: "1,2",
                    disease2: "1,2",
                    diseasetxt: "",
                    gxb: 1,
                    gxy: 1,
                    tnb: 1,
                    allergy: "",
                    allergytxt: ""
                }
            }
        });
    }
});
