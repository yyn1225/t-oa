<!DOCTYPE html>
<html lang="en-us" id="extr-page">
    <head>
        <meta charset="utf-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" media="screen" href="../static/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" media="screen" href="../static/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" media="screen" href="../static/css/smartadmin-production-plugins.min.css">
        <link rel="stylesheet" type="text/css" media="screen" href="../static/css/smartadmin-production.min.css">
        <link rel="stylesheet" type="text/css" media="screen" href="../static/css/smartadmin-skins.min.css">
        <link rel="stylesheet" type="text/css" media="screen" href="../static/css/smartadmin-rtl.min.css">
        <link rel="shortcut icon" href="../static/img/favicon/favicon.png" type="image/x-icon">
        <link rel="icon" href="../static/img/favicon/favicon.png" type="image/x-icon">
    </head>

    <body class="animated fadeInDown">
        <div id="main" role="main">
            <div id="content" class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-7 col-lg-4 hidden-xs hidden-sm">
                        &nbsp;
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-5 col-lg-4">
                        <div class="row" style="text-align: center">
                            <h1 class="txt-color-red login-header-big">Absen</h1>
                        </div>
                        <div class="well no-padding">
                            <form id="login-form" class="smart-form client-form">
                                <header dic="login"></header>
                                <fieldset>
                                  <section style="padding:0px 10px 13px 0px;">
                                    <label class="radio col col-6">
                                      <input type="radio" name="server" value="${(internal)!}" checked>
                                      <i></i> &nbsp;&nbsp;<dic data-dic="internal"></dic>
                                    </label>
                                    <label class="radio col col-6">
                                      <input type="radio" name="server" value="${(foreign)!}">
                                      <i></i> &nbsp;&nbsp;<dic data-dic="foreign"></dic>
                                    </label>
                                  </section>
                                    <section  style="margin-top: 30px">
                                        <label class="label" dic="username"></label>
                                        <label class="input"> <i class="icon-append fa fa-user"></i>
                                            <input type="text" name="username" maxlength="20"/>
                                            <b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i><span dic="username.plt"></span></b>
                                        </label>
                                    </section>

                                    <section>
                                        <label class="label" dic="password"></label>
                                        <label class="input">
                                            <a class="icon-append fa fa-lock" style="cursor: pointer;" id="changePwd"></a>
                                            <input type="password" name="password" maxlength="20"/>
                                            <b class="tooltip tooltip-top-right">
                                                <i class="fa fa-lock txt-color-teal"></i>
                                                <span dic="password.plt"></span>
                                            </b>
                                        </label>
                                    </section>

                                    <section>
                                        <label class="checkbox col col-6">
                                            <input type="checkbox" name="remember">
                                            <i></i> &nbsp;&nbsp;<span dic="remember"></span>
                                        </label>
                                        <label class="radio col col-3">
                                            <input type="radio" name="language" value="zh" checked>
                                            <i></i> &nbsp;&nbsp;中文
                                        </label>
                                        <label class="radio col col-3">
                                            <input type="radio" name="language" value="en">
                                            <i></i> &nbsp;&nbsp;English
                                        </label>
                                    </section>
                                </fieldset>
                                <footer>
                                    <button id="loginSubmit" class="btn btn-primary"><span dic="login"></span></button>
                                </footer>
                            </form>

                            <form id="submit_form" method="post" action="${(internal)!}/login" style="display: none;">
                                <input name="username" id="username"/>
                                <input name="password" id="password"/>
                                <input name="remember" id="remember"/>
                                <input name="language" id="language"/>
                                <input type="submit" id="submit_btn"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="../static/js/libs/jquery-2.1.1.min.js"></script>
        <script src="../static/js/libs/jquery-ui-1.10.3.min.js"></script>
        <script src="../static/js/plugin/pace/pace.min.js"></script>
        <script src="../static/js/app.config.js"></script>
        <script src="../static/js/bootstrap/bootstrap.min.js"></script>
        <script src="../static/js/plugin/jquery-validate/jquery.validate.min.js"></script>
        <script src="../static/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
        <script src="../static/js/app.min.js"></script>
        <script src="../static/js/utils/md5.min.js"></script>
        <script type="text/javascript">
            var userMessage={
                zh:{
                    foreign:"国外服务器",
                    internal:"国内服务器",
                    username:"工号",
                    password:"密码",
                    login:"登录",
                    "username.plt":"请输入您的工号",
                    "password.plt":'请输入登录密码',
                    remember:"记住我",
                    error1:"用户不存在",
                    error2:"该账号已被禁用",
                    error3:"用户名或密码错误"
                },
                en:{
                    foreign:"Foreign server",
                    internal:"Domestic server",
                    username:"EmployeeId",
                    password:"Password",
                    login:"Login",
                    "username.plt":"Please fill in your EmployeeNo",
                    "password.plt":'Please fill in your Password',
                    remember:"Remember",
                    error1:"Account does not exist",
                    error2:"The account has been banned",
                    error3:"ERROR Incorrect username or password"
                }
            };

            $("#changePwd").off("mousedown").mousedown(function (e) {
                $("[name='password']").attr("type","text");
            });

            $("#changePwd").off("mouseup").mouseup(function (e) {
                $("[name='password']").attr("type","password");
            });

            function buildDic(){
                var language=$("input[name='language']:checked").val();
                $.each($("[dic]"),function(){
                    var mess=$(this).attr("dic");
                    $(this).html(userMessage[language][mess]);
                });
                $("[data-dic]").each(function () {
                    var mess=$(this).attr("data-dic");
                    $(this).html(userMessage[language][mess]);
                });
            }

            runAllForms();

            $(function() {
                $("#login-form").validate({
                    rules : {
                        username : {
                            required : true,
                            minlength : 3,
                            maxlength : 20
                        },
                        password : {
                            required : true,
                            minlength : 3,
                            maxlength : 20
                        }
                    },
                    messages : {
                        email : {
                            required : 'Please enter your email address',
                            email : 'Please enter a VALID email address'
                        },
                        password : {
                            required : 'Please enter your password'
                        }
                    },
                    errorPlacement : function(error, element) {
                        error.insertAfter(element.parent());
                    }
                });

                $("#loginSubmit").off("click").click(function (e) {
                    e.preventDefault();
                    var valid = $("#login-form").valid();
                    if(valid){
                        $("#username").val($("#login-form [name='username']").val());
                        $("#password").val(md5($("#login-form [name='password']").val()));
                        $("#remember").val($("#login-form [name='remember']").prop("checked"));
                        $("#language").val($("input[name='language']:checked").val());
                        $("#submit_form").submit();
                    }
                });

                var lang = getUrlParam("lang");
                var msg=getUrlParam("msg");

                if(!!lang){
                    $("input[name='language'][value='"+lang+"']").prop("checked",true);
                }

                if(!!msg){
                    var language=$("input[name='language']:checked").val();
                    alert(userMessage[language]['error'+msg]);
                }

                $("input[name='language']").change(function(){
                    buildDic();
                });
              $("input[name='server']").change(function(){
                var serverUrl = $("input[name='server']:checked").val() +"/login";
                $("#submit_form").attr('action',serverUrl);
              });
                buildDic();
            });

            function getUrlParam(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
                var r = window.location.search.substr(1).match(reg);  //匹配目标参数
                if (r != null) return unescape(r[2]); return null; //返回参数值
            }
        </script>
    </body>
</html>