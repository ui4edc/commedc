/*
 * 实验检查-入院检查
 * 
 * @author: Ricky
 */

es.Views.Form51 = Backbone.View.extend({
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
        $.Mustache.load("asset/tpl/form/form51.html").done(function() {
            me.$el.mustache("tpl-form51", {
                disabled: es.main.editable ? "" : "disabled:true",
                save: es.main.editable ? [1] : []
            });
            me.initCtrl(data.data);
        });
    },
    
    initCtrl: function(data) {
        //赋值
        esui.init(document.body, {
            ExamDay: {
                range: CRF_RANGE,
                value: data.examDate
            }
        });
        
        var me = this,
            editable = es.main.editable;
            
        switch (data.sample) {
            case 2: esui.get("Sample2").setChecked(true); break;
            case 3: esui.get("Sample3").setChecked(true);
        }
        if (data.result != "") {
            $.each(data.result.split(","), function(index, val) {
                esui.get("Result" + val).setChecked(true);
            });
        }
        
        var table1 = esui.get("Exam1");
        table1.datasource = data.data1;
        table1.fields = [
            {
                field: "f1",
                title: "检查日期<br>（YY-MM-DD）",
                editable: editable,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f1;}
            },
            {
                field: "f2",
                title: "体温（℃）",
                editable: editable,
                edittype: "string",
                content: function(item) {return item.f2;}
            },
            {
                field: "f3",
                title: "呼吸（次/分）",
                editable: editable,
                edittype: "string",
                content: function(item) {return item.f3;}
            },
            {
                field: "f4",
                title: "收缩压（mmHg）",
                editable: editable,
                edittype: "string",
                content: function(item) {return item.f4;}
            },
            {
                field: "f5",
                title: "舒张压（mmHg）",
                editable: editable,
                edittype: "string",
                content: function(item) {return item.f5;}
            },
            {
                field: "f6",
                title: "心率（次/分）",
                editable: editable,
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
                editable: editable,
                edittype: "string",
                content: function(item) {return item.f1;}
            },
            {
                field: "f2",
                title: "空腹血糖FPG（mmol/L）",
                editable: editable,
                edittype: "string",
                content: function(item) {return item.f2;}
            },
            {
                field: "f3",
                title: "餐后血糖PPG（mmol/L）",
                editable: editable,
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
                editable: editable,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f1;}
            },
            {
                field: "f2",
                title: "血红蛋白HB<br>（g/L）",
                editable: editable,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f2;}
            },
            {
                field: "f3",
                title: "红细胞RBC<br>（×10(12)/L）",
                editable: editable,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f3;}
            },
            {
                field: "f4",
                title: "白细胞WBC<br>（×10(9)/L）",
                editable: editable,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f4;}
            },
            {
                field: "f5",
                title: "中性粒细胞NEUT<br>（%）",
                editable: editable,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f5;}
            },
            {
                field: "f6",
                title: "中性粒细胞NEUT<br>（×10(9)/L）",
                editable: editable,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f6;}
            },
            {
                field: "f7",
                title: "血小板PLT<br>（×10(9)/L）",
                editable: editable,
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
                editable: editable,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f1;}
            },
            {
                field: "f2",
                title: "谷丙转氨酶ALT<br>（U/L）",
                editable: editable,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f2;}
            },
            {
                field: "f3",
                title: "谷草转氨酶AST<br>（U/L）",
                editable: editable,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f3;}
            },
            {
                field: "f4",
                title: "碱性磷酸酶ALP<br>(U/L)",
                editable: editable,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f4;}
            },
            {
                field: "f5",
                title: "γ-谷氨酰转肽酶γ-GT<br>（U/L）",
                editable: editable,
                edittype: "string",
                width: 100,
                stable: true,
                content: function(item) {return item.f5;}
            },
            {
                field: "f6",
                title: "总胆红素TBIL<br>（umol/L）",
                editable: editable,
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
                editable: editable,
                edittype: "string",
                content: function(item) {return item.f1;}
            },
            {
                field: "f2",
                title: "尿素氮BUN（mmol/L）",
                editable: editable,
                edittype: "string",
                content: function(item) {return item.f2;}
            },
            {
                field: "f3",
                title: "血肌酐CR（umol/L）",
                editable: editable,
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
                editable: editable,
                edittype: "string",
                content: function(item) {return item.f1;}
            },
            {
                field: "f2",
                title: "是否正常（是/否）",
                editable: editable,
                edittype: "string",
                content: function(item) {return item.f2;}
            },
            {
                field: "f3",
                title: "异常心电图描述（心率 _ 次/分）",
                editable: editable,
                edittype: "string",
                content: function(item) {return item.f3;}
            }
        ];
        table6.render();
        
        if (data.done == 1) {
            esui.get("Done1").setChecked(true);
        } else {
            this.$(".exam").hide();
        }
        
        //事件
        esui.get("Done1").onclick = function() {me.$(".exam").show();};
        esui.get("Done2").onclick = function() {me.$(".exam").hide();};
        esui.get("ExamDay").onchange = function(value) {esui.get("ExamDay").setValueAsDate(value);};
        
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
        
        if (es.main.canDoubt) {
            esui.get("DoubtOK").onclick = es.main.doubtCRF;
        }
        if (es.main.editable) {
            esui.get("Save").onclick = this.save;
        }
        if (!es.main.editable) {
            esui.get("ExamDay").disable();
        }
    },
    
    save: function() {
       var me = es.main;
       
       var bodyExam = esui.get("Exam1").datasource;
       if (bodyExam[0].f1 == ""
           || bodyExam[0].f2 == ""
           || bodyExam[0].f3 == ""
           || bodyExam[0].f4 == ""
           || bodyExam[0].f5 == ""
           || bodyExam[0].f6 == ""
       ) {
           esui.Dialog.alert({title: "提示", content: "请将体格检查填写完整"});
           return;
       }
       
       var data = {
           id: me.crfId,
           no: me.model.get("data").no,
           done: parseInt(esui.get("Done1").getGroup().getValue(), 10),
           examDate: esui.get("ExamDay").getValue(),
           sample: parseInt(esui.get("Sample1").getGroup().getValue(), 10),
           sampletxt: $.trim(esui.get("SampleName").getValue()),
           result: esui.get("Result1").getGroup().getValue(),
           resulttxt1: $.trim(esui.get("Result1Content").getValue()),
           resulttxt2: $.trim(esui.get("Result2Content").getValue()),
           resulttxt3: $.trim(esui.get("Result3Content").getValue()),
           data1: bodyExam,
           data2: esui.get("Exam2").datasource,
           data3: esui.get("Exam3").datasource,
           data4: esui.get("Exam4").datasource,
           data5: esui.get("Exam5").datasource,
           data6: esui.get("Exam6").datasource
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
