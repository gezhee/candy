
var API = {
    callInterface: function(url, data, async, callback, errorcallback) {
        var timestamp = (new Date()).getTime();
        var aparam = signparam(data, timestamp, '');
        data.timestamp = timestamp;
        data.sb = aparam;
        data.content = "json"
        var settings = {
            type: 'POST',
            url: '/candy-web/web/b/' + url,
            data: data,
            async: async,
            cache: false,
            dataType: 'json',
            beforeSend: function() { return true; },
            success: function(res) {
                callback(res);
            },
            error: function(result) {
//                window.location.href = "../login.html";
//                errorcallback(result);
            },
            complete: function() {}
        };
        $.ajax(settings);
    },
    test:function(param,callback){
        param.iv="1.0";
        API.callInterface("test.do",param,true,callback)
    },
    verifyCode:function(param,callback){
        param.iv="1.0";
        API.callInterface("verify.do", param, true, callback)
    },
    register:function(param,callback){
        param.iv="1.0";
        API.callInterface("register.do", param, true, callback)
    }
    
}


