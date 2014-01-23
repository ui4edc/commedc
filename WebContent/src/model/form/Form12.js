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
        
        console.log("crf/getPersonHistory.do-请求", args);
        
        util.ajax.run({
            url: "crf/getPersonHistory.do",
            data: args,
            success: function(response) {
                console.log("crf/getPersonHistory.do-响应", response);
                
                $.each(response.data.food, function(index, val) {
                    val.no = index + 1;
                });
                $.each(response.data.drug, function(index, val) {
                    val.no = index + 1;
                });
                $.each(response.data.other, function(index, val) {
                    val.no = index + 1;
                });
                
                me.set({data: response});
            },
            mock: MOCK,
            mockData: {
                success: true,
                data: {
                    no: "333-1234",
                    smoke: 2,
                    drink: 2,
                    hasFood: 1,
                    hasDrug: 1,
                    hasOther: 1,
                    food: [
                        {name: "", value: "1", txt: ""}
                    ],
                    drug: [
                        {name: "1", value: "1,2,3", txt: "", type: 1},
                        {name: "2", value: "8", txt: "txt", type: 2}
                    ],
                    other: [
                        {name: "1", value: "1,2,3", txt: ""},
                        {name: "2", value: "8", txt: "txt"}
                    ]
                }
            }
        });
    }
});
