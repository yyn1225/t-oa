<#compress>
<#if departmentList?? && (departmentList?size > 0)>
    <#list departmentList as department>
    <article class="col-xs-12 col-sm-4 col-md-4 col-lg-4  sortable-grid ui-sortable">
        <div class="jarviswidget jarviswidget-sortable" id="service-panel">
            <header role="heading">
                <span class="widget-icon"><i class="fa fa-cube"></i> </span>
                <h2><strong><i>${(department.name)!}</i></strong></h2>
            </header>
            <#if area == 1 || area==0>
                <div id="service_part">
                    <div class="widget-body no-padding">
                        <form class="smart-form" style="word-break: keep-all">
                            <input type="hidden" name="id" value="${(department.id?c)!}">
                            <input type="hidden" name="area" value="${(department.departmentId)!}">
                            <fieldset>
                                <label class="col col-md-3 text-align-right padding7"></label>
                                <label class="col col-md-6">
                                    <#if (department.approval)?? && (department.approval) == 1>
                                        <label class="toggle">
                                            <input type="checkbox" name="approvalStr" checked="checked">
                                            <i data-swchon-text="ON" data-swchoff-text="OFF"></i><dic data-dic="approval.list.need"></dic></label>
                                    <#else>
                                        <label class="toggle">
                                            <input type="checkbox" name="approvalStr">
                                            <i data-swchon-text="ON" data-swchoff-text="OFF"></i><dic data-dic="approval.list.need"></dic></label>
                                    </#if>
                                </label>
                            </fieldset>
                            <fieldset>
                                <label class="col col-md-3 text-align-right padding7"><dic data-dic="approval.list.condition"></dic>:</label>
                                <label class="col col-md-8 input">
                                    <select class="select2" name="condition" id="condition">
                                        <#if (department.condition)??>
                                            <#if (department.condition) == 1>
                                                <option value="1" data-dic="approval.list.all" selected></option>
                                                <option value="2" data-dic="approval.list.cost"></option>
                                                <option value="3" data-dic="approval.list.discount"></option>
                                                <option value="4" data-dic="approval.list.guide"></option>
                                            </#if>
                                            <#if (department.condition) == 2>
                                                <option value="1" data-dic="approval.list.all"></option>
                                                <option value="2" data-dic="approval.list.cost" selected></option>
                                                <option value="3" data-dic="approval.list.discount"></option>
                                                <option value="4" data-dic="approval.list.guide"></option>
                                            </#if>
                                            <#if (department.condition) == 3>
                                                <option value="1" data-dic="approval.list.all"></option>
                                                <option value="2" data-dic="approval.list.cost"></option>
                                                <option value="3" data-dic="approval.list.discount"
                                                        selected></option>
                                                <option value="4" data-dic="approval.list.guide"></option>
                                            </#if>
                                            <#if (department.condition) == 4>
                                                <option value="1" data-dic="approval.list.all"></option>
                                                <option value="2" data-dic="approval.list.cost"></option>
                                                <option value="3" data-dic="approval.list.discount"></option>
                                                <option value="4" data-dic="approval.list.guide" selected></option>
                                            </#if>
                                        <#else>
                                            <option value=""></option>
                                            <option value="1" data-dic="approval.list.all"></option>
                                            <option value="2" data-dic="approval.list.cost"></option>
                                            <option value="3" data-dic="approval.list.discount"></option>
                                            <option value="4" data-dic="approval.list.guide"></option>
                                        </#if>
                                    </select>
                                </label>
                            </fieldset>
                            <fieldset>
                                <label class="col col-md-3 text-align-right padding7"><dic data-dic="approval.list.approver"></dic>:</label>
                                <label class="col col-md-8" style="padding-top: 7px"><dic data-dic="regional.leadership"></dic>
                                    <#--<select class="select2" name="auditor" id="auditor">-->
                                        <#--<option value="${(department.leader?c)!}">${(department.leaderName)!}</option>-->
                                        <#--<#if roleList??>-->
                                            <#--<#if (department.auditor)??>-->
                                                <#--<#list roleList as item>-->
                                                    <#--<option value="${(item.id?c)!}" <#if (department.auditor?c) == (item.id?c)>selected</#if>>${(item.name)!}</option>-->
                                                <#--</#list>-->
                                            <#--<#else>-->
                                                <#--<#list roleList as item>-->
                                                    <#--<option value="${(item.id?c)!}">${(item.name)!}</option>-->
                                                <#--</#list>-->
                                            <#--</#if>-->
                                        <#--</#if>-->
                                    <#--</select>-->
                                </label>
                            </fieldset>
                            <fieldset style="padding-bottom: 10px !important;">
                                <label class="col col-md-3 text-align-right padding7"><dic data-dic="approval.list.export"></dic>:</label>
                                <label class="col col-md-8 input">
                                    <select class="select2" name="export" id="export">
                                        <#if (department.export)??>
                                            <#if (department.export) == 1>
                                                <option value=""></option>
                                                <option value="1" selected>PDF</option>
                                                <option value="2">EXCEL</option>
                                                <option value="3">PDF&EXCEL</option>
                                            </#if>
                                            <#if (department.export) == 2>
                                                <option value=""></option>
                                                <option value="1">PDF</option>
                                                <option value="2" selected>EXCEL</option>
                                                <option value="3">PDF&EXCEL</option>
                                            </#if>
                                            <#if (department.export) == 3>
                                                <option value=""></option>
                                                <option value="1">PDF</option>
                                                <option value="2">EXCEL</option>
                                                <option value="3" selected>PDF&EXCEL</option>
                                            </#if>
                                        <#else>
                                            <option value=""></option>
                                            <option value="1">PDF</option>
                                            <option value="2">EXCEL</option>
                                            <option value="3">PDF&EXCEL</option>
                                        </#if>
                                    </select>
                                </label>
                            </fieldset>
                        </form>
                    </div>
                </div>
                <div style="border: 1px solid #ccc;margin-top: -2px;" class="row">
                    <label class="col col-md-12 text-align-right">
                        <a class="btn btn-primary submit_button" style="margin-top: -7px;">
                            <dic data-dic="body.list.submit"></dic>
                        </a>
                    </label>
                </div>
            <#else>
                <#if area == (department.departmentId)>
                    <div id="service_part">
                        <div class="widget-body no-padding">
                            <form class="smart-form" style="word-break: keep-all">
                                <input type="hidden" name="id" value="${(department.id?c)!}">
                                <input type="hidden" name="area" value="${(department.departmentId)!}">
                                <fieldset>
                                    <label class="col col-md-3 text-align-right padding7"></label>
                                    <label class="col col-md-6">
                                        <#if (department.approval)?? && (department.approval) == 1>
                                            <label class="toggle">
                                                <input type="checkbox" name="approvalStr" checked="checked">
                                                <i data-swchon-text="ON" data-swchoff-text="OFF"></i><dic data-dic="approval.list.need"></dic></label>
                                        <#else>
                                            <label class="toggle">
                                                <input type="checkbox" name="approvalStr">
                                                <i data-swchon-text="ON" data-swchoff-text="OFF"></i><dic data-dic="approval.list.need"></dic></label>
                                        </#if>
                                    </label>
                                </fieldset>
                                <fieldset>
                                    <label class="col col-md-3 text-align-right padding7"><dic data-dic="approval.list.condition"></dic>:</label>
                                    <label class="col col-md-8 input">
                                        <select class="select2" name="condition" id="condition">
                                            <#if (department.condition)??>
                                                <#if (department.condition) == 1>
                                                    <option value="1" data-dic="approval.list.all" selected></option>
                                                    <option value="2" data-dic="approval.list.cost"></option>
                                                    <option value="3" data-dic="approval.list.discount"></option>
                                                    <option value="4" data-dic="approval.list.guide"></option>
                                                </#if>
                                                <#if (department.condition) == 2>
                                                    <option value="1" data-dic="approval.list.all"></option>
                                                    <option value="2" data-dic="approval.list.cost" selected></option>
                                                    <option value="3" data-dic="approval.list.discount"></option>
                                                    <option value="4" data-dic="approval.list.guide"></option>
                                                </#if>
                                                <#if (department.condition) == 3>
                                                    <option value="1" data-dic="approval.list.all"></option>
                                                    <option value="2" data-dic="approval.list.cost"></option>
                                                    <option value="3" data-dic="approval.list.discount"
                                                            selected></option>
                                                    <option value="4" data-dic="approval.list.guide"></option>
                                                </#if>
                                                <#if (department.condition) == 4>
                                                    <option value="1" data-dic="approval.list.all"></option>
                                                    <option value="2" data-dic="approval.list.cost"></option>
                                                    <option value="3" data-dic="approval.list.discount"></option>
                                                    <option value="4" data-dic="approval.list.guide" selected></option>
                                                </#if>
                                            <#else>
                                                <option value=""></option>
                                                <option value="1" data-dic="approval.list.all"></option>
                                                <option value="2" data-dic="approval.list.cost"></option>
                                                <option value="3" data-dic="approval.list.discount"></option>
                                                <option value="4" data-dic="approval.list.guide"></option>
                                            </#if>
                                        </select>
                                    </label>
                                </fieldset>
                                <fieldset>
                                    <label class="col col-md-3 text-align-right padding7"><dic data-dic="approval.list.approver"></dic>:</label>
                                    <label class="col col-md-8" style="padding-top: 7px"> <dic data-dic="regional.leadership"></dic>
                                        <#--<select class="select2" name="auditor" id="auditor">-->
                                            <#--<option value="${(department.leader?c)!}">${(department.leaderName)!}</option>-->
                                            <#--<#if roleList??>-->
                                                <#--<#if (department.auditor)??>-->
                                                    <#--<#list roleList as item>-->
                                                        <#--<option value="${(item.id?c)!}" <#if (department.auditor?c) == (item.id?c)>selected</#if>>${(item.name)!}</option>-->
                                                    <#--</#list>-->
                                                <#--<#else>-->
                                                    <#--<#list roleList as item>-->
                                                        <#--<option value="${(item.id?c)!}">${(item.name)!}</option>-->
                                                    <#--</#list>-->
                                                <#--</#if>-->
                                            <#--</#if>-->
                                        <#--</select>-->
                                    </label>
                                </fieldset>
                                <fieldset style="padding-bottom: 10px !important;">
                                    <label class="col col-md-3 text-align-right padding7"><dic data-dic="approval.list.export"></dic>:</label>
                                    <label class="col col-md-8 input">
                                        <select class="select2" name="export" id="export">
                                            <#if (department.export)??>
                                                <#if (department.export) == 1>
                                                    <option value=""></option>
                                                    <option value="1" selected>PDF</option>
                                                    <option value="2">EXCEL</option>
                                                    <option value="3">PDF&EXCEL</option>
                                                </#if>
                                                <#if (department.export) == 2>
                                                    <option value=""></option>
                                                    <option value="1">PDF</option>
                                                    <option value="2" selected>EXCEL</option>
                                                    <option value="3">PDF&EXCEL</option>
                                                </#if>
                                                <#if (department.export) == 3>
                                                    <option value=""></option>
                                                    <option value="1">PDF</option>
                                                    <option value="2">EXCEL</option>
                                                    <option value="3" selected>PDF&EXCEL</option>
                                                </#if>
                                            <#else>
                                                <option value=""></option>
                                                <option value="1">PDF</option>
                                                <option value="2">EXCEL</option>
                                                <option value="3">PDF&EXCEL</option>
                                            </#if>
                                        </select>
                                    </label>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                    <div style="border: 1px solid #ccc;margin-top: -2px;" class="row">
                        <label class="col col-md-12 text-align-right">
                            <a class="btn btn-primary submit_button" style="margin-top: -7px;">
                                <dic data-dic="body.list.submit"></dic>
                            </a>
                        </label>
                    </div>
                <#else>
                    <div id="service_part">
                        <div class="widget-body no-padding">
                            <form class="smart-form" style="word-break: keep-all">
                                <fieldset>
                                    <label class="col col-md-3 text-align-right padding7"></label>
                                    <label class="col col-md-6">
                                        <#if (department.approval)?? && (department.approval) == 1>
                                            <label class="toggle state-disabled">
                                                <input type="checkbox" name="approvalStr" checked="checked" disabled="disabled">
                                                <i data-swchon-text="ON" data-swchoff-text="OFF"></i><dic data-dic="approval.list.need"></dic></label>
                                        <#else>
                                            <label class="toggle state-disabled">
                                                <input type="checkbox" name="approvalStr" disabled="disabled">
                                                <i data-swchon-text="ON" data-swchoff-text="OFF"></i><dic data-dic="approval.list.need"></dic></label>
                                        </#if>
                                    </label>
                                </fieldset>
                                <fieldset>
                                    <label class="col col-md-3 text-align-right padding7"><dic data-dic="approval.list.condition"></dic>:</label>
                                    <label class="col col-md-8 input">
                                        <select class="select2" name="condition" id="condition" disabled>
                                            <#if (department.condition)??>
                                                <#if (department.condition) == 1>
                                                    <option value="1" data-dic="approval.list.all" selected></option>
                                                    <option value="2" data-dic="approval.list.cost"></option>
                                                    <option value="3" data-dic="approval.list.discount"></option>
                                                    <option value="4" data-dic="approval.list.guide"></option>
                                                </#if>
                                                <#if (department.condition) == 2>
                                                    <option value="1" data-dic="approval.list.all"></option>
                                                    <option value="2" data-dic="approval.list.cost" selected></option>
                                                    <option value="3" data-dic="approval.list.discount"></option>
                                                    <option value="4" data-dic="approval.list.guide"></option>
                                                </#if>
                                                <#if (department.condition) == 3>
                                                    <option value="1" data-dic="approval.list.all"></option>
                                                    <option value="2" data-dic="approval.list.cost"></option>
                                                    <option value="3" data-dic="approval.list.discount"
                                                            selected></option>
                                                    <option value="4" data-dic="approval.list.guide"></option>
                                                </#if>
                                                <#if (department.condition) == 4>
                                                    <option value="1" data-dic="approval.list.all"></option>
                                                    <option value="2" data-dic="approval.list.cost"></option>
                                                    <option value="3" data-dic="approval.list.discount"></option>
                                                    <option value="4" data-dic="approval.list.guide" selected></option>
                                                </#if>
                                            <#else>
                                                <option value=""></option>
                                                <option value="1" data-dic="approval.list.all"></option>
                                                <option value="2" data-dic="approval.list.cost"></option>
                                                <option value="3" data-dic="approval.list.discount"></option>
                                                <option value="4" data-dic="approval.list.guide"></option>
                                            </#if>
                                        </select>
                                    </label>
                                </fieldset>
                                <fieldset>
                                    <label class="col col-md-3 text-align-right padding7"><dic data-dic="approval.list.approver"></dic>:</label>
                                    <label class="col col-md-8" style="padding-top: 7px"> <dic data-dic="regional.leadership"></dic>
                                        <#--<select class="select2" name="auditor" id="auditor" disabled>-->
                                            <#--<option value="${(department.leader?c)!}">${(department.leaderName)!}</option>-->
                                            <#--<#if roleList??>-->
                                                <#--<#list roleList as item>-->
                                                    <#--<#if (department.auditor)??>-->
                                                      <#--<option value="${(item.id?c)!}" <#if-->
                                                      <#--(department.auditor)??-->
                                                       <#--&& ((department.auditor?c) == (item.id?c))>selected</#if>>${(item.name)!}</option>-->
                                                    <#--<#else>-->
                                                     <#--<option value="${(item.id?c)!}">${(item.name)!}</option>-->
                                                    <#--</#if>-->
                                                <#--</#list>-->
                                            <#--</#if>-->
                                        <#--</select>-->
                                    </label>
                                </fieldset>
                                <fieldset style="padding-bottom: 10px !important;">
                                    <label class="col col-md-3 text-align-right padding7"><dic data-dic="approval.list.export"></dic>:</label>
                                    <label class="col col-md-8 input">
                                        <select class="select2" name="export" id="export" disabled>
                                            <#if (department.export)??>
                                                <#if (department.export) == 1>
                                                    <option value=""></option>
                                                    <option value="1" selected>PDF</option>
                                                    <option value="2">EXCEL</option>
                                                    <option value="3">PDF&EXCEL</option>
                                                </#if>
                                                <#if (department.export) == 2>
                                                    <option value=""></option>
                                                    <option value="1">PDF</option>
                                                    <option value="2" selected>EXCEL</option>
                                                    <option value="3">PDF&EXCEL</option>
                                                </#if>
                                                <#if (department.export) == 3>
                                                    <option value=""></option>
                                                    <option value="1">PDF</option>
                                                    <option value="2">EXCEL</option>
                                                    <option value="3" selected>PDF&EXCEL</option>
                                                </#if>
                                            <#else>
                                                <option value=""></option>
                                                <option value="1">PDF</option>
                                                <option value="2">EXCEL</option>
                                                <option value="3">PDF&EXCEL</option>
                                            </#if>
                                        </select>
                                    </label>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                    <div style="border: 1px solid #ccc;margin-top: -2px;" class="row">
                        <label class="col col-md-12 text-align-right">
                            <button class="btn btn-primary submit_button" style="margin-top: -7px;" disabled>
                                <dic data-dic="body.list.submit"></dic>
                            </button>
                        </label>
                    </div>
                </#if>
            </#if>
        </div>
    </article>
    </#list>
