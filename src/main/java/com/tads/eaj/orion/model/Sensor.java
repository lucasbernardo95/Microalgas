/*
 * A classe sensor representa um sensor da rede com seu dado coletado
 */
package com.tads.eaj.orion.model;

/**
 *
 * @author Lucas
 */
public class Sensor {

    private Integer id;
    private String tipo;
    private String dado;

    public Sensor() {
    }

    public Sensor(Integer id, String tipo, String dado) {
        this.id = id;
        this.tipo = tipo;
        this.dado = dado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDado() {
        return dado;
    }

    public void setDado(String dado) {
        this.dado = dado;
    }

    @Override
    public String toString() {
        return "Sensor{" + "id=" + id + ", tipo=" + tipo + ", dado=" + dado + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Sensor other = (Sensor) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}
