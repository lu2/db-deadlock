package net.haltuf.playground.dbdeadlock.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "BETA")
@Data
public class Beta {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATA", unique = false, nullable = false)
    private String data;

}
