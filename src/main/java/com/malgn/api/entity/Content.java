package com.malgn.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Long viewCount;

    private LocalDateTime createdDate;

    @Column(nullable = false, length = 50)
    private String createdBy;

    private LocalDateTime lastModifiedDate;

    @Column(length = 50)
    private String lastModifiedBy;
}