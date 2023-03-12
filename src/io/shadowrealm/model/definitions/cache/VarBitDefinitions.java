package io.shadowrealm.model.definitions.cache;

import io.shadowrealm.Server;
import io.shadowrealm.util.Stream;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class VarBitDefinitions {

    public static final void unpackConfig() {
        try {
            stream = new Stream(FileUtils.readFileToByteArray(new File(Server.getDataDirectory() + "/data/varbit.dat")));
            totalvarbit = stream.readUnsignedWord();
            if (cache == null)
                cache = new VarBitDefinitions[totalvarbit];
            for(int j = 0; j < totalvarbit; j++)
            {
                if (cache[j] == null)
                    cache[j] = new VarBitDefinitions();
                cache[j].readValues(stream);
            }
            if (stream.currentOffset != stream.buffer.length) {
                System.out.println("varbit load mismatch");
            }
            System.out.println("Successfully loaded: " + totalvarbit + " Varbit definitions.");
        } catch (Exception e) {
            System.err.println("An error has occurred whilst loading Varbit definitions!");
            e.printStackTrace();
        }
    }

    private void readValues(Stream stream) {
        int opcode = stream.readUnsignedByte();
        if (opcode == 0) {
            return;
        } else if (opcode == 1) {
            configID = stream.readUnsignedWord();
            lsb = stream.readUnsignedByte();
            msb = stream.readUnsignedByte();
        } else {
            System.out.println("Invalid varbit opcode: " + opcode);
        }
    }

    private static VarBitDefinitions cache[];
    public int configID;
    public int lsb;
    public int msb;
    private static Stream stream;
    public static int totalvarbit;
}
