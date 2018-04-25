/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.eaj.orion.service;

import com.tads.eaj.orion.dao.SensorDataDAO;
import com.tads.eaj.orion.model.NodeJson;
import com.tads.eaj.orion.model.Sensor;
import com.tads.eaj.orion.model.SensorData;
import com.tads.eaj.orion.response.OutputMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

   /**
     * Método para criação de um no. Recebe um .json de um node e armazena no
     * banco.
     *
     * @param no .json de um node no formado:
     *
     * {
     * "id": "ESP1", 
     * "regiao": "R4", 
     * "energia": 4.4, 
     * "sensores": [ 
     *     { 
     *      "tipo": "Nivel de água", 
     *      "dado": "20" 
     *     }, 
     *     { 
     *      "tipo": "Temperatura da água", 
     *      "dado": "25" 
     *     }, 
     *     { 
     *      "tipo": "PH", 
     *      "dado": "10" 
     *     } 
     * ] 
     *}
     *
     * @return reposta informando se a operação foi bem sucedida ou não.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(NodeJson no) {

        try {

            if (no == null) {
                return Response
                        .status(Response.Status.NOT_ACCEPTABLE)
                        .entity(new OutputMessage(500, "Objeto inválido!"))
                        .build();
            } else {
                SensorDataDAO dao = new SensorDataDAO();
                System.out.println("objeto recebido: " + no.toString());
                for (Sensor s : no.getSensores()) {
                    SensorData data = new SensorData(no.getId(), no.getRegiao(), s.getTipo(), s.getDado());
                    dao.salvar(data);
                }//SensorData data = new SensorData(no.getId(), no.getRegiao(), "energia", no.getEnergia() + "");
                dao.gerarToken();
                //quando receber o dado do sensor de energia, chamar a classe Publisher para mandar a política a ser aplicada
            }
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500, e.getMessage()))
                    .build();

        }

        return Response
                .status(Response.Status.CREATED)
                .entity(no)
                .build();

    }

    /**
     * Método para deletar um objeto pelo id informado
     *
     * @param id identificador do objeto buscado
     * @return resposta do servidor refernete a operação de delete se ocorreu
     * com sucesso ou não.
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") String id) {

        if (id == null || id.equals("")) {
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();

        }
        try {
            SensorDataDAO dao = new SensorDataDAO();
            dao.excluir(id);
            dao.gerarToken();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500, e.getMessage()))
                    .build();

        }
        return Response
                .status(Response.Status.OK)
                .entity(new OutputMessage(200, "Objeto removido."))
                .build();

    }

    /**
     * Método para atualizar um node do banco de dados
     *
     * @param id identificador do objetoo a ser atualizado
     * @param no json do objeto contendo seus atributos
     * @return resposta contendo uma mensagem e um código indicando se deu certo
     * ou não.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, SensorData no) {

        if (id == null || id.equals("")) {
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .entity(new OutputMessage(500, "ID inválido"))
                    .build();
        }
        try {
            SensorDataDAO dao = new SensorDataDAO();
            dao.atualizar(id, no);
            dao.gerarToken();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500, e.getMessage()))
                    .build();

        }

        return Response
                .status(Response.Status.OK)
                .entity(no)
                .build();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listById(@PathParam("id") String id) {
        try {

            if (id == null || id.equals("")) {
                return Response
                        .status(Response.Status.NO_CONTENT)
                        .entity(new OutputMessage(500, "ID inválido"))
                        .build();

            } else {
                SensorDataDAO dao = new SensorDataDAO();
                dao.buscarPorId(id);
                dao.gerarToken();
                return Response
                        .status(Response.Status.OK)
                        .entity(dao.getNo()) //objeto retornado do banco
                        .build();

            }
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500, e.getMessage()))
                    .build();

        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        try {
            SensorDataDAO dao = new SensorDataDAO();
            dao.listar();
            dao.gerarToken();

            if (dao.getLista() == null) {
                return Response
                        .status(Response.Status.NO_CONTENT)
                        .entity(new OutputMessage(500, "Nenhum objeto encontrado no banco"))
                        .build();

            } else {
                return Response
                        .status(Response.Status.OK)
                        .entity(dao.getLista())
                        .build();

            }
        } catch (Exception e) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new OutputMessage(500, e.getMessage()))
                    .build();

        }
    }

}
