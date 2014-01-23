/*
 * 树形菜单
 * 
 * @author: Ricky
 */

function Tree(optin) {
    this.container = optin.container;
    this.data = optin.data;
    this.onClick = optin.onClick;
    this.defaultId = optin.defaultId;
    this.init();
}

Tree.prototype = {
    init: function() {
        var html = [];
        $.each(this.data, function(index, val) {
            html.push($.Mustache.render(val.children ? "tpl-tree" : "tpl-tree-standalone", {
                data: val
            }));
        });
        $(this.container).append(html.join(""));
        
        //绑定事件
        var me = this;
        $(this.container).delegate(".menu-title", "click", function(e) {
            me.fold(e);
        });
        $(this.container).delegate(".menu-title-standalone a", "click", function(e) {
            me.onTitleClick(e);
        });
        $(this.container).delegate(".menu-item", "click", function(e) {
            me.onItemClick(e);
        });
        
        //选中默认结点
        $(this.container + " #TreeNode" + this.defaultId).click();
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
    onTitleClick: function(e) {
        var me = $(e.target);
        if (me.hasClass("active")) {
            return;
        }
        
        $(this.container + " .menu-title-standalone a").removeClass("active");
        $(this.container + " .menu-item").removeClass("menu-item-active");
        me.addClass("active");
        
        this.onClick(parseInt(me.attr("id").replace(/[a-z]+/i, ""), 10));
    },
    
    /*
     * 切换二级表单
     */
    onItemClick: function(e) {
        var me = $(e.target);
        if (me.hasClass("menu-item-active")) {
            return;
        }
        
        $(this.container + " .menu-title-standalone a").removeClass("active");
        $(this.container + " .menu-item").removeClass("menu-item-active");
        me.addClass("menu-item-active");
        
        this.onClick(parseInt(me.attr("id").replace(/[a-z]+/i, ""), 10));
    },
    
    destroy: function() {
        $(this.container).undelegate().empty();
    }
};
