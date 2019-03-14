<#compress>
<div class="widget-body">
    <form class="smart-form addOrEdit-form">

        <fieldset>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="panel.list.search.scn"></dic>:
            </label>
            <label class="col col-2 input">${(box.scnNo)!"--"}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="quotes.offer.list.seriesName"></dic>:
            </label>
            <label class="col col-2 input">${(seriesName.name)!"--"}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.list.certification"></dic>:
            </label>
            <label class="col col-2 input">${(certificationName.name)!"--"}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.list.state"></dic>:
            </label>
            <label class="col col-2 input" style="overflow: hidden">${(product.state)!"--"}
            </label>
        </fieldset>

        <fieldset>

            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.list.configuration"></dic>:
            </label>
            <label class="col col-2 input">${(configurations.remark)!"--"}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.list.featured"></dic>:
            </label>
            <label class="col col-2 input">
                <#if (product.featured)??>
                    <#if (product.featured)! == 1 >
                        <dic data-dic="select.option.yes"></dic>
                    </#if>
                    <#if (product.featured)! == 0 >
                        <dic data-dic="select.option.no"></dic>
                    </#if>
                <#else>
                    "--"
                </#if>
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.list.executionTime"></dic>:
            </label>
            <label class="col col-2 input">
            ${(product.executionTime?string("yyyy-MM-dd"))!"--"}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.list.productType"></dic>:
            </label>
            <label class="col col-2 input">${(productType.name)!"--"}
            </label>
        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.list.status"></dic>:
            </label>
            <label class="col col-2 input">${(productStatus.name)!"--"}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.list.color"></dic>:
            </label>
            <label class="col col-2 input">${(product.color)!"--"}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.list.request"></dic>:
            </label>
            <label class="col col-2 input">${(requests.fields)!"--"}
            </label>
        </fieldset>
    </form>
</div>
<style>
    fieldset {
        border-bottom: 1px dashed #cccccc !important;
    }
</style>
</#compress>