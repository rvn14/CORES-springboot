package com.rvn14.cores.service;


import com.rvn14.cores.model.FriendRequest;
import java.util.List;

public interface FriendRequestService {
    void sendRequest(String senderEmail, Long receiverId);
    void acceptRequest(Long requestId);
    void declineRequest(Long requestId);
    List<FriendRequest> getPendingRequests(String userEmail);
    List<FriendRequest> getIncomingRequests(String userEmail);
    List<FriendRequest> getOutgoingRequests(String userEmail);
}


