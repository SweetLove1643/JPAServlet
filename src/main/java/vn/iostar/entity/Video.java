package vn.iostar.entity;

import java.io.Serializable;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.NamedQuery;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="videos")
@NamedQuery(name="Video.findAll", query="Select v from Video v")
public class Video implements Serializable{
	static final long serialVersionUID = 1L;
	 @Id
	 @Column(name="VideoId")
	 private String videoId;

	 @Column(name="Active")
	 private int active;

	 @Column(name="Description", columnDefinition = "nvarchar(MAX)")
	 private String description;
	 
	 @Column(name="Poster" , columnDefinition = "nvarchar(500) null")
	 private String poster;


	 @Column(name="Title" , columnDefinition = "nvarchar(500) null")
	 private String title;


	 @Column(name="Views")
	 private int views;



	 //bi-directional many-to-one association to Category
	 @ManyToOne
	 @JoinColumn(name="CategoryId")
	 private Category category;
	 //su dung lombok tao getter setter contructer


}
