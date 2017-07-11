package com.github.cangoksel.mail;

/**
 * Created by usuicmez on 13.06.2016.
 */
public class EpostaGondermeException extends RuntimeException {
    private final Eposta eposta;

    public EpostaGondermeException(Eposta eposta, String message, Throwable cause) {
        super(message, cause);
        this.eposta = eposta;
    }

    public Eposta getEposta() {
        return eposta;
    }
}
