<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12 list">
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <h2>
                <dic data-dic="header.list.search"></dic>
            </h2>
        </header>
        <div>
            <div class="widget-body no-padding"  style="min-height: 80px">
                <form class="smart-form">
                    <fieldset>
                        <div class="row">
                          <label class="col col-1 text-align-right"
                                 style="padding-top: 7px; "><dic data-dic="main.title.seriesProduct"></dic>:</label>
                          <label class="col col-3 input">
                            <div class="dropdown dropdown-large ds">
                              <input class="form-control "
                                     data-toggle="dropdown" readonly
                                     id="search_file_series"
                                     aria-expanded="true"
                                     placeholder=""
                                     type="text">
                              <ul class="dropdown-menu dropdown-menu-large row "
                                  style="margin: 0px 0px 0px 0px !important;padding: 0
                                        !important;">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                  <div class="input-group">
                                    <input class="form-control"
                                           id="search_series_tree_input"
                                           placeholder=""
                                           type="text">
                                    <span class="input-group-addon"
                                          id="search_series_tree_input_btn">
                                                    <i class="fa fa-search"></i>
                                                </span>
                                  </div>
                                </div>
                                <div class="ztree" id="series_ztree"
                                     style="height: 200px;overflow-y: scroll;"></div>
                              </ul>
                            </div>
                          </label>
                            <label class="col col-1 text-align-right"
                                   style="padding-top: 7px; "><dic data-dic="file.list.table.type"></dic>:</label>
                            <label class="col col-3 input">
                                <select class="select2" id="search_file_category" style="width:
                                100%;">
                                    <option value="" selected data-dic="file.list.item.all"></option>
                                    <option value="1" data-dic="file.list.security.NewsAndActivities"></option>
                                    <option value="2" data-dic="file.list.security.Product"></option>
                                    <option value="3" data-dic="file.list.security.Solution"></option>
                                    <option value="4" data-dic="file.list.security.Brand"></option>
                                    <option value="5" data-dic="file.list.security.anli"></option>
                                </select>
                            </label>
                            <label class="col col-1 text-align-right"
                                   style="padding-top: 7px; "><dic data-dic="file.list.item.use"></dic>:</label>
                            <label class="col col-3 input">
                                <div class="dropdown dropdown-large dp">
                                    <input type="text" id="search_file_package"
                                           data-toggle="dropdown" readonly/>
                                    <ul class="dropdown-menu dropdown-menu-large row "
                                        style="margin: 0 !important;padding: 0!important;">
                                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                            <div class="input-group">
                                                <input class="form-control"
                                                       id="search_folder_tree_input" placeholder=""
                                                       type="text">
                                                <span class="input-group-addon"
                                                      id="search_folder_tree_input_btn">
                                                    <i class="fa fa-search"></i>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="ztree" id="folder_tree"
                                             style="height: 200px;overflow-y: auto;"></div>
                                    </ul>
                                </div>
                            </label>
                        </div>
                        <div class="row" style="margin-top: 20px;margin-bottom: 10px;">
                            <label class="col col-1 text-align-right"
                                   style="padding-top: 7px; "><dic data-dic="file.message.list.productSubdivision"></dic>:</label>
                            <label class="col col-3 input">
                                <select class="select2" id="fileMarketDetails" multiple style="width:
                                100%;">
                                    <#--<option value="0" selected data-dic="file.list.item.all"></option>-->
                                    <#if fileMarketDetails??>
                                        <#list fileMarketDetails as item>
                                            <option value="${(item.id?c)!}">${(item.name)!}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </label>
                            <label class="col col-1 text-align-right"
                                   style="padding-top: 7px; "><dic data-dic="file.list.table.name"></dic>:</label>
                            <label class="col col-3 input">
                                <input class="form-control" id="search_file_name" placeholder="" type="text">
                            </label>
                            <button type="button" class="btn btn-default btn-sm query-btn pull-right mr15 rest">
                                <dic data-dic="button.reset"></dic>
                            </button>
                            <a class="btn btn-primary btn-sm query-btn pull-right mr15" id="search_file_btn">
                                <dic data-dic="header.list.button.search"></dic>
                            </a>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>

    </div>
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <h2>
                <dic data-dic="file.list.tableName"></dic>
            </h2>
            <div class="col col-md-8">
                <div class="btn-group" data-toggle="buttons" style="position: absolute">
                    <label class="btn   btn-default extend_btn">
                        <input type="checkbox" data-extend="ppt,pptx"> PPT
                    </label>
                    <label class="btn btn-default extend_btn">
                        <input type="checkbox"  data-extend="pdf"> PDF
                    </label>
                    <label class="btn btn-default extend_btn">
                        <input type="checkbox"  data-extend="doc,docx"> Word
                    </label>
                    <label class="btn btn-default extend_btn">
                        <input type="checkbox"  data-extend="png,jpeg,bmp"> Picture
                    </label>
                    <label class="btn btn-default extend_btn">
                        <input type="checkbox"  data-extend="link"> Link
                    </label>
                    <label class="btn btn-default extend_btn">
                        <input type="checkbox"  data-extend="video"> Video
                    </label>
                    <label class="btn btn-default extend_btn">
                        <input type="checkbox"  data-extend="xls,xlsx"> EXCEL
                    </label>
                </div>
                <input type="hidden" id="extendStr" />
            </div>
            <a class="btn btn-primary btn-xs mail-send-batch" style="float: right;margin-top:7px;"
               data-user-id="0">
                <dic data-dic="body.list.mail"></dic>
            </a>
        </header>
        <div>
            <div class="widget-body no-padding">
                <table id="dt_basic_file" class="table table-striped table-bordered table-hover"
                       width="100%">
                    <thead>
                    <tr>
                        <th></th>
                        <th></th>
                        <th>
                            <dic data-dic="file.list.table.name"></dic>
                        </th>
                        <th>
                            <dic data-dic="file.list.table.type"></dic>
                        </th>
                        <th>
                            <dic data-dic="file.message.list.productSubdivision"></dic>
                        </th>
                        <th>
                            <dic data-dic="file.list.table.extend"></dic>
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
<div class="modal fade" id="mail_model" tabindex="-1" role="dialog"
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
                <div id="mail_html"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="mail_send">
                    <dic data-dic="button.confirm"></dic>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<style type="text/css">
    .dd3-content {
        cursor: pointer;
    }

    .dd3-content-active {
        color: #2ea8e5;
        background: #fff;
    }

