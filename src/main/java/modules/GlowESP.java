package modules;

import memory.MemoryController;
import offsets.Netvars;
import offsets.Signatures;

public class GlowESP {

    private final MemoryController memControl;
    private final Signatures signatures;
    private final Netvars netvars;
    private final int glowManager;


    public GlowESP(MemoryController memControl, Signatures signatures, Netvars netvars) {
        this.memControl = memControl;
        this.signatures = signatures;
        this.netvars = netvars;
        this.glowManager = memControl.readInt(memControl.getAddress() + signatures.dwGlowObjectManager);
    }

    public void doWork(){
        for (int i = 1; i < 32; i++) {
            int entity = memControl.readInt(memControl.getAddress() + signatures.dwEntityList + i * 0x10);

            if (entity != 0) {
                int entityGlow = memControl.readInt(entity + netvars.mIGlowIndex);
                int entityTeamId = memControl.readInt(entity + netvars.mITeamNum);

                if (entityTeamId == 2) {
                    memControl.writeFloat(glowManager + entityGlow * 0x38 + 0x4, 1f);
                    memControl.writeFloat(glowManager + entityGlow * 0x38 + 0x8, 0f);
                    memControl.writeFloat(glowManager + entityGlow * 0x38 + 0xC, 0f);
                    memControl.writeFloat(glowManager + entityGlow * 0x38 + 0x10, 1f);
                    memControl.writeInt(glowManager + entityGlow * 0x38 + 0x24, 1);
                } else if (entityTeamId == 3) {
                    memControl.writeFloat(glowManager + entityGlow * 0x38 + 0x4, 0f);
                    memControl.writeFloat(glowManager + entityGlow * 0x38 + 0x8, 0f);
                    memControl.writeFloat(glowManager + entityGlow * 0x38 + 0xC, 1f);
                    memControl.writeFloat(glowManager + entityGlow * 0x38 + 0x10, 1f);
                    memControl.writeInt(glowManager + entityGlow * 0x38 + 0x24, 1);
                }
            }
        }
    }
}
