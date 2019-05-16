package cn.sexycode.sql.type;


/**
 * Additional contract for a {@link Type} may be used for a discriminator.
 *
 */
public interface DiscriminatorType<T> extends IdentifierType<T>, LiteralType<T> {
}
