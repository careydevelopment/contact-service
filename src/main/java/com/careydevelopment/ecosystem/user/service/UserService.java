package com.careydevelopment.ecosystem.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careydevelopment.ecosystem.user.model.User;
import com.careydevelopment.ecosystem.user.repository.UserRepository;
import com.careydevelopment.ecosystem.user.util.SecurityUtil;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private SecurityUtil securityUtil;
    
    
    public User updateUser(User user) {
        addExcludedFields(user);
        User updatedUser = userRepository.save(user);
        
        return updatedUser;
    }
    
    
    private void addExcludedFields(User user) {
        User currentUser = securityUtil.getCurrentUser();
        
        user.setPassword(currentUser.getPassword());
    }
}
