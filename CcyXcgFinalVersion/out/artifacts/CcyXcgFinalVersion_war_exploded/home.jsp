<!-------------Java imports-------------->
<%@ page import="dao.PaginationDao" %>
<%@ page import="dao.ParseDao" %>
<%@ page import="dao.SelectActualValueDao" %>
<%@ page import="dao.UserDao" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.LinkedList" %>
<%--
  Created by IntelliJ IDEA.
  User: lucian.Nicolescu
  Date: 8/28/2017
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!------------------------------------------>
<html>
<head>
    <title>Ccy Xcg</title>
    <link rel="stylesheet" type="text/css" href="../StyleSheet/homeStyle.css">
    <link rel="stylesheet" type="text/css" href="../StyleSheet/myAccountStyle.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/echarts/3.7.1/echarts.min.js"></script>
    <script>
        function pageIndex(){
            document.getElementsByName("page").innerHTML=1;
        }
    </script>
</head>
<body onload="getSparePartsAdditionHorizontalBar1()">
<!----------------Java include needed---------------->
    <%DecimalFormat df = new DecimalFormat("0.0000");%>
    <%
        ParseDao.getcurrency();
        LinkedList pairs =   ParseDao.pairs;
        LinkedList values = ParseDao.values;
        int len;
        UserDao user = new UserDao();
                %>
<!------------------------------------------------>
    <%if(session.getAttribute("name")==null) {%>
    <div class="topnav">
        <a class="active" href="home.jsp">Ccy Xcg </a>
        <a href="home.jsp" >Currencies</a>
        <a href="History.jsp">History</a>
        <a href="login.jsp">Login</a>
    </div>
    <%SelectActualValueDao.select();%>

    <div id="searchandtable">
        <div class="filterbox">
            <div >Filter</div>
            <form action="searchServlet" method="get">
                <input class="search" name="search" type="text" placeholder="Pair name">
                <input type="submit" value="Search">
            </form>
        </div>
        <div id="tableandchart">
            <div id="diagrama" style="width: 600px;height:400px;"></div>
            <div class="table-div">
            <%if(session.getAttribute("page")==null)
                {
                    len=1;
                }
            else{
                    len = (int) session.getAttribute("page");
                 }
            %>
            <table table class="tablee" width="700" frame="box"  id="currencyTable">
                <tr id="tablehader">
                    <th>Pair name<a onclick="sortTableAscending()">▲</a><a onclick="sortTableDescending()">▼</a> </th>
                    <th>Value</th>
                </tr>
                <%for (int i = PaginationDao.pagination(len-1); i< PaginationDao.pagination(len); i++){%>
                    <%String pair = (String)pairs.get(i);
                    Double value = (Double)values.get(i);%>
                <tr>
                    <th><button id="drawchart" value="<%=pair%>" onclick="getSparePartsAdditionHorizontalBar1()"><%=pair%></button></th>
                    <th><%=value%></th>
                </tr>
                <%}%>
                <tr>
                    <form action="PaginationServlet" method="get">
                        <th><input type="submit" name="button1" value="▲" required="required">
                            <input type="submit" name="button2" value="▼" required="required"></th>
                        <th><input class="search" name="page" type="text" value="<%=len%>"></th>
                    </form>
                </tr>
            </table>
            </div>
    </div>
    </div>
<script>
        function getSparePartsAdditionHorizontalBar1(pair) {
            var HttpClient = function() {
                this.get = function(aUrl, aCallback) {
                    var anHttpRequest = new XMLHttpRequest();
                    anHttpRequest.onreadystatechange = function() {
                        if (anHttpRequest.readyState == 4
                            && anHttpRequest.status == 200)
                            aCallback(anHttpRequest.responseText);
                    }

                    anHttpRequest.open("GET", aUrl, true);
                    anHttpRequest.send(null);
                }
            }
            aClient = new HttpClient();
            var url = "jsonServlet?op="+select1()+"&day="+7;
            aClient.get(url, function(response) {
                var values = eval('(' + response + ')');
                var x = Array();
                var y = Array();
                for (i=0;i<values.length;i++) {
                    // xAxis.push(values[key]['period']);
                    x.push(values[i]["Day"]);
                    y.push(values[i]["val"]);
                }
                createChart(x,y);
            });
        }
    </script>
    <script type="text/javascript">
        function createChart(x,y) {
            // based on prepared DOM, initialize echarts instance
            var myChart = echarts.init(document.getElementById('diagrama'));

            // specify chart configuration item and data
            var option = {
                tooltip : {
                    trigger: 'axis'
                },
                toolbox: {
                    show : true,
                    feature : {
                        // mark : {show: true},
                        magicType : {show: true, type: ['radar','line', 'bar']},
                    }
                },
                tooltip: {},
                xAxis: {
                    data:x,
                },
                yAxis: {},
                series: [{
                   // name: 'Value'+' '+select1(),
                    type: 'bar',
                    data: y,
                }]
            };

            // use configuration item and data specified to show chart
            myChart.setOption(option);
        }
    </script>
</body>
<%}else if(session.getAttribute("name")!=null){%>
<body onload="getSparePartsAdditionHorizontalBar2()">
<div class="topnav">
        <a class="active" href="home.jsp">Ccy Xcg </a>
        <a href="Currency.jsp" onclick="pageIndex()">Currencies Shop</a>
        <a href="History.jsp">History</a>
        <a href="myAccount.jsp" id="nameandbalance">Hello <%=user.username%> <br> Balance:<%=df.format(user.balance)%><%=user.currency%> </a>
        <div id="logoutt">
            <form action="logoutServlet" method="post">
                <a>  <input type="submit" id="logout" value="Logout" />   </a>
            </form>
        </div>
    </div>
    <div id="searchandtable">
        <div class="filterbox">
            <div >Filter</div>
            <form action="searchServlet" method="get">
                <input class="search" name="search" type="text" placeholder="Pair name">
                <input type="submit" value="Search">
            </form>
        </div>
        <div id="tableandchart">
            <div id="diagramaa1" style="width: 600px;height:400px;"></div>
        <div class="table-div">
        <%if(session.getAttribute("page")==null)
            {
                len=1;
            }
        else{
                len = (int) session.getAttribute("page");
            }
        %>
            <table class="tablee" frame="box" width="700" id="currencyTable">
                <tr>
                    <th>Pair name<a onclick="sortTableAscending()">▲</a><a onclick="sortTableDescending()">▼</a> </th>
                    <th>Value</th>
                </tr>
                <%for (int i = PaginationDao.pagination(len-1); i< PaginationDao.pagination(len); i++){%>
                <%String pair = (String)pairs.get(i);
                Double value = (Double)values.get(i);%>
                <tr>
                    <th><button id="drawchartt" value="<%=pair%>" onclick="getSparePartsAdditionHorizontalBar2()"><%=pair%></button></th>
                    <th><%=df.format(value)%></th>
                </tr>
                <%}%>
                <tr>
                    <form action="PaginationServlet" method="get">
                    <th><input type="submit" name="button1" value="▲" required="required">
                        <input type="submit" name="button2" value="▼" required="required"></th>
                        <input class="search" name="page" type="hidden" value="<%=len%>">
                    <th><input class="jump" name="page" type="text" value="<%=len%>"></th>
                    </form>
                </tr>
            </table>
        </div>
    </div>
    </div>
<!-----------------Script for chart when login-------------->
    <script>
    function getSparePartsAdditionHorizontalBar2(pair) {
        var HttpClient = function() {
            this.get = function(aUrl, aCallback) {
                var anHttpRequest = new XMLHttpRequest();
                anHttpRequest.onreadystatechange = function() {
                    if (anHttpRequest.readyState == 4
                        && anHttpRequest.status == 200)
                        aCallback(anHttpRequest.responseText);
                }

                anHttpRequest.open("GET", aUrl, true);
                anHttpRequest.send(null);
            }
        }
        aClient = new HttpClient();
        var url = "jsonServlet1?op="+select2()+"&day="+7;
        aClient.get(url, function(response) {
            var values = eval('(' + response + ')');
            var x = Array();
            var y = Array();
            for (i=0;i<values.length;i++) {
                // xAxis.push(values[key]['period']);
                x.push(values[i]["Day"]);
                y.push(values[i]["val"]);
            }
            createChart(x,y);
        });
    }
    </script>
    <script type="text/javascript">
    function createChart(x,y) {
        // based on prepared DOM, initialize echarts instance
        var myChart = echarts.init(document.getElementById('diagramaa1'));

        // specify chart configuration item and data
        var option = {
            tooltip : {
                trigger: 'axis'
            },
            toolbox: {
                show : true,
                feature : {
                    // mark : {show: true},
                    magicType : {show: true, type: ['radar','line', 'bar']},
                }
            },
            tooltip: {},
            xAxis: {
                data:x,
            },
            yAxis: {},
            series: [{
                // name: 'Value'+' '+select1(),
                type: 'line',
                data: y,
            }]
        };

        // use configuration item and data specified to show chart
        myChart.setOption(option);
    }
    </script>

<!-------------------------------------------->
    <%}%>
