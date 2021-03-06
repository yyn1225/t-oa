<#compress>
<form class="smart-form">
    <fieldset>
        <input type="hidden" name="id" id="file_edit_id" value="${(file.id)!}"/>
        <input type="hidden" name="attId" id="attachmentId" value="0"/>
        <!--用来存放文件信息-->
        <div class="btns" style="float: left;width: 20%">
            <div id="picker"><dic data-dic="file.list.item.select"></dic></div>
        </div>
        <div id="thelist" class="uploader-list">
            <div id="WU_FILE_0" class="item" style="float: left;width: 70%">
                <h4 class="info">${(file.name)!}</h4>
                <p class="state"><dic data-dic="file.list.item.already"></dic></p>
                <div class="progress progress-striped active" style="display: none;">
                    <div class="progress-bar" role="progressbar" style="width: 100%;"></div>
                </div>
            </div>
        </div>
    </fieldset>
</form>
<div class="row">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 right" style="margin-top: 20px;">
        <div class="btn-group">
            <button type="button"
                    class="file-type btn btn-default <#if file.category??&&file.category==1>file-type-btn-active</#if>"
                    data-type="1"><dic data-dic="admin.dashboard.news"></dic></button>
            <button type="button"
                    class="file-type btn btn-default <#if file.category??&&file
                    .category==2>file-type-btn-active</#if>"
                    data-type="2"><dic data-dic="product.panel.list.name"></dic></button>
            <button type="button"
                    class="file-type btn btn-default <#if file.category??&&file
                    .category==3>file-type-btn-active</#if>"
                    data-type="3"><dic data-dic="admin.dashboard.solution"></dic></button>
            <button type="button"
                    class="file-type btn btn-default <#if file.category??&&file
                    .category==4>file-type-btn-active</#if>"
                    data-type="4"><dic data-dic="product.spare.list.brand"></dic></button>
        </div>
    </div>
