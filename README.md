# Smart Stream Recommendation System



## Table of Contents
- [Introduction](#introduction)
- [Functionality](#functionality)
- [Commands](#commands)
- [Recommendation Algorithm](#recommendation-algorithm)
- [Scoring](#scoring)
- [Appendix](#appendix)

## Introduction
This project, developed during the academic years 2022-2023, focuses on implementing a smart recommendation system for streams within a popular streaming application. The aim is to devise an algorithm for recommending music, podcasts, or audiobooks based on existing user data and stream creator information.

## Functionality
To implement the recommendation system, the project utilizes input data stored in CSV (Comma Separated Values) files. These files contain information about streamers, streams, and users. The application modifies existing data based on user or streamer commands, enabling accurate recommendations.

### Streamers
Information about stream creators, including musicians, podcast hosts, or audiobook authors, is stored in the `streamers.csv` file. Each line in the file represents a streamer with the following format:

```
streamerType, id, name
```

- **streamerType**: An integer representing the type of streamer (1 for musician, 2 for podcast host, 3 for audiobook author).
- **id**: Unique identifier for the streamer.
- **name**: Name of the streamer.

### Streams
Details about streams posted in the application are stored in the `streams.csv` file. Each line represents a stream with the following format:

```
streamType, id, streamGenre, noOfStreams, streamerId, length, dateAdded, name
```

- **streamType**: An integer representing the type of stream (1 for music, 2 for podcast, 3 for audiobook).
- **id**: Unique identifier for the stream.
- **streamGenre**: Integer representing the genre of the stream.
- **noOfStreams**: Number of listens the stream has received.
- **streamerId**: ID of the streamer who published the stream.
- **length**: Duration of the stream in seconds.
- **dateAdded**: Unix timestamp indicating when the stream was added.
- **name**: Name of the stream.

### Users
User data is stored in the `users.csv` file. Each line represents a user with the following format:

```
id, name, streams
```

- **id**: Unique identifier for the user.
- **name**: User's name.
- **streams**: List of stream IDs representing the user's listening history.

## Commands
The application supports commands executed by both streamers and users to interact with the system.

### Streamer Commands
1. **Add Stream**: Add a new stream.
   - Format: `ADD <streamerId:Integer> <streamType:Integer> <id:Integer> <streamGenre:Integer> <length:Long> <name:String>`
   
2. **List Streams**: List streams of a streamer.
   - Format: `LIST <streamerId:Integer>`
   
3. **Delete Stream**: Delete a stream.
   - Format: `DELETE <streamerId:Integer> <streamId:Integer>`

### User Commands
1. **List Listening History**: List the listening history of a user.
   - Format: `LIST <userId:Integer>`
   
2. **Listen to Stream**: Listen to a stream.
   - Format: `LISTEN <userId:Integer> <streamId:Integer>`
   
3. **Recommend Streams**: Recommend streams based on user preferences.
   - Format: `RECOMMEND <userId:Integer> [SONG | PODCAST | AUDIOBOOK]`
   
4. **Surprise Streams**: Get surprise stream recommendations.
   - Format: `SURPRISE <userId:Integer> [SONG | PODCAST | AUDIOBOOK]`

## Recommendation Algorithm
The recommendation algorithm operates as follows:

- For user-specific recommendations, the system selects the top 5 unlistened streams with the highest number of listens from the user's listening history.
- For surprise recommendations, the system selects 3 unlistened streams added most recently, prioritizing those with the highest number of listens if added on the same day.

## Scoring
The scoring for the project is distributed as follows:

- 40 points for automated tests validating functionality.
- 50 points for utilizing at least 4 design patterns with proper justification.
- 10 points for applying principles of Object-Oriented Programming (inheritance, encapsulation, collections, etc.).

## Appendix
The JSON schema for the stream information output is provided below:

```json
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "array",
  "items": [
    {
      "type": "object",
      "properties": {
        "id": {"type": "string"},
        "name": {"type": "string"},
        "streamerName": {"type": "string"},
        "noOfStreams": {"type": "string"},
        "length": {"type": "string"},
        "dateAdded": {"type": "string"}
      },
      "required": ["id", "name", "streamerName", "noOfStreams", "length", "dateAdded"]
    }
  ]
}
```
