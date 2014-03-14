/*
 * 实验检查-出院检查
 * 
 * @author: Ricky
 */

es.Models.Form53 = Backbone.Model.extend({
    defaults: {
        data: null,
        def: {
			done: null,
			examDate: T.date.format(new Date(), "yyyy-MM-dd"),
			normal: null,
			description: null
		}
    },
    
    getData: function(args) {
        var me = this;
        me.set({data : null}, {silent : true});
        
        console.log("get心电图-请求", args);
        
        util.ajax.run({
            url: "",
            data: args,
            success: function(response) {
                console.log("get心电图-响应", response);
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
