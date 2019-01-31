<!DOCTYPE html>
<html lang="en">
<head>
    <title>结算</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Sublime project">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/online_shop/styles/bootstrap4/bootstrap.min.css">
    <link href="/online_shop/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/online_shop/styles/checkout.css">
    <link rel="stylesheet" type="text/css" href="/online_shop/styles/checkout_responsive.css">
</head>
<body>

<div class="super_container">

    <!-- Header -->

    <#include "/online_shop/client/header.ftl">

    <!-- Home -->

    <#--<div class="home">-->
        <#--<div class="home_container">-->
            <#--<div class="home_background" style="background-image:url(/online_shop/images/cart.jpg)"></div>-->
            <#--<div class="home_content_container">-->
                <#--<div class="container">-->
                    <#--<div class="row">-->
                        <#--<div class="col">-->
                            <#--<div class="home_content">-->
                                <#--<div class="breadcrumbs">-->
                                    <#--<ul>-->
                                        <#--<li><a href="index.html">Home</a></li>-->
                                        <#--<li><a href="cart.html">Shopping Cart</a></li>-->
                                        <#--<li>Checkout</li>-->
                                    <#--</ul>-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->

    <!-- Checkout -->

    <div class="checkout">
        <div class="container">
            <div class="row">

                <!-- Billing Info -->
                <div class="col-lg-6">
                    <div class="billing checkout_section">
                        <div class="section_title">寄送地址</div>
                        <div class="section_subtitle">填写你的地址信息</div>
                        <div class="checkout_form_container">
                            <form action="#" id="checkout_form" class="checkout_form">
                                <div>
                                    <!-- Name -->
                                    <label for="checkout_name">姓名*</label>
                                    <input type="text" id="checkout_name" class="checkout_input" required="required">
                                </div>
                                <div>
                                    <!-- Phone no -->
                                    <label for="checkout_phone">手机号码*</label>
                                    <input type="phone" id="checkout_phone" class="checkout_input" required="required">
                                </div>
                                <div>
                                    <!-- Zipcode -->
                                    <label for="checkout_zipcode">邮编*</label>
                                    <input type="text" id="checkout_zipcode" class="checkout_input" required="required">
                                </div>
                                <div>
                                    <!-- Country -->
                                    <label for="checkout_country">国家/地区*</label>
                                    <select name="checkout_country" id="checkout_country" class="dropdown_item_select checkout_input" require="required">
                                        <option></option>
                                        <option>Lithuania</option>
                                        <option>Sweden</option>
                                        <option>UK</option>
                                        <option>Italy</option>
                                    </select>
                                </div>
                                <div>
                                    <!-- Province -->
                                    <label for="checkout_province">省份*</label>
                                    <select name="checkout_province" id="checkout_province" class="dropdown_item_select checkout_input" require="required">
                                        <option></option>
                                        <option>Province</option>
                                        <option>Province</option>
                                        <option>Province</option>
                                        <option>Province</option>
                                    </select>
                                </div>
                                <div>
                                    <!-- City / Town -->
                                    <label for="checkout_city">城市*</label>
                                    <select name="checkout_city" id="checkout_city" class="dropdown_item_select checkout_input" require="required">
                                        <option></option>
                                        <option>City</option>
                                        <option>City</option>
                                        <option>City</option>
                                        <option>City</option>
                                    </select>
                                </div>
                                <div>
                                    <!-- Address -->
                                    <label for="checkout_address">具体地址*</label>
                                    <input type="text" id="checkout_address" class="checkout_input" required="required">
                                </div>
                                <div class="checkout_extra">
                                    <div>
                                        <input type="checkbox" id="checkbox_terms" name="regular_checkbox" class="regular_checkbox" checked="checked">
                                        <label for="checkbox_terms"><img src="/online_shop/images/check.png" alt=""></label>
                                        <span class="checkbox_title">Terms and conditions</span>
                                    </div>
                                    <div>
                                        <input type="checkbox" id="checkbox_account" name="regular_checkbox" class="regular_checkbox">
                                        <label for="checkbox_account"><img src="/online_shop/images/check.png" alt=""></label>
                                        <span class="checkbox_title">Create an account</span>
                                    </div>
                                    <div>
                                        <input type="checkbox" id="checkbox_newsletter" name="regular_checkbox" class="regular_checkbox">
                                        <label for="checkbox_newsletter"><img src="/online_shop/images/check.png" alt=""></label>
                                        <span class="checkbox_title">Subscribe to our newsletter</span>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Order Info -->

                <div class="col-lg-6">
                    <div class="order checkout_section">
                        <div class="section_title">你的订单</div>
                        <div class="section_subtitle">订单详情</div>

                        <!-- Order details -->
                        <div class="order_list_container">
                            <div class="order_list_bar d-flex flex-row align-items-center justify-content-start">
                                <div class="order_list_title">商品</div>
                                <div class="order_list_value ml-auto">合计</div>
                            </div>
                            <ul class="order_list">
                                <#list cartItems.items as item>
                                    <li class="d-flex flex-row align-items-center justify-content-start">
                                        <div class="order_list_title">${item.productName!} ${item.spec1!} ${item.spec2!} ${item.spec3!} * ${item.quantity!} 件</div>
                                        <div class="order_list_value ml-auto">￥${item.skuPrice! * item.quantity!}</div>
                                    </li>
                                </#list>
                                <li class="d-flex flex-row align-items-center justify-content-start">
                                    <div class="order_list_title">小计</div>
                                    <div class="order_list_value ml-auto">￥${totalPrice!}</div>
                                </li>
                                <li class="d-flex flex-row align-items-center justify-content-start">
                                    <div class="order_list_title">运费</div>
                                    <div class="order_list_value ml-auto">Free</div>
                                </li>
                                <li class="d-flex flex-row align-items-center justify-content-start">
                                    <div class="order_list_title">总价</div>
                                    <div class="order_list_value ml-auto">
                                        ￥<span id="total-amount">${totalPrice!}</span>
                                    </div>
                                </li>
                            </ul>
                        </div>

                        <!-- Payment Options -->
                        <div class="payment">
                            <div class="payment_options">
                                <label class="payment_option clearfix">支付宝
                                    <input type="radio" name="pay-type" value="1" checked="checked">
                                    <span class="checkmark"></span>
                                </label>
                                <label class="payment_option clearfix">微信支付
                                    <input type="radio" name="pay-type" value="2">
                                    <span class="checkmark"></span>
                                </label>
                            </div>
                        </div>

                        <!-- Order Text -->
                        <#--<div class="order_text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin pharetra temp or so dales. Phasellus sagittis auctor gravida. Integ er bibendum sodales arcu id te mpus. Ut consectetur lacus.</div>-->
                        <div class="button order_button"><a href="javascript:generateOrder()">提交订单</a></div>
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
<script src="/online_shop/plugins/easing/easing.js"></script>
<script src="/online_shop/plugins/parallax-js-master/parallax.min.js"></script>
<script src="/js/online_shop/client/checkout.js"></script>
<script>
    var baseUrl = "${baseUrl!}";
</script>
</body>
</html>