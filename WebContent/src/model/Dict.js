/*
 * 数据字典
 * 
 * @author: Ricky
 */

es.Models.Dict = Backbone.Model.extend({
    defaults: {
        dict: null,
        item: null,
        base: null
    },
    
    getDict: function(args) {
        var me = this;
        me.set({dict : null}, {silent : true});
        
        util.ajax.run({
            url: "",
            data: args,
            success: function(response) {
                me.set({dict: me.formatDict(response)});
            },
            mock: true,
            mockData: {
                success: true,
                errorMsg: "errorMsg",
                data: [
                    {id: 1, name: "药品名称字典", undeal: 10},
                    {id: 2, name: "科室名称字典", undeal: 0},
                    {id: 2, name: "用药类别字典", undeal: 0}
                ]
            }
        });
    },
    
    getItem: function(args) {
        var me = this;
        me.set({item : null}, {silent : true});
        
        util.ajax.run({
            url: "",
            data: args,
            success: function(response) {
                me.set({item: response});
                me.getBase(args);
            },
            mock: true,
            mockData: {
                success: true,
                errorMsg: "errorMsg",
                data: [
                    {id: 1, name: "高血压", abbr: "gxy"},
                    {id: 2, name: "糖尿病", abbr: "tnb"},
                    {id: 2, name: "心肌梗塞", abbr: "xjgs"},
                    {id: 2, name: "心肌梗死", abbr: "xjgs"},
                    {id: 2, name: "心梗", abbr: "xg"}
                ]
            }
        });
    },
    
    getBase: function(args) {
        var me = this;
        me.set({base : null}, {silent : true});
        
        util.ajax.run({
            url: "",
            data: args,
            success: function(response) {
                me.set({base: response});
            },
            mock: true,
            mockData: {
                success: true,
                errorMsg: "errorMsg",
                data: [
                    {id: 1, name: "高血压", abbr: "gxy"},
                    {id: 2, name: "糖尿病", abbr: "tnb"},
                    {id: 2, name: "心肌梗塞", abbr: "xjgs"}
                ]
            }
        });
    },
    
    formatDict: function(response) {
        $.each(response.data, function(index, val) {
            val.name = val.name + (val.undeal == 0 ? "" : " (" + val.undeal +　"个未处理)");
        });
        
        return response;
    }
});
