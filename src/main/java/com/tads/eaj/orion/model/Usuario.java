/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.eaj.orion.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lber
 */

@IgnoreExtraProperties
public class Usuario implements Serializable{
    
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 31 * hash + (this.email != null ? this.email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", email=" + email + '}';
    }
    
}
