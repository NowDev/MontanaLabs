package com.nowbie.bot;

import java.io.File;
import java.io.IOException;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

//this is the Main class, which will create new instance of the bot
public class Main {

	public static String version = "1.8.3";
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {

		System.out.println("[!] Starting MontanaHelper...");
		
		//create new bot
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		
		//init bot (we try to create new bot, and throw exception if creation failed)
		try {
			
			try {
				CreateDir();
			}
			catch(Exception e) {
				System.out.println("[?] Tried to create data folder.");
			}
			if(BotInfo.BotUsername == "@nowbie_bot") {
				System.out.println("[!] Running on test mode!");
				}
			
			telegramBotsApi.registerBot(new Bot());
			System.out.println("\n[=======================================]");
			System.out.println("[         MontanaHelper is Online!      ]");
			System.out.println("[             by t.me/nowbie            ]");
			System.out.println("[                 v"+version+"                ]");
			System.out.println("[=======================================]\n");
			
		} 
		catch (TelegramApiException e) {
			e.printStackTrace();
		}
		
	}
	
    public static void CreateDir(){
    	
    	try {
        String d = getProgramPath() + "/users/";
        System.out.println("[!] Generating files...");
        File dir = new File(d); //The name of the directory to create                                                                                      
        dir.mkdir(); //Creates the directory                                                                                                               
        }
    	
        catch (Exception e) { System.out.println("Exception occured:\n" + e); }
    	
    }
    public static String getProgramPath() throws IOException {
    String currentdir = System.getProperty("user.dir");
    currentdir = currentdir.replace( "\\", "/" );
    return currentdir;
    }
    
	
}