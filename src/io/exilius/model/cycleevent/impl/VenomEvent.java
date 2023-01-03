package io.exilius.model.cycleevent.impl;

import java.util.Optional;

import io.exilius.content.combat.Hitmark;
import io.exilius.model.cycleevent.Event;
import io.exilius.model.entity.Entity;
import io.exilius.model.entity.Health;
import io.exilius.model.entity.HealthStatus;

public class VenomEvent extends Event<Entity> {

	private int damage;

	private final Optional<Entity> inflictor;

	public VenomEvent(Entity attachment, int damage, Optional<Entity> inflictor) {
		super("health_status", attachment, 33);
		this.damage = damage;
		this.inflictor = inflictor;
	}

	@Override
	public void execute() {
		if (plr == null) {
			super.stop();
			return;
		}

		Health health = plr.getHealth();

		if (health.isNotSusceptibleTo(HealthStatus.VENOM)) {
			super.stop();
			return;
		}

		if (plr.getHealth().getCurrentHealth() <= 0) {
			super.stop();
			return;
		}

		plr.appendDamage(null, damage, Hitmark.VENOM);
		inflictor.ifPresent(inf -> plr.addDamageTaken(inf, damage));

		damage += 2;

		if (damage > 20) {
			damage = 20;
		}
	}

}
