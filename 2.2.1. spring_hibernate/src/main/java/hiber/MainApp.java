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

        User user1 = new User("User1", "LastUser1", "user1@mail.io");
        User user2 = new User("User2", "LastUser2", "user2@mail.io");
        User user3 = new User("User3", "LastUser3", "user3@mail.io");
        User user4 = new User("User4", "LastUser4", "user4@mail.io");


        Car car1 = new Car("BMW", 10);
        Car car2 = new Car("Ferrari", 11);
        Car car3 = new Car("Ford", 23);
        Car car4 = new Car("Lamborghini", 13);


        userService.add(user1.setCar(car1).setUser(user1));
        userService.add(user2.setCar(car2).setUser(user2));
        userService.add(user3.setCar(car3).setUser(user3));
        userService.add(user4.setCar(car4).setUser(user4));


        for (User user : userService.listUsers()) {
            System.out.println(user + " " + user.getCar());
        }
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }


        System.out.println(userService.getUserByCar("Ferrari", 11));
        System.out.println(userService.getUserByCar("Ford", 23));


        context.close();
    }
}

