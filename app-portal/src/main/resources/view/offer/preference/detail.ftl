<div style="width: 100%;margin: 5px 0;" class="row lang-list">
    <label class="col col-md-1 text-align-right padding7" style="padding-right: 0;"><dic dic="body.list.code"></dic>：</label>
    <label class="col col-md-3">
        <input type="text" onkeydown="keyDownSparesSearch()" id="search_query_material" query="material" class="form-control"/>
    </label>
    <label class="col col-md-1 text-align-right padding7 padding-l"><dic dic="quotes.offer.list.customer"></dic>：</label>
    <label class="col col-md-3">
        <input type="text" onkeydown="keyDownSparesSearch()" id="search_query_brand" query="brand" class="form-control"/>
    </label>
    <label class="col col-md-1 text-align-right padding7" style="padding-right: 0;padding-left: 0;"><dic dic="offer.create.remark"></dic>：</label>
    <label class="col col-md-3">
        <input type="text" onkeydown="keyDownSparesSearch()" id="search_query_description" query="description" class="form-control"/>
    </label>
</div>
<div class="row lang-list" style="width: 100%;margin: 5px 0;">
    <label class="col col-md-9"></label>
    <label class="col col-md-1">
        <button type="button" id="spares_search_btn" style="width: 60px;height: 30px;" class="btn btn-primary"><dic dic="header.list.search">搜索</button>
    </label>
    <label class="col col-md-1">
        <button type="button" id="spares_rest_btn" style="width: 60px;height: 30px;" class="btn btn-default"><dic dic="button.reset">重置</button>
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
        <th dic="body.list.code" style="width:200px">编号</th>
        <th dic="quotes.offer.list.customer" style="width: 100px">客户</th>
        <th dic="quotes.offer.list.totalPrice" style="width: 100px">总价格</th>
        <th dic="offer.create.remark">备注</th>
    </tr>
    </thead>
</table>
<div style="max-height: 350px;overflow-y: scroll;width: 860px;height: 300px;">
    <table class="table table-bordered lang-list" id="spares_list_table" style="width: 100%;height:auto;overflow-y:scroll;table-layout: fixed;">
        <tbody>
        <#list offerList as item>
            <tr data-id="${(item.id?c)!}" data-series="${(item.seriesName)!}" data-customer="${(item.customerName)!}">
                <td style="width: 25px">
                    <label class="checkbox">
                        <input type="checkbox" name="checkbox" class="spare_checkbox">
                        <i></i>
                    </label>
                </td>
                <td class="spare_material" style="width: 200px;" title="${(item.num)!}">${(item.num)!}</td>
                <td class="spare_brand" style="width: 100px" title="${(item.customerName)!}">${(item.customerName)!}</td>
                <td style="width: 100px" title="${(item.rate)!}${(item.totalPrice)!0}">${(item.rate)!}${(item.totalPrice)!0}</td>
                <td class="spare_description" title="${(item.remark)!}">${(item.remark)!}</td>
            </tr>
        </#list>
        <#if offerList?size==0>
            <tr>
                <td colspan="6" rowspan="3" class="text-align-center" dic="portal.dashboard.noData">暂无数据</td>
            </tr>
        </#if>
        </tbody>
    </table>
</div>
<style>
    #spares_list_table td{
        overflow: hidden;
        text-overflow:ellipsis;
        white-space: nowrap;
        padding: 8px 10px !important;
    }
    #dialog_table .form-control{
        padding: 0 3px;
    }
</style>

<script>
    $.each($(".lang-list [dic]"),function (item) {
        $(this).html(message[$(this).attr("dic")]);
    });
    $('#spares_search_btn').off('click').click(function () {
        sparesSearch();
    });
    $('#spares_rest_btn').off('click').click(function () {
        var sparesTableTr = $('#spares_list_table tr');
        sparesTableTr.show();
        $('#search_query_material,#search_query_type,#search_query_brand,#search_query_description').val('');
        checkCheckboxAll();
    });

    $('#spares_list_table,.spares_list_table').on('click','.spare_checkbox',function () {
        var checkAllEl = $('.spares_list_table .spare_checkbox');
        var tableCheckbox = $('#spares_list_table .spare_checkbox:visible');
        var tableCheckboxIsCheck = $('#spares_list_table .spare_checkbox:visible:checked');
        if($(this).hasClass('all')){
            if($(this).prop('checked')){
                tableCheckbox.prop('checked',true);
            }else{
                tableCheckbox.prop('checked',false);
            }
        }else{
            if($(this).prop('checked')){
                if(tableCheckboxIsCheck.length === tableCheckbox.length){
                    checkAllEl.prop('checked',true);
                }
            }else{
                if(tableCheckboxIsCheck.length !== tableCheckbox.length){
                    checkAllEl.prop('checked',false);
                }
            }
        }
    });

    //回车搜索
    function keyDownSparesSearch(e) {
        var ev= window.event||e;
        //13是键盘上面固定的回车键
        if (Number(ev.keyCode) === 13) {
            console.log('Enter Search');
            sparesSearch();
        }
    }

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
        var sparesTableTr = $('#spares_list_table tr');
        var querys = [];
        $('#search_query_material,#search_query_type,#search_query_brand,#search_query_description').each(function () {
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
</script>