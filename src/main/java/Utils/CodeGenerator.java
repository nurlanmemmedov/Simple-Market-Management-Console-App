package Utils;

public class CodeGenerator {

    public static String generateRandomCode(String head, int length){
        StringBuilder code = new StringBuilder(head);
        for (int i =0; i < length; i++){
            code.append(generateRandomNumber(1, 10));
        }

        return code.toString();
    }

    public static int generateRandomNumber(int min, int max){
        return (int)(Math.random()*(max-min)+ min);
    }
}
