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
    <link rel="shortcut icon" href="../static/img/favicon/favicon.png" type="image/x-icon">
    <link rel="icon" href="../static/img/favicon/favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="../static/css/common.css">
</head>
<style type="text/css">
    .SmallBox p{
        margin-top:7px;
    }
    #downloadAppBox:before{
        left: 90%;
    }
    #downloadAppBox:after{
        left: 90%;
    }
</style>

<body class="desktop-detected menu-on-top pace-done fixed-header smart-style-4 fixed-navigation">
<aside id="left-panel" style="padding-top: 0;">
    <nav>
        <ul>
            <li>
                <a href="/dashboard" title="Dashboard" class="action-menu"><i class="fa fa-lg fa-fw fa-home"></i>
                    <span class="menu-item-parent">
                    <dic data-dic="main.title.homepage"></dic>
                    </span>
                </a>
            </li>
            <li>
                <a href="#"><i class="fa fa-lg fa-fw fa-cube"></i>
                    <span class="menu-item-parent">
                    <dic data-dic="main.title.basic"></dic>
                    </span></a>
                <ul>
                    <li class="">
                        <a href="/seriesproduct" class="action-menu"><i class="fa fa-cube"></i>
                            <dic data-dic="main.title.seriesProduct"></dic>
                        </a>
                    </li>
                    <li class="">
                        <a href="/product" class="action-menu"><i class="fa fa-cubes"></i>
                            <dic data-dic="main.title.product"></dic>
                        </a>
                    </li>
                    <li>
                        <a href="/price/list" class="action-menu"><i class="fa fa-cny"></i>
                            <dic data-dic="product.panel.list.price"></dic>
                        </a>
                    </li>
                    <li>
                        <a href="/skimProduct" class="action-menu"><i class="fa fa-desktop"></i>
                            <dic data-dic="main.title.skimProduct"></dic>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#">
                    <i class="fa fa-lg fa-fw fa-th-list"></i><span class="menu-item-parent">
                    <dic data-dic="main.title.offer"></dic>
                </span>
                </a>
                <ul>
                    <li class="">
                        <a href="/offer/create" class="action-menu"><i class="fa fa-lg fa-fw fa-edit"></i>
                            <dic data-dic="main.title.create"></dic>
                        </a>
                    </li>
                    <li>
                        <a href="/offer/list" class="action-menu"><i class="fa fa-lg fa-fw fa-shopping-cart"></i>
                            <dic data-dic="main.title.myOffer"></dic>
                        </a>
                    </li>
                    <li>
                        <a href="/offer/preference" class="action-menu"><i class="fa fa-heart"></i>
                            <dic data-dic="main.title.preference"></dic>
                        </a>
                    </li>
                </ul>
            </li>
            <#--审批-->
            <li>
                <a href="#">
                    <i class="fa fa-lg fa-fw fa-pencil"></i><span class="menu-item-parent">
                    <dic data-dic="main.title.approval"></dic>
                </span>
                </a>
                <ul>
                    <li class="">
                        <a href="/approval/wait" class="action-menu"><i class="fa fa-gavel"></i>
                            <dic data-dic="main.title.myApproval"></dic>
                        </a>
                    </li>
                    <li>
                        <a href="/approval/already" class="action-menu"><i class="fa fa-eye"></i>
                            <dic data-dic="main.title.alreadyApproval"></dic>
                        </a>
                    </li>
                    <#--<li>-->
                        <#--<a href="/approval/launch"><i class="fa fa-pencil"></i>-->
                            <#--<dic data-dic="main.title.myLaunch"></dic>-->
                        <#--</a>-->
                    <#--</li>-->
                </ul>
            </li>
            <#--客户-->
              <li>
                <a href="/customer/list" >
                  <i class="fa fa-lg fa-fw fa-gift"></i><span class="menu-item-parent">
                        <dic data-dic="main.title.myCustomer"></dic>
                    </span>
                </a>
              </li>
            <#--营销文件-->
              <li>
                <a href="/file/list" >
                  <i class="fa fa-lg fa-fw fa-file"></i><span class="menu-item-parent">
                        <dic data-dic="main.title.fileList"></dic>
                    </span>
                </a>
              </li>
            <#--经验分享-->
            <li>
            <a href="/experience/list" >
              <i class="fa fa-lg fa-fw fa-share"></i><span class="menu-item-parent">
                    <dic data-dic="main.title.experience.sharing"></dic>
                </span>
            </a>
          </li>

            <li style="float: right">
                <a href="#" id="logoutButton"><i class="fa fa-lg fa-fw fa-sign-out"></i><span class="menu-item-parent">
                    <dic data-dic="main.title.logout"></dic>
                </span>
                </a>
            </li>
            <li style="float: right">
                <a href="#" id="android-icon">
                    <i class="fa fa-lg fa-mobile-phone"></i>
                    <span class="menu-item-parent"><dic data-dic="main.title.app"></dic></span>
                    <div class="ajax-dropdown" id="downloadAppBox" style="height: 250px;left: auto;right: 0;top: 77px;">
                        <div style="width: 100%">
                            <div style="width: 100%;">
                                <div style=""></div>
                            </div>
                            <div style="margin-top: 30px;">
                                <div style="width: 49%;float: left;">
                                    <div style="width: 100%;text-align: center;">
                                        <img style="width: 120px;height: 120px;border: 1px solid #ccc;" src="../static/img/app-qr/android.png">
                                    </div>
                                    <div style="width: 100%;text-align: center;margin-top: 10px;">
                                        <i class="fa fa-android"></i>
                                        <label><dic data-dic="main.title.android"></dic></label>
                                    </div>
                                </div>
                                <div style="width: 49%;float: left;">
                                    <div style="width: 100%;text-align: center;">
                                        <img style="width: 120px;height: 120px;border: 1px solid #ccc;" src="../static/img/app-qr/IOS.png">
                                    </div>
                                    <div style="width: 100%;text-align: center;margin-top: 10px;">
                                        <i class="fa fa-apple"></i>
                                        <label><dic data-dic="main.title.ios"></dic></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
            </li>
        </ul>
    </nav>
    <span class="minifyme" data-action="minifyMenu">
        <i class="fa fa-arrow-circle-left hit"></i>
    </span>
