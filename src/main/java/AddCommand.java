public class AddCommand extends Command {

    AddCommand(String[] lineSplit) {

        this.lineSplit = lineSplit;
    }

    @Override
    void execute() {
        Integer streamerID = Integer.parseInt(lineSplit[0]);
        Integer streamType = Integer.parseInt(lineSplit[2]);
        Integer streamID = Integer.parseInt(lineSplit[3]);
        Integer streamGenre = Integer.parseInt(lineSplit[4]);
        Long streamLength = Long.parseLong(lineSplit[5]);
        String streamName = lineSplit[6];
        Stream newStream = new StreamBuilder().withStreamType(streamType)
                                                .withID(streamID)
                                                .withStreamGenre(streamGenre)
                                                .withNoOfStreams((long) 0)
                                                .withStreamerID(streamerID)
                                                .withLength(streamLength)
                                                .withDateAdded((long) 1511308800)
                                                .withName(streamName)
                                                .build();
        Database.getInstance().getStreams().put(streamID, newStream);
    }
}

