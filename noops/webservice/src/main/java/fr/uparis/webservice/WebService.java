package fr.uparis.webservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebService {

    @PostMapping("/person")
    public void person(@RequestBody User user){
        System.out.println("Dans le web service : " + user);
    }
    
    @GetMapping("/")
    public String welcome() {
    	return "Welcome to webservice";
    }

}
