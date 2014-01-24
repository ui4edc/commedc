/*
 * 合并用药
 * 
 * @author: Ricky
 */

es.Views.Form40 = Backbone.View.extend({
    el: ".crf-form",
    
    events: {
        "click .add": "addRow",
        "click .del": "delRow"
    },
    
    initialize: function() {
        this.model.bind("change:data", this.renderForm, this);
        this.model.getData({id: es.main.crfId});
    },
    
    destroy: function() {
        esui.dispose();
        this.model.unbind();
        this.$el.unbind();
        this.$el.empty();
    },
    
    renderForm: function(model, data) {
        //插入质疑对话框
        if (es.main.canDoubt) {
            es.main.$el.append($.Mustache.render("tpl-doubt-dialog"));
        }
        es.main.$el.append($.Mustache.render("tpl-check-doubt-dialog"));
        
        var me = this;
        $.Mustache.load("asset/tpl/form/form40.html").done(function() {
            me.$el.mustache("tpl-form40", {
                save: es.main.editable ? [1] : []
            });
            me.initCtrl(data.data);
        });
    },
    
    initCtrl: function(data) {
        esui.init();
        
        var me = this;
        
        if (es.main.canDoubt) {
            esui.get("DoubtOK").onclick = es.main.doubtCRF;
        }
        if (es.main.editable) {
            esui.get("Save").onclick = this.save;
        }
        
        esui.get("Merge").onedit = function (value, options, editor) {
            this.datasource[options.rowIndex][options.field.field] = $.trim(value);
            this.setCellText($.trim(value), options.rowIndex, options.columnIndex);
            editor.stop();
        };
        
        var editable = es.main.editable;
        
        var table = esui.get("Merge");
        table.datasource = data.drug;
        table.fields = [
            {
                field: "name",
                title: "通用名",
                editable: editable,
                edittype: "string",
                content: function(item) {return item.name;}
            },
            {
                field: "start",
                title: "开始日期",
                editable: editable,
                edittype: "string",
                content: function(item) {return item.start;}
            },
            {
                field: "end",
                title: "停止日期",
                editable: editable,
                edittype: "string",
                content: function(item) {return item.end;}
            },
            {
                field: "dose",
                title: "单次用药剂量",
                editable: editable,
                edittype: "string",
                content: function(item) {return item.dose;}
            },
            {
                field: "unit",
                title: "剂量单位",
                editable: editable,
                edittype: "string",
                content: function(item) {return item.unit;}
            },
            {
                field: "way",
                title: "给药途径",
                editable: editable,
                edittype: "string",
                content: function(item) {return item.way;}
            },
            {
                field: "frequency",
                title: "给药频次",
                editable: editable,
                edittype: "string",
                content: function(item) {return item.frequency;}
            }
        ];
        if (editable) {
            table.fields.push({
                field: "op",
                title: "操作",
                content: function(item) {return '<a href="javascript:void(0)" class="del">删除</a>';}
            });
        }
        table.render();
    },
    
    addRow: function() {
        var table = esui.get("Merge");
        table.datasource.push({
            name: "",
            start: "",
            end: "",
            dose: "",
            unit: "",
            way: "",
            frequency: ""
        });
        table.render();
    },
    
    delRow: function(e) {
        var parent = $(e.target).parent().parent();
            row = parent.attr("row"),
            id = parent.attr("id").replace(/^ctrltable(\w+)cell\d+_\d$/, "$1"),
            table = esui.get(id),
            datasource = table.datasource;
        
        table.datasource.splice(row, 1);
        table.render();
    },
    
    save: function() {
       var me = es.main;
       
       var data = {
           id: me.crfId,
           no: me.model.get("data").no,
           drug: esui.get("Merge").datasource
       };
       
       console.log("保存表单-请求", data);
       
       util.ajax.run({
            url: "",
            data: JSON.stringify(data),
            json: true,
            success: function(response) {
                console.log("保存表单-响应:", response);
                
                esui.Dialog.alert({title: "保存", content: "保存成功！"});
                me.updateProgress(response.progress);
            },
            mock: MOCK,
            mockData: {
                success: true,
                progress: "30%"
            }
        });
    }
});
