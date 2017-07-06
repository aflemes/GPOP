/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author UCS
 */
public class ModelProcedimento {
    private String cod_procedimento;
    private String descr_procedimento;
    
    public String getCod_procedimento() {
        return cod_procedimento;
    }

    public void setCod_procedimento(String cod_procedimento) {
        this.cod_procedimento = cod_procedimento;
    }

    public String getDescr_procedimento() {
        return descr_procedimento;
    }

    public void setDescr_procedimento(String descr_procedimento) {
        this.descr_procedimento = descr_procedimento;
    }
}
