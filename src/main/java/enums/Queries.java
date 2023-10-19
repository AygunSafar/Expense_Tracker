package enums;

public enum Queries {

    INSERT_USER("INSERT INTO users(name,surname)" +
            "values(?,?)"),

    GET_ALL_USERS("SELECT * FROM users"),

    GET_USER_BY_ID("SELECT * FROM users WHERE ID=?"),
    UPDATE_USER_BY_ID("UPDATE users SET name=?,surname=? WHERE id=?"),
    DELETE_USER_BY_ID("DELETE FROM users WHERE ID=?"),
    CREATE_EXPENSE_CATEGORY("INSERT INTO expense_categories(name) values(?)"),
        GET_EXPENSE_CATEGORY_BY_ID("SELECT * FROM EXPENSE_CATEGORIES WHERE ID=?"),
    GET_ALL_EXPENSES("SELECT * FROM expenses"),
    UPDATE_EXPENSE_BY_ID("UPDATE expenses SET name=?, date=?, amount=?, category_id=?, user_id=?, description=? WHERE id=? "),
    GET_SUM_OF_ALL_EXPENSES("SELECT SUM(amount) AS total FROM expenses WHERE category_id=? AND date BETWEEN ? AND ?"),
    INSERT_EXPENCE("INSERT INTO expenses(name,date,amount,category_id,user_id,description) values(?,?,?,?,?,?)" );

    private String value;

    Queries(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

//CREATE database tracker_db;
//
//CREATE table expense_categories
//(
//    id   serial PRIMARY KEY,
//    name varchar(30)
//);
//
//CREATE table income_categories
//(
//    id   serial PRIMARY KEY,
//    name varchar(30)
//);
//
//create table users
//(
//    id      serial primary key,
//    name    varchar(30),
//    surname varchar(30)
//);
//
//create table expenses
//(
//    id          serial primary key,
//    name        varchar(30),
//    date        Date,
//    amount integer,
//    category_id integer references expense_categories(id),
//    user_id     integer references users(id),
//    description text
//);
//
//create table incomes
//(
//    id          serial primary key,
//    name        varchar(30),
//    date        Date,
//    amount integer,
//    category_id integer references income_categories(id),
//    user_id     integer references users(id),
//    description text
//);
//
//create table balance(
//    id serial primary key ,
//    last_updated Date,
//    amount integer,
//    user_id integer references users(id)
//
//);
