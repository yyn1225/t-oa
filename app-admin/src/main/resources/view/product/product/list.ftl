<#--<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12 list">-->
    <#--<div class="jarviswidget" data-widget-editbutton="false">-->
        <#--<header>-->
            <#--<h2>-->
                <#--<dic data-dic="product.panel.list.series"></dic>-->
            <#--</h2>-->
        <#--</header>-->
        <#--<div>-->
            <#--<div class="widget-body no-padding">-->
                <#--<div class="widget-body-toolbar">-->
                    <#--<div class="row">-->
                        <#--<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">-->
                            <#--<div class="input-group">-->
                                <#--<input class="form-control" id="search_series_input" placeholder="" type="text">-->
                                <#--<span class="input-group-addon" id="search_series_btn">-->
                                    <#--<i class="fa fa-search"></i>-->
                                <#--</span>-->
                            <#--</div>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->


            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
<#--</article>-->
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12 list">
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <h2>
                <dic data-dic="product.panel.list.name"></dic>
            </h2>
            <a class="btn btn-primary btn-xs operate" style="float: right" data-id="0">
                <dic data-dic="header.list.button.add"></dic>
            </a>
            <a id="common_export" class="btn btn-primary btn-xs" style="float: right"
                   data-dic="common.export"></a>
        </header>
        <div>
            <div class="widget-body no-padding">
                <div class="widget-body-toolbar">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                            <div class="input-group">
                                <input class="form-control" id="search_product_input"
                                       placeholder=""
                                       type="text">
                                <span class="input-group-addon" id="btn_query">
                                    <i class="fa fa-search"></i></span>
                            </div>
                        </div>
                    </div>
                </div>
                <table id="dt_basic_org" class="table table-striped table-bordered table-hover"
                       width="100%">
                    <thead>
                    <tr>
                        <th></th>
                        <th>
                            <dic data-dic="panel.list.series"></dic>
                        </th>
                        <th>
                            <dic data-dic="product.panel.list.name"></dic>
                        </th>
                        <th>
                            <dic data-dic="organization.list.table.status"></dic>
                        </th>
                        <th>
                            <dic data-dic="org.role.list.operate"></dic>
                        </th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</article>
<script type="text/javascript">
    var el = {
        search_series_input: "#search_series_input",
        search_series_btn: "#search_series_btn",
        search_product_input: "#search_product_input",
        btn_query: "#btn_query",
        dt_table: "#dt_basic_org"
    };
    var url = {
        dt: "/series/rest/list",
        edit: "/series/rest/edit"
    };
    var dt;
    var product = {
        _searchSeries: function () {
        },
        _searchProduct: function () {
            dt.setAjaxParam("flag", true);
            dt.setAjaxParam("allName", $(el.search_product_input).val());
            dt.submitFilter();
        },
        _initProductTable: function () {
            dt = new Datatables();
            dt.setAjaxParam("flag", true);
            dt.init({
                src: $(el.dt_table),
                loadingMessage: message["datatable.loading"],
                dataTable: {
                    bStateSave: false,
                    ajax: {url: url.dt},
                    columns: [
                        DTColumnKit.order,
                        {data: "pName",width: "35%"},
                        {data: "text", width: "35%"},
                        {
                            data: "status",width: "10%", render: function (data) {
                                if (data === 0) {
                                    return "<span class='center-block padding-5 label label-danger'>"+message["product.panel.price.AlreadyShelf"]+"</span>";
                                } else {
                                    return "<span class='center-block padding-5 label label-success'>"+message["product.panel.price.AlreadyShelves"]+"</span>";
                                }
                            }
                        },
                        {
                            data: "id",
                            render: function (data, type, row, meta) {
                                var upperHtml = '<a class="btn btn-xs btn-primary edit" href="javascript:;" ' +
                                        'data-id="' + data + '" data-status="1">'+message["product.panel.price.shelves"]+'</a>';
                                var lowerHtml = '<a class="btn btn-xs btn-primary edit" href="javascript:;" ' +
                                        'data-id="' + data + '" data-status="0">'+message["product.panel.price.shelf"]+'</a>';
                                if (row.status == 1) {
                                    return lowerHtml;
                                }else {
                                    return upperHtml;
                                }
                            }
                        }],
                    columnDefs: [{
                        orderable: false,
                        targets: [0,1,2,3,4]
                    }],
                    drawCallback: function () {
                    }

                }
            });
        },
        _editProduct: function (self) {
            var id = self.data("id");
            var status = self.data("status");
            showLoading();
            $.ajax({
                url: url.edit,
                type: 'post',
                data: {id: id, status: status},
                success: function (data) {
                    if (data) {
                        product._searchProduct();
                    }
                    hideLoading();
                },error: function (data) {
                    hideLoading();
                }
            });
        },
        _addProduct: function () {
            
        }
    };

    $(document).ready(function () {
        $("[data-dic]").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
        product._initProductTable();

        $(el.dt_table).on("click", ".edit", function () {
            product._editProduct($(this));
        });
        $(el.btn_query).click(function () {
            product._searchProduct();
        });
        
		$('#common_export').click(function () {
            var allName= $(el.search_product_input).val();
            var flag= true;
            var url = "/series/rest/export";
           	window.location.href=url+"?allName="+allName+
           			"&flag="+flag;
        });
    });
</script>