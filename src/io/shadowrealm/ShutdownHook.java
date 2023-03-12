package io.shadowrealm;

import io.shadowrealm.content.combat.death.kill_limiter.KillLimitHandler;
import io.shadowrealm.content.dailytasks.DailyTaskHandler;
import io.shadowrealm.content.wogw.Wogw;

/**
 * A thread which will be started when the server is being shut down. Although in most cases the Thread will be started, it cannot be guaranteed.
 * 
 * @author Emiel
 *
 */
public class ShutdownHook extends Thread {

	public ShutdownHook() {
		setName("shutdown-hook");
	}

	public void run() {
		System.out.println("Successfully executed ShutdownHook");
		Wogw.save();
	}
}
