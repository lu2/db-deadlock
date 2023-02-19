package net.haltuf.playground.dbdeadlock.repository;

import net.haltuf.playground.dbdeadlock.entity.Beta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BetaRepository extends JpaRepository<Beta, Long> {

    @Query(value = "SELECT 1 FROM BETA WITH (TABLOCKX)", nativeQuery = true)
    void lockTable();

}
