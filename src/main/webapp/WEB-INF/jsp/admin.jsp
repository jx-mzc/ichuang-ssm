<%@ taglib prefix="pager" uri="http://ichuang.com/common/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/22 0022
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>管理员信息管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/dataTables.bootstrap.css" rel="stylesheet" />
    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath }/css/metisMenu.min.css" rel="stylesheet" />
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath }/css/sb-admin-2.css" rel="stylesheet" />
</head>
<body>
<!-- 客户列表查询部分  start-->
<div style="padding: 20px">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">管理员信息管理</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="panel panel-default">
        <div class="panel-body">
            <form class="form-inline" method="get"
                  action="${pageContext.request.contextPath }/listAdmins.action">
                <div class="form-group">
                    <label for="id">管理员号</label>
                    <input type="text" class="form-control" id="id" name="id" />
                </div>
                <div class="form-group">
                    <label for="name">管理员姓名</label>
                    <input type="text" class="form-control" id="name" name="name" />
                </div>
                <button type="submit" class="btn btn-primary">查询</button>
            </form>
        </div>
    </div>
    <a href="#" class="btn btn-primary" data-toggle="modal"
       data-target="#newAdminDialog" onclick="clearAdmin()">新建</a>
    <a href="#" class="btn btn-primary" data-toggle="modal"
       data-target="#newCustomerDialog" onclick="listAll()">查看所有信息</a>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">管理员信息列表</div>
                <!-- /.panel-heading -->
                <table class="table table-bordered table-striped" id="datas">
                    <thead>
                    <tr>
                        <th>管理员号</th>
                        <th>管理员姓名</th>
                        <th>管理员性别</th>
                        <th>管理员电话号码</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="tbMain">
                        <c:forEach items="${page.rows}" var="row">
                            <tr id="admin_tr">
                                <td id="admin_id">${row.id}</td>
                                <td id="admin_name">${row.name}</td>
                                <td id="admin_sex">${row.sex}</td>
                                <td id="admin_phone">${row.phone}</td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#getPasswordDialog" onclick="getPassword(${row.id})">查看密码</a>
                                    <a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#adminEditDialog" onclick= "updateAdmin(${row.id})">修改</a>
                                    <a href="#" class="btn btn-danger btn-xs" onclick="">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="col-md-12 text-right">
                    <pager:page url="${pageContext.request.contextPath }/listAdmins.action" />
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
</div>
<!-- 列表查询部分  end-->
<!-- 查看密码模态框 -->
<div class="modal fade" id="getPasswordDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">查看密码</h4>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">密码：</label>
                    <div class="col-sm-10">
                        <input type="text" disabled="disabled" class="form-control" id="password" name="password" />
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 创建管理员模态框 -->
<div class="modal fade" id="newAdminDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新建管理员信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="new_customer_form">
                    <div class="form-group">
                        <label for="new_adminId" class="col-sm-3 control-label">
                            管理员号
                        </label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="new_adminId" placeholder="管理员号" name="adminId" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="new_adminPassword" class="col-sm-3 control-label">
                            管理员密码
                        </label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="new_adminPassword" placeholder="管理员密码" name="adminPassword" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="new_adminName" class="col-sm-3 control-label">
                            管理员名称
                        </label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="new_adminName" placeholder="管理员名称" name="adminName" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="new_adminName" class="col-sm-3 control-label">
                            管理员性别
                        </label>
                        <div class="col-sm-9">
                            <select	class="form-control" id="new_adminSex" name="adminSex">
                                <option value="">--请选择--</option>
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="new_adminPhone" class="col-sm-3 control-label">
                            管理员电话
                        </label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="new_adminPhone" placeholder="管理员电话" name="adminPhone" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="new_adminPhoto" class="col-sm-3 control-label">
                            管理员照片
                        </label>
                        <div class="col-sm-9">
                            <input type="file" class="form-control" id="new_adminPhoto"  name="adminPhoto" multiple="multiple"/>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="createAdmin()">创建管理员</button>
            </div>
        </div>
    </div>
