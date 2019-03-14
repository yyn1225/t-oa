
/**
 * 点击checked选择配件
 */
$('#choose_spare_list_ul').off('click').on('click','tbody tr td',function () {
    if($.inArray(this,$(this).closest('tr').find('td')) !== 0){
        $(this).closest('tr').find('input[type="checkbox"]').click();
    }
    FittingUtil.updateCheckedCount();
});


/**
 * 多选配件 确定按钮(以下都为批量添加模态框的事件)
 */
$('#choose_spares_sure').off('click').click(function () {
    var spares_list = [];
    var spare_form = $("#panel-standard-1").find("li.active a:first").attr("href");
    var choose_form = $("#panel-standard-2").find("li.active a:first").attr("href");
    var free_form = $("#panel-standard-3").find("li.active a:first").attr("href");

    var spare_form_panel = 'panel-' + spare_form.split("-")[1];
    var choose_form_panel = 'panel-' + choose_form.split("-")[1];
    var free_form_panel = 'panel-' + free_form.split("-")[1];

    $.each($('#choose_spare_list_ul .tabs-spare tbody tr'),function (index,item) {
        if($(item).find('input[type="checkbox"]').prop("checked")){
            spares_list.push({
                data: $(item).data('item'),
                form: $("#"+spare_form+" form.smart-form.temp"),
                panelId: spare_form_panel,
                description: $(item).data('item').description
            });
        }
    });
    $.each($('#choose_spare_list_ul .tabs-choose tbody tr'),function (index,item) {
        if($(item).find('input[type="checkbox"]').prop("checked")){
            spares_list.push({
                data: $(item).data('item'),
                form: $("#"+choose_form+" form.smart-form.temp"),
                panelId: choose_form_panel,
                description: $(item).data('item').description
            });
        }
    });
    $.each($('#choose_spare_list_ul .tabs-free tbody tr'),function (index,item) {
        if($(item).find('input[type="checkbox"]').prop("checked")){
            spares_list.push({
                data: $(item).data('item'),
                form: $("#"+free_form+" form.smart-form.temp"),
                panelId: free_form_panel,
                description: $(item).data('item').description
            });
        }
    });
    spares_list.forEach(function (item) {
        appendSpares(item.data,item.form,item.panelId,item.description);
    });
    $('#choose_spares').modal('hide');
});

/**
 * 检测模态框关闭
 */
$('#choose_spares').on('hide.bs.modal', function () {
    $('body').css('overflow','auto');// 浮层关闭时滚动设置
});
/**
 * 检测模态框开启
 */
$('#choose_spares').on('show.bs.modal', function () {
    $('body').css('overflow','hidden');//浮层出现时窗口不能滚动设置
});

$('#dialog_test').off('click').click(function () {

    FittingUtil.chooseSparesDialog();
});

/**
 * 整合工具方法
 */
