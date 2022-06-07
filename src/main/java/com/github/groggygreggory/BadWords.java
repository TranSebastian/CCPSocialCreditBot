package com.github.groggygreggory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class BadWords {
    String [] badwords;
    String [] goodWords;

    public BadWords () throws FileNotFoundException {
        Scanner reader = new Scanner(new File("badwords"));
        badwords = reader.nextLine().split(", ");
        reader.close();

        Scanner reader2 = new Scanner(new File("goodwords"));
        goodWords = reader2.nextLine().split(", ");
        reader2.close();

    }

    public boolean containsBadPhrase (String message) {
        for (int i =0; i< badwords.length; i++){
            if (message.toLowerCase().contains(badwords[i])){
                return true;
            }
        }
        return false;
    }

    public boolean containsGoodPhrase (String message) {
        for (int i =0; i< goodWords.length; i++){
            if (message.toLowerCase().contains(goodWords[i])){
                return true;
            }
        }
        return false;
    }
}
