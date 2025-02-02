package in.ashokit.controller;

import in.ashokit.feignClient.WelcomeFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetRestController {
   @Autowired
    private WelcomeFeignClient welcomeFeignClient;
    @GetMapping("/Greet")
    public String getGreetMessage(){
        String welocmemessage=welcomeFeignClient.getWelcomeMessage ();
        String message="Good Morning Ashok IT...";
        return message+" "+welocmemessage;
    }
}
