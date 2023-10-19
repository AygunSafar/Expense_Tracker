package service;

import connection.DBconnection;
import enums.Queries;
import exception.UserNotFoundException;
import model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private Connection connection;
    private PreparedStatement preparedStatement;

    //Add, listAll, delete,

    //listAll-- databseden cixarib butun userleri listeyigib listi geri qyatarmaliyam

    public void addUser(User user) {
        connection = DBconnection.getConnection();
        try {
            preparedStatement = connection.prepareStatement(Queries.INSERT_USER.getValue(), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                while (generatedKeys.next()) {
                    int userId = generatedKeys.getInt("id");
                    String createBalanceForThatUser = ("INSERT INTO balance(last_updated,amount,user_id) VALUES(?,?,?) ");
                    preparedStatement = connection.prepareStatement(createBalanceForThatUser);
                    preparedStatement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                    preparedStatement.setInt(2, 0);
                    preparedStatement.setInt(3, userId);
                    preparedStatement.executeUpdate();
                }

                System.out.println("A new user inserted successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            DBconnection.closeConnection(connection);
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        connection = DBconnection.getConnection();

        try {
            preparedStatement = connection.prepareStatement(Queries.GET_ALL_USERS.getValue());

            preparedStatement.executeQuery();
            ResultSet result = preparedStatement.getResultSet();

            while (result.next()) {
                Integer id = result.getInt("id");
                String name = result.getString("name");
                String surname = result.getString("surname");

                User user = new User(id, name, surname);
                userList.add(user);
            }
            result.close();
            return userList;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBconnection.closeConnection(connection);

            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public User getUserById(Integer userId) {

        connection = DBconnection.getConnection();
        try {
            preparedStatement = connection.prepareStatement(Queries.GET_USER_BY_ID.getValue());
            preparedStatement.setInt(1, userId);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Integer id = result.getInt("id");
                String name = result.getString("name");
                String surname = result.getString("surname");

                User user = new User(id, name, surname);
                return user;
            }
            result.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBconnection.closeConnection(connection);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        throw new UserNotFoundException("Student with this id is not found");
    }

    public void updateUSerById(Integer id, String name, String surname) {
        try (Connection connection = DBconnection.getConnection()) {

            if (getUserById(id) != null) {
                try {
                    preparedStatement = connection.prepareStatement(Queries.UPDATE_USER_BY_ID.getValue());
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, surname);
                    preparedStatement.setInt(3, id);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("User information updated successfully");
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    DBconnection.closeConnection(connection);
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                System.out.println("User is not exist");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void deleteUserById(Integer id) {

        connection = DBconnection.getConnection();
        try {
            preparedStatement = connection.prepareStatement(Queries.DELETE_USER_BY_ID.getValue());
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("User deleted successfully!");
            } else {
                throw new UserNotFoundException("User not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBconnection.closeConnection(connection);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void insertListUsers(List<User> users) {
        connection = DBconnection.getConnection();
        try {
            preparedStatement = connection.prepareStatement(Queries.INSERT_USER.getValue());
            for (User user : users) {

                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getSurname());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            System.out.println("User list added successfully!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBconnection.closeConnection(connection);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
