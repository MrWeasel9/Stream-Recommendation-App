import java.util.ArrayList;
import java.util.List;

public class ListenCommand extends Command {
    private String[] lineSplit;
    public ListenCommand(String[] lineSplit) {
        this.lineSplit = lineSplit;
    }
    @Override
    public void execute() {
        Integer userID = Integer.parseInt(lineSplit[0]);
        Integer streamID = Integer.parseInt(lineSplit[2]);

        Stream stream = Database.getInstance().getStreams().get(streamID);
        // cresc numarul de ascultari al streamului
        stream.listen();

        // actualizez streamurile ascultate de user
        User user = Database.getInstance().getUsers().get(userID);
        List<Integer> userStreams = new ArrayList<>(user.getStreams());
        userStreams.add(streamID);
        user.setStreams(userStreams);

    }
}
