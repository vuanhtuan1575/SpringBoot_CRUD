package com.vnpay.security.repository;

import com.vnpay.security.entity.SessionManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface SessionManagerRepository
        extends JpaRepository<SessionManagerEntity, Long>, JpaSpecificationExecutor<SessionManagerEntity> {

SessionManagerEntity findByUserName(String userName);

Optional<SessionManagerEntity> findByToken(String token);

@Modifying
Long deleteByUserName(String userName);

@Modifying
@Query("DELETE FROM SessionManagerEntity  jwtToken WHERE jwtToken.userName=?1 AND jwtToken.token=?2")
void deleteByUserNameAndToken(String userName, String token);
}
