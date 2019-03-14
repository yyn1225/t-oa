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
                        <fieldset style="height: auto !important;padding: 20px 14px">
                            <div class="row">
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="body.list.code"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="code-query" class="input-autosuggest"/>
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="quotes.offer.list.customer"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="name-query" class="input-autosuggest"/>
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="quotes.offer.list.payment"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="payment-query" class="input-payment"/>
                                </label>
                            </div>
                            <div class="row" style="margin-top: 10px;">
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="offer.create.remark"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="remark-query" class="input-payment"/>
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
                                <label class="col col-9"></label>
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
                    <dic data-dic="quotes.offer.preference.title"></dic>
                </h2>
                <a class="btn btn-primary btn-xs" style="float: right" data-spare-id="0" data-dic="quotes.offer.preference.addOffer" id="add_offer_btn"></a>
                <#--<a class="btn btn-primary btn-xs" style="float: right" data-dic="button.save" id="edit_offer_btn"></a>-->
            </header>
            <div>
                <div class="widget-body no-padding">
                    <table id="new_preference" class="table table-striped table-bordered table-hover" width="100%">
                    </table>
                </div>
            </div>
        </div>
    </div>
</article>
<div id="second"></div>
<div class="modal fade in" id="dialog_table" tabindex="-1" role="dialog">
    <div class="modal-dialog" style="width: 900px;">
        <input type="hidden" value="0" id="spare-type">
        <div class="modal-content">
            <div class="modal-header">
                <h4><dic data-dic="main.title.offer"></dic></h4>
            </div>
            <div class="modal-body no-padding">
                <form class="smart-form">
                    <fieldset>
                        <div id="choose_offer_dialog" class="custom-scroll table-responsive" style="max-height:450px; overflow-y: scroll;">

                        </div>
                    </fieldset>
                    <footer>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><dic data-dic="button.cancel"></dic></button>
                        <button type="button" id="offer-choose" class="btn btn-primary"><dic data-dic="offer.create.choose"></button>
                    </footer>
                </form>
            </div>
        </div>
    </div>
