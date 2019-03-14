<div class="jarviswidget">
    <header>
        <i class="fa fa-edit"></i>
        <dic data-dic="product.price.item.maintenance.price.system"></dic>
        <a class="btn-xs btn btn-primary" style="float: right;" id="btn-cancel-back">
            <dic data-dic="header.list.button.cancel"></dic>
        </a>
    </header>
    <div class="widget-body">
        <form class="smart-form">
            <fieldset>
                <label class="col col-md-1 text-align-right no-padding padding7"><dic data-dic="org.role.list.name"></dic>:</label>
                <label class="col col-md-2 text-align-left padding7">${systems.name!}</label>
                <label class="col col-md-1 text-align-right no-padding padding7"><dic data-dic="header.panellist.currency"></dic>:</label>
                <label class="col col-md-2 text-align-left padding7">${systems.unit!}</label>
                <label class="col col-md-1 text-align-right no-padding padding7"><dic data-dic="header.panellist.button.start.time"></dic>:</label>
                <label class="col col-md-2 input-group">
                    <input type="text" id="startTime" class="form-control datepicker" data-dateformat="yy-mm-dd" value="${systems.startTime?string("yyyy-MM-dd")}">
                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                </label>
                <label class="col col-md-1 text-align-right no-padding padding7"><dic data-dic="header.panellist.button.end.time"></dic>:</label>
                <label class="col col-md-2 input-group">
                    <input type="text" id="endTime" class="form-control datepicker" data-dateformat="yy-mm-dd" value="${systems.endTime?string("yyyy-MM-dd")}">
                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                </label>
            </fieldset>
        </form>
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
                                   <#-- 屏体物料号&lt;#&ndash;--><dic data-dic="standard.count.form.productNo"></dic>
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="scnNo-query" query="scnNo"/>
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <#--屏体物料名称&lt;#&ndash;--><dic data-dic="standard.count.form.name"></dic>
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="productState-query" query="productState"/>
                                </label>

                                <label class="col col-4 text-align-right">
                                    <a class="btn btn-primary query-btn" id="btn_query" onclick="productNoSearch()">
                                        <dic data-dic="header.list.button.search"></dic>
                                    </a>
                                </label>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>

        </div>
        <div>
            <div class="dt-toolbar">
                <div class="col-sm-12 col-xs-12 hidden-xs" style="padding: 2px;">
                    <span style="padding: 4px;float:left;"><dic data-dic="import.all.data"></dic></span>
                    <a id="btn-submit" class="btn btn-success" style="float: right;padding: 3px 12px;"><dic data-dic="button.save"></dic></a>
                    <a id="btn-clear-table" class="btn btn-primary" style="float: right; margin-right: 10px; padding: 3px 12px;">
                        <dic data-dic="header.list.button.clear"></dic>
                    </a>
                    <a id="btn-choose-table" class="btn btn-primary" style="float: right; margin-right: 10px; padding: 3px 12px;">
                        <dic data-dic="importMsg.excel.choose"></dic>
                    </a>
                    <a id="btn-download-table" class="btn btn-primary" style="float: right; margin-right: 10px; padding: 3px 12px;">
                        <dic data-dic="importMsg.excel.download"></dic>
                    </a>
                    <a id="btn-download-table-all" class="btn btn-primary" style="float: right; margin-right: 10px; padding: 3px 12px;">
                        <dic data-dic="body.list.downland"></dic>
                    </a>
                </div>
            </div>
            <table id="product-price-tables" class="table table-striped table-bordered table-hover" width="100%" style="margin-bottom: 0;">
                <thead>
                    <tr>
                        <th style="width: 300px;text-align: center"><dic data-dic="product.price.item.panel.material.name"></dic></th>
                        <th style="width: 250px;text-align: center"><dic data-dic="product.price.item.panel.material.partNo"></dic></th>
                        <th style="text-align: center"><dic data-dic="product.price.item.panel.material.price"></dic></th>
                        <th style="text-align: center"><dic data-dic="product.price.item.modules.material.price"></dic></th>
                        <th style="width: 150px;text-align: center"><dic data-dic="header.panellist.button.distribution.operate"></dic></th>
                    </tr>
                </thead>
                <tbody>
                    <#list details as item>
                        <tr class="exists" data-id="${item.panel?c}" data-tag="${item.id?c}">
                            <td class="product-productState"><span>${item.productState!}</span></td>
                            <td class="product-scnNo">
                                <span>${item.scnNo}</span>
                            </td>
                            <td class="smart-form" style="padding:2px !important;">
                                <span>${item.price}</span>
                                <input type="text" class="form-control" value="${item.price?c}" style="display: none">
                            </td>
                            <td class="smart-form" style="padding:2px !important;">
                                <span>${item.modual}</span>
                                <input type="text" class="form-control" value="${item.modual?c}" style="display: none">
                            </td>
                            <td><a class="btn btn-xs btn-primary showedit"><dic data-dic="org.user.list.operate"></dic></a></td>
                        </tr>
                    </#list>
                </tbody>
            </table>

            <div class="dt-toolbar">
                <div class="col-sm-12 col-xs-12 hidden-xs" style="padding: 2px;">
                    <span style="padding: 4px;float:left;"><dic data-dic="body.list.exception.message"></dic></span>
                    <a id="btn-download-table-error" class="btn btn-primary" style="float: right; padding: 3px 12px;">
                        <dic data-dic="body.list.downland"></dic>
                    </a>
                </div>
            </div>
            <table id="product-price-tables-error" class="table table-striped table-bordered table-hover" width="100%"
                   style="margin-bottom: 0;">
                <thead>
                <tr>
                    <th style="width: 100px;"><dic data-dic="header.panellist.rownum"></dic></th>
                    <th style="width: 250px;"><dic data-dic="product.price.item.panel.material.partNo"></dic></th>
                    <th><dic data-dic="body.list.exception.reason"></dic></th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </div>
    <table style="display: none" id="template-table">
        <tbody>
        <tr class="insert">
            <td></td>
            <td></td>
            <td class="smart-form" style="padding:2px !important;">
                <input type="number" class="form-control">
            </td>
            <td class="smart-form" style="padding:2px !important;">
                <input type="number" class="form-control">
            </td>
            <td>
                <a class="btn btn-primary btn-xs" role="deleteItem">
                    <dic data-dic="body.list.delete"></dic>
                </a>
            </td>
        </tr>
        </tbody>
    </table>
    <table style="display: none;" id="template-table-error">
        <tbody>
        <tr>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>
