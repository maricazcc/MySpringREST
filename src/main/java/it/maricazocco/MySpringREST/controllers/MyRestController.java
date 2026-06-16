package it.maricazocco.MySpringREST.controllers;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.maricazocco.MySpringREST.dao.MyClassDAO;
import it.maricazocco.MySpringREST.models.MyClass;
import it.maricazocco.MySpringREST.models.MyResponseBody;
import it.maricazocco.MySpringREST.service.MyService;

@RestController
@RequestMapping("/myrestcontroller") //Aggiungiamo questa parte di url per accedere a questo controller
public class MyRestController {
	
	@Autowired
	private MyService myService;
	
	@GetMapping("/myget") //Associa il metodo myGet alla richiesta GET con l'url "/myget"
	public String myGet() {
		return "My Spring REST Get";
	}
	
	@GetMapping("/mygetquerystring")
	// RequestParam vuole querystring
	//mygetquerystring?myparameter1=Aldo&myparameter2=33
	public String myGetQuerystring(@RequestParam String myparameter1, int myparameter2) {
	  return "Get con variabili nella querystring: " + myparameter1 + " - " + myparameter2 * 2;
	 }
	
	@GetMapping("/mygetpathvariables/{mypathvariable1}/{mypathvariable2}")
	//mygetpathvariables/Aldo/33
	public String myGetParameters(@PathVariable String mypathvariable1, @PathVariable int mypathvariable2) {
	  return "Get con variabili nel path: " + mypathvariable1 + " - " + mypathvariable2 * 2;
	}
	
	
	  /* MICRO_EXE
	  Definire metodo che accetti i parametri della request 'nome' e 'password' 
	  in formato querystring e produca una response a seconda dei loro valori: 
	  se nome è Aldo e password è 2 allora la response sarà "Bentornato Aldo"
	  altrimenti la response sarà "nome o password errata".
	  Realizzare lo stesso anche con PathVariable 
	  */
	
	@GetMapping("/logQueryString")
	//logQueryString?myparameter1=Aldo&myparameter2=2
	public String logQueryString(@RequestParam String nome, int password) {
		if(nome.equals("Aldo") && password==2)
			return "Bentornato Aldo!";
		else
			return "Nome o password errata.";
	}
	
	@GetMapping("/logPathVariables/{nome}/{password}")
	//logPathVariables/Aldo/2
	public String logPathVariables(@PathVariable String nome, @PathVariable int password) {
			if(nome.equals("Aldo") && password==2)
				return "Bentornato Aldo!";
			else
				return "Nome o password errata.";
	}
	
	
	/* MICRO_EXE2
	  Definire metodo che accetti i parametri della request 'operazione', 'n1' e 'n2' 
	  e produca una response a seconda dei loro valori: 
	  es. se operazione è 'somma', esegue somma e restituisce valore, ecc.
	*/
	
	@GetMapping("/operazione")
	//operazione?operazione=somma&n1=8&n2=2
	public String eseguiOperazione(@RequestParam String operazione, int n1, int n2) {
	    if ("somma".equals(operazione)) {
	        return "Risultato: " + (n1 + n2);
	    }
	    else if ("sottrazione".equals(operazione)) {
	        return "Risultato: " + (n1 - n2);
	    }
	    else if ("moltiplicazione".equals(operazione)) {
	        return "Risultato: " + (n1 * n2);
	    }
	    else if ("divisione".equals(operazione)) {
	        return "Risultato: " + (n1 / n2);
	    }
	    else {
	        return "Operazione non valida";
	    }
	}
	
	
	@GetMapping("/mygetclass")
	public MyClass myGetClass() {
	  MyClass myClass = new MyClass(1,"Alfa", 38);
	  return myClass;
	 }
	
	
	/* MicroExe
	Definire metodo e relativo mapping in grado di ricevere, tramite query string,
	i dati necessari a costruire un'istanza di MyClass e ritornarne la rappresentazione JSON
	*/

	    @GetMapping("/crea")
	    //crea?id=1&myString=Aldo&myInt=30
	    public MyClass creaOggetto(@RequestParam int id, String myString, int myInt) {
	        return new MyClass(id, myString, myInt);
	    }
	    
	  
	 /* MicroExe
	 1. Definire metodo e relativo mapping in grado di ricevere, tramite pathvariables, i dati necessari a costruire un'istanza di MyClass, aggiungere l'istanza a un apposito arraylist e ritornare la rappresentazione JSON dell'istanza
	 2. Definire metodo e relativo mapping in grado di iterare l'arraylist e ritornarne la rappresentazione JSON
	 */

	private List<MyClass> lista = new ArrayList<>();
    
    @GetMapping("/aggiungi/{id}/{myString}/{myInt}")
    //aggiungi/1/prova/10
    public MyClass aggiungi(@PathVariable int id, @PathVariable String myString, @PathVariable int myInt) {
        MyClass myclass = new MyClass(id, myString, myInt);
        lista.add(myclass);
        return myclass;
    }
    
    @GetMapping("/tutti")
    //tutti
    public List<MyClass> getTutti() {
        return lista;
    }
    
    @PostMapping("/mypostobj")
    public MyClass myPostObj(@RequestBody MyClass myClass) {
    	return myClass;
    }
    
    @DeleteMapping("deleteclassbymystring/{myString}")
    public boolean deleteClassByMyString(@PathVariable String myString) {
     return lista.removeIf(myClass -> myClass.getMyString().equalsIgnoreCase(myString));
    }
    
   /* @GetMapping("/mygetresponseentity")
    public ResponseEntity<String> myGetResponseEntity() {
     HttpHeaders myHttpHeaders = new HttpHeaders();
     myHttpHeaders.add(HttpHeaders.CONTENT_TYPE, "text/plain");
     //MyClass myBody = new MyClass(1,"Alfa", 33);
     String myBody= "Hello!";
     return new ResponseEntity<>(myBody, myHttpHeaders, HttpStatus.OK);
    }*/
    
    @PostMapping("/mypostresponseentity")
    public ResponseEntity<MyResponseBody> myPostResponseEntity(@RequestBody MyClass myClassReqBody) {
     HttpHeaders myHttpHeaders = new HttpHeaders();
     myHttpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
     lista.add(myClassReqBody);
     MyResponseBody myResBody = new MyResponseBody("Abbiamo aggiunto i dati alla lista");  
     return new ResponseEntity<>(myResBody, myHttpHeaders, HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/mygetresponseentity")
    public ResponseEntity<List<MyClass>> myGetResponseEntity() {
     HttpHeaders myHttpHeaders = new HttpHeaders();
     myHttpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
     List<MyClass> myBody= lista;
     return new ResponseEntity<>(myBody, myHttpHeaders, HttpStatus.OK);
    }
    
   /* @PostMapping("/save")
    public void mySave(@RequestBody MyClass myclass) {
    	myService.save(myclass);       	
    } */
    
    @PostMapping("/save")
    public String mySave(@RequestBody MyClass myclass) {
    	return myService.save(myclass);       	
    }
    
    @PutMapping("/update")
    public void myUpdate(@RequestBody MyClass myclass) {
    	myService.update(myclass);       	
    }
    
    @DeleteMapping("/delete")
    public void myDelete(@RequestParam int id) {
    	myService.delete(id);       	
    }
    
    @GetMapping("/findAll")
    public List<Map<String, Object>> myFindAll() {
    	return myService.findAll();       	
    }

}
