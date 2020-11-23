package com.careydevelopment.ecosystem.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.careydevelopment.ecosystem.exception.file.FileTooLargeException;
import com.careydevelopment.ecosystem.exception.file.MissingFileException;
import com.careydevelopment.ecosystem.user.model.User;
import com.careydevelopment.ecosystem.user.util.FileUtil;

@CrossOrigin
@RestController
public class FileUploaderController {
	
	private static final Logger LOG = LoggerFactory.getLogger(FileUploaderController.class);

	@Value("${max.file.upload.size}")
	private Long maxFileUploadSize;
	
	@Autowired
	private FileUtil fileUtil;

	
	@PostMapping("/user/saveProfileImage")
	public ResponseEntity<?> saveProfileImage(@RequestParam("file") MultipartFile file) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		LOG.debug("User uploading is " + user);
		
		try {
			fileUtil.checkFileExistence(file);
			
			fileUtil.checkFileSize(file, maxFileUploadSize);

			fileUtil.copyFile(file, user);
			
            return ResponseEntity.ok().build();
		} catch (FileTooLargeException fe) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		} catch (MissingFileException me) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
}
