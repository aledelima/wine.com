package br.com.asl.wine.model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true, nullable = false)
	private String storeCode;
	private String address;
	private String phone;
	@OneToMany(mappedBy = "store", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Ziprange> zipRanges = new ArrayList<>();
	
}
