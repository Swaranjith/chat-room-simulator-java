import java.util.Random;

public class BotUser implements Runnable {
    private final String botName;
    private final ChatRoom chatRoom;
    private final String[] botMessages = {
        "Hello everyone!",
        "How's it going?",
        "Anyone here?",
        "I'm a friendly bot.",
        "Java is awesome!",
        "Have you tried multithreading?",
        "This chat is cool!",
        "Keep chatting :)",
        "I'm here to help!",
        "Let's code together!"
    };
    private final Random random = new Random();

    public BotUser(String botName, ChatRoom chatRoom) {
        this.botName = botName;
        this.chatRoom = chatRoom;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Wait until all users have sent messages
                chatRoom.waitForAllUsersToMessage();

                // Pick a random message and post it
                String message = botMessages[random.nextInt(botMessages.length)];
                ChatMessage chatMessage = new ChatMessage(botName, message);
                chatRoom.postMessage(chatMessage);

                // Reset tracking so cycle can restart
                chatRoom.resetUsersMessaged();
            }
        } catch (InterruptedException e) {
            System.out.println("[" + botName + "] Bot stopped.");
        }
    }
}
