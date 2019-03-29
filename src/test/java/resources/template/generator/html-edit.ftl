<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<html>
<head>
    <meta charset="utf-8">
    <title>修改字典信息-add</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" th:href="@{/static/layui/layuiadmin/layui/css/layui.css}" media="all">
</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-form-useradmin" id="layuiadmin-form-useradmin" style="padding: 20px 0 0 0;" th:object="${'"+${modelNameLowerCamel}+"' }">
    <div class="layui-form-item">
        <label class="layui-form-label">字典名称</label>
        <div class="layui-input-inline">
            <input type="hidden" th:name="id" th:value="*{id}" />
            <input type="text" name="dictName" lay-verify="required" th:value="*{dictName}" placeholder="请输入字典名称" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">字典类型</label>
        <div class="layui-input-inline">
            <input type="text" name="dictType" th:value="*{dictType}"  placeholder="请输入字典类型" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-inline">
            <input type="text" name="status"  th:value="*{status}" placeholder="请输入状态" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea th:name="remarks" th:text="*{remarks}" placeholder="请输入备注" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="${modelNameLowerCamel}UpdateSubmit" id="${modelNameLowerCamel}UpdateSubmit" value="确认">
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</div>

<script th:src="@{/static/layui/layuiadmin/layui/layui.js}"></script>
<script>
    layui.use([ 'form','laydate'], function(){
        var $ = layui.$
                ,laydate = layui.laydate;
        form = layui.form;
        //常规用法
        laydate.render({
            elem: '#test1'
        });
    })
</script>
</body>
</html>