package com.myProject1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myProject1.topics.Topic;
import com.myProject1.topics.TopicsController;
import com.myProject1.topics.TopicsService;
@ExtendWith(MockitoExtension.class)
@WebMvcTest(value=TopicsController.class)
public class TestController {
@MockBean
private TopicsService service;
private Topic topic;
private List<Topic> topicList;

@InjectMocks
private TopicsController controller;

@Autowired
private MockMvc mockMvc;

@BeforeEach
public void setup() {
	topic=new Topic(1,"tty","gfg","vhg");
	mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
}

@AfterEach
void tearDown() {
	topic=null;
}

@Test
public void createTopicMapping() throws Exception{
   when(service.addTopic(any())).thenReturn(topic);
   mockMvc.perform(post("/topics").
           contentType(MediaType.APPLICATION_JSON).
           content(asJsonString(topic))).
           andExpect(status().isCreated());
   verify(service,times(1)).addTopic(any());
}
public static String asJsonString(final Object obj) {
    try {
        final ObjectMapper mapper = new ObjectMapper();
        final String jsonContent = mapper.writeValueAsString(obj);
        return jsonContent;
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
@Test
public void getAllTopicsMapping() throws Exception{
   when(service.getallTopics()).thenReturn(topicList);
   mockMvc.perform(MockMvcRequestBuilders.get("/topics").
           contentType(MediaType.APPLICATION_JSON).
           content(asJsonString(topic))).
           andDo(MockMvcResultHandlers.print());
   verify(service).getallTopics();
   verify(service,times(1)).getallTopics();
}
@Test
public void getTopicByIdMapping() throws Exception{
   when(service.getTopic(topic.getId())).thenReturn(topic);
   mockMvc.perform(get("/topics/1").
           contentType(MediaType.APPLICATION_JSON).
           content(asJsonString(topic))).
           andExpect(MockMvcResultMatchers.status().isOk()).
           andDo(MockMvcResultHandlers.print());
}
@Test
public void deleteTopicByIdMapping() throws Exception{
   when(service.deleteTopic(topic.getId())).thenReturn("Success");
   mockMvc.perform(delete("/topics/1").
           contentType(MediaType.APPLICATION_JSON).
           content(asJsonString(topic))).
           andExpect(MockMvcResultMatchers.status().isOk()).
           andDo(MockMvcResultHandlers.print());
}
protected String mapToJson(Object obj) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(obj);
 }
@Test
public void updateTopicMapping() throws Exception {
   Topic topic = new Topic();
   topic.setName("Lemon");
   
   String inputJson = mapToJson(topic);
   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/topics/2")
      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
   String content = mvcResult.getResponse().getContentAsString();
   assertEquals(content, "Topic is updated successfully");
}
}
