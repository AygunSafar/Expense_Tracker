package service;

import connection.DBconnection;
import enums.Queries;
import model.Expense;
import model.ExpenseCategory;
import model.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ExpenseService {


  private  Connection connection;
  private PreparedStatement preparedStatement;

  public void createExpense(Expense expense){

      connection= DBconnection.getConnection();

      try {
          preparedStatement=connection.prepareStatement(Queries.INSERT_EXPENCE.getValue());

         preparedStatement.setString(1,expense.getName());
         preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
         preparedStatement.setDouble(3,expense.getAmount());
         preparedStatement.setInt(4,expense.getCategory().getId());
         preparedStatement.setInt(5,expense.getUser().getId());
         preparedStatement.setString(6,expense.getDescription());
         int affectedRows=preparedStatement.executeUpdate();
         if(affectedRows>0){

             //Whenever adding expense balance will automatically change for given amount

             int expenseAmount =expense.getAmount();
             int userId=expense.getUser().getId();
             String updateBalanceQuery=("UPDATE BALANCE SET AMOUNT= AMOUNT-? WHERE USER_ID=?");
             preparedStatement=connection.prepareStatement(updateBalanceQuery);
             preparedStatement.setInt(1, expenseAmount);
             preparedStatement.setInt(2,userId);
             preparedStatement.executeUpdate();

             int updatedRows = preparedStatement.executeUpdate();

             if (updatedRows > 0) {
                 System.out.println("Expense added and balance updated.");
             } else {
                 System.out.println("Expense added but failed to update balance.");
             }

         } else {
             System.out.println("Failed to add expense.");
         }


      } catch (SQLException e) {
          throw new RuntimeException(e);
      }finally {
          DBconnection.closeConnection(connection);
          if(preparedStatement!=null){
              try {
                  preparedStatement.close();
              } catch (SQLException e) {
                  throw new RuntimeException(e);
              }
          }
      }

  }


  //This method calculate sum of expenses based on category and given time period
    public int calculateTotalExpenses(Integer categoryId, LocalDate startDate, LocalDate endDate){

      int totalAmount=0;
      try(Connection connection=DBconnection.getConnection()) {

          preparedStatement=connection.prepareStatement(Queries.GET_SUM_OF_ALL_EXPENSES.getValue());
          preparedStatement.setInt(1,categoryId);
          preparedStatement.setDate(2,Date.valueOf(startDate));
          preparedStatement.setDate(3,Date.valueOf(endDate));

          ResultSet resultSet=preparedStatement.executeQuery();

          while(resultSet.next()){
              totalAmount=resultSet.getInt("total");
          }

      } catch (SQLException e) {
          throw new RuntimeException(e);
      }finally {
          if(preparedStatement!=null){
              try {
                  preparedStatement.close();
              } catch (SQLException e) {
                  throw new RuntimeException(e);
              }
          }
      }

      return totalAmount;
    }

  public List<Expense> getAllExpenses(){
      List<Expense> expenseList=new ArrayList<>();
      CategoryService categoryService=new CategoryService();
      UserService userService=new UserService();

      connection=DBconnection.getConnection();
      try {
          preparedStatement=connection.prepareStatement(Queries.GET_ALL_EXPENSES.getValue());
          ResultSet results=preparedStatement.executeQuery();
          while(results.next()){
              int id=results.getInt("id");
              String name= results.getString("name");
              Integer amount=results.getInt("amount");
              LocalDateTime time=results.getTimestamp("date").toLocalDateTime();
              String decription=results.getString("description");
              int categoryId=results.getInt("category_id");
              ExpenseCategory expenseCategory=categoryService.getExpenceCategoryById(categoryId);
              int userId=results.getInt("user_id");
              User user= userService.getUserById(userId);

              Expense expense=new Expense(id,name,amount,time,decription,expenseCategory,user);
              expenseList.add(expense);
          }
          results.close();
          return expenseList;

      } catch (SQLException e) {
          throw new RuntimeException(e);
      }finally {
          DBconnection.closeConnection(connection);
          if(preparedStatement!=null){
              try {
                  preparedStatement.close();
              } catch (SQLException e) {
                  throw new RuntimeException(e);
              }
          }
      }
  }
}
