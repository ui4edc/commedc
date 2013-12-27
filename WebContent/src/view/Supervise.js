/*
 * 数据监察
 * 
 * @author: Ricky
 */

es.Views.Supervise = Backbone.View.extend({
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
        es.widgets.nav.setActive("supervise");
    },
    
    render: function() {
        this.renderNav();
        
        var me = this;
        $.Mustache.load('../../asset/tpl/supervise.html').done(function() {
            me.$el.mustache("tpl-supervise");
        });
    }
});
