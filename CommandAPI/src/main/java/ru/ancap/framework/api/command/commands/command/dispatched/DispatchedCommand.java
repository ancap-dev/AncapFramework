package ru.ancap.framework.api.command.commands.command.dispatched;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import org.bukkit.command.CommandSender;
import ru.ancap.framework.api.command.commands.command.dispatched.exception.NoNextArgumentException;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DispatchedCommand {

    @With private final CommandSender sender;
    @With private final List<String> args;
    @With private final DispatchedCommand fullCommand;
    private final DispatchedCommand originalCommand;

    public DispatchedCommand(CommandSender sender, List<String> args) {
        this.sender = sender;
        this.args = List.copyOf(args);
        this.originalCommand = this;
        this.fullCommand = this;
    }

    public DispatchedCommand withoutArgument() {
        return this.withArgs(this.getArgs().subList(1, this.getArgs().size()-1));
    }

    public String nextArgument() throws NoNextArgumentException {
        try {
            return this.getArgs().get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new NoNextArgumentException();
        }
    }

    public boolean isRaw() {
        return this.args.size() == 0;
    }

    public String getFlattenedArgs() {
        return String.join(" ", this.args);
    }
}
