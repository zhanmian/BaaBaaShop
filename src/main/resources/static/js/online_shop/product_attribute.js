$(document).ready(function(){
    initDataTable();
    toCreate();
    handleEdit();
    handleCreateButton();
});

function initDataTable() {
    $('#product-attribute-list-table').DataTable({
        "processing": true,
        "searching": false,
        "serverSide": true,
        "lengthChange": false,
        "sort" : false,
        "ajax": {
            "url":baseUrl+"/product_attribute",
            "type":"post",
            "data":  function(param){
                //从隐藏域获得值，作为DataTable的额外参数
                param.categoryId = $('#category-id').val();
                //0规格，1参数，属性的类型
                param.type = $('#type').val();
            }
        },
        "columns": [
            {"data": "id"},
            {"data": "attributeName"},
            {"data": "categoryName"},
            {"data": "inputStatus",
                render : function(data){
                    if(data == "0"){
                       return "手动输入";
                    }else if(data == "1"){
                        return "从列表加载";
                    }else{
                        return data;
                    }
                }},
            {"data": "selectType",
                render : function(data){
                    if(data == "0"){
                        return "单选";
                    }else if(data == "1"){
                        return "多选";
                    }else{
                        return data;
                    }
                }},
            {"data": "inputList"},
            {
                render: function (data, type, row) {
                    return'<button type="button" class="btn btn-default" id="edit" data-id="'+row.id+'">编辑</button>'
                        +' '+'<button type="button" class="btn btn-default" id="delete" data-id="'+row.id+'">删除</button>';
                }
            }
        ],
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

function toCreate(){
    $('#attribute-create').click(function(){
        var param = {};
        param.categoryId = $('#category-id').val();
        param.type = $('#type').val();
        $.ajax({
            type : 'post',
            data : param,
            url : baseUrl + '/to_add_product_attribute',
            success : function(response){
                $('#content-wrapper').html(response);
            }
        })
    })
}

function handleEdit(){
    $('#product-attribute-list-table tbody').on('click', '#edit', function(){
        var id = $(this).attr('data-id');
        $.ajax({
            type : 'post',
            data : id,
            url : baseUrl + '/to_update_product_attribute?id='+id,
            success : function(response){
                $('#content-wrapper').html(response);
            }
        })
    })
}

function handleCreateButton(){
    var type = $('#type').val();
    if(type == 0){
        $('#attribute-create').text(" 添加规格属性");
    }else if(type == 1) {
        $('#attribute-create').text(" 添加参数属性");
    }
}
