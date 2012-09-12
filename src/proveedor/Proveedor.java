/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proveedor;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.util.Password;


/**
 *
 * @author addiel
 */
public class Proveedor {

    /**
     * @param args the command line arguments
     */
    
    //porvedor
        //clave
        //nombre
        //telefono
        //domicilio
        //fecha
    
    static String url="jdbc:derby://localhost:1527/proveedor";
    static String usuario="proveedor";
    static String password="proveedor";
      
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        
        Connection c = null; //realiza la coneccion
        Statement s = null;
        ResultSet rs = null;
        
        
        try 
        {
            c = DriverManager.getConnection(url, usuario, password);
            s = c.createStatement();
            s.execute("CREATE TABLE Proveedor(clave integer primary key, nombre varchar(30) not null, telefono varchar(20) not null, domicilio varchar(50) not null, fecha_de date not null)");
            boolean n=s.execute("insert into Proveedor values(2,'Pedro', '1234567','calle 1 colonia 1','01/01/2012')");
            System.out.println("conectado a la DB");
             
            rs = s.executeQuery("select * from Proveedor");
            while (rs.next()){
                System.out.println("Clave: "+rs.getString("clave")+ ", "+"Nombre: "+rs.getString("nombre")+  ", Telefono: "+ rs.getString("telefono")+  ", Domicilio: "+ rs.getString("domicilio") + ", Fecha De Nacimiento: " +  fmt.format(rs.getDate("fecha_de")));
            
            }
            
        }
        
      catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
          if (s!=null){  
            try {
                s.close();
            } catch (SQLException ex) {
                
            }
          }
          if (c!=null){  
            try {
                c.close();
            } catch (SQLException ex) {
                
            }
          }
        }
    }
}
