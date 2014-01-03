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
        me.model.getData();
    },
    
    /*
     * 渲染Chart
     */
    renderChart: function(model, data) {
        this.destroyChart();
        
        switch (this.statType) {
            case 1: this.renderValueChart(data); break;
            case 2: this.renderAgeChart(data); break;
            case 3: this.renderSexChart(data); break;
            case 4: this.renderXChart(data);
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
        this.$(".chart").highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: '年龄统计'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: '年龄占比',
                data: [
                    ['60',   45.0],
                    ['50',       26.8],
                    ['70',   12.8],
                    ['40',    8.5],
                    ['30',     6.2],
                    ['80',   0.7]
                ]
            }]
        });
    },
    
    renderSexChart: function(data) {
        this.$(".chart").highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: '性别统计'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: '性别占比',
                data: [
                    ['男',   45.0],
                    ['女',       55.0]
                ]
            }]
        });
    },
    
    renderXChart: function(data) {
        this.$(".chart").highcharts({
            title: {
                text: 'Monthly Average Temperature',
                x: -20 //center
            },
            subtitle: {
                text: 'Source: WorldClimate.com',
                x: -20
            },
            xAxis: {
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },
            yAxis: {
                title: {
                    text: 'Temperature (°C)'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: '°C'
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
                name: 'Tokyo',
                data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
            }, {
                name: 'New York',
                data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
            }, {
                name: 'Berlin',
                data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
            }, {
                name: 'London',
                data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
            }]
        });
    }
});
