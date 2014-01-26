/*
 * 实验检查-出院检查
 * 
 * @author: Ricky
 */

es.Models.Form53 = Backbone.Model.extend({
    defaults: {
        data: null,
        def: {
			done: 2,
			data1: [{f1:"",f2:"",f3:"",f4:"",f5:"",f6:""}],
			data2: [{f1:"",f2:"",f3:""}],
			data3: [{f1:"",f2:"",f3:"",f4:"",f5:"",f6:"",f7:""}],
			data4: [{f1:"",f2:"",f3:"",f4:"",f5:"",f6:""}],
			data5: [{f1:"",f2:"",f3:""}],
			data6: [{f1:"",f2:"",f3:""}]
		}
    },
    
    getData: function(args) {
        var me = this;
        me.set({data : null}, {silent : true});
        
        console.log("crf/getOutHospitalExam.do-请求", args);
        
        util.ajax.run({
            url: "crf/getOutHospitalExam.do",
            data: args,
            success: function(response) {
                console.log("crf/getOutHospitalExam.do-响应", response);
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
