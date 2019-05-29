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
    <a href="#" class="btn btn-primary" data-toggle="modal" onclick="listAll()">查看所有信息</a>
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
                                    <a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#getPasswordDialog" onclick="editPassword(${row.id})">修改密码</a>
                                    <a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#updateAdminDialog" onclick= "editAdmin(${row.id})">修改信息</a>
                                    <a href="#" class="btn btn-danger btn-xs" onclick="deleteAdmin(${row.id})">删除</a>
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
<!-- 修改密码模态框 -->
<div class="modal fade" id="getPasswordDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">修改密码</h4>
                <input type="text" id="adminId" hidden="hidden" value=""/>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="admin_password_form">
                    <div class="form-group">
                        <label for="password" class="col-sm-3 control-label">原密码</label>
                        <div class="col-sm-9">
                            <input type="text" disabled="disabled" class="form-control" id="password" name="password" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="new_password" class="col-sm-3 control-label">新密码</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" id="new_password" name="password" required=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="check_password" class="col-sm-3 control-label">确认新密码</label>
                        <div class="col-sm-9">
                            <input type="password"  class="form-control" id="check_password" name="password" required=""/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" onclick="updatePassword()">修改密码</button>
                    </div>
                </form>
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
                <form class="form-horizontal" id="new_admin_form">
                    <div class="form-group">
                        <label for="new_adminId" class="col-sm-3 control-label">
                            管理员号
                        </label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="new_adminId" placeholder="管理员号" name="id" required=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="new_adminPassword" class="col-sm-3 control-label">
                            管理员密码
                        </label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" id="new_adminPassword" placeholder="管理员密码" name="password" required=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="new_adminCheckPassword" class="col-sm-3 control-label">
                            管理员确认密码
                        </label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" id="new_adminCheckPassword" placeholder="管理员密码" name="adminCheckPassword" required=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="new_adminName" class="col-sm-3 control-label">
                            管理员名称
                        </label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="new_adminName" placeholder="管理员名称" name="name" required=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="new_adminName" class="col-sm-3 control-label">
                            管理员性别
                        </label>
                        <div class="col-sm-9">
                            <select	class="form-control" id="new_adminSex" name="sex" required="">
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
                            <input type="text" class="form-control" id="new_adminPhone" placeholder="管理员电话" name="phone" required=""/>
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
<!-- 修改管理员模态框 -->
<div class="modal fade" id="updateAdminDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabe2">修改管理员信息</h4>
            </div>
            <div class="modal-body">
                <div style="margin: 0 auto;text-align:center;margin-bottom: 20px;">
                    <img id="update_photo" src="https://www.iwchuang.cn/images/admin/2019001.jpg" align="center" style="height: 80px;width: 60px;margin: 0 auto;">
                </div>
                <form class="form-horizontal" id="update_admin_form">
                    <div class="form-group">
                        <label for="update_adminId" class="col-sm-3 control-label">
                            管理员号
                        </label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" readonly=readonly id="update_adminId" placeholder="管理员号" name="id" required=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="update_adminName" class="col-sm-3 control-label">
                            管理员名称
                        </label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="update_adminName" placeholder="管理员名称" name="name" required=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="update_adminName" class="col-sm-3 control-label">
                            管理员性别
                        </label>
                        <div class="col-sm-9">
                            <select	class="form-control" id="update_adminSex" name="sex" required="">
                                <option value="">--请选择--</option>
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="update_adminPhone" class="col-sm-3 control-label">
                            管理员电话
                        </label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="update_adminPhone" placeholder="管理员电话" name="phone" required=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="update_adminPhoto" class="col-sm-3 control-label">
                            管理员照片
                        </label>
                        <div class="col-sm-9">
                            <input type="file" class="form-control" id="update_adminPhoto"  name="adminPhoto" multiple="multiple"/>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="updateAdmin()">修改管理员</button>
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
    function listAll() {
        window.location.href="${pageContext.request.contextPath }/listAdmins.action";
    }
    function editPassword(id) {
        $("#adminId").val(id);
        $("#new_password").val("");
        $("#check_password").val("");
        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath }/getAccount.action",
            data:{"id":id},
            contentType:"application/json;charset=UTF-8",
            success:function(data){
                if (data!="{}") {
                    $("#adminId").val(JSON.parse(data).Account.account);
                     $("#password").val(JSON.parse(data).Account.password);
                }
               else {
                   $("#password").val("该用户无账号");
                }
            }
        });
    }
    function updatePassword() {
        var account = $("#adminId").val();
        var newPassword = $("#new_password").val();
        var checkPassword = $("#check_password").val();
        if (newPassword ==""){
            alert("密码不要为空！");
        } else if (checkPassword == ""){
            alert("确认密码不要为空！");
        } else if (newPassword != checkPassword){
            alert("两次输入的密码不同！");
        } else {
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath }/updateAccount.action",
                data:JSON.stringify({account:account,password:newPassword}),
                contentType:"application/json;charset=UTF-8",
                success:function(data){
                    if (data == "SUCCESS") {
                        alert("密码修改成功！");
                        window.location.reload();
                    }
                    else {
                        alert("密码修改失败！");
                        window.location.reload();
                    }
                }
            });
        }
    }
    //清空新建管理员窗口中的数据
    function clearAdmin() {
        $("#new_adminId").val("");
        $("#new_adminPassword").val("");
        $("#new_adminName").val("");
        $("#new_adminSex").val("");
        $("#new_adminPhone").val("");
        $("#new_adminPhoto").val("");
        $('#new_adminCheckPassword').val("")
    }

    function createAdmin() {
        var id = $('#new_adminId').val();
        var password = $('#new_adminPassword').val();
        var checkPassword = $('#new_adminCheckPassword').val();
        var name = $('#new_adminName').val();
        var sex = $('#new_adminSex').val();
        var phone = $('#new_adminPhone').val();
        var file = $('#new_adminPhoto')[0].files[0];
        if (id == ""){
            alert("管理员号请不要为空！");
        } else if(password == ""){
            alert("管理员密码请不要为空！");
        }else if (checkPassword == ""){
            alert("管理员确认密码请不要为空！");
        } else if (name == ""){
            alert("管理员请名称不要为空！");
        }else if (sex == ""){
            alert("管理员性别请不要为空！");
        }else if (phone == ""){
            alert("管理员电话请不要为空！");
        }else if (password != checkPassword) {
            alert("两次输入的密码不同");
        }else {
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath }/addAdmin.action",
                    data:JSON.stringify({id:id,password:password,name:name,sex:sex,phone:phone}),
                    contentType:"application/json;charset=UTF-8",
                    success:function (data) {
                        if (data == "SUCCESS" && $('#new_adminPhoto').val().length != 0){
                            var formDate = new FormData();
                            formDate.append('file',file);
                            formDate.append('id',id);
                            $.ajax({
                                url:"${pageContext.request.contextPath }/uploadAdminPhoto.action",
                                type:"post",
                                data:formDate,
                                processData: false,
                                contentType: false,
                                success:function(res){
                                    console.log(res);
                                }
                            });
                            alert("管理员创建成功！");
                            window.location.reload();
                        }
                        else if (data == "FAIL") {
                            alert("管理员创建失败！");
                            window.location.reload();
                        }else {
                            alert(data);
                            $('#new_adminId').focus;
                        }
                    }
                });
        }
    }
    function editAdmin(id) {
        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath }/getAdmin.action",
            data:{"id":id},
            success:function (data) {
                $("#update_adminId").val(JSON.parse(data).Admin.id);
                $("#update_adminName").val(JSON.parse(data).Admin.name);
                $("#update_adminSex").val(JSON.parse(data).Admin.sex);
                $("#update_adminPhone").val(JSON.parse(data).Admin.phone);
                $("#update_photo").attr("src",JSON.parse(data).Admin.photo);
            }
        });
    }
    function updateAdmin() {
        var id = $('#update_adminId').val();
        var name = $('#update_adminName').val();
        var sex = $('#update_adminSex').val();
        var phone = $('#update_adminPhone').val();
        var file = $('#update_adminPhoto')[0].files[0];
        if (name == ""){
            alert("管理员名称请不要为空！");
        }else if (sex == ""){
            alert("管理员性别请不要为空！");
        }else if (phone == ""){
            alert("管理员电话请不要为空！");
        } else {
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath }/updateAdmin.action",
                data:JSON.stringify({id:id,name:name,sex:sex,phone:phone}),
                contentType:"application/json;charset=UTF-8",
                success:function (data) {
                    if (data != "FAIL" ){
                        if ($('#update_adminPhoto').val().length != 0) {
                            var formDate = new FormData();
                            formDate.append('file',file);
                            formDate.append('id',id);
                            $.ajax({
                                url:"${pageContext.request.contextPath }/uploadAdminPhoto.action",
                                type:"post",
                                data:formDate,
                                processData: false,
                                contentType: false,
                                success:function(res){
                                    console.log(res);
                                }
                            });
                        }
                        alert("管理员修改成功！");
                        window.location.reload();
                    } else{
                        alert("管理员修改失败！");
                        window.location.reload();
                    }
                }
            });
        }
    }
    function deleteAdmin(id) {
        if (confirm('确定要删除该管理员吗？')){
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath }/deleteAdmin.action",
                data:{"id":id},
                success:function (data) {
                    if (data == "SUCCESS"){
                        alert("管理员删除成功！");
                        window.location.reload();
                    }else {
                        alert("管理员删除失败！");
                        window.location.reload();
                    }
                }
            });
        }
    }
</script>
</body>
</html>
