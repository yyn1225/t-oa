<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div id="first">
        <div class="jarviswidget" data-widget-editbutton="false">
            <header>
                <h2><dic data-dic="header.list.search"></dic></h2>
            </header>
            <div>
                <div class="widget-body no-padding">
                    <form class="smart-form">
                        <fieldset>
                            <div class="row">
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="org.user.list.name"></dic>:
                                </label>
                                <label class="col col-2 input">
                                    <input type="text" id="name-query"/>
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="org.user.list.workno"></dic>:</label>
                                <label class="col col-2 input">
                                    <input type="text" id="workno-query"/>
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="org.user.list.phone"></dic>:
                                </label>
                                <label class="col col-2 input">
                                    <input type="text" id="phone-query"/>
                                </label>
                                <label class="col col-3 text-align-right">
                                    <a class="btn btn-primary query-btn" id="btn_query">
                                        <dic data-dic="header.list.button.search"></dic></a>
                                    </a>
                                </label>
                            </div>
                        </fieldset>
                        <fieldset>
                           <div  class="row">
                               <label class="col col-1 text-align-right padding">
                                   <dic data-dic="prompt.input.area"></dic>
                               </label>
                               <label class="col col-2 input">
                                    <div class="dropdown dropdown-large dp">
                                       <input type="text" id="search_org_name_list" value="" data-toggle="dropdown"
                                              data-ids="${(ids)!}" readonly/>
                                       <ul class="dropdown-menu dropdown-menu-large row "
                                           style="margin: 0 !important;padding: 0!important;">
                                           <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                               <div class="input-group">
                                                   <input class="form-control" id="search_organization_item_input_list"
                                                          placeholder=""
                                                          type="text">
                                                   <span class="input-group-addon" id="search_organization_item_btn_list">
                                                    <i class="fa fa-search"></i>
                                            </span>
                                               </div>
                                           </div>
                                           <div class="ztree" id="folder_tree_list" style="height: 200px;overflow-y: auto;"></div>
                                       </ul>
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
                <span class="widget-icon"> <i class="fa fa-table"></i></span>
                <h2 data-dic="org.user.list.title"></h2>
                <a class="btn btn-primary btn-xs operate" style="float: right"
                   data-user-id="0">
                    <dic data-dic="header.list.button.add"></dic>
                </a>
                <a class="btn btn-primary btn-xs operate-role" style="float: right;margin-top:7px;"
                   data-user-id="0">
                    <dic data-dic="body.list.role"></dic>
                </a>
            </header>
            <div>

                <div class="jarviswidget-editbox"></div>
                <div class="widget-body no-padding">
                    <table id="dt_basic_user" class="table table-striped table-bordered table-hover"
                           width="100%">
                        </select>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div id="second" style="display: none"></div>
</article>

<!-- 模态框（Modal） -->
<div class="modal fade" id="resource_model" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    资源设置
                </h4>
            </div>
            <div class="modal-body">
                <div id="resource_opt_html"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="resource_update">
                    <dic data-dic="button.confirm"></dic>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="role_model" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel1"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel1">
                    <dic data-dic="body.list.role"></dic>
                </h4>
            </div>
            <div class="modal-body">
                <div id="role_opt_html"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="role_update">
                    <dic data-dic="button.confirm"></dic>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="role_new_model" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel1"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel1">
                    <dic data-dic="body.list.role"></dic>
                </h4>
            </div>
            <div class="modal-body">
                <div id="role_new_opt_html"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="role_new_save">
                    <dic data-dic="button.confirm"></dic>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="ru_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel1">
                    角色设置
                </h4>
            </div>
            <div class="modal-body">
                <div id="ru_opt_html"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="ru_update">
                    <dic data-dic="button.confirm"></dic>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<#--删除提示弹窗-->
<div class="modal fade" id="delete-prompt" tabindex="-1" role="dialog">
    <div class="modal-dialog" style="width: 350px;">
        <input type="hidden" value="0" id="formula-type">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    <dic data-dic="admin.window.attention"></dic>
                </h4>
            </div>
            <div class="modal-body no-padding">
                <form class="smart-form">
                    <fieldset class="textPop">
                        <dic data-dic="prompt.message.delete"></dic>
                    </fieldset>
                    <footer>
                        <button type="button" id="delete-choose" class="btn btn-primary">
                            <dic data-dic="button.confirm"></dic>
                        </button>
                        <button type="button" class="btn btn-default" id="delete-close"
                                data-dismiss="modal">
                            <dic data-dic="button.cancel"></dic>
                        </button>
                    </footer>
                </form>
            </div>
        </div>
    </div>
