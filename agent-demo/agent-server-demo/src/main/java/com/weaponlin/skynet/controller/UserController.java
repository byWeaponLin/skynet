package com.weaponlin.skynet.controller;

import com.google.common.collect.Lists;
import com.weaponlin.skynet.repository.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    @RequestMapping("/list")
    public ResponseEntity<?> getUsers(HttpServletRequest request) {
        log.info("receive request, traceId: {}", request.getHeader("traceId"));
        ArrayList<User> users = Lists.newArrayList(
                new User().setId(1L).setAge(20).setGender("F").setName("xxx").setDel(0)
        );
        return new ResponseEntity<Object>(users, HttpStatus.ACCEPTED);
    }

}
