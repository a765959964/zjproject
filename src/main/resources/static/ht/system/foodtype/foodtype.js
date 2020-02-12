//初始化 layui 数据表格
var prefix = "/sys/foodtype/";
$(function (){
    init();
    initSel();
    initFoodtypeSel();
})

$("select#sel").change(function (){
    $("#sel").val($(this).val())
})


$("select#foodtypeId").change(function (){
    $("#foodtypeId").val($(this).val())
})


$("select#foodType").change(function (){
    $("#foodType").val($(this).val())
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

function initFoodtypeSel(){
    var _sel = $("#foodtypeId");

    $.get("/sys/foodtype/getTypeList/",function(rsp){
        if(rsp.code==200){
            if(!rsp.data.length){
                return;
            }
            var html = "";
            html+="<option value=''>全部</option>";
            for(var i=0;i<rsp.data.length;i++){
                html+="<option value='"+rsp.data[i].code+"'>"+rsp.data[i].name+"</option>";
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
            ,url:prefix + 'getFoodTypeList'
            ,toolbar: '#toolbarDemo'
            ,title: '门店菜品管理'
            ,totalRow: true
            , limit : 20
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', width:70,title: 'id', sort: true}
                ,{field:'kitchenName', width:120,title: '餐厅信息', sort: true}
                ,{field:'foodId', width:100,title: '菜品id', sort: true}
                ,{field:'foodName', width:200, title: '菜品名称', sort: true}
                ,{field:'foodtypeName', width:150, title: '菜品分类'}
                ,{field:'kitchenPrice', width:100, title: '价格'}
                ,{field:'status', width:100, title: '状态', sort: true,templet:'#statusTpl'}
                ,{field:'foodType', width:120, title: '堂食 外卖', sort: true,templet:'#foodTypeTpl'}
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
            console.log(data.id);
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
            }else if (obj.event  === '6'){
                console.log(data);
                var  foodid = data.foodId;
                var kitchenId = $("#sel").val();
                batchAdd(foodid,kitchenId);
                // layer.msg('添加页面',{icon:1});
            }
        });

        //头工具栏事件
        table.on('toolbar(test-table-totalRow)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            var kitchenId =  $('#sel').val();
            // console.log($("#sel").val());
            if(obj.event=="add"){   //添加
                var foodIds;    //得到  1,2,3  菜品ids
                var ids;
                if(checkStatus.data.length>0){
                    $(checkStatus.data).each(function (i,e){
                        if(i==0){
                            // foodIds = e.foodId;
                            ids = e.foodId;
                        }else{
                            // foodIds+="," + e.foodId;
                            ids += "," + e.foodId;
                        }
                    })
                    batchAdd(ids,kitchenId);
                }else{
                    layer.msg('请选择一条数据！');
                }
            }else if(obj.event=="refresh"){
                console.log('刷新');
                $.ajax({
                    url : prefix + 'createJson?kitchenId='+$("#sel").val(),
                    type : 'get',
                    success : function (res){
                        console.log(res.msg);
                    }
                })



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
                    url : prefix+'getFoodTypeList'
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        kitchenId : $("#sel").val(),
                        status : $("#status").val(),
                        foodtypeId : $('#foodtypeId').val(),
                        foodName : $('#foodName').val(),
                        foodType : $('#foodType').val()
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
        }else if(status==6){
            layer.msg('添加页面',{icon:1});
        }
    })

}


//批量添加估清
function batchAdd(id,kitchenId){
    layer.open({
        type: 2
        ,title: '批量增加估清'
        ,content: prefix+'batchAdd?foodid='+id+"&kitchenId="+kitchenId
        ,maxmin: true
        ,area: ['650px', '550px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'foodtypeAddSubmit'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);
            var sum  = layero.find('iframe').contents().find('#sum')[0].value;
            var wcSum  = layero.find('iframe').contents().find('#sum1')[0].value;
            var wmSum = layero.find('iframe').contents().find('#sum2')[0].value;
            console.log(sum+","+wcSum+","+wmSum);
            var sums ;
            console.log(wmSum==='');
            if(wmSum===''){
                sums  = Array.of(sum,wcSum);
            }else{
                sums  = Array.of(sum,wcSum,wmSum);
            }
            console.log(sums);
            layero.find('iframe').contents().find('#sums')[0].value = sums;
            //监听提交
            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                debugger;
                var field = data.field; //获取提交的字段
                $.ajax({
                    url : prefix + 'batchUpdate',
                    type : 'POST',
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