</div>
<div class="row" style="margin-top: 20px;">
    <div class="col col-md-6">
        <div class="jarviswidget" data-widget-editbutton="false" style="margin-bottom: 0;">
            <header>
                <h2>
                    <dic data-dic="package.list.treeName"></dic>
                </h2>
            </header>
            <div>
                <div class="widget-body no-padding">
                    <div class="widget-body-toolbar">
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="input-group">
                                    <input class="form-control" id="search_package_input"
                                           placeholder=""
                                           type="text">
                                    <span class="input-group-addon" id="search_package_btn">
                                    <i class="fa fa-search"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <ul id="package_tree" class="ztree" style="overflow-y: scroll;height:
                200px;"></ul>
                </div>
            </div>
        </div>
    </div>

    <div class="col col-md-6">
        <div class="jarviswidget" data-widget-editbutton="false" style="margin-bottom: 0">
            <header>
                <h2>
                    <dic data-dic="series.list.treeName"></dic>
                </h2>
            </header>
            <div>
                <div class="widget-body no-padding">
                    <div class="widget-body-toolbar">
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="input-group">
                                    <input class="form-control" id="search_series_input"
                                           placeholder=""
                                           type="text">
                                    <span class="input-group-addon" id="search_series_btn">
                                    <i class="fa fa-search"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <ul id="series_tree" class="ztree" style="overflow-y: scroll;height:
                200px;"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    url.fileUpdateUrl = '/api/file/update';
    el.series_ztree = '#series_tree';
    el.package_ztree = '#package_tree';
    el.series_tree_search_btn = '#search_series_btn';
    el.package_tree_search_btn = '#search_package_btn';
    el.series_tree_search_input = '#search_series_input';
    el.package_tree_search_input = '#search_package_input';

    url.fileSave = '/api/file/save';
    url.package_ztree = '/api/folder/tree';
    $("dic").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });
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
    var seriesTree;
    var packageTree;
    var editSelectedPid = new Array();
    var editSelectedSid = new Array();
    $.ajax({
        url: '/api/file/selected',
        data: {id: $("#file_edit_id").val()},
        sync: false,
        type: 'GET',
        success: function (data) {
            var sids = data.sids;
            var pids = data.pids;
            if (sids) {
                sids.forEach(function (val) {
                    editSelectedSid.push(val + '');
                })
            }
            if (pids) {
                pids.forEach(function (val) {
                    editSelectedPid.push(val + '');
                })
            }
            editZtrees.seriesTree();
            editZtrees.packageTree();
        }

    });
    uploader.addButton({
        id: '#picker',
        innerHTML: '选择文件'
    });
    uploader.on('fileQueued', function (file) {
        $('#thelist').empty();
        $('#thelist').append('<div id="' + file.id + '" class="item">' +
                '<h4 class="info">' + file.name + '</h4>' +
                '<p class="state">'+ message["file.list.item.wait"] +'...</p>' +
                '</div>');
        uploader.upload();
    });
    uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id),
                $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress progress-striped active">' +
                    '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                    '</div>' +
                    '</div>').appendTo($li).find('.progress-bar');
        }

        $li.find('p.state').text(message["file.list.item.uploading"]);

        $percent.css('width', percentage * 100 + '%');
    });
    uploader.on('uploadSuccess', function (file, data) {
        $('#attachmentId').val(data.id);
        $('#' + file.id).find('p.state').text(message["file.list.item.already"]);
    });

    uploader.on('uploadError', function (file) {
        $('#' + file.id).find('p.state').text(message["file.list.item.error"]);
    });

    uploader.on('uploadComplete', function (file) {
        $('#' + file.id).find('.progress').fadeOut();
    });
    var editZtrees = {
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
                    editSelectedPid.push(val.data.id);
                });
            } else {
                editZtrees._remove(editSelectedPid, packageId);
            }
        },
        _treeNodeClickSeries: function (event, treeId, treeNode) {
            var seriesId = treeNode.data.id;
            if (treeNode.checked) {
                var seriesNode = seriesTree.getCheckedNodes(true);
                seriesNode.forEach(function (val) {
                    editSelectedSid.push(val.data.id);
                });
            } else {
                editZtrees._remove(editSelectedSid, seriesId);
            }
        },
        _treeSearchPackage: function () {
            editZtrees.packageTree();
        },
        _treeSearchSeries: function () {
            var seriesNode = seriesTree.getCheckedNodes(true);
            var seriesIds = new Array();
            seriesNode.forEach(function (val) {
                seriesIds.push(val.data.id);
            });
            editZtrees.seriesTree();
        },
        seriesTree: function () {
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
                    onCheck: self._treeNodeClickSeries
                }
            };
            var zNodes = [];
            var searchObj = {};
            var name = $(el.series_tree_search_input).val();
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
                    if (editSelectedSid) {
                        zNodes.forEach(function (val) {
                            editSelectedSid.unique3().forEach(function (vv) {
                                if (val.data.id == vv) {
                                    val.checked = true;
                                }
                            });
                        });
                    }
                    seriesTree = $.fn.zTree.init($(el.series_ztree), setting, zNodes);
                    seriesTree.expandAll(false);
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
                    if (editSelectedPid) {
                        zNodes.forEach(function (val) {
                            editSelectedPid.unique3().forEach(function (vv) {
                                if (val.data.id == vv) {
                                    val.checked = true;
                                }
                            });
                        });
                    }
                    packageTree = $.fn.zTree.init($(el.package_ztree), setting, zNodes);
                    packageTree.expandAll(false);
                },
                error: function (data) {
                    alert(data.responseJSON.message);
                }
            });
        }
    };

    $(el.package_tree_search_btn).click(function () {
        editZtrees._treeSearchPackage();
    });
    $(el.series_tree_search_btn).click(function () {
        editZtrees._treeSearchSeries();
    });
</script>
</#compress>