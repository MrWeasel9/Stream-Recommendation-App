Sistem smart de recomandări stream-uri

Introducere
O aplicație de streaming cunoscută are nevoie de ajutorul vostru pentru a implementa un sistem smart de recomandări pentru utilizatorii existenți. Scopul acestui proiect este de a realiza un algoritm de recomandări pentru stream-uri (muzica, podcast-uri sau audiobooks) pe baza datelor existente despre utilizatorii aplicației (ascultători) și despre creatorii de stream-uri (muzicieni, moderatori de podcast, etc.) sau cele acumulate de-a lungul rularii aplicației.

Funcționalitatea aplicației
Pentru a putea implementa sistemul de recomandări, veți primi datele despre fiecare entitate implicată în fișiere separate, după cum urmează:

Datele de intrare
Datele de intrare pentru proiect se vor afla în fișiere de tipul CSV (Comma Separated Values) și puteți alege orice librărie de parsare din Java pentru a citi și parsa fișierele de intrare (ex: OpenCSV).

Pentru output, se vor scrie toate comenzile la consola. Metoda main a proiectului se va afla în clasa ProiectPOO și va primi ca parametrii 3 fișiere CSV (în ordinea streamers.csv, streams.csv și users.csv) și un fișier text comenzi.txt, ce va conține comenzile date de-a lungul rulării aplicației.

Streamers
Datele despre autorii de stream-uri, fie muzicieni, gazde de podcast sau autorii audiobook-urilor se vor găsi în fișierul streamers.csv, unde o linie va reprezenta datele unui streamer:

Nume câmp	Tip de date	Descriere
streamerType	Integer	tipul de streamer
id	Integer	identificator unic
name	String	numele streamer-ului
Streams
Datele despre stream-urile postate în aplicație până în momentul curent se vor găsi în fișierul streams.csv, unde o linie va reprezenta datele unui stream:

Nume câmp	Tip de date	Descriere
streamType	Integer	tipul de stream
id	Integer	identificator unic
streamGenre	Integer	genul stream-ului
noOfStreams	Long	numărul de ascultări
streamerId	Integer	id-ul streamer-ului
length	Long	durata stream-ului în secunde
dateAdded	Long	data la care a fost adăugat stream-ul (format Unix timestamp)
name	String	numele streamului
User
Datele despre un utilizator al platformei de streaming se vor afla în fișierul de intrare users.csv, unde o linie va reprezenta un utilizator și va avea formatul:

Nume câmp	Tip de date	Descriere
id	Integer	identificator unic
name	String	numele utilizatorului
streams	List<Integer>	lista de stream id, reprezentând istoria stream-urilor ascultate, despărțite printr-un spațiu
Comenzi
Pentru a implementa sistemul smart de recomandări, implementarea proiectului trebuie să fie capabilă să modifice datele existente atunci când se rulează comenzi de către utilizatori sau stream-uri, pentru a putea face recomandări corecte.

Comenzi pentru streamers:
Adaugă Stream: ADD <streamerId:Integer> <streamType: Integer> <id: Integer> <streamGenre: Integer> <length: Long> <name:String> - Nu se printează nimic la consolă, dar se modifică datele aplicației.

Listează streamurile unui streamer: LIST <streamerId:Integer> - Se va afișa în format JSON o listă de streams.

Șterge Stream: DELETE <streamerId:Integer> <streamId:Integer> - Nu se printează nimic la consolă, dar se modifică datele aplicației.

Comenzi pentru utilizatori:
Listează istoria de ascultare a utilizatorului: LIST <userId: Integer> - Se va afișa în format JSON o listă de streams.

Ascultă un stream: LISTEN <userId: Integer> <streamId:Integer> - Nu se printează nimic la consolă, dar se modifică datele aplicației.

Recomandă 5 stream-uri după preferințe: RECOMMEND [SONG | PODCAST | AUDIOBOOK] - Se va afișa în format JSON o listă de streams recomandate.

Recomandă 3 stream-uri surpriză: SURPRISE [SONG | PODCAST | AUDIOBOOK] - Se va afișa în format JSON o listă de streams recomandate.

Algoritmul de recomandare
Algoritmul de recomandare a stream-urilor va fi următorul:

Pentru recomandările bazate pe preferințe:
Din lista de streamers ascultați de utilizator veți alege top 5 stream-uri (neascultate) cu cele mai multe ascultări pentru tipul de stream pasat ca parametru.
Pentru recomandările surpriză:
Din lista de streamers din aplicație, ce nu au fost ascultați de utilizator, veți alege 3 stream-uri ce au fost adăugate cel mai recent. Dacă au fost adăugate în aceeași zi, atunci veți alege stream-ul cu cele mai multe ascultări pentru tipul de stream pasat ca parametru.
