<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="second">
    <div class="jarviswidget">
        <header>
            <i class="fa fa-edit"></i>
            <dic data-dic="product.spare.price.item.maintenance.price.system"></dic>
        </header>
        <div class="widget-body">
            <form class="smart-form">
                <fieldset>
                    <label class="col col-md-1 text-align-right no-padding padding7"><dic data-dic="org.role.list.name"></dic>:</label>
                    <label class="col col-md-2 text-align-left">
                        <input type="text" class="form-control" maxlength="50" id="sys-name">
                    </label>
                    <label class="col col-md-1 text-align-right no-padding padding7"><dic data-dic="header.panellist.currency"></dic>:</label>
                    <label class="col col-md-2 text-align-left">
                        <select class="select2" style="width: 100%" id="money-type">
                            <option value="CNY">￥</option>
                         <#list moneys as item>
                                <option value="${item.code}">${item.currency}</option>
                         </#list>
                        </select>
                    </label>
                </fieldset>
            </form>
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
                        <a id="btn-download-table" class="btn btn-primary" style="float: right; margin-right: 10px; padding: 3px 12px;"
                           href="/excel/download?type=9">
                            <dic data-dic="importMsg.excel.download"></dic>
                        </a>
                    </div>
                </div>
                <table id="product-price-tables" class="table table-striped table-bordered table-hover" width="100%" style="margin-bottom: 0;">
                    <thead>
                    <tr>
                        <th style="width: 100px;text-align: center""> <dic data-dic="header.panellist.rownum"></dic></th>
                        <th style="width: 250px;text-align: center""><dic data-dic="product.spare.part.number"></dic> </th>
                        <th style="text-align: center""><dic data-dic="product.panel.price.basePrice"></dic></th>
                        <th style="text-align: center""><dic data-dic="product.panel.price.areaPrice"></dic></th>
                        <th style="width: 150px;text-align: center"><dic data-dic="body.list.operate"></dic></th>
                    </tr>
                    </thead>
                </table>

                <div class="dt-toolbar">
                    <div class="col-sm-12 col-xs-12 hidden-xs" style="padding: 2px;">
                        <span style="padding: 4px;float:left;"><dic data-dic="body.list.exception.message"></dic></span>
                        <a id="btn-download-table-error" class="btn btn-primary" style="float: right; padding: 3px 12px;">
                            <dic data-dic="body.list.downland"></dic>
                        </a>
                    </div>
                </div>
                <table id="product-price-tables-error" class="table table-striped table-bordered table-hover" width="100%" style="margin-bottom: 0;">
                    <thead>
                    <tr>
                        <th style="width: 100px;"><dic data-dic="header.panellist.rownum"></dic></th>
                        <th style="width: 250px;"><dic data-dic="product.spare.part.number"></dic></th>
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
                <td style="text-align: center">
                    <a class="btn btn-primary btn-xs" style="float: right" role="deleteItem">
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
</article>
<div style="display: none;">
    <div id="uploadPicker"></div>
</div>
<script type="text/javascript" src="../../../../static/js/plugin/webuploader/webuploader.html5only.js"></script>
<script type="text/javascript">
    $("dic").each(function () {
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
            url: "/import/spare/price",
            type: "post",
            data: {attachmentId: res.id},
            success: function (data) {
                lastUpload=res.id;
                uploader.reset();
                if (!!data && !!data.success && data.success.length>0) {
                    data.success.forEach(function (item) {
                        addPanelPrices(item.rowNum, item.material, item.price, item.salePrice, item.spare);
                    });
                }
                if(!!data && !!data.error && data.error.length>0){
                    data.error.forEach(function (item) {
                        addErrorInfo(item.rowNum,item.material,item.error);
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

    $("#addNewSystem").off("click").click(function () {
        $("#first").hide();
        $("#third").hide();
        $("#second").show();
    });

    pageSetUp();

    $("table").on("click", "[role='deleteItem']", function () {
        $(this).parents("tr:first").remove();
    });

    $("#btn-choose-table").off("clicl").click(function () {
        $("input.webuploader-element-invisible[name='file']").click();
    });

    $("#btn-clear-table").off("click").click(function () {
        $("#product-price-tables tbody tr").remove();
        $("#product-price-tables-error tbody tr").remove();
    });

//    $("#btn-download-table").off("click").click(function () {
//        var form = $("<form method='get' action='/excel/download?type=9'></form>");
//        $('body').append(form);
//        form.submit();
//        form.remove();
//    });

    $("#btn-download-table-error").off("click").click(function () {
        if(lastUpload==0){
            return false;
        }
        var form = $("<form action='/download?attachmentId="+lastUpload+"'></form>");
        $('body').append(form);
        form.submit();
        form.remove();
    });


    function addPanelPrices(index, scnNo, price, modual, id) {
        var templates = $("#template-table tr:first").clone(true);
        templates.find("td:first").html(index);
        templates.find("td:eq(1)").html(scnNo);
        templates.find("input:first").val(price);
        templates.find("input:eq(1)").val(modual);
        templates.data("panel", id);
        $("#product-price-tables").append(templates);
    }

    function addErrorInfo(index,scnNo,error) {
        var tr=$("#template-table-error tr:first").clone();
        $(tr).find("td:first").html(index);
        $(tr).find("td:eq(1)").html(scnNo);
        $(tr).find("td:last").html(message[error]);
        $("#product-price-tables-error tbody").append($(tr));
    }

    $("#btn-submit").off("click").click(function () {
        submitData();
    });

    function submitData() {
        if (!$("#sys-name").val()) {
            alert(message["body.alert.message.fill.in.data"], 2);
            return false;
        }
        showLoading();
        var details = [];
        var valid = true;
        var trList = $("#product-price-tables tr.insert");
        for (var index = 0; index < trList.length; index++) {
            var item = trList[index];
            if (!$(item).find("input:eq(0)").val() || !$(item).find("input:eq(1)").val()) {
                valid = false;
                alert(message["body.alert.message.fill.in.price.data"], 2);
                return false;
            }
            var data = {
                area: $("#department").val(),
                spare: $(item).data("panel"),
                price: $(item).find("input:eq(0)").val(),
                salePrice: $(item).find("input:eq(1)").val(),
                unit: $("#money-type").select2("val")
            };
            details.push(data);
        }
        $.ajax({
            url: "/spare/price/save",
            type: "post",
            data: {
                name: $("#sys-name").val(),
                details: JSON.stringify(details),
                unit: $("#money-type").select2("val")
            },
            success: function (data) {
                alert(message["alert.message.success"]);
                $("#first").show();
                $("#third").show();
                $("#second").hide();
                systemTable.resetFilter();
                hideLoading();
            }, error: function (data) {
                alert(message[data.responseJSON.message], 3);
                hideLoading();
            }
        });
    }
</script>

<style>
    .webuploader-element-invisible{
        display: none !important;
    }

    .btn-xs {
        margin-right: 5px;
        margin-top: 5px;
    }

    .smart-form fieldset {
        padding: 20px 14px 5px;
        height: 50px;
    }

    .padding7{
        padding-top: 7px !important;
    }

    .dt-toolbar {
        display: block;
        position: relative;
        padding: 0;
        height: 32px;
        width: 100%;
        float: left;
        border: 1px solid #ccc;
        background: #f7f7f7;
    }

    #second div.dataTables_paginate{
        margin: 0 !important;
        padding:2px;
    }
</style>

</#compress>
