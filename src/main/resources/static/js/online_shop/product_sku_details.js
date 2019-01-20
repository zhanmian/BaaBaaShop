$(document).ready(function(){
    handleTable();
});

function handleTable(){
    // var attributeColumns =[];
    // for(var i=0; i<attributeList.length; i++){
    //     attributeColumns.push({title:attributeList[i]});
    // }
    var defaultColumns=[
        {title:"颜色"},
        {title:"尺码"},
        {title:"价格"},
        {title:"库存"},
        {title:"SKU编码"}
    ];
    // var columns = attributeColumns.concat(defaultColumns);
    $('#sku-list-edit-table').DataTable( {
        columns: [
            {"data": "spec1", title:"颜色"},
            {"data": "spec2", title:"尺码"},
            {"data": "skuPrice", title:"价格"},
            {"data": "skuStock", title:"库存"},
            {"data": "skuCode", title:"SKU编码"}
        ],
        "destroy": true,
        "processing": true,
        "searching": false,
        "lengthChange": false,
        "paging": false,
        "info" : false,
        "sort" : false,
        "serverSide" : false,
        "ajax": {
            "url":baseUrl+"/get_sku_details",
            "type":"post",
            "data":  function(param){
                    param.productId = $('#product-id').val();
            }
        },
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
    })
}