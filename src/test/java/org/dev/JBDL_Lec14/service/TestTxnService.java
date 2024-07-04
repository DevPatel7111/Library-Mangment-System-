package org.dev.JBDL_Lec14.service;

import org.dev.JBDL_Lec14.model.Transition;
import org.dev.JBDL_Lec14.repositary.TxnRepositary;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class) // Use SpringRunner to run the tests with Spring's support
@SpringBootTest // Load the Spring application context for testing
public class TestTxnService {

    @InjectMocks
    private TxnService txnService;

    // Inject TxnService from Spring context

    @Mock
    private  UserService userService;
    @Mock
    private  BookService bookService;

    @Mock
    private TxnRepositary txnRepositary;
    @Before
    public void setUp() {
       txnService = new TxnService();
        ReflectionTestUtils.setField(txnService, "bookValidUpto", 12);
        ReflectionTestUtils.setField(txnService, "bookFineAmountPerDay", 5);
        MockitoAnnotations.initMocks(this);
        // Any setup code specific to this test class
    }

    @Test
    public void testCalculateFine() throws ParseException {
        // Prepare input data
        Date creationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-06-13");
        Transition txn = Transition.builder().creationDate(creationDate).build();

        // Call the method to test
        int calculatedFine = txnService.calculateFine(txn, 100);

        // Assert the result
//        assertEquals(20, calculatedFine); // Adjust based on actual calculation logic
    }
}
