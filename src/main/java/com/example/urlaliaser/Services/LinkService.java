package com.example.urlaliaser.Services;

import com.example.urlaliaser.Models.Link;

import java.util.List;

public interface LinkService {
    void addNewLink (String url, String alias);
    String getSecretCode(String alias);
    boolean alreadyExistingLink(String alias);
    void counterHittingSameAlias(String alias);
    String getUlrAddress(String alias);
    List<Link> getAll();
    void deleteLink(Long id);
    Link getById(Long id);
}
