package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class SqlUsuarios extends Conexion { 

    public boolean registrar(Usuarios usr) { //Se esta heredando la conexion -Jacky
        PreparedStatement ps = null; //Prepara la sentencia-Jacky
        Connection con = getConexion(); //Procede a conectar la conexion con MySQl-Jacky
//Se hace mantenimiento a través del insert-Jacky
        String sql = "INSERT INTO usuarios (usuario, password, nombre, email, idTipo) VALUES(?,?,?,?,?)"; //se hace mantenimiento atraves de insert

        
        try { // El "Try" y el "Catch" Capturan errores -Jacky
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());//Asigna cada uno en el mantenimiento-Jacky
            ps.setString(2, usr.getPassword());
            ps.setString(3, usr.getNombre());
            ps.setString(4, usr.getEmail());
            ps.setInt(5, usr.getIdTipo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());// trajo informacion de la clase conexion
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());//
            }
        }
    }

    public boolean login(Usuarios usr) {//conexion con la ventana de login 
        PreparedStatement ps = null;//Prepara la sentencia-Jacky
        ResultSet rs = null;//Proporciona el acceso a los datos de la condicion -Jacky
        Connection con = getConexion();//Procede a conectar la conexion con MySQl-Jacky
//Mantenimiento a traves del Select -Jacky
        String sql = "SELECT id, usuario, password, nombre, idTipo FROM usuarios WHERE usuario = ? LIMIT 1";//LIMIT es para limitar el número de registros que devuelve una consulta

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            rs = ps.executeQuery();

            if (rs.next()) {//Inicia una condicion
                if (usr.getPassword().equals(rs.getString(3))) {
                    usr.setId(rs.getInt(1));
                    usr.setNombre(rs.getString(4));
                    usr.setIdTipo(rs.getInt(5));
                    return true;
                } else {//de lo contrario
                    return false;// si es falsa procedera a hacer la siguiente condicion
                }
            }

            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());// trajo informacion de la clase conexion
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());// trajo informacion de la clase conexion
            }
        }
    }

    public int existeUsuario(String usuario) {//para verificar si existe el usuario
        PreparedStatement ps = null;//Prepara la sentencia-Jacky
        ResultSet rs = null;//Proporciona el acceso a los datos de la condicion -Jacky
        Connection con = getConexion();//Procede a conectar la conexion con MySQl-Jacky
//Mantenimiento a traves del Select -jacky
        String sql = "SELECT count(id) FROM usuarios WHERE usuario = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

            return 1;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return 1;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean esEmail(String correo) {

        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");//crea un patron a partir de la expresion regular pasada como parametro al metodo

        Matcher mather = pattern.matcher(correo);//Verifica una secuencia dada de tokens

        return mather.find();

    }

}
