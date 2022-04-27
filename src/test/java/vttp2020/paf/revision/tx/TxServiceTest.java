package vttp2020.paf.revision.tx;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import vttp2020.paf.revision.tx.repositories.AccountRepository;
import vttp2020.paf.revision.tx.services.FundsTransferService;

public class TxServiceTest {
    @MockBean
    private AccountRepository acctRepo;

    @Autowired
    private FundsTransferService transferSvc;

    public void depositShouldSucceed() {

        Mockito.when(acctRepo.withdraw("fred", 1000f)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, 
            () -> transferSvc.transfer("fred", "barney", 1000f));

    }
    
}

