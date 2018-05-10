package br.com.send.email.api.model;

public class SendEmail {

	private String remetente;
	private String destinatario;
	private String assunto;
	private String conteudoDaMensagem;

	/**
	public SendEmail(String remetente, String destinatario, String assunto, String conteudoDaMensagen) {
		this.remetente = remetente;
		this.destinatario = destinatario;
		this.assunto = assunto;
		this.conteudoDaMensagen = conteudoDaMensagen;
	}
	**/

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getConteudoDaMensagem() {
		return conteudoDaMensagem;
	}

	public void setConteudoDaMensagem(String conteudoDaMensagen) {
		this.conteudoDaMensagem = conteudoDaMensagen;
	}
	
}
