/*
 * 常量配置
 * 
 * @author: Ricky
 */

var TIME_TYPE = {
    datasource: [
        {name: "全部", value: 1},
        {name: "自定义", value: 2}
    ],
    value: 1
};

var RANGE = {
    range: {
        begin: new Date(2014, 0, 1),
        end: new Date()
    },
    valueAsObject: {
        begin: new Date(2014, 0, 1),
        end: new Date()
    }
};

var PROGRESS_TYPE = {
    datasource: [
        {name: "不限制", value: 1},
        {name: "大于等于", value: 2},
        {name: "小于等于", value: 3}
    ],
    value: 1
};

var PAGE_SIZE = {
    datasource: [
        {name: "20", value: 20},
        {name: "50", value: 50},
        {name: "100", value: 100}
    ],
    value: 20
};

var C_MENU = [
    {
        name: "患者基本信息",
        children: [
            {name: "基本情况", id: 1},
            {name: "个人既往史", id: 2}
        ]
    },
    {name: "病症情况", id: 3},
    {name: "用药情况", id: 4},
    {name: "合并用药", id: 5},
    {
        name: "查体检测",
        children: [
            {name: "入院检查", id: 6},
            {name: "用药中检查", id: 7},
            {name: "出院检查", id: 8}
        ]
    },
    {name: "出院情况", id: 9},
    {name: "药师干预", id: 10},
    {name: "经济学指标", id: 11},
    {name: "ADR", id: 12}
];

var STAT_MENU = [
    {
        name: "总量统计",
        children: [
            {name: "各值统计", id: 1},
            {name: "年龄", id: 2},
            {name: "性别", id: 3}
        ]
    }
];
