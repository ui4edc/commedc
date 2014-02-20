/*
 * 用药小结
 * 
 * @author: Ricky
 */

es.Views.Form60 = Backbone.View.extend({
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
        $.Mustache.load("asset/tpl/form/form60.html").done(function() {
            me.$el.mustache("tpl-form60", {
                data: data.data,
                disabled: es.main.editable ? "" : "disabled:true",
                save: es.main.editable ? [1] : []
            });
            me.initCtrl(data.data);
        });
    },
    
    initCtrl: function(data) {
        esui.init(document.body, {
            StartDate: {
                range: CRF_RANGE,
                value: data.startDate
            },
            EndDate: {
                range: CRF_RANGE,
                value: data.endDate
            },
            DeathDate: {
                range: CRF_RANGE,
                value: data.deathDate
            }
        });
        
        //赋值
        switch (data.ending) {
            case 2: esui.get("Ending2").setChecked(true); break;
            case 3: esui.get("Ending3").setChecked(true); break;
            case 4: esui.get("Ending4").setChecked(true); break;
            case 5: esui.get("Ending5").setChecked(true); this.$(".death").show(); break;
            case 6: esui.get("Ending6").setChecked(true); break;
            case 7: esui.get("Ending7").setChecked(true);
        }
        if (data.adr == 1) {
            esui.get("ADR1").setChecked(true);
        }
        if (data.unreasonable == 1) {
            esui.get("Unreasonable1").setChecked(true);
        }
        if (data.intervention == 1) {
            esui.get("Intervention1").setChecked(true);
            this.$(".intervention").show();
        }
        
        //事件
        var me = this;
        
        esui.get("StartDate").onchange = function(value) {esui.get("StartDate").setValueAsDate(value);};
        esui.get("EndDate").onchange = function(value) {esui.get("EndDate").setValueAsDate(value);};
        esui.get("DeathDate").onchange = function(value) {esui.get("DeathDate").setValueAsDate(value);};
        
        esui.get("Ending1").onclick = function() {me.$(".death").hide();};
        esui.get("Ending2").onclick = function() {me.$(".death").hide();};
        esui.get("Ending3").onclick = function() {me.$(".death").hide();};
        esui.get("Ending4").onclick = function() {me.$(".death").hide();};
        esui.get("Ending5").onclick = function() {me.$(".death").show();};
        esui.get("Ending6").onclick = function() {me.$(".death").hide();};
        esui.get("Ending7").onclick = function() {me.$(".death").hide();};
        
        esui.get("Intervention1").onclick = function() {me.$(".intervention").show();};
        esui.get("Intervention2").onclick = function() {me.$(".intervention").hide();};
        
        esui.get("ADR1").onclick = function() {
            esui.Dialog.alert({title: "提示", content: "别忘记继续填写ADR噢！"});
        };
        
        if (es.main.canDoubt) {
            esui.get("DoubtOK").onclick = es.main.doubtCRF;
        }
        if (es.main.editable) {
            esui.get("Save").onclick = this.save;
        }
        if (!es.main.editable) {
            esui.get("StartDate").disable();
            esui.get("EndDate").disable();
            esui.get("DeathDate").disable();
        }
    },
    
    save: function() {
       var me = es.main;
       
       var data = {
           id: me.crfId,
           no: me.model.get("data").no,
           startDate: esui.get("StartDate").getValue(),
           endDate: esui.get("EndDate").getValue(),
           ending: parseInt(esui.get("Ending1").getGroup().getValue(), 10),
           deathDate: esui.get("DeathDate").getValue(),
           deathReason: $.trim(esui.get("DeathReason").getValue()),
           adr: parseInt(esui.get("ADR1").getGroup().getValue(), 10),
           unreasonable: parseInt(esui.get("Unreasonable1").getGroup().getValue(), 10),
           intervention: parseInt(esui.get("Intervention1").getGroup().getValue(), 10),
           interventiontxt: $.trim(esui.get("DeathReason").getValue()),
           treatmentCost: $.trim(esui.get("Index1").getValue()),
           drugCost: $.trim(esui.get("Index2").getValue()),
           trqCost: $.trim(esui.get("Index3").getValue()),
           remark: $.trim(esui.get("Remark").getValue())
       };
       
       //验证
       var start = T.date.parse(data.startDate).getTime(),
           end = T.date.parse(data.endDate).getTime();
       if (start > end) {
           esui.Dialog.alert({title: "提示", content: "首次用药时间不能晚于末次用药时间"});
           return;
       }
       if (data.intervention == 1 && data.interventiontxt == "") {
           esui.Dialog.alert({title: "提示", content: "请填写药师如何进行干预"});
           return;
       }
       var floatPattern = /^\d+(\.\d+)?$/;
       if (data.treatmentCost != "" && !floatPattern.test(data.treatmentCost)) {
           esui.Dialog.alert({title: "提示", content: "治疗总费用应为数字"});
           return;
       }
       if (data.drugCost != "" && !floatPattern.test(data.drugCost)) {
           esui.Dialog.alert({title: "提示", content: "药品总费用应为数字"});
           return;
       }
       if (!floatPattern.test(data.trqCost)) {
           esui.Dialog.alert({title: "提示", content: "请填写痰热清注射液费用"});
           return;
       }
       
       console.log("crf/saveDrugSummary.do-请求", data);
       
       util.ajax.run({
            url: "crf/saveDrugSummary.do",
            data: data,
            success: function(response) {
                console.log("crf/saveDrugSummary.do-响应:", response);
                
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
