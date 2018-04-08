/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.eaj.orion.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lber
 */

@IgnoreExtraProperties
public class Usuario {
    
    private String nome;
    private String email;

    public Usuario() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    
    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
    
    @Exclude
    public Map<String, Object> toMap(){ 
        HashMap<String, Object> result = new HashMap();
        result.put("nome", nome);
        result.put("email", email);
        return result;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
