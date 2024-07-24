package br.com.nova.jogos.data.vo.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

import br.com.nova.jogos.model.Permission;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

public class UserVO extends RepresentationModel<UserVO> implements  UserDetails ,Serializable{
		
		private static final long serialVersionUID = 1L;

		@Mapping("id")
		@JsonProperty("id")
		private Long key;
		
		@JsonProperty("Username")
		private String userName;
		
		private String email;

		private String password;
		
		private Boolean accountNonExpired;
		
		private Boolean accountNonLocked;
		
		private Boolean credentialsNonExpired; 

		private Boolean enabled; 
		
		@ManyToMany(fetch = FetchType.EAGER)
		@JoinTable(name = "user_permission", joinColumns = {@JoinColumn (name = "id_user")},
		inverseJoinColumns = {@JoinColumn (name = "id_permission")})
		private List<Permission> permissions;
		
		
		
		
		public UserVO() {}


		public List<String> getRoles(){
			List<String> roles = new ArrayList<>();
			for (Permission permission : permissions) {
				roles.add(permission.getDescription());
			}
			return roles;
		}
		
		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return this.permissions;
		}

		@Override
		public String getPassword() {
			return this.password;
		}

		@Override
		public String getUsername() {
			return this.userName;
		}

		@Override
		public boolean isAccountNonExpired() {
			return this.accountNonExpired;
		}

		@Override
		public int hashCode() {
			return Objects.hash(accountNonExpired, accountNonLocked, credentialsNonExpired, email, enabled, key,
					password, permissions, userName);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			UserVO other = (UserVO) obj;
			return Objects.equals(accountNonExpired, other.accountNonExpired)
					&& Objects.equals(accountNonLocked, other.accountNonLocked)
					&& Objects.equals(credentialsNonExpired, other.credentialsNonExpired)
					&& Objects.equals(email, other.email) && Objects.equals(enabled, other.enabled)
					&& Objects.equals(key, other.key) && Objects.equals(password, other.password)
					&& Objects.equals(permissions, other.permissions) && Objects.equals(userName, other.userName);
		}


		public Long getKey() {
			return key;
		}


		public void setKey(Long key) {
			this.key = key;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public Boolean getAccountNonExpired() {
			return accountNonExpired;
		}


		public void setAccountNonExpired(Boolean accountNonExpired) {
			this.accountNonExpired = accountNonExpired;
		}


		public Boolean getAccountNonLocked() {
			return accountNonLocked;
		}


		public void setAccountNonLocked(Boolean accountNonLocked) {
			this.accountNonLocked = accountNonLocked;
		}


		public Boolean getCredentialsNonExpired() {
			return credentialsNonExpired;
		}


		public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
			this.credentialsNonExpired = credentialsNonExpired;
		}


		public Boolean getEnabled() {
			return enabled;
		}


		public void setEnabled(Boolean enabled) {
			this.enabled = enabled;
		}


		public List<Permission> getPermissions() {
			return permissions;
		}


		public void setPermissions(List<Permission> permissions) {
			this.permissions = permissions;
		}


		public void setUsername(String userName) {
			this.userName = userName;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

}
