package com.github.cangoksel.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by herdemir on 20.11.2015.
 */
public interface TokenRepository extends Repository<Token, UUID> {
    Optional<Token> findOne(UUID id);

    Optional<Token> findByValue(UUID tokenValue);

    Token save(Token token);

    void delete(Token entity);

    @Query("select t from Token t where t.deleted=false and t.id= :kullanici and t.value= :token and t.expiryDate between :tarih1 and :tarih2")
    Optional<Token> findByKullaniciAndTokenAndExpiryDateBetween(@Param("kullanici") UUID kullaniciId,
                                                                @Param("token") UUID token, @Param("tarih1") LocalDateTime now, @Param("tarih2") LocalDateTime localDateTime);

}
