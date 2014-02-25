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
            case 1:
                this.$('.chart').css({height: Math.max(400, data.categories.length * 50)});
                this.renderHospitalChart(data);
                break;
            case 2:
                this.$('.chart').css({height: 400});
                this.renderAgeChart(data);
                break;
            case 3:
                this.$('.chart').css({height: 400});
                this.renderSexChart(data);
        }
    },
    
    renderHospitalChart: function(data) {
        this.$(".chart").highcharts({
            chart: {
                type: 'bar'
            },
            title: {
                text: '医院统计'
            },
            xAxis: {
                categories: data.categories
            },
            yAxis: {
                min: 0,
                title: {
                    text: '表单数'
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
            series: data.series
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
