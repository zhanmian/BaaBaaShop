$(document).ready(function(){
    check();
    checkContent();
});

function check(){
    var categoryId = $('#category-id').val();
    $("#select-category").val(categoryId);
}

function checkContent(){
    $('#submit').click(function() {
        if ($('#product-attribute-name').val().length === 0) {
            alert('请输入属性名称');
        } else {
            create();
        }
    });
}

function create(){
    var attribute = {};
    attribute.attributeName = $('#product-attribute-name').val();
    attribute.categoryId = $('#select-category').val();
    attribute.inputStatus = $("input[name='input-status']:checked").val();
    attribute.selectType = $("input[name='select-type-options']:checked").val();
    attribute.inputList = $('#input-list').val();
    attribute.type = $('#type').val();
    $.ajax({
        type : 'post',
        dataType: 'json',
        data: JSON.stringify(attribute),
        contentType: 'application/json;charset=UTF-8',
        url : baseUrl + '/add_product_attribute',
        success : function(response){
            $('#result-message').html(response.message);
            $('#result-modal').modal('show');
        }
    })
}