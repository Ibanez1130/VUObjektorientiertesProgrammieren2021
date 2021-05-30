package rbs.copy;

/**
 * This Interface is needed since the Cloneable interface provided by Java does
 * not redeclare the clone method of {@link java.lang.Object}. We want to use it
 * as bounded type for generic classes so this additional interface is needed.
 * <p>
 * We have chosen to use an IDeepCopy interface to make handling of
 * copying/cloning objects simpler. Classes that implement this interface should
 * construct a copy of object by either invoking a private copy constructor, or
 * by creating a new object of the same class and setting its values
 * accordingly. Keep in mind, that attributes of classes should also be copied
 * (if they implement the IDeepCopy interface) in the same way, before they are
 * set on the copied object.
 * 
 * @author OOP
 *
 */
public interface IDeepCopy {

	/**
	 * Creates and returns a copy of this object. The precise meaning of "copy"
	 * may depend on the class of the object. The general intent is that, for
	 * any object <code>x</code>, the expression: <blockquote>
	 * 
	 * <pre>
	 * x.deepCopy() != x
	 * </pre>
	 * 
	 * </blockquote> will be true, and that the expression: <blockquote>
	 * 
	 * <pre>
	 * x.deepCopy().getClass() == x.getClass()
	 * </pre>
	 * 
	 * </blockquote> will be <code>true</code>, but these are not absolute
	 * requirements. While it is typically the case that: <blockquote>
	 * 
	 * <pre>
	 * x.deepCopy().equals(x)
	 * </pre>
	 * 
	 * </blockquote> will be <code>true</code>, this is not an absolute requirement.
	 * <p>
	 * 
	 * @return a clone of this object
	 */
	public IDeepCopy deepCopy();

}
