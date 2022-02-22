package com.example.javamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @DeleteMapping(path="/delete")
    public void deleteUser (@RequestParam String name, @RequestParam String email) {
        List<User> users = userRepository.findByName(name);
        if(users.size() != 0){
            User user = users.get(0);
            userRepository.delete(user);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
