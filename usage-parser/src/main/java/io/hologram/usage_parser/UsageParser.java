package io.hologram.usage_parser;

import java.util.ArrayList;
import java.util.List;
import io.hologram.usage_parser.objects.Usage;

public class UsageParser {

    private List<Usage> results = new ArrayList<>();

    public List<Usage> parse(List<String> usageString) {

        for (String string : usageString) {
            parseString(string);
        }

        return results;
    }

    public void parseString(String usage) {

        String[] stringArray = usage.split(",");
        int id = Integer.parseInt(stringArray[0]);

        if (this.idDecoder(id) == "extended" && stringArray.length == 5) {
            Usage usageExtended = Usage.builder()
                    .id(id)
                    .dmcc(stringArray[1].toString())
                    .mnc(Integer.parseInt(stringArray[2]))
                    .bytesUsed(Integer.parseInt(stringArray[3]))
                    .cellId(Integer.parseInt(stringArray[4]))
                    .build();

            results.add(usageExtended);
        }

        if (this.idDecoder(id) == "basic" && stringArray.length == 2) {
            Usage usageBasic = Usage.builder()
                    .id(id)
                    .bytesUsed(Integer.parseInt(stringArray[1]))
                    .build();

            results.add(usageBasic);
        }

        if (this.idDecoder(id) == "hex" && stringArray.length == 2) {
            String hexString = stringArray[1];

            Usage usageHex = Usage.builder()
                    .id(id)
                    .mnc(Integer.parseInt(hexString.substring(0, 4), 16))
                    .bytesUsed(Integer.parseInt(hexString.substring(4, 8), 16))
                    .cellId(Integer.parseInt(hexString.substring(8, 16), 16))
                    .ip(String.valueOf(Integer.parseInt(hexString.substring(16, 18), 16) +
                            "." + Integer.parseInt(hexString.substring(18, 20), 16) + "." +
                            Integer.parseInt(hexString.substring(20, 22), 16) + "." +
                            Integer.parseInt(hexString.substring(22, 24), 16)))
                    .build();

            results.add(usageHex);
        }
    }

    public String idDecoder(int intId) {
        if (intId % 10 == 4) {
            return "extended";
        } else if (intId % 10 == 6) {
            return "hex";
        } else {
            return "basic";
        }
    }

}
