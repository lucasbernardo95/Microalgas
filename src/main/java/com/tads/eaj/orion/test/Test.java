/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.eaj.orion.test;

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
import com.tads.eaj.orion.dao.AuthFactory;
import com.tads.eaj.orion.model.Usuario;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lber
 */
public class Test {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

//        salvar();//ok
//        atualizar("jubela");//ok
        listar();//ok
//        excluir("nome", "junin da mobilete");//ok
//        buscar("email", "angu@live.com");//pk
        gerarToken("ZqUyhCnHIiVxmWhMtUaxvJRWYbm2");
    }

    private static void gerarToken(String uid) {
        try {
            listAllUsers();
            createCustomToken(uid);
        } catch (InterruptedException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static DatabaseReference getReferenceDataBase() {
        return FirebaseDatabase.getInstance().getReference();
    }

    private static DatabaseReference getReferenceDataBase(String caminho) {
        return FirebaseDatabase.getInstance().getReference(caminho);
    }

    private static void buscar(String field, String value) throws InterruptedException, ExecutionException {
        AuthFactory.getInstanceAuthFactory().isAppAutentication();
        Query query = getReferenceDataBase().child("teste").orderByChild(field).equalTo(value);
        gerarToken("ZqUyhCnHIiVxmWhMtUaxvJRWYbm2");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                System.out.println("dado recebido: " + ds.toString());
                System.out.println("dado recebido: " + ds.getValue());
            }

            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    private static void listar() throws InterruptedException, ExecutionException {

        //consultas: https://firebase.google.com/docs/database/admin/retrieve-data?hl=pt-br
        AuthFactory.getInstanceAuthFactory().isAppAutentication();//registra o app
        DatabaseReference ref = getReferenceDataBase().child("teste");
        listAllUsers();//lista os usuários encontrados
        createCustomToken("ZqUyhCnHIiVxmWhMtUaxvJRWYbm2");

        ref.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot ds, String key) {

                Usuario u = ds.getValue(Usuario.class);
                System.out.println("key = " + key + " Usuario: " + u.toString());
                System.out.println("key = " + ds.getKey() + " Usuario: " + ds.getValue());
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

    private static void excluir(String field, final String value) throws InterruptedException, ExecutionException {

        AuthFactory.getInstanceAuthFactory().isAppAutentication();
        Query query = getReferenceDataBase().child("teste")/*.orderByChild(field).equalTo(value)*/;
        gerarToken("ZqUyhCnHIiVxmWhMtUaxvJRWYbm2");
        query.orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot ds, String string) {

                Usuario u = ds.getValue(Usuario.class);
                System.out.println("\nNome passado: " + value + " nome encontrado: " + u.getNome() + "\n\n");
                if (u.getNome().equalsIgnoreCase(value)) {
                    getReferenceDataBase("teste/" + ds.getKey()).removeValueAsync();
                    System.out.println("tchau querida!");
                } else {
                    System.out.println("deu bode!");
                    return;
                }

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

    private static void atualizar(final String value) throws InterruptedException, ExecutionException {
        AuthFactory.getInstanceAuthFactory().isAppAutentication();
        Query query = getReferenceDataBase().child("teste")/*.orderByChild(field).equalTo(value)*/;
        gerarToken("ZqUyhCnHIiVxmWhMtUaxvJRWYbm2");
        query.orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot ds, String string) {

                Usuario u = ds.getValue(Usuario.class);
                if (u.getNome().equalsIgnoreCase(value)) {
                    System.out.println("\nNome passado: " + value + " nome encontrado: " + u.getNome() + "\n\n");
                    u.setNome("papa angu da silva sauro");
                    u.setEmail("angu@live.com");

                    Map<String, Object> childUpdates = new HashMap();
                    childUpdates.put("/teste/" + ds.getKey(), u);


                    getReferenceDataBase().updateChildrenAsync(childUpdates);

                } else {
                    System.out.println("deu bode!");
                    return;
                }

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

    private static void salvar() throws InterruptedException, ExecutionException {
        AuthFactory.getInstanceAuthFactory().isAppAutentication();//registra o app
        DatabaseReference ref = getReferenceDataBase().child("teste");
//        DatabaseReference newRef = ref.push();//ue gera uma chave exclusiva para cada novo filho
        Usuario u = new Usuario("fudeno", "fudeno@yahoo.com");

        ref.push().setValueAsync(u);
        gerarToken("ZqUyhCnHIiVxmWhMtUaxvJRWYbm2");

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

    public static void createCustomToken(String uid) throws InterruptedException, ExecutionException {

        String customToken = FirebaseAuth.getInstance().createCustomTokenAsync("lucas-uid").get();
        System.out.println("Created custom token: " + customToken);
    }
}
