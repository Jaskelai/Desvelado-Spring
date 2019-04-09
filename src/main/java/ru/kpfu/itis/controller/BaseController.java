package ru.kpfu.itis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Controller
@RequestMapping("/")
public class BaseController {
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(ModelMap map) {
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap map) {
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("BC#home").build();
    }
}
