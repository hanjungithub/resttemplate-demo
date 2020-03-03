package com.example.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ClientController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value="/clientGetServerUser")
    public void clientGetServerUser(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("username", "admin");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        String jsonpObject = restTemplate.postForObject("http://localhost:8087/getUser", request,String.class);
        JSONObject jsonObject = JSON.parseObject(jsonpObject);
        System.out.println(jsonObject.get("username"));
        System.out.println(jsonObject.get("password"));
    }

    @RequestMapping(value="/clientGetServerUser1")
    public void clientGetServerUser1(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> map = new HashMap<>();
        map.put("username", "admin");
        HttpEntity<Map> entity = new HttpEntity<>(map, headers);
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity("http://localhost:8087/getUser1", entity, Map.class);
        System.out.println(responseEntity.getBody().get("username"));
        System.out.println(responseEntity.getStatusCodeValue());
    }

    @RequestMapping(value="/clientGetServerUser2")
    public void clientGetServerUser2(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> map = new HashMap<>();
        map.put("username", "admin");
        HttpEntity<Map> entity = new HttpEntity<>(map, headers);
        ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://localhost:8087/getUser1", entity, User.class);
        System.out.println(responseEntity.getBody().getUsername());
        System.out.println(responseEntity.getStatusCodeValue());
    }


    @RequestMapping(value="/clientGetServerUser3")
    public void clientGetServerUser3(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> map = new HashMap<>();
        map.put("username", "admin");
        HttpEntity<Map> entity = new HttpEntity<>(map, headers);
        User user = restTemplate.postForObject("http://localhost:8087/getUser1", entity, User.class);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
    }

    @RequestMapping(value="/clientGetServerUser4")
    public void clientGetServerUser4(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> map = new HashMap<>();
        map.put("username", "admin");
        HttpEntity<Map> entity = new HttpEntity<>(map, headers);
        ResponseEntity<User> exchange = restTemplate.exchange("http://localhost:8087/getUser1", HttpMethod.POST, entity, User.class);
        System.out.println(exchange.getBody().getUsername());
        System.out.println(exchange.getBody().getPassword());
    }


}
