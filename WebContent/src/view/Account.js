/*
 * 账户管理
 * 
 * @author: Ricky
 */

es.Views.Account = Backbone.View.extend({
    el: "#Main",
    
    events: {
        
    },
    
    initialize: function() {
        this.model.bind("change:data", this.renderGrid, this);
    },
    
    destroy: function() {
        esui.dispose();
        this.model.unbind();
        this.$el.unbind();
        this.$el.empty();
    },
    
    renderNav: function() {
        es.widgets.nav.setActive("account");
    },
    
    render: function(query) {
        this.renderNav();
        
        var me = this;
        $.Mustache.load("asset/tpl/account.html").done(function() {
            me.$el.mustache("tpl-account");
            me.$(".data").append($.Mustache.render("tpl-page"));
            me.initCtrl();
            //初始查询
            me.args = {pageSize: 20};
            me.getArgs();
            me.queryFirstPage();
        });
    },
    
    /*
     * 初始化控件
     */
    initCtrl: function() {
        esui.init(this.el, {
            PageSize: PAGE_SIZE
        });
        
        var me = this;
        esui.get("PageSize").onchange = function(value, item) {
            me.args.pageSize = value;
            me.queryFirstPage();
        };
        esui.get("PageNo").onchange = function(page) {
            me.args.pageNo = page + 1;
            me.query(me.args);
        };
    },
    
    /*
     * 获取参数
     */
    getArgs: function() {
        
    },
    
    /*
     * 查询第一页
     */
    queryFirstPage: function() {
        this.args.pageNo = 1;
        this.pageRendered = false;
        this.query(this.args);
    },
    
    /*
     * 发起请求
     */
    query: function(args) {
        console.log(args);
        this.model.getData(args);
    },
    
    /*
     * 渲染列表
     */
    renderGrid: function(model, data) {
        console.log(data);
        if (data.total == 0) {
            this.$(".no-result").show();
            this.$(".data").hide();
        } else {
            this.$(".no-result").hide();
            this.$(".data").show();
            
            //表格
            var table = esui.get("Grid");
            table.datasource = data.data;
            table.fields = [
                {field: "id", title: "账户ID", content: function(item) {return item.id;}},
                {field: "name", title: "账户名称", content: function(item) {return item.name;}},
                {field: "admin", title: "实际管理员", content: function(item) {return item.admin;}},
                {field: "contact", title: "联系方式", content: function(item) {return item.contact;}},
                {field: "auth", title: "权限说明", content: function(item) {return item.auth;}}
            ];
            table.render();
            
            //页码
            this.renderPage(data.total);
        }
    },
    
    /*
     * 渲染页码
     */
    renderPage: function(total) {
        if (!this.pageRendered) {
            var pager = esui.get("PageNo");
            pager.total = Math.ceil(total / esui.get("PageSize").value);
            pager.page = 0;
            pager.render();
            this.pageRendered = true;
        }
        this.$(".row-count").text("共 " + total + " 条");
    }
});
