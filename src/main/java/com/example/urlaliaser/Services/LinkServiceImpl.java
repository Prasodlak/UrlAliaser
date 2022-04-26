package com.example.urlaliaser.Services;

import com.example.urlaliaser.Models.Link;
import com.example.urlaliaser.Repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class LinkServiceImpl implements LinkService{
    private LinkRepository linkRepository;

    @Autowired
    public LinkServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public void addNewLink(String url, String alias) {
        if (!url.equals(" ")|| !alias.equals(" ")){
            linkRepository.save(new Link(url, alias, getSecretCode()));
        }

    }

    @Override
    public String getSecretCode(String alias) {
        Optional<Link> optionalLink = linkRepository.findByAlias(alias);
        return optionalLink.orElse(null).getSecretCode();
    }

    @Override
    public boolean alreadyExistingLink(String alias) {
        Optional<Link> optionalLink = linkRepository.findByAlias(alias);
        if (optionalLink.isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public void counterHittingSameAlias(String alias) {
        Optional<Link> link = linkRepository.findByAlias(alias);
        link.get().setHitCount(link.get().getHitCount() + 1);
        linkRepository.save(link.get());
    }

    @Override
    public String getUlrAddress(String alias) {
        Optional<Link> link = linkRepository.findByAlias(alias);

        return link.get().getUrl();
    }

    @Override
    public List<Link> getAll() {
        return linkRepository.findAll();
    }

    @Override
    public void deleteLink(Long id) {
        linkRepository.deleteById(id);
    }

    @Override
    public Link getById(Long id) {
        Optional<Link> optionalLink = linkRepository.findById(id);
        if (!optionalLink.isPresent()){
            return null;
        }
        return optionalLink.get();
    }

    private String getSecretCode() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            stringBuilder.append(random.nextInt(10));

        }
        return stringBuilder.toString();
    }
}
