<#compress>
<div class="jarviswidget" style="margin: 0 !important;"  data-widget-colorbutton="false" data-widget-editbutton="false"
     data-widget-togglebutton="false" data-widget-deletebutton="false"
     data-widget-fullscreenbutton="false" data-widget-custombutton="false">
    <header>
        <#--<span class="widget-icon"> <i class="fa fa-table"></i></span>-->
            <h2></h2>
            <a class="btn btn-primary btn-xs s-add continue" style="float: right"><dic data-dic="importMsg.excel.continue"></dic></a>
            <a class="btn btn-primary btn-xs s-add look" style="float: right" href="#"><dic data-dic="importMsg.excel.view"></dic></a>
    </header>
    <div style="border: none !important;">
        <div class="widget-body no-padding">
            <form id="standard-edit-form">
                <table id="errorTable"  class="dt_basic_standard table table-striped table-bordered table-hover" width="100%" style="margin: 0 !important;">
                    <thead>
                    <tr>
                        <th width="100">
                            <dic data-dic="importMsg.excel.rowNumber"></dic>
                        </th>
                        <th>
                            <dic data-dic="importMsg.excel.reason"></dic>
                        </th>
                    </tr>
                    </thead>
                </table>
            </form>
        </div>
    </div>
</div>
<script>
    $('[data-dic]').each(function(){
        $(this).html(message[$(this).data("dic")]);
    });
</script>
<style>
    .btn-xs{
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
</style>
</#compress>