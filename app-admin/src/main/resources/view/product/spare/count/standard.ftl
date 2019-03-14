<#compress>
<div class="jarviswidget" data-widget-colorbutton="false" data-widget-editbutton="false"
     data-widget-togglebutton="false" data-widget-deletebutton="false"
     data-widget-fullscreenbutton="false" data-widget-custombutton="false">
    <header>
        <span class="widget-icon"> <i class="fa fa-table"></i></span>
        <input type="hidden" id="productId" value="${(productId?c)!}">
        <input type="hidden" id="s-type" value="0">
        <h2 data-dic="product.standard.list.title"></h2>
        <a class="btn btn-primary btn-xs s-add" style="float: right" data-standard-id="0"><dic data-dic="header.list.button.add"></dic></a>
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
    <div class="modal-dialog">
        <input type="hidden" value="0" id="spare_type">
        <input type="hidden" value="0" id="standard_id">
        <div class="modal-content">
            <div class="modal-header">
                <h4><dic data-dic="admin.spare.maintain"></dic></h4>
            </div>
            <div class="modal-body no-padding" id="edit_count_html">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="header.list.button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="file_edit_ok_sumbit">
                    <dic data-dic="button.confirm"></dic>
                </button>
            </div>
        </div>
    </div>
</div>
<#--删除提示弹窗-->
<div class="modal fade" id="delete-prompt" tabindex="-1" role="dialog">
    <div class="modal-dialog" style="width: 350px;">
        <input type="hidden" value="0" id="formula-type">
        <div class="modal-content">
            <div class="modal-header">
                <h5><dic data-dic="admin.window.attention"></dic></h5>
            </div>
            <div class="modal-body no-padding">
                <form class="smart-form">
                    <fieldset class="textPop">
                        <dic data-dic="prompt.message.delete"></dic>
                    </fieldset>
                    <footer>
                        <button type="button" id="delete-choose" class="btn btn-primary">
                            <dic data-dic="button.confirm"></dic>
                        </button>
                        <button type="button" class="btn btn-default" id="delete-close"
                                data-dismiss="modal"><dic data-dic="button.cancel"></dic>
                        </button>
                    </footer>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var standardTable;
    var spareTable;
    var deleteId;
    var $dialog_table = $("#dialog_table");
    var $delete_prompt = $("#delete-prompt");
    //关系表table
    standardTable = new Datatables();
    var productId = $('#productId').val();
    var a = $('.nav.nav-tabs li.active a');
    var type = $(a).data("standard-id");
    standardTable.addAjaxParam('type', type);
    standardTable.addAjaxParam('product', productId);
    standardTable.init({
        src: $(".dt_basic_standard"),
        loadingMessage: message["datatable.loading"],
        dataTable: {
            bStateSave: false,
            paging:false,
            ajax: {url: "standard/rest/list"},
            columns: [
                {data: "material", title: message["product.spare.list.material"], width: "180px"},
                {data: "type", title: message["product.spare.list.type"], width: "120px"},
                {data: "unit", title: message["product.spare.list.unit"], width: "60px"},
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
                    width: "250px",
                    render: function (data, type, row, meta) {
                        return '<div class="td-show-s" title="' + data + '">' + data + '</div>';
                    }
                },
                {
                    data: "num", title: message["product.spare.list.status"],
                    render: function (data, type, row, meta) {
                        if (!!data && data > 0) {
                            return '<span class="text-color-green">' +message['product.series.maintain.yes']+  '</span>';
                        } else {
                            return '<span class="text-color-yellow">' +message['product.series.maintain.no']+ '</span>';
                        }

                    }
                },
                {
                    data: "id", title: message["product.standard.list.operate"],
                    render: function (data, type, row, meta) {
                        var removeHtml = '<a class="btn btn-xs btn-primary delete" ' +
                                'data-formula="' + row.formula + '"' +
                                'data-id="' + row.sid + '"' +
                                'data-standard-id="' + data + '"' +
                                'href="javascript:;">'+ message["body.list.delete"] +'</a>';
                        var editHtml = '<a class="btn btn-xs btn-primary edit" ' +
                                'data-formula="' + row.formula + '"' +
                                'data-id="' + row.sid + '"' +
                                'data-standard-id="' + data + '">' +
                                message["product.panel.price.maintain"] +'</a>';
                        return editHtml + removeHtml;
                    }
                }
            ],
            columnDefs: [{
                orderable: false,
                targets: [0,1,2,3,4,5]
            }],
            drawCallback: function () {
                //关系表删除
//                $('.delete').off("click").click(function (e) {
//                    e.preventDefault();
//                    var a = confirm('确认删除?');
//                    if (a) {
//                        var sid = $(this).data("id");
//                        $.ajax({
//                            url: "/standard/rest/delete",
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
                //关系表删除
                $('.edit').off("click").click(function (e) {
                    e.preventDefault();
                    var sid = $(this).data("id");
                    var $model = $('#edit_count_model');
                    var $modelHtml = $('#edit_count_html');
                    $.ajax({
                        url: "/spare/count/years",
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
        newtr = [];
        var type = $(a).data("standard-id");
        var product = $('#productId').val();
        standardTable.addAjaxParam('type', type);
        standardTable.addAjaxParam('product', product);
        standardTable.submitFilter();
        hideLoading();
    }

    //关系表新增
    $('.s-add').click(function () {
        $dialog_table.modal("show");
    });
    //备件弹窗table
    spareTable = new Datatables();
    spareTable.init({
        src: $("#dt_basic_spare"),
        loadingMessage: message["datatable.loading"],
        dataTable: {
            bStateSave: false,
            paging:false,
            ajax: {url: "spare/rest/list"},
            "columnDefs": [
                {"bVisible": false, "targets": 5},
                {orderable: false, targets: [0,1,2,3,4,5]}
            ],
            columns: [
                {data: "material", title: message["product.spare.list.material"]},
                {data: "type", title: message["product.spare.list.type"]},
                {data: "brand", title: message["product.spare.list.brand"]},
                {data: "model", title: message["product.spare.list.model"], width: "250px",
                    render: function (data, type, row, meta) {
                        return '<div class="td-show-s" title="' + data + '">' + data + '</div>';
                    }},
                {data: "unit", title: message["product.spare.list.unit"],
                    render: function (data, type, row, meta) {
                        return '<input type="hidden" class="input-hide-tr" data-id="'+row.id+'">'+data;
                    }},
//                {
//                    data: "executeTime", title: message["product.spare.list.executeTime"],
//                    render: function (data, type, row, meta) {
//                        if (data === null) {
//                            return data;
//                        } else {
//                            return (new Date(data)).format("yyyy-MM-dd");
//                        }
//                    }
//                },
                {data: "description", title: message["product.spare.list.description"]}
            ],

            drawCallback: function () {
            }
        }
    });

    $('#dt_basic_spare tbody').on('click','tr',function() {
        var self = $(this);
        if(self.hasClass('on')){
            self.removeClass('on');
        }else{
            self.addClass('on');
        }
    });

    var newtr = [];
    $('#spare-choose').click(function () {
        debugger;
        var pid = $('#productId').val();
        var spareRows = spareTable.getCheckRowsData();
        if (spareRows.length === 0) {
            alert(message['product.series.select'] ,2);
            return;
        }

        $dialog_table.modal("hide");

        for (var i = 0; i < spareRows.length; i++) {
            if ($(".delete[data-standard-id=" + spareRows[i].id + "]").length > 0) {
                continue;
            }
            newtr.push(spareRows[i].id);
            //清楚 暂无数据
            $('.dataTables_empty').hide();
        }
        if (newtr.length == 0) {
            return false;
        }
        var a = $('.nav.nav-tabs li.active a');
        var type = $(a).data("standard-id");
        $.ajax({
            url: '/api/spare/count/save',
            type: 'POST',
            data: {productId: pid, standardIds: newtr.join(','), type: type},
            success: function (data) {
                alert(message['alert.message.success']);
                standardTable.submitFilter();
            },
            error: function () {
                alert(message['alert.message.systemError'], 3);
            }
        });

        newtr=[];
        spareTable.resetFilter();
    });


    $("#btn_query_spare").click(function () {

        spareTable.addAjaxParam('material', $('#material-query').val());
        spareTable.addAjaxParam('type', $('#type-query').val());
        spareTable.submitFilter();
    });

    $('#file_edit_ok_sumbit').on('click', function () {
        year.submit();
    });


    //删除提示弹窗
    $delete_prompt.modal({
        backdrop: 'static',
        keyboard: false,
        show: false
    });

    $('#delete-choose').click(function () {
        $delete_prompt.modal("hide");
        $.ajax({
            url: "/standard/rest/delete",
            type: 'GET',
            data: {standardId: deleteId},
            success: function (data) {
                alert(message['alert.message.success']);
                standardTable.submitFilter();
            }
        });

    });
    $('#delete-close').click(function () {
        $delete_prompt.modal("hide");
    });
    $('article').on('click', '.delete', function () {
        $delete_prompt.modal("show");
        deleteId = $(this).data("id");
    });
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

    .jarviswidget {
        margin: 0px !important;
    }
    .jarviswidget>div {
        border-width: 1px 1px 1px;
    }
</style>
</#compress>