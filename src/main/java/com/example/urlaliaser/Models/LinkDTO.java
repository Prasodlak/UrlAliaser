package com.example.urlaliaser.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkDTO{
    private Long id;
    private String url;
    private String alias;
    private Integer hitCount;

    public LinkDTO(Link link){
        this.id = link.getId();
        this.url = link.getUrl();
        this.alias = link.getAlias();
        this.hitCount = link.getHitCount();
    }
}
