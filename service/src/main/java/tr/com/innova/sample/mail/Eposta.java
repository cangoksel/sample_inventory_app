package tr.com.innova.sample.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
