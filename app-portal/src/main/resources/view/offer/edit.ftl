<#compress>
<#--基本信息-->
<#--<article class="panel-input col-xs-1 col-sm-1 col-md-1 col-lg-1 sortable-grid ui-sortable">-->
<#--</article>-->
<article class="panel-input col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
    <div class="jarviswidget jarviswidget-sortable" id="basic-panel">
    <#--<header role="heading">-->
    <#--<span class="widget-icon"><i class="fa fa-cube"></i> </span>-->
    <#--<h2><strong><i>-->
    <#--<dic data-dic="offer.create.package"></dic>-->
    <#--</i></strong></h2>-->
    <#--<a class="btn btn-primary btn-xs btn-service" onclick="appendPackage()">-->
    <#--<i class="fa fa-plus"></i>-->
    <#--<dic data-dic="header.list.button.add"></dic>-->
    <#--</a>-->
    <#--</header>-->
        <div id="basic_part" style="border-bottom: 1px solid #ccc;border-top: 1px solid #ccc;">
            <div class="widget-body">
                <form class="smart-form">
                    <div class="row">
                        <label class="col col-md-1 text-align-right padding7 no-left no-right">
                            <dic data-dic="offer.create.sales"></dic>
                        </label>
                        <label class="col col-md-2">
                            <select class="select2 sales-select" style="width: 100%">
                                <option value="${(user.id)?c}" selected>${(user.userName)!}</option>
                            </select>
                        </label>

                        <label class="col col-md-1 padding7 no-left no-right">
                            <dic data-dic="quotes.offer.list.customer"></dic>
                        </label>
                        <label class="col col-md-2">
                            <div class="input-group colorpicker-demo2 colorpicker-element" style="width: 100%">
                                <div class='input-group input-group-sm' style="width: 100%">
                                    <input id="select_customer" class="input-autosuggest" data-id="${(offerVo.offer.customer?c)!}" value="${(offerVo.offer.customerName)!}"/>
                                    <div class='input-group-btn'>
                                        <button class='btn btn-default' type='button' style='height: 34px;'
                                                onclick="addNewCustomer()">
                                            <i class='fa fa-list'></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </label>

                        <label class="col col-md-1 padding7 no-left no-right">
                            <dic data-dic="quotes.offer.list.priceArea"></dic>
                        </label>
                        <label class="col col-md-2">
                            <select id="price_area" class="select2">
                                    <#if department??>
                                        <#list department as item>
                                            <option value="${(item.id)!}" <#if (offerVo.offer.area) == (item.id)> selected</#if>>${(item.name)!}</option>
                                        </#list>
                                    </#if>
                            </select>
                        </label>

                        <label class="col col-md-1 padding7 no-left no-right">
                            <dic data-dic="offer.create.monetaryUnit"></dic>
                        </label>
                        <label class="col col-md-2">
                            <select id="money-type" class="select2" style="width: 100%">
                                <option value="CNY" data-key="￥">CNY(￥)</option>
                                    <#list rates as rate>
                                        <option value="${rate.code}" data-key="${rate.currency}" <#if (offerVo.offer.moneyUnit) == (rate.code)> selected</#if>>${rate.code!}
                                            (${rate.currency})
                                        </option>
                                    </#list>
                            </select>
                        </label>
                    </div>
                    <div class="row" style="margin-top: 10px">
                        <label class="col col-md-1 padding7 no-left no-right">
                            <dic data-dic="quotes.offer.list.guarantee"></dic>
                        </label>
                        <label class="col col-md-2">
                            <select name="guarantee" id="guarantee-years" class="select2" style="width: 100%">
                                <#if offerVo.offer.guarantee == 2>
                                    <option value="2" data-dic="portal.my.twoYears" selected></option>
                                    <option value="3" data-dic="portal.my.threeYears"></option>
                                    <option value="4" data-dic="portal.my.fourYears"></option>
                                    <option value="5" data-dic="portal.my.fiveYears"></option>
                                <#elseif offerVo.offer.guarantee == 3>
                                    <option value="2" data-dic="portal.my.twoYears"></option>
                                    <option value="3" data-dic="portal.my.threeYears" selected></option>
                                    <option value="4" data-dic="portal.my.fourYears"></option>
                                    <option value="5" data-dic="portal.my.fiveYears"></option>
                                <#elseif offerVo.offer.guarantee == 4>
                                    <option value="2" data-dic="portal.my.twoYears"></option>
                                    <option value="3" data-dic="portal.my.threeYears"></option>
                                    <option value="4" data-dic="portal.my.fourYears" selected></option>
                                    <option value="5" data-dic="portal.my.fiveYears"></option>
                                <#else>
                                    <option value="2" data-dic="portal.my.twoYears"></option>
                                    <option value="3" data-dic="portal.my.threeYears"></option>
                                    <option value="4" data-dic="portal.my.fourYears"></option>
                                    <option value="5" data-dic="portal.my.fiveYears" selected></option>
                                </#if>
                            </select>
                        </label>

                        <label class="col col-md-1 padding7 no-left no-right"><dic data-dic="offer.create.boundary"></dic></label>
                        <label class="col col-md-2">
                            <select id="size-type" class="select2" style="width: 100%">
                                <#if offerVo.offer.sizeType == 1>
                                    <option value="1" data-dic="offer.create.not" selected></option>
                                    <option value="2" data-dic="offer.create.yes"></option>
                                    <option value="3" data-dic="offer.create.left"></option>
                                    <option value="4" data-dic="offer.create.top"></option>
                                <#elseif offerVo.offer.sizeType == 2>
                                    <option value="1" data-dic="offer.create.not"></option>
                                    <option value="2" data-dic="offer.create.yes" selected></option>
                                    <option value="3" data-dic="offer.create.left"></option>
                                    <option value="4" data-dic="offer.create.top"></option>
                                <#elseif offerVo.offer.sizeType == 3>
                                    <option value="1" data-dic="offer.create.not"></option>
                                    <option value="2" data-dic="offer.create.yes"></option>
                                    <option value="3" data-dic="offer.create.left" selected></option>
                                    <option value="4" data-dic="offer.create.top"></option>
                                <#else>
                                    <option value="1" data-dic="offer.create.not"></option>
                                    <option value="2" data-dic="offer.create.yes"></option>
                                    <option value="3" data-dic="offer.create.left"></option>
                                    <option value="4" data-dic="offer.create.top" selected></option>
                                </#if>
                            </select>
                        </label>

                        <label class="col col-md-1 padding7 no-left no-right">
                            <dic data-dic="offer.create.unit"></dic>
                        </label>
                        <label class="col col-md-3" style="padding-top: 5px">
                            <div class="inline-group">
                                <#if (offerVo.offer.sizeUnit) == 1>
                                    <label class="radio">
                                        <input name="radio-inline" checked="checked" type="radio" value="1">
                                        <i></i>
                                        <dic data-dic="offer.create.meters"></dic>
                                    </label>
                                    <label class="radio">
                                        <input name="radio-inline" type="radio" value="2">
                                        <i></i>
                                        <dic data-dic="offer.create.foot"></dic>
                                    </label>
                                <#else>
                                     <label class="radio">
                                         <input name="radio-inline" type="radio" value="1">
                                         <i></i>
                                         <dic data-dic="offer.create.meters"></dic>
                                     </label>
                                     <label class="radio">
                                         <input name="radio-inline" checked="checked" type="radio" value="2">
                                         <i></i>
                                         <dic data-dic="offer.create.foot"></dic>
                                     </label>
                                </#if>
                            </div>
                        </label>
                    </div>
                </form>
            </div>
        </div>
    </div>
</article>

