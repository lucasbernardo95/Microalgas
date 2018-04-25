/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.eaj.orion.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author berna
 */
public class SensorData {
    
    private String    id;
    private String    regiao;
    private String    tipo;
    private String    valor;
    private String dataColeta;
//    private LocalTime horaColeta;

    public SensorData() {
    }

    public SensorData(String id, String regiao, String tipo, String valor) {
        this.id = id;
        this.regiao = regiao;
        this.tipo = tipo;
        this.valor = valor;
        this.dataColeta = String.valueOf(LocalDateTime.now());//exemplo de saída: 2018-04-25T00:41:31.458 
//        LocalDateTime localDateTime = LocalDateTime.now();//exemplo de saída: localDateTime = 2018-04-25T00:18:09.990 
//        this.horaColeta = localDateTime.toLocalTime();//exemplo de saída: horaColeta = 00:18:09.990;
    }

    @Override
    public String toString() {
        return "SensorData{" + "id=" + id + ", regiao=" + regiao + ", tipo=" + tipo + ", valor=" + valor + ", dataColeta=" + dataColeta + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 89 * hash + (this.regiao != null ? this.regiao.hashCode() : 0);
        hash = 89 * hash + (this.dataColeta != null ? this.dataColeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SensorData other = (SensorData) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.regiao == null) ? (other.regiao != null) : !this.regiao.equals(other.regiao)) {
            return false;
        }
        if (this.dataColeta != other.dataColeta && (this.dataColeta == null || !this.dataColeta.equals(other.dataColeta))) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(String dataColeta) {
        this.dataColeta = dataColeta;
    }

}