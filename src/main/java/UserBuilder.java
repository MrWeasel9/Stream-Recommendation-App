import java.util.List;
import java.util.Set;

public class UserBuilder {
    private User user = new User();
    public UserBuilder withID(Integer id) {
        user.setId(id);
        return this;
    }
    public UserBuilder withName(String name) {
        user.setName(name);
        return this;
    }
    public UserBuilder withStreams(List<Integer> streams) {
        user.setStreams(streams);
        return this;
    }
    public UserBuilder withStreamers(Set<Integer> streamers) {
        user.setStreamers(streamers);
        return this;
    }
    public User build(){
        return user;
    }
}
