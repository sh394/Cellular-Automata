package Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuration {
    private static final int NAME_INDEX = 0;
    private static final int TITLE_INDEX = 1;
    private static final int AUTHOR_INDEX = 2;
    private static final int THRESHOLD_INDEX = 3;
    private static final int ROW_INDEX = 4;
    private static final int COL_INDEX = 5;
    private static final int INITIAL_INDEX= 6;

    // name in data file that will indicate it represents data for this type of object
    protected static final String DATA_TYPE = "rule";
    // field names expected to appear in data file holding values for this object
    protected static final List<String> DATA_FIELDS = List.of(
            "name",
            "title",
            "author",
            "threshold",
            "row",
            "col",
            "initial"
    );

    // specific data values for this instance
    private String myName;
    private String myTitle;
    private String myAuthor;
    private double myThreshold;
    private int myRow;
    private int myCol;
    private String myInitial;
    private Map<String, String> myDataValues;

    /**
     * Create Configuration data from given data.
     */

    public Configuration (String name, String title, String author, double threshold, int row, int col, String initial) {
        myName = name;
        myTitle = title;
        myAuthor = author;
        myThreshold = threshold;
        myRow = row;
        myCol = col;
        myInitial = initial;
        // NOTE: this is useful so our code does not fail due to a NullPointerException
        myDataValues = new HashMap<>();
    }

    /**
     * Create Configuration data from data structure of Strings.
     *
     * @param dataValues map of field names to their values
     */
    public Configuration (Map<String, String> dataValues) {
        this(dataValues.get(DATA_FIELDS.get(NAME_INDEX)),
                dataValues.get(DATA_FIELDS.get(TITLE_INDEX)),
                dataValues.get(DATA_FIELDS.get(AUTHOR_INDEX)),
                Double.parseDouble(dataValues.get(DATA_FIELDS.get(THRESHOLD_INDEX))),
                Integer.parseInt(dataValues.get(DATA_FIELDS.get(ROW_INDEX))),
                Integer.parseInt(dataValues.get(DATA_FIELDS.get(COL_INDEX))),
                dataValues.get(DATA_FIELDS.get(INITIAL_INDEX)));
        myDataValues = dataValues;
    }

    public String getMyName() {
        return myName;
    }
    public String getMyTitle() {
        return myTitle;
    }
    public String getMyAuthor() {
        return myAuthor;
    }
    public double getMyThreshold() { return myThreshold; }
    public int getMyRow() {
        return myRow;
    }
    public int getMyCol() {
        return myCol;
    }
    public String getMyInitial() {
        return myInitial;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append(DATA_TYPE + " = [\n");
        for (Map.Entry<String, String> e : myDataValues.entrySet()) {
            result.append("  ").append(e.getKey()).append(" = '").append(e.getValue()).append("',\n");
        }
        result.append("]\n");
        return result.toString();
    }
}
