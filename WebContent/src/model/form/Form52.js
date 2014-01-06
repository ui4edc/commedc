/*
 * 实验检查-用药中检查
 * 
 * @author: Ricky
 */

es.Models.Form52 = Backbone.Model.extend({
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
                    data1: [
                        {f1:"",f2:"",f3:"",f4:"",f5:"",f6:""},
                        {f1:"",f2:"",f3:"",f4:"",f5:"",f6:""},
                        {f1:"",f2:"",f3:"",f4:"",f5:"",f6:""},
                        {f1:"",f2:"",f3:"",f4:"",f5:"",f6:""},
                        {f1:"",f2:"",f3:"",f4:"",f5:"",f6:""}
                    ],
                    data2: [
                        {f1:"",f2:"",f3:""},
                        {f1:"",f2:"",f3:""},
                        {f1:"",f2:"",f3:""},
                        {f1:"",f2:"",f3:""},
                        {f1:"",f2:"",f3:""}
                    ],
                    data3: [
                        {f1:"",f2:"",f3:"",f4:"",f5:"",f6:"",f7:""},
                        {f1:"",f2:"",f3:"",f4:"",f5:"",f6:"",f7:""},
                        {f1:"",f2:"",f3:"",f4:"",f5:"",f6:"",f7:""},
                        {f1:"",f2:"",f3:"",f4:"",f5:"",f6:"",f7:""},
                        {f1:"",f2:"",f3:"",f4:"",f5:"",f6:"",f7:""}
                    ],
                    data4: [
                        {f1:"",f2:"",f3:"",f4:"",f5:"",f6:""},
                        {f1:"",f2:"",f3:"",f4:"",f5:"",f6:""},
                        {f1:"",f2:"",f3:"",f4:"",f5:"",f6:""},
                        {f1:"",f2:"",f3:"",f4:"",f5:"",f6:""},
                        {f1:"",f2:"",f3:"",f4:"",f5:"",f6:""}
                    ],
                    data5: [
                        {f1:"",f2:"",f3:""},
                        {f1:"",f2:"",f3:""},
                        {f1:"",f2:"",f3:""},
                        {f1:"",f2:"",f3:""},
                        {f1:"",f2:"",f3:""}
                    ],
                    data6: [
                        {f1:"",f2:"",f3:""},
                        {f1:"",f2:"",f3:""},
                        {f1:"",f2:"",f3:""},
                        {f1:"",f2:"",f3:""},
                        {f1:"",f2:"",f3:""}
                    ]
                }
            }
        });
    }
});
