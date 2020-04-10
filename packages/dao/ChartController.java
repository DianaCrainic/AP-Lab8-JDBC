package dao;

import java.sql.*;
import java.util.*;


/**
 * ChartController class:
 * -insert data about an artist
 * -get data from DB about an artist
 * -generate a top
 */
public class ChartController extends Controller {
    public void create(int albumId, int numberOfAlbums) throws SQLException {
        connection = getConnection();
        String sql = "INSERT INTO chart (album_id, profit) VALUES(?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, albumId);
        preparedStatement.setInt(2, numberOfAlbums);
        preparedStatement.execute();
    }

    public void insertGeneratedChart(int rows) throws SQLException {
        connection = getConnection();
        Statement statement = connection.createStatement();
        AlbumController albumController = new AlbumController();
        Random random = new Random();

        int i = 0;
        while (i < rows) {
            int randomChartId = -1;
            String sql = "SELECT id FROM albums ORDER BY random() limit 1;";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                randomChartId = resultSet.getInt("id");
            }
            int randomNumberOfAlbums = random.nextInt(20);
            create(randomChartId, randomNumberOfAlbums);
            i++;
        }
    }

    public void generateTop() throws SQLException {
        List<Map<String, Object>> top = new ArrayList<>();
        connection = getConnection();
        Statement statement = connection.createStatement();
        String query = "SELECT artists.name, SUM(profit) AS total " +
                "FROM artists JOIN albums ON artists.id = albums.artist_id " +
                "JOIN chart ON albums.id = chart.album_id " +
                "GROUP BY artists.name " +
                "ORDER BY total DESC;";
        ResultSet resultSet = statement.executeQuery(query);
        int i = 0;
        while (resultSet.next()) {
            top.add(new HashMap<>());
            top.get(i).put("position", i + 1);
            String artistName = resultSet.getString("name");
            top.get(i).put("name", artistName);
            int totalProfit = resultSet.getInt("total");
            top.get(i).put("total_profit", totalProfit);
            i++;
        }
        for (Map<String, Object> stringObjectMap : top) {
            System.out.println((int) stringObjectMap.get("position") + ". " + (String) stringObjectMap.get("name") +
                    " - profit: " + (int) stringObjectMap.get("total_profit"));
        }
    }
}
