package json;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author duwei7
 * @time 2020/10/12 10:27
 */
@Data
public class XdataPlanBO {
    /**
     * id
     */
    private Long id;

    private Long planId;

    private Long uv = 10L;

    private Long directGuidanceIpv = 10L;

    private Long gmv = 10L;

    private Long click = 10L;

    private Long exposure = 10L;

    private Timestamp gmtModified;


}
