<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
    <div class="jarviswidget" id="panel-tab">
        <header>
            <ul class="nav nav-tabs pull-left">
                <#list (offerVo.panelList) as item>
                    <li <#if item_index==0>class="active"</#if> id="t_${(item_index)}">
                        <a href="#s${item_index}" data-toggle="tab">
                            <dic data-dic="panel.list.header"></dic>${item_index + 1}
                        </a>
                    </li>
                </#list>
            </ul>
        </header>
        <div>
            <div class="widget-body no-padding">
                <form class="smart-form">
                    <div class="tab-content padding-10" role="panel" id="panel-list-content">
                        <#list (offerVo.panelList) as item>
                            <div class="tab-pane fade in <#if item_index==0>active</#if>" id="s${item_index}">
                                <fieldset class="new-fieldset">
                                    <label class="col col-md-1 text-align-right padding7">
                                        <dic data-dic="product.panel.list.partNo"></dic>:
                                    </label>
                                    <label class="col col-md-3 padding7">${(item.panelsDto.partNo)!}</label>
                                </fieldset>
                                <fieldset class="new-fieldset">
                                    <label class="col col-md-1  text-align-right padding7">
                                        <dic data-dic="offer.create.panelNumber"></dic>:
                                    </label>
                                    <label class="col col-md-2 input">
                                        <input type="text" value="${(item.panelsDto.wcount)!0}" readonly/>
                                    </label>
                                    <label class="col col-md-2 input">
                                        <input type="text" value="${(item.panelsDto.lcount)!0}" readonly/>
                                    </label>
                                    <label class="col col-md-2 padding7">
                                        <dic data-dic="offer.create.totalCount"></dic>:
                                        <span>${((item.panelsDto.wcount)*(item.panelsDto.lcount))!0}</span>
                                    </label>
                                    <label class="col col-md-1  text-align-right padding7">
                                        <dic data-dic="offer.create.priceUnit"></dic>:
                                    </label>
                                    <label class="col col-md-2 input">
                                        <input type="text" value="${(item.panelsDto.price)!0}" readonly/>
                                    </label>
                                    <label class="col col-md-2  text-align-right padding7">
                                        <dic data-dic="offer.create.priceCount"></dic>:
                                        <span>${(moneyUnit)!}<span class="moneyFormat">${((item.panelsDto.totalPrice)/((item.panelsDto.discount)/100))!0}</span></span>
                                    </label>
                                </fieldset>
                                <fieldset class="new-fieldset">
                                    <label class="col col-md-1  text-align-right padding7">
                                        <dic data-dic="offer.create.panelArea"></dic>:
                                    </label>
                                    <label class="col col-md-2 input">
                                        <input type="text" value="${(item.panelsDto.horizontal)!0}" readonly/>
                                    </label>
                                    <label class="col col-md-2 input">
                                        <input type="text" value="${(item.panelsDto.longitudinal)!0}" readonly/>
                                    </label>
                                    <label class="col col-md-2 padding7">
                                        <dic data-dic="offer.create.totalArea"></dic>:
                                        <span>${((item.panelsDto.horizontal)*(item.panelsDto.longitudinal))!0}</span>
                                    </label>
                                    <label class="col col-md-1  text-align-right padding7">
                                        <dic data-dic="offer.create.discount"></dic>:
                                    </label>
                                    <label class="col col-md-2 input">
                                        <input type="text" value="${(item.panelsDto.discount)!0}%" readonly/>
                                    </label>
                                    <label class="col col-md-2  text-align-right padding7">
                                        <dic data-dic="offer.create.realPrice"></dic>:
                                        <span>${(moneyUnit)!}<span class="moneyFormat">${(item.panelsDto.totalPrice)!0}</span></span>
                                    </label>
                                </fieldset>
                            </div>
                        </#list>
                    </div>
                </form>
            </div>
        </div>
        <div style="border: 1px solid #ccc;margin-top: -2px;padding-bottom: 5px;padding-top: 7px !important;" class="row">
            <label class="col col-md-5">&nbsp;</label>
            <label class="col col-md-2 padding7"><dic data-dic="offer.create.totalPrice"></dic>:<span tag="total-sale">
                <#if (offerPrice.panelPrice)??>
                    ${(moneyUnit)!}<span class="moneyFormat">${((offerPrice.panelPrice)/((offerPrice.panelDiscount)/100))?string("#.##")}</span>
                <#else>
                    ${(moneyUnit)!}0
                </#if>
            </span></label>
            <label class="col col-md-3 padding7">
                <dic data-dic="offer.create.discount"></dic>:
            <span>
                <#if (offerPrice.panelDiscount)??>
                ${(offerPrice.panelDiscount)?string("#.##")}%
                <#else>
                    100%
                </#if>
            </span>
            </label>
            <label class="col col-md-2 padding7">
                <dic data-dic="offer.create.real"></dic>:
                <span>
                    <#if (offerPrice.panelPrice)?? && (offerPrice.panelDiscount)??>
                        ${(moneyUnit)!}<span class="moneyFormat">${(offerPrice.panelPrice)?string("#.##")}</span>
                    <#else>
                        ${(moneyUnit)!}0
                    </#if>
                </span></label>
        </div>
    </div>
</article>

