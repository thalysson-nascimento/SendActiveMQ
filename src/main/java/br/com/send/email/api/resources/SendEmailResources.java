package br.com.send.email.api.resources;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.send.email.api.model.SendEmail;
import br.com.send.email.api.model.service.ConsumerEmailService;
import br.com.send.email.api.model.service.SendEmailService;

@RestController
@RequestMapping("email-send")
public class SendEmailResources {
	
	@Autowired
	@Qualifier("javasampleapproachMailSender")
	SendEmailService sendEmailService;
	
	@PostMapping
	public HashMap<String, Object> enviarMensagem(@Valid @RequestBody SendEmail sendEmail) {
		HashMap<String, Object> map = new HashMap<>();
		try {
			String msgSucess = "Email enviado com sucesso.";
			sendEmailService.enviarEmail(sendEmail);
			map.put("msgSucess", msgSucess);
			System.out.println("Email enviado com sucesso para "+ sendEmail.getDestinatario());
			return map;
		} catch (Exception e) {
			String msgError = "Erro ao enviar o email";
			map.put("msgError", msgError);
			System.out.println("Erro ao enviar o email.");
			e.printStackTrace();
			return map;
		}
	}
	
	@PostMapping
	@RequestMapping("/consumer")
	public ResponseEntity<SendEmail> userConsume(@Valid @RequestBody SendEmail sendEmail, HttpServletResponse response){
		ConsumerEmailService consume = new ConsumerEmailService();
		consume.userConsume(sendEmail);
		return ResponseEntity.status(HttpStatus.OK).body(sendEmail);
	}
	
}
