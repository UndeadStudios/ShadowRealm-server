package io.exilius.model.cycleevent.impl;

import java.util.Optional;

import io.exilius.content.combat.Hitmark;
import io.exilius.model.cycleevent.Event;
import io.exilius.model.entity.Entity;
import io.exilius.model.entity.Health;
import io.exilius.model.entity.HealthStatus;

public class PoisonEvent extends Event<Entity> {

	private int damage;

	private int hits;

	private final Optional<Entity> inflictor;

	public PoisonEvent(Entity attachment, int damage, Optional<Entity> inflictor) {
		super("health_status", attachment, 100);
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

		if (health.isNotSusceptibleTo(HealthStatus.POISON)) {
			super.stop();
			return;
		}

		if (plr.getHealth().getCurrentHealth() <= 0) {
			super.stop();
			return;
		}

		plr.appendDamage(null, damage, Hitmark.POISON);
		inflictor.ifPresent(inf -> plr.addDamageTaken(inf, damage));

		hits++;

		if (hits >= 4) {
			damage--;
			hits = 0;
		}

		if (damage <= 0) {
			super.stop();
			return;
		}
	}

}
