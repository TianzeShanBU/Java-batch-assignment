package service;

import entity.Song;
import entity.User;

import java.util.ArrayList;

public class UserServiceImpl implements UserService{

    User user;

    public UserServiceImpl(User user) {
        this.user = user;
    }

    @Override
    public ArrayList<Song> getFavoriteSong() {
        return user.getFavouriteSong();
    }

    @Override
    public ArrayList<Song> getListenHistory() {
        return user.getListenHistory();
    }

    @Override
    public void listen(Song song) {
        user.listen(song);
    }
}
