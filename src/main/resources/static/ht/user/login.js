/**
 * Created by 张帆 on 2018/11/30.
 */
function keyLogin(){
    if(event.keyCode==13){
        var username = $("#username").val();
        var password = $("#password").val();
        $.get('/sysuser/login', {username: username, password: password}, function (res) {
            if (res.code == 200) {
                location.href = "/index";
            } else if (res.code == 400) {
                // layer.msg(res.msg);
                alert(res.msg);
            }
        })
    }
}





$("#login-submit").click(function (){
    var username = $("#username").val();
    var password = $("#password").val();
    $.get('/sysuser/login', {username: username, password: password}, function (res) {
        if (res.code == 200) {
            location.href = "/index";
        } else if (res.code == 400) {
            // layer.msg(res.msg);
            alert(res.msg);
        }
    })
});