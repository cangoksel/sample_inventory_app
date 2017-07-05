package tr.com.innova.sample.mail;

/**
 * Created by gcan on 30.03.2017.
 */
public interface EPostaService {

    public void epostaIleGitmesiGerekenHatirlatmalariGonder();
    public void epostaGonder(Eposta eposta);

    public void epostaGondermeErrorHandle(Exception ex);
}