<!-- 屏体信息 -->
<article class="panel-input col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable" style="padding-bottom: 52px;">
    <div class="jarviswidget" id="panel-list-header">
        <header>
            <ul class="nav nav-tabs pull-left">
                <#list (offerVo.panelDetailList) as item>
                    <li <#if item_index==0>class="active"</#if>>
                        <a data-toggle="tab" href="panel-${tag}${item_index}">
                            <span class="hidden-mobile hidden-tablet"><dic data-dic="offer.create.panel"></dic><#if (offerVo.panelDetailList)?? && ((offerVo.panelDetailList)?size > 1)>${item_index+1}</#if><span role="index"></span></span>
                            <div class="drop-panel"><i class="fa fa-trash-o"></i></div>
                        </a>
                    </li>
                </#list>
            </ul>
            <a class="btn btn-xs btn-default" id="dialog_test">
                <dic data-dic="offer.create.addPartsBatch"></dic>
            </a>
            <a class="btn btn-xs btn-primary" onclick="appendNewPanel()">
                <dic data-dic="customer.list.btn.addPanel"></dic>
            </a>
        </header>
        <div>
            <div class="widget-body no-padding">
                <div class="tab-content padding-10" role="panel" id="panel-list-content">
                    <#list (offerVo.panelDetailList) as panel>
                    <div class="tab-pane fade in <#if panel_index==0>active</#if>" id="panel-${tag}${panel_index}" data-width="${((panel.box.width/1000)?c)!100}" data-height="${((panel.box.height/1000)?c)!100}"
                         data-price="${((panel.offerPanels.costPrice)?c)!100}" data-sales="${((panel.offerPanels.suggPrice)?c)!100}" data-ave="${(panel.params.powerAvg?c)!0}"
                         data-top="${(panel.params.powerMax?c)!0}" data-boxwidth="${(panel.params.transverseCount)!1}" data-boxheight="${(panel.params.portraitCount)!1}" data-transversePix="${(panel.box.transversePix?c)!}"
                         data-portraitPix="${(panel.box.portraitPix?c)!}" >
                        <div class="row" style="border-bottom: solid 1px gainsboro">
                            <label class="col col-md-1 text-align-right padding7">
                                <dic data-dic="product.panel.list.type"></dic>
                            </label>
                            <label class="col col-md-2">
                                <select class="select2" name="product" style="width: 100%" id="select-series">
                                    <option value=""></option>
                                    <#list (seriesList)! as item>
                                         <optgroup label="${(item.text)!}">
                                             <#list item.children as child>
                                                 <option value="${(child.id)}" <#if (panel.offerPanels.series) == child.id>selected</#if>>${(child.text)!}</option>
                                             </#list>
                                         </optgroup>
                                    </#list>
                                </select>
                            </label>
                            <label class="col col-md-1 text-align-right padding7">
                                <dic data-dic="product.panel.list.configuration"></dic>
                            </label>
                            <label class="col col-md-5">
                                <#--<select name="panel" style="width: 100%"  class="select2">-->
                                     <#--<#if (panel.productBySeriesList)?? && (panel.productBySeriesList?size > 0)>-->
                                    <#--<#list panel.productBySeriesList as product>-->
                                        <#--<option value="${(product.id?c)!}" <#if (panel.offerPanels.panel) == product.id>selected</#if>>${(product.state)!}</option>-->
                                    <#--</#list>-->
                                     <#--</#if>-->
                                <#--</select>-->
                                    <input name="panel" style="width: 100%">
                                <input type="hidden" name="productId" value="${(panel.offerPanels.panel?c)!}">
                            </label>
                        </div>
                        <fieldset style="margin: 10px -12px 0 -12px;border-bottom: solid 1px gainsboro">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="row">
                                    <label class="col col-md-1 padding7 text-align-right">
                                        <dic data-dic="offer.create.panelNumber"></dic>
                                    </label>
                                    <label class="col col-md-2">
                                        <input type="text" tag="qty-width" value="${(panel.offerPanels.wcount?c)!0}" placeholder="宽"/>
                                    </label>
                                    <label class="col col-md-1 text-align-center" style="font-size: 16pt;">
                                        ×
                                    </label>
                                    <label class="col col-md-2">
                                        <input type="text" tag="qty-height" value="${(panel.offerPanels.lcount?c)!0}" placeholder="高"/>
                                    </label>
                                    <label class="col col-md-1 padding7 text-align-right">
                                        <span role="total">${((panel.offerPanels.wcount*panel.offerPanels.lcount)?c)!0}</span>pcs
                                    </label>
                                </div>
                                <div class="row">
                                    <label class="col col-md-1 padding7 text-align-right">
                                        <dic data-dic="offer.create.panelArea"></dic>
                                    </label>
                                    <label class="col col-md-2">
                                        <input type="text" tag="seq-width" value="${(panel.offerPanels.horizontal?c)!0}" placeholder="宽"/>
                                    </label>
                                    <label class="col col-md-1 text-align-center" style="font-size: 16pt;">
                                        ×
                                    </label>
                                    <label class="col col-md-2">
                                        <input type="text" tag="seq-height" value="${(panel.offerPanels.longitudinal?c)!0}" placeholder="高"/>
                                    </label>
                                    <label class="col col-md-1 padding7 text-align-right">
                                        <span role="total">${((panel.offerPanels.horizontal*panel.offerPanels.longitudinal)?c)!0}<#if offerVo.offer.sizeUnit == 1>㎡<#else>sq.ft</#if></span>
                                    </label>
                                    <label class="col col-md-1 text-align-center" style="font-size: 16pt;">
                                        ×
                                    </label>
                                    <label class="col col-md-1">
                                        <input type="text" tag="quantity" value="${(panel.offerPanels.quantity?c)!1}"/>
                                    </label>
                                    <label class="col col-md-1 padding7" role="total-area">
                                        ${((panel.offerPanels.horizontal*panel.offerPanels.longitudinal*panel.offerPanels.quantity)?c)!0}<#if offerVo.offer.sizeUnit == 1>㎡<#else>sq.ft</#if>
                                    </label>
                                    <label class="col col-md-2 no-left no-right padding7 text-align-center" style="font-weight: 700;">
                                        <dic data-dic="offer.create.panelTotal"></dic>：
                                        <span class="moneyUnit"></span><span id="price-panel-${tag}${panel_index}">${(panel.offerPanels.totalPrice?c)!0.00}</span>
                                    </label>
                                </div>
                            </div>
                        </fieldset>
                        <div style="margin-top: 10px" class="jarviswidget well transparent" id="wid-id-9" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false" role="widget">
                            <!-- widget options:
                            usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

                            data-widget-colorbutton="false"
                            data-widget-editbutton="false"
                            data-widget-togglebutton="false"
                            data-widget-deletebutton="false"
                            data-widget-fullscreenbutton="false"
                            data-widget-custombutton="false"
                            data-widget-collapsed="true"
                            data-widget-sortable="false"

                            -->
                            <header role="heading">
                                <span class="widget-icon"> <i class="fa fa-comments"></i> </span>
                                <h2>Accordions </h2>

                                <span class="jarviswidget-loader"><i class="fa fa-refresh fa-spin"></i></span></header>

                            <!-- widget div-->
                            <div role="content">
                                <!-- widget content -->
                                <div class="widget-body" style="min-height: 0 !important;">
                                    <div class="panel-group smart-accordion-default" id="accordion">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" class="collapsed">
                                                        <i class="fa fa-lg fa-angle-down pull-right"></i>
                                                        <i class="fa fa-lg fa-angle-up pull-right"></i>
                                                        <span><i class="fa fa-lg fa-cog"></i></span>
                                                        <dic data-dic="offer.create.params"></dic>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne" class="panel-collapse collapse" aria-expanded="false">
                                                <div class="panel-body no-padding">
                                                    <div class="tab-content padding-10" id="param-${tag}${panel_index}-dialog-1">
                                                            <div class="tab-pane fade in <#if panel_index==0>active</#if> param-table" id="param-${tag}${panel_index}-1">
                                                                <div class="smart-form">
                                                                    <div class="fnt">
                                                                        <p class="pline"><dic class="ml5 mr5" data-dic="offer.create.modual"></dic></p>
                                                                        <table class="table" style="width: 100%">
                                                                            <tbody style="border-left: 1px dashed #ccc;">
                                                                            <tr class="no-border">
                                                                                <td class="text-align-left"><dic data-dic="offer.create.module.size"></dic></td>
                                                                                <td class="text-align-right">${(panel.module.width?c)!0}&#42;${(panel.module.height?c)!0}</td>
                                                                                <td class='text-align-left'><dic data-dic="offer.create.module.pix"></dic></td>
                                                                                <td class='text-align-right'>${(panel.module.transverse?c)!0}&#42;${(panel.module.portrait?c)!0}</td>
                                                                                <td class="text-align-left"><dic data-dic="offer.view.spacing"></dic></td>
                                                                                <td class="text-align-right">${(panel.module.pitch)!0}</td>
                                                                                <td class='text-align-left'><dic data-dic="offer.create.module.density"></dic></td>
                                                                                <td class='text-align-right'>${(((1000/panel.module.pitch)*(1000/panel.module.pitch))?int)?c}</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td class='text-align-left'><dic data-dic="offer.create.module.weight"></dic></td>
                                                                                <td class='text-align-right'>${(panel.module.weight?c)!0}kg</td>
                                                                                <td class='text-align-left'><dic data-dic="offer.view.type"></dic></td>
                                                                                <td class='text-align-right'>${(panel.module.configuration)!0}</td>
                                                                                <td class="text-align-left"></td>
                                                                                <td class="text-align-right"></td>
                                                                                <td class='text-align-left'></td>
                                                                                <td class='text-align-right'></td>
                                                                            </tr>
                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                    <div class="fnt" style="margin-top: 10px;">
                                                                        <p class="pline"><dic class="ml5 mr5" data-dic="offer.create.box"></dic></p>
                                                                        <table class="table" style="width: 100%">
                                                                            <tbody style="border-left: 1px dashed #ccc;">
                                                                            <tr class="no-border">
                                                                                <td class="text-align-left"><dic data-dic="offer.create.box.size"></dic></td>
                                                                                <td class="text-align-right">${(panel.box.width?c)!0}&#42;${(panel.box.height?c)!0}&#42;${(panel.box.thickness?c)!0}</td>
                                                                                <td class='text-align-left'><dic data-dic="offer.create.box.pix"></dic></td>
                                                                                <td class='text-align-right'>${(panel.box.transversePix?c)!0}&#42;${(panel.box.portraitPix?c)!0}</td>
                                                                                <td class="text-align-left"><dic data-dic="offer.create.box.number"></dic></td>
                                                                                <td class="text-align-right">${((panel.box.transverseCount*panel.box.portraitCount)?c)!0}</td>
                                                                                <td class='text-align-left' style="width: 10% !important;"><dic data-dic="offer.create.box.display"></dic></td>
                                                                                <td class='text-align-right' style="width: 15% !important;">${((panel.box.transversePix*panel.box.portraitPix)?c)!0}</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td class='text-align-left'><dic data-dic="offer.create.module.weight"></dic></td>
                                                                                <td class='text-align-right'>${((panel.box.weight)?c)!0}kg</td>
                                                                                <td class="text-align-left"><dic data-dic="offer.create.params.powerAvg"></dic></td>
                                                                                <td class="text-align-right">${(panel.params.powerAvg?c)!0}</td>
                                                                                <td class='text-align-left'><dic data-dic="offer.create.params.powerMax"></dic></td>
                                                                                <td class='text-align-right'>${(panel.params.powerMax?c)!0}</td>
                                                                                <td class='text-align-left' style="width: 10% !important;"><dic data-dic="offer.create.BoxMaterial"></dic></td>
                                                                                <td class='text-align-right' style="width: 15% !important;">${(panel.box.boxType)!}</td>
                                                                            </tr>
                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                    <div class="fnt" style="margin-top: 10px;">
                                                                        <p class="pline"><dic class="ml5 mr5" data-dic="offer.create.params"></dic></p>
                                                                        <table class="table" style="width: 100%;">
                                                                            <tbody style="border-left: 1px dashed #ccc;">
                                                                            <tr class="no-border">
                                                                            <#--<td class='text-align-left font-important'><dic data-dic="offer.create.weight"></dic></td>-->
                                                                            <#--<td class='text-align-right font-important' data-counts="${(item.box.weight?c)!0}">-->
                                                                            <#--${((item.box.weight*item.offerPanels.wcount*item.offerPanels.lcount)?c)!0}-->
                                                                            <#--</td>-->
                                                                                <td class="text-align-left font-important"><dic data-dic="offer.view.pix"></dic></td>
                                                                                <td class="text-align-right font-important" data-ct1="${(panel.box.transversePix?c)!0}" data-ct2="${(panel.box.portraitPix?c)!0}">
                                            <#if panel.offerPanels.wcount?? && panel.offerPanels.lcount??>
                                                <#if panel.offerPanels.wcount == 0 || panel.offerPanels.lcount == 0>
                                                    ${((panel.box.transversePix)?c)!0}&#42;${((panel.box.portraitPix)?c)!0}
                                                <#else>
                                                    ${((panel.box.transversePix*panel.offerPanels.wcount)?c)!0}&#42;${((panel.box.portraitPix*panel.offerPanels.lcount)?c)!0}
                                                </#if>
                                            <#else>
                                                ${((panel.box.transversePix)?c)!0}&#42;${((panel.box.portraitPix)?c)!0}
                                            </#if>
                                                                                </td>
                                                                                <td class='text-align-left font-important'><dic data-dic="product.panel.form.powerAvg"></dic></td>
                                                                                <td class='text-align-right font-important' data-counts="${(panel.params.powerAvg?c)!0}">
                                            <#if panel.offerPanels.wcount?? && panel.offerPanels.lcount??>
                                                <#if panel.offerPanels.wcount == 0 || panel.offerPanels.lcount == 0>
                                                    ${(panel.params.powerAvg?c)!0}
                                                <#else>
                                                    ${((panel.params.powerAvg*panel.offerPanels.wcount*panel.offerPanels.lcount)?c)!0}
                                                </#if>
                                            <#else>
                                                ${(panel.params.powerAvg?c)!0}
                                            </#if>
                                                                                </td>
                                                                                <td class='text-align-left font-important'><dic data-dic="product.panel.form.powerMax"></dic></td>
                                                                                <td class='text-align-right font-important' data-counts="${(panel.params.powerMax?c)!0}">
                                            <#if panel.offerPanels.wcount?? && panel.offerPanels.lcount??>
                                                <#if panel.offerPanels.wcount == 0 || panel.offerPanels.lcount == 0>
                                                    ${(panel.params.powerMax?c)!0}
                                                <#else>
                                                    ${((panel.params.powerMax*panel.offerPanels.wcount*panel.offerPanels.lcount)?c)!0}
                                                </#if>
                                            <#else>
                                                ${(panel.params.powerMax?c)!0}
                                            </#if>
                                                                                </td>
                                                                                <td class='text-align-left'><dic data-dic="product.panel.list.color"></dic></td>
                                                                                <td class='text-align-right'>${(panel.product.color)!}</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td class='text-align-left'><dic data-dic="offer.create.singlePower"></dic></td>
                                                                                <td class='text-align-right'>${(panel.params.psuPower)!}</td>
                                                                                <td class="text-align-left"><dic data-dic="product.panel.form.brightness"></dic></td>
                                                                                <td class="text-align-right">${(panel.params.brightness)!}</td>
                                                                                <td class='text-align-left'><dic data-dic="product.panel.form.brightnessControl"></dic></td>
                                                                                <td class='text-align-right'>256</td>
                                                                                <td class='text-align-left'><dic data-dic="product.panel.form.viewingAngle"></dic></td>
                                                                                <td class='text-align-right'>${(panel.params.viewing)!}</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td class='text-align-left'><dic data-dic="product.panel.form.minDistance"></dic></td>
                                                                                <td class='text-align-right'>${(panel.module.pitch)!0}m</td>
                                                                                <td class="text-align-left"><dic data-dic="product.panel.form.grayScale"></dic></td>
                                                                                <td class="text-align-right">${(panel.params.grayScale)!}</td>
                                                                                <td class='text-align-left'><dic data-dic="product.panel.form.refresh"></dic></td>
                                                                                <td class='text-align-right'>${(panel.params.refresh)!}</td>
                                                                                <td class='text-align-left'><dic data-dic="product.panel.form.contrastRatio"></dic></td>
                                                                                <td class='text-align-right'>${(panel.params.contrastRatio)!}</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td class='text-align-left'><dic data-dic="product.panel.form.drivingType"></dic></td>
                                                                                <td class='text-align-right'>${(panel.params.drivingType)!}</td>
                                                                                <td class="text-align-left"><dic data-dic="product.panel.form.psuFrequency"></dic></td>
                                                                                <td class="text-align-right">50/60Hz</td>
                                                                                <td class='text-align-left'><dic data-dic="offer.create.input"></dic></td>
                                                                                <td class='text-align-right'>100V~240V</td>
                                                                                <td class='text-align-left'><dic data-dic="offer.create.death"></dic></td>
                                                                                <td class='text-align-right'><1/10000</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td class='text-align-left'><dic data-dic="offer.create.life"></dic></td>
                                                                                <td class='text-align-right'>100000</td>
                                                                                <td class="text-align-left"><dic data-dic="offer.create.Ingress"></dic></td>
                                                                                <td class="text-align-right">${(panel.params.ipRating)!}</td>
                                                                                <td class='text-align-left'><dic data-dic="offer.create.temperature"></dic></td>
                                                                                <td class='text-align-right'>-20 ~ +40 ℃</td>
                                                                                <td class='text-align-left'><dic data-dic="offer.create.humidity"></dic></td>
                                                                                <td class='text-align-right'>10% ~ 90%</td>
                                                                            </tr>
                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- end widget content -->
                            </div>
                            <!-- end widget div -->
                        </div>
                    </div>
                    </#list>
                </div>
            </div>
        </div>
    </div>

    <div class="jarviswidget" id="panel-standard-1">
        <header>
            <ul class="nav nav-tabs pull-left">
                <#list (offerVo.panelDetailList) as panel>
                    <li <#if panel_index==0>class="active"</#if>>
                        <a data-toggle="tab" href="spare-${tag}${panel_index}-1">
                            <span class="hidden-mobile hidden-tablet"><dic data-dic="product.panel.tab.standard"></dic><#if (offerVo.panelDetailList)?? && ((offerVo.panelDetailList)?size > 1)>${panel_index+1}</#if><span role="index"></span></span>
                        </a>
                    </li>
                </#list>
            </ul>
            <a class="btn btn-xs btn-default" style="float:right" onclick="addNewSelfSpare(1)">
                <dic data-dic="offer.create.custom"></dic>
            </a>
            <a class="btn btn-xs btn-primary" style="float:right" onclick="addNewSpare(1)">
                <dic data-dic="header.list.button.add"></dic>
            </a>
        </header>
        <div>
            <div class="widget-body no-padding">
                <div class="tab-content padding-10" id="panel-standard-dialog-1" style="padding-bottom: 0 !important;">
                    <div class="row">
                        <div class="col col-md-12">
                            <form class="smart-form temp">
                                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                    <fieldset style="border-right: 1px dashed #ccc;">
                                        <label class="col col-3 text-align-left">
                                            <dic data-dic="product.standard.list.styType"></dic>
                                        </label>
                                        <label class="col col-3 text-align-left">
                                            <dic data-dic="product.spare.list.brand"></dic>
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="product.standard.list.num"></dic>
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="offer.create.priceUnit">
                                        </label>
                                        <label class="col col-2 text-align-center">
                                            <dic data-dic="offer.create.price.sum"></dic>
                                        </label>
                                    </fieldset>
                                </div>
                                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                    <fieldset style="border-right: 1px dashed #ccc;">
                                        <label class="col col-3 text-align-left">
                                            <dic data-dic="product.standard.list.styType"></dic>
                                        </label>
                                        <label class="col col-3 text-align-left">
                                            <dic data-dic="product.spare.list.brand"></dic>
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="product.standard.list.num"></dic>
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="offer.create.priceUnit">
                                        </label>
                                        <label class="col col-2 text-align-center">
                                            <dic data-dic="offer.create.price.sum"></dic>
                                        </label>
                                    </fieldset>
                                </div>
                            </form>
                        </div>
                    </div>
                    <#list (offerVo.panelDetailList) as panel>
                        <div class="tab-pane fade in <#if panel_index==0>active</#if>" id="spare-${tag}${panel_index}-1">
                            <div class="row">
                                <div class="col col-md-12 spare">
                                    <form class="smart-form temp">
                                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                            <fieldset style="border-right: 1px dashed #ccc;" id="screen-fieldset-${tag}${panel_index}">
                                                <label class="col col-3 text-align-left padding7">LED-Screen</label>
                                                <label class="col col-3 text-align-left padding7" id="screen-brand-${tag}${panel_index}">Absen-</label>
                                                <label class="col col-1 text-align-left padding7 long-label" tag="total-area">
                                                    <input readonly class="acreage" amount value="${((panel.offerPanels.horizontal*panel.offerPanels.longitudinal)?c)!0}<#if offerVo.offer.sizeUnit == 1>㎡<#else>sq.ft</#if>"/>
                                                </label>
                                                <label class="col col-1 padding7 short-label no-left">
                                                <#--<span class="acreageUnit"></span>-->
                                                </label>
                                                <label class="col col-2 long-label margin-left-10">
                                                    <input sales class="form-control" type="text" tag="reference" style="width: 100%;" value="${(panel.offerPanels.price?c)!100}"/>
                                                </label>
                                                <label class="col col-2  text-align-right padding7" id="screen-total-${tag}${panel_index}">
                                                    <span class="moneyUnit"></span><span price>${((panel.offerPanels.price*panel.offerPanels.horizontal*panel.offerPanels.longitudinal)?c)!0.00}</span>
                                                </label>
                                            </fieldset>
                                        </div>
                                    <#list (panel.standardDetailList) as item>
                                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                        <fieldset role="spares" data-id="${(item.id?c)!}"
                                              data-spel2="${(item.spel2Year)!}" data-type2="${(item.type2Year)!}" data-count2="${(item.count2Year?c)!}"
                                              data-spel3="${(item.spel3Year)!}" data-type3="${(item.type3Year)!}" data-count3="${(item.count3Year?c)!}"
                                              data-spel4="${(item.spel4Year)!}" data-type4="${(item.type4Year)!}" data-count4="${(item.count4Year?c)!}"
                                              data-spel5="${(item.spel5Year)!}" data-type5="${(item.type5Year)!}" data-count5="${(item.count5Year?c)!}"
                                              data-cost="${(item.costPrice?c)!0}" data-sale="${(item.realPrice?c)!0}" style="border-right: 1px dashed #ccc;">
                                        <label title="${(item.description)!}" class="col col-3 text-align-left padding7">${(item.type)!}</label>
                                        <label class="col col-3 text-align-left padding7">${(item.brand)!}</label>
                                        <label class="col col-1 long-label">
                                            <input amount class="form-control" type="text" value="${(item.realCount?c)!0}"/>
                                        </label>
                                        <label class="col col-1 padding7 short-label">${(item.unit)!}</label>
                                        <label class="col col-1 long-label margin-left-10">
                                            <input sales class="form-control" type="text" value="${(item.realPrice?c)!0}"/>
                                        </label>
                                        <label class="col col-2  text-align-right padding7">
                                            <span class="moneyUnit"></span><span price>${((item.realCount*item.realPrice)?string("#.##"))!0.00}</span>
                                        </label>
                                        <label class="trash">
                                            <a class="trash-a"><i class="fa fa-trash-o"></i></a>
                                        </label>
                                    </fieldset>
                                    </div>
                                    </#list>
                                    <#list panel.selfStandardList as item>
                                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                        <fieldset role="spares" data-id="${item.id}" data-cost="${(item.costPrice?c)!0}" data-sale="${(item.price?c)!0}" style="border-right: 1px dashed #ccc;">
                                        <label class="col col-3 text-align-left padding7">${(item.spareType)!}</label>
                                        <label class="col col-3 text-align-left padding7">${(item.brand)!}</label>
                                        <label class="col col-1 long-label">
                                            <input amount class="form-control" type="text" value="${(item.amount?c)!0}"/>
                                        </label>
                                        <label class="col col-1 padding7 short-label">${(item.unit)!}</label>
                                        <label class="col col-1 long-label margin-left-10">
                                            <input sales class="form-control" type="text" value="${(item.price?c)!0}"/>
                                        </label>
                                        <label class="col col-2  text-align-right padding7">
                                            <span class="moneyUnit"></span><span price>${((item.amount*item.price)?string("#.##"))!0.00}</span>
                                        </label>
                                        <label class="trash">
                                            <a class="trash-a"><i class="fa fa-trash-o"></i></a>
                                        </label>
                                    </fieldset>
                                    </div>
                                    </#list>
                                    </form>
                                </div>
                            </div>
                            <div style="border-top: 1px solid #ccc;padding: 13px 13px 0;margin-left: 0 !important;margin-right: 0 !important;" class="row total-tag spare-tag">
                                <label class="col col-md-5"></label>
                                <label class="col col-md-2 text-align-right no-left no-right">
                                    <dic data-dic="offer.create.standardTotal"></dic>：
                                    <span class="moneyUnit"></span><span tag="total-sale">${((panel.offerPanels.standardPrice/(panel.offerPanels.standardDiscount/100))?string("#.##"))!0.00}</span>
                                </label>
                                <label class="col col-md-2 text-align-right no-left no-right">
                                    <dic data-dic="offer.create.discount"></dic>：
                                </label>
                                <label class="col col-md-1" style="margin-top: -7px;">
                                    <input type="text" tag="total-discount" value="${(panel.offerPanels.standardDiscount?c)!0}">
                                </label>
                                <label class="col col-md-2 text-align-right">
                                    <dic data-dic="offer.create.real"></dic>：
                                    <span class="moneyUnit"></span><span tag="total-real">${(panel.offerPanels.standardPrice?c)!0.00}</span>
                                </label>
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
    </div>

    <div class="jarviswidget" id="panel-standard-3">
        <header>
            <ul class="nav nav-tabs pull-left">
               <#list (offerVo.panelDetailList) as panel>
                   <li <#if panel_index==0>class="active"</#if>>
                       <a data-toggle="tab" href="spare-${tag}${panel_index}-3">
                           <span class="hidden-mobile hidden-tablet"><dic data-dic="product.panel.tab.free"></dic><#if (offerVo.panelDetailList)?? && ((offerVo.panelDetailList)?size > 1)>${panel_index+1}</#if><span role="index"></span></span>
                       </a>
                   </li>
               </#list>
            </ul>
            <a class="btn btn-xs btn-default" style="float:right" onclick="addNewSelfSpare(3)">
                <dic data-dic="offer.create.custom"></dic>
            </a>
            <a class="btn btn-xs btn-primary" style="float:right" onclick="addNewSpare(3)">
                <dic data-dic="header.list.button.add"></dic>
            </a>
        </header>
        <div>
            <div class="widget-body no-padding">
                <div class="tab-content padding-10" id="panel-standard-dialog-3" style="padding-bottom: 0 !important;">
                    <div class="row">
                        <div class="col col-md-12">
                            <form class="smart-form temp">
                                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                    <fieldset style="border-right: 1px dashed #ccc;">
                                        <label class="col col-3 text-align-left">
                                            <dic data-dic="product.standard.list.styType"></dic>
                                        </label>
                                        <label class="col col-3 text-align-left">
                                            <dic data-dic="product.spare.list.brand"></dic>
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="product.standard.list.num"></dic>
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="offer.create.priceUnit">
                                        </label>
                                        <label class="col col-2 text-align-center">
                                            <dic data-dic="offer.create.price.sum"></dic>
                                        </label>
                                    </fieldset>
                                </div>

                                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                    <fieldset style="border-right: 1px dashed #ccc;">
                                        <label class="col col-3 text-align-left">
                                            <dic data-dic="product.standard.list.styType"></dic>
                                        </label>
                                        <label class="col col-3 text-align-left">
                                            <dic data-dic="product.spare.list.brand"></dic>
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="product.standard.list.num"></dic>
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="offer.create.priceUnit">
                                        </label>
                                        <label class="col col-2 text-align-center">
                                            <dic data-dic="offer.create.price.sum"></dic>
                                        </label>
                                    </fieldset>
                                </div>
                            </form>
                        </div>
                    </div>
                    <#list (offerVo.panelDetailList) as panel>
                        <div class="tab-pane fade in <#if panel_index==0>active</#if>" id="spare-${tag}${panel_index}-3">
                            <div class="row">
                                <div class="col col-md-12 spare">
                                    <form class="smart-form temp">
                                    <#list (panel.freeDetailList) as item>
                                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                    <fieldset role="spares" data-id="${(item.id?c)!}"
                                              data-spel2="${(item.spel2Year)!}" data-type2="${(item.type2Year)!}" data-count2="${(item.count2Year?c)!}"
                                              data-spel3="${(item.spel3Year)!}" data-type3="${(item.type3Year)!}" data-count3="${(item.count3Year?c)!}"
                                              data-spel4="${(item.spel4Year)!}" data-type4="${(item.type4Year)!}" data-count4="${(item.count4Year?c)!}"
                                              data-spel5="${(item.spel5Year)!}" data-type5="${(item.type5Year)!}" data-count5="${(item.count5Year?c)!}"
                                              data-cost="${(item.costPrice?c)!0}" data-sale="${(item.realPrice?c)!0}" style="border-right: 1px dashed #ccc;">
                                        <label title="${(item.description)!}" class="col col-3 text-align-left padding7">${(item.type)!}</label>
                                        <label class="col col-3 text-align-left padding7">${(item.brand)!}</label>
                                        <label class="col col-1 long-label">
                                            <input amount class="form-control" type="text" value="${(item.realCount?c)!0}"/>
                                        </label>
                                        <label class="col col-1 padding7 short-label">${(item.unit)!}</label>
                                        <label class="col col-1 long-label margin-left-10">
                                            <input sales class="form-control" type="text" value="${(item.realPrice?c)!0}"/>
                                        </label>
                                        <label class="col col-2  text-align-right padding7">
                                            <span class="moneyUnit"></span><span price>${((item.realCount*item.realPrice)?string("#.##"))!0.00}</span>
                                        </label>
                                        <label class="trash">
                                            <a class="trash-a"><i class="fa fa-trash-o"></i></a>
                                        </label>
                                    </fieldset>
                                    </div>
                                    </#list>
                                    <#list panel.selfFreeList as item>
                                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                    <fieldset role="spares" data-id="${item.id}" data-cost="${(item.costPrice?c)!0}" data-sale="${(item.price?c)!0}" style="border-right: 1px dashed #ccc;">
                                        <label class="col col-3 text-align-left padding7">${(item.spareType)!}</label>
                                        <label class="col col-3 text-align-left padding7">${(item.brand)!}</label>
                                        <label class="col col-1 long-label">
                                            <input amount class="form-control" type="text" value="${(item.amount?c)!0}"/>
                                        </label>
                                        <label class="col col-1 padding7 short-label">${(item.unit)!}</label>
                                        <label class="col col-1 long-label margin-left-10">
                                            <input sales class="form-control" type="text" value="${(item.price?c)!0}"/>
                                        </label>
                                        <label class="col col-2  text-align-right padding7">
                                            <span class="moneyUnit"></span><span price>${((item.amount*item.price)?string("#.##"))!0.00}</span>
                                        </label>
                                        <label class="trash">
                                            <a class="trash-a"><i class="fa fa-trash-o"></i></a>
                                        </label>
                                    </fieldset>
                                    </div>
                                    </#list>
                                    </form>
                                </div>
                            </div>
                            <div style="border-top: 1px solid #ccc;padding: 13px 13px 0;margin-left: 0 !important;margin-right: 0 !important;" class="row total-tag spare-tag">
                                <label class="col col-md-8"></label>
                                <label class="col col-md-2 text-nowrap text-align-right">
                                    <dic data-dic="offer.create.freeTotal"></dic>：<span class="moneyUnit"></span><span tag="total-sale">${(panel.offerPanels.freePrice?string("#.##"))!}</span>
                                </label>
                                <label class="col col-md-2 text-align-right" style="margin-bottom: 12px;">
                                    <dic data-dic="offer.create.real"></dic>：<span class="moneyUnit"></span><span>0.00</span>
                                </label>
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
    </div>

    <div class="jarviswidget" id="panel-standard-2">
        <header>
            <ul class="nav nav-tabs pull-left">
                <#list (offerVo.panelDetailList) as item>
                    <li <#if item_index==0>class="active"</#if>>
                        <a data-toggle="tab" href="spare-${tag}${item_index}-2">
                            <span class="hidden-mobile hidden-tablet"><dic data-dic="product.panel.tab.match"></dic><#if (offerVo.panelDetailList)?? && ((offerVo.panelDetailList)?size > 1)>${item_index+1}</#if><span role="index"></span></span>
                        </a>
                    </li>
                </#list>
            </ul>
            <a class="btn btn-xs btn-default" style="float:right" onclick="addNewSelfSpare(2)">
                <dic data-dic="offer.create.custom"></dic>
            </a>
            <a class="btn btn-xs btn-primary" style="float:right" onclick="addNewSpare(2)">
                <dic data-dic="header.list.button.add"></dic>
            </a>
        </header>
        <div>
            <div class="widget-body no-padding">
                <div class="tab-content padding-10" id="panel-standard-dialog-2" style="padding-bottom: 0 !important;">
                    <div class="row">
                        <div class="col col-md-12">
                            <form class="smart-form temp">
                                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                    <fieldset style="border-right: 1px dashed #ccc;">
                                        <label class="col col-3 text-align-left">
                                            <dic data-dic="product.standard.list.styType"></dic>
                                        </label>
                                        <label class="col col-3 text-align-left">
                                            <dic data-dic="product.spare.list.brand"></dic>
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="product.standard.list.num"></dic>
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="offer.create.priceUnit">
                                        </label>
                                        <label class="col col-2 text-align-center">
                                            <dic data-dic="offer.create.price.sum"></dic>
                                        </label>
                                    </fieldset>
                                </div>
                                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                    <fieldset style="border-right: 1px dashed #ccc;">
                                        <label class="col col-3 text-align-left">
                                            <dic data-dic="product.standard.list.styType"></dic>
                                        </label>
                                        <label class="col col-3 text-align-left">
                                            <dic data-dic="product.spare.list.brand"></dic>
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="product.standard.list.num"></dic>
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="offer.create.priceUnit">
                                        </label>
                                        <label class="col col-2 text-align-center">
                                            <dic data-dic="offer.create.price.sum"></dic>
                                        </label>
                                    </fieldset>
                                </div>
                            </form>
                        </div>
                    </div>
                    <#list (offerVo.panelDetailList) as panel>
                        <div class="tab-pane fade in <#if panel_index==0>active</#if>" id="spare-${tag}${panel_index}-2">
                            <div class="row">
                                <div class="col col-md-12 spare">
                                    <form class="smart-form temp">
                                    <#list (panel.spareDetailList) as item>
                                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                    <fieldset role="spares" data-id="${(item.id?c)!}"
                                              data-spel2="${(item.spel2Year)!}" data-type2="${(item.type2Year)!}" data-count2="${(item.count2Year?c)!}"
                                              data-spel3="${(item.spel3Year)!}" data-type3="${(item.type3Year)!}" data-count3="${(item.count3Year?c)!}"
                                              data-spel4="${(item.spel4Year)!}" data-type4="${(item.type4Year)!}" data-count4="${(item.count4Year?c)!}"
                                              data-spel5="${(item.spel5Year)!}" data-type5="${(item.type5Year)!}" data-count5="${(item.count5Year?c)!}"
                                              data-cost="${(item.costPrice?c)!0}" data-sale="${(item.realPrice?c)!0}" style="border-right: 1px dashed #ccc;">
                                        <label title="${(item.description)!}" class="col col-3 text-align-left padding7">${(item.type)!}</label>
                                        <label class="col col-3 text-align-left padding7">${(item.brand)!}</label>
                                        <label class="col col-1 long-label">
                                            <input amount class="form-control" type="text" value="${(item.realCount?c)!0}"/>
                                        </label>
                                        <label class="col col-1 padding7 short-label">${(item.unit)!}</label>
                                        <label class="col col-1 long-label margin-left-10">
                                            <input sales class="form-control" type="text" value="${(item.realPrice?c)!0}"/>
                                        </label>
                                        <label class="col col-2  text-align-right padding7">
                                            <span class="moneyUnit"></span><span price>${((item.realCount*item.realPrice)?string("#.##"))!0.00}</span>
                                        </label>
                                        <label class="trash">
                                            <a class="trash-a"><i class="fa fa-trash-o"></i></a>
                                        </label>
                                    </fieldset>
                                    </div>
                                    </#list>
                                    <#list panel.selfSpareList as item>
                                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                    <fieldset role="spares" data-id="${item.id}" data-cost="${(item.costPrice?c)!0}" data-sale="${(item.price?c)!0}" style="border-right: 1px dashed #ccc;">
                                        <label class="col col-3 text-align-left padding7">${(item.spareType)!}</label>
                                        <label class="col col-3 text-align-left padding7">${(item.brand)!}</label>
                                        <label class="col col-1 long-label">
                                            <input amount class="form-control" type="text" value="${(item.amount?c)!0}"/>
                                        </label>
                                        <label class="col col-1 padding7 short-label">${(item.unit)!}</label>
                                        <label class="col col-1 long-label margin-left-10">
                                            <input sales class="form-control" type="text" value="${(item.price?c)!0}"/>
                                        </label>
                                        <label class="col col-2  text-align-right padding7">
                                            <span class="moneyUnit"></span><span price>${((item.amount*item.price)?string("#.##"))!0.00}</span>
                                        </label>
                                        <label class="trash">
                                            <a class="trash-a"><i class="fa fa-trash-o"></i></a>
                                        </label>
                                    </fieldset>
                                    </div>
                                    </#list>
                                    </form>
                                </div>
                            </div>
                            <div style="border-top: 1px solid #ccc;padding: 13px 13px 0;margin-left: 0 !important;margin-right: 0 !important;" class="row total-tag spare-tag">
                                <label class="col col-md-5"></label>
                                <label class="col col-md-2 text-align-right no-left no-right">
                                    <dic data-dic="offer.create.spareTotal"></dic>：
                                    <span class="moneyUnit"></span><span tag="total-sale">${((panel.offerPanels.sparePrice/(panel.offerPanels.spareDiscount/100))?string("#.##"))!0.00}</span>
                                </label>
                                <label class="col col-md-2 text-align-right no-left no-right">
                                    <dic data-dic="offer.create.discount"></dic>：
                                </label>
                                <label class="col col-md-1" style="margin-top: -7px;">
                                    <input type="text" tag="total-discount" value="${(panel.offerPanels.spareDiscount?c)!0}"></label>
                                <label class="col col-md-2 text-align-right">
                                    <dic data-dic="offer.create.real"></dic>：
                                    <span class="moneyUnit"></span><span tag="total-real">${(panel.offerPanels.sparePrice?c)!0.00}</span>
                                </label>
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
    </div>
