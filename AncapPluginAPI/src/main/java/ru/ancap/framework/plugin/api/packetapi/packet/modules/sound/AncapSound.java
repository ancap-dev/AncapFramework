package ru.ancap.framework.plugin.api.packetapi.packet.modules.sound;

import ru.ancap.framework.plugin.api.packetapi.receiver.PacketReceiver;

public class AncapSound implements Sound {

    private String sound;
    private float volume;
    private float pitch;

    public AncapSound(String sound, float volume, float pitch) {
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
    }

    public AncapSound(AncapSound sound) {
        this(sound.getSound(), sound.getVolume(), sound.getPitch());
    }

    protected String getSound() {
        return this.sound;
    }

    protected float getVolume() {
        return this.volume;
    }

    protected float getPitch() {
        return this.pitch;
    }

    @Override
    public void sendTo(PacketReceiver receiveable) {
        receiveable.sendSound(this.getSound(), this.getVolume(), this.getPitch());
    }
}
