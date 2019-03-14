<#compress>
<div class="row" style="margin-top: 20px;">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <input type="hidden" id="fileId" value="${(fileId?c)!}">
        <div class="jarviswidget" data-widget-editbutton="false" style="margin-bottom: 0 !important;">
            <header>
                <h2>
                    <dic data-dic="file.list.item.give"></dic>
                </h2>
                <a class="btn btn-primary btn-xs" style="float: right" id="role_selectAll">
                    <dic data-dic="file.list.item.selectAll"></dic>
                </a>
            </header>
            <div>
                <div class="widget-body no-padding smart-form">
                    <ul style="overflow-y: auto;height: 200px;list-style: none;padding-left: 20px;padding-top: 20px" id="role">
                        <#list roleList as item>
                            <li style="margin-bottom: 5px" class="check_group">
                                <label class="checkbox option block mn">
                                    <input class="role_check" type="checkbox"
                                           <#if item.security?? &&( item.security == 1 || item.security == 2 )>checked </#if>
                                           value="${(item.id?c)!}">
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
<#--<link rel="stylesheet" href="../static/js/plugin/ztree/css/metroStyle/metroStyle.css"/>-->
<script type="text/javascript" src="../static/js/plugin/ztree/js/jquery.ztree.all.min.js"/>
<script type="text/javascript">
    el.uploaderForm = '#box_file_upload';
    el.series_ztree = '#user_tree';
    el.package_ztree = '#package_tree';
    el.user_tree_search_btn = '#search_series_btn';
    el.package_tree_search_btn = '#search_package_btn';
    el.user_tree_search_input = '#search_series_input';
    el.package_tree_search_input = '#search_package_input';
    el.user_tree_selectAll = '#user-selectAll';
    el.role_selectAll = '#role_selectAll';
    el.role_list = '#role';

    url.fileSave = '/api/file/save';
    url.series_ztree = '/api/file/security/tree';
    url.package_ztree = '/api/folder/tree';


    var securitySelectedSid = new Array();
    $.ajax({
        url: '/api/file/security/selected',
        data: {id: $("#fileId").val()},
        sync: false,
        type: 'GET',
        success: function (data) {
            var sids = data.sids;
            if (sids) {
                sids.forEach(function (val) {
                    securitySelectedSid.push(val + '');
                })
            }
            ztrees.seriesTree();
        }

    });


    var maxFiles = 1;
    var addFileSize = 0;
    var complateFileSize = 0;
    if ($("#id").val()) {
        maxFiles = 1;
    } else {
        maxFiles = 10;
    }
    var seriesTree;
    var packageTree;
    Array.prototype.unique3 = function () {
        var res = [];
        var json = {};
        for (var i = 0; i < this.length; i++) {
            if (!json[this[i]]) {
                res.push(this[i]);
                json[this[i]] = 1;
            }
        }
        return res;
    };
    var itemSeletedSids = new Array();
    var itemSeletedPids = new Array();
    var fileItem = {
        _submitFile: function () {
            var seriesIds = new Array();
            var packageIds = new Array();
            itemSeletedSids.unique3().forEach(function (val) {
                seriesIds.push(val);
            });
            itemSeletedPids.unique3().forEach(function (val) {
                packageIds.push(val);
            });
            $.ajax({
                url: url.fileSave,
                data: {
                    ids: $('.attachment-id').val(),
                    packageIds: packageIds.join(','),
                    seriesIds: seriesIds.join(','),
                    fileType: $('.file-type-btn-active').data("type")
                },
                type: 'POST',
                success: function (data) {
                    console.log(data);
                    $(el.fileItemModal).modal('hide');
                    file._dtRefuse();
                }
            });
        }
    };
    var ztrees = {
        _remove: function (arr, val) {
            for (var i = 0; i < arr.length; i++) {
                if (arr[i] == val) {
                    arr.splice(i, 1);
                    break;
                }
            }
        },
        _treeNodeClickPackage: function (event, treeId, treeNode) {
            var packageId = treeNode.data.id;
            if (treeNode.checked) {
                var packageNode = packageTree.getCheckedNodes(true);
                packageNode.forEach(function (val) {
                    itemSeletedPids.push(val.data.id + '');
                });
            } else {
                ztrees._remove(itemSeletedPids, packageId);
            }
        },
        _treeNodeClickSeries: function (event, treeId, treeNode) {
            var seriesId = treeNode.data.id;
            if (treeNode.checked) {
                var seriesNode = seriesTree.getCheckedNodes(true);
                seriesNode.forEach(function (val) {
                    itemSeletedSids.push(val.data.id + '');
                });
            } else {
                ztrees._remove(itemSeletedSids, seriesId);
            }
        },
        _treeSearchPackage: function () {
            ztrees.packageTree();
        },
        _treeSearchSeries: function () {
            ztrees.seriesTree();
        },
        seriesTree: function () {
            var self = this;
            var setting = {
                check: {
                    enable: true,
                    chkboxType: {"Y": "s", "N": "s"}
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: 'id',//主键
                        pIdKey: 'pid'//上级id
                    }
                },
                callback: {
                    onCheck: self._treeNodeClickSeries
                }
            };
            var zNodes = [];
            var searchObj = {};
            var name = $(el.user_tree_search_input).val();
            if (name) {
                searchObj = {name: name};
            }
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
                    if (securitySelectedSid) {
                        zNodes.forEach(function (val) {
                            securitySelectedSid.unique3().forEach(function (vv) {
                                if (val.data.id == vv) {
                                    val.checked = true;
                                }
                            });
                        });
                    }
                    seriesTree = $.fn.zTree.init($(el.series_ztree), setting, zNodes);
//                    seriesTree.expandAll(false);
                },
                error: function (data) {
                    alert(data.responseJSON.message);
                }
            });
        },
        packageTree: function () {
            var self = this;
            var setting = {
                check: {
                    enable: true,
                    chkboxType: {"Y": "", "N": ""}
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: 'id',//主键
                        pIdKey: 'pid'//上级id
                    }
                },
                callback: {
                    onCheck: self._treeNodeClickPackage
                }
            };
            var zNodes = [];
            var searchObj = {};
            var name = $(el.package_tree_search_input).val();
            if (name) {
                searchObj = {q: name};
            }
            $.ajax({
                url: url.package_ztree,
                data: searchObj,
                type: 'GET',
                dataType: 'json',
                success: function (data) {
                    zNodes = data;
                    if (typeof data === "string") {//not obj
                        zNodes = $.parseJSON(data);
                    }
                    if (itemSeletedPids) {
                        zNodes.forEach(function (val) {
                            itemSeletedPids.unique3().forEach(function (vv) {
                                if (val.data.isParent) {

                                }else {
                                    if (val.data.id == vv) {
                                        val.checked = true;
                                    }
                                }
                            });
                        });
                    }
                    packageTree = $.fn.zTree.init($(el.package_ztree), setting, zNodes);
                },
                error: function (data) {
                    alert(data.responseJSON.message);
                }
            });
        }
    };
    ztrees.seriesTree();
    ztrees.packageTree();
    $(el.package_tree_search_btn).click(function () {
        ztrees._treeSearchPackage();
    });
    $(el.user_tree_search_btn).click(function () {
        ztrees._treeSearchSeries();
    });
    $(el.role_selectAll).click(function () {
        if($(el.role_list + ' .role_check:checked').length === $('#roleBatch .role_check').length){
            $(el.role_list + ' .role_check').prop('checked',false);
        }else{
            $(el.role_list + ' .role_check').prop('checked',true);
        }
    });
    $('[data-dic]').each(function () {
        $(this).html(message[$(this).data("dic")]);
    });
</script>
<style>
    .role-icon {
        width: 20px;
        height: 20px;
        border: solid 1px #e5e5e5;
        float: left;
        text-align: center;
    }
</style>
</#compress>