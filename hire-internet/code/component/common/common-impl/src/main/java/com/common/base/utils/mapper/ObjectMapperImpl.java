package com.common.base.utils.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dozer.Mapper;
import org.dozer.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectMapperImpl implements ObjectMapper {

	private static Logger logger = LoggerFactory.getLogger(ObjectMapperImpl.class);

	private Mapper dozerBeanMapper;

	@Override
	public <T> List<T> copyProperties(Collection<?> sourceList, Class<T> destinationClass) {
		List<T> targetList = new ArrayList<T>();
		if (sourceList != null && !sourceList.isEmpty()) {
			for (Object sourceObject : sourceList) {
				T targetObject = copyProperties(sourceObject, destinationClass);
				targetList.add(targetObject);
			}
		}
		return targetList;
	}

	@Override
	public <T> Set<T> copyProperties(Set<?> sourceList, Class<T> destinationClass) {
		Set<T> targetList = new HashSet<T>();
		if (sourceList != null && !sourceList.isEmpty()) {
			for (Object sourceObject : sourceList) {
				T targetObject = copyProperties(sourceObject, destinationClass);
				targetList.add(targetObject);
			}
		}
		return targetList;
	}

	@Override
	public <T> void copyProperties(Collection<?> sourceCollection, Collection<T> destinationCollection,
			Class<T> destinationClass) {
		if (sourceCollection != null && !sourceCollection.isEmpty()) {
			for (Object sourceObject : sourceCollection) {
				T targetObject = copyProperties(sourceObject, destinationClass);
				destinationCollection.add(targetObject);
			}
		}
	}

	@Override
	public <T> T copyProperties(Object sourceObject, Class<T> destinationClass) {
		if (sourceObject != null) {
			try {
				return dozerBeanMapper.map(sourceObject, destinationClass);
			} catch (MappingException e) {
				logger.error("Error while mapping " + sourceObject.getClass() + " and " + destinationClass, e);
				return null;
			}
		}
		return null;
	}

	@Override
	public <T> T copyProperties(Object sourceObject, T targetObject) {
		if (sourceObject != null && targetObject != null) {
			try {
				dozerBeanMapper.map(sourceObject, targetObject);
			} catch (MappingException e) {
				logger.error("Error while mapping " + sourceObject.getClass() + " and " + targetObject.getClass(), e);
				return null;
			}
			return targetObject;
		}
		return null;
	}
	
	

	/*@Override
	public Object copyProperties(Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass) {
		if (source == null) {
			return null;
		}
		if (destinationClass != null) {
			if (destinationClass.getSimpleName().equalsIgnoreCase("String")) {
				return getString(source);
			} else if (destinationClass.isEnum()) {

				return getEnum(destinationClass, source);

			} else {
				throw new MappingException(new StrBuilder("Converter ").append(this.getClass().getSimpleName())
						.append(" was used incorrectly. Arguments were: ")
						.append(destinationClass.getClass().getName()).append(" and ").append(source).toString());
			}
		}
		return null;
	}

	private Object getString(Object object) {
		String value = object.toString();
		return value;
	}

	private Object getEnum(Class<?> destinationClass, Object source) {
		Object enumeration = null;

		Method[] ms = destinationClass.getMethods();
		for (Method m : ms) {
			if (m.getName().equalsIgnoreCase("valueOf")) {
				try {
					enumeration = m.invoke(destinationClass.getClass(), (String) source);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				return enumeration;
			}
		}
		return null;
	}*/

	@Override
	public <T> T copyProperties(Object sourceObject, T targetObject,
			String mapId) {
		if (sourceObject != null && targetObject != null) {
			try {
				dozerBeanMapper.map(sourceObject, targetObject, mapId);
			} catch (MappingException e) {
				logger.error("Error while mapping " + sourceObject.getClass() + " and " + targetObject.getClass(), e);
				return null;
			}
			return targetObject;
		}
		return null;
	}

	public void setDozerBeanMapper(Mapper dozerBeanMapper) {
		this.dozerBeanMapper = dozerBeanMapper;
	}

	@Override
	public <T> List<T> copyProperties(Collection<?> sourceList,
			Class<T> destinationClass, String mapId) {
		// TODO Auto-generated method stub
		List<T> targetList = new ArrayList<T>();
		T targetObject = null;
		if (sourceList != null && !sourceList.isEmpty())
		{
			for (Object sourceObject : sourceList) 
			{
				try 
				{
					targetObject = dozerBeanMapper.map(sourceObject, destinationClass, mapId);
				}
				catch (MappingException e) 
				{
					logger.error("Error while mapping " + sourceList.getClass() + " and " + destinationClass, e);
					return null;
				}
				targetList.add(targetObject);
			}
		} 
		return targetList;
	}
}
