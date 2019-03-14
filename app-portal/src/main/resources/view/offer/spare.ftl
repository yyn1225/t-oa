<div class="jarviswidget">
    <header>
        <ul class="nav nav-tabs pull-left">
            <#list list as spares>
            <li <#if spares_index==0>class="active"</#if>>
                <a data-toggle="tab" href="#choose-spare-${spares_index}">
                    <span class="hidden-mobile hidden-tablet lang-list">
                        <#if (spares_index+1 == list?size)>
                            <dic dic="offer.create.commonSpares"></dic>
                        <#else>
                            <dic dic="offer.create.box"></dic>${spares_index+1}
                        </#if>
                    </span>
                </a>
            </li>
            </#list>
        </ul>
    </header>
    <div style="border-bottom: none">
        <div class="widget-body no-padding">
            <div class="tab-content padding-10" id="spare-content">
                <#list list as spares>
                    <div class="tab-pane fade in <#if spares_index==0>active</#if>" id="choose-spare-${spares_index}">
                        <div class="smart-form">
                        <div style="width: 100%;margin: 5px 0;" class="row lang-list">
                            <label class="col col-md-1 text-align-right padding" style="padding-right: 0;"><dic dic="product.spare.list.material"></dic>：</label>
                            <label class="col col-md-3">
                                <input type="text" onkeydown="keyDownSparesSearch()" id="search_query_material" query="material" class="form-control"/>
                            </label>
                            <label class="col col-md-1 text-align-right padding"><dic dic="product.standard.list.styType"></dic>：</label>
                            <label class="col col-md-3">
                                <input type="text" onkeydown="keyDownSparesSearch()" id="search_query_type" query="type" class="form-control"/>
                            </label>
                            <label class="col col-md-1 text-align-right padding"><dic dic="product.spare.list.brand"></dic>：</label>
                            <label class="col col-md-3">
                                <input type="text" onkeydown="keyDownSparesSearch()" id="search_query_brand" query="brand" class="form-control"/>
                            </label>
                        </div>
                        <div class="row lang-list" style="width: 100%;margin: 5px 0;">
                            <label class="col col-md-1 text-align-right padding7" style="padding-right: 0;padding-left: 0;"><dic dic="product.spare.list.description"></dic>：</label>
                            <label class="col col-md-3">
                                <input type="text" onkeydown="keyDownSparesSearch()" id="search_query_description" query="description" class="form-control"/>
                            </label>
                            <label class="col col-md-5"></label>
                            <label class="col col-md-1">
                                <button type="button" style="width: 60px;height: 30px;" class="btn btn-primary spares_search_btn"><dic dic="header.list.search">搜索</button>
                            </label>
                            <label class="col col-md-1">
                                <button type="button" style="width: 60px;height: 30px;" class="btn btn-default spares_rest_btn"><dic dic="button.reset">重置</button>
                            </label>
                        </div>
                        <table class="table table-bordered lang-list spares_list_table" style="padding-right:17px;background-color:#999;color:#000;width: 100%;">
                            <thead>
                            <tr>
                                <th style="width: 25px">
                                    <label class="checkbox">
                                        <input type="checkbox" class="spare_checkbox all">
                                        <i style="margin-top: -20px;"></i>
                                    </label>
                                </th>
                                <th dic="product.spare.list.material" style="width:120px">物料号</th>
                                <th dic="product.standard.list.styType" style="width: 120px">类型</th>
                                <th dic="product.spare.list.model" style="width: 140px">型号</th>
                                <th dic="product.spare.list.brand" style="width: 50px">品牌</th>
                                <th dic="product.spare.list.description">描述</th>
                            </tr>
                            </thead>
                        </table>
                        <div style="max-height: 350px;overflow-y: auto;height: 300px;">
                            <table class="table table-bordered lang-list table-list" id="spares_list_table" style="width: 100%;height:auto;overflow-y:scroll;table-layout: fixed;">
                            <tbody>
                                <#list spares as item>
                                    <tr data-id="${((item.id)?c)!}" data-model="${(item.model)!}" data-product="${(item.productId?c)!}"
                                        data-type="${(item.type)!}" data-brand="${(item.brand)!}" data-unit="${(item.unit)!}"
                                        data-type2="${(item.type2Year)!}" data-count2="${(item.count2Year)!}" data-spel2="${(item.spel2Year)!}"
                                        data-type3="${(item.type3Year)!}" data-count3="${(item.count3Year)!}" data-spel3="${(item.spel3Year)!}"
                                        data-type4="${(item.type4Year)!}" data-count4="${(item.count4Year)!}" data-spel4"${(item.spel4Year)!}"
                                        data-type5="${(item.type5Year)!}" data-count5="${(item.count5Year)!}" data-spel5="${(item.spel5Year)!}"
                                        data-sale="${(item.salePrice?string("#.####"))!1}" data-cost="${(item.costPrice?string("#.####"))!1}">
                                        <td style="width: 26px">
                                            <label class="checkbox">
                                                <input type="checkbox" name="checkbox" class="spare_checkbox">
                                                <i></i>
                                            </label>
                                        </td>
                                        <td class="spare_material" style="width: 120px;" title="${(item.material)!}">${(item.material)!}</td>
                                        <td class="spare_type" style="width: 120px" title="${(item.type)!}">${(item.type)!}</td>
                                        <td class="spare_type" style="width: 140px" title="${(item.model)!}">${(item.model)!}</td>
                                        <td class="spare_brand" style="width: 50px" title="${(item.brand)!}">${(item.brand)!}</td>
                                        <td class="spare_description" style="" title="${(item.description)!}">${(item.description)!}</td>
                                    </tr>
                                </#list>
                                <#if spares?size==0>
                                    <tr>
                                        <td colspan="6" rowspan="3" class="text-align-center" dic="portal.dashboard.noData">暂无数据</td>
                                    </tr>
                                </#if>
                            </tbody>
                        </table>
                        </div>
                    </div>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</div>
