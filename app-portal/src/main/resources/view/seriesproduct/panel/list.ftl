<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div id="first">
        <div class="jarviswidget" data-widget-editbutton="false">
            <header>
                <span class="widget-icon">
                    <i class="fa fa-table"></i>
                </span>
                <span class="widget-icon" style="margin-left: 3px;" data-dic="panel.list.header"></span>
                <h2>
                    <dic dic="header.list.button.add"></dic>
                </h2>
            </header>
            <div>
                <div class="widget-body no-padding">
                    <table id="dt_basic_product" class="table table-striped table-bordered table-hover" width="100%">
                    </table>
                </div>
            </div>
            <input type="hidden" id="series" value="${(series)!}">
            <input type="hidden" id="area" value="2">

        </div>
        </div>
    <div id="second" style="display: none">123</div>
</article>


<script type="text/javascript">
    var productTable;
    $(document).ready(function(){
        $("[data-dic]").each(function(){
            $(this).html(message[$(this).data("dic")]);
        });

        var area = $("#area").val();
        var products = $("#series").val();
        var series = [];
        if (!! products) {
            series.push(products);
        }
        productTable = new Datatables();
        productTable.setAjaxParam("series",series);
        productTable.init({
            src : $("#dt_basic_product"),
            loadingMessage: message["datatable.loading"],
            scrollX: true,
            dataTable : {
                bStateSave: false,
                ajax: { url: "panel/rest/list?area=" + area },
                columns   : [
//                    DTColumnKit.order,
                    {data:"productSeries",title:message["product.panel.list.name"], width: '100px'},
                    {data:"partNo",title:message["product.panel.list.partNo"],
                        render: function (data, type, row, meta) {
                            return '<a class="operate" data-product-id="'+row.id+'">' + data + '</a>';
                        }},
                    {data:"productConfiguration",title:message["product.panel.list.configuration"], width: '100px'},
                    {data:"certification",title:message["product.panel.list.certification"], width: '100px'},
//                    {data:"state",title:message["product.panel.list.state"]},
                    {data:"featured",title:message["product.panel.list.featured"],
                        width: '100px',
                        render: function (data, type, row, meta) {
                            if (data === 0) {
                                return message['select.option.no'];
                            } else {
                                return message['select.option.yes'];
                            }
                        }},
                    {data:"color",title:message["product.panel.list.color"], width: '100px'},
                    {data:"productType",title:message["product.panel.list.type"], width: '100px'},
                    {data:"productStatus",title:message["product.panel.list.status"],
                        width: '100px',
                        render: function (data, type, row, meta) {
                            if (row.code == 1) {
                                return "<span class='text-color-green'>"+data+"</span>";
                            } else {
                                return "<span class='text-color-yellow'>"+data+"</span>";
                            }
                        }
                    }
                ],
                columnDefs: [{
                    orderable: false,
                    targets: [0,1,2,3,4,5,6,7]
                }],
                drawCallback:function(){ }
            }
        });

        $('article').on('click','.operate',function () {
            var id = $(this).data("product-id");
            var url = "/sp/tab";
            var $second = $('#second');
            var $first = $('#first');
            showLoading();
            $.ajax({
                url:url,
                type:'GET',
                data:{id:id},
                success:function (data) {
                    $first.hide();
                    $second.show();
                    $second.html(data);
                    hideLoading();
                }

            });
        });
    });
</script>

<style>
    .btn-xs{
        margin-right: 15px;
        margin-top: 5px;
    }
    .smart-form fieldset {
        padding: 20px 14px 5px;
        height: 50px;
    }
    .jarviswidget .widget-body {
        min-height: 50px;
    }
    tr a{
        text-decoration: underline;
        cursor:pointer;
    }
</style>
</#compress>