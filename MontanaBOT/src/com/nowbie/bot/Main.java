package com.nowbie.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

//this is the Main class, which will create new instance of the bot
public class Main {

	public static void main(String[] args) throws Exception {

		System.out.println("[!] Starting MontanaHelper...\n");
		
		//create new bot
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		
		//init bot (we try to create new bot, and throw exception if creation failed)
		try {
			telegramBotsApi.registerBot(new Bot());
			System.out.println("[=======================================]");
			System.out.println("[         MontanaHelper is Online!      ]");
			System.out.println("[             by t.me/nowbie            ]");
			System.out.println("[                 V1.0.0                ]");
			System.out.println("[=======================================]\n");
		} 
		catch (TelegramApiException e) {
			e.printStackTrace();
		}
		
	}
	
}