package org.ojl3g.applicationforinternetconnection.controller;

import org.ojl3g.applicationforinternetconnection.model.Application;
import org.ojl3g.applicationforinternetconnection.model.City;
import org.ojl3g.applicationforinternetconnection.model.Street;
import org.ojl3g.applicationforinternetconnection.repository.ApplicationRepository;
import org.ojl3g.applicationforinternetconnection.service.ApplicationService;
import org.ojl3g.applicationforinternetconnection.service.CityService;
import org.ojl3g.applicationforinternetconnection.service.StreetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ApplicationController {

    private ApplicationService applicationService;

    private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);
    private final CityService cityService;
    private final StreetService streetService;
    private final ApplicationRepository applicationRepository;

    public ApplicationController(CityService cityService,
                                 StreetService streetService,
                                 ApplicationRepository applicationRepository,
                                 ApplicationService applicationService) {
        this.cityService = cityService;
        this.streetService = streetService;
        this.applicationRepository = applicationRepository;
        this.applicationService = new ApplicationService(applicationRepository);
    }


    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/apply")
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
            Model model)
    {

        //получение всех городов из базы данных
        List<City> allCity = cityService.getAllCity();

        //проверка есть ои город который ввел пользователь через фильтр
        //если города нет то Optional будет пустой
        Optional<City> cityOptional = allCity.stream()
                .filter(c -> city.equalsIgnoreCase(c.getName()))
                .findFirst();

        //если Optional пустой вернуть на ту же страницу с сообщением
        if (!cityOptional.isPresent()) {
            model.addAttribute("message", "ваш город не обслуживается");
            model.addAttribute("color", "red");
            return "addApplication";
        }

        //если же город не пустой то получаем его
        City city1 = cityOptional.get();
        boolean streetInCity = false;

        //проверка что в городе есть улица которую ввел пользователь
        List<Street> streets = city1.getStreets();
        for (Street streetObject : streets) {
            if (streetObject.getStreetName().equalsIgnoreCase(street)) {
                streetInCity = true;
                break;
            }
        }

        //если не находит улицу в городе вернуть те же страницу с сообщением
        if (!streetInCity) {
            model.addAttribute("message", "ваша улица не обслуживается");
            model.addAttribute("color", "red");
            return "addApplication";
        }


        //если город и улица найдены в базе данных добавляем заявку в бд
        model.addAttribute("message", "Ваша заявка принята");
        model.addAttribute("color", "green");
        Application application = new Application();
        application.setName(name);
        application.setCity(city);
        application.setHouse(house);
        application.setStreet(street);
        application.setPhone(phone);
        application.setTariff(tariff);
        applicationService.saveApplication(application);

        return "addApplication";
    }


}
