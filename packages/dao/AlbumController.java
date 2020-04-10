package dao;

import com.github.javafaker.Faker;
import models.Album;

import java.util.*;
import java.sql.*;

/**
 * AlbumController class
 * -insert data about an album
 * -get data from DB about an album
 * -findByArtist - returns a list of albums
 */
public class AlbumController extends Controller {

    public AlbumController() {
    }

    public void create(String name, int artistId, int releaseYear) throws SQLException {
        connection = getConnection();
        String sql = "INSERT INTO albums (name, artist_id, release_year) VALUES(?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, artistId);
        preparedStatement.setInt(3, releaseYear);
        preparedStatement.execute();
    }

    public void insertGeneratedAlbum(int rows) throws SQLException {
        connection = getConnection();
        Statement statement = connection.createStatement();
        ArtistController artistController = new ArtistController();
        Faker faker = new Faker();
        Random random = new Random();
        int i = 0;
        while (i < rows) {
            String fakeName = faker.music().genre();
            int randomArtistId = -1;
            String sql = "SELECT id FROM artists ORDER BY random() limit 1;";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                randomArtistId = resultSet.getInt("id");
            }
            int randomYear = random.nextInt(50) + 1970;
            create(fakeName, randomArtistId, randomYear);
            i++;
        }
    }

    public List<Album> findByArtist(int artistId) throws SQLException {
        List<Album> albums = new ArrayList<>();
        connection = getConnection();
        String sql = "SELECT * FROM albums WHERE artistId = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, artistId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int albumId = resultSet.getInt("id");
            String albumName = resultSet.getString("name");
            int albumReleaseYear = resultSet.getInt("release_year");
            Album album = new Album(albumId, albumName, artistId, albumReleaseYear);
            albums.add(album);
        }
        return albums;
    }


}