<#--此处的tab样式（tab-pane fade in active）单纯为了寻找元素所用，下同-->
    <div class="jarviswidget tab-pane fade in active" id="service-panel">
        <header role="heading">
            <span class="widget-icon"><i class="fa fa-star"></i> </span>
            <h2>
                <strong>
                    <span><dic data-dic="offer.create.service"></dic></span>
                </strong>
            </h2>
            <a class="btn btn-primary btn-xs btn-service" onclick="appendService()">
                <i class="fa fa-plus"></i>
                <dic data-dic="header.list.button.add"></dic>
            </a>
        </header>
        <div id="service_part">
            <div class="widget-body">
                <form class="smart-form" id="service-form" style="word-break: keep-all">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <fieldset style="border-right: 1px dashed #ccc;">
                            <label class="col col-4 text-align-left">
                                <dic data-dic="product.standard.list.styType">
                            </label>
                            <label class="col col-3 ">
                                <dic data-dic="standard.count.form.count"></dic>
                            </label>
                            <label class="col col-2 text-align-left">
                                <dic data-dic="offer.create.priceUnit"></dic>
                            </label>
                            <label class="col col-2 text-align-right">
                                <dic data-dic="offer.create.price.sum"></dic>
                            </label>
                        </fieldset>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <fieldset style="border-right: 1px dashed #ccc;">
                            <label class="col col-4 text-align-left">
                                <dic data-dic="product.standard.list.styType">
                            </label>
                            <label class="col col-3 ">
                                <dic data-dic="standard.count.form.count"></dic>
                            </label>
                            <label class="col col-2 text-align-left">
                                <dic data-dic="offer.create.priceUnit"></dic>
                            </label>
                            <label class="col col-2 text-align-right">
                                <dic data-dic="offer.create.price.sum"></dic>
                            </label>
                        </fieldset>
                    </div>
                    <#if (offerVo.serviceList)??>
                        <#list offerVo.serviceList as item>
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                            <fieldset role="service" style="border-right: 1px dashed #ccc;">
                                <label class="col col-4">
                                    <input class="form-control" placeholder="" type="text" value="${(item.name)!}"/>
                                </label>
                                <label class="col col-3">
                                    <input amount class="form-control" placeholder="" type="text" value="${(item.counts?c)!0}"/>
                                </label>
                                <label class="col col-2">
                                    <input sales class="form-control" placeholder="" type="text" value="${(item.price?c)!0}"/>
                                </label>
                                <label class="col col-2 text-align-right padding7"><span class="moneyUnit"></span><span price><#if item.counts?? && item.price??>${(item.counts*item.price)?c}<#else>0.00</#if></span>
                                </label>
                                <label class="trash col col-1">
                                    <a class="trash-a"><i class="fa fa-trash-o"></i></a>
                                </label>
                            </fieldset>
                        </div>
                        </#list>
                    </#if>
                </form>
            </div>
        </div>
        <div style="border: 1px solid #ccc;margin-top: -2px;" class="row total-tag">
            <label class="col col-md-5"></label>
            <label class="col col-md-2 text-align-right no-left no-right">
                <dic data-dic="offer.create.totalPrice"></dic>：
                <span class="moneyUnit"></span><span tag="total-sale">${((offerVo.offer.servicePrice/(offerVo.offer.serviceDiscount/100))?string("#.##"))!0.00}</span>
            </label>
            <label class="col col-md-2 text-align-right no-left no-right">
                <dic data-dic="offer.create.discount"></dic>：
            </label>
            <label class="col col-md-1 no-left" style="margin-top: -7px;"><input type="text" tag="total-discount" value="${(offerVo.offer.serviceDiscount?c)!0}"></label>
            <label class="col col-md-2 text-align-right" style="margin-bottom: 12px;">
                <dic data-dic="offer.create.real"></dic>：
                <span class="moneyUnit"></span><span tag="total-real">${(offerVo.offer.servicePrice?c)!0.00}</span>
            </label>
        </div>
    </div>

    <div class="jarviswidget" id="package-panel">
        <header role="heading">
            <span class="widget-icon"><i class="fa fa-cube"></i> </span>
            <h2>
                <strong>
                    <span><dic data-dic="offer.create.package"></dic></span>
                </strong>
            </h2>
            <a class="btn btn-primary btn-xs btn-service" onclick="appendPackage()">
                <i class="fa fa-plus"></i>
                <dic data-dic="header.list.button.add"></dic>
            </a>
        </header>
        <div id="package_part">
            <div class="widget-body">
                <form class="smart-form" id="package-form" style="word-break: keep-all">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <fieldset style="border-right: 1px dashed #ccc;">
                            <label class="col col-md-4 text-align-left">
                                <dic data-dic="offer.create.package">
                            </label>
                            <label class="col col-md-3 text-align-left">
                                <dic data-dic="product.standard.list.num">
                            </label>
                            <label class="col col-md-2 text-align-left">
                                <dic data-dic="offer.create.priceUnit">
                            </label>
                            <label class="col col-md-2 text-align-right">
                                <dic data-dic="offer.create.price.sum">
                            </label>
                        </fieldset>
                    </div>

                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <fieldset style="border-right: 1px dashed #ccc;">
                            <label class="col col-md-4 text-align-left">
                                <dic data-dic="offer.create.package">
                            </label>
                            <label class="col col-md-3 text-align-left">
                                <dic data-dic="product.standard.list.num">
                            </label>
                            <label class="col col-md-2 text-align-left">
                                <dic data-dic="offer.create.priceUnit">
                            </label>
                            <label class="col col-md-2 text-align-right">
                                <dic data-dic="offer.create.price.sum">
                            </label>
                        </fieldset>
                    </div>
                    <#if offerVo.transport??>
                        <#list offerVo.transport as item>
                            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                            <fieldset role="package" style="border-right: 1px dashed #ccc;">
                                <label class="col col-4" style="position: inherit;">
                                    <input type="text" class="form-control packages" packages value="${(item.packages)!}"/>
                                </label>
                                <label class="col col-3">
                                    <input amount class="form-control" placeholder="" type="text" value="${(item.number?c)!0}"/>
                                </label>
                                <label class="col col-2">
                                    <input sales class="form-control" placeholder="" type="text" value="${(item.priceUnit?c)!0}"/>
                                </label>
                            <#--<input sales class="form-control" placeholder="" type="text" value="0"/>-->
                                <label class="col col-2 text-align-right padding7"><span class="moneyUnit"></span><span price>${(item.price?c)!0.00}</span></label>
                                <label class="trash-pack col col-1">
                                    <a class="trash-a"><i class="fa fa-trash-o"></i></a>
                                </label>
                            </fieldset>
                            </div>
                        </#list>
                    </#if>
                </form>
            </div>
        </div>

        <div class="row total-tag" style="border: 1px solid #ccc;margin-top: -3px;">
            <label class="col col-md-2"></label>
            <label class="col col-md-2 text-align-right no-left no-right">
                <dic data-dic="package.form.terms_of_trade"></dic>:
            </label>
            <label class="col col-md-2" style="margin-top: -7px;">
                <select class="packageTrade" style="width:100%">
                    <#list (tradeDict)! as item>
                        <option value="${item.code}" <#if (offerVo.transfer.trade)?? && (offerVo.transfer.trade)==item.name>selected</#if>>${item.name}</option>
                    </#list>
                </select>
            </label>
            <label class="col col-md-2 text-align-right no-left no-right">
                <dic data-dic="offer.create.transport"></dic>:
            </label>
            <label class="col col-md-2 input" style="margin-top: -7px">
                <input type="text" id="logistics-cost" value="${(offerVo.transfer.cost?c)!0}" />
            </label>
            <label class="col col-md-2 text-align-right" style="margin-bottom: 12px">
                <dic data-dic="offer.create.real"></dic>:
                <span class="moneyUnit"></span><span tag="total-real">${(transferPrice?c)!0.00}</span>
            </label>
        </div>
    </div>

    <div class="jarviswidget" style="border-top:1px solid #CCC;border-bottom:1px solid #CCC;">
        <div style="border-bottom: none !important;" class="row">
            <form class="smart-form">
                <fieldset>
                    <label class="col col-md-1 padding7 no-left no-right"><dic data-dic="quotes.offer.list.payment"></dic></label>
                    <label class="col col-md-2">
                        <input id="select_payment" class="form-control" style="width: 100%" value="${(offerVo.offer.payment)!}"/>
                    </label>

                    <label class="col col-md-1 padding7 no-left no-right"><dic data-dic="offer.create.company"></dic></label>
                    <label class="col col-md-2">
                        <select id="select_trader" class="select2" style="width: 100%">
                        <#if tradeCompany??><#list tradeCompany as item><option value="${(item.code)!}" <#if offerVo.offer.trader?? && (offerVo.offer.trader == item.code)>selected</#if>>${(item.name)}</option></#list></#if>
                        </select>
                    </label>

                    <label class="col col-md-1 padding7 no-left no-right input"><dic data-dic="offer.create.shipping"></dic></label>
                    <label class="col col-md-2">
                        <input id="shipping" type="text" class="form-control" value="${(offerVo.offer.shipping)!}"/>
                    </label>

                    <label class="col col-md-1 padding7 no-left no-right input"><dic data-dic="quotes.offer.list.waitingDate"></dic></label>
                    <label class="col col-md-2">
                        <input id="valid-date" value="${(offerVo.offer.waitingDate)!}" style="width: 100%"/>
                    </label>
                </fieldset>

                <fieldset>
                    <label class="col col-md-1 text-align-right no-left no-right padding-7">
                        <dic data-dic="offer.create.project.name"></dic>
                    </label>
                    <label class="col col-md-2">
                        <input type="text" class="form-control" maxlength="200" id="project_name" style="width: 100%;" value="${(offerVo.offer.projectName)!}"/>
                    </label>
                    <label class="col col-md-1 text-align-right no-left no-right padding-7">
                        <dic data-dic="offer.create.remark"></dic>:
                    </label>
                    <label class="col col-md-8 textarea">
                        <textarea rows="3" name="remark" class="custom-scroll" style="width: 100%;resize: none;">${(offerVo.offer.remark)!}</textarea>
                    </label>
                </fieldset>
            </form>
        </div style="border-bottom: none !important;">
    </div>
</article>
<#--页脚锁定行-->
<div class="page-footer" style="position: fixed;left: 0;margin: 0;z-index: 1000;background: #3a3f51">
    <div class="row">
        <div class="col-xs-12 col-sm-12" style="color: #c0bbb7">
            <label class="col col-md-1"></label>
            <label class="col col-md-2 text-align-right no-left no-right">
                <dic data-dic="offer.create.totalPrice"></dic>：
                <span id="totalPriceMoneySymbol"><span class="moneyUnit"></span></span><span id="total-price">${((offerVo.offer.totalPrice/(offerVo.offer.totalDiscount/100))?string("#.##"))!0.00}</span>
            </label>
            <label class="col col-md-2 text-align-right no-left no-right">
                <dic data-dic="offer.create.discount"></dic>：
            </label>
            <label class="col col-md-1 no-left" style="margin-top: -5px;"><input type="text" id="total-discount" value="${(offerVo.offer.totalDiscount?c)!0}"></label>
            <label class="col col-md-2 text-align-right">
                <dic data-dic="offer.create.finalPrices"></dic>：<span class="moneyUnit"></span><span id="total-real">${(offerVo.offer.totalPrice?c)!0.00}</span>
            </label>
            <button class="btn btn-default pull-right mr10" id="back_button" style="margin-top: -5px"><i class="fa  fa-arrow-circle-left mr5"></i>
                <dic data-dic="offer.create.back"></dic>
            </button>
            <#if offerVo.offer.status == 0>
            <button class="btn btn-default pull-right mr10" id="save_button" style="margin-top: -5px"><i class="fa fa-floppy-o mr5"></i>
                <dic data-dic="offer.create.save"></dic>
            </button>
            <button class="btn btn-primary pull-right mr10" id="submit_button" style="margin-top: -5px"><i class="fa fa-cloud-upload mr5" ></i>
                <dic data-dic="offer.create.submit"></dic>
            </button>
            </#if>
        </div>
    </div>
