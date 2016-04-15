$(function(){
    //生成验证码
    getVerifyCodePic("verify_code_pic");
    
    //点击刷新验证码
    $("#login_refresh").click(function(){
        getVerifyCodePic("verify_code_pic");
    })
    
    $("#submit_register").click(function(){
        var param = getRegistInfo();
        register(param);
    });
});


//获取验证码图片
function getVerifyCodePic(id){
    var param = {};
    API.verifyCode(param,function(res){
        if(res.returnData.code = "0"){
            var data = res.returnData.data;
            $("#"+id).attr("src",data.img);
        }
    });
}

//获取注册信息

function getRegistInfo(){
    var param={};
    param.name = $("#name").val();
    param.password = $("#password").val();
    param.email = $("#email").val();
    param.verifyCode = $("#verify_code").val();
    return param;
}

function register(param){
    API.register(param,function(res){
        alert(JSON.stringify(res));
    });
}
