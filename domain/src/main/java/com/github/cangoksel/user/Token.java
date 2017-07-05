package com.github.cangoksel.user;

import com.github.cangoksel.common.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by herdemir on 20.11.2015.
 */
@Getter
@Entity
@Table(name = "TOKEN")
@NoArgsConstructor
@AllArgsConstructor
public class Token extends AbstractEntity {
    public static final int EXPIRATION_TIME = 12; //12 saat

    @Column(name = "VALUE")
    private UUID value;

    @Column(name = "EXPIRY_DATE")
    private LocalDateTime expiryDate;

    public Token(final UUID kullaniciId) {
        this.id = kullaniciId;
        this.value = UUID.randomUUID();
    }

    public Token(final KullaniciInfo kullanici) {
        this(kullanici.getId());
        expiryDate = LocalDateTime.now();
    }

    public UUID getKullaniciId() {
        return getId();
    }

    public void generateNewTokenValue() {
        value = UUID.randomUUID();
    }
}
