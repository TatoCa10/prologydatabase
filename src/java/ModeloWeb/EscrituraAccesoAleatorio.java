package ModeloWeb;

import ModeloWeb.Controlador;
import ModeloWeb.NodoArbol;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

//import org.fluttercode.datafactory.*;
//import org.fluttercode.datafactory.impl.DataFactory;
public class EscrituraAccesoAleatorio {

    public RandomAccessFile raf;
    public RandomAccessFile raf2;
    public RandomAccessFile raf3;
    File RegistroUser = new File("RegistroUser.txt");
    File RegistroActividad = new File("RegistroActividad.txt");
    File FicheroUser = new File("Users.txt");
    File FicheroInventario = new File("Productos.txt");
    File LotesPorId = new File("LotesPorId.txt");
    int temp;
    Fecha Date = new Fecha();

//    EscrituraAccesoAleatorio EaA = new EscrituraAccesoAleatorio();
    //DataFactory data;
//    controlador.Controlador ArbolAdm = new Controlador();
//    controlador.Controlador ArbolAdm2 = new Controlador();
//    controlador.Controlador ArbolAdm3 = new Controlador();
//    NodoArbol arbol = null;
//    NodoArbol arbol2 = null;
//    BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
    public void registrarUser(int id, String pass, String Nombre, String Apellido, String Correo, int Telefono) throws FileNotFoundException, IOException {

        this.raf = new RandomAccessFile(FicheroUser, "rw");

        //System.out.println("Ingrese nuevo ID: ");
        //int id = teclado.read();
        boolean check = true;
        while (check) {

            long pos = raf.length();

            if (!ExistenciaUser(id)) {

                raf.seek(pos);
                raf.writeInt(id); //Agrega el ID al User

                raf.seek(pos + 4);

                //System.out.println("Ingrese su Password: ");
                String PassString = pass;
                char[] PassMedido = PassString.toCharArray();
                int TamañoPass = PassMedido.length * 2;

                if (TamañoPass < 40) {
                    raf.writeUTF(PassString);      //Agrega el Nombre al User
                    raf.seek(raf.getFilePointer() + (40 - TamañoPass));
                } else {
                    char[] PassCortado = null;
                    for (int i = 0; i < 20; i++) {
                        PassCortado[i] = PassMedido[i];  // Agrega solo los bytes posibles en el campo de Nombre
                    }
                    String PassStringCortado = new String(PassCortado);
                    int TamañoPassCortado = PassCortado.length * 2;
                    raf.writeUTF(PassStringCortado);
                    raf.seek(raf.getFilePointer() + (40 - TamañoPassCortado));
                }

                raf.seek(pos + 4 + 40);
                //System.out.println("Ingrese su Nombre: ");
                String NombreString = Nombre;
                char[] NombreMedido = NombreString.toCharArray();
                int TamañoNombre = NombreMedido.length * 2;

                if (TamañoNombre < 40) {
                    raf.writeUTF(NombreString);      //Agrega el Nombre al User
                    raf.seek(raf.getFilePointer() + (40 - TamañoNombre));
                } else {
                    char[] NombreCortado = null;
                    for (int i = 0; i < 20; i++) {
                        NombreCortado[i] = NombreMedido[i];  // Agrega solo los bytes posibles en el campo de Nombre
                    }
                    String NombreStringCortado = new String(NombreCortado);
                    int TamañoNombreCortado = NombreCortado.length * 2;
                    raf.writeUTF(NombreStringCortado);
                    raf.seek(raf.getFilePointer() + (40 - TamañoNombreCortado));
                }

                raf.seek(pos + 4 + 40 + 40);
                //System.out.println("Ingrese su Apellido: ");
                String ApellidoString = Apellido;
                char[] ApellidoMedido = ApellidoString.toCharArray();
                int TamañoApellido = ApellidoMedido.length * 2;

                if (TamañoApellido < 40) {
                    raf.writeUTF(ApellidoString);  //Agrega el Apellido al User
                    raf.seek(raf.getFilePointer() + (40 - TamañoApellido));
                } else {
                    char[] ApellidoCortado = null;
                    for (int i = 0; i < 20; i++) {
                        ApellidoCortado[i] = ApellidoMedido[i];  // Agrega solo los bytes posibles en el campo de Apellido
                    }
                    String ApellidoStringCortado = new String(ApellidoCortado);
                    int TamañoApellidoCortado = ApellidoCortado.length * 2;
                    raf.writeUTF(ApellidoStringCortado);
                    raf.seek(raf.getFilePointer() + (40 - TamañoApellidoCortado));
                }

                raf.seek(pos + 4 + 40 + 40 + 40);
                //System.out.println("Ingrese su Correo: ");
                String CorreoString = Correo;
                char[] CorreoMedido = CorreoString.toCharArray();
                int TamañoCorreo = CorreoMedido.length * 2;

                if (TamañoCorreo < 40) {
                    raf.writeUTF(CorreoString);  //Agrega el Correo al User
                    raf.seek(raf.getFilePointer() + (40 - TamañoCorreo));
                } else {
                    char[] CorreoCortado = null;
                    for (int i = 0; i < 20; i++) {
                        CorreoCortado[i] = CorreoMedido[i];  // Agrega solo los bytes posibles en el campo de Correo
                    }
                    String CorreoStringCortado = new String(CorreoCortado);
                    int TamañoCorreoCortado = CorreoCortado.length * 2;
                    raf.writeUTF(CorreoStringCortado);
                    raf.seek(raf.getFilePointer() + (40 - TamañoCorreoCortado));
                }

                raf.seek(pos + 4 + 40 + 40 + 40 + 40);
                //System.out.println("Ingrese su Telefono");

                raf.writeInt(Telefono); //Agrega Telefono del User
                raf.seek(pos + 168);
                raf.writeBoolean(true);

                System.out.println("Agregado con exito ");
                check = false;
            } else {
                System.out.println("ID ya registrado, intenta con otro.");
                check = false;
            }
        }
    }

