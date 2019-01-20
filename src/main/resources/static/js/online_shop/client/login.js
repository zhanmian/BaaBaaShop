$(document).ready(function(){
    login();
});

function login(){
    $('#login').click(function(){
        var param = {};
        param.username = $('#username').val();
        param.password = $('#password').val();
        // if($('input[name="remember-me"]:checked')){
        //     param.rememberMe = 1;
        // }else{
        //     param.rememberMe = 0;
        // }
        $.ajax({
            type : 'post',
            data : param,
            url : baseUrl + '/baabaa/login',
            success : function(response){
                alert(response.message);
                if(response.code == 1){
                    window.location.href = baseUrl + '/baabaa/home';
                }
            }
        })
    })
}