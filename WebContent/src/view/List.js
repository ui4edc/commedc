/*
 * 病例列表
 * 
 * @author: Ricky
 */

es.Views.List = Backbone.View.extend({
    el: "#Main",
    
    events: {
        "click .tabbar a": "switchList"
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
        es.widgets.nav.setActive("list");
    },
    
    render: function(query) {
        this.renderNav();
        
        var me = this;
        $.Mustache.load("asset/tpl/list.html").done(function() {
            //填充html
            me.$el.mustache("tpl-list");
            me.$(".data").append($.Mustache.render("tpl-page"));
            
            //初始化控件
            me.initCtrl();
            
            //初始查询
            me.args = {
                pageSize: 20,
                orderBy: "lastModified",
                desc: true
            };
            me.getArgs();
            me.queryFirstPage();
            
            //获取通知
            me.getNotify();
        });
    },
    
    /*
     * 初始化控件
     */
    initCtrl: function() {
        esui.init(this.el, {
            CreateRangeType: TIME_TYPE,
            LastModifyRangeType: TIME_TYPE,
            ProgressType: PROGRESS_TYPE,
            PageSize: PAGE_SIZE,
            CreateRange: {
                range: {
                    begin: new Date(2014, 0, 1),
                    end: new Date()
                },
                valueAsObject: {
                    begin: new Date(2014, 0, 1),
                    end: new Date()
                }
            },
            LastModifyRange: {
                range: {
                    begin: new Date(2014, 0, 1),
                    end: new Date()
                },
                valueAsObject: {
                    begin: new Date(2014, 0, 1),
                    end: new Date()
                }
            }
        });
        
        var me = this;
        esui.get("Close").onclick = function() {
            me.$(".notify").remove();
        };
        esui.get("CreateRangeType").onchange = function(value) {
            if (value == 1) {
                $("#ctrlmcalCreateRange").hide();
            } else {
                $("#ctrlmcalCreateRange").show();
            }
        };
        esui.get("LastModifyRangeType").onchange = function(value) {
            if (value == 1) {
                $("#ctrlmcalLastModifyRange").hide();
            } else {
                $("#ctrlmcalLastModifyRange").show();
            }
        };
        esui.get("ProgressType").onchange = function(value) {
            if (value == 1) {
                $("#ctrltextProgress").hide();
            } else {
                $("#ctrltextProgress").show();
            }
        };
        esui.get("PageSize").onchange = function(size) {
            me.args.pageSize = size;
            me.queryFirstPage();
        };
        esui.get("PageNo").onchange = function(page) {
            me.args.pageNo = page + 1;
            me.query(me.args);
        };
        esui.get("Query").onclick = function() {
            me.getArgs();
            me.queryFirstPage();
        };
        esui.get("CRF").onclick = function() {
            me.args.crf = true;
            me.queryFirstPage();
        };
        esui.get("ADR").onclick = function() {
            me.args.crf = false;
            me.queryFirstPage();
        };
        esui.get("Grid").onsort = function (field, order) {
            me.args.orderBy = field.field;
            me.args.desc = order == "desc" ? true : false;
            me.queryFirstPage();
        };
    },
    
    /*
     * 获取参数
     */
    getArgs: function() {
        this.args.crf = esui.get("CRF").isChecked();
        var type = parseInt(this.$(".tabbar .active").attr("type"));
        this.args.type = type;
        this.args.no = $.trim(esui.get("No").getValue());
        this.args.abbr = $.trim(esui.get("Abbr").getValue());
        if (esui.get("CreateRangeType").value == 1) {
            this.args.createDate = null;
        } else {
            var date = esui.get("CreateRange").getValue().split(",");
            this.args.createDate = {
                begin: date[0],
                end: date[1]
            };
        }
        if (esui.get("LastModifyRangeType").value == 1) {
            this.args.lastModified = null;
        } else {
            var date = esui.get("LastModifyRange").getValue().split(",");
            this.args.lastModified = {
                begin: date[0],
                end: date[1]
            };
        }
        this.args.progressType = esui.get("ProgressType").value;
        if (esui.get("ProgressType").value == 1) {
            this.args.progress = null;
        } else {
            this.args.progress = $.trim(esui.get("Progress").getValue());
        }
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
        
        if (this.args.type == 2) {
            this.$("#ctrlbuttonSubmit").hide();
        } else {
            this.$("#ctrlbuttonSubmit").show();
        }
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
                {
                    field: "no",
                    title: "观察表编号",
                    sortable: true,
                    content: function(item) {
                        return $.Mustache.render("tpl-list-detail", {id: item.id, no: item.no});
                    }
                },
                {
                    field: "abbr",
                    title: "姓名缩写",
                    sortable: true,
                    content: function(item) {return item.abbr;}
                },
                {
                    field: "createDate",
                    title: "创建时间",
                    sortable: true,
                    content: function(item) {return item.createDate;}
                },
                {
                    field: "lastModified",
                    title: this.args.type == 2 ? "提交时间" : "最后修改时间",
                    sortable: true,
                    content: function(item) {return item.lastModified;}
                },
                {
                    field: "progress",
                    title: "完成度",
                    sortable: true,
                    content: function(item) {return item.progress;}
                }
            ];
            if (this.args.type) {
                
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
     * 获取通知
     */
    getNotify: function() {
        this.$(".notify").prepend($.Mustache.render("tpl-list-notify", {
            doubtNumber: 12,
            toDoNumber: 9
        })).fadeIn(1000);
    }
});
