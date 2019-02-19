/* JS Document */

/******************************

[Table of Contents]

1. Vars and Inits
2. Set Header
3. Init Search
4. Init Menu
5. Init Image
6. Init Quantity
7. Init Isotope


******************************/

$(document).ready(function()
{
	buildRadio();

	"use strict";

	/* 

	1. Vars and Inits

	*/

	var header = $('.header');
	var hambActive = false;
	var menuActive = false;

	setHeader();

	$(window).on('resize', function()
	{
		setHeader();
	});

	$(document).on('scroll', function()
	{
		setHeader();
	});

	initSearch();
	initMenu();
	initImage();
	initQuantity();
	initIsotope();

	/* 

	2. Set Header

	*/

	function setHeader()
	{
		if($(window).scrollTop() > 100)
		{
			header.addClass('scrolled');
		}
		else
		{
			header.removeClass('scrolled');
		}
	}

	/* 

	3. Init Search

	*/

	function initSearch()
	{
		if($('.search').length && $('.search_panel').length)
		{
			var search = $('.search');
			var panel = $('.search_panel');

			search.on('click', function()
			{
				panel.toggleClass('active');
			});
		}
	}

	/* 

	4. Init Menu

	*/

	function initMenu()
	{
		if($('.hamburger').length)
		{
			var hamb = $('.hamburger');

			hamb.on('click', function(event)
			{
				event.stopPropagation();

				if(!menuActive)
				{
					openMenu();
					
					$(document).one('click', function cls(e)
					{
						if($(e.target).hasClass('menu_mm'))
						{
							$(document).one('click', cls);
						}
						else
						{
							closeMenu();
						}
					});
				}
				else
				{
					$('.menu').removeClass('active');
					menuActive = false;
				}
			});

			//Handle page menu
			if($('.page_menu_item').length)
			{
				var items = $('.page_menu_item');
				items.each(function()
				{
					var item = $(this);

					item.on('click', function(evt)
					{
						if(item.hasClass('has-children'))
						{
							evt.preventDefault();
							evt.stopPropagation();
							var subItem = item.find('> ul');
						    if(subItem.hasClass('active'))
						    {
						    	subItem.toggleClass('active');
								TweenMax.to(subItem, 0.3, {height:0});
						    }
						    else
						    {
						    	subItem.toggleClass('active');
						    	TweenMax.set(subItem, {height:"auto"});
								TweenMax.from(subItem, 0.3, {height:0});
						    }
						}
						else
						{
							evt.stopPropagation();
						}
					});
				});
			}
		}
	}

	function openMenu()
	{
		var fs = $('.menu');
		fs.addClass('active');
		hambActive = true;
		menuActive = true;
	}

	function closeMenu()
	{
		var fs = $('.menu');
		fs.removeClass('active');
		hambActive = false;
		menuActive = false;
	}

	/* 

	5. Init Image

	*/

	function initImage()
	{
		var images = $('.details_image_thumbnail');
		var selected = $('.details_image_large img');

		images.each(function()
		{
			var image = $(this);
			image.on('click', function()
			{
				var imagePath = new String(image.data('image'));
				selected.attr('src', imagePath);
				images.removeClass('active');
				image.addClass('active');
			});
		});
	}

	/* 

	6. Init Quantity

	*/

	function initQuantity()
	{
		// Handle product quantity input
		if($('.product_quantity').length)
		{
			var input = $('#quantity_input');
			var incButton = $('#quantity_inc_button');
			var decButton = $('#quantity_dec_button');

			var originalVal;
			var endVal;

			incButton.on('click', function()
			{
				originalVal = input.val();
				endVal = parseFloat(originalVal) + 1;
				input.val(endVal);
			});

			decButton.on('click', function()
			{
				originalVal = input.val();
				if(originalVal > 0)
				{
					endVal = parseFloat(originalVal) - 1;
					input.val(endVal);
				}
			});
		}
	}

	/* 

	7. Init Isotope

	*/

	function initIsotope()
	{
		var sortingButtons = $('.product_sorting_btn');
		var sortNums = $('.num_sorting_btn');

		if($('.product_grid').length)
		{
			var grid = $('.product_grid').isotope({
				itemSelector: '.product',
				layoutMode: 'fitRows',
				fitRows:
				{
					gutter: 30
				},
	            getSortData:
	            {
	            	price: function(itemElement)
	            	{
	            		var priceEle = $(itemElement).find('.product_price').text().replace( '$', '' );
	            		return parseFloat(priceEle);
	            	},
	            	name: '.product_name',
	            	stars: function(itemElement)
	            	{
	            		var starsEle = $(itemElement).find('.rating');
	            		var stars = starsEle.attr("data-rating");
	            		return stars;
	            	}
	            },
	            animationOptions:
	            {
	                duration: 750,
	                easing: 'linear',
	                queue: false
	            }
	        });
		}
	}

});

