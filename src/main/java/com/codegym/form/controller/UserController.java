package com.codegym.form.controller;

import com.codegym.form.model.User;
import com.codegym.form.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/create-user")
    public ModelAndView showCreateUser(){
        ModelAndView modelAndView = new ModelAndView("/user/create");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/create-user")
    public ModelAndView checkUser(@Validated @ModelAttribute("user") User user, BindingResult bindingResult){
        new User().validate(user, bindingResult);
        ModelAndView modelAndView = new ModelAndView("/user/create");

        if(!bindingResult.hasFieldErrors()){
            userService.save(user);
            modelAndView.addObject("user", new User());
            modelAndView.addObject("message", "Congratulations! You are old enough to sign up for this site");
            return modelAndView;
        }
        return modelAndView;
    }
}
