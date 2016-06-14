package com.leatherswan.artisticendeavors.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import com.fasterxml.jackson.annotation.JsonView;



import com.leatherswan.artisticendeavors.enums.GenreType;



@Entity
@Table(name="GENRE")
public class Genre implements Serializable {
	
    private static final long serialVersionUID = 1L;

    public interface Content {};
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	  private Integer id;
    
    @Column(name = "GENRE_TYPE")
    private GenreType genreType;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
//	@JsonView(Content.class)

    public GenreType getGenreType() {
		return genreType;
	}
	public void setGenreType(GenreType genreType) {
		this.genreType = genreType;
	}
}
