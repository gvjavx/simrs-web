package com.neurix.common.util;

import com.kinoroy.expo.push.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author gondok
 * Monday, 30/12/19 15:38
 */
public class ExpoPushNotif {
    public static String sendNotificationExpo(String expoToken, String title, String body, String os) {
        String actionMessage = "done";
        List<String> somePushTokens = Arrays.asList(expoToken);
        List<String> ticketId = new ArrayList<>();

        List<Message> messages = new ArrayList<>();
        // You can check whether your push tokens are syntactically valid
        for (String token : somePushTokens) {
            // Each push token looks like ExponentPushToken[xxxxxxxxxxxxxxxxxxxxxx]
            if (!ExpoPushClient.isExpoPushToken(token)) {
                actionMessage = "Invalid Expo Token";
            }
        }
        for (String token : somePushTokens) {
            // Construct a message

            if (os.equalsIgnoreCase("Android")) {
                Message message = new Message.Builder()
                        .to(token)
                        .title(title)
                        .sound("default")
                        .priority(Priority.HIGH)
                        .channelId("android-notif")
                        .body(body)
                        .build();
                messages.add(message);
            } else if (os.equalsIgnoreCase("iOS")) {
                Message message = new Message.Builder()
                        .to(token)
                        .title(title)
                        .sound("default")
                        .priority(Priority.HIGH)
                        .body(body)
                        .build();
                messages.add(message);
            }
        }
        // The Expo push service accepts batches of messages, no more than 100 at a time.
        // If you know you're sending more than 100 messages,
        // Use ExpoPushClient.chunkItems to get lists of 100 or less items
        List<List<Message>> chunks = ExpoPushClient.chunkItems(messages);

        for (List<Message> chunk : chunks) {
            try {
                PushTicketResponse response = ExpoPushClient.sendPushNotifications(messages);
                List<ExpoError> errors = response.getErrors();
                // If there is an error with the *entire request*:
                // The errors object will be an list of errors,
                // (usually just one)
                if (errors != null) {
                    for (ExpoError error : errors) {
                        // Handle the errors
                    }
                }
                // If there are errors that affect individual messages but not the entire request,
                // errors will be null and each push ticket will individually contain the status
                // of each message (ok or error)
                List<PushTicket> tickets = response.getTickets();
                if (tickets != null) {
                    for (PushTicket ticket : tickets) {
                        // Handle each ticket (namely, check the status, and save the ID!)
                        // NOTE: If a ticket status is error, you can get the specific error
                        // from the details object. You must handle it appropriately.
                        // The error codes are listed in PushError
                        if (ticket.getStatus() == Status.OK) {
                            ticketId.add(ticket.getId());
                            // Save this id somewhere for later
                        } else {
                            // Handle the error
                            PushError e = ticket.getDetails().getError();
                            switch (e) {
                                case MESSAGE_TOO_BIG:
                                case INVALID_CREDENTIALS:
                                case DEVICE_NOT_REGISTERED:
                                case MESSAGE_RATE_EXCEEDED:
                            }

                        }
                    }
                }
            } catch (IOException e) {
                actionMessage = e.getMessage();
            }
        }

        // Later, you can get the Push Receipts using the ids you saved from above.
        // Usually, the receipts are available within a few seconds, but when Expo is under load,
        // it can take up to 30 min. Push Reciepts are available for at least 1 day

        try {
            PushReceiptResponse response = ExpoPushClient.getPushReciepts(ticketId);
            Map<String, PushReceipt> receipts = response.getReceipts();
            for (String id : ticketId) {
                PushReceipt rec = receipts.get(id);
                if (rec != null) {
                    if (rec.getStatus() == Status.OK) {
                        // It's all good!
                    } else {
                        // Handle the error
                        PushError e = rec.getDetails().getError();
                        switch (e) {
                            case MESSAGE_TOO_BIG:
                            case INVALID_CREDENTIALS:
                            case DEVICE_NOT_REGISTERED:
                            case MESSAGE_RATE_EXCEEDED:
                        }
                    }
                }
            }
        } catch (IOException e) {
           actionMessage = e.getMessage();
        }

        return actionMessage;
    }

}


