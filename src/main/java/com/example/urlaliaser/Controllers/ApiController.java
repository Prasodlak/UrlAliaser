package com.example.urlaliaser.Controllers;

import com.example.urlaliaser.Models.Link;
import com.example.urlaliaser.Models.LinkDTO;
import com.example.urlaliaser.Models.SecretCodeDTO;
import com.example.urlaliaser.Services.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {
    private LinkService linkService;

    @Autowired
    public ApiController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/api/links")
    public ResponseEntity<?> getlinks(){
        List<LinkDTO> linkDTOS = new ArrayList<>();
        for (Link link: linkService.getAll()) {
            linkDTOS.add(new LinkDTO(link));
        }
        return ResponseEntity.status(200).body(linkDTOS);
    }

    @DeleteMapping("/api/links/{id}")
    public ResponseEntity<?> deleteLink(@PathVariable Long id, @RequestBody SecretCodeDTO secretCodeDTO) {
        Link foundedLink = linkService.getById(id);
        if (foundedLink == null) {
            return ResponseEntity.status(404).body("not exist");
        } else if (!foundedLink.getSecretCode().equals(secretCodeDTO.getSecretCode())) {
            return ResponseEntity.status(403).body("secret code doesnt match");
        } else {
            linkService.deleteLink(foundedLink.getId());
            return new ResponseEntity<>("successfully deleted", HttpStatus.NO_CONTENT);
        }
    }
}
