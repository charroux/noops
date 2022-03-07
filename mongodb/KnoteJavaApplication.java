package io.learnk8s.knote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.*;

@SpringBootApplication
public class KnoteJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnoteJavaApplication.class, args);
    }

}

interface EmployeeRepository extends MongoRepository<Employee, String> {

}

@Document(collection = "employee")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Employee {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private String telephone;
    private Organisation orga;


    @Override
    public String toString() {
        return prenom + "<br>" + nom + "<br>" + telephone + "<br>" + orga;
    }
}
@Document(collection = "organisation")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Organisation {
    @Id
    private String id;
    private String nom;
    private String adresse;
    private String telephone;


    @Override
    public String toString() {
        return adresse + "<br>" + nom + "<br>" + telephone;
    }
}


class EmployeeList {
    private List<Employee> employees;

    public EmployeeList() {

        employees = new ArrayList<>();

    }

    public List<Employee> getList(){
        return employees;
    }
    public void setList(ArrayList e){
        employees = e;
    }
}

@RestController
class KNoteController {



    @Autowired
    private EmployeeRepository EmployeeRepository;


    private Parser parser = Parser.builder().build();
    private HtmlRenderer renderer = HtmlRenderer.builder().build();


    @GetMapping("/")
    public String index(Model model) {
        getAllEmploye();
        return "index2";
    }
    @GetMapping("/t")
    public EmployeeList hello(){
        System.out.println(getAllEmploye().getList());
        return getAllEmploye();
    }

    @GetMapping("/delete")
    public void delete(){

        removeAllEmploye();
    }


    @PostMapping("/employee")
    public void createEmployee(@Valid @RequestBody Employee employee) {
        System.out.println(employee.getNom()+"dadazdza");
       saveNote(employee.getNom(), employee.getPrenom(), employee.getTelephone(),  employee.getOrga());


    }


    private EmployeeList getAllEmploye() {
         EmployeeList employee = new EmployeeList();
         ArrayList temp = new ArrayList() ;
         temp.addAll(EmployeeRepository.findAll());
         Collections.reverse(temp);
         employee.setList(temp);



        return employee;
    }
    private void removeAllEmploye() {

        EmployeeRepository.deleteAll();

    }



    private void saveNote(String nom,String prenom,String telephone,  Organisation orga) {
        if (nom != null && !nom.trim().isEmpty()) {
            //We need to translate markup to HTML
            Node document = parser.parse(nom.trim());
            String htmlNom = renderer.render(document);

            document = parser.parse(prenom.trim());
            String htmlPrenom = renderer.render(document);

            document = parser.parse(telephone.trim());
            String htmltelephone = renderer.render(document);


            EmployeeRepository.save(new Employee(null, htmlNom, htmlPrenom, htmltelephone, orga ));


        }
    }

}