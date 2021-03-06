package com.github.cangoksel.model;

import com.github.cangoksel.user.Kullanici;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by tozyurek on 20.07.2016.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfilMobileModel {
    private String kullaniciAdiSoyadi;
    private String eposta;
    private String eskiSifre;
    private String yeniSifre;
    private String yeniSifreTekrar;

    public ProfilMobileModel(Kullanici kullanici){
        this.kullaniciAdiSoyadi = kullanici.getAdiSoyadi();
        this.eposta = kullanici.getEposta();
    }
}
