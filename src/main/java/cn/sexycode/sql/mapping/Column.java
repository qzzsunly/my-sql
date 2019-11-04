package cn.sexycode.sql.mapping;

import cn.sexycode.sql.dialect.Dialect;
import cn.sexycode.sql.type.Mapping;
import cn.sexycode.sql.type.Type;
import cn.sexycode.util.core.str.StringUtils;

import java.io.Serializable;
import java.util.Locale;

/**
 * A column of a relational database table
 *
 */
public class Column implements Selectable, Serializable, Cloneable {

    public static final int DEFAULT_LENGTH = 255;

    public static final int DEFAULT_PRECISION = 19;

    public static final int DEFAULT_SCALE = 2;

    private int length = DEFAULT_LENGTH;

    private int precision = DEFAULT_PRECISION;

    private int scale = DEFAULT_SCALE;

    private Value value;

    private int typeIndex;

    private String name;

    private boolean nullable = true;

    private boolean unique;

    private String sqlType;

    private Integer sqlTypeCode;

    private boolean quoted;

    int uniqueInteger;

    private String checkConstraint;

    private String comment;

    private String defaultValue;

    private String customWrite;

    private String customRead;

    public Column() {
    }

    public Column(String columnName) {
        setName(columnName);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (StringUtils.isNotEmpty(name)
                && Dialect.QUOTE.indexOf(name.charAt(0)) > -1 //TODO: deprecated, remove eventually
        ) {
            quoted = true;
            this.name = name.substring(1, name.length() - 1);
        } else {
            this.name = name;
        }
    }

    /**
     * returns quoted name as it would be in the mapping file.
     */
    public String getQuotedName() {
        return quoted ? "`" + name + "`" : name;
    }

    public String getQuotedName(Dialect d) {
        return quoted ? d.openQuote() + name + d.closeQuote() : name;
    }

    @Override
    public String getAlias(Dialect dialect) {
        final int lastLetter = StringUtils.lastIndexOfLetter(name);
        final String suffix = Integer.toString(uniqueInteger) + '_';

        String alias = name;
        if (lastLetter == -1) {
            alias = "column";
        } else if (name.length() > lastLetter + 1) {
            alias = name.substring(0, lastLetter + 1);
        }

        boolean useRawName = name.length() + suffix.length() <= dialect.getMaxAliasLength() && !quoted && !name
                .toLowerCase(Locale.ROOT).equals("rowid");
        if (!useRawName) {
            if (suffix.length() >= dialect.getMaxAliasLength()) {
                throw new MappingException(
                        String.format("Unique suffix [%s] length must be less than maximum [%d]", suffix,
                                dialect.getMaxAliasLength()));
            }
            if (alias.length() + suffix.length() > dialect.getMaxAliasLength()) {
                alias = alias.substring(0, dialect.getMaxAliasLength() - suffix.length());
            }
        }
        return alias + suffix;
    }

    /**
     * Generate a column alias that is unique across multiple tables
     */
    @Override
    public String getAlias(Dialect dialect, Table table) {
        return getAlias(dialect) + table.getUniqueInteger() + '_';
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(int typeIndex) {
        this.typeIndex = typeIndex;
    }

    public boolean isUnique() {
        return unique;
    }

    @Override
    public int hashCode() {
        //used also for generation of FK names!
        return isQuoted() ? name.hashCode() : name.toLowerCase(Locale.ROOT).hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Column && equals((Column) object);
    }

    @SuppressWarnings("SimplifiableIfStatement")
    public boolean equals(Column column) {
        if (null == column) {
            return false;
        }
        if (this == column) {
            return true;
        }

        return isQuoted() ? name.equals(column.name) : name.equalsIgnoreCase(column.name);
    }

    public int getSqlTypeCode(Mapping mapping) throws MappingException {
        Type type = getValue().getType();
        try {
            int sqlTypeCode = type.sqlTypes(mapping)[getTypeIndex()];
            if (getSqlTypeCode() != null && getSqlTypeCode() != sqlTypeCode) {
                throw new MappingException(
                        "SQLType code's does not match. mapped as " + sqlTypeCode + " but is " + getSqlTypeCode());
            }
            return sqlTypeCode;
        } catch (Exception e) {
            throw new MappingException(
                    "Could not determine type for column " + name + " of type " + type.getClass().getName() + ": " + e
                            .getClass().getName(), e);
        }
    }

    /**
     * Returns the underlying columns sqltypecode.
     * If null, it is because the sqltype code is unknown.
     * <p/>
     * Use #getSqlTypeCode(Mapping) to retrieve the sqltypecode used
     * for the columns associated Value/Type.
     *
     * @return sqlTypeCode if it is set, otherwise null.
     */
    public Integer getSqlTypeCode() {
        return sqlTypeCode;
    }

    public void setSqlTypeCode(Integer typeCode) {
        sqlTypeCode = typeCode;
    }

    public String getSqlType(Dialect dialect, Mapping mapping) throws MappingException {
        if (sqlType == null) {
            sqlType = dialect.getTypeName(getSqlTypeCode(mapping), getLength(), getPrecision(), getScale());
        }
        return sqlType;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isQuoted() {
        return quoted;
    }

    @Override
    public String toString() {
        return getClass().getName() + '(' + getName() + ')';
    }

    public String getCheckConstraint() {
        return checkConstraint;
    }

    public void setCheckConstraint(String checkConstraint) {
        this.checkConstraint = checkConstraint;
    }

    public boolean hasCheckConstraint() {
        return checkConstraint != null;
    }

    @Override
    public boolean isFormula() {
        return false;
    }

    @Override
    public String getText(Dialect d) {
        return getQuotedName(d);
    }

    @Override
    public String getText() {
        return getName();
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int scale) {
        this.precision = scale;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getCustomWrite() {
        return customWrite;
    }

    public void setCustomWrite(String customWrite) {
        this.customWrite = customWrite;
    }

    public String getCustomRead() {
        return customRead;
    }

    public void setCustomRead(String customRead) {
        this.customRead = customRead;
    }

    public String getCanonicalName() {
        return quoted ? name : name.toLowerCase(Locale.ROOT);
    }

    /**
     * Shallow copy, the value is not copied
     */
    @Override
    public Column clone() {
        Column copy = new Column();
        copy.setLength(length);
        copy.setScale(scale);
        copy.setValue(value);
        copy.setTypeIndex(typeIndex);
        copy.setName(getQuotedName());
        copy.setNullable(nullable);
        copy.setPrecision(precision);
        copy.setUnique(unique);
        copy.setSqlType(sqlType);
        copy.setSqlTypeCode(sqlTypeCode);
        copy.setCheckConstraint(checkConstraint);
        copy.setComment(comment);
        copy.setDefaultValue(defaultValue);
        copy.setCustomRead(customRead);
        copy.setCustomWrite(customWrite);
        return copy;
    }

}
