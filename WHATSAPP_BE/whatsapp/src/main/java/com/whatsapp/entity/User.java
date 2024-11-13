package com.whatsapp.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String full_name;
    private String email;
    private String profile_picture;
    private String password;
//
//    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
//    private List<Notification> notifications = new ArrayList<>();
}
