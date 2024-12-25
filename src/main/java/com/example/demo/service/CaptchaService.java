package com.example.demo.service;

import com.example.demo.api.captcha.CaptchaRequest;
import com.example.demo.api.captcha.CaptchaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.CacheRequest;
import java.util.Base64;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CaptchaService {


    private static final int WIDTH = 200;
    private static final int HEIGHT = 50;
    private static final int LENGTH = 6;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public CaptchaResponse generateCaptcha() {
        String captchaText = generateRandomText();
        String captchaImage = generateCaptchaImage(captchaText);
        return new CaptchaResponse(captchaText, captchaImage);
    }

    public boolean verifyCaptcha(CaptchaRequest request) {

        String captchaText = request.getCaptchaText();
        String userInput = request.getUserInput();
        return captchaText.equalsIgnoreCase(userInput);

    }

    private String generateRandomText() {
        StringBuilder text = new StringBuilder(LENGTH);
        Random random = new Random();
        for (int i = 0; i < LENGTH; i++) {
            text.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return text.toString();
    }

    private String generateCaptchaImage(String captchaText) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial", Font.BOLD, 30));
        graphics.drawString(captchaText, 50, 35);
        graphics.dispose();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate CAPTCHA image", e);
        }
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }

}