    public long buscarUserDesdeArchivo(int id) throws FileNotFoundException, IOException {

        this.raf = new RandomAccessFile(FicheroUser, "rw");

        long i = 0;
//        int Numero=1;
        while (i < raf.length()) {
            raf.seek(i);
            int ID = raf.readInt();
            if (id == ID) {
                raf.seek(i);
                System.out.println("Lo encontre");
                long pos = raf.getFilePointer();
                return pos;
            } else {
                System.out.println("Buscando...");
                i = i + 169;
//                Numero++;
            }
        }
        System.out.println("ID No Existe");

        return 0;
    }

    public void LeerArchivoUsers(int id) throws IOException {
        this.raf = new RandomAccessFile(FicheroUser, "rw");
        long pos = buscarUserDesdeArchivo(id);
        raf.seek(pos + 4 + 40 + 40 + 40 + 40 + 4);
        boolean existe = raf.readBoolean(); //Lee Existencia
        if (existe) {

            raf.seek(pos);
            System.out.println("Posicion: " + raf.getFilePointer());
            int aidi = raf.readInt();
            System.out.println("ID: " + aidi); //Lee ID
            raf.seek(pos + 4 + 40);
            String name = raf.readUTF();
            System.out.println(name); //Lee Nombre
            raf.seek(pos + 4 + 40 + 40);
            String last = raf.readUTF();
            System.out.println(last); //Lee Apellido
            raf.seek(pos + 4 + 40 + 40 + 40);
            String email = raf.readUTF();
            System.out.println(email); //Lee Correo
            raf.seek(pos + 4 + 40 + 40 + 40 + 40);
            int tl = raf.readInt();
            System.out.println(tl); //Lee Telefono

        } else {
            System.out.println("Usuario NO Encontrado");
        }
    }

    public void ActualizarArchivoUsers(int id, int New_Id, String Nombre, String Apellido, String Correo, int Telefono) throws IOException {
        this.raf = new RandomAccessFile(FicheroUser, "rw");
        long pos = buscarUserDesdeArchivo(id);
        raf.seek(pos + 128);
        boolean check = raf.readBoolean();

        if (check == true) {
            raf.seek(pos);
            raf.writeInt(New_Id);// ID

            raf.seek(pos + 4);
            char[] NombreActMedido = Nombre.toCharArray();
            int TamañoNombreAct = NombreActMedido.length * 2;

            if (TamañoNombreAct < 40) {
                raf.writeUTF(Nombre);  //Agrega el Nombre nuevo al User
                raf.seek(raf.getFilePointer() + (40 - TamañoNombreAct));
            } else {
                char[] NombreActCortado = null;
                for (int i = 0; i < 20; i++) {
                    NombreActCortado[i] = NombreActMedido[i];  // Agrega solo los bytes posibles en el campo de nombre del User
                }
                String NombreActStringCortado = new String(NombreActCortado);
                int TamañoNombreActCortado = NombreActCortado.length * 2;
                raf.writeUTF(NombreActStringCortado);
                raf.seek(raf.getFilePointer() + (40 - TamañoNombreActCortado));
            }

            raf.seek(pos + 4 + 40);
            char[] ApellidoActMedido = Apellido.toCharArray();
            int TamañoApellidoAct = ApellidoActMedido.length * 2;

            if (TamañoApellidoAct < 40) {
                raf.writeUTF(Apellido);  //Agrega el Apellido nuevo al User
                raf.seek(raf.getFilePointer() + (40 - TamañoApellidoAct));
            } else {
                char[] ApellidoActCortado = null;
                for (int i = 0; i < 20; i++) {
                    ApellidoActCortado[i] = ApellidoActMedido[i];  // Agrega solo los bytes posibles en el campo de Apellido del User
                }
                String ApellidoActStringCortado = new String(ApellidoActCortado);
                int TamañoApellidoActCortado = ApellidoActCortado.length * 2;
                raf.writeUTF(ApellidoActStringCortado);
                raf.seek(raf.getFilePointer() + (40 - TamañoApellidoActCortado));
            }

            raf.seek(pos + 4 + 40 + 40);
            char[] CorreoActMedido = Correo.toCharArray();
            int TamañoCorreoAct = CorreoActMedido.length * 2;

            if (TamañoCorreoAct < 40) {
                raf.writeUTF(Correo);  //Agrega el Correo nuevo al User
                raf.seek(raf.getFilePointer() + (40 - TamañoCorreoAct));
            } else {
                char[] ActCortado = null;
                for (int i = 0; i < 20; i++) {
                    ActCortado[i] = CorreoActMedido[i];  // Agrega solo los bytes posibles en el campo de Correo del User
                }
                String ActStringCortado = new String(ActCortado);
                int TamañoActCortado = ActCortado.length * 2;
                raf.writeUTF(ActStringCortado);
                raf.seek(raf.getFilePointer() + (40 - TamañoActCortado));
            }
            raf.seek(pos + 4 + 40 + 40 + 40);
            raf.writeInt(Telefono);
            raf.seek(pos + 128);
            raf.writeBoolean(true);
        } else {
            System.out.println("El dato no se puede actaulizar, No existe.");
        }
    }

