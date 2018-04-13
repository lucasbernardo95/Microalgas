/*
 * Classe que representa um nó da rede (NodeMcu ou arduino).
 */
package com.tads.eaj.orion.model;

import com.google.firebase.database.IgnoreExtraProperties;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas
 */

@IgnoreExtraProperties
public class Node {

    private Integer id;
    private String regiao;
    private Double energia;
    private List<Sensor> sensores;

    public Node() {
    }

    public Node(Integer id, String regiao, Double energia, List<Sensor> sensores) {
        this.id = id;
        this.regiao = regiao;
        this.energia = energia;
        this.sensores = sensores;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public Double getEnergia() {
        return energia;
    }

    public void setEnergia(Double energia) {
        this.energia = energia;
    }

    public List<Sensor> getSensores() {
        return sensores;
    }

    public void setSensores(List<Sensor> sensores) {
        this.sensores = sensores;
    }

    @Override
    public String toString() {
        return "Node{" + "id=" + id + ", regiao=" + regiao + ", energia=" + energia + ", sensores=" + sensores + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 97 * hash + (this.regiao != null ? this.regiao.hashCode() : 0);
        hash = 97 * hash + (this.energia != null ? this.energia.hashCode() : 0);
        hash = 97 * hash + (this.sensores != null ? this.sensores.hashCode() : 0);
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
        if ((this.regiao == null) ? (other.regiao != null) : !this.regiao.equals(other.regiao)) {
            return false;
        }
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.energia != other.energia && (this.energia == null || !this.energia.equals(other.energia))) {
            return false;
        }
        if (this.sensores != other.sensores && (this.sensores == null || !this.sensores.equals(other.sensores))) {
            return false;
        }
        return true;
    }
    
}
