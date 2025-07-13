package infra.auth.config

data class DatabaseEnvironment(
    val driverClassName: String,
    val jdbcUrl: String,
    val username: String,
    val password: String,
    val maximumPoolSize: Int = 10
) {
    companion object {
        fun fromEnvironment(): DatabaseEnvironment {
            return DatabaseEnvironment(
                driverClassName = System.getenv("DB_DRIVER") ?: "org.postgresql.Driver",
                jdbcUrl = System.getenv("DB_URL") ?: "jdbc:postgresql://localhost:5432/singularity_auth",
                username = System.getenv("DB_USERNAME") ?: "postgres",
                password = System.getenv("DB_PASSWORD") ?: "postgres",
                maximumPoolSize = System.getenv("DB_MAX_POOL_SIZE")?.toIntOrNull() ?: 10
            )
        }
        
        fun development(): DatabaseEnvironment {
            return DatabaseEnvironment(
                driverClassName = "org.postgresql.Driver",
                jdbcUrl = "jdbc:postgresql://localhost:5432/singularity_auth_dev",
                username = "postgres",
                password = "postgres"
            )
        }
        
        fun testing(): DatabaseEnvironment {
            return DatabaseEnvironment(
                driverClassName = "org.h2.Driver",
                jdbcUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;",
                username = "sa",
                password = ""
            )
        }
    }
}
