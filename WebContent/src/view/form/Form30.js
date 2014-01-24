/*
 * 用药情况
 * 
 * @author: Ricky
 */

es.Views.Form30 = Backbone.View.extend({
    el: ".crf-form",
    
    events: {
        "click .times a": "switchTimes",
        "click .times span": "addTimes",
        "click .add-bottle": "addBottle",
        "click .add-injection": "addInjection",
        "click .add-drug": "addDrug"
    },
    
    initialize: function() {
        this.model.bind("change:data", this.renderForm, this);
        this.model.getData({
            id: es.main.crfId,
            times: 1
        });
        this.rendered = false;
    },
    
    destroy: function() {
        esui.dispose();
        this.model.unbind();
        this.$el.unbind();
        this.$el.empty();
    },
    
    switchTimes: function(e) {
        var me = $(e.target);
        if (me.hasClass("active")) {
            return;
        }
        
        this.$(".times a").removeClass("active");
        me.addClass("active");
        
        this.model.getData({
            id: es.main.crfId,
            times: parseInt(me.attr("times"), 10)
        });
    },
    
    addTimes: function(e) {
        var me = $(e.target);
        me.before($.Mustache.render("tpl-form30-times", {
            n: this.$(".times a").length + 1
        }));
    },
    
    renderForm: function(model, data) {
        var disabled = es.main.editable ? "" : "disabled:true";
        
        if (!this.rendered) {
            //插入质疑对话框
            if (es.main.canDoubt) {
                es.main.$el.append($.Mustache.render("tpl-doubt-dialog"));
            }
            es.main.$el.append($.Mustache.render("tpl-check-doubt-dialog"));
            
            var me = this;
            $.Mustache.load("asset/tpl/form/form30.html").done(function() {
                me.$el.mustache("tpl-form30", {
                    data: data.data,
                    disabled: disabled,
                    save: es.main.editable ? [1] : []
                });
                //用药次数
                var btnHtml = [];
                for (var i = 0, n = data.data.total; i < n; i++) {
                    btnHtml.push($.Mustache.render("tpl-form30-times", {
                        n: i + 1
                    }));
                }
                me.$(".times").prepend(btnHtml.join(""));
                me.$(".times a:first").addClass("active");
                //用药情况
                me.$(".form30-body").mustache("tpl-form30-body", {
                    data: data.data,
                    disabled: disabled,
                    save: es.main.editable ? [1] : []
                });
                me.$(".bottles").prepend($.Mustache.render("tpl-form30-bottle", {
                    bottle: data.data.bottle,
                    disabled: disabled
                }));
                me.$(".injections").prepend($.Mustache.render("tpl-form30-injection", {
                    injection: data.data.injection,
                    disabled: disabled
                }));
                me.$(".add-drug").parent().before($.Mustache.render("tpl-form30-drug", {
                    banDrug: data.data.banDrug,
                    disabled: disabled
                }));
                
                me.initCtrl(data.data);
                
                me.rendered = true;
            });
        } else { //切换次数
            esui.dispose("Start");
            esui.dispose("End");
            
            //用药情况
            this.$(".form30-body").html($.Mustache.render("tpl-form30-body", {
                data: data.data,
                disabled: disabled,
                save: es.main.editable ? [1] : []
            }));
            this.$(".bottles").prepend($.Mustache.render("tpl-form30-bottle", {
                bottle: data.data.bottle,
                disabled: disabled
            }));
            this.$(".injections").prepend($.Mustache.render("tpl-form30-injection", {
                injection: data.data.injection,
                disabled: disabled
            }));
            this.$(".add-drug").parent().before($.Mustache.render("tpl-form30-drug", {
                banDrug: data.data.banDrug,
                disabled: disabled
            }));
            
            this.initCtrl(data.data);
        }
    },
    
    initCtrl: function(data) {
        esui.init(document.body, {
            Start: {
                range: CRF_RANGE,
                value: data.startDate
            },
            End: {
                range: CRF_RANGE,
                value: data.endDate
            }
        });
        
        //赋值
        switch (data.history) {
            case 1: esui.get("History1").setChecked(true); this.$(".history").show(); break;
            case 3: esui.get("History3").setChecked(true);
        }
        switch (data.adr) {
            case 1: esui.get("ADR1").setChecked(true); break;
            case 3: esui.get("ADR3").setChecked(true);
        }
        switch (data.solvent) {
            case 2: esui.get("Solvent2").setChecked(true); break;
            case 3: esui.get("Solvent3").setChecked(true);
        }
        if (data.prepareTimeUd) {
            esui.get("PrepareTimeUd").setChecked(true);
        }
        switch (data.location) {
            case 2: esui.get("Location2").setChecked(true); break;
            case 3: esui.get("Location3").setChecked(true);
        }
        switch (data.way) {
            case 2: esui.get("Way2").setChecked(true); break;
            case 3: esui.get("Way3").setChecked(true);
        }
        if (data.sameBottle == 1) {
            esui.get("SameBottle1").setChecked(true);
            this.$(".bottles").show();
        }
        if (data.sameGroup == 1) {
            esui.get("SameGroup1").setChecked(true);
            this.$(".group").show();
        }
        switch (data.gpSolvent) {
            case 2: esui.get("GpSolvent2").setChecked(true); break;
            case 3: esui.get("GpSolvent3").setChecked(true);
        }
        if (data.hasInjection == 1) {
            esui.get("HasInjection1").setChecked(true);
            this.$(".injections").show();
        }
        if (data.hasBan == 1) {
            esui.get("HasBan1").setChecked(true);
            this.$(".ban").show();
        }
        switch (data.ban) {
            case 2: esui.get("Ban2").setChecked(true); break;
            case 3: esui.get("Ban3").setChecked(true); break;
            case 4: esui.get("Ban4").setChecked(true);
        }
        if (data.hasFood == 1) {
            esui.get("HasFood1").setChecked(true);
            this.$(".food").show();
        }
        if (data.food != "") {
            $.each(data.food.split(","), function(index, val) {
                esui.get("Food" + val).setChecked(true);
            });
        }
        
        //事件
        var me = this;
        esui.get("Start").onchange = function(value) {esui.get("Start").setValueAsDate(value);};
        esui.get("End").onchange = function(value) {esui.get("End").setValueAsDate(value);};
        esui.get("SameBottle1").onclick = function() {me.$(".bottles").show();};
        esui.get("SameBottle2").onclick = function() {me.$(".bottles").hide();};
        esui.get("SameGroup1").onclick = function() {me.$(".group").show();};
        esui.get("SameGroup2").onclick = function() {me.$(".group").hide();};
        esui.get("HasInjection1").onclick = function() {me.$(".injections").show();};
        esui.get("HasInjection2").onclick = function() {me.$(".injections").hide();};
        esui.get("HasBan1").onclick = function() {me.$(".ban").show();};
        esui.get("HasBan2").onclick = function() {me.$(".ban").hide();};
        esui.get("HasFood1").onclick = function() {me.$(".food").show();};
        esui.get("HasFood2").onclick = function() {me.$(".food").hide();};
        
        if (!this.rendered) {
            esui.get("History1").onclick = function() {me.$(".history").show();};
            esui.get("History2").onclick = function() {me.$(".history").hide();};
            esui.get("History3").onclick = function() {me.$(".history").hide();};
            
            if (es.main.canDoubt) {
                esui.get("DoubtOK").onclick = es.main.doubtCRF;
            }
            if (es.main.editable) {
                esui.get("Save").onclick = this.save;
            }
            if (!es.main.editable) {
                esui.get("Start").disable();
                esui.get("End").disable();
            }
        }
    },
    
    addBottle: function(e) {
        $(e.target).parent().before($.Mustache.render("tpl-form30-bottle", {
            bottle: [{no: this.$(".bottle").length + 1}],
            disabled: ""
        }));
        esui.init();
    },
    
    addInjection: function(e) {
        $(e.target).parent().before($.Mustache.render("tpl-form30-injection", {
            injection: [{no: this.$(".injection").length + 1}],
            disabled: ""
        }));
        esui.init();
    },
    
    addDrug: function(e) {
        $(e.target).parent().before($.Mustache.render("tpl-form30-drug", {
            banDrug: [{no: this.$(".ban-drug").length + 1}],
            disabled: ""
        }));
        esui.init();
    },
    
    save: function() {
       var me = es.main;
       
       var data = {
           id: me.crfId,
           no: me.model.get("data").no,
           times: parseInt(me.$(".tabbar .active").attr("times"), 10),
           
           history: parseInt(esui.get("History1").getGroup().getValue(), 10),
           adr: parseInt(esui.get("ADR1").getGroup().getValue(), 10),
           adrtxt: $.trim(esui.get("ADRName").getValue()),
           batchNumber: $.trim(esui.get("BatchNumber").getValue()),
           dose: $.trim(esui.get("Dose").getValue()),
           startDate: esui.get("Start").getValue(),
           startH: $.trim(esui.get("StartH").getValue()),
           startM: $.trim(esui.get("StartM").getValue()),
           endDate: esui.get("End").getValue(),
           endH: $.trim(esui.get("EndH").getValue()),
           endM: $.trim(esui.get("EndM").getValue()),
           solventDose: $.trim(esui.get("SolventDose").getValue()),
           solvent: parseInt(esui.get("Solvent1").getGroup().getValue(), 10),
           solventName: $.trim(esui.get("SolventName").getValue()),
           solventPercent: $.trim(esui.get("SolventPercent").getValue()),
           prepareTime: $.trim(esui.get("PrepareTime").getValue()),
           prepareTimeUd: esui.get("PrepareTimeUd").isChecked(),
           location: parseInt(esui.get("Location1").getGroup().getValue(), 10),
           way: parseInt(esui.get("Way1").getGroup().getValue(), 10),
           way1Speed: $.trim(esui.get("Way1Speed").getValue()),
           way1Time: $.trim(esui.get("Way1Time").getValue()),
           way2Speed: $.trim(esui.get("Way2Speed").getValue()),
           way3Name: $.trim(esui.get("Way3Name").getValue()),
           way3Speed: $.trim(esui.get("Way3Speed").getValue()),
           way3Unit: $.trim(esui.get("Way3Unit").getValue()),
           
           sameBottle: parseInt(esui.get("SameBottle1").getGroup().getValue(), 10),
           bottle: [],
           
           sameGroup: parseInt(esui.get("SameGroup1").getGroup().getValue(), 10),
           gpSolvent: parseInt(esui.get("GpSolvent1").getGroup().getValue(), 10),
           gpSolvent1Dose: $.trim(esui.get("GpSolvent1Dose").getValue()),
           gSolvent2Dose: $.trim(esui.get("GpSolvent2Dose").getValue()),
           gpSolvent3Name: $.trim(esui.get("GpSolvent3Name").getValue()),
           gpSolvent3Percent: $.trim(esui.get("GpSolvent3Percent").getValue()),
           gpSolvent3Dose: $.trim(esui.get("GpSolvent3Dose").getValue()),
           
           hasInjection: parseInt(esui.get("HasInjection1").getGroup().getValue(), 10),
           injection: [],
           
           hasBan: parseInt(esui.get("HasBan1").getGroup().getValue(), 10),
           ban: parseInt(esui.get("Ban1").getGroup().getValue(), 10),
           banColor: $.trim(esui.get("BanColor").getValue()),
           bantxt: $.trim(esui.get("BanName").getValue()),
           banDrug: [],
           
           hasFood: parseInt(esui.get("HasFood1").getGroup().getValue(), 10),
           food: esui.get("Food1").getGroup().getValue(),
           foodtxt: $.trim(esui.get("FoodName").getValue())
       };
       
       var bottle = me.$(".bottle");
       $.each(bottle, function(index, val) {
           var no = index + 1;
           data.bottle.push({
               name: $.trim(esui.get("BottleName" + no).getValue()),
               dose: $.trim(esui.get("BottleDose" + no).getValue()),
               unit: $.trim(esui.get("BottleUnit" + no).getValue())
           });
       });
       var injection = me.$(".injection");
       $.each(injection, function(index, val) {
           var no = index + 1;
           data.injection.push({
               name: $.trim(esui.get("InjectionName" + no).getValue()),
               dose: $.trim(esui.get("InjectionDose" + no).getValue()),
               unit: $.trim(esui.get("InjectionUnit" + no).getValue())
           });
       });
       var banDrug = me.$(".ban-drug");
       $.each(banDrug, function(index, val) {
           var no = index + 1;
           data.banDrug.push({
               name: $.trim(esui.get("DrugName" + no).getValue()),
               dose: $.trim(esui.get("DrugDose" + no).getValue()),
               unit: $.trim(esui.get("DrugUnit" + no).getValue())
           });
       });
       
       console.log("保存表单-请求", data);
       
       util.ajax.run({
            url: "",
            data: JSON.stringify(data),
            json: true,
            success: function(response) {
                console.log("保存表单-响应:", response);
                
                esui.Dialog.alert({title: "保存", content: "保存成功！"});
                me.updateProgress(response.progress);
            },
            mock: MOCK,
            mockData: {
                success: true,
                progress: "30%"
            }
        });
    }
});
