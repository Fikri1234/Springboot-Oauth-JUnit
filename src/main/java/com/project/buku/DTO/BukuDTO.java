/**
 * 
 */
package com.project.buku.DTO;

import java.math.BigDecimal;

import com.project.buku.Entity.Buku;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Fikri
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BukuDTO {
	
	private long id;
	private String judul;
	private String pengarang;
	private String deskripsi;
	private String image;
	private BigDecimal harga;
	
	public BukuDTO(Buku buku) {
		super();
		this.id = buku.getId();
		this.judul = buku.getJudul();
		this.pengarang = buku.getPengarang();
		this.deskripsi = buku.getDeskripsi();
		this.image = buku.getImage();
		this.harga = buku.getHarga();
	}

}
