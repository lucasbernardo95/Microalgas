/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.eaj.orion.dao;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tads.eaj.orion.model.Usuario;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lber
 */
public class UsuarioDAO {

//    private Usuario usuario;
    private void gerarToken() throws InterruptedException, ExecutionException {
        AuthFactory.getUserById("lucas-uid");//Recupera o uid do usuário
        AuthFactory.getUserByEmail("bernardotriton@gmail.com");//recupera o email
        AuthFactory.getUserByPhoneNumber("+5584991770750");//recupera o telefone
        AuthFactory.listAllUsers();//lista os usuários encontrados
        AuthFactory.createCustomToken();//cria um token para liberar as operações
    }

    /**
     * Método para atualizar um objeto existente no banco ou criar um novo caso
     * o dado enviado não exista na tabela informada
     *
     * @param id chave primária do objeto (identificador)
     * @param usuario ojeto a ser salvo
     */
    public void merge(String id, Usuario usuario) {
        try {
            //seta as credênciais da aplicação para autenticação com o banco
            AuthFactory.authenticateWithPrivileges();
            //seta o objeto e o id
            Map<String, Object> objeto = new HashMap();
            objeto.put(id, usuario);
            //detecta alterações na tabela
            notificar();
            //informa a tabela e salva
            AuthFactory.getReference().child("usuarios").updateChildrenAsync(objeto);
            //gera um toquem com dados do usuário da aplicação
            
            gerarToken();

        } catch (IOException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        AuthFactory.getReference().addChildEventListener(
                new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot ds, String chaveAnterior) {
                Usuario u = ds.getValue(Usuario.class);
                System.out.println("Um novo elemento foi adicionado:\n" + u.toString());
                System.out.println("ID da chave anterior:\n\n" + chaveAnterior);
            }

            @Override
            public void onChildChanged(DataSnapshot ds, String string) {
                Usuario u = ds.getValue(Usuario.class);
                System.out.println("Alteração detectada em:\n\n" + u.toString());
            }

            @Override
            public void onChildRemoved(DataSnapshot ds) {
                Usuario u = ds.getValue(Usuario.class);
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
            AuthFactory.authenticateWithPrivileges();
            //listar todos por nome
            AuthFactory.getReference().orderByKey().addChildEventListener(
                    new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot ds, String key) {
                    Usuario u = ds.getValue(Usuario.class);
                    System.out.println("Chave: " + key + " usuario: " + ds.toString());
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
        } catch (IOException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            AuthFactory.authenticateWithPrivileges();
            getReference("usuarios").orderByChild(field).equalTo(value).addChildEventListener(
                    new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot ds, String key) {
                    Usuario u = ds.getValue(Usuario.class);
                    System.out.println("Chave: " + key + " usuario: " + ds.toString());
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
            });

            gerarToken();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método para deletar um objeto da tabela por completo
     * @param key é o id do objeto a ser deletado
     */
    public void deletar(String key) {
        try {
            AuthFactory.authenticateWithPrivileges();
            AuthFactory.getReference().child("usuarios/"+key).removeValueAsync();
            notificar();
            gerarToken();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
