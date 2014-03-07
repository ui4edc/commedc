/*
 * 用药小结
 * 
 * @author: Ricky
 */

es.Models.Form60 = Backbone.Model.extend({
    defaults: {
        data: null,
        def: {
            startDate: "2014-01-01",
            endDate: "2014-01-01",
            ending: null,
            deathDate: "2014-01-01",
            deathReason: null,
            adr: null,
            unreasonable: null,
            intervention: null,
            interventiontxt: null,
            treatmentCost: null,
            drugCost: null,
            trqCost: null,
            remark: null
        }
    },
    
    getData: function(args) {
        var me = this;
        me.set({data : null}, {silent : true});
        
        console.log("crf/getDrugSummary.do-请求", args);
        
        util.ajax.run({
            url: "crf/getDrugSummary.do",
            data: args,
            success: function(response) {
                console.log("crf/getDrugSummary.do-响应", response);
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