    public ArrayList RecorrerUserId() throws IOException {
        this.raf = new RandomAccessFile(FicheroUser, "rw");
        ArrayList<Integer> ids = new ArrayList<>();
        raf.seek(0);
        int Numero = 1;
        int corredor = 0;
        int Id;
        Long Pos;

        while (corredor < raf.length()) {
            Pos = raf.getFilePointer();
            Id = raf.readInt();
            raf.seek(129 * Numero);
            //ArbolAdm.insertarNodo( Id, Pos);
            System.out.println(Id + " ->" + Pos);
            ids.add(Id);
            corredor = corredor + 129;
            Numero++;
        }
//        System.out.println("------------------------|||||||||||||||||||-----------------------------");
//        ArbolAdm.inOrder();
        return ids;
    }

    public ArrayList RecorrerUserPos() throws IOException {
        this.raf = new RandomAccessFile(FicheroUser, "rw");
        ArrayList<Long> Poses = new ArrayList<>();
        raf.seek(0);
        int Numero = 1;
        int corredor = 0;
        int Id;
        Long Pos;

        while (corredor < raf.length()) {
            Pos = raf.getFilePointer();
            Id = raf.readInt();
            raf.seek(129 * Numero);
            //ArbolAdm.insertarNodo( Id, Pos);
            System.out.println(Id + " ->" + Pos);
            Poses.add(Pos);
            corredor = corredor + 129;
            Numero++;
        }
//        System.out.println("------------------------|||||||||||||||||||-----------------------------");
//        ArbolAdm.inOrder();
        return Poses;
    }

    public void EliminarUser(int id) throws IOException {
        this.raf = new RandomAccessFile(FicheroUser, "rw");
        long pos = buscarUserDesdeArchivo(id);
        raf.seek(pos);
        System.out.println("User Eliminado ID:" + raf2.readInt());
        raf.seek(pos + 128);
        raf.writeBoolean(false);

    }

    public boolean ExistenciaUser(int id) throws FileNotFoundException, IOException {
        this.raf = new RandomAccessFile(FicheroUser, "rw");
        raf.seek(0);
        long i = 0;

        while (i < raf.length()) {
            int ID = raf.readInt();
            if (ID == id) {
                return true;
            } else {
                i = i + 169;
            }
        }
        System.out.println("No existe");
        return false;
    }

    public boolean LogIn(int id, String Pass) throws IOException {
        this.raf = new RandomAccessFile(FicheroUser, "rw");
        String fecha = "";
        fecha = Date.getDate().toString();
        int reg = 1;
        boolean check = true;
        long pos = buscarUserDesdeArchivo(id);
        raf.seek(pos);
        while (check) {
            int ID = raf.readInt();
            if (ID == id) {
                System.out.println(" ID Correcto");
                raf.seek(pos + 4);
                String contra = raf.readUTF();
                if (Pass.equals(contra)) {
                    GenerarRegistroUser(RegistroUser, id, fecha, reg);
                    temp = id;
                    return true;
                } else {
                    System.out.println("Contraseña Incorrecta");
                    return false;
                }

            } else {
                System.out.println("ID Incorrecto");
                return false;
            }

        }
        return true;
    }

    public boolean LogOut() throws IOException {
        String fecha = "";
        fecha = Date.getDate().toString();
        int reg = 1;
        GenerarRegistroUser(RegistroUser, temp, fecha, reg);
        temp = 0;
        return true;
    }

    public File NuevoLote(int IdLote, int IdUser) throws IOException {

        this.raf2 = new RandomAccessFile(FicheroInventario, "rw");

        if (!Existencia(IdLote, FicheroInventario)) {
            int idLote = IdLote;
            long posLote = raf2.getFilePointer();

            raf2.writeInt(idLote);
            raf2.seek(posLote + 4);
            raf2.writeBoolean(true);
            String nombreLote = "";
            nombreLote = Integer.toString(IdLote);
            File ArchivoCreado = new File(nombreLote + ".txt");
//            CargarIDLote(idLote, ArchivoCreado);
            this.raf2 = new RandomAccessFile(ArchivoCreado, "rw");
            raf2.writeInt(IdLote);
            String what = "Nuevo Lote Creado";
            this.GenerarRegistroActividad(RegistroActividad, idLote, idLote, 1, what);
            this.GenerarRegistoLotesPorUsuario(IdUser, idLote);
            return ArchivoCreado;
        }
        System.out.println("Este Lote ya Existe");
        return null;

    }

