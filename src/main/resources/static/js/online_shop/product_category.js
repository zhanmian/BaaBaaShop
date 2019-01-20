$(document).ready(function () {

    initDataTable();
    subcategory();
    toCreate();
    submit();

    // $('body').on('click', '#result-submit', function(){
    //     window.location.href = baseUrl+"?data-url=article/to_list";
    // });

});

var myEditor;

function initDataTable() {
    $('#product-category-list-table').DataTable({
        "processing": true,
        "searching": false,
        "serverSide": true,
        "lengthChange": false,
        "sort" : false,
        "ajax": {
            "url":baseUrl+"/product_category",
            "type":"post",
            "data":  function(param){
                //从隐藏域获得categoryId的值，作为DataTable的额外参数
                param.categoryId = $('#row-id').val();
            }
        },
        "columns": [
            {"data": "categoryId"},
            {"data": "categoryName"},
            // {"data": "categoryLevel"},
            {"data": "createTime"},
            {
                data: 'adfdfdfdf',
                render: function (data, type, row) {
                    return'<button type="button" class="btn btn-default" id="subcategory" data-id="'+row.categoryId+'">二级分类</button>'
                    +' '+'<button type="button" class="btn btn-default" id="edit" data-id="'+row.categoryId+'">编辑</button>'
                        +' '+'<button type="button" class="btn btn-default" id="delete" data-id="'+row.categoryId+'">删除</button>';
                }
            }],
        language: {
            "sProcessing": "处理中...",
            "sLengthMenu": "显示 _MENU_ 项结果",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        }
    });
}

function subcategory(){
    $('body').on( 'click', '#subcategory', function () {
        var categoryId = $(this).attr("data-id");
        var table = $('#product-category-list-table').DataTable();
        //把categoryId的值赋给隐藏域
        $('#row-id').val(categoryId);
        table.ajax.reload( function ( json ) { } );
    });
}

function toCreate(){
    $('body').on( 'click', '#create', function () {
        $.ajax({
            type: 'post',
            url: baseUrl+"/to_add_product_category",
            success: function (response) {
                $('#content-wrapper').html(response);
                // CKEditor();
            }
        });
    });
}

function handleEdit() {

    $('#article-list-table tbody').on( 'click', '#edit', function () {
        var id = $(this).attr("data-id");
        $.ajax({
            type: 'post',
            data: id,
            url: baseUrl+'/article/to_update?id='+id,
            success: function (response) {
                console.log(response);//控制台输出响应信息
                $('#content-wrapper').html(response);
            }
        });
    });
}
function handleDelete() {

    $('#article-list-table tbody').on( 'click', '#delete', function () {
        var id = $(this).attr("data-id");
        var table = $('#article-list-table').DataTable();

        $('#show-delete-modal').modal('show');

        $('#delete-submit').click(
            function(){
                $.ajax({
                    type: 'post',
                    data: id,
                    url: baseUrl+'/article/delete?id='+id,
                    success: function (response) {
                        $('#show-delete-modal').modal('hide');
                        $('#result-message').html(response.message);
                        $('#result-modal').modal('show');
                        table.ajax.reload();//局部刷新表格
                    }
                });
            }
        );
    });
}

function CKEditor(){
    ClassicEditor
        .create( document.querySelector( '#content' ),{
            language:'zh-cn',//需要引入语言文件
            ckfinder: {
                uploadUrl: baseUrl+'/article/upload/CKImage'
            }
        })
        .then(function(editor){
            myEditor  = editor;
        }).catch(function(error){
        console.error(error);
    });
}
function uploadImage(){

    $("#upload").click(
        function(){
            var formData = new FormData();
            var file = $('#upload-image')[0].files[0];
            formData.append('file', file);
            $.ajax({
                url:baseUrl+'/article/upload',
                data: formData,
                type: 'post',
                cache: false,
                contentType: false,
                processData: false,
                success: function (response) {
                    $('#filePath').val(response.data.filePath);
                    $("#upload-succeed").html("图片上传成功!请点击下方提交按钮提交文章。");
                }
            });
        }
    );
}

function submit(){
    $('#submit').click(function(){

        // var checked=$("input[name='category']:checked");
        //
        if($('#product-category-name').val().length === 0){
            alert('请输入分类名称');
        // }if(checked.length === 0) {
        //     $('#category-message').html('请选择分类！');
        } else{
            create();
        }
    });
}

function create(){
    var productCategory = {};
    productCategory.categoryName = $('#product-category-name').val();
    //下拉框选中取值
    productCategory.pId = $('select  option:selected').val();
    if(productCategory.pId != 0){
        productCategory.level = 1;
    }else{
        productCategory.level = 0;
    }

    $.ajax({
        type: 'post',
        url: baseUrl+'/add_product_category',
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        data:JSON.stringify(productCategory),
        success: function(data){
            $('#result-message').html(data.message);
            $('#result-modal').modal('show');
        }
    });
}

function handleCheckBox(){
    $('.checkbox').find(':checkbox').click(function(){
        var selectedValue = $(this).val();
        //遍历checkbox并把勾选之外的选项设置为false以实现checkbox单选的功能
        $('.checkbox').find(':checkbox').each(function(){
            var value = $(this).val();
            if(value != selectedValue){
                $(this).prop('checked',false);
            }
        });
    });
}




