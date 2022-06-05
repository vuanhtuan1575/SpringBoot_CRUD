package com.vnpay.security.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "table_sessionmanager")
@DynamicUpdate
public class SessionManagerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    protected Long id;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "expiration", nullable = true)
    private int expiration;

    @Column(name = "status", nullable = true)
    private String status;

    @Column(name = "tokentype", nullable = true)
    private String tokenType;

    @Column(name = "refresh_token", nullable = true)
    private String refreshToken;
}
