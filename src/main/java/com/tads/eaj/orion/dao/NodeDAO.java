/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.eaj.orion.dao;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tads.eaj.orion.model.Node;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lber
 */
public class NodeDAO {


    private void gerarToken() throws InterruptedException, ExecutionException {
        AuthFactory.getInstanceAuthFactory().getUserById("lucas-uid");//Recupera o uid do usuário
//        AuthFactory.getInstanceAuthFactory().getUserByEmail("bernardotriton@gmail.com");//recupera o email
//        AuthFactory.getInstanceAuthFactory().getUserByPhoneNumber("+5584991770750");//recupera o telefone
        AuthFactory.getInstanceAuthFactory().listAllUsers();//lista os usuários encontrados
        AuthFactory.getInstanceAuthFactory().createCustomToken();//cria um token para liberar as operações
    }

    /**
     * Método para atualizar um objeto existente no banco ou criar um novo caso
     * o dado enviado não exista na tabela informada
     *
     * @param id chave primária do objeto (identificador)
     * @param node ojeto a ser salvo
     */
    public void merge(String id, Node node) {
        try {
            //seta as credênciais da aplicação para autenticação com o banco
            AuthFactory.getInstanceAuthFactory().isAppAutentication();
            //seta o objeto e o id
            Map<String, Object> objeto = new HashMap();
            objeto.put(id, node);
            //detecta alterações na tabela
            notificar();
            //informa a tabela e salva
            getReference("nodes").updateChildrenAsync(objeto);
            //gera um toquem com dados do usuário da aplicação
            gerarToken();

        } catch (InterruptedException ex) {
            Logger.getLogger(NodeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(NodeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }

    
    /**
     * Método para ouvir a rede e detectar qualquer evento de alterações
     * (altualização, exclusão, adição...) na tabela.
     *
     * obs:
     *
     * Garantias de evento do banco de dados Os eventos serão sempre acionados
     * quando o estado local mudar. No fim, eles sempre refletirão o estado
     * correto dos dados, mesmo quando as operações ou o tempo locais causem
     * diferenças temporárias, como a perda temporária de conexão com a rede. As
     * gravações de um único cliente sempre serão gravadas no servidor e
     * transmitidas a outros usuários, respeitando a ordem. Os eventos "value"
     * sempre serão acionados por último e sempre conterão as atualizações de
     * todos os outros eventos ocorridos antes da geração do instantâneo.
     */
    private void notificar() {
        getReference("nodes").addChildEventListener(
                new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot ds, String chaveAnterior) {
                Node u = ds.getValue(Node.class);
                System.out.println("Um novo elemento foi adicionado:\n" + u.toString());
                System.out.println("ID da chave anterior:\n\n" + chaveAnterior);
            }

            @Override
            public void onChildChanged(DataSnapshot ds, String string) {
                Node u = ds.getValue(Node.class);
                System.out.println("Alteração detectada em:\n\n" + u.toString());
            }

            @Override
            public void onChildRemoved(DataSnapshot ds) {
                Node u = ds.getValue(Node.class);
                System.out.println("O seguinte dado foi excluído:\n\n" + u.toString());
            }

            @Override
            public void onChildMoved(DataSnapshot ds, String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

    }

    public void listarTodos() {
        try {
            AuthFactory.getInstanceAuthFactory().isAppAutentication();
            //listar todos por nome
            getReference("nodes").orderByKey().addChildEventListener(
                    new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot ds, String key) {
                    Node u = ds.getValue(Node.class);
                    System.out.println("Chave: " + key + " Node: " + ds.toString());
                }

                @Override
                public void onChildChanged(DataSnapshot ds, String string) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void onChildRemoved(DataSnapshot ds) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void onChildMoved(DataSnapshot ds, String string) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void onCancelled(DatabaseError de) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            }
            );

            gerarToken();
        } catch (InterruptedException ex) {
            Logger.getLogger(NodeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(NodeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *  retorna uma referência do banco de dados no caminho especificado
     *  caminho: é o caminho de onde desejo recuperar uma instância
     **/
    private DatabaseReference getReference(String caminho){
        return FirebaseDatabase.getInstance().getReference(caminho);
    }
    
    /**
     * Método para buscar um dado específicado pelo campo e valor
     * 
     * @param field campo usado como critério de busca
     * @param value valor do campo exemplo fiel = email value = "test@live.com"
     */
    public void buscar(String field, String value) {
        try {
            AuthFactory.getInstanceAuthFactory().isAppAutentication();
            getReference("node1").orderByChild(field).equalTo(value).addChildEventListener(
                    new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot ds, String key) {
                    Node u = ds.getValue(Node.class);
                    System.out.println("onChildAdded=>Chave: " + key + " Node: " + ds.toString());
                }

                @Override
                public void onChildChanged(DataSnapshot ds, String key) {
                    Node u = ds.getValue(Node.class);
                    System.out.println("onChildChanged=> Chave: " + key + " Node: " + ds.toString());
                }

                @Override
                public void onChildRemoved(DataSnapshot ds) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void onChildMoved(DataSnapshot ds, String string) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void onCancelled(DatabaseError de) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });

            gerarToken();
        } catch (InterruptedException ex) {
            Logger.getLogger(NodeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(NodeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método para deletar um objeto da tabela por completo
     * @param key é o id do objeto a ser deletado
     */
    public void deletar(String key) {
        try {
            AuthFactory.getInstanceAuthFactory().isAppAutentication();
            getReference("nodes/"+key).removeValueAsync();
            notificar();
            gerarToken();
        } catch (InterruptedException ex) {
            Logger.getLogger(NodeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(NodeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
