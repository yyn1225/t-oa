<#compress>
<article>
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>

        </header>
        <div>
            <div class="widget-body no-padding ">
                <form class="smart-form addOrEdit-form" id="addOrEdit-form">

                    <fieldset>
                        <input type="hidden" id="name" name="id" value="${(id?c)!}"/>
                        <label class="col col-1 text-align-right padding">
                            <span style="color: red">*</span>
                            <dic data-dic="product.module.list.scnno"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="scnNo" name="scnNo" value="${(module.scnNo)!}"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <span style="color: red">*</span>
                            <dic data-dic="product.module.list.width"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" class="spinners-number" id="width" name="width"
                                   value="${(module.width?c)!}"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <span style="color: red">*</span>
                            <dic data-dic="product.module.list.height"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" class="spinners-number" id="height" name="height" value="${(module.height?c)!}"/>
                        </label>
                    </fieldset>

                    <fieldset>
                        <label class="col col-1 text-align-right padding">
                            <span style="color: red">*</span>
                            <dic data-dic="product.module.list.transverse"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" class="spinners" id="transverse" name="transverse"
                                   value="${(module.transverse?c)!}"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                          <span style="color: red">*</span>
                          <dic data-dic="product.module.list.portrait"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" class="spinners" id="portrait" name="portrait"
                                   value="${(module.portrait?c)!}"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <span style="color: red">*</span>
                            <dic data-dic="product.module.list.pitch"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" class="spinners-number" id="pitch" name="pitch" value="${(module.pitch?c)!}"/>
                        </label>
                    </fieldset>

                    <fieldset>
                        <label class="col col-1 text-align-right padding">
                            <span style="color: red">*</span>
                            <dic data-dic="product.module.list.density"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" class="spinners" id="density" name="density" value="${(module.density?c)!}"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <span style="color: red">*</span>
                            <dic data-dic="product.module.list.lamp"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" class="spinners" id="lamp" name="lamp" value="${(module.lamp?c)!}"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="product.module.list.configuration"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="configuration" name="configuration" data-zh="${(module.configuration)!}" data-en="${(langMap["type"]["en"])!}" data-category="led_type" value="${(module.configuration)!}"/>
                        </label>
                    </fieldset>

                    <fieldset>
                        <label class="col col-1 text-align-right padding">
                            <span style="color: red">*</span>
                            <dic data-dic="product.module.list.weight"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" class="spinners-number" id="weight" name="weight" value="${(module.weight?c)!}"/>
                        </label>
                    </fieldset>

                    <div class="widget-footer">
                        <div class=" text-align-right">
                            <a class="btn btn-primary btn-sm" id="ok" style="margin-right: 8px" data-dic="body.list.submit"></a>
                            <a class="btn btn-default btn-sm" onclick="back()" style="margin-right: 38px" data-dic="body.list.back"></a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</article>

<script type="text/javascript">
    pageSetUp();
    $("[data-dic]").each(function(){
        $(this).html(message[$(this).data("dic")]);
    });
    var $form = $('#addOrEdit-form');
    $("#ok").on("click", function () {
        //获取表单对象
        var bootstrapValidator = $form.data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if (bootstrapValidator.isValid()) {
            var langs = [];
            $.each(lang, function(index,item){
                langs.push({
                    lang:item,
                    type:$("#configuration").data(item)
                });
            });
            showLoading();
            $.ajax({
                url: '/module/save',
                data: $('form').serialize() + '&lang=' + JSON.stringify(langs),
                type: 'POST',
                success: function (data) {
                    moduleTable.resetFilter();
                    $('#second').hide();
                    $('#first').show();
                    alert(message["alert.message.success"]);
                    hideLoading();
                },
                error: function (data) {
                    if (data.responseJSON.message === "20001") {
                        alert(message["alert.message.codeExist"], 3);
                    }else {
                        alert(message["alert.message.systemError"], 3);
                    }
                    hideLoading();
                }
            });
        }
    });

    function back() {
        $('#second').hide();
        $('#first').show();
    }
    $form.bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            scnNo: {
                group: ".col.col-3",
                validators: {
                    notEmpty: {
                        message: message['validate.message.notEmpty']
                    }
                }
            },
            width: {
                group: ".col.col-3",
                validators: {
                    notEmpty: {
                        message: message['validate.message.notEmpty']
                    },
                    numeric: {
                        message: message['validate.message.number']
                    }
                }
            },
            height: {
                group: ".col.col-3",
                validators: {
                    notEmpty: {
                        message: message['validate.message.notEmpty']
                    },
                    numeric: {
                        message: message['validate.message.number']
                    }
                }
            },
            transverse: {
                group: ".col.col-3",
                validators: {
                    notEmpty: {
                        message: message['validate.message.notEmpty']
                    },
                    digits: {
                        message: message['validate.message.integer']
                    }
                }
            },
            portrait: {
                group: ".col.col-3",
                validators: {
                    notEmpty: {
                        message: message['validate.message.notEmpty']
                    },
                    digits: {
                        message: message['validate.message.integer']
                    }
                }
            },
            pitch: {
                group: ".col.col-3",
                validators: {
                    notEmpty: {
                        message: message['validate.message.notEmpty']
                    },
                    numeric: {
                        message: message['validate.message.number']
                    }
                }
            },
            density: {
                group: ".col.col-3",
                validators: {
                    notEmpty: {
                        message: message['validate.message.notEmpty']
                    },
                    digits: {
                        message: message['validate.message.integer']
                    }
                }
            },
            lamp: {
                group: ".col.col-3",
                validators: {
                    notEmpty: {
                        message: message['validate.message.notEmpty']
                    },
                    digits: {
                        message: message['validate.message.integer']
                    }
                }
            },
            weight: {
                group: ".col.col-3",
                validators: {
                    notEmpty: {
                        message: message['validate.message.notEmpty']
                    },
                    numeric: {
                        message: message['validate.message.number']
                    }
                }
            }
        }
    });

    $(function () {
        $(".spinners").spinner({
            step: 1,
            textFormat: "n"
        });
        $(".spinners-number").spinner({
            step: 0.5,
            textFormat: "n"
        });
    });

    bindLanguages($("#configuration"));
</script>
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