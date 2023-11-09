package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.*;
import tn.esprit.spring.services.SkierServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SkierServiceImplMock {
    @Mock
    ISkierRepository skierRepository;
    @Mock
    private ISubscriptionRepository subscriptionRepository;
    @Mock
    private ICourseRepository courseRepository;
    @Mock
    private IRegistrationRepository registrationRepository;
    @Mock
    private IPisteRepository pisteRepository;
    @InjectMocks
    SkierServicesImpl skierServices;

//    @Test
//    public void testRetrieveAllSkiers() {
//        List<Skier> skiers = new ArrayList<>();
//        // Ajoutez des Skiers à la liste skiers
//
//        when(skierRepository.findAll()).thenReturn(skiers);
//
//        List<Skier> result = skierServices.retrieveAllSkiers();
//
//        assertEquals(skiers, result);
//    }

    @Test
    public void testAddSkier() {
        Skier skier = new Skier();
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        skier.setSubscription(subscription);

        when(skierRepository.save(skier)).thenReturn(skier);

        Skier result = skierServices.addSkier(skier);

        assertEquals(skier, result);
    }
//    @Test
//    public void testAssignSkierToSubscription() {
//        Skier skier = new Skier();
//        Subscription subscription = new Subscription();
//        when(skierRepository.findById(anyLong())).thenReturn(Optional.of(skier));
//        when(subscriptionRepository.findById(anyLong())).thenReturn(Optional.of(subscription));
//
//        Skier result = skierServices.assignSkierToSubscription(1L, 2L);
//
//        assertEquals(subscription, result.getSubscription());
//    }
//
//    @Test
//    public void testAddSkierAndAssignToCourse() {
//        Skier skier = new Skier();
//        Course course = new Course();
//        when(courseRepository.getById(anyLong())).thenReturn(course);
//
//        Skier result = skierServices.addSkierAndAssignToCourse(skier, 1L);
//
//        assertEquals(course, result.getRegistrations().iterator().next().getCourse());
//    }
//
//    @Test
//    public void testRemoveSkier() {
//        skierServices.removeSkier(1L);
//
//        verify(skierRepository).deleteById(1L);
//    }
//
//    @Test
//    public void testRetrieveSkier() {
//        Skier skier = new Skier();
//        when(skierRepository.findById(anyLong())).thenReturn(Optional.of(skier));
//
//        Skier result = skierServices.retrieveSkier(1L);
//
//        assertEquals(skier, result);
//    }
//
//    @Test
//    public void testAssignSkierToPiste() {
//        Skier skier = new Skier();
//        Piste piste = new Piste();
//        when(skierRepository.findById(anyLong())).thenReturn(Optional.of(skier));
//        when(pisteRepository.findById(anyLong())).thenReturn(Optional.of(piste));
//
//        Skier result = skierServices.assignSkierToPiste(1L, 2L);
//
//        assertTrue(result.getPistes().contains(piste));
//    }

//    @Test
//    public void testRetrieveSkiersBySubscriptionType() {
//        List<Skier> skiers = new ArrayList<>();
//        // Ajoutez des Skiers à la liste skiers
//        when(skierRepository.findBySubscription_TypeSub(any(TypeSubscription.class))).thenReturn(skiers);
//
//        List<Skier> result = skierServices.retrieveSkiersBySubscriptionType(TypeSubscription.ANNUAL);
//
//        assertEquals(skiers, result);
//    }
}
