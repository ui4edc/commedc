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
            me.$(".account-wrap").append($.Mustache.render("tpl-new-account-dialog"));
            
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
            PageSize: PAGE_SIZE,
            Admin: {
                datasource: [{name: "请选择", value: 0}],
                value: 0
            },
            Auth: {
                datasource: [
                    {name: "请选择", value: 0},
                    {name: "药师", value: 1},
                    {name: "CRM", value: 2},
                    {name: "DM", value: 3}
                ],
                value: 0
            }
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
        esui.get("OpenNewAccount").onclick = this.openNewAccount;
        esui.get("DelAccount").onclick = this.delAccount;
        esui.get("NewAccountOK").onclick = this.newAccount;
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
        console.log("请求参数:", args);
        this.model.getData(args);
    },
    
    /*
     * 渲染列表
     */
    renderGrid: function(model, data) {
        console.log("返回数据:", data);
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
                {field: "name", title: "账户名称", content: function(item) {
                    return $.Mustache.render("tpl-account-name", {
                        id: item.id,
                        name: item.name
                    });
                }},
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
    },
    
    /*
     * 批量删除
     */
    delAccount: function() {
        var selected = $(".ui-table-row-selected span");
        if (selected.length == 0) {
            esui.Dialog.alert({
                title: "批量删除",
                content: "请选择要删除的账户。"
            });
        } else {
            esui.Dialog.confirm({
                title: "批量删除",
                content: "确定删除吗？",
                onok: function () {
                    var id = [];
                    $.each(selected, function(index, val) {
                        id.push(parseInt(val.id));
                    });
                    
                    util.ajax.run({
                        url: "",
                        data: {id: id.join(",")},
                        success: function(response) {
                            es.main.queryFirstPage();
                        },
                        mock: true,
                        mockData: {
                            success: true,
                            errorMsg: "errorMsg"
                        }
                    });
                }
            });
        }
    },
    
    /*
     * 打开对话框
     */
    openNewAccount: function() {
        esui.get("Name").setValue("");
        esui.get("Admin").setValue(0);
        esui.get("Contact").setValue("");
        esui.get("Auth").setValue(0);
        esui.get("NewAccountDialog").show();
    },
    
    /*
     * 添加账户
     */
    newAccount: function() {
        var data = {
            name: $.trim(esui.get("Name").getValue()),
            admin: esui.get("Admin").value,
            contact: $.trim(esui.get("Contact").getValue()),
            auth: esui.get("Auth").value
        };
        console.log("请求参数:", data);
        util.ajax.run({
            url: "",
            data: data,
            success: function(response) {
                esui.get("NewAccountDialog").hide();
                es.main.queryFirstPage();
            },
            mock: true,
            mockData: {
                success: true,
                errorMsg: "errorMsg"
            }
        });
    }
});
