package ru.ancap.framework.plugin.language.module.model;

import lombok.AllArgsConstructor;
import lombok.With;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "speakers")
@AllArgsConstructor
public class SpeakerModel implements Serializable {

    @Id
    @Column(name = "name", nullable = false, length = 21)
    private String name;

    @With
    @Column(name = "language_code")
    private String languageCode;

    public SpeakerModel() {
    }

    public String getName() {
        return name;
    }

    public String getLanguageCode() {
        return languageCode;
    }
}
