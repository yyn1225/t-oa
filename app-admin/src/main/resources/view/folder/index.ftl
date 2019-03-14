<div class="dd">
    <ol class="dd-list">
    <#macro bpTree children>
        <#if children?? && children?size gt 0>
            <#list children as child>
                <#if child.children?? && child.children?size gt 0>
                    <li class="dd-item dd3-item" data-id="${(child.id)!}">
                        <div class="dd-handle dd3-handle">
                            Drag
                        </div>
                        <div class="dd3-content" data-id="${(child.id)!}" data-delable="false">
                        <span class="${(child.icon)!}"></span>
                            &nbsp;${(child.name)!}
                        </div>
                        <ol class="dd-list">
                            <@bpTree children=child.children/>
                        </ol>
                    </li>
                <#else>
                    <li class="dd-item dd3-item" data-id="${(child.id)!}">
                        <div class="dd-handle dd3-handle">
                            Drag
                        </div>
                        <div class="dd3-content" data-id="${(child.id)!}" data-delable="true">
                        <span class="${(child.icon)!}"></span>
                            &nbsp;${(child.name)!}</div>

                    </li>
                </#if>
            </#list>
        </#if>
    </#macro>
    <@bpTree children=folders />
    </ol>
</div>
