/*
 * 首页
 * 
 * @author: Ricky
 */

es.Views.Index = Backbone.View.extend({
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
        es.widgets.nav.setActive("index");
    },
    
    render: function(query) {
        this.renderNav();
        
        var me = this;
        $.Mustache.load("asset/tpl/index.html").done(function() {
            me.$el.mustache("tpl-index");
        });
    }
});
