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
        es.main.$el.append($.Mustache.render("tpl-check-doubt-dialog"));
        
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
        //赋值
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
            },
            Nation: {
                datasource: NATION,
                value: data.ethic
            }
        });
        
        esui.get("Age").setValue(util.getAge(T.date.parse(data.birthday)));
        
        if (data.sex == 2) {
            esui.get("Female").setChecked(true);
            this.$(".female").show();
            switch (data.hys) {
                case 1: esui.get("Female1").setChecked(true); break;
                case 2: esui.get("Female2").setChecked(true);
            }
        }
        
        if (data.weight != null) {
            this.$(".weight").show();
            esui.get("HasWeight").setChecked(false);
        }
        if (data.height != null) {
            this.$(".height").show();
            esui.get("HasHeight").setChecked(false);
        }
        
        switch (data.yyks) {
            case 2: esui.get("Dep2").setChecked(true); break;
            case 3: esui.get("Dep3").setChecked(true); break;
            case 4: esui.get("Dep4").setChecked(true); break;
            case 5: esui.get("Dep5").setChecked(true); break;
            case 6: esui.get("Dep6").setChecked(true);
        }
        
        switch (data.feemode) {
            case 2: esui.get("Pay2").setChecked(true); break;
            case 3: esui.get("Pay3").setChecked(true); break;
            case 4: esui.get("Pay4").setChecked(true); break;
            case 5: esui.get("Pay5").setChecked(true);
        }
        
        //事件
        var me = this;
        
        esui.get("Birthday").onchange = function(value) {
            esui.get("Birthday").setValueAsDate(value);
            esui.get("Age").setValue(util.getAge(value));
        };
        esui.get("InDate").onchange = function(value) {esui.get("InDate").setValueAsDate(value);};
        esui.get("OutDate").onchange = function(value) {esui.get("OutDate").setValueAsDate(value);};
        
        esui.get("Male").onclick = function() {me.$(".female").hide();};
        esui.get("Female").onclick = function() {me.$(".female").show();};
        
        esui.get("HasWeight").onclick = function() {
            if (esui.get("HasWeight").isChecked()) {
                me.$(".weight").hide();
            } else {
                me.$(".weight").show();
            }
        };
        esui.get("HasHeight").onclick = function() {
            if (esui.get("HasHeight").isChecked()) {
                me.$(".height").hide();
            } else {
                me.$(".height").show();
            }
        };
        
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
           /*id: es.main.crfId,
           birthday: esui.get("Birthday").getValue(),
           age: parseInt(esui.get("Age").getValue()),
           ethic: esui.get("Nation").value,
           sex: parseInt(esui.get("Male").getGroup().getValue()),
           hys: parseInt(esui.get("Female1").getGroup().getValue()),
           weight: $.trim(esui.get("Weight").getValue()),
           height: $.trim(esui.get("Height").getValue()),
           yyks: parseInt(esui.get("CustomDep").getGroup().getValue()),
           yykstext: $.trim(esui.get("CustomDepName").getValue()),
           indate: esui.get("InDate").getValue(),
           outdate: esui.get("OutDate").getValue(),
           feemode: parseInt(esui.get("CustomPay").getGroup().getValue()),
           feemodetxt: $.trim(esui.get("CustomPayName").getValue())*/
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
