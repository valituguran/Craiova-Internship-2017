package com.twitter;

import com.twitter.entity.User;
import com.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwitterCloneApplication implements CommandLineRunner {

		@Autowired
		private UserRepository repository;

		public static void main(String[] args) {
			SpringApplication.run(TwitterCloneApplication.class, args);
		}

		@Override
		public void run(String... args) throws Exception {


			// save a couple of customers
			//repository.save(new User("Alice", "Smith"));
			//repository.save(new User("Bob", "Smith"));

			// fetch all customers
			System.out.println("users found with findAll():");
			System.out.println("-------------------------------");
			for (User user : repository.findAll()) {
				System.out.println(user.getFirstname() + " " + user.getLastname());
			}
			System.out.println();

			// fetch an individual customer
			System.out.println("user found with findByFirstname('user'):");
			System.out.println("--------------------------------");
			System.out.println(repository.findByFirstName("aaa").getFirstname());

			System.out.println("user found with findByLastname('user'):");
			System.out.println("--------------------------------");
			for (User user : repository.findByLastName("aaa")) {
				System.out.println(user.getFirstname() + " " + user.getLastname());
			}

		}

	}
