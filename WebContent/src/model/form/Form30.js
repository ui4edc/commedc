/*
 * 用药情况
 * 
 * @author: Ricky
 */

es.Models.Form30 = Backbone.Model.extend({
    defaults: {
        data: null
    },
    
    getData: function(args) {
        var me = this;
        me.set({data : null}, {silent : true});
        
        console.log("获取表单-请求", args);
        
        util.ajax.run({
            url: "",
            data: args,
            success: function(response) {
                console.log("获取表单-响应", response);
                
                $.each(response.data.bottle, function(index, val) {
                    val.no = index + 1;
                });
                $.each(response.data.injection, function(index, val) {
                    val.no = index + 1;
                });
                $.each(response.data.banDrug, function(index, val) {
                    val.no = index + 1;
                });
                
                me.set({data: response});
            },
            mock: MOCK,
            mockData: {
                success: true,
                data: {
                    history: 1,
                    adr: 1,
                    adrtxt: "",
                    batchNumber: "123456",
                    dose: 10,
                    startDate: "2014-01-01",
                    startH: 12,
                    startM: 0,
                    endDate: "2014-01-01",
                    endH: 12,
                    endM: 30,
                    solventDose: 100,
                    solvent: 1,
                    solventName: null,
                    solventPercent: null,
                    prepareTime: 5,
                    prepareTimeUd: false,
                    location: 1,
                    way: 1,
                    way1Speed: null,
                    way1Time: null,
                    way2Speed: null,
                    way3Name: null,
                    way3Speed: null,
                    way3Unit: null,
                    
                    sameBottle: 1,
                    bottle: [
                        {name: "", dose: "", unit: ""}
                    ],
                    
                    sameGroup: 1,
                    gpSolvent: 1,
                    gpSolvent1Dose: null,
                    gSolvent2Dose: null,
                    gpSolvent3Name: null,
                    gpSolvent3Percent: null,
                    gpSolvent3Dose: null,
                    
                    hasInjection: 1,
                    injection: [
                        {name: "", dose: "", unit: ""},
                        {name: "", dose: "", unit: ""}
                    ],
                    
                    hasBan: 1,
                    ban: 1,
                    banColor: "",
                    bantxt: "",
                    banDrug: [
                        {name: "", dose: "", unit: ""},
                        {name: "", dose: "", unit: ""}
                    ],
                    
                    hasFood: 1,
                    food: "",
                    foodtxt: ""
                }
            }
        });
    }
});
