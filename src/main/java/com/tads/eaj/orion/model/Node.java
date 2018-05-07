/*
 * Classe que representa um nó da rede (NodeMcu ou arduino).
 */
package com.tads.eaj.orion.model;

import com.google.firebase.database.IgnoreExtraProperties;
import java.util.List;

/**
 *
 * @author Lucas
 */

public class Node {

    private String id;
    private String regiao;
    private String dataHora;
    private List<Sensor> sensores;

    public Node() {
    }

    public Node(String id, String regiao, List<Sensor> sensores) {
        this.id = id;
        this.regiao = regiao;
        this.sensores = sensores;
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

    public List<Sensor> getSensores() {
        return sensores;
    }

    public void setSensores(List<Sensor> sensores) {
        this.sensores = sensores;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 37 * hash + (this.regiao != null ? this.regiao.hashCode() : 0);
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
        final Node other = (Node) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.regiao == null) ? (other.regiao != null) : !this.regiao.equals(other.regiao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NodeJson{" + "id=" + id + ", regiao=" + regiao + ", dataHora=" + dataHora + ", sensores=" + sensores + '}';
    }
    
}