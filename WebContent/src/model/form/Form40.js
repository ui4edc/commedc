/*
 * 合并用药
 * 
 * @author: Ricky
 */

es.Models.Form40 = Backbone.Model.extend({
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
                
                me.set({data: response});
            },
            mock: MOCK,
            mockData: {
                success: true,
                data: {
                    drug: [{
                        name: "name",
                        start: "start",
                        end: "end",
                        dose: "dose",
                        unit: "unit",
                        way: "way",
                        frequency: "frequency"
                    }]
                }
            }
        });
    }
});
