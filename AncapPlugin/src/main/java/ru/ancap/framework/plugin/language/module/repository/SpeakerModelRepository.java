package ru.ancap.framework.plugin.language.module.repository;

import ru.ancap.framework.plugin.language.module.model.SpeakerModel;

public interface SpeakerModelRepository {

    SpeakerModel get(String name);
    void write(SpeakerModel model);

}
