package distributed.filter.helper.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import distributed.annotation.object.FilterObject;
import distributed.filter.Filter;

public final class Reflector<T> {

	private static final String PRIMITIVE_SHORT = "short";
	private static final String PRIMITIVE_INT = "int";
	private static final String PRIMITIVE_LONG = "long";
	private static final String PRIMITIVE_FLOAT = "float";
	private static final String PRIMITIVE_DOUBLE = "double";
	private static final String PRIMITIVE_BOOLEAN = "boolean";
	private static final String STRING = "String";
	private static final String WRAPPER_SHORT = "Short";
	private static final String WRAPPER_INT = "Integer";
	private static final String WRAPPER_LONG = "Long";
	private static final String WRAPPER_FLOAT = "Float";
	private static final String WRAPPER_DOUBLE = "Double";
	private static final String WRAPPER_BOOLEAN = "Boolean";
	private static final String COMPARETO = "compareTo";

	public Filter instantiateFilter(FilterObject filterObject) throws RuntimeException, InstantiationException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		Constructor constructor = getConstructor( filterObject );
		Object[] parameters = new Object[filterObject.getArguments().length];
		Class[] parameterClasses = new Class[filterObject.getArguments().length];
		for ( int i = 0; i < constructor.getParameterTypes().length; i++ ) {
			parameters[i] = parseArgument( constructor.getParameterTypes()[i].getName(), filterObject.getArguments()[i] );

			if ( constructor.getParameterTypes()[i].isPrimitive() ) {
				parameterClasses[i] = getPrimitiveType( parameters[i].getClass().getSimpleName() );
			}
			else {
				parameterClasses[i] = parameters[i].getClass();
			}
		}

		return filterObject.getFilter().getDeclaredConstructor( parameterClasses ).newInstance( parameters );
	}

	public Object parseArgument(String primitiveType, String argument) {

		if ( primitiveType.equals( PRIMITIVE_SHORT ) ) {
			return Short.parseShort( argument );
		}
		else if ( primitiveType.equals( PRIMITIVE_INT ) ) {
			return Integer.parseInt( argument );
		}
		else if ( primitiveType.equals( PRIMITIVE_LONG ) ) {
			return Long.parseLong( argument );
		}
		else if ( primitiveType.equals( PRIMITIVE_FLOAT ) ) {
			return Float.parseFloat( argument );
		}
		else if ( primitiveType.equals( PRIMITIVE_DOUBLE ) ) {
			return Double.parseDouble( argument );
		}
		else if ( primitiveType.equals( PRIMITIVE_BOOLEAN ) ) {
			return Boolean.parseBoolean( argument );
		}
		else if ( primitiveType.equals( STRING ) ) {
			return argument;
		}

		throw new IllegalArgumentException( "could not find the specified primitive type, " + primitiveType );
	}

	private Class getPrimitiveType(String wrapperType) {

		if ( wrapperType.equals( WRAPPER_SHORT ) ) {
			return Short.TYPE;
		}
		else if ( wrapperType.equals( WRAPPER_INT ) ) {
			return Integer.TYPE;
		}
		else if ( wrapperType.equals( WRAPPER_LONG ) ) {
			return Long.TYPE;
		}
		else if ( wrapperType.equals( WRAPPER_FLOAT ) ) {
			return Float.TYPE;
		}
		else if ( wrapperType.equals( WRAPPER_DOUBLE ) ) {
			return Double.TYPE;
		}
		else if ( wrapperType.equals( WRAPPER_BOOLEAN ) ) {
			return Boolean.TYPE;
		}
		else if ( wrapperType.equals( STRING ) ) {
			return String.class;
		}

		throw new IllegalArgumentException( "could not find the specified wrapper type, " + wrapperType );
	}

	public Constructor getConstructor(FilterObject filterObject) {

		for ( Constructor constructor : filterObject.getFilter().getDeclaredConstructors() ) {
			if ( constructor.getGenericParameterTypes().length == filterObject.getArguments().length ) {
				return constructor;
			}
		}

		throw new IllegalArgumentException( "could not find any constructor whose parameters' size is "
				+ filterObject.getArguments().length + " for class, " + filterObject.getFilter().getName() );
	}

	@SuppressWarnings("unchecked")
	public int compareTo(T src, T compared) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return (Integer) src.getClass()
				.getDeclaredMethod(COMPARETO, src.getClass())
				.invoke(src, compared);
	}
}
