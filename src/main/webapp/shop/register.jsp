<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/4
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录.dangdang商城</title>
    <link rel="shortcut icon" type="image/x-icon" href="img/icon/favicon.ico">
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/home.css">
</head>
<body>

<header id="pc-header">
    <div class="center">
        <div class="pc-fl-logo">
            <h1>
                <a href="index.html"></a>
            </h1>
        </div>
    </div>
</header>
<section>
    <div class="pc-login-bj">
        <div class="center clearfix">
            <div class="fl"></div>
            <div class="fr pc-login-box">
                <div class="pc-login-title"><h2>用户注册</h2></div>
                <form id="zhucefrombyid">
                    <div class="pc-sign">
                        <input type="text" name="username" placeholder="账号/用户名字">
                    </div>
                    <span id="zhuceusername"></span>
                    <div class="pc-sign">
                        <input type="password" name="password" placeholder="请输入您的密码">
                    </div>
                    <span id="zhucepassword"></span>
                    <div class="pc-sign">
                        <input type="password" name="password2" placeholder="请确认您的密码">
                    </div>
                    <span id="zhucepassword2"></span>
                    <div class="pc-submit-ss">
                        <input type="button" id="zhuce-button" value="立即注册" placeholder="">
                    </div>
                    <div class="pc-item-san clearfix">
                        <a href="https://passport.weibo.cn/signin/welcome?entry=mweibo&r=http%3A%2F%2Fpad.weibo.cn%2F&_T_WM=1c7bf9cbc49235968ba2fab8a0ee77e8"><img src="img/icon/weixin.png" alt="">微信登录</a>
                        <a href="https://wx.qq.com/"><img src="img/icon/weibo.png" alt="">微博登录</a>
                        <a href="http://web2.qq.com/" style="margin-right:0"><img src="img/icon/tengxun.png" alt="">QQ登录</a>
                    </div>
                    <div class="pc-reg">

                        <a href="/shop/login.jsp" class="red">已有账号 请登录</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<footer>
    <div class="center">
        <div class="pc-footer-login">
            <p>关于我们 招聘信息 联系我们 商家入驻 商家后台 商家社区 ©2017 Yungouwu.com 北京dangdang网络有限公司</p>
            <p style="color:#999">营业执照注册号：990106000129004 | 网络文化经营许可证：北网文（2016）0349-219号 | 增值电信业务经营许可证：京2-20110349 | 安全责任书 | 京公网安备 99010602002329号 </p>
        </div>
    </div>
</footer>
<script src="../EasyUI/jquery.min.js" type="text/javascript"></script>
<script>
    $('#zhuce-button').click(function () {
    var username=$("input[name=username]").val();

         if(username!=""&&username!=null){
             $("#zhuceusername").html("");
             if(passwordyanz()&&passwordyanz2()){
                 $.ajax({
                     url:"<%=request.getContextPath()%>/dldshop/userzhuce.do",
                     type:"post",
                     data:$("#zhucefrombyid").serialize(),
                     success:function(msg){
                         if(msg==1){
                             location.href="<%=request.getContextPath()%>/shop/login.jsp"
                         }
                     },
                     error:function(){

                     }

                 })
             }
         }else{
             var st="<font colro='red'>用户名不能为空</font>";
             $("#zhuceusername").html(st);
         }

    });
    
    function passwordyanz() {
        var password=$("input[name=password]").val();
        if(password!=""&&password!=null){
            $("#zhucepassword").html("");
            return true;
        }else{
            var st="<font colro='red'>请输入密码不能为空</font>";
            $("#zhucepassword").html(st);
            return false;
        }
    }

    function passwordyanz2() {
        var password2=$("input[name=password2]").val();
        var password=$("input[name=password]").val();
        if(password2!=""&&password2!=null){
            if(password==password2){
                $("#zhucepassword2").html("");
                return true;
            }
            var st="<font colro='red'>两次密码不一致</font>";
            $("#zhucepassword2").html(st);
            return false;
        }else{
            var st="<font colro='red'>请输入密码不能为空</font>";
            $("#zhucepassword2").html(st);
            return false;
        }
    }
</script>
</body>
</html>
