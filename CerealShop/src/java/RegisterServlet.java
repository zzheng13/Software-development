/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 *
 * @author zimozheng
 */
public class RegisterServlet extends HttpServlet {
    int count = 0;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            
            //request.getRequestDispatcher("link.html").include(request, response);
            PrintWriter out = response.getWriter();
            String name=request.getParameter("name");
            String password=request.getParameter("password");
            String address=request.getParameter("address");
            String email=request.getParameter("email");
            String CC=request.getParameter("CC");
            String htmlResultStr;
            
            if(name.equals("")||password.equals("")||address.equals("")||email.equals("")||CC.equals("")||!email.contains("@")){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Invalid information</title>");            
                out.println("</head>");
                out.println("<body>");

                out.println("<h1> Invalid info! </h1>");
                out.println("<a href=\"register.html\"><button>Go Back</button></a>");
                out.println("</body>");
                out.println("</html>");
            }else{  
 
                
            com.mysql.jdbc.Connection connRemote = null;
            Properties props;
            
            com.mysql.jdbc.Driver myDriver;
            String dbLocal;
            String dbUrlRemote;
            ResultSet resultSet = null;
            String rs;
            Random rand = new Random();
            count = rand.nextInt(50) + 1;
            props = new Properties();
            
            props.put("user", "user46");
            props.put("password", "199611");
            
            myDriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(myDriver);
            int op3 = 0;
            
            
            try{
                     op3 = Integer.parseInt(CC);
                     String me = "select * from registration where email ="+"'"+ email+"'";
                
      
                dbUrlRemote = "jdbc:mysql://sql.cs.usfca.edu:3306/user46";
                
                
                connRemote = (com.mysql.jdbc.Connection) DriverManager.getConnection(dbUrlRemote, props);
                
                   
                com.mysql.jdbc.Statement stat = (com.mysql.jdbc.Statement) connRemote.createStatement();
                
                //stat.execute("insert into test1 values('lkkk',22)");
                //stat.execute("select * from test1");
                boolean a =stat.execute(me);
                resultSet = stat.getResultSet();
                
                                if (resultSet.isBeforeFirst() ) {    
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Invalid information</title>");            
                    out.println("</head>");
                    out.println("<body>");

                    out.println("<h1> Sorry this email has already been used. </h1>");
                    out.println("<a href=\"register.html\"><button>Go Back</button></a>");
                    out.println("</body>");
                    out.println("</html>");
                } 
                                    
                                    
                                     String que = "insert into registration values ("+"'"+name+"',"+"'"+password+"',"+"'"+address+"',"+"'"+email+"',"+ op3+","+count+")";
           // String que = "insert into registration values ('hlsdj','asdfs','asdf','sdfsdf',2342,2342)";
            //stat.execute("insert into test1 values('lkkk',22)");
            dbUrlRemote = "jdbc:mysql://sql.cs.usfca.edu:3306/user46";
            
            
            connRemote = (com.mysql.jdbc.Connection) DriverManager.getConnection(dbUrlRemote, props);
            
            
            //com.mysql.jdbc.Statement stat = (com.mysql.jdbc.Statement) connRemote.createStatement();
            
            //stat.execute("insert into test1 values('lkkk',22)");
            //stat.execute("select * from test1");
            boolean b =stat.execute(que);
             out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Invalid information</title>");            
                    out.println("</head>");
                    out.println("<body>");

                    out.println("<h1> You have successfully registered! </h1>");
                    out.println("<a href=\"index.html\"><button>Go Back</button></a>");
                    out.println("</body>");
                    out.println("</html>");                       
                                    
                                
                                
                                
                                
            
                }catch (NumberFormatException ex) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Invalid information</title>");            
                    out.println("</head>");
                    out.println("<body>");

                    out.println("<h1> Please input 4 digit integers for CC! </h1>");
                    out.println("<a href=\"register.html\"><button>Go Back</button></a>");
                    out.println("</body>");
                    out.println("</html>");
                    }
            
            
            
            }
            
            
           
        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
