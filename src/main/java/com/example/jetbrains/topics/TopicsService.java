package com.example.jetbrains.topics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jetbrains.exception.ResourceNotFoundException;


@Service
public class TopicsService {
	
	@Autowired
	private TopicsRepo topicRepository;
	
	
	public List<Topics> getallTopics(){
		List<Topics> topics= new ArrayList<>();
		topicRepository.findAll()
		.forEach(topics::add);
		return topics;
	}
	public Topics getTopic(Integer id) {
		return topicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic","Id",id));
	}
	public Topics addTopic(Topics topic) {
		return topicRepository.save(topic);
		
		
	}
	public String updateTopic(Topics topic) {
		
		topicRepository.save(topic);
		return "Topic is updated successfully";
	}
	
	public String deleteTopic(Integer id) {
		topicRepository.deleteById(id);
		return "Success";
	}
}
