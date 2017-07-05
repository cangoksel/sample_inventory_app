package com.github.cangoksel.sample.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by gcan on 30.03.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Eposta {
    UUID id;
    String to;
    String subject;
    String text;
}
