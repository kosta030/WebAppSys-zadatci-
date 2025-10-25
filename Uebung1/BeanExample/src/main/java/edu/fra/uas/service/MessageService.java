package edu.fra.uas.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.stereotype.Component;

@Component
public class MessageService {
    public int counter = 0;
    public int increment(int counter){
        return counter++;
    }
	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
	private String message;
	public String getMessage() {
        logger.info("getMessage() aufgerufen. Aktuelle Nachricht: ", message);

		return message;
	}
	public void setMessage(String message){
		logger.info("setMessage() aufgerufen. Neue Nachricht wird gesetzt: {}", message);
        this.message = message;
        logger.debug("Nachricht erfolgreich aktualisiert. Länge der Nachricht: {}", message != null ? message.length() : 0);
	}
}

