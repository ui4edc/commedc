/*
 * 数据字典
 * 
 * @author: Ricky
 */

es.Views.Dict = Backbone.View.extend({
    el: "#Main",
    
    events: {
        
    },
    
    initialize: function() {
        this.model.bind("change:dict", this.renderDict, this);
        this.model.bind("change:item", this.renderItem, this);
        this.model.bind("change:base", this.renderBase, this);
    },
    
    destroy: function() {
        this.menu && this.menu.destroy();
        esui.dispose();
        this.model.unbind();
        this.$el.unbind();
        this.$el.empty();
    },
    
    renderNav: function() {
        es.widgets.nav.setActive("dict");
    },
    
    render: function(query) {
        this.renderNav();
        
        var me = this;
        $.Mustache.load("asset/tpl/dict.html").done(function() {
            me.$el.mustache("tpl-dict");
            esui.init();
            me.model.getDict();
        });
    },
    
    renderDict: function(model, dict) {
        console.log(dict);
        //创建字典菜单
        this.menu = new Tree({
            container: ".sidebar",
            data: dict.data,
            onClick: this.onDictClick,
            defaultId: dict.data[0].id
        });
    },
    
    onDictClick: function(id) {
        var view = es.main;
        view.model.getItem({dictId: id});
    },
    
    renderItem: function(model, item) {
        console.log(item);
        //创建条目
        var table = esui.get("Items");
        table.datasource = item.data;
        table.fields = [
            {field: "name", title: "条目名称", content: function(item) {return item.name;}},
            {field: "abbr", title: "缩写", content: function(item) {return item.abbr;}}
        ];
        table.render();
    },
    
    renderBase: function(model, base) {
        console.log(base);
        //创建底层字典
        var table = esui.get("Base");
        table.datasource = base.data;
        table.fields = [
            {field: "name", title: "条目名称", content: function(item) {return item.name;}},
            {field: "abbr", title: "缩写", content: function(item) {return item.abbr;}}
        ];
        table.render();
    }
});
