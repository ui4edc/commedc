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
    begin: new Date(2014, 0, 1),
    end: new Date()
};

var PROGRESS_TYPE = {
    datasource: [
        {name: "不限制", value: 0},
        {name: "大于等于", value: 1},
        {name: "小于等于", value: 2}
    ],
    value: 0
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
    {value: 0, name: "请选择"},
    {value: 1, name: "汉"},
    {value: 2, name: "壮族"},
    {value: 3, name: "满族"},
    {value: 4, name: "回族"},
    {value: 5, name: "苗族"},
    {value: 6, name: "维吾尔族"},
    {value: 7, name: "土家族"},
    {value: 8, name: "彝族"},
    {value: 9, name: "蒙古族"},
    {value: 10, name: "藏族"},
    {value: 11, name: "布依族"},
    {value: 12, name: "侗族"},
    {value: 13, name: "瑶族"},
    {value: 14, name: "朝鲜族"},
    {value: 15, name: "白族"},
    {value: 16, name: "哈尼族"},
    {value: 17, name: "哈萨克族"},
    {value: 18, name: "黎族"},
    {value: 19, name: "傣族"},
    {value: 20, name: "畲族"},
    {value: 21, name: "僳僳族"},
    {value: 22, name: "仡佬族"},
    {value: 23, name: "东乡族"},
    {value: 24, name: "高山族"},
    {value: 25, name: "拉祜族"},
    {value: 26, name: "水族"},
    {value: 27, name: "佤族"},
    {value: 28, name: "纳西族"},
    {value: 29, name: "羌族"},
    {value: 30, name: "土族"},
    {value: 31, name: "仫佬族"},
    {value: 32, name: "锡伯族"},
    {value: 33, name: "柯尔克孜族"},
    {value: 34, name: "达斡尔族"},
    {value: 35, name: "景颇族"},
    {value: 36, name: "毛南族"},
    {value: 37, name: "撒拉族"},
    {value: 38, name: "布朗族"},
    {value: 39, name: "塔吉克族"},
    {value: 40, name: "阿昌族"},
    {value: 41, name: "普米族"},
    {value: 42, name: "鄂温克族"},
    {value: 43, name: "怒族"},
    {value: 44, name: "京族"},
    {value: 45, name: "基诺族"},
    {value: 46, name: "德昂族"},
    {value: 47, name: "保安族"},
    {value: 48, name: "俄罗斯族"},
    {value: 49, name: "裕固族"},
    {value: 50, name: "乌孜别克族"},
    {value: 51, name: "门巴族"},
    {value: 52, name: "鄂伦春族"},
    {value: 53, name: "独龙族"},
    {value: 54, name: "塔塔尔族"},
    {value: 55, name: "赫哲族"},
    {value: 56, name: "珞巴族"}
];

var DRUG_TYPE = {
    datasource: [
        {name: "抗生素类", value: 1},
        {name: "中药注射液", value: 2},
        {name: "其他", value: 3}
    ],
    value: 1
};

var GXB_LEVEL = {
    datasource: [
        {name: "I", value: 1},
        {name: "II", value: 2},
        {name: "III", value: 3},
        {name: "IV", value: 4}
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
            {name: "入院检查", id: 51},
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
            {name: "医院统计", id: 1},
            {name: "年龄统计", id: 2},
            {name: "性别统计", id: 3}
        ]
    }
];
