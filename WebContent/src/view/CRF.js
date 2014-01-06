/*
 * CRF
 * 
 * @author: Ricky
 */

es.Views.CRF = Backbone.View.extend({
    el: "#Main",
    
    events: {
        "click .submit-crf": "onSubmitCRF",
        "click .doubt-crf": "openDoubtCRF"
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
        this.$("h1").append($.Mustache.render("tpl-crf-title", {
            no: data.no,
            abbr: data.abbr,
            submitBtn: this.canSubmit ? [1] : [],
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
            model: new es.Models[formName],
            parentView: me,
            editable: me.editable,
            crfId: me.crfId
        });
    },
    
    /*
     * 全部提交
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
     * 质疑
     */
    openDoubtCRF: function() {
        esui.get("Field").setValue("");
        esui.get("Description").setValue("");
        esui.get("DoubtDialog").show();
    },
    
    doubtCRF: function() {
        var data = {
            field: $.trim(esui.get("Field").getValue()),
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
                        title: "提出质疑",
                        content: "提交成功！"
                    });
                }, 500);
            },
            mock: MOCK,
            mockData: {
                success: true
            }
        });
    }
});
