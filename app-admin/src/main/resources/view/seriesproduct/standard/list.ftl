<#compress>
<div class="jarviswidget" data-widget-colorbutton="false" data-widget-editbutton="false"
     data-widget-togglebutton="false" data-widget-deletebutton="false"
     data-widget-fullscreenbutton="false" data-widget-custombutton="false">
    <header>
        <span class="widget-icon"> <i class="fa fa-table"></i></span>
        <input type="hidden" id="productId" value="${(productId?c)!}">
        <input type="hidden" id="s-type" value="0">
        <h2 data-dic="product.standard.list.title"></h2>
    </header>
    <div>
        <div class="widget-body no-padding">
            <form id="standard-edit-form">
            <table  class="dt_basic_standard table table-striped table-bordered table-hover" width="100%">

            </table>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    var standardTable;
    $(document).ready(function () {

        $("dic").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });

        //关系表table
        standardTable = new Datatables();
        standardTable.init({
            src : $(".dt_basic_standard"),
            loadingMessage: message["datatable.loading"],
            dataTable : {
                bStateSave: false,
                paging: false,
                ajax: { url: "standard/rest/list" },
                columns   : [
                    DTColumnKit.order,
                    {data: "material", title: message["product.spare.list.material"]},
                    {data: "type", title: message["product.spare.list.type"]},
                    {data: "brand", title: message["product.spare.list.brand"]},
                    {data: "unit", title: message["product.spare.list.unit"]},
                    {data: "model", title: message["product.spare.list.model"]},
                    {
                        data: "executeTime", title: message["product.spare.list.executeTime"],
                        render: function (data, type, row, meta) {
                            if (data === null) {
                                return data;
                            } else {
                                return (new Date(data)).Format("yyyy-MM-dd");
                            }
                        }
                    },
                    {data: "description", title: message["product.spare.list.description"]}

                ],
                drawCallback:function(){

                }
            }
        });


    });


    //备件产品关联页面跳转
    function standardPage(a) {
        showLoading();
        var type = $(a).data("standard-id");
        $('#s-type').val(type);
        var product = $('#productId').val();
        standardTable.addAjaxParam('type', type);
        standardTable.addAjaxParam('product', product);
        standardTable.submitFilter();
        hideLoading();
    }
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
</style>
</#compress>