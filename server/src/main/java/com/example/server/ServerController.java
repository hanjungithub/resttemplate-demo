package com.example.server;

import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ServerController {
    @RequestMapping(value="getUser")
    public void getUser(User user, HttpServletResponse response) throws IOException {
        User result = new User();
        if(user.getUsername().equals("admin")){
            result.setUsername("admin");
            result.setPassword("中文11111");
        }
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().print(JSONObject.toJSONString(result));
        response.getWriter().flush();
    }
    @RequestMapping(value="getUser1")
    @ResponseBody
    public User getUser1(@RequestBody User user, HttpServletResponse response) throws IOException {
        User result = new User();
        if(user.getUsername().equals("admin")){
            result.setUsername("admin");
            result.setPassword("中文");
        }
       return result;
    }

}
