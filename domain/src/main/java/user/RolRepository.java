package user;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Created by gcan on 04.02.2015.
 */
public interface RolRepository extends JpaRepository<Rol, UUID>, QueryDslPredicateExecutor<Rol> {

    @EntityGraph(value = "Rol.withYetkiler", type = EntityGraph.EntityGraphType.LOAD)
    Rol findById(@Param("id") UUID id);

    Optional<Rol> findByKod(String kod);

    @Override
    Set<Rol> findAll(Predicate predicate);

    Optional<Rol> findByAciklama(String aciklama);

    @Query("SELECT r FROM Rol r WHERE (r.rolTipi = :rolTipi or r.rolTipi is null)")
    List<Rol> findByAciklamaContainingIgnoreCase(String query);
}
