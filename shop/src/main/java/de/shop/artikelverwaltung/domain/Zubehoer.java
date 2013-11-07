package de.shop.artikelverwaltung.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Zubehoer extends AbstractArtikel{

	private static final long serialVersionUID = -104960093004088594L;
	
	private String tacho;
	private String licht;
	private String bremsverstaerker;
	private String pulsmesser;
	private String flaschenhalter;
	public String getTacho() {
		return tacho;
	}
	public void setTacho(String tacho) {
		this.tacho = tacho;
	}
	public String getLicht() {
		return licht;
	}
	public void setLicht(String licht) {
		this.licht = licht;
	}
	public String getBremsverstaerker() {
		return bremsverstaerker;
	}
	public void setBremsverstaerker(String bremsverstaerker) {
		this.bremsverstaerker = bremsverstaerker;
	}
	public String getPulsmesser() {
		return pulsmesser;
	}
	public void setPulsmesser(String pulsmesser) {
		this.pulsmesser = pulsmesser;
	}
	public String getFlaschenhalter() {
		return flaschenhalter;
	}
	public void setFlaschenhalter(String flaschenhalter) {
		this.flaschenhalter = flaschenhalter;
	}
	
	 @Override
	public String toString() {
		return "Zubehoer [" + super.toString() + ", tacho=" + tacho + ", licht=" + licht
				+ ", bremsverstaerker=" + bremsverstaerker + ", pulsmesser="
				+ pulsmesser + ", flaschenhalter=" + flaschenhalter + "]";
	}

}
