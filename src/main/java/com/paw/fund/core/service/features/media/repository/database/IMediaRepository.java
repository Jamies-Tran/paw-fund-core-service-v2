package com.paw.fund.core.service.features.media.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMediaRepository extends JpaRepository<MediaEntity, Long> {
    List<MediaEntity> findAllByAccountId(Long accountId);
}
