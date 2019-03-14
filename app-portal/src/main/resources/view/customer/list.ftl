<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div id="first">
        <div class="jarviswidget" data-widget-editbutton="false" style="margin: 0 0 10px">
            <header>
                <h2><dic data-dic="header.list.search"></dic></h2>
            </header>
            <input type="hidden" id="time-type" value="${(type)!}">
            <div>
                <div class="widget-body no-padding">
                    <form class="smart-form">
                        <fieldset style="height: auto !important;padding: 20px 14px">
                            <div class="row">
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="org.customer.list.name"></dic>:
                                </label>
                                <label class="col col-2 input">
                                    <input type="text" id="name-query"/>
                                </label>
                                <#--<label class="col col-1 text-align-right padding">-->
                                    <#--<dic data-dic="customer.item.rating"></dic>:-->
                                <#--</label>-->
                                <#--<label class="col col-2 input">-->
                                    <#--<select class="select2" style="width: 100%" name="rating-query"-->
                                            <#--id="rating-query">-->
                                        <#--<option value=""></option>-->
                                        <#--<#list levels as item>-->
                                            <#--<option value="${(item.id)!}">${(item.name)!}</option>-->
                                        <#--</#list>-->
                                    <#--</select>-->
                                <#--</label>-->
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="org.customer.list.type"></dic>:
                                </label>
                                <label class="col col-2 input">
                                    <input type="text" id="type_query"/>
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic='org.customer.list.location'></dic>:
                                </label>
                                <label class="col col-2 input">
                                    <input type="text" id="location_query"/>
                                </label>
                                <label class="col col-1 pull-right">
                                    <a class="btn btn-primary query-btn" id="btn_query">
                                        <dic data-dic="header.list.button.search"></dic>
                                    </a>
                                </label>
                            </div>
                            <#--<div class="row" style="margin-top: 20px;">-->
                            <#--</div>-->
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <div class="jarviswidget" data-widget-colorbutton="false" data-widget-editbutton="false">
            <header>
                <span class="widget-icon"> <i class="fa fa-table"></i></span>
                <h2>
                    <dic data-dic="customer.list.title"></dic>
                </h2>
                <a class="btn btn-primary btn-xs operate" style="float: right" data-id="0">
                    <dic data-dic="customer.list.btn.add"></dic>
                </a>
            </header>
            <div>
                <div class="widget-body no-padding">

                    <table id="dt_basic_customer"
                           class="table table-striped table-bordered table-hover"
                           width="100%">
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div id="second"></div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="delete-prompt" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myModalLabel">
                        <dic data-dic="portal.window.attention"></dic>
                    </h4>
                </div>
                <div class="modal-body">
                    <dic data-dic="prompt.message.delete"></dic>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <dic data-dic="button.cancel"></dic>
                    </button>
                    <button type="button" class="btn btn-primary" id="delete-choose">
                        <dic data-dic="button.confirm"></dic>
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</article>
<script type="text/javascript">
    var $first = $('#first');
    var $second = $('#second');
    var dt;
    var $delete_prompt = $('#delete-prompt');
    var deleteId;
    $(".select2").each(function () {
        $(this).attr('data-placeholder', message["select2.placeholder.msg"]);
    });
    $(document).ready(function () {
        pageSetUp();
        var timeType = $("#time-type").val();
        $("dic").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
        dt = new Datatables();
        dt.init({
            src: $("#dt_basic_customer"),
            loadingMessage: message["datatable.loading"],
            dataTable: {
                bStateSave: false,
                ajax: {url: "/api/customer/datagrid?type=" + timeType},
                columns: [
                    DTColumnKit.order,
                    {data: "name", title: message["org.customer.list.name"], width:"",'orderable': false,
                        render: function (data, type, row, meta) {
                            return '<div class="td-show-xs"'+ 'title="'+ data +'">'+ data +'</div>';
                        }
                    },
                    {data: "type", title: message["org.customer.list.type"], width:"200px", 'orderable': false,
                        render: function (data, type, row, meta) {
                            return '<div class="td-show-s" '+
                                    'title="'+ data +'">'+ data +'</div>';
                        }
                    },
//                    {data: "ownership", title: message["org.customer.list.ownership"], width: '160px','orderable': false},
                    {data: "location", title: message["org.customer.list.location"], width:"15%", 'orderable': false},
                    {data: "phone", title: message["org.customer.list.phone"], width:"12%", 'orderable': false},
                    {data: "email", title: message["customer.item.email"], width:"20%", 'orderable': false},
                    {data: "ownership", title: message["customer.item.ownership"], width:"8%", 'orderable': false},
                    {
                        data: "id", title: message["org.customer.list.operate"],
                        width: "80px",
                        render: function (data, type, row, meta) {
                            if (row.deleteAble == 1) {
                                return '<a class="btn btn-xs btn-primary operate"  data-id="' + data
                                        + '">'+message["body.list.edit"]+'</a>&nbsp;' +
                                        '<a class="btn btn-xs btn-primary delete" data-customer-id="' + data + '">' +
                                        message["body.list.delete"]+'</a>';
                            }
                            return '<a class="btn btn-xs btn-primary operate"  data-id="' + data
                                    + '">'+message["body.list.edit"]+'</a>';
                        },'orderable': false
                    },
                    {data: "deleteAble", "visible": false, "searchable": false}
                ],

                drawCallback: function () {
                    //关系表删除
                    $('.delete').off("click").click(function (e) {
                        e.preventDefault();
                        $delete_prompt.modal("show");
                        deleteId = $(this).data("customer-id");
                    });
                }
            }
        });
        $('article').on('click', '.operate', function () {
            var id = $(this).data('id');
            $.ajax({
                url: '/customer/item',
                type: 'GET',
                data: {customerId: id},
                success: function (data) {
                    $first.hide();
                    $second.show();
                    $second.html(data);
                }
            });
        });
        $('#delete-choose').click(function () {
            $delete_prompt.modal("hide");
            $.ajax({
                url: "/api/customer/delete",
                type: 'POST',
                data: {customerId: deleteId},
                success: function (data) {
                    alert(message['http.response.success']);
                    dt.submitFilter();
                },
                error: function (data) {
                    alert(message['http.response.error'], 3);
                }
            });
        });
        $("#btn_query").click(function () {
            dt.addAjaxParam('name', $('#name-query').val());
            dt.addAjaxParam('rating', $('#rating-query').val());
            dt.addAjaxParam('type', $('#type_query').val());
            dt.addAjaxParam('location', $('#location_query').val());
            dt.submitFilter();
        });
    });
</script>
<style type="text/css">
    .smart-form fieldset + fieldset {
        border-top: none !important;
    }
    .btn-xs{
        margin-right: 5px;
        margin-top: 5px;
    }
    .smart-form fieldset {
        padding: 20px 14px 5px;
        height: 50px;
    }
    .jarviswidget .widget-body {
        min-height: 50px;
    }
</style>
</#compress>