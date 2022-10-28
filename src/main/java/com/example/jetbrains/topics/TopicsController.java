package com.example.jetbrains.topics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



	@RestController
	public class TopicsController {
		
		@Autowired
		private TopicsService topicService;
		@RequestMapping("/topics")
		public List<Topics> getallTopics() {
			return topicService.getallTopics();
		}
		
		@GetMapping("/topics/{id}")
		public Topics getTopic(@PathVariable Integer id) {
			return topicService.getTopic(id);
		}
		@ResponseStatus(HttpStatus.CREATED)
		@PostMapping(value="/topics")
		public Topics addTopic(@RequestBody Topics topic) {
			return topicService.addTopic(topic);
		}
		@ResponseStatus(HttpStatus.CREATED)
		@RequestMapping(method=RequestMethod.PUT,value="/topics/{id}")
		public String updateTopic(@RequestBody Topics topic,@PathVariable Integer id) {
			topic.setId(id);
			topicService.updateTopic(topic);
			return "Topic is updated successfully";
		}
		@RequestMapping(method = RequestMethod.DELETE , value="/topics/{id}")
		public String  deleteTopic(@PathVariable Integer id) {
			topicService.deleteTopic(id);
			return "Success"; 
		}
		@GetMapping(value="/blah")
		public String getAPI() {
			String uri="http://jbshc1.in2.allegiantair.com:10780/g4-checkinboard/v1/api/travelers?confirmationNum=BXG7YC";
			RestTemplate restTemplate=new RestTemplate();
			String result=restTemplate.getForObject(uri, String.class);
			return result;
	}
	}
