package io.exilius.model.definitions.cache;

import io.exilius.Server;
import io.exilius.util.Stream;
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
        stream.readUnsignedByte();
        configID = stream.readUnsignedWord();
        lsb = stream.readUnsignedByte();
        msb = stream.readUnsignedByte();
    }

    private static VarBitDefinitions cache[];
    public int configID;
    public int lsb;
    public int msb;
    private static Stream stream;
    public static int totalvarbit;
}
