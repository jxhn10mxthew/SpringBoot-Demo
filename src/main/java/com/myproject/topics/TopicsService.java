package com.myproject.topics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.exception.ResourceNotFoundException;


@Service
public class TopicsService {
	
	@Autowired
	private TopicsRepo topicRepository;
	
	
	public List<Topic> getallTopics(){
		List<Topic> topics= new ArrayList<>();
		topicRepository.findAll()
		.forEach(topics::add);
		return topics;
	}
	public Topic getTopic(Integer id) {
		return topicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic","Id",id));
	}
	public Topic addTopic(Topic topic) {
		return topicRepository.save(topic);
		
		
	}
	public String updateTopic(Topic topic) {
		
		topicRepository.save(topic);
		return "Topic is updated successfully";
	}
	
	public String deleteTopic(Integer id) {
		topicRepository.deleteById(id);
		return "Success";
	}
}