    public long BuscarLote(int id, File Archivo) throws FileNotFoundException, IOException {

        this.raf2 = new RandomAccessFile(Archivo, "rw");

        long i = 0;
//        int Numero=1;
        while (i < raf2.length()) {
            raf2.seek(i);
            int ID = raf2.readInt();
            if (id == ID) {
                raf2.seek(i);
                System.out.println("Lo encontre");
                long pos = raf2.getFilePointer();

                return pos;
            } else {
                System.out.println("Buscando...");
                i = i + 5;
//                Numero++;
            }
        }
        System.out.println("ID No Existe");

        return 0;

    }

    public ArrayList ListarLoteId() throws FileNotFoundException, IOException {
        ArrayList<Integer> idsLote = new ArrayList<>();
        this.raf2 = new RandomAccessFile(FicheroInventario, "rw");
        raf2.seek(0);
        int Numero = 1;
        int corredor = 0;
        int Id;
        Long Pos;

        while (corredor < raf2.length()) {
            Pos = raf2.getFilePointer();
            Id = raf2.readInt();
            raf2.seek(5 * Numero);
            //ArbolAdm.insertarNodo( Id, Pos);
            System.out.println(Id + " ->" + Pos);
            idsLote.add(Id);
            corredor = corredor + 5;
            Numero++;
        }
        return idsLote;
    }

    public ArrayList ListarLotePos() throws FileNotFoundException, IOException {
        ArrayList<Long> PosesArchivo = new ArrayList<>();
        this.raf2 = new RandomAccessFile(FicheroInventario, "rw");
        raf2.seek(0);
        int Numero = 1;
        int corredor = 0;
        int Id;
        Long Pos;

        while (corredor < raf2.length()) {
            Pos = raf2.getFilePointer();
            Id = raf2.readInt();
            raf.seek(5 * Numero);
            //ArbolAdm.insertarNodo( Id, Pos);
            System.out.println(Id + " ->" + Pos);
            PosesArchivo.add(Pos);
            corredor = corredor + 5;
            Numero++;
        }
        return PosesArchivo;
    }

    public boolean Existencia(int id, File archivo) throws FileNotFoundException, IOException {

        this.raf2 = new RandomAccessFile(archivo, "rw");
        raf2.seek(0);
        long i = 0;

        while (i < raf2.length()) {
            int ID = raf2.readInt();
            if (ID == id) {
                return true;
            } else {
                i = i + 5;
            }
        }
        System.out.println("No existe");
        return false;
    }

