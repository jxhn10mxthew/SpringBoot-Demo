package com.example.jetbrains.courses;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.jetbrains.topics.Topics;


@Entity
@Table
public class Courses {
		
		@Id
		@GeneratedValue
		private Integer id;
		private String name;
		private String description;
		@ManyToOne
		private Topics topic;
		
		public Courses() {}
		
		public Courses(Integer id, String name, String description, Integer topicId) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.topic=new Topics(topicId,"","");
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}

		public Topics getTopic() {
			return topic;
		}

		public void setTopic(Topics topic) {
			this.topic = topic;
		}
}

