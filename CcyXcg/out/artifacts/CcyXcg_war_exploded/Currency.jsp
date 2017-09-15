<!--Imports---------------------------->
<%@ page import="java.util.LinkedList" %>
<%@ page import="dao.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.ymens.ShopDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--------------------------------->
<html>
<head>
    <title>Ccy Xcg</title>
    <link rel="stylesheet" type="text/css" href="../StyleSheet/homeStyle.css">
    <link rel="stylesheet" type="text/css" href="../StyleSheet/myAccountStyle.css">
</head>
<body>
<!--Java declaration needed-->
<%
    String name= (String) session.getAttribute("name");
    String password= (String) session.getAttribute("password");
    ParseDao.getcurrency();
    UserDao user = new UserDao();
    DecimalFormat df = new DecimalFormat("0.0000");
    SearchCurrencyDao object = new SearchCurrencyDao();
    object.searchPair(UserDao.currency);
    int len=1;
%>
<!------------------------------>
<%if(session.getAttribute("name")==null) {%>
<a>You need to be </a><a href="login.jsp">Login!</a>
<%}else if(session.getAttribute("name")!=null){%>
    <div class="topnav">
        <a class="active" href="home.jsp">Ccy Xcg </a>
        <a href="home.jsp">Currencies</a>
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
        <div id="converter">
            <script src="https://www.transfermate.com/en/exchange_rates_api.asp?csel=,&cshort=" type="text/javascript"></script>
        </div>
        <%if(session.getAttribute("page")==null)
        {
            len=1;
        }
        else{
            len = (int) session.getAttribute("page");
        }
        %>
        <div class="table-div">
        <table class="tablee " frame="box"  id="currencyTable" style="height:500px;overflow:auto;">
            <col span="1" width="300">
            <tr>
                <th>Pair name<a onclick="sortTableAscending()">▲</a><a onclick="sortTableDescending()">▼</a> </th>
                <th>Value</th>
                <th>Quantity</th>
                <th>Actions</th>
            </tr>
            <input type="hidden" name="quantity" value=1>
            <%for (int i = PaginationDao.paginationCurrency(len-1); i< PaginationDao.paginationCurrency(len); i++){%>
            <%String pair = (String) object.list1.get(i);
                Double value = (Double) object.list2.get(i);%>
                <form method="get" action="shopServlet">
                <tr>
                    <input type="hidden" name="curren" value="<%=pair%>"><th><%=pair%></th>
                    <input type="hidden" name="valoare" value="<%=value%>"><th><%=df.format(value)%></th>
                    <th><input type="text" name="quantity" value=1></th>
                    <th><input type="submit" value="Buy"></th>
                </tr>
                </form>
                <%}%>
            <tr>
                <form action="PaginationServlet" method="get">
                    <th><input type="submit" name="buttonCurrency1" value="▲" required="required">
                        <input type="submit" name="buttonCurrency2" value="▼" required="required"></th>
                    <th><input class="search" name="page" type="text" value="<%=len%>"></th>
                </form>
            </tr>
        </table>
        <%int quantity= (int) session.getAttribute("quantity");
            String pairname= (String) session.getAttribute("curren");
            BuyDao.modify(user.getID(name,password), quantity,pairname);%>
    </div>
    </div>
    </div>
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
        <%}%>
</body>
</html>
