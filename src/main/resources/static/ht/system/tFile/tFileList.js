//初始化 layui 数据表格
var prefix = "/sys/tFile/";
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
            , url: prefix + 'getAll'
            , toolbar: '#toolbarDemo'
            , title: '添加文件'
            , totalRow: true
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', width: 100, title: '主键', sort: true}
                , {field: 'name', width: 300, title: '名称', sort: true}
                , {field: 'path', width: 800, title: '路径', sort: true}
                ,{fixed: 'right', width: 250, align:'center', toolbar: '#barTFile'}

            ]]
            , page: true
            , id: 'testReload'
        });

        //监听行工具事件
        table.on('tool(test-table-totalRow)', function (obj) {
            var data = obj.data;
            if(obj.event == 'download'){
                download(data.name);
            }else if (obj.event === 'del') {      //删除
                delById(data.id);
            } else if (obj.event === 'edit') {     //编辑
                dictEdit(data.id);
            } else if (obj.event === 'tabAdd') {     //
                dictAddChildren(data.id);
            }
        });

        //头工具栏事件
        table.on('toolbar(test-table-totalRow)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            if (obj.event == "add") {   //添加
                tFileAdd();
            } else if (obj.event == "batchRemove") {
                var ids;    //得到  1,2,3
                if (checkStatus.data.length > 0) {
                    $(checkStatus.data).each(function (i, e) {
                        if (i == 0) {
                            ids = e.id;
                        } else {
                            ids += "," + e.id;
                        }
                    })
                    batchRemove(ids);
                }
            }

        });

        var $ = layui.$, active = {
            reload: function () {
                var demoReload = $('#demoReload');
                //执行重载
                table.reload('testReload', {
                    url: prefix + 'getAll'
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        id: demoReload.val(),
                        name: $("#name").val(),
                        address: $("#address").val()

                    }
                });
            }
        };

        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
}

function download(fileName){
    window.location.href="/fastdfs/download?fileName="+fileName;
}

//增加页面
function tFileAdd() {
    layer.open({
        type: 2
        , title: '增加字典类型'
        , content: prefix + 'tFileAdd'
        , maxmin: true
        , area: ['650px', '550px']
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            var iframeWindow = window['layui-layer-iframe' + index]
                , submitID = 'tFileAddSubmit'
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
function tFileEdit(id) {
    layer.open({
        type: 2
        , title: '修改字典信息'
        , content: prefix + 'getById?id=' + id
        , maxmin: true
        , area: ['650px', '550px']
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            var iframeWindow = window['layui-layer-iframe' + index]
                , submitID = 'tFileUpdateSubmit'
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
            console.log(res);
            layer.msg('已删除');
            layui.table.reload('testReload');
            layer.close(index); //关闭弹层
        });
    });
}