package memory;

import com.github.jonatino.process.Module;
import com.github.jonatino.process.Processes;
import com.github.jonatino.process.Process;

public class memHandler {
    public Process csgoProcess;
    public Module csgoModule;


    public memHandler() throws IllegalStateException{
        csgoProcess = Processes.byName("csgo.exe");
        csgoModule = csgoProcess.findModule("client.dll");
    }







}
