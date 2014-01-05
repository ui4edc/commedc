/*
 * 患者信息-个人史 + 过敏史
 * 
 * @author: Ricky
 */

es.Views.Form12 = Backbone.View.extend({
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
        esui.get("Food1").onclick = function() {me.$(".food").show();};
        esui.get("Food2").onclick = function() {me.$(".food").hide();};
        esui.get("Drug1").onclick = function() {me.$(".drug").show();};
        esui.get("Drug2").onclick = function() {me.$(".drug").hide();};
        esui.get("Material1").onclick = function() {me.$(".material").show();};
        esui.get("Material2").onclick = function() {me.$(".material").hide();};
    },
    
    renderForm: function(model, data) {
        var me = this;
        $.Mustache.load("asset/tpl/form/form12.html").done(function() {
            me.$el.mustache("tpl-form12", {data: data.data});
            me.initCtrl();
        });
    }
});
