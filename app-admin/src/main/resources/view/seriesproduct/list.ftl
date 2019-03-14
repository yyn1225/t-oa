<#compress>
<article class="col-xs-12 col-sm-12 col-md-3 col-lg-3 list">
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <h2>
                <dic data-dic="heard.series.tree.title"></dic>
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
    <div id="right"></div>
</article>
<link rel="stylesheet" href="../static/js/plugin/ztree/css/metroStyle/metroStyle.css"/>
<script type="text/javascript" src="../static/js/plugin/ztree/js/jquery.ztree.all.min.js"/>
<script type="text/javascript">
    var el = {
        ztree: '#ul_tree_organization',
        tree_search_btn: '#search_organization_btn',
        tree_search_input: '#search_organization_input',
        folderTree: '#folder_tree',
        folderSearchInput: '#search_folder_tree_input',
        folderSearchBtn: '#search_folder_tree_input_btn'

    };
    var url = {
        ztree: '/api/series/tree'
    };
    var file = {
        _treeNodeClick: function (event, treeId, treeNode) {
            var id = treeNode.data.id;
            var url = "/series/count/item";
            $.ajax({
                url: url,
                type: 'GET',
                data: {id: id},
                success: function (data) {
                    $('#right').html(data);
                }

            });
        },
        _loadTree: function () {
            var self = this;
            var setting = {
                view: {
                    addDiyDom: addDiyDom
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: 'id',//主键
                        pIdKey: 'pid' //上级id
                    }
                },
                callback: {
//                    onClick: self._treeNodeClick
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
        }
    };
    function addDiyDom(treeId, treeNode) {
        var aObj = $("#" + treeNode.tId + "_a");
//        if ($("#fileBtn_"+treeNode.id).length>0){
//            return;
//        }
        var editStr;
        var file_a="<a  class='diyBtn1'  id='fileBtn_" + treeNode.id+ "' onfocus='this.blur();'>文件</a>";
        var spare_a="<a  class='diyBtn1'  id='spareBtn_" + treeNode.id+ "' onfocus='this.blur();'>备件</a>";
        var product_a="<a  class='diyBtn1'  id='productBtn_" + treeNode.id+ "' onfocus='this.blur();'>产品</a>";
        if(treeNode.data.parentId ==0){
            editStr =file_a+spare_a;
        }else{
            editStr =file_a+spare_a+product_a;
        }
        var treeHtml="<div style='width: 94px'>"+editStr+"</div>";
        aObj.append(treeHtml);


        var fileBtn = $("#fileBtn_"+treeNode.id);
        var spareBtn = $("#spareBtn_"+treeNode.id);
        var productBtn = $("#productBtn_"+treeNode.id);
        spareBtn.bind("click", function(){
            var id = treeNode.data.id;
            var url = "/sp/spare/count";
            showLoading();
            $.ajax({
                url: url,
                type: 'GET',
                data: {id: id},
                success: function (data) {
                    $('#right').html(data);
                    hideLoading();
                }
            });
            });

        fileBtn.bind("click", function(){
            var id = treeNode.data.id;
            var url = "/sp/file";
            showLoading();
            $.ajax({
                url: url,
                type: 'GET',
                data: {id: id},
                success: function (data) {
                    $('#right').html(data);
                    hideLoading();
                }
            });
        });

        productBtn.bind("click", function(){
            var id = treeNode.data.id;
            var url = "/sp/panel";
            showLoading();
            $.ajax({
                url: url,
                type: 'GET',
                data: {id: id},
                success: function (data) {
                    $('#right').html(data);
                    hideLoading();
                }
            });
        });
    }
    $(document).ready(function () {
        $("dic").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
        file._loadTree();
        $(el.tree_search_btn).on('click', function () {
            file._treeSearch()
        });

    });
</script>
<style type="text/css">
    ul[role='group'] li {
        cursor: pointer;
    }
    .diyBtn1{
        color: #0c7cd5 !important;
        padding-left: 5px;
        text-decoration: underline !important;
    }
    .ztree li>a {
        display: inline;
    }
    .ztree li>a>div{
        float: right !important;

    }
    .dd3-content {
        cursor: pointer;
    }

    .dd3-content-active {
        color: #2ea8e5;
        background: #fff;
    }
</style>
</#compress>