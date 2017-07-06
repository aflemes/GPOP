/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author UCS
 */
import java.awt.List;
import java.util.ArrayList;
import model.ModelProcedimento;
import model.DaoProcedimento;

public class ControllerProcedimento {
    
    public boolean InserirProcedimento(ModelProcedimento modelProcedimento){
        DaoProcedimento daoProcedimento = new DaoProcedimento();
        boolean retorno = daoProcedimento.Inserir(modelProcedimento);
                
        return retorno;        
    }
    
    public ArrayList<ModelProcedimento> BuscaProcedimentos(String txtConsulta){
        DaoProcedimento daoProcedimento = new DaoProcedimento();
        ArrayList<ModelProcedimento> Resultado = daoProcedimento.Buscar(txtConsulta);
        
        return Resultado;        
    }
}
