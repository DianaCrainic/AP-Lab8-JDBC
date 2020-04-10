package models;

/**
 * Album class
 * -information about an album
 */
public class Album {

    private int id;
    private String name;
    private int artistId;
    private int releaseYear;

    Artist artist = null;

    public Album(int id, String name, int artistId, int releaseYear) {
        this.id = id;
        this.name = name;
        this.artistId = artistId;
        this.releaseYear = releaseYear;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getArtistId() {
        return artistId;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    @Override
    public String toString() {
        if (artist != null) {
            return name + " - " + artist.getName() + " (" + releaseYear + ")";
        }
        return name + " - " + artistId + " (" + releaseYear + ")";
    }

}
