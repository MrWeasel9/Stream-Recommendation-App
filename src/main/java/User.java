import java.util.List;
import java.util.Set;

public class User {
    private Integer id;
    private String name;
    private List<Integer> streams;
    private Set<Integer> streamers;

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Integer> getStreams() {
        return streams;
    }
    public Set<Integer> getStreamers() {
        return streamers;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStreams(List<Integer> streams) {
        this.streams = streams;
    }
    public void setStreamers(Set<Integer> streamers) {
        this.streamers = streamers;
    }


}
