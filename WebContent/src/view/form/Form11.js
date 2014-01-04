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
        
        var args = arguments[0];
        this.parentView = args.parentView;
        this.editable = args.editable;
        this.crfId = args.crfId;
        
        this.model.getData({id: this.crfId});
    },
    
    destroy: function() {
        esui.dispose();
        this.model.unbind();
        this.$el.unbind();
        this.$el.empty();
    },
    
    initCtrl: function() {
        esui.init(this.el, {
            Birthday: {
                range: {
                    begin: new Date(1900, 0, 1),
                    end: new Date()
                },
                valueAsDate: new Date()
            },
            InDate: {
                range: {
                    begin: new Date(2000, 0, 1),
                    end: new Date()
                },
                valueAsDate: new Date()
            },
            OutDate: {
                range: {
                    begin: new Date(2000, 0, 1),
                    end: new Date()
                },
                valueAsDate: new Date()
            }
        });
        
        var me = this;
        esui.get("Birthday").onchange = function(value) {
            esui.get("Birthday").setValueAsDate(value);
        };
        esui.get("InDate").onchange = function(value) {
            esui.get("InDate").setValueAsDate(value);
        };
        esui.get("OutDate").onchange = function(value) {
            esui.get("OutDate").setValueAsDate(value);
        };
        esui.get("Male").onclick = function() {
            me.$(".female-period").hide();
        };
        esui.get("Female").onclick = function() {
            me.$(".female-period").show();
        };
    },
    
    renderForm: function(model, data) {
        var me = this;
        $.Mustache.load("asset/tpl/form/form11.html").done(function() {
            me.$el.mustache("tpl-form11", {data: data.data});
            me.initCtrl();
        });
    }
});