<!------------------Script for table sort------------------------->
<script>
    function sortTableAscending() {
        var table, rows, switching, i, x, y, shouldSwitch;
        table = document.getElementById("currencyTable");
        switching = true;
        while (switching) {
            switching = false;
            rows = table.getElementsByTagName("TR");
            for (i = 1; i < (rows.length - 1); i++) {
                shouldSwitch = false;
                x = rows[i].getElementsByTagName("TH")[0];
                y = rows[i + 1].getElementsByTagName("TH")[0];
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    shouldSwitch= true;
                    break;
                }
            }
            if (shouldSwitch) {
                /*If a switch has been marked, make the switch
                 and mark that a switch has been done:*/
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
            }
        }
    }
    function sortTableDescending() {
        var table, rows, switching, i, x, y, shouldSwitch;
        table = document.getElementById("currencyTable");
        switching = true;
        while (switching) {
            switching = false;
            rows = table.getElementsByTagName("TR");
            for (i = 1; i < (rows.length - 1); i++) {
                shouldSwitch = false;
                x = rows[i].getElementsByTagName("TH")[0];
                y = rows[i + 1].getElementsByTagName("TH")[0];
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    shouldSwitch= true;
                    break;
                }
            }
            if (shouldSwitch) {
                /*If a switch has been marked, make the switch
                 and mark that a switch has been done:*/
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
            }
        }
    }
</script>
<!------------------------------------------------------>
<!--------------Script for select pair for chart-------------->
<script>
    function select1() {
        var x = document.getElementById("drawchart").value;
        console.log(x);
        return x;
    }
    function select2() {
        var x = document.getElementById("drawchartt").value;
        console.log(x);
        return x;
    }
</script>
<!------------------------------------------------------------------>
</body>
</html>
