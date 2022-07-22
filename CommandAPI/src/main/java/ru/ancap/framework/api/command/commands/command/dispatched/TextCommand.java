package ru.ancap.framework.api.command.commands.command.dispatched;

import lombok.*;
import ru.ancap.framework.api.command.commands.command.dispatched.exception.NoNextArgumentException;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TextCommand implements LeveledCommand {

    @With private final List<String> args;
    @With private final TextCommand fullCommand;
    private final TextCommand originalCommand;

    public TextCommand(List<String> args) {
        this.args = List.copyOf(args);
        this.originalCommand = this;
        this.fullCommand = this;
    }

    @Override
    public TextCommand withoutArgument() {
        if (this.isRaw()) throw new NoNextArgumentException();
        return this.withArgs(this.getArgs().subList(1, this.getArgs().size()));
    }

    @Override
    public String nextArgument() throws NoNextArgumentException {
        try {
            return this.getArgs().get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new NoNextArgumentException();
        }
    }

    public boolean isRaw() {
        return this.args.isEmpty();
    }

    public String getFlattenedArgs() {
        return String.join(" ", this.args);
    }

    @Override
    public String toString() {
        return "TextCommand{" +
                "args=" + args +
                "original_args=" + originalCommand.getArgs() +
                "full_args=" + fullCommand.getArgs() +
                '}';
    }
}
