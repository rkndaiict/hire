package com.common.base.utils.mapper;

import java.util.Collection;
import java.util.Set;

public interface ObjectMapper {
	
	public <T> T copyProperties(Object object, Class<T> destinationClass);

	public <T> Collection<T> copyProperties(Collection<?> sourceList,
			Class<T> destinationClass);

	public <T> T copyProperties(Object sourceObject, T targetObject);

	public <T> T copyProperties(Object sourceObject, T targetObject,
			String mapId);

	public <T> Collection<T> copyProperties(Collection<?> sourceList,
			Class<T> destinationClass, String mapId);

	public <T> Set<T> copyProperties(Set<?> sourceList,
			Class<T> destinationClass);

	public <T> void copyProperties(Collection<?> sourceCollection,
			Collection<T> destinationCollection, Class<T> destinationClass);

}
