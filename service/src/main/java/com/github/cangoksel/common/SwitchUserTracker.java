package com.github.cangoksel.common;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by herdemir on 14.04.2015.
 */
@Component
public class SwitchUserTracker {
    private Map<String, TrackingInfo> map = Maps.newConcurrentMap();

    public void beginTrackingFor(String user, String supportingUser) {
        map.put(user, new TrackingInfo(user, supportingUser, false));
    }

    public void endTrackingFor(String user, String supportingUser) {
        map.put(user, new TrackingInfo(user, supportingUser, true));
    }

    public TrackingInfo getTrackingInfoFor(String user) {
        return map.getOrDefault(user, TrackingInfo.EMPTY_INFO);
    }
}
