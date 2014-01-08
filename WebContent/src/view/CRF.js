/*
 * CRF
 * 
 * @author: Ricky
 */

es.Views.CRF = Backbone.View.extend({
    el: "#Main",
    
    events: {
        "click .submit-crf": "onSubmitCRF",
        "click .doubt-crf": "openDoubtCRF",
        "click .check-doubt": "openCheckDoubt"
    },
    
    initialize: function() {
        this.model.bind("change:data", this.renderInfo, this);
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
        es.widgets.nav.clearActive();
    },
    
    render: function(query) {
        this.renderNav();
        
        //状态判断
        var hash = window.location.hash,
            title = "";
        this.crfId = parseInt(query);
        var isCRM = $("#RoleId").val() == "2" ? true : false;
        
        if (/^#crf\/update\/\d+$/.test(hash)) {
            title = "填写CRF";
            this.editable = true;
            this.selectADR = false;
            this.canSubmit = true;
            this.canDoubt = false;
        } else if (/^#crf\/updateadr\/\d+$/.test(hash)) {
            title = "填写CRF";
            this.editable = true;
            this.selectADR = true;
            this.canSubmit = true;
            this.canDoubt = false;
        } else if (/^#crf\/doubt\/\d+$/.test(hash)) {
            title = "质疑CRF";
            this.editable = false;
            this.selectADR = false;
            this.canSubmit = isCRM;
            this.canDoubt = true;
        } else if(/^#crf\/doubtadr\/\d+$/.test(hash)) {
            title = "质疑CRF";
            this.editable = false;
            this.selectADR = true;
            this.canSubmit = isCRM;
            this.canDoubt = true;
        }
        
        var me = this;
        $.Mustache.load("asset/tpl/crf.html").done(function() {
            me.$el.mustache("tpl-crf", {title: title});
            
            //获取基本信息
            me.model.getData({id: me.crfId});
        });
    },
    
    renderInfo: function(model, data) {
        this.hasDoubt = data.hasDoubt;
        this.$("h1").append($.Mustache.render("tpl-crf-title", {
            no: data.no,
            abbr: data.abbr,
            submitBtn: this.canSubmit ? [1] : [],
            hasDoubt: this.hasDoubt ? [1] : [],
            doubtBtn: this.canDoubt ? [1] : []
        }));
        this.$(".progressbar .progress").text("完成度：" + data.progress);
        this.$(".progressbar .bar").css({width: data.progress});
        this.progress = data.progress;
        
        //右侧表单view实例
        this.form = null;
        
        //创建菜单
        this.menu = new Tree({
            container: ".sidenav",
            data: CRF_MENU,
            onClick: this.onMenuClick,
            defaultId: this.selectADR ? 70 : 11
        });
    },
    
    /*
     * 更新完成度
     */
    updateProgress: function(progress) {
        this.$(".progressbar .progress").text("完成度：" + progress);
        this.$(".progressbar .bar").css({width: progress});
        this.progress = progress;
    },
    
    /*
     * 切换表单
     */
    onMenuClick: function(id) {
        var me = es.main;
        me.destroyForm();
        
        var formName = "Form" + id;
        me.form = new es.Views[formName]({
            model: new es.Models[formName]
        });
    },
    
    /*
     * 提交验证
     */
    onSubmitCRF: function() {
        var me = this;
        if (this.progress != "100%") {
            esui.Dialog.confirm({
                title: "全部提交",
                content: "CRF未填写完整，是否确定提交？",
                onok: function () {
                    me.submitCRF();
                }
            });
        } else {
            me.submitCRF();
        }
    },
    
    /*
     * 全部提交
     */
    submitCRF: function() {
        var data = {id: this.crfId};
        
        console.log("全部提交-请求", data);
        
        util.ajax.run({
            url: "",
            data: data,
            success: function(response) {
                console.log("全部提交-响应", response);
                
                setTimeout(function() {
                    esui.Dialog.alert({
                        title: "全部提交",
                        content: "提交成功！"
                    });
                }, 500);
            },
            mock: MOCK,
            mockData: {
                success: true
            }
        });
    },
    
    /*
     * 打开对话框
     */
    openDoubtCRF: function() {
        esui.init(this.el, {
            Field: {
                datasource: [{name: "请选择", value: 0}],
                value: 0
            }
        });
        esui.get("Description").setValue("");
        esui.get("DoubtDialog").show();
        
        var data = {};
        
        console.log("获取质疑字段-请求", data);
        
        util.ajax.run({
            url: "",
            data: data,
            success: function(response) {
                console.log("获取质疑字段-响应", response);
                
                var field = esui.get("Field");
                field.datasource = response.data;
                field.datasource.unshift({name: "请选择", value: 0});
                field.render();
                field.setValue(0);
            },
            mock: MOCK,
            mockData: {
                success: true,
                data: [
                    {name: "出生日期", value: 1},
                    {name: "身高", value: 2}
                ]
            }
        });
    },
    
    /*
     * 提交质疑
     */
    doubtCRF: function() {
        var data = {
            id: es.main.crfId,
            field: esui.get("Field").value,
            description: $.trim(esui.get("Description").getValue())
        };
        
        console.log("质疑-请求", data);
        
        util.ajax.run({
            url: "",
            data: data,
            success: function(response) {
                console.log("质疑-响应", response);
                
                esui.get("DoubtDialog").hide();
                
                setTimeout(function() {
                    esui.Dialog.alert({
                        title: "提交质疑",
                        content: "提交成功！"
                    });
                }, 500);
            },
            mock: MOCK,
            mockData: {
                success: true
            }
        });
    },
    
    /*
     * 查看质疑
     */
    openCheckDoubt: function() {
        var data = {
            id: es.main.crfId
        };
        
        console.log("查看质疑-请求", data);
        
        util.ajax.run({
            url: "",
            data: data,
            success: function(response) {
                console.log("查看质疑-响应", response);
                
                es.main.renderDoubtList(response.data);
            },
            mock: MOCK,
            mockData: {
                success: true,
                data: [
                    {
                        doubter: "doubter",
                        doubtField: "身高",
                        description: "非人类",
                        doubtDate: "2014-01-03 00:00:00"
                    },
                    {
                        doubter: "doubter",
                        doubtField: "身高",
                        description: "非人类",
                        doubtDate: "2014-01-03 00:00:00"
                    }
                ]
            }
        });
    },
    
    renderDoubtList: function(data) {
        var table = esui.get("Doubtlist");
        table.datasource = data;
        table.fields = [
            {
                field: "doubter",
                title: "质疑人",
                sortable: true,
                content: function(item) {return item.doubter;}
            },
            {
                field: "doubtField",
                title: "质疑字段",
                sortable: true,
                content: function(item) {return item.doubtField;}
            },
            {
                field: "description",
                title: "说明",
                content: function(item) {return item.description;}
            },
            {
                field: "doubtDate",
                title: "质疑时间",
                sortable: true,
                content: function(item) {return item.doubtDate;}
            }
        ];
        table.render();
        
        esui.get("CheckDoubtDialog").show();
    }
});
