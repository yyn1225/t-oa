<#compress>
<div class="jarviswidget" data-widget-colorbutton="false" data-widget-editbutton="false"
     data-widget-togglebutton="false" data-widget-deletebutton="false"
     data-widget-fullscreenbutton="false" data-widget-custombutton="false">
    <header>
        <span class="widget-icon"> <i class="fa fa-table"></i></span>
        <input type="hidden" id="productId" value="${(productId?c)!}">
        <input type="hidden" id="s-type" value="0">
        <h2 data-dic="product.standard.list.title"></h2>
        <a class="btn btn-primary btn-xs s-add" style="float: right" data-standard-id="0" ><dic data-dic="header.list.button.add"></dic></a>
    </header>
    <div>
        <div class="widget-body no-padding">
            <form id="standard-edit-form">
            <table  class="dt_basic_standard table table-striped table-bordered table-hover" width="100%">

            </table>
            </form>
        </div>
    </div>
</div>

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