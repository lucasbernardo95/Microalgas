/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.eaj.orion.dao;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lber
 */
public class FactoryDAO {

    public FileInputStream serviceAccount;
    public FirebaseOptions options;

    public void FactoryDAO() throws FileNotFoundException, IOException {

        // Fetch the service account key JSON file contents
        setServiceAccount(new FileInputStream("C:\\Users\\lber\\Documents\\autentication.json"));
        // Initialize the app with a service account, granting admin privileges
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://orion-remote-manager.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
        
        // As an admin, the app has access to read and write all data, regardless of Security Rules
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference("restricted_access/secret_document");

//        // The app only has access to public data as defined in the Security Rules
//        DatabaseReference ref = FirebaseDatabase
//                .getInstance()
//                .getReference("/public_resource");
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot ds) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void onCancelled(DatabaseError de) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
    }

//    public FactoryDAO() throws IOException {
//        this.options = new FirebaseOptions.Builder()
//                .setCredential(FirebaseCredentials.fromCertificate(getServiceAccount()))
//                .setDatabaseUrl("https://orion-remote-manager.firebaseio.com/")
//                .build();
//
//        FirebaseApp.initializeApp(options);
//
//        // As an admin, the app has access to read and write all data, regardless of Security Rules
//        DatabaseReference ref = FirebaseDatabase
//                .getInstance()
//                .getReference("restricted_access/secret_document");
//
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot ds) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void onCancelled(DatabaseError de) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
//    }
    public FileInputStream getServiceAccount() {
        return serviceAccount;
    }

    public void setServiceAccount(FileInputStream serviceAccount) {
        this.serviceAccount = serviceAccount;
    }

    public FirebaseOptions getOptions() {
        return options;
    }

    public void setOptions(FirebaseOptions options) {
        this.options = options;
    }

}
