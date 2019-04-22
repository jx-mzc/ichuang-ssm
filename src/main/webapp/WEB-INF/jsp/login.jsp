<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/15 0015
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 登录</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

   <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>

            <h2 class="logo-name">i 创</h2>

        </div>
        <h3>欢迎使用I创后台管理</h3>

        <font color="red">
            <%-- 提示信息--%>
            <span id="message">${msg}</span>
        </font>

            <div class="form-group">
                <input type="text" class="form-control" placeholder="用户名" required="" id="account">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="密码" required="" id="password">
            </div>
            <input type="button" class="btn btn-primary block full-width m-b" value="登 录" onclick="login()" >


            <p class="text-muted text-center"> <a href="login.html#"><small>忘记密码了？</small></a>
            </p>

        </form>
    </div>
</div>

<!-- 全局js -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
<script type="text/javascript">
   function login() {
        var account = $('#account').val();
        var password = $('#password').val();

       if(account=="" || password==""){
           $("#message").text("账号或密码不能为空！");
           return false;
       }
       $.ajax({
           type:"post",
           url:"${pageContext.request.contextPath}/login.action",
           data:JSON.stringify({account:account,password:password}),
           contentType:"application/json;charset=UTF-8",
           success:function (result) {
               if (result!="{}") {
                   window.location.href="main.action"
                   return true;
               }
               $("#message").text("账户或密码错误，请重新输入！");
           }
       });
    }
</script>
</body>

</html>