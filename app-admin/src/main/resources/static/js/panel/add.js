var standardTable;
var spareTable;
var $dialog_table = $("#dialog_table");
var $delete_prompt = $("#delete-prompt");
// var deleteId;
// var $formula_table = $("#formula_table");
// var datalist = [{id: 1, text: "每米"}, {id: 2, text: "每平米"}, {id: 3, text: "每个箱体"}, {
//     id: 4,
//     text: "每个模组"
// }, {id: 5, text: "自定义"}];
$(document).ready(function () {

    $("dic").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });

    //关系表table
    standardTable = new Datatables();
    standardTable.init({
        src: $(".dt_basic_standard"),
        loadingMessage: message["datatable.loading"],
        dataTable: {
            bStateSave: false,
            paging: false,
            ajax: {url: "standard/rest/list"},
            columns: [
                {data: "material", title: message["product.spare.list.material"]},
                {data: "type", title: message["product.spare.list.type"]},
                {data: "brand", title: message["product.spare.list.brand"]},
                {data: "unit", title: message["product.spare.list.unit"]},
                {
                    data: "description", title: message["product.spare.list.description"],
                    render: function (data, type, row, meta) {
                        return '<span title="' + data + '">' + data.substring(0, 9) + '...' + '</span>';
                    }
                },
                {
                    data: "id", title: message["product.standard.list.operate"],
                    render: function (data, type, row, meta) {
                        return '<a class="delete" ' +
                            'data-sid="' + row.sid + '"' +
                            'data-standard-id="' + data + '"><i class="fa fa-trash-o"></i></a>' +
                            '<input type="hidden" name="id" value="' + row.sid + '">';
                    }
                }
            ],
            columnDefs: [{
                orderable: false,
                targets: [0,1,2,3,4,5]
            }],
            drawCallback: function () {
                // $(".st-sp.st-select").select2({data: datalist, minimumResultsForSearch: -1});

                //关系表删除
                $('.delete').off("click").click(function (e) {
                    e.preventDefault();
                    // $delete_prompt.modal("show");
                    var deleteId = $(this).data("sid");
                    $.ajax({
                        url: "/standard/rest/delete",
                        type: 'GET',
                        data: {standardId: deleteId},
                        success: function (data) {
                            // alert(message.deleteover);
                            standardTable.submitFilter();
                        }
                    });
                });
            }
        }
    });

    //删除提示弹窗
    // $delete_prompt.modal({
    //     backdrop: 'static',
    //     keyboard: false,
    //     show: false
    // });
    //
    // $('#delete-choose').click(function () {
    //     $delete_prompt.modal("hide");
    //     $.ajax({
    //         url: "/standard/rest/delete",
    //         type: 'GET',
    //         data: {standardId: deleteId},
    //         success: function (data) {
    //             // alert(message.deleteover);
    //             standardTable.submitFilter();
    //         }
    //     });
    //
    // });
    // $('#delete-close').click(function () {
    //     $delete_prompt.modal("hide");
    // });

    //备件弹窗table
    spareTable = new Datatables();
    spareTable.init({
        src: $("#dt_basic_spare"),
        loadingMessage: message["datatable.loading"],
        dataTable: {
            bStateSave: false,
            paging: false,
            ajax: {url: "spare/rest/list"},
            "columnDefs": [
                {"bVisible": false, "targets": 5},
                {orderable: false, targets: [0,1,2,3,4,5]}
            ],
            columns: [
                {data: "material", title: message["product.spare.list.material"]},
                {data: "type", title: message["product.spare.list.type"]},
                {data: "brand", title: message["product.spare.list.brand"]},
                {data: "model", title: message["product.spare.list.model"]},
                {data: "unit", title: message["product.spare.list.unit"],
                    render: function (data, type, row, meta) {
                        return '<input type="hidden" class="input-hide-tr" data-id="'+row.id+'">'+data;
                    }},
                // {
                //     data: "executeTime", title: message["product.spare.list.executeTime"],
                //     render: function (data, type, row, meta) {
                //         if (data === null) {
                //             return data;
                //         } else {
                //             return (new Date(data)).Format("yyyy-MM-dd");
                //         }
                //     }
                // },
                {data: "description", title: message["product.spare.list.description"]}
            ],
            drawCallback: function () {
            }
        }
    });
    $("#btn_query_spare").click(function () {

        spareTable.addAjaxParam('material', $('#material-query').val());
        spareTable.addAjaxParam('type', $('#type-query').val());
        spareTable.submitFilter();
    });

    //备件信息弹窗
    $dialog_table.modal({
        backdrop: 'static',
        keyboard: false,
        show: false
    });

    // //自定义公式弹窗
    // $formula_table.modal({
    //     backdrop: 'static',
    //     keyboard: false,
    //     show: false
    // });
    //自定义公式弹窗
    // $("article").on("change", ".st-sp.st-select", function () {
    //     var select = $(this).select2("val");
    //     var spId = $(this).parents('tr').find('a[class="delete"]').data('standard-id');
    //     if (select == '5') {
    //         $formula_table.modal("show");
    //         $('#formula-type').val(spId);
    //     }
    // });

    //动态 删除页面tr
    // $("article").on("click", ".delete", function () {
    //     $(this).parents('tr').remove();
    //     if ($('.in tr .delete').length == 0) {
    //         $('.dataTables_empty').show();
    //     }
    // });

});
//备件产品关联页面跳转
function standardPage(a) {
    showLoading();
    $('.tab-pane .dataTables_info').hide();
    $('.tab-pane .jarviswidget-ctrls').hide();
    $('.tab-pane .widget-toolbar').hide();

    var type = $(a).data("standard-id");
    $('#s-type').val(type);
    var product = $('#productId').val();
    standardTable.addAjaxParam('type', type);
    standardTable.addAjaxParam('product', product);
    standardTable.submitFilter();
    hideLoading();
}

