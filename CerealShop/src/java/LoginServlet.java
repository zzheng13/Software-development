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
import javax.servlet.http.HttpSession;  
public class LoginServlet extends HttpServlet {  
    
    
    static int cou =0;
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                    throws ServletException, IOException {  
        try {
            
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
            request.getRequestDispatcher("link.html").include(request, response);
            
            //String name=request.getParameter("name");
            String email=request.getParameter("email");
            String password=request.getParameter("password");
            
            
            com.mysql.jdbc.Connection connRemote = null;
            Properties props;
            
            com.mysql.jdbc.Driver myDriver;
            String dbLocal;
            String dbUrlRemote;
            ResultSet resultSet = null;
            String rs;
            
            props = new Properties();
            
            props.put("user", "user46");
            props.put("password", "199611");
            
            myDriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(myDriver);
            String que = "select * from registration where email ="+"'"+ email+"'";
            
            
            dbUrlRemote = "jdbc:mysql://sql.cs.usfca.edu:3306/user46";
            
            
            connRemote = (com.mysql.jdbc.Connection) DriverManager.getConnection(dbUrlRemote, props);
            
            
            com.mysql.jdbc.Statement stat = (com.mysql.jdbc.Statement) connRemote.createStatement();
            
            //stat.execute("insert into test1 values('lkkk',22)");
            //stat.execute("select * from test1");
            boolean a =stat.execute(que);
            resultSet = stat.getResultSet();
            
            if (!resultSet.isBeforeFirst() ) {    
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Invalid information</title>");            
                    out.println("</head>");
                    out.println("<body>");

                    out.println("<h1> Invalid info! </h1>");
                    out.println("<a href=\"login.html\"><button>Go Back</button></a>");
                    out.println("</body>");
                    out.println("</html>");
                } 
            
            while(resultSet.next()){
                if(resultSet.getString("password").equals(password)){
                    cou =1;
                out.print("Welcome, "+email);
                HttpSession session=request.getSession();
                session.setAttribute("email",email);
                session.setMaxInactiveInterval(120);
                
                }  
            }
            
            
            
            if(cou == 0){
                out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Invalid information</title>");            
                    out.println("</head>");
                    out.println("<body>");

                    out.println("<h1> Sorry! Something wrong with your username or password. </h1>");
                    out.println("<a href=\"login.html\"><button>Go Back</button></a>");
                    out.println("</body>");
                    out.println("</html>");
            }
            
            
          
           
            
            
              
            //out.print("Time out, please log in again.");  
              
            out.close();  
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}  
