<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div id="first">
        <div class="jarviswidget" data-widget-editbutton="false">
            <header>
                <h2><dic data-dic="header.list.search"></dic></h2>
            </header>
            <div>
                <div class="widget-body no-padding">
                    <form class="smart-form">
                        <fieldset>
                            <div class="row">
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="org.customer.list.name"></dic>:</label>
                                <label class="col col-3 input">
                                    <input type="text" id="name-query"/>
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="org.customer.list.levelName"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <select class="select2" style="width: 100%" name="rating-query"
                                            id="rating-query">
                                        <option value=""></option>
                                        <#list levels as item>
                                            <option value="${(item.id)!}">${(item.name)!}</option>
                                        </#list>
                                    </select>
                                </label>
                                <label class="col col-4 text-align-right">
                                    <a class="btn btn-primary query-btn" id="btn_query">
                                        <dic data-dic="header.list.button.search"></dic>
                                    </a>
                                </label>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>

        </div>
        <div class="jarviswidget" data-widget-editbutton="false">
            <header>
                <span class="widget-icon"> <i class="fa fa-table"></i></span>
                <h2 data-dic="org.customer.list.title"></h2>
                <a class="btn btn-primary btn-xs operate" style="float: right" data-customer-id="0">
                    <dic data-dic="header.list.button.add"></dic>
                </a>
            </header>
            <div>

                <div class="jarviswidget-editbox"></div>
                <div class="widget-body no-padding">
                    <table id="dt_basic_customer"
                           class="table table-striped table-bordered table-hover" width="100%">
                    </table>
                </div>
            </div>


        </div>
    <#--删除提示弹窗-->
        <div class="modal fade" id="delete-prompt" tabindex="-1" role="dialog">
            <div class="modal-dialog" style="width: 350px;">
                <input type="hidden" value="0" id="formula-type">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">
                            <dic data-dic="admin.window.attention"></dic>
                        </h4>
                    </div>
                    <div class="modal-body no-padding">
                        <form class="smart-form">
                            <fieldset class="textPop">
                                <dic data-dic="prompt.message.delete"></dic>
                            </fieldset>
                            <footer>
                                <button type="button" id="delete-choose" class="btn btn-primary">
                                    <dic data-dic="button.confirm"></dic>
                                </button>
                                <button type="button" class="btn btn-default" id="delete-close"
                                        data-dismiss="modal">
                                    <dic data-dic="button.cancel"></dic>
                                </button>
                            </footer>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    <#--分配提示弹窗-->
        <div class="modal fade" id="assort-prompt" tabindex="-1" role="dialog">
            <div class="modal-dialog" style="width: 550px;">
                <input type="hidden" value="0" id="customerId">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4><dic data-dic="org.customer.list.follow"></dic></h4>
                    </div>
                    <div class="modal-body no-padding">
                        <form class="smart-form" id="customerUser-form">
                            <fieldset class="textPop">
                                <label class="col col-3 text-align-right padding">
                                    <dic data-dic="customer.item.name"></dic>:
                                </label>
                                <label class="col col-9 input">
                                    <input type="text" id="assortName" disabled/>
                                </label>
                            </fieldset>
                            <fieldset class="textPop">
                                <label class="col col-3 text-align-right padding">
                                    <dic data-dic="org.customer.list.followPerson"></dic>:
                                </label>
                                <label class="col col-9 input">
                                    <select class="select2" style="width: 100%" name="customerUser"
                                            id="customerUser">
                                        <#list users as item>
                                            <option value="${(item.id)!}">${(item.username)!}</option>
                                        </#list>
                                    </select>
                                </label>
                            </fieldset>
                            <footer>
                                <button type="button" id="assort-choose" class="btn btn-primary">
                                    <dic data-dic="button.confirm"></dic>
                                </button>
                                <button type="button" class="btn btn-default" id="assort-close"
                                        data-dismiss="modal">
                                    <dic data-dic="button.cancel"></dic>
                                </button>
                            </footer>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="second" style="display: none"></div>
</article>

