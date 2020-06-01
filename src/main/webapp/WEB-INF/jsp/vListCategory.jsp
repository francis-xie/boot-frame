<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <script src="http://how2j.cn/study/jquery.min.js"></script>
    <script src="http://how2j.cn/study/vue.min.js"></script>
    <script src="http://how2j.cn/study/axios.min.js"></script>
    <style type="text/css">
        td {
            border: 1px solid gray;
        }

        table {
            border-collapse: collapse;
        }

        div#app {
            margin: 20px auto;
            width: 400px;
            padding: 20px;

        }

        div#pagination {
            text-align: center;
            line-height: 100px;
        }

        div#pagination a {
            text-decoration: none;
        }

        .disableHref {
            cursor: default;
            color: #E5E0E0;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div id="app">
    <table id="heroListTable">
        <tr>
            <td colspan="3">
                <div id="pagination">
                    <a :class="{ disableHref: pagination.pageNum==1 }" href="#nowhere"
                       @click="jump('first')">[first]</a>
                    <a :class="{ disableHref: !pagination.hasPreviousPage }" href="#nowhere"
                       @click="jump('pre')">[pre]</a>

                    <a href="#nowhere" :class="{disableHref:pagination.pageNum==i}"
                       v-for="i in pagination.navigatepageNums" @click="jumpByNumber(i)">
                        {{i}}
                    </a>

                    <a :class="{ disableHref: !pagination.hasNextPage }" href="#nowhere"
                       @click="jump('next')">[next]</a>
                    <a :class="{ disableHref: pagination.pageNum==pagination.pages }" href="#nowhere"
                       @click="jump('last')">[last]</a>
                </div>

            </td>
        </tr>

        <tr>
            <td>id</td>
            <td>name</td>
            <td>操作</td>
        </tr>

        <tr v-for="hero in heros ">
            <td>{{hero.id}}</td>
            <td>{{hero.name}}</td>
            <td>
                <a :href="'editCategoriev?id=' + hero.id ">编辑</a>
                <a href="#nowhere" @click="deleteHero(hero.id)">删除</a>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <br>
                name:
                <input type="text" v-model="hero4Add.name"/>
                <br>
                <br>
                <button type="button" v-on:click="add">增加</button>
                <br>
            </td>
        </tr>
    </table>
</div>
</body>
</html>

<script type="text/javascript">
    var data4Vue = {
        heros: [],
        hero4Add: {id: 0, name: ''},
        pagination: {}
    };

    //ViewModel
    var vue = new Vue({
        el: '#app',
        data: data4Vue,
        mounted: function () { //mounted　表示这个 Vue 对象加载成功了
            this.list(1);
        },
        methods: {
            list: function (start) {
                var url = "categoriev?start=" + start;
                axios.get(url).then(function (response) {
                    vue.pagination = response.data;
                    console.log(vue.pagination);
                    vue.heros = response.data.list;
                })
            },
            add: function (event) {
                var url = "categoriev";

                axios.post(url, this.hero4Add).then(function (response) {
                    vue.list(1);
                    vue.hero4Add = {id: 0, name: ''}
                });
            },
            deleteHero: function (id) {
                var url = "categoriev/" + id;
                axios.delete(url).then(function (response) {
                    vue.list(1);
                });
            },
            jump: function (page) {
                if ('first' == page && 1 != vue.pagination.pageNum)
                    vue.list(1);

                else if ('pre' == page && vue.pagination.hasPreviousPage)
                    vue.list(vue.pagination.prePage);

                else if ('next' == page && vue.pagination.hasNextPage)
                    vue.list(vue.pagination.nextPage);

                else if ('last' == page && vue.pagination.pageNum != vue.pagination.pages)
                    vue.list(vue.pagination.pages);

            },
            jumpByNumber: function (start) {
                if (start != vue.pagination.pageNum)
                    vue.list(start);
            }
        }
    });
</script>

<script>
    $(function () {
        $("a.disableHref").click(function (event) {
            return false;
// 	    		  event.preventDefault();
        });
    });
</script>