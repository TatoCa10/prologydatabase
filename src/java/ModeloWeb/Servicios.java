/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloWeb;

import com.itextpdf.text.BaseColor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileOutputStream;

public class Servicios {

    int Id_Global;
    Fecha date = new Fecha();
    String desc;

    public boolean LogIn(Connection connection, int user_id, String pass) {

        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

        // the mysql select statement
        String query = "select user_id, pass from Users where user_id=" + user_id + " and pass='" + pass + "'";

        // create the mysql update and insert preparedstatement
        PreparedStatement preparedStmt = null;

        int id;
        String password;

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                id = rs.getInt(1);
                password = rs.getString(2);

                if (id == user_id && password.equals(pass)) {
                    System.out.println("Logeo promente promente");
                    Id_Global = user_id;
                    desc = "Inicio Sesion";
                    RegistroAct(connection, desc);
                    return true;
                }

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Failed to make insertion!");
            e.printStackTrace();
        }
        System.out.println("Error en el Logeo");
        return false;

    }

    public boolean insertarUser(Connection connection, int user_id, String pass, String Nombre, String Apellido, String Correo, String Telefono) {
        Boolean b;
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

        String query = " insert into Users (user_id, pass, Nombre, Apellido, Correo, Telefono)"
                + " values (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStmt = null;

        try {

            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, user_id);
            preparedStmt.setString(2, pass);
            preparedStmt.setString(3, Nombre);
            preparedStmt.setString(4, Apellido);
            preparedStmt.setString(5, Correo);
            preparedStmt.setString(6, Telefono);

            preparedStmt.executeUpdate();

            System.out.println("You made it, the insertion is ok!");
            desc = "Creo un nuevo usuario";
            RegistroAct(connection, desc);
            b = true;
            return b;

        } catch (SQLException e) {
            System.out.println("Failed to make insertion!");
            b = false;
            e.printStackTrace();
        }
        return b;
    }

    public boolean actUser(Connection connection, int user_id, String pass, String Nombre, String Apellido, String Correo, String Telefono) {

        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        boolean b = true;
        // the mysql select statement
        String query = "SELECT user_id from Users where user_id=" + user_id + "";

        // create the mysql update and insert preparedstatement
        PreparedStatement preparedStmt = null;

        int id;
        boolean check = true;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                id = rs.getInt(1);

                if (id == user_id) {
                    System.out.println("Entro promente");
                    check = false;
                    query = "UPDATE Users SET user_id=" + user_id + ", pass='" + pass + "', Nombre='" + Nombre + "', Apellido='" + Apellido + "', Correo='" + Correo + "', Telefono='" + Telefono + "' where user_id=" + user_id + "";
                    preparedStmt = connection.prepareStatement(query);
                    preparedStmt.executeUpdate();
                    desc = "Actualizo un Usuario";
                    RegistroAct(connection, desc);
                    b = true;
                }
            }
            if (check) {
                System.out.println("No se encontro ID del Usuario");
                b = false;
            }
            desc = "Actualizo Usuario";
            RegistroAct(connection, desc);
            return b;

        } catch (SQLException e) {
            System.out.println("Error Detectado");
            b = false;
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, e);

        }
        return b;
    }

    public boolean borrarUser(Connection connection, int user_id) {

        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        boolean b = true;
        // the mysql select statement
        String query = "SELECT user_id from Users where user_id=" + user_id + "";

        // create the mysql update and insert preparedstatement
        PreparedStatement preparedStmt = null;

        Integer[] id = new Integer[1];
        boolean check = true;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                id[0] = rs.getInt(1);

                if (id[0] != null) {
                    System.out.println("Entro promente");
                    check = false;
                    query = "Delete From Users where user_id=" + user_id + "";
                    preparedStmt = connection.prepareStatement(query);
                    preparedStmt.executeUpdate();
                    desc = "Elimino un Usuario";
                    RegistroAct(connection, desc);
                    b = true;
                }
            }
            if (check) {
                System.out.println("No se encontro ID del Usuario");
                b = false;
            }

            desc = "Elimino Usuario";
            RegistroAct(connection, desc);
            return b;
        } catch (SQLException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
            b = false;
        }
        return b;
    }

    ///////////////////////////////////////////////////////     LOTES     \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public boolean inertarLote(Connection connection, int IdLote, String NombreLote) {
        //Insertion 
        // create a sql date object so we can use it in our INSERT statement
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        boolean b;
        // the mysql insert statement
        String query = " insert into Lote (IdLote, Nombre)"
                + " values (?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = null;

        try {

            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, IdLote);
            preparedStmt.setString(2, NombreLote);

            // execute the preparedstatement
            preparedStmt.executeUpdate();

            System.out.println("You made it, the insertion is ok!");
            b = true;
            desc = "Creo un nuevo Lote";
            RegistroAct(connection, desc);
            return b;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Failed to make insertion!");
            b = false;
            e.printStackTrace();
        }
        return b;
    }

    public boolean actLote(Connection connection, int IdLote, String NombreLote) {

        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        boolean b = true;
        // the mysql select statement
        String query = "SELECT IDLote from Lote where IDLote=" + IdLote + "";

        // create the mysql update preparedstatement
        PreparedStatement preparedStmt = null;

        Integer[] id = new Integer[1];
        boolean check = true;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                id[0] = rs.getInt(1);

                if (id[0] != null) {
                    System.out.println("Entro promente");
                    check = false;
                    query = "UPDATE Lote SET IDLote=" + IdLote + ", Nombre='" + NombreLote + "' where IDLote=" + IdLote + "";
                    preparedStmt = connection.prepareStatement(query);
                    preparedStmt.executeUpdate();
                    b = true;
                    //Revisar si esta vacio
                    query = "SELECT IDLote from Productos where IDLote=" + IdLote + "";
                    rs = st.executeQuery(query);
                    while (rs.next()) {
                        id[0] = rs.getInt(1);

                        if (id[0] != null) {
                            query = "UPDATE Productos SET IDLote=" + IdLote + " where IDLote=" + IdLote + "";
                            preparedStmt = connection.prepareStatement(query);
                            preparedStmt.executeUpdate();
                            b = true;
                        }
                    }

                }
            }
            if (check) {
                System.out.println("No se encontro ID del Lote");
                b = false;
            }
            desc = "Actualizo Lote";
            RegistroAct(connection, desc);
            return b;

        } catch (SQLException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
            b = false;
        }
        return b;
    }

    ///////////////////////////////////////////////////////     ITEMS    \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public boolean insertarItem(Connection connection, int IdItem, int IDLote, int Cantidad, String NombreProd, String Proveedor, int Precio, int Razon) {
        //Insertion 
        // create a sql date object so we can use it in our INSERT statement
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        boolean b;
        // the mysql insert statement
        String query = " insert into Productos (IdItem ,IDLote, Cantidad, NombreProd, Proveedor, Precio, Razon)"
                + " values (?, ?, ?, ?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = null;

        try {

            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, IdItem);
            preparedStmt.setInt(2, IDLote);
            preparedStmt.setInt(3, Cantidad);
            preparedStmt.setString(4, NombreProd);
            preparedStmt.setString(5, Proveedor);
            preparedStmt.setInt(6, Precio);
            preparedStmt.setInt(7, Razon);

            // execute the preparedstatement
            preparedStmt.executeUpdate();

            System.out.println("You made it, the insertion is ok!");
            desc = "Ingreso un producto a Bodega";
            RegistroAct(connection, desc);
            b = true;
            return b;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Failed to make insertion!");
            b = false;
            e.printStackTrace();
        }
        return b;
    }

    public boolean actItem(Connection connection, int IdItem, int IDLote, int Cantidad, String NombreProd, String Proveedor, int Precio, int Razon) {

        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        boolean b = true;
        // the mysql select statement
        String query = "SELECT IdItem from Productos where IdItem=" + IdItem + "";

        // create the mysql update and insert preparedstatement
        PreparedStatement preparedStmt = null;

        Integer[] id = new Integer[1];
        boolean check = true;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                id[0] = rs.getInt(1);

                if (id[0] != null) {
                    System.out.println("Entro promente");
                    check = false;
                    query = "UPDATE Productos SET IdItem=" + IdItem + ", IDLote=" + IDLote + ", Cantidad=" + Cantidad + ", NombreProd='" + NombreProd + "', Proveedor='" + Proveedor + "', Precio=" + Precio + ", Razon=" + Razon + " where IdItem=" + IdItem + "";
                    preparedStmt = connection.prepareStatement(query);
                    preparedStmt.executeUpdate();
                    b = true;

                }
            }
            if (check) {
                System.out.println("No se encontro ID del Item");
                b = false;
            }
            desc = "Actualizo Item";
            RegistroAct(connection, desc);
            return b;

        } catch (SQLException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
            b = false;
        }
        return b;
    }

    public boolean agregarItem(Connection connection, int IdItem, int cantidad) {

        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        boolean b = true;
        // the mysql select statement
        String query = "SELECT IdItem, Cantidad from Productos where IdItem=" + IdItem + "";

        // create the mysql update and insert preparedstatement
        PreparedStatement preparedStmt = null;

        Integer[] id = new Integer[1];
        int cant;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                id[0] = rs.getInt(1);
                cant = rs.getInt(2);
                if (id[0] != null) {
                    System.out.println("Entro promente");
                    cant = cant + cantidad;
                    query = "UPDATE Productos SET Cantidad=" + cant + " where IdItem=" + IdItem + "";
                    preparedStmt = connection.prepareStatement(query);
                    preparedStmt.executeUpdate();
                    desc = "Ingreso Items A Bodega";
                    RegistroAct(connection, desc);
                    b = true;
                } else {
                    System.out.println("No se encontro ID del Item");
                    b = false;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
            b = false;
        }
        return b;
    }

    public boolean sacarItem(Connection connection, int IdItem, int cantidad) {

        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        boolean b = true;
        // the mysql select statement
        String query = "SELECT IdItem, Cantidad from Productos where IdItem=" + IdItem + "";

        // create the mysql update and insert preparedstatement
        PreparedStatement preparedStmt = null;

        Integer[] id = new Integer[1];
        int cant;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                id[0] = rs.getInt(1);
                cant = rs.getInt(2);
                if (id[0] != null) {
                    System.out.println("Entro promente");
                    cant = cant - cantidad;
                    query = "UPDATE Productos SET Cantidad=" + cant + " where IdItem=" + IdItem + "";
                    preparedStmt = connection.prepareStatement(query);
                    preparedStmt.executeUpdate();
                    desc = "Saco de Bodega Items";
                    RegistroAct(connection, desc);
                    b = true;
                } else {
                    System.out.println("No se encontro ID del Item");
                    b = false;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
            b = false;
        }
        return b;
    }

    public boolean vaciarLote(Connection connection, int IdLote) {

        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        boolean b = true;
        // the mysql select statement
        String query = "SELECT IdLote from Lote where IdLote=" + IdLote + "";

        // create the mysql update and insert preparedstatement
        PreparedStatement preparedStmt = null;

        Integer[] id = new Integer[1];
        boolean check = true;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                id[0] = rs.getInt(1);

                if (id[0] != null) {
                    System.out.println("Entro promente");
                    check = false;
                    query = "Delete From Productos where IdItem=" + IdLote + "";
                    preparedStmt = connection.prepareStatement(query);
                    preparedStmt.executeUpdate();
                    b = true;

                }
            }
            if (check) {
                System.out.println("No se encontro ID del Item");
                b = false;
            }
            desc = "Elimino Item";
            RegistroAct(connection, desc);
            return b;

        } catch (SQLException ex) {
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
            b = false;
        }
        return b;
    }

    ///////////////////////////////////////////////////////     CONSULTAS    \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public void RegistroAct(Connection connection, String desc) {

        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

        // the mysql insert statement
        String query = " insert into Registro (user_id ,descrip, fecha)"
                + " values (?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = null;

        String fecha = date.getDate().toString();
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, Id_Global);
            preparedStmt.setString(2, desc);
            preparedStmt.setString(3, fecha);

            // execute the preparedstatement
            preparedStmt.executeUpdate();

            System.out.println("You made it, the Register is ok!");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Failed to make the Register!");
            e.printStackTrace();
        }

    }

    public ResultSet ListaGeneral(Connection connection, int x) {

        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

        String query = "";
        int id;
        String nombre;

        switch (x) {

            case 4:
                query = "select * from Users";

                try {

                    Statement st = connection.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    return rs;

                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    System.out.println("Failed to make insertion!");
                    e.printStackTrace();
                }

            case 5:

                query = "select * from Lote";

                try {

                    Statement st = connection.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    return rs;

                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    System.out.println("Failed to make insertion!");
                    e.printStackTrace();
                }

            case 6:

                query = "select * from Productos";

                try {

                    Statement st = connection.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    return rs;

                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    System.out.println("Failed to make insertion!");
                    e.printStackTrace();
                }
        }
        return null;

    }

    public void GenerarPDF(Connection connection, ArrayList id, ArrayList nombre, int opc) {

        ArrayList<Integer> IdsPDF = new ArrayList<>();
        ArrayList<String> NombresPDF = new ArrayList<>();
        IdsPDF = id;
        NombresPDF = nombre;

        Document document = new Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Carlos Alberto\\Desktop\\tablitas.pdf"));
            document.open();
            System.out.println("Probo");
            PdfPTable table = new PdfPTable(2);
            if(opc == 4){
            Font fuenteUsuario = new Font(Font.FontFamily.TIMES_ROMAN, 20);
            fuenteUsuario.setColor(BaseColor.WHITE);
            
            PdfPCell cellUser = new PdfPCell(new Paragraph("Id Usuario", fuenteUsuario));
            cellUser.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellUser.setBackgroundColor(BaseColor.DARK_GRAY);
            cellUser.setPaddingBottom(15);
            table.addCell(cellUser);
            
            Font fuenteUsuarioNombre = new Font(Font.FontFamily.TIMES_ROMAN, 20);
            fuenteUsuarioNombre.setColor(BaseColor.WHITE);
            
            PdfPCell cellUserNombre = new PdfPCell(new Paragraph("Nombre Usuario", fuenteUsuarioNombre));
            cellUserNombre.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellUserNombre.setBackgroundColor(BaseColor.DARK_GRAY);
            cellUserNombre.setPaddingBottom(15);
            table.addCell(cellUserNombre);
            }
            if(opc == 5){
            Font fuenteUsuario = new Font(Font.FontFamily.TIMES_ROMAN, 20);
            fuenteUsuario.setColor(BaseColor.WHITE);
            
            PdfPCell cellUser = new PdfPCell(new Paragraph("Id Lote", fuenteUsuario));
            cellUser.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellUser.setBackgroundColor(BaseColor.DARK_GRAY);
            cellUser.setPaddingBottom(15);
            table.addCell(cellUser);
            
            Font fuenteUsuarioNombre = new Font(Font.FontFamily.TIMES_ROMAN, 20);
            fuenteUsuarioNombre.setColor(BaseColor.WHITE);
            
            PdfPCell cellUserNombre = new PdfPCell(new Paragraph("Nombre Lote", fuenteUsuarioNombre));
            cellUserNombre.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellUserNombre.setBackgroundColor(BaseColor.DARK_GRAY);
            cellUserNombre.setPaddingBottom(15);
            table.addCell(cellUserNombre);
            }
            if(opc == 6){
            Font fuenteUsuario = new Font(Font.FontFamily.TIMES_ROMAN, 20);
            fuenteUsuario.setColor(BaseColor.WHITE);
            
            PdfPCell cellUser = new PdfPCell(new Paragraph("Cantidad", fuenteUsuario));
            cellUser.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellUser.setBackgroundColor(BaseColor.DARK_GRAY);
            cellUser.setPaddingBottom(15);
            table.addCell(cellUser);
            
            Font fuenteUsuarioNombre = new Font(Font.FontFamily.TIMES_ROMAN, 20);
            fuenteUsuarioNombre.setColor(BaseColor.WHITE);
            
            PdfPCell cellUserNombre = new PdfPCell(new Paragraph("Nombre Producto", fuenteUsuarioNombre));
            cellUserNombre.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellUserNombre.setBackgroundColor(BaseColor.DARK_GRAY);
            cellUserNombre.setPaddingBottom(15);
            table.addCell(cellUserNombre);
            }
            for (int x = 0; x < IdsPDF.size(); x++) {
                String nombreCelda = IdsPDF.get(x).toString();
                
                PdfPCell cellUserNombreFor = new PdfPCell(new Paragraph(nombreCelda));
                cellUserNombreFor.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cellUserNombreFor);
                
                PdfPCell cellUserNombre2For = new PdfPCell(new Paragraph(NombresPDF.get(x)));
                cellUserNombre2For.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cellUserNombre2For);
            }
          
            document.add(table);

            document.close();

        } catch (Exception e) {
            System.err.println("Ocurrio un error al crear el archivo");
            //System.exit(-1);
        }

    }

}
