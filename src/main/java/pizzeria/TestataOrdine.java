package pizzeria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class TestataOrdine implements Comparable<TestataOrdine> {
	@Id
	@SequenceGenerator(name = "testata_ordine_sequence", sequenceName = "testata_ordine_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "testata_ordine_sequence")
	private Long idOrdine;
	private String cliente;
	@Column(name = "ora_ricezione")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime oraRicezione;
	@Column(name = "ora_prevista_evasione")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime oraPrevistaEvasione;
	private StatoOrdine stato;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_ordine", nullable = false)
	private final List<RigaOrdine> righe = new ArrayList<>();

	public TestataOrdine() {
		stato = StatoOrdine.RICEVUTO;
	}

	public TestataOrdine(final String cliente, final LocalDateTime oraRicezione,
			final LocalDateTime oraPrevistaEvasione) {
		this.cliente = cliente;
		this.oraRicezione = oraRicezione;
		this.oraPrevistaEvasione = oraPrevistaEvasione;
		stato = StatoOrdine.RICEVUTO;
	}

	public TestataOrdine(final Long id, final String cliente, final LocalDateTime oraRicezione,
			final LocalDateTime oraPrevistaEvasione) {
		idOrdine = id;
		this.cliente = cliente;
		this.oraRicezione = oraRicezione;
		this.oraPrevistaEvasione = oraPrevistaEvasione;
		stato = StatoOrdine.RICEVUTO;
	}

	public Long getId() {
		return idOrdine;
	}

	public void setId(final Long id) {
		idOrdine = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(final String cliente) {
		this.cliente = cliente;
	}

	public LocalDateTime getOraRicezione() {
		return oraRicezione;
	}

	public void setOraRicezione(final LocalDateTime oraRicezione) {
		this.oraRicezione = oraRicezione;
	}

	public LocalDateTime getOraPrevistaEvasione() {
		return oraPrevistaEvasione;
	}

	public void setOraPrevistaEvasione(final LocalDateTime oraPrevistaEvasione) {
		this.oraPrevistaEvasione = oraPrevistaEvasione;
	}

	public StatoOrdine getStato() {
		return stato;
	}

	public void setStato(final StatoOrdine stato) {
		this.stato = stato;
	}

	@Override
	public int compareTo(final TestataOrdine o) {
		int retValue = oraPrevistaEvasione.compareTo(o.getOraPrevistaEvasione());
		if (retValue == 0) {
			retValue = oraRicezione.compareTo(o.getOraRicezione());

			if (retValue == 0) {
				retValue = idOrdine.compareTo(o.getId());
			}
		}

		return retValue;
	}

	public List<RigaOrdine> getRighe() {
		return righe;
	}

}
