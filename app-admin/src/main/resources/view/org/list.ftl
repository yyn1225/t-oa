<#compress>
<article class="col-xs-12 col-sm-12 col-md-3 col-lg-3 list">
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <h2>
                <dic data-dic="organization.list.treeName"></dic>
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
                <ul id="ul_tree_organization" class="ztree"></ul>
            </div>
        </div>
    </div>
</article>
<article class="col-xs-12 col-sm-12 col-md-9 col-lg-9 list">
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <h2>
                <dic data-dic="organization.list.tableName"></dic>
            </h2>
            <a class="btn btn-primary btn-xs operate" style="float: right" data-id="0">
                <dic data-dic="header.list.button.add"></dic>
            </a>
        </header>
        <div>
            <div class="widget-body no-padding">
                <div class="widget-body-toolbar">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                            <div class="input-group">
                                <input class="form-control" id="search_org_input"
                                       placeholder=""
                                       type="text">
                                <span class="input-group-addon" id="btn_query">
                                    <i class="fa fa-search"></i></span>
                            </div>
                        </div>
                    </div>
                </div>
                <table id="dt_basic_org" class="table table-striped table-bordered table-hover"
                       width="100%">
                    <thead>
                    <tr>
                        <th></th>
                        <th>
                            <dic data-dic="organization.list.table.name"></dic>
                        </th>
                        <th>
                            <dic data-dic="organization.list.table.level"></dic>
                        </th>
                    <#--<th>-->
                    <#--<dic data-dic="organization.list.table.employeeSize"></dic>-->
                    <#--</th>-->
                        <th>
                            <dic data-dic="organization.list.table.leader"></dic>
                        </th>
                        <th>
                            <dic data-dic="organization.list.table.parent"></dic>
                        </th>
                        <th>
                            <dic data-dic="organization.list.table.status"></dic>
                        </th>
                        <th>
                            <dic data-dic="org.role.list.operate"></dic>
                        </th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</article>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="edit">
