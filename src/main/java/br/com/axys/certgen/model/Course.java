package br.com.axys.certgen.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "courses")
public class Course {
    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(updatable = false)
    private String createdAt;

    @Column
    private String updatedAt;

    // Construtor padrão (necessário para JPA)
    public Course() {
        this.id = UUID.randomUUID().toString(); // Gera um UUID automaticamente
    }
}