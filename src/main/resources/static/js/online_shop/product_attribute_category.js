$(document).ready(function(){
    initDataTable();
    selectAttribute();
    selectParam();
    create();

    $('body').on('click', '#result-submit', function(){
        window.location.href = baseUrl+"?data-url=to_product_attribute_category";
    });
});

function initDataTable() {
    $('#product-attribute-category-list-table').DataTable({
        "processing": true,
        "searching": false,
        "serverSide": true,
        "lengthChange": false,
        "sort" : false,
        "ajax": {
            "url":baseUrl+"/product_attribute_category",
            "type":"post",
            "data":  function(param){
                //从隐藏域获得categoryId的值，作为DataTable的额外参数
                // param.categoryId = $('#row-id').val();
            }
        },
        "columns": [
            {"data": "id"},
            {"data": "categoryName"},
            {"data": "attributeCount"},
            {"data": "paramCount"},
            {"data": "createTime"},
            {
                data: 'anything',
                render: function (data, type, row) {
                    return'<button type="button" class="btn btn-default" id="attribute-list" data-id="'+row.id+'">规格属性</button>'
                        +' '+'<button type="button" class="btn btn-default" id="param-list" data-id="'+row.id+'">参数属性</button>'
                }
            },
            {
                data: 'anything',
                render: function (data, type, row) {
                    return'<button type="button" class="btn btn-default" id="edit" data-id="'+row.id+'">编辑</button>'
                        +' '+'<button type="button" class="btn btn-default" id="delete" data-id="'+row.id+'">删除</button>';
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

function selectAttribute(){
    $('#product-attribute-category-list-table tbody').on( 'click', '#attribute-list', function () {
        var param = {};
        param.categoryId = $(this).attr('data-id');
        param.type = 0;
        $.ajax({
            type: 'post',
            data: param,
            url: baseUrl + '/to_product_attribute',
            success: function(response){
                $('#content-wrapper').html(response);
            }
        })
    })
}

function selectParam(){
    $('#product-attribute-category-list-table tbody').on( 'click', '#param-list', function () {
        var param = {};
        param.categoryId = $(this).attr('data-id');
        param.type = 1;
        $.ajax({
            type: 'post',
            data: param,
            url: baseUrl + '/to_product_attribute',
            success: function(response){
                $('#content-wrapper').html(response);
            }
        })
    })
}

function create(){
    $('#create-submit').click(function(){
        var categoryName = $('#product-attribute-category-name').val();
        $.ajax({
            type : 'post',
            data : categoryName,
            url : baseUrl + '/add_product_attribute_category?categoryName='+categoryName,
            success : function(response){
                $('#result-message').html(response.message);
                $('#result-modal').modal('show');
            }
        })
    })


}