    public void insertarProductos(File nombreArchivo, int Cantidad, int IDLote, String NombreProd, String tipo, String Proveedor, int Precio, int razon) throws FileNotFoundException, IOException {

        this.raf2 = new RandomAccessFile(nombreArchivo, "rw");
        //this.data = new DataFactory();

        boolean check = true;
        while (check) {
            if (Existencia(IDLote, nombreArchivo) && raf2.length() == 4) {

                long posLote = raf2.getFilePointer();

                //raf2.writeInt(IDLote);       //Agrega ID Lote
                raf2.writeInt(Cantidad); //Agrega Cantidad al Lote
                raf2.writeBoolean(true);

                for (int x = 0; x < Cantidad; x++) {

                    long pos = raf2.getFilePointer();
                    int id = x;

                    String what = "Inserto Producto";
                    GenerarRegistroActividad(RegistroActividad, id, IDLote, razon, what);

                    this.raf2 = new RandomAccessFile(nombreArchivo, "rw");
                    raf2.seek(pos);
                    raf2.writeInt(id); //Agrega el ID al producto

                    raf2.seek(pos + 4);
                    //nombre
                    String NombreString = NombreProd;
                    char[] NombreMedido = NombreString.toCharArray();
                    int TamañoNombre = NombreMedido.length * 2;

                    if (TamañoNombre < 40) {
                        raf2.writeUTF(NombreString);      //Agrega el Nombre al Producto
                        raf2.seek(raf2.getFilePointer() + (40 - TamañoNombre));
                    } else {
                        char[] NombreCortado = null;
                        for (int i = 0; i < 20; i++) {
                            NombreCortado[i] = NombreMedido[i];  // Agrega solo los bytes posibles en el campo de Nombre del Prod
                        }
                        String NombreStringCortado = new String(NombreCortado);
                        int TamañoNombreCortado = NombreCortado.length * 2;
                        raf2.writeUTF(NombreStringCortado);
                        raf2.seek(raf2.getFilePointer() + (40 - TamañoNombreCortado));
                    }

                    raf2.seek(pos + 4 + 40);
                    //Tipo
                    String TipoString = tipo;
                    char[] TipoMedido = TipoString.toCharArray();
                    int TamañoTipo = TipoMedido.length * 2;

                    if (TamañoTipo < 40) {
                        raf2.writeUTF(TipoString);  //Agrega el Tipo al Prod
                        raf2.seek(raf2.getFilePointer() + (40 - TamañoTipo));
                    } else {
                        char[] TipoCortado = null;
                        for (int i = 0; i < 20; i++) {
                            TipoCortado[i] = TipoMedido[i];  // Agrega solo los bytes posibles en el campo de Tipo del Prod
                        }
                        String TipoStringCortado = new String(TipoCortado);
                        int TamañoTipoCortado = TipoCortado.length * 2;
                        raf2.writeUTF(TipoStringCortado);
                        raf2.seek(raf2.getFilePointer() + (40 - TamañoTipoCortado));
                    }

                    raf2.seek(pos + 4 + 40 + 40);
                    //Proveedor
                    String ProvString = Proveedor;
                    char[] ProvMedido = ProvString.toCharArray();
                    int TamañoProv = ProvMedido.length * 2;

                    if (TamañoProv < 40) {
                        raf2.writeUTF(ProvString);  //Agrega el Proveedor al Prod
                        raf2.seek(raf2.getFilePointer() + (40 - TamañoProv));
                    } else {
                        char[] ProvCortado = null;
                        for (int i = 0; i < 20; i++) {
                            ProvCortado[i] = ProvMedido[i];  // Agrega solo los bytes posibles en el campo de Apellido del Prod
                        }
                        String ProvStringCortado = new String(ProvCortado);
                        int TamañoProvCortado = ProvCortado.length * 2;
                        raf2.writeUTF(ProvStringCortado);
                        raf2.seek(raf2.getFilePointer() + (40 - TamañoProvCortado));
                    }

                    raf2.seek(pos + 4 + 40 + 40 + 40);
                    raf2.writeInt(Precio); //Agrega Precio al Prod
                    raf2.seek(pos + 128);
                    raf2.writeBoolean(true);
                    System.out.println("Agregado con exito");
                    check = false;
                }
            } else if (Existencia(IDLote, nombreArchivo) && raf2.length() > 4) {

                raf2.seek(4);
                int cant = raf2.readInt();
                raf2.seek(4);
                raf2.writeInt(cant + Cantidad);
                raf2.seek(9 + (129 * cant) - 129);
                int idant = raf2.readInt();
                raf2.seek(9 + (129 * cant));

                for (int x = 0; x < Cantidad; x++) {

                    long pos = raf2.getFilePointer();
                    int id = (idant + x + 1);

                    String what = "Inserto Producto";

                    GenerarRegistroActividad(RegistroActividad, id, IDLote, razon, what);

                    this.raf2 = new RandomAccessFile(nombreArchivo, "rw");
                    raf2.seek(pos);
                    raf2.writeInt(id); //Agrega el ID al producto

                    raf2.seek(pos + 4);
                    String NombreString = NombreProd;
                    char[] NombreMedido = NombreString.toCharArray();
                    int TamañoNombre = NombreMedido.length * 2;

                    if (TamañoNombre < 40) {
                        raf2.writeUTF(NombreString);      //Agrega el Nombre al Producto
                        raf2.seek(raf2.getFilePointer() + (40 - TamañoNombre));
                    } else {
                        char[] NombreCortado = null;
                        for (int i = 0; i < 20; i++) {
                            NombreCortado[i] = NombreMedido[i];  // Agrega solo los bytes posibles en el campo de Nombre del Prod
                        }
                        String NombreStringCortado = new String(NombreCortado);
                        int TamañoNombreCortado = NombreCortado.length * 2;
                        raf2.writeUTF(NombreStringCortado);
                        raf2.seek(raf2.getFilePointer() + (40 - TamañoNombreCortado));
                    }

                    raf2.seek(pos + 4 + 40);
                    String TipoString = tipo;
                    char[] TipoMedido = TipoString.toCharArray();
                    int TamañoTipo = TipoMedido.length * 2;

                    if (TamañoTipo < 40) {
                        raf2.writeUTF(TipoString);  //Agrega el Tipo al Prod
                        raf2.seek(raf2.getFilePointer() + (40 - TamañoTipo));
                    } else {
                        char[] TipoCortado = null;
                        for (int i = 0; i < 20; i++) {
                            TipoCortado[i] = TipoMedido[i];  // Agrega solo los bytes posibles en el campo de Tipo del Prod
                        }
                        String TipoStringCortado = new String(TipoCortado);
                        int TamañoTipoCortado = TipoCortado.length * 2;
                        raf2.writeUTF(TipoStringCortado);
                        raf2.seek(raf2.getFilePointer() + (40 - TamañoTipoCortado));
                    }

                    raf2.seek(pos + 4 + 40 + 40);
                    String ProvString = Proveedor;
                    char[] ProvMedido = ProvString.toCharArray();
                    int TamañoProv = ProvMedido.length * 2;

                    if (TamañoProv < 40) {
                        raf2.writeUTF(ProvString);  //Agrega el Proveedor al Prod
                        raf2.seek(raf2.getFilePointer() + (40 - TamañoProv));
                    } else {
                        char[] ProvCortado = null;
                        for (int i = 0; i < 20; i++) {
                            ProvCortado[i] = ProvMedido[i];  // Agrega solo los bytes posibles en el campo de Apellido del Prod
                        }
                        String ProvStringCortado = new String(ProvCortado);
                        int TamañoProvCortado = ProvCortado.length * 2;
                        raf2.writeUTF(ProvStringCortado);
                        raf2.seek(raf2.getFilePointer() + (40 - TamañoProvCortado));
                    }

                    raf2.seek(pos + 4 + 40 + 40 + 40);
                    raf2.writeInt(Precio); //Agrega Precio al Prod
                    raf2.seek(pos + 4 + 40 + 40 + 40 + 4);
                    raf2.writeBoolean(true);
                    System.out.println("Ag Exito");
                    check = false;
                }

            } else if (!Existencia(IDLote, nombreArchivo)) {
                System.out.println("El Lote NO Existente");
                check = false;
            }
        }
    }

