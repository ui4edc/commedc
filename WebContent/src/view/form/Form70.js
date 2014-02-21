/*
 * ADR
 * 
 * @author: Ricky
 */

es.Views.Form70 = Backbone.View.extend({
    el: ".crf-form",
    
    events: {
        "click .add1": "addRow1",
        "click .add2": "addRow2"
    },
    
    initialize: function() {
        this.model.bind("change:data", this.renderForm, this);
        this.model.getData({id: es.main.crfId});
    },
    
    destroy: function() {
        $("body").undelegate(".ui-table-editor input", "focus");
        $(".ui-table-editor input").autocomplete("destroy");
        esui.dispose();
        this.model.unbind();
        this.$el.unbind();
        this.$el.empty();
    },
    
    renderForm: function(model, data) {
        //插入质疑对话框
        if (es.main.canDoubt) {
            es.main.$el.append($.Mustache.render("tpl-doubt-dialog"));
        }
        es.main.$el.append($.Mustache.render("tpl-check-doubt-dialog"));
        
        var me = this;
        $.Mustache.load("asset/tpl/form/form70.html").done(function() {
            me.$el.mustache("tpl-form70", {
                data: data.data,
                disabled: es.main.editable ? "" : "disabled:true",
                save: es.main.editable ? [1] : []
            });
            me.initCtrl(data.data);
        });
    },
    
    initCtrl: function(data) {
        //赋值
        esui.init(document.body, {
            Birthday: {
                range: BIRTHDAY_RANGE,
                value: data.birthday
            },
            Nation: {
                datasource: NATION,
                value: data.ethic
            },
            ADRDate: {
                range: CRF_RANGE,
                value: data.adrDate
            },
            DeathDate: {
                range: CRF_RANGE,
                value: data.deathDate
            },
            ReportDate: {
                range: CRF_RANGE,
                value: data.reportDate
            }
        });
        
        var editable = es.main.editable;
        
        var table1 = esui.get("Drug1"),
            table2 = esui.get("Drug2");;
        table1.datasource = data.drug1;
        table2.datasource = data.drug2;
        table1.fields = table2.fields = [
            {
                field: "f1",
                title: "批准文号",
                editable: editable,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f1;}
            },
            {
                field: "f2",
                title: "商品名称",
                editable: editable,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f2;}
            },
            {
                field: "f3",
                title: "通用名称<br>（含剂型）",
                editable: editable,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f3;}
            },
            {
                field: "f4",
                title: "生产厂家",
                editable: editable,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f4;}
            },
            {
                field: "f5",
                title: "生产批号",
                editable: editable,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f5;}
            },
            {
                field: "f6",
                title: "用法用量<br>（次剂量、途径、日次数）",
                editable: editable,
                edittype: "string",
                width: 170,
                stable: true,
                content: function(item) {return item.f6;}
            },
            {
                field: "f7",
                title: "用药<br>起止时间",
                editable: editable,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f7;}
            },
            {
                field: "f8",
                title: "用药原因",
                editable: editable,
                edittype: "string",
                width: 75,
                stable: true,
                content: function(item) {return item.f8;}
            }
        ];
        table1.render();
        table2.render();
        
        switch (data.type) {
            case 2: esui.get("Type2").setChecked(true); break;
            case 3: esui.get("Type3").setChecked(true); break;
            case 4: esui.get("Type4").setChecked(true);
        }
        if (data.blood == 2) {
            esui.get("Blood2").setChecked(true);
        }
        if (data.sex == 2) {
            esui.get("Female").setChecked(true);
        }
        switch (data.historyadr) {
            case 1: esui.get("HisADR1").setChecked(true); break;
            case 3: esui.get("HisADR3").setChecked(true);
        }
        switch (data.familyadr) {
            case 1: esui.get("FamilyADR1").setChecked(true); this.$(".relationship").show(); break;
            case 3: esui.get("FamilyADR3").setChecked(true);
        }
        switch (data.relationship) {
            case 2: esui.get("Relationship2").setChecked(true); break;
            case 3: esui.get("Relationship3").setChecked(true); break;
            case 4: esui.get("Relationship4").setChecked(true);
        }
        if (data.info != "") {
            $.each(data.info.split(","), function(index, val) {
                esui.get("Info" + val).setChecked(true);
            });
        }
        if (data.adr != "") {
            $.each(data.adr.split(","), function(index, val) {
                esui.get("ADR" + val).setChecked(true);
            });
        }
        if (data.adr1 != "") {
            $.each(data.adr1.split(","), function(index, val) {
                esui.get("ADR1_" + val).setChecked(true);
            });
        }
        if (data.adr2 != "") {
            $.each(data.adr2.split(","), function(index, val) {
                esui.get("ADR2_" + val).setChecked(true);
            });
        }
        if (data.adr3 != "") {
            $.each(data.adr3.split(","), function(index, val) {
                esui.get("ADR3_" + val).setChecked(true);
            });
        }
        if (data.adr4 != "") {
            $.each(data.adr4.split(","), function(index, val) {
                esui.get("ADR4_" + val).setChecked(true);
            });
        }
        if (data.adr5 != "") {
            $.each(data.adr5.split(","), function(index, val) {
                esui.get("ADR5_" + val).setChecked(true);
            });
        }
        if (data.adr6 != "") {
            $.each(data.adr6.split(","), function(index, val) {
                esui.get("ADR6_" + val).setChecked(true);
            });
        }
        if (data.adr7 != "") {
            $.each(data.adr7.split(","), function(index, val) {
                esui.get("ADR7_" + val).setChecked(true);
            });
        }
        if (data.adr8 != "") {
            $.each(data.adr8.split(","), function(index, val) {
                esui.get("ADR8_" + val).setChecked(true);
            });
        }
        if (data.adr9 != "") {
            $.each(data.adr9.split(","), function(index, val) {
                esui.get("ADR9_" + val).setChecked(true);
            });
        }
        if (data.adr10 != "") {
            $.each(data.adr10.split(","), function(index, val) {
                esui.get("ADR10_" + val).setChecked(true);
            });
        }
        switch (data.adrDeal) {
            case 2: esui.get("Deal2").setChecked(true); break;
            case 3: esui.get("Deal3").setChecked(true); break;
            case 4: esui.get("Deal4").setChecked(true);
        }
        switch (data.adrDeal3) {
            case 2: esui.get("Deal3_2").setChecked(true); break;
            case 3: esui.get("Deal3_3").setChecked(true); break;
            case 4: esui.get("Deal3_4").setChecked(true); break;
            case 5: esui.get("Deal3_5").setChecked(true);
        }
        switch (data.ending) {
            case 2: esui.get("Ending2").setChecked(true); break;
            case 3: esui.get("Ending3").setChecked(true); break;
            case 4: esui.get("Ending4").setChecked(true); break;
            case 5: esui.get("Ending5").setChecked(true); break;
            case 6: esui.get("Ending6").setChecked(true); this.$(".death").show();
        }
        switch (data.adrStop) {
            case 2: esui.get("Stop2").setChecked(true); break;
            case 3: esui.get("Stop3").setChecked(true); break;
            case 4: esui.get("Stop4").setChecked(true);
        }
        switch (data.adrRestart) {
            case 2: esui.get("Restart2").setChecked(true); break;
            case 3: esui.get("Restart3").setChecked(true); break;
            case 4: esui.get("Restart4").setChecked(true);
        }
        switch (data.evaluate) {
            case 2: esui.get("Evaluate2").setChecked(true); break;
            case 3: esui.get("Evaluate3").setChecked(true); break;
            case 4: esui.get("Evaluate4").setChecked(true); break;
            case 5: esui.get("Evaluate5").setChecked(true); break;
            case 6: esui.get("Evaluate6").setChecked(true);
        }
        switch (data.career) {
            case 2: esui.get("Career2").setChecked(true); break;
            case 3: esui.get("Career3").setChecked(true); break;
            case 4: esui.get("Career4").setChecked(true);
        }
        
        //事件
        var me = this;
        
        //自动提示
        $("body").delegate(".ui-table-editor input", "focus", function(e) {
            var index = esui.get(esui.Table.Editor.edittingTable).activeColumn;
            if (index == 2) {
                $(e.target).autocomplete({source: util.getSuggestion("drug")});
            } else {
                $(e.target).autocomplete({source: []});
            }
        });
        
        esui.get("Drug1").onedit = function (value, options, editor) {
            var txt = $.trim(value);
            if (txt == "") {
                esui.Dialog.alert({title: "提示", content: "请填写内容"});
                return false;
            }
            
            this.datasource[options.rowIndex][options.field.field] = txt;
            this.setCellText(txt, options.rowIndex, options.columnIndex);
            editor.stop();
        };
        esui.get("Drug2").onedit = function (value, options, editor) {
            var txt = $.trim(value);
            if (txt == "") {
                esui.Dialog.alert({title: "提示", content: "请填写内容"});
                return false;
            }
            
            this.datasource[options.rowIndex][options.field.field] = txt;
            this.setCellText(txt, options.rowIndex, options.columnIndex);
            editor.stop();
        };
        
        esui.get("Birthday").onchange = function(value) {esui.get("Birthday").setValueAsDate(value);};
        esui.get("ADRDate").onchange = function(value) {esui.get("ADRDate").setValueAsDate(value);};
        esui.get("DeathDate").onchange = function(value) {esui.get("DeathDate").setValueAsDate(value);};
        esui.get("ReportDate").onchange = function(value) {esui.get("ReportDate").setValueAsDate(value);};
        
        esui.get("FamilyADR1").onclick = function() {me.$(".relationship").show();};
        esui.get("FamilyADR2").onclick = function() {me.$(".relationship").hide();};
        esui.get("FamilyADR3").onclick = function() {me.$(".relationship").hide();};
        
        esui.get("Ending1").onclick = function() {me.$(".death").hide();};
        esui.get("Ending2").onclick = function() {me.$(".death").hide();};
        esui.get("Ending3").onclick = function() {me.$(".death").hide();};
        esui.get("Ending4").onclick = function() {me.$(".death").hide();};
        esui.get("Ending5").onclick = function() {me.$(".death").hide();};
        esui.get("Ending6").onclick = function() {me.$(".death").show();};
        
        if (es.main.canDoubt) {
            esui.get("DoubtOK").onclick = es.main.doubtCRF;
        }
        if (es.main.editable) {
            esui.get("Save").onclick = this.save;
        }
        if (!es.main.editable) {
            esui.get("Birthday").disable();
            esui.get("ADRDate").disable();
            esui.get("DeathDate").disable();
            esui.get("ReportDate").disable();
        }
    },
    
    addRow1: function() {
        var table = esui.get("Drug1");
        table.datasource.push({f1:"",f2:"",f3:"",f4:"",f5:"",f6:"",f7:"",f8:""});
        table.render();
    },
    
    addRow2: function() {
        var table = esui.get("Drug2");
        table.datasource.push({f1:"",f2:"",f3:"",f4:"",f5:"",f6:"",f7:"",f8:""});
        table.render();
    },
    
    save: function() {
       var me = es.main;
       
       var data = {
           id: me.crfId,
           no: me.model.get("data").no,
           type: parseInt(esui.get("Type1").getGroup().getValue(), 10),
           blood: parseInt(esui.get("Blood1").getGroup().getValue(), 10),
           name: $.trim(esui.get("Name").getValue()),
           sex: parseInt(esui.get("Male").getGroup().getValue(), 10),
           birthday: esui.get("Birthday").getValue(),
           ethic: esui.get("Nation").value,
           weight: $.trim(esui.get("Weight").getValue()),
           contact: $.trim(esui.get("Contact").getValue()),
           disease: $.trim(esui.get("Disease").getValue()),
           patientNo: $.trim(esui.get("PatientNo").getValue()),
           historyadr: parseInt(esui.get("HisADR1").getGroup().getValue(), 10),
           historyadrtxt: $.trim(esui.get("HisADR").getValue()),
           familyadr: parseInt(esui.get("FamilyADR1").getGroup().getValue(), 10),
           familyadrtxt: $.trim(esui.get("FamilyADR").getValue()),
           relationship: parseInt(esui.get("Relationship1").getGroup().getValue(), 10),
           info: esui.get("Info1").getGroup().getValue(),
           info6txt: $.trim(esui.get("Info6Name").getValue()),
           info7txt: $.trim(esui.get("Info7Name").getValue()),
           drug1: esui.get("Drug1").datasource,
           drug2: esui.get("Drug2").datasource,
           adr: esui.get("ADR1").getGroup().getValue(),
           adr1: esui.get("ADR1_1").getGroup().getValue(),
           adr2: esui.get("ADR2_1").getGroup().getValue(),
           adr3: esui.get("ADR3_1").getGroup().getValue(),
           adr4: esui.get("ADR4_1").getGroup().getValue(),
           adr5: esui.get("ADR5_1").getGroup().getValue(),
           adr6: esui.get("ADR6_1").getGroup().getValue(),
           adr7: esui.get("ADR7_1").getGroup().getValue(),
           adr8: esui.get("ADR8_1").getGroup().getValue(),
           adr9: esui.get("ADR9_1").getGroup().getValue(),
           adr10: esui.get("ADR10_1").getGroup().getValue(),
           adrtxt: $.trim(esui.get("CustomADRName").getValue()),
           adrDate: esui.get("ADRDate").getValue(),
           adrH: $.trim(esui.get("ADRH").getValue()),
           adrM: $.trim(esui.get("ADRM").getValue()),
           adrDescription: $.trim(esui.get("Description").getValue()),
           adrDeal: parseInt(esui.get("Deal1").getGroup().getValue(), 10),
           adrDeal3: parseInt(esui.get("Deal3_1").getGroup().getValue(), 10),
           adrDealDose: $.trim(esui.get("Deal2Dose").getValue()),
           adrDeal3txt: $.trim(esui.get("CustomDeal3Name").getValue()),
           adrDeal4txt: $.trim(esui.get("CustomDealName").getValue()),
           adrDealRemark: $.trim(esui.get("DealRemark").getValue()),
           ending: parseInt(esui.get("Ending1").getGroup().getValue(), 10),
           endingtxt: $.trim(esui.get("Ending5Content").getValue()),
           deathDate: esui.get("DeathDate").getValue(),
           deathReason: $.trim(esui.get("DeathReason").getValue()),
           adrStop: parseInt(esui.get("Stop1").getGroup().getValue(), 10),
           adrRestart: parseInt(esui.get("Restart1").getGroup().getValue(), 10),
           evaluate: parseInt(esui.get("Evaluate1").getGroup().getValue(), 10),
           career: parseInt(esui.get("Career1").getGroup().getValue(), 10),
           careertxt: $.trim(esui.get("CustomCareerName").getValue()),
           email: $.trim(esui.get("Email").getValue()),
           reportDate: esui.get("ReportDate").getValue(),
           remark: $.trim(esui.get("Remark").getValue())
       };
       
       //验证
       var floatPattern = /^\d+(\.\d+)?$/,
           intPattern = /^\d+$/;
       if (data.name == "") {
           esui.Dialog.alert({title: "提示", content: "请填写患者姓名"});
           return;
       }
       if (data.ethic == 0) {
           esui.Dialog.alert({title: "提示", content: "请选择民族"});
           return;
       }
       if (!floatPattern.test(data.weight)) {
           esui.Dialog.alert({title: "提示", content: "请填写体重"});
           return;
       }
       if (parseFloat(data.weight) > 150) {
           esui.Dialog.alert({title: "提示", content: "体重范围不正确"});
           return;
       }
       if (data.contact == "") {
           esui.Dialog.alert({title: "提示", content: "请填写联系方式"});
           return;
       }
       if (data.disease == "") {
           esui.Dialog.alert({title: "提示", content: "请填写原患疾病"});
           return;
       }
       if (data.patientNo == "") {
           esui.Dialog.alert({title: "提示", content: "请填写病历号/门诊号"});
           return;
       }
       if (data.historyadr == 1 && data.historyadrtxt == "") {
           esui.Dialog.alert({title: "提示", content: "请填写既往药品不良反应/事件"});
           return;
       }
       if (data.familyadr == 1 && data.familyadrtxt == "") {
           esui.Dialog.alert({title: "提示", content: "请填写家族药品不良反应/事件"});
           return;
       }
       if (data.info == "") {
           esui.Dialog.alert({title: "提示", content: "请选择相关重要信息"});
           return;
       }
       if (data.info.indexOf("6") != -1 && data.info6txt == "") {
           esui.Dialog.alert({title: "提示", content: "请填写过敏史"});
           return;
       }
       if (data.info.indexOf("7") != -1 && data.info7txt == "") {
           esui.Dialog.alert({title: "提示", content: "请填写其他相关重要信息"});
           return;
       }
       if (data.adr == "") {
           esui.Dialog.alert({title: "提示", content: "请选择不良反应/事件名称"});
           return;
       }
       if (data.adr.indexOf("11") != -1 && data.adrtxt == "") {
           esui.Dialog.alert({title: "提示", content: "请填写其他不良反应/事件名称"});
           return;
       }
       if (!intPattern.test(data.adrH) || parseInt(data.adrH) > 24
        || !intPattern.test(data.adrM) || parseInt(data.adrM) > 60) {
           esui.Dialog.alert({title: "提示", content: "请填写不良反应/事件发生时间"});
           return;
       }
       if (data.adrDescription == "") {
           esui.Dialog.alert({title: "提示", content: "请填写不良反应/事件过程描述"});
           return;
       }
       if (data.adrDeal == 2 && !intPattern.test(data.adrDealDose)) {
           esui.Dialog.alert({title: "提示", content: "请填写减少剂量"});
           return;
       }
       if (data.adrDeal == 3 && data.adrDeal3 == 5 && data.adrDeal3txt == "") {
           esui.Dialog.alert({title: "提示", content: "请填写其他对症支持治疗"});
           return;
       }
       if (data.adrDeal == 4 && data.adrDeal4txt == "") {
           esui.Dialog.alert({title: "提示", content: "请填写其他不良反应/事件处理情况"});
           return;
       }
       if (data.ending == 5 && data.endingtxt == "") {
           esui.Dialog.alert({title: "提示", content: "请填写后遗症表现"});
           return;
       }
       if (data.ending == 6 && data.deathReason == "") {
           esui.Dialog.alert({title: "提示", content: "请填写直接死因"});
           return;
       }
       if (data.career == 4 && data.careertxt == "") {
           esui.Dialog.alert({title: "提示", content: "请填写其他职业"});
           return;
       }
       if (data.email == "") {
           esui.Dialog.alert({title: "提示", content: "请填写电子邮箱"});
           return;
       }
       
       console.log("crf/saveADR.do-请求", data);
       
       util.ajax.run({
            url: "crf/saveADR.do",
            data: JSON.stringify(data),
            json: true,
            success: function(response) {
                console.log("crf/saveADR.do-响应:", response);
                
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
