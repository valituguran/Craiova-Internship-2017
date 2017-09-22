<!--------------Imports--------------->
<%@ page import="dao.ParseDao" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.UserDao" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!---------------------------------------->
<html>
<head>
    <title>History</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="../StyleSheet/homeStyle.css">
    <link rel="stylesheet" type="text/css" href="../StyleSheet/myAccountStyle.css">
    <link rel="stylesheet" type="text/css" href="../StyleSheet/historyStyle.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/echarts/3.7.1/echarts.min.js"></script>
</head>
<body onload="getSparePartsAdditionHorizontalBar1();getSparePartsAdditionHorizontalBar2()">
<!--Java include needed-->
<%
    ParseDao.getcurrency();
    LinkedList list =   ParseDao.pairs;
    UserDao user = new UserDao();
    DecimalFormat df = new DecimalFormat("0.0000");%>
<!----------------->
    <%if(session.getAttribute("name")!=null){%>
        <div class="topnav">
            <a class="active" href="home.jsp">Ccy Xcg </a>
            <a href="Currency.jsp">Currencies Shop</a>
            <a href="History.jsp">History</a>
            <a href="myAccount.jsp" id="nameandbalance">Hello <%=user.username%> <br> Balance:<%=df.format(user.balance)%><%=user.currency%> </a>
            <div id="logoutt">
                <form action="logoutServlet" method="post">
                <a> <input type="submit" id="logout" value="Logout" /></a>
                </form>
            </div>
        </div>
        <div class="oneselect">
            <select id="select" name="firstselect">
                <%for (int i=0;i<list.size();i++){%>
                    <%String pair = (String)list.get(i);%>
                     <option id="valueid" value="<%=pair%>"><%=pair%></option>
             <%}%>
            </select>
            <select id="day" class="firstdayselect">
                    <option id="dayid" value=3>Last 3 days</option>
                    <option id="dayid" value=7>Last 7 days</option>
                    <option id="dayid" value=14>Last 14 days</option>
                    <option id="dayid" value=30>Last 30 days</option>
            </select>
            <div id="diagrama" style="width: 600px;height:400px;"></div>
        </div>
        <div class="twoselect">
            <select id="secondselect" class="secondselect">
            <%for (int i=0;i<list.size();i++){%>
            <%String pair = (String)list.get(i);%>
            <option id="secondvalueid" value="<%=pair%>"><%=pair%></option>
            <%}%>
            </select>
            <select id="day1" class="seconddayselect">
                <option id="dayid1" value=3>Last 3 days</option>
                <option id="dayid1" value=7>Last 7 days</option>
                <option id="dayid1" value=14>Last 14 days</option>
                <option id="dayid1" value=30>Last 30 days</option>
            </select>
            <div id="diagrama2" style="width: 600px;height:400px;"></div>
        </div>
        <%} else if(session.getAttribute("name")==null){%>

        <a>Please login to acces the history page</a>
        <a href="login.jsp">Login</a>
        <%}%>
<!----------------------------------Script for chart1-->
    <script>
        function getSparePartsAdditionHorizontalBar1( ) {
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
            var url = "jSonServlet?op="+select1()+"&day="+selectday1();
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
               name: 'Value'+' '+select1(),
               type: 'bar',
               data: y,
           }]
       };
       // use configuration item and data specified to show chart
       myChart.setOption(option);
   }
    </script>
<!-------------------------------->
<!---------Script for chart2---------------->
    <script>
        function getSparePartsAdditionHorizontalBar2( ) {
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
            var url ="jSonServlet1?param="+select2()+"&day1="+selectday2();
            aClient.get(url, function(response) {
            var values = eval('(' + response + ')');
            var x = Array();
            var y = Array();
            for (i=0;i<values.length;i++) {
                // xAxis.push(values[key]['period']);
                x.push(values[i]["Day"]);
                y.push(values[i]["val"]);
                }
            createChart2(x,y);
            });
        }
    </script>
    <script type="text/javascript">
        function createChart2(x,y) {
        // based on prepared DOM, initialize echarts instance
            var myChart = echarts.init(document.getElementById('diagrama2'));
        // specify chart configuration item and data
            var option = {
            tooltip: {},
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    magicType : {show: true, type: ['radar','line', 'bar']},
                }
            },
            xAxis: {
                data: x,
            },
            yAxis: {},
            series: [{
                name: 'Value'+select2(),
                type: 'line',
                data: y,
            }]
        };
        // use configuration item and data specified to show chart
        myChart.setOption(option);
    }
    </script>
<!----------------------------------->
<!---------------Scripts for select-------------->
<script>
    function select1() {
        var x = document.getElementById("select").value;
        console.log(x);
        return x;
    }
    function selectday1() {
        var x = document.getElementById("day").value;
        console.log(x);
        return x;
    }
    function selectday2() {
        var x = document.getElementById("day1").value;
        console.log(x);
        return x;
    }

    function select2() {
        var x = document.getElementById("secondselect").value;
        console.log(x);
        return x;
    }
    $(document).on("click", "#valueid", function () { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
        getSparePartsAdditionHorizontalBar1();
    });
    $(document).on("click", "#dayid", function () { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
        getSparePartsAdditionHorizontalBar1();
    });
    $(document).on("click", "#dayid1", function () { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
        getSparePartsAdditionHorizontalBar2();
    });
    $(document).on("click", "#secondvalueid", function () { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
        getSparePartsAdditionHorizontalBar2();
    });
</script>
<!--------------------------------------->
</body>
</html>
