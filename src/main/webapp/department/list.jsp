<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
    function departmentDelete(id){
        if(confirm("确定要删除这条记录吗?")){
            $.ajax({
                url:"${pageContext.request.contextPath}/department/delete.do",
                type:'get',
                data:{'id':id},
                dataType:'json',
                success:function (data) {
                    if(data.errorInfo){
                        alert(data.errorInfo)
                    }else{
                        window.location.href="${pageContext.request.contextPath}/department/list.do"
                    }
                },
                error:function (data) {
                    alert(JSON.stringify(data))
                }

            })
        }
    }
</script>

<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/department/list.do" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="deptName"  value="${s_department.deptName }" placeholder="请输入要查询的部门...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
      <button type="button" class="btn btn-primary" style="float:right;"
              onclick="javascript:window.location.href='${pageContext.request.contextPath}/department/preSave.do'">添加</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  <tr>
	  	<th>编号</th>
	  	<th>部门名称</th>
	  	<th>部门备注</th>
	  	<th>操作</th>
	  </tr>
	  <c:forEach var="department" items="${departmentList }" varStatus="status">
	  	<tr>
	  		<td>${status.index+1 }</td>
	  		<td>${department.deptName }</td>
	  		<td>${department.remark }</td>
	  		<td>
                <button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/department/preSave.do?id=${department.id}&&deptName=${department.deptName}'">修改</button>
                <button type="button" class="btn btn-danger btn-xs" onclick="departmentDelete(${department.id})">删除</button>
            </td>
	  	</tr>
	  </c:forEach>
	</table>
	<div style="margin-right: 0px">
        <nav>
            <ul class="pagination">
                ${pageCode}
            </ul>
        </nav>
	</div>
</div>



