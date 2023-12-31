package com.trading.application.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trading.application.logs.entity.AccessLog;
import com.trading.application.logs.service.AccessLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.concurrent.ExecutionException;

/**
 * The type Log subsciber.
 */
public class LogSubsciber implements MessageListener {
    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(AccessLogService.class);
    /**
     * The Access log service.
     */
    @Autowired
    AccessLogService accessLogService = new AccessLogService();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        logger.info(String.valueOf(message));
        String jsonMessage = message.toString();
        logger.info(jsonMessage);
        jsonMessage = extractJsonFromMessage(jsonMessage);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            AccessLog accessLog = objectMapper.readValue(jsonMessage, AccessLog.class);
            logger.info("Event Processed");
            accessLogService.addLog(accessLog);

        } catch (JsonProcessingException e) {
            logger.error("Error deserializing AccessLog from message: " + e.getMessage());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Extract json from message string.
     *
     * @param messageContent the message content
     * @return the string
     */
    private String extractJsonFromMessage(String messageContent) {
        // You can implement custom logic here to extract the JSON portion
        // For example, you can use regular expressions or other techniques
        // to find and isolate the JSON part from the message content.

        // For simplicity, let's assume the JSON starts with '{' and ends with '}'.
        int startIndex = messageContent.indexOf('{');
        int endIndex = messageContent.lastIndexOf('}');

        if (startIndex != -1 && endIndex != -1) {
            return messageContent.substring(startIndex, endIndex + 1);
        } else {
            return null;
        }
    }


}
