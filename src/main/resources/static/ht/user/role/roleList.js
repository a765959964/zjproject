/**
 * Created by 张帆 on 2018/10/30.
 */
$(function (){
    init();
})

//初始化 layui 数据表格
function init(){
    layui.use( 'table', function(){
        var $ = layui.$
            ,table = layui.table
            ,form = layui.form;
        table.render({
            elem: '#sysRoleTable'
            ,url: '/sysrole/getAll'
            ,toolbar: '#toolbarsysRole'
            ,title: '角色管理'
            ,totalRow: true
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', width:50, title: 'ID', sort: true}
                ,{field:'roleName', width:200, title: '角色名'}
                ,{field:'roleDesc', width:200, title: '备注'}
                ,{fixed: 'right', width: 200, align:'center', toolbar: '#barsysRole'}
            ]]
            ,page: true
            ,id : 'sysRoleReload'
        });

        //监听行工具事件
        table.on('tool(sysRoleTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){      //删除
                delById(data.id);
            } else if(obj.event === 'edit'){     //编辑
                sysRoleEdit(data.id);
            } else {     //查看
                alert("查看");
            }
        });

        //头工具栏事件
        table.on('toolbar(sysRoleTable)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            if(obj.event=="add"){   //添加
                sysRoleAdd();
            }
        });

        var $ = layui.$, active = {
            reload: function(){
                var demoReload = $('#demoReload');
                //执行重载
                table.reload('sysRoleReload', {
                    url : '/sysrole/getAll'
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        id: demoReload.val(),
                        name : $("#name").val(),
                        address :  $("#address").val()

                    }
                });
            }
        };

        $('.sysRoleTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
}

//增加页面
function sysRoleAdd(){
    layer.open({
        type: 2
        ,title: '增加用户信息'
        ,content: '/sysrole/roleAdd'
        ,maxmin: true
        ,area: ['650px', '550px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'sysRoleSubmit'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);
            //监听提交
            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                var field = data.field; //获取提交的字段
                $.ajax({
                    url : '/sysrole/insert',
                    type : 'post',
                    dataTypep : 'json',
                    data: field,
                    success : function (res){
                        layer.msg("增加成功");
                        layui.table.reload('sysRoleReload');
                        layer.close(index); //关闭弹层
                    }
                })
            });
            submit.trigger('click');
        }
    });
}

//编辑页面
function sysRoleEdit(id){
    layer.open({
        type: 2
        ,title: '修改用户信息'
        ,content: '/sysrole/getById?id='+id
        ,maxmin: true
        ,area: ['650px', '550px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'sysRoleUpdate'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);
            //监听提交
            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                var field = data.field; //获取提交的字段
                $.ajax({
                    url : '/sysRole/update',
                    type : 'post',
                    dataTypep : 'json',
                    data: field,
                    success : function (res){
                        layer.msg("修改成功");
                        layui.table.reload('sysRoleReload');
                        layer.close(index); //关闭弹层
                    }
                })
            });
            submit.trigger('click');
        }
    });
}

//删除id
function delById(id){
    layer.confirm('确定删除吗？', function(index) {
        $.post("/sysrole/deleteById",{id:id},function (res){
            layer.msg('已删除');
            layui.table.reload('sysRoleReload');
            layer.close(index); //关闭弹层
        })  ;
    });
}