</div>
<link rel="stylesheet" href="../static/js/plugin/ztree/css/metroStyle/metroStyle.css"/>
<script type="text/javascript" src="../static/js/plugin/ztree/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript">
    var dt;
    var deleteId;
    var $delete_prompt =$('#delete-prompt');
    $(document).ready(function () {
        $("[data-dic]").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });

        dt = new Datatables();
        dt.init({
            src: $("#dt_basic_user"),
            loadingMessage: message["datatable.loading"],
            dataTable: {
                bStateSave: false,
                ajax: {url: "user/rest/list"},
                columns: [
                    DTColumnKit.checkbox,
                    // DTColumnKit.order,
                    {data: "username", title: message["org.user.list.name"]},
                    {data: "workNo", title: message["org.user.list.workno"]},
                    {data: "phone", title: message["org.user.list.phone"]},
                    {data: "languageDefault", title: message["org.user.list.language"], width: "80px"},
                    {data: "areaName", title: message["org.user.list.area"], width: "70px",
                        render: function (data,type,row) {
                            if (row.area === 0) {
                                return message["organization.item.form.center"];
                            }
                            return data;
                        }},
                    {data: "area", "visible": false},
                    {
                        data: "departmentName", title: message["prompt.input.area"],width: "150px",
                        render: function (data,type,row) {
                            return '<div class="td-show-xxs" '+
                                    'title="'+ data +'">'+ data +'</div>';
                        }
                    },
                    {
                        data: "assistantName", title: message["org.user.list.sale.assistant"],width: "100px"
                    },
                    {data: "status",title: message["org.user.list.status"], width:"60px",
                        render: function (data,type,full) {
                            if(full.deleteFlag==1){
                                return '<span class="center-block padding-5 label label-danger">'+ message["body.list.deleted"] + "</span>"
                            }
                            if (data === 0) {
                                return '<span class="center-block padding-5 label label-warning">'+message["body.list.invalid"]+"</span>";
                            } else {
                                return '<span class="center-block padding-5 label label-success">'+message["body.list.effective"]+"</span>";
                            }
                        }
                    },
                    {
                        data: "id", width:"200", title: message["org.user.list.operate"],
                        render: function (data, type, row) {
                            var editHtml = '<a class="btn btn-xs btn-primary operate" href="javascript:;" ' +
                                    'data-user-id="' + data + '">'+message["body.list.edit"]+'</a>&nbsp;';
                            var roleHtml = '<a class="btn btn-xs btn-primary role-opt" href="javascript:;" ' +
                                    'data-id="' + data + '">'+message["body.list.role"]+'</a>&nbsp;';
//                            var resourceHtml = '<a class="btn btn-xs btn-primary resource-opt" href="javascript:;" ' +
//                                    'data-id="' + data + '">'+message["body.list.resource"]+'</a>&nbsp;';
                            var deleteHtml='<a class="btn btn-xs btn-primary delete-opt" href="javascript:;" ' +
                                    'data-id="' + data + '">'+message["body.list.delete"]+'</a>&nbsp;';
                            var enableHtml='<a class="btn btn-xs btn-primary enable-opt" href="javascript:;" ' +
                                    'data-id="' + data + '">'+message["body.list.enable"]+'</a>&nbsp;';
                            if(row.deleteFlag == 0) {
                                return editHtml + roleHtml  + deleteHtml;
                            }
                            return editHtml + enableHtml;
                        }
                    }
                ],
                columnDefs: [{
                    orderable: false,
                    targets: [0,1,2,3,4,5,6,7,8,9,10]
                }],
                drawCallback: function () {
                }
            }
        });

        $('article').on('click', '.operate', function () {
            var id = $(this).data("user-id");
            var url = "/user/manage";
            var $second = $('#second');
            var $first = $('#first');
            showLoading();
            $.ajax({
                url: url,
                type: 'GET',
                data: {id: id},
                success: function (data) {
                    $first.hide();
                    $second.show();
                    $second.html(data);
                    hideLoading();
                }
            });
        });
        $('article').on('click', '.role-opt', function () {
            var id = $(this).data("id");
            var url = "/user/role/item";
            var $modelHtml = $("#role_opt_html");
            var $model = $("#role_model");
            $.ajax({
                url: url,
                type: 'GET',
                data: {userId: id},
                success: function (data) {
                    $model.modal('show');
                    $modelHtml.html(data);
                }
            });
        });

        $('article').on('click', '.operate-role', function () {
            var userIds = dt.getSelectedRowsData();
            if(userIds.length == 0){
                alert(message["product.series.select"]);
                return false;
            }
            var url = "/user/role/itemList";
            var $modelHtml = $("#role_new_opt_html");
            var $model = $("#role_new_model");
            $.ajax({
                url: url,
                type: 'GET',
                data: {userIds: JSON.stringify(userIds)},
                success: function (data) {
                    $model.modal('show');
                    $modelHtml.html(data);
                }
            });
        });
        $('article').on('click', '.enable-opt', function () {
            var id = $(this).data("id");
            var url = "/user/rest/enable";
            $.ajax({
                url: url,
                type: 'POST',
                data: {id: id},
                success: function (data) {
                    alert(message['alert.message.success']);
                    dt.submitFilter();
                },
                error:function () {
                    alert(message['alert.message.systemError'], 3);
                }
            });
        });

        //删除提示弹窗
         $delete_prompt.modal({
             backdrop: 'static',
             keyboard: false,
             show: false
         });

         $('#delete-choose').click(function () {
             $delete_prompt.modal("hide");
             $.ajax({
                 url: "/user/rest/delete",
                 type: 'POST',
                 data: {id: deleteId},
                 success: function (data) {
                     alert(message['alert.message.success']);
                     dt.submitFilter();
                 }
             });

         });
         $('#delete-close').click(function () {
             $delete_prompt.modal("hide");
         });
        $('article').on('click', '.delete-opt', function () {
            $delete_prompt.modal("show");
            deleteId = $(this).data("id");
        });
        $("#btn_query").click(function () {
            showLoading();
            var areaIds = $(el_list.search_org_name_list).data('ids');
            dt.addAjaxParam('name', $('#name-query').val());
            dt.addAjaxParam('workno', $('#workno-query').val());
            dt.addAjaxParam('phone', $('#phone-query').val());
            dt.addAjaxParam('areaIds','['+areaIds+']');
            dt.submitFilter();
            hideLoading();
        });
        $('#role_update').on('click', function () {
            var id_array = new Array();
            var roleIds = $('#roleId').select2().val();
            roleIds.forEach(function(val){
                id_array.push(val);
            });
            var idstr = id_array.join(',');
            if (idstr == '' || !idstr) {
                alert(message["alert.message.noChooseRole"], 2);
                return false;
            }
            var userId = $('#userId').val();
            $.ajax({
                url: '/api/user/role/update',
                type: 'POST',
                data: {userId: userId, roleIds: idstr},
                success: function (data) {
                    $("#role_model").modal('hide');
                },
                error: function () {
                    alert(message['alert.message.systemError'],3);
                }
            });
        });

        $('#role_new_save').on('click', function () {
            var id_array = new Array();
            var roleIds = $('#roleId').select2().val();

            if(roleIds == null){
                alert(message["alert.message.noChooseRole"], 2);
                return false;
            }
            roleIds.forEach(function(val){
                id_array.push(val);
            });
            var idstr = id_array.join(',');
            if (idstr == '' || !idstr ) {
                alert(message["alert.message.noChooseRole"], 2);
                return false;
            }
            var userIds = $('#userIds').val();
            $.ajax({
                url: '/api/user/role/newSave',
                type: 'POST',
                data: {userIds: userIds, roleIds: idstr},
                success: function (data) {
                    $("#role_new_model").modal('hide');
                },
                error: function () {
                    alert(message['alert.message.systemError'],3);
                }
            });
        });
        $('#resource_update').on('click', function () {
            var id_array = new Array();
            $('input:checked').each(function () {
                id_array.push($(this).val());
            });
            var idstr = id_array.join(',');
            if (idstr == '' || !idstr) {
                alert(message['alert.message.noChooseResource'],2);
                return false;
            }
            $.ajax({
                url: '/api/user/resource/update',
                type: 'POST',
                data: {roleId: $('#id').val(), resourceIds: idstr},
                success: function (data) {
                    $("#resource_model").modal('hide');
                },
                error: function () {
                    alert(message['alert.message.systemError'],3);
                }
            });
        });

        $('#role_model').on('hide.bs.modal', function () {
            $('#role_opt_html').html('');
        });

        $('#role_new_model').on('hide.bs.modal', function () {
            $('#role_new_opt_html').html('');
        });
        $('#resource_model').on('hide.bs.modal', function () {
            $('#resource_opt_html').html('');
        });
    });

    var el_list = {
        tree_search_item_btn_list: "#search_organization_item_btn_list",
        tree_search_item_input_list: "#search_organization_item_input_list",
        search_org_name_list: '#search_org_name_list',
        search_org_id_list: '#search_org_id_list',
        departmentTree_list: '#folder_tree_list',
        ztree_list: '/organization/rest/tree'
    };
    var item_list = {
        /**
         * 勾选事件
         */
        _treeNodeCheck_list: function (event, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj('folder_tree_list');
            var zTreeOne = zTree.getNodes()[0].children; //第一级
            if (zTreeOne.length === 0) {
                return;
            }

            var oneCheckedCount = 0;
            var parentEq = true;
            var oneParent;
            zTreeOne.forEach(function (item) {
                if (item.data.parentId === '1' && item.checked) {
                    oneCheckedCount++;
                    oneParent = item;
                }
            });
            if (oneCheckedCount >= 2) {
                parentEq = false;
            }


            var checkDatas = zTree.getCheckedNodes();
            var showNames = [];
            var ids = [];
            $.each(checkDatas, function (index, item) {
                if (item.data.parentId === '0') {
                    checkDatas.splice(index, 1);
                    showNames.push('总部');
                    ids.push('0');
                    return false;
                }
            });
            checkDatas.forEach(function (item) {
                showNames.push(item.data.name);
                ids.push(item.data.id);
                if (oneParent) {
                    if (parentEq
                            && item.data.parentId !== '1'
                            && oneParent.data.id !== item.data.parentId) {
                        parentEq = false;
                    }
                } else {
                    if (parentEq && item.data.parentId !== '1' && checkDatas[0].data.parentId !== item.data.parentId) {
                        parentEq = false;
                    }
                }

            });
            $(el_list.search_org_name_list).val(showNames.join(','));
            $(el_list.search_org_name_list).data('ids', ids.join(','));
            $(el_list.search_org_name_list).data('parentEq', parentEq);
        },
        /**
         * 展开事件
         */
        _treeNodeExpand_list: function (event, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj('folder_tree_list');
            var ztreeOne = zTree.getNodes()[0].children;
            if (ztreeOne.length === 0) {
                return;
            }
            $.each(ztreeOne, function (index, item) {
                if (item.data.id !== treeNode.data.id) {
                    zTree.expandNode(item, false, false);
                }
            });
        },
        _initTree_list: function () {
            var self = this;
            var setting = {
                data: {
                    simpleData: {
                        enable: true,
                        idKey: 'id',//主键
                        pIdKey: 'pid'//上级id
                    }
                },
                check: {
                    chkStyle: 'checkbox',
                    chkboxType: {"Y": "", "N": ""},
                    enable: true
                },
                callback: {
                    onClick: self._treeNodeClick,
                    onCheck: self._treeNodeCheck_list,
                    onExpand: self._treeNodeExpand_list
                }
            };
            var zNodes = [];
            var name = $(el_list.tree_search_item_input_list).val();
            var searchObj = {};
            if (name) {
                searchObj = {q: name};
            }
            //请求
            $.ajax({
                url: el_list.ztree_list,
                data: searchObj,
                type: 'GET',
                dataType: 'json',
                success: function (data) {
                    var idsStr = $(el_list.search_org_name_list).data('ids') + "";
                    var ids = idsStr.split(',');
                    var showNames = [];
                    $.each(data, function (index, item) {
                        if (item.data.parentId === '1') {
                            item.open = false;
                        }
                        if ($.inArray(item.data.id, ids) >= 0) {
                            item.checked = true;
                            showNames.push(item.data.name);
                        }
                    });
                    $(el_list.search_org_name_list).val(showNames.join(','));
                    $(el_list.search_org_name_list).data('ids', idsStr);
                     zNodes = data;
                    if (typeof data === "string") {//not obj
                        zNodes = $.parseJSON(data);
                    }
                    $.fn.zTree.init($(el_list.departmentTree_list), setting, zNodes);
                },
                error: function (data) {
                    alert(data.responseJSON.message);
                }
            });
        }
    };
    item_list._initTree_list();
    $(el_list.tree_search_item_btn_list).on('click', function () {
        item_list._initTree_list();
    });

</script>

<style>
    .padding {
        padding-top: 7px;
        padding-left: 0 !important;
        padding-right: 0 !important;
    }
    .modal-header{
        padding: 9px;
    }

    .smart-form .button {
        float: none !important;
    }
    td {
        /*padding: 8px 6px !important;*/
        vertical-align: middle !important;
    }
</style>
</#compress>