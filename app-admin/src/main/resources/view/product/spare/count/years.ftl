<#compress>
<form id="yearForm" class="smart-form">
    <input type="hidden" id="standardId" name="standardId"
           value="${(standard.id?c)!}"/>
    <input type="hidden" id="standardType" name="standardId"
           value="${(standard.type)!}"/>
    <fieldset>
        <label class="col col-2 text-align-right padding">
            <dic data-dic="standard.count.form.productNo"></dic>:
        </label>
        <label class="col col-10 input">
            <input type="text" name="certification"
                   value="${(product.partNo)!}" readonly="readonly"/>
        </label>
    </fieldset>
    <fieldset>
        <label class="col col-2 text-align-right padding">
            <dic data-dic="standard.count.form.seriesNo"></dic>:
        </label>
        <label class="col col-10 input">
            <input type="text" name="name" readonly="readonly"
                   value="${(series.name)!}"/>
        </label>
    </fieldset>
    <fieldset>
        <label class="col col-2 text-align-right padding">
            <dic data-dic="standard.count.form.auto"></dic>
            :
        </label>
        <label class="col col-10 input">
            <select class="select2" name="auto" id="auto">
                <option value="0"
                        <#if standard.standard??&&standard.standard==0>selected="selected"</#if> data-dic="select.option.no">
                </option>
                <option value="1"
                        <#if standard.standard??&&standard.standard==1>selected="selected"</#if> data-dic="select.option.yes">
                </option>
            </select>
        </label>
    </fieldset>
    <ul class="nav nav-tabs bordered" id="year_tab">
        <#list years as item>
            <li <#if item_index==0>class="active"</#if> id="t_${(item_index)}">
                <a href="#s${item_index}" data-toggle="tab">${(item.year)!}
                    <dic data-dic="standard.count.form.year"></dic>
                </a>
            </li>
        </#list>
    </ul>
    <div class="tab-content padding-10">
        <#list years as item>
            <div class="tab-pane fade in <#if item_index==0>active</#if>" id="s${item_index}">
                <input value="${(item.year)!}" type="hidden" name="year">
                <fieldset>
                    <label class="col col-2 text-align-right padding">
                        <span style="color: red">*</span>
                        <dic data-dic="standard.count.form.count"></dic>:
                    </label>
                    <label class="col col-10 input">
                        <input type="text" name="counts"
                               value="${(item.counts)!}" class="spinner"/>
                    </label>
                </fieldset>
                <fieldset>
                    <label class="col col-2 text-align-right padding">
                        <span style="color: red">*</span>
                        <dic data-dic="standard.count.form.unit"></dic>:
                    </label>
                    <label class="col col-10 input">
                        <input class="unit-type" name="unitType" style="width: 100%;" id="unit_${(item_index)}"
                               data-year="${(item.year)!}" data-unit="${(item.unitType)!}">
                    </label>
                </fieldset>

                <fieldset id="spel_input_${(item.year)!}"
                    <#if item.unitType??&&item.unitType==5><#else> style="display: none;"</#if>>
                    <label class="col col-2 text-align-right padding">
                        <span style="color: red">*</span>
                        <dic data-dic="standard.count.form.spel"></dic>:
                    </label>
                    <label class="col col-10 input">
                        <input type="text" name="spel"
                               value="${(item.spel)!}"/>
                    </label>
                </fieldset>
            </div>

        </#list>
    </div>
</form>
<script type="text/javascript">
    $('[data-dic]').each(function () {
        $(this).html(message[$(this).data("dic")]);
    });
    pageSetUp();
    $(".spinner").spinner({
        step: 1,
        min: 0,
        textFormat: "n"
    });
    var datalist = [{id: 1, text: message['product.series.single']}, {id: 2, text: message['product.series.square']},
        {id: 3, text: message['product.series.box']}, {id: 4,text: message['product.series.module']},
        {id: 5, text: message['product.series.customize']}];
    $('.unit-type').each(function (index, val) {
        var unitType = $(val).data('unit');
        $(val).select2({
            data: datalist,
            minimumResultsForSearch: -1
        }).val(unitType).trigger('change');
    });

    $('.unit-type').on('change', function () {
        var year = $(this).data("year");
        var select = $(this).select2("val");
        if (select == '5') {
            $('#spel_input_' + year).show();
        } else {
            $('#spel_input_' + year).hide();
        }
    });
    var year = {
        submit: function () {
            bootstrapValidator.validate();
            if (bootstrapValidator.isValid()) {
                var years = [];
                var standardId = $('#standardId').val();
                for (var i = 0; i < 4; i++) {
                    var year = {};
                    var input = $('#s' + i + ' input');
                    var unitType = $('#s' + i + ' fieldset').find('.unit-type').select2('val');
                    if ((!unitType || unitType == '')) {
                        $('#year_tab #t_' + i + ' a').tab('show');
                        var tipHtml = '<small class="help-block" ' +
                                'style="color: #b94a48;"' +
                                ' data-bv-validator="notEmpty" ' +
                                'data-bv-for="counts" data-bv-result="INVALID" ' +
                                'style="">' + message['prompt.message.null'] + '</small>';
                        $($('#s' + i + ' fieldset').find('.unit-type')[0]).find('small').empty();
                        $($('#s' + i + ' fieldset').find('.unit-type')[0]).append(tipHtml);
                        return false;
                    } else {
                        year.unitType = unitType;
                        year.counts = $('#s' + i + ' input[name="counts"]').val();
                        year.spel = $('#s' + i + ' input[name="spel"]').val();
                        year.year = $('#s' + i + ' input[name="year"]').val();
                        year.type = $('#standardType').val();
                        $($('#s' + i + ' fieldset').find('.unit-type')[0]).find('small').empty();
                        years.push(year);
                    }
                }
                $.ajax({
                    url: '/api/spare/count/year/save',
                    data: {
                        standardId: standardId,
                        yearsJsonStr: JSON.stringify(years),
                        auto: $('#auto').select2('val')
                    },
                    type: 'POST',
                    success: function (data) {
                        alert(message['alert.message.success']);
                        var $model = $('#edit_count_model');
                        $model.modal('hide');
                        standardTable.submitFilter();
                    }, error: function () {
                        alert(message['alert.message.systemError']);
                    }
                });
            }
        }
    };
    var form = $('#yearForm');
    //表单校验
    form.bootstrapValidator({
        fields: {
            auto: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message['prompt.message.null']
                    }
                }
            },
            counts: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message['prompt.message.null']
                    },
                    numeric: {
                        message: message["prompt.message.digital"]
                    }
                }
            },
            spel: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message['prompt.message.null']
                    }
                }
            }
        }
    });
    var bootstrapValidator = form.data('bootstrapValidator');
</script>
</#compress>