package com.rvn14.cores.repository;


import com.rvn14.cores.Enum.RequestStatus;
import com.rvn14.cores.model.FriendRequest;
import com.rvn14.cores.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    List<FriendRequest> findByReceiverAndStatus(User receiver, RequestStatus status);
    List<FriendRequest> findBySenderAndStatus(User sender, RequestStatus status);
    boolean existsBySenderAndReceiver(User sender, User receiver);
}




