
package git;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommandeShell {
    public static int execShell(String command) throws IOException{
        Process cmdProc = Runtime.getRuntime().exec(command);


        BufferedReader stdoutReader = new BufferedReader(
                 new InputStreamReader(cmdProc.getInputStream()));
        String line;
        while ((line = stdoutReader.readLine()) != null) {
            System.out.println(line);
        }

        BufferedReader stderrReader = new BufferedReader(
                 new InputStreamReader(cmdProc.getErrorStream()));
        while ((line = stderrReader.readLine()) != null) {
            System.out.println(line);
        }

        return cmdProc.exitValue();
    }
    
    public static List<String> ReadexecShell(String command) throws IOException{
        Process cmdProc = Runtime.getRuntime().exec(command);
        List<String> texte = new ArrayList();
        BufferedReader stdoutReader = new BufferedReader(
                 new InputStreamReader(cmdProc.getInputStream()));
        String line;
        while ((line = stdoutReader.readLine()) != null) {
            System.out.println(line);
            texte.add(line);
        }

        BufferedReader stderrReader = new BufferedReader(
                 new InputStreamReader(cmdProc.getErrorStream()));
        while ((line = stderrReader.readLine()) != null) {
            System.out.println(line);
        }
        
        return texte;
    }
}
