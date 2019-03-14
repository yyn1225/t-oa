<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12 list">
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <h2>
                <dic data-dic="header.list.search"></dic>
            </h2>
        </header>
        <div>
            <div class="widget-body no-padding">
                <form class="smart-form">
                    <fieldset style="height: 100px !important;">
                        <div class="row">
                            <label class="col col-1 text-align-right"
                                   style="padding-top: 7px; "><dic data-dic="file.list.table.type"></dic>:</label>
                            <label class="col col-2 input">
                                <select class="select2" id="search_file_category" style="width:
                                100%;">
                                    <option value="0" selected data-dic="file.list.item.all"></option>
                                    <option value="1" data-dic="file.list.security.NewsAndActivities"></option>
                                    <option value="2" data-dic="file.list.security.Product"></option>
                                    <option value="3" data-dic="file.list.security.Solution"></option>
                                    <option value="4" data-dic="file.list.security.Brand"></option>
                                    <option value="5" data-dic="file.list.security.anli"></option>
                                </select>
                            </label>
                            <label class="col col-1 text-align-right"
                                   style="padding-top: 7px; "><dic data-dic="file.list.item.use"></dic>:</label>
                            <label class="col col-2 input">
                                <div class="dropdown dropdown-large dp">
                                    <input type="text" id="search_file_package"
                                           data-toggle="dropdown" readonly/>
                                    <ul class="dropdown-menu dropdown-menu-large row "
                                        style="margin: 0 !important;padding: 0!important;">
                                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                            <div class="input-group">
                                                <input class="form-control"
                                                       id="search_folder_tree_input" placeholder=""
                                                       type="text">
                                                <span class="input-group-addon"
                                                      id="search_folder_tree_input_btn">
                                                    <i class="fa fa-search"></i>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="ztree" id="folder_tree"
                                             style="height: 200px;overflow-y: auto;"></div>
                                    </ul>
                                </div>
                            </label>
                            <label class="col col-1 text-align-right"
                                   style="padding-top: 7px; "><dic data-dic="panel.list.series"></dic>:</label>
                            <label class="col col-2 input">
                                <div class="dropdown dropdown-large ds">
                                    <input class="form-control "
                                           data-toggle="dropdown" readonly
                                           id="search_file_series"
                                           aria-expanded="true"
                                           placeholder=""
                                           type="text">
                                    <ul class="dropdown-menu dropdown-menu-large row "
                                        style="margin: 0px 0px 0px 0px !important;padding: 0
                                        !important;">
                                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                            <div class="input-group">
                                                <input class="form-control"
                                                       id="search_series_tree_input"
                                                       placeholder=""
                                                       type="text">
                                                <span class="input-group-addon"
                                                      id="search_series_tree_input_btn">
                                                    <i class="fa fa-search"></i>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="ztree" id="series_ztree"
                                             style="height: 200px;overflow-y: scroll;"></div>
                                    </ul>
                                </div>
                            </label>
                            <label class="col col-1 pull-right">
                                <button type="button" class="btn btn-primary query-btn rest">
                                    <dic data-dic="button.reset"></dic>
                                </button>
                            </label>
                            <label class="col col-1 pull-right">
                                <a class="btn btn-primary query-btn" id="btn_query">
                                    <dic data-dic="header.list.button.search"></dic>
                                </a>
                            </label>
                        </div>
                        <div class="row" style="margin-top: 15px">
                            <label class="col col-1 text-align-right"
                                   style="padding-top: 7px; "><dic data-dic="file.list.table.extend"></dic>:</label>
                            <label class="col col-2 input">
                                <div class="dropdown dropdown-large dp">
                                    <input type="text" id="search_extend_file_package" readonly/>

                                </div>
                            </label>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>

    </div>
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <h2>
                <dic data-dic="file.list.tableName"></dic>
            </h2>
            <span class="pull-right">
                <a class="btn btn-primary btn-xs add-file" data-id="0">
                    <dic data-dic="header.list.button.add"></dic>
                </a>
                <#--<a class="btn btn-primary btn-xs" id="security-batch">-->
                    <#--<dic data-dic="body.list.authorize"></dic>-->
                <#--</a>-->
                 <a class="btn btn-primary btn-xs" id="security-delete">
                    <dic data-dic="body.list.delete"></dic>
                </a>
                 <#--<a class="btn btn-primary btn-xs" id="security-edit">-->
                    <#--<dic data-dic="body.list.edit"></dic>-->
                <#--</a>-->
            </span>
        </header>
        <div>
            <div class="widget-body no-padding">
                <table id="dt_basic_file" class="table table-striped table-bordered table-hover"
                       width="100%">
                    <thead>
                    <tr>
                        <th></th>
                        <th></th>
                        <th>
                            <dic data-dic="file.list.table.name"></dic>
                        </th>
                        <th>
                            <dic data-dic="file.list.table.extend"></dic>
                        </th>
                        <th>
                            <dic data-dic="file.list.table.size"></dic>
                        </th>
                        <th>
                            <dic data-dic="file.list.table.uploadTime"></dic>
                        </th>
                        <th>
                            <dic data-dic="product.spare.list.operate"></dic>
                        </th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</article>

<!-- 模态框（Modal） -->
<div class="modal fade" id="del_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width:350px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    <dic data-dic="admin.window.attention"></dic>
                </h4>
            </div>
            <div class="modal-body">
                <dic data-dic="prompt.message.delete"></dic>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="ok_del">
                    <dic data-dic="button.confirm"></dic>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="del_all_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width:350px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    <dic data-dic="admin.window.attention"></dic>
                </h4>
            </div>
            <div class="modal-body">
                <dic data-dic="prompt.message.delete"></dic>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="ok_del_all">
                    <dic data-dic="button.confirm"></dic>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="folder_item" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times
                </button>
                <h4 class="modal-title" id="myModalLabel">
                </h4>
            </div>
            <div class="modal-body" style="height: 210px;" id="folder_item_html">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="ok_sumbit">
                    <dic data-dic="button.confirm"></dic>
                </button>
            </div>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<!-- 模态框（Modal） -->
