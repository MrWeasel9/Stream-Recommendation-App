import com.google.gson.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Facade {
    public static HashMap<Integer, Streamer> readStreamersCSV(String inpFile) {

        HashMap<Integer, Streamer> streamers = new HashMap<>();
        try {
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/" + inpFile));
            String[] nextLine;

            // citesc linia de descriere
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {

                Integer streamerType = Integer.parseInt(nextLine[0].replace("\"", ""));
                Integer id = Integer.parseInt(nextLine[1].replace("\"", ""));
                String name = nextLine[2].replace("\"", "");

                // imi construiesc streamer-ul cu caracteristicile sale;
                Streamer streamer = new StreamerBuilder().withID(id)
                                                        .withName(name)
                                                        .withStreamerType(streamerType)
                                                        .build();
                // il pun in hashmap
                streamers.put(id, streamer);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return streamers;
    }
    public static HashMap<Integer, Stream> readStreamsCSV(String inpFile) {

        HashMap<Integer, Stream> streams = new HashMap<>();
        try {
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/" + inpFile));
            String[] nextLine;

            // citesc linia de descriere
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {

                Integer streamType = Integer.parseInt(nextLine[0].replace("\"", ""));
                Integer id = Integer.parseInt(nextLine[1].replace("\"", ""));
                Integer streamGenre = Integer.parseInt(nextLine[2].replace("\"", ""));
                Long noOfStreams = Long.parseLong(nextLine[3].replace("\"", ""));
                Integer streamerID = Integer.parseInt(nextLine[4].replace("\"", ""));
                Long length = Long.parseLong(nextLine[5].replace("\"", ""));
                Long dateAdded = Long.parseLong(nextLine[6].replace("\"", ""));
                String name = nextLine[7].replace("\"", "");

                // imi construiesc stream-ul cu caracteristicile sale;
                Stream stream = new StreamBuilder().withStreamType(streamType)
                                                    .withID(id)
                                                    .withStreamGenre(streamGenre)
                                                    .withNoOfStreams(noOfStreams)
                                                    .withStreamerID(streamerID)
                                                    .withLength(length)
                                                    .withDateAdded(dateAdded)
                                                    .withName(name)
                                                    .build();
                // il pun in hashmap
                streams.put(id, stream);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return streams;
    }
    public static HashMap<Integer, User> readUsersCSV(String inpFile) {

        HashMap<Integer, User> users = new HashMap<>();
        try {
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/" + inpFile));
            String[] nextLine;

            // citesc linia de descriere
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {

                Integer id = Integer.parseInt(nextLine[0].replace("\"", ""));
                String name = nextLine[1].replace("\"", "");
                String[] userStreams = nextLine[2].split(" ");
                List<Integer> streamList = new ArrayList<>();
                for (String userStream : userStreams) {
                    streamList.add(Integer.parseInt(userStream));
                }
                // fac un set cu id-urile streamerilor ascultati de user
                Set<Integer> streamersIDs = new HashSet<>();
                for (Integer streamID : streamList) {
                    Stream stream = Database.getInstance().getStreams().get(streamID);
                    streamersIDs.add(stream.getStreamerID());
                }
                // imi construiesc user-ul cu caracteristicile sale;
                User user = new UserBuilder().withID(id)
                                            .withName(name)
                                            .withStreams(streamList)
                                            .withStreamers(streamersIDs)
                                            .build();
                // il pun in hashmap
                users.put(id, user);

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
    public static List<String> readCommands(String inpFile) {
        List<String> commands = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + inpFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                commands.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return commands;
    }
    public static void printOneStream(Stream currentStream) {
        JsonObject streamJson = new JsonObject();
        Long seconds = currentStream.getLength();
        long minutes = seconds / 60;
        long hours = minutes / 60;
        Long remainingSeconds = seconds % 60;
        Long remainingMinutes = minutes % 60;

        long unixTimestamp = currentStream.getDateAdded();
        Date date = new Date(unixTimestamp * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        String formattedDate = sdf.format(date);
        String time;
        if (hours == 0) {
            time = String.format("%02d:%02d", remainingMinutes, remainingSeconds);
        } else {
            time = String.format("%02d:%02d:%02d", hours, remainingMinutes, remainingSeconds);
        }

        streamJson.addProperty("id", currentStream.getId().toString());
        streamJson.addProperty("name", currentStream.getName());
        streamJson.addProperty("streamerName", Database.getInstance().getStreamers().get(currentStream.getStreamerID()).getName());
        streamJson.addProperty("noOfListenings", currentStream.getNoOfStreams().toString());
        streamJson.addProperty("length", time);
        streamJson.addProperty("dateAdded", formattedDate);

        Gson gson = new Gson();
        System.out.print(gson.toJson(streamJson));

    }
    List<String> commands;

    public Facade(String[] args) {
        // citesc datele din fisierele de input si le pun in Database
        Database.getInstance().setStreamers(readStreamersCSV(args[0]));
        Database.getInstance().setStreams(readStreamsCSV(args[1]));
        Database.getInstance().setUsers(readUsersCSV(args[2]));
        this.commands = readCommands(args[3]);
    }

    public void run() {
        CommandFactory commandFactory = new CommandFactory();
        for (String line : commands) {
            String[] lineSplit = line.split(" ");
            Command nextCommand = commandFactory.returnCommand(lineSplit);
            nextCommand.execute();
        }
    }
}