</div>

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
<link rel="stylesheet" href="../../../static/js/plugin/bootstrap-autosuggest/autosuggest.css"/>
<script type="text/javascript" src="../../../static/js/plugin/bootstrap-autosuggest/autosuggest.js"></script>
<script type="text/javascript">
    var $first = $('#first');
    var $second = $('#second');
    var preferenceTable;
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
        $('[data-dic]').each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
        preferenceTable = new Datatables();
        preferenceTable.setAjaxParam("preferenceFlag", true);
        preferenceTable.init({
            src: $("#new_preference"),
            loadingMessage: message["datatable.loading"],
            dataTable: {
                ajax: {url: "/offer/rest/list"},
                columns: [
                    DTColumnKit.order,
                    // {data:null, className: 'details-control', defaultContent: '', width: "40px"},
                    {data: "num", title: message["body.list.code"], width: "180px"},
                    {data: "customerName", title: message["quotes.offer.list.customer"],
                        width: "200px",
                        render: function (data, type, row, meta) {
                            return '<div class=".td-show-s" title="' + data + '">' + data + '</div>';
                        }},
                    {data: "totalPrice",title: message["quotes.offer.list.priceReal"], width: "100px",className: 'text-right',
                        render: function (data, type, row, meta) {
                            return row.rate + (data + '').replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                        }},
                    {data: "payment", title: message["quotes.offer.list.payment"], width: "120px"},
                    {data: "guarantee", title: message["quotes.offer.list.guarantee"],
                        width: "60px",
                        render: function (data, type, row, meta) {
                            var str;
                            if(data ==0){
                                str='';
                            }
                            if(data ==2){
                                str = '2' + message["portal.my.years"];
                            }
                            if(data ==3){
                                str = '3' + message["portal.my.years"];
                            }
                            if(data ==4){
                                str = '4' + message["portal.my.years"];
                            }
                            if(data ==5){
                                str = '5' + message["portal.my.years"];
                            }
                            return str;
                        }},
                    {data: "offere", title: message["offer.create.sales"],width:"60px"},
                    {data: "createTime", title:  message["quotes.offer.list.createdTime"],
                        width: "140px",
                        render: function (data) {
                            if (data === null) {
                                return data;
                            } else {
                                return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
                            }
                        }},
                    {data: "remark", title: message["offer.create.remark"], width: "250px", className: "show-n"},
                    {data: "preferenceId", title: message["quotes.offer.list.operate"],
                        width :"200px",
                        render: function (data, type, row, meta) {
                            var copyHtml = '<a class="btn btn-xs btn-primary copy" data-id="'+ row.id + '">' +
                                    message["body.list.generate"]+'</a>';
                            // var editHtml = '<button class="btn btn-xs btn-primary edit" data-id="'+row.id+'">' +
                            //         message["body.list.viewOffer"]+'</button>';
                            var removeHtml = '<button class="btn btn-xs btn-default remove" data-id="'+data+'">' +
                                    message["body.list.delete"]+'</button>';
                            return copyHtml + removeHtml;
                        }
                    }
                ],
                columnDefs: [{
                    orderable: false,
                    targets: [0,1,2,3,4,5,6,7,8,9]
                }],
                drawCallback: function () {
                    $(".dataTables_scrollBody").css("margin-bottom", "13px");
                }
            }
        });
        $(article).on('click', '.copy', function () {
            showLoading();
            var id = $(this).data('id');
            $.ajax({
                url: '/offer/create/copy',
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
        // $(article).on('click', '.edit', function () {
        //     debugger;
        //     $(this).parents('#new_preference_wrapper').find('#new_preference tbody tr').eq($(this).parents("tr").index()).find('td:nth-child(3)').addClass('smart-form');
        //     $(this).parents('#new_preference_wrapper').find('#new_preference tbody tr').eq($(this).parents("tr").index()).find('td:nth-child(3)').addClass('td-display');
        //     $(this).parents('#new_preference_wrapper').find('#new_preference tbody tr').eq($(this).parents("tr").index()).find('td:nth-child(3) span').hide();
        //     $(this).parents('#new_preference_wrapper').find('#new_preference tbody tr').eq($(this).parents("tr").index()).find('td:nth-child(3) input').show();
        // });
        // $('#new_preference_wrapper').on('keyup', '#new_preference tbody td:nth-child(3) input', function () {
        //     $(this).prev().html($(this).val());
        // });
        // var preferenceArr = [];
        // $("#edit_offer_btn").click(function () {
        //     $('#new_preference tbody td:nth-child(3) input').each(function () {
        //         var self = $(this);
        //         if (self.css("display")!="none") {
        //             var offerPreference = {
        //                 id: self.data('id'),
        //                 name: self.val()
        //             };
        //             preferenceArr.push(offerPreference);
        //         }
        //     });
        //     if (preferenceArr.length > 0) {
        //         showLoading();
        //         $.ajax({
        //             url: '/offer/preference/edit',
        //             type: 'post',
        //             data: {preferenceJson: JSON.stringify(preferenceArr)},
        //             success: function (data) {
        //                 $('#new_preference tbody td:nth-child(3) input').each(function () {
        //                     $(this).hide();
        //                 });
        //                 $('#new_preference tbody td:nth-child(3)').each(function () {
        //                     $(this).removeClass('smart-form');
        //                     $(this).removeClass('td-display');
        //                 });
        //                 $('#new_preference tbody td:nth-child(3) span').each(function () {
        //                     $(this).show();
        //                 });
        //                 alert(message['http.response.success']);
        //                 hideLoading();
        //             }, error: function (data) {
        //                 alert(message['http.response.error']);
        //                 hideLoading();
        //             }
        //         });
        //     }
        // });
        $(article).on('click', '.remove', function () {
            var id = $(this).data('id');
            $(delete_prompt).modal('show');
            $(delete_input).val(id);
        });
        $(delete_offer).click(function () {
            var id = $(delete_input).val();
            $(delete_prompt).modal('hide');
            $.ajax({
                url: '/preference/delete?id=' + id,
                type: 'get',
                success: function (data) {
                    if (data) {
                        alert(message['http.response.success']);
                        preferenceTable.setAjaxParam("preferenceFlag", true);
                        preferenceTable.submitFilter();
                    }
                },
                error: function (data) {
                    alert(message[data.responseJSON.message], 3);
                }
            });
        });
        // $(article).on('click', '.copy', function () {
        //     showLoading();
        //     var id = $(this).data('id');
        //     $.ajax({
        //         url: '/offer/create/copy',
        //         type: 'GET',
        //         data: {offerId: id},
        //         success: function (data) {
        //             $first.hide();
        //             $second.show();
        //             $second.html(data);
        //             hideLoading();
        //         }
        //     });
        // });
        $("#btn_query").click(function () {
            preferenceTable.addAjaxParam('num', $('#code-query').val());
            preferenceTable.addAjaxParam('customerName', $('#name-query').val());
            preferenceTable.addAjaxParam('payment', $('#payment-query').val());
            preferenceTable.addAjaxParam('remark', $('#remark-query').val());
            preferenceTable.addAjaxParam('startTime', $("#startTime").val());
            preferenceTable.addAjaxParam('endTime',$("#endTime").val());
            preferenceTable.submitFilter();
        });
        $("#btn_reset").click(function () {
            $("#code-query").val('');
            $("#name-query").val('');
            $("#startTime").val('');
            $("#endTime").val('');
            $('#payment-query').val('');
            $('#remark-query').val('');
            $('#startTime').datepicker('option', 'maxDate', '');
            $('#endTime').datepicker('option', 'minDate', '');
            preferenceTable.resetFilter();
        });
        $("#add_offer_btn").click(function () {
            addPreference();
        });
        $("#offer-choose").click(function (e) {
            e.preventDefault();
            submitPreference();
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
    }

    function fmoney(s) {
//        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(2) + "";
        var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
        t = "";
        for (i = 0; i < l.length; i++) {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
        }
        return t.split("").reverse().join("") + "." + r;
    }

    function addPreference() {
        showLoading();
        $.ajax({
            url: "/offer/preference/detail",
            type: "get",
            success: function (data) {
                $("#choose_offer_dialog").html(data);
                $("#dialog_table").modal("show");
                hideLoading();
            }
        });
    }
    function submitPreference() {
        var checkboxs=$("#choose_offer_dialog table input:checkbox:checked");
        if(!checkboxs || checkboxs.length==0){
            alert(message["offer.create.spare.empty"]);
            return false;
        }
        var ids = [];
        checkboxs.each(function(){
            var offerPreference = {
                offer: $(this).parents("tr:first").data('id'),
                name: $(this).parents("tr:first").data('series') + '-' + $(this).parents("tr:first").data('customer')
            };
            ids.push(offerPreference)
        });
        $("#dialog_table").modal("hide");
        $.ajax({
            url: '/offer/preference/add',
            type: 'post',
            data: {preferences: JSON.stringify(ids)},
            success: function (data) {
                preferenceTable.setAjaxParam("preferenceFlag", true);
                preferenceTable.submitFilter();
                alert(message['http.response.success']);
            },
            error: function (data) {
                alert(message['http.response.error'], 3);
            }
        });
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
    #new_preference td{
        vertical-align: middle;
    }
    .btn.btn-xs.btn-default.remove:hover{
        color: red !important;
        border-color: #ff5757 !important;
    }
    .show-n {
        word-wrap:break-word !important;
        word-break:break-all !important;
    }
</style>
</#compress>