var FittingUtil = function FittingUtil() {
    return {
        //获取已选择
        findFittingList: function () {
            var map = {
                spare: [],
                choose: [],
                free: []
            };
            var panelKey = $('#panel-list-header li.active a').attr("href").split("-")[1];
            $.each($('#spare-'+ panelKey +'-1 fieldset'), function (index, item) {
                map.spare.push($(item).data().id);
            });
            $.each($('#spare-'+ panelKey +'-2 fieldset'), function (index, item) {
                map.choose.push($(item).data().id);
            });
            $.each($('#spare-'+ panelKey +'-3 fieldset'), function (index, item) {
                map.free.push($(item).data().id);
            });
            return map;
        },
        //多选配件 刷新数量
        updateCheckedCount: function () {
            var spareCount = $('.tabs-spare input[type="checkbox"]:checked').length;
            var chooseCount = $('.tabs-choose input[type="checkbox"]:checked').length;
            var freeCount = $('.tabs-free input[type="checkbox"]:checked').length;

            $('#countListShow a[href=".tabs-spare"] .check_count').html(spareCount);
            $('#countListShow a[href=".tabs-choose"] .check_count').html(chooseCount);
            $('#countListShow a[href=".tabs-free"] .check_count').html(freeCount);
        },
        /**
         * 根据类型勾选已选择的配件
         * @param type 类型 1-标配 2-选配 3-免费
         */
        byTypeChecked: function (type, fatherEl) {
            var ids = [];
            $.each($('#panel-standard-dialog-'+ type +' div.active fieldset'),function (index, item) {
                ids.push($(item).data('id'));
            });
            $.each(fatherEl.find('tbody tr'),function (index, item) {
                if($.inArray($(item).data('id'), ids) >= 0){
                    $(item).hide();
//                        $(item).find('input.spare_checkbox').prop('checked',true);
                }
            });
        },
        /**
         * 配件选中事件
         */
        checkedSpare: function (e) {
            e.preventDefault();
            var type = $("#spare-type").val().split('-')[2];
            //已选配件ID
            var ids = [];
            $.each($('#panel-standard-dialog-'+ type +' div.active fieldset'),function (index, item) {
                ids.push($(item).data('id'));
            });

            var checkboxs = $("#choose_spare_dialog table input:checkbox:checked");
            if(!checkboxs || checkboxs.length==0){
                alert(message["offer.create.spare.empty"]);
                return;
            }
            checkboxs.each(function(e) {
                var tr=$(this).parents("tr:first");
                if($(tr).data("id") && $.inArray($(tr).data("id"), ids) === -1){
                    var sparetag=$("#spare-type").val();
                    var data={
                        id:$(tr).data("id"),
                        product: $(tr).data('product'),
                        brand:$(tr).data("brand"),
                        typeName:$(tr).data("type"),
                        model:$(tr).data("model"),
                        unit:$(tr).data("unit"),
                        count2:$(tr).data("count2"),
                        type2:$(tr).data("type2"),
                        spel2:$(tr).data("spel2"),
                        count3:$(tr).data("count3"),
                        type3:$(tr).data("type3"),
                        spel3:$(tr).data("spel3"),
                        count4:$(tr).data("count4"),
                        type4:$(tr).data("type4"),
                        spel4:$(tr).data("spel4"),
                        count5:$(tr).data("count5"),
                        type5:$(tr).data("type5"),
                        spel5:$(tr).data("spel5"),
                        salePrice:$(tr).data("sale"),
                        costPrice:$(tr).data("cost"),
                        desc: $(tr).find('.spare_description').html()
                    };
                    appendSpares(data, $("#"+sparetag+" form.smart-form.temp"), $("#spare-type").data("panel"),$(tr).data("type"));
                }
            });

            $("#dialog_table").modal("hide");
            $("#choose_spare_dialog").html("");
            $("#spare-type").val("");
            $("#spare-type").data("panel","");
        },
        /**
         * 选择 配件、选配、免费
         */
        chooseSparesDialog: function () {
            var self = this;
            showLoading();
            var spare_id = $("#panel-standard-1").find("li.active a:first").attr("href");
            var tag = spare_id.split("-")[1];
            var panel=$("#panel-"+tag);
            var product=$(panel).find("[name='panel']").select2("val");
            var series = panel.find('[name=product]').select2("val");
            if ((!series)|| (!product)) {
                alert(message["quote.empty.product"], 2);
                hideLoading();
                return;
            }

            var spareTbody1 = $('#choose_spare_list_ul .tabs-spare1 tbody');
            var spareTbody2 = $('#choose_spare_list_ul .tabs-spare2 tbody');
            var chooseTbody1 = $('#choose_spare_list_ul .tabs-choose1 tbody');
            var chooseTbody2 = $('#choose_spare_list_ul .tabs-choose2 tbody');
            var freeTbody1 = $('#choose_spare_list_ul .tabs-free1 tbody');
            var freeTbody2 = $('#choose_spare_list_ul .tabs-free2 tbody');
            spareTbody1.html('');
            spareTbody2.html('');
            chooseTbody1.html('');
            chooseTbody2.html('');
            freeTbody1.html('');
            freeTbody2.html('');
            $.ajax({
                url: '/offer/rest/spares',
                type: 'get',
                data: {
                    product: product,
                    moneyType:$('#money-type option:selected').data('key'),
                    area:$("#price_area").select2("val"),
                    series:series
                },
                success: function (res) {
                    console.log(res);
                    hideLoading();
                    //已选择的配件ID
                    var fittingCount = self.findFittingList();
                    // var windowHeight = parseInt($(window).height() * 0.7);
                    // $('#choose_spare_list_ul').css('height', windowHeight + "px");

                    res.spares.forEach(function (item,index) {
                        if($.inArray(item.id, fittingCount.spare) === -1){
                            var itemEl = $('#self-spare-tr').tmpl(item);
                            itemEl.data('item',item);
                            if(index%2===0){
                                spareTbody1.append(itemEl);
                            }else{
                                spareTbody2.append(itemEl);
                            }
                        }
                    });

                    res.choose.forEach(function (item,index) {
                        if($.inArray(item.id, fittingCount.choose) === -1){
                            var itemEl = $('#self-spare-tr').tmpl(item);
                            itemEl.data('item',item);
                            if(index%2===0){
                                chooseTbody1.append(itemEl);
                            }else{
                                chooseTbody2.append(itemEl);
                            }
                        }
                    });

                    res.free.forEach(function (item,index) {
                        if($.inArray(item.id, fittingCount.free) === -1){
                            var itemEl = $('#self-spare-tr').tmpl(item);
                            itemEl.data('item',item);
                            if(index%2===0){
                                freeTbody1.append(itemEl);
                            }else{
                                freeTbody2.append(itemEl);
                            }
                        }
                    });

                    self.updateCheckedCount();
                    self.searchSpare();
                    $('#choose_spares').modal('show');
                },
                error: function (err) {
                    hideLoading();
                    console.log(err);
                    alert(message["operation.failed"]);
                }
            });
        },
        searchSpare: function () {
            var self = this;
            var spareSearchs = $('#choose_spare_list_ul a.btn_search');
            var spareRest = $('#choose_spare_list_ul a.btn_rest');
            var search_type,search_description,search_brand;
            spareSearchs.off('click').click(function () {
                console.log(1);
                if($(this).hasClass('spare_spare')){
                    var tabs_spare = $(this).closest('.tabs-spare');
                    search_type = tabs_spare.find('input.search_type').val();
                    search_description = tabs_spare.find('input.search_description').val();
                    search_brand = tabs_spare.find('input.search_brand').val();

                    $.each(tabs_spare.find('tr.spare_item'), function () {
                        var data = $(this).data('item');
                        if(search_type && data.type.indexOf(search_type) >= 0){
                            $(this).show();
                            return;
                        }
                        if(search_description && data.description.indexOf(search_description) >= 0){
                            $(this).show();
                            return;
                        }
                        if(search_brand && data.brand.indexOf(search_brand) >= 0){
                            $(this).show();
                            return;
                        }
                        if(search_type === '' && search_description === '' && search_brand === ''){
                            $(this).show();
                        }else{
                            $(this).find('input[type="checkbox"]').prop('checked',false);
                            $(this).hide();
                        }
                        self.updateCheckedCount();
                    });

                }else if($(this).hasClass('spare_choose')){
                    var tabs_choose = $(this).closest('.tabs-choose');
                    search_type = tabs_choose.find('input.search_type').val();
                    search_description = tabs_choose.find('input.search_description').val();
                    search_brand = tabs_choose.find('input.search_brand').val();

                    $.each(tabs_choose.find('tr.spare_item'), function () {
                        var data = $(this).data('item');
                        if(search_type && data.type.indexOf(search_type) >= 0){
                            $(this).show();
                            return;
                        }
                        if(search_description && data.description.indexOf(search_description) >= 0){
                            $(this).show();
                            return;
                        }
                        if(search_brand && data.brand.indexOf(search_brand) >= 0){
                            $(this).show();
                            return;
                        }
                        if(search_type === '' && search_description === '' && search_brand === ''){
                            $(this).show();
                        }else{
                            $(this).find('input[type="checkbox"]').prop('checked',false);
                            $(this).hide();
                        }
                        self.updateCheckedCount();
                    });
                }else if($(this).hasClass('spare_free')){
                    var tabs_free = $(this).closest('.tabs-free');
                    search_type = tabs_free.find('input.search_type').val();
                    search_description = tabs_free.find('input.search_description').val();
                    search_brand = tabs_free.find('input.search_brand').val();

                    $.each(tabs_free.find('tr.spare_item'), function () {
                        var data = $(this).data('item');
                        if(search_type && data.type.indexOf(search_type) >= 0){
                            $(this).show();
                            return;
                        }
                        if(search_description && data.description.indexOf(search_description) >= 0){
                            $(this).show();
                            return;
                        }
                        if(search_brand && data.brand.indexOf(search_brand) >= 0){
                            $(this).show();
                            return;
                        }
                        if(search_type === '' && search_description === '' && search_brand === ''){
                            $(this).show();
                        }else{
                            $(this).hide();
                            $(this).find('input[type="checkbox"]').prop('checked',false);
                        }
                        self.updateCheckedCount();
                    });
                }
            });
            spareRest.off('click').click(function () {
                if($(this).hasClass('spare_spare')){
                    var tabs_spare = $(this).closest('.tabs-spare');
                    tabs_spare.find('input.search_type').val('');
                    tabs_spare.find('input.search_description').val('');
                    tabs_spare.find('input.search_brand').val('');
                    tabs_spare.find('tr.spare_item').show();
                }else if($(this).hasClass('spare_choose')){
                    var tabs_choose = $(this).closest('.tabs-choose');
                    tabs_choose.find('input.search_type').val('');
                    tabs_choose.find('input.search_description').val('');
                    tabs_choose.find('input.search_brand').val('');
                    tabs_choose.find('tr.spare_item').show();
                }else if($(this).hasClass('spare_free')){
                    var tabs_free = $(this).closest('.tabs-free');
                    tabs_free.find('input.search_type').val('');
                    tabs_free.find('input.search_description').val('');
                    tabs_free.find('input.search_brand').val('');
                    tabs_choose.find('tr.spare_item').show();
                }
            });
        }
    }
}();