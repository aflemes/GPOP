/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UCS
 */
public class DaoEtapa {
    private static Connection connection;
    
    public DaoEtapa(){        
        try {  
  
            String url = "jdbc:postgresql://localhost:5432/GPOP";  
            String usuario = "postgres";  
            String senha = "carros123";  
  
            Class.forName("org.postgresql.Driver");  
  
            connection = DriverManager.getConnection(url, usuario, senha);
              
        } catch (Exception e) {  
             e.printStackTrace();  
        }  
    }
    
    public boolean Inserir(ModelEtapa etapa){
        String sql;
        Statement stmt;
        
        if (connection == null){
            return false;            
        }
        
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DaoEtapa.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        if (!VerificaEtapa(etapa)){
            sql = "INSERT INTO etapa (cod_etapa, cod_procedimento, descr_etapa) values ";
            sql += "('" + etapa.getCod_etapa()+ "','" + etapa.getCod_procedimento()+ "','" + etapa.getDescr_etapa() + "')";

            try {
                stmt.executeUpdate(sql);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DaoEtapa.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }            
        }
        else
            return false;
    }
    
    public boolean VerificaEtapa(ModelEtapa etapa){
        boolean lgEncontrou = false;
        String sqlGet = "SELECT * FROM etapa where cod_procedimento = '" + etapa.getCod_procedimento() + "' and cod_etapa = '" + etapa.getCod_etapa() + "'";
        Statement stmt;
        ResultSet result;
        
        try {
            stmt = connection.createStatement();
            result = stmt.executeQuery(sqlGet);
        } catch (SQLException ex) {
            Logger.getLogger(DaoEtapa.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }        
        
        try {
            if (result.isFirst())
                lgEncontrou = true;                                
        } catch (SQLException ex) {
            Logger.getLogger(DaoEtapa.class.getName()).log(Level.SEVERE, null, ex);
            lgEncontrou = false;
        }
        
        return lgEncontrou;
    }
    
    public ArrayList<ModelEtapa> Buscar(String procedimento){
        String sqlGet = "";
        Statement stmt;
        ResultSet result;
        ArrayList<ModelEtapa> ResultadoEtapa = new ArrayList<>();
        ModelEtapa etapaAux = new ModelEtapa();
        
        sqlGet = "SELECT * FROM etapa where cod_procedimento = '" + procedimento + "'";
            
        try {
            stmt = connection.createStatement();
            result = stmt.executeQuery(sqlGet);
        } catch (SQLException ex) {
            Logger.getLogger(DaoEtapa.class.getName()).log(Level.SEVERE, null, ex);
            return ResultadoEtapa;
        }         
        
        try {
            while (result.next()) {
                etapaAux = new ModelEtapa();
                etapaAux.setCod_etapa(result.getString(1));
                etapaAux.setCod_procedimento(result.getString(2));                
                etapaAux.setDescr_etapa(result.getString(3));
                ResultadoEtapa.add(etapaAux);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoEtapa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return ResultadoEtapa;
    }
    
    public boolean Alterar(ModelEtapa proced){
        
        return true;
    }
    
    public boolean Deletar(String codEtapa, String codProcedimento){
         
        String sqlDel = "";
        Statement stmt;
        ResultSet result;
        ArrayList<ModelEtapa> ResultadoEtapa = new ArrayList<>();
        ModelEtapa etapaAux = new ModelEtapa();
        
        sqlDel = "DELETE FROM etapa where cod_procedimento = '" + codProcedimento + "' and cod_etapa = '" + codEtapa + "'";
        
        try {
            stmt = connection.createStatement();
            result = stmt.executeQuery(sqlDel);
        } catch (SQLException ex) {
            Logger.getLogger(DaoEtapa.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }         
        
        return true;
    }
        
    public static void main(String[] args) {
         // TODO code application logic here      
    }
}
