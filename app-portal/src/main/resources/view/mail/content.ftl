<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>邮件模板</title>
</head>
<body>
<#list mailFiles as item>
    <a href="${deployServerPath!}/mail/rest/preview?id=${(item.id?c)!}">${item.name!}</a><br>
</#list>
</body>
</html>