package io.hologram.usage_parser.objects;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode
public class Usage {
    private Integer id;
    private Integer mnc;
    private Integer bytesUsed;
    private String dmcc;
    private Integer cellId;
    private String ip;
}
