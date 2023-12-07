package com.CRM.crm.User;

import com.CRM.crm.Email.EmailService;
import com.CRM.crm.Exception.InvalidTokenException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
     UserRepositoryI userRepositoryI;

    @Transactional(rollbackOn = MailException.class)
    void userSave(User user){
        try{
            user.setActivationToken(UUID.randomUUID().toString());
            userRepositoryI.saveAndFlush(user);
           // emailService.sendActivationEmail(user.getUserEmail(),user.getActivationToken());

        }catch (DataIntegrityViolationException exception){
            throw new RuntimeException("User cant created");
        }
    }

    public void activeUser(String token) {
        User inDB=userRepositoryI.findByActivationToken(token);
        if (inDB==null){
        throw new InvalidTokenException();
        }
        inDB.setActive(true);
        inDB.setActivationToken(null);
        userRepositoryI.save(inDB);
    }

    public Page<User> getUsers(Pageable page) {
    return userRepositoryI.findAll(page);
    }
}