<div class="modal fade" id="file_item" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times
                </button>
                <h4 class="modal-title" id="myModalLabel">

                </h4>
            </div>
            <div class="modal-body" id="file_item_html">
            </div>
            <div class="modal-footer" style="padding: 5px !important;">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="file_ok_sumbit">
                    <dic data-dic="button.confirm"></dic>
                </button>
            </div>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->
<div class="modal fade" id="file_extend" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times
                </button>
                <h4 class="modal-title" id="myModalLabel">

                </h4>
            </div>
            <div class="modal-body" id="file_extend_html">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 right" style="margin-top: 20px;">
                        <div class="btn-group" data-toggle="buttons">
                            <label class="btn   btn-default extend_btn">
                                <input type="checkbox" data-extend="pdf"> pdf
                            </label>
                            <label class="btn btn-default extend_btn">
                                <input type="checkbox"  data-extend="pptx"> pptx
                            </label> <label class="btn btn-default extend_btn">
                                <input type="checkbox"  data-extend="ppt"> ppt
                            </label>
                            <label class="btn btn-default extend_btn" id="extrnd_test">
                                <input type="checkbox"  data-extend="png"> png
                            </label><label class="btn btn-default extend_btn" id="extrnd_test">
                                <input type="checkbox"  data-extend="jpg"> jpg
                            </label><label class="btn btn-default extend_btn" id="extrnd_test">
                                <input type="checkbox"  data-extend="jpeg"> jpeg
                            </label><label class="btn btn-default extend_btn" id="extrnd_test">
                                <input type="checkbox"  data-extend="gif"> gif
                            </label><label class="btn btn-default extend_btn" id="extrnd_test">
                                <input type="checkbox"  data-extend="doc"> doc
                            </label><label class="btn btn-default extend_btn" id="extrnd_test">
                                <input type="checkbox"  data-extend="docx"> docx
                            </label><label class="btn btn-default extend_btn" id="extrnd_test">
                                <input type="checkbox"  data-extend="xls"> xls
                            </label><label class="btn btn-default extend_btn" id="extrnd_test">
                                <input type="checkbox"  data-extend="xlsx"> xlsx
                            </label><label class="btn btn-default extend_btn" id="extrnd_test">
                                <input type="checkbox"  data-extend="video"> video
                            </label><label class="btn btn-default extend_btn" id="extrnd_test">
                                <input type="checkbox"  data-extend="link"> link
                            </label>
                        </div>
                    </div>
                </div>

            </div>
            <div class="modal-footer" style="padding: 5px !important;">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="file_extend_ok_sumbit">
                    <dic data-dic="button.confirm"></dic>
                </button>
            </div>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<!-- 模态框（Modal） -->
<div class="modal fade" id="file_edit" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times
                </button>
                <h4 class="modal-title" id="myModalLabel">

                </h4>
            </div>
            <div class="modal-body" id="file_item_edit_html">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="file_edit_ok_sumbit">
                    <dic data-dic="button.confirm"></dic>
                </button>
            </div>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<!-- 模态框（Modal） -->
<div class="modal fade" id="file_edit_all" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times
                </button>
                <h4 class="modal-title" id="myModalLabel">

                </h4>
            </div>
            <div class="modal-body" id="file_item_edit_all_html">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="file_edit_all_ok_sumbit">
                    <dic data-dic="button.confirm"></dic>
                </button>
            </div>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->
<!-- 模态框（Modal） -->
<div class="modal fade" id="file_move" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 300px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    <dic data-dic="folder.nestable.treeName"></dic>
                </h4>
            </div>
            <div class="modal-body">
                <div class="widget-body-toolbar">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <div class="input-group">
                                <input class="form-control" id="search_folder_tree_input"
                                       placeholder=""
                                       type="text">
                                <span class="input-group-addon" id="search_folder_tree_input_btn">
                                    <i class="fa fa-search"></i></span>
                            </div>
                        </div>
                    </div>
                </div>
                <input type="hidden" id="update_folder_file_id"/>
                <input type="hidden" id="update_folder_file_package_id"/>
            </div>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<!-- 模态框（Modal） -->
<div class="modal fade" id="security_item" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times
                </button>
                <h4 class="modal-title" id="myModalLabel">

                </h4>
            </div>
            <div class="modal-body" id="security_item_html">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="security_btn">
                    <dic data-dic="button.confirm"></dic>
                </button>
            </div>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<!-- 模态框（Modal） -->
