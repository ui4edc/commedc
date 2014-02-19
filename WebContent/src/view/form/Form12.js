/*
 * 患者信息-个人史 + 过敏史
 * 
 * @author: Ricky
 */

es.Views.Form12 = Backbone.View.extend({
    el: ".crf-form",
    
    events: {
        "click .add-food": "addFood",
        "click .add-drug": "addDrug",
        "click .add-material": "addMaterial"
    },
    
    initialize: function() {
        this.model.bind("change:data", this.renderForm, this);
        this.model.getData({id: es.main.crfId});
    },
    
    destroy: function() {
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
        $.Mustache.load("asset/tpl/form/form12.html").done(function() {
            var disabled = es.main.editable ? "" : "disabled:true";
            me.$el.mustache("tpl-form12", {
                data: data.data,
                disabled: disabled,
                save: es.main.editable ? [1] : []
            });
            me.$(".food").prepend($.Mustache.render("tpl-form12-food", {
                food: data.data.food,
                disabled: disabled
            }));
            me.$(".drug").prepend($.Mustache.render("tpl-form12-drug", {
                drug: data.data.drug,
                disabled: disabled
            }));
            me.$(".material").prepend($.Mustache.render("tpl-form12-material", {
                other: data.data.other,
                disabled: disabled
            }));
            
            me.initCtrl(data.data);
        });
    },
    
    initCtrl: function(data) {
        esui.init();
        
        //赋值
        switch (data.smoke) {
            case 1: esui.get("Smoke1").setChecked(true); break;
            case 3: esui.get("Smoke3").setChecked(true);
        }
        switch (data.drink) {
            case 1: esui.get("Drink1").setChecked(true); break;
            case 3: esui.get("Drink3").setChecked(true);
        }
        switch (data.hasFood) {
            case 1: esui.get("Food1").setChecked(true); this.$(".food").show(); break;
            case 3: esui.get("Food3").setChecked(true);
        }
        switch (data.hasDrug) {
            case 1: esui.get("Drug1").setChecked(true); this.$(".drug").show(); break;
            case 3: esui.get("Drug3").setChecked(true);
        }
        switch (data.hasOther) {
            case 1: esui.get("Material1").setChecked(true); this.$(".material").show(); break;
            case 3: esui.get("Material3").setChecked(true);
        }
        $.each(data.food, function(index, val) {
            if (val.value != "") {
                $.each(val.value.split(","), function(index2, val2) {
                    esui.get("FoodAllergy" + (index + 1) + "_" + val2).setChecked(true);
                });
            }
        });
        $.each(data.drug, function(index, val) {
            if (val.value != "") {
                $.each(val.value.split(","), function(index2, val2) {
                    esui.get("DrugAllergy" + (index + 1) + "_" + val2).setChecked(true);
                });
            }
            
            var type = esui.get("DrugType" + val.no);
            type.datasource = DRUG_TYPE.datasource;
            type.render();
            type.setValue(val.type);
        });
        $.each(data.other, function(index, val) {
            if (val.value != "") {
                $.each(val.value.split(","), function(index2, val2) {
                    esui.get("MaterialAllergy" + (index + 1) + "_" + val2).setChecked(true);
                });
            }
        });
        
        //事件
        var me = this;
        
        esui.get("Food1").onclick = function() {me.$(".food").show();};
        esui.get("Food2").onclick = function() {me.$(".food").hide();};
        esui.get("Food3").onclick = function() {me.$(".food").hide();};
        esui.get("Drug1").onclick = function() {me.$(".drug").show();};
        esui.get("Drug2").onclick = function() {me.$(".drug").hide();};
        esui.get("Drug3").onclick = function() {me.$(".drug").hide();};
        esui.get("Material1").onclick = function() {me.$(".material").show();};
        esui.get("Material2").onclick = function() {me.$(".material").hide();};
        esui.get("Material3").onclick = function() {me.$(".material").hide();};
        
        if (es.main.canDoubt) {
            esui.get("DoubtOK").onclick = es.main.doubtCRF;
        }
        if (es.main.editable) {
            esui.get("Save").onclick = this.save;
        }
    },
    
    addFood: function(e) {
        $(e.target).parent().before($.Mustache.render("tpl-form12-food", {
            food: [{no: this.$(".food-block").length + 1}],
            disabled: ""
        }));
        esui.init();
    },
    
    addDrug: function(e) {
        var no =  this.$(".drug-block").length + 1;
        $(e.target).parent().before($.Mustache.render("tpl-form12-drug", {
            drug: [{no: no}],
            disabled: ""
        }));
        var option = {};
        option["DrugType" + no] = DRUG_TYPE;
        esui.init(this.el, option);
    },
    
    addMaterial: function(e) {
        $(e.target).parent().before($.Mustache.render("tpl-form12-material", {
            other: [{no: this.$(".material-block").length + 1}],
            disabled: ""
        }));
        esui.init();
    },
    
    save: function() {
       var me = es.main;
       
       var data = {
           id: me.crfId,
           no: me.model.get("data").no,
           smoke: parseInt(esui.get("Smoke1").getGroup().getValue(), 10),
           drink: parseInt(esui.get("Drink1").getGroup().getValue(), 10),
           hasFood: parseInt(esui.get("Food1").getGroup().getValue(), 10),
           hasDrug: parseInt(esui.get("Drug1").getGroup().getValue(), 10),
           hasOther: parseInt(esui.get("Material1").getGroup().getValue(), 10),
           food: [],
           drug: [],
           other: []
       };
       
       if (data.hasFood == 1) {
           var food = me.$(".food-block");
           $.each(food, function(index, val) {
               var item = {},
                   no = index + 1;
               data.food.push({
                   name: $.trim(esui.get("FoodName" + no).getValue()),
                   value: esui.get("FoodAllergy" + no + "_1").getGroup().getValue(),
                   txt: $.trim(esui.get("CustomFoodAllergyName" + no).getValue())
               });
           });
       }
       if (data.hasDrug == 1) {
           var drug = me.$(".drug-block");
           $.each(drug, function(index, val) {
               var item = {},
                   no = index + 1;
               data.drug.push({
                   name: $.trim(esui.get("DrugName" + no).getValue()),
                   value: esui.get("DrugAllergy" + no + "_1").getGroup().getValue(),
                   txt: $.trim(esui.get("CustomDrugAllergyName" + no).getValue()),
                   type: esui.get("DrugType" + no).value
               });
           });
       }
       if (data.hasOther == 1) {
           var material = me.$(".material-block");
           $.each(material, function(index, val) {
               var item = {},
                   no = index + 1;
               data.other.push({
                   name: $.trim(esui.get("MaterialName" + no).getValue()),
                   value: esui.get("MaterialAllergy" + no + "_1").getGroup().getValue(),
                   txt: $.trim(esui.get("CustomMaterialAllergyName" + no).getValue())
               });
           });
       }
       
       //验证
       for (var i = 0, n = data.food.length; i <n; i++) {
           var item = data.food[i], seq = i + 1;
           if (item.name == "") {
               esui.Dialog.alert({title: "提示", content: "请输入第 " + seq + " 个过敏食物名称"});
               return false;
           }
           if (item.value == "") {
               esui.Dialog.alert({title: "提示", content: "请选择第 " + seq + " 个食物过敏表现"});
               return false;
           }
           if (item.value.indexOf("8") != -1 && item.txt == "") {
               esui.Dialog.alert({title: "提示", content: "请输入第 " + seq + " 个其他食物过敏表现"});
               return false;
           }
       }
       for (var i = 0, n = data.drug.length; i <n; i++) {
           var item = data.drug[i], seq = i + 1;
           if (item.name == "") {
               esui.Dialog.alert({title: "提示", content: "请输入第 " + seq + " 个过敏药物名称"});
               return false;
           }
           if (item.value == "") {
               esui.Dialog.alert({title: "提示", content: "请选择第 " + seq + " 个药物过敏表现"});
               return false;
           }
           if (item.value.indexOf("8") != -1 && item.txt == "") {
               esui.Dialog.alert({title: "提示", content: "请输入第 " + seq + " 个其他药物过敏表现"});
               return false;
           }
       }
       for (var i = 0, n = data.other.length; i <n; i++) {
           var item = data.other[i], seq = i + 1;
           if (item.name == "") {
               esui.Dialog.alert({title: "提示", content: "请输入第 " + seq + " 个过敏物质名称"});
               return false;
           }
           if (item.value == "") {
               esui.Dialog.alert({title: "提示", content: "请选择第 " + seq + " 个物质过敏表现"});
               return false;
           }
           if (item.value.indexOf("8") != -1 && item.txt == "") {
               esui.Dialog.alert({title: "提示", content: "请输入第 " + seq + " 个其他物质过敏表现"});
               return false;
           }
       }
       
       console.log("crf/savePersonHistory.do-请求", data);
       
       util.ajax.run({
            url: "crf/savePersonHistory.do",
            data: JSON.stringify(data),
            json: true,
            success: function(response) {
                console.log("crf/savePersonHistory.do-响应:", response);
                
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
