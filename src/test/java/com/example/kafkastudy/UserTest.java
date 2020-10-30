package com.example.kafkastudy;

import com.example.kafkastudy.domain.user.entity.User;
import com.example.kafkastudy.domain.user.entity.UserToken;
import com.example.kafkastudy.domain.user.repository.UserRepository;
import com.example.kafkastudy.domain.user.repository.UserTokenRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class UserTest {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserTokenRepository userTokenRepository;


  @Test
  public void 조인테스트() {
//
//    // given
//    User user1 = User.builder().age(10).name("paden").build();
//    User user2 = User.builder().age(10).name("paden").build();
//    User user3 = User.builder().age(10).name("paden").build();
//    User user4 = User.builder().age(10).name("paden").build();
//    List<User> users = Arrays.asList(user1, user2, user3, user4);
//    userRepository.saveAll(users);
//
//    // when
//    List<User> dbUsers = userRepository.findAll();
//
//    dbUsers.stream().forEach(user -> {
//      System.out.println(user.getName());
//    });
//
//    // then
//    Assertions.assertEquals(users.size(), dbUsers.size());
//
//    // Given
//    UserToken userToken1 = UserToken.builder().token("asdf").user(dbUsers.get(0)).build();
//    UserToken userToken2 = UserToken.builder().token("asdf").user(dbUsers.get(0)).build();
//    List<UserToken> userTokens = Arrays.asList(userToken1, userToken2);
//    userTokenRepository.saveAll(userTokens);

    // When
    List<UserToken> dbUserTokens = userTokenRepository.findAllByToken("asdf");

    dbUserTokens.stream().forEach(token->{
      System.out.println("user name is " + token.getUser().getName());
    });
  }
}
