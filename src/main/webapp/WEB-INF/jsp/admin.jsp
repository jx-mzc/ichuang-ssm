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
            <form class="form-inline" method="get"
                  action="${pageContext.request.contextPath }/customer/list.action">
                <div class="form-group">
                    <label for="customerName">客户名称</label>
                    <input type="text" class="form-control" id="customerName"
                           value="${custName }" name="custName" />
                </div>
                <div class="form-group">
                    <label for="customerFrom">客户来源</label>
                    <select	class="form-control" id="customerFrom" name="custSource">
                        <option value="">--请选择--</option>
                        <c:forEach items="${fromType}" var="item">
                            <option value="${item.dict_id}"
                                    <c:if test="${item.dict_id == custSource}">selected</c:if>>
                                    ${item.dict_item_name }
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="custIndustry">所属行业</label>
                    <select	class="form-control" id="custIndustry"  name="custIndustry">
                        <option value="">--请选择--</option>
                        <c:forEach items="${industryType}" var="item">
                            <option value="${item.dict_id}"
                                    <c:if test="${item.dict_id == custIndustry}"> selected</c:if>>
                                    ${item.dict_item_name }
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="custLevel">客户级别</label>
                    <select	class="form-control" id="custLevel" name="custLevel">
                        <option value="">--请选择--</option>
                        <c:forEach items="${levelType}" var="item">
                            <option value="${item.dict_id}"
                                    <c:if test="${item.dict_id == custLevel}"> selected</c:if>>
                                    ${item.dict_item_name }
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">查询</button>
            </form>
        </div>
    </div>
    <a href="#" class="btn btn-primary" data-toggle="modal"
       data-target="#newCustomerDialog" onclick="clearCustomer()">新建</a>
    <a href="/listAdmin.action" class="btn btn-primary" data-toggle="modal"
       data-target="#newCustomerDialog" onclick="">查看所有信息</a>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">管理员信息列表</div>
                <!-- /.panel-heading -->
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>管理员号</th>
                        <th>管理员姓名</th>
                        <th>管理员性别</th>
                        <th>管理员电话号码</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.rows}" var="row">
                        <tr>
                            <td>${row.cust_id}</td>
                            <td>${row.cust_name}</td>
                            <td>${row.cust_source}</td>
                            <td>${row.cust_industry}</td>
                            <td>${row.cust_level}</td>
                            <td>${row.cust_phone}</td>
                            <td>${row.cust_mobile}</td>
                            <td>
                                <a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#customerEditDialog" onclick= "editCustomer(${row.cust_id})">修改</a>
                                <a href="#" class="btn btn-danger btn-xs" onclick="deleteCustomer(${row.cust_id})">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
</div>
<!-- 客户列表查询部分  end-->
</body>
</html>
