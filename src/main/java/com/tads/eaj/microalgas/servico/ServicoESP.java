/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.eaj.microalgas.servico;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Lucas
 */
@Path("/esp")
public class ServicoESP {
    
    @POST
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public String teste () {
        return "{\n" +
"    \"id\": 1,\n" +
"    \"name\": \"Leanne Graham\",\n" +
"    \"username\": \"Bret\",\n" +
"    \"email\": \"Sincere@april.biz\",\n" +
"    \"address\": {\n" +
"      \"street\": \"Kulas Light\",\n" +
"      \"suite\": \"Apt. 556\",\n" +
"      \"city\": \"Gwenborough\",\n" +
"      \"zipcode\": \"92998-3874\",\n" +
"      \"geo\": {\n" +
"        \"lat\": \"-37.3159\",\n" +
"        \"lng\": \"81.1496\"\n" +
"      }\n" +
"    }";
    }
    
}
