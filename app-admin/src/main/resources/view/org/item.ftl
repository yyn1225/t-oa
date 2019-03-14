<#compress>
<article>
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
        </header>
        <div>
            <div class="widget-body no-padding ">
                <form class="smart-form" id="item-form">
                    <fieldset>
                        <input type="hidden" name="id" id="id" value="${(department.id)!}"/>
                        <input type="hidden" name="langVal" id="lang_val"/>
                        <label class="col col-1 text-align-right padding">
                            <span style="color: red">*</span>
                            <dic data-dic="organization.item.title.name"></dic>
                            :
                        </label>
                        <label class="col col-5 input">
                            <input type="text" name="name"  value="${(department.name)!}">
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <span style="color: red">*</span>
                            <dic data-dic="organization.item.title.code"></dic>
                            :
                        </label>
                        <label class="col col-5 input">
                            <input type="text" name="code"
                                   value="${(department.code)!}"/>
                        </label>
                    </fieldset>
                    <fieldset>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="organization.item.title.leader"></dic>
                            :
                        </label>
                        <label class="col col-5 input">
                            <select class="select2" style="width: 100%" name="leader">
                                <#list (users)! as item>
                                    <option value="${(item.id)!}"
                                        <#if department.leader??&&item.id ==department.leader>
                                            selected</#if>>
                                    ${(item.name)!}
                                    </option>
                                </#list>
                            </select>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="organization.list.table.parent"></dic>:</label>
                        <label class="col col-5 input">
                            <div class="dropdown dropdown-large dp">
                                <#if department.level?? && department.level == 1>
                                <input type="text" id="search_org_name" value="" data-toggle="dropdown" readonly/>
                                <#else>
                                <input type="text" id="search_org_name" value="${(department.parentName)!}" data-toggle="dropdown" readonly/>
                                </#if>
                                <input type="hidden" id="search_org_id" value="${(department.parent)!}" name="parent"/>
                                <ul class="dropdown-menu dropdown-menu-large row "
                                    style="margin: 0 !important;padding: 0!important;">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="input-group">
                                            <input class="form-control"
                                                   id="search_organization_item_input" placeholder=""
                                                   type="text">
                                            <span class="input-group-addon"
                                                  id="search_organization_item_btn">
                                                    <i class="fa fa-search"></i>
                                                </span>
                                        </div>
                                    </div>
                                    <div class="ztree" id="folder_tree"
                                         style="height: 200px;overflow-y: auto;"></div>
                                </ul>
                            </div>
                        </label>
                    </fieldset>
                    <fieldset style="height: 75px;">
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="organization.item.form.remark"></dic>:
                        </label>
                        <label class="col textarea" style="width: 91.66%">
                        <textarea rows="3" class="custom-scroll" name="remark">
                        ${(department.remark)!}
                        </textarea>
                        </label>
                    </fieldset>
                    <div class="widget-footer">
                        <div class=" text-align-right">
                            <a class="btn btn-primary btn-sm org-form-submit"
                               style="margin-right: 8px"><dic data-dic="button.submit"></dic></a>
                            <a class="btn btn-default btn-sm org-back"
                               style="margin-right: 38px"><dic data-dic="button.back"></dic></a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</article>
<div class="modal fade in" id="myModal" tabindex="-1" role="dialog" style="display: none;
padding-right: 17px;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×
                </button>
                <h4 class="modal-title">
                    <dic data-dic="organization.item.title.lanager"></dic>
                </h4>
            </div>
            <div class="modal-body no-padding">
                <form id="lang_form" class="smart-form" novalidate="novalidate">
                    <#if deptLang??&&(deptLang?size > 0)>
                        <#list deptLang as item>
                            <#if item_index==0>
                                <fieldset>
                                    <label class="col col-4 input">
                                        <select class="select2" style="width: 100%" name="parent">
                                            <#list languages as iitem>
                                                <option value="${(iitem.code)!}"
                                                        <#if iitem.code==item.lang>selected</#if>
                                                >
                                                ${(iitem.name)!}
                                                </option>
                                            </#list>
                                        </select>
                                    </label>
                                    <label class="col col-4 input">
                                        <input type="text" name="lang-val"
                                               value="${(item.nameLang)!}"/>
                                    </label>
                                    <label class="col col-4 input">
                                        <button type="button" class="btn btn-primary"
                                                id="add_lang_btn">
                                            <dic data-dic="header.list.button.add"></dic>
                                        </button>
                                    </label>
                                </fieldset>
                            <#else>
                                <fieldset>
                                    <label class="col col-4 input">
                                        <select style="width: 100%" name="parent" class="select2">
                                            <#list languages as iitem>
                                                <option value="${(iitem.code)!}"
                                                        <#if iitem.code==item.lang>selected</#if>
                                                >
                                                ${(iitem.name)!}
                                                </option>
                                            </#list>
                                        </select>
                                    </label>
                                    <label class="col col-4 input">
                                        <input type="text" name="lang-val"
                                               value="${(item.nameLang)!}"/>
                                    </label>
                                    <label class="col col-4 input">
                                        <button type="button" class="btn btn-primary remove-lang">
                                            <dic data-dic="body.list.delete"></dic>
                                        </button>
                                    </label>
                                </fieldset>
                            </#if>
                        </#list>
                    <#else>
                        <fieldset>
                            <label class="col col-4 input">
                                <select class="select2" style="width: 100%" name="parent">
                                    <#list languages as item>
                                        <option value="${(item.code)!}">
                                        ${(item.name)!}
                                        </option>
                                    </#list>
                                </select>
                            </label>
                            <label class="col col-4 input">
                                <input type="text" name="lang-val"
                                       value=""/>
                            </label>
                            <label class="col col-4 input">
                                <button type="button" class="btn btn-primary" id="add_lang_btn">
                                    <dic data-dic="header.list.button.add"></dic>
                                </button>
                            </label>
                        </fieldset>
                    </#if>

                    <footer style="padding-top: 20px;">
                        <button type="button" class="btn btn-primary lang-ok-btn">
                            <dic data-dic="button.confirm"></dic>
                        </button>
                    </footer>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<div id="langerTmpl" style="display: none;">
    <fieldset>
        <label class="col col-4 input">
            <select style="width: 100%" name="parent" class="tttt">
                <#list languages as item>
                    <option value="${(item.code)!}">
                    ${(item.name)!}
                    </option>
                </#list>
            </select>
        </label>
        <label class="col col-4 input">
            <input type="text" name="lang-val"
                   value=""/>
        </label>
        <label class="col col-4 input">
            <button type="button" class="btn btn-primary remove-lang">
                <dic data-dic="body.list.delete"></dic>
            </button>
        </label>
    </fieldset>
