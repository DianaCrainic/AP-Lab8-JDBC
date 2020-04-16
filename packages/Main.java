import dao.*;
import db.Database;
import bonus.ThreadPoolExecutor;

import java.sql.*;

/**
 * Main class
 * Application that allows to connect to a relational database by using JDBC,
 * submit SQL statements and display the results.
 */
public class Main {

    private static Connection connection = Database.getInstance().getConnection();

    private static final int NumberOfRows = 50;
    private static final int numberOfTasks = 100;

    public static void main(String[] args) {
        try {
            connection.setAutoCommit(false);
            //createTables();
            insertData(NumberOfRows);
            ChartController chartController = new ChartController();
            chartController.generateTop();

            connection.commit();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    private static void createTables() {
        createArtistsTable();
        createAlbumsTable();
        createChartsTable();

    }

    /**
     * inserts generated data about artist, album and chart
     */
    private static void insertData(int rows) throws SQLException {
        ArtistController artistController = new ArtistController();
        AlbumController albumController = new AlbumController();
        ChartController chartController = new ChartController();
        artistController.insertGeneratedArtist(rows);
        albumController.insertGeneratedAlbum(rows);
        chartController.insertGeneratedChart(rows);
    }

    /**
     * creates the artists table
     */
    public static void createArtistsTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "create table artists(" +
                    "    id integer not null generated always as identity," +
                    "    name varchar(100) not null," +
                    "    country varchar(100)," +
                    "    primary key (id)" +
                    ");";
            statement.execute(query);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * creates the albums table
     */
    public static void createAlbumsTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "create table albums(" +
                    "    id integer not null generated always as identity," +
                    "    name varchar(100) not null," +
                    "    artist_id integer not null references artists on delete restrict," +
                    "    release_year integer," +
                    "    primary key (id)" +
                    ");";
            statement.execute(query);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * creates the charts table
     */
    public static void createChartsTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "create table chart (" +
                    "    id integer not null generated always as identity," +
                    "    album_id integer not null references albums on delete restrict," +
                    "    profit integer," +
                    "    primary key (id)" +
                    ");";
            statement.execute(query);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * bonus
     */
    public static void runThreadPool(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(numberOfTasks);
        threadPoolExecutor.start();
    }

}
