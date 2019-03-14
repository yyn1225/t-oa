<#compress>
<div class="jarviswidget" data-widget-colorbutton="false" data-widget-editbutton="false"
     data-widget-togglebutton="false" data-widget-deletebutton="false"
     data-widget-fullscreenbutton="false" data-widget-custombutton="false">
    <header>
        <span class="widget-icon"> <i class="fa fa-table"></i></span>
        <input type="hidden" id="seriesId" value="${(seriesId?c)!}">
        <input type="hidden" id="s-type" value="0">
        <h2 data-dic="product.standard.list.title"></h2>
    </header>
    <div>
        <div class="widget-body no-padding">
            <form id="standard-edit-form">
                <table class="dt_basic_standard table table-striped table-bordered table-hover"
                       width="100%">

                </table>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="edit_count_model" tabindex="-1" role="dialog">
    <div class="modal-dialog" style="width: 900px;">
        <input type="hidden" value="0" id="spare_type">
        <input type="hidden" value="0" id="standard_id">
        <div class="modal-content" style="width: 600px">
            <div class="modal-header">
                <h4><dic data-dic="admin.spare.maintain"></dic></h4>
            </div>
            <div class="modal-body no-padding" id="edit_count_html">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.back"></dic>
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var standardTable;
    var spareTable;
    var $dialog_table = $("#dialog_table");
    //关系表table
    standardTable = new Datatables();
    var seriesId = $('#seriesId').val();
    var a = $('.nav.nav-tabs li.active a');
    var type = $(a).data("standard-id");
    standardTable.addAjaxParam('type', type);
    standardTable.addAjaxParam('series', seriesId);
    standardTable.init({
        src: $(".dt_basic_standard"),
        loadingMessage: message["datatable.loading"],
        dataTable: {
            bStateSave: false,
            paging: false,
            ajax: {url: "/api/series/count/series/list"},
            columns: [
                {data: "material", title: message["product.spare.list.material"],
                    render: function (data, type, row, meta) {
                        return '<a class="edit" data-id="'+row.sid+'">' + data + '</a>';
                    }},
                {data: "type", title: message["product.spare.list.type"]},
                {data: "brand", title: message["product.spare.list.brand"]},
                {data: "unit", title: message["product.spare.list.unit"]},
//                {
//                    data: "executeTime", title: message["product.spare.list.executeTime"],
//                    render: function (data, type, row, meta) {
//                        if (data === null) {
//                            return data;
//                        } else {
//                            return (new Date(data)).Format("yyyy-MM-dd");
//                        }
//                    }
//                },
                {
                    data: "description", title: message["product.spare.list.description"],
                    render: function (data, type, row, meta) {
                        return '<span title="' + data + '">' + data.substring(0, 9) + '...' + '</span>';
                    }
                },
                {
                    data: "num", title: message["product.spare.list.status"],
                    render: function (data, type, row, meta) {
                        if (data) {
                            return '<span class="text-color-green">' + message['product.series.maintain.yes'] + '</span>';
                        } else {
                            return '<span class="text-color-yellow">' + message['product.series.maintain.no'] + '</span>';
                        }

                    }
                }
//                {
//                    data: "id", title: message["product.standard.list.operate"],
//                    render: function (data, type, row, meta) {
//                        var removeHtml = '<a class="btn btn-xs btn-primary delete" ' +
//                                'data-formula="' + row.formula + '"' +
//                                'data-id="' + row.sid + '"' +
//                                'data-standard-id="' + data + '"' +
//                                'href="javascript:;">删除</a>';
//                        var editHtml = '<a class="btn btn-xs btn-primary edit" ' +
//                                'data-formula="' + row.formula + '"' +
//                                'data-id="' + row.sid + '"' +
//                                'data-standard-id="' + data + '">' +
//                                '数量维护</a>';
//                        return editHtml + removeHtml;
//                    }
//                }
            ],
            drawCallback: function () {
                //关系表删除
//                $('.delete').off("click").click(function (e) {
//                    e.preventDefault();
//                    var a = confirm('确认删除?');
//                    if (a) {
//                        var sid = $(this).data("id");
//                        $.ajax({
//                            url: "/api/series/count/delete",
//                            type: 'GET',
//                            data: {
//                                standardId: sid
//                            },
//                            success: function (data) {
//                                alert('删除成功');
//                                standardTable.submitFilter();
//                            }
//                        });
//                    }
//                });
                //维护数量
                $('.edit').off("click").click(function (e) {
                    e.preventDefault();
                    var sid = $(this).data("id");
                    var $model = $('#edit_count_model');
                    var $modelHtml = $('#edit_count_html');
                    $.ajax({
                        url: "/sp/count/years",
                        type: 'GET',
                        data: {standardId: sid},
                        success: function (data) {
                            $model.modal('show');
                            $modelHtml.html(data);
                        }
                    });
                });
            }
        }
    });

    //备件产品关联页面跳转
    function standardPage(a) {
        showLoading();
        var type = $(a).data("standard-id");
        var seriesId = $('#seriesId').val();
        standardTable.addAjaxParam('type', type);
        standardTable.addAjaxParam('series', seriesId);
        standardTable.submitFilter();
        hideLoading();
    }
</script>
<style>
    .btn-xs {
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
    tr a {
        text-decoration: underline;
        cursor: pointer;
    }
    .jarviswidget {
        margin: 0px !important;
    }
    .jarviswidget>div {
        border-width: 1px 1px 1px;
    }
</style>
</#compress>