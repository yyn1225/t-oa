<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <h2><dic data-dic="header.list.search"></dic></h2>
        </header>
        <div>
            <div class="widget-body no-padding">
                <form class="smart-form">
                    <fieldset>
                        <div class="row">
                            <label class="col col-1 text-align-right" style="padding-top: 7px; ">
                                <dic data-dic="header.list.input.name"></dic></label>
                            <label class="col col-3 input">
                                <input type="text" id="name-query"/>
                            </label>

                            <label class="col col-1 text-align-right" style="padding-top: 7px; ">
                                <dic data-dic="organization.item.title.code"></dic>:</label>
                            <label class="col col-3 input">
                                <input type="text" id="code-query"/>
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
            <h2 data-dic="sales.level.list.title"></h2>
            <a class="btn btn-primary btn-xs operate" style="float: right" data-level-id="0">
                <dic data-dic="header.list.button.add"></dic>
            </a>
        </header>
        <div>

            <div class="jarviswidget-editbox"></div>
            <div class="widget-body no-padding">
                <table id="dt_basic_user" class="table table-striped table-bordered table-hover" width="100%">
                </table>
            </div>
        </div>


    </div>

    <!-- 模态框（Modal） -->
    <div class="modal fade" id="del_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" style="width: 350px">
            <div class="modal-content">
                <div class="modal-header">
                    <input type="hidden" id="levelId">
                    <h4 class="modal-title" id="myModalLabel">
                        <dic data-dic="admin.window.attention"></dic>
                    </h4>
                </div>
                <div class="modal-body">
                    <dic data-dic="prompt.message.delete"></dic>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <dic data-dic="button.cancel"></dic>
                    </button>
                    <button type="button" class="btn btn-primary" id="ok_del">
                        <dic data-dic="button.confirm"></dic>
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

    <div class="modal fade" id="dialog_table" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <input type="hidden" value="0" id="spare-type">
            <div class="modal-content">
                <div class="modal-header">
                    <h4><dic data-dic="sales.level.list.title"></dic></h4>
                </div>
                <div class="modal-body no-padding" id="level-content">
                </div>
            </div>
        </div>
    </div>
</article>


<script type="text/javascript">
    var dt;
    pageSetUp();

    //    $("#dialog_table").modal({
    //        backdrop: 'static',
    //        keyboard: false,
    //        show: false
    //    });
        $("#del_modal").modal({
            backdrop: 'static',
            keyboard: false,
            show: false
        });
    //    $("#myModal").modal({
    //        backdrop: 'static',
    //        keyboard: false,
    //        show: false
    //    });
    $(document).ready(function () {
        $("[data-dic]").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });

        dt = new Datatables();
        dt.init({
            src: $("#dt_basic_user"),
            loadingMessage: message["datatable.loading"],
            dataTable: {
                bStateSave: false,
                ajax: {url: "/customer/level/rest/list"},
                columns: [
                    DTColumnKit.order,
                    {data: "name", title: message["sales.level.list.name"]},
                    {data: "code", title: message["sales.level.list.code"], width: "300px"},
                    {
                        data: "discount", title: message["sales.level.list.discount"],
                        width: "120px",
                        render: function (data, type, row, meta) {
                            return (data * 100).toFixed(0) + '%';
                        }
                    },
                    {
                        data: "profit", title: message["sales.level.list.profit"],
                        width: "120px",
                        render: function (data, type, row, meta) {
                            return (data * 100).toFixed(0) + '%';
                        }
                    },
                    {
                        data: "id", title: message["org.role.list.operate"],
                        width: "120px",
                        render: function (data) {
                            return '<a class="btn btn-xs btn-primary operate" href="javascript:;" data-level-id="' + data + '">' +
                                    message["body.list.edit"]+'</a>' +
                                    '&nbsp;<a class="btn btn-xs btn-primary remove" href="javascript:;" data-level-id="' + data + '">' +
                                    message["body.list.delete"]+'</a>';
                        }
                    }
                ],
                columnDefs: [{
                    orderable: false,
                    targets: [0,1,2,3,4,5]
                }],
                drawCallback: function () {

                }
            }
        });
        $article = $('article');
        $article.on('click', '.operate', function () {
            var id = $(this).data('level-id');
            $.ajax({
                url: '/customer/level/addOrEdit',
                type: 'get',
                data: {id: id},
                success: function (data) {
                    $("#level-content").html(data);
                    $("#dialog_table").modal("show");
                }
            });
        });

        $("#btn_query").click(function () {
            dt.addAjaxParam('name', $('#name-query').val());
            dt.addAjaxParam('code', $("#code-query").val());
            dt.submitFilter();
        });

        $article.on('click', '.remove', function () {
            var id = $(this).data('level-id');
            $("#levelId").val(id);
            $("#del_modal").modal("show");
        });
        $("#ok_del").click(function () {
            var id = $("#levelId").val();
            $.ajax({
                url: '/customer/level/rest/delete',
                type: 'post',
                data: {id: id},
                success: function (data) {
                    dt.resetFilter();
                    $("#del_modal").modal("hide");
                },
                error: function (data) {
                    alert(data.responseJSON.message);
                }
            });
        });
    });

    $article.on('click','#submit_price',function () {
        //获取表单对象
        var bootstrapValidator = $("#price-form").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if (bootstrapValidator.isValid()) {
            var langs = [];
            $.each(lang, function(index,item){
                langs.push({
                    lang: item,
                    nameLang: $("#levelName").data(item)
                });
            });
            $.ajax({
                url: '/customer/level/rest/save',
                type: 'post',
                data: $("#price-form").serialize() + "&lang=" + JSON.stringify(langs),
                success: function (data) {
                    dt.resetFilter();
                    $("#dialog_table").modal("hide");
                }, error: function (data) {
                    console.log(data);
                    if (data.responseJSON.message === "20001") {
                        alert(message['alert.message.codeExist'], 3);
                    }
                    else if (data.responseJSON.message === "20003") {
                        alert(message['alert.message.noPermission'], 3);
                    }
                    else {
                        alert(message['alert.message.systemError'], 3);
                    }
                }
            });
        }
    });
</script>

<style>
     .modal-header {
         padding: 9px;
     }
</style>
</#compress>