    public void LeerArchivoProductos(int id, File archivo) throws IOException {
        this.raf2 = new RandomAccessFile(archivo, "rw");

        long pos = buscarProdDesdeArchivo(id, archivo);

        raf2.seek(pos + 128);
        boolean existe = raf2.readBoolean(); //Lee Existencia
        if (existe) {

            raf2.seek(pos);
            System.out.println(raf2.getFilePointer());
            int aidi = raf2.readInt();
            System.out.println(aidi);
            raf2.seek(pos + 4);
            String name = raf2.readUTF();
            System.out.println(name); //Lee Nombre
            raf2.seek(pos + 4 + 40);
            String type = raf2.readUTF();
            System.out.println(type); //Lee Tipo
            raf2.seek(pos + 4 + 40 + 40);
            String prov = raf2.readUTF();
            System.out.println(prov); //Lee Proveedor
            raf2.seek(pos + 4 + 40 + 40 + 40);
            int pre = raf2.readInt();
            System.out.println(pre); //Lee Precio

            System.out.println("----- -------- ----");
        } else {
            System.out.println("Producto NO Encontrado");

        }
    }

    public long buscarProdDesdeArchivo(int id, File archivo) throws FileNotFoundException, IOException {

        this.raf2 = new RandomAccessFile(archivo, "rw");

        long i = 9;
//        int Numero=1;
        while (i < raf2.length()) {
            raf2.seek(i);
            int ID = raf2.readInt();
            if (id == ID) {
                raf2.seek(i);
                System.out.println("Lo encontre");
                long pos = raf2.getFilePointer();

                return pos;
            } else {
                System.out.println("Buscando...");
                i = i + 129;
//                Numero++;
            }
        }
        System.out.println("ID No Existe");

        return 0;
    }

    public void LeerLoteProductos(int id, File archivo) throws IOException {
        this.raf2 = new RandomAccessFile(archivo, "rw");
        long pos = BuscarLote(id, archivo);
        raf2.seek(pos + 8);
        boolean existe = raf2.readBoolean(); //Lee Existencia
        if (existe) {

            raf2.seek(pos);
            System.out.println(raf2.getFilePointer());
            int aidi = raf2.readInt();
            System.out.println(aidi); //Lee ID
            //raf2.seek(pos + 4);
            int cant = raf2.readInt();
            System.out.println(cant); //Lee Cantidad

        } else {
            System.out.println("Lote NO Encontrado");

        }
    }

    public void ActualizarArchivoProductos(File archivo, int id, int IDLote, int New_Id, String Nombre, String Tipo, String Proveedor, int Precio, int razon) throws IOException {
        this.raf2 = new RandomAccessFile(archivo, "rw");

        String what = "Actualizo Producto";

        GenerarRegistroActividad(RegistroActividad, New_Id, IDLote, razon, what);

        this.raf2 = new RandomAccessFile(archivo, "rw");
        long pos = buscarProdDesdeArchivo(id, archivo);
        raf2.seek(pos + 128);
        boolean check = raf2.readBoolean();
        if (check == true) {
            raf2.seek(pos);
            raf2.writeInt(New_Id);

            raf2.seek(pos + 4);
            char[] NombreActMedido = Nombre.toCharArray();
            int TamañoNombreAct = NombreActMedido.length * 2;

            if (TamañoNombreAct < 40) {
                raf2.writeUTF(Nombre);  //Agrega el Nombre nuevo al Prod
                raf2.seek(raf2.getFilePointer() + (40 - TamañoNombreAct));
            } else {
                char[] NombreActCortado = null;
                for (int i = 0; i < 20; i++) {
                    NombreActCortado[i] = NombreActMedido[i];  // Agrega solo los bytes posibles en el campo de Nombre del profesor
                }
                String NombreActStringCortado = new String(NombreActCortado);
                int TamañoNombreActCortado = NombreActCortado.length * 2;
                raf2.writeUTF(NombreActStringCortado);
                raf2.seek(raf2.getFilePointer() + (40 - TamañoNombreActCortado));
            }

            raf2.seek(pos + 4 + 40);
            char[] TipoActMedido = Tipo.toCharArray();
            int TamañoTipoAct = TipoActMedido.length * 2;

            if (TamañoTipoAct < 40) {
                raf2.writeUTF(Tipo);  //Agrega el Tipo al Prod
                raf2.seek(raf2.getFilePointer() + (40 - TamañoTipoAct));
            } else {
                char[] ActCortado = null;
                for (int i = 0; i < 20; i++) {
                    ActCortado[i] = TipoActMedido[i];  // Agrega solo los bytes posibles en el campo de Tipo del Prod
                }
                String ActStringCortado = new String(ActCortado);
                int TamañoActCortado = ActCortado.length * 2;
                raf2.writeUTF(ActStringCortado);
                raf2.seek(raf2.getFilePointer() + (40 - TamañoActCortado));
            }

            raf2.seek(pos + 4 + 40 + 40);
            char[] ProvActMedido = Proveedor.toCharArray();
            int TamañoProvAct = ProvActMedido.length * 2;

            if (TamañoProvAct < 40) {
                raf2.writeUTF(Proveedor);  //Agrega el Proveedpr al Prod
                raf2.seek(raf2.getFilePointer() + (40 - TamañoProvAct));
            } else {
                char[] ActCortado = null;
                for (int i = 0; i < 20; i++) {
                    ActCortado[i] = ProvActMedido[i];  // Agrega solo los bytes posibles en el campo de Proveedor del Prod
                }
                String ActStringCortado = new String(ActCortado);
                int TamañoActCortado = ActCortado.length * 2;
                raf2.writeUTF(ActStringCortado);
                raf2.seek(raf2.getFilePointer() + (40 - TamañoActCortado));
            }

            raf2.seek(pos + 4 + 40 + 40 + 40);
            raf2.writeInt(Precio);
            raf2.seek(pos + 128);
            raf2.writeBoolean(true);
//            String what = "Actualizo Producto";
//
//            GenerarRegistroActividad(RegistroActividad, New_Id, IDLote, razon, what);

        } else {
            System.out.println("El dato no se puede actualizar, No existe.");
        }
    }

