package net.tifoha.interview_DataScientist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import com.opencsv.ResultSetHelperService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.text.StrBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * @author Vitalii Sereda
 */
public class DataExtractor extends CSVWriter {

    private final Connection conn;

    public DataExtractor(Writer writer, char separator, char quotechar, char escapechar, String lineEnd, Connection conn) throws SQLException {
        super(writer, separator, quotechar, escapechar, lineEnd);
        this.conn = conn;
    }

    @Override
    public void close() throws IOException {
        super.close();
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        String sql;
        Locale.setDefault(Locale.US);
//        sql = "select campaign_id,\n" +
//                "        channel_id,\n" +
//                "        short_line_item_id,\n" +
//                "        round,\n" +
//                "              DATE_FORMAT(cycle_date, '%Y-%m-%dT%T') cycle_date,\n" +
//                "       initial_budget,\n" +
//                "       budget_id,\n" +
//                "       DATE_FORMAT(budget_start_date, '%Y-%m-%dT%T')budget_start_date,\n" +
//                "       DATE_FORMAT(budget_end_date, '%Y-%m-%dT%T')budget_end_date,\n" +
//                "        initial_duations_hours,\n" +
//                "        residual_duations_hours,\n" +
//                "        daily_engagements                    dEng,\n" +
//                "        daily_spend                          dSpend,\n" +
//                "        budget_time_engagements              bEng,\n" +
//                "        budget_time_spend                    bSpend,\n" +
//                "        lifetime_time_engagements            lEng,\n" +
//                "        lifetime_time_spend                  lSpend,\n" +
//                "        case when cpe = 999.99 then null else CPE end lCpe,\n" +
//                "        case when daily_cpe = 999.99 then null else daily_cpe end dCpe,\n" +
//                "        residual_budget_before_update,\n" +
//                "        residual_budget_after_update,\n" +
//                "        status_before_optimization           beforeStatus,\n" +
//                "        status_after_optimization            afterStatus,\n" +
//                "        line_item_budget_before_optimization beforeBudget,\n" +
//                "        line_item_budget_after_optimization  afterBudget,\n" +
//                "        optimization_algorithm,\n" +
//                "        action                               action\n" +
//                "from OPTIMIZATION_LOG\n" +
//                "where CAMPAIGN_ID in\n" +
//                "      ('21455', '21459', '21460', '21463', '21469', '21543', '21544', '21545', '21593', '21680', '21681', '21701',\n" +
//                "       '21823', '21827', '21872', '21883', '21896', '21900', '21903', '21909', '21910', '21911', '21925', '21927',\n" +
//                "       '21995')\n"
////                + "limit 1000000\n"
//        ;
//        int offset = 1_000_000;
//        for (int i = 48_000_000, x = 48; i < 81_000_000; i += offset, x++) {
//            String limit = " LIMIT " + i + "," + offset;
//            System.out.println(limit);
//            String sql1 = sql + limit;
//            String fileName = format("%s_%03d%s", "optimization_log", x, ".csv");
//            try (Connection conn = DriverManager.getConnection("jdbc:mysql://benjioptimizerdb-prod.inpwrd.net:3306/BENJI_OPTIMIZER",
//                    "benji-optimizer-user",
//                    "inPwrd01.");
//                 DataExtractor dataExtractor = new DataExtractor(
//                         new FileWriter(fileName),
//                         ',', '"', '"', "\n", conn)) {
//                System.out.println(LocalDateTime.now());
//                dataExtractor.write(sql1);
//            }
//        }
        sql = "select\n" +
                "       LI.SHORT_LINE_ITEM_ID,\n" +
                "       DETAIL_TYPE,\n" +
                "       DETAIL_VALUE,\n" +
                "       LI.CHANNEL_ID\n" +
                "from LINE_ITEM as LI\n" +
                "         left join LINE_ITEM_DETAIL LD\n" +
                "                   on LI.CAMPAIGN_ID in\n" +
//                "                      ('21455', '21459', '21460', '21463', '21469', '21543', '21544', '21545', '21593', '21680', '21681', '21701', '21823', '21827', '21872', '21883', '21896', '21900', '21903', '21909', '21910', '21911', '21925', '21927', '21995')\n" +
                "                      (20004, 20017, 20021, 20022, 20023, 20024, 20025, 20026, 20027, 20028, 20029, 20030, 20031, 20032, 20033, 20034, 20035, 20036, 20037, 20038, 20040, 20041, 20042, 20043, 20044, 20045, 20046, 20047, 20048, 20049, 20050, 20051, 20052, 20053, 20054, 20055, 20056, 20057, 20058, 20059, 20060, 20061, 20066, 20067, 20068, 20069, 20071, 20072, 20073, 20074, 20075, 20076, 20077, 20078, 20079, 20080, 20086, 20087, 20088, 20089, 20090, 20091, 20092, 20093, 20094, 20095, 20096, 20097, 20098, 20099, 20100, 20104, 20106, 20108, 20112, 20113, 20117, 20118, 20126, 20127, 20128, 20129, 20130, 20131, 20132, 20133, 20134, 20135, 20136, 20137, 20138, 20139, 20140, 20141, 20142, 20143, 20144, 20145, 20146, 20147, 20148, 20149, 20150, 20151, 20152, 20153, 20154, 20155, 20156, 20157, 20158, 20159, 20160, 20161, 20162, 20163, 20164, 20165, 20166, 20167, 20168, 20169, 20170, 20171, 20172, 20173, 20174, 20175, 20176, 20190, 20191, 20192, 20193, 20194, 20195, 20197, 20198, 20199, 20200, 20201, 20202, 20203, 20204, 20205, 20206, 20207, 20208, 20209, 20213, 20214, 20215, 20217, 20220, 20222, 20224, 20226, 20227, 20228, 20229, 20232, 20233, 20235, 20236, 20237, 20238, 20239, 20240, 20241, 20242, 20244, 20255, 20260, 20264, 20269, 20272, 20273, 20278, 20279, 20280, 20291, 20292, 20296, 20312, 20319, 20337, 20345, 20353, 20364, 20365, 20366, 20367, 20369, 20370, 20371, 20384, 20389, 20390, 20408, 20409, 20410, 20414, 20415, 20422, 20423, 20424, 20425, 20428, 20429, 20441, 20459, 20465, 20467, 20468, 20476, 20478, 20481, 20482, 20483, 20487, 20507, 20508, 20509, 20510, 20516, 20517, 20520, 20524, 20530, 20538, 20542, 20545, 20556, 20560, 20561, 20562, 20574, 20576, 20578, 20593, 20599, 20600, 20601, 20626, 20641, 20643, 20644, 20647, 20648, 20663, 20670, 20671, 20672, 20681, 20682, 20690, 20693, 20694, 20695, 20696, 20708, 20713, 20722, 20723, 20724, 20725, 20735, 20741, 20743, 20744, 20745, 20757, 20770, 20774, 20795, 20798, 20799, 20801, 20817, 20818, 20820, 20821, 20824, 20829, 20830, 20836, 20855, 20857, 20858, 20864, 20869, 20871, 20875, 20892, 20899, 20901, 20902, 20905, 20906, 20914, 20917, 20918, 20919, 20921, 20926, 20934, 20935, 20937, 20938, 20949, 20957, 20965, 20966, 20968, 20970, 20971, 20972, 20973, 20978, 20980, 20981, 20982, 20984, 20985, 20997, 21021, 21033, 21039, 21041, 21042, 21060, 21061, 21071, 21081, 21090, 21091, 21105, 21107, 21108, 21117, 21130, 21134, 21135, 21136, 21153, 21154, 21155, 21156, 21157, 21158, 21169, 21170, 21171, 21192, 21201, 21206, 21207, 21208, 21209, 21210, 21211, 21212, 21213, 21214, 21215, 21226, 21229, 21244, 21245, 21250, 21260, 21268, 21269, 21277, 21287, 21289, 21290, 21292, 21296, 21306, 21310, 21319, 21320, 21321, 21332, 21343, 21345, 21346, 21353, 21354, 21355, 21356, 21357, 21358, 21359, 21360, 21361, 21362, 21363, 21364, 21365, 21366, 21367, 21368, 21369, 21370, 21371, 21374, 21381, 21382, 21387, 21393, 21396, 21397, 21414, 21428, 21439, 21455, 21457, 21459, 21460, 21463, 21465, 21466, 21469, 21485, 21498, 21513, 21514, 21535, 21543, 21544, 21545, 21557, 21562, 21568, 21572, 21573, 21578, 21579, 21589, 21593, 21594, 21597, 21605, 21609, 21610, 21631, 21650, 21657, 21660, 21671, 21673, 21674, 21676, 21677, 21680, 21681, 21684, 21685, 21687, 21689, 21699, 21701, 21702, 21703, 21708, 21709, 21713, 21720, 21750, 21755, 21756, 21763, 21764, 21774, 21804, 21809, 21811, 21823, 21824, 21827, 21837, 21839, 21855, 21863, 21867, 21868, 21869, 21871, 21872, 21876, 21877, 21881, 21883, 21888, 21896, 21900, 21903, 21904, 21906, 21908, 21909, 21910, 21911, 21925, 21927, 21934, 21941, 21942, 21944, 21945, 21948, 21953, 21954, 21955, 21960, 21972, 21976, 21980, 21984, 21986, 21987, 21988, 21989, 21991, 21995, 22037, 22040, 22066, 22067, 22069, 22072, 22076, 22079, 22089, 22419)\n" +
                "                       and LI.SHORT_LINE_ITEM_ID = LD.SHORT_LINE_ITEM_ID\n" +
                "where DETAIL_TYPE like 'target%'\n" +
                "   or DETAIL_TYPE = 'IABCategory'\n" +
                "   or DETAIL_TYPE = 'headline'\n" +
                "   or DETAIL_TYPE = 'storySummary'\n"
//                +"   limit 100"
        ;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://shareddb02-prod.inpwrd.net:3306/BENJI_CHANNELS",
                "benji-channels-user",
                "inPwrd01.");
             DataExtractor dataExtractor = new DataExtractor(
                     new FileWriter("line_items2.csv"),
                     ',', '"', '"', "\n", conn)) {
            System.out.println(LocalDateTime.now());
            dataExtractor.setResultService(new JsonResultSetHelperService());
            dataExtractor.write2(sql);
        }
    }

    private void write(String sql) throws Exception {
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            rs.last();
            int rowsCount = rs.getRow();
            rs.beforeFirst();
            int linesWritten = 0;
            int percent = (int) (((double) linesWritten / rowsCount) * 100);
            this.writeColumnNames(rs);

            while (rs.next()) {
                this.writeNext(this.resultService().getColumnValues(rs, true));
                ++linesWritten;

                int newPercent = (int) (((double) linesWritten / rowsCount) * 100);

                if (newPercent > percent) {
                    percent = newPercent;
                    System.out.println(LocalDateTime.now() + ": " + percent + "%");
                }
            }
        }
    }

    private void write2(String sql) throws Exception {
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            rs.last();
            int rowsCount = rs.getRow();
            rs.beforeFirst();
            int linesWritten = 0;
            int percent = (int) (((double) linesWritten / rowsCount) * 100);
            this.writeColumnNames(rs);

            while (rs.next()) {
                String[] values = this.resultService().getColumnValues(rs, true);
                this.writeNext(values);
                ++linesWritten;

                int newPercent = (int) (((double) linesWritten / rowsCount) * 100);

                if (newPercent > percent) {
                    percent = newPercent;
                    System.out.println(LocalDateTime.now() + ": " + percent + "%");
                }
            }
        }
    }

    private static class JsonResultSetHelperService extends ResultSetHelperService {
        ObjectMapper mapper = new ObjectMapper();

        @Override
        public String[] getColumnValues(ResultSet rs, boolean trim, String dateFormatString, String timeFormatString) throws SQLException, IOException {
            List<String> values = new ArrayList<>();
            ResultSetMetaData metadata = rs.getMetaData();

            for (int i = 1; i <= metadata.getColumnCount(); i++) {
                String value = this.getColumnValue(rs, metadata.getColumnType(i), i, trim, dateFormatString, timeFormatString);
//                value = value.replace("\n", "");
                if (Objects.equals("DETAIL_VALUE", metadata.getColumnName(i))) {
                    value = Arrays.stream(value.split(";"))
                            .map(s -> {
                                if (s.contains("{")) {
                                    try {
                                        Map<String, String> map = mapper.readValue(s, Map.class);
                                        return map.getOrDefault("countryId", map.get("interestName"));
                                    } catch (IOException e) {
                                        return s;
                                    }
                                } else {
                                    return s;
                                }
                            })
                            .collect(Collectors.joining(","));
                } else {
                }
                values.add(value);
            }

            String[] valueArray = new String[values.size()];
            return values.toArray(valueArray);

        }

        private String getColumnValue(ResultSet rs, int colType, int colIndex, boolean trim, String dateFormatString, String timestampFormatString)
                throws SQLException, IOException {

            String value = "";

            switch (colType) {
                case Types.BIT:
                case Types.JAVA_OBJECT:
// Once Java 7 is the minimum supported version.
//            value = Objects.toString(rs.getObject(colIndex), "");
                    value = ObjectUtils.toString(rs.getObject(colIndex), "");
                    break;
                case Types.BOOLEAN:
// Once Java 7 is the minimum supported version.
//            value = Objects.toString(rs.getBoolean(colIndex));
                    value = ObjectUtils.toString(rs.getBoolean(colIndex));
                    break;
                case Types.NCLOB: // todo : use rs.getNClob
                case Types.CLOB:
                    Clob c = rs.getClob(colIndex);
                    if (c != null) {
                        StrBuilder sb = new StrBuilder();
                        sb.readFrom(c.getCharacterStream());
                        value = sb.toString();
                    }
                    break;
                case Types.BIGINT:
// Once Java 7 is the minimum supported version.
//            value = Objects.toString(rs.getLong(colIndex));
                    value = ObjectUtils.toString(rs.getLong(colIndex));
                    break;
                case Types.DECIMAL:
                case Types.REAL:
                case Types.NUMERIC:
// Once Java 7 is the minimum supported version.
//            value = Objects.toString(rs.getBigDecimal(colIndex), "");
                    value = ObjectUtils.toString(rs.getBigDecimal(colIndex), "");
                    break;
                case Types.DOUBLE:
// Once Java 7 is the minimum supported version.
//            value = Objects.toString(rs.getDouble(colIndex));
                    value = ObjectUtils.toString(rs.getDouble(colIndex));
                    break;
                case Types.FLOAT:
// Once Java 7 is the minimum supported version.
//            value = Objects.toString(rs.getFloat(colIndex));
                    value = ObjectUtils.toString(rs.getFloat(colIndex));
                    break;
                case Types.INTEGER:
                case Types.TINYINT:
                case Types.SMALLINT:
// Once Java 7 is the minimum supported version.
//            value = Objects.toString(rs.getInt(colIndex));
                    value = ObjectUtils.toString(rs.getInt(colIndex));
                    break;
                case Types.DATE:
                    java.sql.Date date = rs.getDate(colIndex);
                    if (date != null) {
                        SimpleDateFormat df = new SimpleDateFormat(dateFormatString);
                        value = df.format(date);
                    }
                    break;
                case Types.TIME:
// Once Java 7 is the minimum supported version.
//            value = Objects.toString(rs.getTime(colIndex), "");
                    value = ObjectUtils.toString(rs.getTime(colIndex), "");
                    break;
                case Types.TIMESTAMP:
                    value = handleTimestamp(rs.getTimestamp(colIndex), timestampFormatString);
                    break;
                case Types.NVARCHAR: // todo : use rs.getNString
                case Types.NCHAR: // todo : use rs.getNString
                case Types.LONGNVARCHAR: // todo : use rs.getNString
                case Types.LONGVARCHAR:
                case Types.VARCHAR:
                case Types.CHAR:
                    String columnValue = rs.getString(colIndex);
                    if (trim && columnValue != null) {
                        value = columnValue.trim();
                    } else {
                        value = columnValue;
                    }
                    break;
                default:
                    value = "";
            }


            if (rs.wasNull() || value == null) {
                value = "";
            }

            return value;
        }

    }

}

//    select
//        campaign_id,
//        channel_id,
//        short_line_item_id,
//        round,
//        cycle_date,
//        initial_budget,
//        budget_id,
//        budget_start_date,
//        budget_end_date,
//        initial_duations_hours,
//        residual_duations_hours,
//        daily_engagements                    dEng,
//        daily_spend                          dSpend,
//        budget_time_engagements              bEng,
//        budget_time_spend                    bSpend,
//        lifetime_time_engagements            lEng,
//        lifetime_time_spend                  lSpend,
//        cpe                                  lCpe,
//        daily_cpe                            dCpe,
//        residual_budget_before_update,
//        residual_budget_after_update,
//        status_before_optimization           beforeStatus,
//        status_after_optimization            afterStatus,
//        line_item_budget_before_optimization beforeBudget,
//        line_item_budget_after_optimization  afterBudget,
//        optimization_algorithm,
//        action                               action
//        from OPTIMIZATION_LOG
//        where CAMPAIGN_ID in ('21455','21459','21460','21463','21469','21543','21544','21545','21593','21680','21681','21701','21823','21827','21872','21883','21896','21900','21903','21909','21910','21911','21925','21927','21995')