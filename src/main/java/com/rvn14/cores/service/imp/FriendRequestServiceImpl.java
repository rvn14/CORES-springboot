package com.rvn14.cores.service.imp;

import com.rvn14.cores.Enum.RequestStatus;
import com.rvn14.cores.model.FriendRequest;
import com.rvn14.cores.model.User;
import com.rvn14.cores.repository.FriendRequestRepository;
import com.rvn14.cores.repository.UserRepository;
import com.rvn14.cores.service.FriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendRequestServiceImpl implements FriendRequestService {

    private final FriendRequestRepository friendRequestRepo;
    private final UserRepository userRepository;

    @Override
    public void sendRequest(String senderEmail, Long receiverId) {
        User sender = userRepository.findByEmail(senderEmail).orElseThrow();
        User receiver = userRepository.findById(receiverId).orElseThrow();

        if (sender.equals(receiver) || friendRequestRepo.existsBySenderAndReceiver(sender, receiver)) {
            throw new IllegalStateException("Invalid friend request.");
        }

        FriendRequest request = new FriendRequest();
        request.setSender(sender);
        request.setReceiver(receiver);
        request.setStatus(RequestStatus.PENDING);
        request.setCreatedAt(LocalDateTime.now());

        friendRequestRepo.save(request);
    }

    @Override
    public void acceptRequest(Long requestId) {
        FriendRequest request = friendRequestRepo.findById(requestId).orElseThrow();
        request.setStatus(RequestStatus.ACCEPTED);
        friendRequestRepo.save(request);
    }

    @Override
    public void declineRequest(Long requestId) {
        FriendRequest request = friendRequestRepo.findById(requestId).orElseThrow();
        request.setStatus(RequestStatus.DECLINED);
        friendRequestRepo.save(request);
    }

    @Override
    public List<FriendRequest> getIncomingRequests(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        return friendRequestRepo.findByReceiverAndStatus(user, RequestStatus.PENDING);
    }

    @Override
    public List<FriendRequest> getOutgoingRequests(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        return friendRequestRepo.findBySenderAndStatus(user, RequestStatus.PENDING);
    }

    @Override
    public List<FriendRequest> getPendingRequests(String userEmail) {
        // Implement your logic or just return one of your existing methods if appropriate
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        return friendRequestRepo.findByReceiverAndStatus(user, RequestStatus.PENDING);
    }
}
