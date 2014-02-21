/*
 * 病症情况
 * 
 * @author: Ricky
 */

es.Models.Form20 = Backbone.Model.extend({
    defaults: {
        data: null,
        def: {
			disease1: "",
			fy: 1,
			disease2: "",
			disease3: "",
			diseasetxt: null,
			diagnosis: null,
			zy: 7,
			zytxt: null
		}
    },
    
    getData: function(args) {
        var me = this;
        me.set({data : null}, {silent : true});
        
        console.log("crf/getDeseaseInfo.do-请求", args);
        
        util.ajax.run({
            url: "crf/getDeseaseInfo.do",
            data: args,
            success: function(response) {
                console.log("crf/getDeseaseInfo.do-响应", response);
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
