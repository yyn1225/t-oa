<#compress>
<div class="widget-body">
    <form class="smart-form addOrEdit-form">

        <fieldset>
            <input type="hidden" name="productId" value="${(productId?c)!}"/>
            <input type="hidden" class="submit_url" value="/product/basic/save">
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.box"></dic>:
            </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" name="box">
                    <#if (product.box)??>
                        <#list (boxList)! as item>
                            <option value="${(item.id)!}"
                                    <#if (item.id)! == product.box>selected</#if>>${(item.scnNo)!}</option>
                        </#list>
                    <#else>
                        <#list (boxList)! as item>
                            <option value="${(item.id)!}">${(item.scnNo)!}</option>
                        </#list>
                    </#if>
                </select>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.series"></dic>:
            </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" name="series">
                    <#list (seriesList)! as item>
                        <optgroup label="${(item.text)!}">
                            <#if (product.series)??>
                                <#list item.children as child>
                                    <option value="${(child.id)!}" <#if child.id == product.series>selected</#if>>${(child.text)!}</option>
                                </#list>
                            <#else>
                                <option value=""></option>
                                <#list item.children as child>
                                    <option value="${(child.id)!}">${(child.text)!}</option>
                                </#list>
                            </#if>
                        </optgroup>
                    </#list>
                </select>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.certification"></dic>:
            </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" multiple name="certification" id="cert">
                    <#if (product.certification)??>
                        <#list (certificationList)! as item>
                            <option value="${(item.code)!}" <#if (item.code)! == product.certification>selected</#if>>
                            ${(item.name)!}</option>
                        </#list>
                    <#else>
                        <option value=""></option>
                        <#list (certificationList)! as item>
                            <option value="${(item.code)!}">${(item.name)!}</option>
                        </#list>
                    </#if>
                </select>
            </label>
        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.state"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" name="state" value="${(product.state)!}"/>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.configuration"></dic>:
            </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" name="configuration">
                    <#if (product.configuration)??>
                        <#list (configurationList)! as item>
                            <option value="${(item.id)!}" <#if (item.id)! == product.configuration>selected</#if>>
                            ${(item.remark)!}</option>
                        </#list>
                    <#else>
                        <option value=""></option>
                        <#list (configurationList)! as item>
                            <option value="${(item.id)!}">
                            ${(item.remark)!}</option>
                        </#list>
                    </#if>
                </select>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.featured"></dic>:
            </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" name="featured">
                    <#if (product.featured)?? >
                        <option value="1" <#if product.featured == 1>selected</#if> data-dic="select.option.yes">
                        </option>
                        <option value="0" <#if product.featured == 0>selected</#if> data-dic="select.option.no">
                        </option>
                    <#else>
                        <option value="1" data-dic="select.option.yes"></option>
                        <option value="0" data-dic="select.option.no"></option>
                    </#if>
                </select>
            </label>
        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.executionTime"></dic>:
            </label>
            <label class="col col-3 input-group">
                <input type="text" name="execute" id="execute" class="form-control datepicker"
                       data-dateformat="yy-mm-dd" value="${(product.executionTime?string("yyyy-MM-dd"))!}">
                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.type"></dic>:
            </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" name="type">
                    <#if (product.type)??>
                        <#list (productType)! as item>
                            <option value="${(item.code)!}"
                                    <#if (item.code)! == product.type>selected</#if>>${(item.name)!}</option>
                        </#list>
                    <#else>
                        <option value=""></option>
                        <#list (productType)! as item>
                            <option value="${(item.code)!}">${(item.name)!}</option>
                        </#list>
                    </#if>
                </select>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.status"></dic>:
            </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" name="status">
                    <#if (product.status)??>
                        <#list (productStatus)! as item>
                            <option value="${(item.code)!}"
                                    <#if (item.code)! == product.status>selected</#if>>${(item.name)!}</option>
                        </#list>
                    <#else>
                        <option value=""></option>
                        <#list (productStatus)! as item>
                            <option value="${(item.code)!}">${(item.name)!}</option>
                        </#list>
                    </#if>
                </select>
            </label>
        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.color"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" id="color" name="color" value="${(product.color)!}"
                data-zh="${(product.color)!}" data-en="${(productLang["en"].colorLang)!}" data-category="product_color"/>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.list.special"></dic>:
            </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" name="request">
                    <#if (product.request)??>
                        <#list (special)! as item>
                            <option value="${(item.id?c)!}"
                                    <#if (item.id)! == product.request>selected</#if>>${(item.fields)!}</option>
                        </#list>
                    <#else>
                        <option value=""></option>
                        <#list (special)! as item>
                            <option value="${(item.id)!}">${(item.fields)!}</option>
                        </#list>
                    </#if>
                </select>
            </label>
        </fieldset>

        <#--<fieldset style="border-top: 1px solid rgba(0,0,0,.1) !important;">-->
            <#--<input type="hidden" name="priceId" value="${(price.id?c)!}">-->
            <#--<label class="col col-1 text-align-right padding">-->
                <#--<dic data-dic="product.panel.price.basePrice"></dic>:-->
            <#--</label>-->
            <#--<label class="col col-3 input">-->
                <#--<input type="text" class="spinners" name="basePrice" value="${(price.price?c)!}"/>-->
            <#--</label>-->

            <#--<label class="col col-1 text-align-right padding">-->
                <#--<dic data-dic="product.panel.price.salePrice"></dic>:-->
            <#--</label>-->
            <#--<label class="col col-3 input">-->
                <#--<input type="text" class="spinners" name="salePrice" value="${(price.salePrice?c)!}"/>-->
            <#--</label>-->
            <#--<label class="col col-1 text-align-right padding">-->
                <#--<dic data-dic="product.panel.price.unit"></dic>:-->
            <#--</label>-->
            <#--<label class="col col-3 input">-->
                <#--<select class="select2" name="unit" id="unit">-->
                    <#--<#if (price.unit)??>-->
                        <#--<option value="CNY" data-key="￥">CNY(￥)</option>-->
                        <#--<#list rates as rate>-->
                            <#--<option value="${rate.code}" data-key="${rate.currency}"-->
                                    <#--<#if rate.code == price.unit>selected</#if>>${rate.code!}(${rate.currency})-->
                            <#--</option>-->
                        <#--</#list>-->
                    <#--<#else>-->
                        <#--<#list rates as rate>-->
                            <#--<option value="CNY" data-key="￥">CNY(￥)</option>-->
                            <#--<option value="${rate.code}" data-key="${rate.currency}">${rate.code!}(${rate.currency})-->
                            <#--</option>-->
                        <#--</#list>-->
                    <#--</#if>-->
                <#--</select>-->
            <#--</label>-->
        <#--</fieldset>-->
    </form>
</div>
</#compress>