<%@ page import="dao.UserDao" %>
<%@ page import="dao.ActionsDao" %>
<%@ page import="dao.BuyDao" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="dao.PaginationDao" %><%--
  Created by IntelliJ IDEA.
  User: lucian.Nicolescu
  Date: 9/8/2017
  Time: 3:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%UserDao user= new UserDao();
        int len;
    %>
    <title><%=user.name%></title>
    <link rel="stylesheet" type="text/css" href="../StyleSheet/myAccountStyle.css">

</head>
<body>
    <%DecimalFormat df = new DecimalFormat("0.0000");%>
    <div class="topnav">
        <a class="active" href="home.jsp">Ccy Xcg </a>
        <a href="home.jsp">Currencies</a>
        <a href="History.jsp">History</a>
        <a id="nameandbalance">Hello <%=user.username%> <br> Balance:<%=df.format(user.balance)%><%=user.currency%> </a>
       <div id="logoutt">
        <form action="logoutServlet" method="post">
            <a>  <input type="submit" id="logout" value="Logout" />   </a>
        </form>
       </div>
    </div>
    <div id="detailsandtransaction">
    <div id="accountdetails">
        <table id="details">
            <tr>
                <th>Account Details</th>
            </tr>
            <tr>
                <td>Full Name:<%=user.name%></td>
            </tr>
            <tr>
                <td>Username:<%=user.username%></td>
            </tr>
            <tr>
                <td>Email:<%=user.email%></td>
            </tr>
            <tr>
                <td>Balance:<%=user.balance%></td>
            </tr>
            <tr>
                <td>Currency:<%=user.currency%></td>
            </tr>
        </table>
    </div>
    <div id="tabletransaction">
        <%if(session.getAttribute("page")==null)
        {
            len=1;
        }
        else{
            len = (int) session.getAttribute("page");
        }
        %>
        <table class="tablee " frame="box"  id="currencyTable">
        <tr>
            <th>My Transaction</th>
            <th>Value Buy</th>
            <th>Actual Value</th>
            <th>Total</th>
            <th>Quantity</th>
            <th>Actions</th>
        </tr>
        <%int id=UserDao.getID((String)session.getAttribute("name"),(String) session.getAttribute("password"));
            ActionsDao.getShopping(id);
            BuyDao buy = new BuyDao();
        %>
        <input type="hidden" name="quantity" value=1>
        <%for(int i = PaginationDao.pagination(len-1); i< PaginationDao.pagination(len); i++){%>
            <%String pair = (String) ActionsDao.list1.get(i);
              Double value = (Double) ActionsDao.list2.get(i);%>
        <form method="get" action="sellServlet">
        <tr>
            <input type="hidden" name="pairtosell" value="<%=pair%>"><th><%=pair%></th>
            <input type="hidden" name="valuetosell" value="<%=value%>"><th><%=df.format(value)%></th>
            <th><%=df.format(value)%></th>
            <th><%=df.format(((buy.selectquantity(pair,id)*value)))%></th>
            <th><input type="text" name="quant" value=<%=buy.selectquantity(pair,id)%>></th>
            <th><input type="submit" value="Sell"></th>
        </tr>
        </form>

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
</body>
</html>
