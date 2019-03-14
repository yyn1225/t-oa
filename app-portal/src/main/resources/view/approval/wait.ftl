<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div id="first">
        <div class="jarviswidget" data-widget-editbutton="false" style="margin: 0 0 10px">
            <header>
                <h2><dic data-dic="header.list.search"></dic></h2>
            </header>
            <div>
                <div class="widget-body no-padding"  style="min-height: 80px">
                    <form class="smart-form">
                        <fieldset style="height: 50px !important;">
                            <div class="row">
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="quotes.offer.list.customer"></dic>:
                                </label>
                                <label class="col col-2 input">
                                    <input type="text" id="customerName">
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="portal.approval.username"></dic>:
                                </label>
                                <label class="col col-2 input">
                                    <select class="select2" style="width: 100%" id="user-query">
                                        <option value=""></option>
                                        <#list (userList)! as item>
                                            <option value="${(item.id)!}">${(item.username)!}</option>
                                        </#list>
                                    </select>
                                </label>

                                <label class="col col-1 pull-right">
                                    <a class="btn btn-primary btn-sm query-btn" id="btn_query">
                                        <dic data-dic="header.list.button.search"></dic>
                                    </a>
                                </label>
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
                    <dic data-dic="portal.dashboard.examine"></dic>
                </h2>
            </header>
            <div>
                <div class="widget-body no-padding">
                    <table id="dt_basic_quotes" class="table table-striped table-bordered table-hover" width="100%">
                    </table>
                </div>
            </div>
        </div>
    </div>
</article>
<div id="second"></div>

<#--审批弹窗-->
<div class="modal fade" id="approval-prompt" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5><dic data-dic="main.title.approval"></dic></h5>
            </div>
            <div class="modal-body no-padding">
                <form class="smart-form">
                    <div id="window-html"></div>
                    <footer>
                        <button type="button" id="approval-todo" class="btn btn-primary">
                            <dic data-dic="button.confirm"></dic>
                        </button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <dic data-dic="button.cancel"></dic>
                        </button>
                    </footer>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var $first = $('#first');
    var $second = $('#second');
    var $article = $("article");
    var dt;
    $(document).ready(function () {
        pageSetUp();
        $("dic").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
        dt = new Datatables();
        dt.init({
            src: $("#dt_basic_quotes"),
            loadingMessage: message["datatable.loading"],
            dataTable: {
                bStateSave: false,
                ajax: {url: "/approval/rest/list?status=1"},
                columns: [
                    DTColumnKit.order,
                    {data: "customerName", title: message["quotes.offer.list.customer"]},
                    {
                        title: message["quotes.offer.list.quotes"],
                        width: '120px',
                        className: 'text-right',
                        render: function (data, type, row, meta) {
                            return row.rate + moneyFormat((row.totalPrice/(row.totalDiscount/100)),2);
                        }
                    },
                    {data: "totalDiscount", title: message["quotes.offer.list.discount"],className: 'text-right',
                        width: '80px',
                        render: function (data, type, row, meta) {
                            return data + "%"
                    }},
                    {data: "totalPrice",title: message["quotes.offer.list.totalPrice"],className: 'text-right', width: "120px",
                        render: function (data, type, row, meta) {
                            return row.rate + moneyFormat(data);
                        }
                    },
                    {data: "username", title:message["portal.approval.username"], width: "80px"},
                    {data: "createTime", title:message["portal.approval.createTime"],
                        width: "140px",
                        render: function (data, type, row, meta) {
                            if (data === null) {
                                return data;
                            } else {
                                return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
                            }
                        }},
                    {data: "remark", title:message["offer.create.remark"], width: "250px"},
                    {data: "id", title: message["quotes.offer.list.operate"],
                        width: "250px",
                        render: function (data, type, row, meta) {
                            return '<a class="btn btn-xs btn-primary operate" data-id="'+data+'">' +
                                    message["body.list.viewOffer"]+'</a>'+
                            '<a class="btn btn-xs btn-success agree" data-id="'+row.approvalId+'"data-offer-id="'+ data +'">' +
                            message["body.list.agree"]+'</a>'+
                            '<a class="btn btn-xs btn-danger refuse" data-id="'+row.approvalId+'"data-offer-id="'+ data +'">' +
                            message["body.list.refuse"]+'</a>';
                        }
                    },
                    {data: "approvalId", "visible": false}
                ],
                columnDefs: [{
                    orderable: false,
                    targets: [0,1,2,3,4,5,6,7,8]
                }],
                drawCallback: function () {
                }
            }
        });
        $article.on('click', '.operate', function () {
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
        $article.on('click', '.agree', function () {
            var approvalId = $(this).data('id');
            var offerId = $(this).data('offer-id');
            $.ajax({
                url: '/approval/detail?approvalId=' + approvalId + "&offerId=" +offerId + "&state=1",
                type: 'GET',
                success: function (data) {
                    $("#window-html").html(data);
                    $("#approval-prompt").modal('show');
                }
            });
        });
        $article.on('click', '.refuse', function () {
            var approvalId = $(this).data('id');
            var offerId = $(this).data('offer-id');
            $.ajax({
                url: '/approval/detail?approvalId=' + approvalId + "&offerId=" +offerId + "&state=2",
                type: 'GET',
                success: function (data) {
                    $("#window-html").html(data);
                    $("#approval-prompt").modal('show');
                }
            });
        });
        $("#approval-todo").click(function () {
            var approvalId = $("#approvalId").val();
            var state = $("#examine-state").val();
            var opinion = $("#opinion").val();
            if (state == 2) {
                if (opinion == '') {
                    alert(message["offer.approval.validate"], 3);
                    return;
                }
            }
            $("#approval-prompt").modal('hide');
            $.ajax({
                url: '/approval/rest/examine',
                type: 'post',
                data: {approvalId: approvalId, state: state, opinion: opinion},
                success: function (data) {
                    dt.resetFilter();
                }
            });
        });
        $article.on('click', '.operate', function () {
            var id = $(this).data('id');
            $.ajax({
                url: '/offer/item',
                type: 'GET',
                data: {offerId: id},
                success: function (data) {
                    $first.hide();
                    $second.show();
                    $second.html(data);
                }
            });
        });
        $("#btn_query").click(function () {
            dt.addAjaxParam('userId', $('#user-query').val());
            dt.addAjaxParam('customerName',$('#customerName').val());
            dt.submitFilter();
        });
    });

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
    .btn-xs{
        margin-right: 5px;
    }
    .smart-form fieldset {
        padding: 20px 14px 5px;
        height: 50px;
    }
</style>
</#compress>