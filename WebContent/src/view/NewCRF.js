/*
 * 新建CRF
 * 
 * @author: Ricky
 */

es.Views.NewCRF = Backbone.View.extend({
    el: "#Main",
    
    events: {
        "click .menu-title": "fold",
        "click .menu-title-standalone a": "switchSheet",
        "click .menu-item": "switchSubSheet"
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
        $.Mustache.load("asset/tpl/crf.html").done(function() {
            me.$el.mustache("tpl-crf");
            me.$(".menu-item:first").addClass("menu-item-active");
        });
    },
    
    /*
     * 折叠菜单
     */
    fold: function(e) {
        var me = $(e.target);
        me.toggleClass("menu-title-collapse");
        me.next().toggle(100);
    },
    
    /*
     * 切换一级表单
     */
    switchSheet: function(e) {
        var me = $(e.target);
        if (me.hasClass("active")) {
            return;
        }
        
        //设置激活样式
        this.$(".menu-title-standalone a").removeClass("active");
        this.$(".menu-item").removeClass("menu-item-active");
        me.addClass("active");
        
        //查询
        
    },
    
    /*
     * 切换二级表单
     */
    switchSubSheet: function(e) {
        var me = $(e.target);
        if (me.hasClass("menu-item-active")) {
            return;
        }
        
        //设置激活样式
        this.$(".menu-title-standalone a").removeClass("active");
        this.$(".menu-item").removeClass("menu-item-active");
        me.addClass("menu-item-active");
        
        //查询
        
    }
});
