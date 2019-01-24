<!DOCTYPE html>
<html lang="en">
<head>
    <title>商品详情 - BaaBaa Shop</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Sublime project">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/online_shop/styles/bootstrap4/bootstrap.min.css">
    <link href="/online_shop/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/online_shop/plugins/OwlCarousel2-2.2.1/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="/online_shop/plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
    <link rel="stylesheet" type="text/css" href="/online_shop/plugins/OwlCarousel2-2.2.1/animate.css">
    <link rel="stylesheet" type="text/css" href="/online_shop/styles/product.css">
    <link rel="stylesheet" type="text/css" href="/online_shop/styles/product_responsive.css">
</head>
<body>

<div class="super_container">

    <!-- Header -->

    <#include "/online_shop/client/header.ftl">

    <!-- Home -->

    <#--<div class="home">-->
        <#--<div class="home_container">-->
            <#--<div class="home_background" style="background-image:url(css/online_shop/images/categories.jpg)"></div>-->
            <#--<div class="home_content_container">-->
                <#--<div class="container">-->
                    <#--<div class="row">-->
                        <#--<div class="col">-->
                            <#--<div class="home_content">-->
                                <#--<div class="home_title">Smart Phones<span>.</span></div>-->
                                <#--<div class="home_text"><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam a ultricies metus. Sed nec molestie eros. Sed viverra velit venenatis fermentum luctus.</p></div>-->
                            <#--</div>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->

    <!-- Product Details -->

    <div class="product_details">
        <div class="container">
            <div class="row details_row">

                <!-- Product Image -->
                <div class="col-lg-6">
                    <div class="details_image">
                        <div class="details_image_large"><img src="${baseUrl}/downloadImage/?filePath=${product.picture!}" alt="">
                            <div class="product_extra product_new">
                            <a href="categories.html">New</a>
                            </div>
                        </div>
                        <#--<div class="details_image_thumbnails d-flex flex-row align-items-start justify-content-between">-->
                            <#--<div class="details_image_thumbnail active" data-image="css/online_shop/images/details_1.jpg"><img src="css/online_shop/images/details_1.jpg" alt=""></div>-->
                            <#--<div class="details_image_thumbnail" data-image="css/online_shop/images/details_2.jpg"><img src="css/online_shop/images/details_2.jpg" alt=""></div>-->
                            <#--<div class="details_image_thumbnail" data-image="css/online_shop/images/details_3.jpg"><img src="css/online_shop/images/details_3.jpg" alt=""></div>-->
                            <#--<div class="details_image_thumbnail" data-image="css/online_shop/images/details_4.jpg"><img src="css/online_shop/images/details_4.jpg" alt=""></div>-->
                        <#--</div>-->
                    </div>
                </div>

                <!-- Product Content -->
                <div class="col-lg-6">
                    <div class="details_content">
                        <div class="details_name" id="product-name">${product.productName!}</div>
                        <#--<div class="details_discount">$890</div>-->
                        <div id="product-price" class="details_price">￥${product.productPrice!}</div>

                        <!-- In Stock -->
                        <div class="in_stock_container">
                            <div class="availability">库存：</div>
                            <span id="availability">现在有货</span>
                        </div>
                        <div class="details_text">
                            <#--<p>${product.subTitle!}</p>-->
                        </div>

                        <div id="attribute-container"></div>

                        <!-- Product Quantity -->
                        <div class="product_quantity_container">
                            <span>数量：</span>
                            <div class="product_quantity clearfix">
                                <input id="quantity_input" type="text" pattern="[0-9]*" value="1">
                                <div class="quantity_buttons">
                                    <div id="quantity_inc_button" class="quantity_inc quantity_control"><i class="fa fa-chevron-up" aria-hidden="true"></i></div>
                                    <div id="quantity_dec_button" class="quantity_dec quantity_control"><i class="fa fa-chevron-down" aria-hidden="true"></i></div>
                                </div>
                            </div>
                            <div class="button cart_button"><a href="javascript:addCart();" id="add-cart">加入购物车</a></div>
                        </div>

                        <!-- Share -->
                        <div class="details_share">
                            <span>分享至:</span>
                            <ul>
                                <li><a href="#"><i class="fa fa-weibo" aria-hidden="true"></i></a></li>
                                <li><a href="#"><i class="fa fa-qq" aria-hidden="true"></i></a></li>
                                <li><a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
                                <li><a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row description_row">
                <div class="col">
                    <div class="description_title_container">
                        <div class="description_title">详情</div>
                        <div class="reviews_title"><a href="#">评论 <span>(1)</span></a></div>
                    </div>
                    <div class="description_text">
                        ${product.description!}
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Products -->

    <div class="products">
        <div class="container">
            <div class="row">
                <div class="col text-center">
                    <div class="products_title">相关推介</div>
                </div>
            </div>
            <div class="row">
                <div class="col">

                    <div class="product_grid">

                        <!-- Product -->
                        <div class="product">
                            <div class="product_image"><img src="/online_shop/images/product_1.jpg" alt=""></div>
                            <div class="product_extra product_new"><a href="categories.html">New</a></div>
                            <div class="product_content">
                                <div class="product_title"><a href="product.html">Smart Phone</a></div>
                                <div class="product_price">$670</div>
                            </div>
                        </div>

                        <!-- Product -->
                        <div class="product">
                            <div class="product_image"><img src="/online_shop/images/product_2.jpg" alt=""></div>
                            <div class="product_extra product_sale"><a href="categories.html">Sale</a></div>
                            <div class="product_content">
                                <div class="product_title"><a href="product.html">Smart Phone</a></div>
                                <div class="product_price">$520</div>
                            </div>
                        </div>

                        <!-- Product -->
                        <div class="product">
                            <div class="product_image"><img src="/online_shop/images/product_3.jpg" alt=""></div>
                            <div class="product_content">
                                <div class="product_title"><a href="product.html">Smart Phone</a></div>
                                <div class="product_price">$710</div>
                            </div>
                        </div>

                        <!-- Product -->
                        <div class="product">
                            <div class="product_image"><img src="/online_shop/images/product_4.jpg" alt=""></div>
                            <div class="product_content">
                                <div class="product_title"><a href="product.html">Smart Phone</a></div>
                                <div class="product_price">$330</div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <#include "/online_shop/client/footer.ftl">

<input type="hidden" id="product-id" value=${productId!}>
<input type="hidden" id="attribute-list" value=${attributeList!}>
<input type="hidden" id="product-sku-id">

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
<script src="/js/online_shop/client/product.js"></script>
<script src="/js/online_shop/template-web.js"></script>
<script>
    var baseUrl = "${baseUrl!}";
</script>
<script id="attributeRadio" type="text/html">
    {{each attributeList value index}}
    <#--把数组下标赋给一个临时变量方便第二个循环使用-->
    <#--否则无法直接使用第一个循环的数组下标-->
    <#--这样就实现了两个单选框的name是不相同的-->
    {{set temp = index}}
    <div>
        <div class="availability">{{value.name}}：
            {{each value.value value index}}
            <label class="payment_option clearfix">
                <input type="radio" name="radio{{temp}}" value="{{value}}" onclick="skuDetails()">
                <span>{{value}}</span>
            </label>
            {{/each}}
        </div>
    </div>
    {{/each}}
</script>
</body>
</html>