package com.example.kafkastudy.domain.user.repository;

import com.example.kafkastudy.domain.user.entity.UserToken;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

  @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.LOAD)
  List<UserToken> findAllByToken(String token);

}
