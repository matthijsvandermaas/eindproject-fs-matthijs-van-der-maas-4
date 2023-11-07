package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Security;



import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.AuthKey;
import jakarta.persistence.*;


@Entity
@IdClass(AuthKey.class)
@Table(name = "authorities")
public class Authority {

    @Id
    @Column(nullable = false)
    private String username;

    @Id
    @Column(nullable = false)
    private String authority;

    public Authority() {
    }

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
