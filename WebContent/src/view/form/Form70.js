/*
 * ADR
 * 
 * @author: Ricky
 */

es.Views.Form70 = Backbone.View.extend({
    el: ".crf-form",
    
    events: {
        
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
        $.Mustache.load("asset/tpl/form/form70.html").done(function() {
            me.$el.mustache("tpl-form70", {
                data: data.data,
                disabled: es.main.editable ? "" : "disabled:true",
                save: es.main.editable ? [1] : []
            });
            me.initCtrl(data.data);
        });
    },
    
    initCtrl: function(data) {
        esui.init(es.main.el, {
            Birthday: {
                range: BIRTHDAY_RANGE,
                valueAsDate: new Date()
            },
            ADRTime: {
                range: CRF_RANGE,
                valueAsDate: new Date()
            },
            Dead: {
                range: CRF_RANGE,
                valueAsDate: new Date()
            },
            Report: {
                range: CRF_RANGE,
                valueAsDate: new Date()
            }
        });
        
        var me = this;
        
        esui.get("Birthday").onchange = function(value) {esui.get("Birthday").setValueAsDate(value);};
        esui.get("ADRTime").onchange = function(value) {esui.get("ADRTime").setValueAsDate(value);};
        esui.get("Dead").onchange = function(value) {esui.get("Dead").setValueAsDate(value);};
        esui.get("Report").onchange = function(value) {esui.get("Report").setValueAsDate(value);};
        
        if (es.main.canDoubt) {
            esui.get("DoubtOK").onclick = es.main.doubtCRF;
        }
        if (es.main.editable) {
            esui.get("Save").onclick = this.save;
        }
        if (!es.main.editable) {
            esui.get("Birthday").disable();
            esui.get("ADRTime").disable();
            esui.get("Dead").disable();
            esui.get("Report").disable();
        }
        
        esui.get("Drug").onedit = function (value, options, editor) {
            this.datasource[options.rowIndex][options.field.field] = $.trim(value);
            this.setCellText($.trim(value), options.rowIndex, options.columnIndex);
            editor.stop();
        };
        
        var editable = es.main.editable;
        
        var table = esui.get("Drug");
        table.datasource = data.drug;
        table.fields = [
            {
                field: "f1",
                title: "药品",
                editable: editable,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f1;}
            },
            {
                field: "f2",
                title: "批准文号",
                editable: editable,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f2;}
            },
            {
                field: "f3",
                title: "商品名称",
                editable: editable,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f3;}
            },
            {
                field: "f4",
                title: "通用名称<br>（含剂型）",
                editable: editable,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f4;}
            },
            {
                field: "f5",
                title: "生产厂家",
                editable: editable,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f5;}
            },
            {
                field: "f6",
                title: "生产批号",
                editable: editable,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f6;}
            },
            {
                field: "f7",
                title: "用法用量<br>（次剂量、途径、日次数）",
                editable: editable,
                edittype: "string",
                width: 170,
                stable: true,
                content: function(item) {return item.f7;}
            },
            {
                field: "f8",
                title: "用药<br>起止时间",
                editable: editable,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f8;}
            },
            {
                field: "f9",
                title: "用药原因",
                editable: editable,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f9;}
            }
        ];
        table.render();
    },
    
    save: function() {
       var me = es.main;
       
       var data = {
           id: me.crfId,
           no: me.model.get("data").no
       };
       
       console.log("保存表单-请求", data);
       
       util.ajax.run({
            url: "",
            data: data,
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
