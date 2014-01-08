/*
 * 账户管理
 * 
 * @author: Ricky
 */

es.Views.Account = Backbone.View.extend({
    el: "#Main",
    
    events: {
        "click .tabbar a": "switchList",
        "click .edit-account": "openEditAccount",
        "click .edit-center": "openEditCenter"
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
            },
            EAdmin: {
                datasource: [{name: "请选择", value: 0}],
                value: 0
            },
            EAuth: {
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
        esui.get("DelAccount").onclick = this.delAccount;
        esui.get("OpenNewAccount").onclick = this.openNewAccount;
        esui.get("NewAccountOK").onclick = this.newAccount;
        esui.get("EditAccountOK").onclick = this.editAccount;
        
        esui.get("DelCenter").onclick = this.delCenter;
        esui.get("OpenNewCenter").onclick = this.openNewCenter;
        esui.get("NewCenterOK").onclick = this.newCenter;
        esui.get("EditCenterOK").onclick = this.editCenter;
    },
    
    /*
     * 获取参数
     */
    getArgs: function() {
        this.args.type = parseInt(this.$(".tabbar .active").attr("type"));
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
        this.model.getData(args);
    },
    
    /*
     * 切换Tab
     */
    switchList: function(e) {
        var me = $(e.target);
        if (me.hasClass("active")) {
            return;
        }
        
        //设置激活样式
        this.$(".tabbar a").removeClass("active");
        me.addClass("active");
        
        //查询
        this.getArgs();
        this.queryFirstPage();
        
        if (this.args.type == 1) { //账户管理
            this.$("#ctrlbuttonOpenNewAccount").show();
            this.$("#ctrlbuttonDelAccount").show();
            this.$("#ctrlbuttonOpenNewCenter").hide();
            this.$("#ctrlbuttonDelCenter").hide();
        } else { //中心管理
            this.$("#ctrlbuttonOpenNewAccount").hide();
            this.$("#ctrlbuttonDelAccount").hide();
            this.$("#ctrlbuttonOpenNewCenter").show();
            this.$("#ctrlbuttonDelCenter").show();
        }
    },
    
    /*
     * 渲染列表
     */
    renderGrid: function(model, data) {
        if (data.total == 0) {
            this.$(".no-result").show();
            this.$(".data").hide();
        } else {
            this.$(".no-result").hide();
            this.$(".data").show();
            
            //表格
            var table = esui.get("Grid");
            table.datasource = data.data;
            if (this.args.type == 1) {
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
            } else {
                table.fields = [
                    {field: "name", title: "中心名称", content: function(item) {
                        return $.Mustache.render("tpl-center-name", {
                            id: item.id,
                            name: item.name
                        });
                    }},
                    {field: "code", title: "中心编码", content: function(item) {return item.code;}},
                    {field: "contractNumber", title: "合同数", content: function(item) {return item.contractNumber;}},
                    {field: "supervisor", title: "监察员", content: function(item) {return item.supervisor;}}
                ];
            }
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
     * 批量删除账户
     */
    delAccount: function() {
        var selected = $(".ui-table-row-selected a");
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
                    var data = {
                        id: id.join(",")
                    };
                    
                    console.log("批量删除账户-请求", data);
                    
                    util.ajax.run({
                        url: "",
                        data: data,
                        success: function(response) {
                            console.log("批量删除账户-响应", response);
                            
                            es.main.queryFirstPage();
                        },
                        mock: MOCK,
                        mockData: {
                            success: true
                        }
                    });
                }
            });
        }
    },
    
    /*
     * 打开添加账户对话框
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
        
        console.log("添加账户-请求:", data);
        
        util.ajax.run({
            url: "",
            data: data,
            success: function(response) {
                console.log("添加账户-响应:", response);
                
                esui.get("NewAccountDialog").hide();
                es.main.queryFirstPage();
            },
            mock: MOCK,
            mockData: {
                success: true
            }
        });
    },
    
    /*
     * 打开编辑账户对话框
     */
    openEditAccount: function(e) {
        var data = {id: e.target.id};
        es.main.curAccountId = data.id;
        
        console.log("获取账户信息-请求:", data);
        
        util.ajax.run({
            url: "",
            data: data,
            success: function(response) {
                console.log("获取账户信息-响应:", response);
                
                esui.get("EName").setValue(response.data.name);
                esui.get("EAdmin").setValue(response.data.admin);
                esui.get("EContact").setValue(response.data.contact);
                esui.get("EAuth").setValue(response.data.auth);
                esui.get("EditAccountDialog").show();
            },
            mock: MOCK,
            mockData: {
                success: true,
                data: {id: 1, name: "小明", admin: 0, contact: "13500112233", auth: 1}
            }
        });
    },
    
    /*
     * 编辑账户
     */
    editAccount: function() {
        var data = {
            id: es.main.curAccountId,
            name: $.trim(esui.get("EName").getValue()),
            admin: esui.get("EAdmin").value,
            contact: $.trim(esui.get("EContact").getValue()),
            auth: esui.get("EAuth").value
        };
        
        console.log("编辑账户-请求:", data);
        
        util.ajax.run({
            url: "",
            data: data,
            success: function(response) {
                console.log("编辑账户-响应:", response);
                
                esui.get("EditAccountDialog").hide();
                es.main.query(es.main.args);
            },
            mock: MOCK,
            mockData: {
                success: true
            }
        });
    },
    
    /*
     * 批量删除中心
     */
    delCenter: function() {
        var selected = $(".ui-table-row-selected a");
        if (selected.length == 0) {
            esui.Dialog.alert({
                title: "批量删除",
                content: "请选择要删除的中心。"
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
                    var data = {
                        id: id.join(",")
                    };
                    
                    console.log("批量删除中心-请求", data);
                    
                    util.ajax.run({
                        url: "",
                        data: data,
                        success: function(response) {
                            console.log("批量删除中心-响应", response);
                            
                            es.main.queryFirstPage();
                        },
                        mock: MOCK,
                        mockData: {
                            success: true
                        }
                    });
                }
            });
        }
    },
    
    /*
     * 打开添加中心对话框
     */
    openNewCenter: function() {
        esui.get("CenterName").setValue("");
        esui.get("Code").setValue("");
        esui.get("NewCenterDialog").show();
    },
    
    /*
     * 添加中心
     */
    newCenter: function() {
        var data = {
            name: $.trim(esui.get("CenterName").getValue()),
            code: $.trim(esui.get("Code").getValue())
        };
        
        console.log("添加中心-请求:", data);
        
        util.ajax.run({
            url: "",
            data: data,
            success: function(response) {
                console.log("添加中心-响应:", response);
                
                esui.get("NewCenterDialog").hide();
                es.main.queryFirstPage();
            },
            mock: MOCK,
            mockData: {
                success: true
            }
        });
    },
    
    /*
     * 打开编辑中心对话框
     */
    openEditCenter: function(e) {
        var data = {id: e.target.id};
        es.main.curCenterId = data.id;
        
        console.log("获取中心信息-请求:", data);
        
        util.ajax.run({
            url: "",
            data: data,
            success: function(response) {
                console.log("获取中心信息-响应:", response);
                
                esui.get("ECenterName").setValue(response.data.name);
                esui.get("ECode").setValue(response.data.code);
                esui.get("EditCenterDialog").show();
            },
            mock: MOCK,
            mockData: {
                success: true,
                data: {id: 1, name: "北医", code: "333"}
            }
        });
    },
    
    /*
     * 编辑中心
     */
    editCenter: function() {
        var data = {
            id: es.main.curCenterId,
            name: $.trim(esui.get("ECenterName").getValue()),
            code: $.trim(esui.get("ECode").getValue())
        };
        
        console.log("编辑中心-请求:", data);
        
        util.ajax.run({
            url: "",
            data: data,
            success: function(response) {
                console.log("编辑中心-响应:", response);
                
                esui.get("EditCenterDialog").hide();
                es.main.query(es.main.args);
            },
            mock: MOCK,
            mockData: {
                success: true
            }
        });
    }
});
