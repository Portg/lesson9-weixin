$(function() {
    $("#inner").click(function(){ 
        lottery();
    });
    $("#save-btn").bind("click", function() {
        sendTel();
    });
});

var count = 0;
var awards;

function lottery() {

    var openid =$("#openid").val();
    var subscribe =$("#subscribe").val();
    var actId =$("#actId").val();
    var jwid =$("#jwid").val();

    var url = "../spinwin/getAwards.do";
    $.ajax({ 
        url: url,
        type:"post",
        dataType: "json",
        data:{
            actId:actId,
            openid:openid,
            subscribe:subscribe,
            jwid:jwid
        },
        error: function(){
            alert('出错了！');
            return false;
        },
        success: function(data) {
            $("#inner").unbind('click').css("cursor","default");
            var msg = "谢谢您的参与，下次再接再厉";
            if (data.success) {
                msg = data.attributes.wxActSpinwinPrize.awardsName;//奖项 
                awards = data.attributes.wxActSpinwinPrize.awardsValue;
                var recordId = data.attributes.wxActSpinwinRecord.id;
                $('#recordId').val(recordId);
                if (isNull(msg)) {
                    msg = "谢谢您的参与，下次再接再厉";
                }
            } else {
                if(data.obj=="isNotFoucs"){
                    alert("非关注用户");
                    return;
                } else if(data.obj=="isNotBind"){
                    alert("未绑定手机号");
                    return;
                } else if(data.obj=="yzj"){
                    var recordId = data.attributes.wxActSpinwinRecord.id;
                    $('#recordId').val(recordId);
                    alert('本次活动你已经中过奖，本次只显示你上次抽奖结果!兑奖码为:' + recordId);
                    return;
                } else {
                    alert(data.msg);
                    return;
                }
            }
            var angle = parseInt(data.attributes.angle);//角度
            if (!isNull(angle)) {
                count++;
            }
            $("#outer").rotate({
                duration: 5000,//转动时间 
                angle: 0, 
                animateTo: 1440 + angle,//转动角度 
                easing: $.easing.easeOutSine,
                callback: function(){
                    if(count != 0) {
                        var use_times = $("#count").html();//每天剩余抽奖次数
                        if(use_times==null){
                            use_times = $("#num").html();//剩余抽奖次数
                            use_times--;
                            $("#num").html(use_times);
                        }else{
                            use_times--;
                            $("#count").html(use_times);
                        }
                    }
                    if (awards!= null) {
                        setTimeout(function() {
                            $("#prizetype").text(msg);
                            $("#result").slideToggle(500);
                            $("#outercont").slideUp(500)
                        }, 200);
                        return false;
                    }
                    var con = confirm(msg + '\n还要再来一次吗？');
                    if(con){ 
                        lottery();
                    }else{ 
                        $("#inner").click(function() {
                            lottery();
                        });
                        return false;
                    }
                }
            });

        }
    }); 
}
function sendTel() {

    var tel = $("#tel").val();
    var address = $("#address").val();
    var username = $("#username").val();
    var recordId = $("#recordId").val();
    if (username == '') {
        alert("请认真输入姓名");
        return;
    }
    if (tel == '') {
        alert("请输入手机号");
        return;
    }else if (!isMobile(tel)){
        alert("手机号格式不正确，请重新输入！");
        return;
    }

    var url = "../spinwin/updateRecord.do";
    $.ajax({
        url : url,
        type: "post",
        dataType : "json",
        data:{
            id:recordId,
            phone:tel,
            address:address,
            realname:username
        },
        success : function(data){
            if(data.success){
                alert("提交成功，谢谢您的参与");
                $("#result").hide("slow");
                setTimeout("location.reload()",1000);
                return;
            }else{
                
            }
        }
    });
}

//初始化下标
function resetIndex(idv) {
    $box = $("#"+idv+"");
    $box.find("tr").each(function(i){
        $(this).find("td").each(function(){
            var $this = $(this).children(), name = $this.attr('name'), val = $this.val();
            if(name!=null){
                if (name.indexOf("#index#") >= 0){
                    $this.attr("name",name.replace('#index#',i));
                }else{
                    var s = name.indexOf("[");
                    var e = name.indexOf("]");
                    var new_name = name.substring(s+1,e);
                    $this.attr("name",name.replace(new_name,i));
                }
            }
        });
    });    
}

//手机号数字正则验证
function isMobile(s){
    var patrn= /^1((3\d)|(4[57])|(5[01256789])|(7[678])|(8\d))\d{8}$/;
    if (!patrn.exec(s)) return false;
    return true;
}

function isNull(val) {
    if(!val){
        return true;
    }
    if(val == null || val == "" || val == "undefined" || val == "null" || val == "NULL"){
        return true;
    }
    return false;
}