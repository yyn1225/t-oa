<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div class="jarviswidget" data-widget-editbutton="false" style="margin: 0 0  10px">
        <header>
            <h2>
                <dic data-dic="header.list.search"></dic>
            </h2>
        </header>
        <div>
            <div class="widget-body no-padding">
                <form class="smart-form">
                    <fieldset>
                        <div class="row">
                            <label class="col col-1 text-align-right padding">
                                <span style="color: red">*</span>
                                <dic data-dic="quotes.offer.list.priceArea"></dic>:
                            </label>
                            <label class="col col-2 input">
                                <select class="select2" style="width: 100%" id="user-area">
                                    <option value=""></option>
                                     <#if department??>
                                         <#list department as item>
                                            <option value="${(item.id)!}">${(parent.name)!}-${(item.name)!}</option>
                                         </#list>
                                     </#if>
                                </select>
                            </label>
                            <label class="col col-1 text-align-right padding">
                                <dic data-dic="product.panel.list.name"></dic>:
                            </label>
                            <label class="col col-2 input">
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
                            <label class="col col-1 text-align-right padding">
                                <dic data-dic="panel.list.search.scn"></dic>:
                            </label>
                            <label class="col col-2 input">
                                <input type="text" id="partNo-query"/>
                            </label>
                            <label class="col col-3 text-align-right">
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
            <span class="widget-icon"><i class="fa fa-table"></i></span>
            <h2><dic data-dic="product.panel.list.price"></dic></h2>
        </header>
        <div>
            <div class="widget-body no-padding">
                <table id="dt_price" class="table table-striped table-bordered table-hover" width="100%"></table>
            </div>
        </div>
    </div>
</article>


<script type="text/javascript">
    var priceTable;
    $(document).ready(function () {
        $(".select2").each(function () {
            $(this).attr('data-placeholder', message["select2.placeholder.msg"]);
        });
        $("[data-dic]").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
        pageSetUp();
        priceTable = new Datatables();
        priceTable.init({
            src: $("#dt_price"),
            loadingMessage: message["datatable.loading"],
            scrollX: true,
            dataTable: {
                bStateSave: false,
                ajax: {url: "/price/rest/datagrid"},
                columns: [
                    DTColumnKit.order,
                    {
                        data: "seriesName",
                        title: message["product.panel.list.name"],
                        orderable: false,
                        width: "150px"
                    },
                    {
                        data: "partNo",
                        title: message["panel.list.search.scn"],
                        orderable: false,
                        width: "250px"
                    },
                    {
                        data: "price",
                        title: message["product.panel.list.unitPrice"],
                        orderable: false,
                        width: "200px",
                        render: function (data, type, row, meta) {
                            return row.unit + data.toFixed(2);
                        }
                    },
                    {
                        data: "remark",
                        title: message["product.panel.list.state"],
                        orderable: false
                    }
                ],
                drawCallback: function () {
                }
            }
        });

        $("#btn_query").click(function () {
            showLoading();
            priceTable.addAjaxParam('area', $('#user-area').val());
            priceTable.addAjaxParam('partNo', $('#partNo-query').val());
            priceTable.addAjaxParam('seriesList', $('#series-query').val());
            priceTable.submitFilter();
            hideLoading();
        });
    });
</script>

<style>
    .smart-form fieldset {
        padding: 20px 14px 5px;
        height: 50px !important;
    }
    .jarviswidget .widget-body {
        min-height: 50px;
    }
</style>
</#compress>