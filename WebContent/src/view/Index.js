/*
 * 首页
 * 
 * @author: Ricky
 */

es.Views.Index = Backbone.View.extend({
    el: "#Main",
    
    events: {
        "click .phm1": "openNewCRF",
        "click .phm2": "openNewADR"
    },
    
    initialize: function() {
        
    },
    
    destroy: function() {
        this.model.unbind();
        this.$el.unbind();
        this.$el.empty();
    },
    
    renderNav: function() {
        es.widgets.nav.setActive("index");
    },
    
    render: function(query) {
        this.renderNav();
        
        var me = this;
        $.Mustache.load("asset/tpl/index.html").done(function() {
            me.$el.mustache("tpl-index");
            if (me.$(".phm").length != 0) {
                me.$(".index-wrap").append($.Mustache.render("tpl-new-crf-dialog"));
                me.$(".index-wrap").append($.Mustache.render("tpl-new-adr-dialog"));
                me.initCtrl();
            }
        });
    },
    
    initCtrl: function() {
        esui.init();
        esui.get("NewCRFOK").onclick = this.newCRF;
        esui.get("NewADROK").onclick = this.newADR;
    },
    
    /*
     * 打开对话框
     */
    openNewCRF: function() {
    	esui.get("NewNo").setValue("");
        esui.get("NewAbbr").setValue("");
        esui.get("NewCRFDialog").show();
    },
    
    /*
     * 新建CRF
     */
    newCRF: function() {
        var no = $.trim(esui.get("NewNo").getValue()),
            abbr = $.trim(esui.get("NewAbbr").getValue());
        
        util.ajax.run({
            url: "",
            data: {no: no, abbr: abbr},
            success: function(response) {
                esui.get("NewCRFDialog").hide();
                es.router.navigate("crf/update/" + response.id, true);
            },
            mock: true,
            mockData: {
                success: true,
                errorMsg: "errorMsg",
                id: 1
            }
        });
    },
    
    /*
     * 打开对话框
     */
    openNewADR: function() {
        esui.get("No").setValue("");
        esui.get("NewADRDialog").show();
    },
    
    /*
     * 新建ADR
     */
    newADR: function() {
        var no = $.trim(esui.get("No").getValue());
        
        util.ajax.run({
            url: "",
            data: {no: no},
            success: function(response) {
                esui.get("NewADRDialog").hide();
                es.router.navigate("crf/updateadr/" + response.id, true);
            },
            mock: true,
            mockData: {
                success: true,
                errorMsg: "errorMsg",
                id: 1
            }
        });
    }
});
