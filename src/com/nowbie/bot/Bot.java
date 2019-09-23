package com.nowbie.bot;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.toIntExact;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.groupadministration.KickChatMember;
import org.telegram.telegrambots.meta.api.methods.groupadministration.UnbanChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.ChatMember;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.apache.commons.net.*;
import org.json.*;

@SuppressWarnings("unused")

public class Bot extends TelegramLongPollingBot {	
	
	@Override
	/*
	/ Se quiser um token, fale com o bot father (https://t.me/BotFather)
	/ Simplesmente coloque: return "SEU_BOT_TOKEN";
	/ Nesse meu caso, fiz uma classe para retornar meu token, assim consigo ignorar
	/ a classe com o token e não deixar ser mandado para o github.
	*/
	public String getBotToken() { return BotInfo.BotToken; }
	// O comentário anterior serve para esta linha também, coloque aqui o username do seu bot.
	public String getBotUsername() { return BotInfo.BotUsername; }
	
	@Override
	/*
	/ esta função é acionada toda vez que o bot recebe uma nova mensagem
	/ mais infos aqui: [https://core.telegram.org/bots/api#update ]
	*/
	public void onUpdateReceived(Update update) {
		
	if (update.hasCallbackQuery()) {
		
		String call_data = update.getCallbackQuery().getData();
              
        long chat_id = update.getCallbackQuery().getMessage().getChatId();
        long message_id = update.getCallbackQuery().getMessage().getMessageId();
        String username = update.getCallbackQuery().getFrom().getFirstName();
        Integer user_id = update.getCallbackQuery().getFrom().getId();
        int msg_id = toIntExact(message_id);
            
            
            if (call_data.equals("home")) {
            	
            	String help_string = "📁 Menu - Some things this BOT can do!";

            	EditMessageText helpquery = new EditMessageText()
            			.setChatId(chat_id)
            			.setText(help_string)
            			.setMessageId(msg_id);
            	
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>(); 
                List<InlineKeyboardButton> rowInline2 = new ArrayList<>(); 
                rowInline.add(new InlineKeyboardButton().setText("ROMs 🔥").setCallbackData("roms"));
                rowInline.add(new InlineKeyboardButton().setText("Magisk 📱").setCallbackData("magisk"));
                rowInline.add(new InlineKeyboardButton().setText("TWRP 🔧").setCallbackData("twrp"));
                rowInline2.add(new InlineKeyboardButton().setText("Misc 🤔").setCallbackData("misccall"));
                rowInline2.add(new InlineKeyboardButton().setText("Level 📘").setCallbackData("lvl"));
                rowInline2.add(new InlineKeyboardButton().setText("BotSource 💻").setCallbackData("source"));
                
                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                rowsInline.add(rowInline2);
                // Add it to the message
                markupInline.setKeyboard(rowsInline);
                helpquery.setReplyMarkup(markupInline);
		
        		//try to send
        		try {
        			execute(helpquery);
        		}
        		catch (TelegramApiException e) {
        			e.printStackTrace();
        		}
                 
            }
            if (call_data.equals("misccall")) {
            	String answer = "Here is some cool features... 👍";
            	EditMessageText helpquery = new EditMessageText()
                        .setChatId(chat_id)
                        .setText(answer)
                        .setMessageId(msg_id);
                 InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                 List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                 List<InlineKeyboardButton> rowInline = new ArrayList<>(); 
                 rowInline.add(new InlineKeyboardButton().setText("[Back]").setCallbackData("home"));
                 rowInline.add(new InlineKeyboardButton().setText("Whois 🤔").setCallbackData("whoiscall"));
                 rowInline.add(new InlineKeyboardButton().setText("Wikipedia 📖").setCallbackData("wikicall"));
                  
                 // Set the keyboard to the markup
                 rowsInline.add(rowInline);
                 markupInline.setKeyboard(rowsInline);
                 helpquery.setReplyMarkup(markupInline);
                 helpquery.setMessageId(msg_id);
                 
                 try {
                	 execute(helpquery);
                 }
                 catch (TelegramApiException e) {
                	 e.printStackTrace();
                 }
            }
            if (call_data.equals("twrp")) {
            	String answer = "Sorry, TWRP Download isn't done yet.";
            	EditMessageText helpquery = new EditMessageText()
                        .setChatId(chat_id)
                        .setText(answer)
                        .setMessageId(msg_id);
                 InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                 List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                 List<InlineKeyboardButton> rowInline = new ArrayList<>(); 
                 rowInline.add(new InlineKeyboardButton().setText("[Back]").setCallbackData("home"));
                 
                 // Set the keyboard to the markup
                 rowsInline.add(rowInline);
                 markupInline.setKeyboard(rowsInline);
                 helpquery.setReplyMarkup(markupInline);
                 helpquery.setMessageId(msg_id);
                 
                 try {
                	 execute(helpquery);
                 }
                 catch (TelegramApiException e) {
                	 e.printStackTrace();
                 }
            }
            if (call_data.equals("magisk")) {
            	String answer = "Sorry, Magisk Download isn't done yet.";
            	EditMessageText helpquery = new EditMessageText()
                        .setChatId(chat_id)
                        .setText(answer)
                        .setMessageId(msg_id);
                 InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                 List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                 List<InlineKeyboardButton> rowInline = new ArrayList<>(); 
                 rowInline.add(new InlineKeyboardButton().setText("[Back]").setCallbackData("home"));
                 
                 // Set the keyboard to the markup
                 rowsInline.add(rowInline);
                 markupInline.setKeyboard(rowsInline);
                 helpquery.setReplyMarkup(markupInline);
                 helpquery.setMessageId(msg_id);
                 
                 try {
                	 execute(helpquery);
                 }
                 catch (TelegramApiException e) {
                	 e.printStackTrace();
                 }
            }
            if (call_data.equals("lvl")) {    	
                		 
				
				EditMessageText helpquery = new EditMessageText()
                       .setChatId(chat_id)
                       .setMessageId(msg_id);
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>(); 
                rowInline.add(new InlineKeyboardButton().setText("🔹 Back 🔹").setCallbackData("home"));
                 
                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                markupInline.setKeyboard(rowsInline);
                helpquery.setReplyMarkup(markupInline);
                helpquery.setMessageId(msg_id);
				
				try {
	                       
				String content = user_id.toString();
				String currentdir = System.getProperty("user.dir");
				currentdir = currentdir.replace( "\\", "/" );
				currentdir = currentdir + "/users/";
				currentdir = currentdir + user_id + ".usr";
				
				File file = new File(currentdir);
				
				Stream<String> point = Files.lines(Paths.get(currentdir));
				String points = point.map(Object::toString).collect(Collectors.joining(","));
				int pointint = Integer.parseInt(points);
				String userpoints = Integer.toString(pointint);		
				
				if ( pointint >= 1 ) {
					helpquery.setText("Hello, " + username + "!" + "\nYou have " + userpoints + " points!" + "\nRank: Noob" );
				}
				if ( pointint >= 150 ) {
					helpquery.setText("Hello, " + username + "!" + "\nYou have " + userpoints + " points!" + "\nRank: Rookie" );
					}
				if ( pointint >= 500 ) {
					helpquery.setText("Hello, " + username + "!" + "\nYou have " + userpoints + " points!" + "\nRank: Advanced" );
					}
				if ( pointint >= 1000 ) {
					helpquery.setText("Hello, " + username + "!" + "\nYou have " + userpoints + " points!" + "\nRank: Expert" );
					}
				if ( pointint >= 1500 ) {
					helpquery.setText("Hello, " + username + "!" + "\nYou have " + userpoints + " points!" + "\nRank: PRO" );
					}
				if ( pointint >= 3000 ) {
					helpquery.setText("Hello, " + username + "!" + "\nYou have " + userpoints + " points!" + "\nRank: Elite" );    
					}
				if ( pointint >= 5000 ) {
					helpquery.setText("Hello, " + username + "!" + "\nYou have " + userpoints + " points!" + "\nRank: Master" );
				    }
				if ( pointint >= 10000 ) {
					helpquery.setText("Hello, " + username + "!" + "\nYou have " + userpoints + " points!" + "\nRank: LEGEND" );
				    }
				point.close();
				execute(helpquery);
				}

				catch (TelegramApiException | IOException e) {
                    e.printStackTrace();
                }
				return;
            }
            if (call_data.equals("roms")) {
            	
				String answer = "ROMs Registered on MontanaLabs:";
				
                EditMessageText helpquery = new EditMessageText()
                        .setChatId(chat_id)
                        .setMessageId(msg_id)
                        .setText(answer);
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline2 = new ArrayList<>(); 
                List<InlineKeyboardButton> rowInline3 = new ArrayList<>(); 
                List<InlineKeyboardButton> rowInline4 = new ArrayList<>(); 
                rowInline.add(new InlineKeyboardButton().setText("[Back]").setCallbackData("home"));
                rowInline.add(new InlineKeyboardButton().setText("AICP").setCallbackData("query_aicp"));
                rowInline.add(new InlineKeyboardButton().setText("Arrow").setCallbackData("query_arrow"));
                
                rowInline2.add(new InlineKeyboardButton().setText("Bootleggers").setCallbackData("query_boot"));
                rowInline2.add(new InlineKeyboardButton().setText("Carbon").setCallbackData("query_carbon"));
                rowInline2.add(new InlineKeyboardButton().setText("DirtyUnicorns").setCallbackData("query_du"));
                
                rowInline3.add(new InlineKeyboardButton().setText("EvoX").setCallbackData("query_evox"));
                rowInline3.add(new InlineKeyboardButton().setText("Havoc").setCallbackData("query_havoc"));
                rowInline3.add(new InlineKeyboardButton().setText("Hydrus").setCallbackData("query_hydrus"));
                
                rowInline4.add(new InlineKeyboardButton().setText("LineageOS").setCallbackData("query_lineage"));
                rowInline4.add(new InlineKeyboardButton().setText("POSP").setCallbackData("query_posp"));
                rowInline4.add(new InlineKeyboardButton().setText("ViperOS").setCallbackData("query_viper"));
                
                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                rowsInline.add(rowInline2);
                rowsInline.add(rowInline3);
                rowsInline.add(rowInline4);
                // Add it to the message
                markupInline.setKeyboard(rowsInline);
                helpquery.setReplyMarkup(markupInline);
		
        		//try to send
        		try {
        			execute(helpquery);
        		}
        		catch (TelegramApiException e) {
        			e.printStackTrace();
        		}
            }
            
            if (call_data.equals("source")) {
                String answer = "💻 BOT SourceCode:\nhttps://github.com/NowDev/MontanaLabs"
						+ "\nIf you're a maintainer and want your rom on the list, do a Pull Request on our repo!\n(feel free to give me ideias, @nowbie )";
						
                EditMessageText helpquery = new EditMessageText()
                        .setChatId(chat_id)
                        .setMessageId(msg_id)
                        .setText(answer);
                
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>(); 
                rowInline.add(new InlineKeyboardButton().setText("🔹 Back 🔹").setCallbackData("home"));
                
                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                // Add it to the message
                markupInline.setKeyboard(rowsInline);
                helpquery.setReplyMarkup(markupInline);
                helpquery.setMessageId(msg_id);
                try {
                    execute(helpquery);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            
            
            
            if (call_data.equals("query_aicp")){
            	String ROMToGet = "aicp";
            	long chatID = chat_id;
            	int queryID = msg_id;
				try {
					ReadURL(ROMToGet, chatID, queryID );
				} catch (IOException e) {e.printStackTrace();}
            }
            if (call_data.equals("query_arrow")){
            	String ROMToGet = "arrow";
            	long chatID = chat_id;
            	int queryID = msg_id;
				try {
					ReadURL(ROMToGet, chatID, queryID );
				} catch (IOException e) {e.printStackTrace();}
            }
            if (call_data.equals("query_boot")){
            	String ROMToGet = "bootleggers";
            	long chatID = chat_id;
            	int queryID = msg_id;
				try {
					ReadURL(ROMToGet, chatID, queryID );
				} catch (IOException e) {e.printStackTrace();}
            }
            if (call_data.equals("query_carbon")){
            	String ROMToGet = "carbon";
            	long chatID = chat_id;
            	int queryID = msg_id;
				try {
					ReadURL(ROMToGet, chatID, queryID );
				} catch (IOException e) {e.printStackTrace();}
            }
            if (call_data.equals("query_du")){
            	String ROMToGet = "du";
            	long chatID = chat_id;
            	int queryID = msg_id;
				try {
					ReadURL(ROMToGet, chatID, queryID );
				} catch (IOException e) {e.printStackTrace();}
            }
            if (call_data.equals("query_evox")){
            	String ROMToGet = "evox";
            	long chatID = chat_id;
            	int queryID = msg_id;
				try {
					ReadURL(ROMToGet, chatID, queryID );
				} catch (IOException e) {e.printStackTrace();}
            }
            if (call_data.equals("query_havoc")){
            	String ROMToGet = "havoc";
            	long chatID = chat_id;
            	int queryID = msg_id;
				try {
					ReadURL(ROMToGet, chatID, queryID );
				} catch (IOException e) {e.printStackTrace();}
            }
            if (call_data.equals("query_hydrus")){
            	String ROMToGet = "hydrus";
            	long chatID = chat_id;
            	int queryID = msg_id;
				try {
					ReadURL(ROMToGet, chatID, queryID );
				} catch (IOException e) {e.printStackTrace();}
            }
            if (call_data.equals("query_lineage")){
            	String ROMToGet = "lineage";
            	long chatID = chat_id;
            	int queryID = msg_id;
				try {
					ReadURL(ROMToGet, chatID, queryID );
				} catch (IOException e) {e.printStackTrace();}
            }
            if (call_data.equals("query_posp")){
            	String ROMToGet = "posp";
            	long chatID = chat_id;
            	int queryID = msg_id;
				try {
					ReadURL(ROMToGet, chatID, queryID );
				} catch (IOException e) {e.printStackTrace();}
            }
            if (call_data.equals("query_viper")){
            	String ROMToGet = "viper";
            	long chatID = chat_id;
            	int queryID = msg_id;
				try {
					ReadURL(ROMToGet, chatID, queryID );
				} catch (IOException e) {e.printStackTrace();}
            }
            
            
            
            
            if (call_data.equals("wikicall")) {
                String answer = "Hello! use ```/wiki``` (something)\nExample: ```/wiki Minecraft```";
                EditMessageText helpquery = new EditMessageText()
                        .setChatId(chat_id)
                        .setMessageId(msg_id)
                        .setText(answer);
                
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>(); 
                rowInline.add(new InlineKeyboardButton().setText("🔹 Back 🔹").setCallbackData("misccall"));
                 
                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                markupInline.setKeyboard(rowsInline);
                helpquery.setReplyMarkup(markupInline);
                helpquery.setParseMode("Markdown");
                helpquery.setMessageId(msg_id);
                try {
                    execute(helpquery);
                } catch (TelegramApiException e) {}
            }
            if (call_data.equals("whoiscall")) {
                String answer = "Hello! use ```/whois``` (domain)\nExample: ```/whois reddit.com```";
                EditMessageText helpquery = new EditMessageText()
                        .setChatId(chat_id)
                        .setMessageId(msg_id)
                        .setText(answer);
                
                 InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                 List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                 List<InlineKeyboardButton> rowInline = new ArrayList<>(); 
                 rowInline.add(new InlineKeyboardButton().setText("🔹 Back 🔹").setCallbackData("misccall"));
                  
                 // Set the keyboard to the markup
                 rowsInline.add(rowInline);
                 markupInline.setKeyboard(rowsInline);
                 helpquery.setReplyMarkup(markupInline);
                 helpquery.setParseMode("Markdown");
                 helpquery.setMessageId(msg_id);
                 
                 try {
                	 execute(helpquery);
                 }
                 catch (TelegramApiException e) {}
            }
		}
		
		
		if (update.hasMessage()) {
			if(update.getMessage().isSuperGroupMessage()) {
				Integer user_id = update.getMessage().getFrom().getId();
				String content = user_id.toString();
				String currentdir = System.getProperty("user.dir");
				currentdir = currentdir.replace( "\\", "/" );
				currentdir = currentdir + "/users/";
				currentdir = currentdir + user_id + ".usr";
				
				File file = new File(currentdir);
		        try {
					if(file.createNewFile()){
					    System.out.println( "[+] New user: " + user_id );
					    try {
					    	String startpoint = "1";
							Files.write(Paths.get(currentdir), startpoint.getBytes());
						} catch (IOException e) {}
					}
					else {
						Stream<String> point = Files.lines(Paths.get(currentdir));
						String points = point.map(Object::toString).collect(Collectors.joining(","));
						int pointint = Integer.parseInt(points);
						if(pointint==150) {
							Message message = update.getMessage();
							String username = update.getMessage().getFrom().getFirstName();
							ReplyMsg(message, true, "Hey, ```" + username + "``` are now at Rookie rank!");
						}
						if(pointint==500) {
							Message message = update.getMessage();
							String username = update.getMessage().getFrom().getFirstName();
							ReplyMsg(message, true, "Nice, ```" + username + "``` are now at Advanced rank!");
						}
						if(pointint==1000) {
							Message message = update.getMessage();
							String username = update.getMessage().getFrom().getFirstName();
							ReplyMsg(message, true, "Noice, ```" + username + "``` are now at Expert rank!");
						}
						if(pointint==1500) {
							Message message = update.getMessage();
							String username = update.getMessage().getFrom().getFirstName();
							ReplyMsg(message, true, "Great, ```" + username + "``` are now at PRO rank!");
						}
						if(pointint==3000) {
							Message message = update.getMessage();
							String username = update.getMessage().getFrom().getFirstName();
							ReplyMsg(message, true, "Damn, ```" + username + "``` are now at Elite rank!");
						}
						if(pointint==5000) {
							Message message = update.getMessage();
							String username = update.getMessage().getFrom().getFirstName();
							ReplyMsg(message, true, "WOW, ```" + username + "``` are now at Master rank!");
						}
						if(pointint==10000) {
							Message message = update.getMessage();
							String username = update.getMessage().getFrom().getFirstName();
							ReplyMsg(message, true, "OMG, ```" + username + "``` are now at LEGEND rank!");
						}
						pointint += 1;
						point.close();
						try {
							String pointsave = Integer.toString(pointint);
							Files.write(Paths.get(currentdir), pointsave.getBytes());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} catch (IOException e1) {}
		        
			}
		}
	    
        
		
		//Gerencia as mensagens -> olhamos a mensagem que recebemos
		if (update.hasMessage()) {
			
		//salvamos a mensagem em um objeto de texto
		Message message = update.getMessage();	
		

		if( message.hasText() ) {
		//pegamos o texto e o convertemos em minúsculas, então, mesmo que o usuário tenha digitado "/HELLO", ele deve funcionar
		String msg = message.getText().toLowerCase();
		String msgnt = message.getText().toString().toLowerCase();
		//Apenas faz log de mensagens com texto
				
			
			if( message.isUserMessage() == true ) {
			String username = update.getMessage().getChat().getFirstName().toString();
			String usermessage = update.getMessage().getText().toString();
			System.out.println("[PM] " + username + ": " + usermessage );
			}
			/*
			else {
				
				String groupname = update.getMessage().getChat().getTitle();
				String username = update.getMessage().getFrom().getFirstName();
				String usermessage = update.getMessage().getText().toString();
				System.out.println("[" + groupname + "] " + username + ": " + usermessage );
				
			}
			*/
			
				
				
				
				if (msg.startsWith("/menu")) {
				
				Long chat_id = message.getChatId();
				Integer msg_id = message.getMessageId();
					
                SendMessage replymsg = new SendMessage()
                        .setChatId(chat_id)
                        .setText("Hello! Click on that cool button to start the menu. 😄");
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("Menu").setCallbackData("home"));
                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                // Add it to the message
                markupInline.setKeyboard(rowsInline);
                replymsg.setReplyMarkup(markupInline);
                replymsg.setReplyToMessageId(msg_id);
		
        		//try to send
        		try {
        			execute(replymsg);
        		}
        		catch (TelegramApiException e) {
        			e.printStackTrace();
        		}
			}
        		
				if (msg.startsWith("/wiki ")) {
					if(message.isUserMessage()) {
					
					String term = msgnt;
					term = term.replace("/wiki ", "");
					String termproc = term;
					termproc = termproc.replaceAll(" ", "%20");
					System.out.println("[Debug] looking for: " + termproc );
					
					String whois = message.getFrom().getFirstName();
					ReplyMsg(message, false, "Hmmm... Searching for " + term + ", pls wait " + whois );
					
					String wikiurl = "https://en.wikipedia.org/w/api.php?action=query&prop=extracts&format=json&exintro=&explaintext=&grnnamespace=0&titles=" + termproc;
					String content = null;
					
					   try {
					   URLConnection connection =  new URL(wikiurl).openConnection();
					   Scanner scanner = new Scanner(connection.getInputStream());
					   scanner.useDelimiter("\\Z");
					   content = scanner.next();
					   scanner.close();
					   }catch ( Exception ex ) {
					   SendMsg(message, false, "Error!\n" + ex);
					   }
					   JSONObject data = new JSONObject(content);
					   
				        JsonElement jsonElement = new JsonParser().parse(content);
				        JsonElement query = jsonElement.getAsJsonObject().get("query");
				        JsonElement pages = query.getAsJsonObject().get("pages");

				        Set<Entry<String, JsonElement>> entrySet = pages.getAsJsonObject().entrySet();

				        JsonElement yourDesiredElement = null;

				        for(Map.Entry<String,JsonElement> entry : entrySet){
				            yourDesiredElement = entry.getValue();
				        }
				        
				       if(yourDesiredElement != null) {
				       String result = yourDesiredElement.toString();
				       JsonElement jsonExtract = new JsonParser().parse(result);
				       JsonElement out = jsonExtract.getAsJsonObject().get("extract");
				       String Wiki = out.toString();
				       String WikiResult = Wiki.replace("\n"," ");
					   SendMsg(message, false, WikiResult);
				       }
				    }  
					else {
						String usrname = message.getFrom().getFirstName();
						Long chat_id = message.getChatId();
						Integer msg_id = message.getMessageId();
						
						//Button stuff
		                SendMessage replymsg = new SendMessage()
		                        .setChatId(chat_id)
		                        .setText("Sorry " + usrname + ", only on private.");
		                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		                List<InlineKeyboardButton> rowInline = new ArrayList<>();
		                rowInline.add(new InlineKeyboardButton().setText("Open Chat").setUrl("https://t.me/montanahelper_bot?start=wiki").setCallbackData("wikicall"));
		                // Set the keyboard to the markup
		                rowsInline.add(rowInline);
		                // Add it to the message
		                markupInline.setKeyboard(rowsInline);
		                replymsg.setReplyMarkup(markupInline);
		                replymsg.setReplyToMessageId(msg_id);
				
		        		//try to send
		        		try {
		        			execute(replymsg);
		        		}
		        		catch (TelegramApiException e) {
		        			e.printStackTrace();
		        		}
						
					}
				}
				
				
				if(message.isSuperGroupMessage()){
					if(message.isReply()) {
					   if(msg.equals(".user")){
						      int user_id = message.getReplyToMessage().getFrom().getId();
						      String user = message.getReplyToMessage().getFrom().getFirstName();
						      String user_name = message.getReplyToMessage().getFrom().getUserName();
						      ReplyMsg(message, false, "user: " + user + "\n"
								+ "id: " + user_id +
								"\nusertag: @" + user_name );
						      return;
					   }
					}
				}
			
			
				if(msg.equals(".info")) {
					  if(message.getReplyToMessage().hasSticker()) {
					  String sticker_name = message.getReplyToMessage().getSticker().getSetName().toString();
					  String sticker_emoji = message.getReplyToMessage().getSticker().getEmoji().toString();
					  String sticker_size = message.getReplyToMessage().getSticker().getFileSize().toString();
					  ReplyMsg(message, false, "Sticker Info:"
							  + "\nPack Name: " + sticker_name
							  + "\nEmoji: " + sticker_emoji
							  + "\nSize: " + sticker_size );
					  return;
					  }
					  if(message.getReplyToMessage().hasDocument()) {
					  String file_id = message.getReplyToMessage().getDocument().getFileId().toString();
					  String file_name = message.getReplyToMessage().getDocument().getFileName().toString();
					  String sent_by = message.getReplyToMessage().getFrom().getUserName().toString();
					  String sent_by_id = message.getReplyToMessage().getFrom().getId().toString();
					  ReplyMsg(message, true, "💾  File Info:"
					  		+ "\nID: ```" + file_id + "```"
					  		+ "\nName: ```" + file_name + "```"
					  		+ "\nBy: " + sent_by 
					  		+ "\nUserID: ```" + sent_by_id + "```");
					  return;
					  }
					  ReplyMsg(message, true, "🤔 Try ```.user```, ```.info``` is only for files or stickers!");
				}
				
				
				//agora nós dizemos ao bot para responder a casos específicos:
				if (msg.equals("#rules")) {
					String usrname = message.getFrom().getFirstName();
					Long chat_id = message.getChatId();
					Integer msg_id = message.getMessageId();
					
					//Button stuff
	                SendMessage replymsg = new SendMessage()
	                        .setChatId(chat_id)
	                        .setText("Rules/Regras/Reglas");
	                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
	                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
	                List<InlineKeyboardButton> rowInline = new ArrayList<>();
	                rowInline.add(new InlineKeyboardButton().setText("Rules (EN)").setUrl("https://t.me/montanahelper_bot?start=rulesen").setCallbackData("rulesen"));
	                rowInline.add(new InlineKeyboardButton().setText("Regras (PT)").setUrl("https://t.me/montanahelper_bot?start=rulespt").setCallbackData("rulespt"));
	                rowInline.add(new InlineKeyboardButton().setText("Reglas (ES)").setUrl("https://t.me/montanahelper_bot?start=ruleses").setCallbackData("ruleses"));
	                
	                // Set the keyboard to the markup
	                rowsInline.add(rowInline);
	                // Add it to the message
	                markupInline.setKeyboard(rowsInline);
	                replymsg.setReplyMarkup(markupInline);
	                replymsg.setReplyToMessageId(msg_id);
			
	        		//try to send
	        		try {
	        			execute(replymsg);
	        		}
	        		catch (TelegramApiException e) {
	        			e.printStackTrace();
	        		}
				}
				
				
				if (msg.equals("/start")) {
	
						Long chat_id = message.getChatId();
						int msg_id = message.getMessageId();
						String help_string = "Hello, here is some features:";
						
		                SendMessage helpquery = new SendMessage()
		                        .setChatId(chat_id)
		                        .setText(help_string);
		                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		                List<InlineKeyboardButton> rowInline = new ArrayList<>(); 
		                List<InlineKeyboardButton> rowInline2 = new ArrayList<>(); 
		                rowInline.add(new InlineKeyboardButton().setText("ROMs 🔥").setCallbackData("roms"));
		                rowInline.add(new InlineKeyboardButton().setText("Magisk 📱").setCallbackData("magisk"));
		                rowInline.add(new InlineKeyboardButton().setText("TWRP 🔧").setCallbackData("twrp"));
		                rowInline2.add(new InlineKeyboardButton().setText("Misc 🤔").setCallbackData("misccall"));
		                rowInline2.add(new InlineKeyboardButton().setText("Level 📘").setCallbackData("lvl"));
		                rowInline2.add(new InlineKeyboardButton().setText("BotSource 💻").setCallbackData("source"));
		                
		                // Set the keyboard to the markup
		                rowsInline.add(rowInline);
		                rowsInline.add(rowInline2);
		                // Add it to the message
		                markupInline.setKeyboard(rowsInline);
		                helpquery.setReplyMarkup(markupInline);
		                helpquery.setReplyToMessageId(msg_id);
				
		        		//try to send
		        		try {
		        			execute(helpquery);
		        		}
		        		catch (TelegramApiException e) {
		        			e.printStackTrace();
		        		}
				}
				
				if(msg.equals("/start whois")) {
					SendMsg(message, false, "Use: /whois (domain)\n"
							+ "Example: /whois google.com");
				}
				if(msg.equals("/start wiki")) {
					SendMsg(message, false, "Use: /wiki (something)\n"
							+ "Example: /wiki Minecraft");
				}
				
				if (msg.contains("/help")) {
					if ( message.isUserMessage() == true ) {
					
						Long chat_id = message.getChatId();
						int msg_id = message.getMessageId();
						String help_string = "📁 Menu - Some things this BOT can do!";
						
		                SendMessage helpquery = new SendMessage()
		                        .setChatId(chat_id)
		                        .setText(help_string);
		                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		                List<InlineKeyboardButton> rowInline = new ArrayList<>();
		                List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
		                rowInline.add(new InlineKeyboardButton().setText("ROMs 🔥").setCallbackData("roms"));
		                rowInline.add(new InlineKeyboardButton().setText("Magisk 📱").setCallbackData("magisk"));
		                rowInline.add(new InlineKeyboardButton().setText("TWRP 🔧").setCallbackData("twrp"));
		                rowInline2.add(new InlineKeyboardButton().setText("Misc 🤔").setCallbackData("misccall"));
		                rowInline2.add(new InlineKeyboardButton().setText("Level 📘").setCallbackData("lvl"));
		                rowInline2.add(new InlineKeyboardButton().setText("BotSource 💻").setCallbackData("source"));
		                
		                // Set the keyboard to the markup
		                rowsInline.add(rowInline);
		                rowsInline.add(rowInline2);
		                // Add it to the message
		                markupInline.setKeyboard(rowsInline);
		                helpquery.setReplyMarkup(markupInline);
		                helpquery.setReplyToMessageId(msg_id);
				
		        		//try to send
		        		try {
		        			execute(helpquery);
		        		}
		        		catch (TelegramApiException e) {
		        			e.printStackTrace();
		        		}
					
					    return;
					}
				}
					
				if (msg.startsWith("/level")) {
					if (message.isSuperGroupMessage()) {
					Integer user_id = update.getMessage().getFrom().getId();
					String username = update.getMessage().getFrom().getFirstName();
					
					try {
					String content = user_id.toString();
					String currentdir = System.getProperty("user.dir");
					currentdir = currentdir.replace( "\\", "/" );
					currentdir = currentdir + "/users/";
					currentdir = currentdir + user_id + ".usr";
					
					File file = new File(currentdir);
					
					Stream<String> point = Files.lines(Paths.get(currentdir));
					String points = point.map(Object::toString).collect(Collectors.joining(","));
					int pointint = Integer.parseInt(points);
					String userpoints = Integer.toString(pointint);
					point.close();
					if ( pointint >= 1 ) {
					ReplyMsg( message, true, "Hello, " + username + "!" + "\nYou have " + userpoints + " points!"
							+ "\nRank: Noob" );
					return;
					}
					if ( pointint >= 150 ) {
						ReplyMsg( message, true, "Hello, " + username + "!" + "\nYou have " + userpoints + " points!"
								+ "\nRank: Rookie" );
						return;
						}
					if ( pointint >= 500 ) {
						ReplyMsg( message, true, "Hello, " + username + "!" + "\nYou have " + userpoints + " points!"
								+ "\nRank: Advanced" );
						return;
						}
					if ( pointint >= 1000 ) {
						ReplyMsg( message, true, "Hello, " + username + "!" + "\nYou have " + userpoints + " points!"
								+ "\nRank: Expert" );
						return;
						}
					if ( pointint >= 1500 ) {
						ReplyMsg( message, true, "Hello, " + username + "!" + "\nYou have " + userpoints + " points!"
								+ "\nRank: PRO" );
						return;
						}
					if ( pointint >= 3000 ) {
						ReplyMsg( message, true, "Hello, " + username + "!" + "\nYou have " + userpoints + " points!"
								+ "\nRank: Elite" );
						return;
						}
					if ( pointint >= 5000 ) {
						ReplyMsg( message, true, "Hello, " + username + "!" + "\nYou have " + userpoints + " points!"
								+ "\nRank: Master" );
						return;
						}
					if ( pointint >= 10000 ) {
						ReplyMsg( message, true, "Hello, " + username + "!" + "\nYou have " + userpoints + " points!"
								+ "\nRank: LEGEND" );
						return;
						}
					}
					
					catch (IOException e){}
					return;
					}
					else {
						ReplyMsg(message, false, "Sorry, this command is only for group!" );
					}
				}
					
				if (msg.startsWith("/shot")) {
					if(message.isSuperGroupMessage()) {
					
					Random rand = new Random();
					int n = rand.nextInt(6);
					n += 1;
					if(n==4) {
						ReplyMsg(message, false, "😐  🔫 SHOT!\nSadly you are dead! (" + n + "/6)" );
						Long chat_id = message.getChatId();
						Integer user_id = message.getFrom().getId();
						KickUser(message, chat_id, user_id );
						return;
					}
					else {
						Integer user_id = update.getMessage().getFrom().getId();
						String content = user_id.toString();
						String currentdir = System.getProperty("user.dir");
						currentdir = currentdir.replace( "\\", "/" );
						currentdir = currentdir + "/users/";
						currentdir = currentdir + user_id + ".usr";
						File file = new File(currentdir);
						
						
						Stream<String> point;
						try {
							Random randprize = new Random();
							int prize = randprize.nextInt(10);
							prize += 1;
							point = Files.lines(Paths.get(currentdir));
							String points = point.map(Object::toString).collect(Collectors.joining(","));
							int pointint = Integer.parseInt(points);
							int userpoints = pointint; 
							pointint += prize;
							point.close();
							String pointsave = Integer.toString(pointint);
							Files.write(Paths.get(currentdir), pointsave.getBytes());
							
							ReplyMsg(message, true, "😐  🔫 **SHOT!**\nThe gun haven't bullet! 🙂  (" + n + "/6)\nYou got " + prize + " points for your lucky!\nSee your points with /level" );
						} catch (IOException e1) {}

						
						return;
					}
					}
					else {
						ReplyMsg(message, false, "Sorry, this command is only for group.");
						return;
					}
				}
				/*
				if (msg.contains("kkkkkkkkkk")) {
					SendMsg(message, false, "lol");
					return;
				}
				if (msg.equals("oof")) {
					Long chatId = message.getChatId();
					int fileMessageID = message.getMessageId();
					String docId = "BQADAQADfQADn1DYRpQcMC_3J7SeFgQ";
					try {
					SendFile(chatId, docId, fileMessageID );
					}
					catch(TelegramApiException e) {}
				}
				*/
				if (msg.equals("/quit")) {
					ReplyMsg(message, false, "OK...");
					Long chat_id = message.getChatId();
					Integer user_id = message.getFrom().getId();
					KickUser(message, chat_id, user_id );
				}
				/*
				if (msg.contains("corno")) {
					ReplyMsg(message, false, "moooo! 🐮 ");
					return;
				}
				*/
				if (msg.equals(".alive")) {
					ReplyMsg(message, false,"UserBOT 😏");
				}
				
				if (msg.contains(" safad")) {
					ReplyMsg(message, false, "hmmmmmmm rsrsrsrs 😏");
					return;
				}
				/*
				if (msg.contains("ban")) {
					ReplyMsg(message, "ban  a  na 🍌");
					return;
				}
				*/
				if (msg.contains(" advertência")) {
					ReplyMsg(message, false, "admin's on fire! 🔥");
					return;
				}
				if (msg.startsWith("/ping")) {
					SendMsg(message, false, "Pong! 👾");
				}
				if (msg.startsWith("/botsource")) {
					ReplyMsg(message, false, "💻 BOT SourceCode:\nhttps://github.com/NowDev/MontanaLabs"
							+ "\nIf you're a maintainer and want your rom on the list, do a Pull Request on our repo!");
				}
				/*
				if (msg.contains(" bot ")) {
					ReplyMsg(message, false, "bip bop 🤖");
				}
				*/
				
				if(msg.equals("/start rulesen")) {
					SendMsg(message, false, "Rules:" +
							"\n• No offense by administrators and ordinary users." +
							"\n• Forbidden to call administrators and other maintainers to request ROMs or warn of bugs." +
							"\n• No cyberbullying on group, either by administrators or users." +
							"\n• Forbidden to send photos of users here in the group" +
							"\n• Forbidden to sell products in the group, administrators or users." +
							"\n• No Flood." +
							"\n• No SPAM." +
							"\n• No ETA, If you want some ROM, compile it yourself." +
							"\n• Disrespect members and management (common sense right, friend?)." +
							"\n• Talk about other devices (just look at the group name)." +
							"\n• Flood admins on private messages." +
							"\n• Promote products or websites without permission." +
							"\n• No Shortened links." +
							"\n• Bugs? Report in our bug tracker: https://github.com/MontanaDevelopment/Issue-Tracker" +
							"\n• Don't complain about ROM if you are using 69 modules and modifications." );
				}
				if(msg.equals("/start rulespt")) {
					SendMsg(message, false, "Regras:" +
							"\n• Proibido ofensas por parte de administradores e usuários comuns." +
							"\n• Proibido chamar administradores e outros maintainers para o pedidos de rom's ou alertar sobre bugs." +
							"\n• Proibido cyberbullying dentro do grupo, tanto da parte de administradores ou usuários." +
							"\n• Proibido o envio de fotos dos usuários aqui no grupo, assim evita um 'rombo na privacidade' do usuário." +
							"\n• Proibido venda de produtos no grupo, tanto da parte do administradores e principalmente usuários." +
							"\n• Sem Flood." +
							"\n• Sem SPAM." +
							"\n• Sem ETA, se quer alguma ROM, compile você mesmo." +
							"\n• Desrespeitar os membros e a administração (bom senso né, amigo?)." +
							"\n• Falar sobre outros devices (apenas olhe o nome do grupo)." +
							"\n• Floodar os admins em mensagens privadas." +
							"\n• Divulgar produtos ou sites sem permissão." +
							"\n• Links encurtados." +
							"\n• Bugs? Relate no nosso bug tracker: https://github.com/MontanaDevelopment/Issue-Tracker" +
							"\n• Não reclame da ROM se você estiver usando 69 módulos e modificações.." );
				}
				if(msg.equals("/start ruleses")) {
					SendMsg(message, false, "Reglas:" +
							"\n• No ofender ya sea por parte de administradores o usuarios comunes." +
							"\n• Prohibido llamar a administradores y otros encargados de mantenimiento para solicitar ROM's o avisar de errores." +
							"\n• Sin ciberacoso dentro del grupo, ya sea por parte de administradores o usuarios." +
							"\n• Prohibido enviar fotos de usuarios aquí en el grupo, evitando así una violación de la privacidad del usuario." +
							"\n• Prohibido vender productos en el grupo, tanto por administradores como principalmente usuarios." +
							"\n• No FLOOD." +
							"\n• No SPAM." +
							"\n• No ETA, si desea alguna ROM, compílela usted mismo." +
							"\n• No Falte el respeto a los miembros y a la gerencia (sentido común, ¿verdad, amigo?)." +
							"\n• No hable sobre otros dispositivos (solo mire el nombre del grupo)." +
							"\n• No mandar Flood al privado de los Administradores." +
							"\n• Prohibido divulgar productos o sitios web sin permiso." +
							"\n• No hay enlaces acortados." +
							"\n• Encontró errores?  Informe sobre su BUG en nuestro rastreador de errores: https://github.com/MontanaDevelopment/Issue-Tracker" +
							"\n• No se queje de la ROM si está utilizando 69 módulos y modificaciones." +
							"\n- Traducido por @JRGamerPRO");
				}
				if(msg.startsWith("/whois")) {
					if(message.isUserMessage()) {
					String domain = msgnt;
					domain = domain.replace("/whois", "");
					
				      StringBuilder sb = new StringBuilder("");
				      WhoisClient wic = new WhoisClient();
				      try {
				         wic.connect(WhoisClient.DEFAULT_HOST);
				         String whoisData1 = wic.query("=" + domain);
				         sb.append(whoisData1);
				         wic.disconnect();
				      } catch (Exception e) {
				    	  String i = e.toString();
				    	  ReplyMsg(message, false, i);
				      }
				      String sb1 = sb.toString();
				      String sb2 = sb1.replace("TERMS OF USE: You are not authorized to access or query our Whois\r\n" + 
				      		"database through the use of electronic processes that are high-volume and\r\n" + 
				      		"automated except as reasonably necessary to register domain names or\r\n" + 
				      		"modify existing registrations; the Data in VeriSign Global Registry\r\n" + 
				      		"Services' (\"VeriSign\") Whois database is provided by VeriSign for\r\n" + 
				      		"information purposes only, and to assist persons in obtaining information\r\n" + 
				      		"about or related to a domain name registration record. VeriSign does not\r\n" + 
				      		"guarantee its accuracy. By submitting a Whois query, you agree to abide\r\n" + 
				      		"by the following terms of use: You agree that you may use this Data only\r\n" + 
				      		"for lawful purposes and that under no circumstances will you use this Data\r\n" + 
				      		"to: (1) allow, enable, or otherwise support the transmission of mass\r\n" + 
				      		"unsolicited, commercial advertising or solicitations via e-mail, telephone,\r\n" + 
				      		"or facsimile; or (2) enable high volume, automated, electronic processes\r\n" + 
				      		"that apply to VeriSign (or its computer systems). The compilation,\r\n" + 
				      		"repackaging, dissemination or other use of this Data is expressly\r\n" + 
				      		"prohibited without the prior written consent of VeriSign. You agree not to\r\n" + 
				      		"use electronic processes that are automated and high-volume to access or\r\n" + 
				      		"query the Whois database except as reasonably necessary to register\r\n" + 
				      		"domain names or modify existing registrations. VeriSign reserves the right\r\n" + 
				      		"to restrict your access to the Whois database in its sole discretion to ensure\r\n" + 
				      		"operational stability.  VeriSign may restrict or terminate your access to the\r\n" + 
				      		"Whois database for failure to abide by these terms of use. VeriSign\r\n" + 
				      		"reserves the right to modify these terms at any time.", "");
				      String sb3 = sb2.replace("\r\n" + 
				      		"For more information on Whois status codes, please visit https://icann.org/epp\r\n" + 
				      		"\r\n" + 
				      		"NOTICE: The expiration date displayed in this record is the date the\r\n" + 
				      		"registrar's sponsorship of the domain name registration in the registry is\r\n" + 
				      		"currently set to expire. This date does not necessarily reflect the expiration\r\n" + 
				      		"date of the domain name registrant's agreement with the sponsoring\r\n" + 
				      		"registrar.  Users may consult the sponsoring registrar's Whois database to\r\n" + 
				      		"view the registrar's reported date of expiration for this registration.\r\n" + 
				      		"\r\n" + 
				      		"", "");
				      ReplyMsg(message, false, sb3);
				      //System.out.println(sb.toString());
				      return;
					   }
					else {
						String usrname = message.getFrom().getFirstName();
						Long chat_id = message.getChatId();
						Integer msg_id = message.getMessageId();
						
						//Button stuff
		                SendMessage replymsg = new SendMessage()
		                        .setChatId(chat_id)
		                        .setText("Sorry " + usrname + ", only on private.");
		                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		                List<InlineKeyboardButton> rowInline = new ArrayList<>();
		                rowInline.add(new InlineKeyboardButton().setText("Open Chat").setUrl("https://t.me/montanahelper_bot?start=whois").setCallbackData("whoiscall"));
		                // Set the keyboard to the markup
		                rowsInline.add(rowInline);
		                // Add it to the message
		                markupInline.setKeyboard(rowsInline);
		                replymsg.setReplyMarkup(markupInline);
		                replymsg.setReplyToMessageId(msg_id);
				
		        		//try to send
		        		try {
		        			execute(replymsg);
		        		}
		        		catch (TelegramApiException e) {
		        			e.printStackTrace();
		        		}
					  }
					}
				
				
				//END
				
				else if (msg.contains("/magisk")) {
					
					String magisks = "hmmm... something is wrong, use as my example:\n```/magisk apk```\n```/magisk zip```\n```/magisk uninstaller```";
					int fileMessageID = message.getMessageId();
					if (msg.contains("/magisk ")) {
						String magiskmsg = msg.replace( "/magisk ", "" );
						if (magiskmsg.equals("apk")) {
							
							String docId = "BQADAQADcAADVFdIRG5B0N-W3c-yFgQ";
							Long chatId = message.getChatId();
							try {
								SendMsg(message, false, "Magisk APK v7.3.4:");
								SendFile(chatId, docId, fileMessageID );
							} catch (TelegramApiException e) {
								e.printStackTrace();
							}
						}
						else if (magiskmsg.equals("zip")) {
							
							String docId = "BQADAQADZgAD80FIRIf-usSD7cAmFgQ";
							Long chatId = message.getChatId();
							try {
								SendMsg(message, false, "Magisk ZIP v19.4:");
								SendFile(chatId, docId, fileMessageID );
							} catch (TelegramApiException e) {
								e.printStackTrace();
							}
						}
						else if (magiskmsg.equals("uninstaller")) {
							
							String docId = "BQADAQADcQADVFdIRHNXa-FgoOvMFgQ";
							Long chatId = message.getChatId();
							try {
								SendMsg(message, false, "Magisk Unistaller 20190919:");
								SendFile(chatId, docId, fileMessageID );
							} catch (TelegramApiException e) {
								e.printStackTrace();
							}
						}
						else {
							ReplyMsg(message, false, "argument unknown for magisk");
						}
						
					}
					
					else {
						ReplyMsg(message, true, magisks );
					}
					
				}
				
				

		    }
		}	
	}

	public void ReadURL(String ROMToGet, long chatID, int queryID ) throws MalformedURLException, IOException {
		
		String romtosend = "404.txt";
		if(ROMToGet == "aicp") {romtosend = "aicp.txt";}
		if(ROMToGet == "arrow") {romtosend = "arrow.txt";}
		
		if(ROMToGet == "bootleggers") {romtosend = "bootleggers.txt";}
		if(ROMToGet == "carbon") {romtosend = "carbon.txt";}
		if(ROMToGet == "du") {romtosend = "du.txt";}
		
		if(ROMToGet == "evox") {romtosend = "evox.txt";}
		if(ROMToGet == "havoc") {romtosend = "havoc.txt";}
		if(ROMToGet == "hydrus") {romtosend = "hydrus.txt";}
		
		if(ROMToGet == "lineage") {romtosend = "lineage.txt";}
		if(ROMToGet == "posp") {romtosend = "posp.txt";}
		if(ROMToGet == "viper") {romtosend = "viper.txt";}
		

		URLConnection connection =  new URL("https://raw.githubusercontent.com/NowDev/MontanaLabs/master/ROMs/" + romtosend ).openConnection();
		   String content = null;
		   try {
		   Scanner scanner = new Scanner(connection.getInputStream());
		   scanner.useDelimiter("\\Z");
		   content = scanner.next();
		   scanner.close();
		   }catch ( Exception ex ) {

       	    EditMessageText helpquery = new EditMessageText()
                   .setChatId(chatID)
                   .setText(ex.toString())
                   .setMessageId(queryID);
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>(); 
            rowInline.add(new InlineKeyboardButton().setText("[Back]").setCallbackData("roms"));
            
            // Set the keyboard to the markup
            rowsInline.add(rowInline);
            markupInline.setKeyboard(rowsInline);
            helpquery.setReplyMarkup(markupInline);
            helpquery.setMessageId(queryID);
            
            try {
           	 execute(helpquery);
            }
            catch (TelegramApiException e) {
           	 e.printStackTrace();
            }
		   }
		   
       	   EditMessageText helpquery = new EditMessageText()
                   .setChatId(chatID)
                   .setText(content.toString())
                   .setMessageId(queryID);
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>(); 
            rowInline.add(new InlineKeyboardButton().setText("[Back]").setCallbackData("roms"));
            
            // Set the keyboard to the markup
            rowsInline.add(rowInline);
            markupInline.setKeyboard(rowsInline);
            helpquery.setReplyMarkup(markupInline);
            helpquery.setMessageId(queryID);
            
            try {
           	 execute(helpquery);
            }
            catch (TelegramApiException e) {
           	 e.printStackTrace();
            }
		   
		}
	
	public String getWhois(String domainName, Message message) {

		StringBuilder result = new StringBuilder("");

		WhoisClient whois = new WhoisClient();
		try {

			//default is internic.net
			whois.connect(WhoisClient.DEFAULT_HOST);
			String whoisData1 = whois.query("=" + domainName);
			result.append(whoisData1);
			whois.disconnect();

		} catch (SocketException e) {
			String i = e.toString();
			ReplyMsg(message, false, i );
		} catch (IOException e) {
			String i = e.toString();
			ReplyMsg(message, false, i);
		}

		return result.toString();

	}

	//essa função recebe um objeto de mensagem (que contém o ID do remetente e todos os detalhes que precisamos responder)
	//e uma string simples, e envie uma nova mensagem para o usuário
	public void SendMsg(Message message, boolean format, String s) {
		SendMessage sendMessage = new SendMessage();
		
		//definir o destino e o texto que queremos enviar
		sendMessage.setChatId(message.getChatId().toString());
		if (format) {
		sendMessage.setParseMode("Markdown");
		}
		sendMessage.setText(s);
		
		//tenta enviar:
		try {
			execute(sendMessage);
		}
		catch (TelegramApiException e) {
			e.printStackTrace();
		}
		
	}

	public void ReplyMsg(Message message, boolean format, String s ) {
		
		SendMessage replyMessage = new SendMessage();
		
		if (format) {
		replyMessage.setParseMode("Markdown");
		}
		replyMessage.setChatId(message.getChatId().toString());
		replyMessage.setText(s);
		
		int ss = message.getMessageId();
		replyMessage.setReplyToMessageId(ss);
		
		try {
			execute(replyMessage);
		}
		catch (TelegramApiException e) {
			e.printStackTrace();
		}
		
	}
	public void KickUser(Message message, Long chat_id, Integer user_id) {
		KickChatMember kick = new KickChatMember();
		UnbanChatMember unkick = new UnbanChatMember();
		kick.setChatId(chat_id);
		kick.setUserId(user_id);
		unkick.setChatId(chat_id);
		unkick.setUserId(user_id);
		try {
			execute(kick);
			execute(unkick);
		}
		catch (TelegramApiException e) {
			SendMsg(message, false, "hmmmm... i couldn't kick you 🤔");
		}
		return;
	}
	//Esta funcão serve para enviar arquivos...
	private void SendFile(Long chatId, String docId, int fileMessageID ) throws TelegramApiException {
		
	    SendDocument sendFile = new SendDocument();
	    sendFile.setChatId(chatId);
	    sendFile.setDocument(docId);
		sendFile.setReplyToMessageId(fileMessageID);
	    try {
	    	execute(sendFile);
	    }
		catch (TelegramApiException e) {
			e.printStackTrace();
		}
	    
	}
	
		
}