package com.ericlam.minestrike.modals;

import javax.persistence.*;

@Entity
@Table(name = "`Auth`")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
