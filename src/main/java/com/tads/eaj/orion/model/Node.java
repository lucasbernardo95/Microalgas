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
    private Integer id;
    private String regiao;
    private List<Sensor> sensores;
    private Double energia;
}
