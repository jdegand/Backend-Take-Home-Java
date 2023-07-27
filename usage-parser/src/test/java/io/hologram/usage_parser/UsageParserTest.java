package io.hologram.usage_parser;

import io.hologram.usage_parser.objects.Usage;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for UsageParser
 */
public class UsageParserTest extends TestCase
{
    private final UsageParser usageParser = new UsageParser();
    //private static final String delimiter = ", ";  //included in the source code but not used

    /**
     * Tests a single usage string with an ID that does not end in 4 or 6
     */
    public void testSingleBasicParsingScheme() {
        String usageString = "7291,293451";

        List<String> usageList = new ArrayList<>();
        usageList.add(usageString);

        Usage expectedUsage = Usage.builder()
                .id(7291)
                .bytesUsed(293451)
                .build();

        List<Usage> result = usageParser.parse(usageList);

        assertEquals(1, result.size());
        assertEquals(expectedUsage, result.get(0));
    }

    /**
     * Tests a single extended usage string with an ID that ends in 4
     */
    public void testSingleExtendedParsingScheme() {
        String usageString = "7194,b33,394,495593,192";

        List<String> usageList = new ArrayList<>();
        usageList.add(usageString);

        Usage expectedUsage = Usage.builder()
                .id(7194)
                .mnc(394)
                .bytesUsed(495593)
                .dmcc("b33")
                .cellId(192)
                .build();

        List<Usage> result = usageParser.parse(usageList);

        assertEquals(1, result.size());
        assertEquals(expectedUsage, result.get(0));
    }

    /**
     * Tests a single hex usage string with an ID that ends in 6
     */
    public void testSingleHexParsingScheme() {
        String usageString = "316,0e893279227712cac0014aff";

        List<String> usageList = new ArrayList<>();
        usageList.add(usageString);

        Usage expectedUsage = Usage.builder()
                .id(316)
                .mnc(3721)
                .bytesUsed(12921)
                .ip("192.1.74.255")
                .cellId(578228938)
                .build();

        List<Usage> result = usageParser.parse(usageList);

        assertEquals(1, result.size());
        assertEquals(expectedUsage, result.get(0));
    }

    /**
     * Test an array of mixed type usage strings
     */
    public void testArrayOfMixedParsingSchemes() {
        String usageString1 = "4,0d39f,0,495594,214";
        String usageString2 = "16,be833279000000c063e5e63d";
        String usageString3 = "9991,2935";

        List<String> usageList = new ArrayList<>();
        usageList.add(usageString1);
        usageList.add(usageString2);
        usageList.add(usageString3);

        Usage expectedUsage1 = Usage.builder()
                .id(4)
                .mnc(0)
                .bytesUsed(495594)
                .dmcc("0d39f")
                .cellId(214)
                .build();

        Usage expectedUsage2 = Usage.builder()
                .id(16)
                .mnc(48771)
                .bytesUsed(12921)
                .cellId(192)
                .ip("99.229.230.61")
                .build();

        Usage expectedUsage3 = Usage.builder()
                .id(9991)
                .bytesUsed(2935)
                .build();

        List<Usage> result = usageParser.parse(usageList);

        assertEquals(3, result.size());
        assertThat(result, containsInAnyOrder(expectedUsage1, expectedUsage2, expectedUsage3));
    }
}
