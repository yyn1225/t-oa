<#compress>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>Absen</title>
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" type="text/css" media="screen" href="../static/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="../static/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="../static/css/smartadmin-production-plugins.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="../static/css/smartadmin-production.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="../static/css/smartadmin-skins.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="../static/css/smartadmin-rtl.min.css">
    <link rel="shortcut icon" href="../static/img/favicon.png" type="image/x-icon">
    <link rel="icon" href="../static/img/favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="../static/css/common.css">
</head>

<body class="fixed-header fixed-navigation fixed-ribbon">

<aside id="left-panel" style="padding-top: 0;">
    <div class="login-info">
        <span style="text-align: center">
            <a href="javascript:void(0);" id="show-shortcut" data-action="toggleShortcut" style="padding-top:5px;">
                <span><@shiro.principal property="name"></@shiro.principal></span>
            </a>
        </span>
    </div>

    <nav id="right_menu_nav">
        <ul>
            <#macro menu children>
                <#if children?? && children?size gt 0>
                    <#list children as child>
                        <#if child.children?? && child.children?size gt 0>
                            <li class="top-menu-invisible">
                                <a href="#"><i class="${(child.icon)!}"></i><span
                                        class="menu-item-parent">${(child.name)!}</span></a>
                                <ul>
                                    <@menu children=child.children/>
                                </ul>
                            </li>
                        <#else>
                            <li>
                                <a href="${(child.url)!}" class="action-menu"><i
                                        class="${(child.icon)!}"></i>
                                    <span class="menu-item-parent">${(child.name)!}</span></a>
                            </li>
                        </#if>
                    </#list>
                </#if>
            </#macro>
            <@menu children=resouces />
        </ul>
    </nav>
    <span class="minifyme" data-action="minifyMenu">
        <i class="fa fa-arrow-circle-left hit"></i>
    </span>
</aside>
<div id="main" role="main">
    <input type="hidden" id="system_user_language" value="<@shiro.principal property="deptName"/>"/>
    <div id="dialog-message" style="width: 100%;text-align: center;height: 0;display: none;z-index: 999999999;">
        <div class="alert ajax-message">
            <span></span>
        </div>
    </div>

    <article>
        <div class="modal fade" role="dialog" id="language_model_common" style="z-index: 2000">
            <div class="modal-dialog">
                <input type="hidden" value="0" id="spare-type">
                <div class="modal-content" style="width:500px">
                    <div class="modal-header">
                        <h4>Languages</h4>
                    </div>
                    <div class="modal-body no-padding">
                        <form class="smart-form">
                            <fieldset>
                                <label class="col col-2 text-align-right padding">
                                    中文
                                </label>
                                <label class="col col-8 input" id="money-unit">
                                    <input name="zh" type="text" disabled/>
                                </label>
                            </fieldset>
                            <fieldset>
                                <label class="col col-2 text-align-right padding">
                                    English
                                </label>
                                <label class="col col-8 input" id="money-unit">
                                    <input name="en" type="text"/>
                                </label>
                            </fieldset>
                            <#--<#list langs as lang>-->
                            <#--<fieldset>-->
                                <#--<label class="col col-2 text-align-right padding">-->
                                    <#--${(lang.name)!}-->
                                <#--</label>-->
                                <#--<label class="col col-8 input" id="money-unit">-->
                                    <#--<input name="${(lang.code)!}" type="text"/>-->
                                <#--</label>-->
                            <#--</fieldset>-->
                            <#--</#list>-->
                            <footer>
                                <button type="button" id="submit_price" class="btn btn-primary" data-dic="body.list.submit"></button>
                                <button type="button" id="cancel" class="btn btn-default" data-dismiss="modal"  data-dic="body.list.cancel"></button>
                            </footer>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </article>

    <div id="ribbon">
        <ol class="breadcrumb">
            <li>主数据维护</li>
            <li>模组信息</li>
        </ol>
        <div class="pull-right" style="margin-top: -5px;padding-bottom: 5px;">
            <div id="hide-menu" class="btn-header pull-right">
            <span> <a href="javascript:void(0);" data-action="toggleMenu" title="Collapse Menu"><i
                    class="fa fa-reorder"></i></a> </span>
            </div>
            <div id="logout" class="btn-header transparent pull-right">
            <span>
                <a href="/logout" data-action="userLogout" id="user-logout" data-logout-msg="">
                    <i class="fa fa-sign-out"></i>
                </a>
            </span>
            </div>
            <div id="fullscreen" class="btn-header transparent pull-right">
            <span> <a href="javascript:void(0);" data-action="launchFullscreen" title="Full Screen"><i
                    class="fa fa-arrows-alt"></i></a> </span>
            </div>
        </div>
    </div>

    <div id="content">
        填充内容
    </div>
    <#--遮罩层-->
    <div class="modal fade in" id="bootstrap-Mask" data-backdrop="static" data-keyboard="false"></div>
