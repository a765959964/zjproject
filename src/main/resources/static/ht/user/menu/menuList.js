/**
 * Created by 张帆 on 2018/11/29.
 */
var renderTable;
layui.config({
    base: '/static/layui/layuiadmin/modules/'   //静态文件所在地址
}).extend({
    treetable: 'treetable-lay/treetable'
}).use(['table', 'treetable'], function () {
    var $ = layui.jquery;
    var table = layui.table;
    var treetable = layui.treetable;

    renderTable  = function () {
        // 渲染表格
        layer.load(2);
        treetable.render({
            treeColIndex: 1,
            treeSpid: 0,
            treeIdName: 'id',
            treePidName: 'pid',
            elem: '#auth-table',
            url: '/sysmenu/getTreeTable',
            page: false,
            cols: [[
                {type: 'numbers'},
                {field: 'name', minWidth: 50, title: '权限名称'},
                // {field: 'perms', title: '权限标识'},
                {field: 'url', title: '菜单url'},
                {field: 'sort', width: 80, align: 'center', title: '排序号'},
                {
                    field: 'type', width: 80, align: 'center', templet: function (d) {
                    if (d.type == 2) {
                        return '<span class="layui-badge layui-bg-gray">目录</span>';
                    }
                    if (d.pid == 0) {
                        return '<span class="layui-badge layui-bg-blue">菜单</span>';
                    }
                }, title: '类型'
                },
                {templet: '#auth-state', width: 200, align: 'center', title: '操作'}
            ]],
            done: function () {
                layer.closeAll('loading');
            }
        });
    };
    renderTable();

    $('#btn-add').click(function (){
        add();
    })

    $('#btn-expand').click(function () {
        treetable.expandAll('#auth-table');
    });

    $('#btn-fold').click(function () {
        treetable.foldAll('#auth-table');
    });

    //监听行工具事件
    table.on('tool(auth-table)', function(obj) {
        var data = obj.data;
        if(obj.event === 'add'){
            parentAdd(data.id);
        } else if(obj.event === 'del'){      //删除
            delById(data.id);
        } else if(obj.event === 'edit'){     //编辑
            menuEdit(data.id);
        }
    });


});


function add(){
    layer.open({
        type: 2
        ,title: '增加资源'
        ,content: 'add.html'
        ,maxmin: true
        ,area: ['650px', '550px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'addSubmit'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);
            //监听提交
            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                var field = data.field; //获取提交的字段
                $.ajax({
                    url : '/sysmenu/insert',
                    type : 'post',
                    dataType : 'json',
                    data: field,
                    success : function (res){
                        layer.msg("增加成功");
                        renderTable();
                        layer.close(index); //关闭弹层
                    }
                })
            });
            submit.trigger('click');
        }
    });
}


function parentAdd(id){
    layer.open({
        type: 2
        ,title: '增加资源'
        ,content: '/sysmenu/getParentId?id='+id
        ,maxmin: true
        ,area: ['650px', '550px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'menuAddSubmit'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);
            //监听提交
            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                var field = data.field; //获取提交的字段
                $.ajax({
                    url : '/sysmenu/insert',
                    type : 'post',
                    dataType : 'json',
                    data: field,
                    success : function (res){
                        layer.msg("增加成功");
                        renderTable();
                        layer.close(index); //关闭弹层
                    }
                })
            });
            submit.trigger('click');
        }
    });
}

function delById(id){
    layer.confirm("你确定删除数据吗？如果存在下级节点则一并删除，此操作不能撤销！", {icon: 3, title:'提示'},
        function(index){//确定回调
            $.post("/sysmenu/deleteById",{id:id},function (res){
                layer.msg('已删除');
            });
            renderTable();
            layer.close(index);
        },function (index) {//取消回调
            layer.close(index);
        }
    );
}

function menuEdit(id){
    layer.open({
        type: 2
        ,title: '增加资源'
        ,content: '/sysmenu/getByIdEdit?id='+id
        ,maxmin: true
        ,area: ['650px', '550px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'menuUpdateSubmit'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);
            //监听提交
            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                var field = data.field; //获取提交的字段
                $.ajax({
                    url : '/sysmenu/update',
                    type : 'post',
                    dataType : 'json',
                    data: field,
                    success : function (res){
                        layer.msg("增加成功");
                        renderTable();
                        layer.close(index); //关闭弹层
                    }
                })
            });
            submit.trigger('click');
        }
    });
}