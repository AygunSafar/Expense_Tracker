package enums;

public enum Queries {

    INSERT_USER("INSERT INTO users(name,surname)" +
            "values(?,?)"),

    GET_ALL_USERS("SELECT * FROM users"),

    GET_USER_BY_ID("SELECT * FROM users WHERE ID=?"),
    DELETE_USER_BY_ID("DELETE FROM users WHERE ID=?");

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