//自定义公式 存放到页面
// $('#formula-choose').click(function () {
//     var $formulaText = $('#formula-text');
//     var formulaText = $formulaText.val();
//     if (formulaText.trim() == '') {
//         alert('请输入公式');
//         return;
//     }
//     $formulaText.val('');
//     var hideInput = '.' + $('#formula-type').val();
//     $(hideInput).val(formulaText);
//     $(hideInput).parents('tr').find('.formulaSpan').attr('title', formulaText);
//     $formula_table.modal("hide");
// });

$('#dt_basic_spare tbody').on('click','tr',function() {
    var self = $(this);
    if(self.hasClass('on')){
        self.removeClass('on');
    }else{
        self.addClass('on');
    }
});

//备件产品关系 新增中选择提交
var newtr = [];
$('#spare-choose').click(function () {
    var spareRows = spareTable.getCheckRowsData();
    if (spareRows.length === 0) {
        alert(message["please.select.a.piece.of.data"]);
        return;
    }
    $dialog_table.modal("hide");

    for (var i = 0; i < spareRows.length; i++) {
        var sameFlag = false;
        if ($(".delete[data-standard-id=" + spareRows[i].id + "]").length > 0) {
            sameFlag = true;
        }

        if (sameFlag) {
            continue;
        }

        // var time;
        // if (spareRows[i].executeTime == null) {
        //     time = '';
        // } else {
        //     time = (new Date(spareRows[i].executeTime)).Format("yyyy-MM-dd");
        // }
        // var descriptionStr;
        // if (spareRows[i].description == null) {
        //     descriptionStr = '';
        // } else {
        //     descriptionStr = spareRows[i].description.substring(0, 9) + '...';
        // }

        // $('.dt_basic_standard tbody').append(
        //     '<tr role="row" class="even newtr"><td class="sorting_1">' + spareRows[i].material + '</td><td>' + spareRows[i].type + '</td><td>' + spareRows[i].brand + '</td><td>' + spareRows[i].unit + '</td><td>' + time + '</td><td><span title="' + spareRows[i].description + '">' + descriptionStr + '</span></td><td><a class="delete" data-standard-id="' + spareRows[i].id + '"><i class="fa fa-trash-o"></i></a><input type="hidden" name="id" value="0"></td></tr>');
        // $(".st-sp.st-select").select2({data: datalist, minimumResultsForSearch: -1});
        //数组加入新增的id
        newtr.push(spareRows[i].id);

        // //清楚 暂无数据
        // $('.dataTables_empty').hide();
    }
    var pid = $('#productId').val();
    var tid = $('#s-type').val();
    var url = "/standard/rest/save";
    $.ajax({
        url: url,
        data: {sids:JSON.stringify(newtr),pid:pid,tid:tid},
        type: 'POST',
        success: function (data) {
            // alert(message['alert.message.success']);
            standardTable.submitFilter();
        },
        error: function (data) {
            alert(message['alert.message.systemError'], 3);
        }
    });
    newtr=[];
    spareTable.resetFilter();
});

//关系表新增
$('.s-add').click(function () {
    $dialog_table.modal("show");
});

//两个弹窗的取消按钮
// $('#formula-close').click(function () {
//         $('#formula-text').val('')
// }
// );
$('#spare-close').click(function () {
     $('#material-query').val('');
     $('#type-query').val('');
    spareTable.resetFilter();

});

