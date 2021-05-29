package com.revature.shop.accounts;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest {
    @Mock
    private AccountRepository repo;
    @Mock
    private PointRepository pointsRepo;

    @Test
    public void testPoints() {
        repo = mock(AccountRepository.class);
        pointsRepo = mock(PointRepository.class);

        Account account = new Account(1, "test", 100);

        when(repo.findById(1)).thenReturn(Optional.of(account));
        when(repo.save(account)).thenReturn(account);

        AccountService service = new AccountService(repo, pointsRepo, null, null);

        // Test if account exists
        assertTrue(service.modPoints(1, new PointChange("test", 0)));
        assertFalse(service.modPoints(2, new PointChange("test", 0)));

        // Test modification to points
        assertTrue(service.modPoints(1, new PointChange("test", -40)));
        assertEquals(60, account.getPoints());

        assertTrue(service.modPoints(1, new PointChange("test", -60)));
        assertEquals(0, account.getPoints());

        assertFalse(service.modPoints(1, new PointChange("test", -5)));
        assertEquals(0, account.getPoints());

        assertTrue(service.modPoints(1, new PointChange("test", 1)));
        assertEquals(1, account.getPoints());
    }
}