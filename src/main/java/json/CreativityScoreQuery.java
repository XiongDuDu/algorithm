package json;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author duwei7
 * @time 2020/9/21 17:35
 */
@Data
public class CreativityScoreQuery {
    private Integer resourceId = 12;
    private List<Long> ideaIds = Arrays.asList(1L, 2L);
}