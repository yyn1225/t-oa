<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div id="first">
        <div class="jarviswidget" data-widget-editbutton="false" style="margin: 0 0 10px">
            <header>
                <h2><dic data-dic="header.list.search"></dic></h2>
            </header>
            <div>
                <div class="widget-body no-padding"  style="min-height: 120px">
                    <form class="smart-form">
                        <fieldset style="height: auto !important;">
                            <div class="row">
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="quotes.offer.list.customer"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="name-query" class="input-autosuggest"/>
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="offer.create.sales"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="sales-query"/>
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="offer.create.project.name"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="remark-query" class="input-payment"/>
                                </label>
                                <#--<label class="col col-1 text-align-right padding">-->
                                    <#--<dic data-dic="quotes.offer.list.payment"></dic>:-->
                                <#--</label>-->
                                <#--<label class="col col-3 input">-->
                                    <#--<input type="text" id="payment-query" class="input-payment"/>-->
                                <#--</label>-->
                            </div>
                            <div class="row" style="margin-top: 10px;">
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="body.list.code"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="code-query" class="input-autosuggest"/>
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="offer.view.startTime"></dic>:
                                </label>
                                <label class="col col-3 input-group">
                                    <input type="text" id="startTime" class="form-control datepicker"
                                           data-dateformat="yy-mm-dd">
                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                </label>

                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="offer.view.endTime"></dic>:
                                </label>
                                <label class="col col-3 input-group">
                                    <input type="text" id="endTime" class="form-control datepicker"
                                           data-dateformat="yy-mm-dd">
                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                </label>
                            </div>
                            <div class="row" style="margin-top: 10px;">
                                <label class="col col-1 text-align-right padding">
                                    产品系列:
                                </label>
                                <label class="col col-3 input">
                                    <select class="select2" style="width: 100%" multiple id="series-query">
                                        <option value=""></option>
                                        <#list (series)! as item>
                                            <optgroup label="${(item.text)!}">
                                                <#list item.children as child>
                                                    <option value="${(child.id)!}">${(child.text)!}</option>
                                                </#list>
                                            </optgroup>
                                        </#list>
                                    </select>
                                </label>
                                <#--<label class="col col-1 text-align-right padding">-->
                                    <#--区域:-->
                                <#--</label>-->
                                <#--<label class="col col-3 input">-->
                                    <#--<div class="dropdown dropdown-large dp">-->
                                        <#--<input type="text" id="search_org_name" value="" data-toggle="dropdown" data-ids="" readonly/>-->
                                        <#--<ul class="dropdown-menu dropdown-menu-large row"-->
                                            <#--style="margin: 0 !important;padding: 0!important;">-->
                                            <#--<div class="ztree" id="area_tree" style="height: 200px;overflow-y: auto;"></div>-->
                                        <#--</ul>-->
                                    <#--</div>-->
                                <#--</label>-->

                                <a class="btn btn-default btn-sm query-btn pull-right mr15" id="btn_reset">
                                    <dic data-dic="button.reset"></dic>
                                </a>
                                <a class="btn btn-primary btn-sm query-btn pull-right mr15" id="btn_query">
                                    <dic data-dic="header.list.button.search"></dic>
                                </a>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <div class="jarviswidget" data-widget-colorbutton="false" data-widget-editbutton="false">
            <header>
                <span class="widget-icon"> <i class="fa fa-table"></i></span>
                <h2>
                    <dic data-dic="quotes.offer.list.title"></dic>
                </h2>
            </header>
            <div>
                <div class="widget-body no-padding">
                    <table id="new_quotes" class="table table-striped table-bordered table-hover table-center">
                    </table>
                </div>
            </div>
        </div>
    </div>
