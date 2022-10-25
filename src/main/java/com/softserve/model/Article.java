package com.softserve.model;

import java.sql.Timestamp;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Luis Ábrego
 * Entity for Article table
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column ( name = "id_article", columnDefinition = "serial")
    private Integer idArticle;

    @Column ( name = "title")
    private String title;

    @Column ( name = "description")
    private String description;

    @Column ( name = "image")
    private String image;

    @Column ( name = "id_app_user")
    private Integer idAppUser;

    @Column ( name = "is_commentable", columnDefinition = "boolean default true")
    private Boolean isCommentable;

    @Column ( name = "status", columnDefinition = "serial4 default 1")
    private Integer status;

    @Column ( name = "creation_date")
    private Timestamp creationDate;

    @Column ( name = "modification_date")
    private Timestamp modificationDate;

    @Column(name = "description_html")
    private String descriptionHtml;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private Team team;
}
