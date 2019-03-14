<#compress>
<#--<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">-->
    <#--<div class="alert alert-info" style="padding:3px 10px;margin:2px 6px;">-->
        <#--<h4><dic data-dic="admin.dashboard.file"></dic></h4>-->
    <#--</div>-->
    <#--<div class="jarviswidget col col-md-3" style="padding: 0 6px;" data-widget-editbutton="false">-->
        <#--<header>-->
            <#--<h2><dic data-dic="admin.dashboard.news"></dic></h2>-->
        <#--</header>-->
        <#--<div>-->
            <#--<div class="widget-body no-padding">-->
                <#--<table class="table table-striped table-bordered table-hover dataTable no-footer" width="100%">-->
                    <#--<tbody>-->
                        <#--<#if files.news??>-->
                            <#--<#list files.news as item>-->
                            <#--<tr role="row" class="odd">-->
                                <#--<td class="text-center dt_index sorting_1">${item.name}</td>-->
                            <#--</tr>-->
                            <#--</#list>-->
                        <#--<#else>-->
                        <#--<tr><td class="text-center"><dic data-dic="admin.dashboard.empty"></dic></td></tr>-->
                        <#--</#if>-->
                    <#--</tbody>-->
                <#--</table>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
    <#--<div class="jarviswidget col col-md-3" style="padding: 0 6px;" data-widget-editbutton="false">-->
        <#--<header>-->
            <#--<h2><dic data-dic="product.panel.list.name"></dic></h2>-->
        <#--</header>-->
        <#--<div>-->
            <#--<div class="widget-body no-padding">-->
                <#--<table class="table table-striped table-bordered table-hover dataTable no-footer" width="100%">-->
                    <#--<tbody>-->
                        <#--<#if files.product??>-->
                            <#--<#list files.product as item>-->
                            <#--<tr role="row" class="odd">-->
                                <#--<td class="text-center dt_index sorting_1">${item.name}</td>-->
                            <#--</tr>-->
                            <#--</#list>-->
                        <#--<#else>-->
                            <#--<tr><td class="text-center"><dic data-dic="admin.dashboard.empty"></dic></td></tr>-->
                        <#--</#if>-->
                    <#--</tbody>-->
                <#--</table>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
    <#--<div class="jarviswidget col col-md-3" style="padding: 0 6px;" data-widget-editbutton="false">-->
        <#--<header>-->
            <#--<h2><dic data-dic="admin.dashboard.solution"></dic></h2>-->
        <#--</header>-->
        <#--<div>-->
            <#--<div class="widget-body no-padding">-->
                <#--<table class="table table-striped table-bordered table-hover dataTable no-footer" width="100%">-->
                    <#--<tbody>-->
                        <#--<#if files.solution??>-->
                            <#--<#list files.solution as item>-->
                            <#--<tr role="row" class="odd">-->
                                <#--<td class="text-center dt_index sorting_1">${item.name}</td>-->
                            <#--</tr>-->
                            <#--</#list>-->
                            <#--<#else>-->
                            <#--<tr><td class="text-center"><dic data-dic="admin.dashboard.empty"></dic></td></tr>-->
                        <#--</#if>-->
                    <#--</tbody>-->
                <#--</table>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
    <#--<div class="jarviswidget col col-md-3" style="padding: 0 6px;" data-widget-editbutton="false">-->
        <#--<header>-->
            <#--<h2><dic data-dic="product.spare.list.brand"></dic></h2>-->
        <#--</header>-->
        <#--<div>-->
            <#--<div class="widget-body no-padding">-->
                <#--<table class="table table-striped table-bordered table-hover dataTable no-footer" width="100%">-->
                    <#--<tbody>-->
                        <#--<#if files.brand??>-->
                            <#--<#list files.brand as item>-->
                            <#--<tr role="row" class="odd">-->
                                <#--<td class="text-center dt_index sorting_1">${item.name}</td>-->
                            <#--</tr>-->
                            <#--</#list>-->
                        <#--<#else>-->
                        <#--<tr><td class="text-center"><dic data-dic="admin.dashboard.empty"></dic></td></tr>-->
                        <#--</#if>-->
                    <#--</tbody>-->
                <#--</table>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
<#--</article>-->

<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div class="alert alert-info" style="padding:3px 10px;margin:2px 6px;">
        <h4><dic data-dic="admin.dashboard.panelData"></dic></h4>
    </div>
    <div class="well col col-md-4" style="margin-left: 6px;width: 33%">
        <h1 style="text-align: center;margin-top: 0"><dic data-dic="admin.dashboard.indoor"></dic></h1>
        <div class="row">
            <#if series.indoor??>
                <#list series.indoor as indoor>
                    <div class="col col-md-6 div-series">
                        <label class="col col-md-12" style="text-align: center;">${indoor.text}</label>
                        <#list indoor.children as item>
                            <label class="col label-series">${item.text}</label>
                        </#list>
                    </div>
                </#list>
            </#if>
        </div>
    </div>
    <div class="well col col-md-4" style="width: 33%">
        <h1 style="text-align: center;margin-top: 0;"><dic data-dic="admin.dashboard.outdoor"></dic></h1>
        <div class="row">
            <#if series.outdoor??>
                <#list series.outdoor as outdoor>
                    <div class="col col-md-6 div-series">
                        <label class="col col-md-12" style="text-align: center;">${outdoor.text}</label>
                        <#list outdoor.children as item>
                            <label class="col label-series">${item.text}</label>
                        </#list>
                    </div>
                </#list>
            </#if>
        </div>
    </div>
    <div class="well col col-md-4" style="width: 33%">
        <h1 style="text-align: center;margin-top: 0"><dic data-dic="admin.dashboard.lease"></dic></h1>
        <div class="row">
            <#if series.rental??>
                <#list series.rental as rental>
                    <div class="col col-md-6 div-series">
                        <label class="col col-md-12" style="text-align: center;">${rental.text}</label>
                        <#list rental.children as item>
                            <label class="col label-series">${item.text}</label>
                        </#list>
                    </div>
                </#list>
            </#if>
        </div>
    </div>
</article>
<style>
    .label-series{
        border-radius: 5px;
        border:1px solid #CCCCCC;
        text-align: center;
        padding: 2px 3px;
        margin-right: 2px;
    }

    .well.col.col-md-4{
        padding-top:3px;
        padding-bottom: 2px;
    }

    .div-series{
        border-radius: 20px;
        border:1px solid #6e6e6e;
        text-align: center;
        margin-right:3px;
        width: 48%;
        margin-bottom:5px;
        padding:0;
    }
</style>
<script>
    $("[data-dic]").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });
</script>
</#compress>