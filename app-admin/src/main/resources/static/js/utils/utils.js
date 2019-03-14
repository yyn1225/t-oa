/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

function moneyFormat(s, n)
{
    n = n > 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var l = s.split(".")[0].split("").reverse(),
        r = s.split(".")[1];
    t = "";
    for(i = 0; i < l.length; i ++ )
    {
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
    }
    return t.split("").reverse().join("") + "." + r;
}

Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

function isNum(val){
    return val==+val;
}


function bindLanguages($input,saveCallback){
    var lang=$("#system_user_language").val();
    var outHtml=$input.prop("outerHTML");
    var parent = $input.parent();
    var group="<div class='input-group input-group-sm'>" +
        outHtml +
        "<div class='input-group-btn'>" +
        "<button class='btn btn-default' type='button' style='height: 32px;'>" +
        "<i class='fa fa-list'></i>" +
        "</button>" +
        "</div>" +
        "</div>";
    $(parent).html(group);
    var input=$(parent).find("input");
    $(input).change(function(){
       $(this).data(lang,$(this).val());
    });
    //页面需要翻译字段右边的按钮点击事件
    $(parent).find("button").off("click").click(function(){
        var zhInput = $("#language_model_common input[name='zh']");
        var enInput = $("#language_model_common input[name='en']");
        zhInput.val($(input).val());
        if ($(input).data('en') == '') {
            $.ajax({
                url: "/sys/translate/get",
                type: "get",
                data: {chinese: $(input).val(), category: $(input).data('category')},
                success: function (data) {
                    enInput.val(data);
                    $("#language_model_common").find("button.btn-primary").addClass("save");
                    $("#language_model_common").find("button.btn-primary").text(message['header.list.button.add']);
                    //翻译某个字段的弹窗提交按钮

                        $("#language_model_common").find("button.save").off("click").click(function () {
                            var zh = zhInput.val();
                            var en = enInput.val();
                            $(input).data("zh", zh);
                            $(input).data("en", en);
                            $(input).val("zh" == lang ? zh : en);
                            $("#language_model_common").modal("hide");
                            saveCallback && saveCallback();
                            if (data == '') {
                                $.ajax({
                                    url: "/sys/translate/save",
                                    type: "post",
                                    data: {
                                        category: $(input).data('category'),
                                        chinese: $(input).val(),
                                        language: "en",
                                        translate: en
                                    },
                                    success: function () {
                                    }
                                });
                            }
                        });
                },
                error: function (data) {
                    alert(message["alert.message.systemError"]);
                }
            });
        }else {
            enInput.val($(input).data('en'));
            $("#language_model_common").find("button.btn-primary").addClass("normal");
            $("#language_model_common").find("button.btn-primary").text(message['button.update']);
        }

            //翻译某个字段的弹窗提交按钮
        $("#language_model_common").find("button.normal").off("click").click(function(){
                var zh = zhInput.val();
                var en = enInput.val();
                $(input).data("zh",zh);
                $(input).data("en",en);
                $(input).val("zh"==lang?zh:en);
                $("#language_model_common").modal("hide");
                saveCallback && saveCallback();
            });
        $("#language_model_common").modal("show");
        // $("#language_model_common input").each(function () {
        //     var self = $(this);
        //     self.val($(input).data(self.attr("name")));
        // });
        //
        // $("#language_model_common").find("button.btn-primary").off("click").click(function(){
        //     $("#language_model_common input").each(function () {
        //         var self = $(this);
        //         $(input).data(self.attr("name"), self.val());
        //         if (self.val() == lang) {
        //             $(input).val(self.val());
        //         }
        //     });
        //     $("#language_model_common").modal("hide");
        //     saveCallback && saveCallback();
        // });
        // $("#language_model_common").modal("show");
    });
}


