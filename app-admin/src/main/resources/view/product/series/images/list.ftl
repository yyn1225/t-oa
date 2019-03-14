<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div id="first">
        <div class="jarviswidget" data-widget-editbutton="false">
            <header>
                <h2>
                    <dic data-dic="header.list.search"></dic>
                </h2>
            </header>
            <div>
                <div class="widget-body no-padding">
                    <form class="smart-form">
                        <fieldset style="height: 50px !important;">
                            <div class="row">
                                <label class="col col-1 text-align-right" style="padding-top: 7px; ">
                                    <dic data-dic="series.list.search.name"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="name-query"/>
                                </label>

                                <label class="col col-1 pull-right">
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
                <span class="widget-icon">
                    <i class="fa fa-table"></i>
                </span>
                <span class="widget-icon" style="margin-left: 3px;"
                      data-dic="panel.list.header"></span>
                <h2>
                    <dic dic="header.list.button.add"></dic>
                </h2>
            </header>
            <div>
                <div class="widget-body no-padding">
                    <table id="dt_basic_product"
                           class="table table-striped table-bordered table-hover" width="100%">
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="edit_image_model" tabindex="-1" role="dialog">
        <div class="modal-dialog" style="width: 900px;">
            <input type="hidden" value="0" id="spare_type">
            <input type="hidden" value="0" id="standard_id">
            <div class="modal-content" style="width: 600px">
                <div class="modal-header">
                    <h4><dic data-dic="series.images.window.title"></dic></h4>
                </div>
                <div class="modal-body no-padding" id="edit_image_html">
                    <div id="uploader" class="wu-example">
                        <div class="queueList">
                            <div id="dndArea" class="placeholder">
                                <div id="filePicker"></div>
                                <p><dic data-dic="series.images.window.picker"></dic></p>
                            </div>
                        </div>
                        <div class="statusBar" style="display:none;">
                            <div class="progress">
                                <span class="text">0%</span>
                                <span class="percentage"></span>
                            </div>
                            <div class="info"></div>
                            <div class="btns">
                                <div id="filePicker2"></div>
                                <div class="uploadBtn"><dic data-dic="series.images.window.start"></dic></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <dic data-dic="button.cancel"></dic>
                    </button>
                    <button type="button" class="btn btn-primary" id="image_ok_sumbit">
                        <dic data-dic="button.confirm"></dic>
                    </button>
                </div>
            </div>
        </div>
    </div>
</article>
<!--引入CSS-->
<link rel="stylesheet" type="text/css" href="../../../../static/js/plugin/webuploader/webuploader.css">
<link rel="stylesheet" type="text/css" href="../../../../static/js/plugin/webuploader/image.css">
<!--引入JS-->
<script type="text/javascript" src="../../../../static/js/plugin/webuploader/webuploader.html5only.js"></script>
<script type="text/javascript">
    var productTable;
    var uploader;
    $(document).ready(function () {
        $("[data-dic]").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
        var $model = $('#edit_image_model');
        var area = $("#area").val();
        productTable = new Datatables();
        productTable.init({
            src: $("#dt_basic_product"),
            loadingMessage: message["datatable.loading"],
            scrollX: true,
            dataTable: {
                bStateSave: false,
                ajax: {url: "/series/rest/img/list"},
                columns: [
                    DTColumnKit.order,
                    {data: "text", title: message["series.list.name"]},
                    {
                        data: "remark",
                        title: message["series.list.remark"],
                        width: "450px"
                    },
                    {
                        data: "id", title: message["product.module.list.operate"],
                        width: "80px",
                        render: function (data, type, row, meta) {
                            return '<a style="cursor: pointer" class="btn btn-xs btn-primary operate" data-parent="' +
                                    row.parent + '" data-id="' + data + '">'+ message["series.images.window.maintain"] + '</a>';
                        }
                    }
                ],
                columnDefs: [{
                    orderable: false,
                    targets: [0,1,2,3]
                }],
                drawCallback: function () {
                }
            }
        });

        $("#btn_query").click(function () {

            productTable.addAjaxParam('name', $('#name-query').val());
            productTable.submitFilter();
        });
        $('article').on('click', '.operate', function () {
            var id = $(this).data("id");
            var parent = $(this).data("parent");
            var url = "/series/images/image";
            var $modelHtml = $('#edit_image_html');
            $.ajax({
                url: url,
                type: 'GET',
                data: {seriesId: id},
                success: function (data) {
                    $model.modal("show");
                    $modelHtml.html(data);
                }
            });
        });
        $model.on('shown.bs.modal', function () {
            // 执行一些动作...
            // 添加“添加文件”的按钮，
            uploader.addButton({
                id: '#filePicker',
                label: message["series.images.window.btn"]
            });
        });
        $("#image_ok_sumbit").on('click', function () {
            _sumbit($model);
        })
    });

</script>

<style>
    .col.col-2 {
        padding-left: 0 !important;
    }

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
</style>
</#compress>