<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12  sortable-grid ui-sortable">
    <div class="jarviswidget" id="box-tab">
        <header>
            <ul class="nav nav-tabs pull-left">
                <#list (offerVo.panelList) as item>
                    <li <#if item_index==0>class="active"</#if> id="a_${(item_index)}">
                        <a href="u${item_index}" data-toggle="tab">
                            <dic data-dic="offer.create.params"></dic>${item_index + 1}
                        </a>
                    </li>
                </#list>
            </ul>
        </header>
        <div>
            <div class="jarviswidget-editbox"></div>
            <div class="widget-body no-padding">
                <div class="tab-content padding-10" id="panel-params-dialog-1">
                    <#list (offerVo.panelList) as item>
                        <div class="tab-pane fade in <#if item_index==0>active</#if>" id="u${item_index}">
                            <div class="smart-form">
                                <div class="fnt">
                                    <p class="pline"><dic class="ml5 mr5" data-dic="offer.create.modual"></dic></p>
                                    <table class="table" style="width: 100%">
                                        <tbody style="border-left: 1px dashed #00000033;">
                                        <tr class="no-border">
                                            <td class="text-align-left"><dic data-dic="offer.create.module.size"></dic></td>
                                            <td class="text-align-right">${(item.module.width?c)!0}&#42;${(item.module.height?c)!0}</td>
                                            <td class='text-align-left'><dic data-dic="offer.create.module.pix"></dic></td>
                                            <td class='text-align-right'>${(item.module.transverse?c)!0}&#42;${(item.module.portrait?c)!0}</td>
                                            <td class="text-align-left"><dic data-dic="offer.view.spacing"></dic></td>
                                            <td class="text-align-right">${(item.module.pitch)!0}</td>
                                            <td class='text-align-left'><dic data-dic="offer.create.module.density"></dic></td>
                                            <td class='text-align-right'>${(((1000/item.module.pitch)*(1000/item.module.pitch))?int)?c}</td>
                                        </tr>
                                        <tr>
                                            <td class='text-align-left'><dic data-dic="offer.create.module.weight"></dic></td>
                                            <td class='text-align-right'>${(item.module.weight?c)!0}kg</td>
                                            <td class='text-align-left'><dic data-dic="offer.view.type"></dic></td>
                                            <td class='text-align-right'>${(item.module.configuration)!0}</td>
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
                                        <tbody style="border-left: 1px dashed #00000033;">
                                        <tr class="no-border">
                                            <td class="text-align-left"><dic data-dic="offer.create.box.size"></dic></td>
                                            <td class="text-align-right">${(item.box.width?c)!0}&#42;${(item.box.height?c)!0}&#42;${(item.box.thickness?c)!0}</td>
                                            <td class='text-align-left'><dic data-dic="offer.create.box.pix"></dic></td>
                                            <td class='text-align-right'>${(item.box.transversePix?c)!0}&#42;${(item.box.portraitPix?c)!0}</td>
                                            <td class="text-align-left"><dic data-dic="offer.create.box.number"></dic></td>
                                            <td class="text-align-right">${((item.box.transverseCount*item.box.portraitCount)?c)!0}</td>
                                            <td class='text-align-left' style="width: 10% !important;"><dic data-dic="offer.create.box.display"></dic></td>
                                            <td class='text-align-right' style="width: 15% !important;">${((item.box.transversePix*item.box.portraitPix)?c)!0}</td>
                                        </tr>
                                        <tr>
                                            <td class='text-align-left'><dic data-dic="offer.create.module.weight"></dic></td>
                                            <td class='text-align-right'>${((item.box.weight)?c)!0}kg</td>
                                            <td class="text-align-left"><dic data-dic="offer.create.params.powerAvg"></dic></td>
                                            <td class="text-align-right">${(item.params.powerAvg?c)!0}</td>
                                            <td class='text-align-left'><dic data-dic="offer.create.params.powerMax"></dic></td>
                                            <td class='text-align-right'>${(item.params.powerMax?c)!0}</td>
                                            <td class='text-align-left' style="width: 10% !important;"><dic data-dic="offer.create.BoxMaterial"></dic></td>
                                            <td class='text-align-right' style="width: 15% !important;">${(item.box.boxType)!}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="fnt" style="margin-top: 10px;">
                                    <p class="pline"><dic class="ml5 mr5" data-dic="offer.create.params"></dic></p>
                                    <table class="table" style="width: 100%;">
                                        <tbody style="border-left: 1px dashed #00000033;">
                                        <tr class="no-border">
                                        <#--<td class='text-align-left font-important'><dic data-dic="offer.create.weight"></dic></td>-->
                                        <#--<td class='text-align-right font-important' data-counts="${(item.box.weight?c)!0}">-->
                                        <#--${((item.box.weight*item.offerPanels.wcount*item.offerPanels.lcount)?c)!0}-->
                                        <#--</td>-->
                                            <td class="text-align-left font-important"><dic data-dic="offer.view.pix"></dic></td>
                                            <td class="text-align-right font-important" data-ct1="${(item.box.transversePix?c)!0}" data-ct2="${(item.box.portraitPix?c)!0}">
                                            <#if item.panelsDto.wcount?? && item.panelsDto.lcount??>
                                                <#if item.panelsDto.wcount == 0 || item.panelsDto.lcount == 0>
                                                    ${((item.box.transversePix)?c)!0}&#42;${((item.box.portraitPix)?c)!0}
                                                <#else>
                                                    ${((item.box.transversePix*item.panelsDto.wcount)?c)!0}&#42;${((item.box.portraitPix*item.panelsDto.lcount)?c)!0}
                                                </#if>
                                            <#else>
                                                ${((item.box.transversePix)?c)!0}&#42;${((item.box.portraitPix)?c)!0}
                                            </#if>
                                            </td>
                                            <td class='text-align-left font-important'><dic data-dic="product.panel.form.powerAvg"></dic></td>
                                            <td class='text-align-right font-important' data-counts="${(item.params.powerAvg?c)!0}">
                                            <#if item.panelsDto.wcount?? && item.panelsDto.lcount??>
                                                <#if item.panelsDto.wcount == 0 || item.panelsDto.lcount == 0>
                                                    ${(item.params.powerAvg?c)!0}
                                                <#else>
                                                    ${((item.params.powerAvg*item.panelsDto.wcount*item.panelsDto.lcount)?c)!0}
                                                </#if>
                                            <#else>
                                                ${(item.params.powerAvg?c)!0}
                                            </#if>
                                            </td>
                                            <td class='text-align-left font-important'><dic data-dic="product.panel.form.powerMax"></dic></td>
                                            <td class='text-align-right font-important' data-counts="${(item.params.powerMax?c)!0}">
                                            <#if item.panelsDto.wcount?? && item.panelsDto.lcount??>
                                                <#if item.panelsDto.wcount == 0 || item.panelsDto.lcount == 0>
                                                    ${(item.params.powerMax?c)!0}
                                                <#else>
                                                    ${((item.params.powerMax*item.panelsDto.wcount*item.panelsDto.lcount)?c)!0}
                                                </#if>
                                            <#else>
                                                ${(item.params.powerMax?c)!0}
                                            </#if>
                                            </td>
                                            <td class='text-align-left'><dic data-dic="product.panel.list.color"></dic></td>
                                            <td class='text-align-right'>${(item.panelsDto.color)!}</td>
                                        </tr>
                                        <tr>
                                            <td class='text-align-left'><dic data-dic="offer.create.singlePower"></dic></td>
                                            <td class='text-align-right'>${(item.params.psuPower)!}</td>
                                            <td class="text-align-left"><dic data-dic="product.panel.form.brightness"></dic></td>
                                            <td class="text-align-right">${(item.params.brightness)!}</td>
                                            <td class='text-align-left'><dic data-dic="product.panel.form.brightnessControl"></dic></td>
                                            <td class='text-align-right'>256</td>
                                            <td class='text-align-left'><dic data-dic="product.panel.form.viewingAngle"></dic></td>
                                            <td class='text-align-right'>${(item.params.viewing)!}</td>
                                        </tr>
                                        <tr>
                                            <td class='text-align-left'><dic data-dic="product.panel.form.minDistance"></dic></td>
                                            <td class='text-align-right'>${(item.module.pitch)!0}m</td>
                                            <td class="text-align-left"><dic data-dic="product.panel.form.grayScale"></dic></td>
                                            <td class="text-align-right">${(item.params.grayScale)!}</td>
                                            <td class='text-align-left'><dic data-dic="product.panel.form.refresh"></dic></td>
                                            <td class='text-align-right'>${(item.params.refresh)!}</td>
                                            <td class='text-align-left'><dic data-dic="product.panel.form.contrastRatio"></dic></td>
                                            <td class='text-align-right'>${(item.params.contrastRatio)!}</td>
                                        </tr>
                                        <tr>
                                            <td class='text-align-left'><dic data-dic="product.panel.form.drivingType"></dic></td>
                                            <td class='text-align-right'>${(item.params.drivingType)!}</td>
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
                                            <td class="text-align-right">${(item.params.ipRating)!}</td>
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
                    </#list>
                </div>
            </div>
        </div>
    </div>
