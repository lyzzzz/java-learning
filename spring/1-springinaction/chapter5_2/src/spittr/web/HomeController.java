package spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lyzzzz on 2016-11-13.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {
    @RequestMapping()
    public String home() {
        return "home";
    }
}