<style>
    .table-bordered td{
        overflow: hidden;
        text-overflow:ellipsis;
        white-space: nowrap;
    }
    input.form-control{
        padding: 0 3px;
    }
</style>

<script>
    /**
     * 加载语言数据
     */
    $.each($(".lang-list [dic]"),function (item) {
        $(this).html(message[$(this).attr("dic")]);
    });

    /**
     * 查询当前tab页面下的备件列表
     */
    $('#spare-content').on('click', '.spares_search_btn', function () {
        sparesSearch();
    });

    /**
     * 重置当前tab页面下的备件列表
     */
    $('#spare-content').on('click', '.spares_rest_btn', function () {
        var sparesTabActive = $('#spare-content .active');
        var sparesTableTr =  sparesTabActive.find('.table-list tr');
        sparesTableTr.show();
        sparesTabActive.find('[query="material"],[query="type"],[query="brand"],[query="description"]').val('');
        checkCheckboxAll();
    });

    /**
     * 勾选事件
     */
    $('.table-list,.spares_list_table').on('click','.spare_checkbox',function () {
        // $("#spare-content").find('.tab-pane.fade.in').each(function (index, item) {
        //     var checkAllEl = $(item).find('.spares_list_table .spare_checkbox');
        //     var tableCheckbox = $('.table-list .spare_checkbox:visible');
        // });
        // var sparesTabActive = $('#spare-content .active');
        // var checkAllEl = sparesTabActive.find('.spares_list_table .spare_checkbox');
        //
        // var tableCheckbox = $('.table-list .spare_checkbox:visible');
        // var tableCheckboxIsCheck = $('.table-list .spare_checkbox:visible:checked');
        var self = $(this);
        //判断当前点击是否全选按钮
        if(self.hasClass('all')) {
            if(self.prop('checked')){
                self.parents('.active').find('.table-list .spare_checkbox:visible').prop('checked',true);
                //循环选中其他tab页面相同的备件
                self.parents('.active').find('.table-list .spare_checkbox:visible').each(function (index, child) {
                    $('#spare-content tr').each(function (index, item) {
                        if ($(child).closest('tr').data('id') == $(item).data('id')) {
                            $(item).find('.spare_checkbox').prop('checked', true);
                        }
                    });
                });
            }else{
                self.parents('.active').find('.table-list .spare_checkbox:visible').prop('checked',false);
                //循环取消选中其他tab页面相同的备件
                self.parents('.active').find('.table-list .spare_checkbox:visible').each(function (index, child) {
                    $('#spare-content tr').each(function (index, item) {
                        if ($(child).closest('tr').data('id') == $(item).data('id')) {
                            $(item).find('.spare_checkbox').prop('checked', false);
                        }
                    });
                });
            }
        }else{
            if(self.prop('checked')) {
                $('#spare-content tr').each(function (index, item) {
                    if (self.closest('tr').data('id') == $(item).data('id')) {
                        $(item).find('.spare_checkbox').prop('checked', true);
                    }
                });
                // if(tableCheckboxIsCheck.length === tableCheckbox.length) {
                //     checkAllEl.prop('checked',true);
                // }
            }else {
                $('#spare-content tr').each(function (index, item) {
                    if (self.closest('tr').data('id') == $(item).data('id')) {
                        $(item).find('.spare_checkbox').prop('checked', false);
                    }
                });
                // if(tableCheckboxIsCheck.length !== tableCheckbox.length) {
                //     checkAllEl.prop('checked',false);
                // }
            }
        }
    });

    /**
     * 检查是否全选
     */
    function checkCheckboxAll() {
        if( $('#spares_list_table .spare_checkbox:visible').length === 0){
            $('.spares_list_table .spare_checkbox').prop('checked',false);
            return;
        }
        if($('#spares_list_table .spare_checkbox:visible:checked').length === $('#spares_list_table .spare_checkbox:visible').length){
            $('.spares_list_table .spare_checkbox').prop('checked',true);
        }else{
            $('.spares_list_table .spare_checkbox').prop('checked',false);
        }
    }

    /**
     * 搜索
     */
    function sparesSearch() {
        var sparesTabActive = $('#spare-content .active');
        var sparesTableTr =  sparesTabActive.find('.table-list tr');
        var querys = [];
        sparesTabActive.find('[query="material"],[query="type"],[query="brand"],[query="description"]').each(function () {
            var val = $(this).val();
            if(val){
                querys.push({
                    name: $(this).attr('query'),
                    val: $(this).val()
                });
            }
        });
        var showTrList = [];
        var hideTrList = [];
        $.each(sparesTableTr, function () {
            var tr = searchStrExist(querys, $(this));
            if(tr){
                showTrList.push(tr);
            }else{
                hideTrList.push($(this));
            }
        });
        sparesTableTr.hide();
        $.each(hideTrList,function () {
            $(this).find('.spare_checkbox').prop('checked',false);
        });
        $.each(showTrList,function () {
            $(this).show();
        });
        checkCheckboxAll();

        /**
         * 搜索是否存在 是：返回当前tr对象 否：返回null
         */
        function searchStrExist(searchQueryList,$tr) {
            var showCount = 0;
            if(searchQueryList.length === 0){
                return $tr;
            }
            $.each(searchQueryList,function () {
                var str = $tr.find('.spare_' + this.name).html();
                if(str.toLocaleUpperCase().indexOf($.trim(this.val).toLocaleUpperCase()) >= 0){
                    showCount++;
                }
            });
            if(showCount === searchQueryList.length){
                return $tr;
            }
            return null;
        }
    }

    /**
     * 回车搜索
     */
    function keyDownSparesSearch(e) {
        var ev= window.event||e;
        //13是键盘上面固定的回车键
        if (Number(ev.keyCode) === 13) {
            console.log('Enter Search');
            sparesSearch();
        }
    }
</script>