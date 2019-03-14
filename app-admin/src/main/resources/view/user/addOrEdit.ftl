<#compress>
<article>
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
        </header>
        <div>
            <div class="widget-body no-padding">
                <form class="smart-form" id="addOrEdit-form">
                    <fieldset>
                        <input type="hidden" id="id" name="id" value="${(id?c)!}"/>
                        <label class="col col-1 text-align-right padding">
                            <span style="color:red;">*</span>
                            <dic data-dic="prompt.input.username"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="username" name="username" value="${(user.name)!}"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <span style="color:red;">*</span>
                            <dic data-dic="prompt.input.workNo"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="workno" name="workNo" value="${(user.workNo)!}"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <span style="color:red;">*</span>
                            <dic data-dic="prompt.input.phone"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="phone" name="phone" value="${(user.phone)!}"/>
                        </label>
                    </fieldset>
                    <fieldset>
                        <label class="col col-1 text-align-right padding">
                            <span style="color:red;">*</span>
                            <dic data-dic="prompt.input.area"></dic>
                        </label>
                        <label class="col col-3 input">
                            <div class="dropdown dropdown-large dp">
                                <input type="text" id="search_org_name" value="" data-toggle="dropdown"
                                       data-ids="${(ids)!}" readonly/>
                            <#--<input type="hidden" id="search_org_id" value="${(department.parent)!}" name="parent"/>-->
                                <ul class="dropdown-menu dropdown-menu-large row "
                                    style="margin: 0 !important;padding: 0!important;">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="input-group">
                                            <input class="form-control" id="search_organization_item_input"
                                                   placeholder=""
                                                   type="text">
                                            <span class="input-group-addon" id="search_organization_item_btn">
                                                    <i class="fa fa-search"></i>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="ztree" id="folder_tree" style="height: 200px;overflow-y: auto;"></div>
                                </ul>
                            </div>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <span style="color:red;">*</span>
                            <dic data-dic="prompt.input.loginName"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="loginName" name="loginName" <#if id?? >disabled</#if>
                                   value="${(user.loginName)!}"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <span style="color:red;">*</span>
                            <dic data-dic="prompt.input.email"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="email" name="email" value="${(user.email)!}"/>
                        </label>

                    <#--<label class="col col-3 input">-->
                    <#--<select class="select2" style="width: 100%"  name="area" id="area">-->
                    <#--<#list (departments)! as item>-->
                    <#--<#if item.level == 2>-->
                    <#--<option value="${(item.id)!}" <#if item.id == (user.area)!>selected</#if>>-->
                    <#--${(item.name)!}</option>-->
                    <#--<#else>-->
                    <#--<option value="${(item.id)!}" <#if 0 == user.area>selected</#if>>${(item.name)!}</option>-->
                    <#--</#if>-->
                    <#--</#list>-->
                    <#--</select>-->
                    <#--</label>-->
                    </fieldset>
                    <fieldset>
                        <label class="col col-1 text-align-right padding">
                            <span style="color:red;">*</span>
                            <dic data-dic="prompt.input.language"></dic>
                        </label>
                        <label class="col col-3 input">
                            <select class="select2" style="width: 100%" multiple name="language" id="language">
                                <#list (languages)! as item>
                                    <option value="${(item.code)!}"
                                            <#if (user.language!'')?contains(item.code)>selected</#if>>
                                    ${(item.name)!}</option>
                                </#list>
                            </select>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <span style="color:red;">*</span>
                            <dic data-dic="prompt.input.languageDefault"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input class="select2" value="${(user.languageDefault)!}" style="width: 100%"
                                   name="languageDefault"
                                   id="languageDefault" <#if id??><#else>disabled</#if>
                            />
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="org.user.list.sale.assistant"></dic>
                            :
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="assistant_name"  readonly="readonly" name="assistant_name" data-assistantId="${assistantId!}" value="${assistantName!}"/>

                        </label>
                    </fieldset>
                    <div class="widget-footer">
                        <div class=" text-align-right">
                            <a class="btn btn-primary btn-sm" id="ok"
                               style="margin-right: 8px">
                                <dic data-dic="body.list.submit"></dic>
                            </a>
                            <a class="btn btn-default btn-sm" onclick="back()"
                               style="margin-right: 38px">
                                <dic data-dic="body.list.back"></dic>
                            </a>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</article>
