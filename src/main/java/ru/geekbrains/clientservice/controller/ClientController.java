package ru.geekbrains.clientservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.clientservice.config.WebSecurityConfig;
import ru.geekbrains.clientservice.entity.Client;
import ru.geekbrains.clientservice.service.ClientService;

/**
 * @author Nick Musinov e-mail:n.musinov@gmail.com
 * 19.04.2022
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class ClientController {

    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping ("/users")
    public String findAll(Model model) {
        model.addAttribute("clients", this.clientService.findAllClients());
        return "index";
    }

    @GetMapping({"/registration"})
    public ModelAndView registration(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
//        System.out.println(passwordEncoder.encode("$2a$10$ygEo61EjeHlC9QPVB.6o2.5x5v9j44dW3cocJMskA74VRhJ0Aa2H2"));
        return new ModelAndView("registration");
    }

    @PostMapping({"/registration"})
    public String registration(String clientName, Integer roleId, String clientSecondName,
                               int age, boolean sex, String clientPassword, String confPassword,
                               String clientPhoto, Model model) {
        if (!confPassword.equals(clientPassword)) {
            System.out.println("Passwords do not match");
            return "redirect:/api/registration";
        } else if (clientService.findClientByClientName(clientName) != null) {
            System.out.println("Client is registered already");
            return "redirect:/api/registration";
        } else {
            Client client = new Client(clientName, 2,true, clientSecondName, age, false,
                    clientPassword,
                    "photo",
                    confPassword);
            this.clientService.saveClient(client);
        }
        return "redirect:/api/welcome";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

}
