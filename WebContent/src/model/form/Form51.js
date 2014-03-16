/*
 * 实验检查-入院检查
 * 
 * @author: Ricky
 */

es.Models.Form51 = Backbone.Model.extend({
    defaults: {
        data: null,
        def: {
			done: null,
			examDate: T.date.format(new Date(), "yyyy-MM-dd"),
			temperature: null,
			breathe: null,
			ssy: null,
			szy: null,
			rate: null
		}
    },
    
    getData: function(args) {
        var me = this;
        me.set({data : null}, {silent : true});
        
        console.log("crf/getBodyExam.do-请求", args);
        
        util.ajax.run({
            url: "crf/getBodyExam.do",
            data: args,
            success: function(response) {
                console.log("crf/getBodyExam.do-响应", response);
                if (response.data == null) {
                    response.data = me.get("def");
                    me.first = true;
                } else {
                    me.first = false;
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
