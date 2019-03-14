<form id="lang_form" class="smart-form">
    <fieldset>
        <label class="col-xs-12 col-sm-12 col-md-12 col-lg-12 input">
            <input type="hidden" name="lang-val" id="userIds"
                   value="${(userIds)!c}"/>
            <input type="text" name="lang-val" readonly="readonly"
                   value="${(strName)!}"/>
        </label>
    </fieldset>
    <fieldset>
        <label class="col-xs-12 col-sm-12 col-md-12 col-lg-12 input">
            <select class="select2" style="width: 100%"
                    id="roleId"
                    multiple
                    name="parent">
            <#list roles as item>
                <option value="${(item.id)!}">
                ${(item.name)!}
                </option>
            </#list>
            </select>
        </label>
    </fieldset>
</form>
<script type="text/javascript">
    $('#roleId').select2({
        placeholder: '请选择',
        language: "zh-CN",
        allowClear: true
    });
</script>