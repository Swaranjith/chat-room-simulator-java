import java.util.Scanner;

public class UserSession implements Runnable {
    private final String username;
    private final ChatRoom chatRoom;
    private final Scanner scanner;

    public UserSession(String username, ChatRoom chatRoom) {
        this.username = username;
        this.chatRoom = chatRoom;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        System.out.println("[" + username + "] Type your message. Type /exit to leave.");
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("/exit")) {
                System.out.println("[" + username + "] has left the chat.");
                break;
            }

            if (input.equalsIgnoreCase("/history")) {
                System.out.println("[" + username + "] Chat History:");
                for (ChatMessage msg : chatRoom.getMessages()) {
                    System.out.println(msg);
                }
                continue;
            }

            if (input.equalsIgnoreCase("/users")) {
                System.out.println("[" + username + "] Active users: " +
                        String.join(", ", chatRoom.getActiveUsers()));
                continue;
            }

            if (input.isEmpty()) {
                continue;
            }

            ChatMessage message = new ChatMessage(username, input);
            chatRoom.postMessage(message);
        }
    }
}
