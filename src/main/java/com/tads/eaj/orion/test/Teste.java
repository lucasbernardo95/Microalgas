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
import java.util.regex.Pattern;

/**
 *
 * @author berna
 */
public class Teste {

    public static void main(String[] args) {

            Publisher.publicar("publica quaalquer coisa");


    }
}