function addCart(){

    var spec1 = $("input[type='radio'][name='radio0']:checked").val();
    var spec2 = $("input[type='radio'][name='radio1']:checked").val();
    var spec3 = $("input[type='radio'][name='radio2']:checked").val();

    var attributeName1 = $("#attribute-name0").text();
    var attributeName2 = $("#attribute-name1").text();
    var attributeName3 = $("#attribute-name2").text();

    var productAttribute = [];
    var param = {};
    param.productId = $('#product-id').val();
    param.skuId = $('#product-sku-id').val();
    param.quantity = $('#quantity_input').val();
    if(spec3){
        param.spec3 = spec3;
        productAttribute.push(
            {key: attributeName1, value: spec1},
            {key: attributeName2, value: spec2},
            {key: attributeName3, value: spec3}
        );
    }else{
        productAttribute.push(
            {key: attributeName1, value: spec1},
            {key: attributeName2, value: spec2}
        );
	}
	console.log(productAttribute);
    param.productAttribute = JSON.stringify(productAttribute);

    $.ajax({
        type : 'post',
        data : param,
        url : baseUrl + '/baabaa/add_cart',
        success : function(response){
            alert(response.message);
        }
    })
}

function buildRadio(){
    //把JSON字符串解析为JSON对象
	var attributeList = JSON.parse($('#attribute-list').val());
	for(var i=0; i<attributeList.length; i++){
	    //把规格属性值去掉逗号转为数组并重新赋值
        attributeList[i].value = attributeList[i].value.split(",");
	}
	var data = {
        attributeList : attributeList
	};
	var html = template("attributeRadio", data);
	$('#attribute-container').html(html);
}

function skuDetails(){
    var productId = $('#product-id').val();

    var spec1 = $("input[type='radio'][name='radio0']:checked").val();
    var spec2 = $("input[type='radio'][name='radio1']:checked").val();
    var spec3 = $("input[type='radio'][name='radio2']:checked").val();

    //两个规格属性中只要有一个是空的就返回
    if(!spec3){
        if(!spec1 || !spec2){
            return;
        }
    }else{
        if(!spec1 || !spec2 || !spec3){
            return;
        }
    }

    var param = {};
    param.spec1 = spec1;
    param.spec2 = spec2;
    param.productId = productId;

    if(spec3){
        param.spec3 = spec3;
    }

    $.ajax({
        type : 'post',
        data : JSON.stringify(param),
        dataType : 'json',
        contentType : 'application/json;charset=utf8',
        url : baseUrl + '/baabaa/get_sku_details',
        success : function(response){
            if(response.skuStock <= 0){
                $('#add-cart').text("暂无库存");
                $('#availability').html("现在无货");
            }else{
                $('#add-cart').text("加入购物车");
                $('#availability').html("现在有货");
            }
            $('.details_price').html('￥'+ response.skuPrice);
            $('#product-sku-id').val(response.id);
        }
    })


}