package ru.ancap.framework.iterator.world;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import ru.ancap.lib.scalar.containers.Axis;
import ru.ancap.lib.scalar.containers.ContainerIterator;
import ru.ancap.lib.scalar.containers.DiscretePositionContainer;
import ru.ancap.lib.scalar.containers.DiscreteRange;

import java.util.Iterator;

/**
 * It's an iterator that iterates over all chunks in a rectangular area
 */
@AllArgsConstructor
@ToString @EqualsAndHashCode
public class WorldIterator implements Iterable<Chunk> {

    private final World world;
    private final Chunk leftDownCorner;
    private final Chunk rightUpCorner;

    /**
     * It returns an iterator that iterates through all the chunks in the area
     *
     * @return Iterator of Chunk
     */
    @NonNull
    @Override
    public Iterator<Chunk> iterator() {
        return new ContainerIterator<>(
                DiscretePositionContainer
                        .builder()
                        .add(DiscreteRange.of(Axis.X, this.leftDownCorner.getX(), this.rightUpCorner.getX()))
                        .add(DiscreteRange.of(Axis.Y, this.rightUpCorner.getZ(), this.leftDownCorner.getZ()))
                        .build(),
                discretePosition -> {
                    Chunk chunk = this.world.getChunkAt((int) discretePosition.coordinate(Axis.X), (int) discretePosition.coordinate(Axis.Y));
                    chunk.load(true);
                    return chunk;
                }
        ).iterator();
    }

    public void on() {
        World world = Bukkit.getWorld("world");
        new WorldIterator(
                world,
                world.getChunkAt(-100, -100),
                world.getChunkAt(100, 100)
        ).iterator().forEachRemaining(chunk -> {
            // doing something with every chunk
        });
    }
}
