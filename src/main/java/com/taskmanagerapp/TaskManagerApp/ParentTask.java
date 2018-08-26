package com.taskmanagerapp.TaskManagerApp;

import org.springframework.stereotype.Component;
import javax.persistence.*;

@Component
@Entity
@Table(name="parenttasktable")
public class ParentTask {

        @Id
        @Column(name = "Parent_ID")
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private int parentId;

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        @Column(name = "Parent_Task")
        private String parentaskName;

        public String getParentaskName() {
            return parentaskName;
        }

        public void setParentaskName(String parentaskName) {
            this.parentaskName = parentaskName;
        }
}
