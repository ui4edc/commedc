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
        esui.init();
    },
    
    renderForm: function(model, data) {
        var me = this;
        $.Mustache.load("asset/tpl/form/form51.html").done(function() {
            me.$el.mustache("tpl-form51", {data: data.data});
            me.initCtrl();
        });
    }
});
