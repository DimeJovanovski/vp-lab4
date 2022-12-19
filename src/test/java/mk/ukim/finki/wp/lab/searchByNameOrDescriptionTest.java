package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.repository.jpa.BalloonRepository;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepository;
import mk.ukim.finki.wp.lab.service.impl.BalloonServiceImpl;
import mk.ukim.finki.wp.lab.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class searchByNameOrDescriptionTest {
    @Mock
    private BalloonRepository balloonRepository;

    private BalloonServiceImpl balloonService;

    private static List<Balloon> balloonList;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Manufacturer manufacturer = new Manufacturer("Manufacturer test", "Test address", "Test country");
        Balloon balloon = new Balloon("Test balloon", "A test balloon", manufacturer);
        this.balloonList = new ArrayList<>();
        this.balloonList.add(balloon);
        Mockito.when(this.balloonRepository.findAllByNameOrDescription("Test balloon", "Test balloon")).thenReturn(balloonList);

        this.balloonService = Mockito.spy( new BalloonServiceImpl(this.balloonRepository) );
    }

    @Test
    public void testSuccessFindByNameOrDescription() {
        List<Balloon> balloonList1 = this.balloonService.searchByNameOrDescription("Test balloon");
        Mockito.verify(this.balloonService).searchByNameOrDescription("Test balloon");
        Balloon balloon1 = balloonList1.get(0);
        Assert.assertNotNull("Balloon is null", balloonList);
        Assert.assertEquals("Balloon doesn't have a name", "Test balloon", balloon1.getName());
        Assert.assertEquals("Balloon doesn't have a description", "A test balloon", balloon1.getDescription());
    }

    @Test
    public void testForHalfEmptyFindByNameOrDescription() {
        List<Balloon> balloonList1 = this.balloonService.searchByNameOrDescription("Test");
        Mockito.verify(this.balloonService).searchByNameOrDescription("Test");
        Assert.assertNotNull("Balloon is not null", balloonList);
        Assert.assertEquals("Balloon list is not empty", 0, balloonList1.size());
    }

    @Test
    public void testForNullFindByNameOrDescription() {
        List<Balloon> balloonList1 = this.balloonService.searchByNameOrDescription(null);
        Mockito.verify(this.balloonService).searchByNameOrDescription(null);
        Assert.assertNotNull("Balloon is not null", balloonList);
        Assert.assertEquals("Balloon list is not empty", 0, balloonList1.size());
    }

    @Test
    public void testForNumberFindByNameOrDescription() {
        String ascii = "084101115116032098097108108111111110";
        List<Balloon> balloonList1 = this.balloonService.searchByNameOrDescription(ascii);
        Mockito.verify(this.balloonService).searchByNameOrDescription(ascii);
        Assert.assertNotNull("Balloon is not null", balloonList);
        Assert.assertEquals("Balloon list is not empty", 0, balloonList1.size());
    }
}
