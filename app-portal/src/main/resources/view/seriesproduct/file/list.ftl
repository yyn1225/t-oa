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
                <div class="widget-body-toolbar">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                            <div class="input-group">
                                <input class="form-control" id="search_file_input" placeholder="" type="text">
                                <span class="input-group-addon" id="search_file_btn">
                                    <i class="fa fa-search"></i></span>
                            </div>
                        </div>
                    </div>
                </div>
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
        var type = $(this).data('type');
        var url = $(this).data('url');
        if (type == 999) {
            if (url.indexOf("http://")==0 || url.indexOf("https://")==0) {
                window.open(url);
            }else {
                window.open('http://'+ url);
            }
        }else {
            window.open('/file/view?id=' + id);
        }
    });
    $('table').on('click', '.download', function () {
        var url = $(this).data('url');
        window.open(url);
    });
    $(document).ready(function () {
        $('[data-dic]').each(function () {
            $(this).html(message[$(this).data("dic")]);
        });

        var dt= new Datatables();
        var series = $('#fid').val();
        var seriesId = [];
        seriesId.push(series);
        dt.setAjaxParam("seriesId", seriesId);
        dt.init({
            src: $('#dt_basic_file'),
            loadingMessage: message["datatable.loading"],
            dataTable: {
                bStateSave: false,
                ajax: {url: "/sp/rest/file"},
                columns: [
                    DTColumnKit.order,
                    {data: "name"},
                    {data: "extend", width: "120px"},
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
                    {data: "seriesName", width: "120px"},
                    {
                        data: "id",
                        width: "140px",
                        render: function (data, display, row) {
                            var download = '&nbsp;<a class="btn btn-xs btn-primary download" ' +
                                    ' href="javascript:;"' +
                                    ' data-url="' + row.url + '"' +
                                    ' data-id="' + data + '">'+message["body.list.downland"]+'</a>';
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
                                    || fileType == "video"
                                    || fileType == "link"
                            ) {
                                viewHtml = '&nbsp;<a class="btn btn-xs btn-primary view" ' + ' href="javascript:;"' +
                                        ' data-id="' + data + '"' +'data-type="'+ row.type +'"' + 'data-url="'+ row.url +'"' + '>'
                                        + message["body.list.view"]+'</a>';
                            }
                            if(row.type == 888 || row.type == 999) {
                                return viewHtml;
                            }
                            if (row.securitys==2) {
                                return viewHtml;
                            }
                            if (row.securitys == 1 || row.securitys==3) {
                                return viewHtml + download;
                            }
                            else {
                                return "";
                            }
                        }
                    }],
                columnDefs: [{
                    orderable: false,
                    targets: [0,1,2,3,4,5]
                }],
                drawCallback: function () {
                }
            }
        });
         $("#search_file_btn").click(function () {
             dt.setAjaxParam("name", $("#search_file_input").val());
             dt.submitFilter();
         });
    });

</script>
</#compress>