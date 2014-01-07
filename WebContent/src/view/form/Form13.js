/*
 * 患者信息-既往史
 * 
 * @author: Ricky
 */

es.Views.Form13 = Backbone.View.extend({
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
        $.Mustache.load("asset/tpl/form/form13.html").done(function() {
            me.$el.mustache("tpl-form13", {
                data: data.data,
                disabled: es.main.editable ? "" : "disabled:true",
                save: es.main.editable ? [1] : []
            });
            me.initCtrl();
        });
    },
    
    initCtrl: function() {
        esui.init();
        
        var me = this;
        
        esui.get("Disease1").onclick = function() {me.$(".disease").show();};
        esui.get("Disease2").onclick = function() {me.$(".disease").hide();};
        esui.get("ADisease1").onclick = function() {me.$(".a-disease").show();};
        esui.get("ADisease2").onclick = function() {me.$(".a-disease").hide();};
        
        if (es.main.canDoubt) {
            esui.get("DoubtOK").onclick = es.main.doubtCRF;
        }
        if (es.main.editable) {
            esui.get("Save").onclick = this.save;
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
