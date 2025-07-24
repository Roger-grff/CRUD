package app.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class Conexion {
    
    public Connection conectar() {
        String ruta = System.getProperty("user.home") + "/Desktop/prod.db";
        String url = "jdbc:sqlite:" + new File(ruta).getAbsolutePath();
        Connection conn = null;
        
        try{
            conn = DriverManager.getConnection(url);
            System.out.println("Conexion Exitosa");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
    private final String rutaArchivo = "src/app/modelo/usuario.txt";

    public List<String[]> obtenerCredenciales() {
        List<String[]> credenciales = new ArrayList<>();


        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    credenciales.add(new String[]{partes[0].trim(), partes[1].trim(),partes[2].trim()});
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return credenciales;
    }
   public static void main(String[] args) {
        Conexion conexion = new Conexion();
        conexion.obtenerCredenciales();
       List<String[]> listaCredenciales = conexion.obtenerCredenciales();

       for (String[] credencial : listaCredenciales) {
           System.out.println("Usuario: " + credencial[0] + ", Contraseña: " + credencial[1]+ ", Perfil: " + credencial[2]);
       }
    }


}
