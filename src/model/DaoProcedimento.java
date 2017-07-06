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
public class DaoProcedimento {
    private static Connection connection;
    
    public DaoProcedimento(){        
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
    
    public boolean Inserir(ModelProcedimento proced){
        String sql;
        Statement stmt;
        
        if (connection == null){
            return false;            
        }
        
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DaoProcedimento.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        if (!VerificaProcedimento(proced)){
            sql = "INSERT INTO procedimento (cod_procedimento, descr_procedimento) values ";
            sql += "('" + proced.getCod_procedimento() + "','" + proced.getDescr_procedimento() + "')";

            try {
                stmt.executeUpdate(sql);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DaoProcedimento.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }            
        }
        else
            return false;
    }
    
    public boolean VerificaProcedimento(ModelProcedimento proced){
        boolean lgEncontrou = false;
        String sqlGet = "SELECT * FROM procedimento where cod_procedimento = '" + proced.getCod_procedimento() + "'";
        Statement stmt;
        ResultSet result;
        
        try {
            stmt = connection.createStatement();
            result = stmt.executeQuery(sqlGet);
        } catch (SQLException ex) {
            Logger.getLogger(DaoProcedimento.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }        
        
        try {
            if (result.isFirst())
                lgEncontrou = true;                                
        } catch (SQLException ex) {
            Logger.getLogger(DaoProcedimento.class.getName()).log(Level.SEVERE, null, ex);
            lgEncontrou = false;
        }
        
        return lgEncontrou;
    }
    
    public ArrayList<ModelProcedimento> Buscar(String txtBusca){
        String sqlGet = "";
        Statement stmt;
        ResultSet result;
        ArrayList<ModelProcedimento> ResultadoProcedimento = new ArrayList<>();
        ModelProcedimento procedAux = new ModelProcedimento();
        
        if (!txtBusca.isEmpty()){
            sqlGet = "SELECT * FROM procedimento where cod_procedimento like '%" + txtBusca + "%'";
        }
        else
            sqlGet = "SELECT * FROM procedimento";
            
        try {
            stmt = connection.createStatement();
            result = stmt.executeQuery(sqlGet);
        } catch (SQLException ex) {
            Logger.getLogger(DaoProcedimento.class.getName()).log(Level.SEVERE, null, ex);
            return ResultadoProcedimento;
        }         
        
        try {
            while (result.next()) {
                procedAux = new ModelProcedimento();
                procedAux.setCod_procedimento(result.getString(1));
                procedAux.setDescr_procedimento(result.getString(2));
                ResultadoProcedimento.add(procedAux);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoProcedimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return ResultadoProcedimento;
    }
    
    public boolean Alterar(ModelProcedimento proced){
        
        return true;
    }
    
    public boolean Deletar(ModelProcedimento proced){
         
        return true;
    }
        
    public static void main(String[] args) {
         // TODO code application logic here      
    }
}