</article>

<article class="col-xs-12 col-sm-6 col-md-6 col-lg-6  sortable-grid ui-sortable">
    <div class="jarviswidget" id="standard-tab">
        <header>
            <ul class="nav nav-tabs pull-left">
                <#list (offerVo.panelList) as item>
                    <li <#if item_index==0>class="active"</#if> id="d_${(item_index)}">
                        <a href="x${item_index}" data-toggle="tab">
                            <dic data-dic="offer.create.standard"></dic>${item_index + 1}
                        </a>
                    </li>
                </#list>
            </ul>
        </header>
        <div>
            <div class="jarviswidget-editbox"></div>
            <div class="widget-body no-padding">
                <div class="tab-content padding-10" id="panel-params-dialog-1">
                    <#list (offerVo.panelList) as item>
                        <div class="tab-pane fade in <#if item_index==0>active</#if>" id="x${item_index}">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th class="no-border">
                                        <dic data-dic="product.standard.list.styType"></dic>
                                    </th>
                                    <th class="no-border">
                                        <dic data-dic="product.spare.list.brand"></dic>
                                    </th>
                                    <th class="no-border">
                                        <dic data-dic="product.standard.list.num"></dic>
                                    </th>
                                    <th class="no-border">
                                        <dic data-dic="offer.create.priceUnit"></dic>
                                    </th>
                                    <th class="no-border">
                                        <dic data-dic="offer.create.total"></dic>
                                    </th>
                                </tr>
                                </thead>
                                <#list (item.standardDetailList) as child>
                                    <tr class="no-border">
                                        <td>${(child.type)!}</td>
                                        <td>${(child.brand)!}</td>
                                        <td>${(child.realCount?c)!0}${(child.unit)!}</td>
                                        <td>${(moneyUnit)!}${(child.realPrice?c)!0}</td>
                                        <td>${(moneyUnit)!}<span class="moneyFormat">${((child.realCount*child.realPrice)?c)!0}</span></td>
                                    </tr>
                                </#list>
                                <#list (item.selfStandList) as child>
                                    <tr class="no-border">
                                        <td>${(child.spareType)!}</td>
                                        <td>${(child.brand)!}</td>
                                        <td>${(child.amount)!0}${(child.unit)!}</td>
                                        <td>${(moneyUnit)!}${(child.price)!0}</td>
                                        <td>${(moneyUnit)!}<span class="moneyFormat">${(child.amount*child.price)!0}</span></td>
                                    </tr>
                                </#list>
                            </table>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
        <div style="border: 1px solid #ccc;margin-top: -2px;" class="row">
            <label class="col col-md-3">
                <dic data-dic="offer.create.totalPrice"></dic>:
                <span>
                    <#if (offerPrice.standPrie)??>
                        ${(moneyUnit)!}<span class="moneyFormat">${((offerPrice.standPrie)/((offerPrice.standDiscount)/100))?string("#.##")}</span>
                    <#else>
                        ${(moneyUnit)!}0
                    </#if>
                </span>
            </label>
            <label class="col col-md-3">
                <dic data-dic="offer.create.discount"></dic>:
                <span>
                    <#if (offerPrice.standDiscount)??>
                    ${(offerPrice.standDiscount)?string("#.##")}%
                    <#else>
                        100%
                    </#if>
                </span>
            </label>
            <label class="col col-md-4" style="margin-bottom: 12px;">
                <dic data-dic="offer.create.real"></dic>:
                <span>
                    <#if (offerPrice.standPrie)?? && (offerPrice.standDiscount)??>
                        ${(moneyUnit)!}<span class="moneyFormat">${(offerPrice.standPrie)?string("#.##")}</span>
                    <#else>
                        ${(moneyUnit)!}0
                    </#if>
                </span>
            </label>
        </div>
    </div>
    <div class="jarviswidget" id="free-tab">
        <header>
            <ul class="nav nav-tabs pull-left">
                <#list (offerVo.panelList) as item>
                    <li <#if item_index==0>class="active"</#if> id="f_${(item_index)}">
                        <a href="z${item_index}" data-toggle="tab">
                            <dic data-dic="offer.create.free"></dic>${item_index + 1}
                        </a>
                    </li>
                </#list>
            </ul>
        </header>
        <div>
            <div class="jarviswidget-editbox"></div>
            <div class="widget-body no-padding">
                <div class="tab-content padding-10" id="panel-params-dialog-1">
                    <#list (offerVo.panelList) as item>
                        <div class="tab-pane fade in <#if item_index==0>active</#if>" id="z${item_index}">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th class="no-border">
                                        <dic data-dic="product.standard.list.styType"></dic>
                                    </th>
                                    <th class="no-border">
                                        <dic data-dic="product.spare.list.brand"></dic>
                                    </th>
                                    <th class="no-border">
                                        <dic data-dic="product.standard.list.num"></dic>
                                    </th>
                                    <th class="no-border">
                                        <dic data-dic="offer.create.priceUnit"></dic>
                                    </th>
                                    <th class="no-border">
                                        <dic data-dic="offer.create.total"></dic>
                                    </th>
                                </tr>
                                </thead>
                                <#list (item.freeDetailList) as child>
                                    <tr class="no-border">
                                        <td>${(child.type)!}</td>
                                        <td>${(child.brand)!}</td>
                                        <td>${(child.realCount?c)!0}${(child.unit)!}</td>
                                        <td>${(moneyUnit)!}${(child.realPrice?c)!0}</td>
                                        <td>${(moneyUnit)!}<span class="moneyFormat">${((child.realCount*child.realPrice)?c)!0}</span></td>
                                    </tr>
                                </#list>
                                <#list (item.selfFreeList) as child>
                                    <tr class="no-border">
                                        <td>${(child.spareType)!}</td>
                                        <td>${(child.brand)!}</td>
                                        <td>${(child.amount)!0}${(child.unit)!}</td>
                                        <td>${(moneyUnit)!}${(child.price)!0}</td>
                                        <td>${(moneyUnit)!}<span class="moneyFormat">${(child.amount*child.price)!0}</span></td>
                                    </tr>
                                </#list>
                            </table>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
        <div style="border: 1px solid #ccc;margin-top: -3px;" class="row">
            <label class="col col-md-5">&nbsp;</label>
            <label class="col col-md-3">
                <dic data-dic="offer.create.totalPrice"></dic>:
                <span>
                    <#if (offerPrice.freePrice)??>
                        ${(moneyUnit)!}<span class="moneyFormat">${(offerPrice.freePrice)?string("#.##")}</span>
                    <#else>
                    ${(moneyUnit)!}0
                    </#if>
                </span>
            </label>
            <label class="col col-md-4" style="margin-bottom: 12px;">
                <dic data-dic="offer.create.real"></dic>:
                <span>
                    <#if (offerPrice.freePrice)??>
                        ${(moneyUnit)!}<span class="moneyFormat">${(offerPrice.freePrice)?string("#.##")}</span>
                    <#else>
                    ${(moneyUnit)!}0
                    </#if>
                </span>
            </label>
        </div>
    </div>
    <div class="jarviswidget jarviswidget-sortable" id="service-panel">
        <header role="heading">
            <span class="widget-icon"><i class="fa fa-cube"></i> </span>
            <h2><strong><i><dic data-dic="offer.create.pack"></dic></i></strong></h2>
        </header>
        <div>
            <div class="jarviswidget-editbox"></div>
            <div class="widget-body no-padding">
                <div class="tab-content padding-10">
                        <div class="tab-pane fade in active" >
                            <table class="table">
                                <thead>
                                <tr>
                                    <th class="no-border">
                                        <dic data-dic="offer.create.package"></dic>
                                    </th>
                                    <th class="no-border">
                                        <dic data-dic="product.standard.list.num"></dic>
                                    </th>
                                    <th class="no-border">
                                        <dic data-dic="offer.create.priceUnit"></dic>
                                    </th>
                                    <th class="no-border">
                                        <dic data-dic="offer.create.price.sum"></dic>
                                    </th>
                                </tr>
                                </thead>
                                <#list packageList as item>
                                    <tr class="no-border">
                                        <td>${(item.packages)!}</td>
                                        <td>${(item.number)!}</td>
                                        <td>${(item.priceUnit)!0}</td>
                                        <td>${(moneyUnit)!}${(item.price)!0}</td>
                                    </tr>
                                </#list>
                            </table>
                        </div>
                </div>
            </div>
        </div>
        <div style="border: 1px solid #ccc;margin-top: -3px;" class="row">
            <label class="col col-md-4">
                <dic data-dic="package.form.terms_of_trade"></dic>:
                <span>${(transfer.trade)!}</span>
            </label>
            <label class="col col-md-4" style="margin-bottom: 12px;">
                <dic data-dic="offer.create.transport"></dic>:
                <span>
                    <#if transfer??>
                        ${(moneyUnit)!}<span class="moneyFormat">${(transfer.cost)?string("#.##")}</span>
                    <#else>
                        ${(moneyUnit)!}0
                    </#if>
                </span>
            </label>
            <label class="col col-md-3">
                <dic data-dic="offer.create.totalPrice"></dic>:
                <span>
                    ${(moneyUnit)!}<span class="moneyFormat">${(packageTotal)?string("#.##")}</span>
                </span>
            </label>
        </div>
    </div>
