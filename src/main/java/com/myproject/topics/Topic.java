package com.myproject.topics;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table
public class Topic {
		
		@Id
		//@GeneratedValue(strategy=GenerationType.SEQUENCE)
		private Integer id;
		private String name;
		private String description;
		
		@Size(min=4,max=8,message="Password must be between 4 to 10 characters")
		private String password;
		
		public Topic() {}
		
		public Topic(Integer id, String name, String description,String password) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.password=password;
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
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
}

