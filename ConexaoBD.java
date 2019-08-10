import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
      
   static {
      try {
         Class.forName("com.mysql.jdbc.Driver");
      } 
      catch (ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
   }

   public Connection conectar() throws SQLException {
      String servidor = "localhost";
      String porta = "3306";
      String database = "agenciaBD";
      String usuario = "root";
      String senha = "";
      String JdbcURL = "jdbc:mysql://"+servidor+":"+porta+"/"+database + "?autoReconnect=true&useSSL=false";
      return DriverManager.getConnection(JdbcURL, usuario, senha);
   }

   public static void desconectar(Connection conn) throws SQLException {
      conn.close();
   }
}