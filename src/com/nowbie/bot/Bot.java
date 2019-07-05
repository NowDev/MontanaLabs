package com.nowbie.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
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

@SuppressWarnings("unused")

public class Bot extends TelegramLongPollingBot {	

	@Override
	public String getBotUsername() { return "@montanahelper_bot"; }
	
	@Override
	//Não posso passar o token para o git haha
	//Se quiser um token, fale com o bot father (pai dos bots).
	public String getBotToken() { return BotInfo.BotToken; }
	
	@Override
	//esta função é acionada toda vez que o bot recebe uma nova mensagem
	//mais infos aqui: [https://core.telegram.org/bots/api#update ]
	public void onUpdateReceived(Update update) {
	    
		
		
		if (update.hasMessage()) {
			Message ourmessage = update.getMessage();
			if (ourmessage.hasDocument()) {
				
				//Pega o documento que foi enviado
			    Document document = ourmessage.getDocument();
			    
			    String fileName = document.getFileName();
			    Integer fileSize = document.getFileSize();			    
			    String fileID = document.getFileId();

			    
			    //ReplyMsg(ourmessage, "[ New File 💾 ]" + "\nFile name: " + fileName + "\nFile size: " + fileSize + "\nFile ID: " + fileID );
			    	
			}
		}
        
        
        
		if (update.hasCallbackQuery()) {
	    	if(update.getCallbackQuery().toString().contains("atapo")) {
	    		
            //Setamos variavéi...
            String call_data = update.getCallbackQuery().getData().toString();
            
            if (call_data.equals("atapo")) {
    			
            	//salvamos a mensagem em um objeto de texto
    			Message message2 = update.getMessage();
            	SendMsg(message2, "Ok");	
            	return;
            	}
	    	}
	    }
		
		//Gerencia as mensagens -> olhamos a mensagem que recebemos
		if (update.hasMessage()) {
			
			//salvamos a mensagem em um objeto de texto
			Message message = update.getMessage();
			
			//Apenas faz log de mensagens com texto
			if( message.hasText()) {
				
			
			if( message.isUserMessage() == true ) {
			String username = update.getMessage().getChat().getFirstName().toString();
			String usermessage = update.getMessage().getText().toString();
			System.out.println("[PM] " + username + ": " + usermessage );
			}
			else {
				String groupname = update.getMessage().getChat().getTitle();
				String username = update.getMessage().getFrom().getFirstName();
				String usermessage = update.getMessage().getText().toString();
				System.out.println("[" + groupname + "] " + username + ": " + usermessage );
			}
			
				
				//pegamos o texto e o convertemos em minúsculas, então, mesmo que o usuário tenha digitado "/HELLO", ele deve funcionar
				String msg = message.getText().toLowerCase();
				
				//agora nós dizemos ao bot para responder a casos específicos:
				
				if (msg.equals("/start")) {
					if ( message.isUserMessage() == false ) {ReplyMsg(message, "hmmmm, contact me on private for that :3");}
					else {
						SendMsg(message, "Hey, welcome to MontanaHelper! 🤖\nfor now i have only:\n> /roms\n> /help\n> /magisk \n[this bot was created 2 days ago, so... it's in-dev!]");
					}
				}
		
				if( msg.equals("/help@montanahelper_bot")) {
					if(message.isUserMessage() == false ) {
					ReplyMsg(message, "Hey there! did you wanna help? for now i only have: \n> /roms - see some roms...\n> /magisk - download magisk.\ntalk with me on private:" + "\n> @montanahelper_bot" );
					return;
					}
				}
				if (msg.contains("kkkkkkkkkk")) {
					ReplyMsg(message, "lol 😂😂😂");
					return;
				}
				if (msg.contains("corno")) {
					ReplyMsg(message, "moooo! 🐮 ");
					return;
				}
				if (msg.contains(" safad")) {
					ReplyMsg(message, "hmmmmmmm rsrsrsrs 😏");
					return;
				}
				if (msg.contains("ban")) {
					ReplyMsg(message, "ban  a  na 🍌");
					return;
				}
				if (msg.contains(" advertência")) {
					ReplyMsg(message, "admin's on fire! 🔥");
					return;
				}

				if (msg.startsWith("/hello")) {
					SendMsg(message, "Hello World! kek");
				}

				if (msg.equals("/roms") || msg.equals("/roms@montanahelper_bot")) {
					if(!message.isUserMessage()) {
					String username = message.getFrom().getFirstName();
						ReplyMsg(message, "Hello, " + username + "! Contact me on private for that!\n@montanahelper_bot");
					}
					else {
						SendMsg(message, "ROMs registered:"
								+ "\n" + "CarbonROM: /carbon");
					}
				}
				
				if (msg.equals("/carbon")) {
					
					String username = message.getFrom().getFirstName();
					if(!message.isUserMessage()) {
						ReplyMsg(message, "Hello, " + username + "! Contact me on private for that!\n@montanahelper_bot");
					}
					else {
						String ROMToGet = "carbon";
						try {
							ReadURL(ROMToGet, message);
						} catch (MalformedURLException e) {
							ReplyMsg(message, "Oops! i got some error :(\nPlease report to @nowbie\nInfos:\n[MalformedURLException]\n" + e );
					
						} catch (IOException e) {
							ReplyMsg(message, "Oops! i got some error :(\nPlease report to @nowbie\nInfos:\n[IOException]\n" + e );
						}
					}
				    
				}
				
				else if (msg.equals("/magisk")) {
					ReplyMsg(message, "Magisk v19.0\n\nZIP: /magiskzip\nAPK: /magiskapk\nUninstaller: /magiskun");
				}
				else if (msg.equals("/magisk@montanahelper_bot")) {
					ReplyMsg(message, "Magisk v19.0\n\nZIP: /magiskzip\nAPK: /magiskapk\nUninstaller: /magiskun");
				}
				
				else if (msg.contains("/magiskapk")) {
					
					int fileMessageID = message.getMessageId();
					
					String docId = "BQADAQADjwADCtkQRmJ8_qiir8HLAg";
					Long chatId = message.getChatId();
					try {
						SendFile(chatId, docId, fileMessageID );
					} catch (TelegramApiException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if (msg.contains("/magiskzip")) {
					
					int fileMessageID = message.getMessageId();
					
					String docId = "BQADAQADjgADCtkQRslu2sruOQgIAg";
					Long chatId = message.getChatId();
					try {
						SendFile(chatId, docId, fileMessageID );
					} catch (TelegramApiException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return;
				}
				
				else if (msg.contains("/magiskun")) {
					
					int fileMessageID = message.getMessageId();
					
					String docId = "BQADAQADTwADK9gYRsNaMt6z4Uy-Ag";
					Long chatId = message.getChatId();
					try {
						SendFile(chatId, docId, fileMessageID );
					} catch (TelegramApiException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return;
				}
				

				else if (msg.contains(" bot ")) {
					ReplyMsg(message, "bip bop 🤖");
				}
				else if (msg.startsWith("/help")) {
					if ( message.isUserMessage() == true ) {
						SendMsg(message, "Okay... so did u wanna help 🤔");
						try {Thread.sleep(1500);}catch (InterruptedException e) {e.printStackTrace();}
						SendMsg(message, "hmmm....");
						try {Thread.sleep(2500);}catch (InterruptedException e) {e.printStackTrace();}
						SendMsg(message, "i only have:\n> /roms \n> /magisk");
						return;
					}
					else {
					ReplyMsg(message, "Alright, i'm in-dev and i can't do a lot of things... " + "Send you our ROMs with /roms and get /magisk" );
					return;
				}
			}
		}
			
	}
}

	public void ReadURL(String ROMToGet, Message message) throws MalformedURLException, IOException {
		String romtosend = "404.txt";
		if(ROMToGet == "carbon") {
		  romtosend = "carbon.txt";
		}

		URLConnection connection =  new URL("https://raw.githubusercontent.com/NowDev/MontanaLabs/master/ROMs/" + romtosend ).openConnection();
		   String content = null;
		   try {
		   Scanner scanner = new Scanner(connection.getInputStream());
		   scanner.useDelimiter("\\Z");
		   content = scanner.next();
		   scanner.close();
		   }catch ( Exception ex ) {
		   SendMsg(message, "Error!\n" + ex);
		   }
		   SendMsg(message, content);
		   
		}

	//essa função recebe um objeto de mensagem (que contém o ID do remetente e todos os detalhes que precisamos responder)
	//e uma string simples, e envie uma nova mensagem para o usuário
	public void SendMsg(Message message, String s) {
		SendMessage sendMessage = new SendMessage();
		
		//definir o destino e o texto que queremos enviar
		sendMessage.setChatId(message.getChatId().toString());
		sendMessage.setText(s);
		
		//tenta enviar:
		try {
			execute(sendMessage);
		}
		catch (TelegramApiException e) {
			e.printStackTrace();
		}
		
	}
	
	public void ReplyMsg(Message message, String s) {
		
		SendMessage replyMessage = new SendMessage();
		replyMessage.setChatId(message.getChatId().toString());
		replyMessage.setText(s);
		
		int ss = message.getMessageId();
		replyMessage.setReplyToMessageId(ss);
		
		
		//tenta enviar:
		try {
			execute(replyMessage);
		}
		catch (TelegramApiException e) {
			e.printStackTrace();
		}
		
		
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