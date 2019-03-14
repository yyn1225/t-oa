<#compress>
<span id="panel-size" style="display: none" data-row="${height}" data-col="${width}"></span>
<div id="right" style="float:left;margin:0;overflow:auto;height: 400px;width: 400px;">
    <table style="border:0;" cellspacing="0" cellpadding="0">
        <#list 1..height as row>
            <tr <#if row==height>class="no-bottom"</#if>>
                <#list 1..width as col>
                    <td>
                        <div></div>
                    </td>
                </#list>
            </tr>
        </#list>
    </table>
</div>

<div>
    <button style="margin-left:20px;margin-top:10px;" id="reset">Clear All</button>
</div>

<div style="float:left;width:100%">&nbsp;</div>

<!-- 左边元素框 -->
<div id="left" style="float:left;">
    <div style="float:left;width:600px;margin-left:90px;">
        <span>4*4</span>
        <span style="margin-left:69px;display:none">4*2</span>
        <span style="margin-left:47px;display:none">2*3</span>
        <span style="margin-left:37px;display:none">3*2</span>
        <span style="margin-left:47px;display:none">3*3</span>
    </div>
    <div style="float:left;font-size:18px;font--weight:400;">XD6:&nbsp;&nbsp;&nbsp;&nbsp;</div>


    <table cellspacing="0" cellpadding="0" class="target1" data-color='e7761e' data-col="4" data-row='4' draggable="true" style="width:80px;height:80px;">
        <tr style="height:20px;">
            <td style="width:20px;"/>
            <td style="width:20px;"/>
            <td style="width:20px;"/>
            <td style="width:20px;"/>
        </tr>
        <tr style="height:20px;"><td></td><td></td><td></td><td></td></tr>
        <tr style="height:20px;"><td></td><td></td><td></td><td></td></tr>
        <tr style="height:20px;"><td></td><td></td><td></td><td></td></tr>
    </table>

    <label class="gt">&gt;</label>

    <table cellspacing="0" cellpadding="0" class="target2" data-color='71d38c' data-col='4' data-row='2' draggable="true" style="width:80px;height:40px;display:none">
        <tr style="height:20px;">
            <td style="width:20px;"/>
            <td style="width:20px;"/>
            <td style="width:20px;"/>
            <td style="width:20px;"/>
        </tr>
        <tr style="height:20px;"><td></td><td></td><td></td><td></td></tr>
    </table>

    <table cellspacing="0" cellpadding="0" class="target3" data-color='C0C0C0' data-col="2" data-row='3' draggable="true" style="width:40px;height:60px;display:none">
        <tr style="height:20px;">
            <td style="width:20px;"/>
            <td style="width:20px;"/>
        </tr>
        <tr style="height:20px;"><td/><td/></tr>
        <tr style="height:20px;"><td/><td/></tr>
    </table>

    <table cellspacing="0" cellpadding="0" class="target4" data-color='ff60c9' data-col="3" data-row='2' draggable="true" style="width:60px;height:40px;display:none">
        <tr style="height:20px;">
            <td style="width:20px;"/>
            <td style="width:20px;"/>
            <td style="width:20px;"/>
        </tr>
        <tr style="height:20px;"><td/><td/><td/></tr>
    </table>

    <table cellspacing="0" cellpadding="0" class="target5" data-color='0d88c1' data-col="3" data-row='3'  draggable="true" style="width:60px;height:60px;display:none">
        <tr style="height:20px;">
            <td style="width:20px;"/>
            <td style="width:20px;"/>
            <td style="width:20px;"/>
        </tr>
        <tr style="height:20px;"><td/><td/><td/></tr>
        <tr style="height:20px;"><td/><td/><td/></tr>
    </table>
</div>

