package com.lyzzzz.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by 37 on 2017/3/10.
 */
@Controller
@RequestMapping("/")
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String home(Model model) {
        List<Contact> contacts = contactRepository.findAll();
        model.addAttribute("contacts", contacts);

        return "home";
    }
}
