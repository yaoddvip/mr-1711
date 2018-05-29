<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/4
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>tree</title>
    <script src="<%=path%>/js/jquery/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/js/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="<%=path%>/js/jquery-easyui/jquery.easyui.min.js"></script>
    <script  src="<%=path%>/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
    <ul id="myTree"></ul>

    <div id="mm" class="easyui-menu" style="width:120px;">
        <div onclick="append()" data-options="iconCls:'icon-add'">追加</div>
        <div onclick="remove()" data-options="iconCls:'icon-remove'">移除</div>
    </div>

    <%-- 增加tree的弹框--%>
    <div id="addTreeDialog" style="display:none;">
        <form id="addTreeForm" method="post">
            <input type="hidden" name="pid" id="pid"/>
            text:<input  name="text" />
            <br>
            iconCls:<input name="iconCls">
            <br>
            url：<input name="url">
        </form>
    </div>



    <script>
        $(function(){
            /*初始化加载tree节点*/
            $('#myTree').tree({
                url:'<%=path%>/tree/list.do',
                onContextMenu: function(e, node){
                    e.preventDefault();
                    // 查找节点
                    $('#myTree').tree('select', node.target);
                    // 显示快捷菜单
                    $('#mm').menu('show', {
                        left: e.pageX,
                        top: e.pageY
                    });
                },
                onAfterEdit:function(node){
                    console.log(node);
                }
            });
        })

        //点击追加调用的函数
        function append(){
            //获取到当前对象的id，作为新增对象的pid
            var node = $('#myTree').tree('getSelected');
            console.log(node.id);

            $("#pid").val(node.id);

            $('#addTreeDialog').dialog({
                title: '增加tree',
                width: 400,
                height: 200,
                closed: false,
                cache: false,
                modal: true,
                buttons:[{
                    text:'保存',
                    handler:function(){
                        addTree();
                    }
                },{
                    text:'关闭',
                    handler:function(){
                        alert('关闭');
                    }
                }]

            });
        }

        //增加节点
        function addTree(){
            $('#addTreeForm').form('submit', {
                url: "<%=path%>/tree/addTree.do",
                success: function(){

                }
        });

        }

        function remove(){
           /* var node = $('#myTree').tree('getSelected');
            $('#myTree').tree('remove',node.target);*/

            var node = $('#myTree').tree('getSelected');

            $('#myTree').tree('beginEdit',node.target);
        }
    </script>
</body>
</html>
