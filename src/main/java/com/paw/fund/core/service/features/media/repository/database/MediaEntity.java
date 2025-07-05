package com.paw.fund.core.service.features.media.repository.database;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medias")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MediaEntity extends Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long mediaId;

    @Column
    Long accountId;

    @Column
    Long reviewId;

    @Column
    Long petId;

    @Column
    Long shelterId;

    @Column
    String url;

    @Column
    String mediaTypeCode;

    @Column
    String mediaTypeName;
}
