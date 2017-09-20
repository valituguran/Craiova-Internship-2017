package com.ymens.spring.manager;

import com.ymens.spring.beans.Order;
import com.ymens.spring.beans.OrderItem;
import com.ymens.spring.beans.User;
import com.ymens.spring.dao.OrderDao;
import com.ymens.spring.dao.OrderItemDao;
import com.ymens.spring.dao.UserDao;
import com.ymens.spring.dao.UserTypeDao;
import com.ymens.spring.interfaces.IAuthor;
import com.ymens.spring.interfaces.IBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AccountDetails extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //public LinkedList list = new LinkedList();
    public User user;
    @Autowired
    IBook bookDao ;
    @Autowired
    IAuthor authorDao ;
    @Autowired
    UserDao userDao ;
    @Autowired
    UserTypeDao userTypeDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderItemDao orderItemDao;
    protected AutowireCapableBeanFactory ctx;
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        ctx = ((ApplicationContext) getServletContext().getAttribute(
                "applicationContext")).getAutowireCapableBeanFactory();

        bookDao= (IBook) ctx.getBean("booksDao");
        authorDao = (IAuthor) ctx.getBean("authorsDao");
        userTypeDao = (UserTypeDao) ctx.getBean("userTypeDao");
        orderDao = (OrderDao) ctx.getBean("orderDao");
        orderItemDao = (OrderItemDao) ctx.getBean("orderItemDao");

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        response.setContentType("text/html");
        String n = (String) session.getAttribute("name");
        String p = (String) session.getAttribute("password");
        UserDao userDao = new UserDao();
        user = userDao.getUser(n, p);
        user.setUsername(n);
        user.setPassword(p);
        String type = request.getParameter("type");
        if (type.equals("accountdetails")){
            session.setAttribute("type", type);
            session.setAttribute("currentuser", user);
        } else if(type.equals("myorders")) {
            List<Order> list = orderDao.getOrders(user.getUsername(), user.getPassword());
            session.setAttribute("type", type);
            session.setAttribute("orders", list);
            String string, str;
            int book_id;
            double price;
            Pagination ps = new Pagination();
            ps.UpdateCurrentPage(1);
            for (int i = 0; i < list.size(); i++) {
                string = list.get(i).toString();
                price = orderDao.getTotalPrice(string);
                str = String.format("%s%d", "orders", i);
                List<OrderItem> list1 = orderItemDao.getOrderItems(string);
                session.setAttribute(str, list1);
                session.setAttribute("price" + str, price);
            }
        }

        if (userTypeDao.getType(user.getUsername(), user.getPassword()).equalsIgnoreCase("user")) {
            getServletContext().getRequestDispatcher("/mycont_user.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/mycont_admin.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);


    }





}
