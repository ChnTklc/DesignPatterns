package iztechify.models;

import iztechify.models.music.Artist;
import iztechify.models.music.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Music extends Observable  {
    private List<Artist> artists;

    public Music(List<Artist> artists) {
        this.artists = new ArrayList<>();
        this.artists.addAll(artists);
    }

    public void addArtistToMusic(Artist artist){
        artists.add(artist);
        setChanged();
        notifyObservers();
    }

    public boolean removeArtistFromMusic(Artist artist){
        boolean isSuccessful = artists.remove(artist);
        setChanged();
        notifyObservers();
        return isSuccessful;
    }

    public List<Song> getSongs() {
        List<Song> songs = new ArrayList<>();
        for(Artist artist : artists){
            songs.addAll(artist.getSongs());
        }
        return songs;
    }

    public void read() { // TODO read from json file

    }

    public void write() { // TODO write changes to json file

    }
}
