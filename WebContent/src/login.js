/*
 * 登录
 * 
 * @author: Ricky
 */

$("#login").click(function() {
    var username = $.trim($("#username").val()),
        password = $.trim($("#password").val()),
        tip = $(".tip");
    if (username == "") {
        tip.text("请输入用户名").show().fadeOut(1500);
        return;
    }
    if (password == "") {
        tip.text("请输入密码").show().fadeOut(1500);
        return;
    }
    
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $("#BasePath").val() + "tologin.do",
        data : {
            username: username,
            password: password
        },
        success: function(data) {
            if (data.success) {
                window.location.href = '/index.do';
            } else {
                tip.text("用户名或密码错误").show().fadeOut(1500);
            }
        }
    });
});