<div style="display: none;">
    <div id="uploadPicker"></div>
</div>
<script type="text/javascript">

    $("#second dic").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });

    var lastUpload=0;
    var uploader = WebUploader.create({
        pick: "#uploadPicker",
        auto: true,
        accept: {
            title: 'Excel',
            extensions: 'xls,xlsx'
        },
        server: "/import/upload"
    });
    uploader.on("startUpload", function () {
        showLoading();
    });
    uploader.on("uploadSuccess", function (file, res) {
        $.ajax({
            url: "/import/price",
            type: "post",
            data: {attachmentId: res.id},
            success: function (data) {
                lastUpload=res.id;
                uploader.reset();
                if (!!data && !!data.success && data.success.length>0) {
                    data.success.forEach(function (item) {
                        addPanelPrices(item);
                    });
                }
                if(!!data && !!data.error && data.error.length>0){
                    data.error.forEach(function (item) {
                        addErrorInfo(item.rowNum,item.partNo,item.error);
                    })
                }
            },
            error: function () {
                hideLoading();
                alert(message["body.alert.message.file.parsing.failure"], 3);
            }
        });
        hideLoading();
    });

    uploader.on("uploadError", function (file, res) {
        hideLoading();
        alert(message["body.alert.message.file.upload.failure"]);
    });

    $(".showedit").off("click").click(function(){
        var tr=$(this).parents("tr");
        $(tr).find("td:eq(2)>span,td:eq(3)>span").hide();
        $(this).parents("tr").find("input").show();
    });

    $("table").on("click", "[role='deleteItem']", function () {
        var tr=$(this).parents("tr:first");
        var id =$(tr).data("id");
        $(tr).remove();
        var exists = $("tr.exists[data-id='"+id+"']");
        exists.removeClass("repeat");
    });

    $("#btn-choose-table").off("click").click(function () {
        $("input.webuploader-element-invisible[name='file']").click();
    });

    $("#btn-clear-table").off("click").click(function () {
        $("#product-price-tables tbody tr:not(.exists)").remove();
        $("#product-price-tables-error tbody tr").remove();
    });

    $("#btn-download-table").off("click").click(function () {
        //var form = $("<form method='get' action='/excel/download?type=8'></form>");
        var form = $("<form method='get' action='/excel/download'><input type='hidden' name='type' value='8'></form>");
        $('body').append(form);
        form.submit();
        form.remove();
    });

    $("#btn-download-table-error").off("click").click(function () {
        if(lastUpload==0){
            return false;
        }
        var form = $("<form action='/download?attachmentId="+lastUpload+"'></form>");
        $('body').append(form);
        form.submit();
        form.remove();
    });

    $("#btn-download-table-all").off("click").click(function () {
        var result = true;
        $("#product-price-tables tbody").find("tr").each(function (i) {
            var arrtd = $(this).children();
            if (arrtd.eq(2).find('input').css("display") != 'none' || arrtd.eq(3).find('input').css("display") != 'none') {
                result = false;
                return false;
            }
        });
        if (!result){
            alert(message["body.alert.message.save.data"]);
            return result;
        }


        var id = '${systems.id}';
        var form = $("<form action='/spare/price/currDownload' method='post' style='display:none'><input type='hidden'  name='type'  value='11'/><input type='hidden'  name='param'  value='" + id + "'/></form>");
        $('body').append(form);
        form.submit();
        form.remove();
    });
    function addPanelPrices(item) {
        var td=$("tr:not(.exists) td:contains("+item.partNo+")");
        if(td.length>0){
            var tr=$(td).parents("tr:first");
            $(tr).find("input:first").val(item.price);
            $(tr).find("input:last").val(item.modual);
            return false;
        }

        var input=$("tr.exists[data-id='"+item.panel+"'] input:visible");
        if(input.length>0){
            var exists=$("tr.exists[data-id='"+item.panel+"']");
            $(exists).find("input:first").val(item.price);
            $(exists).find("input:last").val(item.modual);
            return false;
        }


        var templates = $("#template-table tr:first").clone(true);
        var existTr=$("tr.exists[data-id='"+item.panel+"']");
        existTr.addClass("repeat");

        templates.find("td:first").html(item.state);
        templates.find("td:eq(1)").html(item.partNo);
        templates.find("input:first").val(item.price);
        templates.find("input:eq(1)").val(item.modual);
        templates.data("id", item.panel);
        templates.data("tag",existTr.data("tag")||0);
        $("#product-price-tables").append(templates);
    }

    function addErrorInfo(index,scnNo,error) {
        var tr=$("#template-table-error tr:first").clone();
        $(tr).find("td:first").html(index);
        $(tr).find("td:eq(1)").html(scnNo);
        $(tr).find("td:last").html(message[error]);
        $("#product-price-tables-error tbody").append($(tr));
    }

    $("#btn-cancel-back").off("click").click(function () {
        $("#second").hide();
        $("#second").html("");
        $("#first").show();
        $("#third").show();
    });

    $("#btn-submit").off("click").click(function () {
        submitData();
    });

    function submitData() {
        if(!$("#startTime").val() || !$("#endTime").val()){
            alert(message["body.alert.message.fill.in.data"],2);
            return false;
        }
        showLoading();
        var details = [];
        var valid = true;
        var trList=$("#product-price-tables tr.insert,#product-price-tables tr.exists");
        for(var index=0;index<trList.length;index++){
            if($(trList[index]).find("input:visible").length==0){
                continue;
            }

            var item=trList[index];
            if(!$(item).find("input:eq(0)").val() || !$(item).find("input:eq(1)").val()){
                valid=false;
                alert(message["body.alert.message.fill.in.peice.data"],2);
                return false;
            }
            var data = {
                panel: $(item).data("id"),
                price: $(item).find("input:eq(0)").val(),
                modual: $(item).find("input:eq(1)").val(),
                systems: '${systems.id!0}',
                unit: '${systems.unit!'CNY'}',
                id:($(item).data("tag")||0)
            };
            details.push(data);
        }

        $.ajax({
            url: "/panel/price/edit",
            type: "post",
            data: {
                startTime: $("#startTime").val(),
                endTime: $("#endTime").val(),
                id:'${systems.id}',
                details: JSON.stringify(details)
            },
            success: function (data) {
                if(data){
                    systemTable.submitFilter();
                }else{
                    alert(message["alert.message.systemError"],3);
                }
                $("#btn-cancel-back").click();
                hideLoading();
            },error:function () {
                alert(message["alert.message.systemError"],3);
                hideLoading();
            }
        });
    }

    /**
     * 搜索
     */
    function productNoSearch() {
        var sparesTableTr = $('#product-price-tables tbody tr');
        var querys = [];
        $('#scnNo-query,#productState-query').each(function () {
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
        $.each(showTrList,function () {
            $(this).show();
        });

        /**
         * 搜索是否存在 是：返回当前tr对象 否：返回null
         */
        function searchStrExist(searchQueryList,$tr) {
            var showCount = 0;
            if(searchQueryList.length === 0){
                return $tr;
            }
            $.each(searchQueryList,function () {
                var str = $tr.find('.product-' + this.name+" span").html();
                console.log(str);
                console.log($.trim(this.val));
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

<style type="text/css">
    tr.exists.repeat td{
        text-decoration:line-through;
    }

    tr.exists.repeat td>a{
        display: none;
    }

    tr.exists input{
        display: none;
    }

    #second table td{
        vertical-align: middle;
    }
</style>