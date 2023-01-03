package io.exilius;

import io.exilius.content.combat.death.kill_limiter.KillLimitHandler;
import io.exilius.content.dailytasks.DailyTaskHandler;
import io.exilius.content.wogw.Wogw;

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
