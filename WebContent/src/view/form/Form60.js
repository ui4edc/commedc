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
            me.initCtrl();
        });
    },
    
    initCtrl: function() {
        esui.init(es.main.el, {
            Start: {
                range: CRF_RANGE,
                valueAsDate: new Date()
            },
            End: {
                range: CRF_RANGE,
                valueAsDate: new Date()
            },
            Dead: {
                range: CRF_RANGE,
                valueAsDate: new Date()
            }
        });
        
        var me = this;
        
        esui.get("Start").onchange = function(value) {esui.get("Start").setValueAsDate(value);};
        esui.get("End").onchange = function(value) {esui.get("End").setValueAsDate(value);};
        esui.get("Dead").onchange = function(value) {esui.get("Dead").setValueAsDate(value);};
        
        if (es.main.canDoubt) {
            esui.get("DoubtOK").onclick = es.main.doubtCRF;
        }
        if (es.main.editable) {
            esui.get("Save").onclick = this.save;
        }
        if (!es.main.editable) {
            esui.get("Start").disable();
            esui.get("End").disable();
            esui.get("Dead").disable();
        }
    },
    
    save: function() {
       var me = es.main;
       
       var data = {
           id: me.crfId,
           no: me.model.get("data").no
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
