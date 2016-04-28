<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/26
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>设备管理系统--用户登录</title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        function checkForm() {
            var userName = document.getElementById('userName').value
            var password = document.getElementById('password').value
            if(userName == null || userName == ''){
                document.getElementById('login_err').innerHTML = '用户名不能为空'
            }
            if(password==null || password==""){
                document.getElementById("login_err").innerHTML="密码不能为空";
                return false;
            }
            return true;
        }
    </script>
</head>
<body>

<div id="login_center">
    <div id="login_area">
        <div id="login_box">
            <div id="login_form">
                <form id="submitForm"  action="${pageContext.request.contextPath}/user/login.do"  method="post" onsubmit="return checkForm()">
                    <div id="login_tip">
                        <span id="login_err" class="sty_txt2">${errorMsg }</span>
                    </div>
                    <div>
                        用户名： <input type="text"  id="userName" name="userName" class="username"  value="${user.userName }" >
                    </div>
                    <div>
                        密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password"  id="password"  name="password" class="pwd"  value="${user.password }">
                    </div>
                    <div id="btn_area">
                        <input type="submit" class="login_btn" id="login_sub"  value="登  录">
                        <input type="reset" class="login_btn" id="login_ret" value="重 置">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
