package com.example.urlaliaser.Controllers;

import com.example.urlaliaser.Services.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    private LinkService linkService;


    @Autowired
    public MainController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/")
    public String homePage(Model model, @RequestParam(required = false) String message, @RequestParam(required = false) String url, @RequestParam(required = false) String alias, @RequestParam(required = false) String errorMessage) {
        if (message != null) {
            model.addAttribute("successMessage", message);
        }
        if (errorMessage != null) {
            model.addAttribute("url", url);
            model.addAttribute("alias", alias);
            model.addAttribute("errorMessage", errorMessage);
        }

        return "main";
    }

    @PostMapping("/save-link")
    public String submitLink(@RequestParam String url, @RequestParam String alias) {
        String message;
        if (linkService.alreadyExistingLink(alias)) {
            message = "Your alias is already in use!";
            return "redirect:/?errorMessage=" + message + "&url=" + url + "&alias=" + alias;
        }
        linkService.addNewLink(url, alias);
        message = "Your URL is aliased to " + alias + " and your secret code is " + linkService.getSecretCode(alias);
        return "redirect:/?message=" + message;
    }

    @GetMapping("/a/{alias}")
    public String counter(@PathVariable String alias){

        if (linkService.alreadyExistingLink(alias)){
            linkService.counterHittingSameAlias(alias);
            String url = linkService.getUlrAddress(alias);
            return "redirect:" + url ;
        }
        return "errorPage";
    }

}
