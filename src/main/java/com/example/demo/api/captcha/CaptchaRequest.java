package com.example.demo.api.captcha;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaptchaRequest {

    private String captchaText;

    private String userInput;

}
