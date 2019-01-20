$(document).ready(function(){
    checkBox();
    update();
});

function checkBox(){
    var selectType = $('#select-type').val();
    //遍历checkBox并与隐藏域里的值进行匹配和勾选
    $("input[name='select-type-options']").each(function() {
        if($(this).val()===selectType){
            $(this).prop("checked",true);
        }
    });
    var inputStatus = $('#input-status').val();
    $("input[name='input-status']").each(function() {
        if($(this).val()===inputStatus){
            $(this).prop("checked",true);
        }
    });
    //下拉框赋值
    var categoryId = $('#category-id').val();
    $("#select-category").val(categoryId);
}

function update(){
    $('#submit').click(function(){
        var attribute = {};
        attribute.id = $('#attribute-id').val();
        attribute.attributeName = $('#product-attribute-name').val();
        attribute.categoryId = $("#select-category").val();
        attribute.selectType = $("input[name='select-type-options']:checked").val();
        attribute.inputStatus = $("input[name='input-status']:checked").val();
        attribute.inputList = $('#input-list').val();
        $.ajax({
            type : 'post',
            dataType: 'json',
            data: JSON.stringify(attribute),
            contentType: 'application/json;charset=UTF-8',
            url : baseUrl + '/update_product_attribute',
            success : function(response){
                $('#result-message').html(response.message);
                $('#result-modal').modal('show');
            }
        })
    })
}