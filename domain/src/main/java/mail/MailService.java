package mail;

/**
 * Created by herdemir on 30.06.2015.
 */
public interface MailService {
    void sendMail(String to, String subject, String text);
}
