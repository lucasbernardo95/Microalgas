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
import com.tads.eaj.orion.dao.UsuarioDAO;
import com.tads.eaj.orion.model.Usuario;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author lber
 */
public class Test {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//                UsuarioDAO dao = new UsuarioDAO();
//                dao.deletar("node10");//ok
//                dao.listarTodos();//ok
//                dao.merge("node2", new Usuario("lol beta 2", "prata@live.com.br"));//ok
//                dao.buscar("nome", "lol beta");//ok
//        salvar();
//        atualizar("-L9hUBXshdxisEv184-Z", new Usuario("chupa cabra", "querodormir@live.com"));
        listar();
//        excluir("-L9hj2QCqZgCW-tHUWsZ");
//        buscar("juninho@yahoo.com");
//        buscaTeste();
        listAllUsers();//lista os usuários encontrados
//        getUserById("lucas-uid");
        createCustomToken("ZqUyhCnHIiVxmWhMtUaxvJRWYbm2");
    }

    private static DatabaseReference getReferenceDataBase() {
        return FirebaseDatabase.getInstance().getReference();
    }

    private static DatabaseReference getReferenceDataBase(String caminho) {
        return FirebaseDatabase.getInstance().getReference(caminho);
    }

    private static void buscaTeste2() {
        AuthFactory.getInstanceAuthFactory().isAppAutentication();
        Query query = getReferenceDataBase().child("teste").child("user1").orderByChild("nome").equalTo("1");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                System.out.println("dado recebido: " + ds.toString());
            }

            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    private static void buscaTeste() {
        AuthFactory.getInstanceAuthFactory().isAppAutentication();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("teste")
            .addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot ds, String string) {
                
                System.out.println("recebeu esse papangu: " + ds.toString());
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

    private static void buscar() throws InterruptedException, ExecutionException {
        AuthFactory.getInstanceAuthFactory().isAppAutentication();
        getReferenceDataBase("teste").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot ds, String key) {
                Usuario u = ds.getValue(Usuario.class);
                System.out.println("Added=> key: " + key + "\nUsuario: " + u.toString());
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

    private static void excluir(String key) throws InterruptedException, ExecutionException {
        AuthFactory.getInstanceAuthFactory().isAppAutentication();
        getReferenceDataBase("teste/" + key).removeValueAsync();
    }

    private static void listar() throws InterruptedException, ExecutionException {

        //consultas: https://firebase.google.com/docs/database/admin/retrieve-data?hl=pt-br
        AuthFactory.getInstanceAuthFactory().isAppAutentication();//registra o app
        DatabaseReference ref = getReferenceDataBase().child("teste");
        listAllUsers();//lista os usuários encontrados
        createCustomToken("ZqUyhCnHIiVxmWhMtUaxvJRWYbm2");
        
        System.out.println("\nkey" + ref.getRoot() + "\n" + ref.toString() + "\n" + ref.getRef() + "\n" + ref.getKey() + "\n");
        
        ref.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot ds, String key) {
                Usuario u = ds.getValue(Usuario.class);
                System.out.println("Added=> key: " + key + "\nUsuario: " + u.toString());
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

    private static void atualizar(String key, Usuario usuario) throws InterruptedException, ExecutionException {
//        AuthFactory.getInstanceAuthFactory().isAppAutentication();//registra o app
//        getReferenceDataBase("teste").addChildEventListener(new ChildEventListener)
//        
//        listAllUsers();//lista os usuários encontrados
////        getUserById("lucas-uid");
//        createCustomToken("ZqUyhCnHIiVxmWhMtUaxvJRWYbm2");
//
//        Map<String, Object> update = new HashMap();
//        update.put("2", usuario);
//
//        ref.updateChildrenAsync(update);
    }

    private static void salvar() throws InterruptedException, ExecutionException {
        AuthFactory.getInstanceAuthFactory().isAppAutentication();//registra o app
        DatabaseReference ref = getReferenceDataBase().child("teste");
        DatabaseReference newRef = ref.push();//ue gera uma chave exclusiva para cada novo filho
        listAllUsers();//lista os usuários encontrados
//        getUserById("lucas-uid");
        createCustomToken("ZqUyhCnHIiVxmWhMtUaxvJRWYbm2");
        Usuario u = new Usuario("1", "lucasbernardo@yahoo.com");

        newRef.setValueAsync(u);

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
