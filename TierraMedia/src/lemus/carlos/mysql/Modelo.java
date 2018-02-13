/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lemus.carlos.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import lemus.carlos.utilidad.Mensaje;

/**
 *
 * @author MekakuZero
 */
public class Modelo implements Filtro{

    public static final String IGUAL = "=";
    public static final String LIKE = "LIKE";
    public static final String DISTINTO = "!=";
    
    protected String tabla;
    
    // Conexion con la base
    protected Connection conexion;
    
    // Informacion de los campos
    // Campo | Tipo | Valor
    private Map<String, Pair<String, Object>> campos;
    
    // Informacion de la llave primaria
    private int id = -1;
    
    public Modelo(String tabla) {
        // Obtenemos la conexion
        conexion = Conexion.GetConexion();
    
        // Inicialisamos el mapa
        campos = new HashMap<>();
        
        this.tabla = tabla;
        
        // Inicializamos los campos
        InicializarCampos();
    }
    
    public Modelo(String tabla, LectorDatos.Datos datos){
        // Obtenemos la conexion
        conexion = Conexion.GetConexion();
    
        // Inicialisamos el mapa
        campos = new HashMap<>();
        
        this.tabla = tabla;
        
        // Inicializamos los campos
        InicializarCampos();
        
        for(Map.Entry<String, Pair<String, Object>> valor : this.campos.entrySet()){
            String cmp = valor.getKey();
            
            Object obj = datos.GetObject(cmp);
            
            if (obj != null) {
                Pair<String, Object> info = valor.getValue();
                this.SetCampo(cmp, obj);
            }
        }
        
        Object ID = datos.GetObject("id");
        
        if (ID != null) {
            try{
                int indice = Integer.parseInt(ID.toString());
                this.id = indice;
            } catch(Exception ex){}
        }
    }
    
    // Añadir un campos
    protected void AddCampo(String campo, String tipo){
        // Si el campo no existe
        if (!campos.containsKey(campo))
            campos.put(campo, new Pair<>(tipo, null));
    }

    // Obtenemos el id
    public int getId() {
        return id;
    }
    
    // Es la informacion sobre los campos
    protected void InicializarCampos(){}
    
    public void SetCampo(String campo, Object valor){
        if (campos.containsKey(campo)) {
            Pair<String, Object> informacion = campos.get(campo);
            campos.put(campo, new Pair<>(informacion.getKey(), valor));
        }
    }
    
    public String GetCampoTexto(String campo){
        if (campos.containsKey(campo)) {
            Pair<String, Object> informacion = campos.get(campo);
            return informacion.getValue().toString();
        }
        
        return "";
    }
    
    public boolean Guardar(){
        if(conexion != null){
            // Para guardar los datos
            String consulta = "INSERT INTO " + tabla + " ";

            String consultaCampos = "(";
            String consultaValores = "(";

            boolean hayCampos = false;

            // Obtenemos todos los campos
            for(Map.Entry<String, Pair<String, Object>> campo : campos.entrySet()){
                // Informacion
                Pair<String, Object> info = campo.getValue();

                if (info.getValue() != null) {

                    // En caso que hayan campos
                    if (hayCampos) {
                        consultaCampos += ",";
                        consultaValores += ",";
                    }

                    consultaCampos += campo.getKey();

                    consultaValores += TipoDatos.EstructurarCampo(info.getKey(), info.getValue());

                    // Informacion de que hay campos
                    hayCampos = true;
                }
            }

            // En caso que haya campos
            if (hayCampos) {
                consultaCampos += ")";
                consultaValores += ")";

                consulta += consultaCampos + " VALUES " + consultaValores;

                try {
                    Statement sentencia = conexion.createStatement();
                    sentencia.execute(consulta);
                    return true;
                } catch (Exception ex){
                    Mensaje.Errores(ex.getMessage());
                    return false;
                }
            }
        }
        
        return false;
    }
    
    public boolean Modificar(){
        if (id != -1 && conexion != null) {
            String consulta = "UPDATE " +  tabla;
            
            String set = "";
            
            boolean hayCampos = false;

            // Obtenemos todos los campos
            for(Map.Entry<String, Pair<String, Object>> campo : campos.entrySet()){
                // Informacion
                Pair<String, Object> info = campo.getValue();

                if (info.getValue() != null) {
                    // En caso que hayan campos
                    if (hayCampos)
                        set += ",";

                    set += campo.getKey() + " = " + TipoDatos.EstructurarCampo(info.getKey(), info.getValue());

                    // Informacion de que hay campos
                    hayCampos = true;
                }
            }
            
            if (hayCampos) {
                consulta += " SET " + set + " WHERE id = " + id;
                
                try {
                    Statement sentencia = conexion.createStatement();
                    sentencia.execute(consulta);
                    return true;
                } catch (Exception ex){}
            }
            
        }
        return false;
    }
    
    public boolean ModificarConId(int id){
        if (id != -1 && conexion != null) {
            String consulta = "UPDATE " +  tabla;
            
            String set = "";
            
            boolean hayCampos = false;

            // Obtenemos todos los campos
            for(Map.Entry<String, Pair<String, Object>> campo : campos.entrySet()){
                // Informacion
                Pair<String, Object> info = campo.getValue();

                if (info.getValue() != null) {
                    // En caso que hayan campos
                    if (hayCampos)
                        set += ",";

                    set += campo.getKey() + " = " + TipoDatos.EstructurarCampo(info.getKey(), info.getValue());

                    // Informacion de que hay campos
                    hayCampos = true;
                }
            }
            
            if (hayCampos) {
                consulta += " SET " + set + " WHERE id = " + id;
                
                try {
                    Statement sentencia = conexion.createStatement();
                    sentencia.execute(consulta);
                    return true;
                } catch (Exception ex){}
            }
            
        }
        return false;
    }
    
