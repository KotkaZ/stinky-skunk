package memory;

import com.github.jonatino.process.Module;
import com.github.jonatino.process.Processes;
import com.github.jonatino.process.Process;

public class MemoryController {
    private final Process csgoProcess;
    private final Module csgoModule;


    public MemoryController() throws IllegalStateException{
        csgoProcess = Processes.byName("csgo.exe");
        csgoModule = csgoProcess.findModule("client.dll");
    }


    public long getAddress() {
        return csgoModule.address();
    }

    public int readInt(long address){
        return csgoProcess.readInt(address);
    }

    public void writeFloat(long address, float value){
        csgoProcess.writeFloat(address,value);
    }

    public void writeInt(long address, int value){
        csgoProcess.writeInt(address, value);
    }

}
