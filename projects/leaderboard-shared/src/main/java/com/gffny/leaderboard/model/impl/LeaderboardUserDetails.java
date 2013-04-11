/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gffny.leaderboard.model.IGolfer;

/**
 * @author John Gaffney (john@gffny.com) Apr 9, 2013
 * 
 */
public class LeaderboardUserDetails implements UserDetails {

	private IGolfer user;

	private List<GrantedAuthority> authList;
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;
	/**
	 *
	 */
	private static final long serialVersionUID = -3653716496671608295L;

	/**
	 * @param user
	 */
	public LeaderboardUserDetails(IGolfer user) {
		this.user = user;
		this.authList = new ArrayList<GrantedAuthority>();
		// this.authList.add(new SimpleGrantedAuthority("GOLFER"));
	}

	/**
	 * @return the user
	 */
	public IGolfer getUser() {
		return user;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authList;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {

		return this.getUser().getPassword();
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return this.getUser().getProfileHandle();
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {

		return this.accountNonLocked;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {

		return this.credentialsNonExpired;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(IGolfer user) {
		this.user = user;
	}

	/**
	 * @param accountNonExpired
	 *            the accountNonExpired to set
	 */
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	/**
	 * @param accountNonLocked
	 *            the accountNonLocked to set
	 */
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	/**
	 * @param credentialsNonExpired
	 *            the credentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