<!-- 模态框（Modal） -->
<div class="modal fade" id="assistant_model" tabindex="-1" role="dialog">
    <div class="modal-dialog" style="width: 950px;height:450px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="assistantModalLabel1">
                    <dic data-dic="panel.list.selection"></dic>
                </h4>
            </div>
            <div class="modal-body" id="assistant_opt_html" style="height:400px;overflow:auto; ">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="assistant_selection">
                    <dic data-dic="panel.list.selection"></dic>
                </button>
            </div>
        </div>
    </div>
</div>


<link rel="stylesheet" href="../static/js/plugin/ztree/css/metroStyle/metroStyle.css"/>
<script type="text/javascript" src="../static/js/plugin/ztree/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript">
    pageSetUp();
    var el = {
        tree_search_item_btn: "#search_organization_item_btn",
        tree_search_item_input: "#search_organization_item_input",
        search_org_name: '#search_org_name',
        assistant:'#assistant',
        search_org_id: '#search_org_id',
        departmentTree: '#folder_tree',
        ztree: '/organization/rest/tree'
    };
    var item = {
        /**
         * 勾选事件
         */
        _treeNodeCheck: function (event, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj('folder_tree');
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
            $(el.search_org_name).val(showNames.join(','));
            $(el.search_org_name).data('ids', ids.join(','));
            $(el.search_org_name).data('parentEq', parentEq);
        },
        /**
         * 展开事件
         */
        _treeNodeExpand: function (event, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj('folder_tree');
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
        _initTree: function () {
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
                    onCheck: self._treeNodeCheck,
                    onExpand: self._treeNodeExpand
                }
            };
            var zNodes = [];
            var name = $(el.tree_search_item_input).val();
            var searchObj = {};
            if (name) {
                searchObj = {q: name};
            }
            //请求
            $.ajax({
                url: el.ztree,
                data: searchObj,
                type: 'GET',
                dataType: 'json',
                success: function (data) {
                    var idsStr = $(el.search_org_name).data('ids') + "";
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
                    $(el.search_org_name).val(showNames.join(','));
                    $(el.search_org_name).data('ids', idsStr);
                    console.log(data);
                    zNodes = data;
                    if (typeof data === "string") {//not obj
                        zNodes = $.parseJSON(data);
                    }
                    $.fn.zTree.init($(el.departmentTree), setting, zNodes);
                },
                error: function (data) {
                    alert(data.responseJSON.message);
                }
            });
        }
    };
    item._initTree();
    $(el.tree_search_item_btn).on('click', function () {
        item._initTree();
    });
    var $form = $('#addOrEdit-form');

    $("[data-dic]").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });

    $("#ok").on("click", function () {
        var parentIds = $(el.search_org_name).data('ids');
        if (!parentIds) {
            alert(message['org.user.info.parentEq'], 2);
            return;
        }
        //获取表单对象
        var bootstrapValidator = $form.data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        var assistantVal  =$("#assistant_name").data('assistantId');
        if(typeof(assistantVal) == "undefined"){
            assistantVal = "";
        }
        if (bootstrapValidator.isValid()) {
            showLoading();
            $.ajax({
                url: '/user/save',
                data: $('form').serialize() + "&deptIds=" + $(el.search_org_name).data('ids')+"&assistant="+assistantVal,
                type: 'POST',
                success: function (data) {
                    dt.submitFilter();
                    $('#second').hide();
                    $('#first').show();
                    item_list._initTree_list();
                    hideLoading();
                },
                error: function (res) {
                    if (res.responseJSON.code === 20002) {
                        alert(message[res.responseJSON.message], 3);
                    } else {
                        alert(message['alert.message.repeatError'], 3);
                    }
                    hideLoading();
                }
            });
        }
    });

    function back() {
        $('#second').hide();
        $('#first').show();
        item_list._initTree_list();
    }

    //编辑页面加载的时候将第二个select2加载内容
    var langs = "${(user.language)!}".split(",");
    var dataList = [];
    $('#language option').each(function () {
        var vaule = $(this).val();
        var text = $(this).text();
        if (langs.indexOf(vaule) > -1) {
            dataList.push({id: vaule, text: text});
        }
    });

    //select2联动
    $("#languageDefault").select2({data: dataList, minimumResultsForSearch: -1});

    $("#language").on("change", function () {

        var selecteds = $("#language option:selected");

        var $languageDefault = $("#languageDefault");
        if (!selecteds || selecteds.length === 0) {
            $languageDefault.select2({
                data: []
            });
            $languageDefault.prop("disabled", true);
            return;
        }

        var values = [];
        selecteds.each(function (index, item) {
            values.push({
                text: $(item).text(),
                id: $(item).val()
            })
        });
        $languageDefault.select2({
            data: values,
            minimumResultsForSearch: -1
        });
        $languageDefault.removeAttr("disabled");
    });

    //表单校验
    $form.bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message['prompt.message.null']
                    }
                }
            },
            workNo: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message['prompt.message.null']
                    },
                    stringLength: {
                        max: 20,
                        message: message['prompt.message.workNo.length']
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: message['prompt.message.workNo.format']
                    }
                }
            },