<script type="text/javascript">
    var deleteId;
    var customerTable;
    var $first = $('#first');
    var $second = $('#second');
    var $delete_prompt = $('#delete-prompt');
    var $assort_prompt = $('#assort-prompt');
    $(".select2").each(function () {
        $(this).attr('data-placeholder', message["select2.placeholder.msg"]);
    });
    $(document).ready(function () {
        $("[data-dic]").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });

        customerTable = new Datatables();
        customerTable.init({
            src: $("#dt_basic_customer"),
            loadingMessage: message["datatable.loading"],
            dataTable: {
                bStateSave: false,
                ajax: {url: "customer/rest/list"},
                columns: [
                    DTColumnKit.order,
                    {data: "name", title: message["org.customer.list.name"],'orderable': false},
                    {data: "type", title: message["org.customer.list.type"], width:"200px", 'orderable': false,
                        render: function (data, type, row, meta) {
                            return '<div class="td-show-xs" '+
                                    'title="'+ data +'">'+ data +'</div>';
                        }
                    },
                    {data: "location", title: message["org.customer.list.location"], width:"200px", 'orderable': false},
                    {data: "levelName", title: message["org.customer.list.levelName"], width:"160px", 'orderable': false},
                    {
                        data: "id", title: message["org.customer.list.operate"],
                        width: "180px",
                        render: function (data, type, row, meta) {
                            if (row.deleteAble == 1) {
                                return '<a class="btn btn-xs btn-primary operate"  data-customer-id="' + data + '">'+message["body.list.edit"]+'</a>' +
                                        '<a class="btn btn-xs btn-primary assort" data-customer-id="' + data + '" ' +
                                        'data-name="' + row.name + '">'+message["body.list.portion"]+'</a>' +
                                        '<a class="btn btn-xs btn-primary delete" data-customer-id="' + data + '">'+message["body.list.delete"]+'</a>';
                            }
                            return '<a class="btn btn-xs btn-primary operate"  data-customer-id="' + data + '">'+message["body.list.edit"]+'</a>' +
                                    '<a class="btn btn-xs btn-primary assort" data-customer-id="' + data + '" ' +
                                    'data-name="' + row.name + '">'+message["body.list.portion"]+'</a>';
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
                    //分配
                    $('.assort').off("click").click(function (e) {
                        e.preventDefault();
                        var customerId = $(this).data("customer-id");
                        var name = $(this).data("name");
                        $('#customerId').val(customerId);
                        $('#assortName').val(name);
                        $.ajax({
                            url: "/customerUser/rest/user",
                            type: 'POST',
                            data: {customerId: customerId},
                            success: function (data) {
                                $('#customerUser').select2("val", data);
                                $assort_prompt.modal("show");
                            }
                        });

                    });
                }
            }
        });

    });

    pageSetUp();
    $('article').on('click', '.operate', function () {
        var id = $(this).data("customer-id");
        var url = "/customer/item";
        var $second = $('#second');
        var $first = $('#first');
        showLoading();
        $.ajax({
            url: url,
            type: 'GET',
            data: {customerId: id},
            success: function (data) {
                $first.hide();
                $second.show();
                $second.html(data);
                hideLoading();
            }
        });
    });
    $("#btn_query").click(function () {
        customerTable.addAjaxParam('name', $('#name-query').val());
        customerTable.addAjaxParam('rating', $('#rating-query').val());
        customerTable.submitFilter();
    });
    //删除提示弹窗
    $delete_prompt.modal({
        backdrop: 'static',
        keyboard: false,
        show: false
    });
    //分配提示弹窗
    $assort_prompt.modal({
        backdrop: 'static',
        keyboard: false,
        show: false
    });
    //删除提示弹窗
    //    $address_prompt.modal({
    //        backdrop: 'static',
    //        keyboard: false,
    //        show: false
    //    });

    $('#delete-choose').click(function () {
        $delete_prompt.modal("hide");
        showLoading();
        $.ajax({
            url: "/customer/rest/delete",
            type: 'POST',
            data: {customer: deleteId},
            success: function (data) {
                if (data) {
                    alert('删除成功');
                    customerTable.submitFilter();
                } else {
                    alert('菜单资源被占用,无法删除');
                    return;
                }
                hideLoading();
            }
        });
    });

    $('#assort-choose').click(function () {
        var customerId = $('#customerId').val();
        var user = $('#customerUser').val();
//        if(user==null){
//            alert('');
//        }
        //获取表单对象
        var bootstrapValidator = $('#customerUser-form').data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if (bootstrapValidator.isValid()) {
            showLoading();
            $.ajax({
                url: "/customerUser/rest/assort",
                type: 'POST',
                data: {customerId: customerId, user: user},
                success: function (data) {
                    if (data) {
                        alert(message["distribution.success"]);
                        $assort_prompt.modal("hide");
                    } else {
                        alert(message["distribution.failed"]);
                    }
                    hideLoading();
                }
            });
        }
    });

    $('#delete-close').click(function () {
        $delete_prompt.modal("hide");
    });
    $('#assort-close').click(function () {
        $assort_prompt.modal("hide");
    });
    //    $('#address-choose').click(function () {
    //        $address_prompt.modal("hide");
    //    });
    //表单校验
    $('#customerUser-form').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            customerUser: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: '跟进人员不能为空'
                    }
                }
            }

        }
    });
</script>

<style>
    tr a {
        margin-right: 5px;
    }
</style>
</#compress>