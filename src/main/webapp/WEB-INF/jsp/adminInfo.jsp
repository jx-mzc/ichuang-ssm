<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/16 0016
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息</title>
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
<center>
<div style="margin-top: 30px">
    <div style="margin: 0 auto;text-align:center;margin-bottom: 20px;">
        <img id="update_photo" src="https://www.iwchuang.cn/images/admin/2019001.jpg" align="center" style="height: 80px;width: 60px;margin: 0 auto;">
    </div>
    <form class="form-horizontal" id="update_admin_form">
        <div class="form-group">
            <label for="update_adminId" class="col-sm-3 control-label">
                管理员号
            </label>
            <div class="col-sm-6">
                <input type="text"  class="form-control" readonly=readonly id="update_adminId" placeholder="管理员号" name="id" required=""/>
            </div>
        </div>
        <div class="form-group">
            <label for="update_adminName" class="col-sm-3 control-label">
                管理员名称
            </label>
            <div class="col-sm-6">
                <input type="text" readonly=readonly class="form-control" id="update_adminName" placeholder="管理员名称" name="name" required=""/>
            </div>
        </div>
        <div class="form-group">
            <label for="update_adminName" class="col-sm-3 control-label">
                管理员性别
            </label>
            <div class="col-sm-6">
                <input type="text"	class="form-control"  readonly=readonly id="update_adminSex" name="sex" required=""/>
            </div>
        </div>
        <div class="form-group">
            <label for="update_adminPhone" class="col-sm-3 control-label">
                管理员电话
            </label>
            <div class="col-sm-6">
                <input type="text" readonly=readonly class="form-control" id="update_adminPhone" placeholder="管理员电话" name="phone" required=""/>
            </div>
        </div>
        <div style="align-content: center">
            <a href="#" class="btn btn-primary" data-toggle="modal"
               data-target="#updateDialog" onclick="edit(${USER_SESSION.id})">修改信息</a>
        </div>
    </form>
</div>
</center>
<!-- 修改个人信息模态框 -->
<div class="modal fade" id="updateDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabe1">修改个人信息</h4>
            </div>
            <div class="modal-body">
                <div style="margin: 0 auto;text-align:center;margin-bottom: 20px;">
                    <img id="updatePhoto" src="https://www.iwchuang.cn/images/admin/2019001.jpg" align="center" style="height: 80px;width: 60px;margin: 0 auto;">
                </div>
                <form class="form-horizontal" id="update_form">
                    <div class="form-group">
                        <label for="updateAdminId" class="col-sm-3 control-label">
                            管理员号
                        </label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" readonly=readonly id="updateAdminId" placeholder="管理员号" name="id" required=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="updateAdminName" class="col-sm-3 control-label">
                            管理员名称
                        </label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="updateAdminName" placeholder="管理员名称" name="name" required=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="updateAdminSex" class="col-sm-3 control-label">
                            管理员性别
                        </label>
                        <div class="col-sm-9">
                            <select	class="form-control" id="updateAdminSex" name="sex" required="">
                                <option value="">--请选择--</option>
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="updateAdminPhone" class="col-sm-3 control-label">
                            管理员电话
                        </label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="updateAdminPhone" placeholder="管理员电话" name="phone" required=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="updateAdminPhoto" class="col-sm-3 control-label">
                            管理员照片
                        </label>
                        <div class="col-sm-9">
                            <input type="file" class="form-control" id="updateAdminPhoto"  name="adminPhoto" multiple="multiple"/>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="update()">修改信息</button>
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
    window.onload = function () {
        var id = ${USER_SESSION.id};
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

    function edit(id) {
        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath }/getAdmin.action",
            data:{"id":id},
            success:function (data) {
                $("#updateAdminId").val(JSON.parse(data).Admin.id);
                $("#updateAdminName").val(JSON.parse(data).Admin.name);
                $("#updateAdminSex").val(JSON.parse(data).Admin.sex);
                $("#updateAdminPhone").val(JSON.parse(data).Admin.phone);
                $("#updatePhoto").attr("src",JSON.parse(data).Admin.photo);
            }
        });
    }

    function update() {
        var id = $('#updateAdminId').val();
        var name = $('#updateAdminName').val();
        var sex = $('#updateAdminSex').val();
        var phone = $('#updateAdminPhone').val();
        var file = $('#updateAdminPhoto')[0].files[0];
        if (name == ""){
            alert("名称请不要为空！");
        }else if (sex == ""){
            alert("性别请不要为空！");
        }else if (phone == ""){
            alert("电话请不要为空！");
        } else {
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath }/updateAdmin.action",
                data:JSON.stringify({id:id,name:name,sex:sex,phone:phone}),
                contentType:"application/json;charset=UTF-8",
                success:function (data) {
                    if (data != "FAIL" ){
                        if ($('#updateAdminPhoto').val().length != 0) {
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
                        alert("个人信息修改成功！");
                        window.location.reload();
                    } else{
                        alert("个人信息修改失败！");
                        window.location.reload();
                    }
                }
            });
        }
    }
</script>
</body>
</html>
