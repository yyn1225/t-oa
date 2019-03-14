<#compress>
<div class="widget-body">
    <form class="smart-form addOrEdit-form" id="params-form">

        <fieldset>
            <input type="hidden" name="productId" value="${(productId?c)!}"/>
            <input type="hidden" name="paramsId" id="paramsId" value="${(params.id?c)!0}"/>
            <input type="hidden" class="submit_url" value="/product/params/save">
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.control"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" id="control" name="control" value="${(params.control)!}"
                       data-zh="${(params.control)!}" data-en="${(paramsLang["en"].controlLang)!}" data-category="params_control"/>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.calibration"></dic>
            </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" name="calibration" id="calibration">
                    <#if params.calibration??>
                        <option value="1" data-dic="select.option.yes" <#if params.calibration == 1>selected</#if>>
                        </option>
                        <option value="0" data-dic="select.option.no" <#if params.calibration == 0>selected</#if>>
                        </option>
                    <#else>
                        <option value="1" data-dic="select.option.yes"></option>
                        <option value="0" data-dic="select.option.no"></option>
                    </#if>
                </select>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.intelligent"></dic>:
            </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" name="intelligent" id="intelligent">
                    <#if params.intelligent??>
                        <option value="1" <#if params.intelligent == 1>selected</#if> data-dic="select.option.yes">
                        </option>
                        <option value="0" <#if params.intelligent == 0>selected</#if> data-dic="select.option.no">
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
                <dic data-dic="product.panel.form.rigging"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" class="spinners" name="rigging" id="rigging" value="${(params.rigging?c)!}">
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.stack"></dic>:
                </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" name="stack">
                    <#if params.stack??>
                        <option value="1" <#if params.stack == 1>selected</#if> data-dic="select.option.yes">
                        </option>
                        <option value="0" <#if params.stack == 0>selected</#if> data-dic="select.option.no">
                        </option>
                    <#else>
                        <option value="1" data-dic="select.option.yes"></option>
                        <option value="0" data-dic="select.option.no"></option>
                    </#if>
                </select>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.front"></dic>:
            </label>
            <label class="col col-3 input">
                <select class="select2" style="width: 100%" name="front">
                    <#if params.front??>
                        <option value="1" <#if params.front == 1>selected</#if> data-dic="select.option.yes">
                        </option>
                        <option value="0" <#if params.front == 0>selected</#if> data-dic="select.option.no">
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
                <dic data-dic="product.panel.form.fixModual"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" id="fixModual" name="fixModual" value="${(params.fixModual)!}"
                       data-zh="${(params.fixModual)!}" data-en="${(paramsLang["en"].fixModualLang)!}" data-category="params_fixModule"/>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.fixPsu"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" id="fixPsu" name="fixPsu" value="${(params.fixPsu)!}"
                       data-zh="${(params.fixPsu)!}" data-en="${(paramsLang["en"].fixPsu)!}" data-category="params_fixPsu"/>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.ipRating"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" name="ipRating" value="${(params.ipRating)!}"/>
            </label>
        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.brightness"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" name="brightness" value="${(params.brightness)!}"/>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.contrastRatio"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" name="contrastRatio" value="${(params.contrastRatio)!}"/>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.grayScale"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" name="grayScale" value="${(params.grayScale)!}"/>
            </label>
        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.refresh"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" name="refresh" value="${(params.refresh)!}"/>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.viewing"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" name="viewing" value="${(params.viewing)!}"/>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.powerMax"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" class="spinners" name="powerMax" value="${(params.powerMax?c)!}"/>
            </label>
        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.powerAvg"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" class="spinners" name="powerAvg" value="${(params.powerAvg?c)!}"/>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.drivingIc"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" name="drivingIc" value="${(params.drivingIc)!}"/>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.drivingType"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" name="drivingType" value="${(params.drivingType)!}"/>
            </label>
        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.psu"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" name="psu" value="${(params.psu)!}"/>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.psuPower"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" class="spinners" name="psuPower" value="${(params.psuPower?c)!}"/>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.psuCount"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" name="psuCount" value="${(params.psuCount)!}"/>
            </label>
        </fieldset>

        <fieldset>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.standardCarryLower"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" class="spinners" name="standardCarryLower"
                       value="${(params.standardCarryLower?c)!}"/>
            </label>
            <label class="col col-1 text-align-right padding">
                <dic data-dic="product.panel.form.standardCarryHigh"></dic>:
            </label>
            <label class="col col-3 input">
                <input type="text" class="spinners" name="standardCarryHigh" value="${(params.standardCarryHigh?c)!}"/>
            </label>
        </fieldset>
    </form>
</div>
<script type="text/javascript">
    $('[data-dic]').each(function () {
        $(this).html(message[$(this).data("dic")]);
    });
    $(function () {
        $(".spinners").spinner({
            step: 10,
            textFormat: "n"
        });
    });
    $("#params-form").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            box: {
                group: ".col.col-3",
                validators: {
                    notEmpty: {
                        message: message["the.box.can.not.be.empty"]
                    }
                }
            },
            rigging: {
                group: ".col.col-3",
                validators: {
                    digits: {
                        message: message["validate.message.integer"]
                    }
                }
            },
            powerMax: {
                group: ".col.col-3",
                validators: {
                    digits: {
                        message: message["validate.message.number"]
                    }
                }
            },
            powerAvg: {
                group: ".col.col-3",
                validators: {
                    digits: {
                        message: message["validate.message.number"]
                    }
                }
            },
            psuPower: {
                group: ".col.col-3",
                validators: {
                    digits: {
                        message: message["validate.message.number"]
                    }
                }
            },
            standardCarryLower: {
                group: ".col.col-3",
                validators: {
                    digits: {
                        message: message["validate.message.number"]
                    }
                }
            },
            standardCarryHigh: {
                group: ".col.col-3",
                validators: {
                    digits: {
                        message: message["validate.message.number"]
                    }
                }
            }
        }
    });
</script>
</#compress>