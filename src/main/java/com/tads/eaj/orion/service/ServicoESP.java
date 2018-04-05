/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.eaj.orion.service;

import com.tads.eaj.orion.model.Node;
import com.tads.eaj.orion.model.Sensor;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Lucas
 */
@Path("/esp")
public class ServicoESP {
    
    @GET
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public String teste () {
        Sensor s = new Sensor(1, "tipo", "dado");
        return s.toString();
    }
    
    @POST
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDado() {
        
        List<Sensor> lista = new ArrayList();
        
        lista.add( new Sensor(1, "tipo 1", "dado 1"));
        lista.add(new Sensor(2, "tipo 2", "dado 2"));
        lista.add(new Sensor(3, "tipo 3", "dado 3"));
        lista.add(new Sensor(4, "tipo 4", "dado 4"));
        lista.add(new Sensor(5, "tipo 5", "dado 5"));
        
        return Response.status(Response.Status.OK).entity(lista)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();//200
    }
    
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarDado(Node no) {
        
        if (no == null) {
            
            
            
            return Response.status(Response.Status.OK)
                            .header("Access-Control-Allow-Origin", "*")
                            .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Nenhum dado foi recebido")
                            .header("Access-Control-Allow-Origin", "*")
                            .build();//500
        }
    
    }
    
}
