<#compress>
<article class="col-xs-12 col-sm-12 col-md-4 col-lg-4 list">
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <h2>
                <dic data-dic="folder.nestable.treeName"></dic>
            </h2>
            <a class="btn btn-primary btn-xs add-folder" style="float: right" data-id="0">
                <dic data-dic="header.list.button.add"></dic>
            </a>
        </header>
        <div>
            <div class="widget-body no-padding" id="folder-nestable">

            </div>
        </div>
    </div>
</article>
<article class="col-xs-12 col-sm-12 col-md-8 col-lg-8 list">
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <h2>
                <dic data-dic="file.list.tableName"></dic>
            </h2>
            <a class="btn btn-primary btn-xs add-file" style="float: right" data-id="0">
                <dic data-dic="header.list.button.add"></dic>
            </a>
        </header>
        <div>
            <div class="widget-body no-padding">
                <div class="widget-body-toolbar">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                            <div class="input-group">
                                <input class="form-control" id="search_file_input"
                                       placeholder=""
                                       type="text">
                                <span class="input-group-addon" id="btn_query">
                                    <i class="fa fa-search"></i></span>
                            </div>
                        </div>
                    </div>
                </div>
                <table id="dt_basic_file" class="table table-striped table-bordered table-hover"
                       width="100%">
                    <thead>
                    <tr>
                        <th></th>
                        <th>
                            <dic data-dic="file.list.table.name"></dic>
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
            <div class="modal-body" id="folder_item_html">

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
    <div class="modal-dialog" style="width: 800px">
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

<!-- 模态框（Modal） -->
<div class="modal fade" id="file_edit" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
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
<div class="modal fade" id="file_move" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 300px;">
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
                <div class="ztree" id="folder_tree" style="height: 200px;overflow-y: scroll;"></div>
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
<style type="text/css">
    .dd3-content {
        cursor: pointer;
    }

    .dd3-content-active {
        color: #2ea8e5;
        background: #fff;
    }
</style>
<script type="text/javascript"
        src="../static/js/plugin/jquery-nestable/jquery.nestable.min.js"></script>
<script type="text/javascript"
        src="../static/js/plugin/bootstrap-menu/BootstrapMenu.js"></script>
<link rel="stylesheet" href="../static/js/plugin/ztree/css/metroStyle/metroStyle.css"/>
<script type="text/javascript" src="../static/js/plugin/ztree/js/jquery.ztree.all.min.js"/>
<!--引入CSS-->
<link rel="stylesheet" type="text/css"
      href="../static/js/plugin/webuploader/webuploader.css">
<!--引入JS-->
<script type="text/javascript"
        src="../static/js/plugin/webuploader/webuploader.html5only.js"></script>
