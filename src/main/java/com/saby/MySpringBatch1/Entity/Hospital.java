package com.saby.MySpringBatch1.Entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Hospital {

	private String hospitalName;
	private String address;
	private String city;
	private String Network;
	
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getNetwork() {
		return Network;
	}
	public void setNetwork(String network) {
		Network = network;
	}
	@Override
	public String toString() {
		return "Hospital [hospitalName=" + hospitalName + ", address=" + address + ", city=" + city + ", Network="
				+ Network + "]";
	}
}