package com.vitavehiculum.web.controller;

import com.vitavehiculum.web.WebApplication;
import com.vitavehiculum.web.orm.User;
import com.vitavehiculum.web.orm.UserRepository;
import com.vitavehiculum.web.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/form")
public class FormController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CryptoService cryptoService;

    @Autowired
    WebController webController;

    private static Long ID = 0L;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body){

        if(body.containsKey("secret")){

            User user = userRepository.findBySecret(cryptoService.hash(body.get("secret").getBytes(StandardCharsets.UTF_8)));

            if(user != null) {

                if(user.getRole().equals("admin")){
                    //Admin Cookie
                    return ResponseEntity.ok("Test");
                }
                else{
                    //User Cookie
                }

                System.out.println(user.getName());
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/");
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);

    }

    @PostMapping("/contact")
    public ResponseEntity<String> contact(@RequestPart("file") MultipartFile file, @RequestPart("name") String name, @RequestPart("email") String email, @RequestPart("phone") String phone){

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("10.0.114.73");
        mailSender.setPort(25);

        mailSender.setUsername("web@VitaVehiculum.local");
        mailSender.setPassword("password");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", 25);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("web@VitaVehiculum.local");
        message.setTo("web@VitaVehiculum.local");
        message.setSubject("Ligma");
        message.setText("Cheeks");

        mailSender.send(message);

        File newFile = new File("files/" + file.getOriginalFilename());

        try {
            try (OutputStream os = Files.newOutputStream(newFile.toPath())) {
                os.write(file.getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/");
        return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
    }
}
