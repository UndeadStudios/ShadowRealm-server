package io.exilius.model.cycleevent.impl;

import io.exilius.model.cycleevent.Event;
import io.exilius.model.entity.Entity;
import io.exilius.model.entity.Health;
import io.exilius.model.entity.HealthStatus;

public class VenomResistanceEvent extends Event<Entity> {

	public VenomResistanceEvent(Entity attachment, int ticks) {
		super("venom_resistance_event", attachment, ticks);
	}

	@Override
	public void execute() {
		super.stop();
		if (plr == null) {
			return;
		}
		Health health = plr.getHealth();
		health.removeNonsusceptible(HealthStatus.VENOM);
	}

}
