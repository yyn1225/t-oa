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
                                <span class="input-group-addon" id="btn_query">
                                    <i class="fa fa-search"></i></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="input-group">
                                <a id="tree_launch" class="btn btn-primary btn-xs">+</a>
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


<link rel="stylesheet" href="../../static/js/plugin/ztree/css/metroStyle/metroStyle.css"/>
<style type="text/css">
    ul[role='group'] li {
        cursor: pointer;
    }
    .diyBtn1{
        color: #0c7cd5 !important;
        padding-left: 5px !important;
        text-decoration: underline !important;
    }
    .ztree li a{
        display: inline !important;
        vertical-align: middle !important;
    }
    .ztree li a.curSelectedNode {
        padding: 2px !important;
    }
    .ztree li>a>div{
        float: right !important;
        /*text-decoration: underline;*/
    }
    .dd3-content {
        cursor: pointer;
    }

    .dd3-content-active {
        color: #2ea8e5;
        background: #fff;
    }
</style>
<script type="text/javascript" src="../../static/js/plugin/ztree/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript">
    var el = {
        ztree: '#ul_tree_organization',
        tree_search_btn: '#btn_query',
        tree_search_input: '#search_organization_input'
    };
    var url = {
        ztree: '/api/series/tree'
    };
    var file = {
        _loadTree: function () {
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
                    onClick: zTreeOnClick
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
        var file_a="<a  class='diyBtn1'  id='fileBtn_" + treeNode.id+ "' onfocus='this.blur();'>"+ message["portal.series.files"]+"</a>";
//        var spare_a="<a  class='diyBtn1'  id='spareBtn_" + treeNode.id+ "' onfocus='this.blur();'>"+ message["portal.series.spares"]+"</dic></a>";
        //1表示该产品可用，加粗表示
        if (treeNode.data.enableProduct === 1) {
            var product_a="<a  class='diyBtn1' style='font-weight: bolder' id='productBtn_" + treeNode.id+ "' onfocus='this.blur();'>"+ message["product.panel.list.name"]+"</dic></a>";
        }else {
            var product_a="<a  class='diyBtn1' style='color: #ffb300 !important' id='productBtn_" + treeNode.id+ "' onfocus='this.blur();'>"+ message["product.panel.list.name"]+"</dic></a>";
        }

        if(treeNode.data.parentId ==0){
            editStr =file_a;
        }else{
            editStr =file_a+product_a;
        }
        var treeHtml="<div style='width: 74px'>"+editStr+"</div>";
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

    function zTreeOnClick(event, treeId, treeNode) {
        //判断当前点击节点的等级，如果是0，表示第一级节点，为系列，不触发以下函数
        if (treeNode.level === 0) {
            return;
        }
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
    }

    $(document).ready(function () {
        $("dic").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
        file._loadTree();
        $(el.tree_search_btn).on('click', function () {
            file._treeSearch()
        });
        $("#tree_launch").click(function () {
        	var treeObj=$.fn.zTree.getZTreeObj("ul_tree_organization");
    		var nodes=treeObj.getNodes();
    		//如果nodes为null 则return
		    if(!nodes) return;
			//将状态设置为expand
		    curStatus = 'expand';
		    //获取当前的树
		    var treeObj = $.fn.zTree.getZTreeObj("ul_tree_organization");
		    //循环展开节点
		    var val=$(this).html();
		    if(val=='+'){
	            treeObj.expandAll(true);
	            $(this).html("-");
	        }else{
	            treeObj.expandAll(false);
	            $(this).html("+");
			}

		   /** for (var i = 0 ; i < nodes.length; i++){
		        treeObj.expandNode(nodes[i],true,false,false);
		    	//递归 如果子节点的是父节点则进行递归操作
		        if(nodes[i].isParent && nodes[i].zAsync){
		            expandNodes(nodes[i].children);
		        } else {
		            goAsync = true;
		        }
		    }	**/	
        });
    });
</script>
</#compress>