    public void ActualizarLote(File nombreArchivo, int IDLote, int newId, int Cantidad, int razon) throws IOException {

        this.raf2 = new RandomAccessFile(FicheroInventario, "rw");

        long pos = BuscarLote(IDLote, FicheroInventario);
        raf2.seek(pos + 4);
        boolean check = raf2.readBoolean();
        if (check) {
            raf2.seek(pos);

            raf2.writeInt(newId);       //Agrega ID Lote ARCHIVO MAESTRO

            this.raf2 = new RandomAccessFile(nombreArchivo, "rw");//SE REFERENCIA AL ARCHIVO DEL LOTE
            String what = "Actualizo Lote";

            GenerarRegistroActividad(RegistroActividad, newId, IDLote, razon, what);

            this.raf2 = new RandomAccessFile(nombreArchivo, "rw");//VUELVE A REFERENCIA AL ARCHIVO DEL LOTE

            long pos2 = BuscarLote(IDLote, nombreArchivo);
            raf2.seek(pos2);
            raf2.writeInt(newId);
            raf2.seek(pos2 + 4);
            raf2.writeInt(Cantidad); //Agrega Cantidad al Lote
            raf2.seek(pos2 + 4 + 4);
            raf2.writeBoolean(true);

        } else {
            System.out.println("El Lote no se puede actualizar, No existe.");
        }
    }

    public void EliminarProducto(File archivo, int id, int idLote, int razon) throws IOException {
        this.raf2 = new RandomAccessFile(archivo, "rw");

        String what = "Elimino Productos";

        GenerarRegistroActividad(RegistroActividad, id, idLote, razon, what);

        this.raf2 = new RandomAccessFile(archivo, "rw");

        long pos = buscarProdDesdeArchivo(id, archivo);
        long posLote = BuscarLote(id, archivo);
        raf2.seek(pos);
        System.out.println("Item Eliminado ID:" + raf2.readInt());
        raf2.seek(pos + 128);
        raf2.writeBoolean(false);
        raf2.seek(posLote + 4);
        int cant = (raf2.readInt() - 1);
        raf2.seek(posLote + 4);
        raf2.writeInt(cant);

    }

    public void RecuperarProducto(File archivo, int id, int idLote, int razon) throws IOException {

        this.raf2 = new RandomAccessFile(archivo, "rw");

        String what = "Recupero Productos";

        GenerarRegistroActividad(RegistroActividad, id, idLote, razon, what);

        this.raf2 = new RandomAccessFile(archivo, "rw");

        long pos = buscarProdDesdeArchivo(id, archivo);
        long posLote = BuscarLote(id, archivo);
        raf2.seek(pos);
        System.out.println("Item Recuperado ID:" + raf2.readInt());
        raf2.seek(pos + 128);
        raf2.writeBoolean(true);
        raf2.seek(posLote + 4);
        int cant = (raf2.readInt() + 1);
        raf2.seek(posLote + 4);
        raf2.writeInt(cant);

    }

    public void RecorrerProductos(File nombreArchivo) throws IOException {

        this.raf2 = new RandomAccessFile(nombreArchivo, "rw");
        raf2.seek(9);
        int Numero = 1;
        int corredor = 0;
        int Id;
        Long Pos;

        while (corredor < raf2.length()) {
            Pos = raf2.getFilePointer();
            Id = raf2.readInt();
            raf2.seek(9 + (129 * Numero));
            //ArbolAdm2.insertarNodo( Id, Pos);
            System.out.println(Id + " ->" + Pos);
            corredor = corredor + 129;
            Numero++;
        }
//        System.out.println("------------------------|||||||||||||||||||-----------------------------");
//        ArbolAdm2.inOrder();

    }

    public File BorrarLote(File archivo, int idlote, int razon) throws IOException {
        this.raf2 = new RandomAccessFile(FicheroInventario, "rw");
        long pos = BuscarLote(idlote, FicheroInventario);
        raf2.seek(pos + 4);
        raf2.writeBoolean(false);

        String what = "Elimino Lote";

        GenerarRegistroActividad(RegistroActividad, temp, idlote, razon, what);
        archivo.delete();

        return null;

    }

