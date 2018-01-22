<!-----------------------------Java Imports----------------->
<%@ page import="java.util.LinkedList" %>
<%@ page import="dao.ParseDao" %>
<%@ page import="com.ymens.Parse" %>
<%@ page import="dao.SearchDao" %>
<%@ page import="com.ymens.SearchServlet" %>
<%@ page import="dao.PaginationDao" %>
<%@ page import="dao.UserDao" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!------------------------------------------------------------->
<html>
<head>
  <title>Ccy Xcg</title>
  <link rel="stylesheet" type="text/css" href="../StyleSheet/homeStyle.css">
  <link rel="stylesheet" type="text/css" href="../StyleSheet/myAccountStyle.css">
  <link rel="stylesheet" type="text/css" href="E:\workspace\Craiova-Internship-2017\CcyXcg\web\StyleSheet\tableStyleSheet.css">
</head>
<body>
  <%String name= (String) session.getAttribute("name");
    ParseDao.getcurrency();
    LinkedList pairs =   ParseDao.pairs;
    LinkedList values = ParseDao.values;
    UserDao user = new UserDao();
    int lensearch = (int) session.getAttribute("lenght");
    DecimalFormat df = new DecimalFormat("0.0000");
  %>
  <%if(session.getAttribute("name")!=null){%>
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
    <%
      int len;//len este indexul paginii
      if(session.getAttribute("page")==null)
        {
          len=1;
        }
      else{
          len = (int) session.getAttribute("page");
        }
    %>
    <div class="table-div">
      <table class="tablesearch" frame="box">
          <tr>
            <th>Pair name</th>
            <th>Value</th>
          </tr>
      <%if (lensearch >0 || lensearch==0){
          for (int i = PaginationDao.paginationIndex(len-1); i< PaginationDao.paginationIndex(len); i++){%>
          <tr>
            <th><%=SearchDao.list1.get(i)%></th>
            <th><%=SearchDao.list2.get(i)%></th>
          </tr>
          <%}%>
          <tr>
          <form action="PaginationServlet" method="get">
            <th><input type="submit" name="buttonIndex1" value="▲" />
              <input type="submit" name="buttonIndex2" value="▼"/></th>
            <th><input class="search" name="page" type="text" value=<%=len%>></th>
          </form>
        </tr>
      </table>
        <%}%>
      <!------------------------------------------------------------------------------------------------------------>

      </div>
    </div>
    <%}else if(session.getAttribute("name")==null){%>
    <div class="topnav">
      <a class="active" href="home.jsp">Ccy Xcg </a>
      <a href="home.jsp">Currencies</a>
      <a href="History.jsp">History</a>
      <a href="login.jsp">Login</a>
    </div>
      <div id="searchandtable">
          <div class="filterbox">
              <div >Filter</div>
              <form action="searchServlet" method="get">
                  <input class="search" name="search" type="text" placeholder="Pair name">
                  <input type="submit" value="Search">
              </form>
              <%
                  int len;//len este indexul paginii
                  if(session.getAttribute("page")==null)
                  {
                      len=1;
                  }
                  else{
                      len = (int) session.getAttribute("page");
                  }
              %>
              <div class="table-div">
                  <table class="tablesearch" frame="box">
                      <tr>
                          <th>Pair name</th>
                          <th>Value</th>
                      </tr>
                      <%if (lensearch >0 || lensearch==0){
                          for (int i = PaginationDao.paginationIndex(len-1); i< PaginationDao.paginationIndex(len); i++){%>
                      <tr>
                          <th><%=SearchDao.list1.get(i)%></th>
                          <th><%=SearchDao.list2.get(i)%></th>
                      </tr>
                      <%}%>
                      <tr>
                          <form action="PaginationServlet" method="get">
                              <th><input type="submit" name="buttonIndex1" value="▲" />
                                  <input type="submit" name="buttonIndex2" value="▼"/></th>
                              <th><input class="search" name="page" type="text" value=<%=len%>></th>
                          </form>
                      </tr>
                  </table>
                  <%}%>

                  <%if(session.getAttribute()){%>
                  <p id="notexist">Pair doesn`t exist</p>
                  <%}%>
                  <!------------------------------------------------------------------------------------------------------------>

              </div>
          </div>
  </div>
  <%}%>
</body>
</html>
