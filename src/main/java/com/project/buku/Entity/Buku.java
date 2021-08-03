/**
 * 
 */
package com.project.buku.Entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * @author Fikri
 *
 */

@Data
@ToString
@Entity
@Table(name="buku")
public class Buku implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "judul")
	private String judul;
	
	@Column(name = "pengarang")
	private String pengarang;
	
	@Column(name = "deskripsi")
	private String deskripsi;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "harga")
	private BigDecimal harga;

}