</div>

<div class="modal fade in" id="dialog_table" tabindex="-1" role="dialog">
    <div class="modal-dialog" style="width: 900px;">
        <input type="hidden" value="0" id="spare-type">
        <div class="modal-content">
            <div class="modal-header">
                <h4>
                    <dic data-dic="portal.series.spares"></dic>
                </h4>
            </div>
            <div class="modal-body no-padding">
                <form class="smart-form">
                    <fieldset>
                        <div id="choose_spare_dialog" class="custom-scroll table-responsive"
                             style="max-height:450px; overflow-y: scroll;">

                        </div>
                    </fieldset>
                    <footer>
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <dic data-dic="button.cancel"></dic>
                        </button>
                        <button type="button" id="spare-choose" class="btn btn-primary">
                            <dic data-dic="offer.create.choose">
                        </button>
                    </footer>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- 模态框（Modal）删除屏 -->
<div class="modal fade" id="del_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 350px">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    <i class="fa fa-warning"></i>
                    <dic data-dic="portal.window.attention"></dic>
                </h4>
            </div>
            <div class="modal-body">
                <dic data-dic="panel.message.del"></dic>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="ok_del">
                    <dic data-dic="button.confirm"></dic>
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 模态框（Modal）重置提示框 -->
<div class="modal fade" id="reset_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 350px">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    <i class="fa fa-warning"></i>
                    <dic data-dic="portal.window.attention"></dic>
                </h4>
            </div>
            <div class="modal-body">
                <dic data-dic="offer.create.resetMessage"></dic>
                <input type="hidden" id="resetHidden">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="ok_reset">
                    <dic data-dic="button.confirm"></dic>
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel_reset">
                    <dic data-dic="button.cancel"></dic>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="lower_base_price_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 350px">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    <dic data-dic="admin.window.attention"></dic>
                </h4>
            </div>
            <div class="modal-body">
                <dic data-dic="panel.message.lowerBasePrice"></dic>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="ok_low_base_price_submit">
                    <dic data-dic="button.confirm"></dic>
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<#--模态框  选择客户-->
<div class="modal fade in" id="dialog_table_customer" tabindex="-1" role="dialog">
    <div class="modal-dialog" style="width: 900px;">
        <input type="hidden" value="0" id="spare-type">
        <div class="modal-content">
            <div class="modal-header">
                <h4><dic data-dic="portal.dashboard.myCustomer"></dic></h4>
            </div>
            <div class="modal-body no-padding">
                <form class="smart-form">
                    <fieldset>
                        <div id="choose_spare_dialog_customer" class="custom-scroll table-responsive" style="max-height:450px; overflow-y: scroll;">

                        </div>
                    </fieldset>
                    <footer>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><dic data-dic="button.cancel"></dic></button>
                        <button type="button" id="spare-choose_customer" class="btn btn-primary"><dic data-dic="offer.create.choose"></button>
                    </footer>
                </form>
            </div>
        </div>
    </div>
</div>

<#--模态框  更改屏体单价-->
<div id="dialog-message" title="Dialog Simple Title" style="display: none;">
    <fieldset style="margin-top: 13px">
        <div class="col col-md-2 text-align-right padding">
            销售价
        </div>
        <div class="col col-md-8">
            <input type="text" class="form-control" tag="sale-price" />
        </div>
        <a class="btn btn-primary" id="reset-price">重置</a>
    </fieldset>
    <fieldset style="margin-top: 13px">
        <div class="col col-md-2 text-align-right padding">
            折扣(%)
        </div>
        <div class="col col-md-8">
            <input type="text" class="form-control" tag="panel-discount" />
        </div>
    </fieldset>
    <fieldset style="margin-top: 13px">
        <div class="col col-md-2 text-align-right padding">
            折后价格
        </div>
        <div class="col col-md-8">
            <input type="text" class="form-control" tag="discounted-price" />
        </div>
    </fieldset>
</div><!-- #dialog-message -->

<!-- 模态框 选择配件、选配、免费 -->
<div class="modal fade" id="choose_spares" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 1350px;">
        <input type="hidden" value="0" id="spare-type">
        <div class="modal-content">
            <div class="modal-header">
                <h4><dic data-dic="offer.create.choose"></dic></h4>
            </div>
            <div class="modal-body no-padding">
                <form class="smart-form">
                    <fieldset>
                        <div id="choose_spare_list_ul" class="custom-scroll table-responsive"
                             style="overflow-y: scroll;height: 450px;">
                            <div role="content">
                                <div class="jarviswidget-editbox">
                                </div>
                                <div class="widget-body">
                                    <div class="tabs-left">
                                        <ul class="nav nav-tabs tabs-left" id="countListShow"
                                            style="position: absolute;">
                                            <li class="active">
                                                <a href=".tabs-spare" data-toggle="tab"
                                                   aria-expanded="true" style="margin-top: 1px;">
                                                    <span class="badge bg-color-blue
                                                    txt-color-white check_count">0</span>
                                                    <dic data-dic="offer.create.standard"></dic>
                                                </a>
                                            </li>
                                            <li class="">
                                                <a href=".tabs-free" data-toggle="tab" aria-expanded="false">
                                                    <span class="badge bg-color-greenLight
                                                    txt-color-white check_count">0</span>
                                                    <dic data-dic="offer.create.free"></dic>
                                                </a>
                                            </li>
                                            <li class="">
                                                <a href=".tabs-choose" data-toggle="tab" aria-expanded="false">
                                                    <span class="badge bg-color-blueDark
                                                    txt-color-white check_count">0</span>
                                                    <dic data-dic="offer.create.spare"></dic>
                                                </a>
                                            </li>
                                        </ul>
                                        <div class="tab-content">
                                            <div class="tab-pane tabs-spare active">
                                                <div class="row" style="margin: 0">
                                                    <section class="col col-1">
                                                        <label class="control-label" style="margin-top: 10px;">
                                                            <dic data-dic="product.standard.list.styType">
                                                                类型
                                                            </dic>
                                                        </label>
                                                    </section>
                                                    <section class="col col-2 "
                                                             style="padding-left: 0;">
                                                        <label class="input">
                                                            <input type="text"
                                                                   class="search_type"
                                                                   placeholder="">
                                                        </label>
                                                    </section>
                                                    <section class="col col-1">
                                                        <label class="control-label" style="margin-top: 10px;">
                                                            <dic data-dic="product.spare.list.description">
                                                                描述
                                                            </dic>
                                                        </label>
                                                    </section>
                                                    <section class="col col-2 "
                                                             style="padding-left: 0;">
                                                        <label class="input">
                                                            <input type="text"
                                                                   class="search_description"
                                                                   placeholder="">
                                                        </label>
                                                    </section>
                                                    <section class="col col-1">
                                                        <label class="control-label" style="margin-top: 10px;">
                                                            <dic data-dic="product.spare.list.brand">
                                                                品牌
                                                            </dic>
                                                        </label>
                                                    </section>
                                                    <section class="col col-2 "
                                                             style="padding-left: 0;">
                                                        <label class="input">
                                                            <input type="text"
                                                                   class="search_brand"
                                                                   placeholder="">
                                                        </label>
                                                    </section>
                                                    <section class="col col-1"
                                                             style="margin-top: 4px;padding-left: 0;">
                                                        <a class="btn btn-primary btn-sm
                                                        btn_search spare_spare"
                                                           href="javascript:void(0);">
                                                            <dic data-dic="header.list.search">搜索</dic>
                                                        </a>
                                                    </section>
                                                    <section class="col col-1"
                                                             style="margin-top: 4px;padding-left: 0;">
                                                        <a class="btn btn-primary btn-sm btn_rest spare_spare"
                                                           href="javascript:void(0);">
                                                            <dic data-dic="button.reset">重置</dic>
                                                        </a>
                                                    </section>
                                                </div>
                                                <div class="row show-grid" style="max-height:395px;">
                                                    <div class="col-xs-6">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                            <tr>
                                                                <th width="30px">
                                                                </th>
                                                                <th width="20%">
                                                                    <dic data-dic="product.standard.list.styType"></dic>
                                                                </th>
                                                                <th width="">
                                                                    <dic data-dic="customer.item.description"></dic>
                                                                </th>
                                                                <th width="10%">
                                                                    <dic data-dic="product.spare.list.brand"></dic>
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                        <table class="table table-bordered
                                                        choose_spare_table tabs-spare2">
                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                            <tr>
                                                                <th width="30px">
                                                                </th>
                                                                <th width="20%">
                                                                    <dic data-dic="product.standard.list.styType"></dic>
                                                                </th>
                                                                <th width="">
                                                                    <dic data-dic="customer.item.description"></dic>
                                                                </th>
                                                                <th width="10%">
                                                                    <dic data-dic="product.spare.list.brand"></dic>
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                        <table class="table table-bordered
                                                        choose_spare_table tabs-spare1">
                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane tabs-choose">
                                                <div class="row" style="margin: 0">
                                                    <section class="col col-1">
                                                        <label class="control-label" style="margin-top: 10px;">
                                                            <dic data-dic="product.standard.list.styType">
                                                                类型
                                                            </dic>
                                                        </label>
                                                    </section>
                                                    <section class="col col-2 "
                                                             style="padding-left: 0;">
                                                        <label class="input">
                                                            <input type="text"
                                                                   class="search_type"
                                                                   placeholder="">
                                                        </label>
                                                    </section>
                                                    <section class="col col-1">
                                                        <label class="control-label" style="margin-top: 10px;">
                                                            <dic data-dic="product.spare.list.description">
                                                                描述
                                                            </dic>
                                                        </label>
                                                    </section>
                                                    <section class="col col-2 "
                                                             style="padding-left: 0;">
                                                        <label class="input">
                                                            <input type="text"
                                                                   class="search_description"
                                                                   placeholder="">
                                                        </label>
                                                    </section>
                                                    <section class="col col-1">
                                                        <label class="control-label" style="margin-top: 10px;">
                                                            <dic data-dic="product.spare.list.brand">
                                                                品牌
                                                            </dic>
                                                        </label>
                                                    </section>
                                                    <section class="col col-2 "
                                                             style="padding-left: 0;">
                                                        <label class="input">
                                                            <input type="text"
                                                                   class="search_brand"
                                                                   placeholder="">
                                                        </label>
                                                    </section>
                                                    <section class="col col-1"
                                                             style="margin-top: 4px;padding-left: 0;">
                                                        <a class="btn btn-primary btn-sm
                                                        spare_choose btn_search"
                                                           href="javascript:void(0);">
                                                            <dic data-dic="header.list.search">搜索</dic>
                                                        </a>
                                                    </section>
                                                    <section class="col col-1"
                                                             style="margin-top: 4px;padding-left: 0;">
                                                        <a class="btn btn-primary btn-sm btn_rest spare_choose"
                                                           href="javascript:void(0);">
                                                            <dic data-dic="button.reset">重置</dic>
                                                        </a>
                                                    </section>
                                                </div>
                                                <div class="row show-grid" style="overflow: auto;">
                                                    <div class="col-xs-6">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                            <tr>
                                                                <th width="30px">
                                                                </th>
                                                                <th width="20%">
                                                                    <dic data-dic="product.standard.list.styType"></dic>
                                                                </th>
                                                                <th width="">
                                                                    <dic data-dic="customer.item.description"></dic>
                                                                </th>
                                                                <th width="10%">
                                                                    <dic data-dic="product.spare.list.brand"></dic>
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                        <table class="table table-bordered
                                                        choose_spare_table tabs-choose1">
                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                            <tr>
                                                                <th width="30px">
                                                                </th>
                                                                <th width="20%">
                                                                    <dic data-dic="product.standard.list.styType"></dic>
                                                                </th>
                                                                <th width="">
                                                                    <dic data-dic="customer.item.description"></dic>
                                                                </th>
                                                                <th width="10%">
                                                                    <dic data-dic="product.spare.list.brand"></dic>
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                        <table class="table table-bordered
                                                        choose_spare_table tabs-choose2">
                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane tabs-free">
                                                <div class="row" style="margin: 0">
                                                    <section class="col col-1">
                                                        <label class="control-label" style="margin-top: 10px;">
                                                            <dic data-dic="product.standard.list.styType">
                                                                类型
                                                            </dic>
                                                        </label>
                                                    </section>
                                                    <section class="col col-2 "
                                                             style="padding-left: 0;">
                                                        <label class="input">
                                                            <input type="text"
                                                                   class="search_type"
                                                                   placeholder="">
                                                        </label>
                                                    </section>
                                                    <section class="col col-1">
                                                        <label class="control-label" style="margin-top: 10px;">
                                                            <dic data-dic="product.spare.list.description">
                                                                描述
                                                            </dic>
                                                        </label>
                                                    </section>
                                                    <section class="col col-2 "
                                                             style="padding-left: 0;">
                                                        <label class="input">
                                                            <input type="text"
                                                                   class="search_description"
                                                                   placeholder="">
                                                        </label>
                                                    </section>
                                                    <section class="col col-1">
                                                        <label class="control-label" style="margin-top: 10px;">
                                                            <dic data-dic="product.spare.list.brand">
                                                                品牌
                                                            </dic>
                                                        </label>
                                                    </section>
                                                    <section class="col col-2 "
                                                             style="padding-left: 0;">
                                                        <label class="input">
                                                            <input type="text"
                                                                   class="search_brand"
                                                                   placeholder="">
                                                        </label>
                                                    </section>
                                                    <section class="col col-1"
                                                             style="margin-top: 4px;padding-left: 0;">
                                                        <a class="btn btn-primary btn-sm
                                                        spare_free btn_search"
                                                           href="javascript:void(0);">
                                                            <dic data-dic="header.list.search">搜索</dic>
                                                        </a>
                                                    </section>
                                                    <section class="col col-1"
                                                             style="margin-top: 4px;padding-left: 0;">
                                                        <a class="btn btn-primary btn-sm btn_rest spare_free"
                                                           href="javascript:void(0);">
                                                            <dic data-dic="button.reset">重置</dic>
                                                        </a>
                                                    </section>
                                                </div>
                                                <div class="row show-grid" style="max-height:580px;overflow: auto;">
                                                    <div class="col-xs-6">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                            <tr>
                                                                <th width="30px">
                                                                </th>
                                                                <th width="20%">
                                                                    <dic data-dic="product.standard.list.styType"></dic>
                                                                </th>
                                                                <th width="">
                                                                    <dic data-dic="customer.item.description"></dic>
                                                                </th>
                                                                <th width="10%">
                                                                    <dic data-dic="product.spare.list.brand"></dic>
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                        <table class="table table-bordered
                                                        choose_spare_table tabs-free1">
                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                            <tr>
                                                                <th width="30px">
                                                                </th>
                                                                <th width="20%">
                                                                    <dic data-dic="product.standard.list.styType"></dic>
                                                                </th>
                                                                <th width="">
                                                                    <dic data-dic="customer.item.description"></dic>
                                                                </th>
                                                                <th width="10%">
                                                                    <dic data-dic="product.spare.list.brand"></dic>
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                        <table class="table table-bordered
                                                        choose_spare_table tabs-free2">
                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <footer>
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <dic data-dic="button.cancel"></dic>
                        </button>
                        <button type="button" id="choose_spares_sure" class="btn btn-primary">
                            <dic data-dic="offer.create.choose">
                        </button>
                    </footer>
                </form>
            </div>
        </div>
    </div>
</div>

<#--添加屏体的tab-->
<script type="text/template" id="product-content-template">
    <div class="tab-pane fade in" id="{{= tag}}">
        <div class="row" style="border-bottom: solid 1px gainsboro">
            <label class="col col-md-1 text-align-right padding7">
                {{= product}}
            </label>
            <label class="col col-md-2">
                <select class="select2" name="product" style="width: 100%" id="select-series">
                    <option value=""></option>
                    <#list (seriesList)! as item><optgroup label="${(item.text)!}"><#list item.children as child><option value="${(child.id)}">${(child.text)!}</option></#list></optgroup></#list>
                </select>
            </label>
            <label class="col col-md-1 text-align-right padding7">
                {{= configuration}}
            </label>
            <label class="col col-md-5">
                <input name="panel" style="width: 100%" disabled />
            </label>
        </div>
        <fieldset style="margin: 10px -12px 0 -12px;border-bottom: solid 1px gainsboro">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div class="row">
                    <label class="col col-md-1 padding7 text-align-right">
                        {{= panelCount}}
                    </label>
                    <label class="col col-md-2">
                        <input type="text" tag="qty-width" value="0" placeholder="宽"/>
                    </label>
                    <label class="col col-md-1 text-align-center" style="font-size: 16pt;">
                        ×
                    </label>
                    <label class="col col-md-2">
                        <input type="text" tag="qty-height" value="0" placeholder="高"/>
                    </label>
                    <label class="col col-md-1 padding7 text-align-right">
                        <span role="total">0</span>pcs
                    </label>
                </div>
                <div class="row">
                    <label class="col col-md-1 padding7 text-align-right">
                        {{= panelArea}}
                    </label>
                    <label class="col col-md-2">
                        <input type="text" tag="seq-width" value="0" placeholder="宽"/>
                    </label>
                    <label class="col col-md-1 text-align-center" style="font-size: 16pt;">
                        ×
                    </label>
                    <label class="col col-md-2">
                        <input type="text" tag="seq-height" value="0" placeholder="高"/>
                    </label>
                    <label class="col col-md-1 padding7 text-align-right">
                        <span role="total">0</span>
                    </label>
                    <label class="col col-md-1 text-align-center" style="font-size: 16pt;">
                        ×
                    </label>
                    <label class="col col-md-1">
                        <input type="text" tag="quantity" value="1"/>
                    </label>
                    <label class="col col-md-1 padding7" role="total-area">
                        0
                    </label>
                    <label class="col col-md-2 no-left no-right padding7 text-align-center" style="font-weight: 700;">
                        {{= panelTotal}}：
                        <span class="moneyUnit"></span><span id="price-{{= tag}}">0.00</span>
                    </label>
                </div>
            </div>
        </fieldset>
        <div style="margin-top: 10px" class="jarviswidget well transparent" id="wid-id-9" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false" role="widget">
            <!-- widget options:
            usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

            data-widget-colorbutton="false"
            data-widget-editbutton="false"
            data-widget-togglebutton="false"
            data-widget-deletebutton="false"
            data-widget-fullscreenbutton="false"
            data-widget-custombutton="false"
            data-widget-collapsed="true"
            data-widget-sortable="false"

            -->
            <header role="heading">
                <span class="widget-icon"> <i class="fa fa-comments"></i> </span>
                <h2>Accordions </h2>

                <span class="jarviswidget-loader"><i class="fa fa-refresh fa-spin"></i></span></header>

            <!-- widget div-->
            <div role="content">
                <!-- widget content -->
                <div class="widget-body" style="min-height: 0 !important;">
                    <div class="panel-group smart-accordion-default" id="accordion-{{= tag}}">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion-{{= tag}}" href="#collapseOne-{{= tag}}" aria-expanded="false" class="collapsed">
                                        <i class="fa fa-lg fa-angle-down pull-right"></i>
                                        <i class="fa fa-lg fa-angle-up pull-right"></i>
                                        <span><i class="fa fa-lg fa-cog"></i></span>
                                        {{= params}}
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseOne-{{= tag}}" class="panel-collapse collapse" aria-expanded="false">
                                <div class="panel-body no-padding">
                                    <div class="tab-content padding-10" id="param-{{= panelTag}}-dialog-1">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end widget content -->
            </div>
            <!-- end widget div -->
        </div>
    </div>
