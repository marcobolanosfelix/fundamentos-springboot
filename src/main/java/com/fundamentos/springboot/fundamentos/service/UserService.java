package com.fundamentos.springboot.fundamentos.service;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    //Atributos
    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    //Constructores
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Comportamientos
    @Transactional
    public void saveTransactional(List<User> users) {
        users.stream()
                .peek( user -> LOG.info("Usuario insertado: " + user) )
                .forEach( userRepository::save );
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }
}
