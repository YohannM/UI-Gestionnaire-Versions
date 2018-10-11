/*
 */
package tools;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author yohan
 */
public class PathTools {
    public static String nettoyerPath(String path)
    {
        String path2 = path.replace("\\", "/");
        String[] tab =  path2.split("/");
        
        ArrayList<String> lienDec = new ArrayList();
        
        for(String str: tab)
            lienDec.add(str);
        
        for(int i = 0; i< lienDec.size(); i++)
        {
            if (lienDec.get(i).contains(" "))
                lienDec.set(i, "\"" + lienDec.get(i) + "\"");
        }
        
        
        String pathCorr = concatWithChar(lienDec, '/');
        return pathCorr.replace("/", "\\");
    }
    
    private static String concatWithChar(Collection<String> words, char c) {
    StringBuilder wordList = new StringBuilder();
    for (String word : words) {
        wordList.append(word + c);
    }
    return new String(wordList.deleteCharAt(wordList.length() - 1));
}
}
