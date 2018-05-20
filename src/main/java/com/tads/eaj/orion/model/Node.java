/*
 * Classe que representa um nó da rede (NodeMcu ou arduino).
 */
package com.tads.eaj.orion.model;

import java.util.List;

/**
 *
 * @author Lucas
 */

public class Node {

    private String id;
    private String regiao;
    private String data;
    private String hora;
    private String politica;
    private List<Sensor> sensores;

    public Node() {
    }

    public Node(String id, String regiao, String data, String hora, String politica, List<Sensor> sensores) {
        this.id = id;
        this.regiao = regiao;
        this.data = data;
        this.hora = hora;
        this.sensores = sensores;
        this.politica = politica;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPolitica() {
        return politica;
    }

    public void setPolitica(String politica) {
        this.politica = politica;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 47 * hash + (this.regiao != null ? this.regiao.hashCode() : 0);
        hash = 47 * hash + (this.data != null ? this.data.hashCode() : 0);
        hash = 47 * hash + (this.hora != null ? this.hora.hashCode() : 0);
        hash = 47 * hash + (this.politica != null ? this.politica.hashCode() : 0);
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
        if ((this.data == null) ? (other.data != null) : !this.data.equals(other.data)) {
            return false;
        }
        if ((this.hora == null) ? (other.hora != null) : !this.hora.equals(other.hora)) {
            return false;
        }
        if ((this.politica == null) ? (other.politica != null) : !this.politica.equals(other.politica)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Node{" + "id=" + id + ", regiao=" + regiao + ", data=" + data + ", hora=" + hora + ", politica=" + politica + ", sensores=" + sensores + '}';
    }
    
}