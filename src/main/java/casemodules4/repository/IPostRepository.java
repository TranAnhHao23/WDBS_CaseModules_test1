package casemodules4.repository;

import casemodules4.model.Post;
import casemodules4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IPostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByContentContaining(String hashtag);

    List<Post> findAllByUserPostAndStatus(User user, String status);

    List<Post> findAllByUserPostIdUser(Long id);

    List<Post> findAllByUserPostIdUserOrStatus(Long id, String status);

    List<Post> findAllByStatus(String status);
}
