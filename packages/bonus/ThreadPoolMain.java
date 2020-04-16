package bonus;

import dao.*;

import java.sql.SQLException;

/**
 * bonus part
 * ThreadPoolMain class:
 * -inserts data
 */
public class ThreadPoolMain implements Runnable {
    private final int numberOfRows = 50;

    @Override
    public void run() {
        try {
            insertData(numberOfRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertData(int rows) throws SQLException {
        ArtistController artistController = new ArtistController();
        AlbumController albumController = new AlbumController();
        ChartController chartController = new ChartController();
        artistController.insertGeneratedArtist(rows);
        albumController.insertGeneratedAlbum(rows);
        chartController.insertGeneratedChart(rows);
    }
}
