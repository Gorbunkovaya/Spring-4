package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Audi Q7", 2021);
        userService.add(car1);
        Car car2 = new Car("Toyota Corolla", 150);
        userService.add(car2);
        Car car3 = new Car("Audi A7", 3);
        userService.add(car3);
        Car car4 = new Car("Porsche Cayenne", 992);
        userService.add(car4);

        userService.add(new User("Андрей", "Андреев", "aa@mail.ru", car1));
        userService.add(new User("Николай", "Николаев", "nn@mail.ru", car2));
        userService.add(new User("Елена", "Еленина", "ee@mail.ru", car3));
        userService.add(new User("Катерина", "Катина", "kk@mail.ru", car4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
        }

        List<User> userByCar = userService.getUserByCar("Audi A7", 3);
        for (User usersCar : userByCar) {
            System.out.println(usersCar);
        }

        context.close();
    }
}
