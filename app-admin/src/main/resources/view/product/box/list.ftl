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
                        <fieldset>
                            <div class="row">
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="product.box.list.scnNo.search"></dic>
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="scnNo-query"/>
                                </label>

                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="product.box.list.modualNo"></dic>
                                    :
                                </label>
                                <label class="col col-3 input">
                                    <select class="select2" id="modualNo-query">
                                        <option value=""></option>
                                        <#list (modules)! as item>
                                            <option value="${(item.id?c)!}">${(item.scnNo)!}</option>
                                        </#list>
                                    </select>
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
                <h2 data-dic="product.box.list.title"></h2>
                <a class="btn btn-primary btn-xs operate" style="float: right" data-box-id="0">
                    <dic data-dic="header.list.button.add"></dic>
                </a>
                <a id="common_export" class="btn btn-primary btn-xs" style="float: right"
                   data-dic="common.export"></a>
            </header>
            <div>

                <div class="jarviswidget-editbox"></div>
                <div class="widget-body no-padding">
                    <table id="dt_basic_box" class="table table-striped table-bordered table-hover" width="100%">
                    </table>
                </div>
            </div>


        </div>
    </div>
    <div id="second" style="display: none"></div>
</article>


<script type="text/javascript">
    var boxTable;
    $(".select2").each(function () {
        $(this).attr('data-placeholder',message["select2.placeholder.msg"]);
    });
    pageSetUp();
    $(document).ready(function () {
        $("[data-dic]").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });

        boxTable = new Datatables();
        boxTable.init({
            src: $("#dt_basic_box"),
            loadingMessage: message["datatable.loading"],
            dataTable: {
                bStateSave: false,
                ajax: {url: "box/rest/list"},
                columns: [
                    DTColumnKit.order,
                    {
                        data: "scnNo",
                        title: message["product.box.list.scnNo"],
                        orderable: false
                    },
                    {
                        data: "modualNo",
                        title: message["product.box.list.modualNo"],
                        width: '180px', orderable: false,
                        render:function(data,type,row,mate){
                            if(!!row.modualNo2){
                                return data+","+row.modualNo2;
                            }
                            return data;
                        }
                    },
                    {
                        data: "transverseCount",
                        title: message["product.box.list.count"],
                        width: '100px',
                        orderable: false,
                        render: function (data, type, row, meta) {
                            return row.transverseCount + "*" + row.portraitCount;
                        }
                    },
                    {
                        data: "weight",
                        title: message["product.box.list.weight"],
                        width: "100px",
                        orderable: false,
                        className: "text-right"
                    },
                    {
                        data: "transversePix",
                        title: message["product.box.list.pix"],
                        width: "100px",
                        orderable: false,
                        render: function (data, type, row, meta) {
                            return row.transversePix + "*" + row.portraitPix;
                        }
                    },
                    {
                        data: "height",
                        title: message["product.box.list.volume"],
                        width: "100px",
                        orderable: false,
                        render: function (data, type, row, meta) {
                            return row.height + "*" + row.width + "*" + row.thickness;
                        }
                    },
                    {
                        data: "boxType",
                        title: message["product.box.list.boxType"],
                        width: "150px",
                        orderable: false
                    },
                    {
                        data: "id",
                        title: message["product.module.list.operate"],
                        width: "60px",
                        orderable: false,
                        render: function (data, type, row, meta) {
                            return '<a class="btn btn-xs btn-primary operate" href="javascript:;" data-box-id="' +
                                    data + '">' + message["body.list.edit"] + '</a>';
                        }
                    }
                ],
                drawCallback: function () {
                }
            }
        });

        $("#btn_query").click(function () {
            showLoading();
            boxTable.addAjaxParam('scnNo', $('#scnNo-query').val());
            boxTable.addAjaxParam('modual', $('#modualNo-query').val());
            boxTable.submitFilter();
            hideLoading();
        });
        $('article').on('click', '.operate', function () {
            var id = $(this).data("box-id");
            var url = "/box/manage";
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
            var scnNo=$('#scnNo-query').val();
            var modual= $('#modualNo-query').val();
            var url = "/box/rest/export";
           	window.location.href=url+"?scnNo="+scnNo+"&modual="+modual;
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

    .padding {
        padding-top: 7px !important;
        padding-left: 0 !important;
        padding-right: 0 !important;
    }
</style>
</#compress>