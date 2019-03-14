<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <span class="widget-icon"> <i class="fa fa-table"></i></span>
            <h2 data-dic="offer.list.transfer.add"></h2>
        </header>
        <div>
            <div class="jarviswidget-editbox"></div>
            <div class="widget-body no-padding">
                <table id="dt_basic_resource" class="table table-striped table-bordered table-hover" width="100%">
                </table>
            </div>
        </div>
    </div>
<#--详情弹窗-->
    <div class="modal fade" id="approval-prompt" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5>
                        <dic data-dic="offer.item.transfer.supplement"></dic>
                    </h5>
                </div>
                <div class="modal-body no-padding">
                    <form class="smart-form">
                        <div id="window-html"></div>
                        <footer>
                            <button type="button" class="btn btn-primary" id="button-ok">
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
</article>


<script type="text/javascript">
    var $article = $('article');
    var resourceTable;
    $(document).ready(function () {
        $("[data-dic]").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });

        resourceTable = new Datatables();
        resourceTable.init({
            src: $("#dt_basic_resource"),
            loadingMessage: message["datatable.loading"],
            dataTable: {
                bStateSave: false,
                ajax: {url: "transfer/rest/list"},
                columns: [
                    DTColumnKit.order,
                    {data: "seriesName", title: message["product.panel.list.name"]},
                    {data: "bidder", title: message["offer.list.transfer.bidder"], width: "120px"},
                    {data: "toAddress", title: message["offer.list.transfer.toAddress"], width: "300px"},
                    {data: "transport", title: message["offer.list.transfer.transport"], width: "150px"},
                    {data: "size", title: message["offer.list.transfer.transport"], width: "120px"},
                    {data: "offerTime", title: message["offer.list.transfer.offerTime"], width: "120px"},
                    {
                        data: "id", title: message["org.resource.list.operate"],
                        width: '60px',
                        render: function (data) {
                            return '<a class="btn btn-xs btn-primary supplement" data-id="' + data + '">' + message["offer.list.transfer.supplement"] + '</a>';
                        }
                    }
                ],
                columnDefs: [{
                    orderable: false,
                    targets: [0,1,2,3,4,5,6,7]
                }],
                drawCallback: function () {
                }
            }
        });

        $article.on('click', '.supplement', function () {
            var id = $(this).data("id");
            var url = "/transfer/item";
            $.ajax({
                url: url,
                type: 'GET',
                data: {id: id},
                success: function (data) {
                    $("#window-html").html(data);
                    $("#approval-prompt").modal('show');
                }
            });
        });

        $article.on('click', '#button-ok', function () {
            $("#approval-prompt").modal('hide');
            $.ajax({
                url: '/transfer/rest/save',
                type: 'post',
                data: {transferId: $("#transferId").val(), cost: $("#cost").val()},
                success: function (data) {
                    alert(message["alert.message.success"]);
                },
                error: function (data) {
                    alert(message["alert.message.systemError"], 3);
                }
            })
        });
    });
</script>

<style>
    .modal-header {
        padding: 9px;
    }
</style>
</#compress>