</script>

<script type="text/template" id="product-header-template">
    <li>
        <a data-toggle="tab" href="{{= tag}}">
            <span class="hidden-mobile hidden-tablet">{{= name}}<span role="index">{{= index}}</span></span>
            <div class="drop-panel"><i class="fa fa-trash-o"></i></div>
        </a>
    </li>
</script>

<script type="text/template" id="params-header-template">
    <li>
        <a data-toggle="tab" href="{{= tag}}">
            <span class="hidden-mobile hidden-tablet">{{= name}}<span role="index">{{= index}}</span></span>
        </a>
    </li>
</script>

<script id="standard-template" type="text/template">
    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
        <fieldset role="spares" style="border-right: 1px dashed #ccc;">
            <label class="col col-3 text-align-left padding7"></label>
            <label class="col col-3 text-align-left padding7"></label>
            <label class="col col-1 long-label">
                <input amount class="form-control" type="text" value="0"/>
            </label>
            <label class="col col-1 padding7 short-label"></label>
            <label class="col col-1 long-label margin-left-10">
                <input sales class="form-control" type="text" value="0"/>
            </label>
            <label class="col col-2  text-align-right padding7"><span class="moneyUnit"></span><span price>0.00</span></label>
            <label class="trash">
                <a class="trash-a"><i class="fa fa-trash-o"></i></a>
            </label>
        </fieldset>
    </div>
</script>

<script id="service-template" type="text/template">
    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
        <fieldset role="service" style="border-right: 1px dashed #ccc;">
            <label class="col col-4">
                <input class="form-control" placeholder="" type="text" value=""/>
            </label>
            <label class="col col-3">
                <input amount class="form-control" placeholder="" type="text" value="0"/>
            </label>
            <label class="col col-2">
                <input sales class="form-control" placeholder="" type="text" value="0"/>
            </label>
            <label class="col col-2 text-align-right padding7"><span class="moneyUnit"></span><span price>0.00</span></label>
            <label class="trash col col-1">
                <a class="trash-a"><i class="fa fa-trash-o"></i></a>
            </label>
        </fieldset>
    </div>
</script>

<script id="package-template" type="text/template">
    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
        <fieldset role="package" style="border-right: 1px dashed #ccc;">
            <label class="col col-4" style="position: inherit;">
                <input type="text" class="form-control packages" packages />
            </label>
            <label class="col col-3">
                <input amount class="form-control" placeholder="" type="text" value="0"/>
            </label>
            <label class="col col-2">
                <input sales class="form-control" placeholder="" type="text" value="0"/>
            </label>
        <#--<input sales class="form-control" placeholder="" type="text" value="0"/>-->
            <label class="col col-2 text-align-center padding7"><span class="moneyUnit"></span><span price>0.00</span></label>
            <label class="trash-pack col col-1">
                <a class="trash-a"><i class="fa fa-trash-o"></i></a>
            </label>
        </fieldset>
    </div>
</script>

<script id="self-spare-template" type="text/template">
    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
        <fieldset role="self" style="border-right: 1px dashed #ccc;">
            <label class="col col-3 text-align-left">
                <input type="text" class="form-control" maxlength="50"/>
            </label>
            <label class="col col-3 text-align-left">
                <input type="text" class="form-control" maxlength="100"/>
            </label>
            <label class="col col-1 long-label">
                <input amount class="form-control" type="text" value="0"/>
            </label>
            <label class="col col-1 short-label">
                <input type="text" class="form-control"/>
            </label>
            <label class="col col-1 long-label margin-left-10">
                <input sales type="text" value="0"/>
            </label>
            <label class="col col-2 text-align-right padding7"><span class="moneyUnit"></span><span price="">0.00</span></label>
            <label class="trash">
                <a class="trash-a"><i class="fa fa-trash-o"></i></a>
            </label>
        </fieldset>
    </div>
</script>

<script id="self-spare-tr" type="text/template">
    <tr class="spare_item">
        <td width="30px">
            <label class="checkbox">
                <input type="checkbox" class="r">
                <i></i>
            </label>
        </td>
        <td width="20%" title="{{= type}}">{{= type}}</td>
        <td width="" class="font-diy" title="{{= description}}">{{= description}}</td>
        <td width="10%" title="{{= brand}}">{{= brand}}</td>
    </tr>
</script>

<script type="text/javascript" src="../../static/js/plugin/bootstrap-autosuggest/autosuggest.js"></script>
<script>
    $('[data-dic]').each(function () {
        $(this).html(message[$(this).data("dic")]);
    });
    pageSetUp();
    //初始化货币单位的方法
    function initMoneyType() {
        var unit = $('#money-type option:selected').data('key');
        $('.moneyUnit').each(function () {
            $(this).html(unit);
        });
    }
    // 交期数字框
    $("#valid-date").spinner({
        step: 1,
        min: 1,
        max: 999,
        change: function () {
            if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
            }else {
                $(this).val(0);
            }
        }
    });
    //报价区域加载
    if ($("#price_area").select2("val")) {
        loadUnit($("#price_area").select2("val"));
    }
    $("#price_area").change(function () {
        loadUnit($("#price_area").select2("val"));
    });

    /*客户选择*/
    $("#spare-choose_customer").off("click").click(function (e) {
        e.preventDefault();
        var checkboxs = $("#choose_spare_dialog_customer table input:checkbox:checked");
        if (!checkboxs || checkboxs.length == 0) {
            alert(message["offer.create.customer"], 2);
            return false;
        }
        checkboxs.each(function () {
            var tr = $(this).parents("tr:first");
            var data = {
                name: $(tr).data("name"),
                id: $(tr).data("id")
            };
            $("#select_customer").val(data.name);
            $("#select_customer").data("id", data.id);
        });

        $("#dialog_table_customer").modal("hide");
        $("#choose_spare_dialog_customer").html("");
    });

    function addNewCustomer() {
        var name = $("#name").val();
        var type = $("#type").val();
        var location = $("#location").val();
        var phone = $("#phone").val();

        showLoading();
        $.ajax({
            url: "/customer/customer/list",
            data: {
                name: name,
                type: type,
                location: location,
                phone: phone
            },
            type: "get",
            success: function (data) {
                $("#choose_spare_dialog_customer").html(data);
                $("#dialog_table_customer").modal("show");
                hideLoading();
            }
        });
    }

    function loadUnit(area) {
        $.ajax({
            url: "/offer/area/unit",
            type: "get",
            data: {area: area},
            success: function (data) {
                if (data) {
                    $("#money-type").select2("val", data);
                    //成功后加载单位
                    initMoneyType();
                }
            }
        });
    }

    var evt = function () {
        return {
            initForm: function () {
                //客户
                $('#select_customer').autosuggest({
                    url: '/api/customer/customer/autosuggest',
                    queryParamName: 'name',
                    minLength: 1,
                    onSelect: function (option) {
                        $('#select_customer').data('id', $(option).data('id'));
                    }
                });
                $.ajax({
                    url: '/api/dict/find/category',
                    data: {category: 'payment_mode'},
                    type: "GET",
                    success: function (res) {
                        //付款方式'
                        console.log(res);
                        $('#select_payment').data('list', res);
                        $('#select_payment').autosuggest({
                            dataList: res,
                            local: true,
                            minLength: 1
                        });
                    }
                });
            },
            /**
             * 数组处理成map（给下拉款使用）
             */
            list2map: function (list) {
                var map = {};
                list.forEach(function (item) {
                    map[item.id] = item.value;
                });
                return map;
            }
        }
    }();
    evt.initForm();
