<#compress>
<div style="padding: 4% 4% 0 4%">
    <div id="myCarousel" class="carousel slide">
        <!-- 轮播（Carousel）指标 -->
        <ol class="carousel-indicators">
             <#if exhibitions??>
                 <#list exhibitions as item>
                     <li data-target="#myCarousel" data-slide-to="${item_index}" <#if item_index == 0>class="active"</#if>></li>
                 </#list>
             </#if>
        </ol>
        <!-- 轮播（Carousel）项目 -->
        <div class="carousel-inner">
            <#if exhibitions??>
                <#list exhibitions as item>
                    <div class="item <#if item_index == 0>active</#if>">
                        <a class="banner-img" href="${item.url!}" target="_blank" style="background-image: url(${item.image!});"></a>
                    </div>
                </#list>
            </#if>
        </div>
        <!-- 轮播（Carousel）导航 -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>
<link rel="stylesheet" href="../static/js/plugin/ztree/css/metroStyle/metroStyle.css"/>
<script type="text/javascript" src="../static/js/plugin/ztree/js/jquery.ztree.all.min.js"></script>
<script>
    $(".carousel").carousel({
        interval: 8000 //每隔两秒自动轮播
    });
    // var myExamine,myCustomerTable,myFilesTable,myOfferTable;
    // $(document).ready(function () {
    //     $("[data-dic]").each(function () {
    //         $(this).html(message[$(this).data("dic")]);
    //     });
    //     $("dic").each(function () {
    //         $(this).html(message[$(this).data("dic")]);
    //     });
    //
    //     $('.select2').select2();

        // InitData();

        // InitEvent();

        // initZTree();
        /**
         * 点击事件效果
         */
