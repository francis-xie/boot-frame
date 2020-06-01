<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <script src="http://how2j.cn/study/jquery.min.js"></script>
    <script src="http://how2j.cn/study/vue.min.js"></script>
    <script src="http://how2j.cn/study/axios.min.js"></script>
    <style type="text/css">
        div#app {
            margin: 20px auto;
            width: 400px;
            padding: 20px;
        }
    </style>
</head>
<body>
<div id="app">
    <div id="div4Update">
        name:
        <input type="text" v-model="hero4Update.name"/>
        <br>
        <input type="hidden" v-model="hero4Update.id"/>
        <br>
        <button type="button" v-on:click="update">修改</button>
        <button type="button" v-on:click="cancel">取消</button>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    //获取地址栏参数的函数
    function getUrlParms(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null)
            return unescape(r[2]);
        return null;
    }

    var data4Vue = {
        heros: [],
        hero4Update: {id: 0, name: '', hp: '0'}
    };

    //ViewModel
    var vue = new Vue({
        el: '#app',
        data: data4Vue,
        mounted: function () { //mounted　表示这个 Vue 对象加载成功了
            this.get();
        },
        methods: {
            get: function () {
                var id = getUrlParms("id");
                var url = "categoriev/" + id;
                console.log(url);
                axios.get(url).then(function (response) {
                    vue.hero4Update = response.data;
                })
            },
            update: function (event) {
                var url = "categoriev/" + vue.hero4Update.id;
                axios.put(url, vue.hero4Update).then(function (response) {
                    location.href = "listCategoriev";
                });
            },
            cancel: function () {
                location.href = "listCategoriev";
            }
        }
    });
</script>