</article>

<article class="col-xs-12 col-sm-6 col-md-6 col-lg-6  sortable-grid ui-sortable">
    <div class="jarviswidget" id="spare-tab">
        <header>
            <ul class="nav nav-tabs pull-left">
                <#list (offerVo.panelList) as item>
                    <li <#if item_index==0>class="active"</#if> id="e_${(item_index)}">
                        <a href="y${item_index}" data-toggle="tab">
                            <dic data-dic="offer.create.spare"></dic>${item_index + 1}
                        </a>
                    </li>
                </#list>
            </ul>
        </header>
        <div>
            <div class="jarviswidget-editbox"></div>
            <div class="widget-body no-padding">
                <div class="tab-content padding-10" id="panel-params-dialog-1">
                    <#list (offerVo.panelList) as item>
                        <div class="tab-pane fade in <#if item_index==0>active</#if>" id="y${item_index}">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th class="no-border">
                                        <dic data-dic="product.standard.list.styType"></dic>
                                    </th>
                                    <th class="no-border">
                                        <dic data-dic="product.spare.list.brand"></dic>
                                    </th>
                                    <th class="no-border">
                                        <dic data-dic="product.standard.list.num"></dic>
                                    </th>
                                    <th class="no-border">
                                        <dic data-dic="offer.create.priceUnit"></dic>
                                    </th>
                                    <th class="no-border">
                                        <dic data-dic="offer.create.total"></dic>
                                    </th>
                                </tr>
                                </thead>
                                <#list (item.spareDetailList) as child>
                                    <tr class="no-border">
                                        <td>${(child.type)!}</td>
                                        <td>${(child.brand)!}</td>
                                        <td>${(child.realCount?c)!0}${(child.unit)!}</td>
                                        <td>${(moneyUnit)!}${(child.realPrice?c)!0}</td>
                                        <td>${(moneyUnit)!}<span class="moneyFormat">${((child.realCount*child.realPrice)?c)!0}</span></td>
                                    </tr>
                                </#list>
                                <#list (item.selfSpareList) as child>
                                    <tr class="no-border">
                                        <td>${(child.spareType)!}</td>
                                        <td>${(child.brand)!}</td>
                                        <td>${(child.amount)!0}${(child.unit)!}</td>
                                        <td>${(moneyUnit)!}<span class="moneyFormat">${(child.price)!0}</span></td>
                                        <td>${(moneyUnit)!}<span class="moneyFormat">${(child.amount*child.price)!0}</span></td>
                                    </tr>
                                </#list>
                            </table>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
        <div style="border: 1px solid #ccc;margin-top: -2px;" class="row">
            <label class="col col-md-3">
                <dic data-dic="offer.create.totalPrice"></dic>:
                <span>
                    <#if (offerPrice.sparePrice)??>
                        ${(moneyUnit)!}<span class="moneyFormat">${((offerPrice.sparePrice)/((offerPrice.spareDiscount)/100))?string("#.##")}</span>
                    <#else>
                         ${(moneyUnit)!}0
                    </#if>
                </span>
            </label>
            <label class="col col-md-3">
                <dic data-dic="offer.create.discount"></dic>:
            <span>
                <#if (offerPrice.spareDiscount)??>
                ${(offerPrice.spareDiscount)?string("#.##")}%
                <#else>
                    100%
                </#if>
            </span>
            </label>
            <label class="col col-md-4" style="margin-bottom: 12px;">
                <dic data-dic="offer.create.real"></dic>:
                <span>
                    <#if (offerPrice.sparePrice)?? && (offerPrice.spareDiscount)??>
                        ${(moneyUnit)!}<span class="moneyFormat">${(offerPrice.sparePrice)?string("#.##")}</span>
                    <#else>
                        ${(moneyUnit)!}0
                    </#if>
                </span>
            </label>
        </div>
    </div>
    <div class="jarviswidget jarviswidget-sortable" id="service-panel">
        <header role="heading">
            <span class="widget-icon"><i class="fa fa-cube"></i> </span>
            <h2><strong><i><dic data-dic="offer.create.service"></dic></i></strong></h2>
        </header>
        <div id="service_part">
            <div class="widget-body no-padding">
                <form class="smart-form" id="service-form" style="word-break: keep-all">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="no-border">
                                <dic data-dic="offer.create.serviceType"></dic>
                            </th>
                            <th class="no-border">
                                <dic data-dic="product.standard.list.num"></dic>
                            </th>
                            <th class="no-border">
                                <dic data-dic="offer.create.priceUnit"></dic>
                            </th>
                            <th class="no-border">
                                <dic data-dic="offer.create.total"></dic>
                            </th>
                        </tr>
                        </thead>
                        <#list (offerVo.serviceList) as item>
                            <tr class="no-border">
                                <td>${(item.name)!}</td>
                                <td>${(item.counts)!0}</td>
                                <td>${(moneyUnit)!}<span class="moneyFormat">${(item.price)!0}</span></td>
                                <td>${(moneyUnit)!}<span class="moneyFormat">${(item.counts*item.price)!0}</span></td>
                            </tr>
                        </#list>
                    </table>
                </form>
            </div>
        </div>
        <div style="border: 1px solid #ccc;margin-top: -2px;" class="row">
            <label class="col col-md-3">
                <dic data-dic="offer.create.totalPrice"></dic>:
                <span>
                    <#if (offerPrice.servicePrice)??>
                        ${(moneyUnit)!}<span class="moneyFormat">${(offerPrice.servicePrice)?string("#.##")}</span>
                    <#else>
                    ${(moneyUnit)!}0
                    </#if>
                </span>
            </label>
            <label class="col col-md-3">
                <dic data-dic="offer.create.discount"></dic>:
                <span>
                    <#if (offerPrice.serviceDiscount)??>
                    ${(offerPrice.serviceDiscount)?string("#.##")}%
                    <#else>
                        100%
                    </#if>
            </span>
            </label>
            <label class="col col-md-4" style="margin-bottom: 12px;">
                <dic data-dic="offer.create.real"></dic>:
                <span>
                    <#if (offerPrice.servicePrice)?? && (offerPrice.serviceDiscount)??>
                        ${(moneyUnit)!}<span class="moneyFormat">${((offerPrice.servicePrice)/((offerPrice.serviceDiscount)/100))?string("#.##")}</span>
                    <#else>
                    ${(moneyUnit)!}0
                    </#if>
                </span>
            </label>
        </div>
    </div>
    <div class="jarviswidget" style="border-top:1px solid #CCC;">
        <div style="border-bottom: 1px dashed #cccccc !important; padding-bottom: 10px" class="row">
            <form class="smart-form">
                <label class="col col-md-2 text-align-right no-left no-right">
                    <dic data-dic="offer.create.project.name"></dic>:
                </label>
                <label class="col col-md-10">
                ${(offerVo.myOffer.projectName)!}
                </label>
            </form>
        </div>
        <div style="border-bottom: 1px dashed #cccccc !important; padding-bottom: 10px" class="row">
            <form class="smart-form">
                <label class="col col-md-2 text-align-right no-left no-right">
                    <dic data-dic="offer.create.remark"></dic>:
                </label>
                <label class="col col-md-10">
                ${(offerVo.myOffer.remark)!}
                </label>
                <#--<label class="col col-md-1 text-align-right no-left no-right">-->
                    <#--<dic data-dic="offer.create.logisticsCost"></dic>:-->
                <#--</label>-->
                <#--<label class="col col-md-2">-->
                <#--${(transfer.cost)!}-->
                <#--</label>-->
            </form>
        </div>
        <div class="row">
            <label class="col col-md-3">
                <dic data-dic="offer.create.totalPrice"></dic>:
                <span>
                    <#if offerPrice.totalPrice??>
                    ${(moneyUnit)!}<span class="moneyFormat">${((offerPrice.totalPrice)/(offerPrice.totalDiscount/100))?string("#.##")}</span>
                    <#else>
                    ${(moneyUnit)!}0
                    </#if>
                </span>
            </label>
            <label class="col col-md-3">
                <dic data-dic="offer.create.discount"></dic>:
                <span>
                    <#if offerPrice.totalDiscount??>
                    ${offerPrice.totalDiscount?string("#.##")}%
                    <#else>
                        100%
                    </#if>
                </span>
            </label>
            <label class="col col-md-4" style="margin-bottom: 12px;">
                <dic data-dic="offer.create.finalPrices"></dic>:
                <span>
                    <#if offerPrice.panelPrice?? && offerPrice.totalDiscount??>
                    ${(moneyUnit)!}<span class="moneyFormat">${(offerPrice.totalPrice)?string("#.##")}</span>
                    <#else>
                    ${(moneyUnit)!}0
                    </#if>
                </span>
            </label>
            <label class="col col-md-2 text-align-right"
                   style="padding-left: 0 !important;padding-right: 0 !important;">
                <a class="btn btn-default" style="margin-top: -7px;" onclick="list()">
                    <dic data-dic="button.back"></dic>
                </a>
            </label>
        </div>
    </div>
