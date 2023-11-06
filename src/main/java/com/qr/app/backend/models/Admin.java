package com.qr.app.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.Date;

@Entity
@Table(name = "admin")
@RequiredArgsConstructor
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @Column(nullable = false, length = 200)
    private String name;
    @Column(nullable = false, length = 200)
    private String email;
    @Column(nullable = false, length = 200)
    private String password;
    @Column(nullable = false, length = 100)
    private String role;
    @Column(nullable = true, length = 4)
    private int otp;
    @Column(nullable = true, length = 20 )
    private Long otp_generate_time;
    @Column(nullable = false, length = 20)
    private Long expiration_otp;
}
