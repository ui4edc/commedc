/*
 * 患者信息-基本情况
 * 
 * @author: Ricky
 */

es.Models.Form11 = Backbone.Model.extend({
    defaults: {
        data: null
    },
    
    getData: function(args) {
        var me = this;
        me.set({data : null}, {silent : true});
        
        console.log("crf/getBasicInfo.do-请求", args);
        
        util.ajax.run({
            url: "",
            data: args,
            success: function(response) {
                console.log("crf/getBasicInfo.do-响应", response);
                
                me.set({data: response});
            },
            mock: MOCK,
            mockData: {
                success: true,
                data: {
                    birthday: "1980-01-01",
                    ethic: 1,
                    sex: 1,
                    hys: 3,
                    weight: null,
                    height: null,
                    yyks: 1,
                    yykstext: "",
                    indate: "2014-01-01",
                    outdate: "2014-01-01",
                    feemode: 1,
                    feemodetxt: ""
                }
            }
        });
    }
});
