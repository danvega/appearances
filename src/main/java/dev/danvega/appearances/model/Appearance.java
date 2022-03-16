package dev.danvega.appearances.model;

import java.time.LocalDate;
import java.util.List;

public class Appearance {

   private String id;
   private String title;
   private String description;
   private LocalDate startDate;
   private LocalDate endDate;
   private Type type;
   private List<String> tags;
   private String url;

   public Appearance(String id, String title, String description, LocalDate startDate, LocalDate endDate, Type type, List<String> tags, String url) {
      this.id = id;
      this.title = title;
      this.description = description;
      this.startDate = startDate;
      this.endDate = endDate;
      this.type = type;
      this.tags = tags;
      this.url = url;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public LocalDate getStartDate() {
      return startDate;
   }

   public void setStartDate(LocalDate startDate) {
      this.startDate = startDate;
   }

   public LocalDate getEndDate() {
      return endDate;
   }

   public void setEndDate(LocalDate endDate) {
      this.endDate = endDate;
   }

   public Type getType() {
      return type;
   }

   public void setType(Type type) {
      this.type = type;
   }

   public List<String> getTags() {
      return tags;
   }

   public void setTags(List<String> tags) {
      this.tags = tags;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   @Override
   public String toString() {
      return "Appearance{" +
              "id='" + id + '\'' +
              ", title='" + title + '\'' +
              ", description='" + description + '\'' +
              ", startDate=" + startDate +
              ", endDate=" + endDate +
              ", type=" + type +
              ", tags=" + tags +
              ", url='" + url + '\'' +
              '}';
   }
}
