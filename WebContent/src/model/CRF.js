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
        
        console.log("crf/getCRFSumm.do-请求", args);

        util.ajax.run({
            url: "crf/getCRFSumm.do",
            data: args,
            success: function(response) {
                console.log("crf/getCRFSumm.do-响应", response);
                
                me.set({data: response});
            },
            mock: MOCK,
            mockData: {
                success: true,
                no: "333-1234",
                abbr: "XM",
                progress: "10%",
                doubt: {
                    "11": 10,
                    "12": 200,
                    "13": 3,
                    "20": 1,
                    "30": 1,
                    "40": 1,
                    "51": 0,
                    "52": 0,
                    "53": 0,
                    "60": 0,
                    "70": 0
                }
            }
        });
    }
});
