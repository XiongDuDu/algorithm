package json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mysql.cj.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author duwei7
 * @time 2020/10/14 11:11
 */
public class Str2Json {
    public static void main(String[] args) {
        XdataPlanBO xdataPlanBO = new XdataPlanBO();
        List<XdataPlanBO> xdataPlanBOList = new ArrayList<>();
        xdataPlanBOList.add(xdataPlanBO);
        xdataPlanBOList.add(xdataPlanBO);

        String json = new Gson().toJson(xdataPlanBOList);
        System.out.println(json);
        List<XdataPlanBO> xdataPlanBOFromJson = Arrays.asList(new Gson().fromJson(json, XdataPlanBO[].class));
        System.out.println("1");

        List<BusinessPromotionDataQuery> queries = new ArrayList<>();
        BusinessPromotionDataQuery query = new BusinessPromotionDataQuery();
        queries.add(query);
        queries.add(query);
        System.out.println(new Gson().toJson(queries));

        List<CreativityScoreQuery> creativityScoreQueries = new ArrayList<>();
        creativityScoreQueries.add(new CreativityScoreQuery());
        creativityScoreQueries.add(new CreativityScoreQuery());
        System.out.println(new Gson().toJson(creativityScoreQueries));

        List<String> duplicated = Arrays.asList("1", "2", "2", "3");
        Set<String> noRepeat = new HashSet<>(duplicated);
        noRepeat.addAll(duplicated);
        System.out.println("");

        //2020-10-13 00:00:00
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //方法一:优势在于可以灵活的设置字符串的形式。
        String tsStr = sdf.format(ts);
        System.out.println(tsStr);  // 2017-01-15 21:17:04
        //方法二
        tsStr = ts.toString();
        System.out.println(tsStr); // 2017-01-15 21:17:04.7

        String res = "";
        System.out.println(res.length());

        String str = "31346,31347,31348,31497,31499,31495,31498,31494,31496,31116,31118,31117,31119,31121,31122,31114,31115,31120,31123,30707,29832,30913,30904,30905,30908,30910,30906,30907,30909,30911,30912,30385,30403,30729,31462,31459,31461,31458,31460,29971,30338,30581,30583,30582,31367,31370,31374,31371,31368,31369,31372,31373,31413,31414,31415,31416,31418,31419,31417,31420,30331,30333,30330,30332,30723,30936,30628,30159,30161,30158,30160,29946,30734,30733,31359,31365,31356,31357,31362,31363,31358,31360,31361,31364,30296,29873,30339,31289,31293,31297,31292,31296,31288,31290,31291,31294,31295,31130,31131,31132,31136,31128,31129,31133,31134,31135,31127,31059,31056,31058,31060,31057,31061,31062,30390,30391,30387,30389,30392,30393,30394,30388,30373,30376,30378,30372,30374,30377,30375,30379,31577,31580,31583,31578,31581,31582,31585,31579,31584,31586,30918,30920,30924,30925,30926,30921,30922,30923,30927,30919,30736,30739,30738,29871,29869,29870,29872,30629,30632,30635,30638,30631,30636,30637,31610,31611,31613,31614,31615,31617,31618,31609,31612,31616,30412,30413,30343,30344,30345,30346,30347,30404,30410,30405,30406,30408,30409,30411,30407,30264,30263,30265,30269,30270,30892,30895,30891,30893,30896,30894,30274,30275,30277,30279,30273,30276,30278,30281,30272,30280,30993,30998,30999,31004,30996,30997,31000,31001,31002,31003,31005,31016,30590,30591,30592,30256,30688,30689,30694,30690,30691,30692,30693,30696,30687,30695,31733,31734,31731,31732";
        List<String> strList = StringUtils.split(str, ",", false);
        System.out.println(strList.size());

        int intsh = 600_000;

        System.out.println(intsh);

        String goodInfo = "{\"key\":\"gid\",\"gid\":\"117339\",\"cid1\":\"14\",\"cid2\":\"173\",\"cid3\":\"1867\",\"119\":\"0.069\",\"117\":\"2233600.0\",\"106\":\"337.0\",\"115\":\"0.0\",\"107\":\"18.0\",\"109\":\"244.0\",\"108\":\"2290.0\",\"111\":\"484.0\",\"110\":\"7016.0\"}";
        Map<String, String> jsonObject = new Gson().fromJson(goodInfo, Map.class);
        if (jsonObject.get("110") != null) {
            long gid = Double.valueOf(jsonObject.get("gid")).longValue();
            long realtimeClick = Double.valueOf((jsonObject.get("110"))).longValue();
            long realtimeExposure = Double.valueOf(jsonObject.get("111")).longValue();
            System.out.println((double) realtimeClick / realtimeExposure);
        }

    }
}
