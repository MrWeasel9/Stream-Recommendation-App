public class StreamBuilder {
    private Stream stream = new Stream();
    public StreamBuilder withID(Integer id) {
        stream.setId(id);
        return this;
    }
    public StreamBuilder withName(String name) {
        stream.setName(name);
        return this;
    }
    public StreamBuilder withStreamerID(Integer id) {
        stream.setStreamerID(id);
        return this;
    }
    public StreamBuilder withStreamType(Integer streamType) {
        stream.setStreamType(streamType);
        return this;
    }
    public StreamBuilder withStreamGenre(Integer streamGenre) {
        stream.setStreamGenre(streamGenre);
        return this;
    }
    public StreamBuilder withNoOfStreams(Long noOfStreams) {
        stream.setNoOfStreams(noOfStreams);
        return this;
    }
    public StreamBuilder withLength(Long length) {
        stream.setLength(length);
        return this;
    }
    public StreamBuilder withDateAdded(Long dateAdded) {
        stream.setDateAdded(dateAdded);
        return this;
    }
    public Stream build(){
        return stream;
    }
}
