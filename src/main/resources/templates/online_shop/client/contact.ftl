<!DOCTYPE html>
<html lang="en">
<head>
    <title>Contact</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Sublime project">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/online_shop/styles/bootstrap4/bootstrap.min.css">
    <link href="/online_shop/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/online_shop/styles/contact.css">
    <link rel="stylesheet" type="text/css" href="/online_shop/styles/contact_responsive.css">
</head>
<body>

<div class="super_container">

    <!-- Header -->
    <#include "/online_shop/client/header.ftl">

    <!-- Home -->

    <div class="home">
        <div class="home_container">
            <div class="home_background" style="background-image:url(/online_shop/images/contact.jpg)"></div>
            <div class="home_content_container">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="home_content">
                                <div class="breadcrumbs">
                                    <ul>
                                        <li><a href="index.html">首页</a></li>
                                        <li>联系方式</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Contact -->

    <div class="contact">
        <div class="container">
            <div class="row">

                <!-- Get in touch -->
                <div class="col-lg-8 contact_col">
                    <div class="get_in_touch">
                        <div class="section_title">发送站内信</div>
                        <div class="section_subtitle">你好</div>
                        <div class="contact_form_container">
                            <form action="#" id="contact_form" class="contact_form">
                                <div class="row">
                                    <div class="col-xl-6">
                                        <!-- Name -->
                                        <label for="contact_name">姓名*</label>
                                        <input type="text" id="contact_name" class="contact_input" required="required">
                                    </div>
                                    <div class="col-xl-6 last_name_col">
                                        <!-- Last Name -->
                                        <label for="contact_last_name">姓氏*</label>
                                        <input type="text" id="contact_last_name" class="contact_input" required="required">
                                    </div>
                                </div>
                                <div>
                                    <!-- Subject -->
                                    <label for="contact_company">联系方式</label>
                                    <input type="text" id="contact_company" class="contact_input">
                                </div>
                                <div>
                                    <label for="contact_textarea">信息*</label>
                                    <textarea id="contact_textarea" class="contact_input contact_textarea" required="required"></textarea>
                                </div>
                                <button class="button contact_button"><span>发送</span></button>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Contact Info -->
                <div class="col-lg-3 offset-xl-1 contact_col">
                    <div class="contact_info">
                        <div class="contact_info_section">
                            <div class="contact_info_title">售前专线</div>
                            <ul>
                                <li>座机: <span>+86 7953 3245</span></li>
                                <li>Email: <span>yourmail@gmail.com</span></li>
                            </ul>
                        </div>
                        <div class="contact_info_section">
                            <div class="contact_info_title">售后专线</div>
                            <ul>
                                <li>Phone: <span>+53 345 7953 3245</span></li>
                                <li>Email: <span>yourmail@gmail.com</span></li>
                            </ul>
                        </div>
                        <div class="contact_info_section">
                            <div class="contact_info_title">投诉</div>
                            <ul>
                                <li>Phone: <span>+53 345 7953 3245</span></li>
                                <li>Email: <span>yourmail@gmail.com</span></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <#include "/online_shop/client/footer.ftl">

</div>

<script src="js/jquery.min.js"></script>
<script src="/online_shop/styles/bootstrap4/popper.js"></script>
<script src="/online_shop/styles/bootstrap4/bootstrap.min.js"></script>
<script src="/online_shop/plugins/greensock/TweenMax.min.js"></script>
<script src="/online_shop/plugins/greensock/TimelineMax.min.js"></script>
<script src="/online_shop/plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="/online_shop/plugins/greensock/animation.gsap.min.js"></script>
<script src="/online_shop/plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="/online_shop/plugins/easing/easing.js"></script>
<script src="js/contact.js"></script>
</body>
</html>