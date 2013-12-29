/*
 * 登录
 * 
 * @author: Ricky
 */

$("form").submit(function(e) {
    var username = $.trim($("#username").val()),
        password = $.trim($("#password").val()),
        tip = $(".tip");
    if (username == "") {
        tip.text("请输入用户名").show().fadeOut(1500);
        e.preventDefault();
        return;
    }
    if (password == "") {
        tip.text("请输入密码").show().fadeOut(1500);
        e.preventDefault();
    }
    var token = {};
    token.username = username;
    token.password = password;
    $.post('/tologin.do', token, function(data){
    	if (data.success)
    		document.location.href = '/index.do';
    	else{
    		tip.text("用户名或密码错误").show().fadeOut(1500);
    		e.preventDefault();
    	}
    });
});
