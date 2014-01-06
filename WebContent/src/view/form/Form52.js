/*
 * 实验检查-用药中检查
 * 
 * @author: Ricky
 */

es.Views.Form52 = Backbone.View.extend({
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
        //插入质疑对话框
        if (es.main.canDoubt) {
            es.main.$(".crf-wrap").append($.Mustache.render("tpl-doubt-dialog"));
        }
        
        var me = this;
        $.Mustache.load("asset/tpl/form/form52.html").done(function() {
            me.$el.mustache("tpl-form52");
            me.initCtrl(data.data);
        });
    },
    
    initCtrl: function(data) {
        esui.init();
        var me = this;
        esui.get("Save").onclick = this.save;
        if (es.main.canDoubt) {
            esui.get("DoubtOK").onclick = es.main.doubtCRF;
        }
        
        esui.get("Exam1").onedit = function (value, options, editor) {
            this.datasource[options.rowIndex][options.field.field] = $.trim(value);
            this.setCellText($.trim(value), options.rowIndex, options.columnIndex);
            editor.stop();
        };
        esui.get("Exam2").onedit = function (value, options, editor) {
            this.datasource[options.rowIndex][options.field.field] = $.trim(value);
            this.setCellText($.trim(value), options.rowIndex, options.columnIndex);
            editor.stop();
        };
        esui.get("Exam3").onedit = function (value, options, editor) {
            this.datasource[options.rowIndex][options.field.field] = $.trim(value);
            this.setCellText($.trim(value), options.rowIndex, options.columnIndex);
            editor.stop();
        };
        esui.get("Exam4").onedit = function (value, options, editor) {
            this.datasource[options.rowIndex][options.field.field] = $.trim(value);
            this.setCellText($.trim(value), options.rowIndex, options.columnIndex);
            editor.stop();
        };
        esui.get("Exam5").onedit = function (value, options, editor) {
            this.datasource[options.rowIndex][options.field.field] = $.trim(value);
            this.setCellText($.trim(value), options.rowIndex, options.columnIndex);
            editor.stop();
        };
        esui.get("Exam6").onedit = function (value, options, editor) {
            this.datasource[options.rowIndex][options.field.field] = $.trim(value);
            this.setCellText($.trim(value), options.rowIndex, options.columnIndex);
            editor.stop();
        };
        
        var table1 = esui.get("Exam1");
        table1.datasource = data.data1;
        table1.fields = [
            {
                field: "f1",
                title: "检查日期<br>（YY-MM-DD）",
                editable: 1,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f1;}
            },
            {
                field: "f2",
                title: "体温（℃）",
                editable: 1,
                edittype: "string",
                content: function(item) {return item.f2;}
            },
            {
                field: "f3",
                title: "呼吸（次/分）",
                editable: 1,
                edittype: "string",
                content: function(item) {return item.f3;}
            },
            {
                field: "f4",
                title: "收缩压（mmHg）",
                editable: 1,
                edittype: "string",
                content: function(item) {return item.f4;}
            },
            {
                field: "f5",
                title: "舒张压（mmHg）",
                editable: 1,
                edittype: "string",
                content: function(item) {return item.f5;}
            },
            {
                field: "f6",
                title: "心率（次/分）",
                editable: 1,
                edittype: "string",
                content: function(item) {return item.f6;}
            }
        ];
        table1.render();
        
        var table2 = esui.get("Exam2");
        table2.datasource = data.data2;
        table2.fields = [
            {
                field: "f1",
                title: "检查日期（YY-MM-DD）",
                editable: 1,
                edittype: "string",
                content: function(item) {return item.f1;}
            },
            {
                field: "f2",
                title: "空腹血糖FPG（mmol/L）",
                editable: 1,
                edittype: "string",
                content: function(item) {return item.f2;}
            },
            {
                field: "f3",
                title: "餐后血糖PPG（mmol/L）",
                editable: 1,
                edittype: "string",
                content: function(item) {return item.f3;}
            }
        ];
        table2.render();
        
        var table3 = esui.get("Exam3");
        table3.datasource = data.data3;
        table3.fields = [
            {
                field: "f1",
                title: "检查日期<br>（YY-MM-DD）",
                editable: 1,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f1;}
            },
            {
                field: "f2",
                title: "血红蛋白HB<br>（g/L）",
                editable: 1,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f2;}
            },
            {
                field: "f3",
                title: "红细胞RBC<br>（×10(12)/L）",
                editable: 1,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f3;}
            },
            {
                field: "f4",
                title: "白细胞WBC<br>（×10(9)/L）",
                editable: 1,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f4;}
            },
            {
                field: "f5",
                title: "中性粒细胞NEUT<br>（%）",
                editable: 1,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f5;}
            },
            {
                field: "f6",
                title: "中性粒细胞NEUT<br>（×10(9)/L）",
                editable: 1,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f6;}
            },
            {
                field: "f7",
                title: "血小板PLT<br>（×10(9)/L）",
                editable: 1,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f7;}
            }
        ];
        table3.render();
        
        var table4 = esui.get("Exam4");
        table4.datasource = data.data4;
        table4.fields = [
            {
                field: "f1",
                title: "检查日期<br>（YY-MM-DD）",
                editable: 1,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f1;}
            },
            {
                field: "f2",
                title: "谷丙转氨酶ALT<br>（U/L）",
                editable: 1,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f2;}
            },
            {
                field: "f3",
                title: "谷草转氨酶AST<br>（U/L）",
                editable: 1,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f3;}
            },
            {
                field: "f4",
                title: "碱性磷酸酶ALP<br>(U/L)",
                editable: 1,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f4;}
            },
            {
                field: "f5",
                title: "γ-谷氨酰转肽酶γ-GT<br>（U/L）",
                editable: 1,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f5;}
            },
            {
                field: "f6",
                title: "总胆红素TBIL<br>（umol/L）",
                editable: 1,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f6;}
            }
        ];
        table4.render();
        
        var table5 = esui.get("Exam5");
        table5.datasource = data.data5;
        table5.fields = [
            {
                field: "f1",
                title: "检查日期（YY-MM-DD）",
                editable: 1,
                edittype: "string",
                content: function(item) {return item.f1;}
            },
            {
                field: "f2",
                title: "尿素氮BUN（mmol/L）",
                editable: 1,
                edittype: "string",
                content: function(item) {return item.f2;}
            },
            {
                field: "f3",
                title: "血肌酐CR（umol/L）",
                editable: 1,
                edittype: "string",
                content: function(item) {return item.f3;}
            }
        ];
        table5.render();
        
        var table6 = esui.get("Exam6");
        table6.datasource = data.data6;
        table6.fields = [
            {
                field: "f1",
                title: "检查日期（YY-MM-DD）",
                editable: 1,
                edittype: "string",
                content: function(item) {return item.f1;}
            },
            {
                field: "f2",
                title: "是否正常",
                editable: 1,
                edittype: "string",
                content: function(item) {return item.f2;}
            },
            {
                field: "f3",
                title: "异常心电图描述（心率 _ 次/分）",
                editable: 1,
                edittype: "string",
                content: function(item) {return item.f3;}
            }
        ];
        table6.render();
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
