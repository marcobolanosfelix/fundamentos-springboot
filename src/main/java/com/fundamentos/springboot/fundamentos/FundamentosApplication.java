package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.*;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	//private MyNumbers myNumbers;
	//private MyNumbersWithDependency myNumbersWithDependency;
	//private MyOwnBeanWithDependency myOwnBeanWithDependency;
	@Autowired
	private MyBeanWithProperties myBeanWithProperties;
	@Autowired
	private UserPojo userPojo;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	public FundamentosApplication(
			@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
			MyBean myBean,
			MyBeanWithDependency myBeanWithDependency
			//MyNumbers myNumbers,
			//MyOwnBeanWithDependency myOwnBeanWithDependency,
			//MyNumbersWithDependency myNumbersWithDependency
			//MyBeanWithProperties myBeanWithProperties
	) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		//this.myBeanWithProperties = myBeanWithProperties;
		//this.myNumbers = myNumbers;
		//this.myOwnBeanWithDependency = myOwnBeanWithDependency;
		//this.myNumbersWithDependency = myNumbersWithDependency;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//clasesAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}

	private void saveWithErrorTransactional() {
		User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
		User test2 = new User("TestTransactional1", "TestTransactional2@domain.com", LocalDate.now());
		User test3 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
		User test4 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
		List<User> users = Arrays.asList(test1, test2, test3, test4);

		try {
			userService.saveTransactional(users);
		} catch (Exception e) {
			LOGGER.error("Este es una excepción dentro del metodo transaccional " + e.getMessage());
		}

		userService.getAllUsers().stream()
				.forEach( user -> LOGGER.info("Este es el usuario dentro del metodo transaccional: " + user) );
	}

	private void getInformationJpqlFromUser() {
		/*LOGGER.info("Usuario con el metodo findByUserEmail(): " +
				userRepository.findByUserEmail("marco@domain.com")
				.orElseThrow( () -> new RuntimeException("No se encontro el usuario") ));

		userRepository.findAndSort("Ma", Sort.by("id").ascending())
				.stream()
				.forEach(user -> LOGGER.info("Usuario con metodo sort: " + user));

		userRepository.showAllUsers()
				.stream()
				.forEach(user -> LOGGER.info("Usuario con el metodo showAllUsers(): " + user));

		userRepository.findByName("Marco")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con el metodo findByName(): " + user));

		LOGGER.info("Usuario con el metodo findEmailAndName(): " +
				userRepository.findByEmailAndName("daniela@domain.com", "Daniela")
				.orElseThrow( () -> new RuntimeException("Usuario no encontrado")));

		userRepository.findByNameLike("%Mar%")
				.stream()
				.forEach( user -> LOGGER.info("Usuario con el metodo findByNameLike(): " + user));

		userRepository.findByNameOrEmail("Luis", null)
				.stream()
				.forEach( user -> LOGGER.info("Usuario con el metodo findByNameOrEmail(): " + user));
*/
		userRepository.findByBirthDateBetween(
					LocalDate.of(2021, 06, 01),
					LocalDate.of(2021, 12, 31))
				.stream()
				.forEach( user -> LOGGER.info("Usuario con el intervalo de fecha del metodo findByBirthDateBetween(): " + user));

		userRepository.findByNameLikeOrderByIdDesc("%Mar%")
				.stream()
				.forEach( user -> LOGGER.info("Usuario con el metodo findByNameLikeOrderByIdDesc(): " + user) );

		LOGGER.info("El usuario a partir del named parameter es: " +
				userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021, 12, 8), "marco@domain.com")
				.orElseThrow( () -> new RuntimeException("Usuario no encontrado a partir del named parameter") ));

	}

	private void saveUsersInDataBase() {
		User user1 = new User("John", "john@domain.com", LocalDate.of(2021, 3, 13));
		User user2 = new User("Marco", "marco@domain.com", LocalDate.of(2021, 12, 8));
		User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 9, 8));
		User user4 = new User("Marisol", "marisol@domain.com", LocalDate.of(2021, 6, 18));
		User user5 = new User("Karen", "karen@domain.com", LocalDate.of(2021, 1, 1));
		User user6 = new User("Carlos", "carlos@domain.com", LocalDate.of(2021, 7, 7));
		User user7 = new User("Enrique", "enrique@domain.com", LocalDate.of(2021, 11, 12));
		User user8 = new User("Luis", "luis@domain.com", LocalDate.of(2021, 2, 27));
		User user9 = new User("Maria", "paola@domain.com", LocalDate.of(2021, 4, 10));
		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9);
		list.stream().forEach(userRepository::save);
	}

	private void clasesAnteriores() {
		System.out.println();
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		//myNumbers.printNumbers(10);
		//myNumbersWithDependency.printPares(10);
		//myOwnBeanWithDependency.displayElements();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + " - " + userPojo.getPassword());
		try {
			//error
			int value = 10/0;
			LOGGER.debug("Mi valor: " + value);
		} catch (Exception e) {
			LOGGER.error("Esto es un error al dividir por cero: " + e.getMessage());
		}
	}

}
