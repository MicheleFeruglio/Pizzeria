package pizzeria;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzeriaService {

	private final RigheOrdiniRepository righeRepository;
	private final TestateOrdiniRepository testateRepository;

	@Autowired
	public PizzeriaService(final TestateOrdiniRepository testateRepository,
			final RigheOrdiniRepository righeRepository) {
		this.testateRepository = testateRepository;
		this.righeRepository = righeRepository;
	}

	public String getStatoOrdine(final Long idOrdine) {
		final TestataOrdine ordine = testateRepository.findById(idOrdine)
				.orElseThrow(() -> new IllegalArgumentException("Quest'ordine non esiste"));
		return ordine.getStato().toString();
	}

	public List<TestataOrdine> getOrdini() {
		final List<TestataOrdine> ordini = testateRepository.findAll();
		Collections.sort(ordini);
		return ordini;
	}

	public Long inserisciOrdine(final TestataOrdine o) {
		final TestataOrdine ord = testateRepository.saveAndFlush(o);
		righeRepository.saveAll(o.getRighe());

		return ord.getId();
	}

	public void modificaStatoOrdine(final Long idOrdine) {
		final TestataOrdine ordine = testateRepository.findById(idOrdine)
				.orElseThrow(() -> new IllegalArgumentException("Quest'ordine non esiste"));

		switch (ordine.getStato()) {
			case RICEVUTO:
				ordine.setStato(StatoOrdine.INLAVORAZIONE);
				break;
			case INLAVORAZIONE:
				ordine.setStato(StatoOrdine.EVASO);
				break;
			case EVASO:
				throw new IllegalArgumentException("Quest'ordine è già stato completato");
			default:
				throw new IllegalArgumentException("Questo stato non è ancora stato gestito");
		}

		testateRepository.save(ordine);
	}
}
