package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.Book;
import com.example.demo.domain.BookRepository;
import com.example.demo.domain.Category;
import com.example.demo.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository catRepository) {
	return (args) -> {
		
		Category c1 = new Category("Crafts");
		Category c2 = new Category("Environment");
		Category c3 = new Category("Novel");
		
		catRepository.save(c1);
		catRepository.save(c2);
		catRepository.save(c3);
		
		Book a = new Book("Islantilaisia neuleita", "Vesdis Jonsdottir", 2021, "000001", 24.95, catRepository.findByName("Crafts").get(0));
		Book b = new Book("Ilmastonmuutos ilmatieteilijän silmin", "Petteri Taalas", 2021, "000002", 32.95, catRepository.findByName("Environment").get(0));
		Book c = new Book("Kaikki elämästä(ni)", "Antti Holma", 2020, "000003", 24.95, catRepository.findByName("Novel").get(0));
		
		bookRepository.save(a);
		bookRepository.save(b);
		bookRepository.save(c);
	};
	}

}
