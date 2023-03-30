package Encryption;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class EncryptionDecryption {
    private int randomInt = 42069;
    private Map<Character, String> asciiDict;
    private Set<Integer> usedNumbers;

    public EncryptionDecryption(){
        this.asciiDict = new HashMap<>();
        this.usedNumbers = new HashSet<>();
    }

    public EncryptionDecryption(int randomIn){
        this.randomInt = 42069 + randomIn;
        this.asciiDict = new HashMap<>();
        this.usedNumbers = new HashSet<>();
    }

    public void settingUpTheDict() {
        Random random = new Random(this.randomInt);

        while (asciiDict.size() < 128) {
            for (int i = 0; i < 128; i++){
                while (true) {
                    int num = random.nextInt(100000);
                    if (!usedNumbers.contains(num)) {
                        usedNumbers.add(num);
                        asciiDict.put((char) i, String.format("%05d", num));
                        break;
                    }
                }
            }
        }
    }

    public String getEncrypt(String inString){
        this.settingUpTheDict();
        StringBuilder toBeReturned = new StringBuilder();
        for (int i = 0; i < inString.length(); i++) {
            toBeReturned.append(asciiDict.get(inString.charAt(i)));
        }
        return toBeReturned.toString();
    }

    public String getDecrypt(String inString){
        this.settingUpTheDict();
        StringBuilder toBeReturned = new StringBuilder();

        int length = inString.length();
        int remainder = length % 5;
        if (remainder != 0) {
            int padding = 5 - remainder;
            inString = String.format("%0" + (length + padding) + "d", Integer.parseInt(inString));
            length += padding;
        }

        for (int i = 0; i < length; i = i + 5) {
            for (Map.Entry<Character, String> item : asciiDict.entrySet()) {
                if (item.getValue().equals(inString.substring(i, i+5))) {
                    toBeReturned.append(item.getKey());
                }
            }
        }

        return toBeReturned.toString();
    }
}
