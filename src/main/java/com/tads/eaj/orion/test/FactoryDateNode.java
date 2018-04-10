/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.eaj.orion.test;

import com.tads.eaj.orion.dao.NodeDAO;
import com.tads.eaj.orion.dao.UsuarioDAO;
import com.tads.eaj.orion.model.Node;
import com.tads.eaj.orion.model.Sensor;
import com.tads.eaj.orion.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author lber
 */
public class FactoryDateNode {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Sensor> s1 = new ArrayList<Sensor>();
        s1.add(new Sensor(1, "Temperature", "27"));
        s1.add(new Sensor(2, "Temperature", "25"));
        s1.add(new Sensor(3, "Temperature", "26"));
        s1.add(new Sensor(4, "Temperature", "25"));
//        
//        List<Sensor> s2 = new ArrayList<Sensor>();
//        s2.add(new Sensor(1, "humidade", "26"));
//        s2.add(new Sensor(2, "Temperature", "15"));
//        s2.add(new Sensor(3, "Temperature", "16"));
//        s2.add(new Sensor(4, "luminosity", "15"));
//        
//        List<Sensor> s3 = new ArrayList<Sensor>();
//        s3.add(new Sensor(1, "Temperature", "23"));
//        s3.add(new Sensor(2, "Temperature", "23"));
//        s3.add(new Sensor(3, "Temperature", "24"));
//        s3.add(new Sensor(4, "Temperature", "25"));
//        
//        
        List<Sensor> s4 = new ArrayList<Sensor>();
        s4.add(new Sensor(1, "luz", "23"));
        s4.add(new Sensor(2, "temperatura", "23"));
        s4.add(new Sensor(3, "humidade", "24"));
        s4.add(new Sensor(4, "ph", "25"));
//        
//        Node no1 = new Node(1, "R1", s1, 25.4);
//        Node no2 = new Node(2, "R2", s2, 22.4);
//        Node no3 = new Node(3, "R3", s3, 20.4);
        Node no4 = new Node(4, "R4", s4, 20.4);
        
        NodeDAO dao = new NodeDAO();
//        dao.deletar("node2");//ok
//        dao.merge("node4", no4); //ok
//        dao.listarTodos();//ok
//        dao.buscar("regiao", "R4");
        
        UsuarioDAO udao = new UsuarioDAO();
//                udao.deletar("node10");//ok
//                udao.listarTodos();//ok
                udao.merge("node2", new Usuario("lol beta 2", "prata@live.com.br"));//ok
//                udao.buscar("nome", "lol beta");//ok
//        udao.buscar("nome", "lol beta");
//        dao.merge("node2", no2);
//        dao.merge("node3", no3);
//        dao.merge("node10", no3);
//          dao.merge("node9", no4);
//        AuthFactory.getUserUid();
//        AuthFactory.getInstanceAuthFactory().listAllUsers();//lista os usuários encontrados
//        AuthFactory.getInstanceAuthFactory().createCustomToken();
    }

}
