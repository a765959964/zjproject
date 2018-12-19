var prefix = "/sys/dept/";
var editObj=null,ptable=null,treeGrid=null,tableId='treeTable',layer=null;
layui.config({
    base: '/static/layui/layuiadmin/plugins/'
}).extend({
    treeGrid:'treeGrid'
}).use(['jquery','treeGrid','layer'], function(){
    var $=layui.jquery;
    treeGrid = layui.treeGrid;//很重要
    layer=layui.layer;
    ptable=treeGrid.render({
        id:tableId
        ,elem: '#'+tableId
        ,url:prefix + 'getTreeData'
        ,cellMinWidth: 100
        ,idField:'id'//必須字段
        ,treeId:'id'//树形id字段名称
        ,treeUpId:'pId'//树形父id字段名称
        ,treeShowName:'name'//以树形式显示的字段
        ,heightRemove:[".dHead",10]//不计算的高度,表格设定的是固定高度，此项不生效
        ,height:'100%'
        ,isFilter:false
        ,iconOpen:true//是否显示图标【默认显示】
        ,isOpenDefault:true//节点默认是展开还是折叠【默认展开】
        ,loading:true
        ,method:'post'
        ,cols: [[
            {type:'numbers'}
            ,{type:'radio'}
            ,{type:'checkbox',sort:true}
            ,{field:'name', width:300, title: '部门名称',edit:'text',sort:true}
            ,{field:'sort',width:100, title: '排序',sort:true}
            ,{field:'isdel', title: '状态',sort:true}
            ,{width:150,title: '操作', align:'center',toolbar: '#barSysDept'
            }
        ]]
        ,isPage:false
        ,parseData:function (res) {//数据加载后回调
            return res;
        }
        ,onClickRow:function (index, o) {
            //     console.log(index,o,"单击！");
        }
        ,onDblClickRow:function (index, o) {
            //  console.log(index,o,"双击");
        }
    });

    treeGrid.on('tool('+tableId+')',function (obj) {
        var id = obj.data.id;
        if(obj.event === 'del'){//删除行
            del(id);
        }else if(obj.event==="add"){  //添加行
            deptAdd(id);
        }else if(obj.event==="edit"){ //修改
            deptEdit(id);
        }
    });
});



function del(id) {
    layer.confirm("你确定删除数据吗？如果存在下级节点则一并删除，此操作不能撤销！", {icon: 3, title:'提示'},
        function(index){//确定回调
            $.post(prefix + "deleteById",{id:id},function (res){
                layer.msg("删除部门成功");
            });
            query();
            //reload();
            layer.close(index);
        },function (index) {//取消回调
            layer.close(index);
        }
    );
}


function deptAdd(id){
    layer.open({
        type: 2
        ,title: '增加部门信息'
        ,content: prefix+ 'getByIdAdd?id='+id
        //    ,maxmin: true
        ,area: ['450px', '400px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'deptSubmit'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);
            //监听提交
            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                var field = data.field; //获取提交的字段
                field.pid = id;
                $.ajax({
                    url : prefix +'insert',
                    type : 'post',
                    dataType : 'json',
                    data: field,
                    success : function (res){
                        layer.msg("增加部门成功");
                        query();
                        // reload();
                        layer.close(index); //关闭弹层
                    }
                })
            });
            submit.trigger('click');
        }
    });
}


function deptEdit(id){
    layer.open({
        type: 2
        ,title: '修改部门信息'
        ,content:prefix +  'getByIdEdit?id='+id
        //    ,maxmin: true
        ,area: ['450px', '400px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'deptUpdate'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);
            //监听提交
            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                var field = data.field; //获取提交的字段
                $.ajax({
                    url :prefix +  'update',
                    type : 'post',
                    dataType : 'json',
                    data: field,
                    success : function (res){
                        layer.msg("修改部门成功");
                        query();
                        //reload();
                        layer.close(index); //关闭弹层
                    }
                })
            });
            submit.trigger('click');
        }
    });
}


function addParent(){
    layer.open({
        type: 2
        ,title: '新增部门信息'
        ,content:prefix + 'add'
        //    ,maxmin: true
        ,area: ['450px', '400px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'addSubmit'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);
            //监听提交
            iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                var field = data.field; //获取提交的字段
                field.pid = 0;
                $.ajax({
                    url :prefix + 'insert',
                    type : 'post',
                    dataType : 'json',
                    data: field,
                    success : function (res){
                        layer.msg("新增部门成功");
                        query();
                        //reload();
                        layer.close(index); //关闭弹层
                    }
                })
            });
            submit.trigger('click');
        }
    });
}

function print() {
    console.log(treeGrid.cache[tableId]);
    var loadIndex=layer.msg("对象已打印，按F12，在控制台查看！", {
        time:3000
        ,offset: 'auto'//顶部
        ,shade: 0
    });
}

function openorclose() {
    var map=treeGrid.getDataMap(tableId);
    var o= map['102'];
    treeGrid.treeNodeOpen(tableId,o,!o[treeGrid.config.cols.isOpen]);
}


function openAll() {
    var treedata=treeGrid.getDataTreeList(tableId);
    treeGrid.treeOpenAll(tableId,!treedata[0][treeGrid.config.cols.isOpen]);
}

function getCheckData() {
    var checkStatus = treeGrid.checkStatus(tableId)
        ,data = checkStatus.data;
    layer.alert(JSON.stringify(data));
}
function radioStatus() {
    var data = treeGrid.radioStatus(tableId)
    layer.alert(JSON.stringify(data));
}
function getCheckLength() {
    var checkStatus = treeGrid.checkStatus(tableId)
        ,data = checkStatus.data;
    layer.msg('选中了：'+ data.length + ' 个');
}

function reload() {
    treeGrid.reload(tableId,{
        page:{
            curr:1
        }
    });
}
function query() {
    treeGrid.query(tableId,{
        where:{
            name:'sdfsdfsdf'
        }
    });
}

function test() {
    console.log(treeGrid.cache[tableId],treeGrid.getClass(tableId));


    /*var map=treeGrid.getDataMap(tableId);
     var o= map['102'];
     o.name="更新";
     treeGrid.updateRow(tableId,o);*/
}
