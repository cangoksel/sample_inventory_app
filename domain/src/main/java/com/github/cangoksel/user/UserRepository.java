package com.github.cangoksel.user;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author GC
 */
public interface UserRepository extends JpaRepository<Kullanici, UUID>, QueryDslPredicateExecutor<Kullanici> {
    @QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
    Kullanici findByEposta(String eposta);

    @Query("SELECT k FROM Kullanici k WHERE LOWER(CONCAT(k.ad ,' ',k.soyad)) LIKE LOWER(CONCAT('%',:query,'%')) AND k.id <> :kullaniciId")
    List<Kullanici> findByAdSoyadContainingIgnoreCaseAndNotIn(@Param("query") String query,
                                                              @Param("kullaniciId") UUID kullaniciId);

    @Override
    List<Kullanici> findAll(Predicate predicate);

    @Query("SELECT k FROM Kullanici k left join k.roller r where r.id = :rolId")
    List<Kullanici> kullaniciListByRol(@Param("rolId") UUID id);

    @Query("SELECT k FROM Kullanici k join fetch k.roller r join fetch  r.yetkiler y where k.eposta = :eposta")
    Kullanici fetchKullaniciWithRolYetki(@Param("eposta") String eposta);

    @Query("SELECT k FROM Kullanici k join fetch k.roller where k.eposta = :eposta")
    Kullanici fetchKullaniciWithRol(@Param("eposta") String eposta);

    @Query("SELECT k FROM Kullanici k WHERE LOWER(CONCAT(k.ad ,' ',k.soyad)) LIKE LOWER(CONCAT('%',:query,'%'))")
    List<Kullanici> findByAdSoyadContainingIgnoreCase(@Param("query") String query);

    Set<Kullanici> findByDeletedFalse();

    List<Kullanici> findByHesapEtkinTrue();

    @Query("select k.id from Kullanici k where DATE(k.lastLogin) between :date and :date1")
    List<UUID> findByLastLoginBetween(@Param("date") LocalDate date,
                                      @Param("date1") LocalDate date1);

    List<Kullanici> findByIdIn(List<UUID> kullaniciIdList);

}
