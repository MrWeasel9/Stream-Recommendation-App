import java.util.*;

public class RecommendCommand extends Command {
    private String[] lineSplit;


    public RecommendCommand(String[] lineSplit) {
        this.lineSplit = lineSplit;

    }

    Comparator<Stream> comparator = (s1, s2) -> (int) (s2.getNoOfStreams() - s1.getNoOfStreams());

    @Override
    public void execute() {
        Integer userID = Integer.parseInt(lineSplit[0]);
        String type = lineSplit[2];
        Integer streamType = 0;

        if (type.equals("SONG")) {
            streamType = 1;
        } else if (type.equals("PODCAST")) {
            streamType = 2;
        } else if (type.equals("AUDIOBOOK")) {
            streamType = 3;
        }
        User user = Database.getInstance().getUsers().get(userID);
        ArrayList<Stream> requiredStreams = new ArrayList<>();

        for (Stream stream : Database.getInstance().getStreams().values()) {

            // daca in streamerii user ului nu exista streamerul
            // streamului curent si daca tipul este cel cerut
            if (!user.getStreams().contains(stream.getId()) && user.getStreamers().contains(stream.getStreamerID()) && stream.getStreamType() == streamType) {
                requiredStreams.add(stream);
            }
        }

        // sortez streamurile
        requiredStreams.sort(comparator);
        System.out.print("[");
        for (int i = 0; i < requiredStreams.toArray().length && i < 5; i++) {
            Stream currentStream = requiredStreams.get(i);
            Facade.printOneStream(currentStream);
            if (i != requiredStreams.toArray().length - 1) {
                System.out.print(",");
            }
        }
        System.out.print("]");
        System.out.print("\n");
    }
}
