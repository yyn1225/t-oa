<#compress>
<article>
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
        </header>
        <div>
            <div class="widget-body no-padding ">
                <form class="smart-form " id="addOrEdit-form">
                    <fieldset>
                        <input type="hidden" id="name" name="id" value="${id!c}"/>
                        <label class="col col-1 text-align-right" style="padding-top: 7px;">
                            <span style="color: red">*</span>
                            <dic data-dic="prompt.input.scnNo"></dic>
                        </label>
                        <label class="col col-3  input">
                            <input type="text" id="scnNo" name="scnNo" value="${(box.scnNo)!}"/>
                        </label>
                        <label class="col col-1 text-align-right" style="padding-top: 7px;">
                            <span style="color: red">*</span>
                            <dic data-dic="prompt.input.modual"></dic>
                        </label>
                        <label class="col col-md-7 input">
                            <input name="modual" type="hidden" value="${box.modual!}"/>
                            <input name="modual2" type="hidden" value="${box.modual2!}"/>
                            <input type="text" id="modual" style="width: 100%"/>
                        </label>
                    </fieldset>
                    <fieldset>
                        <label class="col col-1 text-align-right" style="padding-top: 7px;">
                            <span style="color: red">*</span>
                            <dic data-dic="prompt.input.transverseCount"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="transverseCount" name="transverseCount" class="spinners" value="${(box.transverseCount!c)!}"/>
                        </label>
                        <label class="col col-1 text-align-right" style="padding-top: 7px; ">
                            <span style="color: red">*</span>
                            <dic data-dic="prompt.input.portraitCount"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="portraitCount" name="portraitCount" class="spinners" value="${(box.portraitCount!c)!}"/>
                        </label>
                        <label class="col col-1 text-align-right" style="padding-top: 7px;">
                            <span style="color: red">*</span>
                            <dic data-dic="prompt.input.weight"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="weight" name="weight" class="spinners" value="${(box.weight!c)!}"/>
                        </label>
                    </fieldset>
                    <fieldset>
                        <label class="col col-1 text-align-right" style="padding-top: 7px;">
                            <span style="color: red">*</span>
                            <dic data-dic="prompt.input.transversePix"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="transversePix" name="transversePix" class="spinners" value="${(box.transversePix!c)!}"/>
                        </label>
                        <label class="col col-1 text-align-right" style="padding-top: 7px; ">
                            <span style="color: red">*</span>
                            <dic data-dic="prompt.input.portraitPix"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="portraitPix" name="portraitPix" class="spinners" value="${(box.portraitPix!c)!}"/>
                        </label>
                        <label class="col col-1 text-align-right" style="padding-top: 7px;">
                            <span style="color: red">*</span>
                            <dic data-dic="prompt.input.width"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="width" name="width" class="spinners" value="${(box.width!c)!}"/>
                        </label>
                    </fieldset>
                    <fieldset>
                        <label class="col col-1 text-align-right" style="padding-top: 7px;">
                            <span style="color: red">*</span>
                            <dic data-dic="prompt.input.height"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="height" name="height" class="spinners" value="${(box.height!c)!}"/>
                        </label>
                        <label class="col col-1 text-align-right" style="padding-top: 7px; ">
                            <span style="color: red">*</span>
                            <dic data-dic="prompt.input.thickness"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="thickness" name="thickness" class="spinners" value="${(box.thickness!c)!}"/>
                        </label>
                        <label class="col col-1 text-align-right" style="padding-top: 7px;">
                            <dic data-dic="prompt.input.boxType"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="boxType" name="boxType" value="${(box.boxType)!}" data-zh="${(box.boxType)!}" data-en="${(boxLang["en"].type)!}" data-category="box_type"/>
                        </label>
                    </fieldset>
                    <div class="widget-footer">
                        <div class=" text-align-right">
                            <a class="btn btn-primary btn-sm"  id="ok" style="margin-right: 8px">
                                <dic data-dic="body.list.submit"></dic>
                            </a>
                            <a class="btn btn-default btn-sm" onclick="back()" style="margin-right: 38px">
                                <dic data-dic="body.list.back"></dic>
                            </a>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</article>
