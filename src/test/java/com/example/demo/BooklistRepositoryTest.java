package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.domain.Book;
import com.example.demo.domain.BookRepository;
import com.example.demo.domain.Category;
import com.example.demo.domain.CategoryRepository;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BooklistRepositoryTest {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository catrepository;
	
	@Autowired
	private UserRepository userRepository;

	@Test
	public void findByTitle() {
		List<Book> books =bookRepository.findByTitle("Kaikki elämästä(ni)");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Antti Holma");
	}
	
	@Test
	public void createNewBook() {
		Book book = new Book("Soturikissat", "Erin Hunter", 2008, "000004", 12.00, new Category("Other"));
		bookRepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void findByName() {
		List<Category> category = catrepository.findByName("Novel");
		
		assertThat(category).hasSize(1);
	}
	
	@Test
	public void deleteBook() {
		List<Book> book = bookRepository.findByTitle("Kaikki elämästä(ni)");
		bookRepository.deleteAll(book);
		assertThat(book.isEmpty());
	}
	
	@Test
	public void createNewCategory() {
		Category category = new Category("Other");
		catrepository.save(category);
		assertThat(category.getCategoryid()).isNotNull();
	}
	
	@Test
	public void deleteCategory() {
		List<Category> category = catrepository.findByName("Novel");
		catrepository.deleteAll(category);
		assertThat(category.isEmpty());
	}
	
	@Test
	public void findByUsername() {
		User user = userRepository.findByUsername("user");
		assertThat(user.getEmail()).isEqualTo("user@user.fi");
	}
	
	@Test
	public void createNewUser() {
		User user = new User("user2", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user2@user.fi", "USER");
		userRepository.save(user);
		assertThat(user.getId()).isNotNull();
	}

	@Test
	public void deleteUser() {
		User user = userRepository.findByUsername("user");
		userRepository.deleteById(user.getId());
		assertThat(userRepository.findByUsername("user")).isNull();
	}
}
