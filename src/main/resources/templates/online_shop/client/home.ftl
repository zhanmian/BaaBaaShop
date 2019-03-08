<!DOCTYPE html>
<html lang="en">
<head>
    <title>首页 - BaaBaa Shop</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Sublime project">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/online_shop/styles/bootstrap4/bootstrap.min.css">
    <link href="/online_shop/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/online_shop/plugins/OwlCarousel2-2.2.1/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="/online_shop/plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
    <link rel="stylesheet" type="text/css" href="/online_shop/plugins/OwlCarousel2-2.2.1/animate.css">
    <link rel="stylesheet" type="text/css" href="/online_shop/styles/main_styles.css">
    <link rel="stylesheet" type="text/css" href="/online_shop/styles/responsive.css">
</head>
<body>

<div class="super_container">

    <!-- Header -->

    <#include "/online_shop/client/header.ftl">

    <!-- Home -->

    <div class="home">
        <div class="home_slider_container">

            <!-- Home Slider -->
            <div class="owl-carousel owl-theme home_slider">

                <!-- Slider Item -->
                <div class="owl-item home_slider_item">
                    <div class="home_slider_background" style="background-image:url(/online_shop/images/banner.jpg)"></div>
                    <div class="home_slider_content_container">
                        <div class="container">
                            <div class="row">
                                <div class="col">
                                    <div class="home_slider_content"  data-animation-in="fadeIn" data-animation-out="animate-out fadeOut">
                                        <div class="home_slider_title">极简主义</div>
                                        <div class="home_slider_subtitle">简单、舒适、与你契合</div>
                                        <div class="button button_light home_button"><a href="#">立即开启</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <!-- Home Slider Dots -->

            <div class="home_slider_dots_container">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="home_slider_dots">
                                <ul id="home_slider_custom_dots" class="home_slider_custom_dots">
                                    <li class="home_slider_custom_dot active">01.</li>
                                    <li class="home_slider_custom_dot">02.</li>
                                    <li class="home_slider_custom_dot">03.</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <!-- Ads -->

    <div class="avds">
        <div class="avds_container d-flex flex-lg-row flex-column align-items-start justify-content-between">
            <div class="avds_small">
                <div class="avds_background" style="background-image:url(/online_shop/images/t-shirt2.jpg)"></div>
                <div class="avds_small_inner">
                    <div class="avds_discount_container">
                        <img src="/online_shop/images/discount.png" alt="">
                        <div>
                            <div class="avds_discount">
                                <div>20<span>%</span></div>
                                <div>Discount</div>
                            </div>
                        </div>
                    </div>
                    <div class="avds_small_content">
                        <div class="avds_title">新品</div>
                        <div class="avds_link"><a href="categories.html">查看更多</a></div>
                    </div>
                </div>
            </div>
            <div class="avds_large">
                <div class="avds_background" style="background-image:url(/online_shop/images/clothes-store.jpg)"></div>
                <div class="avds_large_container">
                    <div class="avds_large_content">
                        <div class="avds_title">了解我们</div>
                        <div class="avds_text">没有一件服饰的设计存在多余</div>
                        <div class="avds_link avds_link_large"><a href="contact.html">查看更多</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Products -->

    <div class="products">
        <div class="container">
            <div class="row">
                <#--<div class="col">-->

                    <#--<div class="product_grid">-->

                        <!-- Product -->
                        <#list recommendProducts as item>
                            <div class="product">
                                <div class="product_image">
                                    <a href="${baseUrl}/baabaa/product_details?id=${item.id!}">
                                        <img src="${baseUrl}/downloadImage?filePath=${item.picture!}" alt="">
                                    </a>
                                </div>
                                <div class="product_extra product_new"><a href="categories.html">New</a></div>
                                <div class="product_content">
                                    <div class="product_title">
                                        <a href="${baseUrl}/baabaa/product_details?id=${item.id!}">
                                            ${item.productName!}
                                        </a>
                                    </div>
                                    <div class="product_price">￥${item.productPrice!}</div>
                                </div>
                            </div>
                        </#list>

                    <#--</div>-->

                <#--</div>-->
            </div>
        </div>
    </div>

    <!-- Ad -->

    <#--<div class="avds_xl">-->
        <#--<div class="container">-->
            <#--<div class="row">-->
                <#--<div class="col">-->
                    <#--<div class="avds_xl_container clearfix">-->
                        <#--<div class="avds_xl_background" style="background-image:url(/online_shop/images/avds_xl.jpg)"></div>-->
                        <#--<div class="avds_xl_content">-->
                            <#--<div class="avds_title">Amazing Devices</div>-->
                            <#--<div class="avds_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam a ultricies metus.</div>-->
                            <#--<div class="avds_link avds_xl_link"><a href="categories.html">See More</a></div>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->

    <!-- Icon Boxes -->

    <div class="icon_boxes">
        <div class="container">
            <div class="row icon_box_row">

                <!-- Icon Box -->
                <div class="col-lg-4 icon_box_col">
                    <div class="icon_box">
                        <div class="icon_box_image"><img src="/online_shop/images/icon_1.svg" alt=""></div>
                        <div class="icon_box_title">全国免运费</div>
                        <div class="icon_box_text">
                            <p>指定快递公司：顺丰速运</p>
                        </div>
                    </div>
                </div>

                <!-- Icon Box -->
                <div class="col-lg-4 icon_box_col">
                    <div class="icon_box">
                        <div class="icon_box_image"><img src="/online_shop/images/icon_2.svg" alt=""></div>
                        <div class="icon_box_title">七天无理由退换</div>
                        <div class="icon_box_text">
                            <p>购物没有顾虑，服务本该如此</p>
                        </div>
                    </div>
                </div>

                <!-- Icon Box -->
                <div class="col-lg-4 icon_box_col">
                    <div class="icon_box">
                        <div class="icon_box_image"><img src="/online_shop/images/icon_3.svg" alt=""></div>
                        <div class="icon_box_title">快速客服响应</div>
                        <div class="icon_box_text">
                            <p>每日9:00~21:00欢迎咨询、投诉、建议</p>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <!-- Newsletter -->

    <div class="newsletter">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="newsletter_border"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 offset-lg-2">
                    <div class="newsletter_content text-center">
                        <div class="newsletter_title">订阅</div>
                        <div class="newsletter_text"><p>欢迎订阅微信公众号 / 微博：baabaashop</p></div>
                        <#--<div class="newsletter_form_container">-->
                            <#--<form action="#" id="newsletter_form" class="newsletter_form">-->
                                <#--<input type="email" class="newsletter_input" required="required">-->
                                <#--<button class="newsletter_button trans_200"><span>Subscribe</span></button>-->
                            <#--</form>-->
                        <#--</div>-->
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <#include "/online_shop/client/footer.ftl">


<script src="/js/jquery.min.js"></script>
<script src="/online_shop/styles/bootstrap4/popper.js"></script>
<script src="/online_shop/styles/bootstrap4/bootstrap.min.js"></script>
<script src="/online_shop/plugins/greensock/TweenMax.min.js"></script>
<script src="/online_shop/plugins/greensock/TimelineMax.min.js"></script>
<script src="/online_shop/plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="/online_shop/plugins/greensock/animation.gsap.min.js"></script>
<script src="/online_shop/plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="/online_shop/plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="/online_shop/plugins/Isotope/isotope.pkgd.min.js"></script>
<script src="/online_shop/plugins/easing/easing.js"></script>
<script src="/online_shop/plugins/parallax-js-master/parallax.min.js"></script>
<script src="/js/online_shop/client/home.js"></script>
</body>
</html>