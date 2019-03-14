<div style="width: 100%;margin: 5px 0;" class="row lang-list">
    <label class="col col-md-1 text-align-right padding7" style="padding-right: 0;padding-left: 0"><dic dic="org.customer.list.name"></dic>：</label>
    <label class="col col-md-3">
        <input type="text" onkeydown="keyDownSparesSearch()" id="search_query_name" query="name" class="form-control"/>
    </label>
    <label class="col col-md-1 text-align-right padding7" style="padding-right: 0;padding-left: 0;"><dic dic="org.customer.list.type"></dic>：</label>
    <label class="col col-md-3">
        <input type="text" onkeydown="keyDownSparesSearch()" id="search_query_type" query="type" class="form-control"/>
    </label>
    <label class="col col-md-1 text-align-right padding7" style="padding-right: 0;padding-left: 0;"><dic dic="org.customer.list.location"></dic>：</label>
    <label class="col col-md-3">
        <input type="text" onkeydown="keyDownSparesSearch()" id="search_query_location" query="location" class="form-control"/>
    </label>
</div>
<div class="row lang-list" style="width: 100%;margin: 5px 0;">
    <label class="col col-md-1 text-align-right padding7" style="padding-right: 0;padding-left: 0;"><dic dic="org.customer.list.phone"></dic>：</label>
    <label class="col col-md-3">
        <input type="text" onkeydown="keyDownSparesSearch()" id="search_query_phone" query="phone" class="form-control"/>
    </label>
    <label class="col col-md-5"></label>
    <label class="col col-md-1">
        <button type="button" id="spares_search_btn" style="width: 60px;height: 30px;" class="btn btn-primary"><dic dic="header.list.search">搜索</button>
    </label>
    <label class="col col-md-1">
        <button type="button" id="spares_rest_btn" style="width: 60px;height: 30px;" class="btn btn-primary"><dic dic="button.reset">重置</button>
    </label>
</div>
<table class="table table-bordered lang-list spares_list_table_customer" style="padding-right:17px;background-color:#999;color:#000;width: 100%;">
    <thead>
    <tr>
        <th style="width: 25px">
        </th>
        <th dic="org.customer.list.name" style="width:140px">名称</th>
        <th dic="org.customer.list.type" style="width: 70px">行业类型</th>
        <th dic="org.customer.list.location" style="width: 110px">城市</th>
        <th dic="product.spare.list.phone" style="width: 110px;">联系电话</th>
        <th dic="org.customer.list.email" style="width: 110px;">邮箱</th>
        <th dic="org.customer.list.levelName"  >等级</th>
    </tr>
    </thead>
</table>
<div style="max-height: 350px;overflow-y: auto;width: 860px;height: 300px;">
    <table class="table table-bordered lang-list" id="spares_list_table_customer" style="width: 100%;height:auto;overflow-y:scroll;table-layout: fixed;">
    <tbody>
        <#list list as item>
            <tr data-name="${(item.name)!}" data-type="${(item.type)!}"
                data-phone="${(item.phone)!}" data-location="${(item.location)!}" data-email="${(item.email)!}"
                data-levelName="${(item.levelName)!}">
                <td style="width: 25px">
                    <label class="checkbox">
                        <input type="checkbox" name="checkbox" class="spare_checkbox">
                        <i></i>
                    </label>
                </td>
                <td class="spare_name" style="width: 140px;" title="${(item.name)!}">${(item.name)!}</td>
                <td class="spare_type" style="width: 70px" title="${(item.type)!}">${(item.type)!}</td>
                <td class="spare_location" style="width: 110px" title="${(item.location)!}">${(item.location)!}</td>
                <td class="spare_phone" style="width:110px" title="${(item.phone)!}">${(item.phone)!}</td>
                <td class="spare_email" style="width: 110px;" title="${(item.email)!}">${(item.email)!}</td>
                <td class="spare_levelName"  title="${(item.levelName)!}">${(item.levelName)!}</td>
            </tr>
        </#list>
        <#if list?size==0>
            <tr>
                <td colspan="6" rowspan="3" class="text-align-center" dic="portal.dashboard.noData">暂无数据</td>
            </tr>
        </#if>
    </tbody>
</table>
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
    $.each($(".lang-list [dic]"),function (item) {
        $(this).html(message[$(this).attr("dic")]);
    });
    $('#spares_search_btn').off('click').click(function () {
        sparesSearch();
    });
    $('#spares_rest_btn').off('click').click(function () {
        var sparesTableTr = $('#spares_list_table_customer tr');
        sparesTableTr.show();
        $('#search_query_name,#search_query_type,#search_query_location,#search_query_phone').val('');
    });

    $('#spares_list_table_customer,.spares_list_table_customer').on('click','.spare_checkbox',function () {

        var tableCheckbox = $('#spares_list_table_customer .spare_checkbox:visible');

        tableCheckbox.prop('checked',false);

        $(this).prop('checked',true);

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
        if( $('#spares_list_table_customer .spare_checkbox:visible').length === 0){
            $('.spares_list_table_customer .spare_checkbox').prop('checked',false);
            return;
        }
        if($('#spares_list_table_customer .spare_checkbox:visible:checked').length === $('#spares_list_table_customer .spare_checkbox:visible').length){
            $('.spares_list_table_customer .spare_checkbox').prop('checked',true);
        }else{
            $('.spares_list_table_customer .spare_checkbox').prop('checked',false);
        }
    }
    /**
     * 搜索
     */
    function sparesSearch() {
        var sparesTableTr = $('#spares_list_table_customer tr');
        var querys = [];
        $('#search_query_name,#search_query_type,#search_query_location,#search_query_phone').each(function () {
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
        //checkCheckboxAll();

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