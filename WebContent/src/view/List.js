/*
 * 病例列表
 * 
 * @author: Ricky
 */

es.Views.List = Backbone.View.extend({
    el: "#Main",
    
    events: {
        
    },
    
    initialize: function() {
        
    },
    
    destroy: function() {
        this.model.unbind();
        this.$el.unbind();
        this.$el.empty();
    },
    
    renderNav: function() {
        es.widgets.nav.setActive("list");
    },
    
    render: function() {
        this.renderNav();
        
        var me = this;
        $.Mustache.load('../../asset/tpl/list.html').done(function() {
            me.$el.mustache("tpl-list");
        });
    }
});
