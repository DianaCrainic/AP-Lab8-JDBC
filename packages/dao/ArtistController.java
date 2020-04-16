package dao;

import com.github.javafaker.Faker;
import models.Artist;

import java.sql.*;
import java.util.*;

/**
 * ArtistController class:
 * -insert data about an artist
 * -get data from DB about an artist
 * -findByName - returns a list of artists
 */
public class ArtistController extends Controller {

    public ArtistController() {
    }

    public void create(String name, String country) throws SQLException {
        connection = getConnection();
        String sql = "INSERT INTO artists (name, country) VALUES(?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, country);
        preparedStatement.execute();
    }

    public void insertGeneratedArtist(int rows) throws SQLException {
        Faker faker = new Faker();
        int i = 0;
        while (i < rows) {
            String fakeName = faker.name().fullName();
            String fakeCountry = faker.country().name();
            create(fakeName, fakeCountry);
            i++;
        }
    }

    public List<Artist> findByName(String name) throws SQLException {
        List<Artist> artists = new ArrayList<>();
        connection = getConnection();
        String sql = "SELECT * FROM artists WHERE name = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int artistId = resultSet.getInt("id");
            String artistCountry = resultSet.getString("country");
            Artist artist = new Artist(artistId, name, artistCountry);
            artists.add(artist);
        }
        return artists;
    }


}