</article>

<div class="stepshow">
    <span id="stepshow-setting">
        <i class="fa fa-flag"></i>
    </span>
    <form style="display: none" class="product-info-look">
        <section>
            <article class="panel-input col-xs-12 col-sm-4 col-md-4 col-lg-4 sortable-grid ui-sortable" style="width: 370px">
                <div class="jarviswidget">
                    <header>
                        <ul class="nav nav-tabs pull-left">
                            <li class="active">
                                <a data-toggle="tab">
                                    <span class="hidden-mobile hidden-tablet"><dic data-dic="offer.create.productInfo"></dic><span role="index"></span></span>
                                </a>
                            </li>
                        </ul>
                    </header>
                    <div>
                        <div class="jarviswidget-editbox"></div>
                        <div class="widget-body no-padding">
                            <div class="tab-content padding-10">
                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="offer.create.product"></dic>：</label>
                                    <label class="col col-md-8">
                                        <span role="productName">${(offerVo.myOffer.seriesName)!}</span>
                                    </label>
                                </div>
                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="offer.create.certification"></dic>：</label>
                                    <label class="col col-md-8">
                                        <#if offerVo.myOffer?? && offerVo.myOffer.certification??>
                                            <span role="cert">${(offerVo.myOffer.certification)!}</span>
                                        <#else>
                                            <span role="cert">Basics</span>
                                        </#if>

                                    </label>
                                </div>
                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="product.panel.list.configuration"></dic>：</label>
                                    <label class="col col-md-8">
                                        <#if offerVo.myOffer?? && offerVo.myOffer.remark??>
                                            <span role="cert">${(offerVo.myOffer.remark)!}</span>
                                        <#else>
                                            <span role="cert" data-dic="offer.create.step1.config.global"></span>
                                        </#if>
                                    </label>
                                </div>
                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="product.panel.list.status"></dic>：</label>
                                    <label class="col col-md-8">
                                        <#if offerVo.myOffer?? && offerVo.myOffer.status??>
                                            <span role="config" data-dic="offer.create.step1.status.${(offerVo.myOffer.status)!}"></span>
                                        <#else>
                                            <span data-dic="offer.create.step1.status.0"></span>
                                        </#if>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </article>
            <article class="panel-input col-xs-12 col-sm-4 col-md-4 col-lg-4 sortable-grid ui-sortable" style="width: 370px">
                <div class="jarviswidget">
                    <header>
                        <ul class="nav nav-tabs pull-left">
                            <li class="active">
                                <a data-toggle="tab" href="#">
                                    <span class="hidden-mobile hidden-tablet"><dic data-dic="product.panel.list.configurationInfo"></dic><span></span></span>
                                </a>
                            </li>
                        </ul>
                    </header>
                    <div>
                        <div class="jarviswidget-editbox"></div>
                        <div class="widget-body no-padding">
                            <div class="tab-content padding-10">
                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="quotes.offer.list.customer"></dic>：</label>
                                    <label class="col col-md-8">
                                        <span role="customer">${(offerVo.myOffer.customerName)!}</span>
                                    </label>
                                </div>
                                <#--<div class="row pading-seven">-->
                                    <#--<label class="col col-md-4 text-align-right"><dic data-dic="quotes.offer.list.priceArea"></dic>：</label>-->
                                    <#--<label class="col col-md-8">-->
                                        <#--<span role="area">${(offerVo.myOffer.customerName)!}</span>-->
                                    <#--</label>-->
                                <#--</div>-->
                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="offer.create.monetaryUnit"></dic>：</label>
                                    <label class="col col-md-8">
                                        <span role="moneyType">${(offerVo.myOffer.moneyUnit)!}</span>
                                    </label>
                                </div>

                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="offer.create.sizeUnit"></dic>：</label>
                                    <label class="col col-md-8">
                                        <#if offerVo.myOffer.sizeUnit?? && offerVo.myOffer.sizeUnit == '1'>
                                            <span role="lengthType" data-dic="offer.create.meters">米(m)</span>
                                        <#elseif offerVo.myOffer.sizeUnit?? && offerVo.myOffer.sizeUnit == '2'>
                                            <span role="lengthType" data-dic="offer.create.foot">英尺(ft)</span>
                                        <#else>
                                            <span role="lengthType"></span>
                                        </#if>
                                        <span role="lengthType"></span>
                                    </label>
                                </div>
                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="quotes.offer.list.guarantee"></dic>：</label>
                                    <label class="col col-md-8">
                                        <span role="autogen">${(offerVo.myOffer.guarantee)!}<span data-dic="portal.my.years">年</span></span>
                                    </label>
                                </div>
                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="quotes.offer.list.payment"></dic>：</label>
                                    <label class="col col-md-8">
                                        <span role="payment">${(offerVo.myOffer.payment)!}</span>
                                    </label>
                                </div>
                                <#--<div class="row">-->
                                    <#--<label class="col col-md-4 text-align-right"><dic data-dic="offer.create.boundary"></dic>：</label>-->
                                    <#--<label class="col col-md-8">-->
                                        <#--<select style="width: 214px" class="select2" role="boundary"></select>-->
                                    <#--</label>-->
                                <#--</div>-->
                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="offer.create.company"></dic>：</label>
                                    <label class="col col-md-8">
                                        <span  role="trader">${(offerVo.myOffer.trader)!}</span>
                                    </label>
                                </div>
                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="quotes.offer.list.waitingDate"></dic>：</label>
                                    <label class="col col-md-8">
                                        <span>${(offerVo.myOffer.waitingDate)!}</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </article>
        </section>
    </form>
