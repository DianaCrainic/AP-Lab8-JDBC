import dao.*;
import db.Database;

import java.sql.*;

public class Main {

    private static Connection connection = Database.getInstance().getConnection();;

    private static final int ROWS = 50;

    public static void main(String[] args) {
        try {
            connection.setAutoCommit(false);
            //createArtistsTable();
            //createAlbumsTable();
            //createChartsTable();
            insertRandomData(ROWS);
            ChartController chartController = new ChartController();
            chartController.showTop();

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

    private static void insertRandomData(int rows) throws SQLException {
        ArtistController artistController = new ArtistController();
        AlbumController albumController = new AlbumController();
        ChartController chartController = new ChartController();
        artistController.insertRandomArtist(rows);
        albumController.insertRandomAlbum(rows);
        chartController.insertRandomChart(rows);
    }

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
            System.out.println("create table artists exception");
        }
    }

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
            System.out.println("create table albums exception");
        }
    }

    public static void createChartsTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "create table chart (" +
                    "    id integer not null generated always as identity," +
                    "    album_id integer not null references albums on delete restrict," +
                    "    sales bigint," +
                    "    primary key (id)" +
                    ");";
            statement.execute(query);
        } catch (SQLException exception) {
            System.out.println("create table chart exception");
        }
    }

}
