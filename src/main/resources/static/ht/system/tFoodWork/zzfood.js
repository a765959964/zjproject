//初始化 layui 数据表格
var prefix = "/sys/tFoodWork/";
$(function () {
    init();
    weekInit();

})

function init() {
    layui.use(['table', 'element'], function () {
        var $ = layui.$
            , table = layui.table
            , element = layui.element
            , form = layui.form;
        table.render({
            elem: '#test-table-totalRow'
            , url: prefix + 'getKitFoodList'
            , toolbar: '#toolbarDemo'
            , title: '添加字典'
            , totalRow: true
            , cols: [[

                // {type: 'checkbox', fixed: 'left'}
                // , {field: 'id', width: 100, title: '字典主键', sort: true,type: hidden}
                 {field: 'food_name', width: 450, title: '菜品名称'}
                , {field: 'chefname', width: 450, title: '大师'}
                , {fixed: 'right', width: 170, align: 'center', toolbar: '#barTFoodWork'}
            ]]
            , page: true
            , id: 'testReload'
        });

        //监听行工具事件
        table.on('tool(test-table-totalRow)', function (obj) {
            var data = obj.data;
            if (obj.event === 'save') {      //添加
               // alert('添加');
                console.log(data);
                zzfoodAdd(data.food_id,data.food_name);
            }
        });

        //头工具栏事件
        table.on('toolbar(test-table-totalRow)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            if (obj.event == "add") {   //添加
                alert('添加');
            } else if (obj.event == "save") {
                // alert('save');
            }else if(obj.event == 'foodSel') {
                // alert(123213)
                table.reload('testReload', {
                    url: prefix + 'getKitFoodList'
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        foodname : $("#foodname").val()
                    }
                });
            }

        });


        var $ = layui.$, active = {
            reload: function () {
                var demoReload = $('#demoReload');
                //执行重载
                table.reload('testReload', {
                    url: prefix + 'getKitFoodList'
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        kitchenId : 1
                    }
                });
            }
        };


        $(' .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
}


function weekInit() {
    layui.use(['table', 'element'], function () {
        var $ = layui.$
            , table = layui.table
            , element = layui.element
            , form = layui.form;
        table.render({
            elem: '#week-table-totalRow'
            , url: prefix + 'getKitZzList'
            , toolbar: '#weekbarDemo'
            , title: '添加字典'
            , totalRow: true
            , cols: [[
                  {field: 'week', width: 200, title: '星期',templet:'#weekTpl'}
                , {field: 'workDate', width: 180, title: '日期'}
                , {field: 'foodname', width: 500, title: '菜品'}
                , {fixed: 'right', width: 180, align: 'center', toolbar: '#barWeek'}
            ]]
            // , page: true
            // , id: 'testReload'
        });

        //监听行工具事件
        table.on('tool(week-table-totalRow)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {      //删除
                console.log(data);
                delById(data.date);
            } else if (obj.event === 'edit') {     //编辑
                tFoodZzEdit(data.id);
            }
        });





        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
}



//增加页面
function zzfoodAdd(foodid,foodname) {
    layer.open({
        type: 2
        , title: '添加自助餐'
        , content: prefix + 'zzfoodAdd?foodid='+foodid+"&foodname="+foodname
        , maxmin: true
        , area: ['650px', '250px']
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            var iframeWindow = window['layui-layer-iframe' + index]
                , submitID = 'zzfoodAddSubmit'
                , submit = layero.find('iframe').contents().find('#' + submitID);
            week =  layero.find('iframe').contents().find('input[name="week"]:checked');
            console.log(week);
            var weeks  = '';
            week.each(function (i,e){
                if(i==0){
                    weeks+=$(this).val()
                }else{
                    weeks+=","+$(this).val()
                }
            })
            console.log(weeks);
            //监听提交
            iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                var field = data.field; //获取提交的字段
                field.weeks = weeks;

                var  params = {
                    kitchenId : field.kitchenId,
                    foodid : field.foodid,
                    type : field.type,
                    ary :weeks
                }

                console.log(params);
                $.ajax({
                    url: prefix + 'foodwork/addZzOrder',
                    type: 'post',
                    dataType: 'json',
                    data: params,
                    success: function (res) {
                        layer.msg("增加成功");
                        weekInit();
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
    layer.confirm('确定删除吗11？', function (index) {
        $.post(prefix + "batchRemove", {ids: ids}, function (res) {
            layer.msg('已删除');
            layui.table.reload('testReload');
            layer.close(index); //关闭弹层
        });
    });
}




//编辑页面
function tFoodZzEdit(id) {
    layer.open({
        type: 2
        , title: '修改'
        , content: prefix + 'getByZzId?id=' + id
        , maxmin: true
        , area: ['650px', '450px']
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            var iframeWindow = window['layui-layer-iframe' + index]
                , submitID = 'tFoodZzUpdateSubmit'
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
function delById(date) {
    layer.confirm('确定删除吗？', function (index) {
        $.post(prefix + "delByWorkDate", {workDate: date,kitchenId:1,type:2}, function (res) {
            layer.msg('已删除');
            weekInit();
            layui.table.reload('testReload');
            layer.close(index); //关闭弹层
        });
    });
}