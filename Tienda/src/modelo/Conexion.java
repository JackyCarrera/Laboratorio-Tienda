
package modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    private final String base = "tienda";//declaracion de variables -gerson
    private final String user = "root";
    private final String password = "Dba052K20";
    private final String url = "jdbc:mysql://localhost:3306/" + base;//se va a ir a conectar a la baase de datos 
    private Connection con = null;// declara variable que sera igual a null
    
    public Connection getConexion()//se crea un metodo tipo conexion para que regrese la conección
    {
        
        try{
            Class.forName("com.mysql.jdbc.Driver");//nombre del espacio que trae el conector
            con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);//se va realizar la coneccion con parametros por orden 
            
        } catch(SQLException e)//agrega una exepcion 
        {
            System.err.println(e);//va imprimir en pantalla
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
      return con;  //me va a retornar la variable con 
    }

}
