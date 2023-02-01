package com.accenture.ra.rql.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.ra.rql.entity.User;
import com.accenture.ra.rql.repository.UserRepository;
import com.accenture.ra.rql.specification.UserSpecificationsBuilder;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/users")
@Slf4j
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping
	public ResponseEntity<List<User>> getAll(){
		return ResponseEntity.ok(
			this.userRepo.findAll()
		);
	}
	
	@GetMapping(path = "/search")
	public ResponseEntity<List<User>> seachUserBy(
			@RequestParam(value = "q", required = false) String search ) {
		log.info("{}", search);
		
        UserSpecificationsBuilder builder = new UserSpecificationsBuilder();
        Matcher matcher = parseSearchQueryString(search);
        while ( matcher.find() ) {
            builder.with(
            	matcher.group(1),
            	matcher.group(2), 
            	matcher.group(3)
            );
        }
        
        Specification<User> spec = builder.build();
		
		return ResponseEntity.ok(
			this.userRepo.findAll(spec)
		);
	}

	private Matcher parseSearchQueryString(String search) {
		Pattern pattern = Pattern.compile("""
			(\\w+?)(_LIKE_|_EQ_|_NEQ_|_GT_|_GTE_|_LT_|_LTE_)(\\w+?),
		""");
		Matcher matcher = pattern.matcher(search + ",");
		return matcher;
	}
}