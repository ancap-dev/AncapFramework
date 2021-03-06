package ru.ancap.framework.api.world.iterator;

import lombok.AllArgsConstructor;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import ru.ancap.lib.scalar.containers.*;

import java.util.Iterator;

/**
 * It's an iterator that iterates over all chunks in a rectangular area
 */
@AllArgsConstructor
public class WorldIterator implements Iterable<Chunk> {

    private final World world;
    private final Chunk leftDownCorner;
    private final Chunk rightUpCorner;

    /**
     * It returns an iterator that iterates through all the chunks in the area
     *
     * @return Iterator of Chunk
     */
    @NotNull
    @Override
    public Iterator<Chunk> iterator() {
        return new ContainerIterator<>(
                DiscretePositionContainer
                        .builder()
                        .add(DiscreteRange.of(Axis.X, leftDownCorner.getX(), rightUpCorner.getX()))
                        .add(DiscreteRange.of(Axis.Y, rightUpCorner.getZ(), leftDownCorner.getZ()))
                        .build(),
                new DiscretePositionConsumer<Chunk>() {
                    @Override
                    public Chunk forThe(DiscretePosition discretePosition) {
                        Chunk chunk = world.getChunkAt((int) discretePosition.coordinate(Axis.X), (int) discretePosition.coordinate(Axis.Y));
                        chunk.load(true);
                        return chunk;
                    }
                }
        ).iterator();
    }
}
