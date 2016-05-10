package com.hire.common.base.api;

import com.common.base.domain.LinkMetaData;

public interface LinkMetadataService {

	LinkMetaData getLinkMetadataByToken(String token);

	LinkMetaData generateNewToken(String extId, String event);

}