</div>

<script data-pace-options='{ "restartOnRequestAfter": true }'
        src="../static/js/plugin/pace/pace.min.js"></script>
<script src="../static/js/libs/common.js"></script>
<script src="../static/i18n/messages/<@shiro.principal property="deptName"/>.js"></script>
<script src="../static/js/libs/jquery-2.1.1.min.js"></script>
<script src="../static/js/libs/jquery-ui-1.10.3.min.js"></script>
<script src="../static/js/app.config.js"></script>
<script src="../static/js/bootstrap/bootstrap.min.js"></script>
<script src="../static/js/notification/SmartNotification.min.js"></script>
<script src="../static/js/smartwidgets/jarvis.widget.min.js"></script>
<script src="../static/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>
<script src="../static/js/plugin/sparkline/jquery.sparkline.min.js"></script>
<script src="../static/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="../static/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<script src="../static/js/plugin/select2/select2.min.js"></script>
<script src="../static/js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>
<script src="../static/js/plugin/fastclick/fastclick.min.js"></script>
<script src="../static/js/app.min.js"></script>

<script src="../static/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="../static/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="../static/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="../static/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="../static/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>
<script src="../static/js/libs/datatable.js"></script>
<script src="../static/js/plugin/bootstrapvalidator/bootstrapValidator.min.js"></script>
<script src="../static/js/utils/utils.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $("#user-logout").data("logout-msg", message["main.user.logout"]);
        pageSetUp();
        //重复点击菜单刷新界面
        // $("#left-panel").on("click", ".action-menu", function () {
        //     window.parent.location.reload();
        // });
    });

    var timeout;

    var lang = ["zh","en"];

    window.alert = function (message, type) {
        var color;
        var icon;

        switch (type) {
            case 1:
                color="#5F895F";
                icon = "fa fa-check bounce animated";
                break;
            case 2:
                color="#FFA500";
                icon = "glyphicon glyphicon-info-sign bounce animated";
                break;
            case 3:
                color="#DC143C";
                icon = "glyphicon glyphicon-remove bounce animated";
                break;
            default:
                color="#5F895F";
                icon = "fa fa-check bounce animated";
                break;

        }
        $.smallBox({
            title: "",
            content: "<i>" + message + "</i>",
            color: color,
            iconSmall: icon,
            timeout: 3000
        });

    };
    //遮罩层显示关闭
    function showLoading(){
        $("#bootstrap-Mask").modal("show");
    }
    function hideLoading(){
        $("#bootstrap-Mask").modal("hide");
    }
    //转义表单序列化相关字符
    function encodeContent(data){
        return encodeURI(data).replace(/&/g,'%26').replace(/\+/g,'%2B').replace(/\s/g,'%20').replace(/#/g,'%23');
    }
    
    /**
     * 回车查询事件
     */
    $('body').bind('keyup', function(event) {
        if (event.keyCode == "13") {
            //回车执行查询
            // $('.btn-primary .query-btn').click();
            $('#btn_query').click();
            $('#search_organization_btn').click();
            
        }
    });
</script>
</body>

</html>
</#compress>