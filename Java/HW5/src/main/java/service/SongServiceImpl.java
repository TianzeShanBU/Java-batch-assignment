package service;

import entity.Song;

public class SongServiceImpl implements SongService{
    Song song;

    public SongServiceImpl(Song song) {
        this.song = song;
    }

    @Override
    public void isListened() {
        song.isListened();
    }

}
