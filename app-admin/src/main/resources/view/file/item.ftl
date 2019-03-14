<#compress>
<div class="jarviswidget" data-widget-colorbutton="false" data-widget-editbutton="false"
     data-widget-togglebutton="false" data-widget-deletebutton="false"
     data-widget-fullscreenbutton="false" data-widget-custombutton="false">
    <header>
        <ul id="widget-tab-1" class="nav nav-tabs pull-left">
            <li class="active">
                <a data-toggle="tab" href="#hr1">
                    <i class="fa fa-lg fa-file-o"></i>
                    <span class="hidden-mobile hidden-tablet">
                        <dic data-dic="file.list.item.normal"></dic>
                    </span>
                </a>
            </li>
            <li>
                <a data-toggle="tab" href="#hr2">
                    <i class="fa fa-lg fa-film"></i>
                        <span class="hidden-mobile hidden-tablet">
                    <dic data-dic="file.list.item.video"></dic>
                </span>
                </a>
            </li>
            <li>
                <a data-toggle="tab" href="#hr3">
                    <i class="fa fa-lg fa-film"></i>
                        <span class="hidden-mobile hidden-tablet">
                    <dic data-dic="file.list.item.outAddress"></dic>
                </span>
                </a>
            </li>
        </ul>
        <span class="pull-right" id="addItem" style="display: none">
            <a class="btn btn-primary btn-xs add-file" data-id="0">
                <dic class="videoEvent addVideo" data-dic="header.list.button.add">添加</dic>
            </a>
        </span>
    </header>
    <div>
        <div class="jarviswidget-editbox"></div>
        <div class="widget-body no-padding">
            <div class="tab-content padding-10" style="height: 170px">
                <div class="tab-pane fade in active" id="hr1">
                    <div style="min-height: 150px;height:150px;overflow-y: scroll;">
                        <form class="dropzone" id="box_file_upload" data-url="/import/upload">
                            <input type="hidden" name="attachmentId" class="attachment-id">
                        </form>
                    </div>
                </div>
                <div class="tab-pane fade" id="hr2">
                    <form class="smart-form" id="video-form" style="height: 150px;overflow: auto;">
                    <fieldset style="height: 42px">
                        <label class="col col-1 text-align-right padding">
                            <span style="color:red;">*</span>
                            <dic data-dic="sales.level.list.name"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" name="name" class="videoName">
                        </label>
                        <label class="col col-2 text-align-right padding">
                            <span style="color:red;">*</span>
                            <dic data-dic="file.list.item.videoUrl"></dic>:
                        </label>
                        <label class="col col-5 input">
                            <input type="text" name="url" class="videoUrl">
                        </label>
                        <label class="col col-1 input">
                            <span class="pull-right">
                                <a class="btn btn-primary btn-xs add-file deleteVideo" data-id="0">
                                    <dic data-dic="body.list.delete">删除</dic>
                                </a>
                            </span>
                        </label>
                    </fieldset>
                    </form>
                </div>
                <div class="tab-pane fade" id="hr3">
                    <form class="smart-form" id="out-link" style="height: 150px;overflow: auto;">
                        <fieldset style="height: 42px">
                            <label class="col col-1 text-align-right padding">
                                <span style="color:red;">*</span>
                                <dic data-dic="sales.level.list.name"></dic>:
                            </label>
                            <label class="col col-3 input">
                                <input type="text" name="name" class="videoName">
                            </label>
                            <label class="col col-2 text-align-right padding">
                                <span style="color:red;">*</span>
                                <dic data-dic="file.list.item.outAddress"></dic>:
                            </label>
                            <label class="col col-5 input">
                                <input type="text" name="url" class="videoUrl">
                            </label>
                            <label class="col col-1 input">
                            <span class="pull-right">
                                <a class="btn btn-primary btn-xs add-file deleteVideo" data-id="0">
                                    <dic data-dic="body.list.delete">删除</dic>
                                </a>
                            </span>
                            </label>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 right" style="margin-top: 20px;">
        <div class="btn-group">
            <button type="button" class="file-type btn btn-default" data-type="1">
                <dic data-dic="file.list.security.NewsAndActivities">公司资料</dic>
            </button>
            <button type="button" class="file-type btn btn-default file-type-btn-active" data-type="2">
                <dic data-dic="file.list.security.Product">公司活动</dic>
            </button>
            <button type="button" class="file-type btn btn-default" data-type="3">
                <dic data-dic="file.list.security.Solution">产品资料</dic>
            </button>
            <button type="button" class="file-type btn btn-default" data-type="4">
                <dic data-dic="file.list.security.Brand">产品视频</dic>
            </button>
            <button type="button" class="file-type btn btn-default" data-type="5">
                <dic data-dic="file.list.security.anli">产品案例</dic>
            </button>
            <div class="btn-group" style="float: right;width: 24%;">
                <button type="button" class="see-type btn btn-default btnSM file-type-btn-active" data-type="1">
                    <dic data-dic="file.list.security.open">公开</dic>
                </button>
                <button type="button" class="see-type btn btn-default btnSM" data-type="2">
                    <dic data-dic="file.list.security.look">查看</dic>
                </button>
                <button type="button" class="see-type btn btn-default btnSM" data-type="3">
                    <dic data-dic="file.list.security.private">私密</dic>
                </button>
            </div>
        </div>
        <div class="btn-group btn-xs-group">
            <#if fileMarketDetails??>
                <#list fileMarketDetails as item>
                    <button type="button" class="product-type btn btn-default" data-type="${(item.id)!}">${(item.name)!}</button>
                </#list>
            </#if>
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
                                    <input class="form-control" id="search_package_input"  placeholder="" type="text">
                                    <span class="input-group-addon" id="search_package_btn">
                                    <i class="fa fa-search"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="checkbox" id="packageSelectAll" onclick="pSelectAll(this)">
                    <dic data-dic="package.list.treeName.selectAll"></dic>
                    <ul id="package_tree" class="ztree" style="overflow-y: auto;height: 200px;"></ul>
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
<script type="text/javascript" src="../static/js/plugin/ztree/js/jquery.ztree.all.min.js"/>
<script src="../static/js/plugin/dropzone/dropzone.min.js"/>
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
        maxFiles = 50;
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
    var itemSeletedSids = [];
    var itemSeletedPids = [];
    var fileItem = {
        //保存
        _submitFile: function () {
            var seriesIds = [];
            var packageIds = [];
            itemSeletedSids.unique3().forEach(function (val) {
                seriesIds.push(val);
            });
            itemSeletedPids.unique3().forEach(function (val) {
                packageIds.push(val);
            });
            var securitys = $('.see-type.file-type-btn-active').data('type');
            var market = $('.product-type.file-type-btn-active').data('type');
            if(!market){
                alert(message["product.segmentation.no.choice"],2);
                return;
            }
            if(!securitys){
                alert(message["product.segmentation.no.view.choice"],2);
                return;
            }
            $.ajax({
                url: url.fileSave,
                data: {
                    ids: $('.attachment-id').val(),
                    packageIds: packageIds.join(','),
                    seriesIds: seriesIds.join(','),
                    fileType: $('.file-type-btn-active').data("type"),
                    securitys: securitys,
                    market: market
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
                autoProcessQueue: true,
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
                    $(el.fileSubBtn).off('click').click(function () {
                        var $fileForm =  $(".tab-pane.fade.in.active").find("#box_file_upload").html();
                        if (!!$fileForm) {
                            if (addFileSize == complateFileSize) {
                                fileItem._submitFile();
                            }
                        }else {
                            var seriesIds = [];
                            var packageIds = [];
                            itemSeletedSids.unique3().forEach(function (val) {
                                seriesIds.push(val);
                            });
                            itemSeletedPids.unique3().forEach(function (val) {
                                packageIds.push(val);
                            });
                            var videoData = evt.findVideoForm();

                            if(videoData === null){
                                alert(message['file.message.error.addressAndNameCantEmpty'],2);
                                return;
                            }
                            //获取表单对象
                            var bootstrapValidator = $("#video-form").data('bootstrapValidator');
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
                            if($('#widget-tab-1 li.active a[href="#hr3"]').length){
                                extend = 2;
                            }
                            //手动触发验证
                            bootstrapValidator.validate();
                            if (bootstrapValidator.isValid()) {
                                $.ajax({
                                    url: '/api/file/video/save',
                                    type: 'post',
                                    data: {
                                        name: $("#videoName").val(),
                                        url: $("#videoUrl").val(),
                                        packageIds: packageIds.join(','),
                                        fileType: $('.file-type-btn-active').data("type"),
                                        seriesIds: seriesIds.join(','),
                                        videoData: JSON.stringify(videoData),
                                        securitys: securitys,
                                        market: market,
                                        extend: extend
                                    },
                                    success: function (data) {
                                        $(el.fileItemModal).modal('hide');
                                        file._dtRefuse();
                                    },
                                    error: function (data) {
                                        alert(message["alert.message.systemError"], 3);
                                    }
                                });
                            }
                        }
                    });
                    this.on("addedfile", function (file) {
                        addFileSize++;
                    });
                    this.on("success", function (file, data) {
                        $('.attachment-id').val($(".attachment-id").val() + "," + data.id);
                    });
                    this.on("complete", function (file) {
                        complateFileSize++;
                    });
                }
            });
        }
    };

    //点击效果
    $('.see-type').off('click').click(function () {
        $('.see-type.file-type-btn-active').removeClass('file-type-btn-active');
        $(this).addClass('file-type-btn-active');
    });
    //点击效果
    $('.product-type').off('click').click(function () {
        $('.product-type.file-type-btn-active').removeClass('file-type-btn-active');
        $(this).addClass('file-type-btn-active');
    });

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
                    onCheck: self._treeNodeClickSeries,
                    onClick: function (e, treeId, treeNode) {
                        seriesTree.checkNode(treeNode, !treeNode.checked, true);
                        if(treeNode.checked){
                            var seriesNodeClick = seriesTree.getCheckedNodes(true);
                            seriesNodeClick.forEach(function (val) {
                                itemSeletedSids.push(val.data.id + '');
                            });
                        }else{
                            ztrees._remove(itemSeletedSids, treeNode.data.id);
                        }
                    }
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
                    chkboxType: {"Y": "ps", "N": "ps"}
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: 'id',//主键
                        pIdKey: 'pid'//上级id
                    }
                },
                callback: {
                    onCheck: self._treeNodeClickPackage,
                    onClick: function (e, treeId, treeNode) {
                        packageTree.checkNode(treeNode, !treeNode.checked, true);
                        if(treeNode.checked){
                            var packageNodeClick = packageTree.getCheckedNodes(true);
                            packageNodeClick.forEach(function (val) {
                                itemSeletedPids.push(val.data.id + '');
                            });
                        }else{
                            ztrees._remove(itemSeletedPids, treeNode.data.id);
                        }
                    }
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
    $("#video-form").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                group: ".col.col-3",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    }
                }
            },
            url: {
                group: ".col.col-5",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    }
                }
            }
        }
    });
    $('#video-form,#out-link').on('click','.deleteVideo',function () {
        $(this).closest('fieldset').remove();
    });
    $('#widget-tab-1 li').on('click', function () {
        if($(this).find('a[href="#hr2"],a[href="#hr3"]').length){
            $('#addItem').show();
        }else{
            $('#addItem').hide();
        }
    });
    $('#addItem').on('click', function () {
        var mesStr = {
            salesLevelListName: message['sales.level.list.name'],
            fileListItemVideoUrl: message['file.list.item.videoUrl'],
            fileListItemOutAddress: message['file.list.item.outAddress'],
            headerListButtonAdd: message['header.list.button.add'],
            bodyListDelete: message['body.list.delete']
        };
        var nameStr = mesStr.fileListItemVideoUrl;
        if($('#widget-tab-1 li.active a[href="#hr3"]').length){
            nameStr = mesStr.fileListItemOutAddress;
        }
        var infoItemHtml = '<fieldset style="height: 42px"><label class="col col-1 text-align-right padding"><span style="color:red;">*</span> ' + mesStr.salesLevelListName + '</label>'
                + '<label class="col col-3 input"><input type="text" name="name" class="videoName"></label><label class="col col-2 text-align-right padding"><span style="color:red;">*</span>'
                + nameStr + '</label><label class="col col-5 input"><input type="text" name="url" class="videoUrl"></label>'
                + '<label class="col col-1 input"><span class="pull-right"><a class="btn btn-primary btn-xs add-file deleteVideo" data-id="0"><dic>' + mesStr.bodyListDelete + '</dic>'
                + '</a></span></label></fieldset>';
        if($('#widget-tab-1 li.active a[href="#hr2"]').length){
            $('#video-form').append(infoItemHtml);
        }else if($('#widget-tab-1 li.active a[href="#hr3"]').length){
            $('#out-link').append(infoItemHtml);
        }
    });
    /**
     * 方法整合
     * @type {{findVideoForm}}
     */
    var evt = function () {
        return{
            /**
             * 获取视频文件列表数据
             */
            findVideoForm: function () {
                var list = [];
                var empty = false;
                if($('#widget-tab-1 li.active a[href="#hr2"]').length){
                    $.each($('#video-form fieldset'),function (index,item) {
                        var name = $(item).find('.videoName').val();
                        var url = $(item).find('.videoUrl').val();
                        if(!name){
                            empty = true;
                            return false;
                        }
                        if(!url){
                            empty = true;
                            return false;
                        }
                        list.push({
                            name: name,
                            url: url
                        });
                    });
                }else if($('#widget-tab-1 li.active a[href="#hr3"]').length){
                    $.each($('#out-link fieldset'),function (index,item) {
                        var name = $(item).find('.videoName').val();
                        var url = $(item).find('.videoUrl').val();
                        if(!name){
                            empty = true;
                            return false;
                        }
                        if(!url){
                            empty = true;
                            return false;
                        }
                        list.push({
                            name: name,
                            url: url
                        });
                    });
                }
                if(empty || list.length === 0){
                    return null;
                }
                return list;
            }
        }
    }();

    function pSelectAll(obj) {
        var packageAllTree = $.fn.zTree.getZTreeObj("package_tree");
        if(obj.checked){
            packageAllTree.checkAllNodes(true);
            var packageNodes = packageAllTree.getCheckedNodes(true);
            packageNodes.forEach(function (val) {
                itemSeletedPids.push(val.data.id + '');
            });
        }else{
            packageAllTree.checkAllNodes(false);
            var packageNodes = packageAllTree.getCheckedNodes(false);
            packageNodes.forEach(function (val) {
                ztrees._remove(itemSeletedPids, val.data.id);
            });
        }
    }
</script>
</#compress>