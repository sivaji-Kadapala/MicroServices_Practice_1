package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
//post:http://localhost:8080/actuator/refresh(whener any changes happens git yml files use this in postman
public class MessageController {
    @Value ( "${msg}" )
    private String msg;
    @GetMapping("/")
    public String getMsg(){
        return msg;
    }

}
