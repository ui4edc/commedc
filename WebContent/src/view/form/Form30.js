/*
 * 用药情况
 * 
 * @author: Ricky
 */

es.Views.Form30 = Backbone.View.extend({
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
        $.Mustache.load("asset/tpl/form/form30.html").done(function() {
            me.$el.mustache("tpl-form30", {
                data: data.data,
                disabled: es.main.editable ? "" : "disabled:true",
                save: es.main.editable ? [1] : []
            });
            me.initCtrl();
        });
    },
    
    initCtrl: function() {
        esui.init(es.main.el, {
            Start: CRF_RANGE,
            End: CRF_RANGE
        });
        
        var me = this;
        
        esui.get("Start").onchange = function(value) {esui.get("Start").setValueAsDate(value);};
        esui.get("End").onchange = function(value) {esui.get("End").setValueAsDate(value);};
        
        esui.get("History1").onclick = function() {me.$(".history").show();};
        esui.get("History2").onclick = function() {me.$(".history").hide();};
        esui.get("Bottle1").onclick = function() {me.$(".bottle").show();};
        esui.get("Bottle2").onclick = function() {me.$(".bottle").hide();};
        esui.get("Group1").onclick = function() {me.$(".group").show();};
        esui.get("Group2").onclick = function() {me.$(".group").hide();};
        esui.get("Ban1").onclick = function() {me.$(".ban").show();};
        esui.get("Ban2").onclick = function() {me.$(".ban").hide();};
        esui.get("HaveFood1").onclick = function() {me.$(".have-food").show();};
        esui.get("HaveFood2").onclick = function() {me.$(".have-food").hide();};
        
        if (es.main.canDoubt) {
            esui.get("DoubtOK").onclick = es.main.doubtCRF;
        }
        if (es.main.editable) {
            esui.get("Save").onclick = this.save;
        }
        if (!es.main.editable) {
            esui.get("Start").disable();
            esui.get("End").disable();
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
