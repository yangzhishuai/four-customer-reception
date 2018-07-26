<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/4
  Time: 20:32
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
                <div class="pc-login-title"><h2>用户登录</h2></div>
                <form id="stuform" >
                    <div class="pc-sign">
                        <input type="text" name="username" placeholder="用户名/邮箱/手机号">
                    </div>
                    <span id="logintishixxi"></span>
                    <div class="pc-sign">
                        <input type="password" name="password" placeholder="请输入您的密码">
                    </div>
                    <div class="pc-submit-ss">
                        <input type="button" id="login-button" value="登录" placeholder="">
                    </div>
                    <div class="pc-item-san clearfix">
                <%--        <a href="#"><img src="img/icon/weixin.png" alt="">微信登录</a>
                        <a href="#"><img src="img/icon/weibo.png" alt="">微博登录</a>
                        <a href="#" style="margin-right:0"><img src="img/icon/tengxun.png" alt="">QQ登录</a>--%>
                    </div>
                    <div class="pc-reg">
                        <a href="#">忘记密码</a>
                        <a href="/shop/register.jsp" class="red">免费注册</a>
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
    $('#login-button').click(function () {
        $.ajax({
            url:"<%=request.getContextPath()%>/dldshop/login.do",
            type:"post",
            data:$("#stuform").serialize(),
            success:function(msg){
//				0:用户名不存在 1：密码错误 2：登录成功
                if(msg == 0){
                    var st="<font colro='red'>用户名或密码输入错误，请核对后重新输入</font>";
                    $("#logintishixxi").html(st);
                }
                if(msg == 1){
                    var st="<font colro='red'>用户名或密码输入错误，请核对后重新输入</font>";
                    $("#logintishixxi").html(st);
                }

                if(msg==2){
                    location.href="<%=request.getContextPath()%>/dldshop/logintiao.do"
                }
            },
            error:function(){
                alert("失败")

            }

        })
    });
</script>
</body>
</html>
