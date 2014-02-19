/*
 * 患者信息-基本情况
 * 
 * @author: Ricky
 */

es.Models.Form11 = Backbone.Model.extend({
    defaults: {
        data: null,
        def: {
            birthday: T.date.format(new Date(), "yyyy-MM-dd"),
            ethic: 0,
            sex: 1,
            hys: 3,
            weight: null,
            weightud: false,
            height: null,
            heightud: false,
            yyks: 1,
            yykstxt: null,
            indate: "2014-01-01",
            outdate: "2014-01-01",
            feemode: 1,
            feemodetxt: null
        }
    },
    
    getData: function(args) {
        var me = this;
        me.set({data : null}, {silent : true});
        
        console.log("crf/getBasicInfo.do-请求", args);
        
        util.ajax.run({
            url: "crf/getBasicInfo.do",
            data: args,
            success: function(response) {
                console.log("crf/getBasicInfo.do-响应", response);
                if (response.data == null) {
                    response.data = me.get("def");
                }
                me.set({data: response});
            },
            mock: MOCK,
            mockData: {
                success: true,
                data: null
            }
        });
    }
});
