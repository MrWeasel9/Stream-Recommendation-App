import java.util.*;

public class SurpriseCommand extends Command {
    private String[] lineSplit;

    // compar dupa data , iar daca data este egala , dupa numarul de streamuri
    Comparator<Stream> surpriseComparator = (stream1, stream2) -> {
        Long date1 = stream1.getDateAdded();
        Long date2 = stream2.getDateAdded();
        if (date1 < date2) {
            return 1;
        } else if (date1 > date2) {
            return -1;
        } else {
            return (int) (stream2.getNoOfStreams() - stream1.getNoOfStreams());
        }
    };
    public SurpriseCommand(String[] lineSplit) {
        this.lineSplit = lineSplit;
    }
    @Override
    public void execute() {
        Integer userID = Integer.parseInt(lineSplit[0]);
        String type = lineSplit[2];
        int streamType = 0;
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
            // daca in streamerii user ului nu exista streamerul streamului
            // curent si daca tipul este cel cerut
            if (!user.getStreamers().contains(stream.getStreamerID()) && stream.getStreamType() == streamType) {
                requiredStreams.add(stream);
            }
        }
        // sortez streamurile
        requiredStreams.sort(surpriseComparator);
        System.out.print("[");
        for (int i = 0; i < requiredStreams.toArray().length && i < 3; i++) {
            Stream currentStream = requiredStreams.get(i);
            Facade.printOneStream(currentStream);
            // verific ca nu este ultimul element pe care il printez
            if (i != requiredStreams.toArray().length - 1 && i < 2) {
                System.out.print(",");
            }
        }
        System.out.print("]");
        System.out.print("\n");

    }
}
