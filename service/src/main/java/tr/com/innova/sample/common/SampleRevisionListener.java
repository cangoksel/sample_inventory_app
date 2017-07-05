package tr.com.innova.sample.common;

import org.hibernate.envers.RevisionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;
import org.springframework.util.Assert;
import tr.com.innova.sample.user.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

/**
 * Created by herdemir on 09.04.2015.
 */
public class SampleRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        final SampleRevisionEntityImpl revEntity = ((SampleRevisionEntityImpl) revisionEntity);
        final RevisionUser revisionUser = RevisionUser.createRevisionUser();

        revEntity.setUsername(revisionUser.getUsername());
        if (revisionUser.hasSupportingUsername()) {
            revEntity.setSupportingUsername(revisionUser.getSupportingUsername());
        }

        final LocalDateTime revisionDate = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(revEntity.getTimestamp()),
            ZoneId.systemDefault()
        );

        revEntity.setRevisionDate(revisionDate);
    }

    private static class RevisionUser {
        private static final Logger LOG = LoggerFactory.getLogger(RevisionUser.class);

        private String username;
        private String supportingUsername;

        public static RevisionUser createRevisionUser() {
            return new RevisionUser();
        }

        private RevisionUser() {
            initialize();
        }

        private void initialize() {
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                username = "@anonymous";
                LOG.warn("anonymous database access (Camel Job?)");
                return;
            }

            initializeUsernameWith(authentication);
            initializeSupportingUsernameWith(authentication);
        }

        private void initializeUsernameWith(Authentication authentication) {
            Assert.notNull(authentication);

            if (authentication.getPrincipal() instanceof User) {
                username = ((User) authentication.getPrincipal()).getUsername();
            } else {
                username = (String) authentication.getPrincipal();
            }
        }

        private void initializeSupportingUsernameWith(Authentication authentication) {
            Assert.notNull(authentication);

            final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            supportingUsername = authorities.stream()
                                            .filter(a -> a instanceof SwitchUserGrantedAuthority)
                                            .findAny()
                                            .map(a -> (SwitchUserGrantedAuthority) a)
                                            .map(a -> ((User) a.getSource().getPrincipal()).getUsername())
                                            .orElse(null);
        }

        public RevisionUser(String username, String supportingUsername) {
            this.username = username;
            this.supportingUsername = supportingUsername;
        }

        public String getUsername() {
            return username;
        }

        public boolean hasSupportingUsername() {
            return supportingUsername != null;
        }

        public String getSupportingUsername() {
            return supportingUsername;
        }
    }
}
