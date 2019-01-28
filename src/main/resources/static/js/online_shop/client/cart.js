/* JS Document */

/******************************

[Table of Contents]

1. Vars and Inits
2. Set Header
3. Init Search
4. Init Menu
5. Init Quantity


******************************/

$(document).ready(function()
{
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
	initQuantity();

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

	5. Init Quantity

	*/

	function initQuantity() {

		if($('.product_quantity').length) {
			var incButton = $('.quantity_inc');
			var decButton = $('.quantity_dec');

            incButton.on('click', function () {
                var dom = $(this).parent().parent().find('input[class*=quantity_input]');
                var cartItemId = $('#cart-item-id').val();
                var skuId = $(this).parent().find('input[id=sku-id]').val();
                var quantity = parseInt(dom.val()) + 1;

                var param = {};
                param.cartItemId = cartItemId;
                param.skuId = skuId;
                param.quantity = quantity;

                $.ajax({
                    type: 'post',
                    data: param,
                    url: baseUrl + '/baabaa/update_quantity',
                    success: function (response) {
                        //如果数量大于库存数量则不允许增加并弹框提示
                        if (response.code === 1) {
                            alert(response.message);
                            //如果没有超过就更新数量和总价
                        } else {
                            dom.val(quantity);
                            var price = $('#' + skuId).find('span[class*=price]').text();
                            $('#' + skuId).find('span[class*=item-total]').html(price * quantity);
                        }
                    }
                });
                setTotal();
            });

            decButton.on('click', function () {
                var cartItemId = $('#cart-item-id').val();
                var dom = $(this).parent().parent().find('input[class*=quantity_input]');
                var quantity = parseInt(dom.val()) - 1;
                dom.val(quantity);
                if (quantity <= 0) {
                    quantity = 0;
                    dom.val(quantity);
                }
                var skuId = $(this).parent().find('input[id=sku-id]').val();
                var price = $('#' + skuId).find('span[class*=price]').text();
                $('#' + skuId).find('span[class*=item-total]').html(price * quantity);

                setTotal();

                var param = {};
                param.cartItemId = cartItemId;
                param.skuId = skuId;
                param.quantity = quantity;

                $.ajax({
                    type: 'post',
                    data: param,
                    url: baseUrl + '/baabaa/update_quantity',
                    success: function (response) {

                    }
                });
            });
        }
    }
});

function setTotal() {
    var sum = 0;
    $("#cart-items .item-total").each(function () {
        sum += parseInt($(this).text());
    });
    $("#total-price").html(sum);
}