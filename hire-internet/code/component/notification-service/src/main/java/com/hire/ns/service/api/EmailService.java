package com.hire.ns.service.api;

import java.util.List;
import java.util.Map;

import com.hire.ns.domain.Email;
import com.hire.ns.domain.MessageTemplate;
import com.hire.ns.dto.EmailDTO;
import com.hire.ns.dto.NotificationDTO;

public interface EmailService {
    
    /**
     * send Email
     * 
     * @param EmailDTO emailDTO
     */
    void sendEmail(EmailDTO emailDTO);
    
    public Email saveUpdateEmailNotification(NotificationDTO notificationDTO, Map<String, Object> params, List<byte[]> files,
            MessageTemplate messageTemplate);
}
