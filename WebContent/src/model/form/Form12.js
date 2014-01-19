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
        
        console.log("获取表单-请求", args);
        
        util.ajax.run({
            url: "",
            data: args,
            success: function(response) {
                console.log("获取表单-响应", response);
                
                $.each(response.data.food, function(index, val) {
                    val.no = index + 1;
                });
                $.each(response.data.drug, function(index, val) {
                    val.no = index + 1;
                });
                $.each(response.data.material, function(index, val) {
                    val.no = index + 1;
                });
                
                me.set({data: response});
            },
            mock: MOCK,
            mockData: {
                success: true,
                data: {
                    smoke: 1,
                    drink: 1,
                    hasFood: 1,
                    hasDrug: 1,
                    hasMaterial: 1,
                    food: [
                        {name: "1", value: "1,2,3", text: ""},
                        {name: "2", value: "7", text: "text"}
                    ],
                    drug: [
                        {name: "1", value: "1,2,3", text: "", type: 1},
                        {name: "2", value: "7", text: "text", type: 2}
                    ],
                    material: [
                        {name: "1", value: "1,2,3", text: ""},
                        {name: "2", value: "7", text: "text"}
                    ]
                }
            }
        });
    }
});
