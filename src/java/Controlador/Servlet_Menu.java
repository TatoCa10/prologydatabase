package Controlador;

import ModeloWeb.EscrituraAccesoAleatorio;
import ModeloWeb.Fecha;
import ModeloWeb.Servicios;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Servlet_Menu", urlPatterns = {"/Servlet_Menu"})
public class Servlet_Menu extends HttpServlet {

    Fecha date = new Fecha();
    EscrituraAccesoAleatorio eaa = new EscrituraAccesoAleatorio();
    Servicios service = new Servicios();
    Controlador.Conexion conexion = new Conexion();
    Connection connection = null;
    ResultSet rs = null;
    Boolean b;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //connection = service.GenerarConexion();
            connection = conexion.GenerarConexion();
            int opcion = Integer.parseInt(request.getParameter("opcion"));

            switch (opcion) {
                case 1:
                    int id = Integer.parseInt(request.getParameter("IdUserReg"));
                    String pass = request.getParameter("NPassUserReg");
                    String Nombre = request.getParameter("NombreUserReg");
                    String Apellido = request.getParameter("ApellidoUserReg");
                    String Correo = request.getParameter("CorreoUserReg");
                    String Telefono = request.getParameter("TelUserReg");

                    b = service.insertarUser(connection, id, pass, Nombre, Apellido, Correo, Telefono);

                    if (b == true) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet Servlet_Menu</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "indexMainMenu.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>El Usuario Se Agrego Satisfactoriamente...</h1>");
                        out.println("<p>Serás dirigido automáticamente en cinco segundos al menu principal. En caso contrario, puedes acceder registrar otro Lote, haciendo click <a href=" + "CrearUser.html" + ">Aquí</a></p>");
                        out.println("</body>");
                        out.println("</html>");
                    } else {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet Servlet_Menu</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "indexMainMenu.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>No fue posible agregar el Usuario, el Id ya existe, intente nuevamente...</h1>");
                        out.println("<p>Serás dirigido automáticamente en cinco segundos al menu principal. En caso contrario, puedes acceder registrar otro Lote, haciendo click <a href=" + "CrearUser.html" + ">Aquí</a></p>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    ;

                case 2:
                    int IdLote = Integer.parseInt(request.getParameter("IdLoteReg"));
                    String NombreLote = request.getParameter("NombreLoteReg");
                    b = service.inertarLote(connection, IdLote, NombreLote);

                    if (b == true) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Registro Lote</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "indexMainMenu.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Lote -> " + IdLote + " agregado Satisfactoriamente, con el nombre:'' " + NombreLote + "''</h1>");
                        out.println("<p>Serás dirigido automáticamente en cinco segundos al menu principal. En caso contrario, puedes acceder registrar otro Lote, haciendo click <a href=" + "CrearLote.html" + ">Aquí</a></p>");
                        out.println("</body>");
                        out.println("</html>");
                    } else {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet Servlet_Menu</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "CrearLote.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Error interno en la creacion del Lote, el Id del lote ya existe, intentelo nuevamente...</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    ;
                case 3:
                    String nombreProducto = request.getParameter("NombreProductoReg");
                    String proveedorProducto = request.getParameter("ProveedorProductoReg");
                    int IdProduc = Integer.parseInt(request.getParameter("IdProductoReg"));
                    int cantidadProducto = Integer.parseInt(request.getParameter("CantidadProductoReg"));
                    //String tipoProducto = request.getParameter("TipoProductoReg");
                    int precioProducto = Integer.parseInt(request.getParameter("PrecioProductoReg"));
                    int razonProducto = Integer.parseInt(request.getParameter("RazonProductoReg"));
                    int idLoteProd = Integer.parseInt(request.getParameter("PathProductoReg"));
                    b = service.insertarItem(connection, IdProduc, idLoteProd, cantidadProducto, nombreProducto, proveedorProducto, precioProducto, razonProducto);

                    if (b == true) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Creacion Producto</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "indexMainMenu.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Se creo correctamente el producto: " + nombreProducto + "</h1>");
                        out.println("<p>Serás dirigido automáticamente en cinco segundos al menu principal nuevamente. En caso contrario, puedes ir al Menu, haciendo click <a href=" + "CrearProducto.html" + ">Aquí</a></p>");
                        out.println("</body>");
                        out.println("</html>");
                    } else {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Creacion Producto</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "CrearProducto.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Error en el ingreso de datos, intentelo nuevamente...</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }

                    ;
                case 4:
                    ArrayList<Integer> idsUser = new ArrayList<>();
                    ArrayList<String> NombresUsers = new ArrayList<>();
                    rs = service.ListaGeneral(connection, 4);

                    while (rs.next()) {
                        idsUser.add(rs.getInt(1));
                        NombresUsers.add(rs.getString(3));
                    }

                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<meta charset=\"utf-8\" />");
                    out.println("<title>Servlet_Menu</title>");
                    out.println("<link rel=\"stylesheet\" href=\"tablas.css\">");
                    out.println("<meta name=\"viewport\" content=\"initial-scale=1.0; maximum-scale=1.0; width=device-width;\">");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div class=\"table-title\">");
                    out.println("<h3>Listado Usuarios</h3>");
                    out.println("</div>");
                    out.println("<table class=\"table-fill\">");
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<th class=\"text-left\">ID</th>");
                    out.println("<th class=\"text-left\">Nombre</th>");
                    out.println("</tr>");
                    out.println("</thead>");
                    out.println("<tbody class=\"table-hover\">");
                    for (int x = 0; x < idsUser.size(); x++) {
                        out.println("<tr>");
                        out.println("<td class=\"text-left\">" + idsUser.get(x) + "</td>");
                        out.println("<td class=\"text-left\">" + NombresUsers.get(x) + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</tbody>");
                    out.println("</table>");
                    out.println("<center>");
                    out.println("<p>Regresar al menu principal <a href=" + "indexMainMenu.html" + "> Click Aquí</a></p>");
                    out.println("</center>");
                    out.println("</body>");
                    out.println("</html>");
                    ;
                case 5:
                    if (opcion == 5) {
                        ArrayList<Integer> idsLotes = new ArrayList<>();
                        ArrayList<String> NombresLotes = new ArrayList<>();
                        rs = service.ListaGeneral(connection, 5);

                        while (rs.next()) {
                            idsLotes.add(rs.getInt(1));
                            NombresLotes.add(rs.getString(2));
                        }

                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<meta charset=\"utf-8\" />");
                        out.println("<title>Servlet_Menu</title>");
                        out.println("<link rel=\"stylesheet\" href=\"tablas.css\">");
                        out.println("<meta name=\"viewport\" content=\"initial-scale=1.0; maximum-scale=1.0; width=device-width;\">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<div class=\"table-title\">");
                        out.println("<h3>Listado Lotes</h3>");
                        out.println("</div>");
                        out.println("<table class=\"table-fill\">");
                        out.println("<thead>");
                        out.println("<tr>");
                        out.println("<th class=\"text-left\">Id</th>");
                        out.println("<th class=\"text-left\">Nombre Lote</th>");
                        out.println("</tr>");
                        out.println("</thead>");
                        out.println("<tbody class=\"table-hover\">");
                        for (int x = 0; x < idsLotes.size(); x++) {
                            out.println("<tr>");
                            out.println("<td class=\"text-left\">" + idsLotes.get(x) + "</td>");
                            out.println("<td class=\"text-left\">" + NombresLotes.get(x) + "</td>");
                            out.println("</tr>");
                        }
                        out.println("</tbody>");
                        out.println("</table>");
                        out.println("<center>");
                        out.println("<p>Regresar al menu principal <a href=" + "indexMainMenu.html" + "> Click Aquí</a></p>");
                        out.println("</center>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    ;
                case 6:
                    if (opcion == 6) {
                        ArrayList<Integer> idsItems = new ArrayList<>();
                        ArrayList<String> NombresItems = new ArrayList<>();
                        ArrayList<Integer> Cant = new ArrayList<>();

                        rs = service.ListaGeneral(connection, 6);

                        while (rs.next()) {
                            idsItems.add(rs.getInt(1));
                            Cant.add(rs.getInt(3));
                            NombresItems.add(rs.getString(4));
                        }
                        //NombresItems = service.ListarNombreItem(connection);

                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<meta charset=\"utf-8\" />");
                        out.println("<title>Servlet_Menu</title>");
                        out.println("<link rel=\"stylesheet\" href=\"tablas.css\">");
                        out.println("<meta name=\"viewport\" content=\"initial-scale=1.0; maximum-scale=1.0; width=device-width;\">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<div class=\"table-title\">");
                        out.println("<h3>Listado Productos</h3>");
                        out.println("</div>");
                        out.println("<table class=\"table-fill\">");
                        out.println("<thead>");
                        out.println("<tr>");
                        out.println("<th class=\"text-left\">Nombre</th>");
                        out.println("<th class=\"text-left\">ID</th>");
                        out.println("<th class=\"text-left\">Cantidad</th>");
                        out.println("</tr>");
                        out.println("</thead>");
                        out.println("<tbody class=\"table-hover\">");
                        for (int x = 0; x < idsItems.size(); x++) {
                            out.println("<tr>");
                            out.println("<td class=\"text-left\">" + NombresItems.get(x) + "</td>");
                            out.println("<td class=\"text-left\">" + idsItems.get(x) + "</td>");
                            out.println("<td class=\"text-left\">" + Cant.get(x) + "</td>");
                            out.println("</tr>");
                        }
                        out.println("</tbody>");
                        out.println("</tbody>");
                        out.println("</table>");
                        out.println("<center>");
                        out.println("<p>Regresar al menu principal <a href=" + "indexMainMenu.html" + "> Click Aquí</a></p>");
                        out.println("</center>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    ;
                case 7: //Generador de Archivos
                    ;
                case 8:
                    int IdAListar = Integer.parseInt(request.getParameter("IdLoteListaPorId"));
                    ArrayList<Integer> LoteIdss = eaa.ListarLotesPorUsuarioLote(IdAListar);

                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<meta charset=\"utf-8\" />");
                    out.println("<title>Servlet_Menu</title>");
                    out.println("<link rel=\"stylesheet\" href=\"tablas.css\">");
                    out.println("<meta name=\"viewport\" content=\"initial-scale=1.0; maximum-scale=1.0; width=device-width;\">");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div class=\"table-title\">");
                    out.println("<h3>Listado Lotes</h3>");
                    out.println("</div>");
                    out.println("<table class=\"table-fill\">");
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<th class=\"text-left\">Id</th>");
                    out.println("<th class=\"text-left\">Posicion</th>");
                    out.println("</tr>");
                    out.println("</thead>");
                    out.println("<tbody class=\"table-hover\">");
                    for (int x = 0; x < LoteIdss.size(); x++) {
                        out.println("<tr>");
                        out.println("<td class=\"text-left\">" + IdAListar + "</td>");
                        out.println("<td class=\"text-left\">" + LoteIdss.get(x) + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</tbody>");
                    out.println("</table>");
                    out.println("<center>");
                    out.println("<p>Regresar al menu principal <a href=" + "indexMainMenu.html" + "> Click Aquí</a></p>");
                    out.println("</center>");
                    out.println("</body>");
                    out.println("</html>");

                    ;
                case 9:

                    String NombreUsAct = request.getParameter("NombreUsuarioAct");
                    String ApellidoUsAct = request.getParameter("ApellidoUsuarioAct");
                    int IdUsAct = Integer.parseInt(request.getParameter("IdUsuarioAct"));
                    String passUserAct = request.getParameter("PassUsuarioAct");
                    String CorreoUsAct = request.getParameter("CorreoUsuarioAct");
                    String TelUsAct = request.getParameter("TelUsuarioAct");

                    b = service.actUser(connection, IdUsAct, passUserAct, NombreUsAct, ApellidoUsAct, CorreoUsAct, TelUsAct);

                    if (b == true) {

                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Actualizar Usuario</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "indexMainMenu.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Se actualizo correctamente el usuario con el Id: " + IdUsAct + " Espere...</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    } else {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Actualizar Usuario</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "ActUser.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<p>No se logro actualizar el Usuario, Serás dirigido automáticamente en cinco segundos nuevamente al formulario de actualizacion. En caso contrario, puedes salir al menu principal, haciendo click <a href=" + "indexMainMenu.html" + ">Aquí</a></p>");
                        out.println("</body>");
                        out.println("</html>");
                    }

                    ;
                case 10:

                    int IdLoteAct = Integer.parseInt(request.getParameter("IdLoteAct"));
                    String NombreLoteAct = request.getParameter("NombreLoteAct");
                    b = service.actLote(connection, IdLoteAct, NombreLoteAct);

                    if (b == true) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Actualizar Lote</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "indexMainMenu.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Se actualizo correctamente el Lote: " + IdLoteAct + "</h1>");
                        out.println("</body>");
                        out.println("</html>");

                    } else {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Agregacion Lote</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "ActLote.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<p>No se logro actualizar el Lote, Serás dirigido automáticamente en cinco segundos nuevamente al formulario de actualizacion. En caso contrario, puedes salir al menu principal, haciendo click <a href=" + "indexMainMenu.html" + ">Aquí</a></p>");
                        out.println("</body>");
                        out.println("</html>");
                    }

                    ;
                case 11:

                    int IdItem = Integer.parseInt(request.getParameter("IdProductoAct"));
                    int IdItemLote = Integer.parseInt(request.getParameter("LoteIdProductoAct"));
                    int CantidadItem = Integer.parseInt(request.getParameter("CantProductoAct"));
                    String NombreProditem = request.getParameter("NombreProductoAct");
                    String TipoItem = request.getParameter("TipoProductoAct");
                    String ProveedorItem = request.getParameter("ProveedorProductoAct");
                    int PrecioItem = Integer.parseInt(request.getParameter("PrecioProductoAct"));
                    int RazonItem = Integer.parseInt(request.getParameter("RazonProductoAct"));

                    b = service.actItem(connection, IdItem, IdItemLote, CantidadItem, NombreProditem, ProveedorItem, PrecioItem, RazonItem);

                    if (b == true) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Actualizar Lote</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "indexMainMenu.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Se actualizo correctamente el Producto con el ID: " + IdItem + "</h1>");
                        out.println("</body>");
                        out.println("</html>");

                    } else {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Agregacion Lote</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "ActLote.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<p>No se logro actualizar el Producto, Serás dirigido automáticamente en cinco segundos nuevamente al formulario de actualizacion. En caso contrario, puedes salir al menu principal, haciendo click <a href=" + "indexMainMenu.html" + ">Aquí</a></p>");
                        out.println("</body>");
                        out.println("</html>");
                    }

                    ;
                case 12:

                    int IdUserToDelete = Integer.parseInt(request.getParameter("IdUserDel"));
                    b = service.borrarUser(connection, IdUserToDelete);

                    if (b == true) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Eliminar Usuario</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "indexMainMenu.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Se elimino correctamente el Usuario: " + IdUserToDelete + "</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    } else {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Eliminar Usuario</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "EliminarUser.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<p>No se logro eliminar el Usuario, Serás dirigido automáticamente en cinco segundos nuevamente al formulario de actualizacion. En caso contrario, puedes salir al menu principal, haciendo click <a href=" + "indexMainMenu.html" + ">Aquí</a></p>");
                        out.println("</body>");
                        out.println("</html>");
                    }

                    ;
                case 13:
                    //Borrar Lote No es Permitido
                    ;
                case 14:

                    int IdItemSacar = Integer.parseInt(request.getParameter("IdProdDelete"));
                    int CantidadSacar = Integer.parseInt(request.getParameter("CantProdDelete"));
                    b = service.sacarItem(connection, IdItemSacar, CantidadSacar);
                    if (b == true) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Eliminar Usuario</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "indexMainMenu.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Se sacaron de bodega: " + CantidadSacar + " Del producto: " + IdItemSacar + "</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    } else {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Eliminar Usuario</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "EliminarUser.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<p>No se logro extraer de bodega, Serás dirigido automáticamente en cinco segundos nuevamente al formulario de actualizacion. En caso contrario, puedes salir al menu principal, haciendo click <a href=" + "indexMainMenu.html" + ">Aquí</a></p>");
                        out.println("</body>");
                        out.println("</html>");
                    }

                    ;
                case 15:
                    int IdItemIn = Integer.parseInt(request.getParameter("IdProdIn"));
                    int Canti = Integer.parseInt(request.getParameter("CantProdIn"));
                    b = service.agregarItem(connection, IdItemIn, Canti);

                    if (b == true) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Eliminar Usuario</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "indexMainMenu.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Se agrego correctamente el item: " + IdItemIn + " a la bodega" + "</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    } else {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Eliminar Usuario</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "EliminarUser.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<p>No se logro agregar a la bodega, Serás dirigido automáticamente en cinco segundos nuevamente al formulario de actualizacion. En caso contrario, puedes salir al menu principal, haciendo click <a href=" + "indexMainMenu.html" + ">Aquí</a></p>");
                        out.println("</body>");
                        out.println("</html>");
                    }

                    ;

                case 16:
                    if (opcion==16){
                    ArrayList<Integer> idsUsersPDF = new ArrayList<>();
                    ArrayList<String> NombresUsersPDF = new ArrayList<>();

                    rs = service.ListaGeneral(connection, 4);

                    while (rs.next()) {
                        idsUsersPDF.add(rs.getInt(1));
                        NombresUsersPDF.add(rs.getString(3));
                    }
                    service.GenerarPDF(connection, idsUsersPDF, NombresUsersPDF,4);

                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Eliminar Usuario</title>");
                    out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "indexMainMenu.html" + ">");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Se genero correctamente el PDF" + "</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    }
                    ;
                case 17:
                    if(opcion==17){
                    ArrayList<Integer> idsUsersPDF2 = new ArrayList<>();
                    ArrayList<String> NombresUsersPDF2 = new ArrayList<>();

                    rs = service.ListaGeneral(connection, 5);

                    while (rs.next()) {
                        idsUsersPDF2.add(rs.getInt(1));
                        NombresUsersPDF2.add(rs.getString(2));
                    }
                    service.GenerarPDF(connection, idsUsersPDF2, NombresUsersPDF2,5);

                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Eliminar Usuario</title>");
                    out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "indexMainMenu.html" + ">");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Se genero correctamente el PDF" + "</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    }
                    ;
                case 18:
                    if(opcion==18){
                    ArrayList<Integer> idsUsersPDF2 = new ArrayList<>();
                    ArrayList<String> NombresUsersPDF2 = new ArrayList<>();

                    rs = service.ListaGeneral(connection, 6);

                    while (rs.next()) {
                        idsUsersPDF2.add(rs.getInt(3));
                        NombresUsersPDF2.add(rs.getString(4));
                    }
                    service.GenerarPDF(connection, idsUsersPDF2, NombresUsersPDF2,6);

                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Eliminar Usuario</title>");
                    out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "indexMainMenu.html" + ">");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Se genero correctamente el PDF" + "</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    }
                    ;

                case 100:
                    if (opcion == 100) {
                        eaa.LogOut();
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Cerrando Sesion</title>");
                        out.println("<meta http-equiv=" + "Refresh" + " content=" + "3;url=" + "index.html" + ">");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<center>");
                        out.println("<h1>Cerrando Sesion...</h1>");
                        out.println("</center>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    ;
            }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Servlet_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Servlet_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
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
