package com.ymens.servlet;


import com.ymens.hibernate.PrintAuthor;
import com.ymens.dao.AddAuthorDao;
import com.ymens.dao.AddBookDao;
import com.ymens.dao.ManageBook;
import com.ymens.hibernate.Books;
import com.ymens.hibernate.HbUtil;
import com.ymens.spring.beans.Author;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

public class ManageBookServlet extends HttpServlet  {
    private static SessionFactory factory;
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
    byte[] userimage=null;
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        factory = HbUtil.getSessionFactory();
        ManageBook ME = new ManageBook();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        id_author = 0;
        Author author = new Author();
        AddBookDao addbook = new AddBookDao();
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        long sizeInBytes = 0;
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
                    sizeInBytes = fi.getSize();
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
            author = PrintAuthor.getDetailsAuthors(id_author);
            int i = (int) sizeInBytes;
            ByteBuffer buffer = ByteBuffer.allocate(4);
            buffer.putInt(i);
            byte[] bytes = buffer.array();
            Books b = new Books(n, id_author,isbn, price, description, bytes);

            id_author = AddAuthorDao.getIdAuthor(CNP);
            if(ME.addBook(n, id_author, isbn, price, description, bytes)!= 0){
                RequestDispatcher rd = request.getRequestDispatcher("/selectbooksadminServlet");
                rd.forward(request, response);
            }else{
                RequestDispatcher rd = request.getRequestDispatcher("addbook.jsp");
                out.println("<font color=red>Please fill all the fields</font>");
                rd.include(request, response);
            }

        }else{
            RequestDispatcher rd = request.getRequestDispatcher("/addauthor.jsp");
            rd.forward(request, response);
        }
    }
}


