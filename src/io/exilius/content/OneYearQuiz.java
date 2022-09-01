package io.exilius.content;

import io.exilius.Configuration;
import io.exilius.model.entity.player.PlayerHandler;

public class OneYearQuiz {
	
	public static void configureEvent(String config) {
		switch (config) {
		case "start":
            break;
			
		case "end":
            Configuration.QUESTION = "";
			Configuration.ANSWER = "";
			break;
		}
	}
	
	public static void setQA(String q, String a) {
		PlayerHandler.executeGlobalMessage("[@red@Quiz@bla@] " + q);
		PlayerHandler.executeGlobalMessage("[@red@Quiz@bla@] Answer by using ::answer (your answer)");
		Configuration.QUESTION = q;
		Configuration.ANSWER = a;
	}

}
