package com.github.cangoksel.sample.common;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by herdemir on 13.05.2015.
 */
public class TrackingInfo {
    public static final TrackingInfo EMPTY_INFO = new TrackingInfo(
        "", "", LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.systemDefault())
    );

    private final String user;
    private final String supportingUser;
    private final LocalDateTime impersonationTime;

    private boolean read;
    private boolean cikis;

    public TrackingInfo(final String user, final String supportingUser, boolean cikis) {
        this(user, supportingUser, LocalDateTime.now());
        this.read = false;
        this.cikis = cikis;
    }

    private TrackingInfo(String user, String supportingUser, LocalDateTime impersonationTime) {
        this.user = user;
        this.supportingUser = supportingUser;
        this.impersonationTime = impersonationTime;
        this.read = true;
        this.cikis = false;
    }

    public String getUser() {
        return user;
    }

    public String getSupportingUser() {
        return supportingUser;
    }

    public LocalDateTime getImpersonationTime() {
        return impersonationTime;
    }

    public boolean isRead() {
        return read;
    }

    public void markAsRead() {
        read = true;
    }

    public String getMessage() {
        return String.format(
            "%s destek kullanıcısı %s tarihinde sizin yerinize %s yapmıştır.",
            supportingUser,
            impersonationTime,
            cikis ? "çıkış" : "giriş"
        );
    }
}
