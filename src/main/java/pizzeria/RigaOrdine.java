package pizzeria;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class RigaOrdine {
	@Id
	@SequenceGenerator(name = "riga_ordine_sequence", sequenceName = "riga_ordine_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "riga_ordine_sequence")
	private Long rigaId;
	private String pizza;
	private Long qta;

	public RigaOrdine() {

	}

	public RigaOrdine(final String pizza, final Long qta) {
		this.pizza = pizza;
		this.qta = qta;
	}

	public RigaOrdine(final Long rigaId, final String pizza, final Long qta) {
		this.rigaId = rigaId;
		this.pizza = pizza;
		this.qta = qta;
	}

	public Long getRigaId() {
		return rigaId;
	}

	public void setRigaId(final Long rigaId) {
		this.rigaId = rigaId;
	}

	public String getPizza() {
		return pizza;
	}

	public void setPizza(final String pizza) {
		this.pizza = pizza;
	}

	public Long getQta() {
		return qta;
	}

	public void setQta(final Long qta) {
		this.qta = qta;
	}

}
