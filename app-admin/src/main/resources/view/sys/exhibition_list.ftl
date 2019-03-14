<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div id="first">
        <div class="jarviswidget" data-widget-editbutton="false">
            <header>
                <span class="widget-icon"> <i class="fa fa-table"></i></span>
                <h2 data-dic="exhibition.title.list"></h2>
                <a class="btn btn-primary btn-xs" id="exhibition-add" style="float: right" data-dic="header.list.button.add"></a>
            </header>
            <div>

                <div class="jarviswidget-editbox"></div>
                <div class="widget-body no-padding">
                    <table id="dt_basic_module" class="table table-striped table-bordered table-hover" width="100%">
                    </table>
                    <input type="hidden" id="exhibition-total">
                </div>
            </div>


        </div>
    </div>
    <div id="second" style="display: none"></div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="exhibition-modal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        <dic data-dic="window.exhibition.title"></dic>
                    </h4>
                </div>
                <div class="modal-body" id="exhibition-content">

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <dic data-dic="button.cancel"></dic>
                    </button>
                    <button type="button" class="btn btn-primary" id="save_submit">
                        <dic data-dic="button.confirm"></dic>
                    </button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->

<#--删除提示弹窗-->
    <div class="modal fade" id="delete-exhibition" tabindex="-1" role="dialog">
        <div class="modal-dialog" style="width: 350px;">
            <input type="hidden" value="0" id="exhibition-id">
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
</article>


<script type="text/javascript">
    var moduleTable;
    $(document).ready(function(){
        $("[data-dic]").each(function(){
            $(this).html(message[$(this).data("dic")]);
        });

        moduleTable = new Datatables();
        moduleTable.init({
            src : $("#dt_basic_module"),
            loadingMessage: message["datatable.loading"],
            dataTable : {
                bStateSave: false,
                paging: false,
                ajax: { url: "exhibition/rest/list" },
                columns   : [
                    DTColumnKit.order,
                    {data:"img",title:message["exhibition.list.img"]},
                    {data:"imageUrl",title:message["exhibition.list.attachment"], width:"180px",
                        render: function (data, type, row, meta) {
                             return '<div title="'+ data +'">' + data + '</div>';
                        }
                    },
                    {data:"url",title:message["exhibition.list.url"], width: "200px"},
                    {data:"type",title:'应用于',width: "80px",
                        render: function (data, type, row, meta) {
                            if (data == 2) {
                                return 'WEB端';
                            } else {
                                return '移动端';
                            }
                        }
                    },
                    {data:"orders",title:message["exhibition.list.orders"],width: "60px"},
                    {data:"id",title:message["body.list.operate"], width: "60px",
                        render: function (data, type, row, meta) {
                            return '<a class="btn btn-xs btn-primary operate" href="javascript:;"' +
                                    ' data-id="'+data+'" data-dic="body.list.delete"></a>';
                        }
                    }
                ],
                columnDefs: [{
                    orderable: false,
                    targets: [0,1,2,3,4,5,6]
                }],
                drawCallback:function(){
                    $("[data-dic]").each(function(){
                        $(this).html(message[$(this).data("dic")]);
                    });
                    $("#exhibition-total").val($('tbody > tr').size());
                }
            }
        });
        $("#exhibition-add").click(function () {
            var size = $("#exhibition-total").val();
            // if (size > 4) {
            //     alert(message["the.number.of.sowing.maps.is.up.to.5"], 3);
            //     return;
            // }
            $.ajax({
                url: '/config/banner/item',
                type: 'get',
                success: function (data) {
                    $("#exhibition-content").html(data);
                    $("#exhibition-modal").modal('show');
                }
            });
        });

        $("#save_submit").click(function () {
            var attachmentId = $(".attachment-id").val();
            var url = $("#img-url").val();
            var orders = $("#orders").val();
            var type = $('#type').select2('val');
            if (attachmentId == 0) {
                alert(message['sys.exhibition.todo'], 2);
                return;
            }
            //获取表单对象
            var bootstrapValidator = $("#exhibition-form").data('bootstrapValidator');
            //手动触发验证
            bootstrapValidator.validate();
            if (bootstrapValidator.isValid()) {
                $.ajax({
                    url: 'exhibition/rest/save',
                    type: 'post',
                    data: {attachmentId: attachmentId, url: url, orders: orders, type: type},
                    success: function (data) {
                        $("#exhibition-modal").modal('hide');
                        moduleTable.resetFilter();
                    }
                });
            }
        });

        $('article').on('click', '.operate', function () {
            $("#delete-exhibition").modal('show');
            $("#exhibition-id").val($(this).data('id'));
        });

        $("#delete-choose").click(function () {
            var id = $("#exhibition-id").val();
            $.ajax({
                url: 'exhibition/rest/delete',
                data: {id: id},
                type: 'get',
                success: function (data) {
                    $("#delete-exhibition").modal('hide');
                    moduleTable.resetFilter();
                }
            });
        });
    });

</script>

<style>
    .btn-xs{
        margin-right: 5px;
        margin-top: 5px;
    }
    .jarviswidget .widget-body {
        min-height: 50px;
    }
    .modal-header{
        padding: 9px;
    }
</style>
</#compress>