package service;

import entity.Song;

import java.util.*;

public class SongCacheImpl implements  SongCache{
    public static Map<String,Song> musicDB;

    public SongCacheImpl() {
        musicDB = new HashMap<>();
        for(int i = 0;i<200;i++){
            musicDB.put("ID-"+i,new Song("ID-"+i));
        }
    }

    @Override
    public void recordSongPlays(String songId, int numPlays) {
        musicDB.get(songId).getTotalPlays().getAndAdd(numPlays);
    }

    @Override
    public int getPlaysForSong(String songId) {
        if(musicDB.containsKey(songId)){
            return musicDB.get(songId).getTotalPlays().get();
        }
        return -1;
    }

    @Override
    public List<String> getTopNSongsPlayed(int n) {
        ArrayList<String> res = new ArrayList<>();
        Map<String,Integer> freq = new HashMap<>();
        for(String s: musicDB.keySet()){
            freq.put(s,musicDB.get(s).getTotalPlays().get());
        }
        PriorityQueue<String> pq = new PriorityQueue<>((a, b)->freq.get(b)-freq.get(a));
        for(String s:freq.keySet()){
            pq.offer(s);
        }
        for(int i=0;i<n;i++){
            res.add(pq.poll());
        }
        return res;
    }
}
