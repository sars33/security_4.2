package web.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"users", "/"})
    public String userList(ModelMap model) {
        List<User> users = userService.showAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "users/add")
    public String addUser(User user, ModelMap model) {
        model.addAttribute("user", user);
        return "add";
    }

    @PostMapping(value = "users/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addAndSave(user);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "users/{userId}/edit")
    public String editUser(@PathVariable("userId") Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping(value = "users/{userId}/edit")
    public String editUser(@ModelAttribute("user") User user, @PathVariable("userId") Long id) {
        User dbUser = userService.getById(id);
        user.getId();

        if (user.getPassword().equals(dbUser.getPassword()) || user.getPassword().isEmpty()) {
            user.setName(user.getName());
            user.setLastName(user.getLastName());
            user.setEmail(user.getEmail());
            user.setPassword(dbUser.getPassword());

        } else {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }

        userService.edit(user);

        return "redirect:/admin/";
    }

    @GetMapping(value = {"/users/{userId}/delete"})
    public String deleteUser(@PathVariable("userId") long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}