<#compress>
<div class="widget-body">
    <form class="smart-form addOrEdit-form" id="addOrEdit-form">

        <fieldset>
            <input type="hidden" name="productId" value="${(productId?c)!}"/>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.box"></dic>:
            </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" name="box">
                    <option value=""></option>
                    <#list (boxList)! as item>
                        <option value="${(item.id)!}">${(item.scnNo)!}</option>
                    </#list>
                </select>
            </label>

            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.series"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="hidden" id="seriesId">
                <select class="select2" style="width: 100%" name="series">
                    <option value=""></option>
                    <#list (seriesList)! as item>
                        <optgroup label="${(item.text)!}">
                            <#list item.children as child>
                                <option value="${(child.id)}">${(child.text)!}</option>
                            </#list>
                        </optgroup>
                    </#list>
                </select>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.certification"></dic>
                :
            </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" multiple name="certification">
                    <option value=""></option>
                    <#list (certificationList)! as item>
                        <option value="${(item.code)!}">${(item.name)!}</option>
                    </#list>
                </select>
            </label>
        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.state"></dic>
                :
            </label>
            <label class="col col-3 input">
                <input type="text" name="state"/>
            </label>

            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.configuration"></dic>
                :
            </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" name="configuration">
                    <option value=""></option>
                    <#list (configurationList)! as item>
                        <option value="${(item.id)!}">${(item.remark)!}</option>
                    </#list>
                </select>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.featured"></dic>
                :
            </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" name="featured">
                    <option value="1" data-dic="select.option.yes"></option>
                    <option value="0" data-dic="select.option.no"></option>
                </select>
            </label>
        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.executionTime"></dic>
                :
            </label>
            <label class="col col-3 input-group">
                <input type="text" name="execute" id="execute" class="form-control datepicker"
                       data-dateformat="yy-mm-dd">
                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.type"></dic>
                :
            </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" name="type">
                    <option value=""></option>
                    <#list (productType)! as item>
                        <option value="${(item.code)!}">${(item.name)!}</option>
                    </#list>
                </select>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.status"></dic>
                :
            </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" name="status">
                    <option value=""></option>
                    <#list (productStatus)! as item>
                        <option value="${(item.code)!}">${(item.name)!}</option>
                    </#list>
                </select>
            </label>
        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.color"></dic>
                :
            </label>
            <label class="col col-3 input">
                <input type="text" name="color" id="color" data-zh="" data-en="" data-category="product_color"/>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.special"></dic>
                :
            </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" name="request">
                    <option value=""></option>
                    <#list (special)! as item>
                        <option value="${(item.id)!}">${(item.fields)!}</option>
                    </#list>
                </select>
            </label>
        </fieldset>

        <#--<fieldset style="border-top: 1px solid rgba(0,0,0,.1) !important;">-->
            <#--<label class="col col-1 text-align-right padding">-->
                <#--<dic data-dic="product.panel.price.basePrice"></dic>-->
                <#--:-->
            <#--</label>-->
            <#--<label class="col col-3 input">-->
                <#--<input type="text" class="spinners" name="basePrice"/>-->
            <#--</label>-->
            <#--<label class="col col-1 text-align-right padding">-->
                <#--<dic data-dic="product.panel.price.salePrice"></dic>-->
                <#--:-->
            <#--</label>-->
            <#--<label class="col col-3 input">-->
                <#--<input type="text" class="spinners" name="salePrice"/>-->
            <#--</label>-->
            <#--<label class="col col-1 text-align-right padding">-->
                <#--<dic data-dic="product.panel.price.unit"></dic>-->
                <#--:-->
            <#--</label>-->
            <#--<label class="col col-3 input">-->
                <#--<select class="select2" name="unit" id="unit">-->
                    <#--<option value="CNY" data-key="￥">CNY(￥)</option>-->
                    <#--<#list rates as rate>-->
                        <#--<option value="${rate.code}" data-key="${rate.currency}">${rate.code!}(${rate.currency})</option>-->
                    <#--</#list>-->
                <#--</select>-->
            <#--</label>-->
        <#--</fieldset>-->
    </form>
</div>
<style>
    .smart-form fieldset + fieldset {
        border-top: none !important;
    }

    .padding {
        padding-top: 7px !important;
        padding-left: 0 !important;
        padding-right: 0 !important;
    }
</style>
</#compress>