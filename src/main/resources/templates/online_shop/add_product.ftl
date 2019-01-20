<link rel="stylesheet" href="/cropper/cropper.css" type="text/css">
<style>
    img {
        max-width: 100%; /* This rule is very important, please do not ignore this! */
    }
</style>

<section class="content-header">
    <h1>
        BaaBaa Shop
        <small>后台管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>首页</a></li>
        <li class="active">添加商品</li>
    </ol>
</section>

<section class="content">

    <div class="box box-primary" id="article-create">
        <div class="box-header with-border">
            <h3 class="box-title">添加商品</h3>
        </div>
        <p id="content"></p>
        <div class="box-body">
            <form role="form">
                <div class="form-group">
                    <label>选择商品分类</label>
                    <select id="select-product-category" class="form-control">
                        <#list productCategoryList as item>
                            <option value="${item.categoryId!}">${item.categoryName!}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label>商品名称</label>
                    <input type="text" id="product_name" class="form-control" placeholder="……">
                </div>
                <div class="form-group">
                    <label>商品货号</label>
                    <input type="text" id="product_code" class="form-control" placeholder="……">
                </div>
                <div class="form-group">
                    <label>商品价格</label>
                    <input type="text" id="product_price" class="form-control" placeholder="……">
                </div>
                <div class="form-group">
                    <label>商品描述</label>
                    <textarea class="form-control" id="description" rows="3" placeholder="……"></textarea>
                </div>

                <div class="form-group has-feedback">
                    <label>上传图片</label>
                    <div class="img-container" style="width: 30%">
                        <img id="image" src="/AdminLTE/images/fuji.jpg">
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label class="btn btn-primary fa fa-file-image-o" for="inputImage" title="Upload image file">
                        <input type="file" class="sr-only" id="inputImage" name="file" accept=".jpg,.jpeg,.png,.gif,.bmp,.tiff">
                        <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="Import image with Blob URLs">
                        选择图片
                        </span>
                    </label>
                    <div class="btn-group btn-group-crop">
                        <button class="btn btn-primary fa fa-upload" id="upload" data-method="getCroppedCanvas" type="button">
                            上传
                        </button>
                    </div>
                    <label id="upload-avatar-result"></label><#--显示上传结果-->
                </div>

                <input type="hidden" id="picture-path"><#--隐藏域存储图片路径-->

                <div class="form-group">
                    <label>选择属性分类</label>
                    <select id="select-category" class="form-control"
                            onchange="handleAttributeChange()">
                        <#list productAttributeCategoryList as item>
                            <option value="${item.id!}">${item.categoryName!}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label>商品规格</label>
                </div>
                <div id="attribute" class="form-group"></div>
                <table id="product-sku-list-table" class="table table-bordered table-striped">
                    <thead></thead>
                    <tbody></tbody>
                </table>
                <button type="button" class="btn btn-primary" id="refresh-table">刷新列表</button>
            </form>
        </div>

        <div class="box-footer">
            <button type="button" id="submit" class="btn btn-primary">提交</button>
        </div>
    </div>
</section>
<div class="modal fade" id="result-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel">操作结果</h4></div>

            <div class="modal-body">
                <p id="result-message"></p>
            </div>

            <div class="modal-footer">
                <button type="button" id="result-submit" class="btn btn-primary">确定</button>
            </div>
        </div>
    </div>
</div>

<script src="/AdminLTE/js/ckeditor.js"></script>
<script src="/js/online_shop/add_product.js"></script>
<script src="/js/online_shop/template-web.js"></script>
<script type="text/javascript" src="/cropper/cropper.js"></script>
<script id="attribute-checkbox" type="text/html">
    {{each attributeList value index}}
    <label>{{value}}：</label>
    {{if inputStatus[index] == 1}}
    <div class="checkbox">
        {{each inputList[index]}}
        <label>
            <input type="checkbox" name="attribute-value{{index}}" value="{{$value}}">
            {{$value}}
        </label>
        {{/each}}
    </div>
    {{else if inputStatus[index] == 0}}
    <div class="form-group">
        <div id="input-checkbox" class="checkbox"></div>
        <input type="text" id="attribute-input">
        <button type="button" id="attribute-input-submit" onclick="addAttribute({{index}}, {{attributeIdList[index]}})" class="btn btn-xs btn-primary">新增</button>
    </div>
    {{/if}}
    {{/each}}
</script>
<script id="new-attribute-checkbox" type="text/html">
    <label>
        <input type="checkbox" name="attribute-value{{index}}" value="{{newAttribute}}">
        {{newAttribute}}
    </label>
</script>