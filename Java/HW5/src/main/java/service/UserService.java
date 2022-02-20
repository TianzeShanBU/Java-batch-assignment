package service;

import entity.Song;

import java.util.ArrayList;

public interface UserService {
    public ArrayList<Song> getFavoriteSong();
    public ArrayList<Song> getListenHistory();
    public void listen(Song song);
}
