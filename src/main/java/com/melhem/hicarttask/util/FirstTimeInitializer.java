package com.melhem.hicarttask.util;





import com.melhem.hicarttask.security.AppUser;
import com.melhem.hicarttask.security.UserRepository;
import com.melhem.hicarttask.security.UserService;
import com.sun.istack.NotNull;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class FirstTimeInitializer implements CommandLineRunner {

    private final Log logger = LogFactory.getLog(FirstTimeInitializer.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Override
    public void run(String... strings) throws Exception {

        if (userRepository.findAll().isEmpty()) {
            logger.info("No user  found");
AppUser user = new AppUser("melhemshoker@gmail.com", "123456", "melhem shoker","male","baalback",71722760 );
            userService.save(user);

        }


    }
}