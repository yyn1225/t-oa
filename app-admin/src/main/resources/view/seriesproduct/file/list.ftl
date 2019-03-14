<#compress>
<section id="widget-grid" class="">
    <div class="row">
        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <h2>
                <dic data-dic="file.list.tableName"></dic>
                <input type="hidden" value="${(fid)!}" id="fid"/>
            </h2>
        </header>
        <div>
            <div class="widget-body no-padding">

                <table id="dt_basic_file" class="table table-striped table-bordered table-hover"
                       width="100%">
                    <thead>
                    <tr>
                        <th></th>
                        <th>
                            <dic data-dic="file.list.table.name"></dic>
                        </th>
                        <th>
                            <dic data-dic="file.list.table.extend"></dic>
                        </th>
                        <th>
                            <dic data-dic="file.list.table.size"></dic>
                        </th>
                        <th>
                            <dic data-dic="product.panel.list.series"></dic>
                        </th>
                        <th>
                            <dic data-dic="product.spare.list.operate"></dic>
                        </th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
        </article>
    </div>
</section>

<style type="text/css">
    .dd3-content {
        cursor: pointer;
    }

    .dd3-content-active {
        color: #2ea8e5;
        background: #fff;
    }
</style>
<script type="text/javascript">

    $('table').on('click', '.view', function () {
        var id = $(this).data('id');
        window.open('/file/view?id=' + id);
    });
    $(document).ready(function () {
        $("dic").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });

        var dt= new Datatables();
        dt.setAjaxParam("seriesId", $('#fid').val());
        dt.init({
            src: $('#dt_basic_file'),
            loadingMessage: message["datatable.loading"],
            dataTable: {
                bStateSave: false,
                ajax: {url: "/api/series/datagrid"},
                columns: [
                    DTColumnKit.order,
                    {data: "name"},
                    {data: "extend"},
                    {
                        data: "size",
                        render: function (fileSize, display, row) {
                            if (fileSize < 1024) {
                                return fileSize + 'B';
                            } else if (fileSize < (1024 * 1024)) {
                                var temp = fileSize / 1024;
                                temp = temp.toFixed(2);
                                return temp + 'KB';
                            } else if (fileSize < (1024 * 1024 * 1024)) {
                                var temp = fileSize / (1024 * 1024);
                                temp = temp.toFixed(2);
                                return temp + 'MB';
                            } else {
                                var temp = fileSize / (1024 * 1024 * 1024);
                                temp = temp.toFixed(2);
                                return temp + 'GB';
                            }
                        }
                    },
                    {data: "seriesName"},
                    {
                        data: "id",
                        render: function (data, display, row) {
                            var viewHtml = '';
                            var fileType = row.extend;
                            if (fileType == 'png'
                                    || fileType == 'jpg'
                                    || fileType == 'jpeg'
                                    || fileType == 'gif'
                                    || fileType == 'doc'
                                    || fileType == 'docx'
                                    || fileType == 'xls'
                                    || fileType == 'xlsx'
                                    || fileType == 'ppt'
                                    || fileType == 'pptx'
                                    || fileType == 'pdf'
                            ) {
                                viewHtml = '&nbsp;<a class="btn btn-xs btn-primary view" ' +
                                        ' href="javascript:;"' +
                                        ' data-id="' + data + '">'+message["select.review"]+'</a>';
                            }
                            return viewHtml;
                        }
                    }],
                drawCallback: function () {
                }
            }
        });

    });
</script>
</#compress>