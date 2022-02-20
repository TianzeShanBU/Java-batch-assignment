package entity;

import java.util.concurrent.atomic.AtomicInteger;

public class Song {
    private String id;
    private AtomicInteger totalPlays;

    public Song(String id) {
        this.id = id;
        this.totalPlays = new AtomicInteger(0);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AtomicInteger getTotalPlays() {
        return totalPlays;
    }

    public void setTotalPlays(Integer totalPlays) {
        this.totalPlays.set(totalPlays);
    }

    public void isListened(){
        this.totalPlays.getAndIncrement();
    }

    @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", totalPlays=" + totalPlays +
                '}';
    }
}
