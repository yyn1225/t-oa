<#compress>
<article class="col-xs-12 col-sm-12 col-md-3 col-lg-3 list">
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <h2>
                <dic data-dic="spare.series.tree.title"></dic>
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
                <ul id="ul_tree_organization" class="ztree"

                    style="overflow-y: scroll;height:
                400px;"></ul>
            </div>
        </div>
    </div>
</article>
<article class="col-xs-12 col-sm-12 col-md-9 col-lg-9 list">
    <div id="right"></div>
</article>

<style type="text/css">
    .dd3-content {
        cursor: pointer;
    }

    .dd3-content-active {
        color: #2ea8e5;
        background: #fff;
    }
</style>
<link rel="stylesheet" href="../static/js/plugin/ztree/css/metroStyle/metroStyle.css"/>
<script type="text/javascript" src="../static/js/plugin/ztree/js/jquery.ztree.all.min.js"></script>
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
        _folderTreeNodeClick: function (event, treeId, treeNode) {
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
                data: {
                    simpleData: {
                        enable: true,
                        idKey: 'id',//主键
                        pIdKey: 'pid' //上级id
                    }
                },
                callback: {
                    onClick: self._folderTreeNodeClick
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
</style>
</#compress>