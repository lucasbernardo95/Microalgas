/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.eaj.orion.test;

import com.tads.eaj.orion.pubsub.Publisher;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author berna
 */
public class Teste {

    public static void main(String[] args) {

//        LocalDate localDate = LocalDate.now();
//        System.out.println("localdate: " + localDate);
//
//        LocalDateTime localDateTime = LocalDateTime.now();
//        LocalTime localTime = localDateTime.toLocalTime();
//
//        System.out.println("LocalDateTime: " + localDateTime + "   ||  LocalTime: " + localTime);
//
//        Instant instant = Instant.now();
//        long timeStampSeconds = instant.getEpochSecond();
//
//        System.out.println(instant + "   ||  " + timeStampSeconds);
//
//        // Isso era buscar o locale do dispositivo
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
//
//        Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
//        String dataFormatada = sdf.format(hora);
//        System.out.println("HORA: " + hora+ " dataFormatada: " + dataFormatada);
          Publisher.publicar("deepsleep");
    }
}
