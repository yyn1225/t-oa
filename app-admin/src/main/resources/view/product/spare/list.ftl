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
                                    <dic data-dic="product.spare.list.material"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="material-query"/>
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="product.spare.list.type"></dic>:
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="type-query"/>
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
                <h2 data-dic="product.spare.list.title"></h2>
                <a class="btn btn-primary btn-xs operate" style="float: right"
                   data-spare-id="0" data-dic="header.list.button.add"></a>
                <a id="common_export" class="btn btn-primary btn-xs" style="float: right"
                data-dic="common.export"></a>
            </header>
            <div>

                <div class="jarviswidget-editbox"></div>
                <div class="widget-body no-padding">
                    <table id="dt_basic_spare" class="table table-striped table-bordered table-hover" width="100%">
                    </table>
                </div>
            </div>


        </div>
    </div>
    <div id="second" style="display: none"></div>
</article>


<script type="text/javascript">
    var spareTable;
    $(document).ready(function () {
        $("[data-dic]").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });

        spareTable = new Datatables();
        spareTable.init({
            src: $("#dt_basic_spare"),
            loadingMessage: message["datatable.loading"],
            dataTable: {
                bStateSave: false,
                ajax: {url: "spare/rest/list"},
                columns: [
                    DTColumnKit.order,
                    {data: "material", title: message["product.spare.list.material"],'orderable': false},
                    {data: "type", title: message["product.spare.list.type"],width:"150px",'orderable': false},
                    {data: "model", title: message["product.spare.list.model"],width:"120px",'orderable': false},
                    {data: "unit", title: message["product.spare.list.unit"],width:"80px",'orderable': false},
                    {data: "classify", title: message["product.spare.list.classify"],width:"120px",'orderable': false,
                    render: function (data, type, row, meta) {
                        if (data === 1) {
                            return message["product.spare.list.spares"];
                        }
                        if (data === 2) {
                            return message["product.spare.list.parts"];
                        }
                        return "";
                    }},
//                    {
//                        data: "updateTime", title: message["product.spare.list.updateTime"],
//                        render: function (data, type, row, meta) {
//                            if (data === null) {
//                                return data;
//                            } else {
//                                return (new Date(data)).Format("yyyy-MM-dd");
//                            }
//                        },'orderable': false
//                    },
//                    {
//                        data: "executeTime", title: message["product.spare.list.executeTime"],
//                        render: function (data, type, row, meta) {
//                            if (data === null) {
//                                return data;
//                            } else {
//                                return (new Date(data)).Format("yyyy-MM-dd");
//                            }
//                        },'orderable': false
//                    },
                    {
                        data: "description", title: message["product.spare.list.description"],
                        render: function (data, type, row, meta) {
                            return '<div class="td-show" '+
                            'title="'+ data +'">'+ data +'</div>';
                        },
                        width: '300px','orderable': false
                    },
                    {
                        data: "id", title: message["product.module.list.operate"],width:"60px",'orderable': false,
                        render: function (data, type, row, meta) {
                            return '<a class="btn btn-xs btn-primary operate" href="javascript:;"' +
                                    ' data-spare-id="' + data + '">'+message["body.list.edit"]+'</a>';
                        }
                    }
                ],
                drawCallback: function () {
                }
            }
        });

        $("#btn_query").click(function () {
            showLoading();
            spareTable.addAjaxParam('material', $('#material-query').val());
            spareTable.addAjaxParam('type', $('#type-query').val());
            spareTable.addAjaxParam('brand', $('#brand-query').val());
            spareTable.submitFilter();
            hideLoading();
        });
        $('article').on('click', '.operate', function () {
            var id = $(this).data("spare-id");
            var url = "/spare/manage";
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
                }

            });
        });
        
		$('#common_export').click(function () {
            var material=$('#material-query').val();
            var type= $('#type-query').val();
            var url = "/spare/rest/export";
           	window.location.href=url+"?material="+material+
           			"&type="+type;
        });
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