    public void GenerarRegistroUser(File archivo, int id, String Date, int tipo) throws FileNotFoundException, IOException {

        this.raf2 = new RandomAccessFile(archivo, "rw");

        long pos = raf2.length();
        raf2.seek(pos);
        raf2.writeInt(id);

        raf2.seek(pos + 4);

        char[] DateMedido = Date.toCharArray();
        int TamañoDate = DateMedido.length * 2;

        if (TamañoDate < 60) {
            raf2.writeUTF(Date);  //Agrega el Tipo al Prod
            raf2.seek(raf2.getFilePointer() + (60 - TamañoDate));
        } else {
            char[] Cortado = null;
            for (int i = 0; i < 30; i++) {
                Cortado[i] = DateMedido[i];  // Agrega solo los bytes posibles en el campo de Tipo del Prod
            }
            String StringCortado = new String(Cortado);
            int TamañoCortado = Cortado.length * 2;
            raf2.writeUTF(StringCortado);
            raf2.seek(raf2.getFilePointer() + (60 - TamañoCortado));
        }

        raf2.seek(pos + 4 + 60);
        raf2.writeInt(tipo);

    }

    public void GenerarRegistroActividad(File archivo, int idProd, int IdLote, int Razon, String tipo) throws FileNotFoundException, IOException {

        this.raf2 = new RandomAccessFile(archivo, "rw");

        long pos = raf2.length();
        raf2.seek(pos);
        raf2.writeInt(temp);

        raf2.seek(pos + 4);

        char[] TipoMedido = tipo.toCharArray();
        int TamañoTipo = TipoMedido.length * 2;

        if (TamañoTipo < 40) {
            raf2.writeUTF(tipo);  //Agrega el Tipo al Prod
            raf2.seek(raf2.getFilePointer() + (40 - TamañoTipo));
        } else {
            char[] Cortado = null;
            for (int i = 0; i < 20; i++) {
                Cortado[i] = TipoMedido[i];  // Agrega solo los bytes posibles en el campo de Tipo del Prod
            }
            String StringCortado = new String(Cortado);
            int TamañoCortado = Cortado.length * 2;
            raf2.writeUTF(StringCortado);
            raf2.seek(raf2.getFilePointer() + (40 - TamañoCortado));
        }

        raf2.seek(pos + 4 + 40);

        raf2.writeInt(idProd);
        raf2.seek(pos + 4 + 40 + 4);
        raf2.writeInt(IdLote);
        raf2.seek(pos + 4 + 40 + 4 + 4);
        raf2.writeInt(Razon);
        raf2.seek(pos + 4 + 40 + 4 + 4 + 4);

        String fecha = "";
        fecha = Date.getDate().toString();

        char[] FechaMedido = fecha.toCharArray();
        int TamañoFecha = FechaMedido.length * 2;

        if (TamañoFecha < 60) {
            raf2.writeUTF(fecha);  //Agrega el Tipo al Prod
            raf2.seek(raf2.getFilePointer() + (60 - TamañoFecha));
        } else {
            char[] Cortado = null;
            for (int i = 0; i < 30; i++) {
                Cortado[i] = FechaMedido[i];  // Agrega solo los bytes posibles en el campo de Tipo del Prod
            }
            String StringCortado = new String(Cortado);
            int TamañoCortado = Cortado.length * 2;
            raf2.writeUTF(StringCortado);
            raf2.seek(raf2.getFilePointer() + (60 - TamañoCortado));
        }
    }

    public void GenerarRegistoLotesPorUsuario(int IdUser, int IdLote) throws FileNotFoundException, IOException {
        this.raf3 = new RandomAccessFile(LotesPorId, "rw");

        int idLote = IdLote;
//        long posLote = raf3.getFilePointer();
        long posLote = raf3.length();

        raf3.writeInt(IdUser);
        raf3.seek(posLote + 4);
        raf3.writeInt(idLote);
    }

    public ArrayList ListarLotesPorUsuarioId(int IdUsuario) throws FileNotFoundException, IOException {

        this.raf3 = new RandomAccessFile(LotesPorId, "rw");
        ArrayList<Integer> IdsUsuarios = new ArrayList<>();
        int a = 0;
        int IdUsuarioArchivo = 0;
        int IdLoteArchivo = 0;

        while (a < raf3.length()) {
            raf3.seek(a);
            IdUsuarioArchivo = raf3.readInt();
            raf3.seek(a + 4);
            IdLoteArchivo = raf3.readInt();
            if (IdUsuarioArchivo == IdUsuario) {
                IdsUsuarios.add(IdUsuarioArchivo);
            }
            a = a + 8;
        }
        return IdsUsuarios;
    }

    public ArrayList ListarLotesPorUsuarioLote(int IdUsuario) throws FileNotFoundException, IOException {

        this.raf3 = new RandomAccessFile(LotesPorId, "rw");
        ArrayList<Integer> IdsLotes = new ArrayList<>();
        int a = 0;
        int IdUsuarioArchivo = 0;
        int IdLoteArchivo = 0;

        while (a < raf3.length()) {
            raf3.seek(a);
            IdUsuarioArchivo = raf3.readInt();
            raf3.seek(a + 4);
            IdLoteArchivo = raf3.readInt();
            if (IdUsuarioArchivo == IdUsuario) {
                IdsLotes.add(IdLoteArchivo);
            }
            a = a + 8;
        }
        return IdsLotes;
    }

}
