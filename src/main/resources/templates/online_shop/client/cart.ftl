<!DOCTYPE html>
<html lang="en">
<head>
    <title>购物车 - BaaBaa Shop</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Sublime project">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/online_shop/styles/bootstrap4/bootstrap.min.css">
    <link href="/online_shop/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/online_shop/styles/cart.css">
    <link rel="stylesheet" type="text/css" href="/online_shop/styles/cart_responsive.css">
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
                                        <#--<li><a href="categories.html">Categories</a></li>-->
                                        <#--<li>Shopping Cart</li>-->
                                    <#--</ul>-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->

    <!-- CartItem Info -->

    <div class="cart_info">
        <div class="container">
            <div class="row">
                <div class="col">
                    <!-- Column Titles -->
                    <div class="cart_info_columns clearfix">
                        <div class="cart_info_col cart_info_col_product">商品</div>
                        <div class="cart_info_col cart_info_col_price">价格</div>
                        <div class="cart_info_col cart_info_col_quantity">数量</div>
                        <div class="cart_info_col cart_info_col_total">合计</div>
                    </div>
                </div>
            </div>
            <div class="row cart_items_row">
                <div class="col" id="cart-items">
                    <#if hasItem=="false">
                        <div style="text-align: center"><p>购物车空空如也~</p></div>
                    <#else>
                        <#list cart.items as item>
                        <!-- CartItem Item -->
                        <div class="cart_item d-flex flex-lg-row flex-column align-items-lg-center align-items-start justify-content-start"
                             id="${item.skuId!}">
                            <!-- Name -->
                            <div class="cart_item_product d-flex flex-row align-items-center justify-content-start">
                                <div class="cart_item_image">
                                    <div>
                                        <img src="${baseUrl}/downloadImage?filePath=${item.productPicture!}" alt="">
                                    </div>
                                </div>
                                <div class="cart_item_name_container">
                                    <div class="cart_item_name"><a href="#">${item.productName!}</a></div>
                                    <div class="cart_item_edit"><a href="#">${item.spec1!} ${item.spec2!} ${item.spec3!} </a></div>
                                </div>
                            </div>
                            <!-- Price -->
                            <div class="cart_item_price">￥<span class="price">${item.skuPrice!}</span></div>
                            <!-- Quantity -->
                            <div class="cart_item_quantity">
                                <div class="product_quantity_container">
                                    <div class="product_quantity clearfix">
                                        <span>数量</span>
                                        <input id="quantity_input" type="text" class="quantity_input" pattern="[0-9]*" value="${item.quantity!}">
                                        <div class="quantity_buttons">
                                        <#--隐藏域-->
                                            <input type="hidden" id="sku-id" value="${item.skuId!}">
                                            <div id="quantity_inc_button" class="quantity_inc quantity_control"><i class="fa fa-chevron-up" aria-hidden="true"></i></div>
                                            <div id="quantity_dec_button" class="quantity_dec quantity_control"><i class="fa fa-chevron-down" aria-hidden="true"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Total -->
                            <div class="cart_item_total">￥<span class="item-total">${item.quantity! * item.skuPrice!}</span></div>
                        </div>
                        </#list>
                    </#if>
                </div>
            </div>
            <div class="row row_cart_buttons">
                <div class="col">
                    <div class="cart_buttons d-flex flex-lg-row flex-column align-items-start justify-content-start">
                        <div class="button continue_shopping_button"><a href="/baabaa/home">继续逛一逛</a></div>
                        <div class="cart_buttons_right ml-lg-auto">
                            <div class="button clear_cart_button"><a href="#">清空购物车</a></div>
                            <div class="button update_cart_button"><a href="#">更新购物车</a></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row row_extra">
                <div class="col-lg-4">

                    <!-- Delivery -->
                    <div class="delivery">
                        <div class="section_title">运输方案</div>
                        <div class="section_subtitle">请选择你想要的方案</div>
                        <div class="delivery_options">
                            <label class="delivery_option clearfix">次日送达
                                <input type="radio" name="radio">
                                <span class="checkmark"></span>
                                <span class="delivery_price">￥20</span>
                            </label>
                            <label class="delivery_option clearfix">普通运输
                                <input type="radio" name="radio">
                                <span class="checkmark"></span>
                                <span class="delivery_price">￥10</span>
                            </label>
                            <label class="delivery_option clearfix">商品自提
                                <input type="radio" checked="checked" name="radio">
                                <span class="checkmark"></span>
                                <span class="delivery_price">￥0</span>
                            </label>
                        </div>
                    </div>

                    <!-- Coupon Code -->
                    <#--<div class="coupon">-->
                        <#--<div class="section_title">优惠券码</div>-->
                        <#--<div class="section_subtitle">输入你的优惠券码</div>-->
                        <#--<div class="coupon_form_container">-->
                            <#--<form action="#" id="coupon_form" class="coupon_form">-->
                                <#--<input type="text" class="coupon_input" required="required">-->
                                <#--<button class="button coupon_button"><span>兑换</span></button>-->
                            <#--</form>-->
                        <#--</div>-->
                    <#--</div>-->
                </div>

                <div class="col-lg-6 offset-lg-2">
                    <div class="cart_total">
                        <div class="section_title">购物车结算</div>
                        <div class="section_subtitle">汇总信息</div>
                        <div class="cart_total_container">
                            <ul>
                                <li class="d-flex flex-row align-items-center justify-content-start">
                                    <div class="cart_total_title">商品小计</div>
                                    <div class="cart_total_value ml-auto">￥<span id="total-price">${totalPrice!}</span></div>
                                </li>
                                <li class="d-flex flex-row align-items-center justify-content-start">
                                    <div class="cart_total_title">运费</div>
                                    <div class="cart_total_value ml-auto">￥<span id="freight">0</span></div>
                                </li>
                                <li class="d-flex flex-row align-items-center justify-content-start">
                                    <div class="cart_total_title">总和</div>
                                    <div class="cart_total_value ml-auto">￥<span id="total-amount"></span></div>
                                </li>
                            </ul>
                        </div>
                        <div class="button checkout_button"><a href="${baseUrl}/baabaa/checkout">结账</a></div>
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
<script src="/js/online_shop/client/cart.js"></script>
<script>
    var baseUrl = "${baseUrl!}";
</script>
</body>
</html>