</style>

<link rel="stylesheet" href="../../static/js/plugin/ztree/css/metroStyle/metroStyle.css"/>
<script type="text/javascript" src="../../static/js/plugin/ztree/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript"
        src="../../static/js/plugin/bootstrap-tags/bootstrap-tagsinput.min.js"></script>
<script type="text/javascript">
    var el = {
        fileTable: '#dt_basic_file',
        folderTree: '#folder_tree',
        fileSearchBtn: '#search_file_btn',
        fileSearchInputCategory: '#search_file_category',
        fileMarketDetails: '#fileMarketDetails',
        fileSearchInputPackage: '#search_file_package',
        fileSearchInputSeries: '#search_file_series',
        folderSearchInput: '#search_folder_tree_input',
        folderSearchBtn: '#search_folder_tree_input_btn',
        seriesSearchInput: '#search_series_tree_input',
        seriesSearchBtn: '#search_series_tree_input_btn',
        search_package_tree: '#search_package_tree',
        series_ztree: '#series_ztree',
        search_series_tree: '#search_series_tree',
        search_file_name: '#search_file_name'

    };
    var url = {
        folderItem: '/folder/item',
        fileItem: '/file/item',
        fileSecurity: '/file/security',
        folderRemove: '/api/folder/delete',
        folderStructUpdate: '/api/folder/struct/update',
        fileList: '/file/rest/datagrid',
        updateFileFolder: '/api/file/folder/update',
        fileRemove: '/api/file/delete',
        folderTreeUrl: '/file/rest/tree',
        series_ztree: '/api/series/tree',
        send_mail: '/mail/rest/send'
    };
    var dt;
    var packageId = 0;
    var file = {
        _folderTreeNodeCheck: function (event, treeId, treeNode) {
            event.preventDefault();
            var zTree  = $.fn.zTree.getZTreeObj('folder_tree');
            var checkDatas = zTree.getCheckedNodes();
            var showNames = [];
            var ids = [];
            checkDatas.forEach(function (item) {
                showNames.push(item.data.name);
                ids.push(item.data.id);
            });
            $(el.fileSearchInputPackage).val(showNames.join(','));
            $(el.fileSearchInputPackage).data('ids',ids);
        },
        _seriesTreeNodeCheck: function (event, treeId, treeNode) {
            event.preventDefault();
            var zTree  = $.fn.zTree.getZTreeObj('series_ztree');
            var checkDatas = zTree.getCheckedNodes();
            var showNames = [];
            var ids = [];
            checkDatas.forEach(function (item) {
                showNames.push(item.data.name);
                ids.push(item.data.id);
            });
            $(el.fileSearchInputSeries).val(showNames.join(','));
            $(el.fileSearchInputSeries).data('ids',ids);
        },
        _folderTree: function () {
            var self = this;
            var setting = {
                data: {
                    simpleData: {
                        enable: true,
                        idKey: 'id',//主键
                        pIdKey: 'pid'//上级id
                    }
                },
                check:{
                    chkStyle: 'checkbox',
                    chkboxType:{ "Y" : "", "N" : "" },
                    enable: true
                },
                callback: {
                    onCheck: self._folderTreeNodeCheck
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
        _seriesTree: function () {
            var self = this;
            var setting = {
                data: {
                    simpleData: {
                        enable: true,
                        idKey: 'id',//主键
                        pIdKey: 'pid'//上级id
                    }
                },
                check:{
                    chkStyle: 'checkbox',
                    chkboxType:{ "Y" : "", "N" : "" },
                    enable: true
                },
                callback: {
                    onCheck: self._seriesTreeNodeCheck
                }
            };
            var zNodes = [];
            var name = $(el.seriesSearchInput).val();
            var searchObj = {};
            if (name) {
                searchObj = {name: name};
            }
            //请求
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
                    $.fn.zTree.init($(el.series_ztree), setting, zNodes);
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
        _loadFilesTable: function () {
            dt = new Datatables();
            dt.init({
                src: $(el.fileTable),
                loadingMessage: message["datatable.loading"],
                dataTable: {
                    bStateSave: false,
                    ajax: {url: url.fileList},

                    columns: [
                        DTColumnKit.checkbox,
                        DTColumnKit.order,
                        {data: "name"},
                        {
                            data: "category",
                            render: function (data, row) {
                                if (data == 1) {
                                    return message["file.list.security.NewsAndActivities"]
                                }
                                if (data == 2) {
                                    return message["file.list.security.Product"]
                                }
                                if (data == 3) {
                                    return message["file.list.security.Solution"]
                                }
                                if (data == 4) {
                                    return message["file.list.security.Brand"]
                                }
                                else {
                                    return '';
                                }
                            }
                        },
                        {data: "detailName"},
                        {data: "extend"},
                        {
                            data: "size",
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
                            render: function (data, display, row) {
                                // button: download
                                var download = '&nbsp;<a class="btn btn-xs btn-primary download" ' +
                                        ' href="javascript:;"' +
                                        ' data-url="' + row.url + '"' +
                                        ' data-id="' + data + '">'+message["body.list.downland"]+'</a>';
                                // button: view
                                var viewHtml = '';
                                var fileType = row.extend;
                                var fileTypes = ['png','jpg','jpeg','gif','doc','docx','xls','xlsx','ppt','pptx','pdf','video','link'];
                                if($.inArray(fileType,fileTypes) >= 0){
                                    viewHtml = '&nbsp;<a class="btn btn-xs btn-primary view" ' + ' href="javascript:;"' +
                                            ' data-id="' + data + '"' +'data-type="'+ row.type +'"' + 'data-url="'+ row.url +'"' + '>'
                                            + message["body.list.view"]+'</a>';
                                }
                                var viewRes = "";
                                if(row.type == 888 || row.type == 999) {
                                    viewRes = viewHtml;
                                }
                                if (row.securitys==2) {
                                    viewRes = viewHtml;
                                }
                                if (row.securitys == 1 || row.securitys==3) {
                                    if (row.extend.toLowerCase() === 'pptx' ||
                                            row.extend.toLowerCase() === 'ppt' ||
                                    row.extend.toLowerCase() === 'xls' ||
                                    row.extend.toLowerCase() === 'xlsx' ||
                                    row.extend.toLowerCase() === 'doc' ||
                                    row.extend.toLowerCase() === 'docx') {
                                        viewRes = download;
                                    } else {
                                        viewRes = viewHtml + download;
                                    }
                                }
                                else {
                                    viewRes = "";
                                }

                                return viewRes;
                            }
                        },
                        {data: 'roleSecurity',"visible": false}
                        ],
                    columnDefs: [{
                        orderable: false,
                        targets: [0,1,2,3,4,5,6,7,8]
                    }],
                    drawCallback: function () {
                    }
                }
            });
        },
        _fileSearch: function (id) {
            var category = $(el.fileSearchInputCategory).val();
            var _packageIds = $(el.fileSearchInputPackage).data("ids");
            var seriesIds = $(el.fileSearchInputSeries).data("ids");
            var fileDetailsId = $(el.fileMarketDetails).select2("val");
            var fileName = $(el.search_file_name).val();
            var extend = $("#extendStr").val();

            if (_packageIds && _packageIds.length > 0) {
                dt.addAjaxParam('folderId', _packageIds);
            }else{
                dt.addAjaxParam('folderId', '');
            }
            if (seriesIds && seriesIds.length > 0) {
                dt.addAjaxParam('seriesId', seriesIds);
            }else{
                dt.addAjaxParam('seriesId', '');
            }
            dt.addAjaxParam('category', category);
            dt.addAjaxParam('fileMarketDetailId', fileDetailsId.length <= 0 ? '' : fileDetailsId);
            dt.addAjaxParam('name', fileName);
            dt.addAjaxParam('extend', extend);
            dt.submitFilter();
        }
    };
    $(document).ready(function () {
        $('[data-dic]').each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
        $(el.fileSearchInputCategory).select2();
        $(el.fileMarketDetails).select2();
        file._folderTree();
        file._seriesTree();
        $("dic").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
        file._loadFilesTable(0);
//        $('.add-folder').on('click', function () {
//            folder._folderItem(0, 0);
//        });

//        $('.add-file').on('click', function () {
//            var id = $(this).data('id');
//            file._fileItem(id);
//        });
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

        $('body').on('click', '.mail-send-batch', function () {
            var fileIds = dt.getSelectedRows();
            if (fileIds.length === 0) {
                alert(message["please.select.a.piece.of.data"]);
                return;
            }

            var url = "/mail/edit";
            var $modelHtml = $("#mail_html");
            var $model = $("#mail_model");
            $.ajax({
                url: url,
                type: 'GET',
                data: {
                    fileIds: JSON.stringify(fileIds)
                },
                success: function (html) {
                    $model.modal('show');
                    $modelHtml.html(html);
                }
            });
        });


        $(el.folderSearchBtn).on('click', function () {
            file._folderTree();
        });
        $(el.seriesSearchBtn).on('click', function () {
            file._seriesTree();
        });
        $(el.fileSearchBtn).on('click', function () {
            file._fileSearch();
        });
        $('div').on('click', '.file-type', function () {
            $('.file-type').removeClass('file-type-btn-active');
            $(this).addClass('file-type-btn-active');
        });
        $('.rest').on('click', function () {
            $(el.fileSearchInputCategory).select2('val',"");
            $(el.fileSearchInputPackage).val("");
            $(el.fileSearchInputPackage).data("ids", "");
            $(el.fileSearchInputSeries).val("");
            $(el.fileSearchInputSeries).data("ids", "");
            $(el.fileMarketDetails).select2('val',"");
            $(el.search_file_name).val('');
            $(el.seriesSearchInput).val('');
            $(el.folderSearchInput).val('');
            file._folderTree();
            file._seriesTree();

            var treeSeries = $.fn.zTree.getZTreeObj('series_ztree');
            var treeFolder = $.fn.zTree.getZTreeObj('folder_tree');
            $.fn.zTree.getZTreeObj('folder_tree');
            if(treeSeries){
                treeSeries.checkAllNodes(false);
            }
            if(treeFolder){
                treeFolder.checkAllNodes(false);
            }
            dt.resetFilter();

        });
        $('.extend_btn').on('click',function () {
            console.log($(this).attr('class'));
            var extendVar;
            if($(this).hasClass('active')){
                $(this).removeClass('btn-warning');
                $(this).addClass('btn-default');
                extendVar = "";
            }else{
                $(this).removeClass('btn-default');
                $(this).addClass('btn-warning');
                extendVar = $(this).find("input[type='checkbox']").data('extend');
            }
            var extend =  $(".btn.extend_btn.btn-warning.active input[type='checkbox']");
            $.each(extend, function (index,item) {
                if (extendVar!=""){
                    extendVar  += (","+$(item).data('extend'))  ;
                }else {
                    extendVar  +=  $(item).data('extend');
                }
            });
            $("#extendStr").val(extendVar);
            file._fileSearch();
        });
        // 确定发送邮件
        $('#mail_send').on('click', function () {

            // console.log($('#fileIds').val());
            var idsStr = $('#fileIds').data('ids');
            // var ids = JSON.parse(idsStr);
            //获取表单对象
            var bootstrapValidator = $('#mail_send_form').data('bootstrapValidator');
            //手动触发验证
            bootstrapValidator.validate();
            if (bootstrapValidator.isValid()) {
                $.ajax({
                    url: '/mail/rest/send',
                    type: 'POST',
                    data: {
                        ids: idsStr,
                        acceptMail: $('#acceptMail').val(),
                        mailSubject: $('#mailSubject').val(),
                        mailValidity: $('#mailValidity').val()
                    },
                    success: function (data) {
                        $("#mail_model").modal('hide');
                    },
                    error: function () {
                        alert(message['alert.message.systemError'], 3);
                    }
                });
            }

        });

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

    #folder_tree.ztree span.button {
        float: none !important;
    }
    #series_ztree.ztree span.button {
        float: none !important;
    }

    .btn-group{
        width: 100%;
        padding-left: 0;
        padding-right:0;
    }

    .btn-group .file-type.btn{
        width: 25%;
    }

    #file_item .jarviswidget{
        margin:0;
    }
</style>
</#compress>