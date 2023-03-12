package io.shadowrealm.model.cycleevent.impl;

import io.shadowrealm.model.cycleevent.Event;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.Health;
import io.shadowrealm.model.entity.HealthStatus;

public class PoisonResistanceEvent extends Event<Entity> {

	public PoisonResistanceEvent(Entity attachment, int ticks) {
		super("poison_resistance_event", attachment, ticks);
	}

	@Override
	public void execute() {
		if (plr == null) {
			super.stop();
			return;
		}
		super.stop();

		Health health = plr.getHealth();
		health.removeNonsusceptible(HealthStatus.POISON);
	}

}
