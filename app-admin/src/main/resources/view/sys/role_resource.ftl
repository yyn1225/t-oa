<#compress>
<form class="smart-form" id="addOrEdit-form">
    <fieldset style="border-bottom: 1px solid #C2C2C2; height: 50px !important;" class="role-fieldset">
        <input type="hidden" id="id" name="id" value="${(role.id)!}"/>
        <label class="col col-2 text-align-right"
               style="padding-top: 7px;"><dic data-dic="window.role.name"></dic>:</label>
        <label class="col col-10 input">
            <input type="text" id="username" name="username" readonly="readonly"
                   value="${(role.name)!}"/>
        </label>
    </fieldset>
    <#if resources?? && resources?size gt 0>
        <#list resources as r>
            <fieldset style="border: 1px solid #C2C2C2;" class="role-fieldset">
                <label class="col col-3">
                    <label class="checkbox" style="font-style: ">
                        <input type="checkbox" id="rrr_${(r.id)!}" name="resource"
                               class="r"
                               value="${(r.id)!}">
                        <i></i><strong>${(r.name)!}</strong></label>
                </label>
                <div class="col col-9">
                    <div class="inline-group">
                        <#if r.children??&&r.children?size gt 0 >
                            <#list r.children as rr>
                                <label class="checkbox">
                                    <input type="checkbox"
                                           class="r"
                                           name="resource"
                                           id="rrr_${(rr.id)!}"
                                           data-parent="${(r.id)!}"
                                           value="${(rr.id)!}">
                                    <i></i>${(rr.name)!}</label>
                            </#list>
                        </#if>
                    </div>
                </div>
            </fieldset>
        </#list>
    </#if>
</form>
<style type="text/css">
    .role-fieldset {
        height: 100% !important;
    }
</style>
<script type="text/javascript">
    $("[data-dic]").each(function(){
        $(this).html(message[$(this).data("dic")]);
    });
    $('.r').on('click', function () {
        if ($(this).is(':checked')) {
            $('#rrr_' + $(this).data('parent')).prop('checked', true);
        } else {
            if (!$(this).data('parent')) {
                $('.r[data-parent="' + $(this).val() + '"]')
                        .removeAttrs('checked');
            }
        }
    });

    $.ajax({
        url: '/api/role/resource/checked',
        type: 'GET',
        data: {roleId: $('#id').val()},
        success: function (data) {
            if (data) {
                data.forEach(function (val) {
                    $('#rrr_' + val.resource).attr('checked', 'checked');
                })
            }
        }
    });
</script>
</#compress>