<div class="modal fade" id="security_batch_modal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times
                </button>
                <h4 class="modal-title" id="myModalLabel">

                </h4>
            </div>
            <div class="modal-body" id="security_batch_html">
                <div class="row" style="margin-top: 20px;">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="jarviswidget" data-widget-editbutton="false" style="margin-bottom: 0 !important;">
                            <header>
                                <h2>
                                    <dic data-dic="file.list.item.give"></dic>
                                </h2>
                                <a class="btn btn-primary btn-xs" style="float: right" id="selectAll">
                                    <dic data-dic="file.list.item.selectAll"></dic>
                                </a>
                            </header>
                            <div>
                                <div class="widget-body no-padding smart-form">
                                    <ul style="overflow-y: auto;height: 200px;list-style: none;padding-left: 20px;padding-top: 20px" id="roleBatch">
                                        <#list roles as item>
                                            <li style="margin-bottom: 5px" class="check_group">
                                                <label class="checkbox option block mn">
                                                    <input class="role_check" type="checkbox" value="${(item.id?c)!}">
                                                    <i></i>
                                                    <span>${(item.name)!}</span>
                                                </label>
                                            </li>
                                        </#list>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="security_batch_btn">
                    <dic data-dic="button.confirm"></dic>
                </button>
            </div>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<div class="modal fade bs-example-modal-lg text-center" id="imgModal"tabindex="-1"   role="dialog" aria-labelledby="myLargeModalLabel" style="height: 100%;	overflow: hidden;">
    <div class="modal-dialog modal-lg" style="display: inline-block; width: auto;">
        <div class="modal-content"  " >
            <img  id="imgInModalID" src="" style="width: 400pz;height:400px;position: absolute;top: 100px;left: 100px;z-index: 1">
        </div>
    </div>
</div>
<style type="text/css">
    .dd3-content {
        cursor: pointer;
    }

    .dd3-content-active {
        color: #2ea8e5;
        background: #fff;
    }
</style>
<link rel="stylesheet" href="../../static/css/viewer.min.css">
<script src="../../static/js/plugin/viewer.min.js"></script>

<script type="text/javascript"
        src="../../static/js/plugin/jquery-nestable/jquery.nestable.min.js"></script>
<script type="text/javascript"
        src="../../static/js/plugin/bootstrap-menu/BootstrapMenu.js"></script>
<script type="text/javascript"
        src="../../static/js/plugin/bootstrap-tags/bootstrap-tagsinput.min.js"></script>
<link rel="stylesheet" href="../../static/js/plugin/ztree/css/metroStyle/metroStyle.css" />
<script type="text/javascript" src="../../static/js/plugin/ztree/js/jquery.ztree.all.min.js"></script>
<!--引入CSS-->
<link rel="stylesheet" type="text/css"
      href="../../static/js/plugin/webuploader/webuploader.css">
<!--引入JS-->
<script type="text/javascript"
        src="../../static/js/plugin/webuploader/webuploader.html5only.js"></script>
