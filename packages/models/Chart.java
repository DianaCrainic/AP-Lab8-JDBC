package models;

/**
 * Chart class
 * -information about a chart
 */
public class Chart {
    private int id;
    private int albumId;
    private int profit;

    public Chart(int id, int albumId, int profit) {
        this.id = id;
        this.albumId = albumId;
        this.profit = profit;
    }

    public int getId() {
        return id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getProfit() {
        return profit;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "id=" + id +
                ", albumId=" + albumId +
                ", profit=" + profit +
                '}';
    }
}
