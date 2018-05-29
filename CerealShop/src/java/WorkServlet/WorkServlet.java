package WorkServlet;
/**
 * <pre>
 * author:      rrs
 * usage:
 *
 * description:
 *              This is a server-side online adder.
 *              Please input two floating point number.
 *              The data will be transmitted to servlet on the server.
 *              The servlet on teh server will compute the answer.
 *           
 *
 * created
 *              2015/07/12
 * works:
 *              2015/07/14
 *              2017/05/04   NetBeans 8.0.2
 * BUGS: none known
 *
 */
import static java.lang.System.out;
import java.beans.Statement;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author rrsuser
 */
public class WorkServlet extends HttpServlet
{
    //new debug for Servlet.
    static final boolean noteEn = true;
    static final boolean warningEn = true;
    static final boolean errorEn = true;
    private final boolean enabScreen = true;
    //context section
    //private ServletContext myContext;
    //serializable section
    static public final long serialVersionUID = 277001234145L;
    /**
     * <pre>
     * Initialize stuff.
     * </pre>
     */
    @Override
    public void init(ServletConfig config) throws ServletException
    {
        //Must call exactly super.init(config); as the FIRST thing.
        super.init(config);
    }
    /**
     * <pre>
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        com.mysql.jdbc.Connection connRemote = null;
        Properties props;

        com.mysql.jdbc.Driver myDriver;
        String dbLocal;
        String dbUrlRemote;
        ResultSet resultSet = null;
        String rs;
        writeNote("processRequest");
        String op1Str;
        String op2Str;
        String op3Str;
        String op4Str;
        String op5Str;
        String op6Str;
        String htmlResultStr;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            op1Str = request.getParameter("op1");
            op1Str = checkInputValid(op1Str);
            op2Str = request.getParameter("op2");
            op2Str = checkInputValid(op2Str);           
            op3Str = request.getParameter("op3");
            op3Str = checkInputValid(op3Str);        
            op4Str = request.getParameter("op4");
            op4Str = checkInputValid(op4Str);          
            op5Str = request.getParameter("op5");
            op5Str = checkInputValid(op5Str);
            op6Str = request.getParameter("op6");
            op6Str = checkInputValid(op6Str);
            String selected_valuef = request.getParameter("valuef") ;
            String selected_values = request.getParameter("values") ;
            String selected_valuet = request.getParameter("valuet") ;
            String act = request.getParameter("addButt");
                if (act == null) {
                //no button has been selected
                } else if (act.equals("submitB")) {
                    
                    
                    
             if (request.getRequestedSessionId() != null&& !request.isRequestedSessionIdValid()) {
                out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Invalid information</title>");            
                    out.println("</head>");
                    out.println("<body>");

                    out.println("<h1> Sorry! Your session has expired, please login again. </h1>");
                    out.println("<a href=\"login.html\"><button>Go Back</button></a>");
                    out.println("</body>");
                    out.println("</html>");
                    response.sendRedirect("login.html");
            }

                   if(op1Str.equals("")){
                    printI(response);
                   }else{
                          try
            {
                String que = "select * from cereal where brand ="+"'"+ op1Str+"'";
                com.mysql.jdbc.Statement stat = connect(que);
                boolean a =stat.execute(que);
                resultSet = stat.getResultSet();
                printT(resultSet,response);
                                
            } catch (Exception ex)
            {
                ex.printStackTrace();
                printV(response);
            }
                   }
                } else if (act.equals("submitN")) {
                    if (request.getRequestedSessionId() != null&& !request.isRequestedSessionIdValid()) {
                out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Invalid information</title>");            
                    out.println("</head>");
                    out.println("<body>");

                    out.println("<h1> Sorry! Your session has expired, please login again. </h1>");
                    out.println("<a href=\"login.html\"><button>Go Back</button></a>");
                    out.println("</body>");
                    out.println("</html>");
                    response.sendRedirect("login.html");
            }

                    if(op2Str.equals("")){
                       printI(response);
                   }else{
                          try
            {
                String que = "select * from cereal where name ="+"'"+ op2Str+"'";
                com.mysql.jdbc.Statement stat = connect(que);
                boolean a = stat.execute(que);
                resultSet = stat.getResultSet();
                printT(resultSet,response);
                
                } catch (Exception ex)
            {
                ex.printStackTrace();
                printV(response);
            }
                   }
                //update button was pressed
                } else if (act.equals("submitE")) {
                //someone has altered the HTML and sent a different value!
                if (request.getRequestedSessionId() != null&& !request.isRequestedSessionIdValid()) {
                out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Invalid information</title>");            
                    out.println("</head>");
                    out.println("<body>");

                    out.println("<h1> Sorry! Your session has expired, please login again. </h1>");
                    out.println("<a href=\"login.html\"><button>Go Back</button></a>");
                    out.println("</body>");
                    out.println("</html>");
                    response.sendRedirect("login.html");
            }

                    if(op5Str.equals("")){
                        printI(response);
                   }else{
                          try
            {
                
                String que = "select * from cereal where expiredate ="+"'"+ op5Str+"'";
                com.mysql.jdbc.Statement stat = connect(que);
                boolean a = stat.execute(que);
                resultSet = stat.getResultSet();
                
                printT(resultSet,response);
            } catch (Exception ex)
            {
                ex.printStackTrace();
                printV(response);
            }
                   }
                }else if (act.equals("submitS")){
                    //someone has altered the HTML and sent a different value!
                if (request.getRequestedSessionId() != null&& !request.isRequestedSessionIdValid()) {
                out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Invalid information</title>");            
                    out.println("</head>");
                    out.println("<body>");

                    out.println("<h1> Sorry! Your session has expired, please login again. </h1>");
                    out.println("<a href=\"login.html\"><button>Go Back</button></a>");
                    out.println("</body>");
                    out.println("</html>");
                    response.sendRedirect("login.html");
            }

                    if(op3Str.equals("")){
                        printI(response);
                   }else{
                          try
            { 
                int op3;
                op3 = Integer.valueOf(op3Str);
                String que;
                String usf1,usf2;
                usf1 = Integer.toString(op3+1);
                usf2 = Integer.toString(op3-1);
                if(selected_valuef.equals("value1")){
                    que = "select * from cereal where sugargram >="+ op3Str;
                }else if (selected_valuef.equals("value2")){
                    que = "select * from cereal where sugargram ="+ "'"+op3Str+"'";
                }else{
                    que = "select * from cereal where sugargram <="+ op3Str;
                }
                com.mysql.jdbc.Statement stat = connect(que);
                
                boolean a = stat.execute(que);
                resultSet = stat.getResultSet();
                
                printT(resultSet,response);
            } catch (Exception ex)
            {
                 printV(response);
            }
                   }
                }else if (act.equals("submitF")){
                    //someone has altered the HTML and sent a different value!
                if (request.getRequestedSessionId() != null&& !request.isRequestedSessionIdValid()) {
                out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Invalid information</title>");            
                    out.println("</head>");
                    out.println("<body>");

                    out.println("<h1> Sorry! Your session has expired, please login again. </h1>");
                    out.println("<a href=\"login.html\"><button>Go Back</button></a>");
                    out.println("</body>");
                    out.println("</html>");
                    response.sendRedirect("login.html");
            }

                    if(op4Str.equals("")){
                        printI(response);
                   }else{
                          try
            {
               
                String que;
                
                if(selected_values.equals("value1")){
                    que = "select * from cereal where fibergram >="+ op4Str;
                }else if (selected_values.equals("value2")){
                    que = "select * from cereal where fibergram ="+ op4Str;
                }else{
                    que = "select * from cereal where fibergram <="+ op4Str;
                }
      
                com.mysql.jdbc.Statement stat = connect(que);
                boolean a = stat.execute(que);
                resultSet = stat.getResultSet();
                
                printT(resultSet,response);
            } catch (Exception ex)
            {
                ex.printStackTrace();
                 printV(response);
            }
                   }
                }else if (act.equals("submitP")){
                    if (request.getRequestedSessionId() != null&& !request.isRequestedSessionIdValid()) {
                out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Invalid information</title>");            
                    out.println("</head>");
                    out.println("<body>");

                    out.println("<h1> Sorry! Your session has expired, please login again. </h1>");
                    out.println("<a href=\"login.html\"><button>Go Back</button></a>");
                    out.println("</body>");
                    out.println("</html>");
                    response.sendRedirect("login.html");
            }

                    //someone has altered the HTML and sent a different value!
                
                    if(op6Str.equals("")){
                        printI(response);
                   }else{
                          try
            {
              
                String que;
                if(selected_valuet.equals("value1")){
                    que = "select * from cereal where price >="+ op6Str;
                }else if (selected_valuet.equals("value2")){
                    que = "select * from cereal where price ="+op6Str;
                }else{
                    que = "select * from cereal where price <="+ op6Str;
                }
      com.mysql.jdbc.Statement stat = connect(que);
                boolean a = stat.execute(que);
                resultSet = stat.getResultSet();
                
                printT(resultSet,response);
            } catch (Exception ex)
            {
                ex.printStackTrace();
                 printV(response);
            }
                   }
                
                }        
        } 
    }

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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * <pre>
     * Description:
     * Check input valid.
     *
     * pre:
     *
     *
     * post:
     *
     * @return a String containing servlet description
     */
    public String checkInputValid(String input)
    {
        if (input == null)
        {
            log("bad input");
        }

        if (input.trim().length() == 0)
        {
            log("bad input");
        }
        return (input);
    }// </editor-fold>

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "This servlet adds two numbers.";
    }// </editor-fold>

    

   /**
     * <pre>
     * Use this to write a friendly reminder that is not an error or warning.
     * For example, "congratulaition, it is your birthday, or you successfully
     * opened the file"
     *
     * @param msg
     */
    public void writeNote(String msg)
    {
        String logMsg = "RRSINFO " + " " + msg;
        if (noteEn)
        {
            log(logMsg);
            
        }
    } 

    /**
     * <pre>
     * Use this to write a warning.
     *
     * @param msg
     */
    public void writeWarning(String msg)
    {
        String logMsg = "RRSWARN " + " " + msg;
        if (warningEn)
        {
           log(logMsg);
        }
    }

    /**
     * <pre>
     * Use this to write error.
     *
     * @param msg
     */
    public void writeError(String msg)
    {
        String logMsg = "RRSERROR " + " " + msg;
        if (errorEn)
        {
           log(logMsg);
        }
    
    }
    public com.mysql.jdbc.Statement connect(String que) throws SQLException{
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
                dbUrlRemote = "jdbc:mysql://sql.cs.usfca.edu:3306/user46";
                connRemote = (com.mysql.jdbc.Connection) DriverManager.getConnection(dbUrlRemote, props);
                com.mysql.jdbc.Statement stat = (com.mysql.jdbc.Statement) connRemote.createStatement();
                return stat;
    }
    
     public void printV(HttpServletResponse response){
            PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Invalid information</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Invalid information!</h1>");
            out.println("<a href=\"work.html\"><button>Go Back</button></a>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException ex) {
            Logger.getLogger(WorkServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
    
    public void printI(HttpServletResponse response){
            PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Invalid information</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Please enter something!</h1>");
            out.println("<a href=\"work.html\"><button>Go Back</button></a>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException ex) {
            Logger.getLogger(WorkServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }    
    }
 
        public void printT(ResultSet rs,HttpServletResponse response)
        {
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            if (!rs.isBeforeFirst() ) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Invalid information</title>");
                out.println("</head>");
                out.println("<body>");
                
                out.println("<h1> Invalid info! </h1>");
                //out.println("<a href=\"work.html\"><button>Go Back</button></a>");
                out.println("</body>");
                out.println("</html>");
            }
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>This is what you can choose!</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<table border = '1'>");  
            out.println("<tr>");
            out.println("<th> Idcereal</th>");
            out.println("<th> Brand</th>");
            out.println("<th> Name</th>");
            out.println("<th> Sugargram</th>");
            out.println("<th> Fibergram</th>");
            out.println("<th> Expiredate</th>");
            out.println("<th> Price</th>");
            out.println("<th> Productdescription</th>");
            out.println("<th> Inventory</th>");
            out.println("</tr>");
            while(rs.next()){
                out.println("<tr>");
                out.println("<td>");
                out.println(rs.getString("idcereal")+"\n");
                out.println("</td>");
                out.println("<td>");
                out.println(rs.getString("brand")+"\n");
                out.println("</td>");
                out.println("<td>");
                out.println(rs.getString("name")+"\n");
                out.println("</td>");
                out.println("<td>");
                out.println(rs.getString("sugargram")+"\n");
                out.println("</td>");
                out.println("<td>");
                out.println( rs.getString("fibergram")+"\n");
                out.println("</td>");
                out.println("<td>");
                out.println( rs.getString("expiredate")+"\n");
                out.println("</td>");
                out.println("<td>");
                out.println( rs.getString("price")+ "\n");
                out.println("</td>");
                out.println("<td>");
                out.println("productdescription: "+ rs.getString("productdescription")+"\n");
                out.println("</td>");
                out.println("<td>");
                out.println(rs.getString("inventory")+"\n");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            //out.println("<a href=\"work.html\"><button>Go Back</button></a>");
            out.println("</body>");
            out.println("</html>");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            
            out.println("<body>");
            
            
            out.println("<a href=\"work.html\"><button>Go Back</button></a>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Invalid information</title>");            
                    out.println("</head>");
                    out.println("<body>");

                    out.println("<h1> Invalid input! </h1>");
                    out.println("<a href=\"work.html\"><button>Go Back</button></a>");
                    out.println("</body>");
                    out.println("</html>");
                ex.printStackTrace();
        }
                
        }
    }


