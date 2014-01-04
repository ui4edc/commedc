/*
 * CRF
 * 
 * @author: Ricky
 */

es.Views.CRF = Backbone.View.extend({
    el: "#Main",
    
    events: {
        "click .submit-crf": "onSubmitCRF"
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
        } else if (/^#crf\/updateadr\/\d+$/.test(hash)) {
            title = "填写CRF";
            this.editable = true;
            this.selectADR = true;
            this.canSubmit = true;
        } else if (/^#crf\/doubt\/\d+$/.test(hash)) {
            title = "质疑CRF";
            this.editable = false;
            this.selectADR = false;
            this.canSubmit = isCRM;
        } else if(/^#crf\/doubtadr\/\d+$/.test(hash)) {
            title = "质疑CRF";
            this.editable = false;
            this.selectADR = true;
            this.canSubmit = isCRM;
        }
        
        var me = this;
        $.Mustache.load("asset/tpl/crf.html").done(function() {
            me.$el.mustache("tpl-crf", {title: title});
            me.model.getData({id: me.crfId});
        });
    },
    
    renderInfo: function(model, data) {
        console.log(data);
        this.$("h1").append($.Mustache.render("tpl-crf-title", {
            no: data.no,
            abbr: data.abbr,
            submitBtn: this.canSubmit ? [1] : []
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
        util.ajax.run({
            url: "",
            data: {id: this.crfId},
            success: function(response) {
                esui.Dialog.alert({
                    title: "全部提交",
                    content: "提交成功！"
                });
            },
            mock: true,
            mockData: {
                success: true,
                errorMsg: "errorMsg"
            }
        });
    }
});
