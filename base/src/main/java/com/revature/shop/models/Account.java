package com.revature.shop.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonValue;

@Entity
@Table(name = "accounts")
public class Account {
	enum Role {
        USER, ADMIN;

        @JsonValue
        public int toValue() {
            return ordinal();
        }
    }
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String name;

    private Role role;

    private double points;
    
    @Column(name = "email_subscription")
    private boolean subscribed;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<PointHistory> pointHistory;

    public Account() {}

    public Account(int id, String email, int points, boolean subscribed) {
        this.id = id;
        this.email = email;
        this.points = points;
        this.subscribed = subscribed;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public int getId(){
        return this.id;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double d) {
        this.points = d;
    }

    public boolean isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}

	public List<PointHistory> getPointHistory() {
        return pointHistory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pointHistory == null) ? 0 : pointHistory.hashCode());
		long temp;
		temp = Double.doubleToLongBits(points);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + (subscribed ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pointHistory == null) {
			if (other.pointHistory != null)
				return false;
		} else if (!pointHistory.equals(other.pointHistory))
			return false;
		if (Double.doubleToLongBits(points) != Double.doubleToLongBits(other.points))
			return false;
		if (role != other.role)
			return false;
		if (subscribed != other.subscribed)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", email=" + email + ", name=" + name + ", role=" + role + ", points=" + points
				+ ", subscribed=" + subscribed + ", pointHistory=" + pointHistory + "]";
	}
}