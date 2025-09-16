package com.example.chat.repository;

import com.example.chat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatSessionId(Long chatSessionId);
    List<Message> findByChatSession_Id(Long chatSessionId);

    List<Message> findBySenderUser_Id(Long senderUserId);
    List<Message> findBySenderCustomerService_Id(Long senderCustomerServiceId);
    List<Message> findByReceiverUser_Id(Long receiverUserId);
    List<Message> findByReceiverCustomerService_Id(Long receiverCustomerServiceId);
}