//            phone : {
//                group:".input",
//                validators : {
////                    notEmpty : {
////                        message : message['prompt.message.null']
////                    }
////                    regexp: {
////                        regexp: /^1[34578]\d{9}$/,
////                        message: message['prompt.message.phone.format']
////                    }
//                }
//            },
            loginName: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message['prompt.message.null']
                    },
                    stringLength: {
                        max: 20,
                        message: message['prompt.message.loginName.length']
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: message['prompt.message.loginName.format']
                    }
                }
            },
            email: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message['prompt.message.null']
                    },
                    emailAddress: {
                        message: message['prompt.message.email.format']
                    }
                }
            },
            area: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message['prompt.message.null']
                    }
                }
            },
            language: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message['prompt.message.null']
                    }
                }
            },
            languageDefault: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message['prompt.message.null']
                    }
                }
            }

        }
    });

    $(function () {
        var $input = $("#assistant_name");
        var outHtml = $input.prop("outerHTML");
        var parent = $input.parent();
        var group = "<div class='input-group input-group-sm'>" +
                outHtml +
                "<div class='input-group-btn'>" +
                "<button class='btn btn-default' id='assistant_colse' type='button' onclick='assistatnClear()' style='height: 32px;'>" +
                "<i class='fa fa-close'></i>" +
                "</button>"  +
                "<button class='btn btn-default' id = 'assistant_open' type='button' onclick='addAssistant()' style='height: 32px;'>" +
                "<i class='fa fa-list'></i>" +
                "</button>" +
                "</div>";
        $(parent).html(group);

        /*$("#assistant_open").parent().find("button").off("click").click(function () {
            ;
        });*/
    });

    function addAssistant() {

        showLoading();
        $.ajax({
            url: "/user/assistant",
            data: {},
            type: "get",
            success: function (data) {

                $("#assistant_opt_html").html(data);
                $("#assistant_model").modal("show");
                hideLoading();
            }
        });

        /*销售选择*/
        $("#assistant_selection").off("click").click(function (e) {
            e.preventDefault();

            var ids = dtn.getSelectedRowsTableData();
            console.log(ids);

            if (!ids || ids.length == 0) {
                alert(message["org.user.list.sale.one.assistant"]);
                return false;
            } else if (ids.length > 1) {
                alert(message["org.user.list.sale.one.assistant"]);
                return false;
            }

            $("#dt_basic_assistant").find(":checkbox:checked").each(function () {
                var val = $(this).parent().next().text();
                console.log("val" + val);
            });

            console.log(ids[0]["id"]);
            $("#assistant_name").data('assistantId',ids[0]["id"]);
            $("#assistant_name").val(ids[0]["username"]);

            $("#assistant_opt_html").html("");
            $("#assistant_model").modal("hide");
        });
    }

    function assistatnClear(){
        $("#assistant_name").data('assistantId',"");
        $("#assistant_name").val("");
    }
</script>
<style>
    .smart-form .button {
        float: none !important;
    }
</style>
</#compress>