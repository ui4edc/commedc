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
});
