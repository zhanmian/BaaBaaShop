<!DOCTYPE html>
<html lang="en">
<head>
    <title>分类-${productList.list[0].categoryName!} | BaaBaa Shop.</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Sublime project">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/online_shop/styles/bootstrap4/bootstrap.min.css">
    <link href="/online_shop/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/online_shop/plugins/OwlCarousel2-2.2.1/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="/online_shop/plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
    <link rel="stylesheet" type="text/css" href="/online_shop/plugins/OwlCarousel2-2.2.1/animate.css">
    <link rel="stylesheet" type="text/css" href="/online_shop/styles/categories.css">
    <link rel="stylesheet" type="text/css" href="/online_shop/styles/categories_responsive.css">
</head>
<body>

<div class="super_container">

    <!-- Header -->

    <#include "/online_shop/client/header.ftl">

    <!-- Home -->

    <div class="home">
        <div class="home_container">
            <div class="home_background" style="background-image:url(/online_shop/images/banner.jpg)"></div>
            <div class="home_content_container">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="home_content">
                                <div class="home_title">${productList.list[0].categoryName!}<span>.</span></div>
                                <#--<div class="home_text"><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam a ultricies metus. Sed nec molestie eros. Sed viverra velit venenatis fermentum luctus.</p></div>-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Products -->

    <div class="products">
        <div class="container">
            <div class="row">
                <div class="col">

                    <!-- Product Sorting -->
                    <div class="sorting_bar d-flex flex-md-row flex-column align-items-md-center justify-content-md-start">
                        <div class="results">一共有 <span>${productList.totalRecord!}</span> 件商品</div>
                        <div class="sorting_container ml-md-auto">
                            <div class="sorting">
                                <ul class="item_sorting">
                                    <li>
                                        <span class="sorting_text">排序</span>
                                        <i class="fa fa-chevron-down" aria-hidden="true"></i>
                                        <ul>
                                            <li class="product_sorting_btn" data-isotope-option='{ "sortBy": "original-order" }'><span>默认</span></li>
                                            <li class="product_sorting_btn" data-isotope-option='{ "sortBy": "price" }'><span>价格</span></li>
                                            <li class="product_sorting_btn" data-isotope-option='{ "sortBy": "stars" }'><span>名称</span></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">

                    <div class="product_grid">

                        <!-- Product -->
                        <#list productList.list as item>
                            <div class="product">
                                <div class="product_image">
                                    <a href="${baseUrl}/baabaa/product_details?id=${item.id!}">
                                        <img src="${baseUrl}/downloadImage?filePath=${item.picture!}" alt="">
                                    </a>
                                </div>
                                <div class="product_extra product_new"><a href="categories.html">New</a></div>
                                <div class="product_content">
                                    <div class="product_title">
                                        <a href="${baseUrl}/baabaa/product_details?id=${item.id!}">${item.productName!}</a>
                                    </div>
                                    <div class="product_price">￥${item.productPrice!}</div>
                                </div>
                            </div>
                        </#list>


                    </div>
                    <div class="product_pagination">
                        <ul id="pagination">
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </div>

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
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <#include "/online_shop/client/footer.ftl">

</div>

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
<script src="/js/online_shop/client/categories.js"></script>
</body>
</html>
<script>
    var baseUrl = "${baseUrl}";
    var totalPage = ${productList.totalPage!};
    var currentPage = ${productList.page!};
    var categoryId = ${categoryId!};

    $(document).ready(function(){
        initPagination();
    });

    function initPagination(){
        var p = [];
        var pre = '<li><a href="javascript:search('+(currentPage-1)+');">上一页</a></li>';
        var next = '<li><a href="javascript:search('+(currentPage+1)+');">下一页</a></li>';
        var isDots = 0;

        if (currentPage !== 1) {
            p.push(pre);
        }
        for (var i = 1; i <= totalPage; i++) {
            if (i === currentPage) {
                p.push('<li class="active"><a href="javascript:search(' + i + ');">'+'第'+ i + '页'+  '</a></li>');
            } else {
                //根据条件判断是否显示页码
                if (i < 2 || i === totalPage || i === (currentPage + 1) || i === (currentPage - 1)) {
                    p.push('<li><a href="javascript:search(' + i + ');">' + i + '</a></li>');
                    isDots = 1;
                } else {
                    if (isDots === 1) {
                        p.push('<li>…</li>');
                        isDots = 0;
                    }
                }
            }
        }
        if(currentPage !== totalPage){
            p.push(next);
        }
        $('#pagination').html(p);
    }

    function search(page) {
        window.location.href=baseUrl+'/baabaa/product_category?categoryId='+categoryId+'&page='+page;
    }
</script>