//初始化 layui 数据表格
var prefix = "/sys/foodtype/";
$(function (){
    init();
    initSel();
})

$("select#sel").change(function (){
    $("#sel").val($(this).val())
})

$("select#status").change(function (){
    $("#status").val($(this).val())
})

function initSel(){
    var _sel = $("#sel");

    $.get("/sys/tKitchen/list",function(rsp){
        if(rsp.code==200){
           if(!rsp.data.length){
               return;
           }
            var html = "";
           for(var i=0;i<rsp.data.length;i++){
               html+="<option value='"+rsp.data[i].kitchenId+"'>"+rsp.data[i].name+"</option>";
           }
            _sel.append(html);
        }
    });
}


function init(){
    layui.use([ 'table','element'], function(){
        var $ = layui.$
            ,table = layui.table
            ,element = layui.element
            ,form = layui.form;
        table.render({
            elem: '#test-table-totalRow'
            ,url:prefix + 'getAll'
            ,toolbar: '#toolbarDemo'
            ,title: '添加字典'
            ,totalRow: true
            ,cols: [[
                {type: 'radio', fixed: 'left'}
                ,{field:'foodId', width:100,title: '菜品id', sort: true}
                ,{field:'foodName', width:150, title: '菜品名称', sort: true}
                ,{field:'kichenPrice', width:80, title: '价格', sort: true}
                // ,{field:'score', width:80, title: '评分', sort: true,hidden:false}
                ,{field:'status', width:100, title: '状态', sort: true,templet:'#statusTpl'}
                // ,{field:'jsonpath', width:300, title: 'json地址', sort: true}
                ,{field:'createTime', width:200, title: '创建时间', sort: true}
                ,{fixed: 'right', width: 280, align:'center', toolbar: '#barFoodType'}
            ]]
            ,page: true
            ,id : 'testReload'
            ,LAY_CHECKED : true

        });

        //监听行工具事件
        table.on('tool(test-table-totalRow)', function(obj){
            var data = obj.data;
            if(obj.event === '1'){      //上线
                updateStatus(data.id,1);
            } else if(obj.event === '2'){     //下线
                updateStatus(data.id,2);
            } else if(obj.event === '3') {     //试吃
                updateStatus(data.id,3);
            } else if(obj.event === '4') {     //试吃
                updateStatus(data.id,4);
            } else if(obj.event === '5') {     //试吃
                updateStatus(data.id,5);
            }
        });

        //头工具栏事件
        table.on('toolbar(test-table-totalRow)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            if(obj.event=="add"){   //添加
                dictAdd();
            }else if(obj.event=="batchRemove"){
                var ids;    //得到  1,2,3
                if(checkStatus.data.length>0){
                    $(checkStatus.data).each(function (i,e){
                        if(i==0){
                            ids = e.id;
                        }else{
                            ids+="," + e.id;
                        }
                    })
                    batchRemove(ids);
                }
            }

        });

        table.on('radio(test-table-totalRow)', function(obj){
            console.log(obj)
        });


        var $ = layui.$, active = {
            reload: function(){
                var sel = $('#sel');
                //执行重载
                table.reload('testReload', {
                    url : prefix+'getAll'
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        kitchenId : $("#sel").val(),
                        status : $("#status").val()
                    }
                });
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
}


function updateStatus(id,status){
    var data = {id:id,status:status};
    $.post(prefix+"update",data,function(data){
        console.log(data);
        layui.table.reload('testReload');
        if(status==1){
            layer.msg('以修改为上线', {icon: 1});
        }else if(status==2){
            layer.msg('以修改为下线', {icon: 1});
        }else if(status==3){
            layer.msg('以修改为试吃', {icon: 1});
        }else if(status==4){
            layer.msg('以修改为收藏', {icon: 1});
        }else if(status==5){
            layer.msg('以修改为删除', {icon: 1});
        }


    })

}

//增加页面
function dictAdd(){
    layer.open({
        type: 2
        ,title: '增加字典类型'
        ,content: prefix+'dictAdd'
        ,maxmin: true
        ,area: ['650px', '550px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'sysDictAddSubmit'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);
            //监听提交
            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                var field = data.field; //获取提交的字段
                console.log(field);
                $.ajax({
                    url : prefix+'insert',
                    type : 'post',
                    dataType : 'json',
                    data: field,
                    success : function (res){
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


function batchRemove(ids){
    layer.confirm('确定删除吗？', function(index) {
        $.post(prefix+"batchRemove",{ids:ids},function (res){
            layer.msg('已删除');
            layui.table.reload('testReload');
            layer.close(index); //关闭弹层
        })  ;
    });
}

function dictAddChildren(id){
    layer.open({
        type: 2
        ,title: '增加字典项'
        ,content: prefix+'dictAdd?id='+id
        ,maxmin: true
        ,area: ['650px', '550px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'sysDictAddSubmit'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);
            //监听提交
            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                var field = data.field; //获取提交的字段
                console.log(field);
                $.ajax({
                    url :prefix+ 'insert',
                    type : 'post',
                    dataType : 'json',
                    data: field,
                    success : function (res){
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


//编辑页面
function dictEdit(id){
    layer.open({
        type: 2
        ,title: '修改字典信息'
        ,content:prefix+ 'getById?id='+id
        ,maxmin: true
        ,area: ['650px', '550px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'sysDictUpdateSubmit'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);
            //监听提交
            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                var field = data.field; //获取提交的字段
                $.ajax({
                    url : prefix+'update',
                    type : 'post',
                    dataType : 'json',
                    data: field,
                    success : function (res){
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
function delById(id){
    layer.confirm('确定删除吗？', function(index) {
        $.post(prefix+"deleteById",{id:id},function (res){
            layer.msg('已删除');
            layui.table.reload('testReload');
            layer.close(index); //关闭弹层
        })  ;
    });
}