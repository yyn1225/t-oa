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
                                    <dic data-dic="product.panel.list.series"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <select class="select2" style="width: 100%" id="series_query">
                                        <option value="" data-dic="select2.default.all"></option>
                                        <#list (seriesList)! as item>
                                            <option value="${(item.id)!}">${(item.text)!}</option>
                                        </#list>
                                    </select>
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="product.panel.list.name"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <select class="select2" style="width: 100%" id="series_item_query">
                                        <option value="" data-dic="select2.default.all"></option>
                                    </select>
                                    <div id="series_item_html" style="display: none">
                                        <#list (seriesList)! as item>
                                            <div class="parent_${(item.id)!}">
                                                <#list item.children as child>
                                                    <option value="${(child.id)!}">${(child.text)!}</option>
                                                </#list>
                                            </div>
                                        </#list>
                                    </div>
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="product.panel.list.certification"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <select class="select2" style="width: 100%" id="certification_query">
                                        <option value="" data-dic="select2.default.all"></option>
                                        <#list (certification)! as item>
                                            <option value="${(item.code)!}">${(item.name)!}</option>
                                        </#list>
                                    </select>
                                </label>
                            </div>
                            <div class="row" style="margin-top: 10px">
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="product.panel.list.featured"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <select class="select2" style="width: 100%" id="featured_query">
                                        <option value="" data-dic="select2.default.all"></option>
                                        <option value="1" data-dic="select.option.yes"></option>
                                        <option value="0" data-dic="select.option.no"></option>
                                    </select>
                                </label>

                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="product.panel.list.productType"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <select class="select2" style="width: 100%" id="type_query">
                                        <option value="" data-dic="select2.default.all"></option>
                                        <#list (productType)! as item>
                                            <option value="${(item.code)!}">${(item.name)!}</option>
                                        </#list>
                                    </select>
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="product.panel.list.status"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <select class="select2" style="width: 100%" id="status_query">
                                        <option value="" data-dic="select2.default.all"></option>
                                        <#list (productStatus)! as item>
                                            <option value="${(item.code)!}">${(item.name)!}</option>
                                        </#list>
                                    </select>
                                </label>
                            </div>
                            <div class="row" style="margin-top: 10px;">

                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="panel.list.search.scn"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="partNo-query"/>
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="product.panel.list.color"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="color_query"/>
                                </label>
                                <a class="btn btn-default btn-sm query-btn pull-right mr15" id="btn_reset_query">
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
        <div class="jarviswidget" data-widget-editbutton="false">
            <header>
                <span class="widget-icon">
                    <i class="fa fa-table"></i>
                </span>
                <span class="widget-icon" style="margin-left: 3px;" data-dic="panel.list.header"></span>
                <h2>
                    <dic dic="header.list.button.add"></dic>
                </h2>
                <#--<a class="btn btn-primary btn-xs operate" style="float: right" data-product-id="0">-->
                    <#--新增-->
                    <#--<dic data-dic="header.list.button.add"></dic>-->
                <#--</a>-->
            </header>
            <div>
                <div class="widget-body no-padding">
                    <table id="dt_basic_product" class="table table-center table-striped table-bordered table-hover" width="100%">
                    </table>
                </div>
            </div>
            <#--<input type="hidden" id="area" value="${(area)!}">-->
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
        initQuery();
        var area = $("#area").val();
        productTable = new Datatables();
        productTable.init({
            src : $("#dt_basic_product"),
            loadingMessage: message["datatable.loading"],
            scrollX: true,
            dataTable : {
                bStateSave: false,
                ajax: { url: "panel/rest/list?area=" + area },
                columns   : [
                    // DTColumnKit.order,
                    {data:"parentSeriesName",title:message["product.panel.list.series"],width: "7%"},
                    {data:"productSeries",title:message["product.panel.list.name"],width: "8%"},
                    {data:"partNo",title:message["product.panel.list.partNo"]},
                    {data:"productConfiguration",title:message["product.panel.list.configuration"],width: "8%"},
                    {data:"certification",title:message["product.panel.list.certification"],width: "10%"},
                    {data:"state",title:message["product.panel.list.state"],width: "25%"},
                    {data:"featured",title:message["product.panel.list.featured"],
                        width: "5%",
                        render: function (data, type, row, meta) {
                            if (data === 1) {
                                return message["select.option.yes"];
                            } else {
                                return message["select.option.no"];
                            }
                        }},
                    {data:"productType",title:message["product.panel.list.productType"], width: "5%"},
                    {data:"color",title:message["product.panel.list.color"], width: "10%"},
                    {data:"productStatus",title:message["product.panel.list.status"],
                        width: "5%",
                        render: function (data, type, row, meta) {
                            if (row.code == 1) {
                                return "<span class='text-color-green'>"+data+"</span>";
                            } else {
                                return "<span class='text-color-yellow'>"+data+"</span>";
                            }
                        }},
                    {data:"id",title:message["product.module.list.operate"],
                        width: "60px",
                        render: function (data, type, row, meta) {
                            return '<a class="btn btn-xs btn-primary operate" href="javascript:;" data-product-id="'+data+'">' +
                                    message["product.panel.list.view"]+'</a>';
                        }
                    }
                ],
                columnDefs: [{
                    orderable: false,
                    targets: [0,1,2,3,4,5,6,7,8,9,10]
                }],
                drawCallback:function(){ }
            }
        });

        $("#btn_query").click(function(){
            search(productTable);

        });
        $('#btn_reset_query').click(function () {
            clearQuery(productTable);
        });
        $('article').on('click','.operate',function () {
            var id = $(this).data("product-id");
            var url = "/product/addUI";
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

    /**
     * 搜索
     */
    function search(productTable) {
        productTable.addAjaxParam('partNo', $('#partNo-query').val()); //物料号
        productTable.addAjaxParam('parentSeries', $('#series_query').select2('val')); //系列号(上级)
        productTable.addAjaxParam('certificationStr', $('#certification_query').select2('val')); //认证
        productTable.addAjaxParam('featured', $('#featured_query').select2('val')); //主推
        productTable.addAjaxParam('productType', $('#type_query').select2('val')); //产品类型
        productTable.addAjaxParam('status',  $('#status_query').select2('val')); //状态
        productTable.addAjaxParam('productId', $('#series_item_query').select2('val')); //产品
        productTable.addAjaxParam('color',  $('#color_query').val()); //标准颜色
        productTable.submitFilter();
    }

    /**
     * 清除查询条件
     */
    function clearQuery(productTable) {
        $('#series_query').select2('val','');
        $('#certification_query').select2('val','');
        $('#featured_query').select2('val','');
        $('#type_query').select2('val','');
        $('#status_query').select2('val','');
        $('#series_item_query').select2('val','');
        $('#partNo-query').val('');
        $('#color_query').val('');
        $('#series_item_query ').html('<option value="" data-dic="select2.default.all">' + message["select2.default.all"]+ '</option>');
        productTable.clearAjaxParams();
        productTable.resetFilter();
    }

    /**
     * 初始化界面搜索条件
     */
    function initQuery() {
        $('#series_query').select2();
        $('#certification_query').select2();
        $('#featured_query').select2();
        $('#type_query').select2();
        $('#status_query').select2();
        $('#series_query').change(function () {
            var defaultAll = '<option value="" data-dic="select2.default.all">' + message["select2.default.all"]+ '</option>';
            $('#series_item_query').select2('val','');
            $('#series_item_query ').hide();
            $('#series_item_query ').html(defaultAll + $('#series_item_html .parent_'+$(this).val()).html());
        });
        $('#series_item_query').select2();

    }
</script>

<style>
    .btn-xs{
        margin-right: 15px;
        margin-top: 5px;
    }
    .smart-form fieldset {
        padding: 20px 14px;
        height: auto;
    }
    .jarviswidget .widget-body {
        min-height: 50px;
    }
    .jarviswidget{
        margin: 0 0 10px;
    }
</style>
</#compress>