</article>
<!-- 模态框（Modal） -->
<div class="modal fade" id="del_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 350px">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
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
<link rel="stylesheet" href="../static/js/plugin/ztree/css/metroStyle/metroStyle.css"/>
<script type="text/javascript" src="../static/js/plugin/ztree/js/jquery.ztree.all.min.js"/>
<script type="text/javascript">
    var el = {
        ztree: '#ul_tree_organization',
        tree_search_btn: '#search_organization_btn',
        tree_search_input: '#search_organization_input',
        dt_table: '#dt_basic_org',
        dt_search_btn: '#btn_query',
        list: '.list',
        edit: '#edit',
        dt_search_val: '#search_org_input',
        opt: '.operate',
        del_modal: '#del_modal',
        ok_del_btn: '#ok_del',
        del: '.remove'
    };
    var url = {
        ztree: 'organization/rest/tree',
        item: 'organization/item?id=',
        dt: 'organization/rest/datagrid',
        del: 'organization/rest/del'
    };
    var dt;
    var parentId = 0;
    var org = {
        _del: function (id) {
            var self = this;
            $.ajax({
                data: {id: id},
                url: url.del,
                type: 'POST',
                success: function (data) {
                    self._dtSearch();
                    self._treeSearch();
                }
            }).fail(function () {
                alert(message["delete.failure"])
            });
        },
        _item: function (id) {
            //请求
            showLoading();
            $.ajax({
                url: url.item + id,
                type: 'GET',
                success: function (data) {
                    $(el.list).hide();
                    $(el.edit).html(data);
                    $(el.edit).show();
                    hideLoading();
                }
            });
        },
        _treeSearch: function () {
            org._loadTree();
        },
        _dtSearch: function () {
            var searchName = $(el.dt_search_val).val();
            dt.addAjaxParam('parentId', parentId);
            dt.addAjaxParam('name', searchName);
            dt.submitFilter();
        },
        _initDataTable: function () {
            dt = new Datatables();
            dt.init({
                src: $(el.dt_table),
                loadingMessage: message["datatable.loading"],
                dataTable: {
                    bStateSave: false,
                    ajax: {url: url.dt},
                    columns: [
                        DTColumnKit.order,
                        {data: "name"},
                        {data: "level", width: "60px"},
                        {data: "leaderName",width: "80px"},
                        {data: "parentName"},
                        {
                            data: "status",width: "60px", render: function (data) {
                            if (data === 0) {
                                return "<span class='text-color-red'>"+message["body.list.invalid"]+"</span>";
                            } else {
                                return "<span class='text-color-green'>"+message["body.list.effective"]+"</span>";
                            }
                        }
                        },
                        {
                            data: "id",
                            width: "100px",
                            render: function (data, type, row, meta) {
                                var html = '<a class="btn btn-xs btn-primary operate" href="javascript:;" ' +
                                        'data-id="' + data + '">'+message["org.user.list.operate"]+'</a>';
                                var removeHtml = '&nbsp;<a class="btn btn-xs btn-primary remove" ' +
                                        ' href="javascript:;"' +
                                        ' data-id="' + data + '">'+message["body.list.delete"]+'</a>';
                                if (row.level == 1 || row.level == 2) {
                                    removeHtml = '';
                                }
                                if (row.status === 0) {
                                    removeHtml = '';
                                }
                                return html + removeHtml;
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
        _treeNodeClick: function (event, treeId, treeNode) {
            parentId = treeNode.data.id;
            dt.addAjaxParam('parentId', parentId);
            dt.submitFilter();
        },
        /**
         * 勾选事件
         */
        _treeNodeCheck: function (event, treeId, treeNode) {
            var zTree  = $.fn.zTree.getZTreeObj('ul_tree_organization');
            var zTreeOne = zTree.getNodes()[0].children; //第一级
            if(zTreeOne.length === 0){
                return;
            }
            //去掉其他二级下的checkbox
            $.each(zTreeOne, function (index,item) {
                if(item.data.id !== treeNode.getParentNode().data.id){
                    item.children.forEach(function (childrenItem) {
                        zTree.checkNode(childrenItem,false,false);
                    });
                }
            });

            var ids = [];
            zTree.getCheckedNodes().forEach(function (item) {
                ids.push(item.data.id);
            });
//            parentId = treeNode.data.id;
            dt.addAjaxParam('parentId', ids.join(','));
            dt.submitFilter();
        },
        /**
         * 展开事件
         */
        _treeNodeExpand: function (event, treeId, treeNode) {
            var zTree  = $.fn.zTree.getZTreeObj('ul_tree_organization');
            var ztreeOne = zTree.getNodes()[0].children;
            if(ztreeOne.length === 0){
                return;
            }
            $.each(ztreeOne, function (index,item) {
                if(item.data.id !== treeNode.data.id){
                    zTree.expandNode(item,false,false);
                }
            });
        },
        _loadTree: function () {
            var self = this;
            var setting = {
                data: {
                    simpleData: {
                        enable: true,
                        idKey: 'id',//主键
                        pIdKey: 'pid'//上级id
                    }
                },
//                check:{
//                    chkStyle: 'checkbox',
//                    enable: true
//                },
                callback: {
                    onClick: self._treeNodeClick
//                    onCheck: self._treeNodeCheck,
//                    onExpand: self._treeNodeExpand
                }
            };
            var zNodes = [];
            var name = $(el.tree_search_input).val();
            var searchObj = {};
            if (name) {
                searchObj = {q: name};
            }
            //请求
            $.ajax({
                url: url.ztree,
                data: searchObj,
                type: 'GET',
                dataType: 'json',
                success: function (data) {
                    $.each(data, function (index,item) {
                        if(item.data.parentId === '0'){
                            item.nocheck = true;
                        }
                        if(item.data.parentId === '1'){
                            item.nocheck = true;
                            item.open = false;
                        }
                    });
                    zNodes = data;
                    if (typeof data === "string") {//not obj
                        zNodes = $.parseJSON(data);
                    }
                    $.fn.zTree.init($(el.ztree), setting, zNodes);
                },
                error: function (data) {
                    alert(data.responseJSON.message);
                }
            });
        }
    };
    $(document).ready(function () {
        $("dic").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
        org._initDataTable();
        org._loadTree();
        $(el.tree_search_btn).on('click', function () {
            org._treeSearch()
        });
        $(el.dt_search_btn).on('click', function () {
            org._dtSearch()
        });
        $('article').on('click', el.opt, function () {
            var id = $(this).data("id");
            org._item(id);
        });
        $(el.dt_table).on('click', el.del, function () {
            var id = $(this).data("id");
            $(el.del_modal).modal('show');
            $(el.ok_del_btn).data("id", id);

        });
        $(el.del_modal).on('click', el.ok_del_btn, function () {
            var id = $(this).data("id");
            org._del(id);
            $(el.del_modal).modal('hide');
        });
    });
</script>

<style type="text/css">
    ul[role='group'] li {
        cursor: pointer;
    }
    .modal-header{
        padding: 9px;
    }
</style>
</#compress>