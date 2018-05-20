/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.eaj.orion.test;

import com.tads.eaj.orion.dao.NodeDAO;
import com.tads.eaj.orion.dao.PoliticaDAO;
import com.tads.eaj.orion.model.Node;
import com.tads.eaj.orion.model.Politica;
import com.tads.eaj.orion.model.Sensor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author berna
 */
public class FactorySensor {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

//        List<Sensor> s1 = new ArrayList<Sensor>();
//        s1.add(new Sensor("Temperatura água", "22"));
//        s1.add(new Sensor("pH", "39"));
//        s1.add(new Sensor("Energia", "41"));
//
//        List<Sensor> s2 = new ArrayList<Sensor>();
//        s2.add(new Sensor("Temperatura ambiente", "27"));
//        s2.add(new Sensor("Temperatura ambiente", "27"));
//        s2.add(new Sensor("energia", "78"));
////
//        Node no1 = new Node("ESP1", "Interna", "", "", s1);
////        Node no2 = new Node("ESP2", "Externa", s2);
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalTime localTime = localDateTime.toLocalTime();
//        no1.setData(String.valueOf(localDate));
//        no1.setHora(String.valueOf(localTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))));
//        NodeDAO dao = new NodeDAO();
//        dao.salvar(no1);
//        dao.listar();

//        dao.gerarToken();
//        for (Node node : dao.getLista()) {
//            System.out.println(node.getDataHora());
//        }
//        Publisher.publicar("DeepSleep");

        /*=====================politicas==================*/
        localTime = localDateTime.toLocalTime();
        Politica p = new Politica("deepsleep", String.valueOf(localDate), localTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)), "10");
        localTime = localDateTime.toLocalTime();
        Politica p2 = new Politica("modemsleep", String.valueOf(localDate), String.valueOf(localTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))), "0");
        localTime = localDateTime.toLocalTime();
        Politica p3 = new Politica("lightsleep", String.valueOf(localDate), String.valueOf(localTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))), "0");
        PoliticaDAO pdao = new PoliticaDAO();
//        pdao.salvar(p);
//        pdao.salvar(p2);
//        pdao.salvar(p3);
        pdao.listar();
        pdao.gerarToken();
        System.out.println("\n\n");
        for (Politica pc : pdao.getLista()) {
            System.out.println(pc.toString());
        }

    }

    /*
    Message message = new Message(username, body, time);
 
Map<String, Object> messageValues = message.toMap();
Map<String, Object> childUpdates = new HashMap<>();
 
String key = mDatabase.child("messages").push().getKey();
 
childUpdates.put("/messages/" + key, messageValues);
childUpdates.put("/user-messages/" + user.getUid() + "/" + key, messageValues);
     */
}