<script type="text/javascript">
    var folderId = 0;
    var el = {
        securityModal: '#security_item',
        securityItemHtml: '#security_item_html',
        itemModal: '#folder_item',
        fileEditModal: '#file_edit',
        fileItemModal: '#file_item',
        delConfigModal: '#del_modal',
        fileMoveModel: '#file_move',
        itemHtml: '#folder_item_html',
        fileItemHtml: '#file_item_html',
        fileItemEditHtml: '#file_item_edit_html',
        folderNestable: '#folder-nestable',
        folderOkDeleteBtn: '#ok_del',
        fileTable: '#dt_basic_file',
        folderSubBtn: '#ok_sumbit',
        fileSubBtn: '#file_ok_sumbit',
        folderTree: '#folder_tree',
        updateFolderFileId: '#update_folder_file_id',
        updateFolderFilePId: '#update_folder_file_package_id',
        fileEditSubBtn: '#file_edit_ok_sumbit',
        fileSearchBtn: '#btn_query',
        fileSearchInput: '#search_file_input',
        folderSearchInput: '#search_folder_tree_input',
        folderSearchBtn: '#search_folder_tree_input_btn'

    };
    var url = {
        folderList: '/folder/index',
        folderItem: '/folder/item',
        fileItem: '/file/item',
        fileSecurity: '/file/security',
        folderRemove: '/api/folder/delete',
        folderStructUpdate: '/api/folder/struct/update',
        fileList: '/api/file/datagrid',
        updateFileFolder: '/api/file/folder/update',
        fileRemove: '/api/file/delete',
        folderTreeUrl: '/api/folder/tree'
    };
    var dt;
    var packageId = 0;
    var fileId = 0;
    var file = {
        _folderTreeNodeClick: function (event, treeId, treeNode) {
            var packageId = treeNode.data.id;
            $.ajax({
                url: url.updateFileFolder,
                data: {
                    id: $(el.updateFolderFileId).val(),
                    oldPackageId: $(el.updateFolderFilePId).val(),
                    packageId: packageId
                },
                type: 'POST',
                success: function (data) {
                    file._dtRefuse();
                    $(el.fileMoveModel).modal('hide');

                }
            });
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
            $(el.fileMoveModel).modal('show');
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
        _dtRefuse: function () {
            dt.addAjaxParam('folderId', packageId);
            dt.submitFilter();
        },
        _loadFilesTable: function (folderId) {
            dt = new Datatables();
            dt.setAjaxParam("folderId", folderId);
            dt.init({
                src: $(el.fileTable),
                loadingMessage: message["datatable.loading"],
                dataTable: {
                    bStateSave: false,
                    ajax: {url: url.fileList},

                    columns: [
                        DTColumnKit.order,
                        {data: "name",
                            render: function (data,type,row,meta) {
                                return '<div class="td-show-s" '+
                                        'title="'+ data +'">'+ data +'</div>';
                            }
                        },
                        {
                            data: "size",
                            width:"80px",
                            render: function (fileSize, display, row) {
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
                            width: "130px",
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
                            width: "240px",
                            render: function (data, display, row) {
                                var html = '<a class="btn btn-xs btn-primary operate" href="javascript:;" ' +
                                        'data-id="' + data + '">'+message["body.list.edit"]+'</a>';
                                var removeHtml = '&nbsp;<a class="btn btn-xs btn-primary remove" ' +
                                        ' href="javascript:;"' +
                                        ' data-id="' + data + '">'+message["body.list.delete"]+'</a>';
//                                var securityHtml = '&nbsp;<a class="btn btn-xs btn-primary security" ' +
//                                        ' href="javascript:;"' +
//                                        ' data-id="' + data + '">授权</a>';
                                var moveHtml = '&nbsp;<a class="btn btn-xs btn-primary move" ' +
                                        ' href="javascript:;"' +
                                        ' data-parent="' + row.parent + '"' +
                                        ' data-id="' + data + '">移动</a>';
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
                                            ' data-id="' + data + '"' +'data-type="'+ row.type +'"' + 'data-url="'+ row.url +'"' + '>'
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
                                        return html + removeHtml +  viewHtml;
                                    }
                                }
                                if (row.securitys == 1) {
                                    return html + removeHtml + viewHtml + download;
                                }
                                if (row.securitys == 2) {
                                    return html + removeHtml + viewHtml;
                                }
                                if (row.securitys == 3) {
                                    return html + removeHtml +  viewHtml + download;
                                }
                                else {
                                    return "";
                                }
                            }
                        }],
                    columnDefs: [{
                        orderable: false,
                        targets: [0,1,2,3,4]
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
            var fileName = $(el.fileSearchInput).val();
            dt.addAjaxParam('folderId', folderId);
            dt.addAjaxParam('name', fileName);
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
                    $('.dd').nestable('collapseAll');
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
//                    self._loadFolders();
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
        $("dic").each(function () {
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
            var id = $(this).data('id');
            file._fileItem(id);
        });
        $('table').on('click', '.operate', function () {
        	alert("------operate-------");
            var id = $(this).data('id');
            file._fileItem(id);
        });
        $('table').on('click', '.move', function () {
            var id = $(this).data('id');
            var pid = $(this).data('parent');
            fileId = id;
            $(el.updateFolderFileId).val(id);
            $(el.updateFolderFilePId).val(pid);
            file._folderTree(id);
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
            if (type == 999) {
                if (url.indexOf("http://")==0 || url.indexOf("https://")==0) {
                    window.open(url);
                }else {
                    window.open('http://'+ url);
                }
            }else {
                window.open('/file/view?id=' + id);
            }
        });
     	$('table').on('click', '.download', function () {
            var url = $(this).data('url');
            window.open(url);
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
                    console.log("新建");
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
        $(el.folderSubBtn).on('click', function () {
            item._submitForm();
        });
        $(el.fileEditSubBtn).on('click', function () {
            var seriesIds = new Array();
            var packageIds = new Array();
            editSelectedSid.unique3().forEach(function (val) {
                seriesIds.push(val);
            });
            editSelectedPid.unique3().forEach(function (val) {
                packageIds.push(val);
            });
            var $edit = $("#video-edit-form").html();
            if ($edit != undefined && $edit != null && $edit != '') {
                //获取表单对象
                var bootstrapValidator = $("#video-edit-form").data('bootstrapValidator');
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
                            fileType: $('.file-type-btn-active').data("type")
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
                        fileType: $('.file-type-btn-active').data("type")
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
        $('div').on('click', '.file-type', function () {
            $('.file-type').removeClass('file-type-btn-active');
            $(this).addClass('file-type-btn-active');
        });
        //模态框弹出后，重新初始化webuploader
        $(el.fileEditModal).on('shown.bs.modal', function() {
            uploader.addButton({
                id: '#picker',
                innerHTML: '选择文件'
            });
        });
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

    .btn-group .file-type.btn{
        width: 15%;
    }
    .btn-xs-group .product-type.btn{
        width: 10.1%;
    }
    .btn-group{
        width: 100%;
        padding-left: 0;
        padding-right:0;
        margin-bottom: 5px;
    }

    .btnSM{
        width: 33% !important;
    }

    #file_item .jarviswidget{
        margin:0;
    }
</style>
</#compress>