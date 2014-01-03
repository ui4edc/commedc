/*
 * 数据管理
 * 
 * @author: Ricky
 */

es.Models.Data = Backbone.Model.extend({
    defaults: {
        data: null
    },
    
    getData: function(args) {
        var me = this;
        me.set({data : null}, {silent : true});
        
        util.ajax.run({
            url: "",
            data: args,
            success: function(response) {
                me.set({data: response});
            },
            mock: true,
            mockData: {
                success: true,
                errorMsg: "errorMsg",
                total: 100,
                data: [
                    {
                        id: 1,
                        no: "333-0125",
                        abbr: "XM",
                        createDate: "2014-01-01 00:00:00",
                        lastModified : "2014-01-02 00:00:00",
                        progress: "30%",
                        doubter: "doubter",
                        doubtField: "身高",
                        description: "非人类",
                        doubtDate: "2014-01-03 00:00:00"
                    },
                    {
                        id: 2,
                        no: "333-0126",
                        abbr: "XH",
                        createDate: "2014-01-01 00:00:00",
                        lastModified : "2014-01-02 00:00:00",
                        progress: "30%",
                        doubter: "doubter",
                        doubtField: "身高",
                        description: "非人类",
                        doubtDate: "2014-01-03 00:00:00"
                    }
                ]
            }
        });
    }
});