</div>
<script type="text/javascript">
    pageSetUp();
    el.lanager_btn = '#lanager_btn';
    el.add_lang_btn = '#add_lang_btn';
    el.lang_tmpl = '#langerTmpl';
    el.lang_remove_btn = '.remove-lang';
    el.first_row = '#first_row';
    el.lang_form = '#lang_form';
    el.departmentTree = '#folder_tree';
    el.tree_search_item_btn = "#search_organization_item_btn";
    el.tree_search_item_input = "#search_organization_item_input";
    el.search_org_name = '#search_org_name';
    el.search_org_id = '#search_org_id';
    el.back_btn = '.org-back';
    el.list = '.list';
    el.edit = '#edit';
    el.lang_ok_btn = '.lang-ok-btn';
    el.item_form = '#item-form';
    el.submit_btn = '.org-form-submit';
    el.lang_modal = '#myModal';
    el.lang_val = '#lang_val';
    var id = $('#id').val();
    if (!id) {
        id = 0;
    }
    var langArray;
    var form = $(el.item_form);

    var item = {
        _back: function () {
            $(el.list).show();
            $(el.edit).html('');
            $(el.edit).hide();
        },
        _addLang: function () {
            var html = $(el.lang_tmpl).html().replace("tttt", "select2");
            $('#lang_form fieldset:last').after(html);
            pageSetUp();
        },
        _submitForm: function () {
            var self = this;
            var dtThis = org;
            bootstrapValidator.validate();
            if (bootstrapValidator.isValid()) {
                showLoading();
                $.ajax({
                    url: 'organization/rest/save',
                    data: $(el.item_form).serialize(),
                    type: 'POST',
                    success: function (data) {
                        dt.submitFilter();
                        alert(message["alert.message.success"]);
                        self._back();
                        hideLoading();
                    },
                    error: function (data) {
                        if (data.responseJSON.code === 10047) {
                            alert(message["alert.message.encodedExist"], 3);
                        }else {
                            alert(message["alert.message.systemError"], 3);
                        }
                        hideLoading();
                    }
                });
            }
        },
        _removeLang: function (target) {
            $($($(target.currentTarget).parent()).parent()).remove();//找到父级的父级，然后删除
        },
        _getLangVal: function () {
            langArray = {};
            var fieldset = $('#lang_form fieldset');
            for (var i = 0; i < fieldset.length; i++) {
                var tmp = fieldset[i];
                var lang = $(tmp).find(".select2").select2("val");
                var val = $(tmp).find('input[name="lang-val"]').val();
                langArray[lang] = val;
            }
            $(el.lang_modal).modal('hide');
            $(el.lang_val).val(JSON.stringify(langArray));
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
                callback: {
                    onClick: self._folderTreeNodeClick
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
                url: url.ztree,
                data: searchObj,
                type: 'GET',
                dataType: 'json',
                success: function (data) {
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
        },
        _folderTreeNodeClick: function (event, treeId, treeNode) {
            event.preventDefault();
            var packageId = treeNode.data.id;
            var name = treeNode.data.name;
            $(el.search_org_name).val(name);
            $(el.search_org_id).val(packageId);
            $('.dropdown.dp.open').removeClass("open");
        }
    };
    item._initTree();
    $(el.tree_search_item_btn).on('click', function () {
        item._initTree();
    });
    $("[data-dic]").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });
    $(el.lanager_btn).on('click', function () {
        $(el.lang_modal).modal('toggle');
    });
    $(el.add_lang_btn).on('click', function () {
        item._addLang();
    });
    $(el.lang_form).on('click', el.lang_remove_btn, function (target) {
        item._removeLang(target);
    });
    $(el.back_btn).on('click', function () {
        item._back();
    });
    $(el.lang_ok_btn).on('click', function () {
        item._getLangVal();
    });
    $(el.submit_btn).on('click', function () {
        item._submitForm();
    });
    //表单校验
    form.bootstrapValidator({
        fields: {
            name: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    }
                }
            },
            level: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    },
                    number: {
                        message: message["prompt.message.digital"]
                    }
                }
            },
            code: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    }
                }
            }
        }
    });
    var bootstrapValidator = form.data('bootstrapValidator');
</script>
<style>
    .smart-form .button{
        float: none !important;
    }
</style>
</#compress>