</script>
<script type="text/javascript">
    var lengths = $("[name='radio-inline']:radio:checked").val();
    var lengthType = (lengths == 1)? 1 : 0.3048;//用于换算米和英尺
    var squareType = (lengths == 1)? 1 : 0.092903;//
    $(document).ready(function () {
        var length = $("#panel-list-header").find(".nav.nav-tabs li").length;
        for (var i = 0; i < length; i++) {
            addPanel('${tag}' + i);
            //加载屏体品牌
            $("#screen-brand-" + '${tag}' + i).html("Absen-" + $("#panel-"+'${tag}' + i).find('[name="product"]').select2('data').text);
        }
        initSpinner();
    });

    $("#back_button").off("click").click(function () {
        $("#second").html('');
        $("#second").hide();
        $("#first").show();
    });

    //每次选择尺寸，则切换尺寸单位
    $("[name='radio-inline']").change(function () {
        lengths = $("[name='radio-inline']:radio:checked").val();
        lengthType = (lengths == 1)? 1 : 0.3048;//用于换算米和英尺
        squareType = (lengths == 1)? 1 : 0.092903;//
    });

    //编辑页面时初始化spinner控件
    function initSpinner() {
        $("#panel-standard-dialog-1").find("input[amount],input[sales]").not(".acreage").spinner({
            step:1,
            min:1,
            max:100000,
            change:function(e){
                var fieldSet=$(e.target).parents("fieldset");
                if($(this).val() && isNum($(this).val()) && $(this).val() > 0){
                    calcSingleSparePrice(fieldSet);
                }else{
                    $(this).val(0);
                    calcSingleSparePrice(fieldSet);
                }
            }
        });
        $("#panel-standard-dialog-2").find("input[amount],input[sales]").spinner({
            step:1,
            min:1,
            max:100000,
            change:function(e){
                var fieldSet=$(e.target).parents("fieldset");
                if($(this).val() && isNum($(this).val()) && $(this).val() > 0){
                    calcSingleSparePrice(fieldSet);
                }else{
                    $(this).val(0);
                    calcSingleSparePrice(fieldSet);
                }
            }
        });
        $("#panel-standard-dialog-3").find("input[amount],input[sales]").spinner({
            step:1,
            min:1,
            max:100000,
            change:function(e){
                var fieldSet=$(e.target).parents("fieldset");
                if($(this).val() && isNum($(this).val()) && $(this).val() > 0){
                    calcSingleSparePrice(fieldSet);
                }else{
                    $(this).val(0);
                    calcSingleSparePrice(fieldSet);
                }
            }
        });
        $("#service-panel").find("input[amount],input[sales]").spinner({
            step:1,
            min:1,
            max:100000,
            change:function(e){
                var fieldSet=$(e.target).parents("fieldset");
                if($(this).val() && isNum($(this).val()) && $(this),val() > 0){
                    calcSingleSparePrice(fieldSet);
                }else{
                    $(this).val(0);
                    calcSingleSparePrice(fieldSet);
                }
            }
        });
        $("#package-form").find("input[amount],input[sales]").spinner({
            step:1,
            min:1,
            max:100000,
            change:function(e){
                var fieldSet=$(e.target).parents("fieldset");
                if($(this).val() && isNum($(this).val()) && $(this).val() > 0){
                    calcTransport(fieldSet);
                }else{
                    $(this).val(0);
                    calcTransport(fieldSet);
                }
            }
        });
    }

    //初始化屏体select
    function initPanelSelect(value, panel) {
        var $panel = panel.find("[name='panel']");
        if(!value || value.length===0){
            $panel.select2({
                data:[],
                placeholder: message['select2.placeholder.msg']
            });
            $panel.prop("disabled",true);
            return;
        }
        var values = [];
        $.ajax({
            url:'/offer/product/get',
            type:'get',
            data: {id: value},
            success: function (data) {
                $.each(data, function (index, item) {
                    values.push({
                        text: item.state,
                        id: item.id
                    });
                });
                $panel.select2({
                    data:values,
                    placeholder: message['select2.placeholder.msg'],
                    minimumResultsForSearch:-1
                });
                $panel.select2("val", panel.find("[name='productId']").val());
                $panel.removeAttr("disabled");
            },error: function (data) {

            }
        });
    }

    //添加新屏体的方法
    function appendNewPanel() {
        var length = $("#panel-list-header li").length + 1;
        if (length >= 6) {
            return;
        }

        var tag = (new Date()).valueOf();
        // var certificationDefault = $('#stepshow_cert').html();
        var panelTemp = {
            tag: 'panel-' + tag,
            panelTag: tag,
            panelTotal: message["offer.create.panelTotal"],
            scnNo: message["panel.list.search.scn"],
            panelCount: message["offer.create.panelNumber"],
            total: message["offer.create.totalCount"],
            price: message["offer.create.priceUnit"],
            priceCount: message["offer.create.priceCount"],
            panelArea: message["offer.create.panelArea"],
            totalArea: message["offer.create.totalArea"],
            discount: message["offer.create.discount"],
            realPrice: message["offer.create.realPrice"],
            certification: message['offer.create.certification'],
            // certificationDefault: certificationDefault,
            state: message['product.panel.list.state'],
            empty: message['product.panel.no'],
            reference: message['offer.create.reference'],
            quantity: message['offer.create.screenQuantity'],
            priceLevel: message['offer.create.priceLevel'],
            product: message['offer.create.product'],
            configuration: message['product.panel.list.configuration'],
            params: message['offer.create.params']
        };
        $("#product-content-template").tmpl(panelTemp).appendTo($("#panel-list-content"));

        var panelHeader = {name: message["offer.create.panel"], tag: "panel-" + tag, index: length};
        $("#product-header-template").tmpl(panelHeader).appendTo($("#panel-list-header ul:first"));

        var paramHeader = {name: message["offer.create.params"], tag: "param-" + tag + "-1", index: length};
        $("#params-header-template").tmpl(paramHeader).appendTo($("#panel-param-1 ul:first"));

        var modualHeader = {name: message["offer.create.standard"], tag: "spare-" + tag + "-1", index: length};
        $("#params-header-template").tmpl(modualHeader).appendTo($("#panel-standard-1 ul:first"));

        var modualHeader = {name: message["offer.create.spare"], tag: "spare-" + tag + "-2", index: length};
        $("#params-header-template").tmpl(modualHeader).appendTo($("#panel-standard-2 ul:first"));

        var modualHeader = {name: message["offer.create.free"], tag: "spare-" + tag + "-3", index: length};
        $("#params-header-template").tmpl(modualHeader).appendTo($("#panel-standard-3 ul:first"));

        addPanel(tag);
        reBuildPanelIndex();
        initMoneyType();
    }

    //重新刷新屏体以及
    function refreshPanel(panel) {
        var panelId = panel.find('[name="panel"]').select2('val');
        if (panelId) {
            panel.find("[name='panel']").change();
        }else {
            initMoneyType();
        }
    }

    //添加屏体的函数
    function addPanel(id) {
        var panel = $("#panel-" + id);
        panel.find("input[type='text'][tag]").spinner({
            step: 1,
            min: 1,
            max: 100000,
            change: function () {
                if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                    reCalcSeq(panel);
                } else {
                    $(this).val(0);
                    reCalcSeq(panel);
                }
            }
        });

        // 让对话框的标题支持html
        $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
            _title: function(title) {
                if (!this.options.title ) {
                    title.html("&#160;");
                } else {
                    title.html(this.options.title);
                }
            }
        }));

        // 价格水平相关 start
        $(".jarviswidget").on("click", ".edit-a", function () {
            var $dialog = $("#dialog-message");
            $dialog.find('[tag="sale-price"]').val($(this).parents("fieldset").find("[sales]").val());
            $dialog.find('[tag="panel-discount"]').val(100);
            $dialog.find('[tag="discounted-price"]').val($(this).parents("fieldset").find("[sales]").val());
            $dialog.dialog('open');
            $dialog.find('[tag="sale-price"]').spinner({
                step: 1, min: 0, max: 1000000,
                change: function () {
                    var self = $(this);
                    if (self.val() && isNum(self.val()) && self.val() > 0) {
                        var discount = $dialog.find('[tag="panel-discount"]').val() || 0;
                        $dialog.find('[tag="panel-discount"]').val(discount);
                        $dialog.find('[tag="discounted-price"]').val(toDecimal(parseFloat(self.val())*discount/100));
                    } else {
                        self.val(0);
                        $dialog.find('[tag="discounted-price"]').val(0);
                    }
                }
            });
            $dialog.find('[tag="panel-discount"]').spinner({
                step: 1, min: 0, max: 1000000,
                change: function () {
                    var self = $(this);
                    if (self.val() && isNum(self.val()) && self.val() > 0) {
                        var selfNum = self.val();
                        var salePrice = parseFloat($dialog.find('[tag="sale-price"]').val()) || 0;
                        $dialog.find('[tag="discounted-price"]').val(toDecimal(salePrice*selfNum/100));
                    } else {
                        self.val(0);
                        $dialog.find('[tag="discounted-price"]').val(0);
                    }
                }
            });
            $dialog.find('[tag="discounted-price"]').spinner({
                step: 1, min: 0, max: 1000000,
                change: function () {
                    var self = $(this);
                    if (self.val() && isNum(self.val()) && self.val() > 0) {
                        var salePrice = parseFloat($dialog.find('[tag="sale-price"]').val()) || 0;
                        var discount = parseFloat(toDouble(self.val()/salePrice*100));
                        $dialog.find('[tag="panel-discount"]').val(discount);
                        $dialog.find('[tag="discounted-price"]').val(toDecimal(salePrice*discount/100));
                    } else {
                        self.val(0);
                        $dialog.find('[tag="discounted-price"]').val(0);
                    }
                }
            });
            return false;
        });

        $("#dialog-message").dialog({
            autoOpen : false,
            width : 600,
            resizable : false,
            modal : true,
            title : "<div class='widget-header'><h4>编辑价格</h4></div>",
            buttons : [{
                html : "<i class='fa fa-check'></i>&nbsp; OK",
                "class" : "btn btn-primary",
                click : function() {
                    var realPrice = $("#dialog-message").find('[tag="discounted-price"]').val();
                    $("#panel-standard-dialog-1").find(".tab-pane.fade.in.active fieldset").eq(0).find("[sales]").val(realPrice);
                    var fieldSet = $("#panel-standard-dialog-1").find(".tab-pane.fade.in.active fieldset").eq(0).find("[sales]").parents("fieldset");
                    calcSingleSparePrice(fieldSet);
                    $(this).dialog("close");
                }
            }, {
                html : "Cancel",
                "class" : "btn btn-default",
                click : function() {
                    $(this).dialog("close");
                }
            }]
        });
        $("#reset-price").click(function () {
            var $dialog = $("#dialog-message");
            var price = parseFloat($("#spare-" + id + "-1 fieldset").eq(0).find("[sales]").val());
            $dialog.find('[tag="sale-price"]').val(price);
            $dialog.find('[tag="panel-discount"]').val(100);
            $dialog.find('[tag="discounted-price"]').val(price);
        });
        //价格水平 end

        //start
        // 下列方法用于下拉框刷新
        $("#money-type").change(function () {
            refreshPanel(panel);
        });
        $("#price_area").change(function () {
            refreshPanel(panel);
        });
        $('[name="radio-inline"]').change(function () {
            refreshPanel(panel);
        });
        $('select[name="guarantee"]').change(function () {
            refreshPanel(panel);
        });
        //end

        //产品与物料号两个下拉菜单二级联动
        panel.find("[name='panel']").select2({data:[],minimumResultsForSearch:-1,placeholder: message['offer.create.choosePanel']});
        var v = panel.find("[name='product']").select2("val");
        initPanelSelect(v, panel);
        panel.find("[name='product']").select2().change(function () {
            var value = $(this).select2("val");
            initPanelSelect(value, panel);
        });

        //屏体物料选择事件
        panel.find("[name='panel']").change(function () {
            var self = this;
            var value = $(this).select2("val");
            if (value) {
                showLoading();
                $.ajax({
                    url: "/offer/create/panel/params",
                    type: "get",
                    data: {
                        product: value,
                        tag: id,
                        series: panel.find($("[name='product']")).select2("val"),
                        money: $("#money-type").select2('val'),
                        requestArea: $("#price_area").select2("val")
                    },
                    success: function (html) {
                        //判断是否已经选择过屏体物料
                        if ($("#param-" + id + "-1").length > 0) {
                            $("#param-" + id + "-1").html($(html).find("div.tab-pane:eq(0)").html());
                            $("#spare-" + id + "-1").html($(html).find("div.tab-pane:eq(1)").html());
                            $("#spare-" + id + "-2").html($(html).find("div.tab-pane:eq(2)").html());
                            $("#spare-" + id + "-3").html($(html).find("div.tab-pane:eq(3)").html());
                        } else {
                            $(html).find("div.tab-pane:eq(0)").appendTo($("#param-" + id + "-dialog-1"));
                            $(html).find("div.tab-pane:eq(1)").appendTo($("#panel-standard-dialog-1"));
                            $(html).find("div.tab-pane:eq(2)").appendTo($("#panel-standard-dialog-2"));
                            $(html).find("div.tab-pane:eq(3)").appendTo($("#panel-standard-dialog-3"));
                        }
                        var js = $(html).find("#param-" + id + "-js").html();
                        eval(js);
                        initPanelSpare(panel);
//重新计算价格
                        reCalcSeq(panel);
                        hideLoading();
                        initMoneyType();
                        //成功后加载所有备件的spinner控件
                        $(".row.spare-tag input").spinner({
                            step: 1,
                            min: 1,
                            max: 100,
                            change: function () {
                                var div = $(this).parents(".row.total-tag:first");
                                var sale = parseFloat($(div).find("[tag='total-sale']").html()) || 0;
                                var discount;
                                if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                                    discount = parseFloat($(this).val()) || 100;
                                    $(div).find("[tag='total-real']").html((sale * discount / 100).toFixed(2));
                                    calcPanelTotalPrice(id);
                                    calcTotalPrice();
                                } else {
                                    $(this).val(100);
                                    discount = parseFloat($(this).val()) || 100;
                                    $(div).find("[tag='total-real']").html((sale * discount / 100).toFixed(2));
                                    calcPanelTotalPrice(id);
                                    calcTotalPrice();
                                }
                            }
                        });
                    },
                    error: function (err) {
                        console.log(err);
                        hideLoading();
                        alert(message["http.response.error"], 3);
                    }
                });
                $.each($(this).data('panelInfo'),function () {
                    if(Number(value) === Number(this.id)){
                        $(self).data('panelItemInfo',this);
                    }
                });
            }
        });

        //箱体数量change计算事件
        panel.find("input[tag='qty-height'],input[tag='qty-width']").change(function () {
            reCalcSeq(panel);
        });

        //屏体面积change计算事件
        panel.find("input[tag='seq-height'],input[tag='seq-width']").change(function () {
            if ($(this).val() > 0) {
                calcQty(panel);
            }else {
                $(this).val(0);
                calcQty(panel);
            }
        });

        //屏体数量change计算事件
        panel.find("input[tag='quantity']").change(function () {
            var seqWidth = $(panel).find("input[tag='seq-width']").val() || 0;
            var seqHeight = $(panel).find("input[tag='seq-height']").val() || 0;

            //获取屏体数量
            var quantity = $(panel).find("[tag='quantity']").val() || 0;

            $(panel).find("span[role='total']:last").html((seqHeight * seqWidth / squareType).toFixed(4));
            $(panel).find("span[role='total']:last").append($("[name='radio-inline']:radio:checked").val() == 1 ? "㎡" : "sq.ft");
            var panelTag = $(panel).attr("id").replace("panel-", "");

            var seqArea = (seqHeight * seqWidth / squareType).toFixed(4);
            var sizeType = $("[name='radio-inline']:radio:checked").val() == 1 ? "㎡" : "sq.ft";
            var html = '<input type="text" readonly class="acreage" amount value="'+ (seqArea*quantity).toFixed(4) + sizeType +'"/>';
            $(panel).find('[role="total-area"]').html(html);
            //标配中屏体面积框赋值
            $("#spare-" + panelTag + "-1").find('[tag="total-area"]').html(html);
            reCalcEveryCountsAndPrice(panelTag);
            // lowTotalPrice();
        });
    }

    //添加备件
    function addNewSpare(type) {
        var spare_id = $("#panel-standard-" + type).find("li.active a:first").attr("href");
        var tag = spare_id.split("-")[1];
        var panel = $("#panel-" + tag);
        var product = $(panel).find("[name='panel']").select2("val");
        if (!product) {
            alert(message["quote.empty.product"], 2);
            return;
        }
        showLoading();
        $.ajax({
            url: "/offer/spares",
            data: {
                product: product,
                type: type,
                moneyType: $("#money-type").select2('val'),
                area: $("#price_area").select2("val"),
                series:  $("#panel-" + tag).find('[name=product]').select2("val")
            },
            type: "get",
            success: function (data) {
                $("#choose_spare_dialog").html(data);
                $("#dialog_table").modal("show");
                $("#spare-type").val(spare_id);
                $("#spare-type").data("panel", panel.attr("id"));
                FittingUtil.byTypeChecked(type, $("#choose_spare_dialog"));
                hideLoading();
            }
        });
    }

    //添加自定义备件
    function addNewSelfSpare(type) {
        var form = $("#panel-standard-" + type).find(".tab-pane.active form.self");
        var template = $("#self-spare-template").html();
        var newItem = $(template).clone(true);
        newItem.find("input[amount],input[sales]").spinner({
            step: 1,
            min: 1,
            max: 1000000,
            change: function (e) {
                var fieldSet = $(e.target).parents("fieldset:first");
                if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                    calcSingleSparePrice(fieldSet);
                } else {
                    $(this).val(0);
                    calcSingleSparePrice(fieldSet);
                }
            }
        });
        if (!!$(form) && $(form).length > 0) {
            $(form).append(newItem);
        } else {
            $("#panel-standard-" + type).find(".tab-pane.active .spare").append($("<form class='smart-form self'></form>"));
            $("#panel-standard-" + type).find(".tab-pane.active form.self").append(newItem);
        }
    }

    //添加服务
    function appendService() {
        var template = $("#service-template").html();
        var newItem = $(template).clone(true);

        newItem.find("input[amount],input[sales]").spinner({
            step: 1,
            min: 1,
            max: 100000,
            change: function (e) {
                var fieldSet = $(e.target).parents("fieldset");
                if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                    calcSingleSparePrice(fieldSet);
                } else {
                    $(this).val(0);
                    calcSingleSparePrice(fieldSet);
                }
            }
        });

        $("#service-form").append(newItem);
    }

    //添加包装
    function appendPackage() {
        var packages = [
            {id: 1, value: message["package.type.paper"]},
            {id: 2, value: message["package.type.wold"]},
            {id: 3, value: message["package.type.plane"]}
        ];
        $("#package-template").tmpl({}).appendTo($("#package-form"));
        $("#package-form .packages:last-child").autosuggest({
            dataList: packages,
            local: true,
            minLength: 1
        });

        $("#package-form fieldset:last").find("input[amount],input[sales]").spinner({
            step: 1,
            min: 1,
            max: 100000,
            change: function (e) {
                var fieldSet = $(e.target).parents("fieldset");
                if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                    calcTransport(fieldSet);
                } else {
                    $(this).val(0);
                    calcTransport(fieldSet);
                }
            }
        });
    }

    //初始化所有备件区域spinner控件
    function initPanelSpare(panel) {
        var id = panel.attr("id").replace("panel-", "");

        $("#spare-" + id + "-1,#spare-" + id + "-2,#spare-" + id + "-3").find("input[amount],input[sales]").spinner({
            step: 1,
            min: 1,
            max: 100000,
            change: function () {
                var fieldSet = $(this).parents("fieldset");
                if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                    calcSingleSparePrice(fieldSet);
                } else {
                    $(this).val(0);
                    calcSingleSparePrice(fieldSet);
                }
            }
        });
    }

    /**
     * 计算某一个备件的销售价格，并且重新计算当前的组件集合的全部金额
     */
    function calcSingleSparePrice(fieldSet) {
        calcSingleSparePriceNoTop(fieldSet);
        var widget = $(fieldSet).parents(".tab-pane.fade.active");
        calcKindSparePrice(widget);
    }

    /**
     *  重新计算所有备件数量、价格
     */
    function reCalcEveryCountsAndPrice(panelTag) {
        var widgets = $("#spare-" + panelTag + "-1,#spare-" + panelTag + "-2,#spare-" + panelTag + "-3");
        var guarantee = $("[name='guarantee']").select2("val");
        $.each(widgets, function (index, item) {
            //寻找fieldset，排除screen
            var fieldSets = $(item).find("form.smart-form.temp").find('fieldset').not("#screen-fieldset-" + panelTag);
            $.each(fieldSets, function (index, fied) {
                var type = $(fied).data("type" + guarantee);
                var ct = $(fied).data("count" + guarantee);
                var spel = $(fied).data("spel" + guarantee);
                var input = $(fied).find("input[amount]:first");
                var count = calcCounts(type, ct, spel, "panel-" + panelTag) || 0;
                if (0 != count) {
                    $(input).val(count);
                }
                var price = $(fied).find("input[sales]").val() || 0;
                $(fied).find("[price]").html((price * count).toFixed(2));
            });
            calcKindSparePriceNoTop(item);
        });
        calcPanelTotalPrice(panelTag);
        calcTotalPrice();
    }

    function calcKindSparePrice(widget) {
        calcKindSparePriceNoTop(widget);
        var tag = widget.attr('id').split("-")[1];
        calcPanelTotalPrice(tag);
        calcTotalPrice();
    }

    /**
     * 计算某个备件的销售价格，不重新计算备件集合价格
     */
    function calcSingleSparePriceNoTop(fieldSet) {
        var price = parseFloat($(fieldSet).find("input[sales]").val()) || 0;
        var amount = parseFloat($(fieldSet).find("input[amount]").val()) || 0;
        $(fieldSet).find("span[price]").html(parseFloat(price * amount).toFixed(2));
    }

    /**
     * 计算所有屏体的某一个种类的备件总价，不重新计算总体价格
     */
    function calcKindSparePriceNoTop(widget) {
        var fieldset = $(widget).find("fieldset");
        var totals = 0;
        $.each(fieldset, function (index, item) {
            var amount = parseFloat($(item).find("input[amount]").val()) || 0;
            var sales = parseFloat($(item).find("input[sales]").val()) || 0;
            var total = amount * sales;
            totals += total;
        });

        $(widget).find("span[tag='total-sale']").html(totals.toFixed(2));
        var discount = parseFloat($(widget).find("input[tag='total-discount']").val()) || 100;
        $(widget).find("[tag='total-real']").html((totals * discount / 100).toFixed(2));
    }

    /**
     * 计算屏幕的总价格
     */
    // function calcPanelTotalPrice() {
    //     var totals = 0;
    //     $.each($("[role='price-real']"), function (index, item) {
    //         var price = parseFloat($(this).html()) || 0;
    //         totals += price;
    //     });
    //     var discount = $("#panel-list-header input[tag='total-discount']").val();
    //     $("#panel-list-header span[tag='total-sale']").html(totals.toFixed(2));
    //     $("#panel-list-header span[tag='total-real']").html((totals * discount / 100).toFixed(2));
    //     calcTotalPrice();
    // }

    /**
     * 计算单个屏体价格，不计算整个单子的总价
     */
    function calcPanelTotalPrice(tag) {
        var totals = 0;
        $.each($("#spare-"+ tag + "-1 [tag='total-real'],#spare-" + tag + "-2 [tag='total-real'],#spare-" + tag + "-3 [tag='total-real']"), function (index, item) {
            var value = parseFloat($(item).html()) || 0;
            totals += value;
        });
        $("#price-panel-" + tag).html(totals.toFixed(2));
    }

    /**
     * 计算整个单子的总价格
     */
    function calcTotalPrice() {
        var totals = 0;
        $.each($("[tag='total-real']"), function (index, item) {
            var value = parseFloat($(item).html()) || 0;
            totals += value;
        });
        var discount = $("#total-discount").val() || 100;
        $("#total-price").html(totals.toFixed(2));
        $("#total-real").html(parseFloat(discount * totals / 100).toFixed(2));
    }

    /**
     * 保留四位小数的函数
     * @param x 数值
     */
    function toDecimal(x) {
        var f = parseFloat(x);
        if (isNaN(f)) {
            return 0;
        }
        f = Math.round(x*10000)/10000;
        return f;
    }

    /**
     * 保留两位小数的函数
     * @param x 数值
     */
    function toDouble(x) {
        var f = parseFloat(x);
        if (isNaN(f)) {
            return 0;
        }
        f = Math.round(x*100)/100;
        return f;
    }

    /**
     * 通过屏体的数量，计算对应的宽度、高度、面积、价格、功率
     * @param panel
     */
    function reCalcSeq(panel) {
        var height = parseInt($(panel).find("input[tag='qty-height']").val()) || 0;
        var width = parseInt($(panel).find("input[tag='qty-width']").val()) || 0;
        var total = height * width;
        $(panel).find("span[role='total']:first").html(total);

        var seqHeight = height * $(panel).data("height") || 0;
        var seqWidth = width * $(panel).data("width") || 0;

        //获取屏体数量
        var quantity = $(panel).find("[tag='quantity']").val() || 0;
        $(panel).find("input[tag='seq-width']").val(toDecimal(seqWidth / lengthType));
        $(panel).find("input[tag='seq-height']").val(toDecimal(seqHeight / lengthType));

        $(panel).find("span[role='total']:last").html((seqHeight * seqWidth / squareType).toFixed(4));
        $(panel).find("span[role='total']:last").append($("[name='radio-inline']:radio:checked").val() == 1 ? "㎡" : "sq.ft");
        var panelTag = $(panel).attr("id").replace("panel-", "");

        var seqArea = (seqHeight * seqWidth / squareType).toFixed(4);
        var sizeType = $("[name='radio-inline']:radio:checked").val() == 1 ? "㎡" : "sq.ft";
        var html = '<input type="text" readonly class="acreage" amount value="'+ (seqArea*quantity).toFixed(4) + sizeType +'"/>';
        $(panel).find('[role="total-area"]').html(html);
        //标配中屏体面积框赋值
        $("#spare-" + panelTag + "-1").find('[tag="total-area"]').html(html);
        var fieldset = $("#spare-" + panelTag + "-1").find('[tag="total-area"]').parents('fieldset');
        //重新计算配件中屏体部分价格
        calcSingleSparePriceNoTop(fieldset);

        $.each($("#param-" + panelTag + "-1 [data-ct1]"), function () {
            var ct1;
            var ct2;
            if (width == 0 || height == 0) {
                ct1 = $(this).data("ct1");
                ct2 = $(this).data("ct2");
            } else {
                ct1 = $(this).data("ct1") * width;
                ct2 = $(this).data("ct2") * height;
            }
            $(this).html(ct1 + "*" + ct2);
        });

        $.each($("#param-" + panelTag + "-1 [data-counts]"), function () {
            if (width == 0 || height == 0) {
                $(this).html($(this).data("counts"));
            } else {
                $(this).html($(this).data("counts") * width * height);
            }
        });
        calcPanelTotalPrice(panelTag);
        reCalcEveryCountsAndPrice(panelTag);
    }

    $('#package-panel .packageTrade').select2();

    /**
     * 通过宽度、高度计算屏体的数量
     * @param panel
     */
    function calcQty(panel) {
        var heightInput = $(panel).find("input[tag='seq-height']:first").val();
        var widthInput = $(panel).find("input[tag='seq-width']:first").val();
        var heightUnit = parseFloat($(panel).data("height"));
        var widthUnit = parseFloat($(panel).data("width"));
        var widthSize = ($("#size-type").select2("val") == 2 || $("#size-type").select2("val") == 3);//横向是否允许超出边界
        var heightSize = ($("#size-type").select2("val") == 2 || $("#size-type").select2("val") == 4);//纵向是否允许超出边界

        if (!isNaN(heightInput)) {
            var count_height = parseFloat(heightInput * lengthType) / heightUnit;
            var height = heightSize ? Math.ceil(count_height) : Math.floor(count_height);
            $(panel).find("input[tag='qty-height']").val(height);
        }
        if (!isNaN(widthInput)) {
            var count_width = parseFloat(widthInput * lengthType) / widthUnit;
            var width = widthSize ? Math.ceil(count_width) : Math.floor(count_width);
            $(panel).find("input[tag='qty-width']").val(width);
        }

        if (!isNaN(heightInput) && !isNaN(widthInput)) {
            reCalcSeq(panel);
        }
    }

    $("#panel-list-header").on("click", "ul>li>a:not(.active)", function () {
        var tag = $(this).attr("href").replace("panel-", "");

        $("#panel-list-content .tab-pane.active").removeClass("active");
        $("#panel-" + tag).addClass("active");

        $("#panel-param-1 li.active").removeClass("active");
        $("a[href='param-" + tag + "-1']").parent().addClass("active");

        $("#panel-params-dialog-1 .tab-pane.active").removeClass("active");
        $("#param-" + tag + "-1").addClass("active");

        $("#panel-standard-1 li.active,#panel-standard-2 li.active,#panel-standard-3 li.active").removeClass("active");
        $("a[href='spare-" + tag + "-1'],a[href='spare-" + tag + "-2'],a[href='spare-" + tag + "-3']").parent().addClass("active");

        $("#panel-standard-dialog-1 .tab-pane.active,#panel-standard-dialog-2 .tab-pane.active,#panel-standard-dialog-3 .tab-pane.active").removeClass("active");
        $("#spare-" + tag + "-1,#spare-" + tag + "-2,#spare-" + tag + "-3").addClass("active");
    });

    $("#panel-param-1,#panel-standard-1,#panel-standard-2,#panel-standard-3").on("click", "li:not(.active)", function () {
        var href = $(this).find("a").attr("href");
        $(this).parents(".jarviswidget").find(".tab-pane.active").removeClass("active");
        $("#" + href).addClass("active");
    });

    $("#spare-choose").off("click").click(function (e) {
        FittingUtil.checkedSpare(e);
    });

    /**
     * 给备件列表添加一个备件信息
     */
    function appendSpares(data, form, panelId, description) {
        var template = $("#standard-template").html();
        var newItem = $(template).clone(true);
        var newfieldset = $(newItem).find('fieldset');
        newfieldset.find("label:eq(0)").html(data.type);
        newfieldset.find("label:eq(1)").html(data.brand);
        newfieldset.find("label:eq(3)").html(data.unit);

        newfieldset.data("id", data.id);
        newfieldset.data("spel2", data.spel2Year);
        newfieldset.data("type2", data.type2Year);
        newfieldset.data("count2", data.count2Year);
        newfieldset.data("spel3", data.spel3Year);
        newfieldset.data("type3", data.type3Year);
        newfieldset.data("count3", data.count3Year);
        newfieldset.data("spel4", data.spel4Year);
        newfieldset.data("type4", data.type4Year);
        newfieldset.data("count4", data.count4Year);
        newfieldset.data("spel5", data.spel5Year);
        newfieldset.data("type5", data.type5Year);
        newfieldset.data("count5", data.count5Year);
        newfieldset.data("cost", data.costPrice);
        newfieldset.data("sale", data.salePrice);

        newItem.find("input[amount],input[sales]").spinner({
            step: 1,
            min: 1,
            max: 1000000,
            change: function (e) {
                var fieldSet = $(e.target).parents("fieldset:first");
                if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                    calcSingleSparePrice(fieldSet);
                } else {
                    $(this).val(0);
                    calcSingleSparePrice(fieldSet);
                }
            }
        });

        var guarantee = $("[name='guarantee']").select2("val");
        var type = newfieldset.data("type" + guarantee);
        var ct = newfieldset.data("count" + guarantee);
        var spel = newfieldset.data("spel" + guarantee);

        newItem.find("input[sales]").val(data.salePrice);
        newItem.find("label:first").attr("title", description);
        var input = newItem.find("input[amount]:first");
        var count = calcCounts(type, ct, spel, panelId);
        if (0 != count) {
            input.val(count);
        }

        $(form).append(newItem);

        calcSingleSparePrice(newItem);
    }

    function calcCounts(type, ct, spel, panel) {
        if (type == 1) {
            return ct;
        }

        var result = 0;

        var w_b = parseInt($("#" + panel).find("input[tag='qty-width']").val()) || 0; //箱体横向数量
        var h_b = parseInt($("#" + panel).find("input[tag='qty-height']").val()) || 0; //箱体纵向数量
        var w_p = parseFloat($("#" + panel).find("input[tag='seq-width']").val()) * lengthType;//屏体横向长度
        var h_p = parseFloat($("#" + panel).find("input[tag='seq-height']").val()) * lengthType;//屏体纵向长度
        var w_m = parseInt($("#" + panel).data("boxwidth")) || 0;//每个箱体横向多少块模组
        var h_m = parseInt($("#" + panel).data("boxheight")) || 0;//每个箱体纵向多少块模组
        var w_r = parseFloat($("#" + panel).data("transversePix"))*w_b/10000 || 0; //屏横向总分辨率 这里除以10000是表示每万点多少
        var h_r = parseFloat($("#" + panel).data("portraitPix"))*h_b || 0; //屏横向总分辨率

        if (type == 2) {
            result = w_p * h_p * ct; //单位面积
        } else if (type == 3) {
            result = w_b * h_b * ct; //每个箱体
        } else if (type == 4) {
            result = w_b * w_m * h_b * h_m * ct;//每个模组
        } else if (type == 5) {
            if (!spel) {
                return ct;
            }
            try {
                result = eval(spel);
            } catch (err) {
                return ct;
            }
        }

        return Math.ceil(result);
    }

    //所有备件右边删除按钮事件
    $(".jarviswidget").on("click", ".trash", function () {
        var widget = $(this).parents(".tab-pane.fade.active");
        $(this).parents("fieldset").parent().remove();
        calcKindSparePrice(widget);
    });
    //包装右边删除按钮事件
    $(".jarviswidget").on("click", ".trash-pack", function () {
        $(this).parents("fieldset").parent().remove();
        var fieldSet = $(this).find("fieldset");
        calcTransport(fieldSet);
    });
    //包装计算事件
    function calcTransport(fieldSet) {
        var price = parseFloat($(fieldSet).find("input[sales]").val()) || 0;
        var amount = parseFloat($(fieldSet).find("input[amount]").val()) || 0;
        $(fieldSet).find("span[price]").html(parseFloat(price * amount).toFixed(2));
        var field = $("#package-form fieldset");
        var total = 0;
        $.each(field, function () {
            var number = parseInt($(this).find("input[amount]").val());
            var value = parseFloat($(this).find("input[sales]").val());
            if (!!value) {
                total += number * value;
            }
        });
        total += (parseFloat($("#logistics-cost").val()) || 0);
        $("#package-panel").find("[tag='total-real']").html(total.toFixed(2));
        calcTotalPrice();
    }

    $(".row.total-tag input:not(.select2-focusser,.select2-input)").spinner({
        step: 1,
        min: 1,
        max: 100,
        change: function () {
            var div = $(this).parents(".row.total-tag:first");
            var sale = parseFloat($(div).find("[tag='total-sale']").html()) || 0;
            var discount;
            if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                discount = parseFloat($(this).val()) || 100;
                $(div).find("[tag='total-real']").html((sale * discount / 100).toFixed(2));
                calcTotalPrice();
            } else {
                $(this).val(100);
                discount = parseFloat($(this).val()) || 100;
                $(div).find("[tag='total-real']").html((sale * discount / 100).toFixed(2));
                calcTotalPrice();
            }
        }
    });

    $("#panel-list-header").on("click", ".drop-panel", function (event) {
        if ($(".drop-panel").length == 1) {
            return;
        }
        $('#del_modal').modal('show');
        $('#ok_del').data("event", event);
        $('#ok_del').data("$dropPanel", $(this));
    });
    $('#del_modal').on('click', '#ok_del', function () {
        $('#del_modal').modal('hide');
        removePanel($(this).data('event'), $(this).data('$dropPanel'))
    });

    //删除屏体事件
    function removePanel(event, $panel) {
        event.preventDefault();
        event.stopPropagation();
        if ($(".drop-panel").length == 1) {
            return;
        }
        var active = $panel.parents("li").hasClass("active");
        var panel = $panel.parent("a:first").attr("href");
        var tag = panel.replace("panel-", "");
        var panels = $("#" + panel);
        var panelLi = $("[href='" + panel + "']");
        var params = "#param-" + tag + "-1";
        var spares = "#spare-" + tag + "-1,#spare-" + tag + "-2,#spare-" + tag + "-3";
        var paramLi = "[href='param-" + tag + "-1']";
        var spareLi = "[href='spare-" + tag + "-1'],[href='spare-" + tag + "-2'],[href='spare-" + tag + "-3']";
        $(params).remove();
        $(spares).remove();
        $(panels).remove();
        $(paramLi).parents("li").remove();
        $(spareLi).parents("li").remove();
        $(panelLi).parents("li").remove();
        if (active) {
            $("#panel-list-header li:first>a").click();
        }

        calcPanelTotalPrice(tag);
        $.each($("#panel-standard-1,#panel-standard-2,#panel-standard-3"), function (index, item) {
            calcKindSparePriceNoTop(item);
        });
        calcTotalPrice();
        reBuildPanelIndex();
    }


    $("#total-discount").spinner({
        step: 1,
        min: 1,
        max: 100,
        change: function () {
            var discount;
            var real = parseFloat($("#total-price").html()) || 0;
            if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                discount = $(this).val() || 100;
                $("#total-real").html(parseFloat(discount * real / 100).toFixed(2));
            } else {
                $(this).val(100);
                discount = $(this).val() || 100;
                $("#total-real").html(parseFloat(discount * real / 100).toFixed(2));
            }
        }
    });

    $("#logistics-cost").spinner({
        step: 1,
        min: 0,
        max: 10000,
        change: function () {
            if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                calcTransport();
            }else {
                $(this).val(0);
                calcTransport();
            }
        }
    });

    /**
     * 提交按钮
     */
    $("#submit_button").off("click").click(function () {
        showLoading();
        if (!$("#select_customer").val()) {
            alert(message["offer.create.step1.customer"], 2);
            hideLoading();
            return;
        }
        var valid = checkInput();
        if (!valid) {
            hideLoading();
            return false;
        }
        var confirmFlag = checkConfirm();
        if (confirmFlag) {
            hideLoading();
            return false;
        }
//用来标记是否草稿 true-是 false-否
        submitFun(false);
    });
    /**
     * 保存按钮
     */
    $("#save_button").off("click").click(function () {
        showLoading();
        if (!$("#select_customer").val()) {
            alert(message["offer.create.step1.customer"], 2);
            hideLoading();
            return;
        }
        var valid = checkInput();
        if (!valid) {
            hideLoading();
            return false;
        }
        var confirmFlag = checkConfirm();
        if (confirmFlag) {
            hideLoading();
            return false;
        }
//用来标记是否草稿 true-是 false-否
        submitFun(true);
    });

    /**
     * 提交之前，检查是否需要给出提醒
     */
    function checkConfirm() {
        var confirmFlag = false;
        $.each($("[name='panel']"), function (index, item) {
            var tabPane = $(item).parents(".tab-pane");
            var basePrice = tabPane.find("input[tag='price']").data('base_price');
            var tagPrice = tabPane.find("input[tag='price']").val();
            if (Number(tagPrice) < Number(basePrice)) {
                var tab = $(item).parents(".tab-pane").attr("id");
                var li = $("a[href='" + tab + "']");
                $(li).click();
                confirmFlag = true;
                $('#lower_base_price_modal').modal('show');
                return false;
            }
        });
        return confirmFlag;
    }

    /**
     * 提交方法
     */
    function submitFun(draftFlag) {
        var offer = {
            id: '${(offerVo.offer.id?c)!}',
            num: '${(offerVo.offer.num)!}',
            customer: $("#select_customer").data("id"),
            customerName: $("#select_customer").val(),
            payment: $("#select_payment").val(),
            shipping: $("#shipping").val(),
            guarantee: $("[name='guarantee']").select2("val"),
            waitingDate: $("#valid-date").val(),
            trader: $('#select_trader').val(),
            moneyUnit: $("#money-type").select2("val"),
            sizeUnit: $("[name='radio-inline']:radio:checked").val(),
            series: $("#select-series").select2("val"),
            remark: $("[name='remark']").val(),
            projectName: $("#project_name").val(),
            area: $("#price_area").select2("val"),
            sizeType: $("#size-type").select2("val"),
            totalPrice: $("#total-real").html(),
            totalDiscount: $("#total-discount").val(),
            servicePrice: $("#service-panel span[tag='total-real']").html(),
            serviceDiscount: $("#service-panel input[tag='total-discount']").val(),
            rate: $('#money-type option:selected').data('key'),
            offere: $(".sales-select").select2('val')
        };
        var transfer = {
            id: '${(offerVo.transfer.id?c)!}',
            toAddress: "",//$("[name='to_address']").val(),
            cost: $("#logistics-cost").val() || 0,
            trade: $('#package-panel .packageTrade').select2('data').text,
            transport: "",//$("[name='transport']").val(),
            size: ""//$("[name='size']").val()
        };

        var transport = [];
        $.each($("#package-form fieldset[role='package']"), function () {
            var trans = {
                packages: $(this).find("input[packages]").val(),
                number: $(this).find("input[amount]").val(),
                priceUnit: $(this).find("input[sales]").val(),
                price: $(this).find("span[price]").html()
            };
            transport.push(trans);
        });

        var serviceList = [];
        $.each($("#service-panel .smart-form fieldset[role='service']"), function (index, item) {
            var service = {
                name: $(item).find("input:first").val(),
                counts: $(item).find("input:eq(1)").val(),
                price: $(item).find("input:eq(2)").val()
            };
            serviceList.push(service);
        });

        var panelList = [];
        var gua = $("select[name='guarantee']").select2("val");
        $.each($("#panel-list-header li"), function (index, item) {
            var panelId = $(item).find("a").attr("href");
            var panelDiv = $("#" + panelId);
            var tag = panelId.split("-")[1];
            var panelItemInfo = $(panelDiv).find("[name='panel']").data('panelItemInfo') || {};
            var panels = {
                panel: $(panelDiv).find("[name='panel']").select2("val"),
                wcount: $(panelDiv).find("input[tag='qty-width']").val(),
                lcount: $(panelDiv).find("input[tag='qty-height']").val(),
                horizontal: $(panelDiv).find("input[tag='seq-width']").val(),
                longitudinal: $(panelDiv).find("input[tag='seq-height']").val(),
                price: $("#spare-" + tag + "-1").find("input[tag='reference']").val(),
                orders: index + 1,
                totalPrice: $("#price-panel-" + tag).html(),
                discount: $("#spare-" + tag + "-1").find("input[tag='total-discount']").val(),
                suggPrice: $(panelDiv).data("sales"),
                costPrice: $(panelDiv).data("price"),
                quantity: $(panelDiv).find("input[tag='quantity']").val(),
                series: $(panelDiv).find('[name=product]').select2("val"),
                standardPrice: $("#spare-" + tag + "-1").find("span[tag='total-real']").html(),
                standardDiscount: $("#spare-" + tag + "-1").find("input[tag='total-discount']").val(),
                sparePrice: $("#spare-" + tag + "-2").find("span[tag='total-real']").html(),
                spareDiscount: $("#spare-" + tag + "-2").find("input[tag='total-discount']").val(),
                freePrice:$("#spare-" + tag + "-3").find("span[tag='total-sale']").html(),
                description: $(panelDiv).find("[name='panel']").select2("data").text,
                partNo: panelItemInfo.partNo,
                remark: panelItemInfo.state
            };

            var standDiv = $("#spare-" + panelId.replace("panel-", "") + "-1");
            var standardList = [];
            $.each($(standDiv).find("form.smart-form.temp fieldset[role]"), function (ind, fieldset) {
                standardList.push(buildSpare(fieldset, gua, panelId));
            });
            var selfStandList = [];
            $.each($(standDiv).find("form.smart-form.self fieldset[role]"), function (ind, fieldset) {
                selfStandList.push(buildSelf(fieldset));
            });

            var spareDiv = $("#spare-" + panelId.replace("panel-", "") + "-2");
            var spareList = [];
            $.each($(spareDiv).find("form.smart-form.temp fieldset[role]"), function (ind, fieldset) {
                spareList.push(buildSpare(fieldset, gua, panelId));
            });
            var selfSpareList = [];
            $.each($(spareDiv).find("form.smart-form.self fieldset[role]"), function (ind, fieldset) {
                selfSpareList.push(buildSelf(fieldset));
            });

            var freeDiv = $("#spare-" + panelId.replace("panel-", "") + "-3");
            var freeList = [];
            $.each($(freeDiv).find("form.smart-form.temp fieldset[role]"), function (ind, fieldset) {
                freeList.push(buildSpare(fieldset, gua, panelId));
            });
            var selfFreeList = [];
            $.each($(freeDiv).find("form.smart-form.self fieldset[role]"), function (ind, fieldset) {
                selfFreeList.push(buildSelf(fieldset));
            });

            panelList.push({
                panels: panels,
                standardList: standardList,
                spareList: spareList,
                freeList: freeList,
                selfStandList: selfStandList,
                selfSpareList: selfSpareList,
                selfFreeList: selfFreeList
            });
        });

        var offerVo = {
            offer: offer,
            // prices: prices,
            panelList: panelList,
            serviceList: serviceList,
            transfer: transfer,
            transport: transport
            // basic: basics
        };


        $.ajax({
            url: "/offer/submit",
            type: "post",
            data: {
                draftFlag: draftFlag,
                offer: JSON.stringify(offerVo)
            },
            success: function (data) {
                if (data) {
                    alert(message["http.response.success"]);
                    if (window.location.hash == "#/offer/list") {
                        setTimeout(function () {
                            $("#second").html('');
                            $('#second').hide();
                            $('#first').show();
                            dt.resetFilter();
                        }, 2000);
                    }
                    setTimeout(function () {
                        window.location.href = "#/offer/list"
                    }, 2000);
                } else {
                    alert(message["http.response.fail"], 3);
                }
                hideLoading();
            },
            error: function (data) {
                console.log(data);
                alert(message["http.response.error"], 3);
                hideLoading();
            }
        });
    }

    function reBuildPanelIndex() {
        var items = $("#panel-list-header li");
        $.each(items, function (index, item) {
            var tag = $(item).find("a").attr("href").replace("panel-", "");
            if (items.length > 1) {
                $("a[href*='" + tag + "']").find("span[role='index']").html(index + 1);
            } else {
                $("a[href*='" + tag + "']").find("span[role='index']").html("");
            }

        });
    }

    function buildSpare(fieldset, gua, panelId) {
        var item = {
            spare: $(fieldset).data("id"),
            spareName: $(fieldset).find("label:first").html(),
            brand: $(fieldset).find("label:eq(1)").html(),
            countGuid: calcCounts($(fieldset).data("type" + gua), $(fieldset).data("count" + gua), $(fieldset).data("spel" + gua), panelId),
            countReal: $(fieldset).find("input[amount]").val(),
            priceCost: $(fieldset).data("cost"),
            priceSale: $(fieldset).find("input[sales]").val(),
            priceGuide: $(fieldset).data("sale"),
            unit: $(fieldset).find(".short-label").html()
        };
        return item;
    }

    function buildSelf(fieldset) {
        var item = {
            spareType: $(fieldset).find("input:first").val(),
            brand: $(fieldset).find("input:eq(1)").val(),
            price: $(fieldset).find("input[sales]").val(),
            amount: $(fieldset).find("input[amount]").val(),
            unit: $(fieldset).find("input:eq(3)").val()
        };
        return item;
    }

    function checkInput() {
        var valid = true;
        $.each($("[name='panel']"), function (index, item) {
            if (!$(item).select2("val")) {
                var tab = $(item).parents(".tab-pane").attr("id");
                var li = $("a[href='" + tab + "']");
                $(li).click();
                var name = $(li).find("span").html().replace("<span role=\"index\">", "").replace("</span>", "");
                console.log("quote.empty.product.panel");
                alert(message["quote.empty.product.panel"].replace("_index_", name), 2);
                valid = false;
                return false;
            }
        });
        if (valid) {
            $.each($("[tag='total-discount'],#total-discount"), function (index, item) {
                if (!$(item).val()) {
                    console.log("offer.create.empty.discount");
                    alert(message["offer.create.empty.discount"], 2);
                    valid = false;
                    return false;
                }
            });
        }
        if (valid) {
            $.each($(".panel-input .tab-pane input.ui-spinner-input:not([tag='total-discount'],#total-discount,select2-input),.panel-input .tab-pane .smart-form input:not(.ui-spinner-input)"), function (index, item) {
                if (!$(this).val()) {
                    var id = $(this).parents(".tab-pane").attr("id");
                    var li = $("a[href='" + id + "']");
                    $(li).click();
                    var name = $(li).find("span").html().replace("<span role=\"index\">", "").replace("</span>", "");
                    console.log("offer.create.empty.input");
                    alert(message["offer.create.empty.input"].replace("_index_", name), 2);
                    valid = false;
                    return false;
                }
            });
        }
        if (valid) {
            var inputs = $("#package-panel input:not(.select2-focusser,.select2-input)");
            $.each(inputs, function () {
                if (!$(this).val()) {
                    console.log("offer.create.empty.package");
                    alert(message["offer.create.empty.package"], 2);
                    valid = false;
                    return false;
                }
            });
        }
        if (valid) {
            var inputs = $("#service-form input");
            $.each(inputs, function () {
                if (!$(this).val()) {
                    console.log("offer.create.empty.service");
                    alert(message["offer.create.empty.service"], 2);
                    valid = false;
                    return false;
                }
            });
        }
        return valid;
    }



    /**
     * 点击checked选择配件
     */
    $('#choose_spare_list_ul').off('click').on('click','tbody tr td',function () {
        if($.inArray(this,$(this).closest('tr').find('td')) !== 0){
            $(this).closest('tr').find('input[type="checkbox"]').click();
        }
        FittingUtil.updateCheckedCount();
    });


    /**
     * 多选配件 确定按钮(以下都为批量添加模态框的事件)
     */
    $('#choose_spares_sure').off('click').click(function () {
        var spares_list = [];
        var spare_form = $("#panel-standard-1").find("li.active a:first").attr("href");
        var choose_form = $("#panel-standard-2").find("li.active a:first").attr("href");
        var free_form = $("#panel-standard-3").find("li.active a:first").attr("href");

        var spare_form_panel = 'panel-' + spare_form.split("-")[1];
        var choose_form_panel = 'panel-' + choose_form.split("-")[1];
        var free_form_panel = 'panel-' + free_form.split("-")[1];

        $.each($('#choose_spare_list_ul .tabs-spare tbody tr'),function (index,item) {
            if($(item).find('input[type="checkbox"]').prop("checked")){
                spares_list.push({
                    data: $(item).data('item'),
                    form: $("#"+spare_form+" form.smart-form.temp"),
                    panelId: spare_form_panel,
                    description: $(item).data('item').description
                });
            }
        });
        $.each($('#choose_spare_list_ul .tabs-choose tbody tr'),function (index,item) {
            if($(item).find('input[type="checkbox"]').prop("checked")){
                spares_list.push({
                    data: $(item).data('item'),
                    form: $("#"+choose_form+" form.smart-form.temp"),
                    panelId: choose_form_panel,
                    description: $(item).data('item').description
                });
            }
        });
        $.each($('#choose_spare_list_ul .tabs-free tbody tr'),function (index,item) {
            if($(item).find('input[type="checkbox"]').prop("checked")){
                spares_list.push({
                    data: $(item).data('item'),
                    form: $("#"+free_form+" form.smart-form.temp"),
                    panelId: free_form_panel,
                    description: $(item).data('item').description
                });
            }
        });
        spares_list.forEach(function (item) {
            appendSpares(item.data,item.form,item.panelId,item.description);
        });
        $('#choose_spares').modal('hide');
    });

    /**
     * 检测模态框关闭
     */
    $('#choose_spares').on('hide.bs.modal', function () {
        $('body').css('overflow','auto');// 浮层关闭时滚动设置
    });
    /**
     * 检测模态框开启
     */
    $('#choose_spares').on('show.bs.modal', function () {
        $('body').css('overflow','hidden');//浮层出现时窗口不能滚动设置
    });

    $('#dialog_test').off('click').click(function () {

        FittingUtil.chooseSparesDialog();
    });

    /**
     * 整合工具方法
     */
    var FittingUtil = function FittingUtil() {
        return {
            //获取已选择
            findFittingList: function () {
                var map = {
                    spare: [],
                    choose: [],
                    free: []
                };
                var panelKey = $('#panel-list-header li.active a').attr("href").split("-")[1];
                $.each($('#spare-'+ panelKey +'-1 fieldset'), function (index, item) {
                    map.spare.push($(item).data().id);
                });
                $.each($('#spare-'+ panelKey +'-2 fieldset'), function (index, item) {
                    map.choose.push($(item).data().id);
                });
                $.each($('#spare-'+ panelKey +'-3 fieldset'), function (index, item) {
                    map.free.push($(item).data().id);
                });
                return map;
            },
            //多选配件 刷新数量
            updateCheckedCount: function () {
                var spareCount = $('.tabs-spare input[type="checkbox"]:checked').length;
                var chooseCount = $('.tabs-choose input[type="checkbox"]:checked').length;
                var freeCount = $('.tabs-free input[type="checkbox"]:checked').length;

                $('#countListShow a[href=".tabs-spare"] .check_count').html(spareCount);
                $('#countListShow a[href=".tabs-choose"] .check_count').html(chooseCount);
                $('#countListShow a[href=".tabs-free"] .check_count').html(freeCount);
            },
            /**
             * 根据类型勾选已选择的配件
             * @param type 类型 1-标配 2-选配 3-免费
             */
            byTypeChecked: function (type, fatherEl) {
                var ids = [];
                $.each($('#panel-standard-dialog-'+ type +' div.active fieldset'),function (index, item) {
                    ids.push($(item).data('id'));
                });
                $.each(fatherEl.find('tbody tr'),function (index, item) {
                    if($.inArray($(item).data('id'), ids) >= 0){
                        $(item).hide();
//                        $(item).find('input.spare_checkbox').prop('checked',true);
                    }
                });
            },
            /**
             * 配件选中事件
             */
            checkedSpare: function (e) {
                e.preventDefault();
                var type = $("#spare-type").val().split('-')[2];
                //已选配件ID
                var ids = [];
                $.each($('#panel-standard-dialog-'+ type +' div.active fieldset'),function (index, item) {
                    ids.push($(item).data('id'));
                });

                var checkboxs = $("#choose_spare_dialog table input:checkbox:checked");
                if(!checkboxs || checkboxs.length==0){
                    alert(message["offer.create.spare.empty"]);
                    return;
                }
                checkboxs.each(function(e){
                    var tr=$(this).parents("tr:first");
                    if($(tr).data("id") && $.inArray($(tr).data("id"), ids) === -1){
                        var sparetag=$("#spare-type").val();
                        var data={
                            id:$(tr).data("id"),
                            brand:$(tr).data("brand"),
                            type:$(tr).data("type"),
                            model:$(tr).data("model"),
                            unit:$(tr).data("unit"),
                            count2Year:$(tr).data("count2"),
                            type2Year:$(tr).data("type2"),
                            spel2Year:$(tr).data("spel2"),
                            count3Year:$(tr).data("count3"),
                            type3Year:$(tr).data("type3"),
                            spel3Year:$(tr).data("spel3"),
                            count4Year:$(tr).data("count4"),
                            type4Year:$(tr).data("type4"),
                            spel4Year:$(tr).data("spel4"),
                            count5Year:$(tr).data("count5"),
                            type5Year:$(tr).data("type5"),
                            spel5Year:$(tr).data("spel5"),
                            salePrice:$(tr).data("sale"),
                            costPrice:$(tr).data("cost")
                        };
                        appendSpares(data,$("#"+sparetag+" form.smart-form.temp"), $("#spare-type").data("panel"),$(tr).find("td:last").html());
                    }
                });

                $("#dialog_table").modal("hide");
                $("#choose_spare_dialog").html("");
                $("#spare-type").val("");
                $("#spare-type").data("panel","");
            },
            /**
             * 选择 配件、选配、免费
             */
            chooseSparesDialog: function () {
                var self = this;
                showLoading();
                var spare_id = $("#panel-standard-1").find("li.active a:first").attr("href");
                var tag = spare_id.split("-")[1];
                var panel=$("#panel-"+tag);
                var product=$(panel).find("[name='panel']").select2("val");
                var series = panel.find('[name=product]').select2("val");
                if ((!series)|| (!product)) {
                    alert(message["quote.empty.product"], 2);
                    hideLoading();
                    return;
                }

                var spareTbody1 = $('#choose_spare_list_ul .tabs-spare1 tbody');
                var spareTbody2 = $('#choose_spare_list_ul .tabs-spare2 tbody');
                var chooseTbody1 = $('#choose_spare_list_ul .tabs-choose1 tbody');
                var chooseTbody2 = $('#choose_spare_list_ul .tabs-choose2 tbody');
                var freeTbody1 = $('#choose_spare_list_ul .tabs-free1 tbody');
                var freeTbody2 = $('#choose_spare_list_ul .tabs-free2 tbody');
                spareTbody1.html('');
                spareTbody2.html('');
                chooseTbody1.html('');
                chooseTbody2.html('');
                freeTbody1.html('');
                freeTbody2.html('');
                $.ajax({
                    url: '/offer/rest/spares',
                    type: 'get',
                    data: {
                        product: product,
                        moneyType:$('#money-type option:selected').data('key'),
                        area:$("#price_area").select2("val"),
                        series:series
                    },
                    success: function (res) {
                        console.log(res);
                        hideLoading();
                        //已选择的配件ID
                        var fittingCount = self.findFittingList();
                        // var windowHeight = parseInt($(window).height() * 0.7);
                        // $('#choose_spare_list_ul').css('height', windowHeight + "px");

                        res.spares.forEach(function (item,index) {
                            if($.inArray(item.id, fittingCount.spare) === -1){
                                var itemEl = $('#self-spare-tr').tmpl(item);
                                itemEl.data('item',item);
                                if(index%2===0){
                                    spareTbody1.append(itemEl);
                                }else{
                                    spareTbody2.append(itemEl);
                                }
                            }
                        });

                        res.choose.forEach(function (item,index) {
                            if($.inArray(item.id, fittingCount.choose) === -1){
                                var itemEl = $('#self-spare-tr').tmpl(item);
                                itemEl.data('item',item);
                                if(index%2===0){
                                    chooseTbody1.append(itemEl);
                                }else{
                                    chooseTbody2.append(itemEl);
                                }
                            }
                        });

                        res.free.forEach(function (item,index) {
                            if($.inArray(item.id, fittingCount.free) === -1){
                                var itemEl = $('#self-spare-tr').tmpl(item);
                                itemEl.data('item',item);
                                if(index%2===0){
                                    freeTbody1.append(itemEl);
                                }else{
                                    freeTbody2.append(itemEl);
                                }
                            }
                        });

                        self.updateCheckedCount();
                        self.searchSpare();
                        $('#choose_spares').modal('show');
                    },
                    error: function (err) {
                        hideLoading();
                        console.log(err);
                        alert(message["operation.failed"]);
                    }
                });
            },
            searchSpare: function () {
                var self = this;
                var spareSearchs = $('#choose_spare_list_ul a.btn_search');
                var spareRest = $('#choose_spare_list_ul a.btn_rest');
                var search_type,search_description,search_brand;
                spareSearchs.off('click').click(function () {
                    console.log(1);
                    if($(this).hasClass('spare_spare')){
                        var tabs_spare = $(this).closest('.tabs-spare');
                        search_type = tabs_spare.find('input.search_type').val();
                        search_description = tabs_spare.find('input.search_description').val();
                        search_brand = tabs_spare.find('input.search_brand').val();

                        $.each(tabs_spare.find('tr.spare_item'), function () {
                            var data = $(this).data('item');
                            if(search_type && data.type.indexOf(search_type) >= 0){
                                $(this).show();
                                return;
                            }
                            if(search_description && data.description.indexOf(search_description) >= 0){
                                $(this).show();
                                return;
                            }
                            if(search_brand && data.brand.indexOf(search_brand) >= 0){
                                $(this).show();
                                return;
                            }
                            if(search_type === '' && search_description === '' && search_brand === ''){
                                $(this).show();
                            }else{
                                $(this).find('input[type="checkbox"]').prop('checked',false);
                                $(this).hide();
                            }
                            self.updateCheckedCount();
                        });

                    }else if($(this).hasClass('spare_choose')){
                        var tabs_choose = $(this).closest('.tabs-choose');
                        search_type = tabs_choose.find('input.search_type').val();
                        search_description = tabs_choose.find('input.search_description').val();
                        search_brand = tabs_choose.find('input.search_brand').val();

                        $.each(tabs_choose.find('tr.spare_item'), function () {
                            var data = $(this).data('item');
                            if(search_type && data.type.indexOf(search_type) >= 0){
                                $(this).show();
                                return;
                            }
                            if(search_description && data.description.indexOf(search_description) >= 0){
                                $(this).show();
                                return;
                            }
                            if(search_brand && data.brand.indexOf(search_brand) >= 0){
                                $(this).show();
                                return;
                            }
                            if(search_type === '' && search_description === '' && search_brand === ''){
                                $(this).show();
                            }else{
                                $(this).find('input[type="checkbox"]').prop('checked',false);
                                $(this).hide();
                            }
                            self.updateCheckedCount();
                        });
                    }else if($(this).hasClass('spare_free')){
                        var tabs_free = $(this).closest('.tabs-free');
                        search_type = tabs_free.find('input.search_type').val();
                        search_description = tabs_free.find('input.search_description').val();
                        search_brand = tabs_free.find('input.search_brand').val();

                        $.each(tabs_free.find('tr.spare_item'), function () {
                            var data = $(this).data('item');
                            if(search_type && data.type.indexOf(search_type) >= 0){
                                $(this).show();
                                return;
                            }
                            if(search_description && data.description.indexOf(search_description) >= 0){
                                $(this).show();
                                return;
                            }
                            if(search_brand && data.brand.indexOf(search_brand) >= 0){
                                $(this).show();
                                return;
                            }
                            if(search_type === '' && search_description === '' && search_brand === ''){
                                $(this).show();
                            }else{
                                $(this).hide();
                                $(this).find('input[type="checkbox"]').prop('checked',false);
                            }
                            self.updateCheckedCount();
                        });
                    }
                });
                spareRest.off('click').click(function () {
                    if($(this).hasClass('spare_spare')){
                        var tabs_spare = $(this).closest('.tabs-spare');
                        tabs_spare.find('input.search_type').val('');
                        tabs_spare.find('input.search_description').val('');
                        tabs_spare.find('input.search_brand').val('');
                        tabs_spare.find('tr.spare_item').show();
                    }else if($(this).hasClass('spare_choose')){
                        var tabs_choose = $(this).closest('.tabs-choose');
                        tabs_choose.find('input.search_type').val('');
                        tabs_choose.find('input.search_description').val('');
                        tabs_choose.find('input.search_brand').val('');
                        tabs_choose.find('tr.spare_item').show();
                    }else if($(this).hasClass('spare_free')){
                        var tabs_free = $(this).closest('.tabs-free');
                        tabs_free.find('input.search_type').val('');
                        tabs_free.find('input.search_description').val('');
                        tabs_free.find('input.search_brand').val('');
                        tabs_choose.find('tr.spare_item').show();
                    }
                });
            }
        }
    }();
