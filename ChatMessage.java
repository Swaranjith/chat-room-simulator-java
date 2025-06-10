import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatMessage {
    private final String sender;
    private final String content;
    private final LocalDateTime timestamp;

    public ChatMessage(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return "[" + timestamp.format(formatter) + "] " + sender + ": " + content;
    }

    public String getSender() {
    return sender;
}

}
