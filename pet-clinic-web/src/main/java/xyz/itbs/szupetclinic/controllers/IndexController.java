package xyz.itbs.szupetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/","","index.html","index"})
    public String returnIndex(){
        return "index";
    }

    @RequestMapping({"/oops","/oups"})
    public String returnError() {
        return "error";
    }
}
