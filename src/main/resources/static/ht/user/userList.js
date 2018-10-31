/**
 * Created by 张帆 on 2018/10/30.
 */
$(function (){
    init();
    var obj;
    $.post("/sysdept/getTree",function (res){
        obj = res; // console.log(res);
        layui.use('tree', function(){
            var $ = layui.jquery;
            layui.tree({
                elem: '#demo2' //指定元素
                ,target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
                ,url : 'sysdept/getTree'
                ,click: function(item){ //点击节点回调
                    //  $('#demo2-view').html('当前节名称：'+ item.name + '<br>全部参数：'+ JSON.stringify(item));
                    if(item.pId==0){
                        $("#deptId").html('0');
                    }else{
                        $("#deptId").html(item.id);
                    }
                    layui.use( 'table', function(){
                            var $ = layui.$
                                ,table = layui.table
                                ,form = layui.form;
                            table.render({
                                elem: '#sysUserTable'
                                ,url: '/sysuser/getAll?deptId='+$("#deptId").html()
                                ,toolbar: '#toolbarSysUser'
                                ,title: '用户数据表'
                                ,totalRow: true
                                ,cols: [[
                                    {type: 'checkbox', fixed: 'left'}
                                    ,{field:'id', width:50, title: 'ID', sort: true}
                                    ,{field:'name', width:100, title: '用户名'}
                                    ,{field:'sex', width:150, title: '性别', sort: true,templet:'#sexTpl'}
                                    ,{field:'pwd', width:100, title: '密码'}
                                    ,{field:'email', width:200, title: 'email'}
                                    ,{fixed: 'right', width: 200, align:'center', toolbar: '#barSysUser'}
                                ]]
                                ,page: true
                                ,id : 'sysUserReload'
                            })


                        }
                    )}
                ,nodes: obj
            });
        });

    });

})





//初始化 layui 数据表格
function init(){
    layui.use( 'table', function(){
        var $ = layui.$
            ,table = layui.table
            ,form = layui.form;
        table.render({
            elem: '#sysUserTable'
            ,url: '/sysuser/getAll'
            ,toolbar: '#toolbarSysUser'
            ,title: '用户数据表'
            ,totalRow: true
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', width:50, title: 'ID', sort: true}
                ,{field:'name', width:100, title: '用户名'}
                ,{field:'sex', width:150, title: '性别', sort: true,templet:'#sexTpl'}
                ,{field:'pwd', width:100, title: '密码'}
                ,{field:'email', width:200, title: 'email'}
                ,{fixed: 'right', width: 200, align:'center', toolbar: '#barSysUser'}
            ]]
            ,page: true
            ,id : 'sysUserReload'
        });

        //监听行工具事件
        table.on('tool(sysUserTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){      //删除
                delById(data.id);
            } else if(obj.event === 'edit'){     //编辑
                sysUserEdit(data.id);
            } else {     //查看
                alert("查看");
            }
        });

        //头工具栏事件
        table.on('toolbar(sysUserTable)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            if(obj.event=="add"){   //添加
                sysUserAdd();
            }
        });

        var $ = layui.$, active = {
            reload: function(){
                var demoReload = $('#demoReload');
                //执行重载
                table.reload('sysUserReload', {
                    url : '/sysuser/getAll'
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        id: demoReload.val(),
                        name : $("#name").val(),
                        deptId : $("#deptId").html(),
                        address :  $("#address").val()
                    }
                });
            }
        };

        $('.sysUserTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
}

//增加页面
function sysUserAdd(){
    layer.open({
        type: 2
        ,title: '增加用户信息'
        ,content: '/sysuser/userAdd'
        ,maxmin: true
        ,area: ['650px', '550px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'sysUserSubmit'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);
            //监听提交
            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                var field = data.field; //获取提交的字段
                $.ajax({
                    url : '/sysuser/insert',
                    type : 'post',
                    dataType : 'json',
                    data: field,
                    success : function (res){
                        layer.msg("增加成功");
                        layui.table.reload('sysUserReload');
                        layer.close(index); //关闭弹层
                    }
                })
            });
            submit.trigger('click');
        }
    });
}

//编辑页面
function sysUserEdit(id){
    layer.open({
        type: 2
        ,title: '修改用户信息'
        ,content: '/sysuser/getById?id='+id
        ,maxmin: true
        ,area: ['650px', '550px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'sysUserUpdate'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);
            //监听提交
            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                var field = data.field; //获取提交的字段
                $.ajax({
                    url : '/sysuser/update',
                    type : 'post',
                    dataType : 'json',
                    data: field,
                    success : function (res){
                        layer.msg("修改成功");
                        layui.table.reload('sysUserReload');
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
         $.post("/sysuser/deleteById",{id:id},function (res){
            layer.msg('已删除');
            layui.table.reload('sysUserReload');
            layer.close(index); //关闭弹层
        })
    });
}