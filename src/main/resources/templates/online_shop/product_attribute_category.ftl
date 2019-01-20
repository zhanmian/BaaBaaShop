<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        BaaBaa Shop
        <small>后台管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>首页</a></li>
        <li class="active">商品类型分类列表</li>
    </ol>
</section>

<!-- Main content -->
<section class="content" id="content-section">

    <div class="row">
        <div class="col-xs-12">

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">商品类型分类列表</h3>
                </div>
                <input type="hidden" id="row-id">
                <!-- /.box-header -->
                <div class="box-body">
                    <table id="product-attribute-category-list-table" class="table table-bordered table-striped">
                        <thead>
                        <tr id="table-row">
                            <th>编号</th>
                            <th>类型名称</th>
                            <th>规格属性数量</th>
                            <th>参数属性数量</th>
                            <th>创建时间</th>
                            <th>查看列表</th>
                            <th>操作</th>
                        </tr>
                        </thead>

                        <tbody>
                        <div id="article-list-table_wrapper"
                             class="dataTables_wrapper form-inline dt-bootstrap no-footer">

                            <div class="row">
                            <#--<div class="col-xs-8 pull-right">-->
                            <#--<div id="article-list-table_filter" class="dataTables_filter">-->
                            <#--<label>-->
                            <#--<div class="input-group">-->
                            <#--<input type="text" id="title" class="form-control" placeholder="标题">-->
                            <#--</div>-->
                            <#--<div class="input-group">-->
                            <#--<input type="text" id="content" class="form-control" placeholder="正文">-->
                            <#--</div>-->
                            <#--<button type="button" id="search" class="btn btn-flat"><i class="fa fa-search"></i></button>-->
                            <#--</label>-->
                            <#--</div>-->
                            <#--</div>-->
                            <#--<@shiro.hasPermission name="create article">-->
                                <div class="col-xs-4 pull-left">
                                    <#--<button type="button" id="create" class="btn btn-default form-control fa fa-plus">-->
                                        <#--添加-->
                                    <#--</button>-->
                                    <button class="btn btn-default form-control fa fa-plus" data-toggle="modal"
                                            data-target="#myModal" onclick="return false;">
                                        添加分类
                                    </button>
                                </div>
                                <!-- 模态框（Modal） -->
                                <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                                     aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span></button>
                                                <h4 class="modal-title" id="myModalLabel">
                                                    添加
                                                </h4>
                                            </div>
                                            <div class="modal-body">
                                                <div class="input-group">
                                                    属性分类名：<input type="text" id="product-attribute-category-name"/>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" id="create-submit" class="btn btn-primary">确认</button>
                                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                            </div>
                                        </div><!-- /.modal-content -->
                                    </div><!-- /.modal -->
                            <#--</@shiro.hasPermission>-->
                            </div>

                            <div class="modal fade" id="show-delete-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                                    aria-hidden="true">×</span></button>
                                            <h4 class="modal-title" id="myModalLabel">删除</h4>
                                        </div>

                                        <div class="modal-body">
                                            <div id="submit-message">
                                                <p>确定删除吗？</p>
                                            </div>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                            <button type="button" id="delete-submit" class="btn btn-primary">确定</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

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
                                            <button type="button" id="result-submit" class="btn btn-primary" data-dismiss="modal">确定</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </tbody>

                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
        <!-- /.col -->
    </div>
</section>
<!-- /.content -->

<script src="js/online_shop/product_attribute_category.js"></script>
<script>
    var baseUrl = '${baseUrl}';
</script>