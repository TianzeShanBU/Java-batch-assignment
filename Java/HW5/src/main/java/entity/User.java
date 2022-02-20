package entity;

import util.LRUCache;

import java.util.ArrayList;

public class User {
    private String id;
    private ArrayList<Song> favouriteSong;
    private LRUCache lru;
    private ArrayList<Song> listenHistory;

    public User(String id) {
        this.id = id;
        this.favouriteSong = new ArrayList<>();
        lru = new LRUCache(100);
        listenHistory = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public ArrayList<Song> getFavouriteSong() {
        return favouriteSong;
    }

    public LRUCache getLru() {
        return lru;
    }

    public void listen(Song song){
        lru.set(song.getId(),song);
        song.isListened();
    }

    public void addFavoriteSong(Song song){
        favouriteSong.add(song);
    }

    public ArrayList<Song> getListenHistory() {
        LRUCache.Node n = lru.getHead().getNext();
        while(n.getNext()!= lru.getHead() && n.getNext()!=null){
            listenHistory.add(n.getValue());
            n=n.getNext();
        }
        return listenHistory;
    }
}
