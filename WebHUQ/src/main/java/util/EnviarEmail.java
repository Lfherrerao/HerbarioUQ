/**
 * 
 */
package util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import co.edu.analisis2.co.entidades.Persona;

/**
 * @author LENOVO
 *
 */
public class EnviarEmail {
	
	/**
	 * Usuario del correo electronico del cual se envian los correos
	 */
	private static String usuario = "leo_9494@hotmail.com";

	/**
	 * Contrasenia del correo electronico del cual se envian los corres
	 */
	private static String password = "44caramelos";

	/**
	 * Direccion a la cual se envia el correo de
	 */
	private String para;

	/**
	 * Asunto del mensaje de correo electronico
	 */
	private String asunto;

	/**
	 * Mensaje que se envia al usuario
	 */
	private String mensaje;

	/**
	 * Metodo constructos de la clase EnviarEmail
	 */
	public EnviarEmail() {

	}

	/**
	 * Permite enviar un correo electronico al usuario para recordar su contrasenia
	 */
	public boolean enviarEmailolvideContrasenia(Persona usuarioEmail) {
		
		para = usuarioEmail.getCorreo();
		asunto = "OLVIDE CONTRASEÑA DE USUARIO EN MI HERBARIO";
		mensaje = "Su contraseña de acceso al sistemas es: " + usuarioEmail.getContrasenia();
		
		Properties props = new Properties();

		props = System.getProperties();
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usuario, password);
			}
		});
		
		session.setDebug(true);
		
		System.out.println("PASO DEBUG");
		
		Message message = new MimeMessage(session);
		
		
		try {
			
			message.setFrom(new InternetAddress(usuario));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
			message.setSubject(asunto);
			message.setText(mensaje);
			Transport.send(message);
			return true;
			
		} catch (MessagingException e) {
			System.out.println("Error de email: " + e.getMessage());
			e.printStackTrace();
			//throw new RuntimeException(e);
		}
		return false;	

	}

	/**
	 * @return the para
	 */
	public String getPara() {
		return para;
	}

	/**
	 * @param para the para to set
	 */
	public void setPara(String para) {
		this.para = para;
	}

	/**
	 * @return the asunto
	 */
	public String getAsunto() {
		return asunto;
	}

	/**
	 * @param asunto the asunto to set
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


}
