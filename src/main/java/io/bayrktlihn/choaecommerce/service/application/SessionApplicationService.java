package io.bayrktlihn.choaecommerce.service.application;

import io.bayrktlihn.choaecommerce.exception.I18nSupportedException;
import io.bayrktlihn.choaecommerce.model.Customer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class SessionApplicationService {

    // repo yapılabilir output port yani ben doğrudan buraya göndüm. Hız için InMemory yaptım.
    private final Map<String, Customer> sessions = new HashMap<>();

    public boolean loggedIn(String sessionId) {
        synchronized (sessions) {
            return sessions.get(sessionId) != null;
        }
    }

    public Customer findCustomer(String sessionId) {
        synchronized (sessions) {
            return sessions.get(sessionId);
        }
    }

    public boolean customerLogged(String email) {
        synchronized (sessions) {
            return sessions.values().stream().filter(Objects::nonNull).map(Customer::getEmail).anyMatch(email::equals);
        }
    }

    public boolean anyCustomerLogged(String sessionId) {
        synchronized (sessions) {
            Customer customer = sessions.get(sessionId);
            return customer != null;
        }
    }

    public void logout(String sessionId) {

        boolean anyCustomerLogged = anyCustomerLogged(sessionId);
        if (!anyCustomerLogged) {
            throw I18nSupportedException.createWithDefaultMessage("Customer is already log out");
        }

        sessions.put(sessionId, null);

    }

    public void login(Customer customer, String sessionId) {
        synchronized (sessions) {
            if (customerLogged(customer.getEmail())) {
                throw I18nSupportedException.createWithDefaultMessage("Customer already logged in");
            }
            sessions.put(sessionId, customer);
        }
    }

    public void addSessionWithoutCustomerIfNotExists(String sessionId) {
        synchronized (sessions) {
            if (!sessions.keySet().stream().anyMatch(sessionId::equals)) {
                sessions.put(sessionId, null);
            }
        }
    }
}
