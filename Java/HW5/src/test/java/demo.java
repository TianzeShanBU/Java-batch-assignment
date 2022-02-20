import entity.Song;
import entity.User;
import org.junit.Assert;
import org.junit.Test;
import service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;



public class demo {
    @Test
    public void cacheIsWorking(){
        SongCache cache = new SongCacheImpl();
        CompletableFuture.runAsync(()->{});
        cache.recordSongPlays("ID-1", 3);
        cache.recordSongPlays("ID-1", 1);
        cache.recordSongPlays("ID-2", 2);
        cache.recordSongPlays("ID-3", 5);

        assertThat(cache.getPlaysForSong("ID-1"), is(4));
        assertThat(cache.getPlaysForSong("ID-1001"), is(-1));

        assertThat(cache.getTopNSongsPlayed(2), contains("ID-3", "ID-1"));
        assertEquals(cache.getTopNSongsPlayed(2).get(0),"ID-3");
        assertThat(cache.getTopNSongsPlayed(0), is(empty()));
    }

    @Test
    public void cacheIsWorkingInMultiThread(){
        SongCache cache = new SongCacheImpl();
        List<CompletableFuture> futures = new ArrayList();
        futures.add(CompletableFuture.runAsync(()->{
            for(int i=0;i<1000;i++){
                cache.recordSongPlays("ID-1", 3);
            }
        }));
        futures.add(CompletableFuture.runAsync(()->{
            for(int i=0;i<1000;i++){
                cache.recordSongPlays("ID-1", 3);
            }
        }));
        futures.add(CompletableFuture.runAsync(()->{
            for(int i=0;i<1000;i++){
                cache.recordSongPlays("ID-1", 4);
            }
        }));
        futures.add(CompletableFuture.runAsync(()->{
            for(int i=0;i<1000;i++){
                cache.recordSongPlays("ID-2", 2);
            }
        }));
        cache.recordSongPlays("ID-3", 5);
        CompletableFuture.allOf(futures.toArray(new  CompletableFuture[futures.size()])).join();

        assertThat(cache.getPlaysForSong("ID-1"), is(10000));
        assertThat(cache.getPlaysForSong("ID-2"), is(2000));
        assertThat(cache.getPlaysForSong("ID-3"), is(5));
    }

    @Test
    public void userAndSongAreWorking(){
        UserService user1 = new UserServiceImpl(new User("1"));
        UserService user2 = new UserServiceImpl(new User("2"));
        UserService user3 = new UserServiceImpl(new User("3"));
        SongCache cache = new SongCacheImpl();

        List<CompletableFuture> futures = new ArrayList();

        futures.add(CompletableFuture.runAsync(()->{
            for(int i=0;i<1000;i++){
                user1.listen(SongCacheImpl.musicDB.get("ID-1"));
//                System.out.println(Thread.currentThread().getName());
            }
        }));

        futures.add(CompletableFuture.runAsync(()->{
            for(int i=0;i<2000;i++){
                user2.listen(SongCacheImpl.musicDB.get("ID-1"));
//                System.out.println(Thread.currentThread().getName());
            }
        }));

        futures.add(CompletableFuture.runAsync(()->{
            for(int i=0;i<3000;i++){
                user3.listen(SongCacheImpl.musicDB.get("ID-1"));
//                System.out.println(Thread.currentThread().getName());
            }
        }));


        CompletableFuture.allOf(futures.toArray(new  CompletableFuture[futures.size()])).join();
        assertThat(cache.getPlaysForSong("ID-1"), is(6000));
    }

    @Test
    public void songHistoryIsWorking(){
        SongCacheImpl cache = new SongCacheImpl();
        UserService user1 = new UserServiceImpl(new User("1"));
        for(int i = 0; i<101;i++){
            user1.listen(SongCacheImpl.musicDB.get("ID-"+i));;
        }
        ArrayList<Song> s = user1.getListenHistory();
        String firstID = user1.getListenHistory().get(0).getId();
        assertEquals(firstID,"ID-100");
        String lastID = user1.getListenHistory().get(user1.getListenHistory().size()-1).getId();
        assertEquals(lastID, "ID-1");
    }
}
