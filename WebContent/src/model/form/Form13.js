/*
 * 患者信息-既往史
 * 
 * @author: Ricky
 */

es.Models.Form13 = Backbone.Model.extend({
    defaults: {
        data: null,
        def: {
			hasDisease: null,
			hasAllergy: null,
			disease: "",
			disease1: "",
			disease2: "",
			diseasetxt: null,
			gxb: 1,
			gxy: 1,
			tnb: 1,
			allergy: "",
			allergytxt: null
		}
    },
    
    getData: function(args) {
        var me = this;
        me.set({data : null}, {silent : true});
        
        console.log("crf/getPastHistory.do-请求", args);
        
        util.ajax.run({
            url: "crf/getPastHistory.do",
            data: args,
            success: function(response) {
                console.log("crf/getPastHistory.do-响应", response);
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
