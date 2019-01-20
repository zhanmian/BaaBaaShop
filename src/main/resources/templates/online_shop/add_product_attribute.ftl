<section class="content-header">
    <h1>
        BaaBaa Shop
        <small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>首页</a></li>
        <li class="active">新建商品属性</li>
    </ol>
</section>

<section class="content">

    <input type="hidden" id="category-id" value="${categoryId!}">
    <input type="hidden" id="type" value="${type!}">

    <div class="box box-primary" id="article-create">
        <div class="box-header with-border">
            <h3 class="box-title">新建商品属性</h3>
        </div>
        <div class="box-body">
            <form role="form">
                <div class="form-group">
                    <label>属性名称</label>
                    <input type="text" id="product-attribute-name" class="form-control" placeholder="……">
                </div>
                <div class="form-group">
                    <label>选择分类</label>
                    <select id="select-category" class="form-control">
                        <#list productAttributeCategoryList as item>
                            <option value="${item.id!}">${item.categoryName!}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label>属性是否可选</label>
                    <div class="radio">
                        <label>
                            <input type="radio" name="select-type-options" id="select-type1" value="0" checked>
                            单选
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="select-type-options" id="select-type2" value="1">
                            多选
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label>属性值的录入方式</label>
                    <div class="radio">
                        <label>
                            <input type="radio" name="input-status" id="input-status1" value="0" checked>
                            手动录入
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="input-status" id="input-status2" value="1">
                            从列表加载
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label>属性值可选列表</label>
                    <div class="form-group">
                        <textarea class="form-control" id="input-list" rows="3"></textarea>
                    </div>
                </div>
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

<script src="/js/online_shop/add_product_attribute.js"></script>