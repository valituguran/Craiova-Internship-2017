package com.ymens.servlet;

import com.ymens.dao.AddAuthorDao;
import com.ymens.dao.AddBookDao;
import com.ymens.hibernate.Books;
import com.ymens.hibernate.PrintAuthor;
import com.ymens.spring.beans.Author;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by madalina.luca on 8/4/2017.
 */
public class AddBookServlet extends HttpServlet{

    private File file ;

    private static final long serialVersionUID = 1L;
    public static String text;
    public static String n;
    public static String cnp;
    public static String isbnString;
    public static String priceString;
    public static String description;
    public  static String image;
    public static long CNP = 0;
    public static long isbn = 0;
    public static double price = 0.0;
    public static int id_author;
    public static  String path=null;
    public static byte[] bytes ;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
//        HttpSession session = request.getSession(false);
        id_author = 0;
        Author author = new Author();
        AddBookDao addbook = new AddBookDao();
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        String imageStr = null;

        try {
            // Parse the request to get file items.
            List formFields = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = formFields.iterator();

            while ( i.hasNext () ) {
                FileItem fi = (FileItem)i.next();
                if ( !fi.isFormField () ) {
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();
                    imageStr = new String(fi.get());
                    File temp = File.createTempFile("image", ".png");
                    path = temp.getAbsolutePath();
                    fi.write(temp);
                    System.out.println(fi.get());
                } else {
                    switch (fi.getFieldName()) {
                        case "name":
                            n = fi.getString();
                            break;
                        case "cnp":
                            cnp = fi.getString();
                            break;
                        case "isbn":
                            isbnString = fi.getString();
                            break;
                        case "price":
                            priceString = fi.getString();
                            break;
                        case "description":
                            description = fi.getString();
                            break;
                        default:
                            System.err.println("Unexpected field " + fi.getFieldName());
                            break;
                    }
                }
            }
        } catch(Exception ex) {
            System.out.println(ex);
        }
        try {
            CNP = Long.parseLong(cnp);
            isbn = Long.parseLong(isbnString);
            price = Double.parseDouble(priceString);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        if (AddAuthorDao.getIdAuthor(CNP) != 0){
            id_author = AddAuthorDao.getIdAuthor(CNP);
             author = PrintAuthor.getDetails(id_author);
              bytes = imageStr.getBytes();
            Books b = new Books(n, id_author,isbn, price, description, bytes);
            try{
                if(addbook.addBook(b, CNP) == 1){
                    RequestDispatcher rd = request.getRequestDispatcher("/selectbooksadminServlet");
                    rd.forward(request, response);
                }else{
                    RequestDispatcher rd = request.getRequestDispatcher("addbook.jsp");
                    out.println("<font color=red>Please fill all the fields</font>");
                    rd.include(request, response);
                }
            }catch (SQLException e1) {
                e1.printStackTrace();
            }
        }else{
            RequestDispatcher rd = request.getRequestDispatcher("/addauthor.jsp");
            rd.forward(request, response);
        }
    }
}
