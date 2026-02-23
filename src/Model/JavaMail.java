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
        if (remitente == null || remitente.isEmpty()) {
            System.err.println("[JavaMail] No se puede enviar email: remitente no configurado.");
            return;
        }

        String destinatarioMail = notificacion.getDestinatario().getMail();
        String mensajeTexto = notificacion.getMensaje();

        Thread emailThread = new Thread(() -> {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", String.valueOf(smtpPort));
            props.put("mail.smtp.socketFactory.port", String.valueOf(smtpPort));
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");

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
                        InternetAddress.parse(destinatarioMail));
                message.setSubject("Notificacion - Uno Mas");
                message.setText(mensajeTexto);

                Transport.send(message, remitente, password);

                System.out.println("[JavaMail] Email enviado a: " + destinatarioMail);
            } catch (MessagingException e) {
                System.err.println("[JavaMail] Error al enviar email a "
                        + destinatarioMail + ": " + e.getMessage());
            }
        });

        emailThread.setDaemon(true);
        emailThread.start();

        try {
            emailThread.join(20000);
            if (emailThread.isAlive()) {
                System.out.println("[JavaMail] Envio a " + destinatarioMail + " continua en segundo plano...");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
