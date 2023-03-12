package io.shadowrealm.model.cycleevent.impl;

import io.shadowrealm.model.cycleevent.Event;
import io.shadowrealm.model.entity.Entity;
import io.shadowrealm.model.entity.Health;
import io.shadowrealm.model.entity.HealthStatus;

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