<script type="text/javascript">

    var el = {
        securityBatch: '#security-batch',
        securityEdit: '#security-edit',
        securityDelete: '#security-delete',
        securityBatchModal: '#security_batch_modal',
        securityBatchHtml: '#security_batch_html',
        securityBatchBtn: '#security_batch_btn',
        securityModal: '#security_item',
        securityItemHtml: '#security_item_html',
        itemModal: '#folder_item',
        fileEditModal: '#file_edit',
        fileEditAllModal: '#file_edit_all',
        fileItemModal: '#file_item',
        delConfigModal: '#del_modal',
        delAllConfigModal: '#del_all_modal',
        fileMoveModel: '#file_move',
        itemHtml: '#folder_item_html',
        fileItemHtml: '#file_item_html',
        fileItemEditHtml: '#file_item_edit_html',
        fileItemEditAllHtml: '#file_item_edit_all_html',
        folderNestable: '#folder-nestable',
        folderOkDeleteBtn: '#ok_del',
        folderOkDeleteAllBtn: '#ok_del_all',
        fileTable: '#dt_basic_file',
        folderSubBtn: '#ok_sumbit',
        fileSubBtn: '#file_ok_sumbit',
        folderTree: '#folder_tree',
        updateFolderFileId: '#update_folder_file_id',
        updateFolderFilePId: '#update_folder_file_package_id',
        fileEditSubBtn: '#file_edit_ok_sumbit',
        fileEditAllSubBtn: '#file_edit_all_ok_sumbit',
        fileSearchBtn: '#btn_query',
        fileSearchInputCategory: '#search_file_category',
        fileSearchInputPackage: '#search_file_package',
        fileSearchInputSeries: '#search_file_series',
        folderSearchInput: '#search_folder_tree_input',
        folderSearchBtn: '#search_folder_tree_input_btn',
        seriesSearchInput: '#search_series_tree_input',
        seriesSearchBtn: '#search_series_tree_input_btn',
        search_package_tree: '#search_package_tree',
        dropdownSeries: '#seriesDropdownWrapper',
        dropdownPackage: '#packageDropdownWrapper',
        series_ztree: '#series_ztree',
        search_series_tree: '#search_series_tree',
        roleSelectAll : '#selectAll',
        roleList : '#roleBatch',
        search_extend_file_package: '#search_extend_file_package',
        file_extend :'#file_extend'
    };
    var url = {
        folderList: '/folder/index',
        folderItem: '/folder/item',
        fileItem: '/file/item',
        fileEdit: '/file/edit',
        fileSecurity: '/file/security',
        fileSecurityBatch: '/file/batch',
        folderRemove: '/api/folder/delete',
        folderStructUpdate: '/api/folder/struct/update',
        fileList: '/api/file/datagrid',
        updateFileFolder: '/api/file/folder/update',
        fileRemove: '/api/file/delete',
        folderTreeUrl: '/api/folder/tree',
        series_ztree: '/api/series/tree'
    };
    var dt;
    var packageId = 0;
    var fileId = 0;
    var file = {
        _folderTreeNodeClick: function (event, treeId, treeNode) {
            event.preventDefault();
            var packageId = treeNode.data.id;
            var name = treeNode.data.name;
            $(el.fileSearchInputPackage).val(name);
            $(el.fileSearchInputPackage).data('id', packageId);
            $('.dropdown.dp.open').removeClass("open");
        },
        _seriesTreeNodeClick: function (event, treeId, treeNode) {
            event.preventDefault();
            var seriesId = treeNode.data.id;
            var name = treeNode.data.name;
            $(el.fileSearchInputSeries).val(name);
            $(el.fileSearchInputSeries).data('id', seriesId);
            $('.dropdown.dp.open').removeClass("open");
        },
        _remove: function () {
            $.ajax({
                url: url.fileRemove,
                type: 'POST',
                data: {id: fileId},
                success: function (data) {
                    file._dtRefuse();
                    $(el.delConfigModal).modal('hide');
                }
            });
        },
        _folderTree: function () {
            var self = this;
            var setting = {
                data: {
                    simpleData: {
                        enable: true,
                        idKey: 'id',//主键
                        pIdKey: 'pid'//上级id
                    }
                },
                callback: {
                    onClick: self._folderTreeNodeClick
                }
            };
            var zNodes = [];
            var name = $(el.folderSearchInput).val();
            var searchObj = {};
            if (name) {
                searchObj = {q: name};
            }
            //请求
            $.ajax({
                url: url.folderTreeUrl,
                data: searchObj,
                type: 'GET',
                dataType: 'json',
                success: function (data) {
                    zNodes = data;
                    if (typeof data === "string") {//not obj
                        zNodes = $.parseJSON(data);
                    }
                    $.fn.zTree.init($(el.folderTree), setting, zNodes);
                },
                error: function (data) {
                    alert(data.responseJSON.message);
                }
            });
        },
        _seriesTree: function () {
            var self = this;
            var setting = {
                data: {
                    simpleData: {
                        enable: true,
                        idKey: 'id',//主键
                        pIdKey: 'pid'//上级id
                    }
                },
                callback: {
                    onClick: self._seriesTreeNodeClick
                }
            };
            var zNodes = [];
            var name = $(el.seriesSearchInput).val();
            var searchObj = {};
            if (name) {
                searchObj = {name: name};
            }
            //请求
            $.ajax({
                url: url.series_ztree,
                data: searchObj,
                type: 'GET',
                dataType: 'json',
                success: function (data) {
                    zNodes = data;
                    if (typeof data === "string") {//not obj
                        zNodes = $.parseJSON(data);
                    }
                    $.fn.zTree.init($(el.series_ztree), setting, zNodes);
                },
                error: function (data) {
                    alert(data.responseJSON.message);
                }
            });
        },
        _dtRefuse: function () {
            dt.addAjaxParam('folderId', packageId);
            dt.submitFilter();
        },
        _loadFilesTable: function () {
            dt = new Datatables();
            dt.init({
                src: $(el.fileTable),
                loadingMessage: message["datatable.loading"],
                dataTable: {
                    bStateSave: false,
                    ajax: {url: url.fileList},

                    columns: [
                        DTColumnKit.checkbox,
                        DTColumnKit.order,
                        {data: "name"},
                        {data: "extend", width: "80px"},
                        {
                            data: "size", width: "80px",
                            render: function (fileSize, display, row) {
                                if(row.type === 999 || row.type === 888){
                                    return '无';
                                }
                                if (fileSize < 1024) {
                                    return fileSize + 'B';
                                } else if (fileSize < (1024 * 1024)) {
                                    var temp = fileSize / 1024;
                                    temp = temp.toFixed(2);
                                    return temp + 'KB';
                                } else if (fileSize < (1024 * 1024 * 1024)) {
                                    var temp = fileSize / (1024 * 1024);
                                    temp = temp.toFixed(2);
                                    return temp + 'MB';
                                } else {
                                    var temp = fileSize / (1024 * 1024 * 1024);
                                    temp = temp.toFixed(2);
                                    return temp + 'GB';
                                }
                            }
                        },
                        {
                            data: "uploadTime",
                            width: "150px",
                            render: function (data, display, row) {
                                if (data === null) {
                                    return data;
                                } else {
                                    return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
                                }
                            }
                        },
                        {
                            data: "id",
                            width: "290px",
                            render: function (data, display, row) {
                                var html = '<a class="btn btn-xs btn-primary operate" href="javascript:;" ' +
                                        'data-id="' + data + '">'+message["body.list.edit"]+'</a>';
                                var removeHtml = '&nbsp;<a class="btn btn-xs btn-primary remove" ' +
                                        ' href="javascript:;"' +
                                        ' data-id="' + data + '">'+message["body.list.delete"]+'</a>';
                                var securityHtml = '&nbsp;<a class="btn btn-xs btn-primary security" ' +
                                        ' href="javascript:;"' +
                                        ' data-id="' + data + '">'+message["body.list.authorize"]+'</a>';
                                var download = '&nbsp;<a class="btn btn-xs btn-primary download" ' +
                                        ' href="javascript:;"' +
                                        ' data-url="' + row.url + '"' +
                                        ' data-id="' + data + '">'+message["body.list.downland"]+'</a>';
                                var viewHtml = '';
                                var fileType = row.extend;
                                if (fileType == 'png'
                                        || fileType == 'jpg'
                                        || fileType == 'jpeg'
                                        || fileType == 'gif'
                                        || fileType == 'doc'
                                        || fileType == 'docx'
                                        || fileType == 'xls'
                                        || fileType == 'xlsx'
                                        || fileType == 'ppt'
                                        || fileType == 'pptx'
                                        || fileType == 'pdf'
                                        || fileType == "video"
                                        || fileType == "link"
                                ) {
                                    viewHtml = '&nbsp;<a class="btn btn-xs btn-primary view" ' + ' href="javascript:;"' +
                                            ' data-id="' + data + '"' +'data-type="'+ row.type +'"' + 'data-url="'+ row.url +'"' +'data-extend="'+ row.extend +'" >'
                                            + message["body.list.view"]+'</a>';
                                }
                                if (row.type == 999 || row.type == 888) {
                                    if (row.securitys == 1) {
                                        return html + removeHtml + viewHtml;
                                    }
                                    if (row.securitys == 2) {
                                        return html + removeHtml + viewHtml;
                                    }
                                    if (row.securitys == 3) {
                                        return html + removeHtml + securityHtml + viewHtml;
                                    }
                                }
                                if (row.securitys == 1) {
                                    return html + removeHtml + viewHtml + download;
                                }
                                if (row.securitys == 2) {
                                    return html + removeHtml + viewHtml;
                                }
                                if (row.securitys == 3) {
                                    return html + removeHtml + securityHtml + viewHtml + download;
                                }
                                else {
                                    return "";
                                }
                            }
                        }],
                    columnDefs: [{
                        orderable: false,
                        targets: [0,1,2,3,4,5,6]
                    }],
                    drawCallback: function () {
                    }
                }
            });
        },
        _fileItem: function (id) {
            $.ajax({
                url: url.fileItem,
                type: 'GET',
                data: {id: id},
                success: function (data) {
                    if (id == 0) {
                        $(el.fileItemHtml).html(data);
                        $(el.fileItemModal).modal('toggle');

                        if ($(el.fileSearchInputPackage).data("id")) {
                            itemSeletedPids.push($(el.fileSearchInputPackage).data("id"));
                        }
                        if ($(el.fileSearchInputSeries).data("id")) {
                            itemSeletedSids.push($(el.fileSearchInputSeries).data("id"));
                        }
                    } else {
                        $(el.fileItemEditHtml).html(data);
                        $(el.fileEditModal).modal('toggle');
                    }
                }
            });
        },
        _fileSecurity: function (id) {
            $.ajax({
                url: url.fileSecurity,
                type: 'GET',
                data: {id: id},
                success: function (data) {
                    $(el.securityItemHtml).html(data);
                    $(el.securityModal).modal('toggle');
                }
            });
        },
        _fileSearch: function (id) {
            var category = $(el.fileSearchInputCategory).val();
            var _packageId = $(el.fileSearchInputPackage).data("id");
            var series = $(el.fileSearchInputSeries).data("id");
            var extend = $(el.fileSearchInputSeries).data("extend");

            dt.addAjaxParam('category', category);
            dt.addAjaxParam('folderId', _packageId);
            dt.addAjaxParam('seriesId', series);
            dt.addAjaxParam('extend', extend);
            dt.submitFilter();
        }
    };
    var folder = {
        _loadFolders: function () {
            var self = this;
            $.ajax({
                url: url.folderList,
                type: 'GET',
                success: function (data) {
                    $(el.folderNestable).html(data);
                    $('.dd3-content').on("click", function (target) {
                        $('.dd3-content').removeClass('dd3-content-active');
                        $(target.currentTarget).addClass('dd3-content-active');

                        var id = $(target.currentTarget).data('id');
                        packageId = id;
                        file._dtRefuse();
                    });
                    $('.dd').nestable();
                    $('.dd').on('change', function () {
                        self._updateFolder();
                    });
                }
            })
        },
        _remove: function () {
            var self = this;
            $.ajax({
                url: url.folderRemove,
                type: 'POST',
                data: {id: folderId},
                success: function (data) {
                    self._loadFolders();
                    $(el.delConfigModal).modal('hide');
                }
            });
        },
        _updateFolder: function () {
            var self = this;
            $.ajax({
                url: url.folderStructUpdate,
                type: 'POST',
                data: {folderJson: JSON.stringify($('.dd').nestable('serialize'))},
                success: function (data) {
                    self._loadFolders();
                }
            });
        },
        _folderItem: function (id, parentId) {
            $.ajax({
                url: url.folderItem,
                type: 'GET',
                data: {id: id, parentId: parentId},
                success: function (data) {
                    $(el.itemHtml).html(data);
                    $(el.itemModal).modal('toggle');
                }
            });
        }
    };
    $(document).ready(function () {
        $(".add-file").click(function () {
            console.log(dt.getSelectedRowsData());
        });
        $(el.fileSearchInputCategory).select2();
        file._folderTree();
        file._seriesTree();
        $('[data-dic]').each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
        folder._loadFolders();
        $(el.folderOkDeleteBtn).on('click', function () {
            var type = $(this).data("type");
            if (type == 1) {
                folder._remove();
            } else {
                file._remove();
            }

        });
        file._loadFilesTable(0);
        $('.add-folder').on('click', function () {
            folder._folderItem(0, 0);
        });
        $('.add-file').on('click', function () {
            debugger
            var id = $(this).data('id');
            file._fileItem(id);
        });
        $('.see-type').off('click').click(function () {
            $('.see-type.file-type-btn-active').removeClass('file-type-btn-active');
            $(this).addClass('file-type-btn-active');
        });

        $('.product-type').off('click').click(function () {
            $('.product-type.file-type-btn-active').removeClass('file-type-btn-active');
            $(this).addClass('file-type-btn-active');
        });
        $('table').on('click', '.operate', function () {
        	alert("------operate-------");
            var id = $(this).data('id');
            file._fileItem(id);
        });
        $('table').on('click', '.remove', function () {
            var id = $(this).data('id');
            fileId = id;
            $(el.folderOkDeleteBtn).data('type', 2);
            $(el.delConfigModal).modal('toggle');
        });
        $('table').on('click', '.security', function () {
            var id = $(this).data('id');
            file._fileSecurity(id);
        });
        $('table').on('click', '.view', function () {
            var id = $(this).data('id');
            var type = $(this).data('type');
            var url = $(this).data('url');
            var extend = $(this).data('extend');
            if (type == 999) {
                if (url.indexOf("http://")==0 || url.indexOf("https://")==0) {
                    window.open(url);
                }else {
                    window.open('http://'+ url);
                }
            }else {
                /*if(extend == 'png'
                        || extend == 'jpg'
                        || extend == 'jpeg'
                        || extend == 'gif'){
                    $("#imgInModalID").attr("src",url);
                    $("#imgModal").modal();
                }else {
                    window.open('/file/view?id=' + id);
                }*/
                window.open('/file/view?id=' + id);
            }



        });
        $('table').on('click', '.download', function () {
            var url = $(this).data('url');
            window.open(url);
        });
        //图片模态框关闭事件
        $('#imgModal').on('hide.bs.modal',
                    function() {
                        $("#imgInModalID").css("width","400px");
                        $("#imgInModalID").css("height","400px");
                        $("#imgInModalID").css("left","");
                        $("#imgInModalID").css("top","");
                    })
        $(search_extend_file_package).on('click',function () {
            $(file_extend).modal('show');
        });

        $("#file_extend_ok_sumbit").on('click',function () {
            var extend =  $(".btn.btn-primary.extend_btn.active input[type='checkbox']");
            var extendVar = "";
            $.each(extend, function (index,item) {
                if (extendVar!=""){
                    extendVar  += (","+$(item).data('extend'))  ;
                }else {
                    extendVar  +=  $(item).data('extend');
                }
            });

            $("#search_extend_file_package").val(extendVar);
            $(el.fileSearchInputSeries).data("extend",extendVar);
            $(file_extend).modal('hide');
        });

        $('.extend_btn').on('click',function () {
            console.log($(this).attr('class'));
            if($(this).hasClass('active')){
                $(this).removeClass('btn-primary');
                $(this).addClass('btn-default');
            }else{
                $(this).removeClass('btn-default');
                $(this).addClass('btn-primary');
            }

        });
        var menu = new BootstrapMenu('.dd3-content', {
            fetchElementData: function ($rowElem) {
                var rowId = $rowElem.data('id');
                var isRemovable = $rowElem.data('delable');
                var row = {id: rowId, isRemovable: isRemovable};
                return row;
            },
            actions: [{
                name: message['header.list.button.add'],
                onClick: function (row) {
                    folder._folderItem(0, row.id);
                }
            }, {
                name: message['body.list.edit'],
                onClick: function (row) {
                    folder._folderItem(row.id, 0);
                }
            }, {
                name: message['body.list.delete'],
                onClick: function (row) {
                    folderId = row.id;
                    $(el.folderOkDeleteBtn).data('type', 1);
                    $(el.delConfigModal).modal('toggle');
                },
                isEnabled: function (row) {
                    return row.isRemovable;
                }
            }]
        });
        $(el.folderSearchBtn).on('click', function () {
            file._folderTree();
        });
        $(el.seriesSearchBtn).on('click', function () {
            file._seriesTree();
        });
        $(el.fileEditModal).on('hide.bs.modal', function () {
            $(el.fileItemEditHtml).html('');
        });
        $(el.fileItemModal).on('hide.bs.modal', function () {
            $(el.fileItemHtml).html('');
        });
        $(el.fileMoveModel).on('hide.bs.modal', function () {
            $(el.folderSearchInput).val('');
        });
        $(el.fileSearchBtn).on('click', function () {
            file._fileSearch();
        });
        $(el.securityBatch).on('click', function () {
            var ids = dt.getSelectedRowsData();
            if (ids.length == 0) {
                alert(message['prompt.message.security'], 2);
                return;
            }
            $("#roleBatch .role_check:checked").each(function () {
                $(this).prop('checked',false);
            });
            $(el.securityBatchModal).modal('toggle');
        });
        $(el.securityDelete).on('click', function () {
            var ids = dt.getSelectedRowsData();
            if (ids.length == 0) {
                alert(message['product.series.select'], 2);
                return;
            }
            $(el.delAllConfigModal).modal('show');
        });
        $(el.folderOkDeleteAllBtn).on('click', function () {
            var ids = dt.getSelectedRowsData();
            $("#roleBatch .role_check:checked").each(function () {
                $(this).prop('checked',false);
            });
            $.ajax({
                url: '/api/file/deleteAll',
                type: 'post',
                data: {ids: JSON.stringify(ids)},
                success: function (data) {
                    alert(message['alert.message.success']);
                    file._dtRefuse();
                    $(el.delAllConfigModal).modal('hide');
                },error: function (data) {
                    if (data.responseJSON.code === 20002) {
                        alert(data.responseJSON.message);
                    }
                    alert(message['alert.message.systemError'], 3);
                }
            });
        });
        $(el.securityEdit).on('click', function () {
            var ids = dt.getSelectedRowsData();
            console.log(ids);
            if (ids.length == 0) {
                alert(message['product.series.select'], 2);
                return;
            }
            $("#roleBatch .role_check:checked").each(function () {
                $(this).prop('checked',false);
            });
            $.ajax({
                url: url.fileEdit,
                type: 'GET',
                data: {ids:  JSON.stringify(ids)},
                success: function (data) {
                        $(el.fileItemEditAllHtml).html(data);
                        $(el.fileEditAllModal).modal('toggle');
                }
            });
        });

        $(el.folderSubBtn).on('click', function () {
            item._submitForm();
        });
        //模态框弹出后，重新初始化webuploader
        $(el.fileEditModal).on('shown.bs.modal', function() {
            uploader.addButton({
                id: '#picker',
                innerHTML: message["file.list.item.select"]
            });
        });
        $(el.fileEditSubBtn).on('click', function () {
            var seriesIds = [];
            var packageIds = [];
            editSelectedSid.unique3().forEach(function (val) {
                seriesIds.push(val);
            });
            editSelectedPid.unique3().forEach(function (val) {
                packageIds.push(val);
            });

            var securitys = $('.see-type.file-type-btn-active').data('type');
            var market = $('.product-type.file-type-btn-active').data('type');
            if(!market){
                alert(message['file.message.error.productSubdivision'],2);
                return;
            }
            if(!securitys){
                alert(message['file.message.error.choiceView'],2);
                return;
            }
            var extend = 1; //用以区分视频地址和外部地址。 1 - 视频地址 2 - 外部地址
            if($('#file_edit_id form.smart-form').data('type') === '999'){
                extend = 2;
            }

            var uploadDomLength = $('#upload-form').length;
            if (uploadDomLength <= 0) {
                //获取表单对象
                var validatorEl =  $('#video-edit-form');
                if(validatorEl.length <= 0){
                    validatorEl = $('#link-edit-form');
                }
                var bootstrapValidator = validatorEl.data('bootstrapValidator');
                //手动触发验证
                bootstrapValidator.validate();
                if (bootstrapValidator.isValid()) {
                    $.ajax({
                        url: '/api/file/video/update',
                        type: 'post',
                        data: {
                            id: $("#file_edit_id").val(),
                            name: $("#videoName").val(),
                            url: $("#videoUrl").val(),
                            packageIds: packageIds.join(','),
                            seriesIds: seriesIds.join(','),
                            fileType: $('.file-type.file-type-btn-active').data("type"),
//                            extend: extend,
                            securitys: securitys,
                            market: market
                        },
                        success: function (data) {
                            $(el.fileEditModal).modal('hide');
                            file._dtRefuse();
                            alert(message["alert.message.success"]);
                        },
                        error: function (data) {
                            alert(message["alert.message.systemError"]);
                        }
                    });
                }
            }else {
                $.ajax({
                    url: url.fileUpdateUrl,
                    data: {
                        id: $("#file_edit_id").val(),
                        attId: $("#attachmentId").val(),
                        packageIds: packageIds.join(','),
                        seriesIds: seriesIds.join(','),
                        fileType: $('.file-type-btn-active').data("type"),
                        securitys: securitys,
                        market: market
                    },
                    type: 'POST',
                    success: function (data) {
                        $(el.fileEditModal).modal('hide');
                        file._dtRefuse();
                        alert(message["alert.message.success"]);
                    },
                    error: function (data) {
                        alert(message["alert.message.systemError"]);
                    }
                });
            }
        });
        $(el.fileEditAllSubBtn).on('click', function () {
            var seriesIds = [];
            var packageIds = [];
            editSelectedSid.unique3().forEach(function (val) {
                seriesIds.push(val);
            });
            editSelectedPid.unique3().forEach(function (val) {
                packageIds.push(val);
            });
            var securitys = $('.see-type.file-type-btn-active').data('type');
            var market = $('.product-type.file-type-btn-active').data('type');
            if(!market){
                alert(message['file.message.error.productSubdivision'],2);
                return;
            }
            if(!securitys){
                alert(message['file.message.error.choiceView'],2);
                return;
            }
            $.ajax({
                    url: url.fileUpdateAllUrl,
                    data: {
                        ids: $("#file_edit_id").html(),
                        packageIds: packageIds.join(','),
                        seriesIds: seriesIds.join(','),
                        fileType: $('.file-type-btn-active').data("type"),
                        securitys: securitys,
                        market: market
                    },
                    type: 'POST',
                    success: function (data) {
                        $(el.fileEditAllModal).modal('hide');
                        file._dtRefuse();
                        alert(message["alert.message.success"]);
                    },
                    error: function (data) {
                        alert(message["alert.message.systemError"]);
                    }
            });
        });
        $('div').on('click', '.file-type', function () {
            $('.file-type').removeClass('file-type-btn-active');
            $(this).addClass('file-type-btn-active');
        });
        $('.rest').on('click', function () {
            $(el.fileSearchInputCategory).val("");
            $(el.fileSearchInputPackage).val("");
            $(el.fileSearchInputPackage).data("id", "0");
            $(el.fileSearchInputSeries).val("");
            $(el.fileSearchInputSeries).data("id", "0");
            $("#search_extend_file_package").val("");
            $(el.fileSearchInputSeries).data("extend","");
            $(".extend_btn").removeClass('btn-primary active');
            $(".extend_btn").addClass('btn-default');
        });

        $("#security_btn").click(function () {
            var fileId = $("#fileId").val();
            var roleArr = [];
            $('#role .role_check').each(function () {
                if($(this).prop('checked')){
                    roleArr.push({
                        security: 1, //之前的有1和2，现全部为1
                        role: $(this).val()
                    });
                }
            });
            if (roleArr.length === 0) {
                alert(message["file.list.item.security"], 3);
                return;
            }
            $.ajax({
                url: '/api/file/security/save',
                type: 'post',
                data: {fileId: fileId, fileSecurityRoleJson: JSON.stringify(roleArr)},
                success: function (data) {
                    alert(message['alert.message.success']);
                    $(el.securityModal).modal('hide');
                },error: function (data) {
                    if (data.responseJSON.code === 20002) {
                        alert(data.responseJSON.message);
                    }
                    alert(message['alert.message.systemError'], 3);
                }
            });
        });
        $(el.securityBatchBtn).click(function () {
            var fileIds = dt.getSelectedRowsData();
            var roleArr = [];
            $('#roleBatch .role_check').each(function () {
                if($(this).prop('checked')){
                    roleArr.push({
                        security: 1, //之前的有1和2，现全部为1
                        role: $(this).val()
                    });
                }
            });
            if (roleArr.length === 0) {
                alert(message["file.list.item.security"], 2);
                return;
            }
            $.ajax({
                url: '/api/file/batch/save',
                type: 'post',
                data: {ids: JSON.stringify(fileIds), fileSecurityRoleJson: JSON.stringify(roleArr)},
                success: function (data) {
                    alert(message['alert.message.success']);
                    $(el.securityBatchModal).modal('hide');
                },error: function (data) {
                    if (data.responseJSON.code === 20002) {
                        alert(data.responseJSON.message);
                    }
                    alert(message['alert.message.systemError'], 3);
                }
            });
        });
    });

    $(el.roleSelectAll).click(function () {
        if($('#roleBatch .role_check:checked').length === $('#roleBatch .role_check').length){
            $('#roleBatch .role_check').prop('checked',false);
        }else{
            $('#roleBatch .role_check').prop('checked',true);
        }
    });

    var uploader = WebUploader.create({
        // 文件接收服务端。
        server: '/import/upload',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker',
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false
    });
    //时间的格式化
    Date.prototype.Format = function (fmt) { //author: meizz 
        var o = {
            "M+": this.getMonth() + 1,
            //月份 
            "d+": this.getDate(),
            //日 
            "h+": this.getHours(),
            //小时 
            "m+": this.getMinutes(),
            //分 
            "s+": this.getSeconds(),
            //秒 
            "q+": Math.floor((this.getMonth() + 3) / 3),
            //季度 
            "S": this.getMilliseconds() //毫秒 
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    }

    /*绑定事件*/
    function addEvent(obj, sType, fn) {
        if (obj.addEventListener) {
            obj.addEventListener(sType, fn, false);
        } else {
            obj.attachEvent('on' + sType, fn);
        }
    };
    function removeEvent(obj, sType, fn) {
        if (obj.removeEventListener) {
            obj.removeEventListener(sType, fn, false);
        } else {
            obj.detachEvent('on' + sType, fn);
        }
    };
    function prEvent(ev) {
        var oEvent = ev || window.event;
        if (oEvent.preventDefault) {
            oEvent.preventDefault();
        }
        return oEvent;
    }
    /*添加滑轮事件*/
    function addWheelEvent(obj, callback) {
        if (window.navigator.userAgent.toLowerCase().indexOf('firefox') != -1) {
            addEvent(obj, 'DOMMouseScroll', wheel);
        } else {
            addEvent(obj, 'mousewheel', wheel);
        }
        function wheel(ev) {
            var oEvent = prEvent(ev),
                    delta = oEvent.detail ? oEvent.detail > 0 : oEvent.wheelDelta < 0;
            callback && callback.call(oEvent, delta);
            return false;
        }
    };
    /*页面载入后*/
     var oImg = document.getElementById('imgInModalID');
        /*拖拽功能*/
        (function() {
            addEvent(oImg, 'mousedown', function(ev) {
                var oEvent = prEvent(ev),
                        oParent = oImg.parentNode,
                        disX = oEvent.clientX - oImg.offsetLeft,
                        disY = oEvent.clientY - oImg.offsetTop,
                        startMove = function(ev) {
                            if (oParent.setCapture) {
                                oParent.setCapture();
                            }
                            var oEvent = ev || window.event,
                                    l = oEvent.clientX - disX,
                                    t = oEvent.clientY - disY;
                            oImg.style.left = l +'px';
                            oImg.style.top = t +'px';
                            oParent.onselectstart = function() {
                                return false;
                            }
                        }, endMove = function(ev) {
                            if (oParent.releaseCapture) {
                                oParent.releaseCapture();
                            }
                            oParent.onselectstart = null;
                            removeEvent(oParent, 'mousemove', startMove);
                            removeEvent(oParent, 'mouseup', endMove);
                        };
                addEvent(oParent, 'mousemove', startMove);
                addEvent(oParent, 'mouseup', endMove);
                return false;
            });
        })();
        /*以鼠标位置为中心的滑轮放大功能*/
        (function() {
            addWheelEvent(oImg, function(delta) {
                debugger
                var ratioL = (this.clientX - oImg.offsetLeft) / oImg.offsetWidth,
                        ratioT = (this.clientY - oImg.offsetTop) / oImg.offsetHeight,
                        ratioDelta = !delta ? 1 + 0.1 : 1 - 0.1,
                        w = parseInt(oImg.offsetWidth * ratioDelta),
                        h = parseInt(oImg.offsetHeight * ratioDelta),
                        l = Math.round(this.clientX - (w * ratioL)),
                        t = Math.round(this.clientY - (h * ratioT));
                with(oImg.style) {
                    width = w +'px';
                    height = h +'px';
                    left = l +'px';
                    top = t +'px';
                }
            });
        })();

</script>
<style type="text/css">
    ul[role='group'] li {
        cursor: pointer;
    }

    .modal-body:after {
        content: "";
        display: block;
        clear: both;
    }

    .file-type-btn-active {
        color: #333 !important;
        background-color: #f8c567 !important;
        border-color: #adadad !important;
    }

    #folder_tree.ztree span.button {
        float: none !important;
    }
    #series_ztree.ztree span.button {
        float: none !important;
    }

    .btn-group{
        width: 100%;
        padding-left: 0;
        padding-right:0;
        margin-bottom: 5px;
    }

    .btn-group .file-type.btn{
        width: 15%;
    }
    .btn-xs-group .product-type.btn{
        width: 10.1%;
    }

    .btnSM{
        width: 33% !important;
    }

    #file_item .jarviswidget{
        margin:0;
    }
    .role-icon {
        width: 20px;
        height: 20px;
        border: solid 1px #e5e5e5;
        float: left;
        text-align: center;
    }
</style>
</#compress>