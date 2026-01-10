package com.designpattern.webmotosystem.Services;

import com.designpattern.webmotosystem.Entities.Validation;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class NotificationService {
    JavaMailSender javaMailSender;
    public void notifierValidation(Validation validation){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("wandjikamdemgeslainidriss@gmail.com");
        mailMessage.setTo(validation.getUtilisateur().getEmail());
        mailMessage.setSubject("Votre code d'activation");

        String text=String.format("Bonjour %s,<br/> Votre code d'activation est %s; A bientot",
                validation.getUtilisateur().getNom(),
                validation.getCode());
        mailMessage.setText(text);
        javaMailSender.send(mailMessage); // envoie du mail
    }
}
