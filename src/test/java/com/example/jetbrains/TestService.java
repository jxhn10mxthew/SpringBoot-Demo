package com.example.jetbrains;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.jetbrains.topics.Topics;
import com.example.jetbrains.topics.TopicsRepo;
import com.example.jetbrains.topics.TopicsService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestService {
	
	@InjectMocks
	private TopicsService service;
	private Topics topic1;
	private Topics topic2;
	List<Topics> topicList;
	
	@BeforeEach
	 public void setUp() {
	    topicList = new ArrayList<>();
	    topic1 = new Topics(1, "bread","ds");
	    topic2 = new Topics(2, "jam","fg");
	    topicList.add(topic1);
	    topicList.add(topic2);
	    }
	
	@AfterEach
    public void tearDown() {
		topic1 = topic2 = null;
		topicList = null;
    }
	@MockBean
	private TopicsRepo repo;
	
	
	@Test
	public void testAddTopic() {
		when(repo.save(any())).thenReturn(topic1);
	     service.addTopic(topic1);
	     verify(repo,times(1)).save(any());
	}
	
	@Test
	public void testGetTopicById() {
		 Mockito.when(repo.findById(1)).thenReturn(Optional.ofNullable(topic1));
		   assertThat(service.getTopic(topic1.getId())).isEqualTo(topic1);
	}
	
	@Test
	public void testGetAllTopics() {
		repo.save(topic1);
	    when(repo.findAll()).thenReturn(topicList);
	    List<Topics> topicList1 =service.getallTopics();
	    assertEquals(topicList1,topicList);
	    verify(repo,times(1)).save(topic1);
	    verify(repo,times(1)).findAll();
	}
	
	@Test
	public void testDeleteTopic() {
		service.deleteTopic(topic1.getId());
		verify(repo,times(1)).deleteById(topic1.getId());

	}
	
	@Test
	public void testUpdateTopic() {
		Mockito.when(repo.save(topic1)).thenReturn(topic1);
		topic1.setName("oj");
		topic1.setDescription("dsdsda");
		
		Mockito.when(repo.save(topic1)).thenReturn(topic1);
		assertThat(service.updateTopic(topic1)).isEqualTo("Topic is updated successfully");
	}
	
}
