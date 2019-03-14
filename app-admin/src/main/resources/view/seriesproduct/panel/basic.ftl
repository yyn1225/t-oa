<#compress>
<div class="widget-body">
    <form class="smart-form addOrEdit-form">

        <fieldset>
            <label class="col col-1 text-align-right"><dic data-dic="product.panel.list.partNo"></dic>:</label>
            <label class="col col-3 input">${(product.partNo)!"--"}
            </label>
            <label class="col col-1 text-align-right"><dic data-dic="product.panel.list.series"></dic>:</label>
            <label class="col col-3 input">${(seriesName.name)!"--"}
            </label>
            <label class="col col-1 text-align-right"><dic data-dic="product.panel.list.certification"></dic>:</label>
            <label class="col col-3 input">${(certificationName.name)!"--"}
            </label>

        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right"><dic data-dic="product.panel.list.state"></dic>:</label>
            <label class="col col-3 input">${(product.state)!"--"}
            </label>
            <label class="col col-1 text-align-right"><dic data-dic="product.panel.list.configuration"></dic>:</label>
            <label class="col col-3 input">${(configurations.remark)!"--"}
            </label>
            <label class="col col-1 text-align-right"><dic data-dic="product.panel.list.featured.select"></dic>:</label>
            <label class="col col-3 input">
                <#if (product.featured)??>
                    <#if (product.featured)! == 0 ><dic data-dic="select.option.yes"></dic></#if>
                    <#if (product.featured)! == 1 ><dic data-dic="select.option.no"></dic></#if>
                <#else>
                    "--"
                </#if>
            </label>

        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right"><dic data-dic="product.panel.list.executionTime"></dic>:</label>
            <label class="col col-3 input">
            ${(product.executionTime?string("yyyy-MM-dd"))!"--"}
            </label>
            <label class="col col-1 text-align-right"><dic data-dic="product.panel.list.type"></dic>:</label>
            <label class="col col-3 input">${(productType.name)!"--"}
            </label>
            <label class="col col-1 text-align-right"><dic data-dic="product.panel.list.status"></dic>:</label>
            <label class="col col-3 input">${(productStatus.name)!"--"}
            </label>

        </fieldset>
        <fieldset>
            <label class="col col-1 text-align-right"><dic data-dic="product.panel.list.color"></dic>:</label>
            <label class="col col-3 input">${(product.color)!"--"}
            </label>
            <label class="col col-1 text-align-right"><dic data-dic="product.panel.list.special"></dic>:</label>
            <label class="col col-3 input">${(requests.fields)!"--"}
            </label>

            <label class="col col-1 text-align-right"><dic data-dic="product.panel.price.basePrice"></dic>:</label>
            <label class="col col-3 input">
                <#if (price.unit)??>
                    <#if (price.unit)! == "1">￥</#if>
                    <#if (price.unit)! == "2">$</#if>
                </#if>
            ${(price.price?c)!"--"}
            </label>
        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right"><dic data-dic="product.panel.price.salePrice"></dic>:</label>
            <label class="col col-3 input">
                <#if (price.unit)??>
                    <#if (price.unit)! == "1">￥</#if>
                    <#if (price.unit)! == "2">$</#if>
                </#if>
            ${(price.salePrice?c)!"--"}
            </label>

        </fieldset>
    </form>
</div>
<style>
    .col-2 {
        padding-left: 10px !important;
    }
    /*.smart-form .col-1 {*/
         /*width: 10%;*/
        /*padding: 0 !important;*/
     /*}*/
    /*.smart-form .col-3 {*/
        /*width: 22%;*/
    /*}*/

    fieldset {
        border-bottom: 1px dashed #cccccc !important;
    }
</style>
</#compress>