</div>

<script type="text/javascript">
    $("dic").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });
    $("[data-dic]").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });
    $("#stepshow-setting").click(function () {
        $(this).parent().toggleClass("activate");
        $(this).parent().find("form:first").toggle();
    });
    function list() {
        showLoading();
        if (
            window.location.hash === "#/approval/launch" ||
            window.location.hash === "#/approval/wait" ||
            window.location.hash === "#/approval/already"||
            window.location.hash === "#/offer/list"
        ) {
            $('#first').show();
            dt.resetFilter();
            $("#second").html('');
            $("#second").hide();
        } else {
            window.location.href = "/#/offer/list";
        }
        hideLoading();
    }
    var $box_tab = $("#box-tab");
//    var $module_tab = $("#module-tab");
//    var $params_tab = $("#params-tab");
    var $panel_tab = $('#panel-tab');
    var $standard_tab = $("#standard-tab");
    var $spare_tab = $("#spare-tab");
    var $free_tab = $("#free-tab");
    //页面展示之后执行的函数
    $panel_tab.find('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
        var tabId = $panel_tab.find('.tab-pane.active').attr('id');
        var index = tabId.substr(1);
        $box_tab.find('.tab-pane.active').removeClass('active');
        $box_tab.find('.nav.nav-tabs .active').removeClass('active');
//        $module_tab.find('.tab-pane.active').removeClass('active');
//        $module_tab.find('.nav.nav-tabs .active').removeClass('active');
//        $params_tab.find('.tab-pane.active').removeClass('active');
//        $params_tab.find('.nav.nav-tabs .active').removeClass('active');
        $standard_tab.find('.tab-pane.active').removeClass('active');
        $standard_tab.find('.nav.nav-tabs .active').removeClass('active');
        $spare_tab.find('.tab-pane.active').removeClass('active');
        $spare_tab.find('.nav.nav-tabs .active').removeClass('active');
        $free_tab.find('.tab-pane.active').removeClass('active');
        $free_tab.find('.nav.nav-tabs .active').removeClass('active');
    });
    //页面展示之后执行的函数
    $panel_tab.find('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        var tabId = $panel_tab.find('.tab-pane.active').attr('id');
        var index = tabId.substr(1);
        $box_tab.find('#a_' + index).addClass('active in');
        $box_tab.find('#u' + index).addClass('active in');
//        $module_tab.find('#b_' + index).addClass('active in');
//        $module_tab.find('#v' + index).addClass('active in');
//        $params_tab.find('#c_' + index).addClass('active in');
//        $params_tab.find('#w' + index).addClass('active in');
        $standard_tab.find('#d_' + index).addClass('active in');
        $standard_tab.find('#x' + index).addClass('active in');
        $spare_tab.find('#e_' + index).addClass('active in');
        $spare_tab.find('#y' + index).addClass('active in');
        $free_tab.find('#f_' + index).addClass('active in');
        $free_tab.find('#z' + index).addClass('active in');
    });
    $('.moneyFormat').each(function () {
        $(this).html($(this).html().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
    });
</script>
<style>
    .new-fieldset {
        border-bottom: 1px dashed #cccccc !important;
        height: auto !important;
        padding-left: 0 !important;
        padding-right: 0 !important;
        padding-top: 5px !important;
    }

    .params-table td {
        border-top: 1px dashed rgba(0,0,0,.2) !important;
    }
    .table td.text-align-right {
        border-right: 1px dashed #00000033;
    }
    .jarviswidget {
        margin: 0 0 10px;
    }
    .table .no-border td {
        border-top: 0 !important;
    }
    header {
        cursor: auto !important;
    }
    .no-left {
        padding-left: 0 !important;
    }

    .no-left.no-right {
        text-align: right;
    }
    #choose_spare_dialog label i {
        margin-top: -3px;
    }
</style>
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

    .param-table td.text-align-right{
        border-right: 1px dashed #00000033;
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
    .jarviswidget{
        margin:0 0 10px;
    }
    .padding7{
        padding-top:7px;
    }
    .no-left{
        padding-left:0 !important;
    }

    .no-right{
        padding-right:0 !important;
    }

    .no-left.no-right{
        text-align: right;
    }
    #choose_spare_dialog label i{
        margin-top:-3px;
    }
    .product-info-look .col-md-4{
        padding-right: 0;
    }
    .pading-seven{
        padding-bottom: 7px;
    }
</style>
</#compress>