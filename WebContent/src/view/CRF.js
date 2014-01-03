/*
 * CRF
 * 
 * @author: Ricky
 */

es.Views.CRF = Backbone.View.extend({
    el: "#Main",
    
    events: {
        
    },
    
    initialize: function() {
        
    },
    
    destroy: function() {
        this.menu && this.menu.destroy();
        this.destroyForm();
        this.model.unbind();
        this.$el.unbind();
        this.$el.empty();
    },
    
    destroyForm: function() {
        var form = this.form;
        form && form.destroy();
    },
    
    renderNav: function() {
        es.widgets.nav.setActive("index");
    },
    
    render: function(query) {
        this.renderNav();
        
        //状态判断
        var hash = window.location.hash,
            title = "";
        if (hash.indexOf("create") > 0) {
            title = "新建CRF";
            this.status = "create";
            this.crfId = null;
        } else if (hash.indexOf("update") > 0) {
            title = "修改CRF";
            this.status = "update";
            this.crfId = parseInt(query);
        } else if (hash.indexOf("doubt") > 0) {
            title = "质疑CRF";
            this.status = "doubt";
            this.crfId = parseInt(query);
        }
        
        var me = this;
        $.Mustache.load("asset/tpl/crf.html").done(function() {
            me.$el.mustache("tpl-crf", {title: title});
            
            esui.init();
            esui.get("SubmitAll").setDisabled(true);
            
            //右侧表单view实例
            me.form = null;
            
            //创建菜单
            me.menu = new Tree({
                container: ".sidenav",
                data: C_MENU,
                onClick: me.onMenuClick,
                defaultId: 1
            });
        });
    },
    
    onMenuClick: function(id) {
        var me = es.main;
        me.destroyForm();
        
        switch (id) {
            case 1: formName = "A"; break;
            case 2: formName = "B"; break;
            case 3: formName = "C";
        }
        
        me.form = new es.Views[formName]({
            model: new es.Models[formName],
            status: view.status,
            crfId: view.crfId
        });
    }
});
