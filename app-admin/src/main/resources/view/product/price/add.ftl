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
                <label class="col col-md-1 text-align-right no-padding padding7"><dic data-dic="header.panellist.button.start.time"></dic>:</label>
                <label class="col col-md-2 input-group">
                    <input type="text" id="startTime" class="form-control datepicker" data-dateformat="yy-mm-dd"
                           value="">
                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                </label>
                <label class="col col-md-1 text-align-right no-padding padding7"><dic data-dic="header.panellist.button.end.time"></dic>:</label>
                <label class="col col-md-2 input-group">
                    <input type="text" id="endTime" class="form-control datepicker" data-dateformat="yy-mm-dd" value="">
                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                </label>
            </fieldset>
        </form>
        <div>
            <div class="dt-toolbar">
                <div class="col-sm-12 col-xs-12 hidden-xs" style="padding: 2px;">
                    <span style="padding: 4px;float:left;"><dic data-dic="import.all.data"></dic></span>
                    <a id="btn-submit" class="btn btn-success" style="float: right;padding: 3px 12px;"><dic data-dic="button.save"></dic></a>
                    <a id="btn-clear-table" class="btn btn-primary"
                       style="float: right; margin-right: 10px; padding: 3px 12px;">
                        <dic data-dic="header.list.button.clear"></dic>
                    </a>
                    <a id="btn-choose-table" class="btn btn-primary"
                       style="float: right; margin-right: 10px; padding: 3px 12px;">
                        <dic data-dic="importMsg.excel.choose"></dic>
                    </a>
                    <a id="btn-download-table" class="btn btn-primary"
                       style="float: right; margin-right: 10px; padding: 3px 12px;">
                        <dic data-dic="importMsg.excel.download"></dic>
                    </a>
                </div>
            </div>
            <table id="product-price-tables" class="table table-striped table-bordered table-hover" width="100%"
                   style="margin-bottom: 0;">
                <thead>
                <tr>
                    <th style="width: 100px;text-align: center"><dic data-dic="header.panellist.rownum"></dic></th>
                    <th style="width: 250px;text-align: center"><dic data-dic="standard.count.form.productNo"></th>
                    <th style="text-align: center" ><dic data-dic="product.price.item.panel.material.price"></th>
                    <th style="text-align: center" ><dic data-dic="product.price.item.modules.material.price"></th>
                    <th style="width: 150px;text-align: center"><dic data-dic="header.panellist.button.distribution.operate"></th>
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
            <table id="product-price-tables-error" class="table table-striped table-bordered table-hover" width="100%"
                   style="margin-bottom: 0;">
                <thead>
                <tr>
                    <th style="width: 100px;"><dic data-dic="header.panellist.rownum"></dic></th>
                    <th style="width: 250px;"><dic data-dic="standard.count.form.productNo"></dic></th>
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
</div>
<div style="display: none;">
    <div id="uploadPicker"></div>
</div>
<script type="text/javascript">



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
                        addPanelPrices(item.rowNum, item.partNo, item.price, item.modual, item.panel);
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

    $("#btn-download-table").off("click").click(function () {
        var form = $("<form method='get' action='/excel/download'><input type='hidden' name='type' value='8'></form>");
        $('body').append(form);
        form.submit();
        form.remove();
    });

    $("#btn-download-table-error").off("click").click(function () {
        if(lastUpload==0){
            return false;
        }
        var form = $("<form action='/download'><input type='hidden' name='attachmentId' value='"+lastUpload+"'></form>");
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

    $("#btn-cancel-back").off("click").click(function () {
        $("#second").hide();
        $("#second").html("");
        $("#first").show();
        $("#third").show();
    });

    $("#btn-submit").off("click").click(function () {
        submitData();
    });

    pageSetUp();

    $("#second dic").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });

    function submitData() {
        if(!$("#sys-name").val()|| !$("#startTime").val() || !$("#endTime").val()){
            alert(message["body.alert.message.fill.in.data"],2);
            return false;
        }
        showLoading();
        var details = [];
        var valid = true;
        var trList=$("#product-price-tables tr.insert");
        for(var index=0;index<trList.length;index++){
            var item=trList[index];
            if(!$(item).find("input:eq(0)").val() || !$(item).find("input:eq(1)").val()){
                valid=false;
                alert(message["body.alert.message.fill.in.price.data"],2);
                return false;
            }
            var data = {
                panel: $(item).data("panel"),
                price: $(item).find("input:eq(0)").val(),
                modual: $(item).find("input:eq(1)").val(),
                systems: 0,
                unit: $("#money-type").select2("val")
            };
            details.push(data);
        }
        $.ajax({
            url: "/panel/price/save",
            type: "post",
            data: {
                name: $("#sys-name").val(),
                startTime: $("#startTime").val(),
                endTime: $("#endTime").val(),
                details: JSON.stringify(details),
                unit: $("#money-type").select2("val")
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
</script>