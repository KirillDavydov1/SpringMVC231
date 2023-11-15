package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.ServiceUser;

@Controller
public class UsersController {

    @Autowired
    private ServiceUser serviceUser;

    @GetMapping("/show")
    public String showAllUsers(Model model) {
        model.addAttribute("listUsers", serviceUser.listAll());
        return "index";
    }

    @GetMapping("/getUserById")
    public String getUserById(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", serviceUser.get(id));
        return "showUser";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        serviceUser.update(user);
        return "redirect:/show";
    }

    @GetMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        serviceUser.save(user);
        return "redirect:/show";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") long id, Model model) {
        serviceUser.delete(id);
        return "redirect:/show";
    }
}
