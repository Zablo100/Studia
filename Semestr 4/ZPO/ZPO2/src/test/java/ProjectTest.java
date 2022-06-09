package resources.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import com.project.model.Projekt;
import com.project.model.Zadanie;
import com.project.model.Student;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectTest {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    @BeforeAll
    public static void init() throws FileNotFoundException, SQLException {
        entityManagerFactory = Persistence.createEntityManagerFactory("testHsqlManager");
        entityManager = entityManagerFactory.createEntityManager();
    }
    @AfterAll
    public static void afterAll() {
        entityManager.close();
        entityManagerFactory.close();
    }
    // --- URUCHAMIANIE TESTÓW ---
    // ABY URUCHOMIĆ TESTY KLIKNIJ NA NAZWIE KLASY PRAWYM PRZYCISKIEM
    // MYSZY I WYBIERZ Z MENU 'Run As' -> 'Gradle Test' LUB PO USTAWIENIU
    // KURSORA NA NAZWIE KLASY WCIŚNIJ SKRÓT 'CTRL+ALT+X' A PÓŹNIEJ 'G'
    // MOŻNA RÓWNIEŻ ANALOGICZNIE URUCHAMIAĆ POJEDYNCZE METODY KLIKAJĄC
    // WCZEŚNIEJ NA ICH NAZWĘ
    // PO ZAKOŃCZENIU TESTÓW W PODKATALOGU PROJEKTU DOSTĘPNY JEST SZCZEGÓŁOWY RAPORT
    // project-jpa-client\build\reports\tests\test\index.html,
    // JEGO ADRES DRUKOWANY JEST W KONSOLI, GDY JAKIŚ TEST ZAKOŃCZY SIĘ BŁĘDEM NP.
    // file:///C:/eclipse-2018-09/workspace/project-jfx-client/build/reports/tests/test/index.html
    @Test
    @Order(1)
    public void dodawanieProjektuZZadaniami() {
        Projekt projekt = new Projekt("Aplikacji webowa", "Aplikacja w Javie", LocalDate.of(2020, 6, 19));
        Zadanie zadanie1 = new Zadanie("Instalacja kontenera serwletów", 1, "Instalacja serwera Tomcat 9.0.33");
        Zadanie zadanie2 = new Zadanie("Implementacja aplikacji", 2, "Zgodna z wzorcem MVC");
        //przypisujemy do zadań projekt
        zadanie1.setProjekt(projekt);
        zadanie2.setProjekt(projekt);
        entityManager.getTransaction().begin();
        entityManager.persist(zadanie1); //utrwalanie zawsze dla wszystkich obiektów - projektu i jego zadań
        entityManager.persist(zadanie2);
        entityManager.persist(projekt);
        entityManager.getTransaction().commit();
        entityManager.refresh(projekt); //odświeżenie stanu zarządzanej encji
        //na podstawie informacji z bazy danych
        //sprawdzamy czy w bazie danych do projektu zostały przypisane zadania
        List<Zadanie> zadania = projekt.getZadania();
        assertNotNull(zadania);
        assertEquals(2, zadania.size());

        System.out.printf("Projekt - Id: %d, Nazwa: %s%n", projekt.getProjektId(), projekt.getNazwa());
        for (Zadanie zad : zadania) {
            System.out.printf(" Zadanie - Id: %d, Nazwa: %s%n", zad.getZadanieId(), zad.getNazwa());
        }
    }
    @Test
    @Order(2)
    public void usuwanieProjektuZZadaniami() {
        // TODO
        // Pamiętaj, że dane w powiązanych tabelach naszego modelu nie będą
        // automatycznie modyfikowane (ON DELETE NO ACTION, ON UPDATE NO ACTION).
        // Przy każdej próbie usuwania, czy modyfikacji projektu, do którego są
        // odwołania przez wartości kluczy obcych, zawsze generowany będzie błąd,
        // a polecenie DELETE lub UPDATE wycofywane.
        // Należy zatem zacząć od usuwania zadań.
    }
    @Test
    @Order(3)
    public void dodawanieProjektuZeStudentamiIZadaniami() {
        // TODO
    }

    @Test
    @Order(4)
    public void wyszukiwanieProjektuZeStudentamiIZadaniemTomcat() {
        // TODO
        // Pobierz projekty, do których zostało przypisanych co najmniej dwóch studentów
        // i które maja w nazwie lub opisie jakiegokolwiek zadania słowo 'Tomcat'.
    }

    @BeforeEach
    public void before(TestInfo testInfo) {
        System.out.printf("-- METODA -> %s%n", testInfo.getTestMethod().get().getName());
    }
    @AfterEach
    public void after(TestInfo testInfo) {
        System.out.printf("<- KONIEC -- %s%n", testInfo.getTestMethod().get().getName());
    }
}
