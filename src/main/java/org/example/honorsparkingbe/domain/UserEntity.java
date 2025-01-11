package org.example.honorsparkingbe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Setter
@Getter
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L; // 직렬화 버전 ID

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;
}
