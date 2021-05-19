package com.revature.shop.accounts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest {
    @Mock
    private AccountRepository repo;

    @Test
    public void testPoints() {
        repo = mock(AccountRepository.class);

        Account account = new Account(1, "test", 100);

        when(repo.findById(1)).thenReturn(Optional.of(account));
        when(repo.save(account)).thenReturn(account);

        AccountService service = new AccountService(repo);

        // Test if account exists
        Assertions.assertTrue(service.modPoints(1, 0));
        Assertions.assertFalse(service.modPoints(2, 0));

        // Test modification to points
        Assertions.assertTrue(service.modPoints(1, -40));
        Assertions.assertEquals(60, account.getPoints());

        Assertions.assertTrue(service.modPoints(1, -60));
        Assertions.assertEquals(0, account.getPoints());

        Assertions.assertFalse(service.modPoints(1, -5));
        Assertions.assertEquals(0, account.getPoints());

        Assertions.assertTrue(service.modPoints(1, 1));
        Assertions.assertEquals(1, account.getPoints());
    }
}