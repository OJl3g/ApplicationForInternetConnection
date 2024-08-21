package org.ojl3g.applicationforinternetconnection.controller;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.ojl3g.applicationforinternetconnection.model.City;
import org.ojl3g.applicationforinternetconnection.model.Street;
import org.ojl3g.applicationforinternetconnection.service.CityService;
import org.ojl3g.applicationforinternetconnection.service.StreetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ApplicationController {

    private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);
    private final CityService cityService;
    private final StreetService streetService;

    public ApplicationController(CityService cityService, StreetService streetService) {
        this.cityService = cityService;
        this.streetService = streetService;
    }


    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "apply")
    public String apply() {
        return "addApplication";
    }

    @GetMapping(value = "/contact")
    public String contact() {
        return "contact";
    }

    @PostMapping("/submit-application")
    public String addApplicationPost(
            //RequestParam параметры запроса -
            @RequestParam String name,
            @RequestParam String city,
            @RequestParam String house,
            @RequestParam String street,
            @RequestParam String phone,
            @RequestParam String tariff,
            Model model) {


        List<City> allCity = cityService.getAllCity();
        Optional<City> first = allCity.stream()
                .filter(c -> city.equalsIgnoreCase(c.getName()))
                .findFirst();
        log.info(allCity.toString());
        log.info(first.toString());

        if (first.isPresent()) {
            model.addAttribute("message", "Ваша заявка принята");
            model.addAttribute("color", "green");
        } else { //возможно этот  else перенести в самый низ ?
            model.addAttribute("message", "Ваш город не обслуживается");
            model.addAttribute("color", "red");
        }

        List<Street> allStreet = streetService.getAllStreet();
        Optional<Street> first1 = allStreet.stream()
                .filter(c -> street.equalsIgnoreCase(c.getStreetName()))
                .findFirst();
        log.info(allStreet.toString());
        log.info(first1.toString());

        if (first1.isPresent()) {
            model.addAttribute("message", "Ваша заявка принята");
            model.addAttribute("color", "green");
        } else {
            model.addAttribute("message", "Ваша улица не обслуживается");
            model.addAttribute("color", "red");
        }

        return "addApplication";
    }


}
