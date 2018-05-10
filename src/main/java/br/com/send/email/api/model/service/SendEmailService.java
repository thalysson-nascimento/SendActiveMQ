package br.com.send.email.api.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.send.email.api.model.SendEmail;

@Service
@Component("javasampleapproachMailSender")
public class SendEmailService {

	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired 
	private JmsTemplate jmsTemplate;
	
	SimpleMailMessage mail = new SimpleMailMessage();
	
	public void enviarEmail(SendEmail sendEmail) {
		try {
			
			jmsTemplate.convertAndSend(sendEmail.getDestinatario(), sendEmail);
			
			mail.setFrom(sendEmail.getRemetente());
			mail.setTo(sendEmail.getDestinatario());
			mail.setSubject(sendEmail.getAssunto());
			mail.setText(sendEmail.getConteudoDaMensagem());
			
			javaMailSender.send(mail);
			
			emailEnviadoComSucesso(sendEmail);
		} catch (Exception e) {
			erroAoEnviarOEmail(sendEmail);
			e.printStackTrace();
		}
	}
	
	public void emailEnviadoComSucesso(SendEmail sendEmail) {
		String assuntoMsg = "Mensagem enviada com sucesso";
		String MesageSucess = "Olá, sua mensagem foi enviada com sucesso para " + sendEmail.getDestinatario()+".";
		
		mail.setTo(sendEmail.getRemetente());
		mail.setSubject(assuntoMsg);
		mail.setText(MesageSucess);
		
		javaMailSender.send(mail);
	}
	
	public void erroAoEnviarOEmail(SendEmail sendEmail) {
		String assuntoMsgErro = "Erro de envio de mensagem";
		String MesageError = "Erro ao enviar a mensagem para o destinatário";
		
		mail.setTo(sendEmail.getRemetente());
		mail.setSubject(assuntoMsgErro);
		mail.setText(MesageError);
		
		javaMailSender.send(mail);
	}
	
}