</aside>


<div id="main" role="main" style="margin-top:0 !important;padding-bottom: 0 !important;min-height: 0 !important;">

    <div id="content"></div>
    <div class="modal fade in" id="bootstrap-Mask" data-backdrop="static" data-keyboard="false"></div>
</div>

<script src="../static/js/libs/jquery-2.1.1.min.js"></script>
<script src="../static/js/libs/common.js"></script>

<script src="../static/i18n/messages/${language!'zh'}-CN.js"></script>

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
<script src="../static/js/libs/jquery.tmpl.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        pageSetUp();
        $("dic").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
        $("#bootstrap-Mask").modal({
            backdrop: 'static',
            keyboard: false,
            show:false
        });
        //重复点击菜单刷新界面
        $("#left-panel").on("click", ".action-menu", function () {
            window.parent.location.reload();
        });
    });

    /**
     * 回车查询事件
     */
    $('body').bind('keyup', function(event) {
        if (event.keyCode == "13") {
            //回车执行查询
            // $('.btn-primary .query-btn').click();
            $('#btn_query').click();
        }
    });

    $("#logoutButton").off("click").click(function(){
        $.ajax({
            url:"/logout",
            type:"get",
            success:function(){
                window.location.reload();
            }
        });
    });
    $("#android-icon").hover(function(){
        $('#downloadAppBox').show();
    },function(){
        $('#downloadAppBox').hide();
    });

    function showLoading(){
        $("#bootstrap-Mask").modal("show");
    }

    function hideLoading(){
        $("#bootstrap-Mask").modal("hide");
    }

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
    }
</script>
</body>
</html>
</#compress>