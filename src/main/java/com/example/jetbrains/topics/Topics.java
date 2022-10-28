package com.example.jetbrains.topics;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Topics {
		
		@Id
		@GeneratedValue
		private Integer id;
		private String name;
		private String description;
		
		public Topics() {}
		
		public Topics(Integer id, String name, String description) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
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
}

