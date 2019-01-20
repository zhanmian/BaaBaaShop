$(document).ready(function(){
    CKEditor();
    handleAttributeChange();
    refreshTable();
    submit();
    uploadPicture();

    $('body').on('click', '#result-submit', function(){
        window.location.href = baseUrl+"?data-url=to_add_product";
    });
});

var myEditor;
var addAttributeValue = [];

function CKEditor(){
    ClassicEditor
        .create( document.querySelector( '#description' ),{
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

function submit(){
    $('#submit').click(function(){
        var product = {};
        product.categoryId = $('#select-product-category').val();
        product.productName = $('#product_name').val();
        product.productCode = $('#product_code').val();
        product.productPrice = $('#product_price').val();
        product.description = myEditor.getData();
        product.attributeCategoryId = $('#select-category').val();
        product.picture = $('#picture-path').val();

        var table = $('#product-sku-list-table').DataTable();
        //获取DataTables的所有input输入框的值
        var data = table.$('input').serializeArray();
        var formData = JSON.parse(JSON.stringify(data));
        //获取DataTables的所有行数据
        var rowsData = table.rows().data();
        var list = [];
        for(var i=0; i<formData.length; i=i+3){
            list.push(
                {price: formData[i].value,
                stock: formData[i+1].value,
                skuCode: formData[i+2].value}
            )
        }
        var skuList = [];
        //拼接JSON
        for(var a=0; a<rowsData.length; a++){
            //判断spec3是否存在
            if(rowsData[a][2]==null){
                skuList.push([
                    {spec1: rowsData[a][0],
                     spec2: rowsData[a][1],
                     price: list[a].price,
                     stock: list[a].stock,
                     skuCode: list[a].skuCode}
                ]);
            }else{
                skuList.push([
                    {spec1: rowsData[a][0],
                     spec2: rowsData[a][1],
                     spec3: rowsData[a][2],
                     price: list[a].price,
                     stock: list[a].stock,
                     skuCode: list[a].skuCode}
                ]);
            }
        }
        product.skuList = skuList;
        product.addAttributeValue = addAttributeValue;
        $.ajax({
            type:'post',
            dataType: 'json',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(product),
            url: baseUrl + '/add_product',
            success: function(response){
                $('#result-message').html(response.message);
                $('#result-modal').modal('show');
            }
        })
    });
}

// function checkNumber(){
//     var price = $('#product_price').val();
//     var productCode = $('#product_code').val();
//
//     if(!isNaN(price) || !isNaN(productCode)){
//         alert('请输入数字格式的价格或编码');
//     }
// }

function handleAttributeChange(){
    var param = {};
    param.categoryId = $('#select-category').val();
    param.type = 0;
    $.ajax({
        type:'post',
        data:param,
        url:baseUrl+'/get_product_attribute',
        success:function(response){
            //一旦重新选择属性分类就清空手动输入的属性值数组
            addAttributeValue = [];
            buildCheckbox(response);

            //首次加载不能销毁表格，因为表格还没有初始化，所以只能硬编码
            if($('#select-category').val()==1){
                handleTable(response.attributeList);
            }else{
                $('#product-sku-list-table').DataTable().clear();
                $('#product-sku-list-table').DataTable().destroy();
                $('#product-sku-list-table').empty();
                handleTable(response.attributeList);
            }
        }
    });
}

//属性分类选择一旦改变就重新渲染多选框，使用art-Template
function buildCheckbox(response){
    var attributeList = response.attributeList;
    var attributeIdList = response.attributeIdList;
    var inputList = response.inputList;
    var inputStatus = response.inputStatus;
    var newInputList = [];
    for(var i=0; i<inputList.length; i++) {
        //规格属性值去掉逗号转换成数组，然后在art-Template里遍历
        newInputList.push(inputList[i].split(","));
    }
    var data = {
        attributeList : attributeList,
        attributeIdList : attributeIdList,
        inputList : newInputList,
        inputStatus : inputStatus
    };
    var attributeHTML = template("attribute-checkbox",data);
    $('#attribute').html(attributeHTML);
}

//手动输入规格，用art-Template渲染
function addAttribute(index, attributeId){
    var newAttribute = $('#attribute-input').val();
    //如果是同样的规格属性ID就把它们的规格属性名称拼接在一起，
    // 这样写入数据库的时候就不用一条条分别插入
    if(addAttributeValue.length == 0){
        addAttributeValue.push({
            attributeName : newAttribute,
            attributeId : attributeId
        });
    }else{
        for(var i = 0; i < addAttributeValue.length; i ++){
            var obj = addAttributeValue[i];
            if(obj.attributeId == attributeId){
                obj.attributeName = obj.attributeName +','+ newAttribute;
            }else{
                addAttributeValue.push({
                    attributeName : newAttribute,
                    attributeId : attributeId
                });
            }}
    }

    if(newAttribute.length!=0){
        var data = {
            newAttribute : newAttribute,
            index : index
        };
        var attributeHTML = template("new-attribute-checkbox",data);
        $('#input-checkbox').append(attributeHTML);
    }
}
//规格多选框选择后刷新表格
function refreshTable(){
    $('#refresh-table').click(function(){
        var checkedList1 = [];
        var checkedList2 = [];
        var checkedList3 = [];
        var list = [];
        $('input[name="attribute-value0"]:checked').each(function() {
            checkedList1.push($(this).val());
        });
        $('input[name="attribute-value1"]:checked').each(function() {
            checkedList2.push($(this).val());
        });
        $('input[name="attribute-value2"]:checked').each(function() {
            checkedList3.push($(this).val());
        });
        for (var a = 0; a < checkedList1.length; a++) {
            for (var b = 0; b < checkedList2.length; b++) {
                if(checkedList3.length===0){
                    var d = [];
                    d.push(checkedList1[a], checkedList2[b]);
                    list.push(d);
                    continue;
                }
                for (var c = 0; c < checkedList3.length; c++) {
                    var e = [];
                    e.push(checkedList1[a], checkedList2[b], checkedList3[c]);
                    list.push(e);
                }
            }
        }
        var table = $('#product-sku-list-table').DataTable();
        table.clear();
        table.rows.add(list).draw(false);
    });
}

// function handleDelete() {
//     $('#product-sku-list-table tbody').on( 'click', '#delete', function () {
//         var table = $('#product-sku-list-table').DataTable();
//         // var index = this.rowIndex;
//         // table.row($(this).parents('tr')).remove().draw();
//         // table.row(index).remove().draw();
//         var index = $(this).attr('data-id');
//         alert(index);
//         table.row(index).remove().draw();
//     })
// }

function handleTable(attributeList){//属性分类选择一旦改变就重新渲染表格（先销毁原表格）
    var attributeColumns =[];
    for(var i=0; i<attributeList.length; i++){
        attributeColumns.push({title:attributeList[i]});
    }
    var defaultColumns=[
        {title:"价格",
            render: function (data, type, row) {
                return '<input type="text" name="price" value="" class="form-control">';
            }},
        {title:"库存",
            render: function (data, type, row) {
                return '<input type="text" name="stock" value="" class="form-control">';
            }},
        {title:"SKU编码",
            render: function (data, type, row) {
                return '<input type="text" name="skuCode" value="" class="form-control">';
            }},
        {title:"操作",
            render: function (data, type, row) {
                return '<button type="button" class="btn btn-default" id="delete" data-id="'+row.index+'">删除</button>';
            }
        }
    ];
    var columns = attributeColumns.concat(defaultColumns);
    $('#product-sku-list-table').DataTable( {
        columns: columns,
        "destroy": true,
        "processing": true,
        "searching": false,
        "lengthChange": false,
        "paging": false,
        "info" : false,
        "sort" : false,
        "serverSide" : false,
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
function uploadPicture(){
    'use strict';//表示强规则
    var $image = $('#image');
    var $inputImage = $('#inputImage');
    var URL = window.URL || window.webkitURL;
    var uploadedImageURL;
    //获取图片截取的位置
    var $dataX = $('#dataX');
    var $dataY = $('#dataY');
    var $dataHeight = $('#dataHeight');
    var $dataWidth = $('#dataWidth');
    var $dataRotate = $('#dataRotate');
    var $dataScaleX = $('#dataScaleX');
    var $dataScaleY = $('#dataScaleY');
    var options = {
        aspectRatio: 1 / 1, //裁剪框比例1:1
        preview: '.img-preview',
        crop: function (e) {
            $dataX.val(Math.round(e.x));
            $dataY.val(Math.round(e.y));
            $dataHeight.val(Math.round(e.height));
            $dataWidth.val(Math.round(e.width));
            $dataRotate.val(e.rotate);
            $dataScaleX.val(e.scaleX);
            $dataScaleY.val(e.scaleY);
        }
    };
    // Cropper
    $image.on({
        ready: function (e) {
            // console.log(e.type);
        },
        cropstart: function (e) {
            // console.log(e.type, e.action);
        },
        cropmove: function (e) {
            // console.log(e.type, e.action);
        },
        cropend: function (e) {
            // console.log(e.type, e.action);
        },
        crop: function (e) {
            // console.log(e.type, e.x, e.y, e.width, e.height, e.rotate, e.scaleX, e.scaleY);
        },
        zoom: function (e) {
            // console.log(e.type, e.ratio);
        }
    }).cropper(options);

    var file;

    if (URL) {
        $inputImage.change(function () {
            var files = this.files;

            if (!$image.data('cropper')) {
                return;
            }

            if (files && files.length) {
                file = files[0];

                if (/^image\/\w+$/.test(file.type)) {
                    if (uploadedImageURL) {
                        URL.revokeObjectURL(uploadedImageURL);
                    }

                    uploadedImageURL = URL.createObjectURL(file);
                    $image.cropper('destroy').attr('src', uploadedImageURL).cropper(options);
                    $inputImage.val('');
                } else {
                    window.alert('Please choose an image file.');
                }
            }
        });
    } else {
        $inputImage.prop('disabled', true).parent().addClass('disabled');
    }

    $('#upload').click(
        function(){
            $image.cropper("getCroppedCanvas",
                {
                    width: 690,
                    height: 690 //设置长宽避免文件体积过大
                })
                .toBlob(function(blob){

                    var formData = new FormData();
                    formData.append('file', blob, file.name);
                    console.log(formData);

                    $.ajax({
                        method: 'post',
                        data: formData,
                        processData: false,
                        contentType: false,
                        url: baseUrl+'/upload_picture',
                        success: function (response) {
                            $('#picture-path').val(response.data.filePath);
                            $('#upload-avatar-result').html('(●\'◡\'●)ﾉ图片上传成功！');
                        }
                    });
                });
        });
}

