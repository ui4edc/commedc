/*
 * 修改密码
 * 
 * @author: Ricky
 */

$("#change").click(function() {
    var oldPassword = $.trim($("#oldPassword").val()),
        newPassword = $.trim($("#newPassword").val()),
        repeatPassword = $.trim($("#repeatPassword").val()),
        tip = $(".tip");
    if (oldPassword == "") {
        tip.text("请输入原密码").show().fadeOut(1500);
        return;
    }
    if (newPassword == "") {
        tip.text("请输入新密码").show().fadeOut(1500);
        return;
    }
    if (newPassword != repeatPassword) {
        tip.text("密码不一致").show().fadeOut(1500);
        return;
    }
    
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $("#BasePath").val() + "",
        data : {
            oldPassword: oldPassword,
            newPassword: newPassword
        },
        success: function(data) {
            if (data.success) {
                $("#oldPassword").val("");
                $("#newPassword").val("");
                $("#repeatPassword").val("");
                tip.text("修改成功").show().fadeOut(1500);
            } else {
                tip.text(data.errorMsg).show().fadeOut(1500);
            }
        }
    });
});
