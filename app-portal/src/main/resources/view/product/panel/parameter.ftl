<#compress>
<div class="widget-body">
    <form class="smart-form addOrEdit-form" id="params-form">

        <fieldset>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.grayScale"></dic>:
            </label>
            <label class="col col-2 input">${(params.grayScale)!"--"}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.calibration"></dic>:
            </label>
            <label class="col col-2 input">
                <#if (params.calibration)??>
                    <#if (params.calibration)! == 1>
                        <dic data-dic="select.option.yes"></dic>
                    </#if>
                    <#if (params.calibration)! == 0>
                        <dic data-dic="select.option.yes"></dic>
                    </#if>
                </#if>
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.intelligent"></dic>:
            </label>
            <label class="col col-2 input">
                <#if (params.intelligent)??>
                    <#if (params.intelligent)! == 1>
                    <dic data-dic="select.option.yes"></dic>
                    </#if>
                    <#if (params.intelligent)! == 0>
                        <dic data-dic="select.option.no"></dic>
                    </#if>
                </#if>
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.rigging"></dic>:</label>
            <label class="col col-2 input">${(params.rigging?c)!}
            </label>
        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.stack"></dic>:</label>
            <label class="col col-2 input">
                <#if (params.stack)??>
                    <#if (params.stack)! == 1>
                        <dic data-dic="select.option.yes"></dic>
                    </#if>
                    <#if (params.stack)! == 0>
                        <dic data-dic="select.option.no"></dic>
                    </#if>
                </#if>
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.front"></dic>:</label>
            <label class="col col-2 input">
                <#if (params.front)??>
                    <#if (params.front)! == 1>
                        <dic data-dic="select.option.yes"></dic>
                    </#if>
                    <#if (params.front)! == 0>
                        <dic data-dic="select.option.no"></dic>
                    </#if>
                </#if>
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.fixModual"></dic>:</label>
            <label class="col col-2 input">${(params.fixModual)!"--"}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.ipRating"></dic>:
            </label>
            <label class="col col-2 input">${(params.ipRating)!"--"}
            </label>
        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.brightness"></dic>:
            </label>
            <label class="col col-2 input">${(params.brightness)!"--"}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.contrastRatio"></dic>:
            </label>
            <label class="col col-2 input">${(params.contrastRatio)!"--"}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.fixPsu"></dic>:
            </label>
            <label class="col col-2 input">${(params.fixPsu)!"--"}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.refresh"></dic>:
            </label>
            <label class="col col-2 input">${(params.refresh)!"--"}
            </label>
        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.viewing"></dic>:
            </label>
            <label class="col col-2 input">${(params.viewing)!"--"}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.powerMax"></dic>:
            </label>
            <label class="col col-2 input">${(params.powerMax?c)!}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.powerAvg"></dic>:
            </label>
            <label class="col col-2 input">${(params.powerAvg?c)!}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.drivingIc"></dic>:
            </label>
            <label class="col col-2 input">${(params.drivingIc)!"--"}
            </label>
        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.drivingType"></dic>:
            </label>
            <label class="col col-2 input">${(params.drivingType)!"--"}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.psu"></dic>:
            </label>
            <label class="col col-2 input">${(params.psu)!"--"}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.psuPower"></dic>:
            </label>
            <label class="col col-2 input">${(params.psuPower?c)!}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.psuCount"></dic>:
            </label>
            <label class="col col-2 input">${(params.psuCount)!"--"}
            </label>
        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.standardCarryLower"></dic>:
            </label>
            <label class="col col-2 input">${(params.standardCarryLower?c)!}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.standardCarryHigh"></dic>:
            </label>
            <label class="col col-2 input">${(params.standardCarryHigh?c)!}
            </label>
            <label class="col col-1 text-align-right padding-l">
                <dic data-dic="product.panel.form.control"></dic>:
            </label>
            <label class="col col-2 input">${(params.control)!"--"}
            </label>
        </fieldset>
    </form>
</div>
</#compress>