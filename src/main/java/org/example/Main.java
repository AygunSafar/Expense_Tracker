package org.example;

import connection.DBconnection;
import model.User;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        UserService userService= new UserService();
        // userService.addUser(new User(12,"Aygun","Safarova"));
      //  userService.addUser(new User(12,"Selma","Safarova"));

     //  userService.addUser(new User(12,"Samir","Memmedli"));

       User user1=new User(1,"Ehmer","Ehmedov");
       User user2=new User(1,"Esmer","Esmerov");
       User user3=new User(1,"Tahir","TAhirov");
       User user4=new User(1,"Esed","Esedov");


       List<User> userList= new ArrayList<>();

       userList.add(user1);
       userList.add(user2);
       userList.add(user3);
       userList.add(user4);


        userService.insertListUsers(userList);


    }
}