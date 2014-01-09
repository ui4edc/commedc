/*
 * 工具
 * 
 * @author: Ricky
 */

var util = {};

/*
 * 兼容旧浏览器
 */
util.fixOld = function() {
    //使IE6缓存背景
    if (T.ie == 6) {
        try {
            document.execCommand("BackgroundImageCache", false, true);
        } catch(e) {}
    }
    
    //兼容调试
    if (!window.console) {
        window.console = {
            log: function() {}
        };
    }
    if (!DEBUG) {
        window.console.log = function() {};
    }
};

/*
 * 阻止默认
 * @param {Event} e 原生事件
 */
util.stopDefault = function(e) {
    var e = e || window.event;
    if (e.preventDefault) {
        e.preventDefault();
    } else {
        e.returnValue = false;
    }
};

/*
 * 阻止冒泡
 * @param {Event} e 原生事件
 */
util.stopBubble = function(e) {
    var e = e || window.event;
    if (e.stopPropagation) {
        e.stopPropagation();
    } else {
        e.cancelBubble = true;
    }
};

/*
 * Ajax封装
 * option字段:
 * url: 请求地址
 * data: json格式数据
 * showMask: true/false 是否显示遮罩
 * success: 成功回调
 * mock: true/false 模拟数据开关
 * mockData: 模拟数据
 */
util.ajax = {
    showError: function(msg) {
        $("#Mask").hide();
        $("#Message").text(msg).show().fadeOut(5000);
    },
    
    showMask: function() {
        $("#Mask").show();
        $("#Message").text("加载中...").show();
    },
    
    hideMask: function() {
        $("#Mask").hide();
        $("#Message").empty().hide();
    },
    
    run: function(option) {
        if (option.mock) {
            var data = option.mockData;
            if (data.success) {
                option.success(data);
            } else {
                util.ajax.showError(data.errorMsg);
            }
            return;
        }
        
        $.ajax({
            type: "POST",
            dataType: "json",
            url: $("#BasePath").val() + option.url,
            data : option.data,
            
            beforeSend: function(jqXHR, settings) {
                if (option.showMask) {
                    util.ajax.showMask();
                }
            },
            
            complete: function(jqXHR, textStatus) {
                if (option.showMask) {
                    util.ajax.hideMask();
                }
            },
            
            error: function(jqXHR, textStatus, errorThrown) {
                util.ajax.showError(textStatus + ": " + errorThrown);
            },
            
            success: function(data, textStatus, jqXHR) {
                if (data.success) {
                    option.success && option.success(data);
                } else {
                    util.ajax.showError(data.errorMsg);
                }
            }
        });
    }
};
