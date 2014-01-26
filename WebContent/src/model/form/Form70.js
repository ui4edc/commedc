/*
 * ADR
 * 
 * @author: Ricky
 */

es.Models.Form70 = Backbone.Model.extend({
    defaults: {
        data: null,
        def: {
            type: 1,
            blood: 1,
            name: null,
            sex: 1,
            birthday: "2014-01-01",
            ethic: 1,
            weight: null,
            contact: null,
            disease: null,
            patientNo: null,
            historyadr: 2,
            historyadrtxt: null,
            familyadr: 2,
            familyadrtxt: null,
            relationship: 1,
            info: "",
            info6txt: null,
            info7txt: null,
            drug1: [
                {f1:"",f2:"",f3:"",f4:"",f5:"",f6:"",f7:"",f8:""}
            ],
            drug2: [
                {f1:"",f2:"",f3:"",f4:"",f5:"",f6:"",f7:"",f8:""}
            ],
            adr: "",
            adr1: "",adr2: "",adr3: "",adr4: "",adr5: "",
            adr6: "",adr7: "",adr8: "",adr9: "",adr10: "",
            adrtxt: null,
            adrDate: "2014-01-01",
            adrH: null,
            adrM: null,
            adrDescription: null,
            adrDeal: 1,
            adrDeal3: 1,
            adrDealDose: null,
            adrDeal3txt: null,
            adrDeal4txt: null,
            adrDealRemark: null,
            ending: 1,
            endingtxt: null,
            deathDate: "2014-01-01",
            deathReason: null,
            adrStop: 1,
            adrRestart: 1,
            evaluate: 1,
            career: 1,
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
