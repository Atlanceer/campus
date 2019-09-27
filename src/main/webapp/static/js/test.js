//发送ajax请求测试
$(function () {
    $("#test").click(function (){
       $.ajax({
           url:"./user/test",
           contentType:"application/json;charset=UTF-8",
           headers:{
               token:"eyJhbGciOiJIUzI1NiIsIlR5cGUiOiJKd3QiLCJ0eXAiOiJKV1QifQ.eyJpZCI6IjExMTExMTExMTExMTExMTEiLCJleHAiOjE1NjU1MTU3MjgsInVzZXJuYW1lIjoidGVzdCJ9.VWdRIsLuHvRIkbZ3Q-mP_6rKPgziddXKdpHjQQEwVdQ"
           },
           data:'{"key":"value","key2":"value2"}',
           dataType:"json",
           type:"post",
           success:function(data){
                //数据解析
           }
       });
    });
    $("#register").click(function (){
        $.ajax({
            url:"./user/register",
            /*data:"username="+$("#username").val()+"&password="+$("#password").val(),*/
            contentType:"application/json;charset=UTF-8",
            headers:{
                codeToken:"eyJhbGciOiJIUzI1NiIsIlR5cGUiOiJKd3QiLCJ0eXAiOiJKV1QifQ.eyJjb2RlIjoiMjcyOTQyIiwiZXhwIjoxNTY1NTQwNDUzfQ.ahwipQgZ-pCLsOgXlhwU5MQv3--dk5xcWWmCCCkMK1s"
            },
            data:'{"phone":"17828298850","password":"value2","code":"272942"}',
            dataType:"json",
            type:"post",
            success:function(data){
                /*alert(data.status);*/
                if (data.status){
                    alert("success");
                } else {
                    alert(data.message);
                }
            }
        });
    });
    $("#login").click(function (){
        $.ajax({
            url:"./user/login",
            /*data:"username="+$("#username").val()+"&password="+$("#password").val(),*/
            contentType:"application/json;charset=UTF-8",
            data:'{"username":"test","password":"test"}',
            dataType:"json",
            type:"post",
            success:function(data){
                /*alert(data.status);*/
                if (data.status){
                    alert("success");
                } else {
                    alert(data.message);
                }
            }
        });
    });
    $("#sendMessage").click(function (){
        $.ajax({
            url:"./user/getMessage",
            /*data:"username="+$("#username").val()+"&password="+$("#password").val(),*/
            contentType:"application/json;charset=UTF-8",
            data:'{"phone":"1782829885"}',
            dataType:"json",
            type:"post",
            success:function(data){
                /*alert(data.status);*/
                if (data.status){
                    alert("success");
                } else {
                    alert(data.message);
                }
            }
        });
    });
});

