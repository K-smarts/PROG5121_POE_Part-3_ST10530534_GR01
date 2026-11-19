package chat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    // ===== PART 2 TESTS =====
    @Test
    public void testCheckRecipientCellSuccess() {
        Message m = new Message(0, "+27718693002", "Hi");
        assertEquals("Cell phone number successfully captured.", m.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCellFail() {
        Message m = new Message(0, "08575975889", "Hi");
        assertTrue(m.checkRecipientCell().startsWith("Cell phone number is incorrectly"));
    }

    @Test
    public void testCheckMessageLengthSuccess() {
        Message m = new Message(0, "+27718693002", "Hi");
        assertEquals("Message ready to send.", m.checkMessageLength());
    }

    @Test
    public void testMessageHash() {
        Message m = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight");
        String hash = m.createMessageHash();
        assertTrue(hash.contains(":0:HITONIGHT"));
    }

    @Test
    public void testMessageIDLength() {
        Message m = new Message(0, "+27718693002", "Hi");
        assertEquals(10, m.getMessageID().length());
    }

    // ===== PART 3 TESTS =====
    private void seedMessages() {
        Message.clearAllForTesting();
        
        Message m1 = new Message(0, "+27718693002", "Did you get the cake?");
        Message m2 = new Message(1, "+27838968976", "Where are you? You are late! I have waited for an hour.");
        Message.getSentMessages().add(m1);
        Message.getSentMessages().add(m2);
    }

    @Test
    public void testDisplaySentMessages() {
        seedMessages();
        assertTrue(Message.displaySentMessages().contains("Did you get the cake?"));
    }

    @Test
    public void testDisplayLongestMessage() {
        seedMessages();
        assertTrue(Message.displayLongestMessage().contains("Where are you?"));
    }

    @Test
    public void testSearchByRecipient() {
        seedMessages();
        assertTrue(Message.searchByRecipient("+27718693002").contains("Did you get the cake?"));
    }

    @Test
    public void testSearchByMessageID() {
        seedMessages();
        String id = Message.getSentMessages().get(0).getMessageID();
        assertTrue(Message.searchByMessageID(id).contains("Did you get the cake?"));
    }

    @Test
    public void testDeleteByHash() {
        seedMessages();
        String hash = Message.getSentMessages().get(0).getMessageHash();
        assertEquals("Message successfully deleted.", Message.deleteByHash(hash));
        assertEquals(1, Message.getSentMessages().size());
    }

    @Test
    public void testDisplayReport() {
        seedMessages();
        assertTrue(Message.displayReport().contains("+27718693002"));
    }
}