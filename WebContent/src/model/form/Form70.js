/*
 * ADR
 * 
 * @author: Ricky
 */

es.Models.Form70 = Backbone.Model.extend({
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
                    drug: [
                        {f1:"怀疑药品",f2:"",f3:"",f4:"",f5:"",f6:"",f7:"",f8:"",f9:""},
                        {f1:"怀疑药品",f2:"",f3:"",f4:"",f5:"",f6:"",f7:"",f8:"",f9:""},
                        {f1:"并用药品",f2:"",f3:"",f4:"",f5:"",f6:"",f7:"",f8:"",f9:""},
                        {f1:"并用药品",f2:"",f3:"",f4:"",f5:"",f6:"",f7:"",f8:"",f9:""}
                    ]
                }
            }
        });
    }
});
