package Utils;

import Enums.Category;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class InputReader {
    public static Scanner scanner = new Scanner(System.in);

    public static int readInt(String message ,int min, int max){
        int choice;
        while (true){
            try {
                System.out.print(message);
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <=max){
                    break;
                }
            }catch (Exception e){
                System.out.println("Please type number between "+min+" and "+ max);
            }
        }
        return choice;
    }

    public static float readFloat(String message, float min, float max){
        float price;

        while (true){
            try{
                System.out.print(message);
                price = Float.parseFloat(scanner.nextLine());
                if (price >= min && price <= max){
                    break;
                }
            }catch (Exception e){
                System.out.println("Please type correct price");
            }
        }
        return price;
    }

    public static String readString(String message){
        System.out.print(message);
        String str = scanner.nextLine();
        return str;
    }

    public static LocalDate readDate(String message){
        LocalDate date;

        while (true){
            System.out.print(message);
            String dateInput = scanner.nextLine();
            try {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                date = LocalDate.parse(dateInput, dateFormat);
                break;
            }
            catch (Exception e){
                System.out.println("Please type date in correct format : dd-MM-yyyy" + " you enter "+ dateInput);
            }
        }
        return date;
    }

    public static Category readCategory(){
        Category category;
        System.out.println("Select a category from followings (type number of category)");
        for (Category cat : Category.values()){
            System.out.println(cat.ordinal() + ". "+ cat);
        }
        int categoryNum =  readInt("", 0, Category.values().length-1);
        category = Category.values()[categoryNum];
        return category;
    }

    public static boolean readBoolean(String message){
        boolean answer;
        while (true){
            System.out.print(message + " (Type 'yes' or 'no')");
            String answerStr = scanner.nextLine();
            if (answerStr.equals("yes")){
                answer = true;
                break;
            }
            else if (answerStr.equals("no")){
                answer = false;
                break;
            }
            else{
                System.out.println("Please type 'yes' or 'no'");
            }
        }
        return answer;
    }
}
