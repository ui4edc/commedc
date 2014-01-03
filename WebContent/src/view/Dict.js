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
        this.$("#ctrltextItem").autocomplete("destroy");
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
            me.initCtrl();
            me.model.getDict();
        });
    },
    
    initCtrl: function() {
        esui.init();
        this.$("#ctrltextItem").autocomplete({
            source: [],
            select: this.onSelectBaseItem
        });
        esui.get("AddToDict").onclick = this.addToDict;
    },
    
    /*
     * 创建字典
     */
    renderDict: function(model, dict) {
        console.log("字典：", dict);
        this.menu = new Tree({
            container: ".sidebar",
            data: dict.data,
            onClick: this.onDictClick,
            defaultId: dict.data[0].id
        });
    },
    
    onDictClick: function(id) {
        var me = es.main;
        me.dictId = id;
        me.model.getItem({dictId: id});
    },
    
    /*
     * 创建条目
     */
    renderItem: function(model, item) {
        console.log("条目：", item);
        var table = esui.get("Items");
        table.datasource = item.data;
        table.fields = [
            {field: "name", title: "条目名称", content: function(item) {
                return $.Mustache.render("tpl-dict-item", {
                    id: item.id,
                    name: item.name
                });
            }},
            {field: "abbr", title: "缩写", content: function(item) {return item.abbr;}}
        ];
        table.render();
    },
    
    /*
     * 创建底层字典
     */
    renderBase: function(model, base) {
        console.log("底层字典：", base);
        var table = esui.get("Base");
        table.datasource = base.data;
        table.fields = [
            {field: "name", title: "条目名称", content: function(item) {return item.name;}},
            {field: "abbr", title: "缩写", content: function(item) {return item.abbr;}}
        ];
        table.render();
        
        //添加autocomplete数据
        $("#ctrltextItem").autocomplete("option", "source", base.data);
    },
    
    onSelectBaseItem: function(evt, ui) {
        var me = es.main;
        me.baseItemId = ui.item.id;
        me.baseItemName = ui.item.name;
        setTimeout(function() {
            esui.get("Item").setValue(ui.item.name);
        }, 10);
    },
    
    addToDict: function() {
        var selected = $(".ui-table-row-selected span"),
            item = $.trim(esui.get("Item").getValue()),
            me = es.main,
            baseItemId = null,
            baseItemName = null;
        
        if (item == "") {
            esui.Dialog.alert({
                title: "添加到底层字典",
                content: "请填写底层字典条目。"
            });
            return;
        }
        
        if (selected.length == 0) {
            esui.Dialog.alert({
                title: "添加到底层字典",
                content: "请选择条目。"
            });
            return;
        }
        
		if (me.baseItemId && me.baseItemName == item) { //从autocomplete选择
			baseItemId = me.baseItemId;
		} else {
		    var tempId;
		    $.each(me.model.get("base").data, function(index, val) {
		        if (val.name == item) {
		            tempId = val.id;
		        }
		    });
		    if (tempId) { //输入在底层字典中
		        baseItemId = tempId;
		    } else {
			    baseItemName = item;
			}
		}
		
		var selectedItemId = [];
		$.each(selected, function(index, val) {
			selectedItemId.push(parseInt(val.id));
		});
		
		var data = {
		    selectedItemId: selectedItemId.join(","),
            baseItemId: baseItemId,
            baseItemName: baseItemName
		};
		console.log(data);
		util.ajax.run({
			url: "",
			data: data,
			success: function(response) {
				if (baseItemId == null) { //新建
					me.model.getBase({dictId: me.dictId});
				}
			},
			mock: true,
			mockData: {
				success: true,
				errorMsg: "errorMsg"
			}
		});
    }
});
