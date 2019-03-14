<#compress>

<form class="smart-form" id="price-form">
    <input type="hidden" name="id" value="${(levelId)!}" id="level">
    <fieldset>
        <label class="col col-3 text-align-right padding">
            <dic data-dic="sales.level.list.name"></dic>:
        </label>
        <label class="col col-6 input">
            <input type="text" name="name" value="${(level.name)!}"
                   id="levelName" data-zh="${(lang["zh"].nameLang)!}" data-en="${(lang["en"].nameLang)!}">
        </label>
    </fieldset>

    <fieldset>
        <label class="col col-3 text-align-right padding">
            <span style="color: red">*</span>
            <dic data-dic="sales.level.list.code"></dic>:
        </label>
        <label class="col col-6 input">
            <input type="text" name="code"
                   value="${(level.code)!}">
        </label>
    </fieldset>

    <fieldset>
        <label class="col col-3 text-align-right padding">
            <span style="color: red">*</span>
            <dic data-dic="sales.level.list.discount"></dic>:
        </label>
        <label class="col col-6 input">
            <#if (level.discount)??>
                <input type="text" name="discount"
                       value="${level.discount*100}" class="spinners">
            <#else>
                <input type="text" name="discount" class="spinners">
            </#if>
        </label>
        <label class="col col-1" style="padding-top: 7px;">%
    </fieldset>

    <fieldset>
        <label class="col col-3 text-align-right padding">
            <span style="color: red">*</span>
            <dic data-dic="sales.level.list.profit"></dic>:
        </label>
        <label class="col col-6 input">
            <#if (level.profit)??>
                <input type="text" name="profit"
                       value="${level.profit*100}" class="spinners">
            <#else>
                <input type="text" name="profit" class="spinners">
            </#if>
        </label>
        <label class="col col-1" style="padding-top: 7px;">%
        </label>
    </fieldset>
    <footer>
        <button type="button" id="submit_price" class="btn btn-primary">
            <dic data-dic="button.submit"></dic>
        </button>
        <button type="button" id="cancel" class="btn btn-default"
                data-dismiss="modal">
            <dic data-dic="button.cancel"></dic>
        </button>
    </footer>
</form>


<script type="text/javascript">
    pageSetUp();
    $(".spinners").spinner({
        step: 1,
        textFormat: "n"
    });
    $(document).ready(function () {
        var id = $("#level").val();
        if (!id) {
            id = 0;
        }
        $("[data-dic]").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
    });
    $form = $("#price-form");
    $form.bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                group: ".col.col-6",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    }
                }
            },
            code: {
                group: ".col.col-6",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    }
                }
            },
            discount: {
                group: ".col.col-6",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    },
                    digits: {
                        message: message["prompt.message.digital"]
                    }
                }
            },
            profit: {
                group: ".col.col-6",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    },
                    digits: {
                        message: message["prompt.message.digital"]
                    }
                }
            }
        }
    });
    bindLanguages($("#levelName"));
</script>
</#compress>