package org.example.lesoon22.repository;

@Repository
public class PaymentRepository {

    private final JdbcTemplate jdbcTemplate;

    public PaymentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long createPayment(Long userId, BigDecimal amount, String status) {
        Long id = jdbcTemplate.queryForObject("SELECT nextval('payment_seq')", Long.class);

        jdbcTemplate.update(
                "INSERT INTO payments VALUES (?,?,?,?,?)",
                id, userId, amount, status, Timestamp.valueOf(LocalDateTime.now())
        );
        return id;
    }

    public void updateStatus(Long paymentId, String status) {
        jdbcTemplate.update(
                "UPDATE payments SET status = ? WHERE id = ?",
                status, paymentId
        );
    }

    public List<PaymentReadResponse> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM payments ORDER BY created_at DESC",
                rowMapper
        );
    }

    public List<PaymentReadResponse> findByUserId(Long userId) {
        return jdbcTemplate.query(
                "SELECT * FROM payments WHERE user_id = ? ORDER BY created_at DESC",
                rowMapper,
                userId
        );
    }

    public PaymentReadResponse findById(Long paymentId) {
        List<PaymentReadResponse> list = jdbcTemplate.query(
                "SELECT * FROM payments WHERE id = ?",
                rowMapper,
                paymentId
        );
        return list.isEmpty() ? null : list.get(0);
    }

    private final RowMapper<PaymentReadResponse> rowMapper = (rs, i) ->
            new PaymentReadResponse(
                    rs.getLong("id"),
                    rs.getLong("user_id"),
                    rs.getBigDecimal("amount"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at").toLocalDateTime()
            );
}
