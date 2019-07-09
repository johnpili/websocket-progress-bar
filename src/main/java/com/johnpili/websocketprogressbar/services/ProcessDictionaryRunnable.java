package com.johnpili.websocketprogressbar.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.*;

public class ProcessDictionaryRunnable implements Runnable
{
	private static final Logger logger = LoggerFactory.getLogger(ProcessDictionaryRunnable.class);

	public static String BROADCAST_WS_PROGRESS_UPDATE_WITH_DETAILS = "/ws-broadcast/update-progress-with-details";

	BroadcastWebsocketService broadcastWebsocketService;
	ObjectMapper objectMapper;
	MessageDigest messageDigest;
	String[] dictionary = { "revise", "critical", "rock", "facility", "fist", "owe", "acquisition", "mountain", "literacy", "thank",
			"perceive", "survivor", "flight", "press", "mutation", "investigation", "blast", "crisis", "crosswalk", "mutual",
			"bean", "invisible", "class", "evening", "man", "ideology", "due", "glory", "slice", "continuation", "fast", "crime",
			"damage", "bird", "cunning", "foreigner", "merit", "battery", "exhibition", "army", "summit", "relieve", "accent", "legend",
			"elect", "prison", "deficit", "Sunday", "wealth", "break", "error", "familiar", "roll", "miner", "building", "consumer", "photography",
			"break down", "withdraw", "dinner", "graze", "unlawful", "ministry", "consideration", "current", "reflection", "shame", "cancel",
			"democratic", "partner", "confusion", "aviation", "kid", "ally", "abstract", "tear", "aunt", "script", "related", "crash", "volcano", "helmet",
			"Europe", "passage", "school", "responsibility", "depressed", "sweep", "liver", "agenda", "nuclear", "background", "breed", "policeman",
			"regret", "father", "selection", "waste", "realism", "monstrous", "greet", "herb", "divide", "personal", "legislature", "white", "barrier",
			"layout", "work", "defendant", "loan", "swell", "index", "install", "percent", "discriminate", "colorful", "clear", "print", "gift", "deter",
			"axis", "valid", "sip", "suspicion", "north", "folk music", "profession", "sail", "feminist", "career", "drive", "acid", "lock", "freckle",
			"tooth", "provoke", "complex", "inhibition", "belong", "card", "god", "seize", "bush", "minority", "runner", "wrap", "drill", "smash", "rest",
			"excess", "squeeze", "inhabitant", "fix", "element", "disorder", "drown", "occupation", "indirect", "tiger", "shorts", "charity", "weakness",
			"review", "difference", "nail", "hear", "maid", "care", "creed", "adjust", "stroll", "reveal", "passive", "revoke", "justice", "move", "license",
			"flower", "slave", "slide", "oil", "theater", "manufacture", "voyage", "fraud", "theme", "lean", "pat", "expect", "mobile", "prescription", "pasture",
			"cassette", "feeling", "hobby", "bomb", "concentration", "carve", "jelly", "steep", "past", "astonishing", "manual", "horseshoe", "pack", "south",
			"term", "disappoint", "emphasis", "wriggle", "result", "word", "market", "agent", "suffering", "ceiling", "prediction", "track", "weed", "quota",
			"trend", "rabbit", "circulate", "achieve", "decay", "trust", "eyebrow", "kitchen", "chip", "scandal", "linger", "disgrace", "dairy", "fisherman",
			"cotton", "childish", "contrast", "pour", "manager", "insure"};

	public ProcessDictionaryRunnable(BroadcastWebsocketService broadcastWebsocketService, ObjectMapper objectMapper)
	{
		try
		{
			this.broadcastWebsocketService = broadcastWebsocketService;
			this.objectMapper = objectMapper;
			this.messageDigest = MessageDigest.getInstance("MD5");
		}
		catch(Exception exception)
		{
			logger.error(exception.getMessage());
		}
	}

	@Override
	public void run()
	{
		if(messageDigest != null)
		{
			List<String> words = Arrays.asList(this.dictionary);
			Collections.shuffle(words);

			for (int i = 0; i < words.size(); ++i)
			{
				try
				{
					Map<String, Object> payload = new HashMap<>();
					payload.put("word", words.get(i));
					payload.put("md5", this.messageDigest.digest(words.get(i).getBytes()));
					payload.put("current", i);
					payload.put("max", words.size());

					this.broadcastWebsocketService.broadcastProgressUpdate(BROADCAST_WS_PROGRESS_UPDATE_WITH_DETAILS, this.objectMapper.writeValueAsString(payload));
					if (i >= dictionary.length)
					{
						Thread.currentThread().interrupt();
						break;
					}
					Thread.sleep(100);
				}
				catch (Exception exception)
				{
					logger.error(exception.getMessage());
				}
			}
		}
	}
}
