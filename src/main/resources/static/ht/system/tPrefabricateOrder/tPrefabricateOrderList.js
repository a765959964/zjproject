//初始化 layui 数据表格
var prefix = "/sys/tPrefabricateOrder/";
$(function () {
    init();
    initSel();
    initFoodtypeSel();
})

$("select#sel").change(function (){
    $("#sel").val($(this).val())
})

$("select#type").change(function (){
    $("#type").val($(this).val())
})

$("select#foodType").change(function (){
    $("#foodType").val($(this).val())
})

$("select#foodtypeId").change(function (){
    $("#foodtypeId").val($(this).val())
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


function init() {
    layui.use(['table', 'element'], function () {
        var $ = layui.$
            , table = layui.table
            , element = layui.element
            , form = layui.form;
        table.render({
            elem: '#test-table-totalRow'
            , url: prefix + 'getPrefabricateList'
            , toolbar: '#toolbarDemo'
            , title: '菜品预售管理'
            , totalRow: true
            , limit : 20
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', width: 100, title: '主键', sort: true,hidden:true}
                // , {field: 'kitchenId', width: 100, title: '门店id', sort: true}
                , {field: 'kitchenName', width: 100, title: '门店名称', sort: true}
                , {field: 'foodid', width: 100, title: '菜品id', sort: true}
                , {field: 'foodName', width: 180, title: '菜品名称', sort: true}
                , {field: 'systemSum', width: 120, title: '系统生成份数'}
                , {field: 'sum', width: 100, title: '总份数',edit: 'text'}
                , {field: 'sellNum', width: 100, title: '销量份数'}
                , {field: 'remainNum', width: 100, title: '剩余份数'}
                , {field: 'prefabricateTime', width: 130, title: '预制时间', sort: true}
                // , {field: 'payDate', width: 200, title: '下单时间', sort: true}
                , {field: 'type', width: 120, title: '午餐晚餐', sort: true,templet:'#typeTpl'}
                , {fixed: 'right', width: 180, align: 'center', toolbar: '#barTPrefabricateOrder'}
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
                tPrefabricateOrderEdit(data.id);
            }
        });

        //监听单元格编辑
        table.on('edit(test-table-totalRow)', function(obj){
            debugger;
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
                obj.data.remainNum = value;
            layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
        });


        //头工具栏事件
        table.on('toolbar(test-table-totalRow)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            if (obj.event == "add") {   //添加
                var ids;    //得到  1,2,3
                if (checkStatus.data.length > 0) {
                    $(checkStatus.data).each(function (i, e) {
                        if (i == 0) {
                            ids = e.id;
                        } else {
                            ids += "," + e.id;
                        }
                    })
                    console.log("id是:"+ids);
                    prefabricateAdd(ids);
                }else{
                    layer.msg("请选择数据");
                }

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
                    url: prefix + 'getPrefabricateList'
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        kitchenId : $("#sel").val(),
                        type : $("#type").val(),
                        foodName : $("#foodName").val(),
                        foodtypeId : $("#foodtypeId").val(),
                        foodType : $("#foodType").val(),
                        prefabricateTime : $("#prefabricateTime").val()

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

//增加页面
function prefabricateAdd(ids) {
    layer.open({
        type: 2
        , title: '批量设置预售信息'
        , content: prefix + 'tPrefabricateOrderAdd?ids='+ids
        , maxmin: true
        , area: ['650px', '550px']
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            var iframeWindow = window['layui-layer-iframe' + index]
                , submitID = 'tPrefabricateOrderAddSubmit'
                , submit = layero.find('iframe').contents().find('#' + submitID);
            //监听提交
            iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                var field = data.field; //获取提交的字段
                console.log(field);
                $.ajax({
                    url: prefix + 'batchUpdate',
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
function tPrefabricateOrderEdit(id) {
    layer.open({
        type: 2
        , title: '修改预售信息'
        , content: prefix + 'getById?id=' + id
        , maxmin: true
        , area: ['650px', '550px']
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            var iframeWindow = window['layui-layer-iframe' + index]
                , submitID = 'tPrefabricateOrderUpdateSubmit'
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