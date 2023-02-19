package net.haltuf.playground.dbdeadlock.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ALPHA")
@Data
public class Alpha {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATA", unique = false, nullable = false)
    private String data;

}
