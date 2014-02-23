/*
 * 全局统计
 * 
 * @author: Ricky
 */

es.Models.Stat = Backbone.Model.extend({
    defaults: {
        data: null
    },
    
    getData: function(id) {
        var me = this;
        me.set({data : null}, {silent : true});
        
        var method, mockData, formater;
        switch (id) {
            case 1:
                method = "";
                formater = me.formatValue;
                mockData = [];
                break;
            case 2:
                method = "stat/getAgeStat.do";
                formater = me.formatAge;
                mockData = [
                    {name: "60", number: 100},
                    {name: "70", number: 300}
                ];
                break;
            case 3:
                method = "stat/getSexStat.do";
                formater = me.formatSex;
                mockData = [
                    {name: "男", number: 400},
                    {name: "女", number: 600}
                ];
        }
        
        util.ajax.run({
            url: method,
            data: {},
            success: function(response) {
                console.log(method, response);
                me.set({data: formater.call(me, response.data)});
            },
            mock: MOCK,
            mockData: {
                success: true,
                total: 100,
                data: mockData
            }
        });
    },
    
    formatValue: function(data) {
        var charData = [];
        
        return charData;
    },
    
    formatAge: function(data) {
        return this.formatPie(data);
    },
    
    formatSex: function(data) {
        return this.formatPie(data);
    },
    
    formatPie: function(data) {
        var pieData = [];
        $.each(data, function(index, val) {
            pieData.push([val.name, val.number]);
        });
        return pieData;
    }
});
