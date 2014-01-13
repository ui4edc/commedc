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
                    age: 33,
                    ethic: 2,
                    ethictxt: "满族",
                    sex: 2,
                    hys: 1,
                    weight: 70.5,
                    height: 178,
                    yyks: [
                        {id: 1, name: "呼吸科", checked: "checked"},
                        {id: 2, name: "急诊科", checked: ""},
                        {id: 3, name: "儿科", checked: ""},
                        {id: 4, name: "感染科", checked: ""},
                        {id: 5, name: "ICU", checked: ""}
                    ],
                    yykstext: "",
                    indate: "2013-12-01",
                    outdate: "2013-12-10",
                    feemode: 5,
                    feemodetxt: "xxx"
                }
            }
        });
    }
});
