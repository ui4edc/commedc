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
                    {id: 1, name: "药品名称字典", undealed: 10},
                    {id: 2, name: "科室名称字典", undealed: 0},
                    {id: 3, name: "用药类别字典", undealed: 0}
                ]
            }
        });
    },
    
    formatDict: function(response) {
        $.each(response.data, function(index, val) {
            if (val.undealed != 0) {
                val.name = val.name + " (" + val.undealed + "个未处理)";
            }
        });
        
        return response;
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
                    {id: 11, name: "高血压", abbr: "gxy"},
                    {id: 12, name: "糖尿病", abbr: "tnb"},
                    {id: 13, name: "心肌梗塞", abbr: "xjgs"},
                    {id: 14, name: "心肌梗死", abbr: "xjgs"},
                    {id: 15, name: "心梗", abbr: "xg"}
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
                me.set({base: me.formatBase(response)});
            },
            mock: true,
            mockData: {
                success: true,
                errorMsg: "errorMsg",
                data: [
                    {id: 21, name: "高血压", abbr: "gxy"},
                    {id: 22, name: "糖尿病", abbr: "tnb"},
                    {id: 23, name: "心肌梗塞", abbr: "xjgs"}
                ]
            }
        });
    },
    
    formatBase: function(response) {
        $.each(response.data, function(index, val) {
            val.value = val.name + " " + val.abbr;
        });
        
        return response;
    }
});
