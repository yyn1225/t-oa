<#compress>
<article class="col-xs-12 col-sm-12 col-md-3 col-lg-3 list">
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <h2>
                <dic data-dic="product.series.tree.title"></dic>
            </h2>
        </header>
        <div>
            <div class="widget-body no-padding">
                <div class="widget-body-toolbar">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <div class="input-group">
                                <input class="form-control" id="search_organization_input"
                                       placeholder=""
                                       type="text">
                                <span class="input-group-addon" id="search_organization_btn">
                                    <i class="fa fa-search"></i></span>
                            </div>
                        </div>
                    </div>
                </div>
                <ul id="ul_tree_organization" class="ztree" style="overflow-y: scroll;height:
                400px;"></ul>
            </div>
        </div>
    </div>
</article>
<article class="col-xs-12 col-sm-12 col-md-9 col-lg-9 list">
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <h2>
                <dic data-dic="file.list.tableName"></dic>
            </h2>
        <#--<a class="btn btn-primary btn-xs add-file" style="float: right" data-id="0">-->
        <#--<dic data-dic="header.list.button.add"></dic>-->
        <#--</a>-->
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
                            <dic data-dic="file.list.table.extend"></dic>
                        </th>
                    <#--<th>-->
                    <#--<dic data-dic="file.list.table.type"></dic>-->
                    <#--</th>-->
                    <#--<th>-->
                    <#--<dic data-dic="file.list.table.url"></dic>-->
                    <#--</th>-->
                        <th>
                            <dic data-dic="file.list.table.size"></dic>
                        </th>
                    <#--<th>-->
                    <#--<dic data-dic="file.list.table.path"></dic>-->
                    <#--</th>-->
                        <th>
                            <dic data-dic="product.panel.list.series"></dic>
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

<style type="text/css">
    .dd3-content {
        cursor: pointer;
    }

    .dd3-content-active {
        color: #2ea8e5;
        background: #fff;
    }
</style>
<link rel="stylesheet" href="../static/js/plugin/ztree/css/metroStyle/metroStyle.css"/>
<script type="text/javascript" src="../static/js/plugin/ztree/js/jquery.ztree.all.min.js"/>
<script type="text/javascript">
    var folderId = 0;
    var el = {
        ztree: '#ul_tree_organization',
        tree_search_btn: '#search_organization_btn',
        tree_search_input: '#search_organization_input',
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
        fileEditSubBtn: '#file_edit_ok_sumbit',
        fileSearchBtn: '#btn_query',
        fileSearchInput: '#search_file_input',
        folderSearchInput: '#search_folder_tree_input',
        folderSearchBtn: '#search_folder_tree_input_btn'

    };
    var url = {
        ztree: '/api/series/tree',
        folderList: '/folder/index',
        folderItem: '/folder/item',
        fileItem: '/file/item',
        folderRemove: '/api/folder/delete',
        folderStructUpdate: '/api/folder/struct/update',
        fileList: '/api/series/datagrid',
        updateFileFolder: '/api/file/folder/update',
        fileRemove: '/api/file/delete',
        folderTreeUrl: '/api/folder/tree'
    };
    var dt;
    var packageId = 0;
    var fileId = 0;
    var file = {
        _folderTreeNodeClick: function (event, treeId, treeNode) {
            parentId = treeNode.data.id;
            dt.addAjaxParam('seriesId', parentId);
            dt.submitFilter();
        },
        _loadTree: function () {
            var self = this;
            var setting = {
                data: {
                    simpleData: {
                        enable: true,
                        idKey: 'id',//主键
                        pIdKey: 'pid' //上级id
                    }
                },
                callback: {
                    onClick: self._folderTreeNodeClick
                }
            };
            var zNodes = [];
            var name = $(el.tree_search_input).val();
            var searchObj = {};
            if (name) {
                searchObj = {name: name};
            }
            //请求
            $.ajax({
                url: url.ztree,
                data: searchObj,
                type: 'GET',
                dataType: 'json',
                success: function (data) {
                    zNodes = data;
                    if (typeof data === "string") {//not obj
                        zNodes = $.parseJSON(data);
                    }
                    sTree = $.fn.zTree.init($(el.ztree), setting, zNodes);
                    sTree.expandAll(false);
                },
                error: function (data) {
                    alert(data.responseJSON.message);
                }
            });
        },
        _treeSearch: function () {
            file._loadTree();
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
                        {data: "name"},
                        {data: "extend", width: "80px"},
                        {
                            data: "size",
                            width: "100px",
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
                        {data: "seriesName", width: "120px"},
                        {
                            data: "id",
                            width: "140px",
                            render: function (data, display, row) {
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
                                        || fileType == 'video'
                                        || fileType == 'link'
                                ) {
                                    viewHtml = '&nbsp;<a class="btn btn-xs btn-primary view" ' + ' href="javascript:;"' +
                                            ' data-id="' + data + '"' +'data-type="'+ row.type +'"' + 'data-url="'+ row.url +'"' + '>'
                                            + message["body.list.view"]+'</a>';
                                }
                                if(row.type == 888 || row.type == 999) {
                                    return viewHtml;
                                }
                                if (row.securitys==2) {
                                    return viewHtml;
                                }
                                if (row.securitys == 1 || row.securitys==3) {
                                    return viewHtml + download;
                                }
                                else {
                                    return "";
                                }
                            }
                        }],
                    columnDefs: [{
                        orderable: false,
                        targets: [0,1,2,3,4,5]
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
        _fileSearch: function (id) {
            var fileName = $(el.fileSearchInput).val();
            dt.addAjaxParam('folderId', folderId);
            dt.addAjaxParam('name', fileName);
            dt.submitFilter();
        }
    };

    $(document).ready(function () {
        $("dic").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
        file._loadTree();
        file._loadFilesTable(0);
        $(el.tree_search_btn).on('click', function () {
            file._treeSearch()
        });
        $('.add-folder').on('click', function () {
            folder._folderItem(0, 0);
        });
        $('.add-file').on('click', function () {
            var id = $(this).data('id');
            file._fileItem(id);
        });
        $('table').on('click', '.operate', function () {
            var id = $(this).data('id');
            file._fileItem(id);
        });
        $('table').on('click', '.move', function () {
            var id = $(this).data('id');
            fileId = id;
            file._folderTree(id);
        });
        $('table').on('click', '.remove', function () {
            var id = $(this).data('id');
            fileId = id;
            $(el.folderOkDeleteBtn).data('type', 2);
            $(el.delConfigModal).modal('toggle');
        });
        $('table').on('click', '.view', function () {
            var id = $(this).data('id');
            var type = $(this).data('type');
            var url = $(this).data('url');
            if (type == 999) {
                if (url.indexOf("http://")==0 || url.indexOf("https://")==0) {
                    window.location.href = url;
                }else {
                    window.location.href = 'http://'+ url;
                }
            }else {
                window.open('/file/view?id=' + id);
            }
        });

        $(el.folderSearchBtn).on('click', function () {
            file._folderTree(fileId);
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
            $.ajax({
                url: url.fileUpdateUrl,
                data: {id: $("#file_edit_id").val(), attId: $("#attachmentId").val()},
                type: 'POST',
                success: function (data) {
                    $(el.fileEditModal).modal('hide');
                    file._dtRefuse();
                }
            })
        });

    });
</script>
<style type="text/css">
    ul[role='group'] li {
        cursor: pointer;
    }
</style>
</#compress>