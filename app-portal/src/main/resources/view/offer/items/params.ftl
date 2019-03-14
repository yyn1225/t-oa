<#compress>
<div>
    <div class="tab-pane fade in active param-table" id="param-${tag}-1">
        <div class="smart-form">
            <div class="fnt">
                <p class="pline"><dic class="ml5 mr5" data-dic="offer.create.modual"></dic></p>
                <table class="table" style="width: 100%">
                    <tbody style="border-left: 1px dashed #ccc;">
                    <tr class="no-border">
                        <td class="text-align-left first-td"><dic data-dic="offer.create.module.size"></dic></td>
                        <td class="text-align-right first-td">${(modual.width?c)!0}&#42;${(modual.height?c)!0}</td>
                        <td class='text-align-left first-td'><dic data-dic="offer.create.module.pix"></dic></td>
                        <td class='text-align-right first-td'>${(modual.transverse?c)!0}&#42;${(modual.portrait?c)!0}</td>
                        <td class="text-align-left first-td"><dic data-dic="offer.view.spacing"></dic></td>
                        <td class="text-align-right first-td">${(modual.pitch)!0}</td>
                        <td class='text-align-left first-td'><dic data-dic="offer.create.module.density"></dic></td>
                        <td class='text-align-right first-td'>${(((1000/modual.pitch)*(1000/modual.pitch))?int)?c}</td>
                    </tr>
                    <tr>
                        <td class='text-align-left'><dic data-dic="offer.create.module.weight"></dic></td>
                        <td class='text-align-right'>${(modual.weight?c)!0}kg</td>
                        <td class='text-align-left'><dic data-dic="offer.view.type"></dic></td>
                        <td class='text-align-right'>${(modual.configuration)!0}</td>
                        <td class="text-align-left"></td>
                        <td class="text-align-right"></td>
                        <td class='text-align-left'></td>
                        <td class='text-align-right'></td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="fnt" style="margin-top: 10px;">
                <p class="pline panel-index"><dic class="ml5 mr5" data-dic="offer.create.box"></dic></p>
                <table class="table" style="width: 100%">
                    <tbody style="border-left: 1px dashed #ccc;">
                    <tr class="no-border">
                        <td class="text-align-left first-td"><dic data-dic="offer.create.box.size"></dic></td>
                        <td class="text-align-right first-td">${(box.width?c)!0}&#42;${(box.height?c)!0}&#42;${(box.thickness?c)!0}</td>
                        <td class='text-align-left first-td'><dic data-dic="offer.create.box.pix"></dic></td>
                        <td class='text-align-right first-td'>${(box.transversePix?c)!0}&#42;${(box.portraitPix?c)!0}</td>
                        <td class="text-align-left first-td"><dic data-dic="offer.create.box.number"></dic></td>
                        <td class="text-align-right first-td">${((box.transverseCount*box.portraitCount)?c)!0}</td>
                        <td class='text-align-left first-td' style="width: 10% !important;"><dic data-dic="offer.create.box.display"></dic></td>
                        <td class='text-align-right first-td' style="width: 15% !important;">${((box.transversePix*box.portraitPix)?c)!0}</td>
                    </tr>
                    <tr>
                        <td class='text-align-left'><dic data-dic="offer.create.module.weight"></dic></td>
                        <td class='text-align-right'>${((box.weight)?c)!0}kg</td>
                        <td class="text-align-left"><dic data-dic="offer.create.params.powerAvg"></dic></td>
                        <td class="text-align-right">${(param.powerAvg?c)!0}</td>
                        <td class='text-align-left'><dic data-dic="offer.create.params.powerMax"></dic></td>
                        <td class='text-align-right'>${(param.powerMax?c)!0}</td>
                        <td class='text-align-left' style="width: 10% !important;"><dic data-dic="offer.create.BoxMaterial"></dic></td>
                        <td class='text-align-right' style="width: 15% !important;">${(box.boxType)!}</td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="fnt" style="margin-top: 10px;">
                <p class="pline"><dic class="ml5 mr5" data-dic="offer.create.params"></dic></p>
                <table class="table" style="width: 100%">
                    <tbody style="border-left: 1px dashed #ccc;">
                        <tr class="no-border">
                            <td class="text-align-left font-important first-td"><dic data-dic="offer.view.pix"></dic></td>
                            <td class="text-align-right font-important first-td totalPix" data-ct1="${(box.transversePix)!0}" data-ct2="${(box.portraitPix)!0}"></td>
                            <td class='text-align-left font-important first-td'><dic data-dic="product.panel.form.powerAvg"></dic></td>
                            <td class='text-align-right font-important first-td powerAvg' data-counts="${(param.powerAvg)!0}"></td>
                            <td class='text-align-left font-important first-td'><dic data-dic="product.panel.form.powerMax"></dic></td>
                            <td class='text-align-right font-important first-td powerMax' data-counts="${(param.powerMax)!0}"></td>
                            <td class='text-align-left first-td'><dic data-dic="product.panel.list.color"></dic></td>
                            <td class='text-align-right first-td'>${(panel.color)!}</td>
                        </tr>
                        <tr>
                            <td class='text-align-left'><dic data-dic="offer.create.singlePower"></dic></td>
                            <td class='text-align-right'>${(param.psuPower)!}</td>
                            <td class="text-align-left"><dic data-dic="product.panel.form.brightness"></dic></td>
                            <td class="text-align-right">${(param.brightness)!}</td>
                            <td class='text-align-left'><dic data-dic="product.panel.form.brightnessControl"></dic></td>
                            <td class='text-align-right'>256</td>
                            <td class='text-align-left'><dic data-dic="product.panel.form.viewingAngle"></dic></td>
                            <td class='text-align-right'>${(param.viewing)!}</td>
                        </tr>
                        <tr>
                            <td class='text-align-left'><dic data-dic="product.panel.form.minDistance"></dic></td>
                            <td class='text-align-right'>${(modual.pitch)!0}m</td>
                            <td class="text-align-left"><dic data-dic="product.panel.form.grayScale"></dic></td>
                            <td class="text-align-right">${(param.grayScale)!}</td>
                            <td class='text-align-left'><dic data-dic="product.panel.form.refresh"></dic></td>
                            <td class='text-align-right'>${(param.refresh)!}</td>
                            <td class='text-align-left'><dic data-dic="product.panel.form.contrastRatio"></dic></td>
                            <td class='text-align-right'>${(param.contrastRatio)!}</td>
                        </tr>
                        <tr>
                            <td class='text-align-left'><dic data-dic="product.panel.form.drivingType"></dic></td>
                            <td class='text-align-right'>${(param.drivingType)!}</td>
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
                            <td class="text-align-right">${(param.ipRating)!}</td>
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
    <div class="tab-pane fade in active" id="spare-${tag}-1">
        <div class="row">
            <div class="col col-md-12 spare">
                <form class="smart-form temp">
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                <fieldset style="border-right: 1px dashed #ccc;border-bottom: 1px dashed #ccc;" id="screen-fieldset-${tag}">
                    <label class="col col-3 text-align-left padding7">LED-Screen</label>
                    <label class="col col-2 text-align-left padding7" id="screen-brand-${tag}"></label>
                    <label class="col col-2 text-align-left padding7" tag="total-area" style="padding-left: 0;padding-right: 0">
                        <input readonly class="acreage" amount/>
                    </label>
                    <label class="col col-2 no-padding margin-left-10">
                        <input sales class="form-control" type="number" value="100" tag="reference" style="width: 100%;"/>
                    </label>
                    <label class="col col-2  text-align-right padding7" id="screen-total-${tag}"><span price>0.00</span></label>
                    <label class="edit-price" title="此处调整主屏体单价&折扣">
                        <a class="edit-a"><i class="fa fa-pencil"></i></a>
                    </label>
                    <label class="col col-md-12 spare-desc">
                        <span><dic data-dic="customer.item.description"></dic>:</span><span>~</span>
                    </label>
                </fieldset>
            </div>
            <#list standards as item>
                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                    <fieldset role="spares" data-id="${(item.id)?c}"
                              data-spel2="${(item.spel2Year)!}" data-type2="${(item.type2Year)!}" data-count2="${(item.count2Year?c)!}"
                              data-spel3="${(item.spel3Year)!}" data-type3="${(item.type3Year)!}" data-count3="${(item.count3Year?c)!}"
                              data-spel4="${(item.spel4Year)!}" data-type4="${(item.type4Year)!}" data-count4="${(item.count4Year?c)!}"
                              data-spel5="${(item.spel5Year)!}" data-type5="${(item.type5Year)!}" data-count5="${(item.count5Year?c)!}"
                              data-cost="${(item.costPrice?c)!0}" data-sale="${(item.salePrice?string("#.####"))!0}" style="border-right: 1px dashed #ccc;border-bottom: 1px dashed #ccc">
                        <label title="${(item.type)!}" class="col col-3 text-align-left padding7 text-spare">${(item.type)!}</label>
                        <label class="col col-2 text-align-left padding7">${(item.brand)!}</label>
                        <label class="col col-1 long-label">
                            <input amount class="form-control" type="number" value="0"/>
                        </label>
                        <label class="col col-1 padding7 short-label">${(item.unit)!}</label>
                        <label class="col col-2 no-padding margin-left-10">
                            <#if (item.salePrice)??>
                                <input sales class="form-control" type="number" value="${(item.salePrice)?string("#.####")}"/>
                            <#else>
                                <input sales class="form-control" type="number" value="0"/>
                            </#if>
                        </label>
                        <label class="col col-2  text-align-right padding7">${money}<span price>0.00</span></label>
                        <label class="trash">
                            <a class="trash-a"><i class="fa fa-trash-o"></i></a>
                        </label>
                        <label class="col col-md-12 spare-desc">
                            <span><dic data-dic="customer.item.description"></dic>:</span><span class="desc">${(item.description)!}</span>
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
                <span class="moneyUnit"></span><span tag="total-sale">0.00</span>
            </label>
            <label class="col col-md-2 text-align-right no-left no-right">
                <dic data-dic="offer.create.discount"></dic>：
            </label>
            <label class="col col-md-1" style="margin-top: -7px;">
                <input type="number" tag="total-discount" value="100">
            </label>
            <label class="col col-md-2 text-align-right">
                <dic data-dic="offer.create.real"></dic>：
                <span class="moneyUnit"></span><span tag="total-real">0.00</span>
            </label>
        </div>
    </div>
    <div class="tab-pane fade in active" id="spare-${tag}-2">
        <div class="row">
            <div class="col col-md-12 spare">
                <form class="smart-form temp">
                    <#list optionals as item>
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                            <fieldset role="spares" data-id="${(item.id)?c}"
                                      data-spel2="${(item.spel2Year)!}" data-type2="${(item.type2Year)!}" data-count2="${(item.count2Year?c)!}"
                                      data-spel3="${(item.spel3Year)!}" data-type3="${(item.type3Year)!}" data-count3="${(item.count3Year?c)!}"
                                      data-spel4="${(item.spel4Year)!}" data-type4="${(item.type4Year)!}" data-count4="${(item.count4Year?c)!}"
                                      data-spel5="${(item.spel5Year)!}" data-type5="${(item.type5Year)!}" data-count5="${(item.count5Year?c)!}"
                                      data-cost="${(item.costPrice?c)!0}" data-sale="${(item.salePrice?string("#.####"))!0}" style="border-right: 1px dashed #ccc;border-bottom: 1px dashed #ccc">
                                <label title="${(item.type)!}" class="col col-3 text-align-left padding7 text-spare">${(item.type)!}</label>
                                <label class="col col-2 text-align-left padding7">${(item.brand)!}</label>
                                <label class="col col-1 long-label">
                                    <input amount class="form-control" type="number" value="0"/>
                                </label>
                                <label class="col col-1 padding7 short-label">${(item.unit)!}</label>
                                <label class="col col-2 no-padding margin-left-10">
                            <#if (item.salePrice)??>
                                <input sales class="form-control" type="number" value="${(item.salePrice)?string("#.####")}"/>
                            <#else>
                                <input sales class="form-control" type="number" value="0"/>
                            </#if>
                                </label>
                                <label class="col col-2  text-align-right padding7">${money}<span price>0.00</span></label>
                                <label class="trash">
                                    <a class="trash-a"><i class="fa fa-trash-o"></i></a>
                                </label>
                                <label class="col col-md-12 spare-desc">
                                    <span><dic data-dic="customer.item.description"></dic>:</span><span class="desc">${(item.description)!}</span>
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
                <span class="moneyUnit"></span><span tag="total-sale">0.00</span>
            </label>
            <label class="col col-md-2 text-align-right no-left no-right">
                <dic data-dic="offer.create.discount"></dic>：
            </label>
            <label class="col col-md-1" style="margin-top: -7px;">
                <input type="number" tag="total-discount" value="100"></label>
            <label class="col col-md-2 text-align-right">
                <dic data-dic="offer.create.real"></dic>：
                <span class="moneyUnit"></span><span tag="total-real">0.00</span>
            </label>
        </div>
    </div>
    <div class="tab-pane fade in active" id="spare-${tag}-3">
        <div class="row">
            <div class="col col-md-12 spare">
                <form class="smart-form temp">
            <#list frees as item>
                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                    <fieldset role="spares" data-id="${(item.id)?c}"
                              data-spel2="${(item.spel2Year)!}" data-type2="${(item.type2Year)!}" data-count2="${(item.count2Year?c)!}"
                              data-spel3="${(item.spel3Year)!}" data-type3="${(item.type3Year)!}" data-count3="${(item.count3Year?c)!}"
                              data-spel4="${(item.spel4Year)!}" data-type4="${(item.type4Year)!}" data-count4="${(item.count4Year?c)!}"
                              data-spel5="${(item.spel5Year)!}" data-type5="${(item.type5Year)!}" data-count5="${(item.count5Year?c)!}"
                              data-cost="${(item.costPrice?c)!0}" data-sale="${(item.salePrice?string("#.####"))!0}" style="border-right: 1px dashed #ccc;border-bottom: 1px dashed #ccc">
                        <label title="${(item.type)!}"  class="col col-3 text-align-left padding7 text-spare">${(item.type)!}</label>
                        <label class="col col-2 text-align-left padding7">${(item.brand)!}</label>
                        <label class="col col-1 long-label">
                            <input amount class="form-control" type="number" value="0"/>
                        </label>
                        <label class="col col-1 padding7 short-label">${(item.unit)!}</label>
                        <label class="col col-2 no-padding margin-left-10">
                            <#if (item.salePrice)??>
                                <input sales class="form-control" type="number" value="${(item.salePrice)?string("#.####")}"/>
                            <#else>
                                <input sales class="form-control" type="number" value="0"/>
                            </#if>
                        </label>
                        <label class="col col-2  text-align-right padding7">${money}<span price>0.00</span></label>
                        <label class="trash">
                            <a class="trash-a"><i class="fa fa-trash-o"></i></a>
                        </label>
                        <label class="col col-md-12 spare-desc">
                            <span><dic data-dic="customer.item.description"></dic>:</span><span class="desc">${(item.description)!}</span>
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
                <dic data-dic="offer.create.freeTotal"></dic>：<span class="moneyUnit"></span><span tag="total-sale">0.00</span>
            </label>
            <label class="col col-md-2 text-align-right" style="margin-bottom: 12px;">
                <dic data-dic="offer.create.real"></dic>：<span class="moneyUnit"></span><span>0.00</span>
            </label>
        </div>
    </div>

    <script id="param-${tag}-js">
        $("[data-dic]").each(function() {
            $(this).html(message[$(this).data("dic")]);
        });
        $("#panel-"+'${tag}').find("fieldset").eq('${fieldsetIndex}').data("width",${(box.width?c)!100}/1000);
        $("#panel-"+'${tag}').find("fieldset").eq('${fieldsetIndex}').data("height",${(box.height?c)!100}/1000);
        $("#panel-"+'${tag}').find("fieldset").eq('${fieldsetIndex}').data("price",${((price.price)?c)!100});
        $("#panel-"+'${tag}').find("fieldset").eq('${fieldsetIndex}').data("sales",${((price.salePrice)?string("#.####"))!100});
        $("#panel-"+'${tag}').find("fieldset").eq('${fieldsetIndex}').data("ave",${(param.powerAvg?c)!0});
        $("#panel-"+'${tag}').find("fieldset").eq('${fieldsetIndex}').data("top",${(param.powerMax?c)!0});
        $("#panel-"+'${tag}').find("fieldset").eq('${fieldsetIndex}').data("boxwidth",${(param.transverseCount)!1});
        $("#panel-"+'${tag}').find("fieldset").eq('${fieldsetIndex}').data("boxheight",${(param.portraitCount)!1});
        $("#panel-"+'${tag}').find("fieldset").eq('${fieldsetIndex}').data("transversePix",${(box.transversePix?c)!0});
        $("#panel-"+'${tag}').find("fieldset").eq('${fieldsetIndex}').data("portraitPix",${(box.portraitPix?c)!0});
        $("#panel-"+'${tag}').find("fieldset").eq('${fieldsetIndex}').data("partNo",'${(panel.partNo)!}');
        var price = parseFloat(${(price.salePrice?string("0.##"))!100.00});
        $("#spare-${tag}-1").find("input[tag='reference']").val((price*squareType).toFixed(2));
        $("#screen-brand-" + '${tag}').html($("#panel-"+'${tag}').find('[name="product"]').select2('data').text);
    </script>
</div>
</#compress>