<script type="text/javascript">
    $(document).ready(function() {
        $(".select2").each(function () {
            $(this).attr('data-placeholder',message["select2.placeholder.msg"]);
        });

        var dataList=[];
        $.each(${modules}, function (index,item) {
           dataList.push({
               id:item.id,
               text:item.scnNo
           })
        });

        $("#modual").select2({
            multiple: true,
            data:dataList,
            maximumSelectionSize:2
        });
        $("#modual").on("change",function(){
            var values = $(this).select2("val");
            if(values.length==0){
                $("[name='modual']").val(0);
                $("[name='modual2']").val(0);
            }else if(values.length==1){
                $("[name='modual']").val(values[0]);
                $("[name='modual2']").val(0);
            }else{
                $("[name='modual']").val(values[0]);
                $("[name='modual2']").val(values[1]);
            }
        });

        var values=[];
        if(${box.modual!0}>0){
            values.push(${box.modual!})
        }
        if(${box.modual2!0}>0){
            values.push(${box.modual2!})
        }

        $("#modual").val(values).trigger('change');

        $("[data-dic]").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });

        $(".spinners").spinner({
            step: 1,
            textFormat: "n"
        });

    });

    $("[data-dic]").each(function(){
        $(this).html(message[$(this).data("dic")]);
    });
    pageSetUp();
    var $form = $('#addOrEdit-form');
    $("#ok").on("click", function(){
        //获取表单对象
        var bootstrapValidator = $form.data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()) {
            showLoading();
            var langs = [];
            $.each(lang, function(index,item){
                langs.push({
                    lang:item,
                    type:$("#boxType").data(item)
                });
            });
            $.ajax({
                url: '/box/rest/save',
                data: $('form').serialize() + '&lang=' + JSON.stringify(langs),
                type: 'POST',
                success: function (data) {
                    boxTable.resetFilter();
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
            })
        }
    });

    function back() {
        $('#second').hide();
        $('#first').show();
    }
    var id = $('#id').val();
    if (!id) {
        id = 0;
    }
    //    表单校验
    $form.bootstrapValidator({
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        fields : {
            scnNo : {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message['prompt.message.null']
                    }
                }
            },
            modual : {
                group:".input",
                validators : {
                    notEmpty : {
                        message : message['prompt.message.null']
                    }
                }
            },
            height : {
                group:".input",
                validators : {
                    notEmpty : {
                        message : message['prompt.message.null']
                    },
                    digits : {
                        message : message['prompt.message.digital']
                    }
                }
            },
            width : {
                group:".input",
                validators : {
                    notEmpty : {
                        message : message['prompt.message.null']
                    },
                    digits : {
                        message : message['prompt.message.digital']
                    }
                }
            },
            transverseCount : {
                group:".input",
                validators : {
                    notEmpty : {
                        message : message['prompt.message.null']
                    },
                    digits : {
                        message : message['prompt.message.digital']
                    }
                }
            },
            portraitCount : {
                group:".input",
                validators : {
                    notEmpty : {
                        message : message['prompt.message.null']
                    },
                    digits : {
                        message : message['prompt.message.digital']
                    }
                }
            },
            transversePix : {
                group:".input",
                validators : {
                    notEmpty : {
                        message : message['prompt.message.null']
                    },
                    digits : {
                        message : message['prompt.message.digital']
                    }
                }
            },
            portraitPix : {
                group:".input",
                validators : {
                    notEmpty : {
                        message : message['prompt.message.null']
                    },
                    digits : {
                        message : message['prompt.message.digital']
                    }
                }
            },
            thickness : {
                group:".input",
                validators : {
                    notEmpty : {
                        message : message['prompt.message.null']
                    },
                    digits : {
                        message : message['prompt.message.digital']
                    }
                }
            },
            boxType : {
                group:".input",
                validators : {
                    notEmpty : {
                        message : message['prompt.message.null']
                    }
                }
            },
            weight : {
                group:".input",
                validators : {
                    notEmpty : {
                        message : message['prompt.message.null']
                    },
                    numeric : {
                        message : message['prompt.message.digital']
                    }
                }
            }}
        });
    bindLanguages($("#boxType"));
</script>
<style>
    .smart-form fieldset + fieldset {
        border-top: none !important;
    }
    .col-1{
        padding-left: 0 !important;
        padding-right: 0 !important;
    }

</style>
</#compress>