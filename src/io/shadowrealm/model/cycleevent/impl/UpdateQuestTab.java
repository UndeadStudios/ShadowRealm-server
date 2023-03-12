package io.shadowrealm.model.cycleevent.impl;

import java.util.concurrent.TimeUnit;

import io.shadowrealm.model.cycleevent.Event;
import io.shadowrealm.model.entity.player.PlayerHandler;
import io.shadowrealm.util.Misc;

public class UpdateQuestTab extends Event<Object> {


	private static final int INTERVAL = Misc.toCycles(5, TimeUnit.SECONDS);

	
	public UpdateQuestTab() {
		super("", new Object(), INTERVAL);
	}	

	@Override
	public void execute() {
		PlayerHandler.nonNullStream().forEach(player -> { 
			player.getQuestTab().updateInformationTab();
		});
	}
} 