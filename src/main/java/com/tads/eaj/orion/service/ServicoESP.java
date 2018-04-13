/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.eaj.orion.service;

import com.tads.eaj.orion.dao.NodeDAO;
import com.tads.eaj.orion.model.Node;
import com.tads.eaj.orion.model.Sensor;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Lucas
 */
@Path("/esp")
public class ServicoESP {

    private NodeDAO dao;

    /**
     *
     * @param id identificador do node
     * @return o node correspondente ao id passado no parâmetro 
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar(@PathParam("id") int id) {
        dao = new NodeDAO();
        dao.buscarPorId(id);
        dao.gerarToken();
        if (dao.getNo() != null) {
            System.out.println("deu bom");
            return Response.status(Response.Status.OK).entity(dao.getNo())
                    .header("Access-Control-Allow-Origin", "*")
                    .build();//200
        } else {
            System.out.println("deu ruim");
            return Response.status(Response.Status.NOT_FOUND).entity("Erro: objeto não encontrado")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();//404
        }
    }

    /**
     * listagem de nodes armazenados no banco
     *
     * @return lista de Node em formato .json
     */
    @POST
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        dao = new NodeDAO();

        try {
            dao.listar();
            dao.gerarToken();
            return Response.status(Response.Status.OK).entity(dao.getLista())
                    .header("Access-Control-Allow-Origin", "*")
                    .build();//200
        } catch (InterruptedException ex) {
            Logger.getLogger(ServicoESP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ServicoESP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Erro ao listar os nodes")
                .header("Access-Control-Allow-Origin", "*")
                .build();//200

    }

    /**
     * Método para criação de um no. Recebe um .json de um node e armazena no
     * banco.
     *
     * @param no .json de um node
     * @return reposta informando se a operação foi bem sucedida ou não.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarNode(Node no) {
        String sms = "";
        if (no != null) {

            dao = new NodeDAO();
            try {
                dao.salvar(no);
                dao.gerarToken();
                sms = "Node salvo com sucesso";
                return Response.status(Response.Status.OK).entity(sms)
                        .header("Access-Control-Allow-Origin", "*")
                        .build();//200
            } catch (InterruptedException ex) {
                Logger.getLogger(ServicoESP.class.getName()).log(Level.SEVERE, null, ex);
                sms += ex + "\n\n";
            } catch (ExecutionException ex) {
                Logger.getLogger(ServicoESP.class.getName()).log(Level.SEVERE, null, ex);
                sms += ex + "\n\n";
            }

        }

        return Response.status(Response.Status.NOT_FOUND).entity(sms)
                .header("Access-Control-Allow-Origin", "*")
                .build();//200

    }

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarNode(@PathParam("id") Integer id, Node no) {
        String sms = "";
        if (no != null && id != null) {

            dao = new NodeDAO();

                dao.atualizar(id, no);
                dao.gerarToken();
                sms = "Node salvo com sucesso";
                return Response.status(Response.Status.OK).entity(sms)
                        .header("Access-Control-Allow-Origin", "*")
                        .build();//200


        } else if (id == null || id <= 0) 
            sms += "ID inválido";
        else if (no == null)
            sms += "Dados do node inválidos";

        return Response.status(Response.Status.NOT_FOUND).entity(sms)
                .header("Access-Control-Allow-Origin", "*")
                .build();//200
        
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") Integer id) throws InterruptedException, ExecutionException {
        String sms = "";
        if ( id != null) {

            dao = new NodeDAO();

                dao.excluir(id);
                dao.gerarToken();
                sms = "Node salvo com sucesso";
                return Response.status(Response.Status.OK).entity(sms)
                        .header("Access-Control-Allow-Origin", "*")
                        .build();//200


        } else if (id == null || id <= 0) 
            sms += "ID inválido";

        return Response.status(Response.Status.NOT_FOUND).entity(sms)
                .header("Access-Control-Allow-Origin", "*")
                .build();//200
    }
    
}
