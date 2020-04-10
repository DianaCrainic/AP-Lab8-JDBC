package models;

public class Chart {
    private int id;
    private int albumId;
    private int sales;

    public Chart(int id, int albumId, int sales) {
        this.id = id;
        this.albumId = albumId;
        this.sales = sales;
    }

    public int getId() {
        return id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getSales() {
        return sales;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "id=" + id +
                ", albumId=" + albumId +
                ", sales=" + sales +
                '}';
    }
}
