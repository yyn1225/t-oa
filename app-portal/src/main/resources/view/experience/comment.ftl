<div id="comment-append" xmlns="http://www.w3.org/1999/html">
<#if (shareComment.commentDtoList)?exists>
  <#list (shareComment.commentDtoList) as item>
    <fieldset class="panel" style="height: auto;">
      <div id="${(item.id?c)!}" style="width:100%;height:auto;">
        <span>${(item.createUser)!}</span>
        <span style="float: right;">${(item.createTime)!} <a style="float: right"
                                                             onclick="onOpenComment('${(item.id?c)!}')">
          <span style="float: right;">&nbsp;&nbsp;<dic
              data-dic="main.title.spare.reply"></dic></span>
        </a>
         <#if item.isAllow == '1'>
             <a style="float: right" onclick="onDeleteComment('${(item.id?c)!}')">
          <span style="float: right;">&nbsp;&nbsp;<dic
                  data-dic="header.panellist.button.delete"></dic></span>
        </a>
         </#if>
        </span>
      </div>
      <div style="width:100%;height:auto;margin-top: 6px;">
        <span>${(item.content)!}</span>
      </div>
      <div id="commentImage" style="height: auto;width: 100%;">
        <#if (item.listImages)?exists>
          <#list (item.listImages) as images>
            <div id="${(images.id?c)!}" class="file-item thumbnail">
                <a class="example-image-link" href="${(images.url)!}" data-lightbox="example-set"  ><img class="pimg" src="${(images.url)!}"/></a></div>
          </#list>
        </#if>
        <div class="clear" style="clear:both;"></div>
      </div>
      <#if (item.experienceCommentList)?exists>
        <#list (item.experienceCommentList) as comment>
          <div id="${(comment.id?c)!}" style="width: 100%;height: auto;margin-top: 4px;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="font-weight:bold;">${(comment.createUser)!}
            &nbsp;<dic data-dic="main.title.spare.reply"></dic> </span>
            <#if comment.isAllow == '1'>
                <a style="float: right" class="col col-3" onclick="onDeleteReply('${(comment.id?c)!}')">
              <span style="float: right;">&nbsp;${(comment.createTime)!}&nbsp;<dic
                      data-dic="header.panellist.button.delete"></dic></span>
                </a>
            </#if>
          </div>
          <div id="${(comment.id?c)!}" style=" height: auto;width: 84%;margin: 0 auto;">
            <span style="fill-rule: gray;">${(comment.content)!}</span>
          </div>
        </#list>
      </#if>
    </fieldset>
  </#list>
</#if>
  <div id="outerdiv"
       style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:3000;width:100%;height:100%;display:none;">
    <div id="innerdiv" style="position:absolute;">
      <img id="bigimg" style="border:5px solid #fff;" src=""/>
    </div>
  </div>
</div>

<script type="text/javascript">
  $('[data-dic]').each(function () {
    $(this).html(message[$(this).data("dic")]);
  });
  // $(".pimg").click(function () {
  //   var _this = $(this);//将当前的pimg元素作为_this传入函数
  //   imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
  // });

</script>