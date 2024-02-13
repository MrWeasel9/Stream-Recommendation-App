public class DeleteCommand extends Command {
    private String[] lineSplit;
    public DeleteCommand(String[] lineSplit) {
        this.lineSplit = lineSplit;
    }
    @Override
    public void execute() {
        Integer streamID = Integer.parseInt(lineSplit[2]);
        Database.getInstance().getStreams().remove(streamID);
    }
}