//         function InitEvent() {
//             //点击效果
//             $('.file-type,.product-type').off('click').click(function () {
//                 if($(this).hasClass('file-type-btn-active')){
//                     $(this).removeClass('file-type-btn-active');
//                 }else{
//                     $('.file-type.file-type-btn-active').removeClass('file-type-btn-active');
//                     $(this).addClass('file-type-btn-active');
//                 }
//             });
//             $('.product-type').off('click').click(function () {
//                 if($(this).hasClass('file-type-btn-active')){
//                     $(this).removeClass('file-type-btn-active');
//                 }else{
//                     $('.product-type.file-type-btn-active').removeClass('file-type-btn-active');
//                     $(this).addClass('file-type-btn-active');
//                 }
//
//             });
//
//             //文件搜索
//             $('#btn_files_query').click(function () {
//                 var fileNmae = $('#file_name_query').val();
//                 var productType = $('.product-types .file-type-btn-active').data('type');
//                 var fileType = $('.file-types .file-type-btn-active').data('type');
//                 myFilesTable.addAjaxParam('name', fileNmae);
//                 myFilesTable.addAjaxParam('fileMarketDetailId', productType ? [productType] : '');
//                 myFilesTable.addAjaxParam('category', fileType);
//                 myFilesTable.submitFilter();
//             });
//
//             //文件搜索
//             $('#btn_file_query').click(function () {
//                 fileTableRefresh();
//             });
//
//             $('#my_files_table').on('click', '.view', function () {
//                 var id = $(this).data('id');
//                 var type = $(this).data('type');
//                 var url = $(this).data('url');
//                 if (type == 999) {
//                     if (url.indexOf("http://")==0 || url.indexOf("https://")==0) {
//                         window.location.href = url;
//                     }else {
//                         window.location.href = 'http://'+ url;
//                     }
//                 }else {
//                     window.open('/file/view?id=' + id);
//                 }
//             });
//         }
//
//         function InitData() {
//             myExamine = new Datatables();
//             myExamine.init({
//                 src: $("#my_examine_table"),
//                 loadingMessage: message["datatable.loading"],
//                 dataTable: {
//                     bStateSave: false,
//                     ajax: {url: "/approval/rest/list?status=1"},
//                     columns: [
// //                        DTColumnKit.order,
//                         {data: "seriesName", orderable: false,title: message["quotes.offer.list.seriesName"], width: '80px'},
//                         {data: "customerName",orderable: false, title: message["quotes.offer.list.customer"]},
//                         {data: "totalPrice",orderable: false,title: message["quotes.offer.list.totalPrice"],className: 'text-right', width: "120px"},
//                         {data: "username",orderable: false, title:message["portal.approval.username"], width: "80px"},
//                         {data: "createTime",orderable: false, title:message["portal.approval.createTime"],
//                             width: "90px",
//                             render: function (data, type, row, meta) {
//                                 if (data === null) {
//                                     return data;
//                                 } else {
//                                     return (new Date(data)).Format("yyyy-MM-dd");
//                                 }
//                             }},
//                         {data: "approvalId", "visible": false}
//                     ]
//                 }
//             });
//
//             myCustomerTable = new Datatables();
//             myCustomerTable.init({
//                 src: $("#my_customer_table"),
//                 loadingMessage: message["datatable.loading"],
//                 dataTable: {
//                     bStateSave: false,
//                     ajax: {url: "/api/customer/datagrid?"},
//                     columns: [
// //                        DTColumnKit.order,<span style="width:30px;overflow:hidden;text-overflow:ellipsis">
//                         {data: "name", title: message["org.customer.list.name"], width:"270px",'orderable': false,
//                             render: function (data) {
//                                 return '<div class="omitted" style="width: 150px;" title="'+ data +'">' + data + '</div>'
//                             }},
//                         {data: "website", title: message["customer.item.website"], width:"100px",'orderable': false,
//                             render: function (data) {
//                                 return '<div class="omitted" style="width:100px;" title="'+ data +'">' + data + '</div>'
//                         }},
//                         {data: "phone", title: message["org.customer.list.phone"],  'orderable': false,
//                             render: function (data) {
//                                 return '<div class="omitted" style="width:140px;" title="'+ data +'">' + data + '</div>'
//                         }},
//                         {data: "email", title: message["customer.item.email"], width:"50px", 'orderable': false,
//                             render: function (data) {
//                                 return '<div class="omitted" style="width:100px;" title="'+ data +'">' + data + '</div>'
//                         }}
//                     ]
//                 }
//             });
//
//             myFilesTable = new Datatables();
//             myFilesTable.init({
//                 src: $('#my_files_table'),
//                 loadingMessage: message["datatable.loading"],
//                 dataTable: {
//                     bStateSave: false,
//                     ajax: {url: '/file/rest/datagrid'},
//                     columns: [
// //                        DTColumnKit.order,
//                         {data: "name",'orderable': false,title: message["file.list.table.name"],
//                             render: function (data) {
//                                 return '<div class="omitted" style="width:180px;" title="'+ data +'">' + data + '</div>'
//                         }},
//                         {data: "extend",title: message["file.list.table.extend"],'orderable': false, width: "70px"},
//                         {
//                             data: "size",
//                             'orderable': false,
//                             title: message["file.list.table.size"],
//                             width: "60px",
//                             render: function (fileSize, display, row) {
//                                 var temp = 0;
//                                 if (fileSize < 1024) {
//                                     return fileSize + 'B';
//                                 } else if (fileSize < (1024 * 1024)) {
//                                     temp = fileSize / 1024;
//                                     temp = temp.toFixed(2);
//                                     return temp + 'KB';
//                                 } else if (fileSize < (1024 * 1024 * 1024)) {
//                                     temp = fileSize / (1024 * 1024);
//                                     temp = temp.toFixed(2);
//                                     return temp + 'MB';
//                                 } else {
//                                     temp = fileSize / (1024 * 1024 * 1024);
//                                     temp = temp.toFixed(2);
//                                     return temp + 'GB';
//                                 }
//                             }
//                         },
//                         {
//                             data: "uploadTime",
//                             orderable: false,
//                             width: '130px',
//                             title: message['file.list.table.uploadTime'],
//                             render: function (data, display, row) {
//                                 if (data === null) {
//                                     return data;
//                                 } else {
//                                     return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
//                                 }
//                             }
//                         },{
//                             data: "id",
//                             orderable: false,
//                             width: '100px',
//                             title: message['product.spare.list.operate'],
//                             render: function (data, display, row) {
//                                 var viewHtml = '';
//                                 var fileType = row.extend;
//                                 var fileTypes = ['png','jpg','jpeg','gif','doc','docx','xls','xlsx','ppt','pptx','pdf','video','link'];
//                                 if($.inArray(fileType,fileTypes) >= 0){
//                                     viewHtml = '&nbsp;<a class="btn btn-xs btn-primary view" ' + ' href="javascript:;"' +
//                                             ' data-id="' + data + '"' +' data-type="'+ row.type +'"' + ' data-url="'+ row.url +'"' + '>'
//                                             + message["body.list.view"]+'</a>';
//                                 }
//                                 return viewHtml
//                             }
//                         }
//                     ]
//                 }
//             });
//
//             myOfferTable = new Datatables();
//             myOfferTable.init({
//                 src: $("#my_offer_table"),
//                 loadingMessage: message["datatable.loading"],
//                 dataTable: {
//                     bStateSave: false,
//                     ajax: {url: "/offer/rest/list"},
//                     columns: [
// //                        DTColumnKit.order,
//                         {data: "num", orderable: false, title: message["body.list.code"], width: "180px"},
//                         {data: "customerName",orderable: false, title: message["quotes.offer.list.customer"], width: "150px",
//                             render: function (data) {
//                                 return '<div class="omitted" style="width:150px;" title="'+ data +'">' + data + '</div>'
//                             }
//                         },
//                         {data: "payment",orderable: false, title: message["quotes.offer.list.payment"]},
//                         {title: message["quotes.offer.list.totalPrice"],width: "100px",orderable: false,
//                             render: function (data, type, row) {
//                                 return row.rate  + ((row.totalPrice/(row.totalDiscount/100)).toFixed(2));
//                             }
//                         }
//                     ]
//                 }
//             });
//         }
//
//         //初始化树
//         function initZTree() {
//             var self = this;
//             var setting = {
//                 data: {
//                     simpleData: {
//                         enable: true,
//                         idKey: 'id',//主键
//                         pIdKey: 'pid'//上级id
//                     }
//                 },
//                 check:{
//                     chkStyle: 'checkbox',
//                     chkboxType:{ "Y" : "", "N" : "" },
//                     enable: true
//                 },
//                 callback: {
//                     onCheck: function (event, treeId, treeNode) {
//                         var zTree  = $.fn.zTree.getZTreeObj('folder_tree');
//                         var zTreeOne = zTree.getNodes()[0].children; //第一级
//                         if(zTreeOne.length === 0){
//                             return;
//                         }
//
//                         var oneCheckedCount = 0;
//                         var parentEq = true;
//                         var oneParent;
//                         zTreeOne.forEach(function (item) {
//                             if(item.data.parentId === '1' && item.checked){
//                                 oneCheckedCount++;
//                                 oneParent = item;
//                             }
//                         });
//                         if(oneCheckedCount >= 2){
//                             parentEq = false;
//                         }
//                         var checkDatas = zTree.getCheckedNodes();
//                         var showNames = [];
//                         var ids = [];
//                         $.each(checkDatas, function (index,item) {
//                             if(item.data.parentId === '0'){
//                                 checkDatas.splice(index,1);
//                                 showNames.push('总部');
//                                 ids.push('0');
//                                 return false;
//                             }
//                         });
//                         checkDatas.forEach(function (item) {
//                             showNames.push(item.data.name);
//                             ids.push(item.data.id);
//                             if(oneParent){
//                                 if(parentEq
//                                         && item.data.parentId !== '1'
//                                         && oneParent.data.id !== item.data.parentId){
//                                     parentEq = false;
//                                 }
//                             }else{
//                                 if(parentEq && item.data.parentId !== '1' && checkDatas[0].data.parentId !== item.data.parentId){
//                                     parentEq = false;
//                                 }
//                             }
//
//                         });
//                         $('#search_org_name').val(showNames.join(','));
//                         $('#search_org_name').data('ids',ids);
//                         $('#search_org_name').data('parentEq',parentEq);
//                     }
//                 }
//             };
//             var zNodes = [];
//             var name = $('#search_organization_item_input').val();
//             var searchObj = {};
//             if (name) {
//                 searchObj = {q: name};
//             }
//             //请求
//             $.ajax({
//                 url: '/file/rest/tree',
//                 data: searchObj,
//                 type: 'GET',
//                 dataType: 'json',
//                 success: function (data) {
//                     $.each(data, function (index,item) {
//                         if(item.data.parentId === '1'){
//                             //二级不展开
//                             item.open = false;
//                         }
//                     });
//                     console.log(data);
//                     zNodes = data;
//                     if (typeof data === "string") {//not obj
//                         zNodes = $.parseJSON(data);
//                     }
//                     $.fn.zTree.init($('#folder_tree'), setting, zNodes);
//                 },
//                 error: function (data) {
//                     alert(data.responseJSON.message);
//                 }
//             });
//         }
//
//         $("#search_organization_item_btn").click(function () {
//             initZTree();
//         });
//
//         //文件列表刷新
//         function fileTableRefresh() {
//             var subdivision = $('#fileMarketDetails').val();//细分
//             var fileType = $('#files_query').val();//文件类型
//             var orgIds = $('#search_org_name').data('ids');//应用场景
//             var fileName = $('#file_name').val();//文件名称
//             myFilesTable.addAjaxParam('fileMarketDetailId',  subdivision?[subdivision]:'');
//             myFilesTable.addAjaxParam('category', fileType);
//             myFilesTable.addAjaxParam('folderId', orgIds);
//             myFilesTable.addAjaxParam('name', fileName);
//             myFilesTable.submitFilter();
//         }
//
//         /**
//          * 工具方法
//          */
//         function Util() {
//             return{
//
//             }
//         }
//     });
//     //时间的格式化
//     Date.prototype.Format = function (fmt) { //author: meizz 
//         var o = {
//             "M+": this.getMonth() + 1,
//             //月份 
//             "d+": this.getDate(),
//             //日 
//             "h+": this.getHours(),
//             //小时 
//             "m+": this.getMinutes(),
//             //分 
//             "s+": this.getSeconds(),
//             //秒 
//             "q+": Math.floor((this.getMonth() + 3) / 3),
//             //季度 
//             "S": this.getMilliseconds() //毫秒 
//         };
//         if (/(y+)/.test(fmt)) {
//             fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
//         }
//         for (var k in o) {
//             if (new RegExp("(" + k + ")").test(fmt)) {
//                 fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
//             }
//         }
//         return fmt;
//     }
</script>
<style>
    .banner-img {
        display: block;
        padding-top: 34.9%;
        background-repeat: no-repeat;
        background-position: center;
        background-size: cover;
    }
</style>
</#compress>