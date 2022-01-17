/**
 * bootstrao-table user表格
 */
$(function() {
	// 初始加载
	initDataGrid();
});

function initDataGrid() {
	// 创建bootstrapTable
	$("#dataGrid").bootstrapTable({
		method : "POST",
		url : '/user/getUser',
		// 只有post请求在用，极为重要，缺失无法执行queryParams，传递page参数
		contentType : "application/x-www-form-urlencoded",
		dataType : "json",

		queryParams : queryParam,
		pagination : true,// 显示分页条：页码，条数等
		striped : true,// 隔行变色
		search : true,// 搜索
		searchOnEnterKey : false,
		showRefresh : true,// 刷新
		// showToggle : true,//
		showFooter : false,
		smartDisplay : false,
		showSearch : false,
		showToggle : false,
		showColumns : false,
		showPageGo : true,
		formatNoMatches : function() {
			return '无符合条件的记录';
		},
		formatLoadingMessage : function() {
			return "请稍等，正在加载中...";
		},
		cache : false,
		pageNumber : 1,// 首页页码
		pageSize : 10,// 分页，页面数据条数
		pageList : [ 10, 25, 50, 100, 'All' ],// 可选的每页数据
		uniqueId : "id",// Indicate an unique identifier for each row
		sidePagination : "server",// 在服务器分页
		// height:tableModel.getHeight(),
		toolbar : "#toolbar",// 工具栏
		columns : [ {
			checkbox : "true",
			field : "box"
		}, {
			title : "ID",
			field : "id",
			visible : true
		}, {
			title : "姓名",
			field : "name"
		}, {
			title : "昵称",
			field : "nickname"
		}, {
			title : "年龄",
			field : "age"
		}, {
			title : "状态",
			field : "state"
		}, {
			title : "时间状态",
			field : "inputTime"
		} ]
	});
	// 刷新方法
	$('#dataGrid').click(function() {
		$table1.bootstrapTable('refresh', queryParams);
	});

}

// 查询参数设计
function queryParam(params) {
	// 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	var param = {
		// limit : this.limit, // 页面大小
		// offset : this.offset, // 页码
		pageSize : this.pageSize,
		pageNumber : this.pageNumber
	// searchValue: params.search,
	// orderByColumn: params.sort,
	// isAsc: params.order
	};
	return param;
}

// 点击取消后清空表单中已写信息
function resetAddModal() {
	document.getElementById("addUserForm").reset();
}

// 新增用户
function addUser() {
	var param = $("#addUserForm").serializeArray();
	// debugger;
	$("#conf").attr("onclick", "addUser()");
	$.ajax({
		url : "/user/addUser",
		method : "post",
		data : param,
		dataType : "json",
		success : function(data) {
			if (data.state == "success") {
				document.getElementById("al").innerText = "新增成功";
				$("#addEnd").modal('show');
				$("#addUserModal").modal('hide');
				$("#dataGrid").bootstrapTable('refresh');
			}
		},
		error : function() {
			document.getElementById("al").innerText = "新增失败";
			$("#addEnd").modal('show');
		}
	});
}

// 修改用户
function editUser() {
	// 获取选中行的数据
	var rows = $("#dataGrid").bootstrapTable('getSelections');
	if (rows.length != 1) {
		document.getElementById("tipContent").innerText = "请选择一行数据";
		$("#Tip").modal('show');
	} else {
		var row = rows[0];
		$('#editId').val(row.id);
		$('#editName').val(row.name);
		$('#editNickName').val(row.nickname);
		$('#editAge').val(row.age);
		$('#editState').val(row.state);
		$("#editModal").modal("show");
	}
}

function updateUser() {
	var param = $("#editForm").serializeArray();
	// 设为disable则无法获取
	$.ajax({
		url : "/user/updateUser",
		method : "post",
		data : param,
		dataType : "json",
		success : function(data) {
			if (data.state == "success") {
				$("#editModal").modal("hide");
				document.getElementById("tipContent").innerText = "修改成功";
				$("#Tip").modal('show');
				$("#dataGrid").bootstrapTable('refresh');
			}
		},
		error : function(data) {
			alert("wrong");
		}
	});
}

// 删除数据
function deleteUser() {
	var rows = $("#dataGrid").bootstrapTable("getSelections");
	var ids = [];
	var len = rows.length;
	if (len < 1) {
		document.getElementById("deleteTipContent").innerText = "请至少选择一行数据";
		$("#deleteEnd").modal('show');
	} else {
		for (var i = 0; i < len; i++) {
			ids.push(rows[i].id);
		}
		$.ajax({
			url : "/user/deleteUser",
			dataType : "json",
			traditional : true,// 属性在这里设置
			method : "post",
			data : {
				"ids" : ids
			},
			success : function(data) {
				document.getElementById("tipContent").innerText = "删除成功";
				$("#Tip").modal('show');
				$("#dataGrid").bootstrapTable("refresh");
			},
			error : function() {
				document.getElementById("tipContent").innerText = "删除失败";
				$("#Tip").modal('show');
			}
		});
	}
}

// 刷新表格
function refreshUser() {
	$("#dataGrid").bootstrapTable("refresh");
}

// 按钮连接示例
