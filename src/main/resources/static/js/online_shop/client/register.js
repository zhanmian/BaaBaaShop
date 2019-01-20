$(document).ready(function(){
    register();
});

function register(){
    $('#sign-up').click(function(){
        var param = {};
        param.username = $('#username').val();
        param.password = $('#password').val();
        param.phone = $('#phone').val();
        $.ajax({
            type : 'post',
            data : param,
            url : baseUrl + '/baabaa/register',
            success : function(response){
                alert(response.message);
                window.location.href = baseUrl + '/baabaa/to_login';
            }
        })
    });
}