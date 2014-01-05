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
        var me = this;
        esui.get("Disease1").onclick = function() {me.$(".disease").show();};
        esui.get("Disease2").onclick = function() {me.$(".disease").hide();};
        esui.get("ADisease1").onclick = function() {me.$(".a-disease").show();};
        esui.get("ADisease2").onclick = function() {me.$(".a-disease").hide();};
    },
    
    renderForm: function(model, data) {
        var me = this;
        $.Mustache.load("asset/tpl/form/form13.html").done(function() {
            me.$el.mustache("tpl-form13", {data: data.data});
            me.initCtrl();
        });
    }
});
