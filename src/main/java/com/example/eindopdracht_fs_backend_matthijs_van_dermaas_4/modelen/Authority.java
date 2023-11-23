package com.example.eindopdracht_fs_backend_matthijs_van_dermaas_4.modelen;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(AuthorityKey.class)
@Table(name = "authorities")
public class Authority implements Serializable {
    @Id
    @Column(nullable = false)
    private String username;
    @Id
    @Column(nullable = false)
    private String authority;
    public Authority() {}
    @ManyToOne
    @JoinColumn(name= "user_Id")
    private User user;

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }

}