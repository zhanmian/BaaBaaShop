<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=0.7">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登录 - BaaBaa</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="/online_shop/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="/online_shop/style.css">
</head>
<body>

<div class="main">
    <!-- Sing in  Form -->
    <section class="sign-in">
        <div class="container">
            <div class="signin-content">
                <div class="signin-image">
                    <figure><img src="/online_shop/images/signin-image.jpg" alt="sing up image"></figure>
                    <a href="${baseUrl!}/baabaa/to_sign_up" class="signup-image-link">新建账号</a>
                </div>

                <div class="signin-form">
                    <h2 class="form-title">登录</h2>
                    <#--<form method="POST" class="register-form" id="login-form">-->
                        <div class="form-group">
                            <label for="your_name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" name="your_name" id="username" placeholder="用户名"/>
                        </div>
                        <div class="form-group">
                            <label for="your_pass"><i class="zmdi zmdi-lock"></i></label>
                            <input type="password" name="your_pass" id="password" placeholder="密码"/>
                        </div>
                        <div class="form-group">
                            <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
                            <label for="remember-me" class="label-agree-term"><span><span></span></span>记住我</label>
                        </div>
                        <div class="form-group form-button">
                            <button type="button" name="signin" id="login" class="form-submit">登录</button>
                        </div>
                    <#--</form>-->
                    <div class="social-login">
                        <span class="social-label">其他方式登录</span>
                        <ul class="socials">
                            <li><a href="#"><i class="display-flex-center zmdi zmdi-facebook"></i></a></li>
                            <li><a href="#"><i class="display-flex-center zmdi zmdi-twitter"></i></a></li>
                            <li><a href="#"><i class="display-flex-center zmdi zmdi-google"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>

</div>

<!-- JS -->
<script src="/js/jquery.min.js"></script>
<script src="/js/online_shop/client/login.js"></script>
<script>
    var baseUrl = '${baseUrl!}';
</script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>