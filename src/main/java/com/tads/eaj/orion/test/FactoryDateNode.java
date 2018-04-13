/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.eaj.orion.test;

import com.tads.eaj.orion.dao.NodeDAO;
import com.tads.eaj.orion.model.Node;
import com.tads.eaj.orion.model.Sensor;
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
        s1.add(new Sensor("Nivel de água", "1"));
        s1.add(new Sensor("Temperatura da água", "25"));
        s1.add(new Sensor("PH", "10"));

        List<Sensor> s2 = new ArrayList<Sensor>();
        s2.add(new Sensor("FLuxo de ar", "50"));
        s2.add(new Sensor("Temperatura do ambiente", "22"));
        s2.add(new Sensor("luminozidade", "500"));

        List<Sensor> s3 = new ArrayList<Sensor>();
        s3.add(new Sensor("Ar condicionado", "1"));
        s3.add(new Sensor("Luz", "1"));
        s3.add(new Sensor("Bombas", "1"));

        Node no1 = new Node(1, "R1", 25.4, s1);
        Node no2 = new Node(2, "R2", 22.4, s2);
        Node no3 = new Node(5, "R5", 20.4, s3);

        NodeDAO dao = new NodeDAO();
        //cadastro ok
//        dao.salvar(no1);
//        dao.salvar(no2);
//        dao.salvar(no3);
//        NodeDAO.gerarToken("ZqUyhCnHIiVxmWhMtUaxvJRWYbm2");
        //busca
//        dao.buscar("regiao", "R3");
//        NodeDAO.gerarToken("ZqUyhCnHIiVxmWhMtUaxvJRWYbm2");
//        dao.buscarPorId(4);
//        dao.gerarToken();
//        System.out.println("\nNode: " + dao.getNo());
        //listar 
//        dao.listar();
//        NodeDAO.gerarToken("ZqUyhCnHIiVxmWhMtUaxvJRWYbm2");
//        for (Node s : dao.getLista()) {
//            System.out.println("\nNo: " + s.toString());
//        }
        //deletar
//        dao.excluir(3);
//        dao.gerarToken();
//        System.out.println("\n\n"+dao.getNo());
        //atualizar
//        dao.atualizar(1, no3);
//        dao.gerarToken();
//        System.out.println("\n\n"+dao.getNo());


//        System.out.println("\n"+no.toString());
    }

}
