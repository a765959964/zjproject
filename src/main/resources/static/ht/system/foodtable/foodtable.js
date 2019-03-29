//初始化 layui 数据表格
var prefix = "/sys/foodtable/";
$(function (){
    init();
})




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
                ,{field:'id', width:100,title: '菜品id', sort: true}
                ,{field:'foodname', width:180, title: '菜品名称', sort: true}
                ,{field:'foodtypeField', width:200, title: '菜系', sort: true}
                // ,{field:'jsonpath', width:300, title: 'json地址', sort: true}
                ,{field:'createTime', width:300, title: '创建时间', sort: true}
                ,{fixed: 'right', width: 180, align:'center', toolbar: '#barFoodtable'}
            ]]
            ,page: true
            ,id : 'testReload'
        });

        //监听行工具事件
        table.on('tool(test-table-totalRow)', function(obj){
            var data = obj.data;
            if(obj.event === 'tabAdd'){      //添加至门店
                foodtableAdd(data.id);
            }
        });

        //头工具栏事件
        table.on('toolbar(test-table-totalRow)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            console.log(checkStatus);
            if(obj.event=="add"){   //添加
            }

        });

        var $ = layui.$, active = {
            reload: function(){
                var demoReload = $('#demoReload');
                //执行重载
                table.reload('testReload', {
                    url : prefix+'getAll'
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        id: demoReload.val(),
                        foodname : $("#foodname").val()

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

//增加页面
function foodtableAdd(id){
    layer.open({
        type: 2
        ,title: '增加门店菜品'
        ,content: prefix+'getById?id='+id
        ,maxmin: true
        ,area: ['650px', '550px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            debugger;
            var id = layero.find('iframe').contents().find("input[type='button']").attr("id");
            var iframeWindow = window['layui-layer-iframe'+ index]
                // ,submitID = 'foodtypeAddSubmit'?'foodtypeAddSubmit':'foodtypeUpdateSubmit'
                ,submit = layero.find('iframe').contents().find('#'+ id);
            //监听提交
            iframeWindow.layui.form.on('submit('+ id +')', function(data){
                var field = data.field; //获取提交的字段
                if(id == "foodtypeAddSubmit"){
                    $.ajax({
                        url : '/sys/foodtype/insert',
                        type : 'post',
                        dataType : 'json',
                        data: field,
                        success : function (res){
                            layer.msg("增加成功");
                            layui.table.reload('testReload');
                            layer.close(index); //关闭弹层
                        }
                    })
                }else{
                    $.ajax({
                        url : '/sys/foodtype/update',
                        type : 'post',
                        dataType : 'json',
                        data: field,
                        success : function (res){
                            layer.msg("修改成功");
                            layui.table.reload('testReload');
                            layer.close(index); //关闭弹层
                        }
                    })
                }
            });
            submit.trigger('click');
        }
    });
}
