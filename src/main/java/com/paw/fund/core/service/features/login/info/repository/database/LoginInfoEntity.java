package com.paw.fund.core.service.features.login.info.repository.database;

import com.paw.fund.core.service.bootstrap.config.auditor.Auditor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "login_info")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginInfoEntity extends Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long loginInfoId;

    @Column
    Long accountId;

    @Column
    String accountEmail;

    @Column
    String refreshToken;

    @Column
    LocalDateTime accessExpiredAt;

    @Column
    LocalDateTime refreshExpiredAt;

    @Column
    BigDecimal latitude;

    @Column
    BigDecimal longitude;

    @Column
    String statusCode;

    @Column
    String statusName;
}
