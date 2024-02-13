public class CommandFactory {
    public Command returnCommand(String[] line) {
        Command command = null;
        switch (line[1]) {
            case "LIST":
                command = new ListCommand(line);
                return command;
            case "ADD":
                command = new AddCommand(line);
                return command;
            case "DELETE":
                command = new DeleteCommand(line);
                return command;
            case "LISTEN":
                command = new ListenCommand(line);
                return command;
            case "RECOMMEND":
                command = new RecommendCommand(line);
                return command;
            case "SURPRISE":
                command = new SurpriseCommand(line);
                return command;
        }
        return command;
    }
}
