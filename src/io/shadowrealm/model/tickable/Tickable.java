package io.shadowrealm.model.tickable;

public interface Tickable<T> {

    void tick(TickableContainer<T> container, T t);

}
