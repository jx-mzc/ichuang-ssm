<%@ taglib prefix="ichuang" uri="http://ichuang.com/" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/22 0022
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员信息管理</title>
    <meta charset="utf-8">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">
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
                <div class="form-group">
                    <label for="adminId">管理员号</label>
                    <input type="text" class="form-control" id="adminId" name="adminId" />
                </div>
                <div class="form-group">
                    <label for="adminName">管理员姓名</label>
                    <input type="text" class="form-control" id="adminName" name="adminName" />
                </div>
                <input type="button" class="btn btn-primary" onclick="select()" value="查询">
        </div>
    </div>
    <input type="button" class="btn btn-primary"  onclick="" value="新建"/>
    <input type="button" class="btn btn-primary"  onclick="listAll()" value="查看所有信息"/>
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
                        <%--<tr id="admin_tr">--%>
                            <%--<td id="admin_id"></td>--%>
                            <%--<td id="admin_name"></td>--%>
                            <%--<td id="admin_sex"></td>--%>
                            <%--<td id="admin_phone"></td>--%>
                            <%--<td>--%>
                                <%--<a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#customerEditDialog" onclick= "">修改</a>--%>
                                <%--<a href="#" class="btn btn-danger btn-xs" onclick="">删除</a>--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                    </tbody>
                </table>
                <div class="col-md-12 text-right">
                    <ichuang:page url="${pageContext.request.contextPath }/listAdmin.action" />
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
</div>
<!-- 客户列表查询部分  end-->

<!-- 全局js -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
<script>
    function select() {
        var id = $('#admin_id').val();
        var name = $('#admin_name').val();
        $.ajax ({
            type:"post",
            url:"${pageContext.request.contextPath}/listAdmin.action",
            data:JSON.stringify({id:id,name:name}),
            contentType:"application/json;charset=UTF-8",
            success:function (result) {
               var admins = JSON.parse(result);
               for (i in admins){
                   var row = $("#admin_tr").clone();
                   row.find("#admin_id").text(admins[i].id);
                   row.find("#admin_name").text(admins[i].name);
                   row.find("admin_sex").text(admins[i].sex);
                   row.find("admin_phone").text(admins[i].phone);
                   row.appendTo("#datas");
               }
            }
    })
    }

    function listAll() {
        $.ajax ({
            type:"post",
            url:"${pageContext.request.contextPath}/listAdmin.action",
            data:JSON.stringify({page:1,rows:10}),
            contentType:"application/json;charset=UTF-8",
            success:function (result) {
                var admins = JSON.parse(result).rows;
                var table = document.getElementById('tbMain');
                var childs = table.childNodes;
                for (var k = childs.length - 1;k>=0;k--){
                    table.removeChild(childs[k]);
                }
                for (var i = 0; i < admins.length; i++) {
                    var trow = getDataRow(admins[i]);
                    table.appendChild(trow);
                }

                function getDataRow(h) {
                    var row = document.createElement('tr'); //创建行  

                    var idCell = document.createElement('td'); //创建第一列id  
                    idCell.innerHTML = h.id; //填充数据  
                    row.appendChild(idCell); //加入行  ，下面类似  

                    var nameCell = document.createElement('td');//创建第二列name  
                    nameCell.innerHTML = h.name;
                    row.appendChild(nameCell);

                    var jobCell = document.createElement('td');//创建第三列sex 
                    jobCell.innerHTML = h.sex;
                    row.appendChild(jobCell);

                    var jobCell = document.createElement('td');//创建第四列phone  
                    jobCell.innerHTML = h.phone;
                    row.appendChild(jobCell);
                    //到这里，json中的数据已经添加到表格中，下面为每行末尾添加操作按钮

                    var doCell = document.createElement('td');//创建第四列，操作列
                    row.appendChild(doCell);
                    var btnUpdate = document.createElement('input');//创建一个input控件
                    btnUpdate.setAttribute('type','button');
                    btnUpdate.setAttribute('class','btn btn-primary btn-xs');
                    btnUpdate.setAttribute('value','修改');
                    //修改操作
                    btnUpdate.onclick=function () {  }
                    doCell.appendChild(btnUpdate);

                    var btndel = document.createElement('input');//创建一个input控件
                    btndel.setAttribute('type','button');
                    btndel.setAttribute('class','btn btn-danger btn-xs');
                    btndel.setAttribute('value','删除');
                    //删除操作
                    btndel.onclick=function () {  }
                    doCell.appendChild(btndel);

                    return row;
                }
            }
        })
    }
</script>
</body>
</html>
