<form id="lang_form" class="smart-form">
    <fieldset>
        <label class="col-xs-12 col-sm-12 col-md-12 col-lg-12 input">
            <input type="hidden" name="lang-val" id="role_id"
                   value="${(role.id)!}"/>
            <input type="text" name="lang-val" readonly="readonly"
                   value="${(role.name)!}"/>
        </label>
    </fieldset>
    <fieldset style="height:auto !important;">
        <label class="col-xs-12 col-sm-12 col-md-12 col-lg-12 input">
            <select style="width: 100%"
                    id="userIds"
                    multiple
                    name="userIds">
            <#list users as item>
                <option value="${(item.id)!}">
                ${(item.name)!}
                </option>
            </#list>
            </select>
        </label>
    </fieldset>
</form>
<script type="text/javascript">
    $('#userIds').select2({
        placeholder: '请选择',
        language: "zh-CN",
        allowClear: true
    });
    $.ajax({
        url: '/api/role/user/checked',
        type: 'GET',
        data: {roleId: $('#role_id').val()},
        success: function (data) {
            $('#userIds').select2().val(data).trigger('change');
        },
        error: function () {
            alert(message["operation.failed"]);
        }
    });
</script>