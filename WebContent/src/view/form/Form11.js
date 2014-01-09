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
            me.initCtrl();
        });
    },
    
    initCtrl: function() {
        esui.init(es.main.el, {
            Birthday: BIRTHDAY_RANGE,
            InDate: CRF_RANGE,
            OutDate: CRF_RANGE
        });
        
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
