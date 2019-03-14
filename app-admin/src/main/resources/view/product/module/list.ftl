<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div id="first">
        <div class="jarviswidget" data-widget-editbutton="false">
            <header>
                <h2 data-dic="header.list.search"></h2>
            </header>
            <div>
                <div class="widget-body no-padding">
                    <form class="smart-form">
                        <fieldset>
                            <div class="row">
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="product.module.list.scnno"></dic>
                                    :
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="scnno-query"/>
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="product.module.list.configuration"></dic>
                                    :
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="configuration-query"/>
                                </label>

                                <label class="col col-4 text-align-right">
                                    <a class="btn btn-primary query-btn" id="btn_query"
                                       data-dic="header.list.button.search"></a>
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
                <h2 data-dic="product.module.list.title"></h2>
                <a class="btn btn-primary btn-xs operate" style="float: right"
                   data-module-id="0" data-dic="header.list.button.add"></a>
                <a id="common_export" class="btn btn-primary btn-xs" style="float: right"
                   data-dic="common.export"></a>
            </header>
            <div>

                <div class="jarviswidget-editbox"></div>
                <div class="widget-body no-padding">
                    <table id="dt_basic_module" class="table table-striped table-bordered table-hover" width="100%">
                    </table>
                </div>
            </div>


        </div>
    </div>
    <div id="second" style="display: none"></div>
</article>


<script type="text/javascript">
    var moduleTable;
    $(document).ready(function () {
        $("[data-dic]").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });

        moduleTable = new Datatables();
        moduleTable.init({
            src: $("#dt_basic_module"),
            loadingMessage: message["datatable.loading"],
            dataTable: {
                bStateSave: false,
                ajax: {url: "module/rest/list"},
                columns: [
                    DTColumnKit.order,
                    {
                        data: "scnNo",
                        title: message["product.module.list.scnno"],
                        'orderable': false
                    },
                    {
                        data: "width",
                        title: message["product.box.list.volume"],
                        width: "120px",
                        'orderable': false,
                        render: function (data, type, row, meta) {
                            return row.width+"*"+row.height
                        }
                    },
                    {
                        data: "transverse",
                        title: message["product.box.list.pix"],
                        width: "130px",
                        'orderable': false,
                        render: function (data, type, row, meta) {
                            return row.transverse+"*"+row.portrait
                        }
                    },
                    {
                        data: "pitch",
                        title: message["product.module.list.pitch"],
                        width: "100px",
                        'orderable': false
                    },
                    {
                        data: "density",
                        title: message["product.module.list.density"],
                        width: "100px",
                        'orderable': false
                    },
//                    {
//                        data: "lamp",
//                        title: message["product.module.list.lamp"],
//                        width: "80px",
//                        'orderable': false
//                    },
                    {
                        data: "configuration",
                        title: message["product.module.list.configuration"],
                        width: "180px",
                        'orderable': false
                    },
                    {
                        data: "weight",
                        title: message["product.module.list.weight"],
                        width: "150px",
                        'orderable': false
                    },
                    {
                        data: "id", title: message["product.module.list.operate"], width: "60px", 'orderable': false,
                        render: function (data, type, row, meta) {
                            return '<a class="btn btn-xs btn-primary operate" href="javascript:;"' +
                                    ' data-module-id="' + data + '">' + message["body.list.edit"] + '</a>';
                        }
                    }
                ],
                drawCallback: function () {
                }
            }
        });

        $("#btn_query").click(function () {

            moduleTable.addAjaxParam('scnNo', $('#scnno-query').val());
            moduleTable.addAjaxParam('configuration', $('#configuration-query').val());
            moduleTable.submitFilter();
        });
        $('article').on('click', '.operate', function () {
            var id = $(this).data("module-id");
            var url = "/module/manage";
            var $second = $('#second');
            var $first = $('#first');
            showLoading();
            $.ajax({
                url: url,
                type: 'GET',
                data: {id: id},
                success: function (data) {
                    $first.hide();
                    $second.show();
                    $second.html(data);
                    hideLoading();
                },
                error: function (data) {
                    hideLoading();
                }
            });
        });
        $('#common_export').click(function () {
            var scnno=$('#scnno-query').val();
            var configuration= $('#configuration-query').val();
            var url = "/module/rest/export";
           	window.location.href=url+"?scnNo="+scnno+"&configuration="+configuration;
        })
    });

</script>

<style>
    .btn-xs {
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

    .padding {
        padding-top: 7px !important;
        padding-left: 0 !important;
        padding-right: 0 !important;
    }
</style>
</#compress>