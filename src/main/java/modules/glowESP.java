package modules;

import memory.memHandler;
import offsets.Netvars;
import offsets.Signatures;

public class glowESP {

    public glowESP(memHandler memHandler, Signatures signatures, Netvars netvars) {
        while(true){
            int glowManager = memHandler.csgoProcess.readInt(memHandler.csgoModule.address() + signatures.dwGlowObjectManager);

            int entity = 0;
            for (int i = 1; i < 32; i++) {
                entity = memHandler.csgoProcess.readInt(memHandler.csgoModule.address() + signatures.dwEntityList + i * 0x10);

                int entityTeamId = 0;
                int entityGlow = 0;

                if (entity != 0) {
                    entityGlow = memHandler.csgoProcess.readInt(entity + netvars.mIGlowIndex);
                    entityTeamId = memHandler.csgoProcess.readInt(entity + netvars.mITeamNum);

                    if (entityTeamId == 2) {
                        memHandler.csgoProcess.writeFloat(glowManager + entityGlow * 0x38 + 0x4, 1f);
                        memHandler.csgoProcess.writeFloat(glowManager + entityGlow * 0x38 + 0x8, 0f);
                        memHandler.csgoProcess.writeFloat(glowManager + entityGlow * 0x38 + 0xC, 0f);
                        memHandler.csgoProcess.writeFloat(glowManager + entityGlow * 0x38 + 0x10, 1f);
                        memHandler.csgoProcess.writeInt(glowManager + entityGlow * 0x38 + 0x24, 1);
                    } else if (entityTeamId == 3) {
                        memHandler.csgoProcess.writeFloat(glowManager + entityGlow * 0x38 + 0x4, 0f);
                        memHandler.csgoProcess.writeFloat(glowManager + entityGlow * 0x38 + 0x8, 0f);
                        memHandler.csgoProcess.writeFloat(glowManager + entityGlow * 0x38 + 0xC, 1f);
                        memHandler.csgoProcess.writeFloat(glowManager + entityGlow * 0x38 + 0x10, 1f);
                        memHandler.csgoProcess.writeInt(glowManager + entityGlow * 0x38 + 0x24, 1);
                    }
                }
            }


        }

    }

    /*
    dwEntityList = (0x4D4B104)
    dwGlowObjectManager = (0x5292F20)
    m_iGlowIndex = (0xA428)
    m_iTeamNum = (0xF4)

    print("Diamond has launched.")
    pm = pymem.Pymem("csgo.exe")
    client = pymem.process.module_from_name(pm.process_handle, "client.dll").lpBaseOfDll

    while True:
        glow_manager = pm.read_int(client + dwGlowObjectManager)

        for i in range(1, 32):  # Entities 1-32 are reserved for players.
            entity = pm.read_int(client + dwEntityList + i * 0x10)

            if entity:
                entity_team_id = pm.read_int(entity + m_iTeamNum)
                entity_glow = pm.read_int(entity + m_iGlowIndex)

                if entity_team_id == 2:  # Terrorist
                    pm.write_float(glow_manager + entity_glow * 0x38 + 0x4, float(1))   # R
                    pm.write_float(glow_manager + entity_glow * 0x38 + 0x8, float(0))   # G
                    pm.write_float(glow_manager + entity_glow * 0x38 + 0xC, float(0))   # B
                    pm.write_float(glow_manager + entity_glow * 0x38 + 0x10, float(1))  # Alpha
                    pm.write_int(glow_manager + entity_glow * 0x38 + 0x24, 1)           # Enable glow

                elif entity_team_id == 3:  # Counter-terrorist
                    pm.write_float(glow_manager + entity_glow * 0x38 + 0x4, float(0))   # R
                    pm.write_float(glow_manager + entity_glow * 0x38 + 0x8, float(0))   # G
                    pm.write_float(glow_manager + entity_glow * 0x38 + 0xC, float(1))   # B
                    pm.write_float(glow_manager + entity_glow * 0x38 + 0x10, float(1))  # Alpha
                    pm.write_int(glow_manager + entity_glow * 0x38 + 0x24, 1)           # Enable glow
     */
}
