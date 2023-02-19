package net.haltuf.playground.dbdeadlock.repository;

import net.haltuf.playground.dbdeadlock.entity.Alpha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlphaRepository extends JpaRepository<Alpha, Long> {

    @Query(value = "SELECT 1 FROM ALPHA WITH (TABLOCKX)", nativeQuery = true)
    void lockTable();

}
