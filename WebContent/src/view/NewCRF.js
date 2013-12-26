/*
 * 新建CRF
 * 
 * @author: Ricky
 */

es.Views.NewCRF = Backbone.View.extend({
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
    
    render: function() {
        this.renderNav();
    }
});
