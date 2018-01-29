package com.twitter;

import com.twitter.entity.User;
import com.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwitterCloneApplication {
		public static void main(String[] args) {
			SpringApplication.run(TwitterCloneApplication.class, args);
		}
}

