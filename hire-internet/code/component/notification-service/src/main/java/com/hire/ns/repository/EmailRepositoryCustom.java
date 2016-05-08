package com.hire.ns.repository;

import java.util.List;

import com.hire.ns.domain.Email;

public interface EmailRepositoryCustom {

    /**
     * find emails by message type and status starting from startIndex with given pageSize
     * 
     * @param String messageType
     * @param NsStatusreference status
     * @param int pageSize
     */
	List<Email> findAllByMessageTypeAndStatus(String messageType, int pageSize);
}
