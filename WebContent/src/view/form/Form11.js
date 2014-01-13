/*
 * 患者信息-基本情况
 * 
 * @author: Ricky
 */

es.Views.Form11 = Backbone.View.extend({
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
        if (es.main.hasDoubt) {
            es.main.$el.append($.Mustache.render("tpl-check-doubt-dialog"));
        }
        
        var me = this;
        $.Mustache.load("asset/tpl/form/form11.html").done(function() {
            me.$el.mustache("tpl-form11", {
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
                value: data.birthday
            },
            InDate: {
                range: CRF_RANGE,
                value: data.indate
            },
            OutDate: {
                range: CRF_RANGE,
                value: data.outdate
            }
        });
        if (data.ethic == 1) {
            esui.get("HanNation").setChecked(true);
        } else {
            esui.get("CustomNation").setChecked(true);
        }
        if (data.sex == 1) {
            esui.get("Male").setChecked(true);
            $(".female").hide();
        } else {
            esui.get("Female").setChecked(true);
            switch (data.hys) {
                case 1: esui.get("Female1").setChecked(true); break;
                case 2: esui.get("Female2").setChecked(true); break;
                case 3: esui.get("Female3").setChecked(true);
            }
        }
        if (data.yykstext != "") {
            esui.get("CustomDep").setChecked(true);
        }
        switch (data.feemode) {
            case 1: esui.get("Pay1").setChecked(true); break;
            case 2: esui.get("Pay2").setChecked(true); break;
            case 3: esui.get("Pay3").setChecked(true); break;
            case 4: esui.get("Pay4").setChecked(true); break;
            case 5: esui.get("CustomPay").setChecked(true);
        }
        
        var me = this;
        
        esui.get("Birthday").onchange = function(value) {esui.get("Birthday").setValueAsDate(value);};
        esui.get("InDate").onchange = function(value) {esui.get("InDate").setValueAsDate(value);};
        esui.get("OutDate").onchange = function(value) {esui.get("OutDate").setValueAsDate(value);};
        
        esui.get("Male").onclick = function() {me.$(".female").hide();};
        esui.get("Female").onclick = function() {me.$(".female").show();};
        
        if (es.main.canDoubt) {
            esui.get("DoubtOK").onclick = es.main.doubtCRF;
        }
        if (es.main.editable) {
            esui.get("Save").onclick = this.save;
        }
        if (!es.main.editable) {
            esui.get("Birthday").disable();
            esui.get("InDate").disable();
            esui.get("OutDate").disable();
        }
    },
    
    save: function() {
       var me = es.main;
       
       var data = {
           id: es.main.crfId,
           birthday: esui.get("Birthday").getValue(),
           age: parseInt($.trim(esui.get("Age").getValue())),
           ethic: parseInt(esui.get("HanNation").getGroup().getValue()),
           ethictxt: $.trim(esui.get("CustomNationName").getValue()),
           sex: parseInt(esui.get("Male").getGroup().getValue()),
           hys: parseInt(esui.get("Female1").getGroup().getValue()),
           weight: $.trim(esui.get("Weight").getValue()),
           height: $.trim(esui.get("Height").getValue()),
           yyks: esui.get("CustomDep").getGroup().getValue(),
           yykstext: $.trim(esui.get("CustomDepName").getValue()),
           indate: esui.get("InDate").getValue(),
           outdate: esui.get("OutDate").getValue(),
           feemode: parseInt(esui.get("CustomPay").getGroup().getValue()),
           feemodetxt: $.trim(esui.get("CustomPayName").getValue())
       };
       
       console.log("crf/saveBasicInfo.do-请求", data);
       
       util.ajax.run({
            url: "crf/saveBasicInfo.do",
            data: data,
            success: function(response) {
                console.log("crf/saveBasicInfo.do-响应:", response);
                
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