<script>
    var moveItem = document.getElementsByTagName('table');

    var fromElement={};
    var colStart=0;
    var rowStart=0;

    for (let i = 0; i < moveItem.length; i++) {
        moveItem[i].ondragstart = function () {
            fromElement = moveItem[i];
        };
    }

    document.getElementById('right').ondragover = function (ev) {
        ev.preventDefault(); //阻止向上冒泡
    };

    document.getElementById('right').ondrop = function (ev) {
        ev.preventDefault();
        var newNode=fromElement.cloneNode(true);
        newNode.onclick=function(){
            this.remove();
        };
        var td=ev.target;
        if(td.tagName=="TD"){
            var tdIndex=Math.max($(td).index()-colStart,0);
            var trIndex=Math.max($(td).parent("tr").index()-rowStart,0);
            var length=$(fromElement).data("col");
            var weight=$(fromElement).data("row");
            if((25-tdIndex)<length || (10-trIndex)<weight){
                return false;
            }

            for(var tdStart=tdIndex;tdStart<length+tdIndex;tdStart++){
                for(var trStart=trIndex;trStart<weight+trIndex;trStart++){
                    var sing = $("tr:eq("+trStart+")").find("td:eq("+tdStart+")");
                    if(sing.data("tag")){
                        return false;
                    }
                }
            }

            for(var tdStart=tdIndex;tdStart<length+tdIndex;tdStart++){
                for(var trStart=trIndex;trStart<weight+trIndex;trStart++){
                    var sing = $("tr:eq("+trStart+")").find("td:eq("+tdStart+")");
                    sing.data("tag",tdIndex+"/"+trIndex);
                    sing.attr("data-tag",tdIndex+"/"+trIndex);
                    sing.css("background-color","#"+$(fromElement).data("color"));
                }
            }
        }
    };

    $("td").click(function(){
        var tag=$(this).data("tag");
        var tds=$("td[data-tag='"+tag+"']");
        tds.data("tag","");
        tds.removeAttr("data-tag");
        tds.css("background-color","");
    });

    $("table[data-color] td").mousedown(function(){
        var td=$(this).index();
        var tr=$(this).parent("tr").index();
        colStart=td;
        rowStart=tr;
    });

    $("#reset").click(function(){
        var tds=$("#right td");
        tds.data("tag","");
        tds.removeAttr("data-tag");
        tds.css("background-color","");
    });

    var cols = $("#panel-size").data("width");
    var rows= $("#panel-size").data("height");
    $("table[data-color]").click(function(){
        fromElement=$(this);
        colStart=0;
        rowStart=0;
        var tds=$("#right td:not([data-tag])");
        for(var i=0;i<tds.length;i++){
            var td=tds[i];
            var tdIndex=Math.max($(td).index()-colStart,0);
            var trIndex=Math.max($(td).parent("tr").index()-rowStart,0);
            var length=$(fromElement).data("col");
            var weight=$(fromElement).data("row");
            if((cols-tdIndex)<length || (rows-trIndex)<weight){
                continue;
            }

            var emptys=true;
            for(var tdStart=tdIndex;tdStart<length+tdIndex;tdStart++){
                for(var trStart=trIndex;trStart<weight+trIndex;trStart++){
                    var sing = $("tr:eq("+trStart+")").find("td:eq("+tdStart+")");
                    if(sing.data("tag")){
                        emptys=false;
                        break;
                    }
                }
                if(!emptys){
                    break;
                }
            }
            if(!emptys){
                continue;
            }

            for(var tdStart=tdIndex;tdStart<length+tdIndex;tdStart++){
                for(var trStart=trIndex;trStart<weight+trIndex;trStart++){
                    var sing = $("tr:eq("+trStart+")").find("td:eq("+tdStart+")");
                    sing.data("tag",tdIndex+"/"+trIndex);
                    sing.attr("data-tag",tdIndex+"/"+trIndex);
                    sing.css("background-color","#"+$(fromElement).data("color"));
                }
            }
            break;
        }
    });

    $(".gt").click(function(){
        $("table").show();
        $("div#left span").show();
        $(this).hide();
    });
</script>
<style scoped>
    div#right {
        width: auto;
        margin: 10px 100px 10px 0;
    }

    div#right{
        height:200px;
        width:500px;
        border: 1px solid #696969;
        padding-right:1px;
        padding-bottom:1px;
        overflow-y:visible;
    }

    div label {
        vertical-align:middle;
        float:left;
        font-size: 6px;
        font-weight: bold;
        display: inline-block;
        text-align: center;
        color: #fff;
        border:1px solid #ccc;
        cursor:pointer;
        margin:0;
        box-sizing: border-box;
    }

    button{
        padding:6px 10px;
        background-color:#0d88c1;
        border:1px solid #CCC;
        border-radius:3px;
        color:#FFF;
        font-size:14px;
    }

    input{
        padding:2px 5px;
        height:23px;
        border:1px solid #CCC;
        border-radius:3px;
        color:#BBB;
    }

    td{
        border:1px solid #CCC;
    }

    td div{
        height: 20px;
        width: 20px;
    }

    tr{
        height:20px;
    }

    .no-bottom td{
        border-bottom:0;
    }
</style>
</#compress>