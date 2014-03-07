/*
 * 合并用药
 * 
 * @author: Ricky
 */

es.Views.Form40 = Backbone.View.extend({
    el: ".crf-form",
    
    events: {
        "click .add-drug": "addDrug",
        "click .del-drug": "delDrug"
    },
    
    initialize: function() {
        this.model.bind("change:data", this.renderForm, this);
        this.model.getData({id: es.main.crfId});
    },
    
    destroy: function() {
        this.$(".sug").autocomplete("destroy");
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
            var disabled = es.main.editable ? "" : "disabled:true";
            
            me.$el.mustache("tpl-form40", {
                disabled: disabled,
                save: es.main.editable ? [1] : []
            });
            
            me.$(".drugs").prepend($.Mustache.render("tpl-form40-drug", {
                drug: data.data.drug,
                disabled: disabled,
                save: es.main.editable ? [1] : []
            }));
            me.drugCount = data.data.drug.length;
            
            if (es.main.editable) {
                me.$(".del-drug:first").parent().remove();
            }
            
            me.initCtrl(data.data);
        });
    },
    
    initCtrl: function(data) {
        //赋值
        $.each(data.drug, function(index, val) {
            var option = {};
            option["Start" + val.no] = {range: CRF_RANGE, value: val.start};
            option["End" + val.no] = {range: CRF_RANGE, value: val.end};
            esui.init(this.el, option);
            
            var start = esui.get("Start" + val.no);
            start.onchange = function(value) {start.setValueAsDate(value);};
            if (!es.main.editable) {
                start.disable();
            }
            var end = esui.get("End" + val.no);
            end.onchange = function(value) {end.setValueAsDate(value);};
            if (!es.main.editable) {
                end.disable();
            }
            //自动提示
            $("#ctrltextName" + val.no).autocomplete({source: util.getSuggestion("drug")});
            $("#ctrltextWay" + val.no).autocomplete({source: util.getSuggestion("way")});
        });
        switch (data.hasDrug) {
            case 1: esui.get("Merge1").setChecked(true); this.$(".drugs").show(); break;
            case 2: esui.get("Merge2").setChecked(true);
        }
        
        //事件
        var me = this;
        esui.get("Merge1").onclick = function() {me.$(".drugs").show();};
        esui.get("Merge2").onclick = function() {me.$(".drugs").hide();};
        
        if (es.main.canDoubt) {
            esui.get("DoubtOK").onclick = es.main.doubtCRF;
        }
        if (es.main.editable) {
            esui.get("Save").onclick = this.save;
        }
    },
    
    addDrug: function(e) {
        var no = ++this.drugCount;
        $(e.target).parent().before($.Mustache.render("tpl-form40-drug", {
            drug: [{no: no}],
            disabled: "",
            save: [1]
        }));
        var option = {};
        option["Start" + no] = {range: CRF_RANGE, value: "2014-01-01"};
        option["End" + no] = {range: CRF_RANGE, value: "2014-01-01"};
        esui.init(this.el, option);
        
        esui.get("Start" + no).onchange = function(value) {esui.get("Start" + no).setValueAsDate(value);};
        esui.get("End" + no).onchange = function(value) {esui.get("End" + no).setValueAsDate(value);};
        $("#ctrltextName" + no).autocomplete({source: util.getSuggestion("drug")});
        $("#ctrltextWay" + no).autocomplete({source: util.getSuggestion("way")});
    },
    
    delDrug: function(e) {
        var el = $(e.target),
            parent = el.parent().parent(),
            no = parent.attr("no");
        esui.dispose("Start" + no);
        esui.dispose("End" + no);
        $("#ctrltextName" + no).autocomplete("destroy");
        $("#ctrltextWay" + no).autocomplete("destroy");
        parent.remove();
    },
    
    save: function() {
       var me = es.main;
       
       var data = {
           id: me.crfId,
           no: me.model.get("data").no,
           hasDrug: parseInt(esui.get("Merge1").getGroup().getValue(), 10),
           drug: []
       };
       
       if (data.hasDrug == 1) {
           var drug = me.$(".drug-block");
           $.each(drug, function(index, val) {
               var no = $(val).attr("no");
               data.drug.push({
                   name: $.trim(esui.get("Name" + no).getValue()),
                   start: esui.get("Start" + no).getValue(),
                   end: esui.get("End" + no).getValue(),
                   dose: $.trim(esui.get("Dose" + no).getValue()),
                   unit: $.trim(esui.get("Unit" + no).getValue()),
                   way: $.trim(esui.get("Way" + no).getValue()),
                   frequency: $.trim(esui.get("Frequency" + no).getValue())
               });
           });
       }
       
       //验证
       var intPattern = /^\d+$/;
       if (isNaN(data.hasDrug)) {
           esui.Dialog.alert({title: "提示", content: "请选择有无合并用药"});
           return;
       }
       for (var i = 0, n = data.drug.length; i <n; i++) {
           var item = data.drug[i], seq = i + 1;
           if (item.name == "") {
               esui.Dialog.alert({title: "提示", content: "请填写第 " + seq + " 个通用名称"});
               return false;
           }
           var start = T.date.parse(item.start).getTime(),
               end = T.date.parse(item.end).getTime();
           if (start > end) {
               esui.Dialog.alert({title: "提示", content: "第" + seq + "个开始日期不能晚于结束日期"});
               return;
           }
           if (!intPattern.test(item.dose)) {
               esui.Dialog.alert({title: "提示", content: "请选择第 " + seq + " 个单次用药剂量"});
               return false;
           }
           if (item.unit == "") {
               esui.Dialog.alert({title: "提示", content: "请选择第 " + seq + " 个剂量单位"});
               return false;
           }
           if (item.way == "") {
               esui.Dialog.alert({title: "提示", content: "请填写第 " + seq + " 个给药途径"});
               return false;
           }
           if (item.frequency == "") {
               esui.Dialog.alert({title: "提示", content: "请填写第 " + seq + " 个给药频次"});
               return false;
           }
       }
       
       console.log("crf/saveDrugCombinationInfo.do-请求", data);
       
       util.ajax.run({
            url: "crf/saveDrugCombinationInfo.do",
            data: JSON.stringify(data),
            json: true,
            success: function(response) {
                console.log("crf/saveDrugCombinationInfo.do-响应:", response);
                
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
