/*
 * 用药情况
 * 
 * @author: Ricky
 */

es.Models.Form30 = Backbone.Model.extend({
    defaults: {
        data: null,
        def: {
			history: 2,
			adr: 2,
			adrtxt: null,
			batchNumber: null,
			dose: null,
			startDate: "2014-01-01",
			startH: null,
			startM: null,
			endDate: "2014-01-01",
			endH: null,
			endM: null,
			solventDose: null,
			solvent: 1,
			solventName: null,
			solventPercent: null,
			prepareTime: null,
			prepareTimeUd: false,
			location: 1,
			way: 1,
			way1Speed: null,
			way1Time: null,
			way2Speed: null,
			way3Name: null,
			way3Speed: null,
			way3Unit: null,
			
			sameBottle: 2,
			bottle: [
				{name: null, dose: null, unit: null}
			],
			
			sameGroup: 2,
			gpSolvent: 1,
			gpSolvent1Dose: null,
			gSolvent2Dose: null,
			gpSolvent3Name: null,
			gpSolvent3Percent: null,
			gpSolvent3Dose: null,
			
			hasInjection: 2,
			injection: [
				{name: null, dose: null, unit: null}
			],
			
			hasBan: 2,
			ban: 1,
			banColor: null,
			bantxt: null,
			banDrug: [
				{name: null, dose: null, unit: null}
			],
			
			hasFood: 2,
			food: "",
			foodtxt: null
		}
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
                if (response.data == null) {
                    response.data = me.get("def");
                }
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
                data: null
            }
        });
    }
});
