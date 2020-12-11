package json;

/**
 * @author duwei7
 * @time 2020/9/18 20:11
 */
public enum BusinessPromotionQueryIdsType {
    Department( "行业"),
    Plan("计划"),
    Resource("资源位");

    private String msg;

    BusinessPromotionQueryIdsType(String msg) {
        this.msg = msg;
    }
}
