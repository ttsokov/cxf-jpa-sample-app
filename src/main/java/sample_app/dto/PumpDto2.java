package sample_app.dto;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import sample_app.model.Pump;

import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PumpDto2")
public class PumpDto2 {
	@XmlElement(name = "id")
	private Long id;
	
	@XmlElement(name = "number")
	private Long number;

	public PumpDto2() {
		// Required by JAXB;
	}
	
	public PumpDto2(Pump pump) {
		this.number = pump.getNumber();
		this.id = pump.getId();
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}
}
