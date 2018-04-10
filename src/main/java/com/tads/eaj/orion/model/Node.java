/*
 * Classe que representa um nó da rede (NodeMcu ou arduino).
 */
package com.tads.eaj.orion.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lucas
 */

@IgnoreExtraProperties
public class Node {

    private Integer id;
    private String regiao;
    private List<Sensor> sensores;
    private Double energia;

    public Node() {
    }

    public Node(Integer id, String regiao, List<Sensor> sensores, Double energia) {
        this.id = id;
        this.regiao = regiao;
        this.sensores = sensores;
        this.energia = energia;
    }

    public Node(Integer id, String regiao, Double energia) {
        this.id = id;
        this.regiao = regiao;
        this.energia = energia;
    }

    public void addSensor(Sensor sensor) {
        if (sensores == null || sensores.isEmpty())//se a lista estiver vazia 
        {
            sensores = new ArrayList<Sensor>(); //cria a lista
        }
        sensores.add(sensor);
    }

    public Sensor getSensor(Sensor sensor) {
        return sensores.get(sensores.indexOf(sensor));
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
    @Exclude
    public Map<String, Object> toMap(){ 
        HashMap<String, Object> result = new HashMap();
        result.put("id", id);
        result.put("regiao", regiao);
        result.put("sensores", sensores);
        result.put("energia", energia);
        return result;
    }
}
