package ModeloWeb;

import Controlador.Conexion;
import java.io.File;
import java.io.IOException;
import ModeloWeb.Controlador;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws IOException, SQLException {

        Conexion conexion = new Conexion();
        Connection connection = null;
        ResultSet rs = null;
        connection = conexion.GenerarConexion();

        Servicios service = new Servicios();

        ArrayList<Integer> id = new ArrayList<>();
        ArrayList<String> nombre = new ArrayList<>();

        rs = service.ListaGeneral(connection, 4);

        while (rs.next()) {
            id.add(rs.getInt(1));
            nombre.add(rs.getString(3));
        }
        service.GenerarPDF(connection, null, null, 0);

    }

}
