package json;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author duwei7
 * @time 2020/9/15 13:03
 */
@Data
public class BusinessPromotionDataQuery {
    private Integer departmentId = 1;
    private List<Long> ids = Arrays.asList(1L,2L);
    /**
     * 标明传入的IDs是行业ID 计划ID or 资源位ID
     */
    private BusinessPromotionQueryIdsType idsType = BusinessPromotionQueryIdsType.Department;
    /**
     * 秒级时间戳
     */
    private Long startDay = 0L;
    private Long endDay = 0L;
}
