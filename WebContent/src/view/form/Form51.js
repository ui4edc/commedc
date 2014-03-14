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
            ExamDate: {
                range: CRF_RANGE,
                value: data.examDate
            }
        });
        
        var me = this;
        
        switch (data.done) {
            case 1: esui.get("Done1").setChecked(true); this.$(".exam").show(); break;
            case 2: esui.get("Done2").setChecked(true);
        }
        
        //事件
        esui.get("Done1").onclick = function() {me.$(".exam").show();};
        esui.get("Done2").onclick = function() {me.$(".exam").hide();};
        esui.get("ExamDate").onchange = function(value) {esui.get("ExamDate").setValueAsDate(value);};
        
        if (es.main.canDoubt) {
            esui.get("DoubtOK").onclick = es.main.doubtCRF;
        }
        if (es.main.editable) {
            esui.get("Save").onclick = this.save;
        }
        if (!es.main.editable) {
            esui.get("ExamDate").disable();
        }
    },
    
    save: function() {
       var me = es.main;
       
       var data = {
           id: me.crfId,
           no: me.model.get("data").no,
           
           done: parseInt(esui.get("Done1").getGroup().getValue(), 10),
           examDate: esui.get("ExamDate").getValue(),
           temperature: $.trim(esui.get("Temperature").getValue()),
           breathe: $.trim(esui.get("Breathe").getValue()),
           ssy: $.trim(esui.get("Ssy").getValue()),
           szy: $.trim(esui.get("Szy").getValue()),
           rate: $.trim(esui.get("Rate").getValue())
       };
       
       //验证
       if (isNaN(data.done)) {
           esui.Dialog.alert({title: "提示", content: "请选择是否做了体格检查"});
           return;
       } else if (data.done == 2) {
           data.examDate = null;
           data.temperature = "";
           data.breathe = "";
           data.ssy = "";
           data.szy = "";
           data.rate = "";
       } else {
           var floatPattern = /^\d+(\.\d+)?$/;
           if (!floatPattern.test(data.temperature)) {
               esui.Dialog.alert({title: "提示", content: "请填写体温"});
               return;
           }
           if (!floatPattern.test(data.breathe)) {
               esui.Dialog.alert({title: "提示", content: "请填写呼吸"});
               return;
           }
           if (!floatPattern.test(data.ssy)) {
               esui.Dialog.alert({title: "提示", content: "请填写收缩压"});
               return;
           }
           if (!floatPattern.test(data.szy)) {
               esui.Dialog.alert({title: "提示", content: "请填写舒张压"});
               return;
           }
           if (!floatPattern.test(data.rate)) {
               esui.Dialog.alert({title: "提示", content: "请填写心率"});
               return;
           }
       }
       
       console.log("crf/save体格检查.do-请求", data);
       
       util.ajax.run({
            url: "",
            data: JSON.stringify(data),
            json: true,
            success: function(response) {
                console.log("crf/save体格检查.do-响应:", response);
                
                me.updateProgress(response.progress);
                if (me.form.model.first) {
                    $("#TreeNode52").click();
                } else {
                    esui.Dialog.alert({title: "保存", content: "保存成功！"});
                }
            },
            mock: MOCK,
            mockData: {
                success: true,
                progress: "30%"
            }
        });
    }
});
