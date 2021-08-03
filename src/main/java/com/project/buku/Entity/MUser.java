/**
 * 
 */
package com.project.buku.Entity;

import java.io.Serializable;

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
@Table(name="M_USER")
public class MUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "account_expired")
	private boolean accountExpired;
	
	@Column(name = "account_locked")
	private boolean accountLocked;
	
	@Column(name = "credentials_expired")
	private boolean credentialsExpired;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "user_detail_id")
	private Long userDetailId;

}
