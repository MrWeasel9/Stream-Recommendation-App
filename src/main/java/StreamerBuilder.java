
public class StreamerBuilder {
    private Streamer streamer = new Streamer();
    public StreamerBuilder withID(Integer id) {
        streamer.setId(id);
        return this;
    }
    public StreamerBuilder withName(String name) {
        streamer.setName(name);
        return this;
    }
    public StreamerBuilder withStreamerType(Integer streamerType) {
        streamer.setStreamerType(streamerType);
        return this;
    }
    public Streamer build(){
        return streamer;
    }
}
