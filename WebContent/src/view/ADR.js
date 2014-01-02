/*
 * ADR
 * 
 * @author: Ricky
 */

es.Views.ADR = Backbone.View.extend({
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
            title = "新建ADR";
            this.status = "create";
            this.adrId = null;
        } else if (hash.indexOf("update") > 0) {
            title = "修改ADR";
            this.status = "update";
            this.adrId = parseInt(query);
        } else if (hash.indexOf("doubt") > 0) {
            title = "质疑ADR";
            this.status = "doubt";
            this.adrId = parseInt(query);
        }
        
        var me = this;
        $.Mustache.load("asset/tpl/adr.html").done(function() {
            me.$el.mustache("tpl-adr", {title: title});
            
            esui.init();
            esui.get("SubmitAll").setDisabled(true);
            
            //右侧表单view实例
            me.form = null;
            
            //创建菜单
            me.menu = new Tree({
                container: ".sidenav",
                data: C_MENU,
                onClick: me.onMenuClick,
                defaultId: 12
            });
        });
    },
    
    onMenuClick: function(id) {
        var view = es.main;
        view.destroyForm();
        
        switch (id) {
            case 1: formName = "A"; break;
            case 2: formName = "B"; break;
            case 3: formName = "C";
        }
        
        view.form = new es.Views[formName]({
            model: new es.Models[formName],
            status: view.status,
            adrId: view.adrId
        });
    }
});
