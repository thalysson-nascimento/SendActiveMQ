package br.com.send.email.api.model.service;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.send.email.api.model.SendEmail;
@Service
@Component("javasampleapproachMailSender")
public class ConsumerEmailService {
	
	public void userConsume(SendEmail sendEmail){
		try {
			ActiveMQConnectionFactory factory = 
					new ActiveMQConnectionFactory("tcp://localhost:61616");
			factory.setTrustAllPackages(true);
			
			//Cria a conexão com ActiveMQ
			Connection connection = factory.createConnection();
			// Inicia a conexão
			connection.start();
			// Cria a sessão
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //Crea a fila e informa qual o destinatário.
            Destination queue = session.createQueue(sendEmail.getDestinatario());            
            MessageConsumer consumer = session.createConsumer(queue);
            Message message = consumer.receive();
            
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Consumer Received 1: " + text);
            }
            
            if(message instanceof ObjectMessage){
            	ObjectMessage objectMessage = (ObjectMessage) message;
            	SendEmail paciente = (SendEmail) objectMessage.getObject();
            	System.out.println("Consumer Received 2: " + paciente);
            }
            
            session.close();
            connection.close();
		} catch (Exception e) {
			System.out.println("Exception Occured");
		}
	}
	
}
