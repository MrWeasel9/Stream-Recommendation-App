public class Stream {

    private Integer streamType;
    private Integer id;
    private Integer streamGenre;
    private Long noOfStreams;
    private Integer streamerID;
    private Long length;
    private Long dateAdded;
    private String name;

    public Integer getStreamType() {
        return streamType;
    }
    public Integer getId() {
        return id;
    }
    public Long getNoOfStreams() {
        return noOfStreams;
    }
    public Integer getStreamerID() {
        return streamerID;
    }
    public Long getLength() {
        return length;
    }
    public Long getDateAdded() {
        return dateAdded;
    }
    public String getName() {
        return name;
    }

    public void setStreamType(Integer streamType) {
        this.streamType = streamType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStreamGenre(Integer streamGenre) {
        this.streamGenre = streamGenre;
    }

    public void setNoOfStreams(Long noOfStreams) {
        this.noOfStreams = noOfStreams;
    }

    public void setStreamerID(Integer streamerID) {
        this.streamerID = streamerID;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public void setDateAdded(Long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void listen() {
        this.noOfStreams++;
    }
}
