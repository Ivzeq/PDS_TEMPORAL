package Model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMail implements IAdapterJavaMail {

    private final String smtpHost;
    private final int smtpPort;
    private final String remitente;
    private final String password;

    public JavaMail(String smtpHost, int smtpPort, String remitente, String password) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.remitente = remitente;
        this.password = password;
    }

    @Override
    public void enviarNotificacion(Notificacion notificacion) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", String.valueOf(smtpPort));

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(notificacion.getDestinatario().getMail()));
            message.setSubject("Notificacion - Uno Mas");
            message.setText(notificacion.getMensaje());

            Transport.send(message);

            System.out.println("[JavaMail] Email enviado a: "
                    + notificacion.getDestinatario().getMail());
        } catch (MessagingException e) {
            System.err.println("[JavaMail] Error al enviar email a "
                    + notificacion.getDestinatario().getMail() + ": " + e.getMessage());
        }
    }
}