    public Sentencia Where(String campo, Object valor){
        Sentencia s = new Sentencia(this.conexion, this.campos, this.tabla);
        
        // Si el campo coincide con el de la tabla y si no esta vacio
        if (campos.containsKey(campo) && valor != null)
            s.Where(campo, valor);
        
        return s;
    }

    @Override
    public Sentencia Where(String campo, String operacion, Object valor) {
        Sentencia s = new Sentencia(this.conexion, this.campos, this.tabla);
        
        // Si el campo coincide con el de la tabla y si no esta vacio
        if (campos.containsKey(campo) && valor != null)
            s.Where(campo, operacion, valor);
        
        return s;
    }
    
    @Override
    public Sentencia WhereID(int id) {
        Sentencia s = new Sentencia(this.conexion, this.campos, this.tabla);
        
        // Si el campo coincide con el de la tabla y si no esta vacio
        if (id > 0)
            s.WhereID(id);
        
        return s;
    }

    @Override
    public LectorDatos LeerDatos() {
        String consulta = "SELECT id";
        
        String num = "SELECT COUNT(*) FROM " + tabla; 
        
        List<String> columnas = new ArrayList<>();
        columnas.add("id");
        
        for(Map.Entry<String, Pair<String, Object>> valor : campos.entrySet()){
            consulta += "," + valor.getKey();
            columnas.add(valor.getKey());
        }
        
        consulta += " FROM " + tabla;
        
        if (conexion != null) {
            try{
                // Obtenemos el tamaño de las filas
                Statement numS = conexion.createStatement();
                ResultSet numR = numS.executeQuery(num);
                 
                int size = 0;
                
                while(numR.next())
                    size = numR.getInt(1);
                
                if (size > 0) {
                    // Obtenemos los campos
                    Statement sentencia = conexion.createStatement();
                    ResultSet resultado = sentencia.executeQuery(consulta);
                
                    List<LectorDatos.Datos> datos = new ArrayList<>();
                    
                    while(resultado.next()){
                        List<Pair<String, Object>> list = new ArrayList<>();
                        
                        for (int i = 0; i < columnas.size(); i++)
                            list.add(new Pair<>(columnas.get(i), resultado.getObject(columnas.get(i))));
                        
                        datos.add(new LectorDatos.Datos(list));
                    }
                    
                    return new LectorDatos(datos);
                } else
                    System.out.println("Error: no hay filas");
            } catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }
        
        return new LectorDatos(new ArrayList<>());
    }
    
    public boolean HayDatos() {
        
        String num = "SELECT COUNT(*) FROM " + tabla;
        
        if (conexion != null) {
            try{
                // Obtenemos el tamaño de las filas
                Statement numS = conexion.createStatement();
                ResultSet numR = numS.executeQuery(num);
                 
                int size = 0;
                
                while(numR.next())
                    size = numR.getInt(1);
                
                return size > 0;
            } catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }
        
        return false;
    }
    
    public int GetLastID(){
        if (conexion != null) {
            String consulta = "SELECT id FROM " + tabla + " ORDER BY id DESC LIMIT 1";
            
            try{
                int id = -1;
                
                Statement s = conexion.createStatement();
                ResultSet r = s.executeQuery(consulta);
                
                while(r.next())
                    id = r.getInt("id");
                
                return id;
                
            } catch (Exception ex) { System.out.println("Error: " + ex.getMessage()); }
        }
        
        return -1;
    }
    
    public static final class Relacion<k, v>{
        private List<k> campos;
        private Map<k, v> camposValores;
    
        public Relacion(){
            this.campos = new ArrayList<>();
            this.camposValores = new HashMap<>();
        }
        
        public Relacion(LectorDatos datos, String llave, String valor){
            this.campos = new ArrayList<>();
            this.camposValores = new HashMap<>();
            
            if (datos.PoseeDatos()) {
                while(datos.Siguiente()){
                    this.campos.add((k)datos.GetObject(llave));
                    this.camposValores.put((k)datos.GetObject(llave), (v)datos.GetObject(valor));
                }
            }
        }
        
        public Object[] GetArrayObject(){
            List<Object> menu = new ArrayList<>();
            
            for(Object obj : this.campos)
                menu.add(obj);
            
            menu.add("Volver");
            
            return menu.toArray();
        }
        
        public v GetValor(k campo){
            if (this.camposValores.containsKey(campo))
                return this.camposValores.get(campo);
            
            return null;
        }
    }
    
    public static final class TipoDatos{
        public static final String STRING = "string";
        public static final String INTEGER = "integer";
        
        public static String EstructurarCampo(String tipo, Object valor){
            
            switch(tipo){
                case STRING:
                    return "'" + valor + "'";
                case INTEGER:
                    return valor.toString();
            }
            
            return "";
        }
    }

    @Override
    public String toString() {
        String consulta = this.tabla + ":";
        
        consulta += "\nID: " + this.id;
        
        for(Map.Entry<String, Pair<String, Object>> valores : this.campos.entrySet()){
            Pair<String, Object> info = valores.getValue();
        
            consulta += "\n" + valores.getKey() + ": " + info.getValue().toString();
        }
        
        return consulta;
    }

    public String getTabla() {
        return tabla;
    }
    
    public boolean Eliminar(){
        if (id > 0) {
            String consulta = "DELETE FROM " + tabla + " WHERE id = " + id;

            if (conexion != null) {
                try {
                    Statement sentencia = conexion.createStatement();
                    sentencia.execute(consulta);

                    return true;
                } catch (Exception ex){
                    return false;
                }
            }
        }
        
        return false;
    }
}
