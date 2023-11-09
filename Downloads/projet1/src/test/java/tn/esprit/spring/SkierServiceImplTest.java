package tn.esprit.spring;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.*;
import tn.esprit.spring.services.ISkierServices;
import tn.esprit.spring.services.SkierServicesImpl;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SkierServiceImplTest {
    private ISkierRepository skierRepository;

    private IPisteRepository pisteRepository;

    private ICourseRepository courseRepository;

    private IRegistrationRepository registrationRepository;

    private ISubscriptionRepository subscriptionRepository;
    @Autowired
    SkierServicesImpl skierServices;

    @BeforeTestClass
    public void setUp() {
        // Initialisez l'instance de votre service ou utilisez l'injection de dépendances.
        skierServices = new SkierServicesImpl(skierRepository,pisteRepository,courseRepository,registrationRepository,subscriptionRepository); // Remplacez SkierServiceImpl par la classe réelle de votre service.
    }

    @Test
    @Order(6)
    public void testAssignSkierToSubscription() {
        // Créez un skieur et une souscription fictive
        Skier skier = new Skier();
        Subscription subscription = new Subscription();
        // Initialisez les propriétés du skieur et de la souscription

        // Appelez la méthode assignSkierToSubscription
        Skier assignedSkier = skierServices.assignSkierToSubscription(skier.getNumSkier(), subscription.getNumSub());

        // Vérifiez si la souscription du skieur correspond à celle que vous avez assignée
        assertEquals(subscription, assignedSkier.getSubscription());
    }
    @Test
    @Order(5)
    public void testAddSkierAndAssignToCourse() {
        // Créez un skieur et un cours fictif
        Skier skier = new Skier();
        Course course = new Course();
        // Initialisez les propriétés du skieur et du cours

        // Appelez la méthode addSkierAndAssignToCourse
        Skier assignedSkier = skierServices.addSkierAndAssignToCourse(skier, course.getNumCourse());

        // Vérifiez si le skieur a été correctement assigné au cours
        assertTrue(assignedSkier.getRegistrations().stream()
                .anyMatch(registration -> registration.getCourse().equals(course)));
    }
    @Test
    @Order(4)
    public void testRemoveSkier() {
        // Créez un skieur fictif
        Skier skier = new Skier();
        skier = skierServices.addSkier(skier);

        // Appelez la méthode removeSkier
        skierServices.removeSkier(skier.getNumSkier());

        // Vérifiez si le skieur a été supprimé en essayant de le récupérer (doit renvoyer null)
        assertNull(skierServices.retrieveSkier(skier.getNumSkier()));
    }
    @Test
    @Order(3)
    public void testRetrieveSkier() {
        // Créez un skieur fictif
        Skier skier = new Skier();
        skier = skierServices.addSkier(skier);

        // Appelez la méthode retrieveSkier pour récupérer le skieur
        Skier retrievedSkier = skierServices.retrieveSkier(skier.getNumSkier());

        // Vérifiez si le skieur récupéré correspond au skieur créé
        assertEquals(skier, retrievedSkier);
    }
    @Test
    @Order(7)
    public void testAssignSkierToPiste() {
        // Créez un skieur et une piste fictifs
        Skier skier = new Skier();
        Piste piste = new Piste();
        // Initialisez les propriétés du skieur et de la piste

        // Appelez la méthode assignSkierToPiste
        Skier assignedSkier = skierServices.assignSkierToPiste(skier.getNumSkier(), piste.getNumPiste());

        // Vérifiez si la piste a été correctement assignée au skieur
        assertTrue(assignedSkier.getPistes().contains(piste));
    }
    @Test
    @Order(8)
    public void testRetrieveSkiersBySubscriptionType() {
        // Créez des skieurs fictifs avec différents types de souscription
        Skier skier1 = new Skier();
        skier1.getSubscription().setTypeSub(TypeSubscription.ANNUAL);

        Skier skier2 = new Skier();
        skier2.getSubscription().setTypeSub(TypeSubscription.SEMESTRIEL);

        Skier skier3 = new Skier();
        skier3.getSubscription().setTypeSub(TypeSubscription.MONTHLY);

        // Ajoutez les skieurs à la base de données (utilisez votre service pour cela)

        // Appelez la méthode retrieveSkiersBySubscriptionType
        List<Skier> annualSkiers = skierServices.retrieveSkiersBySubscriptionType(TypeSubscription.ANNUAL);

        // Vérifiez si la liste contient les skieurs correspondant au type d'abonnement (dans cet exemple, les skieurs avec un abonnement annuel)
        assertEquals(1, annualSkiers.size()); // Vérifiez si le nombre de skieurs correspond à vos attentes
        assertTrue(annualSkiers.stream().allMatch(skier -> skier.getSubscription().getTypeSub() == TypeSubscription.ANNUAL));
    }
    @Test
    @Order(1)
    public void testAddSkier() {
        // Créez un Skier pour le test
        Skier newSkier = new Skier();
        Subscription subscription = new Subscription();
        LocalDate startDate = LocalDate.now();
        subscription.setStartDate(startDate);
        subscription.setTypeSub(TypeSubscription.ANNUAL); // Ou un autre type d'abonnement
        newSkier.setSubscription(subscription);

        // Appelez la méthode addSkier
        Skier addedSkier = skierServices.addSkier(newSkier);

        // Vérifiez si la méthode a correctement défini la date de fin d'abonnement
        LocalDate expectedEndDate = startDate.plusYears(1);
        assertEquals(expectedEndDate, addedSkier.getSubscription().getEndDate());
    }
    @Test
    @Order(2)
    public void testRetrieveAllSkiers() {
        // Appelez la méthode pour récupérer la liste des skieurs
        List<Skier> skiers = skierServices.retrieveAllSkiers();

        // Assurez-vous que la liste n'est pas nulle
        assertNotNull(skiers);

        // Ajoutez des assertions pour vérifier que la liste n'est pas vide ou qu'elle contient le nombre attendu de skieurs
        assertTrue(!skiers.isEmpty());
        // Vous pouvez également vérifier d'autres critères, comme la présence de skieurs spécifiques dans la liste

        // Vérifiez d'autres conditions spécifiques à votre application
        // Par exemple, si vous avez des critères de filtrage pour les skieurs, assurez-vous qu'ils sont correctement appliqués ici
    }

}
