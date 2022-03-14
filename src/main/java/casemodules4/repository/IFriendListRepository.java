package casemodules4.repository;

import casemodules4.model.FriendList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IFriendListRepository extends CrudRepository<FriendList, Long> {
}
