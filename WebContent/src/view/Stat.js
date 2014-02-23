/*
 * 全局统计
 * 
 * @author: Ricky
 */

es.Views.Stat = Backbone.View.extend({
    el: "#Main",
    
    events: {
        
    },
    
    initialize: function() {
        this.model.bind("change:data", this.renderChart, this);
    },
    
    destroy: function() {
        this.destroyChart();
        this.menu && this.menu.destroy();
        this.model.unbind();
        this.$el.unbind();
        this.$el.empty();
    },
    
    destroyChart: function() {
        var chart = this.$(".chart").highcharts();
        chart && chart.destroy();
    },
    
    renderNav: function() {
        es.widgets.nav.setActive("stat");
    },
    
    render: function(query) {
        this.renderNav();
        
        var me = this;
        $.Mustache.load("asset/tpl/stat.html").done(function() {
            me.$el.mustache("tpl-stat");
            
            //创建菜单
            me.menu = new Tree({
                container: ".sidebar",
                data: STAT_MENU,
                onClick: me.onMenuClick,
                defaultId: 1
            });
        });
    },
    
    onMenuClick: function(id) {
        var me = es.main;
        me.statType = id;
        me.model.getData(id);
    },
    
    /*
     * 渲染Chart
     */
    renderChart: function(model, data) {
        this.destroyChart();
        
        switch (this.statType) {
            case 1: this.renderValueChart(data); break;
            case 2: this.renderAgeChart(data); break;
            case 3: this.renderSexChart(data);
        }
    },
    
    renderValueChart: function(data) {
        this.$(".chart").highcharts({
            chart: {
                type: 'bar'
            },
            title: {
                text: 'Stacked bar chart'
            },
            xAxis: {
                categories: ['Apples', 'Oranges', 'Pears', 'Grapes', 'Bananas']
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Total fruit consumption'
                }
            },
            legend: {
                backgroundColor: '#FFFFFF',
                reversed: true
            },
            plotOptions: {
                series: {
                    stacking: 'normal'
                }
            },
            series: [{
                name: 'John',
                data: [5, 3, 4, 7, 2]
            }, {
                name: 'Jane',
                data: [2, 2, 3, 2, 1]
            }, {
                name: 'Joe',
                data: [3, 4, 4, 2, 5]
            }]
        });
    },
    
    renderAgeChart: function(data) {
        this.renderPieChart(data, "年龄统计");
    },
    
    renderSexChart: function(data) {
        this.renderPieChart(data, "性别统计");
    },
    
    renderPieChart: function(data, title) {
        this.$(".chart").highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: title
            },
            tooltip: {
                pointFormat: '表单数: <b>{point.y}</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                    },
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                data: data
            }]
        });
    }
});
