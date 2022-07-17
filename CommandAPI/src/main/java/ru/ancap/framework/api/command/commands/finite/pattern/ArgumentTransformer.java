package ru.ancap.framework.api.command.commands.finite.pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import ru.ancap.framework.api.command.commands.command.dispatched.LeveledCommand;
import ru.ancap.framework.api.command.commands.command.dispatched.exception.NoNextArgumentException;
import ru.ancap.framework.api.event.classic.DescribedIncorrectArgsEvent;
import ru.ancap.framework.api.event.classic.IncorrectArgsEvent;
import ru.ancap.framework.api.event.classic.NotEnoughArgsEvent;


public class ArgumentTransformer<T> implements CommandEventPattern {

    private final TransformedEventProvider<T> eventSource;
    private final Transformer<T> transformer;

    public ArgumentTransformer(TransformedEventProvider<T> provider, Transformer<T> transformer) {
        this.eventSource = provider;
        this.transformer = transformer;
    }

    public ArgumentTransformer(Transformer<T> transformer, TransformedEventProvider<T> provider) {
        this(provider, transformer);
    }

    @Override
    public Event patternalize(CommandSender sender, LeveledCommand command) {
        String argument = null;
        try {
            argument = command.nextArgument();
            return this.eventSource.event(sender, this.transformer.transform(argument));
        } catch (NoNextArgumentException e) {
            return new NotEnoughArgsEvent(sender, 1);
        } catch (DescribedTransformationException e) {
            return new DescribedIncorrectArgsEvent(sender, e.description);
        } catch (TransformationException e) {
            return new IncorrectArgsEvent(sender, argument);
        }
    }

    @FunctionalInterface
    public interface TransformedEventProvider<T> {
        Event event(CommandSender sender, T argument);
    }

    @FunctionalInterface
    public interface Transformer<T> {
        T transform(String string) throws TransformationException;
    }

    public static class TransformationException extends Exception {

    }

    @Getter
    @AllArgsConstructor
    public static class DescribedTransformationException extends TransformationException {

        String description;

    }
}
