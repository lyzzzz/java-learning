package spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lyzzzz on 2016-11-15.
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {
    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model) {

        return "spittles";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterationFrom() {
        return "registerForm";
    }
}
