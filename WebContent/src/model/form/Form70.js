/*
 * ADR
 * 
 * @author: Ricky
 */

es.Models.Form70 = Backbone.Model.extend({
    defaults: {
        data: null,
        def: {
            type: null,
            blood: null,
            name: null,
            sex: null,
            birthday: "2014-01-01",
            ethic: 0,
            weight: null,
            contact: null,
            disease: null,
            patientNo: null,
            historyadr: null,
            historyadrtxt: null,
            familyadr: null,
            familyadrtxt: null,
            relationship: null,
            info: "",
            info6txt: null,
            info7txt: null,
            drug1: [],
            drug2: [],
            adr: "",
            adr1: "",adr2: "",adr3: "",adr4: "",adr5: "",
            adr6: "",adr7: "",adr8: "",adr9: "",adr10: "",
            adrtxt: null,
            adrDate: "2014-01-01",
            adrH: null,
            adrM: null,
            adrDescription: null,
            adrDeal: null,
            adrDeal3: null,
            adrDealDose: null,
            adrDeal3txt: null,
            adrDeal4txt: null,
            adrDealRemark: null,
            ending: null,
            endingtxt: null,
            deathDate: "2014-01-01",
            deathReason: null,
            adrStop: null,
            adrRestart: null,
            evaluate: null,
            career: null,
            careertxt: null,
            email: null,
            reportDate: "2014-01-01",
            remark: null
        }
    },
    
    getData: function(args) {
        var me = this;
        me.set({data : null}, {silent : true});
        
        console.log("crf/getADR.do-请求", args);
        
        util.ajax.run({
            url: "crf/getADR.do",
            data: args,
            success: function(response) {
                console.log("crf/getADR.do-响应", response);
                if (response.data == null) {
                    response.data = me.get("def");
                }
                $.each(response.data.drug1, function(index, val) {
                    val.no = index + 1;
                });
                $.each(response.data.drug2, function(index, val) {
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
