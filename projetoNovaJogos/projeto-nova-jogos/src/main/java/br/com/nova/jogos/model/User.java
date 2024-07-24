package br.com.nova.jogos.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements  UserDetails ,Serializable {
		
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(name = "user_name", unique = true)
		private String userName;
		
		@Column(name = "email", unique = true)
		private String email;

		@Column(name="password")
		private String password;
		
		
		@ManyToMany(fetch = FetchType.EAGER)
		@JoinTable(name = "user_permission", joinColumns = {@JoinColumn (name = "id_user")},
		inverseJoinColumns = {@JoinColumn (name = "id_permission")})
		private List<Permission> permissions;
		
		
		public User(String Username, String email , String password, List<Permission> permission) {
			this.userName = Username;
			this.email = email;
			this.password = password;
			this.permissions = permission;
		}
		

		
		


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

		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
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
		public boolean isAccountNonExpired() {
			return true;
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
