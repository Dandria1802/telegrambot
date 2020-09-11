package telegrambot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

public class DandriaBot extends TelegramLongPollingBot {
	List<String> dataFromUser = new ArrayList<String>(); // We store raw data from user in this array.
	ArrayList<String> dataToDB = new ArrayList<String>(); // We store edited data from user in this array.
	long chat_id;

	public void onUpdateReceived(Update update) {
		chat_id = update.getMessage().getChatId();
		SendMessage sendMessage = new SendMessage().setChatId(chat_id)
				.setText(getMessage(update.getMessage().getText()));

		try {
			execute(sendMessage);

		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	public String getMessage(String msg) {
		String startedMsg = "/start"; // If user launches bot
		String stop = "/reset";
		if (msg.equals(stop)) {
			dataFromUser.clear();
			dataToDB.clear();			
		}
		if (msg.equals(startedMsg)) { // We give instructions
			String i = "Каждый";
			String in = "ОТДЕЛЬНЫМ";
			String inf = "СООБЩЕНИЕМ";
			String info1 = "Вводи пять ингридиентов.";
			String info2 = "Привет! ";
			SendMessage sendMessage1 = new SendMessage().setChatId(chat_id).setText(info2 + info1);
			SendMessage sendMessage2 = new SendMessage().setChatId(chat_id).setText(i);
			SendMessage sendMessage3 = new SendMessage().setChatId(chat_id).setText(in);
			SendMessage sendMessage4 = new SendMessage().setChatId(chat_id).setText(inf);
			try {
				execute(sendMessage1);
				execute(sendMessage2);
				execute(sendMessage3);
				execute(sendMessage4);
			} catch (TelegramApiException e) {

				e.printStackTrace(); // Instructions sent
			}
		}
		while (!msg.equals(startedMsg)) {
			if (!msg.equals(stop)) {
			if (dataFromUser.size() < 5) {
				String info2 = "Введён ингридиент  " + " * " + msg + " * "; // Because we cant check �orrectness users
																			// data
				SendMessage sendMessage1 = new SendMessage().setChatId(chat_id).setText(info2);
				try {
					execute(sendMessage1);
				} catch (TelegramApiException e) {

					e.printStackTrace();
				}
				msg = msg.toLowerCase(); // To lower case
				if (msg.contains(",")) { // Delete incorrect symbols
					msg = msg.replaceAll("\\s*,\\s*", "");
				}
				if (msg.contains(" ")) {
					msg = msg.replaceAll("[,]", "");
					msg = msg.trim();
				}
			}
				dataFromUser.add(msg);
				System.out.println(dataFromUser);
				break;
			}
			break;
		}
		if (dataFromUser.size() == 5) {                                     // When we have 5 ingridients
			Multimap<String, String> keyValue = ArrayListMultimap.create(); // Create list where in one key (basket)
																			// store several values
			keyValue.put("0", "хлеб");
			keyValue.put("0", "хлеб белый");
			keyValue.put("0", "батон");
			keyValue.put("1", "масло");
			keyValue.put("1", "масло сливочное");
			keyValue.put("2", "майонез");
			keyValue.put("2", "майонезик");
			keyValue.put("3", "кетчуп");
			keyValue.put("3", "кетчунез");
			keyValue.put("4", "яйцо");
			keyValue.put("4", "яйца");
			keyValue.put("5", "сыр");
			keyValue.put("5", "сыр твёрдый");
			keyValue.put("6", "куриное филе");
			keyValue.put("6", "курица");
			keyValue.put("7", "свинина");
			keyValue.put("7", "ошеек");
			keyValue.put("8", "куринные крылья");
			keyValue.put("8", "крылья куринные");
			keyValue.put("8", "крылья");
			keyValue.put("9", "куринные ножки");
			keyValue.put("9", "куринные голени");
			keyValue.put("10", "картошка");
			keyValue.put("10", "картофель");
			keyValue.put("10", "картоха");
			keyValue.put("11", "колбаса");
			keyValue.put("11", "колбаса копчённая");
			keyValue.put("11", "колбаса копченая");
			keyValue.put("11", "копчённая колбаса");
			keyValue.put("12", "сардели");
			keyValue.put("12", "сардельки");
			keyValue.put("12", "сардели");
			keyValue.put("12", "сардели с сыром");
			keyValue.put("13", "лук");
			keyValue.put("13", "цибуля");
			keyValue.put("13", "лучёк");
			keyValue.put("14", "олия");
			keyValue.put("14", "подсолнечное масло");
			keyValue.put("14", "масло растительное");
			keyValue.put("14", "растительное масло");
			keyValue.put("15", "cоль");
			keyValue.put("15", "йодированная соль");
			keyValue.put("16", "перец");
			keyValue.put("16", "перец чёрный");
			keyValue.put("17", "зелень");
			keyValue.put("17", "петрушка");
			keyValue.put("17", "укроп");
			keyValue.put("18", "макароны");
			keyValue.put("18", "спагетти");
			keyValue.put("18", "спагети");
			keyValue.put("19", "мука");
			keyValue.put("20", "сметана");
			keyValue.put("21", "капуста");
			keyValue.put("21", "капуста белокачаная");
			keyValue.put("22", "перец болгарский");
			keyValue.put("22", "перец зелёный");
			keyValue.put("23", "помидор");
			keyValue.put("23", "помидоры");
			keyValue.put("23", "помидор красный");
			keyValue.put("24", "огурец");
			keyValue.put("24", "огурцы");
			keyValue.put("24", "огурец свежий");
			keyValue.put("25", "крупа манная");
			keyValue.put("25", "манная крупа");
			keyValue.put("25", "манка");
			keyValue.put("26", "сыр плавленный");
			keyValue.put("26", "сыр");
			keyValue.put("26", "плавленный сыр");
			keyValue.put("27", "морковь");
			keyValue.put("27", "морковка");
			keyValue.put("28", "сосиски");
			keyValue.put("28", "сосиски с сыром");
			keyValue.put("28", "сосиски копчёные");
			keyValue.put("29", "вода");
			keyValue.put("30", "дрожжи");
			keyValue.put("30", "дрожжи сухие");
			keyValue.put("30", "сухие дрожжи");
			keyValue.put("31", "сахар");
			keyValue.put("33", "рис");
			keyValue.put("33", "рисовая крупа");
			keyValue.put("34", "гречка");
			keyValue.put("34", "гречневая крупа");
			keyValue.put("35", "куриный фарш");
			keyValue.put("35", "фарш куриный");
			keyValue.put("36", "рыба");
			keyValue.put("37", "шпроты");
			keyValue.put("38", "паштет");
			keyValue.put("39", "сало");
			keyValue.put("40", "паштет домашний");
			keyValue.put("41", "горох");
			keyValue.put("41", "гороховая каша");
			keyValue.put("42", "бекон");
			keyValue.put("43", "фарш");
			keyValue.put("44", "кукуруза");
			keyValue.put("44", "банка кукурузы");
			keyValue.put("45", "горошек");
			keyValue.put("45", "консервированный горошек");
			keyValue.put("45", "горошек консервированный");
			keyValue.put("46", "чеснок");
			keyValue.put("47", "сухари");
			keyValue.put("47", "сухари панировочные");
			keyValue.put("47", "панировочные сухари");
			keyValue.put("48", "томат");
			keyValue.put("48", "томатная паста");
			keyValue.put("48", "паста томатная");
			keyValue.put("49", "молоко");
			keyValue.put("50", "ветчина");
			keyValue.put("51", "солёные огурцы");
			keyValue.put("51", "огурцы солёные");
			keyValue.put("51", "огурец солёный");
			keyValue.put("51", "солёный огурец");
			keyValue.put("52", "куринная печень");
			keyValue.put("52", "печень куринная");
			keyValue.put("52", "печень куриная");
			keyValue.put("52", "куриная печень");
			keyValue.put("60", "колбаса варенная");
			keyValue.put("60", "варенная колбаса");
			keyValue.put("60", "вареная колбаса");
			keyValue.put("60", "колбаса вареная");
			keyValue.put("53", "банан");
			keyValue.put("53", "бананы");
			keyValue.put("54", "апельсин");
			keyValue.put("54", "апельсины");
			keyValue.put("55", "киви");
			keyValue.put("56", "яблоко");
			keyValue.put("56", "яблоки");
			keyValue.put("57", "виноград");
			keyValue.put("58", "персик");
			keyValue.put("58", "персики");
			keyValue.put("59", "кефир");
			Multimap<String, String> invertedKeyValue = Multimaps.invertFrom(keyValue,
					ArrayListMultimap.<String, String>create());
			// Because i couldnt get key from multimap(keyValue), i invert my
			// multimap(keyValue)
			for (int i = 0; i <= dataFromUser.size() - 1; i++) { // ;)
				String mp = invertedKeyValue.get(dataFromUser.get(i)).toString(); // Here i get key from inverted
																					// multimap, change to string "mp"
				String delBrack = mp.substring(1, mp.length() - 1); // Delete brackets
				dataToDB.add(delBrack);
				System.out.println(dataToDB);// Add clear number(that is id of ingridient in DataBase) to array dataToDb
			}
			dataFromUser.clear(); // Clear array dataFromUser
			String query = "select recipe, id1, id2, id3, id4, id5, id6, id7, id8, id9, url from recipes_table";
			ArrayList<String> list = null;
			ArrayList<String> listid = null; // In listId we store all id from database string
			try {
				System.out.println("мы попытались подключиться");
				String url = "jdbc:mysql://us-cdbr-east-02.cleardb.com/heroku_a30e7fd2176c426?useSSL=false&serverTimezone=" // Open connection
						+ "UTC&useLegacyDatetimeCode=false?characterEncoding=utf8";
				System.out.println("url");
				String name = "b61d5817452261";
				System.out.println("name");
				String password = "156b12cf7c4e28b";
				System.out.println("pass");
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("мы подключились");
				try (Connection conn = DriverManager.getConnection(url, name, password)) {
					try {
						System.out.println("Подключились");
						list = new ArrayList<String>();
						listid = new ArrayList<String>();
						PreparedStatement st = null;
						st = conn.prepareStatement(query);
						ResultSet rs = st.executeQuery(query);
						rs = st.executeQuery();
						int size = 0; // ====Int ****size**** here = 0 ======
						while (rs.next() && size == 0) {
							// list.add(rs.getString("recipe"));
							listid.add(rs.getString("id1"));
							listid.add(rs.getString("id2"));
							listid.add(rs.getString("id3"));
							listid.add(rs.getString("id4"));
							listid.add(rs.getString("id5"));
							listid.add(rs.getString("id6"));
							listid.add(rs.getString("id7"));
							listid.add(rs.getString("id8"));
							listid.add(rs.getString("id9"));
							if (listid.containsAll(dataToDB)) { // Compare all data
								list.add(rs.getString("recipe"));
								String recipeUrl = rs.getString("url");
								size++; // ====If we find recipe int ****size**** = 1=====
								String info = "Приятного аппетита :)   ";
								SendMessage sendMessage1 = new SendMessage().setChatId(chat_id)
										.setText(info + recipeUrl);
								try {
									execute(sendMessage1);
								} catch (TelegramApiException e) {

									e.printStackTrace();
								}
							}
							listid.clear();
						}
						if (size == 0) { // *size* = 0, dont have recipe
							String refuse = "Нет совпадений :(    "; // say it
							String advise = "Попробуй другие ингридиенты :)";
							SendMessage sendMessage1 = new SendMessage().setChatId(chat_id).setText(refuse + advise);
							try {
								execute(sendMessage1);
							} catch (TelegramApiException e) {

								e.printStackTrace();
							}
						}
						dataToDB.clear();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

		return "";

	}

	public String getBotUsername() {
		return "DandriaBot";
	}

	@Override
	public String getBotToken() {
		return "1293235080:AAGnS58hmQNAHBu5zAwHZ0wGSm9A6VXfjBg";
	}
}

