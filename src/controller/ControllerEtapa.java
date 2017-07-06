/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.DaoEtapa;
import model.ModelEtapa;

/**
 *
 * @author skyli
 */
public class ControllerEtapa {

    public boolean InserirEtapa(ModelEtapa modeletapa){
        DaoEtapa daoEtapa = new DaoEtapa();
        boolean retorno = daoEtapa.Inserir(modeletapa);
                
        return retorno;        
    }    
    
    public ArrayList<ModelEtapa> BuscaEtapas(String codProcedimento){
        DaoEtapa daoEtapa = new DaoEtapa();
        ArrayList<ModelEtapa> Resultado = daoEtapa.Buscar(codProcedimento);
        
        return Resultado;        
    }
    
    public boolean RemoveEtapa(String codEtapa, String codProcedimento){
        DaoEtapa daoEtapa = new DaoEtapa();
        boolean Resultado = daoEtapa.Deletar(codEtapa,codProcedimento);
        
        return Resultado;        
    }
    
    
}
