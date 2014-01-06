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
        
        var args = arguments[0];
        this.parentView = args.parentView;
        this.editable = args.editable;
        this.crfId = args.crfId;
        
        this.model.getData({id: this.crfId});
    },
    
    destroy: function() {
        esui.dispose();
        this.model.unbind();
        this.$el.unbind();
        this.$el.empty();
    },
    
    renderForm: function(model, data) {
        var me = this;
        $.Mustache.load("asset/tpl/form/form70.html").done(function() {
            me.$el.mustache("tpl-form70", {data: data.data});
            me.initCtrl(data.data);
        });
        
        //插入质疑对话框
        if (es.main.canDoubt) {
            es.main.$(".crf-wrap").append($.Mustache.render("tpl-doubt-dialog"));
        }
    },
    
    initCtrl: function(data) {
        esui.init(this.el, {
            Birthday: BIRTHDAY_RANGE,
            ADRTime: CRF_RANGE,
            DeadTime: CRF_RANGE,
            ReportTime: CRF_RANGE
        });
        
        var me = this;
        esui.get("Save").onclick = this.save;
        if (es.main.canDoubt) {
            esui.get("DoubtOK").onclick = es.main.doubtCRF;
        }
        esui.get("Drug").onedit = function (value, options, editor) {
            this.datasource[options.rowIndex][options.field.field] = $.trim(value);
            this.setCellText($.trim(value), options.rowIndex, options.columnIndex);
            editor.stop();
        };
        
        var table = esui.get("Drug");
        table.datasource = data.drug;
        table.fields = [
            {
                field: "f1",
                title: "药品",
                editable: 1,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f1;}
            },
            {
                field: "f2",
                title: "批准文号",
                editable: 1,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f2;}
            },
            {
                field: "f3",
                title: "商品名称",
                editable: 1,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f3;}
            },
            {
                field: "f4",
                title: "通用名称<br>（含剂型）",
                editable: 1,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f4;}
            },
            {
                field: "f5",
                title: "生产厂家",
                editable: 1,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f5;}
            },
            {
                field: "f6",
                title: "生产批号",
                editable: 1,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f6;}
            },
            {
                field: "f7",
                title: "用法用量<br>（次剂量、途径、日次数）",
                editable: 1,
                edittype: "string",
                width: 170,
                stable: true,
                content: function(item) {return item.f7;}
            },
            {
                field: "f8",
                title: "用药<br>起止时间",
                editable: 1,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f8;}
            },
            {
                field: "f9",
                title: "用药原因",
                editable: 1,
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
           
       };
       
       console.log("保存表单-请求", data);
       
       util.ajax.run({
            url: "",
            data: data,
            success: function(response) {
                console.log("保存表单-响应:", response);
                
                esui.Dialog.alert({
                    title: "保存",
                    content: "保存成功！"
                });
                
                //更新进度
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
