/*
 * 常量配置
 * 
 * @author: Ricky
 */

var DEBUG = true;

var MOCK = true;

var TIME_TYPE = {
    datasource: [
        {name: "全部", value: 1},
        {name: "自定义", value: 2}
    ],
    value: 1
};

var QUERY_RANGE = {
    range: {
        begin: new Date(2014, 0, 1),
        end: new Date()
    },
    valueAsObject: {
        begin: new Date(2014, 0, 1),
        end: new Date()
    }
};

var BIRTHDAY_RANGE = {
    begin: new Date(1900, 0, 1),
    end: new Date()
};

var CRF_RANGE = {
    begin: new Date(2000, 0, 1),
    end: new Date()
};

var PROGRESS_TYPE = {
    datasource: [
        {name: "不限制", value: 1},
        {name: "大于等于", value: 2},
        {name: "小于等于", value: 3}
    ],
    value: 1
};

var DEFAULT_OPTION = {
    datasource: [{name: "请选择", value: 0}],
    value: 0
};

var PAGE_SIZE = {
    datasource: [
        {name: "20", value: 20},
        {name: "50", value: 50},
        {name: "100", value: 100}
    ],
    value: 20
};

var NATION = [
    {value: 1, name: "汉"}
];

var DRUG_TYPE = {
    datasource: [
        {name: "抗生素类", value: 1},
        {name: "中药注射液", value: 2},
        {name: "其他", value: 3}
    ],
    value: 1
};

var GXY_LEVEL = {
    datasource: [
        {name: "1级", value: 1},
        {name: "2级", value: 2},
        {name: "3级", value: 3},
        {name: "单纯收缩期", value: 4},
        {name: "不详", value: 5}
    ],
    value: 1
};

var TNB_Type = {
    datasource: [
        {name: "I型", value: 1},
        {name: "II型", value: 2},
        {name: "不详", value: 3}
    ],
    value: 1
};

var CRF_MENU = [
    {
        name: "患者信息",
        children: [
            {name: "基本情况", id: 11},
            {name: "个人史 + 过敏史", id: 12},
            {name: "既往史", id: 13}
        ]
    },
    {name: "病症情况", id: 20},
    {name: "用药情况", id: 30},
    {name: "合并用药", id: 40},
    {
        name: "实验检查",
        children: [
            {name: "入组检查", id: 51},
            {name: "用药中检查", id: 52},
            {name: "出院检查", id: 53}
        ]
    },
    {name: "用药小结", id: 60},
    {name: "ADR", id: 70}
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
