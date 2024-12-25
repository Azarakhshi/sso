package com.example.demo.controller;


import com.example.demo.api.captcha.CaptchaRequest;
import com.example.demo.api.captcha.CaptchaResponse;
import com.example.demo.service.CaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final CaptchaService captchaService;


    @GetMapping("/captcha/generate")
    public CaptchaResponse generateCaptcha(){
        return captchaService.generateCaptcha();
    }


    @PostMapping("/captcha/verify")
    public Boolean verifyCaptcha(@RequestBody CaptchaRequest request){
        return captchaService.verifyCaptcha(request);
    }




}
