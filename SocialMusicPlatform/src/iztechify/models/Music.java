package iztechify.models;

import iztechify.models.music.Album;
import iztechify.models.music.Artist;
import iztechify.models.music.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Music extends Observable {
    private List<Artist> artists;

    public Music(List<Artist> artists) {
        this.artists = new ArrayList<>();
        this.artists.addAll(artists);
    }

    public void remove(String artist) {
        Artist a = null;
        for(Artist art : artists) {
            if(art.getName().equals(artist)) {
                a = art;
                break;
            }
        }
        artists.remove(a);
        setChanged();
        notifyObservers();
    }

    public void remove(String artist, String album) {
        for(Artist a : artists) {
            if(a.getName().equals(artist)) {
                a.removeAlbum(album);
                setChanged();
                notifyObservers();
                return;
            }
        }
    }

    public void remove(String artist, String album, String song) {
        for(Artist a : artists) {
            if(a.getName().equals(artist)) {
                for(Album al : a.getAlbums()) {
                    if(al.getTitle().equals(album)) {
                        al.removeSong(song);
                        setChanged();
                        notifyObservers();
                        return;
                    }
                }
            }
        }
    }

    public void addArtist(String artist) {
        artists.add(new Artist(artist));
        setChanged();
        notifyObservers();
    }

    public void addAlbum(String artist, String album, String description) {
        Artist a = getArtist(artist);
        if(a == null)
            return;

        a.addAlbum(new Album(a, album, description));
        setChanged();
        notifyObservers();
    }

    public void addSong(String artist, String album, String title, String length) {
        Artist a = getArtist(artist);
        if(a == null)
            return;
        Album aa = a.getAlbum(album);
        if(aa == null)
            return;
        aa.addSong(new Song(aa, title, length));
        setChanged();
        notifyObservers();
    }

    public void editArtist(String artist, String newName) {
        getArtist(artist).setName(newName);
        setChanged();
        notifyObservers();
    }

    public void editAlbum(String artist, String album, String newName, String newDesc) {
        getArtist(artist).getAlbum(album).setTitle(newName);
        getArtist(artist).getAlbum(newName).setDescription(newDesc);
        setChanged();
        notifyObservers();
    }

    public void editSong(String artist, String album, String song, String newName, String newLength) {
        getArtist(artist).getAlbum(album).getSong(song).setTitle(newName);
        getArtist(artist).getAlbum(album).getSong(newName).setLength(newLength);
        setChanged();
        notifyObservers();
    }

    public Artist getArtist(String name) {
        for(Artist artist : artists) {
            if(artist.getName().equals(name))
                return artist;
        }
        return null;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public List<Album> getAlbums(String artistName) {
        for(Artist artist : artists) {
            if(artistName.equals(artist.getName()))
                return artist.getAlbums();
        }
        return null;
    }

    public List<Song> getSongs() {
        List<Song> songs = new ArrayList<>();
        for(Artist artist : artists) {
            songs.addAll(artist.getSongs());
        }
        return songs;
    }

    public List<Song> getSongs(String artist, String album) {
        for(Artist a : artists)
            if(a.getName().equals(artist))
                return a.getSongs(album);
        return null;
    }
}
