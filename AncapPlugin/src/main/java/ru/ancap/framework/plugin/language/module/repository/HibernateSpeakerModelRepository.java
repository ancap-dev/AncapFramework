package ru.ancap.framework.plugin.language.module.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.ancap.framework.plugin.language.module.model.SpeakerModel;

import java.util.function.Consumer;

@AllArgsConstructor
public class HibernateSpeakerModelRepository implements SpeakerModelRepository {

    private final SessionFactory factory;

    @Override
    public SpeakerModel get(String name) {
        return factory.openSession().get(SpeakerModel.class, name);
    }

    @Override
    public void write(SpeakerModel model) {
        this.operate(session -> session.saveOrUpdate(model));
    }

    private void operate(Consumer<Session> consumer) {
        Session session = this.factory.openSession();
        Transaction transaction = session.beginTransaction();
        consumer.accept(session);
        transaction.commit();
        session.close();
    }
}
