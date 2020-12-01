package com.careydevelopment.ecosystem.user.controller;

import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.careydevelopment.ecosystem.user.util.FileUtil;
import com.careydevelopment.ecosystem.user.util.SecurityUtil;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
    
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    
    @Autowired
    private SecurityUtil securityUtil;
    
    @Autowired
    private FileUtil fileUtil;
    
    
    @GetMapping("/user/{userId}/profileImage")
    public ResponseEntity<?> getProfileImage(@PathVariable String userId) {        
        try {
            Path imagePath = fileUtil.fetchProfilePhotoByUserId(userId);
            
            if (imagePath != null) {
                LOG.debug("Getting image from " + imagePath.toString());
                
                ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(imagePath));
                
                return ResponseEntity
                        .ok()
                        .contentLength(imagePath.toFile().length())
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);                    
            } else {
                LOG.debug("Profile photo not found for user " + userId);
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
