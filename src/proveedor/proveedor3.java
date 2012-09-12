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
public class proveedor3 {
    
    static String url="jdbc:derby://localhost:1527/proveedor";
    static String usuario="proveedor";
    static String password="proveedor";
    static Connection c =null;
    static PreparedStatement ps1 =null;
    static PreparedStatement ps2 =null;
    static   ResultSet rs=null;
    static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

    
    public static void main(String[] args) throws ClassNotFoundException, ParseException, SQLException {
       Class.forName("org.apache.derby.jdbc.ClientDriver");
       Scanner ent = new Scanner(System.in);
       int opcion=0;
       do{
           System.out.println("1.Agregar proveedor");
           System.out.println("2.Consultar Proveedor");
           System.out.println("3.Salir");
           System.out.print("Elige una opcion: ");
           opcion= Integer.parseInt(ent.nextLine());
           switch (opcion) {
               case 1:
                    System.out.print("Clave del prveedor: ");
                    int clave = Integer.parseInt(ent.nextLine());
                    System.out.print("Nombre del proveedor: ");
                    String nombre = ent.nextLine();
                    System.out.print("telefono del proveedor: ");
                    String telefono = ent.nextLine();
                    System.out.print("domicilio del proveedor: ");
                    String domicilio = ent.nextLine();
                    System.out.print("Fecha del proveedor: ");
                    String fecha = ent.nextLine();
                    agregarProveedor(clave, nombre, telefono, domicilio, fecha);
                    break;
               case 2: consultarProveedor();
                    break;
           }
           
           
       }while(opcion!=3);
       
        
    }
    
    
    
    
        
    
    
    /////////////////////////////////
    private static void agregarProveedor(int clave, String nombre, String telefono, String domicilio, String fecha) throws SQLException, ParseException {
        try{
            c = DriverManager.getConnection(url, usuario, password);
            ps1 = c.prepareStatement("Insert into Proveedor(clave, nombre, telefono, domicilio, fecha_de) VALUES(?,?,?,?,?)");
            ps1.setInt(1, clave);
            ps1.setString(2, nombre);
            ps1.setString(3, telefono);
            ps1.setString(4, domicilio);
            ps1.setDate(5, new Date(fmt.parse(fecha).getTime()));
            ps1.executeUpdate();
            System.out.println("Proveedor Agregado");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
          if (ps1!=null){  
            try {
                ps1.close();
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
    ////////////////////////////////////////////////////

    private static void consultarProveedor() {
         try {
            c = DriverManager.getConnection(url, usuario, password);
            
            ps2 = c.prepareStatement("select * from Proveedor");
            rs = ps2.executeQuery();
            while (rs.next()){
                System.out.println("Clave: "+rs.getString("clave")+ ", "+", Nombre: "+rs.getString("nombre")+ ", Telefono; "+ rs.getString("telefono") + ", Dimicilio: "+ rs.getString("domicilio") + ", Fecha : "+  fmt.format(rs.getDate("fecha_de")));
            
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
          
          if (ps2!=null){  
            try {
                ps2.close();
            } catch (SQLException ex) {
                
            }
          }
          if (rs!=null){  
            try {
                rs.close();
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