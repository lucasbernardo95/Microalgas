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
@IgnoreExtraProperties
public class NodeJson {

    private String id;
    private String regiao;
    private Double energia;
    private List<Sensor> sensores;

    public NodeJson() {
    }

    public NodeJson(String id, String regiao, Double energia, List<Sensor> sensores) {
        this.id = id;
        this.regiao = regiao;
        this.energia = energia;
        this.sensores = sensores;
    }

    public NodeJson(String id, String regiao, Double energia) {
        this.id = id;
        this.regiao = regiao;
        this.energia = energia;
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

    public Double getEnergia() {
        return energia;
    }

    public void setEnergia(Double energia) {
        this.energia = energia;
    }

    @Override
    public String toString() {
        return "Node{" + "id=" + id + ", regiao=" + regiao + ", energia=" + energia + ", sensores=" + sensores + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 53 * hash + (this.regiao != null ? this.regiao.hashCode() : 0);
        hash = 53 * hash + (this.sensores != null ? this.sensores.hashCode() : 0);
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
        final NodeJson other = (NodeJson) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.regiao == null) ? (other.regiao != null) : !this.regiao.equals(other.regiao)) {
            return false;
        }
        if (this.sensores != other.sensores && (this.sensores == null || !this.sensores.equals(other.sensores))) {
            return false;
        }
        return true;
    }

}
