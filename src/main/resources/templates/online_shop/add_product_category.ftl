<section class="content-header">
    <h1>
        BaaBaa Shop
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>首页</a></li>
        <li class="active">添加商品分类</li>
    </ol>
</section>

<section class="content">

    <div class="box box-primary" id="article-create">
        <div class="box-header with-border">
            <h3 class="box-title">添加商品分类</h3>
        </div>
        <div class="box-body">
            <form role="form">
                <div class="form-group">
                    <label>分类名称</label>
                    <input type="text" id="product-category-name" class="form-control" placeholder="……">
                </div>
                <div class="form-group">
                    <label>上级分类</label>
                    <select id="select-category" class="form-control">
                        <option value="0">无上级分类</option>
                        <#list parentCategoryList as item>
                            <option value="${item.categoryId!}">${item.categoryName!}</option>
                        </#list>
                    </select>
                </div>
            <#--<div class="form-group">-->
            <#--<label for="exampleInputFile">上传封面</label>-->
            <#--<input type="file" id="upload-image">-->
            <#--</div>-->
            <#--<div class="form-group">-->
            <#--<button type="button" id="upload" class="btn btn-primary">上传</button>-->
            <#--<label id="upload-succeed"></label>-->
            <#--</div>-->
                <#--<div class="form-group">-->
                    <#--<label>商品描述</label>-->
                    <#--<textarea class="form-control" id="description" rows="3" placeholder="……"></textarea>-->
                <#--</div>-->
            <#--<div class="form-group">-->
            <#--<label>分类</label>-->
            <#--<#list categoryList as item>-->
            <#--<div class="checkbox">-->
            <#--<label>-->
            <#--<input type="checkbox" name="category" value="${item.categoryId!}">${item.category!}-->
            <#--</label>-->
            <#--</div>-->
            <#--</#list>-->
            <#--<p id="category-message" style="color:red"></p>-->
            <#--</div>-->
            <#--隐藏文本框以存储图片路径-->
            <#--<input type="hidden" id="filePath">-->
            <#--<input type="hidden" id="userId" value="${adminUser.userId}">-->
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
                <h4 class="modal-title" id="myModalLabel">操作</h4></div>

            <div class="modal-body">
                <p id="result-message"></p>
            </div>

            <div class="modal-footer">
                <button type="button" id="result-submit" class="btn btn-primary" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>

<script src="/js/online_shop/product_category.js"></script>