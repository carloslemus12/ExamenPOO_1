/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lemus.carlos.mysql;

/**
 *
 * @author MekakuZero
 */
public interface Filtro {
    public Sentencia Where(String campo, Object valor);
    public Sentencia Where(String campo, String opreracion, Object valor);
    public Sentencia WhereID(int id);
    public LectorDatos LeerDatos();
}
