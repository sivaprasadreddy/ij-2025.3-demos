package com.jetbrains.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
public class SingleQueryLoadingApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSingleQueryLoading() {
        Address addr1 = new Address("Main Street", "Springfield", "USA");
        Address addr2 = new Address("Second Street", "Springfield", "USA");

        User user = new User();
        user.setEmail("user1@example.com");
        user.setName("Test User1");
        user.setAddresses(List.of(addr1, addr2));

        User savedUser = userRepository.save(user);

        User user1 = userRepository.findById(savedUser.getId()).orElseThrow();

        System.out.println(user1);
    }

}
