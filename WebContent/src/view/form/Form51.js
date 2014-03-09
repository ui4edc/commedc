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
                data: data.data,
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
        
        var me = this;
        
        switch (data.done) {
            case 1: esui.get("Done1").setChecked(true); this.$(".exam").show(); break;
            case 2: esui.get("Done2").setChecked(true);
        }
        switch (data.sample) {
            case 1: esui.get("Sample1").setChecked(true); break;
            case 2: esui.get("Sample2").setChecked(true); break;
            case 3: esui.get("Sample3").setChecked(true);
        }
        if (data.result != "") {
            $.each(data.result.split(","), function(index, val) {
                esui.get("Result" + val).setChecked(true);
            });
        }
        
        //事件
        esui.get("Done1").onclick = function() {me.$(".exam").show();};
        esui.get("Done2").onclick = function() {me.$(".exam").hide();};
        esui.get("ExamDay").onchange = function(value) {esui.get("ExamDay").setValueAsDate(value);};
        
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
           data1: [],
           data2: [],
           data3: [],
           data4: [],
           data5: [],
           data6: []
       };
       
       //验证
       if (isNaN(data.done)) {
           esui.Dialog.alert({title: "提示", content: "请选择是否做了入院检查"});
           return;
       }
       if (data.done == 1) {
           if (isNaN(data.sample)) {
               esui.Dialog.alert({title: "提示", content: "请选择送检样本"});
               return;
           }
           if (data.sample == 3) {
               if (data.sampletxt == "") {
                   esui.Dialog.alert({title: "提示", content: "请填写其他送检样本"});
                   return;
               }
           } else {
               data.sampletxt = "";
           }
           if (data.result == "") {
               esui.Dialog.alert({title: "提示", content: "请选择病原学结果"});
               return;
           }
           if (data.result.indexOf("1") != -1) {
               if (data.resulttxt1 == "") {
                   esui.Dialog.alert({title: "提示", content: "请填写细菌培养结果"});
                   return;
               }
           } else {
               data.resulttxt1 = "";
           }
           if (data.result.indexOf("2") != -1) {
               if (data.resulttxt1 == "") {
                   esui.Dialog.alert({title: "提示", content: "请填写支原体抗体结果"});
                   return;
               }
           } else {
               data.resulttxt2 = "";
           }
           if (data.result.indexOf("3") != -1) {
               if (data.resulttxt1 == "") {
                   esui.Dialog.alert({title: "提示", content: "请填写病毒咽拭子结果"});
                   return;
               }
           } else {
               data.resulttxt3 = "";
           }
       } else {
           data.examDate = null;
           data.sample = 0;
           data.sampletxt = "";
           data.result = "";
           data.resulttxt1 = "";
           data.resulttxt2 = "";
           data.resulttxt3 = "";
           data.data1 = [];
           data.data2 = [];
           data.data3 = [];
           data.data4 = [];
           data.data5 = [];
           data.data6 = [];
       }
       
       console.log("crf/saveInHospitalExam.do-请求", data);
       
       util.ajax.run({
            url: "crf/saveInHospitalExam.do",
            data: JSON.stringify(data),
            json: true,
            success: function(response) {
                console.log("crf/saveInHospitalExam.do-响应:", response);
                
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
