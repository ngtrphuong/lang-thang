package com.langthang.model;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "password_reset_token")
@Getter
public class PasswordResetToken {
    private static final int EXPIRATION = 60 * 24 * 365;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;

    @OneToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "account_id")
    private Account account;

    @Column(name = "expire_date")
    private Date expireDate;

    public PasswordResetToken(String token, Account account) {
        this.token = token;
        this.account = account;
        this.expireDate = calculateExpireDate(EXPIRATION);
    }

    public PasswordResetToken() {
    }

    private Date calculateExpireDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}