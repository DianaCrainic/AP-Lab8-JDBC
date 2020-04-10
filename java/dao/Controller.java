package dao;

import db.Database;

import java.sql.Connection;

public class Controller {
    Connection connection = null;

    Connection getConnection(){
        if(connection == null){
            connection = Database.getInstance().getConnection();
        }
        return connection;
    }
}
