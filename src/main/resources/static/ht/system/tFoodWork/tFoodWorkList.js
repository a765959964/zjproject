//初始化 layui 数据表格
var prefix = "/sys/tFoodWork/";
$(function () {
    init();
})

function init() {
    layui.use(['table', 'element'], function () {
        var $ = layui.$
            , table = layui.table
            , element = layui.element
            , form = layui.form;
        table.render({
            elem: '#test-table-totalRow'
            , url: prefix + 'getFoodWorkList'
            , toolbar: '#toolbarDemo'
            , title: '添加字典'
            , totalRow: true
            , cols: [[

                // {type: 'checkbox', fixed: 'left'}
                // , {field: 'id', width: 100, title: '字典主键', sort: true,type: hidden}
                 {field: 'workDate', width: 180, title: '日期'}
                , {field: 'week', width: 200, title: '星期'}
                , {field: 'foodname', width: 500, title: '菜品'}
                , {fixed: 'right', width: 180, align: 'center', toolbar: '#barTFoodWork'}
            ]]
            , page: true
            , id: 'testReload'
        });

        //监听行工具事件
        table.on('tool(test-table-totalRow)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {      //删除
                delById(data.id);
            } else if (obj.event === 'edit') {     //编辑
                tFoodWorkEdit(data.id);
            }
        });

        //头工具栏事件
        table.on('toolbar(test-table-totalRow)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            if (obj.event == "add") {   //添加
                tFoodWorkAdd();
            } else if (obj.event == "save") {

            }

        });



        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
}


function init() {
    layui.use(['table', 'element'], function () {
        var $ = layui.$
            , table = layui.table
            , element = layui.element
            , form = layui.form;
        table.render({
            elem: '#test-table-totalRow'
            , url: prefix + 'getFoodWorkList'
            , toolbar: '#toolbarDemo'
            , title: '添加字典'
            , totalRow: true
            , cols: [[

                // {type: 'checkbox', fixed: 'left'}
                // , {field: 'id', width: 100, title: '字典主键', sort: true,type: hidden}
                {field: 'workDate', width: 180, title: '日期'}
                , {field: 'week', width: 200, title: '星期'}
                , {field: 'foodname', width: 500, title: '菜品'}
                , {fixed: 'right', width: 180, align: 'center', toolbar: '#barTFoodWork'}
            ]]
            , page: true
            , id: 'testReload'
        });

        //监听行工具事件
        table.on('tool(test-table-totalRow)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {      //删除
                delById(data.id);
            } else if (obj.event === 'edit') {     //编辑
                tFoodWorkEdit(data.id);
            }
        });

        //头工具栏事件
        table.on('toolbar(test-table-totalRow)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            if (obj.event == "add") {   //添加
                tFoodWorkAdd();
            } else if (obj.event == "save") {

            }

        });



        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
}

//增加页面
function tFoodWorkAdd() {
    layer.open({
        type: 2
        , title: '添加'
        , content: prefix + 'tFoodWorkAdd'
        , maxmin: true
        , area: ['650px', '450px']
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            var iframeWindow = window['layui-layer-iframe' + index]
                , submitID = 'tFoodWorkAddSubmit'
                , submit = layero.find('iframe').contents().find('#' + submitID);
            //监听提交
            iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                var field = data.field; //获取提交的字段
                console.log(field);
                $.ajax({
                    url: prefix + 'insert',
                    type: 'post',
                    dataType: 'json',
                    data: field,
                    success: function (res) {
                        layer.msg("增加成功");
                        layui.table.reload('testReload');
                        layer.close(index); //关闭弹层
                    }
                })
            });
            submit.trigger('click');
        }
    });
}


function batchRemove(ids) {
    layer.confirm('确定删除吗？', function (index) {
        $.post(prefix + "batchRemove", {ids: ids}, function (res) {
            layer.msg('已删除');
            layui.table.reload('testReload');
            layer.close(index); //关闭弹层
        });
    });
}




//编辑页面
function tFoodWorkEdit(id) {
    layer.open({
        type: 2
        , title: '修改'
        , content: prefix + 'getById?id=' + id
        , maxmin: true
        , area: ['650px', '450px']
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            var iframeWindow = window['layui-layer-iframe' + index]
                , submitID = 'tFoodWorkUpdateSubmit'
                , submit = layero.find('iframe').contents().find('#' + submitID);
            //监听提交
            iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                var field = data.field; //获取提交的字段
                $.ajax({
                    url: prefix + 'update',
                    type: 'post',
                    dataType: 'json',
                    data: field,
                    success: function (res) {
                        layer.msg("修改成功");
                        layui.table.reload('testReload');
                        layer.close(index); //关闭弹层
                    }
                })
            });
            submit.trigger('click');
        }
    });
}

//删除id
function delById(id) {
    layer.confirm('确定删除吗？', function (index) {
        $.post(prefix + "deleteById", {id: id}, function (res) {
            layer.msg('已删除');
            layui.table.reload('testReload');
            layer.close(index); //关闭弹层
        });
    });
}