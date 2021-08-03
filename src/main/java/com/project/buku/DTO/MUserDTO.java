/**
 * 
 */
package com.project.buku.DTO;

import com.project.buku.Entity.MUser;

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
public class MUserDTO {
	
	private Long id;
	private String username;
	private String password;
	private boolean accountExpired;
	private boolean accountLocked;
	private boolean credentialsExpired;
	private boolean enabled;
	private Long userDetailId;
	
	public MUserDTO(MUser mUser) {
		super();
		this.id = mUser.getId();
		this.username = mUser.getUsername();
		this.password = mUser.getPassword();
		this.accountExpired = mUser.isAccountExpired();
		this.accountLocked = mUser.isAccountLocked();
		this.credentialsExpired = mUser.isCredentialsExpired();
		this.enabled = mUser.isEnabled();
		this.userDetailId = mUser.getUserDetailId();
	}

}