</#if>
<script>
    $(".select2").each(function () {
        $(this).attr('data-placeholder', message["select2.placeholder.msg"]);
    });
    $("[data-dic]").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });
    $(':checkbox').each(function () {
        var self = $(this);
       if (!(self.is(":checked"))) {
           self.parents(".smart-form").find("select[name='condition']").attr("disabled", true);
           self.parents(".smart-form").find("select[name='auditor']").attr("disabled", true);
//           self.parents(".smart-form").find("select").each(function () {
//              $(this).attr("disabled", true);
//           });
       }
    });
    $('#main').on('change','input[name="approvalStr"]', function () {
        var self = $(this);
        if (self.is(":checked")) {
            self.parents(".smart-form").find("select[name='condition']").removeAttr("disabled");
            self.parents(".smart-form").find("select[name='auditor']").removeAttr("disabled");
        }else {
            self.parents(".smart-form").find("select[name='condition']").attr("disabled", true);
            self.parents(".smart-form").find("select[name='auditor']").attr("disabled", true);
        }
    });
    $('#main').on("click",".submit_button",function (e) {
        e.preventDefault();
        showLoading();
        $.ajax({
            url: '/approval/config/rest/save',
            type: 'post',
            data: $(this).parents(".sortable-grid.ui-sortable").find('.smart-form').serialize(),
            success: function () {
                window.parent.location.reload();
                // setTimeout(alert(message["alert.message.success"]),1000);
                hideLoading();
            },
            error: function () {
                alert(message["alert.message.systemError"], 3);
            }
        });
    });
    pageSetUp();
</script>
<style>
    .padding7 {
        padding-top: 7px;
        padding-left: 0 !important;
        padding-right: 0 !important;
    }

    fieldset {
        height: auto !important;
    }
</style>
</#compress>
