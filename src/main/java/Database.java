import java.util.HashMap;

public class Database {

    private static Database uniqueInstance;
    private HashMap<Integer, Stream> streams = new HashMap<>();
    private HashMap<Integer, Streamer> streamers = new HashMap<>();
    private HashMap<Integer, User> users = new HashMap<>();

    private Database() {

    }

    private Database(HashMap<Integer, Stream> streams, HashMap<Integer, Streamer> streamers,
                     HashMap<Integer, User> users) {
        this.streams = streams;
        this.streamers = streamers;
        this.users = users;
    }
    public void setStreams(HashMap<Integer, Stream> streams) {
        this.streams = streams;
    }
    public void setStreamers(HashMap<Integer, Streamer> streamers) {
        this.streamers = streamers;
    }
    public void setUsers(HashMap<Integer, User> users) {
        this.users = users;
    }
    public HashMap<Integer, Stream> getStreams() {
        return streams;
    }
    public HashMap<Integer, Streamer> getStreamers() {
        return streamers;
    }
    public HashMap<Integer, User> getUsers() {
        return users;
    }
    public static Database getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new Database();

        return uniqueInstance;
    }
}