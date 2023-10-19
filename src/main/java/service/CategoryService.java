package service;

import connection.DBconnection;
import enums.Queries;
import exception.CategoryNotFoundException;
import model.ExpenseCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryService {

    private Connection connection;
    private PreparedStatement preparedStatement;


    public void CreateExpenseCategory(ExpenseCategory expenseCategory){
        connection= DBconnection.getConnection();
        try {
            preparedStatement=connection.prepareStatement(Queries.CREATE_EXPENSE_CATEGORY.getValue());
            preparedStatement.setString(1,expenseCategory.getExpenseCatName());
            int affectedRows=preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Expense category created");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DBconnection.closeConnection(connection);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public ExpenseCategory getExpenceCategoryById(int id){
        connection=DBconnection.getConnection();
        try {
            preparedStatement=connection.prepareStatement(Queries.GET_EXPENSE_CATEGORY_BY_ID.getValue());
            preparedStatement.setInt(1,id);
            ResultSet result= preparedStatement.executeQuery();
            while (result.next()){
                String name=result.getString("name");
                int catId=result.getInt("id");
                return new ExpenseCategory(catId,name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBconnection.closeConnection(connection);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        throw new CategoryNotFoundException("Category is not found");
    }




}