</article>
<div id="second"></div>
<div class="modal fade" id="delete-prompt" tabindex="-1" role="dialog">
    <div class="modal-dialog" style="width: 350px;">
        <input type="hidden" value="0" id="delete_offer_id">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    <dic data-dic="portal.window.attention"></dic>
                </h4>
            </div>
            <div class="modal-body no-padding">
                <form class="smart-form">
                    <fieldset class="textPop" style="padding-bottom: 25px">
                        <dic data-dic="prompt.message.delete"></dic>
                    </fieldset>
                    <footer>
                        <button type="button" class="btn btn-default" id="delete-close"
                                data-dismiss="modal">
                            <dic data-dic="button.cancel"></dic>
                        </button>
                        <button type="button" id="delete-offer" class="btn btn-primary">
                            <dic data-dic="button.confirm"></dic>
                        </button>
                    </footer>
                </form>
            </div>
        </div>
    </div>
</div>
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
<link rel="stylesheet" href="../../../static/js/plugin/bootstrap-autosuggest/autosuggest.css"/>
<script type="text/javascript" src="../../../static/js/plugin/bootstrap-autosuggest/autosuggest.js"></script>
<script type="text/javascript">
    var $first = $('#first');
    var $second = $('#second');
    var dt;
    var article = "article";
    var delete_prompt = "#delete-prompt";
    var delete_input = "#delete_offer_id";
    var delete_offer = "#delete-offer";
    $('#startTime').datepicker({
        dateFormat : 'yy-mm-dd',
        prevText : '<i class="fa fa-chevron-left"></i>',
        nextText : '<i class="fa fa-chevron-right"></i>',
        onSelect : function(selectedDate) {
            $('#endTime').datepicker('option', 'minDate', selectedDate);
        }
    });
    $.ajax({
        url: '/api/customer/customer/autosuggest',
        type: 'GET',
        success: function (res) {
            $('#name-query').autosuggest({
                dataList:res,
                local:true,
                minLength:1
            });
        }
    });


    $('#endTime').datepicker({
        dateFormat : 'yy-mm-dd',
        prevText : '<i class="fa fa-chevron-left"></i>',
        nextText : '<i class="fa fa-chevron-right"></i>',
        onSelect : function(selectedDate) {
            $('#startTime').datepicker('option', 'maxDate', selectedDate);
        }
    });
    $(document).ready(function () {

        pageSetUp();
        $("dic").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
        dt = new Datatables();
        dt.init({
            src: $("#new_quotes"),
            loadingMessage: message["datatable.loading"],
            dataTable: {
                bStateSave: false,
                bDestory: true,
                bServerSide: true,
                ajax: {url: "/offer/rest/list"},
                columns: [
                    DTColumnKit.order,
                    // {data:null, className: 'details-control', defaultContent: '', width: "40px"},
                    {data: "num", title: message["body.list.code"], className: "show-dropdown",
                        render: function (data, type, row, meta) {
                            return '<div>' + data + '</div>' +
                                    '<div class="ajax-dropdown dropup" style="height: auto; left: auto; display: none;">' +
                                    '<div style="width: 100%;text-align: center;padding: 2px;">' +
                                    '<img style="width: 120px;height: 120px;border: 1px solid #ccc;" ' +
                                    'src="' + row.qcodeUrl + '" onerror="javascript:this.src=\'../../../static/img/no-image.png\'">' +
                                    '</div>' +
                                    '</div>';
                        }
                    },
                    {data: "customerName", title: message["quotes.offer.list.customer"],
                        width: "150px"},
                    // {data: "seriesName", title: message["quotes.offer.list.seriesName"]},
                    // {title: message["quotes.offer.list.totalPrice"], className: 'text-right',
                    //     render: function (data, type, row, meta) {
                    //         return row.rate + moneyFormat((row.totalPrice/(row.totalDiscount/100)),2);
                    //     }},
                    {data: "totalDiscount", title: message["quotes.offer.list.discount"], className: 'text-right',
                    render: function (data, type, row, meta) {
                        return data + "%"
                    }},
                    {data: "totalPrice",title: message["quotes.offer.list.priceReal"], className: 'text-right',
                    render: function (data, type, row, meta) {
                        return row.rate + (data + '').replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                    }},
                    {data: "offere", title: message["offer.create.sales"]},
                    {data: "createTime", title:  message["quotes.offer.list.createdTime"],
                    render: function (data) {
                        if (data === null) {
                            return data;
                        } else {
                            return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
                        }
                    }},
                    {data: "projectName", title: message["offer.create.project.name"],className: "show-n", width: "80px"},
                    {data: "offerStatus", title: message["product.panel.list.status"],
                        width: '60px',
                         render: function (data, type, row, meta) {
                             if (data == 0) {
                                 return '<span class="center-block padding-5 label label-default">' + message['body.list.draft'] + '</span>';
                             }
                             if (data == 1) {
                                 return '<span class="center-block padding-5 label label-warning">' + message['body.list.inApproval'] + '</span>';
                             }
                             if (data == 2) {
                                 return '<span class="center-block padding-5 label label-success">' + message['body.list.agree'] + '</span>';
                             }
                             if (data == 3) {
                                 return '<span class="center-block padding-5 label label-danger">' + message['body.list.refuse'] + '</span>';
                             }
                             if (data == 4) {
                                 return '<span class="center-block padding-5 label label-info">' + message['body.list.retract'] + '</span>';
                             }
                             if (data == 5) {
                                 return '<span class="center-block padding-5 label label-success">' + message['body.list.finish'] + '</span>';
                             }
                             else {
                                 return '';
                             }
                    }},
                    {data: "id", title: message["quotes.offer.list.operate"],
                        render: function (data, type, row, meta) {
                            // var copyHtml = '<a class="btn btn-xs btn-primary copy" data-id="'+data+'">' +
                            //         message["quotes.offer.list.quick"]+'</a>';
                            var downLoad = '<div class="btn-group">' +
                                    '<button class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown"> ' + message['button.export'] +
                                    '&nbsp<span class="caret"></span>' +
                                    '</button>' +
                                    '<ul class="dropdown-menu">' +
                                    '<li><a href="/offer/export/create?offerId='+ data +'&language=zh' + '">' + message["button.exportCN"] + '</a></li>' +
                                    '<li class="divider"></li>' +
                                    '<li><a href="/offer/export/create?offerId='+ data +'&language=en' + '">' + message["button.exportEN"] + '</a></li>' +
                                    '<li class="divider"></li>' +
                                    '<li><a href="/offer/export/createPdf?offerId='+ data +'&language=zh' + '">' + message["button.exportPdfCN"] + '</a></li>' +
                                    '<li class="divider"></li>' +
                                    '<li><a href="/offer/export/createPdf?offerId='+ data +'&language=en' + '">' + message["button.exportPdfEN"] + '</a></li>' +
                                    '</ul>' +
                                    '</div>';
                            var sendMailFun = '<div class="btn-group">' +
                                    '<button class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown"> ' + message['body.list.mail'] +
                                    '&nbsp<span class="caret"></span>' +
                                    '</button>' +
                                    '<ul class="dropdown-menu">' +
                                    '<li><a class="sendMail" href="javascript:;"  data-language="zh"  data-id="'+data+ '">' + message["button.mail.exportCN"] + '</a></li>' +
                                    '<li class="divider"></li>' +
                                    '<li><a class="sendMail" href="javascript:;" data-language="en"  data-id="'+data+ '">'+ message["button.mail.exportEN"] + '</a></li>' +
                                    '<li class="divider"></li>' +
                                    '<li><a class="sendMail" href="javascript:;" data-language="zhpdf"  data-id="'+data+ '">' + message["button.mail.exportPdfCN"] + '</a></li>' +
                                    '<li class="divider"></li>' +
                                    '<li><a class="sendMail"  href="javascript:;" data-language="enpdf"  data-id="'+data+ '">' + message["button.mail.exportPdfEN"] + '</a></li>' +
                                    '</ul>' +
                                    '</div>';
                            var editHtml = '<button class="btn btn-xs btn-primary edit" data-id="'+data+'">' +
                                    message["body.list.viewOffer"]+'</button>';
                            var removeHtml = '<button class="btn btn-xs btn-default enabled remove" data-id="'+data+'">' +
                                    message["body.list.delete"]+'</button>';
                            var removeDisable = '<button class="btn btn-xs btn-default remove" disabled=disabled data-id="'+data+'">' +
                                    message["body.list.delete"]+'</button>';
                            if (row.offerStatus == 0 || row.offerStatus == 4) {
                                return '<div style="width: 270px">' + downLoad + sendMailFun + editHtml + removeHtml + '</div>';
                            }else {
                                return '<div style="width: 270px">' + downLoad + sendMailFun + editHtml + removeHtml + '</div>';
                            }
                        }
                    }
                ],
                columnDefs: [{
                    orderable: false,
                    targets: [0,1,2,3,4,5,6,7,8,9]
                }],
                drawCallback: function () {
                    // $(".dataTables_scrollBody").css("margin-bottom", "13px");
                }
            }
        });

        $(article).on('click', '.operate', function () {
            showLoading();
            var id = $(this).data('id');
            $.ajax({
                url: '/offer/item',
                type: 'GET',
                data: {offerId: id},
                success: function (data) {
                    $first.hide();
                    $second.show();
                    $second.html(data);
                    hideLoading();
                }
            });
        });
        $('#new_quotes tbody').on('click','.sendMail', function () {
            var id = $(this).data('id');
            var language = $(this).data('language');

            var url = "/offer/mail";
            var $modelHtml = $("#mail_html");
            var $model = $("#mail_model");
            $.ajax({
                url: url,
                type: 'GET',
                data: {
                    fileIds: id,
                    language: language
                },
                success: function (html) {
                    $model.modal('show');
                    $modelHtml.html(html);
                }
            });
        });
        // 确定发送邮件
        $('#mail_send').on('click', function () {

            // console.log($('#fileIds').val());
            var idsStr = $('#fileIds').data('ids');
            var language = $('#language').data('language');
            var url ;
            if (language == 'zh' || language == 'en'){
               url =    '/offer/export/send';
            }else{
                url =  '/offer/export/sendPdf';
            }
            // var ids = JSON.parse(idsStr);
            //获取表单对象
            var bootstrapValidator = $('#mail_send_form').data('bootstrapValidator');
            //手动触发验证
            bootstrapValidator.validate();
            if (bootstrapValidator.isValid()) {
                $.ajax({
                    url:url,
                    type: 'POST',
                    data: {
                        id: idsStr,
                        language: language,
                        acceptMail: $('#acceptMail').val(),
                        mailSubject: $('#mailSubject').val()/*,
                        mailValidity: $('#mailValidity').val()*/
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

        $(article).on('click', '.edit', function () {
            showLoading();
            var id = $(this).data('id');
            $.ajax({
                url: '/offer/create/edit',
                type: 'GET',
                data: {offerId: id},
                success: function (data) {
                    $first.hide();
                    $second.show();
                    $second.html(data);
                    hideLoading();
                }
            });
        });
        $(article).on('click', '.remove', function () {
            var id = $(this).data('id');
            $(delete_prompt).modal('show');
            $(delete_input).val(id);
        });
        $(delete_offer).click(function () {
            var id = $(delete_input).val();
            $(delete_prompt).modal('hide');
            $.ajax({
                url: '/offer/delete?id=' + id,
                type: 'get',
                success: function (data) {
                    if (data) {
                        alert(message['http.response.success']);
                        dt.resetFilter();
                    }
                },
                error: function (data) {
                    alert(message[data.responseJSON.message], 3);
                }
            });
        });
        $("#btn_query").click(function () {

            dt.addAjaxParam('customerName', $.trim($('#name-query').val()));
            dt.addAjaxParam('sales', $.trim($('#sales-query').val()));
            dt.addAjaxParam('num', $.trim($('#code-query').val()));
            dt.addAjaxParam('remark', $.trim($('#remark-query').val()));
            dt.addAjaxParam('startTime', $.trim($("#startTime").val()));
            dt.addAjaxParam('endTime',$.trim($("#endTime").val()));
            if ($('#series-query').select2('val').length > 0) {
                dt.addAjaxParam('series', $('#series-query').select2('val'));
            }else {
                dt.addAjaxParam('series', '');
            }
            dt.submitFilter();
        });
        $("#btn_reset").click(function () {
            $("#name-query").val('');
            $("#startTime").val('');
            $("#endTime").val('');
            $('#code-query').val('');
            $('#remark-query').val('');
            $('#series-query').select2("val",[]);
            $('#series-query').val("val",'');
            $('#startTime').datepicker('option', 'maxDate', '');
            $('#endTime').datepicker('option', 'minDate', '');
            dt.resetFilter();
        });

        // $('#new_quotes tbody').on('click', 'td.details-control', function () {
        //     var tr = $(this).closest('tr');
        //     var row = dt.getDataTable().row( tr );
        //
        //     if ( row.child.isShown() ) {
        //         // This row is already open - close it
        //         row.child.hide();
        //         tr.removeClass('shown');
        //     }
        //     else {
        //         // Open this row
        //         row.child( format(row.data()) ).show();
        //         tr.addClass('shown');
        //     }
        // });
        $('#new_quotes tbody').on('mouseover', '.show-dropdown', function(){
            $(this).find(".ajax-dropdown").show();
        });
        $('#new_quotes tbody').on('mouseout', '.show-dropdown', function(){
            $(this).find(".ajax-dropdown").hide();
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
    };

    function formatNum(num){
        return (num || 0).toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,');
    };

    function format ( d ) {
        // `d` is the original data object for the row
        var panelHtml = '';
        $.each(d.panelsList,function (index, item) {
            panelHtml += '<tr><td style="width: 38px">' + message["offer.create.panel"] + ':</td><td>'+ item.remark +
                    '<br><small class="text-muted"><span>' + d.rate + formatNum(item.totalPrice) + '</span></small></td></tr>';
        });
        return '<table cellpadding="5" cellspacing="0" border="0" class="table table-hover table-condensed">'+
                panelHtml +
                '</table>';
    }
    //判断图片路径是否有效
    function CheckImgExists(imgurl) {
        var ImgObj = new Image(); //判断图片是否存在
        ImgObj.src = imgurl;
        //没有图片，则返回-1
        if (ImgObj.fileSize > 0 || (ImgObj.width > 0 && ImgObj.height > 0)) {
            return true;
        } else {
            return false;
        }
    }
</script>
<style type="text/css">
    .smart-form fieldset + fieldset {
        border-top: none !important;
    }
    .btn-xs{
        margin-right: 5px;
        margin-top: 5px;
    }
    /*.smart-form fieldset {*/
        /*padding: 20px 14px 5px;*/
        /*height: 50px;*/
    /*}*/
    .input-autosuggest{
        width: 100%;
        height: 32px;
        border: 1px solid #c9ccd0;
        padding: 0 7px;
    }
    .list-group .as-align-left{
        padding: 10px 15px;
    }
    .dataTables_scroll .dataTables_scrollHeadInner {
        margin-top: 0px !important;
    }
    .btn.btn-xs.btn-default.remove.enabled:hover{
        color: red !important;
        border-color: #ff5757 !important;
    }
    table .ajax-dropdown{
        top: auto !important;
        width: auto !important;
    }
    .show-n {
        word-wrap:break-word;
        word-break:break-all;
    }
</style>
</#compress>