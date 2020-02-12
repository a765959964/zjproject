//初始化 layui 数据表格
var prefix = "/sys/myorder/";
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
            , url: prefix + 'getOrderListCount'
            , toolbar: '#toolbarDemo'
            , title: '添加字典'
            , limit : 20
            , totalRow: true
            , cols: [[
                {type: 'checkbox', fixed: 'left',totalRowText: '合计'}
                , {field: 'ordertime', width: 150, title: '订单时间', sort: true}
                , {field: 'foodTypeName', width: 150, title: '菜品分类', sort: true}
                , {field: 'foodname', width: 200, title: '菜品名称', sort: true}
                , {field: 'totalPrice', width: 110, title: '菜品总价', totalRow: true}
                , {field: 'foodcount', width: 110, title: '菜品份数', sort: true,totalRow: true}
                , {field: 'foodtable_price', width:110, title: '菜品单价', sort: true}


                // , {fixed: 'right', width: 180, align: 'center', toolbar: '#barMyorder'}
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
                dictEdit(data.id);
            } else if (obj.event === 'tabAdd') {     //
                dictAddChildren(data.id);
            }
        });

        //头工具栏事件
        table.on('toolbar(test-table-totalRow)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            if (obj.event == "add") {   //添加
                dictAdd();
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
                    url: prefix + 'getOrderListCount'
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {
                        // ordertime: $("#ordertime").val(),
                        beginDate : $("#beginDate").val(),
                        endDate : $("#endDate").val(),
                        type : $("#type").val()
                        // address: $("#address").val()

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