</div>
<!-- 全局js -->
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath }/js/metisMenu.min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="${pageContext.request.contextPath }/js/sb-admin-2.js"></script>
<!-- DataTables JavaScript -->
<script src="${pageContext.request.contextPath }/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath }/js/dataTables.bootstrap.min.js"></script>
<script>
    <%--function select() {--%>
        <%--var id = $('#admin_id').val();--%>
        <%--var name = $('#admin_name').val();--%>
        <%--$.post ({--%>
            <%--type:"post",--%>
            <%--url:"${pageContext.request.contextPath}/listAdmin.action",--%>
            <%--data:JSON.stringify({id:id,name:name}),--%>
            <%--contentType:"application/json;charset=UTF-8",--%>
            <%--success:function (result) {--%>
               <%--var admins = JSON.parse(result);--%>
               <%--for (i in admins){--%>
                   <%--var row = $("#admin_tr").clone();--%>
                   <%--row.find("#admin_id").text(admins[i].id);--%>
                   <%--row.find("#admin_name").text(admins[i].name);--%>
                   <%--row.find("admin_sex").text(admins[i].sex);--%>
                   <%--row.find("admin_phone").text(admins[i].phone);--%>
                   <%--row.appendTo("#datas");--%>
               <%--}--%>
            <%--}--%>
    <%--})--%>
    <%--}--%>

    function listAll() {
        window.location.href="${pageContext.request.contextPath }/listAdmins.action";
        <%--var url = "${pageContext.request.contextPath}/listAdmin.action"--%>
        <%--$.post (--%>
            <%--url,--%>
            <%--function (result) {--%>
                <%--var admins = JSON.parse(result).rows;--%>
                <%--var table = document.getElementById('tbMain');--%>
                <%--var childs = table.childNodes;--%>
                <%--for (var k = childs.length - 1;k>=0;k--){--%>
                    <%--table.removeChild(childs[k]);--%>
                <%--}--%>
                <%--for (var i = 0; i < admins.length; i++) {--%>
                    <%--var trow = getDataRow(admins[i]);--%>
                    <%--table.appendChild(trow);--%>
                <%--}--%>

                <%--function getDataRow(h) {--%>
                    <%--var row = document.createElement('tr'); //创建行  --%>

                    <%--var idCell = document.createElement('td'); //创建第一列id  --%>
                    <%--idCell.innerHTML = h.id; //填充数据  --%>
                    <%--row.appendChild(idCell); //加入行  ，下面类似  --%>

                    <%--var nameCell = document.createElement('td');//创建第二列name  --%>
                    <%--nameCell.innerHTML = h.name;--%>
                    <%--row.appendChild(nameCell);--%>

                    <%--var jobCell = document.createElement('td');//创建第三列sex --%>
                    <%--jobCell.innerHTML = h.sex;--%>
                    <%--row.appendChild(jobCell);--%>

                    <%--var jobCell = document.createElement('td');//创建第四列phone  --%>
                    <%--jobCell.innerHTML = h.phone;--%>
                    <%--row.appendChild(jobCell);--%>
                    <%--//到这里，json中的数据已经添加到表格中，下面为每行末尾添加操作按钮--%>

                    <%--var doCell = document.createElement('td');//创建第四列，操作列--%>
                    <%--row.appendChild(doCell);--%>
                    <%--var btnUpdate = document.createElement('input');//创建一个input控件--%>
                    <%--btnUpdate.setAttribute('type','button');--%>
                    <%--btnUpdate.setAttribute('class','btn btn-primary btn-xs');--%>
                    <%--btnUpdate.setAttribute('value','修改');--%>
                    <%--//修改操作--%>
                    <%--btnUpdate.onclick=function () {  }--%>
                    <%--doCell.appendChild(btnUpdate);--%>

                    <%--var btndel = document.createElement('input');//创建一个input控件--%>
                    <%--btndel.setAttribute('type','button');--%>
                    <%--btndel.setAttribute('class','btn btn-danger btn-xs');--%>
                    <%--btndel.setAttribute('value','删除');--%>
                    <%--//删除操作--%>
                    <%--btndel.onclick=function () {  }--%>
                    <%--doCell.appendChild(btndel);--%>

                    <%--return row;--%>
                <%--}--%>
            <%--}--%>
        <%--);--%>
    }
    function getPassword(id) {
        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath }/getAccount.action",
            data:{"id":id},
            contentType:"application/json;charset=UTF-8",
            success:function(data){
                if (data!="{}") {
                     $("#password").val(JSON.parse(data).Account.password);
                }
               else {
                   $("#password").val("该用户无账号");
                }
            }
        });
    }
    //清空新建管理员窗口中的数据
    function clearAdmin() {
        $("#new_adminId").val("");
        $("#new_adminPassword").val("");
        $("#new_adminName").val("");
        $("#new_adminSex").val("");
        $("#new_adminPhone").val("");
        $("#new_adminPhoto").val("");
    }
    function updateAdmin(id) {

    }
</script>
</body>
</html>
