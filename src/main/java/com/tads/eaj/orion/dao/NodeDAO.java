/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.eaj.orion.dao;

import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tads.eaj.orion.model.Node;
import com.tads.eaj.orion.model.Usuario;
import com.tads.eaj.orion.test.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lber
 */
public class NodeDAO {

    private Node no;
    private String key;//chave para excluir o objeto
    private List<Node> lista;

    public void buscar(String field, String value) throws InterruptedException, ExecutionException {
        no = null;
        AuthFactory.getInstanceAuthFactory().isAppAutentication();
        Query query = getReferenceDataBase().child("nodes").orderByChild(field).equalTo(value);
        gerarToken();
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot ds, String string) {
                setNo(ds.getValue(Node.class));
                key = ds.getKey();
                System.out.println("dado: " + ds.getValue());
                System.out.println("no: " + no.toString() + " key " + key);
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
    }

    public void buscarPorId(Integer id) {
        no = null;
        AuthFactory.getInstanceAuthFactory().isAppAutentication();
        Query query = getReferenceDataBase().child("nodes").orderByChild("id").equalTo(id);
        gerarToken();
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot ds, String string) {
                setNo(ds.getValue(Node.class));
                key = ds.getKey();
                System.out.println("dado: " + ds.getValue());
                System.out.println("no: " + no.toString() + " key " + key);
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
    }

    public void listar() throws InterruptedException, ExecutionException {
        lista = null;
        //consultas: https://firebase.google.com/docs/database/admin/retrieve-data?hl=pt-br
        AuthFactory.getInstanceAuthFactory().isAppAutentication();//registra o app
        DatabaseReference ref = getReferenceDataBase().child("nodes");
        gerarToken();
        ref.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot ds, String key) {
                //insere a lista de objetos vindos do banco na lista
                no = ds.getValue(Node.class);
                if (no != null) {
                    if (lista == null) {
                        lista = new ArrayList();
                    }
                    lista.add(no);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot ds, String key) {
                Usuario u = ds.getValue(Usuario.class);
                System.out.println("Changed=> key: " + key + "\nUsuario: " + u.toString());
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

    }

    public void excluir(final Integer id) throws InterruptedException, ExecutionException {
        no = null;
        key = null;
        buscarPorId(id);

        gerarToken();
        if (!key.equals("")) {
            System.out.println("\nEncontrado: " + getNo().toString() + "\nkey " + key + "\n");
            getReferenceDataBase("nodes/" + key).removeValueAsync();
        } else {
            System.out.println("Objeto não encontrado");
        }

    }

    public void atualizar(final Integer id, Node node) {
        no = null;
        key = null;

        buscarPorId(id);
        gerarToken();

        if (!key.equals("")) {
            System.out.println("node old: " + getNo());
            System.out.println("node new: " + node);
            setNo(node);
            Map<String, Object> childUpdates = new HashMap();
            childUpdates.put("/nodes/" + key, getNo());

            getReferenceDataBase().updateChildrenAsync(childUpdates);
        } else {
            System.out.println("Erro ao tentar atualizar");
        }

    }

    public boolean salvar(Node newNode) throws InterruptedException, ExecutionException {
        AuthFactory.getInstanceAuthFactory().isAppAutentication();//registra o app
        DatabaseReference ref = getReferenceDataBase().child("nodes");
        //push() gera uma chave exclusiva para cada novo filho
        if (newNode.getId() != null && newNode.getRegiao() != null && newNode.getEnergia() != null) {
            ref.push().setValueAsync(newNode);
            gerarToken();
            return true;
        }
        return false;

    }

    public static String getUserById(String uid) throws InterruptedException, ExecutionException {

        UserRecord userRecord = FirebaseAuth.getInstance().getUserAsync(uid).get();
        System.out.println("Successfully fetched user data: " + userRecord.getUid());
        return userRecord.getUid();

    }

    public static void listAllUsers() throws InterruptedException, ExecutionException {
        ListUsersPage page = FirebaseAuth.getInstance().listUsersAsync(null).get();
        while (page != null) {
            for (ExportedUserRecord user : page.getValues()) {
                System.out.println("User: " + user.getUid() + "\n" + user.getEmail());
            }
            page = page.getNextPage();
        }
        page = FirebaseAuth.getInstance().listUsersAsync(null).get();
        for (ExportedUserRecord user : page.iterateAll()) {
            System.out.println("User: " + user.getUid());
        }
    }

    private static void createCustomToken(String uid) throws InterruptedException, ExecutionException {

        String customToken = FirebaseAuth.getInstance().createCustomTokenAsync("ZqUyhCnHIiVxmWhMtUaxvJRWYbm2").get();
        System.out.println("Created custom token: " + customToken);
    }

    public void gerarToken() {
        try {
            listAllUsers();
            createCustomToken("ZqUyhCnHIiVxmWhMtUaxvJRWYbm2");
        } catch (InterruptedException ex) {
            Logger.getLogger(NodeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(NodeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DatabaseReference getReferenceDataBase() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public static DatabaseReference getReferenceDataBase(String caminho) {
        return FirebaseDatabase.getInstance().getReference(caminho);
    }

    public Node getNo() {
        return no;
    }

    public void setNo(Node no) {
        this.no = no;
    }

    public List<Node> getLista() {
        return lista;
    }

    public void setLista(List<Node> lista) {
        this.lista = lista;
    }

}
