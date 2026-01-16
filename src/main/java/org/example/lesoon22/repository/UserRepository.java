package org.example.lesoon22.repository;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BigDecimal getBalance(Long userId) {
        return jdbcTemplate.queryForObject(
                "SELECT balance FROM users WHERE id = ?",
                BigDecimal.class,
                userId
        );
    }

    public boolean exists(Long userId) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM users WHERE id = ?",
                Integer.class,
                userId
        );
        return count != null && count > 0;
    }

    public void updateBalance(Long userId, BigDecimal newBalance) {
        jdbcTemplate.update(
                "UPDATE users SET balance = ? WHERE id = ?",
                newBalance, userId
        );
    }
}
