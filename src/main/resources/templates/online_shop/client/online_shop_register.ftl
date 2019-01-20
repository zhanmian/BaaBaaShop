<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=0.7">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>注册 - BaaBaa</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="/online_shop/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="/online_shop/style.css">
</head>
<body>

<div class="main">

    <!-- Sign up form -->
    <section class="signup">
        <div class="container">
            <div class="signup-content">
                <div class="signup-form">
                    <h2 class="form-title">注册 | BaaBaa</h2>
                    <#--<form method="POST" class="register-form" id="register-form">-->
                        <div class="form-group">
                            <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" name="name" id="username" placeholder="用户名"/>
                        </div>
                        <div class="form-group">
                            <label for="phone"><i class="zmdi zmdi-smartphone"></i></label>
                            <input type="text" name="phone" id="phone" placeholder="手机号码"/>
                        </div>
                        <div class="form-group">
                            <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                            <input type="password" name="pass" id="password" placeholder="密码"/>
                        </div>
                        <div class="form-group">
                            <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                            <input type="password" name="re_pass" id="re_password" placeholder="再输入一次密码"/>
                        </div>
                        <div class="form-group">
                            <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                            <label for="agree-term" class="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label>
                        </div>
                        <div class="form-group form-button">
                            <button type="button" name="sign-up" id="sign-up" class="form-submit">注册</button>
                        </div>
                    <#--</form>-->
                </div>
                <div class="signup-image">
                    <figure><img src="/online_shop/images/signup-image.jpg" alt="sing up image"></figure>
                    <a href="${baseUrl!}/baabaa/to_login" class="signup-image-link">已经拥有账号</a>
                </div>
            </div>
        </div>
    </section>
</div>

<!-- JS -->
<script src="/js/jquery.min.js"></script>
<script src="/js/online_shop/client/register.js"></script>
<script>
    var baseUrl = '${baseUrl!}';
</script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>