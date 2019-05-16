package cn.sexycode.sql.type;


/**
 * Specific adaptation of the "any" type to the old deprecated "object" type
 *
 */
public class ObjectType extends AnyType implements BasicType {
    /**
     * Singleton access
     */
    public static final ObjectType INSTANCE = new ObjectType();

    private ObjectType() {
        super(StringType.INSTANCE, SerializableType.INSTANCE);
    }

    @Override
    public String getName() {
        return "object";
    }

    @Override
    public String[] getRegistrationKeys() {
        return new String[]{getName(), Object.class.getName()};
    }
}
