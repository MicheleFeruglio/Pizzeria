package pizzeria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "pizzeria")
public class PizzeriaController {
	@Autowired
	private final PizzeriaService pizzeriaService;

	public PizzeriaController(final PizzeriaService pizzeriaService) {
		this.pizzeriaService = pizzeriaService;
	}

	@GetMapping
	@RequestMapping("/isalive")
	public String isAlive() {
		return "Alive";
	}

	@GetMapping
	@RequestMapping("/tuttiOrdini")
	public List<TestataOrdine> getOrdini() {
		return pizzeriaService.getOrdini();
	}

	@GetMapping(path = "{id}")
	@RequestMapping("/statoOrdine")
	public String getStatoOrdine(@RequestParam("id") final Long id) {
		return pizzeriaService.getStatoOrdine(id);
	}

	@PostMapping
	@RequestMapping("/inserisciOrdine")
	public Long inserisciOrdine(@RequestBody final TestataOrdine ordine) {
		return pizzeriaService.inserisciOrdine(ordine);
	}

	@RequestMapping("/modificaStatoOrdine/{id}")
	public void modificaStatoOrdine(@PathVariable final Long id) {
		pizzeriaService.modificaStatoOrdine(id);
	}
}