</script>

<link rel="stylesheet" href="../../static/js/plugin/bootstrap-autosuggest/autosuggest.css"/>
<style>
    .stepshow.activate {
        right: 0 !important;
        box-shadow: -11px 12px 23px rgba(0, 0, 0, .2);
        padding: 5px 10px 10px;
    }

    .stepshow {
        position: absolute;
        top: 5px;
        right: 0;
        width: 380px;
        z-index: 902;
        background: #F1DA91;
    }

    .param-table td.text-align-right {
        border-right: 1px dashed #ccc;
    }

    .stepshow.activate > span {
        left: -30px;
    }

    .stepshow > span {
        position: absolute;
        right: 0;
        top: 0;
        display: block;
        height: 30px;
        width: 30px;
        border-radius: 5px 0 0 5px;
        background: #F1DA91;
        padding: 2px;
        text-align: center;
        line-height: 28px;
        cursor: pointer;
    }

    .smart-form fieldset[role] a.btn-sm, .smart-form fieldset[tag] a.btn-sm {
        margin-right: 5px;
    }

    .jarviswidget {
        margin: 0 0 10px;
    }

    .smart-form fieldset {
        padding-top: 2px;
    }

    .smart-form fieldset a {
        margin-bottom: 2px;
        margin-top: 2px;
    }

    .smart-form header {
        font-size: 14px;;
    }

    .swiper-container, .swiper-slide {
        min-height: 255px;
    }

    .smart-form header {
        padding-bottom: 0;
        font-size: 15px;
        color: #828282;
    }

    /*.table td {*/
    /*border-top: 1px dashed rgba(0, 0, 0, .2) !important;*/
    /*}*/

    .table {
        width: 90%;
    }

    /*.table .no-border td {*/
    /*border-top: 0 !important;*/
    /*}*/

    .smart-form section label {
        padding-top: 5px;;
    }

    .table > tbody > tr > td {
        padding: 5px 10px !important;
    }

    header {
        cursor: auto !important;
    }

    .ui-spinner-button.ui-spinner-down.ui-corner-br {
        margin-bottom: 0;
        background-color: #ababab;
    }

    .ui-spinner-button.ui-spinner-up.ui-corner-tr {
        margin-top: 0;
        background-color: #ababab;
        padding-top: 2px;
    }

    .padding7 {
        padding-top: 7px;
    }

    .input-group .input-group-addon {
        padding-left: 3px;
        padding-right: 0;
        border: 0;
        background-color: #fff;
        padding-top: 3px;
        width: 25px;
    }

    .long-label {
        padding-right: 0 !important;
        padding-left: 0 !important;
        width: 11.3% !important;
    }

    .short-label {
        padding-left: 3px !important;
        width: 5.3% !important;
        padding-right: 0 !important;
    }

    .no-left {
        padding-left: 0 !important;
    }

    .no-right {
        padding-right: 0 !important;
    }

    .no-left.no-right {
        text-align: right;
    }

    .smart-form .ui-widget-content .ui-spinner-input {
        padding-left: 3px;
    }

    .select2-hidden-accessible {
        display: none;
    }

    .widget-body .smart-form fieldset .col.col-3 {
        padding-left: 0 !important;
    }

    .right-span {
        float: right;
        padding-top: 7px;
        padding-right: 25px;
        color: #ff5757;
    }

    #choose_spare_dialog_customer label i {
        margin-top: -3px;
    }

    .trash-a {
        color: #666 !important;
    }

    .trash-a:hover {
        color: #ff5757 !important;
    }

    .fa.fa-trash-o {
        font-size: 15pt;
        padding-top: 5px;
        cursor: pointer;
    }

    .margin-left-10 {
        margin-left: 10px;
    }

    .free, [tag="free_part"] [price] {
        text-decoration: line-through;
    }

    .input-autosuggest {
        width: 92%;
        height: 32px;
        border: 1px solid #c9ccd0;
        padding: 0 4%;
    }

    .form-control {
        height: 29px;
    }

    .smart-form fieldset + fieldset {
        border-top: 0;
    }

    .drop-panel:hover {
        color: #ff5757 !important;
    }


    .drop-panel {
        color: #666 !important;
        display: inline-block;
        cursor: pointer;
        margin-top: -7px;
        margin-left: 5px;
    }

    .product-info-look .col-md-4 {
        padding-right: 0;
    }

    .pading-seven {
        padding-bottom: 7px;
    }

    .panel_certification, .panel_state {
        font-weight: 700;
    }

    /*文字限制一行显示*/
    .text-nowrap {
        white-space: nowrap;
    }

    .list-group-item {
        padding: 8px 10px;
    }
    .row {
        margin-left: -12px;
        margin-right: -12px;
    }
    .form-control{
        padding-left: 5px;
        padding-right: 5px;
    }
    .first-td {
        border-top: 0 !important;
    }
    .acreage {
        border: 0 !important;
        width: 100%;
    }
    #choose_spare_list_ul .show-grid{
        margin: 0 15px !important;
    }
    #choose_spare_list_ul tr:hover{
        background-color: #efefef;
    }
    #choose_spare_list_ul table{
        width: 99%;
    }
    #choose_spare_list_ul tbody tr td{
        text-overflow: ellipsis; /* for IE */
        -moz-text-overflow: ellipsis; /* for Firefox,mozilla */
        overflow: hidden;
        white-space: nowrap;
        text-align: left;
        height: 22px;
    }
    #choose_spare_list_ul table th .first-check{
        width: 30px;
        height: 30px;
        padding: 0;
        margin-left: 5px;
    }
    #choose_spare_list_ul table th:first-child{
    }
    .font-diy{
        font-size: 10px;
    }

    section.spare_search{
        z-index: 999999999999;
        background-color: #FFF;
        padding-left: 0;
        height: 52px;
        position: absolute;
    }
    .choose_spare_table{
        table-layout: fixed;
    }
    #choose_spare_dialog td .checkbox {
        margin-top: -5px;
    }
    .ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable {
        position: fixed !important;
    }
</style>
</#compress>