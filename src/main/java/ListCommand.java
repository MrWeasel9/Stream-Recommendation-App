import java.util.ArrayList;
import java.util.List;

public class ListCommand extends Command {

    ListCommand(String[] lineSplit) {
        this.lineSplit = lineSplit;
    }
    @Override
    void execute() {
        Integer id = Integer.parseInt(lineSplit[0]);
        User user = Database.getInstance().getUsers().get(id);
        System.out.print("[");
            // streamurile userului
        if (user != null) {
            List<Integer> userStreamsID = new ArrayList<>(user.getStreams());
            for (int i = 0; i < userStreamsID.toArray().length; i++) {
                Integer streamID = userStreamsID.get(i);
                Stream currentStream = Database.getInstance().getStreams().get(streamID);
                Facade.printOneStream(currentStream);
                // daca streamul curent este ultimul care trb
                // sa fie printat nu mai pun ,
                if (i != userStreamsID.toArray().length - 1) {
                    System.out.print(",");
                }
            }
            // streamurile streamerului
        } else {
            // fac o lista de streamuri cu id-uri corecte
            ArrayList<Stream> myStreams = new ArrayList<>();
            for (Stream currentStream : Database.getInstance().getStreams().values()) {
                if (currentStream.getStreamerID().equals(id)) {
                    myStreams.add(currentStream);
                }
            }
            int counter = 0;
            for (Stream currentStream : myStreams) {
                if (currentStream.getStreamerID().equals(id)) {
                    Facade.printOneStream(currentStream);
                }
                //verific daca streamul printat nu este
                // ultimul ca sa stiu cand sa pun virgula
                if (counter != myStreams.toArray().length - 1) {
                    System.out.print(",");
                }
                counter++;
            }

        }
        System.out.print("]");
        System.out.print("\n");
    }
}
