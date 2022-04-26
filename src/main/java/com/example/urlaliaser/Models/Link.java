package com.example.urlaliaser.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Link extends BaseEntity{
    private String url;
    private String alias;
    private String secretCode;
    private Integer hitCount;


    public Link(Long id, String url, String alias) {
        super(id);
        this.url = url;
        this.alias = alias;
        this.hitCount = 1;
    }

    public Link( String url, String alias, String secretCode) {
        this.url = url;
        this.alias = alias;
        this.secretCode = secretCode;
    }

    public Link(String url, String alias) {
        this.alias = alias;
        this.url = url;
    }
}
