/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.eaj.orion.test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author berna
 */
public class FactorySensor {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalTime localTime = localDateTime.toLocalTime();

        System.out.println(localDateTime + "   ||   " + localTime);

        Instant instant = Instant.now();
        long timeStampSeconds = instant.getEpochSecond();
        
        System.out.println(instant + "   ||  " + timeStampSeconds);

//        List<Sensor> lista  = new ArrayList<Sensor>();
//        lista.add(new Sensor( "Nivel de água", "100"));
//        lista.add(new Sensor( "Temperatura", "100"));
//        lista.add(new Sensor( "PH", "100"));
//
//        SensorDAO sdao = new SensorDAO();
//        sdao.salvar("-LAuQZGMf53cIS9BFiIf" , lista);//ok
//
////        sdao.buscar("idNode", "ESP1");
//
//        sdao.gerarToken();
//        System.out.println(sdao.getSensor());
    }

}
