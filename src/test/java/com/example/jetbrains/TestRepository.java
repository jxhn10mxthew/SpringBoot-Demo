package com.example.jetbrains;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jetbrains.topics.Topics;
import com.example.jetbrains.topics.TopicsRepo;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestRepository {
	@Autowired
	TopicsRepo topicrepo;
	
	@Test
	@Order(1)
	public void testCreate() {
		Topics topic=new Topics (23,"abc21","j212hk");
		topicrepo.save(topic);
		Boolean result=topicrepo.existsById(23);
		assertThat(result).isTrue();
	}
	@Test
	@Order(2)
	public void testReadAll() {
		List<Topics> list=topicrepo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	@Test
	@Order(3)
	public void testupdate() {
		Topics topics=topicrepo.findById(9).get();
		topics.setName("john");
		topics.setDescription("descrioptoipn");
		topicrepo.save(topics);
		assertNotEquals("Springboot",topicrepo.findById(9).get().getName());
	}
	@Test
	@Order(4)
	public void testDelete() {
		topicrepo.deleteById(22);
		assertThat(topicrepo.existsById(22)).isFalse();
	}
}
