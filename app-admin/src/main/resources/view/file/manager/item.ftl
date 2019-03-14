<#compress>
<div class="row">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 right" style="margin-top: 20px;">
        <div class="btn-group" style="width: 100%;padding-left: 0;padding-right: 0;;">
            <button type="button" class="file-type btn btn-default" data-type="1">新闻&活动</button>
            <button type="button" class="file-type btn btn-default" data-type="2">产品</button>
            <button type="button" class="file-type btn btn-default" data-type="3">案例</button>
            <button type="button" class="file-type btn btn-default" data-type="4">品牌</button>
        </div>
    </div>
</div>
<div class="row" style="margin-top: 20px;">
    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
        <div class="jarviswidget" data-widget-editbutton="false">
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

    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
        <div class="jarviswidget" data-widget-editbutton="false">
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
<link rel="stylesheet" href="../static/js/plugin/ztree/css/metroStyle/metroStyle.css"/>
<script type="text/javascript" src="../static/js/plugin/ztree/js/jquery.ztree.all.min.js"></script>
<script src="../static/js/plugin/dropzone/dropzone.min.js"></script>
<script type="text/javascript">
    el.uploaderForm = '#box_file_upload';
    el.series_ztree = '#series_tree';
    el.package_ztree = '#package_tree';
    el.series_tree_search_btn = '#search_series_btn';
    el.package_tree_search_btn = '#search_package_btn';
    el.series_tree_search_input = '#search_series_input';
    el.package_tree_search_input = '#search_package_input';

    url.fileSave = '/api/file/save';
    url.series_ztree = '/api/series/tree';
    url.package_ztree = '/api/folder/tree';

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
                    $(el.fileItemModal).modal('hide');
                    file._dtRefuse();
                }
            });
        },
        _dropzone: function () {
            url.upload = $(el.uploaderForm).data("url");
            Dropzone.autoDiscover = false;
            $(el.uploaderForm).dropzone({
                autoProcessQueue: false,
                url: url.upload,
                addRemoveLinks: true,
                maxFilesize: 100,
                maxFiles: maxFiles,
                dictDefaultMessage: '<span class="text-center"><span class="font-lg visible-xs-block visible-sm-block visible-lg-block"><span class="font-lg"><i class="fa fa-caret-right text-danger"></i>' + message["importMsg.chooseFile"] + '</span><span>&nbsp&nbsp<h4 class="display-inline"> (' + message["importMsg.clickFile"] + ')</h4></span>',
                dictFallbackMessage: message["dropzone.dictFallbackMessage"],
                dictFileTooBig: message["dropzone.dictFileTooBig"],
                dictCancelUpload: message["dropzone.dictCancelUpload"],
                dictCancelUploadConfirmation: message["dropzone.dictCancelUploadConfirmation"],
                dictRemoveFile: message["dropzone.dictRemoveFile"],
                dictMaxFilesExceeded: message["dropzone.dictMaxFilesExceeded"],
                dictInvalidFileType: message["dropzone.dictInvalidFileType"],
                init: function () {
                    var self = this;
                    $(el.fileSubBtn).on("click", function () {

                        self.processQueue(); // Tell Dropzone to process all queued files.
                    });
                    this.on("addedfile", function (file) {
                        addFileSize++;
                    });
                    this.on("success", function (file, data) {
                        $('.attachment-id').val($(".attachment-id").val() + "," + data.id);
                    });
                    this.on("complete", function (file) {
                        complateFileSize++;
                        if (addFileSize == complateFileSize) {
                            fileItem._submitFile();
                        }
                    });
                }
            });
        }
    };
    $("dic").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });
    fileItem._dropzone();
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
                    if (itemSeletedSids) {
                        zNodes.forEach(function (val) {
                            itemSeletedSids.unique3().forEach(function (vv) {
                                if (val.data.id == vv) {
                                    val.checked = true;
                                }
                            });
                        });
                    }
                    seriesTree = $.fn.zTree.init($(el.series_ztree), setting, zNodes);
                    seriesTree.expandAll(true);
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
    ztrees.seriesTree();
    ztrees.packageTree();
    $(el.package_tree_search_btn).click(function () {
        ztrees._treeSearchPackage();
    });
    $(el.series_tree_search_btn).click(function () {
        ztrees._treeSearchSeries();
    });
</script>
<style type="text/css">
    .btn-group .file-type.btn-default{
        width: 25%;
    }
</style>
</#compress>